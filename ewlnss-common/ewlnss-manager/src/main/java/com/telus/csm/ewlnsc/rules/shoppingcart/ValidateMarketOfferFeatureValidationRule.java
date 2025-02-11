package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;

public class ValidateMarketOfferFeatureValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private CartItemVO cartItem;
	private WirelineOfferProduct offerProduct;
	private String offerId;
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(ValidateMarketOfferFeatureValidationRule.class);
	
	public ValidateMarketOfferFeatureValidationRule(CartItemVO cartItem, WirelineOfferProduct offerProduct,String offerId) {
		this.cartItem = cartItem;
		this.offerProduct = offerProduct;
		this.offerId = offerId;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied = true;
		

		List<String> includedFeatureMissingList = new ArrayList<String>();
		List<String> featureNotInOfferList = new ArrayList<String>();
		
		
		if(cartItem.isSalesOrderItem()){
			if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(offerProduct.getProductTypeCode()) && cartItem.getProducts().getHomePhoneProduct()!=null){
				if (offerProduct.getFeature() != null && !CollectionUtils.isEmpty(offerProduct.getFeature().getIncludedProductCatalogueItemList())) {
					for (ProductCatalogueItem tomFeature : offerProduct.getFeature().getIncludedProductCatalogueItemList()) {
						FFHFeatureTypeVO cartFeature = getCartFeature(cartItem.getProducts().getHomePhoneProduct().getFeatures(), tomFeature);
						if (cartFeature == null) {
							includedFeatureMissingList.add(tomFeature.getProductCatalogueIdentifier().getProductCatalogueId());
						}
					}
				}
				
				for (FFHFeatureTypeVO feature : cartItem.getProducts().getHomePhoneProduct().getFeatures()) {
					if (!isFeatureInOffer(offerProduct, feature) ) {
						featureNotInOfferList.add(feature.getProductCatalogueIdentifier().getProductCatalogueId());
					}
					
				}
				
			}
			
			if (!CollectionUtils.isEmpty(includedFeatureMissingList)) {
				ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferFeatureValidationRule.class.getName());
				ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
						.getErrorForCode(ShoppingCartValidationErrorCodes.MANDATORY_FEATURE_NOT_IN_CART);
				shoppingCartValidationError.setMessage("MandatoryIncludedFeaturesNotInCartRule"
						+ ": One or more features in the cartItem's market offer with offerId = " + offerId + " ,is configured as 'included' which requires that it is added to the cartItem "
						+ " However that feature was not found in the cartItem: "
						+ includedFeatureMissingList.toString());
				trace.setValidationPassedInd(false);
				trace.setErrors(shoppingCartValidationError);
				trace.setCartItemRelationId(cartItem.getCartItemRelationId());
				trace.setShoppingCartItemId(cartItem.getCartItemId());
				traces.add(trace);
				isSatisfied = false;
			}

			if (!CollectionUtils.isEmpty(featureNotInOfferList)) {
				ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(ValidateMarketOfferFeatureValidationRule.class.getName());
				ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
						.getErrorForCode(ShoppingCartValidationErrorCodes.FEATURE_NOT_IN_OFFER);
				shoppingCartValidationError.setMessage("FeatureExistsInOfferRule"
						+ ": One or more features in the cartItem is not found in the market offer: "
						+ featureNotInOfferList.toString());
				trace.setValidationPassedInd(false);
				trace.setErrors(shoppingCartValidationError);
				trace.setCartItemRelationId(cartItem.getCartItemRelationId());
				trace.setShoppingCartItemId(cartItem.getCartItemId());
				traces.add(trace);
				isSatisfied = false;
			}
			
		}else {
			logger.debug("isSatisfiedBy", "Rule skipped. cartItem is: market Offer=" + cartItem.isSalesOrderItem()
			+ " and cartItemRelationId=" + cartItem.getCartItemRelationId());
}
		
		return isSatisfied;
	}
	
	private FFHFeatureTypeVO getCartFeature(List<FFHFeatureTypeVO> list,
			ProductCatalogueItem tomFeature) {

		if (list == null) {
			return null;
		}

		String tomId = tomFeature.getProductCatalogueIdentifier().getProductCatalogueId();
		for (FFHFeatureTypeVO cartFeature : list) {
			if (!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction()) && StringUtils.equals(tomId, cartFeature.getProductCatalogueIdentifier().getProductCatalogueId())) {
				return cartFeature;
			}
		}

		return null;
	}
	
private boolean isFeatureInOffer(WirelineOfferProduct wirelineProduct, FFHFeatureTypeVO cartFeature) {
		
		if (wirelineProduct == null) {
			return false;
		}
		
		String id = cartFeature.getProductCatalogueIdentifier().getProductCatalogueId();
		
		if (wirelineProduct.getFeature().getIncludedProductCatalogueItemList() != null) {
			for (ProductCatalogueItem offerFeatureId : wirelineProduct.getFeature().getIncludedProductCatalogueItemList()) {
				if (StringUtils.equals(offerFeatureId.getProductCatalogueIdentifier().getProductCatalogueId(), id)) {
					return true;
				}
			}
		}

		if (wirelineProduct.getFeature().getOptionalProductCatalogueItemList() != null) {
			for (ProductCatalogueItem offerFeatureId : wirelineProduct.getFeature().getOptionalProductCatalogueItemList()) {
				if (StringUtils.equals(offerFeatureId.getProductCatalogueIdentifier().getProductCatalogueId(), id)) {
					return true;
				}
			}
		}

		return false;
	}
	
}
