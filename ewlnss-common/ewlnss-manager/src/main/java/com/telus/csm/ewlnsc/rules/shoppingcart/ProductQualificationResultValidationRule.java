package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ProductQualificationResultValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductQualificationResultValidationRule.class);

	public ProductQualificationResultValidationRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied=true;
		String functionName = "ProductQualificationResultValidationRule.isSatisfiedBy()";
		logger.enter(functionName);
		
		String cartItemRelationId=null;
		
		if(cartItem!=null){
			if(!StringUtils.isBlank(cartItem.getCartItemRelationId())){
				cartItemRelationId = cartItem.getCartItemRelationId();
			}
			
			if(shoppingCartContextVO!=null && shoppingCartContextVO.getProductQualificationAdapterResponseVO()!=null){
				logger.debug(functionName, "ProductQualificationResponseVO is not null in ShoppingCartContextVO, checking the result");
				
				
			}
			
		}
		
		logger.exit(functionName);
		return isSatisfied;
	
	}
	
	
	
}
