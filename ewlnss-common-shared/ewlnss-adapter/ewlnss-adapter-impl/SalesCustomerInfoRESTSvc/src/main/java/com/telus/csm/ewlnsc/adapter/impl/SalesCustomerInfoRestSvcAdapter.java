package com.telus.csm.ewlnsc.adapter.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import com.telus.csm.ewlnsc.adapter.ISalesCustomerInfoRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnsc.util.SalesServiceConstants;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

public class SalesCustomerInfoRestSvcAdapter extends RestServiceAdapter implements ISalesCustomerInfoRestSvcAdapter {
	
	private static final String QUERY_PARAM_LANGUAGE_CD = "langcd";
	private static final String QUERY_PARAM_BRAND_ID = "brandid";
	private static final String QUERY_PARAM_APPLICATION_ID = "applicationid";
	private static final String QUERY_PARAM_ADDRESS_ID = "addressid";
	private static final String QUERY_PARAM_PROVINCE_CD = "provincecd";
	private static final String QUERY_PARAM_CITY = "city";
	private static final String QUERY_PARAM_QUAL_BY_SERVICE_ID = "qualbyserviceid";
	private static final String QUERY_PARAM_CORRELATION = "correlationid";
	private static final String QUERY_PARAM_SALES_REP_ID = "salesrepid";
	private static final String QUERY_PARAM_CHANNEL_OUTLET_ID = "channeloutletid";
	private static final String QUERY_PARAM_REFRESH_INDICATOR = "refreshIndicator"; // QC79739

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(SalesCustomerInfoRestSvcAdapter.class);
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/salesCustomerInfoRestSvc/wsdlUrl}");

	private static final String PRODUCT_QUALIFICATION = "productqualification";
	private static final String PRODUCT_QUALIFICATION_FOR_ACCOUNT = "customer/{customer}/account/{account}/productqualification";
	private static final String CONSOLIDATE_ACC = "customer/{customer}/account/{account}/consolidatedaccountprofile";
	
	public SalesCustomerInfoRestSvcAdapter() {
		super();
	}
	
	
	public SalesCustomerInfoRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
	}
	
	@Override
	public synchronized String getRootUrl() {
		return rootUrl;
	}
	
	@Override
	public String ping() {
		Object obj = get("ping", "version", String.class);
		return obj.toString();
	}

	@Override
	public GetProductQualificationAdapterResponse getProductQualification(final GetProductQualificationAdapterRequest param) {
		GetProductQualificationAdapterResponse result = null;
		final String functionName = "getProductQualification";
		LOGGER.enter(functionName,"Entering method...");

		if (!param.isRefreshCache()) {
			result = super.getFromCache(param.getCacheKey(), GetProductQualificationAdapterResponse.class);

			if (result != null) {
				return result;
			}
		}

		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();

		LOGGER.info(functionName, "Setting Query Parameters for SalesCustomerInfo Request");
		queryParams.put(QUERY_PARAM_APPLICATION_ID, SalesServiceConstants.APPLICATION_ID_DEFAULT);
		if (!StringUtils.isBlank(param.getAddressId())) {
			queryParams.put(QUERY_PARAM_ADDRESS_ID, param.getAddressId());
		}
		if (!StringUtils.isBlank(param.getProvinceCd())) {
			queryParams.put(QUERY_PARAM_PROVINCE_CD, param.getProvinceCd());
		}
		if (!StringUtils.isBlank(param.getCity())) {
			queryParams.put(QUERY_PARAM_CITY, param.getCity());
		}
		if (param.getQualByServiceId() != null) {
			queryParams.put(QUERY_PARAM_QUAL_BY_SERVICE_ID, param.getQualByServiceId().toString());
		}
		if (!StringUtils.isBlank(param.getCorrelationId())) {
			queryParams.put(QUERY_PARAM_CORRELATION, param.getCorrelationId());
		}
		if (!StringUtils.isBlank(param.getSalesRepId())) {
			queryParams.put(QUERY_PARAM_SALES_REP_ID, param.getSalesRepId());
		}
		if (!StringUtils.isBlank(param.getChannelOutletId())) {
			queryParams.put(QUERY_PARAM_CHANNEL_OUTLET_ID, param.getChannelOutletId());
		}
		
		//QC79739 - pass refreshIndcator to downstream 
		if (param.isRefreshCache()) {
			queryParams.put(QUERY_PARAM_REFRESH_INDICATOR, "true");
		} else {
			queryParams.put(QUERY_PARAM_REFRESH_INDICATOR, "false");
		}

		String resourceTemplate = PRODUCT_QUALIFICATION;
		String resource = PRODUCT_QUALIFICATION;	
		
		
		//performance fix - pass customer id and ban if present
		if (StringUtils.isNotBlank(param.getCustomerId()) && StringUtils.isNotBlank(param.getBillingAccountNumber())) {
			 resourceTemplate = PRODUCT_QUALIFICATION_FOR_ACCOUNT;
			 resource = PRODUCT_QUALIFICATION_FOR_ACCOUNT.replace("{customer}", param.getCustomerId()).replace("{account}", param.getBillingAccountNumber());
		}

		LOGGER.info(functionName, "Calling getProductQualification Operation " + resource);

		// no need to catch exception as all exceptions are logged by the parent class RestServiceAdapter
		ResponseEntity<GetProductQualificationAdapterResponse> responseEntity;
		try {
			responseEntity = get(resourceTemplate, resource, GetProductQualificationAdapterResponse.class, queryParams);
			LOGGER.info(functionName, "Response Successfully retrieved from SalesCustomerInfoRestSvcAdapter.getProductQualification. Status=" + responseEntity.getStatusCode().toString());

			result = responseEntity.getBody();

		} catch (HttpClientErrorException e) {
			//Handle Exception - for 400 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetProductQualificationAdapterResponse.class);
			if (result == null || result.getResponseStatus() == null) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		// let error 500 flow through
//		} catch (HttpServerErrorException e) {
		}
		
		if (result != null && !result.hasError()) {
			super.saveToCache(param.getCacheKey(), result);
		}
		
		return result;
	}

	@Override
	public GetConsolidatedAccountProfileAdapterResponse getConsolidateAcc(final GetConsolidatedAccountProfileAdapterRequest param) {
		GetConsolidatedAccountProfileAdapterResponse result = null;

		if (!param.isRefreshCache()) {
			result = super.getFromCache(param.getCacheKey(), GetConsolidatedAccountProfileAdapterResponse.class);

			if (result != null) {
				return result;
			}
		}
		
		final String functionName = "getConsolidateAcc";
		LOGGER.enter(functionName,"Entering method...");
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();

		LOGGER.info(functionName, "Setting Query Parameters for SalesCustomerInfo Request");
		queryParams.put(QUERY_PARAM_APPLICATION_ID, SalesServiceConstants.APPLICATION_ID_DEFAULT);
		if (!StringUtils.isBlank(param.getLangCd())) {
			queryParams.put(QUERY_PARAM_LANGUAGE_CD, param.getLangCd());
		}
		if (!StringUtils.isBlank(param.getBrandId())) {
			queryParams.put(QUERY_PARAM_BRAND_ID, param.getBrandId());
		}
		if (!StringUtils.isBlank(param.getCorrelationId())) {
			queryParams.put(QUERY_PARAM_CORRELATION, param.getCorrelationId());
		}
		if (!StringUtils.isBlank(param.getSalesRepId())) {
			queryParams.put(QUERY_PARAM_SALES_REP_ID, param.getSalesRepId());
		}
		if (!StringUtils.isBlank(param.getChannelOutletId())) {
			queryParams.put(QUERY_PARAM_CHANNEL_OUTLET_ID, param.getChannelOutletId());
		}
		
		//QC79739 - pass refreshIndcator to downstream 
		if (param.isRefreshCache()) {
			queryParams.put(QUERY_PARAM_REFRESH_INDICATOR, "true");
		} else {
			queryParams.put(QUERY_PARAM_REFRESH_INDICATOR, "false");
		}
		
		String resourcePathWithCustIdAndBan = CONSOLIDATE_ACC.replace("{customer}", param.getCustomerId()).replace("{account}", param.getBan());
		
		LOGGER.info(functionName, "Calling getConsolidatedaccountprofile Operation " + resourcePathWithCustIdAndBan);

		// no need to catch exception as all exceptions are logged by the parent class RestServiceAdapter
		ResponseEntity<GetConsolidatedAccountProfileAdapterResponse> responseEntity = get(CONSOLIDATE_ACC, resourcePathWithCustIdAndBan, GetConsolidatedAccountProfileAdapterResponse.class, queryParams);
		LOGGER.info(functionName, "Response Successfully retrieved from SalesCustomerInfoRestSvcAdapter.getConsolidatedaccountprofile. Status=" + responseEntity.getStatusCode().toString());

		result = responseEntity.getBody();
		
		if (result != null) {
			super.saveToCache(param.getCacheKey(), result);
		}
		
		return result;
	}
}
