package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetProductsByCustomerIdAdapterResponse extends WlnOPRestSvcResponseBase implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Product> products;

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}