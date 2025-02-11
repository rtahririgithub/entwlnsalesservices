package com.telus.csm.ewlnssproxy.rest.transformer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.DeserializationConfig.Feature;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.adapter.scis.domain.AccountProfileRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductInstanceInfoRestVO;
import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseProxyVO;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnssproxy.cpib.domain.Message;

public class BaseTransformer {
	private static Logger logger = Logger.getLogger(BaseTransformer.class);
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
	private static SimpleDateFormat oldDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	
	
	public static List<Message> transformResponseMessages(List<String> messageList,String errorCode) {		
		List<Message> responseMessageList = new ArrayList<Message>();
		for(String message: messageList){
			Message responseMsg = new Message();
			responseMsg.setCode(errorCode);
			responseMsg.setType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR);
			responseMsg.setText(message);
			responseMessageList.add(responseMsg);
		}
		return responseMessageList;
	}
	
    public static List<Message> getResponseMessageFromException(Exception e) {
		
    	List<Message> messages = new ArrayList<Message>();
    	Message x = new Message();
		x.setCode(new Integer(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()).toString());
		x.setType(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_EXCEPTION);
		x.setText("Exception: " + ExceptionUtils.getFullStackTrace(e)); 
		messages.add(x);
		
		return messages; 
	}
    
    public static String transformResponse(Object response) throws Exception{
		
		String responseJson = "";
		 
		try{
		  ObjectMapper mapper = new ObjectMapper();
		  mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
		  mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
		  responseJson = mapper.writeValueAsString(response);
		}catch(Exception e){
		   throw e;
		}
		 
		return responseJson;

	}  

	public static <T> T transformResponse(Object response, Class<T> output, String dateFormat) throws Exception {

		String responseJson = "";

		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			
			if(dateFormat != null) {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
				mapper.setDateFormat(sdf);
			}
			responseJson = mapper.writeValueAsString(response);

			mapper.configure(Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return (T) mapper.readValue(responseJson, output);
		} catch (Exception e) {
			throw e;
		}

	}
	
	public static <T> T transformResponse(Object response, Class<T> output) throws Exception {
		return transformResponse(response, output, null);
	}

    
    public static String transformResponseWithDateFormat(Object response, String dateFormat) throws Exception{
		
		String responseJson = "";
		 
		try {
			ObjectMapper mapper = new ObjectMapper();
			SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			mapper.setDateFormat(sdf);
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
			mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_EMPTY);
			responseJson = mapper.writeValueAsString(response);
		} catch (Exception e) {
			throw e;
		}
		return responseJson;
	}
    
    // QC74721 convert commitmentPeriodStartDt format
 	protected static void convertDateFormatInResponse(GetAssignedAndPendingProductResponseProxyVO response) {
 		logger.info("convertDateFormatInResponse Start");
 		if (response != null) {
 			convertDateFormat(response.getSubscribedProductList());
 			convertDateFormat(response.getPendingProductList());
 			if (response.getPrimaryAccountProfile() != null) {
 				convertDateFormat(response.getPrimaryAccountProfile().getSubscribedProductList());
 			}
 			if (response.getCustomerAccountProfileList() != null
 					&& !response.getCustomerAccountProfileList().isEmpty()) {
 				for (AccountProfileRestVO customerAccount : response.getCustomerAccountProfileList()) {
 					convertDateFormat(customerAccount.getSubscribedProductList());
 				}
 			}
 		}
 		logger.info("convertDateFormatInResponse End");
 	}

 	// QC74721 convert commitmentPeriodStartDt format
 	private static void convertDateFormat(List<SubscribedProductInfoRestVO> subscribedProductList) {
 		if (subscribedProductList != null && !subscribedProductList.isEmpty()) {
 			for (SubscribedProductInfoRestVO subProductInfo : subscribedProductList) {
 				if (subProductInfo.getProductInstance() != null && !subProductInfo.getProductInstance().isEmpty()) {
 					for (ProductInstanceInfoRestVO productInstance : subProductInfo.getProductInstance()) {
 						if (!StringUtils.isBlank(productInstance.getCommitmentPeriodStartDt())) {
 							try {

 								Date commitmentStartDt = oldDateFormat
 										.parse(productInstance.getCommitmentPeriodStartDt());
 								productInstance.setCommitmentPeriodStartDt(dateFormat.format(commitmentStartDt));

 							} catch (ParseException e) {
 								logger.error("Can't convert commitmentStartDt:"
 										+ productInstance.getCommitmentPeriodStartDt(), e);
 							}
 						}
 					}
 				}
 			}
 		}
 	}
}
