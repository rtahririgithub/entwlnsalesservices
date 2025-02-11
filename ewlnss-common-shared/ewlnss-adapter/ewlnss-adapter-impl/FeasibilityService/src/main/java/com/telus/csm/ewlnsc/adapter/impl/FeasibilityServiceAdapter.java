package com.telus.csm.ewlnsc.adapter.impl;

import java.math.BigInteger;
import java.util.Collections;

import javax.xml.ws.BindingProvider;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.IFeasibilityServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.transformer.FeasibilityServiceTransformer;
import com.telus.csm.ewlnsc.adapter.transformer.ProductServiceInstanceComparator;
import com.telus.csm.ewlnsc.adapter.transformer.ProductSpecificationCharacteristicComparator;
import com.telus.csm.ewlnsc.adapter.transformer.ProductSpecificationComparator;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.cacheadapter.CacheAdapterFactory;
import com.telus.csm.ewlnsc.util.cacheadapter.ICacheAdapter;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.SOAPServiceAdapter;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.CheckProductFeasibility;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.CheckProductFeasibilityResponse;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductServiceInstance;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.types.ping_v1.Ping;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecification;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;
import com.telus.wsdl.smo.ordermgmt.feasibilityservice_3.FeasibilityServicePortType;
import com.telus.wsdl.smo.ordermgmt.feasibilityservice_3.FeasibilityServiceStub;
import com.telus.wsdl.smo.ordermgmt.feasibilityservice_3.PolicyException;
import com.telus.wsdl.smo.ordermgmt.feasibilityservice_3.ServiceException;

public class FeasibilityServiceAdapter extends SOAPServiceAdapter implements IFeasibilityServiceAdapter{
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(FeasibilityServiceAdapter.class);

	private static final String ERROR_PREFIX = "FeasibilityServiceAdapter_";

	private static String wsdlUrl = ApplicationProperties.getConfigString("${connections/webServices/feasibilityService/wsdlUrl}");
	
	public FeasibilityServiceAdapter(){
		super();
	}
	
	public FeasibilityServiceAdapter(AdapterFeatureDriver featureDrivers){
		super(featureDrivers);
		//Initialize CacheAdapter
		ICacheAdapter feasibilitySvcCacheAdapter = CacheAdapterFactory.getSessionCacheAdapter();
		super.setCacheAdapter(feasibilitySvcCacheAdapter);
	}
		
	@Override
	public String getWsdlUrl() {
		return wsdlUrl;
	}
	
	
	private synchronized FeasibilityServicePortType getPort(){
		return (FeasibilityServicePortType) getInitilizedPort();
	}
	
	@Override
	protected BindingProvider getNewPort() {
		return (BindingProvider) new FeasibilityServiceStub().getFeasibilityServicePort();
	}
	
	@Override
	public String ping() throws PolicyException, ServiceException {
		return getPort().ping(new Ping()).getVersion();
	}

