package com.telus.csm.ewlnsc.rules.shoppingcart;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.delegate.GetAvailableProductItemDelegate;
import com.telus.csm.ewlnsc.domain.AdditionalProductItemTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHDiscountTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrorCodes;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationErrors;
import com.telus.csm.ewlnsc.domain.ShoppingCartValidationTraceVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.domain.product.AvailableHomePhoneProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableInternetProductItemVO;
import com.telus.csm.ewlnsc.domain.product.AvailableTelevisionProductItemVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemGroupCategoryVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ProductItemValidationRule extends AbstractSpecification<ShoppingCartContextVO, ShoppingCartValidationTraceVO>{

	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductItemValidationRule.class);
	
	private CartItemVO cartItem;

	public ProductItemValidationRule(CartItemVO cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public boolean isSatisfiedBy(ShoppingCartContextVO shoppingCartContextVO, List<ShoppingCartValidationTraceVO> traces) {
		super.isSatisfiedBy(shoppingCartContextVO, traces);
		boolean isSatisfiedBy = true;
		String functionName="ProductItemValidationRule.isSatisfiedBy";
		logger.enter(functionName);
		String cartItemRelationId=null;
		String cartItemId=null;
		List<String> cannotCarryOverProductList = new ArrayList<String>();
		List<String> childOfRemovedProductItemList = new ArrayList<String>();
		
		if(!StringUtils.isEmpty(cartItem.getCartItemRelationId())){
			cartItemRelationId = cartItem.getCartItemRelationId();
		}
		
		if(!StringUtils.isEmpty(cartItem.getCartItemId())){
			cartItemId = cartItem.getCartItemId();
		}
		GetAvailableProductItemDelegate delegate = new GetAvailableProductItemDelegate();
		GetAvailableProductItemDelegateResponse availableProductItemResponse = delegate.execute(shoppingCartContextVO.getShoppingCartVO().getOperationHeader().getSalesTransactionId(), shoppingCartContextVO);
		
		if(availableProductItemResponse!=null && availableProductItemResponse.getResponse()!=null){
			
			if(cartItem.getProducts().getInternetProduct()!=null){
				InternetProductTypeVO internetProduct = cartItem.getProducts().getInternetProduct();	
				if(!CollectionUtils.isEmpty(availableProductItemResponse.getResponse().getInternetProductItems())){
					cannotCarryOverProductList.addAll(validateCarryOverProductItemListForInternet(availableProductItemResponse.getResponse().getInternetProductItems(),internetProduct));
				}
				
				//Logic to cover the rule: ChildOfRemovedProductItemRule for Internet product
				childOfRemovedProductItemList.addAll(validateChildOfRemovedProductItemForInternet(internetProduct));
			}
			
			if(cartItem.getProducts().getTelevisionProduct()!=null){
				TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();
				if(!CollectionUtils.isEmpty(availableProductItemResponse.getResponse().getTelevisionProductItems())){
					cannotCarryOverProductList.addAll(validateCarryOverProductItemListForTelevision(availableProductItemResponse.getResponse().getTelevisionProductItems(),televisionProduct));					
				}
				
				//Logic to cover the rule: ChildOfRemovedProductItemRule for Television product
				childOfRemovedProductItemList.addAll(validateChildOfRemovedProductItemForTelevision(televisionProduct));
			}
			
			if(cartItem.getProducts().getHomePhoneProduct()!=null){
				HomePhoneProductTypeVO homePhoneProduct = cartItem.getProducts().getHomePhoneProduct();
				if(!CollectionUtils.isEmpty(availableProductItemResponse.getResponse().getHomePhoneProductItems())){
					cannotCarryOverProductList.addAll(validateCarryOverProductItemListForHomePhone(availableProductItemResponse.getResponse().getHomePhoneProductItems(),homePhoneProduct));
				}
				
				//Logic to cover the rule: ChildOfRemovedProductItemRule for Home Phone product
				childOfRemovedProductItemList.addAll(validateChildOfRemovedProductItemForHomePhone(homePhoneProduct));
			}
			
			if(!CollectionUtils.isEmpty(cannotCarryOverProductList)){
				ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
				isSatisfiedBy = false;
				trace.setValidationPassedInd(isSatisfiedBy);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.PRODUCT_ITEM_CANNOT_CARRY_OVER, this.getClass().getSimpleName() + ": One or more products of the existing service or pending order cannot be kept. Details: " + EnterpriseWLNOrderUtil.getFormatedStringList(cannotCarryOverProductList)));
				trace.setCartItemRelationId(cartItemRelationId);
				if(!StringUtils.isBlank(cartItemId)){
					trace.setShoppingCartItemId(cartItemId);
				}
				traces.add(trace);
			}
			
			if(!CollectionUtils.isEmpty(childOfRemovedProductItemList)){
				ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
				isSatisfiedBy = false;
				trace.setValidationPassedInd(isSatisfiedBy);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.CHILD_OF_REMOVED_PRODUCT_ITEM, "ChildOfRemovedProductItemRule: One or more productItems in the cartItem with cartItemRelationId=" + cartItem.getCartItemRelationId() + " must be removed due to the following removed productItems. " + EnterpriseWLNOrderUtil.getFormatedStringList(childOfRemovedProductItemList)));
				trace.setCartItemRelationId(cartItemRelationId);
				if(!StringUtils.isBlank(cartItemId)){
					trace.setShoppingCartItemId(cartItemId);
				}
				traces.add(trace);
			}
			
		}else{
			if(availableProductItemResponse==null || (availableProductItemResponse.getErrorResponse()!=null)){
				ShoppingCartValidationTraceVO trace = new ShoppingCartValidationTraceVO(this.getClass().getName());
				isSatisfiedBy = false;
				trace.setValidationPassedInd(isSatisfiedBy);
				trace.setErrors(ShoppingCartValidationErrors.getErrorForCodeAndCustomeMessage(ShoppingCartValidationErrorCodes.AVAILABLE_PRODUCT_ITEM_DELEGATE_NULL, this.getClass().getSimpleName() + ": AvailableProductItemDelegate returned error."));
				trace.setCartItemRelationId(cartItemRelationId);
				if(!StringUtils.isBlank(cartItemId)){
					trace.setShoppingCartItemId(cartItemId);
				}
				traces.add(trace);
			}
			
		}
		
		logger.exit(functionName);
		return isSatisfiedBy;
	}
	
	
	private List<String> validateChildOfRemovedProductItemForInternet(InternetProductTypeVO internetProduct) {
		List<String> childOfRemovedProductItemList = new ArrayList<String>();
		
		//checking the productComponents
		if(!CollectionUtils.isEmpty(internetProduct.getProductComponents())){
			for(ProductComponentVO productComponent : internetProduct.getProductComponents()){
				String parentCatalogueId = productComponent.getParentProductCatalogueId();
				if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productComponent.getAction())){
					childOfRemovedProductItemList.addAll(parentComponentHasChildComponentsNonRemove(parentCatalogueId,internetProduct.getProductComponents()));					
				}
			}
		}
		
		//checking the addOns
		if(!CollectionUtils.isEmpty(internetProduct.getAddOns())){
			for(FFHProductPlanAddOnTypeVO cartAddon : internetProduct.getAddOns()){				
				if(cartAddon.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddon.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = cartAddon.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasAddOnChildComponentsNonRemove(parentCatalogueId, internetProduct.getAddOns()));
					}					
				}
			}
		}
		
		//checking the additionalProductItems
		if(!CollectionUtils.isEmpty(internetProduct.getAdditionalProductItemList())){
			for(AdditionalProductItemTypeVO additionalItem : internetProduct.getAdditionalProductItemList()){
				if(additionalItem.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(additionalItem.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = additionalItem.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasAdditionalItemChildComponentsNonRemove(parentCatalogueId, internetProduct.getAdditionalProductItemList()));
					}
				}
			}
		}
		return childOfRemovedProductItemList;
	}
	
	private List<String> parentComponentHasAdditionalItemChildComponentsNonRemove(String parentCatalogueId, List<AdditionalProductItemTypeVO> additionalProductItemList) {
		List<String> nonValidChildComponentList = new ArrayList<String>();
		for(AdditionalProductItemTypeVO additionalProductItem : additionalProductItemList){
			if(additionalProductItem.getProductCatalogueIdentifier()!=null && parentCatalogueId.equals(additionalProductItem.getProductCatalogueIdentifier().getProductCatalogueId()) && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(additionalProductItem.getProductCatalogueIdentifier().getAction())){
				nonValidChildComponentList.add("Child Additional productItem with catalogueId=" + additionalProductItem.getProductCatalogueIdentifier().getProductCatalogueId() + " must be removed, since it's parent component has action : REMOVE");
			}
		}
		
		return nonValidChildComponentList;
	}

	private List<String> parentComponentHasAddOnChildComponentsNonRemove(String parentCatalogueId,List<FFHProductPlanAddOnTypeVO> addOns) {
		List<String> nonValidChildComponentList = new ArrayList<String>();
		for(FFHProductPlanAddOnTypeVO cartAddOn : addOns){
			if(cartAddOn.getProductCatalogueIdentifier()!=null && parentCatalogueId.equals(cartAddOn.getProductCatalogueIdentifier().getProductCatalogueId()) && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddOn.getProductCatalogueIdentifier().getAction())){
				nonValidChildComponentList.add("Child AddOn component with catalogueId=" + cartAddOn.getProductCatalogueIdentifier().getProductCatalogueId() + " must be removed, since it's parent component has action : REMOVE");
			}
		}
		return nonValidChildComponentList;
	}

	private List<String> parentComponentHasChildComponentsNonRemove(String parentCatalogueId,List<ProductComponentVO> productComponents) {
		List<String> nonValidChildComponentList = new ArrayList<String>();
		for(ProductComponentVO productComponentVO : productComponents){
			if(productComponentVO.getProductCatalogueId().equals(parentCatalogueId) && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productComponentVO.getAction())){
				nonValidChildComponentList.add("Child Component product with catalogueId=" + productComponentVO.getProductCatalogueId() + " must be removed, since it's parent component has action : REMOVE");
			}
		}
		return nonValidChildComponentList;
	}
	

	private List<String> parentComponentHasFeatureChildComponentsNonRemove(String parentCatalogueId,List<FFHFeatureTypeVO> features) {
		List<String> nonValidChildComponentList = new ArrayList<String>();
		for(FFHFeatureTypeVO cartFeature : features){
			if(cartFeature.getProductCatalogueIdentifier().getProductCatalogueId().equals(parentCatalogueId) && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction())){
				nonValidChildComponentList.add("Child Component product with catalogueId=" + cartFeature.getProductCatalogueIdentifier().getProductCatalogueId() + " must be removed, since it's parent component has action : REMOVE");
			}
		}
		return nonValidChildComponentList;
	}

	private List<String> validateChildOfRemovedProductItemForTelevision(TelevisionProductTypeVO televisionProduct) {
		List<String> childOfRemovedProductItemList = new ArrayList<String>();
		
		//checking the productComponents
		if(!CollectionUtils.isEmpty(televisionProduct.getProductComponents())){
			for(ProductComponentVO productComponent : televisionProduct.getProductComponents()){
				String parentCatalogueId = productComponent.getParentProductCatalogueId();
				if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productComponent.getAction())){
					childOfRemovedProductItemList.addAll(parentComponentHasChildComponentsNonRemove(parentCatalogueId,televisionProduct.getProductComponents()));					
				}
			}
		}
		
		//checking the addOns
		if(!CollectionUtils.isEmpty(televisionProduct.getAddOns())){
			for(FFHProductPlanAddOnTypeVO cartAddon : televisionProduct.getAddOns()){				
				if(cartAddon.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddon.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = cartAddon.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasAddOnChildComponentsNonRemove(parentCatalogueId, televisionProduct.getAddOns()));
					}					
				}
			}
		}
		
		//checking the additionalProductItems
		if(!CollectionUtils.isEmpty(televisionProduct.getAdditionalProductItemList())){
			for(AdditionalProductItemTypeVO additionalItem : televisionProduct.getAdditionalProductItemList()){
				if(additionalItem.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(additionalItem.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = additionalItem.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasAdditionalItemChildComponentsNonRemove(parentCatalogueId, televisionProduct.getAdditionalProductItemList()));
					}
				}
			}
		}
		return childOfRemovedProductItemList;
	}
	
	private List<String> validateChildOfRemovedProductItemForHomePhone(HomePhoneProductTypeVO homePhoneProduct) {
		List<String> childOfRemovedProductItemList = new ArrayList<String>();
		
		//checking the productComponents
		if(!CollectionUtils.isEmpty(homePhoneProduct.getProductComponents())){
			for(ProductComponentVO productComponent : homePhoneProduct.getProductComponents()){
				String parentCatalogueId = productComponent.getParentProductCatalogueId();
				if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(productComponent.getAction())){
					childOfRemovedProductItemList.addAll(parentComponentHasChildComponentsNonRemove(parentCatalogueId,homePhoneProduct.getProductComponents()));					
				}
			}
		}
		
		//checking the addOns
		if(!CollectionUtils.isEmpty(homePhoneProduct.getAddOns())){
			for(FFHProductPlanAddOnTypeVO cartAddon : homePhoneProduct.getAddOns()){				
				if(cartAddon.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddon.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = cartAddon.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasAddOnChildComponentsNonRemove(parentCatalogueId, homePhoneProduct.getAddOns()));
					}					
				}
			}
		}
		
		//checking the additionalProductItems
		if(!CollectionUtils.isEmpty(homePhoneProduct.getAdditionalProductItemList())){
			for(AdditionalProductItemTypeVO additionalItem : homePhoneProduct.getAdditionalProductItemList()){
				if(additionalItem.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(additionalItem.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = additionalItem.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasAdditionalItemChildComponentsNonRemove(parentCatalogueId, homePhoneProduct.getAdditionalProductItemList()));
					}
				}
			}
		}
		
		//checking the features
		if(!CollectionUtils.isEmpty(homePhoneProduct.getFeatures())){
			for(FFHFeatureTypeVO cartFeature : homePhoneProduct.getFeatures()){
				if(cartFeature.getProductCatalogueIdentifier()!=null){
					if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction())){
						String parentCatalogueId = cartFeature.getProductCatalogueIdentifier().getParentProductCatalogueId();
						childOfRemovedProductItemList.addAll(parentComponentHasFeatureChildComponentsNonRemove(parentCatalogueId, homePhoneProduct.getFeatures()));
					}
				}
			}
		}
		
		return childOfRemovedProductItemList;
	}

	private List<String> validateCarryOverProductItemListForInternet(List<AvailableInternetProductItemVO> internetProductItems, InternetProductTypeVO internetProduct) {
		//checking addOn, discountList, sweetenerList and additionalProductItem of the product.
		String functionName="validateCarryOverProductItemListForInternet";
		List<String> nonValidCarryOverProductList = new ArrayList<String>();
		
		for (AvailableInternetProductItemVO availableInternetItemVO : internetProductItems) {
			if (!CollectionUtils.isEmpty(availableInternetItemVO.getProductItemGroupCategories())) {
				for (ProductItemGroupCategoryVO productItemCategoryVO : availableInternetItemVO.getProductItemGroupCategories()) {
					if (!CollectionUtils.isEmpty(productItemCategoryVO.getProductItems())) {
						for (ProductItemVO productItem : productItemCategoryVO.getProductItems()) {
							if (!CollectionUtils.isEmpty(internetProduct.getAddOns())) {
								nonValidCarryOverProductList.addAll(validateAddonsAvalability(internetProduct.getAddOns(), productItem));
							}

							if (!CollectionUtils.isEmpty(internetProduct.getDiscounts())) {
								nonValidCarryOverProductList.addAll(validateDiscountsAvailability(internetProduct.getDiscounts(), productItem));
							}

							if (!CollectionUtils.isEmpty(internetProduct.getSweeteners())) {
								nonValidCarryOverProductList.addAll(validateSweetenersAvailability(internetProduct.getSweeteners(), productItem));
							}

							if (!CollectionUtils.isEmpty(internetProduct.getAdditionalProductItemList())) {
								nonValidCarryOverProductList.addAll(validateAdditionalProductsAvailability(internetProduct.getAdditionalProductItemList(), productItem));
							}
						}
					} else {
						logger.debug(functionName,"No product items were found for Internet from AvailableProductItemDelegate response");
					}
				}
			}
		}
		
		
		return nonValidCarryOverProductList;
	}
	
	private List<String> validateCarryOverProductItemListForTelevision(List<AvailableTelevisionProductItemVO> televisionProductItems, TelevisionProductTypeVO televisionProduct) {
		//checking addOn, discountList, sweetenerList and additionalProductItem of the product.
		String functionName="validateCarryOverProductItemListForTelevision";
		List<String> nonValidCarryOverProductList = new ArrayList<String>();
		
		
		for (AvailableTelevisionProductItemVO availableTelevisionItemVO : televisionProductItems) {
			if (!CollectionUtils.isEmpty(availableTelevisionItemVO.getProductItemGroupCategories())) {
				for (ProductItemGroupCategoryVO productItemCategoryVO : availableTelevisionItemVO.getProductItemGroupCategories()) {
					if (!CollectionUtils.isEmpty(productItemCategoryVO.getProductItems())) {
						for (ProductItemVO productItem : productItemCategoryVO.getProductItems()) {
							if (!CollectionUtils.isEmpty(televisionProduct.getAddOns())) {
								nonValidCarryOverProductList.addAll(validateAddonsAvalability(televisionProduct.getAddOns(), productItem));
							}

							if (!CollectionUtils.isEmpty(televisionProduct.getDiscounts())) {
								nonValidCarryOverProductList.addAll(validateDiscountsAvailability(televisionProduct.getDiscounts(), productItem));
							}

							if (!CollectionUtils.isEmpty(televisionProduct.getSweeteners())) {
								nonValidCarryOverProductList.addAll(validateSweetenersAvailability(televisionProduct.getSweeteners(), productItem));
							}

							if (!CollectionUtils.isEmpty(televisionProduct.getAdditionalProductItemList())) {
								nonValidCarryOverProductList.addAll(validateAdditionalProductsAvailability(televisionProduct.getAdditionalProductItemList(), productItem));
							}
						}
					} else {
						logger.debug(functionName,"No product items were found for Television from AvailableProductItemDelegate response");
					}
				}
			}
		}
		
		return nonValidCarryOverProductList;
	}
	
	private List<String> validateCarryOverProductItemListForHomePhone(List<AvailableHomePhoneProductItemVO> homePhoneProductItems, HomePhoneProductTypeVO homePhoneProduct) {
		//checking addOn, discountList, sweetenerList and additionalProductItem of the product.
		String functionName="validateCarryOverProductItemListForHomePhone";
		List<String> nonValidCarryOverProductList = new ArrayList<String>();
		
		
		for (AvailableHomePhoneProductItemVO availableHomePhoneItemVO : homePhoneProductItems) {
			if (!CollectionUtils.isEmpty(availableHomePhoneItemVO.getProductItemGroupCategories())) {
				for (ProductItemGroupCategoryVO productItemCategoryVO : availableHomePhoneItemVO.getProductItemGroupCategories()) {
					if (!CollectionUtils.isEmpty(productItemCategoryVO.getProductItems())) {
						for (ProductItemVO productItem : productItemCategoryVO.getProductItems()) {
							//TODO: ask Aman how to handle equipment for carryOver rule
							
							if (!CollectionUtils.isEmpty(homePhoneProduct.getAddOns())) {
								nonValidCarryOverProductList.addAll(validateAddonsAvalability(homePhoneProduct.getAddOns(), productItem));
							}

							if (!CollectionUtils.isEmpty(homePhoneProduct.getDiscounts())) {
								nonValidCarryOverProductList.addAll(validateDiscountsAvailability(homePhoneProduct.getDiscounts(), productItem));
							}

							if (!CollectionUtils.isEmpty(homePhoneProduct.getSweeteners())) {
								nonValidCarryOverProductList.addAll(validateSweetenersAvailability(homePhoneProduct.getSweeteners(), productItem));
							}

							if (!CollectionUtils.isEmpty(homePhoneProduct.getAdditionalProductItemList())) {
								nonValidCarryOverProductList.addAll(validateAdditionalProductsAvailability(homePhoneProduct.getAdditionalProductItemList(), productItem));
							}

							if (!CollectionUtils.isEmpty(homePhoneProduct.getFeatures())) {
								nonValidCarryOverProductList.addAll(validateFeaturesAvailability(homePhoneProduct.getFeatures(), productItem));
							}
						}
					} else {
						logger.debug(functionName,"No product items were found for Home Phone from AvailableProductItemDelegate response");
					}
				}
			}
		}
		
		
		return nonValidCarryOverProductList;
	}

	private List<String> validateFeaturesAvailability(List<FFHFeatureTypeVO> features,ProductItemVO productItem) {
		List<String> invalidFeatureList = new ArrayList<String>();
		if(!CollectionUtils.isEmpty(features)){
			for(FFHFeatureTypeVO cartFeature : features){
				if(EnterpriseWLNOrderUtil.productItemCatalogueIdMatches(productItem.getProductItemIdentifier(), cartFeature.getProductCatalogueIdentifier()) && !productItem.isCarryOverInd() && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction())){
					invalidFeatureList.add("the following Feature product with productCatalogueId=" + cartFeature.getProductCatalogueIdentifier().getProductCatalogueId() + " on the existing service or pending order cannot be kept.");							
				}			
			}
		}
		return invalidFeatureList;
	}

	private List<String> validateSweetenersAvailability(List<FFHSweetenerTypeVO> sweeteners,ProductItemVO productItem) {
		List<String> invalidSweetenersList =new ArrayList<String>();
		if(!CollectionUtils.isEmpty(sweeteners)){
			for(FFHSweetenerTypeVO sweetener : sweeteners){
				//get the productCatalogueId to see if this one matches the addon from the cartItem
				//checking the discounts from the sweetener
				if(!CollectionUtils.isEmpty(sweetener.getDiscounts())){
						invalidSweetenersList.addAll(validateDiscountsAvailability(sweetener.getDiscounts(), productItem));
					}
				}
			}
		return invalidSweetenersList;
	}
	
	private List<String> validateAdditionalProductsAvailability(List<AdditionalProductItemTypeVO> additionalProductItemList, ProductItemVO productItem) {
		List<String> invalidAdditionalProductList = new ArrayList<String>();
		if(!CollectionUtils.isEmpty(additionalProductItemList)){
			for(AdditionalProductItemTypeVO cartAdditionalProduct : additionalProductItemList){
				//get the productCatalogueId to see if this one matches the addon from the cartItem
				if(EnterpriseWLNOrderUtil.productItemCatalogueIdMatches(productItem.getProductItemIdentifier(), cartAdditionalProduct.getProductCatalogueIdentifier()) && !productItem.isCarryOverInd() && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAdditionalProduct.getProductCatalogueIdentifier().getAction())){							
					invalidAdditionalProductList.add("the following Additional product with productCatalogueId=" + cartAdditionalProduct.getProductCatalogueIdentifier().getProductCatalogueId() + " on the existing service or pending order cannot be kept.");							
				}
			}
		}
		return invalidAdditionalProductList;
	}

	private List<String> validateDiscountsAvailability(List<FFHDiscountTypeVO> discounts,ProductItemVO productItem) {
		List<String> invalidDiscountList = new ArrayList<String>();
		if(!CollectionUtils.isEmpty(discounts)){
			for(FFHDiscountTypeVO cartDiscount : discounts){
				//get the productCatalogueId to see if this one matches the addon from the cartItem				
				if(EnterpriseWLNOrderUtil.productItemCatalogueIdMatches(productItem.getProductItemIdentifier(), cartDiscount.getProductCatalogueIdentifiers().get(0)) && !productItem.isCarryOverInd() && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartDiscount.getProductCatalogueIdentifiers().get(0).getAction())){							
					invalidDiscountList.add("the following Discount with productCatalogueId=" + cartDiscount.getProductCatalogueIdentifiers().get(0).getProductCatalogueId() + " on the existing service or pending order cannot be kept.");							
				}
			}
		}
		return invalidDiscountList;
	}

	private List<String> validateAddonsAvalability(List<FFHProductPlanAddOnTypeVO> addOns, ProductItemVO productItem) {
		List<String> invalidAddonList = new ArrayList<String>();
		if(!CollectionUtils.isEmpty(addOns)){
			for(FFHProductPlanAddOnTypeVO cartAddon : addOns){
				//get the productCatalogueId to see if this one matches the addon from the cartItem
				if(EnterpriseWLNOrderUtil.productItemCatalogueIdMatches(productItem.getProductItemIdentifier(),cartAddon.getProductCatalogueIdentifier()) && productItem.isExistingInd() && !productItem.isCarryOverInd() && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartAddon.getProductCatalogueIdentifier().getAction())){						
					invalidAddonList.add("the following addOn with productCatalogueId=" + cartAddon.getProductCatalogueIdentifier().getProductCatalogueId() + " on the existing service or pending order cannot be kept.");
				}
			}
		}
		return invalidAddonList;
	}	
}
