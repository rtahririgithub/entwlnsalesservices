package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOrderItem implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected String actionDesc;
	protected String stateDesc;
	protected boolean isShoppingCartOrder;
	protected String action;
	protected long id;
	protected Product product;
	protected String state;
	
// QC61279 begin 	
//	protected ProductCharacteristicValue partyInteractionRole;
	protected InteractionRole partyInteractionRole;
	
	public InteractionRole getPartyInteractionRole() {
		return partyInteractionRole;
	}

	public void setPartyInteractionRole(InteractionRole partyInteractionRole) {
		this.partyInteractionRole = partyInteractionRole;
	}
// QC61279 end
	
	public String getActionDesc() {
		return actionDesc;
	}
	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}
	public String getStateDesc() {
		return stateDesc;
	}
	public void setStateDesc(String stateDesc) {
		this.stateDesc = stateDesc;
	}
	public boolean isShoppingCartOrder() {
		return isShoppingCartOrder;
	}
	public void setShoppingCartOrder(boolean isShoppingCartOrder) {
		this.isShoppingCartOrder = isShoppingCartOrder;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
// QC61279 begin 	
//	public ProductCharacteristicValue getPartyInteractionRole() {
//		return partyInteractionRole;
//	}
//	public void setPartyInteractionRole(
//			ProductCharacteristicValue partyInteractionRole) {
//		this.partyInteractionRole = partyInteractionRole;
//	}
// QC61279 end 
}
