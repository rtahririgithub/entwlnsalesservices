package com.telus.csm.ewlnsc.adapter.woscs.domain;

import java.util.ArrayList;
import java.util.List;

public class UpdateOrderAdapterRequestBody {

	/* REQUEST BODY elements */
	private String id;
	private String type;
	private PartyInteractionRole partyInteractionRole;
	//private PartyInteractionRole feasibilityInterviewQuestions;
	private List<ProductOrderItem> productOrderItems;
	
	
	public void addProductOrderItem(ProductOrderItem param) {
		if (productOrderItems == null) {
			productOrderItems = new ArrayList<ProductOrderItem>();
		}
		
		productOrderItems.add(param);
	}
	public List<ProductOrderItem> getProductOrderItems() {
		return productOrderItems;
	}
	public void setProductOrderItems(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
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
//	public PartyInteractionRole getFeasibilityInterviewQuestions() {
//		return feasibilityInterviewQuestions;
//	}
//	public void setFeasibilityInterviewQuestions(PartyInteractionRole feasibilityInterviewQuestions) {
//		this.feasibilityInterviewQuestions = feasibilityInterviewQuestions;
//	}
	public void setId(String opShoppingCartId) {
		// TODO Auto-generated method stub
		this.id = opShoppingCartId;
	}
	public String getId() {
		return id;
	}

	
}
