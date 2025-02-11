package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;


public class ShoppingCartInputValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	public boolean isSatisfiedBy(ShoppingCartContextVO contextVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(contextVO, traces);
		
		boolean isSatisfied=true;
		
		List<AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>> specList = new ArrayList<AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO>>();
		
		specList.add(new MandatoryElementValidationRule());
		specList.add(new UnsupportedElementRule());
		
		for (AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> spec: specList){ 
			isSatisfied = isSatisfied && spec.isSatisfiedBy(contextVO, traces);
			if(!CollectionUtils.isEmpty(getResult())){
				traces = getResult();
			}
		}
		
		return isSatisfied;
	}

}
