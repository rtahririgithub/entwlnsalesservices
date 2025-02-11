package com.telus.csm.ewlnsc.transformer;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.InternetProductTypeVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartContextVO;
import com.telus.csm.ewlnsc.domain.ShoppingCartVO;
import com.telus.csm.ewlnsc.domain.TelevisionProductTypeVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.EnterpriseWLNOrderUtil;
import com.telus.csm.ewlnsc.helper.ProductInstanceMapper;
import com.telus.csm.ewlnsc.helper.ProductSpecificationMapper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.AccessCriteria;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductSpecificationList;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

public class FeasibilitySvcTransformer {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(FeasibilitySvcTransformer.class);
	
	private FeasibilitySvcTransformer(){
	}

	public static CheckProductFeasibilityAdapterRequest transform(ShoppingCartContextVO contextVO){
		String functionName = "FeasibilitySvcTransformer.transform()";
		logger.info(functionName,"Preparing FeasibilitySvc request for ShoppingCart operations.");
		
		CheckProductFeasibilityAdapterRequest request = new CheckProductFeasibilityAdapterRequest();
		
		if(contextVO.getShoppingCartVO()==null){
			return request;
		}
		ShoppingCartVO shoppingCartVO = contextVO.getShoppingCartVO();
		if(shoppingCartVO.getShoppingProfile()!=null && shoppingCartVO.getShoppingProfile().getUserProfile()!=null){
			request.setUserId(shoppingCartVO.getShoppingProfile().getUserProfile().getSalesRepInternalId());
		}
		
		request.setTimeStamp(new Timestamp(new Date().getTime()));
		
		
		if(shoppingCartVO.getOperationHeader()!=null)
		request.setSalesTransactionId(shoppingCartVO.getOperationHeader().getSalesTransactionId());
		
		/**
		 * Transforming the service Address
		 */
		request.setAddress(getServiceAddressFromVO(contextVO.getServiceAddressResponseVO()));
		
		List<SubscribedProductInfoRestVO> assignedProducts = EnterpriseWLNCommonTransformer.getAssignedProductsFromVO(contextVO);
		
		if(shoppingCartVO.getCustomer()!=null && !StringUtils.isBlank(shoppingCartVO.getCustomer().getCustomerId())){ 
			request.setCustomerId(shoppingCartVO.getCustomer().getCustomerId());
		}
		
		// String hsServicePlan = EnterpriseWLNSalesServicesConstants.SERVICE_PLAN
		String productRequestId = new SimpleDateFormat(Constants.FORMAT_SHORT_DATE_AND_TIME_MIL).format(new Date());
		
		boolean changeOfTechnology = EnterpriseWLNOrderUtil.getCotIndicator(contextVO.getProductQualificationAdapterResponseVO());
		
		request.setCotInd(changeOfTechnology);
		
		if(!changeOfTechnology && !assignedProducts.isEmpty()){
			request.setProductInstanceList(ProductInstanceMapper.mapToProductInstanceForAvailableProducts(productRequestId, assignedProducts)); //Existing products
		}
		
		List<Product> orderedProducts = EnterpriseWLNCommonTransformer.getOrderedProductFromVO(shoppingCartVO,contextVO);
		
		if(changeOfTechnology && !assignedProducts.isEmpty()){
			enrichOrderedProducstFromExisting(orderedProducts,assignedProducts,shoppingCartVO);
		}
		
		isCustomerUpgradingExistingProduct(assignedProducts,orderedProducts,shoppingCartVO,contextVO);
		
		request.setProductSpecificationList(ProductSpecificationMapper.mapToProductSpecificationListForAvailableProducts(changeOfTechnology, productRequestId, assignedProducts, contextVO.getServiceAddressResponseVO(), orderedProducts,shoppingCartVO));
		
		request.setAccessCriteria(mapAccessCriteria(request.getProductSpecificationList(),assignedProducts));
		logger.exit(functionName);
		return request;
	}
	
private static void isCustomerUpgradingExistingProduct(List<SubscribedProductInfoRestVO> assignedProducts,List<Product> orderedProducts,ShoppingCartVO shoppingCart,ShoppingCartContextVO contextVO) {
		/**
		 * If customer is upgrading an existing product, we need to call FeasibilityService with the new upgrade product, not the existing one
		 */
		if(!CollectionUtils.isEmpty(assignedProducts)){
			for(SubscribedProductInfoRestVO existingProduct : assignedProducts){
				if(EnterpriseWLNOrderUtil.isUpgradingExistingProduct(existingProduct, shoppingCart) || EnterpriseWLNOrderUtil.isCustomerRecontractingProduct(existingProduct,shoppingCart)){
					//remove the existing product from the orderedProducts and add the ordered product from the ShoppingCart
					removeOrderedProduct(existingProduct.getProductTypeCd(),orderedProducts);
					
					//add the ordered product for upgrade
					addProductToUpgrade(shoppingCart,orderedProducts,existingProduct.getProductTypeCd(),contextVO);
				}
			}
		}
		
		
		
	}

private static void addProductToUpgrade(ShoppingCartVO shoppingCart, List<Product> orderedProducts, String productType,ShoppingCartContextVO contextVO) {
	
	for(CartItemVO cartItem : shoppingCart.getCartItemList()){
		if(cartItem.isSalesOrderItem()){
			if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productType) && cartItem.getProducts()!=null && cartItem.getProducts().getInternetProduct()!=null){
					Product internet = new Product();
					internet.setProductTypeCd(EnterpriseWLNSalesServicesConstants.HSIC);
					String hsicTier = null;
					String productCatalogueId = null;
					if(cartItem.getProducts().getInternetProduct().getProductComponents()!=null && !cartItem.getProducts().getInternetProduct().getProductComponents().isEmpty()){
						productCatalogueId = EnterpriseWLNCommonTransformer.getProductCatalogueIdFromVO(cartItem.getProducts().getInternetProduct().getProductComponents());
					}
					CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
					CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
					
					if(catalogueItemDO!=null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())){
						hsicTier = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
					}
					if(!StringUtils.isBlank(hsicTier)){
						internet.setProductTierCd(hsicTier);
					}
					internet.setProductCatalogueId(productCatalogueId);
					orderedProducts.add(internet);
					break;
			}
			
