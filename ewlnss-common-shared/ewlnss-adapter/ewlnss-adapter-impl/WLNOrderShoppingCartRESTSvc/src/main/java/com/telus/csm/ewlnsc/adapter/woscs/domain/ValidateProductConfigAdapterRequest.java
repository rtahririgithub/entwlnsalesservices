package com.telus.csm.ewlnsc.adapter.woscs.domain;

public class ValidateProductConfigAdapterRequest {
	
	// http://cmf-orderprocsvc2-wrnk-itn02.tsl.telus.com/ome-shoppingcart-svc-1_0/shoppingcartorder/532940315/productconfigvalidation
	// https://soa-mp-kidc-it02.tsl.telus.com/v1/cmo/ordermgmt/ordershoppingcart/shoppingcartorder/532940315/productconfigvalidation

	// EMPTY MESSAGE

	private int orderId; 
	private boolean outboundChannel;
	private boolean returnOrderDetails;
	
	public boolean isReturnOrderDetails() {
		return returnOrderDetails;
	}
	public void setReturnOrderDetails(boolean returnOrderDetails) {
		this.returnOrderDetails = returnOrderDetails;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public boolean isOutboundChannel() {
		return outboundChannel;
	}
	public void setOutboundChannel(boolean outboundChannel) {
		this.outboundChannel = outboundChannel;
	}
	 
	
	

}
