package com.telus.csm.ewlnsc.helper;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.ProductSummaryVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartChange;


public class ShoppingCartComparatorResult {
      
	List<ShoppingCartChange> shoppingCartChangeList = new ArrayList<ShoppingCartChange>();
	
	public void addShoppingCartChange(ProductSummaryVO product, String changeType) {
		shoppingCartChangeList.add(new ShoppingCartChange(product, changeType));
	}

	public List<ShoppingCartChange> getShoppingCartChangeList() {
		return shoppingCartChangeList;
	}
    
	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (ShoppingCartChange change : shoppingCartChangeList) {
			buffer.append(change.toString());
		}
		return buffer.toString();
	}
}
