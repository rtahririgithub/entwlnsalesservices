package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.GetAvailableServiceInstanceList;
import com.telus.tmi.xmlschema.srv.mso.campaignmgmt.boltonofferservicerequestresponse_v3.GetAvailableServiceInstanceListResponse;

public class BoltOnOfferSvcTransformer {

	private BoltOnOfferSvcTransformer(){
		
	}
	
	public static GetAvailableServiceInstanceList transformRequest(GetAvailableServiceInstanceListAdapterRequest param) {
		GetAvailableServiceInstanceList request = new GetAvailableServiceInstanceList();
		request.setCustomerId(param.getCustomerId());
		request.setApplicationId(param.getApplicationId());
		request.setRoleId(param.getRoleId());
		return request;
	}
	
	public static GetAvailableServiceInstanceListAdapterResponse transformResponse (GetAvailableServiceInstanceListResponse param) {
		GetAvailableServiceInstanceListAdapterResponse response = new GetAvailableServiceInstanceListAdapterResponse();
		response.setServiceList(param.getServiceList());
		return response;
	}
}