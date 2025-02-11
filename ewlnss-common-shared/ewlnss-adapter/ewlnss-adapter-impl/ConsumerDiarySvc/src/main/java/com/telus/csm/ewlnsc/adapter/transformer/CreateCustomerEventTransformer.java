package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.CreateCustomerEventAdapterResponse;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CreateCustomerEvent;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CreateCustomerEventResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreateCustomerEventTransformer {
	
	private CreateCustomerEventTransformer(){
		
	}

	public static CreateCustomerEvent transformAdapterRequest(CreateCustomerEventAdapterRequest request) {
		CreateCustomerEvent req = new CreateCustomerEvent();
		req.setCustomerEvent(request.getCustomerEvent());
		req.setExternalTypeId(request.getExternalTypeId());
		return req;
	}

	public static CreateCustomerEventAdapterResponse transformToAdapterResponse(CreateCustomerEventResponse result) {
		CreateCustomerEventAdapterResponse resp = new CreateCustomerEventAdapterResponse();
		resp.setCustomerEventId(result.getCustomerEventId());
		return resp;
	}

}
