package com.telus.csm.ewlnsc.adapter.woscs.domain;

public class CreateOrderAdapterRequest {

	// https://soa-mp-kidc-it03.tsl.telus.com:443/v1/cmo/ordermgmt/ordershoppingcart/customer/92193557/shoppingcartorder?
	// cid=1492018702784&returnorderdetails=false&prevalidation=true&ordercreation=true

	/* URI PARAMS elements */
	private String customerid;
	
	/* REQUEST PARAMS elements */
	private String cid;
	private String returnorderdetails;
	private String prevalidation;
	private String ordercreation;
	
	/* REQUEST HEADER elements */
	private String stickysessionid;
	
	/* REQUEST BODY elements */
	private CreateOrderAdapterRequestBody body;
	
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
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
	public CreateOrderAdapterRequestBody getBody() {
		
		if (body == null) {
			body = new CreateOrderAdapterRequestBody();
		}
		return body;
	}
	public void setBody(CreateOrderAdapterRequestBody body) {
		this.body = body;
	}
}