			if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productType)){
				Product television = new Product();
					television.setProductTypeCd(EnterpriseWLNSalesServicesConstants.TTV);
					String ttvTier = null;
					String productCatalogueId=null;
					if(cartItem.getProducts()!=null && cartItem.getProducts().getTelevisionProduct()!=null && !CollectionUtils.isEmpty(cartItem.getProducts().getTelevisionProduct().getProductComponents())){
						productCatalogueId = EnterpriseWLNCommonTransformer.getProductCatalogueIdFromVO(cartItem.getProducts().getTelevisionProduct().getProductComponents());
					}
					CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
					CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogueId);
					
					if(catalogueItemDO!=null && !StringUtils.isEmpty(catalogueItemDO.getProductCode())){
						ttvTier = WLNOfferUtil.mapOmsCode(catalogueItemDO.getProductCode());
					}
					if(!StringUtils.isBlank(ttvTier)){
						television.setProductTierCd(ttvTier);
					}else{
						String ttvTierCodeFromCtxVO = EnterpriseWLNCommonTransformer.getTTVTierCodeFromCtxVO(cartItem, contextVO);
						if(StringUtils.isNotBlank(ttvTierCodeFromCtxVO)){
							television.setProductTierCd(ttvTierCodeFromCtxVO);
						}
						
					}
					television.setProductCatalogueId(productCatalogueId);
					orderedProducts.add(television);
					break;
			}
			
			if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(productType) && cartItem.getProducts()!=null && cartItem.getProducts().getHomePhoneProduct()!=null && CollectionUtils.isNotEmpty(cartItem.getProducts().getHomePhoneProduct().getProductComponents())){
				Product homePhone = new Product();
					homePhone.setProductTypeCd(EnterpriseWLNSalesServicesConstants.SING);
					homePhone.setProductTierCd(EnterpriseWLNSalesServicesConstants.SING);
					homePhone.setProductCatalogueId(EnterpriseWLNCommonTransformer.getProductCatalogueIdFromVO(cartItem.getProducts().getHomePhoneProduct().getProductComponents()));
					orderedProducts.add(homePhone);
					break;
			}
		}
	}
	
}

