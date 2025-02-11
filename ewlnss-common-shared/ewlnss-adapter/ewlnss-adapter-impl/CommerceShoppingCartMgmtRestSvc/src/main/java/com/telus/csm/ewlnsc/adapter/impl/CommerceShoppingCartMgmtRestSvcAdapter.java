package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.commerce.sc.domain.TelusShoppingCart;
import com.telus.csm.ewlnsc.adapter.ICommerceShoppingCartMgmtRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetCommerceShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCommerceShoppingCartAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.GzipRestServiceAdapter;
import com.telus.csm.ewlnss.adapter.domain.Message;

public class CommerceShoppingCartMgmtRestSvcAdapter  extends GzipRestServiceAdapter  implements ICommerceShoppingCartMgmtRestSvcAdapter {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(CommerceShoppingCartMgmtRestSvcAdapter.class);
	
	private static final String GET_SHOPPING_CART_RESOURCE_PATH = "";
	
	private static String ROOT_URL = ApplicationProperties.getConfigString("${connections/webServices/commerceShoppingCartMgmtRestSvc/endpoint}");
	private static final String KONG_ENV = ApplicationProperties.getConfigString("${connections/webServices/commerceShoppingCartMgmtRestSvc/kongEnv}");
	
	public CommerceShoppingCartMgmtRestSvcAdapter() {
		super();
	}
	
	
	public CommerceShoppingCartMgmtRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
	}


	@Override
	public GetCommerceShoppingCartAdapterResponse getCommerceShoppingCart( GetCommerceShoppingCartAdapterRequest param ) {

		final String functionName = "getCommerceShoppingCart";
		
		logger.enter(functionName);
		
		GetCommerceShoppingCartAdapterResponse result = new GetCommerceShoppingCartAdapterResponse();
		
		try {
			
			Map<String, String> headerParams = getHeaderParams( param );
			
			String shoppingCartPath = String.format("/shoppingCart/%s", param.getShoppingCartId() );

			ResponseEntity<TelusShoppingCart> responseEntity = get(GET_SHOPPING_CART_RESOURCE_PATH, shoppingCartPath, TelusShoppingCart.class, null, headerParams);

			String httpStatusCode = responseEntity.getStatusCode().toString();
			
			logger.info(functionName, "Response retrieved from commerce-shoppingcart-mgmt. Status=" + httpStatusCode);

			result.setShoppingCart( responseEntity.getBody() );
			
		
		} catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetCommerceShoppingCartAdapterResponse.class);
			if (result == null || result.getMessageList() == null || result.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		
		} catch (HttpStatusCodeException httpStatusCodeException) { //This exception catches all HTTP status other than 200		
	 
			Error dowstreamError = JsonUtil.parseJsonToObject(httpStatusCodeException.getResponseBodyAsString(), Error.class);
			
			if (dowstreamError == null){
				throw httpStatusCodeException;
			} else {

				List<Message> messageList = this.transformMessage( dowstreamError, httpStatusCodeException );
				result.setMessageList(messageList);
			}
			
		} finally {
			
			logger.exit(functionName);
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
	
	void setQueryParams(GetCommerceShoppingCartAdapterRequest param, Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		logger.enter(functionName);
		pathParams.put("refreshCacheInd", ""+param.isRefreshCacheInd()); 
		
		logger.exit(functionName);
	}
	
	private  Map<String, String>  getHeaderParams(GetCommerceShoppingCartAdapterRequest param) {
		
		Map<String, String> headerParams = null;
		if (!StringUtils.isBlank(param.getSalesTransactionId())) {
			headerParams = new HashMap<String, String> ();

			headerParams.put("transactionId", param.getSalesTransactionId());
		}
		return headerParams;
	}
	
	@Override
	public String getRootUrl() {
		return ROOT_URL;
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

