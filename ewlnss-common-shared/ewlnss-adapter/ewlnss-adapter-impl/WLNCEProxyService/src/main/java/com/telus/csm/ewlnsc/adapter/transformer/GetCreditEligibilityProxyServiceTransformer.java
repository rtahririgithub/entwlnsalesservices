package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncrediteligibilityproxyservicerequestresponse_v1.GetCreditEligibility;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncrediteligibilityproxyservicerequestresponse_v1.GetCreditEligibilityResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class GetCreditEligibilityProxyServiceTransformer {
	
	private GetCreditEligibilityProxyServiceTransformer(){
		
	}
	
	public static GetCreditEligibility transformRequestDO(GetCreditEligibilityAdapterRequest request){
		GetCreditEligibility req = new GetCreditEligibility();
		req.setCustomerId(Long.valueOf(request.getCustomerId()));
		req.setAuditInfo(request.getAuditInfo());
		return req;
	}

	public static GetCreditEligibilityAdapterResponse transformResponseToDO(GetCreditEligibilityResponse result) {
		GetCreditEligibilityAdapterResponse resp = new GetCreditEligibilityAdapterResponse();
		resp.setEquipmentQualificationList(result.getEquipmentQualificationList());
		resp.setCollectionInd(result.isCollectionInd());
		resp.setFraudInd(result.isFraudInd());
		resp.setEligibilityWarningMessageCd(result.getEligibilityWarningMessageCd());
		return resp;
	}
}
