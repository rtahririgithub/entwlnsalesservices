package com.telus.csm.ewlnsc.adapter.woscs.domain;

import java.util.ArrayList;
import java.util.List;

public class CreateOrderAdapterRequestBody {

	/* REQUEST BODY elements */
	private String type;
	private PartyInteractionRole partyInteractionRole;
	//private PartyInteractionRole feasibilityInterviewQuestions;
	private List<ProductOrderItem> productOrderItems;
	private List<GiftWithPurchase> giftWithPurchase;
	
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

	public List<GiftWithPurchase> getGiftWithPurchase() {
		return giftWithPurchase;
	}
	public void setGiftWithPurchase(List<GiftWithPurchase> giftWithPurchase) {
		this.giftWithPurchase = giftWithPurchase;
	}
}