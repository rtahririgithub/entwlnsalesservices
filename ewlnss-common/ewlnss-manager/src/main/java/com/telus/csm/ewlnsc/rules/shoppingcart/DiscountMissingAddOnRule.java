package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.AccessoryProductTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
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
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class DiscountMissingAddOnRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(DiscountMissingAddOnRule.class);

	private CartItemVO cartItemVO;

	public DiscountMissingAddOnRule(CartItemVO cartItem) {
		this.cartItemVO = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO ctxVO, List<ShoppingCartValidationTraceVO> traces) {

		String functionName = "isSatisfiedBy";
		logger.enter(functionName);
		boolean isSatisfied = true;

		String cartItemRelationId = "";
		GetSalesOfferDetailResponseVO2 offerDetailResponseVO = null;
		List<String> addOnMissingList = new ArrayList<String>();

		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getSimpleName());
		ShoppingCartVO shoppingCartVO = ctxVO.getShoppingCartVO();
		String shppoingCartId = shoppingCartVO.getShoppingCartId();

		if (cartItemVO.isSalesOrderItem() && !StringUtils.isEmpty(cartItemVO.getCartItemRelationId())) {

			cartItemRelationId = cartItemVO.getCartItemRelationId();

			offerDetailResponseVO = ctxVO
					.getOfferByCartItemOfferId(cartItemVO.getProductMarketOffering().getOfferHeader().getOfferId());

			if (offerDetailResponseVO != null && offerDetailResponseVO.getOffer() != null
					&& offerDetailResponseVO.getOffer().getOfferProduct() != null && !CollectionUtils.isEmpty(
							offerDetailResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())) {

				List<WirelineOfferProduct> wirelineOfferProductList = offerDetailResponseVO.getOffer().getOfferProduct()
						.getWirelineOfferProductList();

				ProductTypeVO cartItemProducts = cartItemVO.getProducts();
				if (cartItemProducts != null) {

					if (cartItemProducts.getInternetProduct() != null) {
						InternetProductTypeVO internetProduct = cartItemProducts.getInternetProduct();
						validate(internetProduct.getDiscounts(), internetProduct.getAddOns(), EnterpriseWLNSalesServicesConstants.HSIC, wirelineOfferProductList, addOnMissingList);
					}

					if (cartItemProducts.getTelevisionProduct() != null) {
						TelevisionProductTypeVO televisionProduct = cartItemProducts.getTelevisionProduct();
						validate(televisionProduct.getDiscounts(), televisionProduct.getAddOns(), EnterpriseWLNSalesServicesConstants.TTV, wirelineOfferProductList, addOnMissingList);
					}

					if (cartItemProducts.getHomePhoneProduct() != null) {
						HomePhoneProductTypeVO homePhoneProduct = cartItemProducts.getHomePhoneProduct();
						validate(homePhoneProduct.getDiscounts(), homePhoneProduct.getAddOns(), EnterpriseWLNSalesServicesConstants.SING, wirelineOfferProductList, addOnMissingList);
					}
					// NWLN-10301 validate accessory promotion
					if (cartItemProducts.getAccessoryProduct() != null) {
						AccessoryProductTypeVO accessoryProduct = cartItemProducts.getAccessoryProduct();
						validate(accessoryProduct.getDiscounts(), accessoryProduct.getAddOns(), EnterpriseWLNSalesServicesConstants.CPE, wirelineOfferProductList, addOnMissingList);
					}
				}

				if (!CollectionUtils.isEmpty(addOnMissingList)) {
					ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
							.getErrorForCode(ShoppingCartValidationErrorCodes.DISCOUNT_MISSING_ADDON);
					shoppingCartValidationError
							.setMessage(this.getClass().getSimpleName() + ": One or more discounts in the cartItem: "
									+ cartItemRelationId + " The following discount(s) in the cartItem are dependant on a specific addOn being included as a cartItem. "
									+ addOnMissingList.toString());
					trace.setValidationPassedInd(false);
					trace.setErrors(shoppingCartValidationError);
					trace.setShoppingCartId(shppoingCartId);
					trace.setCartItemRelationId(cartItemRelationId);
					traces.add(trace);
					isSatisfied = false;
				}

			} else {
				ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
						.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_DISCOUNT_CODES_NOT_EXISTING);
				shoppingCartValidationError.setMessage(
						this.getClass().getSimpleName() + ": offer is not found for cartItem: " + cartItemRelationId);
				trace.setValidationPassedInd(false);
				trace.setErrors(shoppingCartValidationError);
				trace.setShoppingCartId(shppoingCartId);
				trace.setCartItemRelationId(cartItemRelationId);
				traces.add(trace);
				isSatisfied = false;
			}
		} else {
			logger.debug(functionName, "Rule skipped. cartItem is: market Offer=" + cartItemVO.isSalesOrderItem()
					+ " and cartItemRelationId=" + cartItemVO.getCartItemRelationId());
		}

		return isSatisfied;
	}

	private void validate(List<FFHDiscountTypeVO> discounts, List<FFHProductPlanAddOnTypeVO> addOns, String productCd, List<WirelineOfferProduct> wirelineOfferProductList,
			List<String> addOnMissingList) {
		
		if (!CollectionUtils.isEmpty(discounts)) {
			WirelineOfferProduct wirelineProduct = getOfferProduct(wirelineOfferProductList, productCd);
			if (wirelineProduct != null) {
				for (FFHDiscountTypeVO x : discounts) {
					if (!EnterpriseWLNOrderUtil.productComponentHasRemove(x.getProductCatalogueIdentifiers())) {
						String productCatalogueId = x.getProductCatalogueIdentifiers().get(0).getProductCatalogueId();
						String parentProductCatalogueId = x.getProductCatalogueIdentifiers().get(0).getParentProductCatalogueId();
						if (isAddOnExistInOfferProduct(wirelineProduct, parentProductCatalogueId)) {
							if (!isAddOnExistInCartItem(addOns, parentProductCatalogueId)) {
								addOnMissingList.add(productCatalogueId + " requires " + parentProductCatalogueId);
							}
						}
					}
				}
			}
		}
	}

	private WirelineOfferProduct getOfferProduct(List<WirelineOfferProduct> wirelineOfferProductList,
			String productCd) {

		if (!CollectionUtils.isEmpty(wirelineOfferProductList)) {
			for (WirelineOfferProduct x : wirelineOfferProductList) {
				if (productCd.equalsIgnoreCase(x.getProductTypeCode())) {
					return x;
				}
			}
		}

		return null;
	}

	private boolean isAddOnExistInCartItem(List<FFHProductPlanAddOnTypeVO> addOns, String productCatalogueId) {

		if (!CollectionUtils.isEmpty(addOns)) {
			for (FFHProductPlanAddOnTypeVO x : addOns) {
				if (productCatalogueId.equals(x.getProductCatalogueIdentifier().getProductCatalogueId())) {
					return true;
				}
			}
		}

		return false;
	}

	private boolean isAddOnExistInOfferProduct(WirelineOfferProduct wirelineProduct, String productCatalogueId) {

		if (wirelineProduct != null && wirelineProduct.getAddOn() != null) {
			for (ProductCatalogueItem x : wirelineProduct.getAddOn().getIncludedProductCatalogueItemList()) {
				if (productCatalogueId.equals(x.getProductCatalogueIdentifier().getProductCatalogueId())) {
					return true;
				}
			}
	
			for (ProductCatalogueItem x : wirelineProduct.getAddOn().getOptionalProductCatalogueItemList()) {
				if (productCatalogueId.equals(x.getProductCatalogueIdentifier().getProductCatalogueId())) {
					return true;
				}
			}
		}

		return false;
	}

}
