package com.telus.csm.ewlnsc.adapter.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.HttpStatusCodeException;

import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoRequest;
import com.telus.csm.ewlnsc.adapter.IWLNOrderChargingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateInvoiceAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteRequest;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetDepositInfoResponse;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.FeatureAgentUID;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

public class WLNOrderChargingRestSvcAdapter extends RestServiceAdapter implements IWLNOrderChargingRestSvcAdapter {
	
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(WLNOrderChargingRestSvcAdapter.class);
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/wlnOrderChargingRestSvc/wsdlUrl}");

	private static final String CREATE_INVOICE_RESOURCE = "shoppingcartorder/{orderid}/invoice"; // POST
	private static final String GET_DEPOSIT_RESOURCE = "shoppingcartorder/{orderid}/depositinfo"; 
	private static final String QUOTE = "quote"; // GET quote
	private static final String QUOTE_RESOURCE_PATH = "shoppingcartorder/{orderid}/" + QUOTE;

	public WLNOrderChargingRestSvcAdapter() {
		super();
	}
	
	public WLNOrderChargingRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
		super(adapterFeatureDriver);
		addFeature(new FeatureAgentUID(adapterFeatureDriver));
	}
	
	@Override
	public synchronized String getRootUrl() {
		return rootUrl;
	}
	
	@Override
	public String ping() {
		Object obj = get("ping", "demo", String.class, null);
		return (String) obj;
	}

	@Override
	public CreateInvoiceAdapterResponse createInvoice(CreateInvoiceAdapterRequest param) {
		final String functionName = "WLNOrderChargingRestSvcAdapter->createInvoice";
		LOGGER.enter(functionName);
		
		CreateInvoiceAdapterResponse createInvoiceAdapterResponse = null;
		try {
			String requestJsonStr = JsonUtil.getJsonFromObject(param);
			String createInvoiceResourcePathWithOrderId = CREATE_INVOICE_RESOURCE.replace("{orderid}", param.getOrderid());
			ResponseEntity<String> responseEntity = post(CREATE_INVOICE_RESOURCE, createInvoiceResourcePathWithOrderId, requestJsonStr, String.class);

			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderChargingRest.createInvoice. Status=" + responseEntity.getStatusCode().toString());
			createInvoiceAdapterResponse = new CreateInvoiceAdapterResponse();
			createInvoiceAdapterResponse.setInvoiceNo(responseEntity.getBody());

		// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
		// let error 400 flow through
//		} catch (HttpClientErrorException e) {
		}catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			createInvoiceAdapterResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), CreateInvoiceAdapterResponse.class);
			//The downstream doesn't return a Error Code, so it is necessary to append the Http Exception code
			if (createInvoiceAdapterResponse == null || createInvoiceAdapterResponse.getMessageList() == null || createInvoiceAdapterResponse.getMessageList().isEmpty()) {
				throw e;
			}
		}
		return createInvoiceAdapterResponse;
	}

	@Override
	public QuoteResponse getQuote(final QuoteRequest param) {
		
		QuoteResponse result = null;
		
		final String functionName = "getQuote";
		LOGGER.enter(functionName, "Entering method...");
		
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams  = new HashMap<String, String>(); 
		
		try { 
			LOGGER.info(functionName, "Calling getQuote Operation for orderId=" + param.getOrderId());
			
			String resourcePathWithOrderId = QUOTE_RESOURCE_PATH.replace("{orderid}", param.getOrderId());
			
			ResponseEntity<QuoteResponse> responseEntity = get(QUOTE_RESOURCE_PATH, resourcePathWithOrderId, QuoteResponse.class, queryParams);
		
			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderChargingRest.getQuote. Status=" + responseEntity.getStatusCode().toString());
			result = responseEntity.getBody();

		} catch (HttpStatusCodeException e) { //This exception catch the 503 error
			result = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), QuoteResponse.class);
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
	
	@Override
	public GetDepositInfoResponse getDepositInfo(GetDepositInfoRequest param) {
		final String functionName = "WLNOrderChargingRestSvcAdapter->getDepositInfo";
		LOGGER.enter(functionName);
		
		GetDepositInfoResponse getDepositInfoResponse = null;
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams  = new HashMap<String, String>();
		try {
			LOGGER.info(functionName, "Calling deposit Operation for orderId=" + param.getOrderId());
			
			String uri = GET_DEPOSIT_RESOURCE.replace("{orderid}", param.getOrderId());
			
			ResponseEntity<GetDepositInfoResponse> responseEntity = get(GET_DEPOSIT_RESOURCE, uri, GetDepositInfoResponse.class, queryParams);
		
			LOGGER.info(functionName, "Response Successfully retrieved from WLNOrderChargingRest.getQuote. Status=" + responseEntity.getStatusCode().toString());
			getDepositInfoResponse = responseEntity.getBody();

		// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
		// let error 400 flow through
//		} catch (HttpClientErrorException e) {
		}catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			getDepositInfoResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetDepositInfoResponse.class);
			//The downstream doesn't return a Error Code, so it is necessary to append the Http Exception code
			if (getDepositInfoResponse == null || getDepositInfoResponse.getMessageList() == null || getDepositInfoResponse.getMessageList().isEmpty()) {
				throw e;
			}
		}
		return getDepositInfoResponse;
	}

}
