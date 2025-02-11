package com.telus.csm.ess.common.domain;

import java.io.Serializable;

public class ESDGShoppingCartVO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String jsonShoppingCart = null;
	
	public String getJsonShoppingCart() {
		return jsonShoppingCart;
	}

	public void setJsonShoppingCart(String jsonSales) {
		this.jsonShoppingCart = jsonSales;
	}

}
