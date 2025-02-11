package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;

/**
 * 
 * @author Alejandro.Hernandez
 *<br>
 *	This rule includes the logic of the following rules:
 *<br>
 *	
 *	- MarketOfferDiscountValidationRule
 *<br>
 *  - MandatoryIncludedDiscountsNotInCartRule
 *
 */
public class ValidateMarketOfferDiscountRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	
	private WirelineOfferProduct wirelineOfferProduct;
	private CartItemVO cartItem;
	private List<String> recontractEligibleProductCodeList;
	private String offerId;
	private static final LoggerUtil logger = LoggerUtil.getLogger(ValidateMarketOfferDiscountRule.class);
	
	public ValidateMarketOfferDiscountRule(WirelineOfferProduct wirelineOfferProduct, CartItemVO cartItem,List<String> recontractEligibleProductList,String offerId) {
		this.wirelineOfferProduct = wirelineOfferProduct;
		this.cartItem = cartItem;
		this.recontractEligibleProductCodeList = recontractEligibleProductList;
		this.offerId = offerId;
	}


	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied = true;
		String functionName="MarketOfferDiscountValidationRule";
		logger.enter(functionName);
		
		//List<String> nonStackableDiscountCodeList = new ArrayList<String>();
		List<String> notFoundDiscountList = new ArrayList<String>();
		List<String> nonValidWinbackList = new ArrayList<String>();
		List<String> recontractingDiscountCodeList = new ArrayList<String>();
		List<String> missingMandatoryIncludedDiscountCodeList = new ArrayList<String>();
		
		if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getInternetProduct()!=null){
			InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
			validateInternetDiscounts(internetProduct, notFoundDiscountList, /*nonStackableDiscountCodeList,*/ nonValidWinbackList, recontractingDiscountCodeList);
			validateMandatoryIncludedDiscounts(wirelineOfferProduct, internetProduct.getDiscounts(), Boolean.TRUE.equals(internetProduct.getWinback()), shoppingCartContextVO, missingMandatoryIncludedDiscountCodeList);
		}
		
		if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getTelevisionProduct()!=null){
			TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();
			validateTelevisionDiscounts(televisionProduct,notFoundDiscountList,/*nonStackableDiscountCodeList,*/nonValidWinbackList, recontractingDiscountCodeList);
			validateMandatoryIncludedDiscounts(wirelineOfferProduct, televisionProduct.getDiscounts(), Boolean.TRUE.equals(televisionProduct.getWinback()), shoppingCartContextVO, missingMandatoryIncludedDiscountCodeList);
		}
		
		if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getHomePhoneProduct()!=null){
			HomePhoneProductTypeVO homePhoneProduct = cartItem.getProducts().getHomePhoneProduct();
			validateHomePhoneDiscounts(homePhoneProduct,notFoundDiscountList,/*nonStackableDiscountCodeList,*/nonValidWinbackList, recontractingDiscountCodeList);
			validateMandatoryIncludedDiscounts(wirelineOfferProduct, homePhoneProduct.getDiscounts(), Boolean.TRUE.equals(homePhoneProduct.getWinback()), shoppingCartContextVO, missingMandatoryIncludedDiscountCodeList);
		}
		
		//Commented out. July, 2019. Bundle Builder redesign			
