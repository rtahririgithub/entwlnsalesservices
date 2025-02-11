package com.telus.csm.ewlnsc.rules.shoppingcart;


import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class CustomerNotFoundRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(CustomerNotFoundRule.class);
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		String functionName="CustomerNotFoundRule.isSatisfiedBy";
		/**
		 * Given: customerId is present in the cart.
		 * When: customer cannot be retrieved from CCMS.getFullCustomerInfo
		 * Then: return warning message and continue processing.
		 */
		logger.enter(functionName);
		boolean isSatisfied=true;
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = ctxVO.getShoppingCartVO().getShoppingCartId();		
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(CustomerNotFoundRule.class.getName());
		
		if(!ctxVO.getShoppingCartVO().isWirelineProspectCustomer() && shoppingCartVO.getCustomer()!=null && !StringUtils.isBlank(shoppingCartVO.getCustomer().getCustomerId())){
			logger.debug("CustomerNotFoundRule.execute()", "customerId it's present in ShoppingCart, checking if response for CCMS.getFullCustomerInfo is successful");
			if(ctxVO.getFullCustomerInfoAdapterResponse()==null || (ctxVO.getFullCustomerInfoAdapterResponse()!=null && ctxVO.getFullCustomerInfoAdapterResponse().getFullCustomer()==null)){
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.CUSTOMER_NOT_FOUND));
				trace.setShoppingCartId(shppoingCartId);
				trace.setHasWarning(true);
				traces.add(trace);
			}else{
				logger.debug(functionName, " executed successfully. Customer information was retrieved from downstream.");
			}
		}else{
			logger.debug(functionName, "Skipping rule since customerId was not passed in the shoppingCart or a wireline prospect customer.");
		}
		
		logger.exit(functionName);
		return isSatisfied;
	}

}
