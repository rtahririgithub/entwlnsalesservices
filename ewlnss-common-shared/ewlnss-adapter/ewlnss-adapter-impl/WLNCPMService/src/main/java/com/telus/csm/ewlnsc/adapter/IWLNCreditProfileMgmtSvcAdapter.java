package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetCreditProfileByCustomerIdAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchCreditProfileByCreditIdAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.SearchCreditProfileByCreditId;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.wlncreditprofilemanagementservicerequestresponse_v2.SearchCreditProfileByCreditIdResponse;

public interface IWLNCreditProfileMgmtSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param SearchCreditProfileByCreditId
	 * @return
	 */
	public SearchCreditProfileByCreditIdAdapterResponse searchCreditProfileByCreditId(final SearchCreditProfileByCreditIdAdapterRequest param);
	
	public GetCreditProfileByCustomerIdAdapterResponse getCreditProfileByCustomerId(GetCreditProfileByCustomerIdAdapterRequest request);

}
