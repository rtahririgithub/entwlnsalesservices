package com.telus.csm.ess.core.posttask;

import com.telus.csm.ess.core.posttask.strategy.WirelinePostTaskScStrategy;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;

public class PostTaskStrategyFactory {

	static public IPostTaskStrategy getPostTaskStrategy(ShoppingCartContextVO shoppingCartContextVO) {
		if(shoppingCartContextVO != null && hasWirelineProducts(shoppingCartContextVO)) {
			return new WirelinePostTaskScStrategy();
		}
		return null;
	}

	private static boolean hasWirelineProducts(ShoppingCartContextVO shoppingCartContextVO) {
		if(shoppingCartContextVO != null && shoppingCartContextVO.getShoppingCartVO() != null && shoppingCartContextVO.getShoppingCartVO().getCartItemList() != null) {
			for(CartItemVO cartItm: shoppingCartContextVO.getShoppingCartVO().getCartItemList()) {
				if(cartItm.getProducts() != null && (cartItm.getProducts().getHomePhoneProduct() != null || cartItm.getProducts().getInternetProduct() != null || cartItm.getProducts().getTelevisionProduct() != null)) {
					return true;
				}
			}
		}
		return false;
	}
}
