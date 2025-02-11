package com.telus.csm.ewlnsc.adapter.woscs.domain;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class UpdateOrderAdapterResponse extends WlnOPRestSvcResponseBase {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String type;
	private String typeDesc;
	private String statusDesc;
	private Boolean shoppingCartOrder;
	private String status;
	  
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
	
	public Boolean isShoppingCartOrder() {
		return shoppingCartOrder;
	}
	public void setShoppingCartOrder(Boolean shoppingCartOrder) {
		this.shoppingCartOrder = shoppingCartOrder;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
