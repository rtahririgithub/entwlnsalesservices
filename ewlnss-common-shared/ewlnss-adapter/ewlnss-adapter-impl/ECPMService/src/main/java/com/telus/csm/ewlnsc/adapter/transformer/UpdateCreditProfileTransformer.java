package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.UpdateCreditProfileAdapterResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.enterprisecreditprofilemanagementservicerequestresponse_v1.UpdateCreditProfile;

/**
 * 
 * @author Jose.Mena
 *
 */
public class UpdateCreditProfileTransformer {
	
	private UpdateCreditProfileTransformer(){
		
	}

	public static UpdateCreditProfile transformRequestDO(UpdateCreditProfileAdapterRequest request){
		UpdateCreditProfile req = new UpdateCreditProfile();
		req.setConsumerCreditProfileInfo(request.getConsumerCreditProfileInfo());
		req.setAuditInfo(request.getAuditInfo());
		return req;
	}
	
	public static UpdateCreditProfileAdapterResponse transformResponseToDO(){
		// Response schema class in downstream is empty. Investigate if this is expected or not.
		
		return new UpdateCreditProfileAdapterResponse();
	}
}
