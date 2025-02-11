package com.telus.csm.ewlnsc.domain;

import java.util.List;

import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SalesResponseMessage;

public class ShoppingCartValidationTraceVO extends TraceVO {

	public static final String SHOPPING_CART = "SHOPPING CART";
	public static final String SHOPPING_CART_ITEM = "SHOPPING CART ITEM";

	private String shoppingCartId = null;
	private String shoppingCartItemId = null;
	private String cartItemRelationId = null;
	private List<SalesResponseMessage.MessageList> messageList;
	
	public String getCartItemRelationId() {
		return cartItemRelationId;
	}
	public void setCartItemRelationId(String cartItemRelationId) {
		this.cartItemRelationId = cartItemRelationId;
	}
	private boolean hasWarning;
	
	private ShoppingCartValidationErrors errors = null;

	public ShoppingCartValidationTraceVO(String ruleName) {
		super();
		this.setRuleName(ruleName);
	}
	public String toLogMessage() {
		StringBuilder logMessage = new StringBuilder();
				
		logMessage.append(super.toLogMessage());

		if (shoppingCartId != null) {
			logMessage.append(" shoppingCartId=" + shoppingCartId);
		}
		if (shoppingCartItemId != null) {
			logMessage.append(" shoppingCartItemId=" + shoppingCartItemId);
		}

		return logMessage.toString();
	}


	public String getShoppingCartId() {
		return shoppingCartId;
	}


	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}


	public String getShoppingCartItemId() {
		return shoppingCartItemId;
	}


	public void setShoppingCartItemId(String shoppingCartItemId) {
		this.shoppingCartItemId = shoppingCartItemId;
	}


	public ShoppingCartValidationErrors getErrors() {
		return errors;
	}


	public void setErrors(ShoppingCartValidationErrors errors) {
		this.errors = errors;
	}
	public boolean isHasWarning() {
		return hasWarning;
	}
	public void setHasWarning(boolean hasWarning) {
		this.hasWarning = hasWarning;
	}
	public List<SalesResponseMessage.MessageList> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<SalesResponseMessage.MessageList> messageList) {
		this.messageList = messageList;
	}
}
