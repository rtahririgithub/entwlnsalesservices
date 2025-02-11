package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.transformer.EnterpriseWLNCommonTransformer;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
@Deprecated //not used
public class MandatoryIncludedDiscountsNotInCartRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(MandatoryIncludedDiscountsNotInCartRule.class);
	
	private CartItemVO cartItemVO;
	
	public MandatoryIncludedDiscountsNotInCartRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}


	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO,List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);
		
		String functionName="MandatoryIncludedDiscountsNotInCartRule.isSatisfiedBy";
		logger.enter(functionName);
		boolean isSatisfied=true;
		String cartItemRelationId="";
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO=null;
		List<String> missingMandatoryIncludedDiscountCodeList = new ArrayList<String>();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getSimpleName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();		
		
		if(shoppingCartVO.getCartItemList()!=null && !shoppingCartVO.getCartItemList().isEmpty()){
			 //the cartItem element means market offer
				if (cartItemVO.isSalesOrderItem() && !StringUtils.isEmpty(cartItemVO.getCartItemRelationId())) {
					
					cartItemRelationId=cartItemVO.getCartItemRelationId();
					
					offerDetailResponseVO = ctxVO.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());
					
					if(offerDetailResponseVO!=null && offerDetailResponseVO.getOffer()!=null && offerDetailResponseVO.getOffer().getOfferProduct()!=null
							&& !CollectionUtils.isEmpty(offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){
						List<WirelineOfferProduct> wirelineOfferProductList = offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList();
					
						ProductTypeVO cartItemProducts = cartItemVO.getProducts();	
						if(cartItemProducts!=null){
							
							if(cartItemProducts.getInternetProduct()!=null){
								InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
								for(WirelineOfferProduct wirelineProduct : wirelineOfferProductList){
									if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
										validateMandatoryIncludedDiscounts(wirelineProduct, internetProduct.getDiscounts(), Boolean.TRUE.equals(internetProduct.getWinback()), ctxVO, offerDetailResponseVO, missingMandatoryIncludedDiscountCodeList);
									}
								}
							}

							if(cartItemProducts.getTelevisionProduct()!=null){
								TelevisionProductTypeVO televisionProduct = cartItemProducts.getTelevisionProduct();
								for(WirelineOfferProduct wirelineProduct : wirelineOfferProductList){
									if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
										validateMandatoryIncludedDiscounts(wirelineProduct, televisionProduct.getDiscounts(), Boolean.TRUE.equals(televisionProduct.getWinback()), ctxVO, offerDetailResponseVO, missingMandatoryIncludedDiscountCodeList);
									}
								}
							}

							if(cartItemProducts.getHomePhoneProduct()!=null){
								HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
								for(WirelineOfferProduct wirelineProduct : wirelineOfferProductList){
									if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineProduct.getProductTypeCode())){
										validateMandatoryIncludedDiscounts(wirelineProduct, homePhoneProduct.getDiscounts(), Boolean.TRUE.equals(homePhoneProduct.getWinback()), ctxVO, offerDetailResponseVO, missingMandatoryIncludedDiscountCodeList);
									}
								}
							}
						}

						if(!CollectionUtils.isEmpty(missingMandatoryIncludedDiscountCodeList)){
							ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.MANDATORY_INCLUDED_DISCOUNT_NON_IN_CART);
							shoppingCartValidationError.setMessage(this.getClass().getSimpleName() + ": One or more discounts in the cartItem's market offer is configured as 'included' which requires that it is added to the cartItem: " + cartItemRelationId + ". However that discounts element was not found in the cartItem: " + missingMandatoryIncludedDiscountCodeList.toString());							
							trace.setValidationPassedInd(false);
							trace.setErrors(shoppingCartValidationError);							
							trace.setShoppingCartId(shppoingCartId);	
							trace.setCartItemRelationId(cartItemRelationId);
							traces.add(trace);
							isSatisfied = false;
						}
						
				}else{
					ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_DISCOUNT_CODES_NOT_EXISTING);
					shoppingCartValidationError.setMessage(this.getClass().getSimpleName() + ": offer is not found for cartItem: " + cartItemRelationId);					
					trace.setValidationPassedInd(false);
					trace.setErrors(shoppingCartValidationError);					
					trace.setShoppingCartId(shppoingCartId);	
					trace.setCartItemRelationId(cartItemRelationId);
					traces.add(trace);
					isSatisfied=false;
				}
			}else{
				logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItemVO.isSalesOrderItem() + " and cartItemRelationId=" + cartItemVO.getCartItemRelationId());
			}
		}
		return isSatisfied;
	}


	private void validateMandatoryIncludedDiscounts(WirelineOfferProduct offerProduct,
			List<FFHDiscountTypeVO> cartDiscounts, boolean cartProductIsWinback, ShoppingCartContextVO ctxVO,
			GetSalesOfferDetailResponseVO2 offerDetailResponseVO, List<String> missingMandatoryIncludedDiscountCodeList) {

		if (offerProduct.getDiscountList() == null) {
			return;
		}
		
		for (Discount offerDiscount : offerProduct.getDiscountList()) {
			//discount is mandatory included in the offer
			if (!offerDiscount.isIncludedInd()) {
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
			if (Boolean.TRUE.equals(offerDiscount.isRecontractingInd()) && offerDetailResponseVO.getRecontractEligibleProductCodeList() != null) {
				boolean recontractingProduct = false;
				
				for (String pc : offerDetailResponseVO.getRecontractEligibleProductCodeList()) {
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
}