//		if(!CollectionUtils.isEmpty(nonStackableDiscountCodeList)){
//			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferDiscountRule.class.getName());
//			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.NON_STACKABLE_MARKET_OFFER_DISCOUNT,"MarketOfferDiscountValidationRule: One or more discounts are configured in the market offer as non-stackable but the cartItem includes another discount. Expected no other discounts. List of non-stackable discounts: " + nonStackableDiscountCodeList.toString());							
//			addTrace(shoppingCartValidationError, trace,traces);
//			isSatisfied = false;
//		}
		
		if(!CollectionUtils.isEmpty(notFoundDiscountList)){
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferDiscountRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.MARKET_DISCOUNT_CODES_NOT_EXISTING,"MarketOfferDiscountValidationRule: One or more discounts are not found in the market offer. List of discounts not found: " + notFoundDiscountList);
			addTrace(shoppingCartValidationError, trace, traces);
			isSatisfied=false;
		}
		
		if(!CollectionUtils.isEmpty(nonValidWinbackList)){
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferDiscountRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.MARKET_OFFER_WINBACK_DISCOUNT,"DiscountWinbackRule: One or more discounts requires that a customer be performing a winback/disconnect of that productType however that productType is not specified as winback, list of winback discounts: " + nonValidWinbackList);
			addTrace(shoppingCartValidationError, trace, traces);
			isSatisfied=false;
		}

		if(!CollectionUtils.isEmpty(recontractingDiscountCodeList)){
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferDiscountRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.RECONTRACTING_DISCOUNT_PRODUCT_NOT_RECONTRACTING);
			shoppingCartValidationError.setMessage("DiscountRecontractingRule: One or more discounts in the cartItem requires that a customer be performing a recontracting transaction for that productType however that product type does not meet the eligibility criteria for a recontracting transaction: " + recontractingDiscountCodeList.toString());							
			addTrace(shoppingCartValidationError, trace, traces);
			isSatisfied=false;
		}
		 
		if(!CollectionUtils.isEmpty(missingMandatoryIncludedDiscountCodeList)){
			ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferDiscountRule.class.getName());
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.MANDATORY_INCLUDED_DISCOUNT_NON_IN_CART,"MandatoryIncludedDiscountsNotInCartRule: One or more discounts in the cartItem's market offer is configured as 'included' which requires that it is added to the cartItem. However that discounts element was not found in the cartItem: " + missingMandatoryIncludedDiscountCodeList.toString());
			addTrace(shoppingCartValidationError, trace, traces);
			isSatisfied = false;
		}
		
		logger.exit(functionName);
		return isSatisfied;
	}
	
	private void validateHomePhoneDiscounts(HomePhoneProductTypeVO homePhoneProduct, List<String> notFoundDiscountList,/*List<String> nonStackableDiscountCodeList,*/ List<String> nonValidWinbackList, List<String> recontractingDiscountCodeList) {
		if(!CollectionUtils.isEmpty(homePhoneProduct.getDiscounts())){
			//Getting the discounts of Home Phone product for this offer
			if(CollectionUtils.isEmpty(wirelineOfferProduct.getDiscountList()) && !EnterpriseWLNOrderUtil.isRemoveDiscounts(homePhoneProduct.getDiscounts())){
				notFoundDiscountList.add("There are Discounts in the CartItem for Home Phone product, however there are no discounts returned for Home Phone product for the Market Offer with offerId=" + offerId);
			}else{
				for(FFHDiscountTypeVO cartItemHomePhoneDiscount : homePhoneProduct.getDiscounts()){
					if(!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemHomePhoneDiscount.getProductCatalogueIdentifiers())){
						Discount tomDiscount = getTomDiscount(wirelineOfferProduct, cartItemHomePhoneDiscount);
						if(tomDiscount == null){
							notFoundDiscountList.add(cartItemHomePhoneDiscount.getDiscountCode());
						} else {
//							if(!tomDiscount.isStackableInd() && existsOtherOptionalDiscount(wirelineOfferProduct, cartItemHomePhoneDiscount, homePhoneProduct.getDiscounts())){
//								nonStackableDiscountCodeList.add(cartItemHomePhoneDiscount.getDiscountCode());																			
//							}

							if(tomDiscount.isWinbackInd() && (homePhoneProduct.getWinback()==null || !homePhoneProduct.getWinback())){
								nonValidWinbackList.add("Home Phone discount, with discount code = " + cartItemHomePhoneDiscount.getDiscountCode());
							}
							
							if (Boolean.TRUE.equals(tomDiscount.isRecontractingInd()) && !isProductsContain(recontractEligibleProductCodeList, EnterpriseWLNSalesServicesConstants.SING)) {
								recontractingDiscountCodeList.add(cartItemHomePhoneDiscount.getDiscountCode());	
							}

						}
					}
				}
				
			}
		}
		
	}


	private void validateTelevisionDiscounts(TelevisionProductTypeVO televisionProduct,List<String> notFoundDiscountList, /*List<String> nonStackableDiscountCodeList,*/List<String> nonValidWinbackList, List<String> recontractingDiscountCodeList) {
		if(!CollectionUtils.isEmpty(televisionProduct.getDiscounts())){
			//Getting the discounts of Television product for this offer
			if(CollectionUtils.isEmpty(wirelineOfferProduct.getDiscountList()) && !EnterpriseWLNOrderUtil.isRemoveDiscounts(televisionProduct.getDiscounts())){
				notFoundDiscountList.add("There are Discounts in the CartItem for Television product, however there are no discounts returned for Internet product for the Market Offer with OfferId = " + offerId);
			}else{
				for(FFHDiscountTypeVO cartItemTelevisionDiscount : televisionProduct.getDiscounts()){
					if(!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemTelevisionDiscount.getProductCatalogueIdentifiers())){
						Discount tomDiscount = getTomDiscount(wirelineOfferProduct, cartItemTelevisionDiscount);
						if(tomDiscount == null){
							notFoundDiscountList.add(cartItemTelevisionDiscount.getDiscountCode());
						} else {
//							if(!tomDiscount.isStackableInd() && existsOtherOptionalDiscount(wirelineOfferProduct, cartItemTelevisionDiscount, televisionProduct.getDiscounts())){
//								nonStackableDiscountCodeList.add(cartItemTelevisionDiscount.getDiscountCode());																			
//							}

							if(tomDiscount.isWinbackInd() && (televisionProduct.getWinback()==null || !televisionProduct.getWinback())){
								nonValidWinbackList.add("Television discount, with discount code = " + cartItemTelevisionDiscount.getDiscountCode());
							}
							
							if (Boolean.TRUE.equals(tomDiscount.isRecontractingInd()) && !isProductsContain(recontractEligibleProductCodeList, EnterpriseWLNSalesServicesConstants.TTV)) {
								recontractingDiscountCodeList.add(cartItemTelevisionDiscount.getDiscountCode());	
							}

						}
					}
				}
				
			}
		}
	}


	private void validateInternetDiscounts(InternetProductTypeVO internetProduct,List<String> notFoundDiscountList, /*List<String> nonStackableDiscountCodeList,*/ List<String> nonValidWinbackList, List<String> recontractingDiscountCodeList){
		if(!CollectionUtils.isEmpty(internetProduct.getDiscounts())){
			//Getting the discounts of Internet product for this offer
			if(CollectionUtils.isEmpty(wirelineOfferProduct.getDiscountList()) && !EnterpriseWLNOrderUtil.isRemoveDiscounts(internetProduct.getDiscounts())){
				notFoundDiscountList.add("There are Discounts in the CartItem for Internet product, however there are no discounts returned for Internet product for the Market Offer with offerId = " + offerId);
			}else{
				for(FFHDiscountTypeVO cartItemInternetDiscount : internetProduct.getDiscounts()){
					if(!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemInternetDiscount.getProductCatalogueIdentifiers())){
						Discount tomDiscount = getTomDiscount(wirelineOfferProduct, cartItemInternetDiscount);
						if(tomDiscount == null){
							notFoundDiscountList.add(cartItemInternetDiscount.getDiscountCode());
						} else {
//							if(!tomDiscount.isStackableInd() && existsOtherOptionalDiscount(wirelineOfferProduct, cartItemInternetDiscount, internetProduct.getDiscounts())){
//								nonStackableDiscountCodeList.add(cartItemInternetDiscount.getDiscountCode());																			
//							}

							if(tomDiscount.isWinbackInd() && (internetProduct.getWinback()==null || !internetProduct.getWinback())){
								nonValidWinbackList.add("Internet discount, with discount code = " + cartItemInternetDiscount.getDiscountCode());
							}
							
							if (Boolean.TRUE.equals(tomDiscount.isRecontractingInd()) && !isProductsContain(recontractEligibleProductCodeList, EnterpriseWLNSalesServicesConstants.HSIC)) {
								recontractingDiscountCodeList.add(cartItemInternetDiscount.getDiscountCode());	
							}

						}
					}
				}
				
			}
		}
	}
	
	
	private Discount getTomDiscount(WirelineOfferProduct wirelineProduct, FFHDiscountTypeVO cartItemInternetDiscount) {
		
		for(Discount tomDiscount : wirelineProduct.getDiscountList()){
			String tomDiscountCode = tomDiscount.getDiscountCode();
			if(cartItemInternetDiscount.getDiscountCode().equalsIgnoreCase(tomDiscountCode) && EnterpriseWLNCommonTransformer.catalogueIdMatches(tomDiscount,cartItemInternetDiscount)){
				return tomDiscount;
			}
		}
		
		return null;
	}
	
