package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

public class CartItemValidationRule extends AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO> {
	
	private static String ALL = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_CTX_TYPE_ALL;
	private static String PROMOTION = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_PROMOTION;
	private static String SWEETENER = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_SWEETENER;
	private static String EQUIPMENT = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_EQUIPMENT;
	private static String ADDON = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_ADDON;
	private static String OFFER = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_OFFER;
	private static String PRODUCT_ITEMS = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_PRODUCT_ITEMS;
	private static String FEATURE = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_FEATURE;

	CartItemVO cartItem = null;
	ShoppingCartContextVO contextVO = null;
	
	public CartItemValidationRule(CartItemVO cartItem, ShoppingCartContextVO shoppingCartContextVO) {
		this.cartItem = cartItem;
		this.contextVO = shoppingCartContextVO;
	}
	
	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO contextVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(contextVO, traces);
		
		boolean isSatisfied=true;
		 
		List<AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>> specList = new ArrayList<AbstractSpecification<ShoppingCartContextVO,ShoppingCartValidationTraceVO>>();
		
		if (cartItem!=null && !CollectionUtils.isEmpty(cartItem.getCartItemContextTypeList())){
			
			specList.add(new PortInDateNotMatchingProvideDateRule(cartItem));
			
			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL})) {
				specList.add(new WinBackWithoutDisconnectCartItemRule(cartItem));
				specList.add(new BookingDateNotProvidedRule(cartItem));
			}
//Commented out. Jul., 2019. The rule to be handled by the Promotion Qualification app		
//			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, PROMOTION})) {
//				specList.add(new DiscountCrossProductDependencyRule(cartItem));
//			}
			
			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, SWEETENER})) {
				specList.add(new CartItemSweetenerEligibilityRule(cartItem));
			}

			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, EQUIPMENT})) {
				specList.add(new ShippedEquipmentMissingShippingAddress(cartItem));
			}
			
			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, ADDON})) {
				specList.add(new DiscountMissingAddOnRule(cartItem));
			}

			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, OFFER})) {
				specList.add(new InternetProductQualificationRule(cartItem));
				//specList.add(new InternetFeasibilityRule(cartItem)); removed due to Jira story NGSP-7105
				specList.add(new AccessoryOfferValidationRule(cartItem));
				specList.add(new PortsAvailableRule(cartItem));
			}

			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, PRODUCT_ITEMS})) {
				//specList.add(new ProductItemValidationRule(cartItem));
				//specList.add(new ProductItemMissingParentFromMarketOfferRule(cartItem));
			}

			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, FEATURE})) {
				specList.add(new CallRevealWithoutCallDisplayRule(cartItem));
				specList.add(new SmartringListingWithoutFeatureRule(cartItem));
				specList.add(new SmartringFeatureWithoutListingRule(cartItem));
			}

			if (EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{ALL, PROMOTION, EQUIPMENT, ADDON, OFFER, FEATURE})) {
				specList.add(new MarketOfferValidationRule(cartItem));
				specList.add(new ValidatePromotionListRule(cartItem));

			}

		}
		
		for (AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> spec: specList){ 
			boolean isSatify = spec.isSatisfiedBy(contextVO, traces);
			isSatisfied = isSatisfied && isSatify;
			if(!CollectionUtils.isEmpty(getResult())){
				traces = getResult();
			}
			
		}

		return isSatisfied;
	}
	
}
