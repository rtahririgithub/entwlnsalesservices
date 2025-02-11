package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CreateDepositOutput;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreateDepositAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;

	private CreateDepositOutput output;

	public CreateDepositOutput getOutput() {
		return output;
	}

	public void setOutput(CreateDepositOutput output) {
		this.output = output;
	}

}
