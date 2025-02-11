package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class RelatedCartItemVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static enum CARTITEM_TYPE {SALE, DISCONNECT, GWP, PERK};
	private String cartItemId;
	private String cartItemType;
	public String getCartItemId() {
		return cartItemId;
	}
	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}
	public String getCartItemType() {
		return cartItemType;
	}
	public void setCartItemType(String cartItemType) {
		this.cartItemType = cartItemType;
	}
	

}
