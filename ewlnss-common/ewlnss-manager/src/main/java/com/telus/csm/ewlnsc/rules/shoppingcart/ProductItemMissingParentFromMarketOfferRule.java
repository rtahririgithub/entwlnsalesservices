package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailResponseVO2;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AddOn;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Feature;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueIdentifier;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductComponent;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ProductItemMissingParentFromMarketOfferRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductItemMissingParentFromMarketOfferRule.class);
	
	private CartItemVO cartItem;

	public ProductItemMissingParentFromMarketOfferRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfied = true;
		String functionName="ProductItemMissingParentFromMarketOfferRule.isSatisfiedBy";
		logger.enter(functionName);
		ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
		String cartItemRelationId = null;
		String offerId = null;
		GetSalesOfferDetailResponseVO2 offerResponseVO = null;
		List<String> productItemMissingParentList = new ArrayList<String>();
		if(cartItem!=null && cartItem.isSalesOrderItem()){
			//Getting the offer
			if(cartItem.getProductMarketOffering()!=null && cartItem.getProductMarketOffering().getOfferHeader()!=null && !StringUtils.isBlank(cartItem.getProductMarketOffering().getOfferHeader().getOfferId())){
				offerId = cartItem.getProductMarketOffering().getOfferHeader().getOfferId();
				
				offerResponseVO = shoppingCartContextVO.getOfferByCartItemOfferId(offerId);
				if(offerResponseVO!=null && offerResponseVO.getOffer()!=null){
					
					if(offerResponseVO.getOffer().getOfferProduct()!=null && !CollectionUtils.isEmpty(offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList())){
						if(cartItem.getProducts()!=null){
							for(WirelineOfferProduct wirelineOfferProduct : offerResponseVO.getOffer().getOfferProduct().getWirelineOfferProductList()){
								if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getInternetProduct()!=null){
									productItemMissingParentList.addAll(validateCatalogueItemIdsForInternet(cartItem.getProducts().getInternetProduct(),wirelineOfferProduct));
								}
								
								if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getTelevisionProduct()!=null){
									productItemMissingParentList.addAll(validateCatalogueItemIdsForTelevision(cartItem.getProducts().getTelevisionProduct(),wirelineOfferProduct));
								}
								
								if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode()) && cartItem.getProducts().getHomePhoneProduct()!=null){
									productItemMissingParentList.addAll(validateCatalogueIteIdsForHomePhone(cartItem.getProducts().getHomePhoneProduct(),wirelineOfferProduct));
								}
							}
						}
						
						
					}else{
						logger.debug(functionName, "No products were returned for market offer with offerId=" + offerId + " and cartItemRelationId=" + cartItemRelationId);
					}
					
				}else{
					logger.debug(functionName, "No offer was found for cartItem with cartRelationId=" + cartItemRelationId + " and offerId=" + offerId);
				}
			}else{
				logger.debug(functionName, "Skipping rule since offerId was not provided for cartItem with cartItemRelationId=" + cartItemRelationId);
			}
			
			if(!CollectionUtils.isEmpty(productItemMissingParentList)){
				isSatisfied = false;
				trace.setValidationPassedInd(isSatisfied);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.PRODUCT_ITEM_MISSING_PARENT_FROM_MARKET_OFFER, this.getClass().getSimpleName()+": One or more elements in the cart Item's market Offer must be removed as its parent is missing or has been removed. Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(productItemMissingParentList)));
				traces.add(trace);
			}
			
		}
		
		logger.exit(functionName);
		return isSatisfied;
	}


	private List<String> validateCatalogueItemIdsForInternet(InternetProductTypeVO internetProduct,WirelineOfferProduct wirelineOfferProduct) {
		List<String> invalidInternetItemList = new ArrayList<String>();
		
		if(!CollectionUtils.isEmpty(internetProduct.getAddOns()) && wirelineOfferProduct.getAddOn()!=null){
			invalidInternetItemList.addAll(validateAddOnsCatalogueIds(internetProduct.getAddOns(),wirelineOfferProduct.getAddOn()));
		}
		
		if(!CollectionUtils.isEmpty(internetProduct.getDiscounts()) && !CollectionUtils.isEmpty(wirelineOfferProduct.getDiscountList())){
			invalidInternetItemList.addAll(validateDiscountsCatalogueIds(internetProduct.getDiscounts(),wirelineOfferProduct.getDiscountList()));
		}
		
		if(!CollectionUtils.isEmpty(internetProduct.getProductComponents()) && !CollectionUtils.isEmpty(wirelineOfferProduct.getProductComponentList())){
			invalidInternetItemList.addAll(validateProductComponentCatalogueIds(internetProduct.getProductComponents(),wirelineOfferProduct.getProductComponentList()));
		}
		
		return invalidInternetItemList;
	}
	
	
	private List<String> validateCatalogueItemIdsForTelevision(TelevisionProductTypeVO televisionProduct, WirelineOfferProduct wirelineOfferProduct) {
		List<String> invalidTelevisionItemList = new ArrayList<String>();
		
		if(!CollectionUtils.isEmpty(televisionProduct.getAddOns()) && wirelineOfferProduct.getAddOn()!=null){
			invalidTelevisionItemList.addAll(validateAddOnsCatalogueIds(televisionProduct.getAddOns(),wirelineOfferProduct.getAddOn()));
		}
		
		if(!CollectionUtils.isEmpty(televisionProduct.getDiscounts()) && !CollectionUtils.isEmpty(wirelineOfferProduct.getDiscountList())){
			invalidTelevisionItemList.addAll(validateDiscountsCatalogueIds(televisionProduct.getDiscounts(),wirelineOfferProduct.getDiscountList()));
		}
		
		if(!CollectionUtils.isEmpty(televisionProduct.getProductComponents()) && !CollectionUtils.isEmpty(wirelineOfferProduct.getProductComponentList())){
			invalidTelevisionItemList.addAll(validateProductComponentCatalogueIds(televisionProduct.getProductComponents(),wirelineOfferProduct.getProductComponentList()));
		}
		
		return invalidTelevisionItemList;
	}
	


	private List<String> validateCatalogueIteIdsForHomePhone(HomePhoneProductTypeVO homePhoneProduct,WirelineOfferProduct wirelineOfferProduct) {
		List<String> invalidHomePhoneItemList = new ArrayList<String>();
		
		if(!CollectionUtils.isEmpty(homePhoneProduct.getAddOns()) && wirelineOfferProduct.getAddOn()!=null){
			invalidHomePhoneItemList.addAll(validateAddOnsCatalogueIds(homePhoneProduct.getAddOns(),wirelineOfferProduct.getAddOn()));
		}
		
		if(!CollectionUtils.isEmpty(homePhoneProduct.getDiscounts()) && !CollectionUtils.isEmpty(wirelineOfferProduct.getDiscountList())){
			invalidHomePhoneItemList.addAll(validateDiscountsCatalogueIds(homePhoneProduct.getDiscounts(),wirelineOfferProduct.getDiscountList()));
		}
		
		if(!CollectionUtils.isEmpty(homePhoneProduct.getProductComponents()) && !CollectionUtils.isEmpty(wirelineOfferProduct.getProductComponentList())){
			invalidHomePhoneItemList.addAll(validateProductComponentCatalogueIds(homePhoneProduct.getProductComponents(),wirelineOfferProduct.getProductComponentList()));
		}
		
		if(!CollectionUtils.isEmpty(homePhoneProduct.getFeatures()) && wirelineOfferProduct.getFeature()!=null){
			invalidHomePhoneItemList.addAll(validateFeatureCatalogueIds(homePhoneProduct.getFeatures(),wirelineOfferProduct.getFeature()));
		}
		
		return invalidHomePhoneItemList;
	}

	private List<String> validateFeatureCatalogueIds(List<FFHFeatureTypeVO> features, Feature feature) {
		List<String> invalidFeatureCatalogueIdList = new ArrayList<String>();
		
		for(FFHFeatureTypeVO cartFeature : features){
			if(cartFeature.getProductCatalogueIdentifier()!=null){
				if(!CollectionUtils.isEmpty(feature.getIncludedProductCatalogueItemList())){
					for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem offerProductCatalogueItem : feature.getIncludedProductCatalogueItemList()){
						if(offerProductCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId().equals(cartFeature.getProductCatalogueIdentifier().getProductCatalogueId()) && (StringUtils.isEmpty(cartFeature.getProductCatalogueIdentifier().getParentProductCatalogueId()) || EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction()))){
							invalidFeatureCatalogueIdList.add("The feature with catalogueId=" + cartFeature.getProductCatalogueIdentifier().getProductCatalogueId() + " must be removed since parentCatalogueId is missing or has been removed.");
						}
					}
				}
			}
		}
		
		return invalidFeatureCatalogueIdList;
	}

	private List<String> validateProductComponentCatalogueIds(List<ProductComponentVO> cartProductComponents, List<ProductComponent> productComponentList) {
		List<String> invalidProductComponentList = new ArrayList<String>();
		for(ProductComponentVO cartProductComponent : cartProductComponents){
			for(ProductComponent offerProductComponent : productComponentList){
				if(!CollectionUtils.isEmpty(offerProductComponent.getProductCatalogueItemList())){
					for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem offerProductCatalogueItem : offerProductComponent.getProductCatalogueItemList()){
						if(cartProductComponent.getParentProductCatalogueId().equals(offerProductCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId()) && (!offerItemParentCatIdIsInProductComponent(offerProductCatalogueItem.getParentProductCatalogueIdentifier().getProductCatalogueId(),cartProductComponents) || EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartProductComponent.getAction()))){
							invalidProductComponentList.add("the productComponent with catalogueId=" + cartProductComponent.getProductCatalogueId() + " must be removed since parentCatalogueId is missing or has been removed.");
						}
					}
				}
			}
		}
		return invalidProductComponentList;
	}


	private List<String> validateAddOnsCatalogueIds(List<FFHProductPlanAddOnTypeVO> addOns,AddOn addOn) {
		List<String> invalidAddOnList = new ArrayList<String>();
		for(FFHProductPlanAddOnTypeVO cartAddon : addOns){
			if(cartAddon.getProductCatalogueIdentifier()!=null){
				if(!CollectionUtils.isEmpty(addOn.getIncludedProductCatalogueItemList())){
					for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem offerCatalogueItem : addOn.getIncludedProductCatalogueItemList()){
						if(offerCatalogueItem.getParentProductCatalogueIdentifier().getProductCatalogueId().equals(cartAddon.getProductCatalogueIdentifier().getProductCatalogueId()) && (!offerItemParentCatIdIsInAddOnCart(offerCatalogueItem.getParentProductCatalogueIdentifier(),addOns) || EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddon.getProductCatalogueIdentifier().getAction()))){
							invalidAddOnList.add("the Addon with catalogueId=" + cartAddon.getProductCatalogueIdentifier().getProductCatalogueId() + " must be removed since the parentCatalogueId is missing or has been removed.");
						}
					}
				}
			}
			
		}
		return invalidAddOnList;
	}
	
	private boolean offerItemParentCatIdIsInAddOnCart(ProductCatalogueIdentifier parentProductCatalogueIdentifier,List<FFHProductPlanAddOnTypeVO> addOns) {
		boolean offerItemParentCatIdIsInCartInd = false;
		for(FFHProductPlanAddOnTypeVO addOnTypeVO : addOns){
			if(addOnTypeVO.getProductCatalogueIdentifier()!=null){
				if(parentProductCatalogueIdentifier.getProductCatalogueId().equals(addOnTypeVO.getProductCatalogueIdentifier().getParentProductCatalogueId())){
					offerItemParentCatIdIsInCartInd = true;
					break;
				}
			}
		}
		return offerItemParentCatIdIsInCartInd;
	}
	
	private boolean offerItemParentCatIdIsInDiscountCart(String parentProductCatalogueId,List<FFHDiscountTypeVO> cartDiscounts) {
		boolean offerItemParentCatIdIsInCartInd = false;
		for(FFHDiscountTypeVO discount : cartDiscounts){
			if(!CollectionUtils.isEmpty(discount.getProductCatalogueIdentifiers())){
				for(ProductComponentVO productComponentVO : discount.getProductCatalogueIdentifiers()){
					if(parentProductCatalogueId.equals(productComponentVO.getParentProductCatalogueId())){
						offerItemParentCatIdIsInCartInd = true;
						break;
					}
				}
			}
		}
		return offerItemParentCatIdIsInCartInd;
	}
	
	private boolean offerItemParentCatIdIsInProductComponent(String parentProductCatalogueId,List<ProductComponentVO> cartProductComponents) {
		boolean offerItemParentCatIdIsInCartInd = false;
		for(ProductComponentVO productComponentVO : cartProductComponents){
				if(parentProductCatalogueId.equals(productComponentVO.getParentProductCatalogueId())){
					offerItemParentCatIdIsInCartInd = true;
					break;
				}
				
		}
		return offerItemParentCatIdIsInCartInd;
	}


	private List<String> validateDiscountsCatalogueIds(List<FFHDiscountTypeVO> cartDiscounts,List<Discount> discountList) {
		List<String> invalidDiscountList = new ArrayList<String>();
		for(FFHDiscountTypeVO cartDiscount : cartDiscounts){
			if(!CollectionUtils.isEmpty(cartDiscount.getProductCatalogueIdentifiers())){
				for(ProductComponentVO cartProductComponentVO : cartDiscount.getProductCatalogueIdentifiers()){
					for(Discount offerDiscount : discountList){
						if(!CollectionUtils.isEmpty(offerDiscount.getProductCatalogueItemList())){
							for(com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem offerCatalogueItem : offerDiscount.getProductCatalogueItemList()){
								if(offerCatalogueItem.getParentProductCatalogueIdentifier().getProductCatalogueId().equals(cartProductComponentVO.getProductCatalogueId()) && (offerItemParentCatIdIsInDiscountCart(offerCatalogueItem.getParentProductCatalogueIdentifier().getProductCatalogueId(),cartDiscounts) || EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartProductComponentVO.getAction()))){
									invalidDiscountList.add("the Addon with catalogueId=" + cartProductComponentVO.getProductCatalogueId() + " must be removed since the parentCatalogueId is missing or has been removed.");
								}
							}
						}
					}
				}
			}
		}
		
		return invalidDiscountList;
	}


	
	
}
