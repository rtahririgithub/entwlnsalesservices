package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.cpq.rest.domain.Characteristic;
import com.telus.csm.cpq.rest.domain.PromotionQualification;
import com.telus.csm.cpq.rest.domain.RelatedPartyRef;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.ewlnsc.adapter.IPromotionQualificationRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetPromotionAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetPromotionAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

public class PromotionQualificationRestSvcAdapter  extends RestServiceAdapter  implements IPromotionQualificationRestSvcAdapter {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(PromotionQualificationRestSvcAdapter.class);
	
	private static final String GET_PROMOTION_RESOURCE_PATH = "";
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/promotionQualificationRestSvc/wsdlUrl}");
	
	public PromotionQualificationRestSvcAdapter() {
		super();
	}
	
	
	public PromotionQualificationRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
	}


	@Override
	public GetPromotionAdapterResponse getPromotion(GetPromotionAdapterRequest param) {
		final String functionName = "getPromotion";
		logger.enter(functionName,"Entering method...");
		GetPromotionAdapterResponse result = new GetPromotionAdapterResponse();
		PromotionQualification promotionQualification = null;
		
		//try to read from cache first 
//		String cacheKey = new StringBuilder()
//				.append("PromotionQualificationRestSvcAdapter").append(".getPromotion").append("_")
//				.append(param.getCacheKey())
//				.toString();
//		
//		if (!param.isRefreshCache()) {
//			result = super.getFromCache(cacheKey, GetPromotionAdapterResponse.class);
//
//			if (result != null) {
//				return result;
//			} else {
//				result = new GetPromotionAdapterResponse();
//			}
//		}
		

		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();
		logger.info(functionName, "Setting Query Parameters for Request ");
		setQueryParams(param,queryParams);
		Map<String, String> headerParams = new HashMap<String, String>();
		logger.info(functionName, "Setting Header Parameters for Request ");
		setHeaderParams(param,headerParams);
		try{
			logger.info(functionName, "Calling promotionQualification Operation");
			ResponseEntity<PromotionQualification> responseEntity = post(GET_PROMOTION_RESOURCE_PATH, GET_PROMOTION_RESOURCE_PATH, param.getPromotionQualification(), PromotionQualification.class,queryParams,headerParams);

			String httpStatusCode = responseEntity.getStatusCode().toString();
			 
			logger.info(functionName, "Response retrieved from promotion-qualification. Status=" + httpStatusCode);
			promotionQualification = responseEntity.getBody();
			result.setPromotionQualification(promotionQualification );
			//save good response to cache
//			super.saveToCache(cacheKey, result);
		
		} catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetPromotionAdapterResponse.class);
			if (result == null || result.getMessageList() == null || result.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		
		} catch (HttpStatusCodeException httpStatusCodeException) { //This exception catches all HTTP status other than 200		
	 
			if ( HttpStatus.NOT_FOUND.equals(httpStatusCodeException.getStatusCode())	) { //this indicates no promotion found for the search criteria
	
				logger.info( functionName, "search promotion, no promotion found due to status=" + httpStatusCodeException.getStatusCode() + ", exception is ignored." );
				
			} else {
				Error dowstreamError = JsonUtil.parseJsonToObject(httpStatusCodeException.getResponseBodyAsString(), Error.class);
				
				if (dowstreamError == null){
					throw httpStatusCodeException;
				} else {

					List<Message> messageList = this.transformMessage( dowstreamError, httpStatusCodeException );
					result.setMessageList(messageList);
				}
			}
		}
		
		return result;
	}

	private List<Message> transformMessage(Error error, Exception exception ){
		List<Message> messageList = new ArrayList<Message>();
		Message messageDO = new Message();

		messageDO.setMessageType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
		messageDO.setMessage(error.getMessage());

		messageList.add(messageDO);
		return messageList;
	}
	private void setQueryParams(GetPromotionAdapterRequest param, Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		logger.enter(functionName);
		pathParams.put("refreshCacheInd", ""+param.isRefreshCacheInd()); 
		pathParams.put("originatorApplicationId", param.getOriginatorApplicationId());
		pathParams.put("consolidatedCustomerProfileInd", ""+param.isConsolidatedCustomerProfileInd());
		pathParams.put("allowProductTierUpgradeOnlyInd", ""+param.isAllowProductTierUpgradeOnlyInd());
		
		logger.exit(functionName);
	}
	
	private void setHeaderParams(GetPromotionAdapterRequest param, Map<String, String> headerParams) {
		String functionName = "setHeaderParams";
		logger.enter(functionName);
		if (!StringUtils.isBlank(param.getSalesTransactionId())) {
			headerParams.put("transactionId", param.getSalesTransactionId());
		}
		logger.exit(functionName);
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

}

