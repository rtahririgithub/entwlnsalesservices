package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class WirelineOrderWithoutServiceAddressRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(WirelineOrderWithoutServiceAddressRule.class);
	private boolean isSatify = true;
	
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCart,List<ShoppingCartValidationTraceVO> traces){
		super.isSatisfiedBy(shoppingCart, traces);
		
		String functionName = "WirelineOrderWithoutServiceAddressRule.execute()";
		logger.enter(functionName);
		
		ShoppingCartVO shoppingCartVO = shoppingCart.getShoppingCartVO();
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(
				WirelineOrderWithoutServiceAddressRule.class.getName());
		
		//looping thru all cartItemSalesOrder
		//if any cartItemSalesOrder has SING/HSIC/TTV,
		//if shoppingCartVO input doesn't have serviceAddress element
		//then create traceVO
		
		boolean hasWlnOrderedProduct=false;
		
		if(shoppingCartVO!=null && !CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.isSalesOrderItem()) {
					if(cartItem.getProducts()!=null){
						if(cartItem.getProducts().getInternetProduct()!=null){
							hasWlnOrderedProduct=true;
							break;
						}
						if(cartItem.getProducts().getHomePhoneProduct()!=null){
							hasWlnOrderedProduct=true;
							break;
						}
						if(cartItem.getProducts().getTelevisionProduct()!=null){
							hasWlnOrderedProduct=true;
							break;
						}
					}
				}
				
			}
		}
		
		if(hasWlnOrderedProduct && (shoppingCartVO.getServiceAddress()==null || (StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getCityCode())) ||
				StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getProvinceCode()) || StringUtils.isEmpty(shoppingCartVO.getServiceAddress().getServiceAddressId()))){
			isSatify = false;
			trace.setValidationPassedInd(false);
			trace.setErrors(ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.SERVICE_ADDRESS_NOT_PROVIDED));
			logger.error(functionName + " failed because ServiceAddress was not correctly passed in the request");
			traces.add(trace);
		}
		
		return isSatify;
	}

}
