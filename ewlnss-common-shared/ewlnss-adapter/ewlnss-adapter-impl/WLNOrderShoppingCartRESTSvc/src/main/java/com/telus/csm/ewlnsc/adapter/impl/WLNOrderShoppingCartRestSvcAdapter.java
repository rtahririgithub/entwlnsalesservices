package com.telus.csm.ewlnsc.adapter.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import com.telus.csm.ewlnsc.adapter.IWLNOrderShoppingCartRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.CreateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.SubmitOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateOrderAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.UpdateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterRequest;
import com.telus.csm.ewlnsc.adapter.woscs.domain.ValidateProductConfigAdapterResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.FeatureAgentUID;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

public class WLNOrderShoppingCartRestSvcAdapter extends RestServiceAdapter implements IWLNOrderShoppingCartRestSvcAdapter {
	
	private static final String QUERY_PARAM_CUSTOMER_ID = "customerid";
	private static final String QUERY_PARAM_CID = "cid";
	private static final String QUERY_PARAM_RETURN_ORDER_DETAILS = "returnorderdetails";
	private static final String QUERY_PARAM_PREVALIDATION = "prevalidation";
	private static final String QUERY_PARAM_ORDERCREATION = "ordercreation";

	private static final String QUERY_PARAM_ORDER_ID = "orderid";
	private static final String QUERY_PARAM_MODE = "mode";

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(WLNOrderShoppingCartRestSvcAdapter.class);
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/orderShoppingCartRestSvc/wsdlUrl}");

	private static final String CREATE_ORDER = "shoppingcartorder"; // POST
	private static final String SUBMIT_ORDER = "shoppingcartorder/{orderid}/submitorder"; // POST
	private static final String UPDATE_ORDER = "shoppingcartorder"; // POST
	private static final String VALIDATE_PROD = "productconfigvalidation"; // GET and UpdateProdConf is a PUT
	private static final String VALIDATE_PRODUCT_RESOURCE_PATH = "shoppingcartorder/{orderId}/" + VALIDATE_PROD;
	private static final String CREATE_ORDER_RESOURCE_PATH = "customer/{customerid}/"+CREATE_ORDER;
	private static final String UPDATE_ORDER_RESOURCE_PATH = "shoppingcartorder/{orderid}";
	
	public WLNOrderShoppingCartRestSvcAdapter() {
		super();
	}
	
	
	public WLNOrderShoppingCartRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
		addFeature(new FeatureAgentUID(adapterFeatureDriver)); 
	}
	
	@Override
	public synchronized String getRootUrl() {
		return rootUrl;
	}
	
	@Override
	public String ping() {

		try {
			Object obj = get("ping", "shoppingcartorder/999999/productconfigvalidation", String.class);
			return obj.toString();
		} catch (HttpServerErrorException e) {
			String responseBody = e.getResponseBodyAsString();
			if (responseBody != null && responseBody.contains("messageList")) {
				return responseBody;
			}
			throw e;
		}
	}

	@Override
	public CreateOrderAdapterResponse createOrder(final CreateOrderAdapterRequest param) {
		CreateOrderAdapterResponse result = null;
		final String functionName = "createOrder";
		LOGGER.enter(functionName,"Entering method...");
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();
		LOGGER.info(functionName, "Setting Query Parameters for OrderShoppingCart Request");
		setQueryParams(param,queryParams);
		
		try { 
			LOGGER.info(functionName, "Calling createOrder Operation");
			String resourcePathWithCustomerId = CREATE_ORDER_RESOURCE_PATH.replace("{customerid}", param.getCustomerid());
			LOGGER.info(functionName, resourcePathWithCustomerId);
			ResponseEntity<CreateOrderAdapterResponse> responseEntity = post(CREATE_ORDER_RESOURCE_PATH, resourcePathWithCustomerId, JsonUtil.getJsonFromObjectNonNUll(param.getBody()), CreateOrderAdapterResponse.class, queryParams);

			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderShoppingCartRest.createOrder. Status=" + responseEntity.getStatusCode().toString());
			result = responseEntity.getBody();

			// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
			// let error 400 flow through
//			} catch (HttpClientErrorException e) {
			} catch (HttpServerErrorException e) { //This exception catch the 503 error		
				//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
				result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), CreateOrderAdapterResponse.class);
				if (result == null) {
					throw e;
				}
				result.setStatus(e.getStatusCode().toString());
				if (result.getMessageList() == null || result.getMessageList().isEmpty()) {
					//The downstream doesn't return the expected error message structure - so need to rethrow the exception
					throw e;
				}
			}
		return result;
	}
	@Override
	public UpdateOrderAdapterResponse updateOrder(final UpdateOrderAdapterRequest param) {
		UpdateOrderAdapterResponse result = null;
		final String functionName = "updateOrder";
		LOGGER.enter(functionName,"Entering method...");
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();
		LOGGER.info(functionName, "Setting Query Parameters for OrderShoppingCart Request");
		//setQueryParams(param,queryParams);
		
		try { 
			LOGGER.info(functionName, "Calling createOrder Operation");
			String resourcePathWithCustomerId = UPDATE_ORDER_RESOURCE_PATH.replace("{orderid}", param.getOrderId());
			LOGGER.info(functionName, resourcePathWithCustomerId);
			ResponseEntity<UpdateOrderAdapterResponse> responseEntity = put(UPDATE_ORDER_RESOURCE_PATH, resourcePathWithCustomerId, JsonUtil.getJsonFromObjectNonNUll(param.getBody()), queryParams);

			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderShoppingCartRest.updateOrder. Status=" + responseEntity.getStatusCode().toString());
			result = responseEntity.getBody();

			// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
			// let error 400 flow through
//			} catch (HttpClientErrorException e) {
			} catch (HttpServerErrorException e) { //This exception catch the 503 error		
				//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
				result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), UpdateOrderAdapterResponse.class);
				if (result == null) {
					throw e;
				}
				result.setStatus(e.getStatusCode().toString());
				if (result.getMessageList() == null || result.getMessageList().isEmpty()) {
					//The downstream doesn't return the expected error message structure - so need to rethrow the exception
					throw e;
				}
			}
		return result;
	}
	private void setQueryParams(UpdateOrderAdapterRequest param, Map<String, String> queryParams) {
		String functionName = "setQueryParams";
		LOGGER.enter(functionName);
        if (!StringUtils.isBlank(param.getOrderId())) {
        	queryParams.put(QUERY_PARAM_ORDER_ID, param.getOrderId());
		}
		if (!StringUtils.isBlank(param.getCid())) {
			queryParams.put(QUERY_PARAM_CID, param.getCid());
		}
		if (!StringUtils.isBlank(param.getOrdercreation())) {
			queryParams.put(QUERY_PARAM_ORDERCREATION, param.getOrdercreation());
		}
		if (!StringUtils.isBlank(param.getPrevalidation())) {
			queryParams.put(QUERY_PARAM_PREVALIDATION, param.getPrevalidation());
		}
		if (!StringUtils.isBlank(param.getReturnorderdetails())) {
			queryParams.put(QUERY_PARAM_RETURN_ORDER_DETAILS, param.getReturnorderdetails());
		}
		LOGGER.exit(functionName);
	
	}


	@Override
	public SubmitOrderAdapterResponse submitOrder(final SubmitOrderAdapterRequest param) {
		SubmitOrderAdapterResponse result = null;
		final String functionName = "submitOrder";
		LOGGER.enter(functionName,"Entering method...");
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();
		LOGGER.info(functionName, "Setting Query Parameters for OrderShoppingCart Request");
		setQueryParams(param, queryParams);

		String resourcePathWithOrderid = SUBMIT_ORDER.replace("{orderid}", param.getOrderid());
		try { 
			LOGGER.info(functionName, "Calling submit Order Operation");
			ResponseEntity<SubmitOrderAdapterResponse> responseEntity = post(SUBMIT_ORDER, resourcePathWithOrderid, null, SubmitOrderAdapterResponse.class, queryParams);

			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderShoppingCartRest.submitOrder. Status=" + responseEntity.getStatusCode().toString());
			result = responseEntity.getBody();
			
			// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
			// let error 400 flow through
//			} catch (HttpClientErrorException e) {
			} catch (HttpServerErrorException e) { //This exception catch the 503 error		
				//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
				result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), SubmitOrderAdapterResponse.class);
				if (result == null) {
					throw e;
				}
				result.setStatus(e.getStatusCode().toString());
				if (result.getMessageList() == null || result.getMessageList().isEmpty()) {
					//The downstream doesn't return the expected error message structure - so need to rethrow the exception
					throw e;
				}
			}
		return result;
	}
	
	/********************************************/
	/* validateProductConfig                    */
	/********************************************/
	@Override
	public ValidateProductConfigAdapterResponse validateProductConfig(final ValidateProductConfigAdapterRequest param) {
		
		ValidateProductConfigAdapterResponse result = null;
		
		final String functionName = "validateProductConfig";
		LOGGER.enter(functionName, "Entering method...");
		
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams  = new HashMap<String, String>(); 
		
		/**
		 * setup query parameters
		 */
		setQueryParams(param, queryParams);
		
		try { 
			LOGGER.info(functionName, "Calling productconfigvalidation Operation");
			
			String resourcePathWithOrderId = VALIDATE_PRODUCT_RESOURCE_PATH.replace("{orderId}", Integer.toString(param.getOrderId()));
			
			ResponseEntity<ValidateProductConfigAdapterResponse> responseEntity = get(VALIDATE_PRODUCT_RESOURCE_PATH, resourcePathWithOrderId, ValidateProductConfigAdapterResponse.class, queryParams);
		
			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderShoppingCartRest.validateProductConfig. Status=" + responseEntity.getStatusCode().toString());
			result = responseEntity.getBody();

		// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
		// let error 400 flow through
//		} catch (HttpClientErrorException e) {
		} catch (HttpServerErrorException e) { //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), ValidateProductConfigAdapterResponse.class);
			if (result == null) {
				throw e;
			}
			result.setStatus(e.getStatusCode().toString());			
			//The downstream doesn't return a Error Code, so it is necessary to append the Http Exception code
			if (result.getMessageList() == null || result.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		}
		return result;
		 
	}
	
	
	private void setQueryParams(final CreateOrderAdapterRequest param, final Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		LOGGER.enter(functionName);
/*		if (!StringUtils.isBlank(param.getCustomerid())) {
			pathParams.put(QUERY_PARAM_CUSTOMER_ID, param.getCustomerid());
		}*/
		if (!StringUtils.isBlank(param.getCid())) {
			pathParams.put(QUERY_PARAM_CID, param.getCid());
		}
		if (!StringUtils.isBlank(param.getOrdercreation())) {
			pathParams.put(QUERY_PARAM_ORDERCREATION, param.getOrdercreation());
		}
		if (!StringUtils.isBlank(param.getPrevalidation())) {
			pathParams.put(QUERY_PARAM_PREVALIDATION, param.getPrevalidation());
		}
		if (!StringUtils.isBlank(param.getReturnorderdetails())) {
			pathParams.put(QUERY_PARAM_RETURN_ORDER_DETAILS, param.getReturnorderdetails());
		}
		LOGGER.exit(functionName);
	}

	private void setQueryParams(final SubmitOrderAdapterRequest param, final Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		LOGGER.enter(functionName);
		if (!StringUtils.isBlank(param.getMode())) {
			pathParams.put(QUERY_PARAM_MODE, param.getMode());
		}
		LOGGER.exit(functionName);
	}
	
	/***********************************************************/
	/* setQueryParams for ValidateProductConfigAdapterRequest  */
	/***********************************************************/
	private void setQueryParams(final ValidateProductConfigAdapterRequest param, final Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		LOGGER.enter(functionName);
		
		 
//		pathParams.put(QUERY_PARAM_ORDER_ID, new Integer(param.getOrderId()).toString()); 
		
		if (param.isReturnOrderDetails())
			pathParams.put(QUERY_PARAM_RETURN_ORDER_DETAILS, "true");
		else
			pathParams.put(QUERY_PARAM_RETURN_ORDER_DETAILS, "false");
		 
		LOGGER.exit(functionName);
	}

}