private static void removeOrderedProduct(String productTypeCd, List<Product> orderedProducts) {
	Product product = null;
	for(Product orderedProduct : orderedProducts){
		if(productTypeCd.equalsIgnoreCase(orderedProduct.getProductTypeCd())){
			product = orderedProduct;
			break;
		}
	}
	
	if(product!=null){
		orderedProducts.remove(product);
	}
}

private static void enrichOrderedProducstFromExisting(List<Product> orderedProducts,
			List<SubscribedProductInfoRestVO> assignedProducts, ShoppingCartVO shoppingCartVO) {
		String functionName="enrichOrderedProducstFromExisting";
		logger.enter(functionName);
		if(assignedProducts!=null && !assignedProducts.isEmpty()){
			
			for(SubscribedProductInfoRestVO subscribedProduct : assignedProducts){
				if(!existingProductIsOrdered(subscribedProduct,shoppingCartVO)){
					//add the new product
					addExistingProductToOrderedProducts(orderedProducts,subscribedProduct);
					
				}
			}
		}
		logger.exit(functionName);
	}

private static void addExistingProductToOrderedProducts(List<Product> orderedProducts,SubscribedProductInfoRestVO subscribedProduct) {
	String functionName="addExistingProductToOrderedProducts";
	boolean productFound = false;
	for(Product product : orderedProducts){
		if(product.getProductTypeCd().equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
			productFound = true;
			break;
		}
	}
	
	if(!productFound){
		logger.debug(functionName, "Change of technology scenario: adding non requested product to productSpecificationList");
		Product product = new Product();
		product.setProductTypeCd(subscribedProduct.getProductTypeCd());
		if(!StringUtils.isBlank(subscribedProduct.getProductTierCd())){
			product.setProductTierCd(subscribedProduct.getProductTierCd());
		}
		orderedProducts.add(product);
	}
}

private static boolean existingProductIsOrdered(SubscribedProductInfoRestVO subscribedProduct,
		ShoppingCartVO shoppingCartVO) {
	boolean productOrderedIsAssigned=false;
	if(shoppingCartVO!=null && shoppingCartVO.getCartItemList()!=null && !shoppingCartVO.getCartItemList().isEmpty()){
		for(CartItemVO cartItemVO : shoppingCartVO.getCartItemList()){
			if (cartItemVO.isSalesOrderItem()) {
			if(cartItemVO.getProducts().getInternetProduct()!=null){
						InternetProductTypeVO internetProduct = cartItemVO.getProducts().getInternetProduct();
						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd()) 
								&& internetProduct.getSelectedContractTermCd().equalsIgnoreCase(WLNOfferUtil.getContracTermInMonthByYears(subscribedProduct.getProductInstance().get(0).getTermCd()))
								&& catalogueIdMatch(subscribedProduct.getProductTierCd(),internetProduct.getProductComponents().get(0).getProductCatalogueId())){
							productOrderedIsAssigned=true;
							break;
						}
			}
			if(cartItemVO.getProducts().getTelevisionProduct()!=null){
				TelevisionProductTypeVO televisionProduct = cartItemVO.getProducts().getTelevisionProduct();
				if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())
						&& televisionProduct.getSelectedContractTermCd().equalsIgnoreCase(WLNOfferUtil.getContracTermInMonthByYears(subscribedProduct.getProductInstance().get(0).getTermCd()))
						&& catalogueIdMatch(subscribedProduct.getProductTierCd(),televisionProduct.getProductComponents().get(0).getProductCatalogueId())){
					productOrderedIsAssigned=true;
					break;
				}
					
			}
			if(cartItemVO.getProducts().getHomePhoneProduct()!=null){
				if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					productOrderedIsAssigned = true;
					break;
				}
			}
			}	
		}
	}
	return productOrderedIsAssigned;
}

