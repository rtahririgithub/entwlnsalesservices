package com.telus.csm.ewlnsc.adapter.woscs.domain;

public class UpdateOrderAdapterRequest {

	// https://soa-mp-kidc-it03.tsl.telus.com:443/v1/cmo/ordermgmt/ordershoppingcart/customer/92193557/shoppingcartorder?
	// cid=1492018702784&returnorderdetails=false&prevalidation=true&ordercreation=true

	/* URI PARAMS elements */
	private String orderId;
	
	/* REQUEST PARAMS elements */
	private String cid;
	private String returnorderdetails;
	private String prevalidation;
	private String ordercreation;
	
	/* REQUEST HEADER elements */
	private String stickysessionid;
	
	/* REQUEST BODY elements */
	private UpdateOrderAdapterRequestBody body;
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderid(String orderId) {
		this.orderId = orderId;
	}
	
	public String getReturnorderdetails() {
		return returnorderdetails;
	}
	public void setReturnorderdetails(String returnorderdetails) {
		this.returnorderdetails = returnorderdetails;
	}
	
	public String getStickysessionid() {
		return stickysessionid;
	}
	public void setStickysessionid(String stickysessionid) {
		this.stickysessionid = stickysessionid;
	}
	
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getPrevalidation() {
		return prevalidation;
	}
	public void setPrevalidation(String prevalidation) {
		this.prevalidation = prevalidation;
	}
	public String getOrdercreation() {
		return ordercreation;
	}
	public void setOrdercreation(String ordercreation) {
		this.ordercreation = ordercreation;
	}
	public UpdateOrderAdapterRequestBody getBody() {
		
		if (body == null) {
			body = new UpdateOrderAdapterRequestBody();
		}
		return body;
	}
	public void setBody(UpdateOrderAdapterRequestBody body) {
		this.body = body;
	}
}
