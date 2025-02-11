/**
 * 
 */
package com.telus.csm.ess.core.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ess.rest.domain.CreateShoppingCart;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductRequestVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationResultVO;
import com.telus.csm.ewlnsc.helper.OperationHeaderUtil;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.helper.ShoppingCartInputValidationHelper;
import com.telus.csm.ewlnsc.helper.ShoppingCartValidationHelper;
import com.telus.csm.ewlnsc.task.GetAssignedAndPendingProductTask;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;
import com.telus.csm.ewlnsc.util.workmanager.ICommonJWorkManager;
import com.telus.csm.ewlnsc.util.workmanager.WorkManagerFactory;

import commonj.work.Work;

/**
 * @author x145592
 *
 */
@Component
public class CreateShoppingCartCoreOperation {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(CreateShoppingCartCoreOperation.class);
	private ShoppingCartValidationResultVO validationResult;
	private ICommonJWorkManager workManager;

	@Autowired
	private com.telus.csm.ewlnsc.adapter.IWirelineSalesEJBAdapter wirelineSalesEJBAdapter;

	public CreateShoppingCartCoreOperation() {
		try {
			workManager = WorkManagerFactory.getCommonJWorkManager();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

	public ShoppingCartVO execute(final CreateShoppingCart request, ShoppingCartVO shoppingCartVO) {
		String functionName = "CreateShoppingCartCoreOperation.execute()";
		LOGGER.enter(functionName);
		// step 1) input validation
		ShoppingCartInputValidationHelper inputValidationHelper = new ShoppingCartInputValidationHelper();
		validationResult = inputValidationHelper.execute(shoppingCartVO);
		if (!validationResult.isValid() || !CollectionUtils.isEmpty(validationResult.getValidationResults())) {
			shoppingCartVO.setValidationResultList(validationResult.getValidationResults());
			shoppingCartVO.setValidShoppingCart(false);
			return shoppingCartVO;
		}

		// step 2) populate common object
		ShoppingCartContextHelper contextHelper = new ShoppingCartContextHelper();
		ShoppingCartContextVO contextVO = contextHelper.execute(shoppingCartVO);

		// step 3) applying cart validation rules
		ShoppingCartValidationHelper validationHelper = new ShoppingCartValidationHelper();
		validationResult = validationHelper.execute(contextVO);
		
		if (validationResult.isValid()) {
			
			//set cart and item's status
			if ( shoppingCartVO.isCartContextTypeForAll() ) {
				shoppingCartVO.setStatus(ShoppingCartStatus.NEW);
				shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.NEW.getCode() );
			} else {
				shoppingCartVO.setStatus(ShoppingCartStatus.PREPARED);
				shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.PREPARED.getCode() );
			}
			
			//String shoppingCartId = SalesContextDelegate.getInstance().putShoppingCart(shoppingCartVO);
			String shoppingCartId = ShoppingCartDelegate.getInstance().saveNewShoppingCart(shoppingCartVO);
			
			shoppingCartVO.setShoppingCartId(shoppingCartId);
			if (!CollectionUtils.isEmpty(validationResult.getValidationResults())) {
				LOGGER.debug(functionName,
						"Validation Result List is not empty but Validation result is Valid, WARN scenario.");
				shoppingCartVO.setValidationResultList(validationResult.getValidationResults());
			}
			shoppingCartVO.setValidShoppingCart(true);

			/*
			 * For valid SC, fire AssiognePendingTask that goes to OP directly.
			 */
			if (shoppingCartVO.getCustomer() != null
					&& !StringUtils.isEmpty(shoppingCartVO.getCustomer().getCustomerId())) {
				// Prepare the parallel task to later populate the commonDO object
				List<Work> tasksList = new ArrayList<Work>();

				GetAssignedAndPendingProductRequestVO taskRequest = new GetAssignedAndPendingProductRequestVO();
				taskRequest.setBillingAccountNumber(shoppingCartVO.getBillingAccount().getBillingAccountNumber());
				taskRequest.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
				taskRequest.setOperationHeader(OperationHeaderUtil.buildOperationHeader(
						shoppingCartVO.getOperationHeader().getSalesTransactionId(), shoppingCartVO));
				
				//QC74221 - GetAssignedAndPendingProductTask2 refractoring
				GetAssignedAndPendingProductTask getAsssignedAndPendingProductTask
					= new GetAssignedAndPendingProductTask(taskRequest, null, wirelineSalesEJBAdapter);
				
				tasksList.add(getAsssignedAndPendingProductTask);
				workManager.processAsyncTasks(tasksList);
			}

		} else {
			if (!validationResult.isValid() && !CollectionUtils.isEmpty(validationResult.getValidationResults())) {
				shoppingCartVO.setValidationResultList(validationResult.getValidationResults());
				shoppingCartVO.setValidShoppingCart(false);
			}
		}
		LOGGER.exit(functionName);
		return shoppingCartVO;
	}

}
