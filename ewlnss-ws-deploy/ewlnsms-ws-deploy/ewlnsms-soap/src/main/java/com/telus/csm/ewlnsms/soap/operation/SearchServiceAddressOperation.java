package com.telus.csm.ewlnsms.soap.operation;


import com.telus.csm.ewlnsms.core.operation.SearchServiceAddressCoreOperation;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchServiceAddressResponseType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.SearchServiceAddressType;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreRequest;
import com.telus.csm.ewlnsms.core.domain.SearchServiceAddressCoreResponse;
import org.apache.log4j.Logger;
public class SearchServiceAddressOperation {
	
	private static final Logger LOGGER = Logger.getLogger(SearchServiceAddressOperation.class);
	
	public SearchServiceAddressResponseType execute(SearchServiceAddressType parameters){
		//Transforming request to Core Request
				LOGGER.info("Entering SearchServiceAddressOperation.execute()...");
				SearchServiceAddressResponseType response = new SearchServiceAddressResponseType();
				SearchServiceAddressCoreRequest coreRequest = new SearchServiceAddressCoreRequest();
				coreRequest.setOperationHeader(parameters.getOperationHeader());
				coreRequest.setServiceAddress(parameters.getServiceAddress());
				SearchServiceAddressCoreOperation coreOperation = new SearchServiceAddressCoreOperation();
				
				try {
					LOGGER.info("Calling SearchServiceAddressCoreOperation...");
					SearchServiceAddressCoreResponse coreResponse =coreOperation.execute(coreRequest);
					response = transformResponse(coreResponse);
				} catch (Throwable e) {
					LOGGER.error("Error happened when calling searchAddressCoreOperation -> details: " + e.getMessage() ,e);
				}
				return response;
	}

	private SearchServiceAddressResponseType transformResponse(SearchServiceAddressCoreResponse coreResponse) {
		LOGGER.info("Entering SearchServiceAddressOperation.transformResponse()");
		SearchServiceAddressResponseType response = new SearchServiceAddressResponseType();
		if(coreResponse.getMatchingServiceAddress()!=null){
			response.setMatchingServiceAddress(coreResponse.getMatchingServiceAddress());
		}else if(coreResponse.getMessageList()!=null){
			response.setMessageList(coreResponse.getMessageList());
		}
		return response;
	}

}
