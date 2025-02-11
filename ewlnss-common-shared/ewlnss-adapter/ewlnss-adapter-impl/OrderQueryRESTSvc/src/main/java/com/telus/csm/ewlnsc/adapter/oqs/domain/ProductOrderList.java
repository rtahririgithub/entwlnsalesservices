package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

public class ProductOrderList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected List<ProductOrder> productOrders;

	public List<ProductOrder> getProductOrders() {
		return productOrders;
	}

	public void setProductOrders(List<ProductOrder> productOrders) {
		this.productOrders = productOrders;
	}
}
