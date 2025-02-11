/**
 * 
 */
package com.telus.csm.ess.core.operation;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ess.core.domain.UpdateShoppingCartCoreResponse;
import com.telus.csm.ewlnsc.delegate.SalesContextDelegate;
import com.telus.csm.ewlnsc.delegate.ShoppingCartDelegate;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationResultVO;
import com.telus.csm.ewlnsc.helper.ShoppingCartContextHelper;
import com.telus.csm.ewlnsc.helper.ShoppingCartInputValidationHelper;
import com.telus.csm.ewlnsc.helper.ShoppingCartValidationHelper;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.ShoppingCartStatus;


/**
 * @author x145592
 *
 */
public class UpdateShoppingCartCoreOperation {
	
	private ShoppingCartValidationResultVO validationResult;
	
	public UpdateShoppingCartCoreResponse execute(String transactionId, ShoppingCartVO shoppingCartVO) {
		UpdateShoppingCartCoreResponse response = new UpdateShoppingCartCoreResponse();
		
		GetShoppingCartCoreOperation getOp = new GetShoppingCartCoreOperation();
		ShoppingCartVO shoppingCartCached = getOp.execute(shoppingCartVO.getShoppingCartId());
		if(shoppingCartCached == null) {
			shoppingCartVO.setValidShoppingCart(false);
			response.setShoppingCart(shoppingCartVO);
			response.setHasError(true);
			response.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_UPDATE_SC_00005", "Shopping cart is not found. shoppingCartId: " + shoppingCartVO.getShoppingCartId()));
			return response;
		}
		
		//step#1: input validation
		ShoppingCartInputValidationHelper inputValidationHelper = new ShoppingCartInputValidationHelper();
		validationResult = inputValidationHelper.execute(shoppingCartVO);
		if (!validationResult.isValid() || !CollectionUtils.isEmpty(validationResult.getValidationResults()) ) {
			shoppingCartVO.setValidationResultList(validationResult.getValidationResults());
			shoppingCartVO.setValidShoppingCart(false);
			response.setShoppingCart(shoppingCartVO);
			response.setHasError(true);
			return response;
		}
		
		//step #2 : populate common object
		ShoppingCartContextHelper shoppingCartContextHelper = new ShoppingCartContextHelper();
		ShoppingCartContextVO shoppingCartContextVO = shoppingCartContextHelper.execute(shoppingCartVO);
		
//		//TODO retrieve 
//		OPShoppingCartDelegate opShoppingCartDelegate = new OPShoppingCartDelegate();
//		OPShoppingCartDelegateResponseVO retrieveOpShoppingCartResponse = opShoppingCartDelegate.retrieveOrder(shoppingCartCached);
//		shoppingCartContextVO.setProductOrder(retrieveOpShoppingCartResponse.getProductOrder());
//		
//		//TODO review
//		OPShoppingCartDelegateResponseVO opShoppingCartResponse = opShoppingCartDelegate.execute(shoppingCartContextVO, shoppingCartCached);
//		if(opShoppingCartResponse != null && !opShoppingCartResponse.hasError()) {
//			if(opShoppingCartResponse.getOpShoppingCartId() > 0) {
//				shoppingCartVO.setOpShoppingCartId(String.valueOf(opShoppingCartResponse.getOpShoppingCartId()));
//			}
//		} else {
//			shoppingCartVO.setValidShoppingCart(false);
//			response.setShoppingCart(shoppingCartVO);
//			response.setHasError(true);
//			response.setMessageList(EnterpriseWLNCommonTransformer.transformToMessageListVO("ESS_UPDATE_SC_00003", opShoppingCartResponse.getMessageList()));
//			return response;
//		}		
		
		//step #3: apply cart validation rules
		ShoppingCartValidationHelper shoppingCartValidationHelper = new ShoppingCartValidationHelper();
		validationResult = shoppingCartValidationHelper.execute(shoppingCartContextVO);
		
		if(validationResult.isValid()) {
			
			//set cart and item's status
			if ( shoppingCartVO.isCartContextTypeForAll() ) {
				shoppingCartVO.setStatus(ShoppingCartStatus.NEW);
				shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.NEW.getCode() );
			} else {
				shoppingCartVO.setStatus(ShoppingCartStatus.PREPARED);
				shoppingCartVO.setCartItemsStatus( ShoppingCartStatus.CartItemStatus.PREPARED.getCode() );
			}

			//SalesContextDelegate.getInstance().putShoppingCart(shoppingCartVO);
			ShoppingCartDelegate.getInstance().updateShoppingCart(shoppingCartVO);
			
			if(!CollectionUtils.isEmpty(validationResult.getValidationResults())){
				shoppingCartVO.setValidationResultList(validationResult.getValidationResults());
			}
			shoppingCartVO.setValidShoppingCart(true);
			response.setShoppingCart(shoppingCartVO);
		}else{
			if(validationResult!=null && !CollectionUtils.isEmpty(validationResult.getValidationResults())){
				shoppingCartVO.setValidationResultList(validationResult.getValidationResults());
				shoppingCartVO.setValidShoppingCart(false);
				response.setShoppingCart(shoppingCartVO);
			}
		}
		
		return response;
	}

}
