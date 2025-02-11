package com.telus.csm.ewlnsc.task;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;

public class ShoppingCartValidationTask extends TaskBase {

	AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO> validationRule;
	ShoppingCartContextVO shoppingCart;
	List<ShoppingCartValidationTraceVO> traces = null;
	
	public ShoppingCartValidationTask(AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO> validationRule, ShoppingCartContextVO shoppingCart) {
		this.validationRule = validationRule;
		this.shoppingCart = shoppingCart;
	}
	@Override
	protected void execute() {
		
		traces = new ArrayList<ShoppingCartValidationTraceVO>();
		validationRule.isSatisfiedBy(shoppingCart, traces);
		traces = validationRule.getResult();
	}

	public List<ShoppingCartValidationTraceVO> getResult() {
		return traces;
	}
}
