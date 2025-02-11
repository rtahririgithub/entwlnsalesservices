package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOrder implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected List<ProductCharacteristicValue> productCharacteristics;
	protected String typeDesc;
	protected String statusDesc;
	protected boolean shoppingCartOrder;
	protected boolean proratedQuoteInd;
	protected boolean taxationCalledInd;
	protected String id;
	protected InteractionRole partyInteractionRole;
	protected List<ProductOrderItem> productOrderItems;
	protected String status;
	protected String type;
	
	public List<ProductCharacteristicValue> getProductCharacteristics() {
		return productCharacteristics;
	}
	public void setProductCharacteristics(
			List<ProductCharacteristicValue> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public boolean isShoppingCartOrder() {
		return shoppingCartOrder;
	}
	public void setIsShoppingCartOrder(boolean shoppingCartOrder) {
		this.shoppingCartOrder = shoppingCartOrder;
	}
	
	public boolean isProratedQuoteInd() {
		return proratedQuoteInd;
	}
	public void setProratedQuoteInd(boolean proratedQuoteInd) {
		this.proratedQuoteInd = proratedQuoteInd;
	}
	public boolean isTaxationCalledInd() {
		return taxationCalledInd;
	}
	public void setIsTaxationCalledInd(boolean taxationCalledInd) {
		this.taxationCalledInd = taxationCalledInd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public InteractionRole getPartyInteractionRole() {
		return partyInteractionRole;
	}
	public void setPartyInteractionRole(InteractionRole partyInteractionRole) {
		this.partyInteractionRole = partyInteractionRole;
	}
	public List<ProductOrderItem> getProductOrderItems() {
		return productOrderItems;
	}
	public void setProductOrderItems(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
}
