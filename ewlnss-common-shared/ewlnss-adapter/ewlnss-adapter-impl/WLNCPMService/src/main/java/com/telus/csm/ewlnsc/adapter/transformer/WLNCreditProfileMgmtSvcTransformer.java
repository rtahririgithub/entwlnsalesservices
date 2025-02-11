package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.GetCreditProfileByCustomerId;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.GetCreditProfileByCustomerIdResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.SearchCreditProfileByCreditId;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.SearchCreditProfileByCreditIdResponse;

/**
 * 
 * @author Jose.Mena
 *
 */
public class WLNCreditProfileMgmtSvcTransformer {

	private WLNCreditProfileMgmtSvcTransformer(){
		
	}
	
	public static GetCreditProfileByCustomerId transformRequestDO(GetCreditProfileByCustomerIdAdapterRequest request) {
		GetCreditProfileByCustomerId req = new GetCreditProfileByCustomerId();
		req.setCustomerId(Long.valueOf(request.getCustomerId()));
		req.setAuditInfo(request.getAuditInfo());
		return req;
	}

	public static GetCreditProfileByCustomerIdAdapterResponse transformResponseToDO(
			GetCreditProfileByCustomerIdResponse result) {
		GetCreditProfileByCustomerIdAdapterResponse resp = new GetCreditProfileByCustomerIdAdapterResponse();
		resp.setCreditProfile(result.getCreditProfile());
		return resp;
	}

	public static SearchCreditProfileByCreditId transformAdapterRequestForSearchCredit(
			SearchCreditProfileByCreditIdAdapterRequest request) {
		SearchCreditProfileByCreditId req = new SearchCreditProfileByCreditId();
		req.setCreditIdentification(request.getCreditIdentification());
		req.setAuditInfo(request.getAuditInfo());
		return req;
	}

	public static SearchCreditProfileByCreditIdAdapterResponse transformToAdapterResponseForSearchClient(
			SearchCreditProfileByCreditIdResponse param) {
		SearchCreditProfileByCreditIdAdapterResponse resp = new SearchCreditProfileByCreditIdAdapterResponse();
		resp.setCreditProfileSearchResultList(param.getCreditProfileSearchResultList());
		return resp;
	}
	
}
