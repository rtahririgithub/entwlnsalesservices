package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;

public class DistinctProductTypeRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCart,List<ShoppingCartValidationTraceVO> traces) {

		super.isSatisfiedBy(shoppingCart, traces);
		boolean isSatisfied=true;
		Set<String> orderedProductsSet = new HashSet<String>();
		String shppoingCartId = shoppingCart.getShoppingCartVO().getShoppingCartId();
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(DistinctProductTypeRule.class.getName());
		if (shoppingCart.getShoppingCartVO().getCartItemList() != null) {
			for (CartItemVO cartItem : shoppingCart.getShoppingCartVO().getCartItemList()) {
				if (cartItem.isSalesOrderItem()) {
					if (cartItem.getProducts() != null) {
						if (cartItem.getProducts().getHomePhoneProduct() != null) {
							if (orderedProductsSet.contains("HomePhone")) {
								trace.setValidationPassedInd(false);
								trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.DUPLICATE_SINGLE_LINE_PRODUCT_IN_CART));
								trace.setShoppingCartId(shppoingCartId);
								isSatisfied=false;
								traces.add(trace);
							} else {
								orderedProductsSet.add("HomePhone");
							}
						}
						if (cartItem.getProducts().getInternetProduct() != null) {
							if (orderedProductsSet.contains("Internet")) {
								trace.setValidationPassedInd(false);
								trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.DUPLICATE_INTERNET_PRODUCT_IN_CART));
								trace.setShoppingCartId(shppoingCartId);
								isSatisfied=false;
								traces.add(trace);
							} else {
								orderedProductsSet.add("Internet");
							}
						}
						if (cartItem.getProducts().getTelevisionProduct() != null) {
							if (orderedProductsSet.contains("Television")) {
								trace.setValidationPassedInd(false);
								trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.DUPLICATE_TV_PRODUCT_IN_CART));
								trace.setShoppingCartId(shppoingCartId);
								isSatisfied=false;
								traces.add(trace);
							} else {
								orderedProductsSet.add("Television");
							}
						}
					}
				}
			}
		}
		
		return isSatisfied;

	}
}
