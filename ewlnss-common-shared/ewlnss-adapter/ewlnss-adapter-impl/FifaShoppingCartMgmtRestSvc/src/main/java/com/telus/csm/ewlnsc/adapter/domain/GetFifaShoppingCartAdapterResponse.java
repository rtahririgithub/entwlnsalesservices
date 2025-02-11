package com.telus.csm.ewlnsc.adapter.domain;


import com.telus.csm.fifa.sc.domain.ShoppingCart;
import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class GetFifaShoppingCartAdapterResponse extends WlnOPRestSvcResponseBase {

	private static final long serialVersionUID = 1L;
	private ShoppingCart shoppingCart;
	
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
