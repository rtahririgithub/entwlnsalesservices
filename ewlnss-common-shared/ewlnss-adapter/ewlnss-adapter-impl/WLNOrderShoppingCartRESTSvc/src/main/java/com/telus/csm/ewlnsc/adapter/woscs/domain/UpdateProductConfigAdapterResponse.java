package com.telus.csm.ewlnsc.adapter.woscs.domain;


import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UpdateProductConfigAdapterResponse extends WlnOPRestSvcResponseBase {
		
	private List<ProductCharacteristic> productCharacteristics;
	private String typeDesc;
	private String statusDesc;
	private boolean isShoppingCartOrder;
	private boolean isTaxationCalledInd;
	private String id;
	private PartyInteractionRole partyInteractionRole;
	private List<ProductOrderItem> productOrderItems;
	
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
