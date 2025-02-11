package com.telus.csm.ess.core.domain;

import java.io.Serializable;

import com.telus.csm.ess.common.domain.CoreResponseBaseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;

public class UpdateShoppingCartCoreResponse extends CoreResponseBaseVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private ShoppingCartVO shoppingCart;

	public ShoppingCartVO getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCartVO shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	
}
