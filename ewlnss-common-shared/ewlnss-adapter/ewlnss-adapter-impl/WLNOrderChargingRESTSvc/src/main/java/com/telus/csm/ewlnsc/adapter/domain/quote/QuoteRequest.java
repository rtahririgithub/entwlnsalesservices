package com.telus.csm.ewlnsc.adapter.domain.quote;

public class QuoteRequest {
	
	// GET https://soa-mp-kidc-it02.tsl.telus.com/v1/cmo/ordermgmt/charge/shoppingcartorder/545669315/quote

	private String orderId; 
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	

}