	@Override
	public CheckProductFeasibilityAdapterResponse checkProductFeasibility(final CheckProductFeasibilityAdapterRequest request) {
		String functionName="checkProductFeasibility";
		LOGGER.info(functionName, "Entering method -> checkProductFeasibility");
		CheckProductFeasibilityAdapterResponse result = null;
		CheckProductFeasibilityResponse response = null;
		String contextData="";
		try{
			//checking if the response is already in the cache
			String cacheKey = buildCacheKey(request);
			LOGGER.info("checkProductFeasibility cacheKey: " , cacheKey);
			if(StringUtils.isNotBlank(cacheKey)){
				CheckProductFeasibilityAdapterResponse feasibilityResponseFromCache = super.getFromCache(cacheKey, CheckProductFeasibilityAdapterResponse.class);
				if(feasibilityResponseFromCache !=null){
					return feasibilityResponseFromCache;
				}
			}
			//Transforming AdapterRequest to Downstream Request
			FeasibilityServiceTransformer transformer = new FeasibilityServiceTransformer();
			CheckProductFeasibility checkProductFeasibilityRequest = transformer.transformAdapterRequestToDownstreamRequest(request);
			contextData = functionName + " Transaction ID: " + request.getSalesTransactionId() + " ]";
			response = getPort().checkProductFeasibility(checkProductFeasibilityRequest);
			if(response.getResponseMessageList()!=null && !response.getResponseMessageList().getResponseMessage().isEmpty() && response.getProductFeasibilityInfoList()!=null){
				LOGGER.info(functionName, "has messages from CheckProductFeasibility.. But successful response");
			}else if(response.getResponseMessageList()!=null && !response.getResponseMessageList().getResponseMessage().isEmpty() && response.getProductFeasibilityInfoList()==null){
				LOGGER.error("", functionName, "FeasibilityService returned Error only, please check Response");
			}
			result = transformer.transformDownstreamResponseToAdapterResponse(response);
			if(result.isSuccessfulProcessInd()){
				super.saveToCache(cacheKey, result);
			}
		}catch(PolicyException e){
			LOGGER.error(null,functionName,e.getMessage(),e);
			result = new CheckProductFeasibilityAdapterResponse();
			return (CheckProductFeasibilityAdapterResponse) this
					.handlePolicyException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextData, functionName, ERROR_PREFIX);
		}catch (ServiceException e){
			LOGGER.error(null, functionName, e.getMessage(),e);
			result = new CheckProductFeasibilityAdapterResponse();
			return (CheckProductFeasibilityAdapterResponse) this
					.handleServiceException(
							request.getSalesTransactionId(), e,
							e.getFaultInfo(), result,
							contextData, functionName, ERROR_PREFIX);
		} catch (Exception e){
			LOGGER.error(null, functionName, e.getMessage(),e);
			result = new CheckProductFeasibilityAdapterResponse();
			return (CheckProductFeasibilityAdapterResponse) this.handleException(
					request.getSalesTransactionId(), e, result,
					contextData, functionName);
		}
		return result;
	}

	public static String buildCacheKey(CheckProductFeasibilityAdapterRequest request){
		String addressId = request.getAddress().getAddressId();
		String proviceStateCd = request.getAddress().getProvinceStateCode();
		String transactionId = request.getSalesTransactionId();
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
			sbCacheKey.append(request.getCotInd());

			//Iterating over subscribedProductList
			if (request.getProductSpecificationList() != null) {
				//sort productSpecification by name 
				ProductSpecificationComparator psComparator = new ProductSpecificationComparator();
				Collections.sort(request.getProductSpecificationList().getProductSpecification(), psComparator);
				
				for (ProductSpecification productSpecification : request.getProductSpecificationList().getProductSpecification()) {
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_NAME);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productSpecification.getName());
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.PRODUCT_NUMBER);
					sbCacheKey.append(EnterpriseWLNSalesServicesConstants.CACHE_KEY_DELIMITER);
					sbCacheKey.append(productSpecification.getProductNumber());
					if(productSpecification.getProductSpecificationCharacteristics()!=null && !productSpecification.getProductSpecificationCharacteristics().isEmpty()){
						//sort productSpecificationCharacteristic by name
						ProductSpecificationCharacteristicComparator pscComparator = new ProductSpecificationCharacteristicComparator();
						Collections.sort(productSpecification.getProductSpecificationCharacteristics(),pscComparator);
						
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
			if(request.getProductInstanceList()!=null && request.getProductInstanceList().getProductInstance()!=null && !request.getProductInstanceList().getProductInstance().isEmpty()){
				//Sorting by productCatalogueId
				ProductServiceInstanceComparator psiComparator = new ProductServiceInstanceComparator();
				Collections.sort(request.getProductInstanceList().getProductInstance(),psiComparator);
				for(ProductServiceInstance productServiceInstance : request.getProductInstanceList().getProductInstance()){
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
						
				}
			}
			
		
		return sbCacheKey.toString();

	}



}
