package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.AssessCreditWorthinessAdapterResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementproxyservicerequestresponse_v2.AssessCreditWorthiness;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementproxyservicerequestresponse_v2.AssessCreditWorthinessResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class AssessCreditWorthinessTransformer {
	
	private AssessCreditWorthinessTransformer(){
		
	}
	
	public static AssessCreditWorthiness transformRequestDO(AssessCreditWorthinessAdapterRequest request) {
		AssessCreditWorthiness req = new AssessCreditWorthiness();
		req.setAssessCreditWorthinessRequest(request.getAssessCreditWorthinessRequest());
		req.setAuditInfo(request.getAuditInfo());
		return req;
	}
	
	public static AssessCreditWorthinessAdapterResponse transformResponseToDO(AssessCreditWorthinessResponse response){
		AssessCreditWorthinessAdapterResponse resp = new AssessCreditWorthinessAdapterResponse();
		resp.setAssessedCreditWorthiness(response.getAssessedCreditWorthiness());
		return resp;
	}
}
