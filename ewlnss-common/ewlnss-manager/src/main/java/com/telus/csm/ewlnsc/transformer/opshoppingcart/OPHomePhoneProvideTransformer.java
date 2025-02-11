package com.telus.csm.ewlnsc.transformer.opshoppingcart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.ccm.domain.GetFullCustomerInfoAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.Product;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductComponent;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOffering;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ProductOrderItem;
import com.telus.csm.ewlnsc.delegate.ProductItemChargeDelegate;
import com.telus.csm.ewlnsc.domain.AppointmentDetailTypeVO;
import com.telus.csm.ewlnsc.domain.AppointmentTypeVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.DirectoryListingTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.FFHProductPlanAddOnTypeVO;
import com.telus.csm.ewlnsc.domain.FFHSweetenerTypeVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.GetAvailableProductItemDelegateResponse;
import com.telus.csm.ewlnsc.domain.HomePhoneNumberDetailTypeVO;
import com.telus.csm.ewlnsc.domain.HomePhoneProductTypeVO;
import com.telus.csm.ewlnsc.domain.ProductComponentVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.SmartRingTypeVO;
import com.telus.csm.ewlnsc.domain.TelephoneNumberTypeVO;
import com.telus.csm.ewlnsc.domain.product.MarketOfferClassificationVO;
import com.telus.csm.ewlnsc.domain.product.ProductItemVO;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.SweetenerOfferSummary;
import com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes.WirelineOfferProductSummary;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OperationHeader;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProduct;

public class OPHomePhoneProvideTransformer extends OPCommonProvideTransformer implements EnterpriseWLNSalesServicesConstants, Constants {
	private static final LoggerUtil logger = LoggerUtil.getLogger(OPCommonProvideTransformer.class);
	private static HashMap<String, List<String>> OP_FEATURE_DEPENDENCIES = new HashMap<String, List<String>>();
	static {
		OP_FEATURE_DEPENDENCIES.put("10820", Arrays.asList("56214"));//Voice Mail: Voice Mail Last Number Match 
		OP_FEATURE_DEPENDENCIES.put("10021", Arrays.asList("56214"));//Voice Mail Simple: Voice Mail Last Number Match 
	}

	public static ProductOrderItem transform(final OperationHeader header, final CartItemVO ciVO, final ShoppingCartContextVO scCtxVO) {
		final HomePhoneProductTypeVO ciSingProduct = ciVO.getProducts().getHomePhoneProduct();
		final WirelineOfferProduct tomOfferProduct = findWirelineOfferProduct(scCtxVO, ciVO, SING);
		if (tomOfferProduct == null) {
			throw new RuntimeException("Cannot find a SING WirelineOfferProduct for CartItemRelationId: " + ciVO.getCartItemRelationId());
		}

		final Date serviceRequiredDate = findServiceRequiredDate(ciVO, SING, scCtxVO);
		final String orderTypeCd = ciSingProduct.getProductOrderType().getOrderTypeCd();
		final ProductOrderItem result = populateCommonProductOrderItem(header, ciVO, tomOfferProduct, SING, orderTypeCd, scCtxVO, serviceRequiredDate);
		final Product product = result.getProduct();

		// product components
		if (ciSingProduct.getProductComponents() != null) {
			for (final ProductComponentVO ciProdCompVO : ciSingProduct.getProductComponents()) {
				ProductComponent prodComp = populateProdComp(tomOfferProduct, ciProdCompVO, SING, ciSingProduct.getSelectedContractTermCd(), scCtxVO);
				if (prodComp != null) {
					product.addProductComponent(prodComp);
				}
			}
		}

		// productInformation
		final ProductComponent productInfoComp = populateProductInformationCompSING(ciSingProduct, scCtxVO);
		product.addProductComponent(productInfoComp);

		populateCallingFeatures(scCtxVO, ciSingProduct, tomOfferProduct, product);
		
		populatePrimarySubscription(scCtxVO, ciSingProduct, product);

		populateSmartRing(scCtxVO, ciSingProduct, product);

		populateAddons(ciSingProduct, tomOfferProduct, product);

		populateDiscounts(ciVO, scCtxVO, ciSingProduct, tomOfferProduct, product);

		return result;
	}

