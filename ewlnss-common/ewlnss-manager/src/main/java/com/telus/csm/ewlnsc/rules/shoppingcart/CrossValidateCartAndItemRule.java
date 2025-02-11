package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;

public class CrossValidateCartAndItemRule extends AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO> {



	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContext, List<ShoppingCartValidationTraceVO> traces) {

		//TODO
		//Retrieves available TV channel/Packs by GTM.
		//Filters out those add-on that are not available to the location

		return true;
	}

}
