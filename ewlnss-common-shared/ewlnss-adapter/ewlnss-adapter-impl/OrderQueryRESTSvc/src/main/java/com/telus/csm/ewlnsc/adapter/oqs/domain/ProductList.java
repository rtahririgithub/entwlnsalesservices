package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

public class ProductList implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

}
