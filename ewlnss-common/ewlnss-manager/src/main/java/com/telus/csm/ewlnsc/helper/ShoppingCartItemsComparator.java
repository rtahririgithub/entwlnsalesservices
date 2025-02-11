package com.telus.csm.ewlnsc.helper;

import java.util.Comparator;

import com.telus.csm.ewlnsc.domain.CartItemVO;

public class ShoppingCartItemsComparator implements Comparator<CartItemVO> {

	@Override
	public int compare(CartItemVO cartItem1, CartItemVO cartItem2) {
		// Sorting the cart items in the following order:
		// 1. SalesOrder, 2. DisconnectOrder, 3. PerkOrderItem, 4. GiftWithPurchaseOrder

		if (cartItem1.isSalesOrderItem()) {
			if (cartItem2.isSalesOrderItem()) {
				return 0;
			}
			else if (cartItem2.isDisconnectOrderItem()) {
				return -1;
			}
			else if (cartItem2.isPerkOrderItem()) {
				return -1;				
			}
			else if (cartItem2.isGwpOrderItem()) {
				return -1;
			}
		}
		else if (cartItem1.isDisconnectOrderItem()) {
			if (cartItem2.isSalesOrderItem()) {
				return 1;
			}
			else if (cartItem2.isDisconnectOrderItem()) {
				return 0;
			}
			else if (cartItem2.isPerkOrderItem()) {
				return -1;				
			}
			else if (cartItem2.isGwpOrderItem()) {
				return -1;
			}
		}
		else if (cartItem1.isPerkOrderItem()) {
			if (cartItem2.isSalesOrderItem()) {
				return 1;
			}
			else if (cartItem2.isDisconnectOrderItem()) {
				return 1;
			}
			else if (cartItem2.isPerkOrderItem()) {
				return 0;				
			}
			else if (cartItem2.isGwpOrderItem()) {
				return -1;
			}
		}
		else if (cartItem1.isGwpOrderItem()) {
			if (cartItem2.isSalesOrderItem()) {
				return 1;
			}
			else if (cartItem2.isDisconnectOrderItem()) {
				return 1;
			}
			else if (cartItem2.isPerkOrderItem()) {
				return 1;				
			}
			else if (cartItem2.isGwpOrderItem()) {
				return 0;
			}
		}

		return 0;
	}
}
