package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;

public class OPHomePhoneChangeTransformer extends OPHomePhoneProvideTransformer {

	public static ProductOrderItem transformChange(OperationHeader operationHeader, CartItemVO cartItemVO, ShoppingCartContextVO shoppingCartContextVO) {
		ProductOrderItem result = transform(operationHeader, cartItemVO, shoppingCartContextVO);

		Product product = result.getProduct();
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd = findExistingProduct(shoppingCartContextVO, EnterpriseWLNSalesServicesConstants.SING);
		if (existingProd != null) {
			product.setProductSerialNumber(existingProd.getProductSerialNumber());
		}
		
		// explicit product remove
		if (cartItemVO.getProducts()!=null && cartItemVO.getProducts().getHomePhoneProduct().getAdditionalProductItemList() != null) {
			explicitProductRemoval(cartItemVO.getProducts().getHomePhoneProduct().getAdditionalProductItemList(), shoppingCartContextVO, product);
		}
		
		explicitAddonRemoval(product, existingProd, cartItemVO.getProducts().getHomePhoneProduct().getAddOns());
		
		// implicit product or orphaned discount removal
		implicitProductOrOrphanedDiscountRemoval(shoppingCartContextVO, product, EnterpriseWLNSalesServicesConstants.SING);

		// explicit discount remove is DONE as part of addProductOfferings
		// if the action type is REMOVE

		// implicit discount removal
		implicitDiscountRemoval(shoppingCartContextVO, product);

		// primarySubscriptionNumber
		updatePrimarySubscriptionNumberComp(existingProd, product, cartItemVO.getProducts().getHomePhoneProduct());
	
		// smartRingSubscriptionNumber
		updateSmartringSubscriptionNumberComp(existingProd, product, cartItemVO.getProducts().getHomePhoneProduct());
		
		return result;
	}

	private static void updatePrimarySubscriptionNumberComp(com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd, Product product, HomePhoneProductTypeVO homePhoneProduct) {
		Boolean toBeListed = null;
		if(homePhoneProduct != null 
				&& homePhoneProduct.getPhoneNumber() != null
				&& homePhoneProduct.getPhoneNumber().getDirectoryListing() != null
				&& homePhoneProduct.getPhoneNumber().getDirectoryListing().getListedInd() != null) {
			toBeListed = homePhoneProduct.getPhoneNumber().getDirectoryListing().getListedInd();
		}
		
		//find new primarySubscriptionNumberComp
		ProductComponent newPrimarySubscriptionNumberComp = findProductComponentByName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(PRIMARY_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME), product.getProductComponents());
		ProductComponent newDirectoryListingComp = null;
		if (newPrimarySubscriptionNumberComp != null) {
			newDirectoryListingComp = findProductComponentByName(COMP_DIRECTORY_LISTING_COMPONENT, newPrimarySubscriptionNumberComp.getProductComponents());
		}
		
