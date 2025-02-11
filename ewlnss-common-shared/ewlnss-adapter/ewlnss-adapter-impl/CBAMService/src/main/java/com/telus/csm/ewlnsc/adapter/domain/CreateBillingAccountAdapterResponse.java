package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.CreateBillingAccountResponse;

public class CreateBillingAccountAdapterResponse extends AdapterResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8422007306334555511L;

	private CreateBillingAccountResponse response;

	public CreateBillingAccountResponse getResponse() {
		return response;
	}

	public void setResponse(CreateBillingAccountResponse response) {
		this.response = response;
	}
	
	
}
