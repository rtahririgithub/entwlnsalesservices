package com.telus.csm.ewlnsc.adapter.woscs.domain;

public class SubmitOrderAdapterRequest {
	
	// http://cmf-orderprocsvc2-wrnk-itn01.tsl.telus.com/ome-shoppingcart-svc-1_0/shoppingcartorder/519679959/submitorder
	// https://soa-mp-kidc-it01.tsl.telus.com/v1/cmo/ordermgmt/ordershoppingcart/shoppingcartorder/519679959/submitorder

	// EMPTY MESSAGE

	/* REQUEST HEADER elements */
	private String orderid;
	private String mode;

	public String getOrderid() {
		return orderid;
	}

	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

}
