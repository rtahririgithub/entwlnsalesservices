package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;

public class AutomatedCOTNotSupportedRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO , List<ShoppingCartValidationTraceVO> traces){
		boolean isSatisfied=false;
		
		/**
		 * Cart includes at least one cartItem with SING, HSIC or TTV product
		 */
		
		
		
		return isSatisfied;
	}
}
