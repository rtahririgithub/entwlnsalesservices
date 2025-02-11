package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ShoppingCartValidationRule extends AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO> {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ShoppingCartValidationRule.class);

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCart,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCart, traces);
		String functionName="ValidateShoppingCartRule.isSatisfiedBy";	
		logger.enter(functionName);
		String cartContextType = "";
				
		if (shoppingCart.getShoppingCartVO()!= null && !CollectionUtils.isEmpty(shoppingCart.getShoppingCartVO().getCartContextTypeList())){
			cartContextType = shoppingCart.getShoppingCartVO().getCartContextTypeList().get(0);
		}
		
		boolean isSatisfied = true;
		
		List<AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>> specList = new ArrayList<AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO>>();
		
		if(EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_NEW.equalsIgnoreCase(cartContextType)){
			
			specList.add(new DistinctProductTypeRule());
			specList.add(new ServiceAddressNotFoundRule());
			specList.add(new CustomerNotFoundRule());
			//spec = spec.and(new AccountNotFoundRule());
		}
		
		if(EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_ALL.equalsIgnoreCase(cartContextType)){
			specList.add(new DistinctProductTypeRule());
			specList.add(new ServiceAddressNotFoundRule());
			specList.add(new CustomerNotFoundRule());
			//spec = spec.and(new AccountNotFoundRule());
		}
		
		if(EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_UPDATE.equalsIgnoreCase(cartContextType)){
			specList.add(new ServiceAddressNotFoundRule());
			specList.add(new CustomerNotFoundRule());
			//spec = spec.and(new AccountNotFoundRule());
			specList.add(new DistinctProductTypeRule());
		}
		
		for (AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> spec: specList){ 
			isSatisfied = isSatisfied && spec.isSatisfiedBy(shoppingCart, traces);
			if(!CollectionUtils.isEmpty(getResult())){
				traces = getResult();
			}
			
		}
							
		return isSatisfied;
	}
	
}
