package com.telus.csm.ewlnsc.adapter.woscs.domain;


import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class ValidateProductConfigAdapterResponse extends WlnOPRestSvcResponseBase {
	
	private String orderItemId;
	private String status;
	private String validationItem;
	private List<ProductCharacteristic> productCharacteristics;
	private String typeDesc;
	private String type;
	private String statusDesc;
	private boolean isShoppingCartOrder;
	
	private boolean proratedQuoteInd;
	private boolean isTaxationCalledInd;
	private String id;
	private PartyInteractionRole partyInteractionRole;
	private List<ProductOrderItem> productOrderItems;

	public String getStatus() {
		return status;
	}
	public String getOrderItemId() {
		return orderItemId;
	}
	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getValidationItem() {
		return validationItem;
	}
	public void setValidationItem(String validationItem) {
		this.validationItem = validationItem;
	}
	public List<ProductCharacteristic> getProductCharacteristics() {
		return productCharacteristics;
	}
	public void setProductCharacteristics(List<ProductCharacteristic> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public boolean isShoppingCartOrder() {
		return isShoppingCartOrder;
	}
	public void setShoppingCartOrder(boolean isShoppingCartOrder) {
		this.isShoppingCartOrder = isShoppingCartOrder;
	}
	public boolean isProratedQuoteInd() {
		return proratedQuoteInd;
	}
	public void setProratedQuoteInd(boolean proratedQuoteInd) {
		this.proratedQuoteInd = proratedQuoteInd;
	}
	public boolean isTaxationCalledInd() {
		return isTaxationCalledInd;
	}
	public void setTaxationCalledInd(boolean isTaxationCalledInd) {
		this.isTaxationCalledInd = isTaxationCalledInd;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public PartyInteractionRole getPartyInteractionRole() {
		return partyInteractionRole;
	}
	public void setPartyInteractionRole(PartyInteractionRole partyInteractionRole) {
		this.partyInteractionRole = partyInteractionRole;
	}
	public List<ProductOrderItem> getProductOrderItems() {
		return productOrderItems;
	}
	public void setProductOrderItems(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}
	
}
