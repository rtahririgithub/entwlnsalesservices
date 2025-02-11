package com.telus.csm.ewlnsc.domain;

public class SubmitWirelineOrderRequestVO {

	private String orderId;
	private String shoppingCartId;
	
	public void setShoppingCartId(String shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	/**
	 * 
	 * @return
	 */
	public boolean isManualOrder() {
		return false;
	}
	/**
	 * 
	 * @return
	 */
	public String getOrderId() {
		return orderId;
	}
	/**
	 * 
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getShoppingCartId() {
		return shoppingCartId;
	}

}
