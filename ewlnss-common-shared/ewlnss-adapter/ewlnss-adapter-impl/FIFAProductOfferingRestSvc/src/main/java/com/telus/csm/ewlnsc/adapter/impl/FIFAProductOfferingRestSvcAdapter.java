package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ewlnsc.adapter.IFIFAProductOfferingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.GzipRestServiceAdapter;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.ffpo.rest.domain.ProductOfferingQualification;

public class FIFAProductOfferingRestSvcAdapter  extends GzipRestServiceAdapter  implements IFIFAProductOfferingRestSvcAdapter {
	


	private static final LoggerUtil logger = LoggerUtil.getLogger(FIFAProductOfferingRestSvcAdapter.class);
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/fifaProductOfferingQualificationRestSvc/wsdlUrl}");
	private static final String KONG_ENV = ApplicationProperties.getConfigString("${connections/webServices/fifaProductOfferingQualificationRestSvc/kongEnv}");
	
	public FIFAProductOfferingRestSvcAdapter() {
		super();
	}
	
	
	public FIFAProductOfferingRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
	}


	@Override
	protected boolean isOAuth2TokenRequird() {
		//Even though we don't call Fifa product offering API directly, but the offerBridgeAPI insists us to 
		//acquire the token and share with it.
		return true;
	}	
	
	@Override
	public GetFIFAPoductOfferingAdapterResponse getProductOfferingQualification( GetFIFAPoductOfferingAdapterRequest param) {
		final String functionName = "getProductOfferingQualification";

		logger.enter(functionName,"Entering method...");
		
		
		GetFIFAPoductOfferingAdapterResponse result = new GetFIFAPoductOfferingAdapterResponse();

		
		
		Map<String, String> headerParams = getHeaderParams( param );

		try{
			
			logger.info(functionName, String.format( "Calling ProductOfferingQualification: offerId=%s, category[id=%s, type[%s / %s: %s]",
					param.getOfferId(), param.getCategoryId(), param.getProductType() ,param.getCategoryType() , param.getCategoryName() ) );
			
			ResponseEntity<ProductOfferingQualification> responseEntity = post("", "", param.getProductOfferingQualification(), ProductOfferingQualification.class, null ,headerParams);

			String httpStatusCode = responseEntity.getStatusCode().toString();
			 
			logger.info(functionName, "Response retrieved from promotion-qualification. Status=" + httpStatusCode);
			ProductOfferingQualification  productOfferingQualificationResponse = responseEntity.getBody();
			result.setProductOfferingQualification(productOfferingQualificationResponse);
			
			logger.enter(functionName,"Exiting method...");
			
		} catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetFIFAPoductOfferingAdapterResponse.class);
			if (result == null || result.getMessageList() == null || result.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		
		} catch (HttpStatusCodeException httpStatusCodeException) { //This exception catches all HTTP status other than 200		
	 
			if ( HttpStatus.NOT_FOUND.equals(httpStatusCodeException.getStatusCode())	) { //this indicates no promotion found for the search criteria
	
				logger.info( functionName, "search productOffering, no productOffering found due to status=" + httpStatusCodeException.getStatusCode() + ", exception is ignored." );
				
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
		
		result.setRequest(param);
		
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
	
	
	private  Map<String, String>  getHeaderParams(GetFIFAPoductOfferingAdapterRequest param) {
		
		Map<String, String> headerParams = null;
		if (!StringUtils.isBlank(param.getSalesTransactionId())) {
			headerParams = new HashMap<String, String> ();

			//TODO confirm the header from Commerce OQ team.
			headerParams.put("transactionId", param.getSalesTransactionId());
		}
		return headerParams;
	}
	
	@Override
	public synchronized String getRootUrl() {
		return rootUrl;
	}
	
	@Override
	protected String getKongEnv() {
		return KONG_ENV;
	}
	
	@Override
	public String ping() {
		Object obj = get("ping", "version", String.class);
		return obj.toString();
	}

}