		//find existing primarySubscriptionNumberComp
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingPrimarySubscriptionNumberComp = findOpProductComponentByName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(PRIMARY_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME), existingProd.getProductComponents());
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingDirectoryListingComp = null;
		if (existingPrimarySubscriptionNumberComp != null) {
			existingDirectoryListingComp = findOpProductComponentByName(COMP_DIRECTORY_LISTING_COMPONENT, existingPrimarySubscriptionNumberComp.getProductComponents()); 
		}
		
		if(existingPrimarySubscriptionNumberComp != null && newPrimarySubscriptionNumberComp != null) {
			newPrimarySubscriptionNumberComp.setActionType(EnterpriseWLNSalesServicesConstants.UP);
			newPrimarySubscriptionNumberComp.setProductSerialNumber(existingPrimarySubscriptionNumberComp.getProductSerialNumber());
		}
		
		if(toBeListed != null) {
			if (toBeListed) {
				if (existingDirectoryListingComp != null && newDirectoryListingComp != null) {
					newDirectoryListingComp.setActionType(EnterpriseWLNSalesServicesConstants.UP);
					newDirectoryListingComp.setProductSerialNumber(existingDirectoryListingComp.getProductSerialNumber());
				}
				else if (existingDirectoryListingComp == null && newDirectoryListingComp != null) {
					newDirectoryListingComp.setActionType(EnterpriseWLNSalesServicesConstants.AD);
				}
			}
			else {
				if (existingDirectoryListingComp != null && newPrimarySubscriptionNumberComp != null) {
					ProductComponent listingProdComp = new ProductComponent();
					listingProdComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
					listingProdComp.setCatalogId(PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_LIST_COMP + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ID));
					listingProdComp.setName(COMP_DIRECTORY_LISTING_COMPONENT);
					listingProdComp.setProductSerialNumber(existingDirectoryListingComp.getProductSerialNumber());
					newPrimarySubscriptionNumberComp.addProductComponent(listingProdComp);
				}
			}
		}
	}

	
	private static void updateSmartringSubscriptionNumberComp(com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingProd, Product product, HomePhoneProductTypeVO homePhoneProduct) {
		//we expect just one smartring subscription number
		Boolean toBeListed = null;
//		if(homePhoneProduct != null 
//				&& homePhoneProduct.getSmartRingPhoneList() != null
//				&& ! homePhoneProduct.getSmartRingPhoneList().isEmpty()
//				&& homePhoneProduct.getSmartRingPhoneList().get(0) != null
//				&& homePhoneProduct.getSmartRingPhoneList().get(0).getPhone() != null
//				&& homePhoneProduct.getSmartRingPhoneList().get(0).getPhone().getDirectoryListing() != null
//				&& homePhoneProduct.getSmartRingPhoneList().get(0).getPhone().getDirectoryListing().getListedInd() != null) {
//			toBeListed = homePhoneProduct.getSmartRingPhoneList().get(0).getPhone().getDirectoryListing().getListedInd();
//		}
		//OP has only one listed indicator and it under main 
		if(homePhoneProduct != null 
				&& homePhoneProduct.getPhoneNumber() != null
				&& homePhoneProduct.getPhoneNumber().getDirectoryListing() != null
				&& homePhoneProduct.getPhoneNumber().getDirectoryListing().getListedInd() != null) {
			toBeListed = homePhoneProduct.getPhoneNumber().getDirectoryListing().getListedInd();
		}

		
		//find new smartringSubscriptionNumberComp
		ProductComponent newSmartringSubscriptionNumberComp = findProductComponentByName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(SMARTRING_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME), product.getProductComponents());
		ProductComponent newDirectoryListingComp = null;
		if (newSmartringSubscriptionNumberComp != null) {
			newDirectoryListingComp = findProductComponentByName(COMP_DIRECTORY_LISTING_COMPONENT, newSmartringSubscriptionNumberComp.getProductComponents());
		}
	
		//find existing smartringSubscriptionNumberComp
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingSmartringSubscriptionNumberComp = findOpProductComponentByName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(SMARTRING_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME), existingProd.getProductComponents());
		com.telus.csm.ewlnsc.adapter.oqs.domain.Product existingDirectoryListingComp = null;
		if (existingSmartringSubscriptionNumberComp != null) {
			existingDirectoryListingComp = findOpProductComponentByName(COMP_DIRECTORY_LISTING_COMPONENT, existingSmartringSubscriptionNumberComp.getProductComponents()); 
		}
		
		//if removing the smartring calling feature then remove the smartring subscription number
		if(isSmartRingCallingFeatureToBeRemoved(product)) {
			ProductComponent smartRingComp = newSmartringSubscriptionNumberComp;
			if(smartRingComp == null) {
				smartRingComp = new ProductComponent();
				smartRingComp.setName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(SMARTRING_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME));
				smartRingComp.setCatalogId(PRODUCT_CHARACTERISTCS_PROPS.getProperty(SMARTRING_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ID));
				product.addProductComponent(smartRingComp);
			}
			smartRingComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
			smartRingComp.setProductSerialNumber(existingSmartringSubscriptionNumberComp.getProductSerialNumber());
		} else if(existingSmartringSubscriptionNumberComp != null && newSmartringSubscriptionNumberComp != null) {
			newSmartringSubscriptionNumberComp.setActionType(EnterpriseWLNSalesServicesConstants.UP);
			newSmartringSubscriptionNumberComp.setProductSerialNumber(existingSmartringSubscriptionNumberComp.getProductSerialNumber());
		}
	
		if(toBeListed != null) {
			if (toBeListed) {
				if (existingDirectoryListingComp != null && newDirectoryListingComp != null) {
					newDirectoryListingComp.setActionType(EnterpriseWLNSalesServicesConstants.UP);
					newDirectoryListingComp.setProductSerialNumber(existingDirectoryListingComp.getProductSerialNumber());
				}
				else if (existingDirectoryListingComp == null && newDirectoryListingComp != null) {
					newDirectoryListingComp.setActionType(EnterpriseWLNSalesServicesConstants.AD);
				}
			}
			else {
				if (existingDirectoryListingComp != null && newSmartringSubscriptionNumberComp != null) {
					final ProductComponent listingProdComp = new ProductComponent();
					listingProdComp.setActionType(EnterpriseWLNSalesServicesConstants.RM);
					listingProdComp.setCatalogId(PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_LIST_COMP + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ID));
					listingProdComp.setName(COMP_DIRECTORY_LISTING_COMPONENT);
					listingProdComp.setProductSerialNumber(existingDirectoryListingComp.getProductSerialNumber());
					newSmartringSubscriptionNumberComp.addProductComponent(listingProdComp);
				}
			}
		}
	}

	private static boolean isSmartRingCallingFeatureToBeRemoved(Product newProduct) {
		boolean smartRingCallingFeatureToBeRemoved = false;

		ProductComponent callingFeaturesComponent = findProductComponentByCatId(Constants.CALLINGFEATURECID, newProduct.getProductComponents());

		if ( (callingFeaturesComponent != null) &&
			 (callingFeaturesComponent.getProductComponents() != null) &&
			 (!callingFeaturesComponent.getProductComponents().isEmpty()) ) {
			ProductComponent smartRingComponent = findProductComponentByCatId(Constants.SMARTRINGCID, callingFeaturesComponent.getProductComponents());

			if ( (smartRingComponent != null) &&
				 (smartRingComponent.getActionType() != null) &&
				 (smartRingComponent.getActionType().equals(EnterpriseWLNSalesServicesConstants.RM)) ) {
				smartRingCallingFeatureToBeRemoved = true;
			}
		}

		return smartRingCallingFeatureToBeRemoved;
	}
}