	private static void populateDiscounts(final CartItemVO ciVO, final ShoppingCartContextVO scCtxVO,
			final HomePhoneProductTypeVO ciSingProduct, final WirelineOfferProduct tomOfferProduct, final Product product) {
		if (ciSingProduct.getSweeteners() != null) {
			final List<SweetenerOfferSummary> sweetenerOffers = scCtxVO.getSweetnersByProduct(SING, ciVO.getCartItemRelationId());
			for (final FFHSweetenerTypeVO ciSweetenerElem : ciSingProduct.getSweeteners()) {
				SweetenerOfferSummary offerSummary = findSweetenerOfferSummary(ciSweetenerElem.findPromotionId(), sweetenerOffers);
				if (offerSummary != null && offerSummary.getOfferProductSummary() != null) {
					for (final WirelineOfferProductSummary offerProdSummaryElem : offerSummary.getOfferProductSummary().getWirelineOfferProductSummaryList()) {
						addProductOfferings(offerProdSummaryElem.getDiscountList(), ciSweetenerElem.getDiscounts(), product, scCtxVO);
					}
				}
			}
		}

		addProductOfferings(tomOfferProduct.getDiscountList(), ciSingProduct.getDiscounts(), product, scCtxVO);
	}

	private static void populateAddons(final HomePhoneProductTypeVO ciSingProduct, final WirelineOfferProduct tomOfferProduct, final Product product) {
		if (ciSingProduct.getAddOns() != null) {
			for (final FFHProductPlanAddOnTypeVO addonElem : ciSingProduct.getAddOns()) {
				ProductComponent addonComp = createAddonProductComponent(tomOfferProduct, addonElem);
				if (addonComp != null) {
					product.addProductComponent(addonComp);
				}
			}
		}
	}

	private static void populateSmartRing(final ShoppingCartContextVO scCtxVO, final HomePhoneProductTypeVO ciSingProduct, final Product product) {
		if (ciSingProduct.getSmartRingPhoneList() != null) {
			for (final SmartRingTypeVO ciSmartRingElem : ciSingProduct.getSmartRingPhoneList()) {
				HomePhoneNumberDetailTypeVO ciPhoneDetailVO = ciSmartRingElem.getPhone();
				if (ciPhoneDetailVO != null) {
					TelephoneNumberTypeVO ciPhoneVO = ciPhoneDetailVO.getTelephoneNumber();
					if (ciPhoneVO != null && ciPhoneVO.getPhoneNumber() != null && ciPhoneVO.getPhoneNumber() > 0) {
						ProductComponent smartRingSubscriptionNumberComp = populateSmartRingSubscriptionNumberComp(ciPhoneDetailVO, scCtxVO, findDirectoryListedInd(ciSingProduct));
						product.addProductComponent(smartRingSubscriptionNumberComp);
					}
				}
			}
		}
	}

	private static void populatePrimarySubscription(final ShoppingCartContextVO scCtxVO, final HomePhoneProductTypeVO ciSingProduct, final Product product) {
		final HomePhoneNumberDetailTypeVO ciPhoneDetailVO = ciSingProduct.getPhoneNumber();
		if (ciPhoneDetailVO != null) {
			final TelephoneNumberTypeVO ciPhoneVO = ciPhoneDetailVO.getTelephoneNumber();
			if (ciPhoneVO != null && ciPhoneVO.getPhoneNumber() != null && ciPhoneVO.getPhoneNumber() > 0) {
				final ProductComponent primarySubscriptionNumberComp = populatePrimarySubscriptionNumberComp(ciPhoneDetailVO, scCtxVO, findDirectoryListedInd(ciSingProduct));
				product.addProductComponent(primarySubscriptionNumberComp);
			}
		}
	}

