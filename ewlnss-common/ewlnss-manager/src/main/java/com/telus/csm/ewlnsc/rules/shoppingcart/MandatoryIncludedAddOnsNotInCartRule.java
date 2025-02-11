package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
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
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class MandatoryIncludedAddOnsNotInCartRule
		extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(MandatoryIncludedAddOnsNotInCartRule.class);

	private CartItemVO cartItemVO;

	public MandatoryIncludedAddOnsNotInCartRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(ctxVO, traces);

		String functionName = "MandatoryIncludedAddOnsNotInCartRule";
		logger.enter(functionName);
		boolean isSatisfied = true;
		String cartItemRelationId = "";
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO = null;
		List<String> includedAddOnMissingList = new ArrayList<String>();
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(MandatoryIncludedAddOnsNotInCartRule.class.getName());
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

						// HSIC
						if (cartItemProducts.getInternetProduct() != null) {
							InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (wirelineProduct.getAddOn() != null
											&& wirelineProduct.getAddOn().getIncludedProductCatalogueItemList() != null
											&& !wirelineProduct.getAddOn().getIncludedProductCatalogueItemList().isEmpty()) {
										for (ProductCatalogueItem tomAddOn : wirelineProduct.getAddOn().getIncludedProductCatalogueItemList()) {											
											FFHProductPlanAddOnTypeVO cartAddOn = getCartAddOn(internetProduct.getAddOns(), tomAddOn);
											if (cartAddOn == null) {
												includedAddOnMissingList.add(tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId());
											}
										}
									}
								}
							}
						}

						// TTV
						if (cartItemProducts.getTelevisionProduct() != null) {
							TelevisionProductTypeVO televisionProduct = cartItemProducts.getTelevisionProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (wirelineProduct.getAddOn() != null
											&& wirelineProduct.getAddOn().getIncludedProductCatalogueItemList() != null
											&& !wirelineProduct.getAddOn().getIncludedProductCatalogueItemList().isEmpty()) {
										for (ProductCatalogueItem tomAddOn : wirelineProduct.getAddOn().getIncludedProductCatalogueItemList()) {
											FFHProductPlanAddOnTypeVO cartAddOn = getCartAddOn(televisionProduct.getAddOns(), tomAddOn);
											if (cartAddOn == null) {
												includedAddOnMissingList.add(tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId());
											}
										}
									}
								}
							}
						}

						// SING
						if (cartItemProducts.getHomePhoneProduct() != null) {
							HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
							for (WirelineOfferProduct wirelineProduct : wirelineOfferProductList) {
								if (EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineProduct.getProductTypeCode())) {
									if (wirelineProduct.getAddOn() != null
											&& wirelineProduct.getAddOn().getIncludedProductCatalogueItemList() != null
											&& !wirelineProduct.getAddOn().getIncludedProductCatalogueItemList().isEmpty()) {
										for (ProductCatalogueItem tomAddOn : wirelineProduct.getAddOn().getIncludedProductCatalogueItemList()) {
											FFHProductPlanAddOnTypeVO cartAddOn = getCartAddOn(homePhoneProduct.getAddOns(), tomAddOn);
											if (cartAddOn == null) {
												includedAddOnMissingList.add(tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId());
											}
										}
									}
								}
							}
						}
					}

					if (!CollectionUtils.isEmpty(includedAddOnMissingList)) {
						ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
								.getErrorForCode(ShoppingCartValidationErrorCodes.MANDATORY_ADDON_NOT_IN_CART);
						shoppingCartValidationError.setMessage(this.getClass().getSimpleName()
								+ ": One or more addOns in the cartItem's market offer is configured as 'included' which requires that it is added to the cartItem: "
								+ cartItemRelationId
								+ " However that discount addOn element was not found in the cartItem or the addOn within the cart has action different than ADD: "
								+ includedAddOnMissingList.toString());
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

	private FFHProductPlanAddOnTypeVO getCartAddOn(List<FFHProductPlanAddOnTypeVO> cartAddOns,
			ProductCatalogueItem tomAddOn) {

		if (cartAddOns == null) {
			return null;
		}

		String tomId = tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId();
		for (FFHProductPlanAddOnTypeVO cartAddOn : cartAddOns) {
			if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddOn.getProductCatalogueIdentifier().getAction())){
				if (StringUtils.equals(tomId, cartAddOn.getProductCatalogueIdentifier().getProductCatalogueId())) {
					return cartAddOn;
				}
			}
		}

		return null;
	}

}
