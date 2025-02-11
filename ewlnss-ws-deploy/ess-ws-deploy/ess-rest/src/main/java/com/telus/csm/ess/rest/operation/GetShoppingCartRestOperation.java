package com.telus.csm.ess.rest.operation;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.telus.csm.ess.rest.domain.GetShoppingCartErrorResponse;
import com.telus.csm.ess.rest.domain.MessageType;
import com.telus.csm.ess.rest.domain.ResponseMessage;
import com.telus.csm.ess.rest.domain.ShoppingCart;
import com.telus.csm.ess.rest.domain.ShoppingCartResponse;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.rest.exception.EssRestErrorException;
import com.telus.csm.ewlnsc.transformer.ShoppingCartTransformer;
import com.telus.csm.ess.core.operation.GetShoppingCartCoreOperation;

public class GetShoppingCartRestOperation {

	@SuppressWarnings("unused")
	private static final Logger logger = Logger.getLogger(GetShoppingCartRestOperation.class);

	public GetShoppingCartRestOperation() {
		
	}

	public ShoppingCartResponse execute(final String shoppingCartId) {
		ShoppingCartResponse response = new ShoppingCartResponse();
		response.setShoppingCartId(shoppingCartId);
		List<ResponseMessage> errors = validateInput(shoppingCartId);
		if(errors != null && !errors.isEmpty()) {
			response.setResponseMessages(errors);
			return response;
		}
		
		GetShoppingCartCoreOperation op = new GetShoppingCartCoreOperation();
		ShoppingCartVO shoppingCartVO = op.execute(shoppingCartId);
		if(shoppingCartVO == null) {
			GetShoppingCartErrorResponse err = new GetShoppingCartErrorResponse();
			ResponseMessage responseMessagesItem = new ResponseMessage();
			MessageType messagesItem = new MessageType();
			messagesItem.setMessageTxt("Shopping cart is not found. shoppingCartId: " + shoppingCartId);
			responseMessagesItem.addMessagesItem(messagesItem);
			err.addResponseMessagesItem(responseMessagesItem);
			throw new EssRestErrorException(err);
		}
		ShoppingCart shoppingCartRest = ShoppingCartTransformer.transformShoppingCartToRest(shoppingCartVO);
		response.setShoppingCart(shoppingCartRest);
		
		return response;
	}

	private List<ResponseMessage> validateInput(String shoppingCartId) {
		ArrayList<ResponseMessage> errors = new ArrayList<ResponseMessage>();
		if(StringUtils.isEmpty(shoppingCartId)) {
			//TODO
			ResponseMessage err = new ResponseMessage();
			err.errorCode(String.valueOf(Response.Status.BAD_REQUEST.getStatusCode()));
			errors.add(err);
		}
		return errors;
	}

	

}
