package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.TransactionType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;

public class ValidateProductComponentMarketOffeRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private WirelineOfferProduct wirelineOfferProduct;
	private CartItemVO cartItem;
	private String offerId;
	
	public ValidateProductComponentMarketOffeRule(WirelineOfferProduct wirelineOfferProduct,CartItemVO cartItem,String offerId) {
		this.wirelineOfferProduct = wirelineOfferProduct;
		this.cartItem = cartItem;
		this.offerId = offerId;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied = true;
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
		
		List<String> unmatchedProductComponentProductList = new ArrayList<String>();
		
		//HSIC
		if(cartItem.getProducts().getInternetProduct()!=null && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
			if(isOfferProductActivationOrUpgrade(wirelineOfferProduct) && !isProductComponentExactMatch(wirelineOfferProduct, cartItem.getProducts().getInternetProduct().getProductComponents())){
				unmatchedProductComponentProductList.add(wirelineOfferProduct.getProductTypeCode());
			}
		}
		
		//TTV
		if(cartItem.getProducts().getTelevisionProduct()!=null && EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
			if(isOfferProductActivationOrUpgrade(wirelineOfferProduct) && !isProductComponentExactMatch(wirelineOfferProduct, cartItem.getProducts().getTelevisionProduct().getProductComponents())){
				unmatchedProductComponentProductList.add(wirelineOfferProduct.getProductTypeCode());
			}
		}
		
		//SING
		if(cartItem.getProducts().getHomePhoneProduct()!=null && EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())){
			if(isOfferProductActivationOrUpgrade(wirelineOfferProduct) && !isProductComponentExactMatch(wirelineOfferProduct, cartItem.getProducts().getHomePhoneProduct().getProductComponents())){
				unmatchedProductComponentProductList.add(wirelineOfferProduct.getProductTypeCode());
			}
		}
		
		if (!CollectionUtils.isEmpty(unmatchedProductComponentProductList)) {
			ShoppingCartValidationErrors shoppingCartValidationError = ShoppingCartValidationErrors
					.getErrorForCode(ShoppingCartValidationErrorCodes.MARKET_OFFER_PRODUCT_COMPONENT_NOT_MATCHED);
			shoppingCartValidationError.setMessage(this.getClass().getSimpleName()
					+ ": The cartItem's productComponent do not match the existing market offer with offerId=" + offerId + " ,productComponent for that product: "
					+ unmatchedProductComponentProductList.toString());
			trace.setValidationPassedInd(false);
			trace.setErrors(shoppingCartValidationError);
			trace.setCartItemRelationId(cartItem.getCartItemRelationId());
			trace.setShoppingCartItemId(cartItem.getCartItemId());
			traces.add(trace);
			isSatisfied = false;
		}
		
		return isSatisfied;
	}
	
	private boolean isOfferProductActivationOrUpgrade(WirelineOfferProduct wirelineProduct) {

		if (wirelineProduct == null || wirelineProduct.getTransactionTypeList() == null) {
			return false;
		}
		
		for (TransactionType tranType : wirelineProduct.getTransactionTypeList()) {
			if (EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION.equalsIgnoreCase(tranType.getTransactionTypeCode())
					|| EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE.equalsIgnoreCase(tranType.getTransactionTypeCode())) {
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isProductComponentExactMatch(WirelineOfferProduct offerProduct,
			List<ProductComponentVO> cartProductComponents) {
		
		List<String> offerProductCompIds = new ArrayList<String>();
		List<String> cartProductcompIds = new ArrayList<String>();
		
		for (ProductComponent pc: offerProduct.getProductComponentList()) {
			for (ProductCatalogueItem pci : pc.getProductCatalogueItemList()) {
				offerProductCompIds.add(pci.getProductCatalogueIdentifier().getProductCatalogueId());
			}
		}
		
		for (ProductComponentVO pc : cartProductComponents) {
			if(!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(pc.getAction())){
				cartProductcompIds.add(pc.getProductCatalogueId());
			}
			
		}
		
		return offerProductCompIds.containsAll(cartProductcompIds) && cartProductcompIds.containsAll(offerProductCompIds);
		
	}
	
	
}
