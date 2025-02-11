package com.telus.csm.ewlnsc.adapter.domain;


import com.telus.csm.commerce.sc.domain.TelusShoppingCart;
import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class GetCommerceShoppingCartAdapterResponse extends WlnOPRestSvcResponseBase {

	private static final long serialVersionUID = 1L;
	private TelusShoppingCart shoppingCart;
	
	public TelusShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(TelusShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
