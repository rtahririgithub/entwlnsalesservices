package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class OPShoppingCartDelegateResponseVO extends WlnOPRestSvcResponseBase implements Serializable {
	private static final long serialVersionUID = 1L;
	private int opShoppingCartId;
	private ProductOrder productOrder;
	//used for Vesta update. 
	private List<ProductOrderItem> productOrderItems;

	public List<ProductOrderItem> getProductOrderItems() {
		return productOrderItems;
	}

	public void setProductOrderItems(List<ProductOrderItem> productOrderItems) {
		this.productOrderItems = productOrderItems;
	}

	public int getOpShoppingCartId() {
		return opShoppingCartId;
	}

	public void setOpShoppingCartId(int opShoppingCartId) {
		this.opShoppingCartId = opShoppingCartId;
	}

	public ProductOrder getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(ProductOrder productOrder) {
		this.productOrder = productOrder;
	}

	
	
}