//	private boolean existsOtherOptionalDiscount(WirelineOfferProduct wirelineProduct,
//			FFHDiscountTypeVO cartItemDiscount, List<FFHDiscountTypeVO> discounts) {
//		
//		for (FFHDiscountTypeVO otherDiscount : discounts) {
//			if (!cartItemDiscount.getDiscountCode().equals(otherDiscount.getDiscountCode())) {
//				 Discount otherTomDiscount = getTomDiscount(wirelineProduct, otherDiscount);
//				 if (otherTomDiscount != null && !otherTomDiscount.isIncludedInd()) {
//					 return true;
//				 }
//			}
//		}
//		
//		return false;
//	}
	
	private void validateMandatoryIncludedDiscounts(WirelineOfferProduct offerProduct,
			List<FFHDiscountTypeVO> cartDiscounts, boolean cartProductIsWinback, ShoppingCartContextVO ctxVO,
			 List<String> missingMandatoryIncludedDiscountCodeList) {

		if (offerProduct.getDiscountList() == null) {
			return;
		}
		
		for (Discount offerDiscount : offerProduct.getDiscountList()) {
			//discount is mandatory included in the offer
			if (!offerDiscount.isIncludedInd()) {
				continue;
			}
			//NWLN-8452 only validate "non-addon" promotion since included is not meaningful for addon promotion which will be added based on parent
			if (EnterpriseWLNSalesServicesConstants.DISCOUNT_TYPE_CD_ADD_ON.equalsIgnoreCase(offerDiscount.getDiscountedComponentTypeCd())) {
				continue;
			}
			//discount not present in the cart
			FFHDiscountTypeVO cartDiscount = getCartDiscount(cartDiscounts, offerDiscount);
			if (cartDiscount != null) {
				continue;
			}
			// discount is not winback or the product is not winback 
			if (offerDiscount.isWinbackInd() && !cartProductIsWinback) {
				continue;
			}
			// discount is not for recontracting or the product is recontracting   			
			if (Boolean.TRUE.equals(offerDiscount.isRecontractingInd()) && this.recontractEligibleProductCodeList != null) {
				boolean recontractingProduct = false;
				
				for (String pc : this.recontractEligibleProductCodeList) {
					if (offerProduct.getProductTypeCode().equalsIgnoreCase(pc)) {
						recontractingProduct = true;
					}
				}
				
				if (!recontractingProduct) {
					continue;
				}
			}
			//Commented out. Jul., 2019. The rule to be handled by the Promotion Qualification app
			//discount meets cross product dependency
//			if (!EnterpriseWLNOrderUtil.meetCrossProductDependency(ctxVO, offerDiscount)) {
//				continue;
//			}
			
			try {
				missingMandatoryIncludedDiscountCodeList.add(offerDiscount.getDiscountProductCatalogueItemList().get(0).getProductCatalogueIdentifier().getProductCatalogueId());
			} catch (Exception e) {
				logger.error("", "validateMandatoryIncludedDiscounts", "Exception from offerDiscount.getDiscountProductCatalogueItemList().get(0).getProductCatalogueIdentifier().getProductCatalogueId()", e);
			}
			
		}
		
	}
	
	private FFHDiscountTypeVO getCartDiscount(List<FFHDiscountTypeVO> cartDiscounts, Discount offerDiscount) {
		
		for (FFHDiscountTypeVO cartItemDiscount : cartDiscounts) {
			String offerDiscountCode = offerDiscount.getDiscountCode();
			if (!EnterpriseWLNOrderUtil.productComponentHasRemove(cartItemDiscount.getProductCatalogueIdentifiers()) && cartItemDiscount.getDiscountCode().equalsIgnoreCase(offerDiscountCode) && EnterpriseWLNCommonTransformer.catalogueIdMatches(offerDiscount, cartItemDiscount) ){
				return cartItemDiscount;
			}
		}

		return null;
	}
	
	private void addTrace(ShoppingCartValidationErrors validationError,ShoppingCartValidationTraceVO trace,List<ShoppingCartValidationTraceVO> traces){
		trace.setValidationPassedInd(false);
		trace.setCartItemRelationId(cartItem.getCartItemRelationId());
		trace.setShoppingCartItemId(cartItem.getCartItemId());
		trace.setErrors(validationError);							
		traces.add(trace);
	}

	private boolean isProductsContain(List<String> recontractEligibleProductCodeList, String productCd) {

		if (!CollectionUtils.isEmpty(recontractEligibleProductCodeList)) {
			for (String x : recontractEligibleProductCodeList) {
				if (x.equalsIgnoreCase(productCd)) {
					return true;
				}
			}
		}

		return false;
	}


}
