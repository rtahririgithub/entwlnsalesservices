package com.telus.csm.ewlnsc.adapter.woscs.domain;

import java.util.List;

public class UpdateProductConfigAdapterRequest {
	
	// https://soa-mp-kidc-it03.tsl.telus.com:443/v1/cmo/ordermgmt/ordershoppingcart/customer/92193557/shoppingcartorder?
	// cid=1492018702784&returnorderdetails=false&prevalidation=true&ordercreation=true

	// http://cmf-orderprocsvc2-wrnk-itn03.tsl.telus.com/ome-shoppingcart-svc-1_0/shoppingcartorder/10004489?returnorderdetails=true
	// https://soa-mp-kidc-it03.tsl.telus.com/v1/cmo/ordermgmt/ordershoppingcart/shoppingcartorder/10004489

	private String id;
	private List<ProductOrderItem> productOrderItems;
	
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
	private String type;
	private PartyInteractionRole partyInteractionRole;
	private PartyInteractionRole feasibilityInterviewQuestions;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<ProductOrderItem> getProductOrderItems() {
		return productOrderItems;
	}
	public void setProductOrderItems(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}
	public String getCustomerid() {
		return customerid;
	}
	public void setCustomerid(String customerid) {
		this.customerid = customerid;
	}
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getReturnorderdetails() {
		return returnorderdetails;
	}
	public void setReturnorderdetails(String returnorderdetails) {
		this.returnorderdetails = returnorderdetails;
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
	public String getStickysessionid() {
		return stickysessionid;
	}
	public void setStickysessionid(String stickysessionid) {
		this.stickysessionid = stickysessionid;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public PartyInteractionRole getPartyInteractionRole() {
		return partyInteractionRole;
	}
	public void setPartyInteractionRole(PartyInteractionRole partyInteractionRole) {
		this.partyInteractionRole = partyInteractionRole;
	}
	public PartyInteractionRole getFeasibilityInterviewQuestions() {
		return feasibilityInterviewQuestions;
	}
	public void setFeasibilityInterviewQuestions(PartyInteractionRole feasibilityInterviewQuestions) {
		this.feasibilityInterviewQuestions = feasibilityInterviewQuestions;
	}
}
