package com.telus.csm.ess.rest.operation;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ess.core.domain.UpdateShoppingCartCoreResponse;
import com.telus.csm.ess.core.operation.UpdateShoppingCartCoreOperation;
import com.telus.csm.ess.rest.domain.MessageType;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ess.rest.domain.ShoppingCart;
import com.telus.csm.ess.rest.domain.ShoppingCartErrorResponse;
import com.telus.csm.ess.rest.domain.ShoppingCartResponse;
import com.telus.csm.ess.rest.domain.UpdateShoppingCart;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.rest.exception.EssRestSystemErrorException;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.transformer.ShoppingCartTransformer;
import com.telus.csm.ewlnsc.util.JsonUtil;

public class UpdateShoppingCartRestOperation {

	private static final Logger logger = Logger.getLogger(UpdateShoppingCartRestOperation.class);
	private static final String DEFAULT_ERROR_CODE = "ESS_UPDATE_SC_90002";

	public UpdateShoppingCartRestOperation() {
		
	}

	public ShoppingCartResponse execute(String shoppingCartId, UpdateShoppingCart request) {
		ShoppingCartVO shoppingCart = null;
		UpdateShoppingCartCoreResponse coreResponse=null;
		try {
			ShoppingCartResponse response = new ShoppingCartResponse();
			response.setShoppingCartId(shoppingCartId);
			List<ResponseMessage> errors = validateInput(shoppingCartId, request);
			if(errors != null && !errors.isEmpty()) {
				ShoppingCartErrorResponse err = new ShoppingCartErrorResponse();
				err.setResponseMessages(errors);
				throw new EssRestErrorException(err);
			}
			
//			GetShoppingCartCoreOperation getOp = new GetShoppingCartCoreOperation();
//			shoppingCartVOin = getOp.execute(shoppingCartId);
//			if(shoppingCartVOin == null) {
//				ShoppingCartErrorResponse err = new ShoppingCartErrorResponse();
//				ResponseMessage responseMessagesItem = new ResponseMessage();
//				MessageType messagesItem = new MessageType();
//				messagesItem.setMessageTxt("Shopping cart is not found. shoppingCartId: " + shoppingCartId);
//				responseMessagesItem.addMessagesItem(messagesItem);
//				err.addResponseMessagesItem(responseMessagesItem);
//				throw new EssRestErrorException(err);
//			}
	
			shoppingCart = ShoppingCartTransformer.transformRestToShoppingCart(shoppingCartId, request.getShoppingCart());
			shoppingCart.setOperationHeader(ShoppingCartTransformer.transformOperationHeaderFromRest(request.getOperationHeader()));
			logger.debug("ShoppingCartVO content is: " + JsonUtil.getJsonFromObjectNonNUll(shoppingCart));
			
			UpdateShoppingCartCoreOperation op = new UpdateShoppingCartCoreOperation();
			coreResponse = op.execute(request.getOperationHeader().getSalesTransactionId(), shoppingCart);
			if(coreResponse.isHasError()) {
				ShoppingCartErrorResponse err = EnterpriseWLNCommonTransformer.transformToShoppingCartErrorResponse(coreResponse.getMessageList(), coreResponse.getShoppingCart());
				throw new EssRestErrorException(err);
			}
			if(coreResponse.getShoppingCart() !=null && !CollectionUtils.isEmpty(coreResponse.getShoppingCart().getValidationResultList()) && !coreResponse.getShoppingCart().isValidShoppingCart()){
				ShoppingCartErrorResponse err = EnterpriseWLNCommonTransformer.transformShoppingCartException(coreResponse.getShoppingCart());
				throw new EssRestErrorException(err);
			}
			ShoppingCart shoppingCartRest = ShoppingCartTransformer.transformShoppingCartToRest(coreResponse.getShoppingCart());
			response.setShoppingCart(shoppingCartRest);
			if(coreResponse.getShoppingCart().isValidShoppingCart() && !CollectionUtils.isEmpty(coreResponse.getShoppingCart().getValidationResultList())){
				response.setResponseMessages(EnterpriseWLNCommonTransformer.transformWarnMessages(coreResponse.getShoppingCart().getValidationResultList()));
			}
			return response;
		} catch(Exception e) {
			ShoppingCartErrorResponse err=null;
			if(coreResponse != null && coreResponse.getShoppingCart() !=null && !CollectionUtils.isEmpty(coreResponse.getShoppingCart().getValidationResultList())){
				err = EnterpriseWLNCommonTransformer.transformShoppingCartException(coreResponse.getShoppingCart());
			}else if(coreResponse != null && coreResponse.isHasError()){
				err = EnterpriseWLNCommonTransformer.transformToShoppingCartErrorResponse(coreResponse.getMessageList(), coreResponse.getShoppingCart());
			}
			else{
				err = EnterpriseWLNCommonTransformer.transformToShoppingCartErrorResponse(e, DEFAULT_ERROR_CODE, shoppingCart);
			}
			throw new EssRestSystemErrorException(err);
		}
	}

	private List<ResponseMessage> validateInput(String shoppingCartId, UpdateShoppingCart request) {
		ArrayList<ResponseMessage> errors = new ArrayList<ResponseMessage>();
		if(StringUtils.isEmpty(shoppingCartId)) {
			//TODO
			ResponseMessage err = new ResponseMessage();
			MessageType msg = new MessageType();
			msg.setMessageTxt("shoppingCartId is missing");
			errors.add(err);
		}
		if(request == null || request.getShoppingCart() == null) {
			//TODO
			ResponseMessage err = new ResponseMessage();
			MessageType msg = new MessageType();
			msg.setMessageTxt("shoppingCart is missing");
			errors.add(err);
		}
		//TODO
		return errors;
	}

}