private static boolean catalogueIdMatch(String productTierCd, String productCatalogueId) {
	
	String existingProductCatalogueId = WLNOfferUtil.getInternalCidFromProductTierCd(productTierCd);
	
	if(!StringUtils.isBlank(existingProductCatalogueId) && productCatalogueId.equalsIgnoreCase(existingProductCatalogueId)){
		return true;
	}
	
	return false;
}




	private static com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress getServiceAddressFromVO(
			ServiceAddressResponseVO serviceAddressResponseVO) {
		com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress feasibilityServiceAddress = new com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress();
		
		//TODO: throw exception if we cannot get the serviceAddress
		
		if(serviceAddressResponseVO!=null && serviceAddressResponseVO.getServiceAddress()!=null){
			com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress serviceAddress = serviceAddressResponseVO.getServiceAddress();
			feasibilityServiceAddress.setAddressId(serviceAddress.getAddressId());
			feasibilityServiceAddress.setCountryCode(serviceAddress.getCountryCode());
			feasibilityServiceAddress.setMunicipalityName(serviceAddress.getMunicipalityName());
			feasibilityServiceAddress.setPostalZipCode(serviceAddress.getPostalZipCode());
			feasibilityServiceAddress.setProvinceStateCode(serviceAddress.getProvinceStateCode());
			feasibilityServiceAddress.setStreetName(serviceAddress.getStreetName());
			feasibilityServiceAddress.setStreetNumber(serviceAddress.getStreetNumber());
			feasibilityServiceAddress.setStreetTypeCode(serviceAddress.getStreetTypeCode());
			feasibilityServiceAddress.setUnitNumber(serviceAddress.getUnitNumber());
			feasibilityServiceAddress.setCLLICode(serviceAddress.getCLLICode());
			feasibilityServiceAddress.setCOID(serviceAddress.getCOID());
		}
		
		return feasibilityServiceAddress;
	}

	private static AccessCriteria mapAccessCriteria(ProductSpecificationList productSpecificationList, List<SubscribedProductInfoRestVO> assignedProducts) {
		AccessCriteria resp = new AccessCriteria();
		List<String> products = new ArrayList<String>();
		boolean existingSing=false;
		boolean existingHsic=false;
		if(productSpecificationList!=null && productSpecificationList.getProductSpecification()!=null && !productSpecificationList.getProductSpecification().isEmpty()){
			for(ProductSpecification productSpec : productSpecificationList.getProductSpecification()){
				if(productSpec.getProductSpecificationCharacteristics()!=null && !productSpec.getProductSpecificationCharacteristics().isEmpty()){
					for(ProductSpecificationCharacteristic characteristic : productSpec.getProductSpecificationCharacteristics()){
						if(EnterpriseWLNSalesServicesConstants.ORDER_ACTION_TYPE.equalsIgnoreCase(characteristic.getName())){
							if(EnterpriseWLNSalesServicesConstants.CREATE.equalsIgnoreCase(characteristic.getProductSpecificationCharacteristicValues().get(0).getValue())){
								products.add(productSpec.getName());
							}
						}
					}
				}
			}
		}
		
		if(assignedProducts!=null && !assignedProducts.isEmpty()){
			for(SubscribedProductInfoRestVO subscribedProduct : assignedProducts){
				if(EnterpriseWLNSalesServicesConstants.SING.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					existingSing = true;
				} else if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
					existingHsic = true;
				}
			}
		}
		
		/**
		 * if there is a provide SL product or provide HS and customer doesn't have SL
		 */
		/*
		 * From: Sergey
		 * I had clarification with OMS again during investigation of this issue - adding HS for existing SL
		 * so in case clearance information is required, it should be or clearance information or bypassCLearanceInd = true
		 */
		if((products.contains(EnterpriseWLNSalesServicesConstants.SING)  && !existingSing) || (products.contains(EnterpriseWLNSalesServicesConstants.HSIC) && !existingHsic)){
			//does not exists. It is a provide case
			resp.setBypassClearanceInd(true);
		}
		
		return resp;
	}
	
}
