package com.telus.csm.ewlnsc.adapter.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ewlnsc.adapter.IFifaShoppingCartMgmtRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFifaShoppingCartAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.GzipRestServiceAdapter;
import com.telus.csm.ewlnss.adapter.domain.Message;
import com.telus.csm.fifa.sc.domain.ShoppingCart;

public class FifaShoppingCartMgmtRestSvcAdapter  extends GzipRestServiceAdapter  implements IFifaShoppingCartMgmtRestSvcAdapter {
	
	private static final LoggerUtil logger = LoggerUtil.getLogger(FifaShoppingCartMgmtRestSvcAdapter.class);
	
	private static final String GET_SHOPPING_CART_RESOURCE_PATH = "";
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/fifaShoppingCartMgmtRestSvc/endpoint}");
	private static String KONG_ENV = ApplicationProperties.getConfigString("${connections/webServices/fifaShoppingCartMgmtRestSvc/kongEnv}");

	
	public FifaShoppingCartMgmtRestSvcAdapter() {
		super();
	}
	
	
	public FifaShoppingCartMgmtRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
	}


	@Override
	public GetFifaShoppingCartAdapterResponse getFifaShoppingCart( GetFifaShoppingCartAdapterRequest param ) {

		final String functionName = "getFifaShoppingCart";
		
		logger.enter(functionName);
		
		GetFifaShoppingCartAdapterResponse result = new GetFifaShoppingCartAdapterResponse();
		
		try {
			
			String shoppingCartPath = String.format("/%s", param.getShoppingCartId() );

			ResponseEntity<ShoppingCart> responseEntity = get(GET_SHOPPING_CART_RESOURCE_PATH, shoppingCartPath, ShoppingCart.class, null, null);

			String httpStatusCode = responseEntity.getStatusCode().toString();
			
			logger.info(functionName, "Response retrieved from fifa-shoppingcart-mgmt. Status=" + httpStatusCode);

			result.setShoppingCart( responseEntity.getBody() );
			
		
		} catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetFifaShoppingCartAdapterResponse.class);
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
	
	void setQueryParams(GetFifaShoppingCartAdapterRequest param, Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		logger.enter(functionName);
		pathParams.put("refreshCacheInd", ""+param.isRefreshCacheInd()); 
		
		logger.exit(functionName);
	}
	
	void setHeaderParams(GetFifaShoppingCartAdapterRequest param, Map<String, String> headerParams) {
		String functionName = "setHeaderParams";
		logger.enter(functionName);
		if (!StringUtils.isBlank(param.getSalesTransactionId())) {
			headerParams.put("transactionId", param.getSalesTransactionId());
		}
		logger.exit(functionName);
	}
	
	@Override
	public String getRootUrl() {
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

