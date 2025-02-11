package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.CheckProductFeasibility;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.CheckProductFeasibilityResponse;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessage;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ResponseMessageList;

public class FeasibilityServiceTransformer {

	/****************************************************/
	/*    transformAdapterRequestToDownstreamRequest    */ 
	/****************************************************/
	public CheckProductFeasibility transformAdapterRequestToDownstreamRequest(CheckProductFeasibilityAdapterRequest request) {
		CheckProductFeasibility checkRequest = new CheckProductFeasibility();
		
		checkRequest.setAccessCriteria(request.getAccessCriteria());
		checkRequest.setAddress(request.getAddress());
		checkRequest.setCustomerId(request.getCustomerId());
		checkRequest.setProductInstanceList(request.getProductInstanceList());
		checkRequest.setProductSpecificationList(request.getProductSpecificationList());
		checkRequest.setServiceAccessList(request.getServiceAccessList());
		checkRequest.setTimestamp(request.getTimeStamp());
		checkRequest.setUserId(request.getUserId());
		
		return checkRequest;
	}

	/****************************************************/
	/*    transformDownstreamResponseToAdapterResponse  */
	/****************************************************/
	public CheckProductFeasibilityAdapterResponse transformDownstreamResponseToAdapterResponse(CheckProductFeasibilityResponse response) {
		CheckProductFeasibilityAdapterResponse checkResponse = new CheckProductFeasibilityAdapterResponse();
		if(response.getProductFeasibilityInfoList()!=null && response.getResponseMessageList()!=null && !response.getResponseMessageList().getResponseMessage().isEmpty()){
			checkResponse.setProductFeasibilityInfoList(response.getProductFeasibilityInfoList());
			checkResponse.setServiceAccessList(response.getServiceAccessList());
			checkResponse.setSuccessfulProcessInd(true);
			checkResponse.setResponseMessageList(response.getResponseMessageList());
		}else if(response.getProductFeasibilityInfoList()==null && response.getResponseMessageList()!=null && !response.getResponseMessageList().getResponseMessage().isEmpty()){
			checkResponse.setResponseMessageList(response.getResponseMessageList());
			ResponseMessageList responseMessageList = response.getResponseMessageList();
			if (responseMessageList != null && ! responseMessageList.getResponseMessage().isEmpty()){
				for (ResponseMessage respMessage : response.getResponseMessageList().getResponseMessage()){
					if (respMessage.getMessageType().equalsIgnoreCase(EnterpriseWLNSalesServicesConstants.MESSAGE_TYPE_ERROR)){
						checkResponse.setMsgHasError(true);
						break;
					}
				}
			}
		}else if(response.getProductFeasibilityInfoList()!=null && response.getResponseMessageList().getResponseMessage().isEmpty()){
			checkResponse.setProductFeasibilityInfoList(response.getProductFeasibilityInfoList());
			checkResponse.setServiceAccessList(response.getServiceAccessList());
			checkResponse.setSuccessfulProcessInd(true);
		}
		return checkResponse;
	}

}
