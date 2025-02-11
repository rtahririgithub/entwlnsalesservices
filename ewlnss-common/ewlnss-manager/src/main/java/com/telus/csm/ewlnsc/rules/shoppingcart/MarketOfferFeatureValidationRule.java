package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

//This class enforces these rules:
//1. MandatoryIncludedFeaturesNotInCartRule
//2. FeatureExistsInOfferRule

public class MarketOfferFeatureValidationRule
		extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(MarketOfferFeatureValidationRule.class);

	private CartItemVO cartItemVO;

	public MarketOfferFeatureValidationRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);

		String functionName = "MarketOfferFeatureValidationRule";
		logger.enter(functionName);
		boolean isSatisfied = true;
		String cartItemRelationId = "";
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO = null;
		List<String> includedFeatureMissingList = new ArrayList<String>();
		List<String> featureNotInOfferList = new ArrayList<String>();
		
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(MarketOfferFeatureValidationRule.class.getName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();

		if (shoppingCartVO.getCartItemList() != null && !shoppingCartVO.getCartItemList().isEmpty()) {
			// the cartItem element means market offer
			if (cartItemVO.isSalesOrderItem() && !StringUtils.isEmpty(cartItemVO.getCartItemRelationId())) {

				cartItemRelationId = cartItemVO.getCartItemRelationId();

				offerDetailResponseVO = ctxVO.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());

				if (offerDetailResponseVO != null && offerDetailResponseVO.getOffer() != null
						&& offerDetailResponseVO.getOffer().getOfferProduct() != null && !CollectionUtils.isEmpty(
								offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())) {
					List<WirelineOfferProduct> wirelineOfferProductList = offerDetailResponseVO.getOffer()
							.getOfferProduct().getWirelineOfferProductList();

					ProductTypeVO cartItemProducts = cartItemVO.getProducts();
					if (cartItemProducts != null) {

						// SING
						if (cartItemProducts.getHomePhoneProduct() != null) {
							HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (wirelineProduct.getFeature() != null
											&& wirelineProduct.getFeature().getIncludedProductCatalogueItemList() != null
											&& !wirelineProduct.getFeature().getIncludedProductCatalogueItemList().isEmpty()) {
										for (ProductCatalogueItem tomFeature : wirelineProduct.getFeature().getIncludedProductCatalogueItemList()) {
											FFHFeatureTypeVO cartFeature = getCartFeature(homePhoneProduct.getFeatures(), tomFeature);
											if (cartFeature == null) {
												includedFeatureMissingList.add(tomFeature.getProductCatalogueIdentifier().getProductCatalogueId());
											}
										}
									}

									for (FFHFeatureTypeVO feature : homePhoneProduct.getFeatures()) {
										if (!isFeatureInOffer(wirelineProduct, feature) ) {
											featureNotInOfferList.add(feature.getProductCatalogueIdentifier().getProductCatalogueId());
										}
										
									}	
									
								}
							}
						}
					}

					if (!CollectionUtils.isEmpty(includedFeatureMissingList)) {
						ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
								.getErrorForCode(ShoppingCartValidationErrorCodes.MANDATORY_FEATURE_NOT_IN_CART);
						shoppingCartValidationError.setMessage("MandatoryIncludedFeaturesNotInCartRule"
								+ ": One or more features in the cartItem's market offer is configured as 'included' which requires that it is added to the cartItem: "
								+ cartItemRelationId
								+ " However that feature was not found in the cartItem: "
								+ includedFeatureMissingList.toString());
						trace.setValidationPassedInd(false);
						trace.setErrors(shoppingCartValidationError);
						trace.setShoppingCartId(shppoingCartId);
						trace.setCartItemRelationId(cartItemRelationId);
						traces.add(trace);
						isSatisfied = false;
					}

					if (!CollectionUtils.isEmpty(featureNotInOfferList)) {
						ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
								.getErrorForCode(ShoppingCartValidationErrorCodes.FEATURE_NOT_IN_OFFER);
						shoppingCartValidationError.setMessage("FeatureExistsInOfferRule"
								+ ": One or more features in the cartItem " + cartItemRelationId + " is not found in the market offer: "
								+ featureNotInOfferList.toString());
						trace.setValidationPassedInd(false);
						trace.setErrors(shoppingCartValidationError);
						trace.setShoppingCartId(shppoingCartId);
						trace.setCartItemRelationId(cartItemRelationId);
						traces.add(trace);
						isSatisfied = false;
					}
				} else {
					logger.debug(functionName, "No offer was found for cartItemRelationId: " + cartItemRelationId);
				}
			} else {
				logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItemVO.isSalesOrderItem()
						+ " and cartItemRelationId=" + cartItemVO.getCartItemRelationId());
			}
		}
		return isSatisfied;
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

}
