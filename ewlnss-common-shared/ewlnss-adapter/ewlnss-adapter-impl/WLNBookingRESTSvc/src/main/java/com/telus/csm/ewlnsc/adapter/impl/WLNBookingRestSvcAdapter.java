package com.telus.csm.ewlnsc.adapter.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpServerErrorException;

import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.BookAppointmentAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.CancelBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.ConfirmBookingAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetBookingRequirementResponse;
import com.telus.csm.ewlnsc.adapter.wbk.domain.InstallationRequirementsList;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.JsonUtil;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.csm.ewlnss.adapter.common.AdapterFeatureDriver;
import com.telus.csm.ewlnss.adapter.common.FeatureAgentUID;
import com.telus.csm.ewlnss.adapter.common.RestServiceAdapter;

public class WLNBookingRestSvcAdapter extends RestServiceAdapter implements IWLNBookingRestSvcAdapter{
	
	
		private static final String QUERY_PARAM_FROM_DATE="fromdate";
		private static final String QUERY_PARAM_TO_DATE="todate";
		private static final String QUERY_PARAM_FMS_ADDRESS_ID="fmsaddressid";
		private static final String QUERY_PARAM_PROVINCE="province";
		private static final String QUERY_PARAM_CITY="city";
		private static final String QUERY_PARAM_PROJECTCD="projectcode";
		private static final String QUERY_PARAM_INSTALL_REQ_LIST="installationrequirementslist";
		private static final String STICKY_SESSION_ID="stickysessionid";
		
	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(WLNBookingRestSvcAdapter.class);
	
	private static String rootUrl = ApplicationProperties.getConfigString("${connections/webServices/wlnBookingRestSvc/wsdlUrl}");