	private static void populateCallingFeatures(final ShoppingCartContextVO scCtxVO, final HomePhoneProductTypeVO ciSingProduct, final WirelineOfferProduct tomOfferProduct, final Product product) {
		if (ciSingProduct.getFeatures() != null) {
			final String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(CALLING_FEATURES + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			final String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(CALLING_FEATURES + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);

			final ProductComponent callingFeaturesComp = new ProductComponent();
			callingFeaturesComp.setName(name);
			callingFeaturesComp.setCatalogId(catalogAttributeId);
			
			populateFeatureComp(scCtxVO, ciSingProduct, tomOfferProduct, callingFeaturesComp);

			if (callingFeaturesComp.getProductComponents() != null && !callingFeaturesComp.getProductComponents().isEmpty()) {
				product.addProductComponent(callingFeaturesComp);
			}
		}
	}

	private static void populateFeatureComp(final ShoppingCartContextVO scCtxVO, final HomePhoneProductTypeVO ciSingProduct, final WirelineOfferProduct tomOfferProduct, final ProductComponent callingFeaturesComp) {
		final ArrayList<ProductItemVO> availablePackEligPiList = new ArrayList<ProductItemVO>();
		ArrayList<ProductItemVO> notInPackPiList = new ArrayList<ProductItemVO>();
		final Map<String, String> actionTypeMap = new HashMap<String, String>();
		
		for (final FFHFeatureTypeVO ciFeatureElem : ciSingProduct.getFeatures()) {
			if(ciFeatureElem.getProductCatalogueIdentifier() != null) {
				String ciFeaturePci = ciFeatureElem.getProductCatalogueIdentifier().getProductCatalogueId();
				ProductCatalogueItem tomFeaturePCItem = findFeatureProductCatalogueItem(ciFeatureElem, tomOfferProduct);
				if (tomFeaturePCItem != null) {
					String key = ciFeaturePci;
					actionTypeMap.put(key, ciFeatureElem.getProductCatalogueIdentifier().getAction());
					boolean foundInAvailableProductItems = false;
					GetAvailableProductItemDelegateResponse piDelegateResp = scCtxVO.getAvailableProductItemDelegateResponse(); 
					if (piDelegateResp != null && piDelegateResp.getProductItems() != null) {
						for (final ProductItemVO piElem : piDelegateResp.getProductItems()) {
							if (piElem.getProductItemIdentifier() != null && ciFeaturePci.equalsIgnoreCase(piElem.getProductItemIdentifier().getProductCatalogueId())) {
								foundInAvailableProductItems = true;
								ProductItemVO clonedPrdItmVO = cloneProductItemVO(piElem);
								if (!EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(ciFeatureElem.getProductCatalogueIdentifier().getAction()) && Boolean.TRUE.equals(tomFeaturePCItem.isPackEligibleItemInd())) {
									availablePackEligPiList.add(clonedPrdItmVO);
								} else {
									notInPackPiList.add(clonedPrdItmVO);
								}
							}
						}
					}
					if(!foundInAvailableProductItems) {
						logger.info("populateFeatureComp", "Could not find the calling feature with catalogId [" + ciFeaturePci + "] in AvailableProductItems");
				}
			}
		}
		}
		final GetAvailableProductItemDelegateResponse piDelegateResp = scCtxVO.getAvailableProductItemDelegateResponse();
		if (piDelegateResp != null && piDelegateResp.getProductItems() != null) { // add the existing features with carry over true and not removed and not yet added to the actionTypeMap
			for (final ProductItemVO piElem : piDelegateResp.getProductItems()) {
				if(piElem.getProductItemIdentifier() != null) {
					String key = piElem.getProductItemIdentifier().getProductCatalogueId();
					MarketOfferClassificationVO marketVO = piElem.getMarketOfferClassification();
					if (piElem.isExistingInd() 
							&& piElem.isCarryOverInd() 
							&& Boolean.TRUE.equals(piElem.getPackEligibleItemInd())
							&& actionTypeMap.get(key) == null 
							&& marketVO != null 
							&& Boolean.TRUE.equals(marketVO.isCallingFeatureInd())) {
						actionTypeMap.put(key, PRODUCT_ITEM_ACTION_NO_CHANGE);
						ProductItemVO clonedPrdItmVO = cloneProductItemVO(piElem);
						availablePackEligPiList.add(clonedPrdItmVO);
					}
				}
			}
		}
		//remove dependent feature. E.g. Voice Mail Last Number Match requires Voice Mail or Voice Mail Simple. So removing one of later requires the Voice Mail Last Number Match to be removed as well
		doDependableFeatuesForRemove(scCtxVO, ciSingProduct.getFeatures(), availablePackEligPiList, notInPackPiList, actionTypeMap);
		
		final ProductItemChargeDelegate piChargeDelegate = new ProductItemChargeDelegate();
		piChargeDelegate.applyConstraintPricing(SING, availablePackEligPiList, scCtxVO);
		for(String prodCatId: actionTypeMap.keySet()) {
			Boolean inPack = null;
			//inPack eligible but can be reset
			for(ProductItemVO prdItm: availablePackEligPiList) {
				if(prdItm.getProductItemIdentifier() != null && prodCatId.equalsIgnoreCase(prdItm.getProductItemIdentifier().getProductCatalogueId())) {
					//we expect the PackEligibleItemInd to be set/rest on the cloned instance in the "applyConstraintPricing" above
					if (Boolean.TRUE.equals(prdItm.getPackEligibleItemInd())) {
						inPack = Boolean.TRUE;
					} else {
						inPack = Boolean.FALSE;
					}
					ProductComponent featureProdComp = createCallingFeatureProductComponent(scCtxVO, prdItm, inPack, actionTypeMap.get(prodCatId));
					callingFeaturesComp.addProductComponent(featureProdComp);
					break;
				}
			}
			//not inPack eligible
			for(ProductItemVO prdItm: notInPackPiList) {
				if(prdItm.getProductItemIdentifier() != null && prodCatId.equalsIgnoreCase(prdItm.getProductItemIdentifier().getProductCatalogueId())) {
					ProductComponent featureProdComp = createCallingFeatureProductComponent(scCtxVO, prdItm, inPack, actionTypeMap.get(prodCatId));
					callingFeaturesComp.addProductComponent(featureProdComp);
					break;
				}
			}
		}
	}

	private static void doDependableFeatuesForRemove(ShoppingCartContextVO scCtxVO, List<FFHFeatureTypeVO> features,
			ArrayList<ProductItemVO> availablePackEligPiList, ArrayList<ProductItemVO> notInPackPiList, Map<String, String> actionTypeMap) {
		//remove dependent feature. E.g. Voice Mail Last Number Match requires Voice Mail or Voice Mail Simple. So removing one of later requires the Voice Mail Last Number Match to be removed as well
		if(features == null) {
			return;
		}
		for(FFHFeatureTypeVO feat: features) {
			if(EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(feat.getProductCatalogueIdentifier().getAction())) {
				//find extCatId
				String featPrdCatId = feat.getProductCatalogueIdentifier().getProductCatalogueId();
				String featExtCatId = findExternalCatalogueId(featPrdCatId);
				if(StringUtils.isEmpty(featExtCatId)) {
					continue;
				}
				//if extCatId in dependency map
				List<String> dependentFeatures = OP_FEATURE_DEPENDENCIES.get(featExtCatId);
				if(dependentFeatures == null || dependentFeatures.isEmpty()) {
					continue;
				}
				//remove from availablePackEligPiList if there
				if(availablePackEligPiList != null) {
					for(Iterator<ProductItemVO> itr = availablePackEligPiList.iterator(); itr.hasNext();) {
						ProductItemVO packEligPrd = itr.next();
						if(dependentFeatures.contains(packEligPrd.getProductItemIdentifier().getExternalId())) {
							itr.remove();
							String name = findEnglishTextValue(packEligPrd.getProductCatalogueName());
							logger.info("doDependableFeatuesForRemove", "availablePackEligPiList. Removing from list a dependable feature with ExtCataId [" + packEligPrd.getProductItemIdentifier().getExternalId() + "] (" + name + ")" 
									+ " because we delete the feature [" + featPrdCatId + "] with ExtCataId [" + featExtCatId + "]");
						}
					}
				}
				
				//remove from notInPackPiList if there
				if(notInPackPiList != null) {
					for(Iterator<ProductItemVO> itr = notInPackPiList.iterator(); itr.hasNext();) {
						ProductItemVO packEligPrd = itr.next();
						if(dependentFeatures.contains(packEligPrd.getProductItemIdentifier().getExternalId())) {
							itr.remove();
							String name = findEnglishTextValue(packEligPrd.getProductCatalogueName());
							logger.info("doDependableFeatuesForRemove", "notInPackPiList. Removing from list a dependable feature with ExtCataId [" + packEligPrd.getProductItemIdentifier().getExternalId() + "] (" + name + ")" 
									+ " because we delete the feature [" + featPrdCatId + "] with ExtCataId [" + featExtCatId + "]");
						}
					}
				}
				
				//check if dependent is an existing feature
				GetAvailableProductItemDelegateResponse availPrdItmResp = scCtxVO.getAvailableProductItemDelegateResponse();
				if (availPrdItmResp != null && availPrdItmResp.getProductItems() != null) { // add the existing features with carry over true and not removed and not yet added to the actionTypeMap
					for(String dependFeatExtCatId: dependentFeatures) {
						for (ProductItemVO availPrdItm : availPrdItmResp.getProductItems()) {
							if(availPrdItm.isExistingInd()
									&& availPrdItm.getProductItemIdentifier() != null 
									&& dependFeatExtCatId.equalsIgnoreCase(availPrdItm.getProductItemIdentifier().getExternalId())) {
								//add to notInPackPiList with REMOVE
								ProductItemVO clonedPrdItmVO = cloneProductItemVO(availPrdItm);
								notInPackPiList.add(clonedPrdItmVO);
								actionTypeMap.put(availPrdItm.getProductItemIdentifier().getProductCatalogueId(), EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE);
								//log
								String name = findEnglishTextValue(availPrdItm.getProductCatalogueName());
								logger.info("doDependableFeatuesForRemove", "Adding a dependable feature to remove. " 
										+ "[" + name + "], catalogId [" + availPrdItm.getProductItemIdentifier().getProductCatalogueId() + "] with ExtCatalogId [" + dependFeatExtCatId + "] because we delete the feature [" + featPrdCatId + "] with ExtCatalogId [" + featExtCatId + "]");
							}
						}
					}
				}
			}
		}
	}

	private static ProductComponent createCallingFeatureProductComponent(final ShoppingCartContextVO scCtxVO, final ProductItemVO tomFeaturePCItem, Boolean inPack, final String actionType) {
		final ProductComponent result = new ProductComponent();
		
		result.setActionType(findProdCompActionType(actionType));
		
		final String extCid = tomFeaturePCItem.getProductItemIdentifier().getExternalId();
		result.setCatalogId(extCid);
		// set the serial number if the action type is removed or updated
		if (!AD.equalsIgnoreCase(result.getActionType())) {
			final GetAssignedAndPendingProductResponseVO assignedProd = scCtxVO.getOpAssignedAndPendingProductResponseVO();
			if (assignedProd != null && assignedProd.getGetProductsByCustomerIdAdapterResponse() != null) {
				final com.telus.csm.ewlnsc.adapter.oqs.domain.Product prod = findOpProductComponentByExtCatId(extCid, assignedProd.getGetProductsByCustomerIdAdapterResponse().getProducts());
				if (prod != null) {
					result.setProductSerialNumber(prod.getProductSerialNumber());
					result.setDescription(prod.getDescription());
				}
			}
		}
		if (CollectionUtils.isNotEmpty(tomFeaturePCItem.getProductCatalogueName())) {
			result.setName(tomFeaturePCItem.getProductCatalogueName().get(0).getValueTxt());
		}
		if (!RM.equalsIgnoreCase(result.getActionType()) && inPack != null) {
			//inPack
			final String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(IN_PACK + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			final String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(IN_PACK + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			final String value = inPack? "Yes": "No";
			result.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
		}
		
		return result;
	}

	protected static ProductComponent populatePrimarySubscriptionNumberComp(final HomePhoneNumberDetailTypeVO ciPhoneDetailVO, final ShoppingCartContextVO scCtxVO, Boolean directoryListedInd) {
		final ProductComponent result = new ProductComponent();

		result.setName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(PRIMARY_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME));
		result.setCatalogId(PRODUCT_CHARACTERISTCS_PROPS.getProperty(PRIMARY_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ID));
		
		populatePhoneNumberComponent(ciPhoneDetailVO, scCtxVO, result, directoryListedInd);
		
		return result;
	}

	protected static ProductComponent populateSmartRingSubscriptionNumberComp(final HomePhoneNumberDetailTypeVO ciPhoneDetailVO, final ShoppingCartContextVO scCtxVO, Boolean directoryListedInd) {
		final ProductComponent result = new ProductComponent();
		
		result.setName(PRODUCT_CHARACTERISTCS_PROPS.getProperty(SMARTRING_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_NAME));
		result.setCatalogId(PRODUCT_CHARACTERISTCS_PROPS.getProperty(SMARTRING_SUB_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ID));
		
		populatePhoneNumberComponent(ciPhoneDetailVO, scCtxVO, result, directoryListedInd);
		
		return result;
	}
	
	private static void populatePhoneNumberComponent(HomePhoneNumberDetailTypeVO ciPhoneDetailVO, final ShoppingCartContextVO scCtxVO, final ProductComponent subscriptionNumberComp, Boolean directoryListedInd) {
		final TelephoneNumberTypeVO phoneVO = ciPhoneDetailVO.getTelephoneNumber();
		final DirectoryListingTypeVO dlVO = ciPhoneDetailVO.getDirectoryListing();
		
		if (phoneVO != null && phoneVO.getPhoneNumber() > 0 ) {
			if (!isPineAppleOrder(scCtxVO.getShoppingCartVO())) {
				//subscriptionNumber
				final String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(PHONE_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
				final String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(PHONE_NUMBER + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
				final String value = String.valueOf(phoneVO.getPhoneNumber());
				subscriptionNumberComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
			}
		}
		if (dlVO != null && Boolean.TRUE.equals(directoryListedInd)) {
			final ProductComponent listingProdComp = new ProductComponent();
			listingProdComp.setActionType(AD);
			listingProdComp.setCatalogId(PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_LIST_COMP + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ID));
			listingProdComp.setName(COMP_DIRECTORY_LISTING_COMPONENT);
			subscriptionNumberComp.addProductComponent(listingProdComp);
			//DirectoryType
			String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_TYPE + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_TYPE + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			String value = "PR";
			listingProdComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
			//directoryAccess
			name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_ACCESS + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DIRECTORY_ACCESS + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			value = "411D";
			listingProdComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
			//omitDirectoryAddress
			name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(OMIT_DIRECTORY_ADDRESS + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(OMIT_DIRECTORY_ADDRESS + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			value = Boolean.TRUE.equals(dlVO.getAddressListedInd()) ? "No" : "Yes";
			listingProdComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));

			populateDirListing(scCtxVO, dlVO, listingProdComp);
		}
	}

	private static Boolean findDirectoryListedInd(HomePhoneProductTypeVO ciSingProduct) {
		if(ciSingProduct != null
				&& ciSingProduct.getPhoneNumber() != null
				&& ciSingProduct.getPhoneNumber().getDirectoryListing() != null) {
			return ciSingProduct.getPhoneNumber().getDirectoryListing().getListedInd();
		}
		return null;
	}

	private static void populateDirListing(final ShoppingCartContextVO scCtxVO, final DirectoryListingTypeVO dlVO, final ProductComponent listingProdComp) {
		String name;
		String catalogAttributeId;
		String value;
		final ServiceAddressResponseVO svcAddrVO = scCtxVO.getServiceAddressResponseVO();
		final GetFullCustomerInfoAdapterResponse fullCustVO = scCtxVO.getFullCustomerInfoAdapterResponse();

		if (Boolean.TRUE.equals(dlVO.getNameListedInd()) && fullCustVO != null && fullCustVO.getFullCustomer() != null && CollectionUtils.isNotEmpty(fullCustVO.getFullCustomer().getNameList())) {
			//listingFirstName
			name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(LISTING_FIRST_NAME + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(LISTING_FIRST_NAME + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			value = fullCustVO.getFullCustomer().getNameList().get(0).getFirstName();
			listingProdComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, Objects.toString(value, Constants.EMPTY)));
			//listingLastName
			name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(LISTING_LAST_NAME + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(LISTING_LAST_NAME + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			value = fullCustVO.getFullCustomer().getNameList().get(0).getLastName();
			listingProdComp.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, Objects.toString(value, Constants.EMPTY)));
		}
		if (Boolean.TRUE.equals(dlVO.getAddressListedInd()) && svcAddrVO != null && svcAddrVO.getServiceAddress() != null) {
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_POSTOFFICEBOX, Objects.toString(svcAddrVO.getServiceAddress().getPostOfficeBoxNumber(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_APPARTMENT,  Objects.toString(svcAddrVO.getServiceAddress().getUnitNumber(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_STREET_NUMBER,  Objects.toString(svcAddrVO.getServiceAddress().getStreetNumber(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_STREET_NAME,  Objects.toString(svcAddrVO.getServiceAddress().getStreetName(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_VECTOR,  Objects.toString(svcAddrVO.getServiceAddress().getStreetDirectionCode(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_CITY,  Objects.toString(svcAddrVO.getServiceAddress().getMunicipalityName(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_STATE,  Objects.toString(svcAddrVO.getServiceAddress().getProvinceStateCode(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_COUNTRY,  Objects.toString(svcAddrVO.getServiceAddress().getCountryCode(), Constants.EMPTY)));
			listingProdComp.addProductCharacteristic(createProductCharacteristic(OP_POSTALCODE,  Objects.toString(svcAddrVO.getServiceAddress().getPostalZipCode(), Constants.EMPTY)));
		}
	}

	private static ProductComponent populateProductInformationCompSING(final HomePhoneProductTypeVO ciSingProduct, final ShoppingCartContextVO scCtxVO) {
		final ProductComponent result = new ProductComponent();
		result.setName(COMP_PRODUCT_INFORMATION);
		final HomePhoneNumberDetailTypeVO ciPhoneDetailVO = ciSingProduct.getPhoneNumber();
		if (ciPhoneDetailVO != null && ciPhoneDetailVO.getDirectoryListing() != null) {
			//listingType
			String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(LISTING_TYPE + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(LISTING_TYPE + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			String value = null;
			if(ciPhoneDetailVO.getDirectoryListing().getListedInd() != null) {
				value = Boolean.TRUE.equals(ciPhoneDetailVO.getDirectoryListing().getListedInd())? "LIS": "NONPUB";
			}
			result.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
		}
		//nameDisplay
		if (ciPhoneDetailVO != null && ciPhoneDetailVO.getDirectoryListing() != null) {
			String name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(NAME_DISPLAY + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			String catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(NAME_DISPLAY + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			String value = null;
			if(Boolean.TRUE.equals(ciPhoneDetailVO.getDirectoryListing().getListedInd())) {
				final GetFullCustomerInfoAdapterResponse fullCust = scCtxVO.getFullCustomerInfoAdapterResponse();
				if (fullCust != null && fullCust.getFullCustomer() != null && CollectionUtils.isNotEmpty(fullCust.getFullCustomer().getNameList())) {
					final String firstName = fullCust.getFullCustomer().getNameList().get(0).getFirstName();
					final String lastName = fullCust.getFullCustomer().getNameList().get(0).getLastName();
					final StringBuilder sb = new StringBuilder();
					//Initial & Last name
					if (!StringUtils.isEmpty(firstName)) {
						sb.append(firstName.charAt(0));
					}
					if (sb.length() > 0) {
						sb.append(" ");
					}
					if (!StringUtils.isEmpty(lastName)) {
						sb.append(lastName);
					}
					value = sb.toString();
				} else {
					value = "";
				}
			} else {
				value = "Private";
			}
			result.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
		}
		
		populateTollsAndInstallCredit(ciSingProduct, scCtxVO, result);

		return result;
	}

	private static void populateTollsAndInstallCredit(final HomePhoneProductTypeVO ciSingProduct, final ShoppingCartContextVO scCtxVO, final ProductComponent result) {
		String name;
		String catalogAttributeId;
		String value;
		if (EnterpriseWLNSalesServicesConstants.ACTIVATION.equalsIgnoreCase(ciSingProduct.getProductOrderType().getOrderTypeCd())
				&& CollectionUtils.isEmpty(ciSingProduct.getAddOns())) {
			//declineTollPlans
			name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DECLINE_TOLL_PLANS + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DECLINE_TOLL_PLANS + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			value = "Yes";
			result.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
			
			//declineTollPlanReason
			name = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DECLINE_TOLL_PLAN_REASON + UNDERSCORE + SING + PRODUCT_COMPONENT_ATTRIBUTE_NAME);
			catalogAttributeId = PRODUCT_CHARACTERISTCS_PROPS.getProperty(DECLINE_TOLL_PLAN_REASON + UNDERSCORE + SING + PRODUCT_COMPONENT_CATALOG_ATTRIBUTE_ID);
			value = "OT";
			result.addProductCharacteristic(createProductCharacteristic(catalogAttributeId, name, value));
			
		}

		// adding installation credit for sweetener
		final AppointmentDetailTypeVO appointVO = ciSingProduct.getAppointmentDetail();
		if (appointVO != null) {
			final AppointmentTypeVO bookedDateVO = appointVO.getBookedAppointmentDate();
			if (bookedDateVO != null && scCtxVO.isEligibleForInstallCredit(bookedDateVO.getAppointmentDate()) && scCtxVO.getInstallionCreditSweetener() != null) {
				final List<ProductOffering> installCredits = buildInstallationCreditProductOfferings(scCtxVO.getInstallionCreditSweetener());
				if (CollectionUtils.isNotEmpty(installCredits)) {
					for (final ProductOffering instCrdElem : installCredits) {
						result.addProductOffering(instCrdElem);
					}
				}
			}
		}
	}

}
