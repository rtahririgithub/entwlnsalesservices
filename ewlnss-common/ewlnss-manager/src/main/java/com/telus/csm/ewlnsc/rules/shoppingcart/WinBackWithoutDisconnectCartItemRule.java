package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.DisconnectRequestTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class WinBackWithoutDisconnectCartItemRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;
	
	public WinBackWithoutDisconnectCartItemRule(CartItemVO cartItem){
		this.cartItem = cartItem;
	}
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(MarketOfferEquipmentValidationRule.class);
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied=true;
		String cartItemRelationId=null;
		String functionName="WinBackWithoutDisconnectCartItem";
		logger.enter(functionName);
		
		if(!StringUtils.isBlank(cartItem.getCartItemRelationId())){
			cartItemRelationId = cartItem.getCartItemRelationId();
		}
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(WinBackWithoutDisconnectCartItemRule.class.getName());
		
		
		List<String> marketOrderedProductList = getCartItemOrderedProduct(cartItem);
		
		if(!CollectionUtils.isEmpty(marketOrderedProductList)){
			logger.debug(functionName, "market offer products with winback were found in the cartItem, checking the disconnected products.");
			List<String> disconnectedOrderedProductList = getDisconnectedProductListFromCartItem(shoppingCartContextVO.getShoppingCartVO().getCartItemList());
			
			if(!CollectionUtils.isEmpty(marketOrderedProductList)){
				for(String marketOfferOrderedProduct : marketOrderedProductList){
					if(!disconnectedOrderedProductList.contains(marketOfferOrderedProduct)){
						isSatisfied = false;
						trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.WINBACK_WITHOUT_DISCONNECT_ORDER, this.getClass().getSimpleName() + ": It is necessary to include a disconnect cart item for a competitor product of the same type when setting winBackInd=true for the salesCartItem."));
						trace.setCartItemRelationId(cartItemRelationId);
						trace.setValidationPassedInd(isSatisfied);
						traces.add(trace);
					}
				}
			}
		}else{
			logger.debug(functionName,"Skipping rule since there are no market offer products with winback within the cartItem with carItemRelationId=" + cartItemRelationId);
		}
		logger.exit(functionName);
		return isSatisfied;
	}

	private List<String> getDisconnectedProductListFromCartItem(List<CartItemVO> list) {
		List<String> disconnectedProductList = new ArrayList<String>();
		for(CartItemVO cartItem : list) {
			if(cartItem.isDisconnectOrderItem()){
				if(!CollectionUtils.isEmpty(cartItem.getDisconnectRequestList())){
					for(DisconnectRequestTypeVO disconnectType : cartItem.getDisconnectRequestList()){
						if(!CollectionUtils.isEmpty(disconnectType.getProductServiceType())){
							for(String product : disconnectType.getProductServiceType()){
								disconnectedProductList.add(product);
							}
						}
					}
				}
			}
		}
		
		
		return disconnectedProductList;
	}

	private List<String> getCartItemOrderedProduct(CartItemVO cartItem) {
		List<String> orderedProductList = new ArrayList<String>();
		if(cartItem.isSalesOrderItem()) {
			if(cartItem.getProducts()!=null){
				if(cartItem.getProducts().getInternetProduct()!=null && cartItem.getProducts().getInternetProduct().getWinback()!=null && cartItem.getProducts().getInternetProduct().getWinback()){
					orderedProductList.add(EnterpriseWLNSalesServicesConstants.HSIC);
				}
				if(cartItem.getProducts().getHomePhoneProduct()!=null && cartItem.getProducts().getHomePhoneProduct().getWinback()!=null && cartItem.getProducts().getHomePhoneProduct().getWinback()){
					orderedProductList.add(EnterpriseWLNSalesServicesConstants.SING);
				}
				if(cartItem.getProducts().getTelevisionProduct()!=null && cartItem.getProducts().getTelevisionProduct().getWinback()!=null && cartItem.getProducts().getTelevisionProduct().getWinback()){
					orderedProductList.add(EnterpriseWLNSalesServicesConstants.TTV);
				}
			}
		}
		return orderedProductList;
	}

	
	
}