	private static final String RESOURCE_PATH="opentimeslots";
	private static final String CONFIRM_BOOKING_RESOURCE_PATH="shoppingcartorder/appointment";
	private static final String APPOINTMENT_RESOURCE_PATH="appointment";
	private static final String GET_BOOKING_REQUIREMENTS_RESOURCE_PATH="/shoppingcartorder/{orderid}/bookingrequirement";
	
	
	public WLNBookingRestSvcAdapter() {
	super();
	}
	
	
	public WLNBookingRestSvcAdapter(AdapterFeatureDriver adapterFeatureDriver){
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
			Object obj = get("ping", "opentimeslots", String.class);
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
	public GetAvailableTimeSlotsAdapterResponse getAvailableTimeSlots(GetAvailableTimeSlotsAdapterRequest param) {
		final String functionName = "WLNBookingRestSvcAdapter->getAvailableTimeSlots";
		LOGGER.enter(functionName,"Entering method...");
		//Creating Map to hold the PathParameters
		Map<String, String> queryParams = new HashMap<String, String>();
		LOGGER.info(functionName, "Setting Query Parameters for GetAvailableTimeSlots Request");
		setAvailableTimeSlotsQueryParams(param,queryParams);
		GetAvailableTimeSlotsAdapterResponse orderBookingRestAdapterResponse=null;
		try{
			LOGGER.info(functionName, "Calling GetAvailableTimeSlots Operation");
			ResponseEntity<GetAvailableTimeSlotsAdapterResponse> responseEntity = get(RESOURCE_PATH, RESOURCE_PATH, GetAvailableTimeSlotsAdapterResponse.class, queryParams);

			LOGGER.info(functionName, "Response Successfully retrieved from BookingService.GetAvailableTimeSlots. Status=" + responseEntity.getStatusCode().toString());
			orderBookingRestAdapterResponse = responseEntity.getBody();

		// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
		// let error 400 flow through
//		} catch (HttpClientErrorException e) {
		}catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			orderBookingRestAdapterResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetAvailableTimeSlotsAdapterResponse.class);
			//The downstream doesn't return a Error Code, so it is necessary to append the Http Exception code
			if (orderBookingRestAdapterResponse == null || orderBookingRestAdapterResponse.getMessageList() == null || orderBookingRestAdapterResponse.getMessageList().isEmpty()) {
				throw e;
			}
		}
		return orderBookingRestAdapterResponse;
	}

	private void setAvailableTimeSlotsQueryParams(GetAvailableTimeSlotsAdapterRequest param, Map<String, String> pathParams) {
		String functionName = "setAvailableTimeSlotsQueryParams";
		LOGGER.enter(functionName);
		if (!StringUtils.isBlank(param.getFromDate())) {
			pathParams.put(QUERY_PARAM_FROM_DATE, param.getFromDate());
		}
		if (!StringUtils.isBlank(param.getToDate())) {
			pathParams.put(QUERY_PARAM_TO_DATE, param.getToDate());
		}
		if (!StringUtils.isBlank(param.getCity())) {
			pathParams.put(QUERY_PARAM_CITY, param.getCity());
		}
		if (!StringUtils.isBlank(param.getFmsAddressId())) {
			pathParams.put(QUERY_PARAM_FMS_ADDRESS_ID, param.getFmsAddressId());
		}
		if (!StringUtils.isBlank(param.getProvinceCd())) {
			pathParams.put(QUERY_PARAM_PROVINCE, param.getProvinceCd());
		}
		if (!param.getInstallationRequirementsList().isEmpty()) {
			pathParams.put(QUERY_PARAM_INSTALL_REQ_LIST, getJsonList(param.getInstallationRequirementsList()));
		}
		if (!StringUtils.isBlank(param.getProjectCd())) {
			pathParams.put(QUERY_PARAM_PROJECTCD, param.getProjectCd());
		}
		LOGGER.exit(functionName);
	}
	


	private String getJsonList(List<InstallationRequirementsList> installationRequirementsList) {
		StringBuilder sb = new StringBuilder();
		String functionName = "setJsonList";
		LOGGER.enter(functionName);
		sb.append("{\"installationRequirementsList\":[");
		int i=0;
		for(InstallationRequirementsList installationReqElement : installationRequirementsList){
			i++;
			sb.append(JsonUtil.getJsonFromObject(installationReqElement));
			if(i!=installationRequirementsList.size()){ //Logic to put a comma in between each Json String returned, but not to put a comma at the end of the last Json String returned
				sb.append(",");
			}
		}
		sb.append("]}");
		LOGGER.exit(functionName);
		return sb.toString();
	}


	@Override
	public ConfirmBookingAdapterResponse confirmBooking(ConfirmBookingAdapterRequest param) {
		final String functionName = "WLNBookingRestSvcAdapter->confirmBooking";
		LOGGER.enter(functionName,"Entering method...");
		//Creating Map with HttpHeaders
		Map<String,String> httpHeaders = new HashMap<String,String>();
		//Setting SESSION in HTTP headers
		httpHeaders.put(STICKY_SESSION_ID, param.getStickysessionid());
		LOGGER.info(functionName, "Setting stickysessionid in HttpHeader with value: " + param.getStickysessionid());
		ConfirmBookingAdapterResponse confirmBookingRestAdapterResponse = new ConfirmBookingAdapterResponse();
		ResponseEntity<ConfirmBookingAdapterResponse> responseEntity = null;
		try{
			LOGGER.info(functionName, "Calling confirmBooking Operation");
			responseEntity = put(CONFIRM_BOOKING_RESOURCE_PATH, CONFIRM_BOOKING_RESOURCE_PATH, JsonUtil.getJsonFromObjectNonNUll(param.getBody()), null, httpHeaders);

			LOGGER.info(functionName, "Response Successfully retrieved from BookingService.confirmBooking. Status=" + responseEntity.getStatusCode().toString());
			confirmBookingRestAdapterResponse = responseEntity.getBody();
			if (confirmBookingRestAdapterResponse == null){
				confirmBookingRestAdapterResponse = new ConfirmBookingAdapterResponse();
			}
//			confirmBookingRestAdapterResponse.setStatusCode(responseEntity.getStatusCode().toString());

		// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
		// let error 400 flow through
//		} catch (HttpClientErrorException e) {
		}catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			confirmBookingRestAdapterResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), ConfirmBookingAdapterResponse.class);
			if (confirmBookingRestAdapterResponse == null) {
				throw e;
			}
			confirmBookingRestAdapterResponse.setStatusCode(e.getStatusCode().toString());
			if (confirmBookingRestAdapterResponse.getMessageList() == null || confirmBookingRestAdapterResponse.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		}
		return confirmBookingRestAdapterResponse;
	}


	@Override
	public BookAppointmentAdapterResponse bookAppointment(BookAppointmentAdapterRequest param) {
		final String functionName = "WLNBookingRestSvcAdapter->bookAppointment";
		LOGGER.enter(functionName);
		
		BookAppointmentAdapterResponse bookAppointmentAdapterResponse = null;
		try {
			//Creating Map with HttpHeaders
			Map<String,String> httpHeaders = new HashMap<String,String>();
			//Setting SESSION in HTTP headers
			if(!StringUtils.isBlank(param.getStickysessionid())){
				httpHeaders.put(STICKY_SESSION_ID, param.getStickysessionid());
			}
			LOGGER.info(functionName, "Setting stickysessionid in HttpHeader with value: " + param.getStickysessionid());

			ResponseEntity<BookAppointmentAdapterResponse> responseEntity =
					post(APPOINTMENT_RESOURCE_PATH,APPOINTMENT_RESOURCE_PATH, JsonUtil.getJsonFromObjectNonNUll(param.getBody()), BookAppointmentAdapterResponse.class, null, httpHeaders);

			LOGGER.info(functionName, "Response Successfully retrieved from BookingService.bookAppointment. Status=" + responseEntity.getStatusCode().toString());
			bookAppointmentAdapterResponse = responseEntity.getBody();

		// no need to catch HttpClientErrorException as all exceptions are logged by the parent class RestServiceAdapter
		// let error 400 flow through
//		} catch (HttpClientErrorException e) {
		} catch(HttpServerErrorException e) { //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			bookAppointmentAdapterResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), BookAppointmentAdapterResponse.class);
			if (bookAppointmentAdapterResponse == null || bookAppointmentAdapterResponse.getMessageList() == null || bookAppointmentAdapterResponse.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		}
		return bookAppointmentAdapterResponse;
	}


	@Override
	public CancelBookingAdapterResponse cancelBooking(final CancelBookingAdapterRequest param) {
		final String functionName = "WLNBookingRestSvcAdapter->cancelBooking";
		final String CANCEL_BOOKING = APPOINTMENT_RESOURCE_PATH+"/{bookingId}";
		
		Map<String, String> queryParamsMap = new HashMap<String, String>();
		String resourcePath = CANCEL_BOOKING.replace("{bookingId}", param.getBookingId());
		
		
		LOGGER.enter(functionName,"Entering method...");
		//Creating variable to hold query parameter
		
		LOGGER.info(functionName, "Setting Query Parameter for cancelBooking Request");
		
		if (!StringUtils.isBlank(param.getOrderId())) {
		  queryParamsMap.put("orderid", param.getOrderId()); 		  
		}	
		
		CancelBookingAdapterResponse cancelBookingRestAdapterResponse = new CancelBookingAdapterResponse();
		try{
			LOGGER.info(functionName, "Calling CancelBooking Operation");
			ResponseEntity<Void> responseEntity = delete(CANCEL_BOOKING, resourcePath, Void.class, queryParamsMap );

			LOGGER.info(functionName, "Response Successfully retrieved from BookingService.cancelBooking. Status=" + responseEntity.getStatusCode().toString());
			
			if(responseEntity.getBody()!=null){
				Object response = responseEntity.getBody();
				
				if(responseEntity.getStatusCode()==HttpStatus.OK) {
					
					cancelBookingRestAdapterResponse.setCallSuccessfulInd(true);
				}else {
					if(response!=null) {
						//Error scenario
					
						cancelBookingRestAdapterResponse = (CancelBookingAdapterResponse) response;
						cancelBookingRestAdapterResponse.setCallSuccessfulInd(false);
					}
				}
			}
		}catch(HttpServerErrorException e){ //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			cancelBookingRestAdapterResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), CancelBookingAdapterResponse.class);
			//The downstream doesn't return a Error Code, so it is necessary to append the Http Exception code
			if (cancelBookingRestAdapterResponse == null || cancelBookingRestAdapterResponse.getMessageList() == null || cancelBookingRestAdapterResponse.getMessageList().isEmpty()) {
				throw e;
			}
		}
		return cancelBookingRestAdapterResponse;
	}	
	
	public GetBookingRequirementResponse getBookingRequirement(GetBookingRequirementRequest request) {
		final String functionName = "WLNBookingRestSvcAdapter->getBookingRequirement";
		LOGGER.enter(functionName);
		
		GetBookingRequirementResponse getBookingRequirementResponse = new GetBookingRequirementResponse();
		try {
			String resourcePathWithOrderId = GET_BOOKING_REQUIREMENTS_RESOURCE_PATH.replace("{orderid}", request.getOrderId());

//			ResponseEntity<GetBookingRequirementResponse.BookingRequirement[]> responseEntity =
//					get(GET_BOOKING_REQUIREMENTS_RESOURCE_PATH, resourcePathWithOrderId, GetBookingRequirementResponse.BookingRequirement[].class);
			ResponseEntity<GetBookingRequirementResponse> responseEntity =
					get(GET_BOOKING_REQUIREMENTS_RESOURCE_PATH, resourcePathWithOrderId, GetBookingRequirementResponse.class);

			LOGGER.info(functionName, "Response Successfully retrieved from BookingService.getBookingRequirement. Status=" + responseEntity.getStatusCode().toString());
			//GetBookingRequirementResponse.BookingRequirement[] bookingRequirements = responseEntity.getBody();
			getBookingRequirementResponse = responseEntity.getBody();
			
//			if(bookingRequirements != null && bookingRequirements.length > 0) {
//				getBookingRequirementResponse.setBookingRequirements(Arrays.asList(bookingRequirements));
//			}

		} catch(HttpServerErrorException e) { //This exception catch the 503 error
			//Handle Exception - for 503 Error return the ResponseBodyAsString which contains the MessageList from Downstream
			getBookingRequirementResponse = JsonUtil.parseJsonToObject(e.getResponseBodyAsString(), GetBookingRequirementResponse.class);
			if (getBookingRequirementResponse == null || getBookingRequirementResponse.getMessageList() == null || getBookingRequirementResponse.getMessageList().isEmpty()) {
				//The downstream doesn't return the expected error message structure - so need to rethrow the exception
				throw e;
			}
		}
		return getBookingRequirementResponse;
	}
}
