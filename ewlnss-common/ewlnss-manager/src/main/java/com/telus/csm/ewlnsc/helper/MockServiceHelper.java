package com.telus.csm.ewlnsc.helper;
 
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.telus.csm.ewlnsc.util.ApplicationProperties;
import com.telus.csm.ewlnsc.util.EnterpriseSalesServiceUtil; 

public class MockServiceHelper { 
	
	private static Logger logger = Logger.getLogger(MockServiceHelper.class);
	
	public static List<String> mocktransactionIdList(){
		List<String> mockList = Arrays.asList("0001","0002","0003","0004","0005","0006","0007","0008","0009","0010");
		return mockList;
	}
	
	/************************************/
	/*   post                           */
	/************************************/
	public static Map<String, String> post(String operation, String requestBody){
		
		return callMock("post", operation, requestBody);
	}

	/************************************/
	/*   put                            */
	/************************************/
	public static Map<String, String> put(String operation, String requestBody){
		
		return callMock("put", operation, requestBody);
	}

	/************************************/
	/*   get                            */
	/************************************/
	public static Map<String, String> get(String operation, String requestBody){
	
		return callMock("get", operation, requestBody);
	}
	
	/********************************************/
	/*   change range of salesTransactionId     */
	/********************************************/
	public static boolean inRange(String input) {
		if(EnterpriseSalesServiceUtil.containsAnyIgnoreCase(mocktransactionIdList(), new String[]{input})){
			return true;
		}
	    return false;
	}
	
	
	public static boolean inRange(String lowerBound, String upperBound, String input) {
	    if (lowerBound == null || upperBound == null || input == null)
	    	return false;
	    
	    return input.compareToIgnoreCase(lowerBound) >= 0 && input.compareToIgnoreCase(upperBound) <= 0;
	}
	
	/************************************/
	/*   is MOCK enabled ?              */
	/************************************/
	public static boolean enableMock(){
		try{
			String enableMock = ApplicationProperties.getConfigString("${common/mockService/enableMock}");
			return enableMock.equalsIgnoreCase("true");
		} catch (Exception e){
			return false;
		}
	}
	
	/************************************/
	/*   calling SOAPUI Mock service    */
	/************************************/
	private static Map<String, String> callMock(String action, String operation, String requestBody){
		
		Map<String, String> mockResponse = new HashMap<String, String>();
		 
		logger.debug("calling mock service action=" + action + " operation=" + operation + " request body=" + requestBody);
		
		try {
			
			String endPoint = ApplicationProperties.getConfigString("${common/mockService/" + operation + "/end-point}");
			String resource = ApplicationProperties.getConfigString("${common/mockService/" + operation + "/resource}");

			String mockURL = endPoint + resource;
			
			Client client = Client.create();

			WebResource webResource = client.resource(mockURL);
			webResource.type("application/json");
			
			ClientResponse response = null;
			
			if (action.equalsIgnoreCase("post")){
				response = webResource.post(ClientResponse.class, requestBody);
			} else if (action.equalsIgnoreCase("put")){
				response = webResource.put(ClientResponse.class, requestBody);
			} else if (action.equalsIgnoreCase("get")){
				webResource = client.resource(mockURL + requestBody.trim());
				response = webResource.get(ClientResponse.class);
			}
			
			mockResponse.put("status", new Integer(response.getStatus()).toString());
			mockResponse.put("response", response.getEntity(String.class));

		  } catch (Exception e) {
			  mockResponse.put("status", "999");
			  e.printStackTrace();
			  logger.error("mock service exception =>" + e.getMessage());
		  }

		
		return mockResponse;
	}
}
