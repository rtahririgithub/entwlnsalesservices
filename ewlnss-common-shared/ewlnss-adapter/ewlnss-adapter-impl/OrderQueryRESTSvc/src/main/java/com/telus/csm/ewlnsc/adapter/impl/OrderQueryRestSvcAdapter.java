package com.telus.csm.ewlnsc.adapter.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import com.telus.csm.ewlnsc.adapter.IOrderQueryRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrderSummaryByOrderIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetOrdersByCustomerIdAndStatusAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.oqs.domain.GetProductsByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.oqs.domain.Product;
import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOrder;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.FeatureAgentUID;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

/**
 * 
 * @author Jose.Mena
 *
 */
public class OrderQueryRestSvcAdapter extends RestServiceAdapter implements IOrderQueryRestSvcAdapter {

	private static final String QUERY_PARAM_INCLUDE_DETAILS = "includedetails";
	private static final String QUERY_PARAM_INSTANCE_ID_LIST = "instanceidlist";
	private static final String QUERY_PARAM_PRICE_OFFERING = "priceoffering";
	
	private static final String COMMA = ",";

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(OrderQueryRestSvcAdapter.class);

	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/orderQueryRestSvc/wsdlUrl}");

	private static final String RESOURCE_PATH = "customer/{customerid}/products";
	
	private static final String RESOURCE_PATH_RETRIEVE_ORDERS = "order/{orderId}";
	
	private static final String RESOURCE_PATH_SEARCH_ORDERS = "customer/{customerId}/orders";

	public OrderQueryRestSvcAdapter() {
		super();
	}

	public OrderQueryRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver) {
		super(adapterFeatureDriver);
		addFeature(new FeatureAgentUID(adapterFeatureDriver));
	}

	@Override
	public synchronized String getRootUrl() {
		return rootUrl;
	}
	
	@Override
	public String ping() {
		
		Object obj = get("ping", "order/999999", String.class);
		return obj.toString();
	}

	private void setQueryParams(GetProductsByCustomerIdAdapterRequest param, Map<String, String> pathParams) {
		String functionName = "setQueryParams";
		LOGGER.enter(functionName);
		pathParams.put(QUERY_PARAM_INCLUDE_DETAILS, Boolean.toString(param.isIncludedetails()));
		if (param.isIncludedetails()) {
			pathParams.put(QUERY_PARAM_INSTANCE_ID_LIST, getCommaSeparatedStrFromList(param.getInstanceidlist()));
			// QC79124 ESS GWP offer issue for OMNI, add new parameter
			pathParams.put("assignedonly", Boolean.toString(Boolean.TRUE));
		}
		LOGGER.exit(functionName);
	}

	private String getCommaSeparatedStrFromList(List<String> instanceidlist) {
		StringBuilder result = new StringBuilder();
		for (String element : instanceidlist){
			result.append(element).append(",");
		}
		if (result.length() > 0){
			result.replace(result.length()-1, result.length(), "");
		}
		return result.toString();
	}

	@Override
	public GetProductsByCustomerIdAdapterResponse getProductsByCustomerId(final GetProductsByCustomerIdAdapterRequest param) {
		final String functionName = "OrderQueryRestSvcAdapter->getProductsByCustomerId";
		LOGGER.enter(functionName);
		String resourcePathWithCustomerId = RESOURCE_PATH.replace("{customerid}", param.getCustomerid());
				
		// Setting queryParameters
		final Map<String, String> queryParams = new HashMap<String, String>();
		setQueryParams(param, queryParams);
		
		GetProductsByCustomerIdAdapterResponse result = super.getFromCache(param.getCacheKey(), GetProductsByCustomerIdAdapterResponse.class);

		if (result != null) {
			return result;
		} else {
			result = new GetProductsByCustomerIdAdapterResponse();
		}
		
		try {
			LOGGER.info(functionName, "Calling getProductsByCustomerId Operation");
			final ResponseEntity<Product[]> resp = get(RESOURCE_PATH, resourcePathWithCustomerId, Product[].class, queryParams);

			LOGGER.info(functionName, "Response Successfully retrieved from OrderQuearyRest.getProductsByCustomerId. Status=" + resp.getStatusCode().toString());
			final Product[] products = resp.getBody();
			
			result.setProducts(Arrays.asList(products));
			super.saveToCache(param.getCacheKey(), result);
		} catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetProductsByCustomerIdAdapterResponse.class);
			if (result == null || result.getMessageList() == null || result.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		}
		
		LOGGER.exit(functionName);
		return result;
	}
	
	@Override
	public GetOrderSummaryByOrderIdAdapterResponse getOrderSummaryByOrderId(final GetOrderSummaryByOrderIdAdapterRequest param) {
		GetOrderSummaryByOrderIdAdapterResponse result;
		result = super.getFromCache(param.getKey(), GetOrderSummaryByOrderIdAdapterResponse.class);
		if (result != null) {
			return result;
		} else {
			result = new GetOrderSummaryByOrderIdAdapterResponse();
		}

		final String orderId = param.getOrderId();
		final boolean isPriceOffering = param.isPriceOffering();
		final String resourceWithOrderId = RESOURCE_PATH_RETRIEVE_ORDERS.replace("{orderId}", orderId);
		
		//Query Parameters
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(QUERY_PARAM_PRICE_OFFERING, Boolean.toString(isPriceOffering));
		
		final ResponseEntity<ProductOrder> responseEntity = get(RESOURCE_PATH_RETRIEVE_ORDERS, resourceWithOrderId, ProductOrder.class, queryParams);
		final ProductOrder productOrder = responseEntity.getBody();
		
		if (productOrder != null) {
			result.setProductOrderDetail(productOrder);
			super.saveToCache(param.getKey(), result);
		}
		
		return result;
	}
	
	@Override
	public ProductOrder getOrderSummaryByOrderId(final String orderId, final String txnId, final String agentUid) {
		final String key = "getOrderSummaryByOrderId_txnId_" + txnId + "_orderId_" + orderId;
		final ProductOrder result = super.getFromCache(key, ProductOrder.class);
		if (result != null) {
			return result;
		}
		final String resourceWithOrderId = RESOURCE_PATH_RETRIEVE_ORDERS.replace("{orderId}", orderId);
		
		//Query Parameters
		final Map<String, String> queryParams = new HashMap<String, String>();
		queryParams.put(QUERY_PARAM_PRICE_OFFERING, "true");
		
		final ResponseEntity<ProductOrder> responseEntity = get(RESOURCE_PATH_RETRIEVE_ORDERS, resourceWithOrderId, ProductOrder.class, queryParams);
		
		if (responseEntity.getBody() != null) {
			super.saveToCache(key, responseEntity.getBody());
		}
		
		return responseEntity.getBody();
	}
	
	@Override
	public List<ProductOrder> searchOrdersByCustomerId(long customerId, List<String> statusList, String agentUid, String txnId) {
		String statuses = "";
		for (String status : statusList) {
			statuses += status + COMMA;
		}
		statuses = statuses.substring(0, statuses.length() - COMMA.length());
		final String key = "searchOrdersByCustomerId_txnId_" + txnId + "_customerId_" + customerId + "_statuses_" + statuses;
		
		GetOrdersByCustomerIdAndStatusAdapterResponse result = super.getFromCache(key, GetOrdersByCustomerIdAndStatusAdapterResponse.class);
		if (result != null) {
			return result.getProductOrderList();
		} else {
			result = new GetOrdersByCustomerIdAndStatusAdapterResponse();
		}
		
		final String resourceWithCustomerId = RESOURCE_PATH_SEARCH_ORDERS.replace("{customerId}", String.valueOf(customerId));
		final Map<String, String> queryParams = new HashMap<String, String>();		
		queryParams.put("status", statuses);
		final ResponseEntity<ProductOrder[]> responseEntity = get(RESOURCE_PATH_SEARCH_ORDERS, resourceWithCustomerId, ProductOrder[].class, queryParams);
		result.setProductOrderList(Arrays.asList(responseEntity.getBody()));
		
		super.saveToCache(key, result);
		
		return result.getProductOrderList();
	}

	@Override
	public String getApplicationName(){
		return "APP_CHSALESSVC";
	}
	
	@Override
	protected synchronized String getApplicationPwd() {
		return "soaorgid";
	}

}
