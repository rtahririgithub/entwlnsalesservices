package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO2;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.RelatedImmediatePromotionVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.AccessoryOffer;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class AccessoryOfferValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(AccessoryOfferValidationRule.class);

	private CartItemVO cartItemVO;
	
	public AccessoryOfferValidationRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		String functionName = "AccessoryOfferValidationRule.isSatisfiedBy()";
		logger.enter(functionName);

		boolean isSatisfied = true;
		
		GetOffersResponseVO2 eligibleAccessoryOffers = shoppingCartContextVO.getAvailableAccessoryResponse();
		List<String> ineligiblePromos = new ArrayList<String>();
		List<String> incompatibleDiscounts = new  ArrayList<String>();
		List<String> incompatibleDiscountsInCart = new  ArrayList<String>();
		List<String> incompatibleSweeetenersInCart = new  ArrayList<String>();
		if(cartItemVO != null && cartItemVO.isGwpOrderItem() && cartItemVO.getRelatedPromotionList() != null) {
			//For each selected GWP, check if its in the eligible list.
			for(RelatedImmediatePromotionVO selectedPromo :cartItemVO.getRelatedPromotionList()) {
				
				//Search in all eligible offers.
				if( eligibleAccessoryOffers != null && eligibleAccessoryOffers.getAccessoryOfferSummaryList() != null ) {
					List <AccessoryOffer> eligibleOffers = eligibleAccessoryOffers.getAccessoryOfferSummaryList();
					boolean matchFound = false;
					for(AccessoryOffer eligibleOffer : eligibleOffers){
						if(StringUtils.isNotBlank(eligibleOffer.getPromotionId()) && eligibleOffer.getPromotionId().equals(selectedPromo.getId())) {
							matchFound = true;
							if ( eligibleOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList() != null) {
								for(ProductCatalogueIdentifier catlogIdentifier : eligibleOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList()) {
									incompatibleDiscounts.add(catlogIdentifier.getProductCatalogueId());
								}
							}
						}
					}
					
					if(!matchFound) {
						logger.debug(functionName, "GWPOfferIsIneligibleRule:  One or more cartItemGiftWithPurchase promotions is not eligible for this transaction. Promotion ID = " + selectedPromo.getId());
						ineligiblePromos.add(selectedPromo.getId());
					}
				}else {
					logger.debug(functionName, "No eligible offers found.");
					ineligiblePromos.add(selectedPromo.getId());
				}
			}
		}
		
		if(!ineligiblePromos.isEmpty()) {
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(AccessoryOfferValidationRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.ACCESSORY_OFFER_INELIBILE);
			shoppingCartValidationError.setMessage("GWPOfferIsIneligibleRule:  One or more cartItemGiftWithPurchase promotions is not eligible for this transaction. Ineligible promotions:  " + ineligiblePromos.toString());
			trace.setErrors(shoppingCartValidationError);
			trace.setValidationPassedInd(false);
			trace.setShoppingCartId(shoppingCartContextVO.getShoppingCartVO().getShoppingCartId());	
			trace.setCartItemRelationId(cartItemVO.getCartItemRelationId());
			trace.setValidationPassedInd(false);
			trace.setOfferList(ineligiblePromos);
			traces.add(trace);
			isSatisfied = false;
			
		}
		
		if(!incompatibleDiscounts.isEmpty()) {
			
			ShoppingCartVO sc = shoppingCartContextVO.getShoppingCartVO();
			for(CartItemVO cartItem : sc.getCartItemList() ) {
				if(cartItem.isSalesOrderItem()) {
					
					//Discounts
					List<FFHDiscountTypeVO> discounts = new ArrayList<FFHDiscountTypeVO>();
					if(cartItem.getProducts() != null && cartItem.getProducts().getHomePhoneProduct() != null && cartItem.getProducts().getHomePhoneProduct().getDiscounts() != null) {
						discounts.addAll(cartItem.getProducts().getHomePhoneProduct().getDiscounts());
					}
					if(cartItem.getProducts() != null && cartItem.getProducts().getInternetProduct() != null && cartItem.getProducts().getInternetProduct().getDiscounts() != null) {
						discounts.addAll(cartItem.getProducts().getInternetProduct().getDiscounts());
					}
					if(cartItem.getProducts() != null && cartItem.getProducts().getTelevisionProduct() != null && cartItem.getProducts().getTelevisionProduct().getDiscounts() != null) {
						discounts.addAll(cartItem.getProducts().getTelevisionProduct().getDiscounts());
					}
					
					for(FFHDiscountTypeVO discount: discounts) {
						for(ProductComponentVO productCatalogueIdentifier : discount.getProductCatalogueIdentifiers()) {
							if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productCatalogueIdentifier.getAction())){
								if(incompatibleDiscounts.contains(productCatalogueIdentifier.getProductCatalogueId())) {
									incompatibleDiscountsInCart.add(productCatalogueIdentifier.getProductCatalogueId());
								}
							}
							
						}
						
					}
					
					//Sweeteners
					List<FFHSweetenerTypeVO> sweeteners = new ArrayList<FFHSweetenerTypeVO>();
					if(cartItem.getProducts() != null && cartItem.getProducts().getHomePhoneProduct() != null && cartItem.getProducts().getHomePhoneProduct().getSweeteners() != null) {
						sweeteners.addAll(cartItem.getProducts().getHomePhoneProduct().getSweeteners());
					}
					if(cartItem.getProducts() != null && cartItem.getProducts().getInternetProduct() != null && cartItem.getProducts().getInternetProduct().getSweeteners() != null) {
						sweeteners.addAll(cartItem.getProducts().getInternetProduct().getSweeteners());
					}
					if(cartItem.getProducts() != null && cartItem.getProducts().getTelevisionProduct() != null && cartItem.getProducts().getTelevisionProduct().getSweeteners() != null) {
						sweeteners.addAll(cartItem.getProducts().getTelevisionProduct().getSweeteners());
					}
					
					for(FFHSweetenerTypeVO sweetener: sweeteners) {
						for(FFHDiscountTypeVO discount: sweetener.getDiscounts()) {
							for(ProductComponentVO productCatalogueIdentifier : discount.getProductCatalogueIdentifiers()) {
								if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productCatalogueIdentifier.getAction())){
									if(incompatibleDiscounts.contains(productCatalogueIdentifier.getProductCatalogueId())) {
										incompatibleSweeetenersInCart.add(productCatalogueIdentifier.getProductCatalogueId());
									}
								}								
							}
							
						}
						
					}
					
				}
			}
		}

		if(!incompatibleDiscountsInCart.isEmpty()) {
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(AccessoryOfferValidationRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.ACCESSORY_OFFER_INCOMPATIBLE_DISCOUNTS);
			shoppingCartValidationError.setMessage("GWPIncompatibleWithDiscount:  The cartItemGiftWithPurchase has a price plan catalogue id which is ineligible for this GWP offer. [ " + incompatibleDiscountsInCart.toString() + " ]");
			trace.setErrors(shoppingCartValidationError);
			trace.setValidationPassedInd(false);
			trace.setShoppingCartId(shoppingCartContextVO.getShoppingCartVO().getShoppingCartId());	
			trace.setCartItemRelationId(cartItemVO.getCartItemRelationId());
			trace.setValidationPassedInd(false);
			//TODO:: see how this needs to be returned in context data.
			trace.setDiscountList(incompatibleDiscountsInCart);
			traces.add(trace);
			isSatisfied = false;
		}
		
		if(!incompatibleSweeetenersInCart.isEmpty()) {
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(AccessoryOfferValidationRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.ACCESSORY_OFFER_INCOMPATIBLE_DISCOUNTS);
			shoppingCartValidationError.setMessage("GWPIncompatibleWithSweetener:  The cartItemGiftWithPurchase is incompatible with the following discount catalogue id which is ineligible for this GWP offer. [ " + incompatibleSweeetenersInCart.toString() + " ]");
			trace.setErrors(shoppingCartValidationError);
			trace.setValidationPassedInd(false);
			trace.setShoppingCartId(shoppingCartContextVO.getShoppingCartVO().getShoppingCartId());	
			trace.setCartItemRelationId(cartItemVO.getCartItemRelationId());
			trace.setValidationPassedInd(false);
			traces.add(trace);
			isSatisfied = false;
		}


		logger.exit(functionName);

		return isSatisfied;
	}

}