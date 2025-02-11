package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerbillingaccountmgmtsvcrequestresponse_v1.CreateBillingAccount;

public class CreateBillingAccountAdapterRequest extends AdapterRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7082869127686870360L;

	private CreateBillingAccount request;

	public CreateBillingAccount getRequest() {
		return request;
	}

	public void setRequest(CreateBillingAccount request) {
		this.request = request;
	}
	
	
}
