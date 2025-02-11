package com.telus.csm.ewlnsc.adapter.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

import com.telus.csm.ewlnsc.adapter.impl.FeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.AccessCriteria;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductInstanceList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductServiceInstance;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductSpecificationList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAccessList;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ServiceAddress;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

public class CheckProductFeasibilityAdapterRequest extends AdapterRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userId;
	private Timestamp timeStamp;
	private String customerId;
	private ServiceAddress address;
	private AccessCriteria accessCriteria;
	private ServiceAccessList serviceAccessList;
	private ProductSpecificationList productSpecificationList; 
	private ProductInstanceList productInstanceList; 
	private Boolean cotInd;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public ServiceAddress getAddress() {
		return address;
	}
	public void setAddress(ServiceAddress address) {
		this.address = address;
	}
	public AccessCriteria getAccessCriteria() {
		return accessCriteria;
	}
	public void setAccessCriteria(AccessCriteria accessCriteria) {
		this.accessCriteria = accessCriteria;
	}
	public ServiceAccessList getServiceAccessList() {
		return serviceAccessList;
	}
	public void setServiceAccessList(ServiceAccessList serviceAccessList) {
		this.serviceAccessList = serviceAccessList;
	}
	public ProductSpecificationList getProductSpecificationList() {
		return productSpecificationList;
	}
	public void setProductSpecificationList(ProductSpecificationList productSpecificationList) {
		this.productSpecificationList = productSpecificationList;
	}
	public ProductInstanceList getProductInstanceList() {
		return productInstanceList;
	}
	public void setProductInstanceList(ProductInstanceList productInstanceList) {
		this.productInstanceList = productInstanceList;
		
	}
	public Boolean getCotInd() {
		return cotInd;
	}
	public void setCotInd(Boolean cotInd) {
		this.cotInd = cotInd;
	}
	
	@Override
	public String getCacheKey(){
		String addressId = getAddress().getAddressId();
		String proviceStateCd = getAddress().getProvinceStateCode();
		String transactionId = getSalesTransactionId();
		StringBuilder sbCacheKey = new StringBuilder();
			sbCacheKey.append(FeasibilityServiceAdapter.class.getSimpleName());
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SALES_TXN_ID);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(transactionId);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.ADDRESS_ID);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(addressId);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PROVINCE_STATE_CODE);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(proviceStateCd);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.BRAND_ID);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(new BigInteger(Constants.TELUS_BRAND_ID));
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.COT_IND);
			sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
			sbCacheKey.append(this.cotInd);

			//Iterating over subscribedProductList
			if (productSpecificationList != null) {
				for (ProductSpecification productSpecification : this.productSpecificationList.getProductSpecification()) {
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_NAME);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productSpecification.getName());
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_NUMBER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productSpecification.getProductNumber());
					if(productSpecification.getProductSpecificationCharacteristics()!=null && !productSpecification.getProductSpecificationCharacteristics().isEmpty()){
						for (ProductSpecificationCharacteristic productSpecificationCharacteristic : productSpecification.getProductSpecificationCharacteristics()) {
							//We cannot use the productRequestId as part of the cacheKey, since this one is composed by productType + timeStamp making the record unique across all the requests
							//Also, we cannot use the serviceRequestDate, since this element is unique for request, since it contains the time stamp
							if(!EnterpriseWLNSalesServicesConstants.PRODUCT_REQUEST_ID.equalsIgnoreCase(productSpecificationCharacteristic.getName()) && !EnterpriseWLNSalesServicesConstants.SERVICE_REQUEST_DATE.equalsIgnoreCase(productSpecificationCharacteristic.getName())){
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_CHARACTERISTIC_NAME);
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
								sbCacheKey.append(productSpecificationCharacteristic.getName());
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_CHARACTERISTIC_VALUE_TYPE);
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
								sbCacheKey.append(productSpecificationCharacteristic.getProductSpecificationCharacteristicValues().get(0).getValueType());
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_CHARACTERISTIC_VALUE);
								sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
								sbCacheKey.append(productSpecificationCharacteristic.getProductSpecificationCharacteristicValues().get(0).getValue());
							}
						}
					}				
				}
			}

			//Iterating over orderedProductList
			if(this.productInstanceList!=null && this.productInstanceList.getProductInstance()!=null && !this.productInstanceList.getProductInstance().isEmpty()){
				for(ProductServiceInstance productServiceInstance : this.productInstanceList.getProductInstance()){
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_TYPE_CODE);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productServiceInstance.getProductTypeCode());
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_CATALOG_ID);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productServiceInstance.getProductCatalogId());
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.SERVICE_ID);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productServiceInstance.getServiceId());
					
					//We cannot use the productRequestId as part of the cacheKey, since this one is composed by productType + timeStamp making the record unique
					
					/*
					for (ProductParameter productParameter : productServiceInstance.getProductParameters()) {
						if (EnterpriseWLNSalesServicesConstants.PRODUCT_REQUEST_ID.equals(productParameter.getParameterName())) {
							sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
							sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_REQUEST_ID);
							sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
							sbCacheKey.append(productParameter.getParameterValue());
							break;
						}
					}
					*/				
				}
			}
			
		
		return sbCacheKey.toString();
	}
	
	
}
