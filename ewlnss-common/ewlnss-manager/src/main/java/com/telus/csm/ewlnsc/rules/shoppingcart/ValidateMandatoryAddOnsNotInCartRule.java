package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;

public class ValidateMandatoryAddOnsNotInCartRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private WirelineOfferProduct offerProduct;
	private CartItemVO cartItem;
	private String offerId;
	
	public ValidateMandatoryAddOnsNotInCartRule(WirelineOfferProduct offerProduct,CartItemVO cartItemVO,String offerId) {
		this.offerProduct = offerProduct;
		this.cartItem = cartItemVO;
		this.offerId = offerId;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied = true;
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(MandatoryIncludedAddOnsNotInCartRule.class.getName());

		List<String> includedAddOnMissingList = new ArrayList<String>();
		
		if(cartItem.isSalesOrderItem()){
			
			//HSIC
			if (cartItem.getProducts().getInternetProduct() != null && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(offerProduct.getProductTypeCode())) {
				InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();
				
				if (offerProduct.getAddOn() != null && !CollectionUtils.isEmpty(offerProduct.getAddOn().getIncludedProductCatalogueItemList())) {
					for (ProductCatalogueItem tomAddOn : offerProduct.getAddOn().getIncludedProductCatalogueItemList()) {											
						FFHProductPlanAddOnTypeVO cartAddOn = getCartAddOn(internetProduct.getAddOns(), tomAddOn);
						if (cartAddOn == null) {
							includedAddOnMissingList.add(tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId());
						}
					}
				}
			}
			
			//TTV
			if (cartItem.getProducts().getTelevisionProduct() != null && EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(offerProduct.getProductTypeCode())) {
				TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();
				
				if (offerProduct.getAddOn() != null && !CollectionUtils.isEmpty(offerProduct.getAddOn().getIncludedProductCatalogueItemList())) {
					for (ProductCatalogueItem tomAddOn : offerProduct.getAddOn().getIncludedProductCatalogueItemList()) {											
						FFHProductPlanAddOnTypeVO cartAddOn = getCartAddOn(televisionProduct.getAddOns(), tomAddOn);
						if (cartAddOn == null) {
							includedAddOnMissingList.add(tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId());
						}
					}
				}
			}
			
			//SING
			if (cartItem.getProducts().getHomePhoneProduct() != null && EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(offerProduct.getProductTypeCode())) {
				HomePhoneProductTypeVO homePhoneProduct = cartItem.getProducts().getHomePhoneProduct();
				
				if (offerProduct.getAddOn() != null && !CollectionUtils.isEmpty(offerProduct.getAddOn().getIncludedProductCatalogueItemList())) {
					for (ProductCatalogueItem tomAddOn : offerProduct.getAddOn().getIncludedProductCatalogueItemList()) {											
						FFHProductPlanAddOnTypeVO cartAddOn = getCartAddOn(homePhoneProduct.getAddOns(), tomAddOn);
						if (cartAddOn == null) {
							includedAddOnMissingList.add(tomAddOn.getProductCatalogueIdentifier().getProductCatalogueId());
						}
					}
				}
			}
			
		}
		
		if (!CollectionUtils.isEmpty(includedAddOnMissingList)) {
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
					.getErrorForCode(ShoppingCartValidationErrorCodes.MANDATORY_ADDON_NOT_IN_CART);
			shoppingCartValidationError.setMessage(this.getClass().getSimpleName()
					+ ": One or more addOns in the cartItem's market offer with offerId = " + offerId + " ,is configured as 'included' which requires that it is added to the cartItem: "  
					+ " However that discount addOn element was not found in the cartItem or the addOn within the cart has action different than ADD: "
					+ includedAddOnMissingList.toString());
			trace.setValidationPassedInd(false);
			trace.setErrors(shoppingCartValidationError);
			traces.add(trace);
			isSatisfied = false;
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
