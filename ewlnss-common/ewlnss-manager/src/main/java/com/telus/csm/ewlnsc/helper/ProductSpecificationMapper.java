package com.telus.csm.ewlnsc.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.AddressRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.FeatureInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.delegate.ReferencePDSDataSvcBusDelegator;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.FFHEquipmentTypeVO;
import com.telus.csm.ewlnsc.domain.FFHFeatureTypeVO;
import com.telus.csm.ewlnsc.domain.ProductOrderTypeVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.*;

import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.SalesServiceConstants;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductSpecificationList;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.base_types_2_0.ValueType;
import com.telus.tmi.xmlschema.xsd.product.productinstance.customer_product_instance_sub_domain_v2.ProductParameter;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

import weblogic.wsee.util.StringUtil;

/**
 * @author Jose.Mena
 *
 */
public class ProductSpecificationMapper {

	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductSpecificationMapper.class);
	private static final String IS_NEW_CUSTOMER = "isNewCustomer";
	private static final String IS_NEW_HSIC = "isNewHSIC";
	private static final String IS_NEW_TTV = "isNewTTV";
	private static final String EQUIPMENT = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_EQUIPMENT;
	private static final String ALL = EnterpriseWLNSalesServicesConstants.SHOPPING_CART_ITEM_CTX_TYPE_ALL;
	private static final String INSTALLER = EnterpriseWLNSalesServicesConstants.DELIVERY_METHOD_INSTALLER;
	
	private static Map<String, String> productCatalogMap = new HashMap<String, String>();
	
	private ProductSpecificationMapper() {
		
	}
	
	static {
		productCatalogMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_HS,
				ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeHSIC}"));
		productCatalogMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_TTV,
				ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeTTV}"));
		productCatalogMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_SL,
				ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeSING}"));
		productCatalogMap.put(Constants.CUSTOMER_ODS_PRODUCT_TYPE_STV,
				ApplicationProperties.getConfigString("${common/wlnProductSpecification/productTypeSTV}"));
	}
	
	public static ProductSpecificationList mapToProductSpecificationListForAvailableProducts(Boolean changeOfTechnologyYes, String productRequestId,
			List<SubscribedProductInfoRestVO> existingProducts,
			ServiceAddressResponseVO serviceAddress, List<Product> productsToCheck,ShoppingCartVO shoppingCartVO) {
		
		if (productsToCheck == null || productsToCheck.isEmpty()) {
			return null;
		}

		ProductSpecificationList productSpecificationList = new ProductSpecificationList();
		List<ProductSpecification> productSpecifications = new ArrayList<ProductSpecification>();

		boolean isNewHSIC = isNewProduct(existingProducts, HSIC);
		boolean isNewTTV = isNewProduct(existingProducts, TTV);
		boolean isNewCustomer = existingProducts.isEmpty();
		
		Map<String, Boolean> isNewMap = new HashMap<String, Boolean>();
		isNewMap.put(IS_NEW_HSIC, isNewHSIC);
		isNewMap.put(IS_NEW_TTV, isNewTTV);
		isNewMap.put(IS_NEW_CUSTOMER, isNewCustomer);
		
		
		if (!productsToCheck.isEmpty()){
			for (Product prod : productsToCheck) {
				boolean acquisitionInd = matchProduct(existingProducts, serviceAddress, prod) == null;
				ProductSpecification productSpecification = mapProductSpecification(changeOfTechnologyYes, productRequestId, prod.getProductTypeCd(), prod.getProductTierCd(), acquisitionInd, isNewMap,existingProducts,prod.getProductCatalogueId(),shoppingCartVO);
				productSpecifications.add(productSpecification);
			}
		}
		
		productSpecificationList.setProductSpecification(productSpecifications);
		return productSpecificationList;
	}
	
	/**
	 * @param existingProducts
	 * @param hsic
	 * @return
	 */
	private static boolean isNewProduct(List<SubscribedProductInfoRestVO> existingProducts, String productTypeCodeConstant) {
		for (SubscribedProductInfoRestVO prod : existingProducts) {
			if (productTypeCodeConstant.equalsIgnoreCase(prod.getProductTypeCd())) {
				return false;
			}
		}
		return true;
	}

	public static boolean isOptik(String productTier) {
		String wssProductTier = mapProductTierToWSSProductTier(productTier);
		Map <String, String> optiksProductsMap;
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		optiksProductsMap = refPdsBusDelegate.getReferencePDSTableByName(REFPDS_WSS_PRODUCT_OPTIK);
		if (optiksProductsMap.isEmpty()){
			return false;
		}
		return optiksProductsMap.containsKey(wssProductTier);
	}

	private static Object matchProduct(List<SubscribedProductInfoRestVO> existingProducts, ServiceAddressResponseVO serviceAddress,
			Product availableProduct) {
		if (existingProducts == null || existingProducts.isEmpty() || availableProduct == null) {
			return null;
		}

		ProductQualification response = null;

		for (SubscribedProductInfoRestVO existingProduct : existingProducts) {
			// match product under the same address
			if (existingProduct.getServiceAddress() != null && serviceAddress != null 
					&& addressesAreEqual(existingProduct.getServiceAddress(), serviceAddress)
					&& existingProduct.getProductTypeCd() != null && availableProduct.getProductTypeCd() != null
					&& existingProduct.getProductTypeCd().trim().equalsIgnoreCase(availableProduct.getProductTypeCd().trim())) {
				// ProductType matched
				return existingProduct;
			}
		}
		return response;
	}
	
	/**
	 * @param serviceAddress
	 * @param serviceAddress2
	 * @return
	 */
	public static boolean addressesAreEqual(AddressRestVO serviceAddress, ServiceAddressResponseVO serviceAddress2) {
		String addressId = "";
		if (serviceAddress2.getServiceAddress() != null) {
			addressId = serviceAddress2.getServiceAddress().getAddressId();
		}
		if (serviceAddress != null) {
			return compareStrings(true, serviceAddress.getServiceAddressId(), addressId); // AddressRestVO does not contain "vector" like property, so, using addressId as suggested in meeting
		} else {
			return true;
		}
	}
	
	protected static boolean compareStrings(boolean identical, String s1, String s2){
		try{
			String s1a = "";
			String s2a = "";
			
			if(!StringUtils.isBlank(s1)){
				s1a = s1.trim();
			}
			
			if(!StringUtils.isBlank(s2)){
				s2a = s2.trim();
			}
			
			if(identical){
				if(StringUtils.isBlank(s1a) && StringUtils.isBlank(s2a)){
					return true;
				} else if(!StringUtils.isBlank(s1a) && !StringUtils.isBlank(s2a)){
					return s1a.equals(s2a);	
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}	
		} catch(Exception ex){
			return false;
		}
	}
	
	/**
	 * @param productRequestId
	 * @param productTypeCd
	 * @param hsServicePlan
	 * @param acquisitionInd
	 * @param existingProducts 
	 * @param productCatalogueId 
	 * @return
	 */
	public static ProductSpecification mapProductSpecification(Boolean changeOfTechnologyYes, String productRequestId, String productTypeCd, String productTierCd, boolean acquisitionInd,
			Map<String, Boolean> isNewMap, List<SubscribedProductInfoRestVO> existingProducts, String productCatalogueId,ShoppingCartVO shoppingCartVO) {
		ProductSpecification productSpecification = new ProductSpecification();
		
		productSpecification.setName(productTypeCd);
		productSpecification.setProductNumber(getProductNumberFromMap(productTypeCd));
		productSpecification.setBrandID(BRAND_ID_TELUS);
		List<ProductSpecificationCharacteristic> prodSpecCharacs = new ArrayList<ProductSpecificationCharacteristic>();

		// Setting productRequestId
		String valueTypeString = ValueType.STRING.value();
		ProductSpecificationCharacteristic prodSpecCharac2 = createProductSpecificationCharacteristic(PRODUCT_REQUEST_ID, valueTypeString, false, productRequestId + productTypeCd);

		prodSpecCharacs.add(prodSpecCharac2);
		
		// Setting orderActionType
		
		String orderActionType = MODIFY;
		if (acquisitionInd) {
			orderActionType = CREATE;
		}
		
//		if productQualification response=>changeOfTechnologyInd="true" 
//		=> set orderActionType to CREATE
		if (changeOfTechnologyYes) {
			orderActionType = CREATE;
		}
		
//		if (SalesServiceConstants.SALES_PRODUCT_TYPE_SINGLE_LINE.equalsIgnoreCase(productTypeCd)) { commented on December 19th
//			orderActionType = CREATE;
//		}
		
		ProductSpecificationCharacteristic prodSpecCharac3 = createProductSpecificationCharacteristic(ORDER_ACTION_TYPE, valueTypeString, false, orderActionType);
		prodSpecCharacs.add(prodSpecCharac3);
		// Setting servicePlan
		if (SalesServiceConstants.OMS_PRODUCT_HSIC.equalsIgnoreCase(productTypeCd)) {
			ProductSpecificationCharacteristic prodSpecCharac5 = createProductSpecificationCharacteristic(SERVICE_PLAN, valueTypeString, false, productTierCd);
			prodSpecCharacs.add(prodSpecCharac5);
		}
		
		// Setting serviceRequestDate
		ProductSpecificationCharacteristic prodSpecCharac11 = createProductSpecificationCharacteristic(SERVICE_REQUEST_DATE, valueTypeString, false, new SimpleDateFormat(Constants.FORMAT_SHORT_DATE_AND_TIME_SEC).format(new Date()));
		prodSpecCharacs.add(prodSpecCharac11);
		
		// add Wired/Wireless STBs characteristics
		if(!(PRODUCT_TIER_CD_TVX.equalsIgnoreCase(productTierCd))){ // NWLN-4580 
			//QC80819 doesn't need to add STB if it's upgrade or contract TV
			if(!tvEquipmentAdded(shoppingCartVO) && !isEquipmentInContextTypeList(shoppingCartVO) && !isUpgradeTV(shoppingCartVO)){ //TODO: Oct 27, 2018 - temporary fix, later we should take some indicator for final equipment to be ordered. 
				populateSTBCharacteristics(changeOfTechnologyYes, productTypeCd, isNewMap, prodSpecCharacs);
			}			
		} else {
			// 2018 Aug release for Pik TV
			// add TVType to FeasibilityService as TVX for Pik TV
			ProductSpecificationCharacteristic prodSpecCharacTVTYPE = createProductSpecificationCharacteristic(SERVICE_TVTYPE, valueTypeString, false, productTierCd);
			prodSpecCharacs.add(prodSpecCharacTVTYPE);
		}
		
		if(isTvInCart(shoppingCartVO) && isEquipmentInContextTypeList(shoppingCartVO)){
			//This is the call for Feasibility in the context of the shoppingCart
			//Logic from CSS
			int numberOfSTB = getTotalNumEquipments(productTypeCd,shoppingCartVO,existingProducts);
			int wlnStbs = getNumAddedEquipments(productTypeCd,shoppingCartVO,WIRED_EQUIPMENT);
			int wlsStbs = getNumAddedEquipments(productTypeCd, shoppingCartVO, WLS_EQUIPMENT);
			
			if(Boolean.TRUE.equals(changeOfTechnologyYes)){
				//get the existing product
				SubscribedProductInfoRestVO existingProduct = EnterpriseWLNOrderUtil.getExistingProductByType(existingProducts,productTypeCd);
				
				if(existingProduct!=null && TTV.equalsIgnoreCase(existingProduct.getProductTypeCd())){
					numberOfSTB = existingProduct.getProductInstance().get(0).getEquipmentList()!=null ? existingProduct.getProductInstance().get(0).getEquipmentList().size() : 0;
					
					//PikTV ordered
					if((PRODUCT_TIER_CD_TVX.equalsIgnoreCase(productTierCd))){
						numberOfSTB = 0;
					}
					
					//Comment on CSS logic for the below -> when is COT=true and TV is existing
					/* Ada: 
					 * work time for wireless stb is .25 hour less on copper
					 * same on gpon
					 * In this case, let's always populate all the existing STBs as Wired STBs.
					 */
					
					wlnStbs = numberOfSTB;
					wlsStbs = 0;
				}
			}
			
			if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productTypeCd) || EnterpriseWLNSalesServicesConstants.STV.equalsIgnoreCase(productTypeCd)){
				//set the totalNumberOfSTBs as CSS is doing it too.
				
				if(TTV.equalsIgnoreCase(productTypeCd) || STV.equalsIgnoreCase(productTypeCd)){
					ProductSpecificationCharacteristic prodSpecCharTotalStbs = createProductSpecificationCharacteristic(TOTAL_NUMBER_OF_STBS, valueTypeString, false, String.valueOf(numberOfSTB));
					prodSpecCharacs.add(prodSpecCharTotalStbs);
				}
				
				// STBAddedInd
				ProductSpecificationCharacteristic prodSpecCharStbAddedInd = createProductSpecificationCharacteristic(STB_ADDED_IND, valueTypeString, false, new Boolean(numberOfSTB> 0).toString());
				prodSpecCharacs.add(prodSpecCharStbAddedInd);
				
				ProductSpecificationCharacteristic prodCharWiredStbs = createProductSpecificationCharacteristic(NEW_STB_TO_BE_INSTALLED_BY_TELUS, valueTypeString, false, String.valueOf(wlnStbs));
				prodSpecCharacs.add(prodCharWiredStbs);
				
				ProductSpecificationCharacteristic prodCharWlsStbs = createProductSpecificationCharacteristic(NEW_WLS_STB_TO_BE_INSTALLED_BY_TELUS, valueTypeString, false, String.valueOf(wlsStbs));
				prodSpecCharacs.add(prodCharWlsStbs);
				
				
				
			}
		}
		
		//Change made on September 7, 2018 - Alejandro: add more ProductSpecificationCharacteristics to make the feasibility request more alike to the one produced by HS3
		
		/**
		 * additionalLineInd, only for SL
		 */
		if(SalesServiceConstants.OMS_PRODUCT_SING.equalsIgnoreCase(productTypeCd)){
			ProductSpecificationCharacteristic prodSpecCharac4;
			if(changeOfTechnologyYes){
				prodSpecCharac4 = createProductSpecificationCharacteristic("additionalLineInd", valueTypeString, false, "false");
			}else{
				prodSpecCharac4 =  createProductSpecificationCharacteristic("additionalLineInd", valueTypeString, false, "31187");
			}
			prodSpecCharacs.add(prodSpecCharac4);
		}
		
		/**
		 * localServiceProvider, only for HSIC
		 */
		if(SalesServiceConstants.OMS_PRODUCT_HSIC.equalsIgnoreCase(productTypeCd)){
			
			if(changeOfTechnologyYes){
				String localServiceProvider = getLocalServiceProvider(existingProducts);
				if(!StringUtils.isBlank(localServiceProvider)){
					ProductSpecificationCharacteristic prodSpecCharac5 = createProductSpecificationCharacteristic("localServiceProvider", valueTypeString, false, localServiceProvider);
					prodSpecCharacs.add(prodSpecCharac5);
				}
			} //TODO: revise what to send when COT is false, seems to be a property
			
			
		}
		
		/**
		 * wholeSaleAdslIndicator
		 */
		
		ProductSpecificationCharacteristic prodSpecCharac6 = createProductSpecificationCharacteristic("wholesaleAdslIndicator", valueTypeString, false, "No");
		prodSpecCharacs.add(prodSpecCharac6);
		
		/**
		 * primaryPortingIndicator
		 */
		ProductSpecificationCharacteristic prodSpecCharac7 = createProductSpecificationCharacteristic("primaryPortingIndicator", valueTypeString, false, "");
		prodSpecCharacs.add(prodSpecCharac7);
		
		/**
		 * smartRingPortingIndicator
		 */
		ProductSpecificationCharacteristic prodSpecCharac8 = createProductSpecificationCharacteristic("smartRingPortingIndicator", valueTypeString, false, "");
		prodSpecCharacs.add(prodSpecCharac8);
		
		/**
		 * callingFeatureList, only for SL
		 */
		if(SalesServiceConstants.OMS_PRODUCT_SING.equalsIgnoreCase(productTypeCd)){
			if(!callingFeaturesExistInShoppingCart(shoppingCartVO)){
				if(!StringUtils.isBlank(productCatalogueId)){
					CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
					CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
					if(catalogueItemDO!=null && !StringUtil.isEmpty(catalogueItemDO.getInternalName())){
						logger.debug("mapProductSpecification", "Calling Feature for SING: " + catalogueItemDO.getInternalName() + " productCatalogueId: " + productCatalogueId);
						ProductSpecificationCharacteristic prodSpecCharac9 = createProductSpecificationCharacteristic("callingFeatureList", valueTypeString, false, catalogueItemDO.getInternalName());
						prodSpecCharacs.add(prodSpecCharac9);
					}
				}
			}else{
				populateCallingFeaturesFromShoppingCart(existingProducts,shoppingCartVO,prodSpecCharacs);
			}
			
		}
		
		/**
		 * HDChannelInd, question on this one
		 */
		if(SalesServiceConstants.OMS_PRODUCT_TTV.equalsIgnoreCase(productTypeCd)){
			String hdChannelInd = getHdChannelInd(existingProducts);
			if(!StringUtils.isBlank(hdChannelInd)){
				ProductSpecificationCharacteristic prodSpecCharac10 = createProductSpecificationCharacteristic("HDChannelInd", valueTypeString, false, hdChannelInd);
				prodSpecCharacs.add(prodSpecCharac10);
			}
			 
		}
		
		productSpecification.getProductSpecificationCharacteristics().addAll(prodSpecCharacs);
		
		return productSpecification;
	}

	private static void populateCallingFeaturesFromShoppingCart(List<SubscribedProductInfoRestVO> existingProducts,ShoppingCartVO shoppingCartVO, List<ProductSpecificationCharacteristic> prodSpecCharacs) {
		String functionName = "populateCallingFeaturesFromShoppingCart";
		logger.enter(functionName);
		List<FeatureInfoRestVO> existingCallingFeatureList = new ArrayList<FeatureInfoRestVO>();
		// always pass existing service feature list if they are not being removed from the cart
		//add any feature from the cartItem with ADD action 
		
		if(CollectionUtils.isNotEmpty(existingProducts)){
			for(SubscribedProductInfoRestVO subscribedProduct : existingProducts){
				populateExistingCallingFeatures(subscribedProduct,shoppingCartVO,prodSpecCharacs,existingCallingFeatureList);
				
			}
		}
		
		populateCartFeaturesToAdd(shoppingCartVO,prodSpecCharacs,existingCallingFeatureList);
		logger.exit(functionName);
	}

	private static void populateCartFeaturesToAdd(ShoppingCartVO shoppingCartVO,List<ProductSpecificationCharacteristic> prodSpecCharacs, List<FeatureInfoRestVO> existingCallingFeatureList) {
		String functionName="populateCartFeaturesToAdd";
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.getProducts()!=null && cartItem.getProducts().getHomePhoneProduct()!=null && CollectionUtils.isNotEmpty(cartItem.getProducts().getHomePhoneProduct().getFeatures())){
					for(FFHFeatureTypeVO cartFeature : cartItem.getProducts().getHomePhoneProduct().getFeatures()){
						if(cartFeature!=null && !EnterpriseWLNSalesServicesConstants.PRODUCT_ITEM_ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction()) && StringUtils.isNotBlank(cartFeature.getProductCatalogueIdentifier().getProductCatalogueId())){
							CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
							CatalogueItemDO featureCatalogueItem = gridHelper.getCatalogueItemById(cartFeature.getProductCatalogueIdentifier().getProductCatalogueId());							
							if(featureCatalogueItem!=null && !cartFeatureIsExisting(featureCatalogueItem,existingCallingFeatureList)){
								logger.info(functionName, "Calling Feature from Shopping cart with productCatalogueId=" + cartFeature.getProductCatalogueIdentifier().getProductCatalogueId() + " and name: " + featureCatalogueItem.getName() + " has been added to the Feasibility productSpecificationList");
								ProductSpecificationCharacteristic prodSpecCharac = createProductSpecificationCharacteristic("callingFeatureList", ValueType.STRING.value(), false, featureCatalogueItem.getName());
								prodSpecCharacs.add(prodSpecCharac);
							}							
							
						}
					}
				}
			}		
	}

	private static boolean cartFeatureIsExisting(CatalogueItemDO featureCatalogueItem,List<FeatureInfoRestVO> existingCallingFeatureList) {
		if(CollectionUtils.isNotEmpty(existingCallingFeatureList)){
			for(FeatureInfoRestVO existingFeature : existingCallingFeatureList){
				if(StringUtils.isNotBlank(existingFeature.getName()) && StringUtils.isNotBlank(featureCatalogueItem.getName()) && featureCatalogueItem.getName().equalsIgnoreCase(existingFeature.getName())){
					return true;
				}
			}
		}
		return false;		
	}

	private static void populateExistingCallingFeatures(SubscribedProductInfoRestVO subscribedProduct,ShoppingCartVO shoppingCartVO, List<ProductSpecificationCharacteristic> prodSpecCharacs, List<FeatureInfoRestVO> existingCallingFeatureList) {
		if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) && subscribedProduct.getProductInstance().get(0).getSingleLineComponent()!=null && CollectionUtils.isNotEmpty(subscribedProduct.getProductInstance().get(0).getSingleLineComponent().getCallingFeatureList())){
			for(FeatureInfoRestVO feature : subscribedProduct.getProductInstance().get(0).getSingleLineComponent().getCallingFeatureList()){
				if(!existingCallingFeatureIsRemove(feature,shoppingCartVO)){
					ProductSpecificationCharacteristic prodSpecCharac9 = createProductSpecificationCharacteristic("callingFeatureList", ValueType.STRING.value(), false, feature.getName());
					prodSpecCharacs.add(prodSpecCharac9);
					existingCallingFeatureList.add(feature);
				}
				
			}
		}				
	}

	private static boolean existingCallingFeatureIsRemove(FeatureInfoRestVO feature, ShoppingCartVO shoppingCartVO) {
		String functionName = "existingCallingFeatureIsRemove";
		if(feature!=null && shoppingCartVO!=null && CollectionUtils.isNotEmpty(shoppingCartVO.getCartItemList())){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.getProducts()!=null && cartItem.getProducts().getHomePhoneProduct()!=null && CollectionUtils.isNotEmpty(cartItem.getProducts().getHomePhoneProduct().getFeatures())){
					for(FFHFeatureTypeVO cartFeature : cartItem.getProducts().getHomePhoneProduct().getFeatures()){
						if(cartFeature.getProductCatalogueIdentifier()!=null && StringUtils.isNotBlank(cartFeature.getProductCatalogueIdentifier().getProductCatalogueId())){
							CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
							CatalogueItemDO featureCatalogueItem = gridHelper.getCatalogueItemById(cartFeature.getProductCatalogueIdentifier().getProductCatalogueId());
							if(featureCatalogueItem!=null && featureCatalogueItem.getName()!=null && featureCatalogueItem.getName().equalsIgnoreCase(feature.getName())){							
								logger.info(functionName, "Existing Calling Feature: " + feature.getName() + " has been found from the ShoppingCart calling features.");
								if(EnterpriseWLNSalesServicesConstants.ACTION_REMOVE.equalsIgnoreCase(cartFeature.getProductCatalogueIdentifier().getAction())){
									logger.info(functionName, "Existing calling Feature: " + feature.getName() + " is REMOVE, not adding this calling Feature to the FeasibilitySvc Request");
									return true;
								}
							}
							
						}
					}
				}
			}
		}
		logger.exit(functionName);
		return false;
	}

	private static boolean callingFeaturesExistInShoppingCart(ShoppingCartVO shoppingCartVO) {
		
		if(shoppingCartVO!=null && CollectionUtils.isNotEmpty(shoppingCartVO.getCartItemList())){			
			//check if SL is present
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.getProducts()!=null && cartItem.getProducts().getHomePhoneProduct()!=null && CollectionUtils.isNotEmpty(cartItem.getProducts().getHomePhoneProduct().getFeatures())){
					return true;
				}
			}
		}
		
		return false;
	}

	private static boolean tvEquipmentAdded(ShoppingCartVO shoppingCartVO) {

		if(shoppingCartVO!=null){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){				
				if(cartItem.getProducts()!=null && cartItem.getProducts().getTelevisionProduct()!=null && CollectionUtils.isNotEmpty(cartItem.getProducts().getTelevisionProduct().getEquipments())){
					for(FFHEquipmentTypeVO equipment : cartItem.getProducts().getTelevisionProduct().getEquipments()){
						if(equipment.getAcquisitionType()!=null && INSTALLER.equalsIgnoreCase(equipment.getDeliveryMethodType()) && (equipment.getAcquisitionType().isCustomerOwnedIndicator() == null || Boolean.FALSE.equals(equipment.getAcquisitionType().isCustomerOwnedIndicator()))){
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	private static boolean isUpgradeTV(ShoppingCartVO shoppingCartVO) {

		if(shoppingCartVO!=null){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){				
				if(cartItem.getProducts()!=null && cartItem.getProducts().getTelevisionProduct()!=null){
					ProductOrderTypeVO orderType = cartItem.getProducts().getTelevisionProduct().getProductOrderType();
					if("UPGRADE".equalsIgnoreCase(orderType.getOrderTypeCd()))
						return true;
				}
			}
		}
		
		return false;
	}

	private static boolean isEquipmentInContextTypeList(ShoppingCartVO shoppingCartVO) {
		if(shoppingCartVO!=null && CollectionUtils.isNotEmpty(shoppingCartVO.getCartItemList())){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.isSalesOrderItem() && CollectionUtils.isNotEmpty(cartItem.getCartItemContextTypeList()) && EnterpriseSalesServiceUtil.containsAnyIgnoreCase(cartItem.getCartItemContextTypeList(), new String[]{EQUIPMENT,ALL})){
					return true;
				}
			}
		}
		return false;
	}

	private static boolean isTvInCart(ShoppingCartVO shoppingCartVO) {
		
		if(shoppingCartVO!=null){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){				
				if(cartItem.getProducts()!=null && cartItem.getProducts().getTelevisionProduct()!=null){
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	

	private static int getNumAddedEquipments(String productType, ShoppingCartVO shoppingCartVO,String type) {
		int numWlnEquipmentAdded = 0;
		int numWlsEquipmentAdded = 0;
		if(!CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.getProducts()!=null && cartItem.getProducts().getTelevisionProduct()!=null && EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType)){
					TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();
					if(CollectionUtils.isNotEmpty(televisionProduct.getEquipments())){
						for(FFHEquipmentTypeVO equipment : televisionProduct.getEquipments()){
							if(equipment.getAcquisitionType()!=null && INSTALLER.equalsIgnoreCase(equipment.getDeliveryMethodType()) && (equipment.getAcquisitionType().isCustomerOwnedIndicator() == null || Boolean.FALSE.equals(equipment.getAcquisitionType().isCustomerOwnedIndicator()))){
								if(!isWirelessEquipmentAdded(equipment)){
									numWlnEquipmentAdded++;
								}else{
									numWlsEquipmentAdded++;
								}
							}
						}
					}
				}				
			}
		}
		
		if(type.equalsIgnoreCase(WIRED_EQUIPMENT)){
			return numWlnEquipmentAdded;
		}else{
			return numWlsEquipmentAdded;
		}
	}

	private static boolean isWirelessEquipmentAdded(FFHEquipmentTypeVO equipment) {
			if(EnterpriseWLNSalesServicesConstants.WIRELESS_STB_MIC_BUY.equals(equipment.getMaterialItemCode()) 
					|| EnterpriseWLNSalesServicesConstants.WIRELESS_STB_MIC_RENT.equals(equipment.getMaterialItemCode()) 
					|| EnterpriseWLNSalesServicesConstants.WIRELESS_OPTIK_TV_4K_STB_MIC_BUY.equals(equipment.getMaterialItemCode()) 
					|| EnterpriseWLNSalesServicesConstants.WIRELESS_OPTIK_TV_4K_STB_MIC_RENT.equals(equipment.getMaterialItemCode())){
						return true;
					}
		
		return false;
	}

	private static int getTotalNumEquipments(String productType,ShoppingCartVO shoppingCartVO, List<SubscribedProductInfoRestVO> existingProducts) {
		int numEquipmentAdded = 0;
		if(!CollectionUtils.isEmpty(shoppingCartVO.getCartItemList())){
			for(CartItemVO cartItem : shoppingCartVO.getCartItemList()){
				if(cartItem.getProducts()!=null &&  cartItem.getProducts().getTelevisionProduct()!=null && EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType)){
					TelevisionProductTypeVO televisionProduct = cartItem.getProducts().getTelevisionProduct();		
					numEquipmentAdded = getEquipmentAdded(televisionProduct.getEquipments()) + getExistingEquipmentByProductType(productType,existingProducts);
					break;					
				}		
			}
		}
		
		
		return numEquipmentAdded;
	}

	private static int getExistingEquipmentByProductType(String productType,List<SubscribedProductInfoRestVO> existingProducts) {
		if(CollectionUtils.isNotEmpty(existingProducts)){
			for(SubscribedProductInfoRestVO subscribedProduct : existingProducts){
				if(subscribedProduct.getProductTypeCd().equalsIgnoreCase(productType)){
					if(subscribedProduct.getProductInstance().get(0).getEquipmentList()!=null){
						return subscribedProduct.getProductInstance().get(0).getEquipmentList().size();
					}
				}
			}
		}
		return 0;
	}

	private static int getEquipmentAdded(List<FFHEquipmentTypeVO> equipments) {
		int equipmentAdded = 0;
		if(CollectionUtils.isNotEmpty(equipments)){
			for(FFHEquipmentTypeVO equipment : equipments){
				if(equipment.getAcquisitionType()!=null && INSTALLER.equalsIgnoreCase(equipment.getDeliveryMethodType()) && (equipment.getAcquisitionType().isCustomerOwnedIndicator() == null || Boolean.FALSE.equals(equipment.getAcquisitionType().isCustomerOwnedIndicator()))){
					equipmentAdded++;
				}
			}
		}
		return equipmentAdded;
	}

	private static String getHdChannelInd(List<SubscribedProductInfoRestVO> existingProducts) {
		String hdChannelInd = null;
		if(existingProducts!=null && !existingProducts.isEmpty()){
			for(SubscribedProductInfoRestVO subscribedProduct : existingProducts){
				if(SalesServiceConstants.OMS_PRODUCT_TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					ProductInstanceInfoRestVO productInstance = subscribedProduct.getProductInstance().get(0);
					if(productInstance!=null && productInstance.getTtvComponent()!=null && productInstance.getTtvComponent().getHdChannelInd()!=null){
						hdChannelInd = productInstance.getTtvComponent().getHdChannelInd().toString();
						break;
					}
					
				}
			}
		}
		return hdChannelInd;
	}

	private static String getLocalServiceProvider(List<SubscribedProductInfoRestVO> existingProducts) {
		String localServiceProvider=null;
		if(existingProducts!=null && !existingProducts.isEmpty()){
			for(SubscribedProductInfoRestVO subscribedProduct : existingProducts){
				if(SalesServiceConstants.OMS_PRODUCT_HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					ProductInstanceInfoRestVO productInstance = subscribedProduct.getProductInstance().get(0);
					if(productInstance!=null && productInstance.getInternetComponent()!=null && !StringUtils.isBlank(productInstance.getInternetComponent().getInternetTypeCd())){
						localServiceProvider = productInstance.getInternetComponent().getInternetTypeCd();
						break;
					}
					
				}
			}
		}
		return localServiceProvider;
	}

	private static void populateSTBCharacteristics(Boolean changeOfTechnologyYes, String productTypeCd,
			Map<String, Boolean> isNewMap, List<ProductSpecificationCharacteristic> prodSpecCharacs) {
		if (TTV.equalsIgnoreCase(productTypeCd)) {
			if (changeOfTechnologyYes) {
				if (isNewMap.get(IS_NEW_CUSTOMER)){
					if (isNewMap.get(IS_NEW_HSIC) && isNewMap.get(IS_NEW_TTV)) {
						addSTBCharacteristics(prodSpecCharacs, 1, 1);
					}
				} else {
					// existing customer
					if (!isNewMap.get(IS_NEW_HSIC) && !isNewMap.get(IS_NEW_TTV)) {
						addSTBCharacteristics(prodSpecCharacs, 2, 0);
					} else if (!isNewMap.get(IS_NEW_HSIC) && isNewMap.get(IS_NEW_TTV)) {
						addSTBCharacteristics(prodSpecCharacs, 1, 1);
					}
				}
			} else {
				if (isNewMap.get(IS_NEW_CUSTOMER)){
					if (isNewMap.get(IS_NEW_HSIC) && isNewMap.get(IS_NEW_TTV)) {
						addSTBCharacteristics(prodSpecCharacs, 1, 1);
					}
				} else {
					// existing customer
					if (!isNewMap.get(IS_NEW_HSIC) && !isNewMap.get(IS_NEW_TTV)) {
						addSTBCharacteristics(prodSpecCharacs, 1, 1);
					} else if (!isNewMap.get(IS_NEW_HSIC) && isNewMap.get(IS_NEW_TTV)) {
						addSTBCharacteristics(prodSpecCharacs, 1, 1);
					}
				}
			}
		}
	}
	
	/**
	 * @param prodSpecCharacs
	 * @param wired
	 * @param wls
	 */
	private static void addSTBCharacteristics(List<ProductSpecificationCharacteristic> prodSpecCharacs, int wired, int wls) {
		if (wired > 0) {
			ProductSpecificationCharacteristic prodSpecCharacNewSTB = createProductSpecificationCharacteristic(NEW_STB_TO_BE_INSTALLED_BY_TELUS, ValueType.NUMERIC.value(), false, String.valueOf(wired));
			prodSpecCharacs.add(prodSpecCharacNewSTB);
		}
		if (wls > 0) {
			ProductSpecificationCharacteristic prodSpecCharacNewWlsSTB = createProductSpecificationCharacteristic(NEW_WLS_STB_TO_BE_INSTALLED_BY_TELUS, ValueType.NUMERIC.value(), false, String.valueOf(wls));
			prodSpecCharacs.add(prodSpecCharacNewWlsSTB);
		}
		
	}

	/**
	 * @param productTypeCd
	 * @return
	 */
	public static String mapProductTierToWSSProductTier(String productTier) {
		String wssProductTier = "";
		
		Map <String, String> productTierMap;
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		productTierMap = refPdsBusDelegate.getReferencePDSTableByName(REFPDS_WSS_PRODUCT_TIER);
		if (productTierMap.isEmpty()){
			return wssProductTier;
		} else {
			for (Map.Entry<String, String> entry : productTierMap.entrySet()) {
				if (entry.getValue().equals(productTier)) {
					return entry.getKey();
				}
			}
		}
		return wssProductTier;
	}

	/**
	 * @param productTypeCd
	 * @return
	 */
	public static String mapToOMSProductTier(String productTypeCd) {
		String functionName = "mapToOMSProductTier";
		String omsProductTier = "";
		Map<String, String> omsProductCdMap;
		ReferencePDSDataSvcBusDelegator refPdsBusDelegate = ReferencePDSDataSvcBusDelegator.getInstance();
		omsProductCdMap = refPdsBusDelegate
				.getReferencePDSTableByName(REFPDS_WSS_PRODUCT_TIER);

		if (!omsProductCdMap.isEmpty()) {
			omsProductTier = omsProductCdMap.get(productTypeCd);
			if (!StringUtils.isEmpty(omsProductTier)) {
				logger.debug(functionName, productTypeCd + " was found in RefPDS and mapped to OMS code: "
						+ omsProductCdMap.get(productTypeCd));
			} else {
				logger.debug(functionName, productTypeCd + " was NOT found in RefPDS.");
			}

		} else {
			logger.debug(functionName, "RefPDS returned an empty map for table "
					+ REFPDS_WSS_PRODUCT_TIER);
		}

		return omsProductTier;
	}
	
	/**
	 * @param productType
	 * @return
	 */
	public static String getProductNumberFromMap(String productType) {
		return productCatalogMap.get(productType);
	}
	
	/**
	 * @param name
	 * @param valueType
	 * @param isSetDefault
	 * @param value
	 * @return
	 */
	public static ProductSpecificationCharacteristic createProductSpecificationCharacteristic(String name, String valueType,
			boolean isSetDefault, String value) {
		ProductSpecificationCharacteristic prodSpecCharac = new ProductSpecificationCharacteristic();
		ProductSpecificationCharacteristicMapper prodSpecCharacHelper = new ProductSpecificationCharacteristicMapper(prodSpecCharac);
		prodSpecCharac.setName(name);
		prodSpecCharac.setValueType(ValueType.fromValue(valueType));
		
		List<CompositeProductSpecificationCharacteristicValue> prodSpecCharacVals = prodSpecCharacHelper.getProductSpecificationCharacteristicValues();
		
		CompositeProductSpecificationCharacteristicValue prodSpecCharacVal = new CompositeProductSpecificationCharacteristicValue();
		prodSpecCharacVal.setValueType(ValueType.fromValue(valueType));
		prodSpecCharacVal.setDefault(isSetDefault);
		prodSpecCharacVal.setValue(value);
		
		prodSpecCharacVals.add(prodSpecCharacVal);
		
		prodSpecCharacHelper.finished();
		return prodSpecCharac;
	}
}
