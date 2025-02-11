package com.telus.csm.ess.rest.operation;


import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ess.rest.domain.CreateCartItem;
import com.telus.csm.ess.rest.domain.CartItemResponse;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ess.core.operation.CreateCartItemCoreOperation;

public class CreateCartItemRestOperation {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(CreateCartItemRestOperation.class);
	
	public CartItemResponse execute(String shoppingCartId, CreateCartItem request) {
		String functionName = "CreateCartItemRestOperation->execute";
		logger.enter(functionName);
		CartItemResponse response = new CartItemResponse();
		CreateCartItemCoreOperation op = new CreateCartItemCoreOperation();
		
		List<ResponseMessage> errors = validateInput(shoppingCartId,request);
		if(errors != null && !errors.isEmpty()) {
			response.setResponseMessages(errors);
			return response;
		}
		
		CartItemResponse cartItemVO = op.execute(shoppingCartId, request);
		
		return cartItemVO;
	}

	private List<ResponseMessage> validateInput(String shoppingCartId, CreateCartItem request) {
		ArrayList<ResponseMessage> errors = new ArrayList<ResponseMessage>();
		if(StringUtils.isBlank(shoppingCartId)){
			ResponseMessage err = new ResponseMessage();
			err.errorCode(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()));
			errors.add(err);
		}
		//TODO: need to find out inputValidation for CartItem request
		return errors;
	}
	
}
