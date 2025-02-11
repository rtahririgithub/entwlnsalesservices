package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class EquipmentNotFoundInOffer extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

		private static final LoggerUtil logger = LoggerUtil.getLogger(EquipmentNotFoundInOffer.class);
		
		@Override
		public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
			super.isSatisfiedBy(ctxVO, traces);
			String functionName="CustomerNotFoundRule.isSatisfiedBy";
			/**
			 * Given: customerId is present in the cart.
			 * When: customer cannot be retrieved from CCMS.getFullCustomerInfo
			 * Then: return warning message and continue processing.
			 */
			logger.enter(functionName);
			boolean isSatisfied=true;
			ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
			String shppoingCartId = ctxVO.getShoppingCartVO().getShoppingCartId();		
			
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(CustomerNotFoundRule.class.getName());
			
			if(shoppingCartVO!=null && !CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){
				for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
					if(cartItem.isSalesOrderItem()) {
						if(cartItem.getProducts()!=null){
							if(cartItem.getProducts().getInternetProduct()!=null){
							}
							if(cartItem.getProducts().getHomePhoneProduct()!=null){
							}
							if(cartItem.getProducts().getTelevisionProduct()!=null){
							}
						}
					}
				}
			}
			
			return isSatisfied;
		}
}
