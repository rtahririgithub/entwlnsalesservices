/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

/**
 * @author x145592
 *
 */
public class CartItemPriceVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String cartItemId = null;
	private List<ProductPriceVO> productPriceList = null;

	public String getCartItemId() {
		return cartItemId;
	}

	public void setCartItemId(String cartItemId) {
		this.cartItemId = cartItemId;
	}

	public List<ProductPriceVO> getProductPriceList() {
		return productPriceList;
	}

	public void setProductPriceList(List<ProductPriceVO> productPriceList) {
		this.productPriceList = productPriceList;
	}

	
}
