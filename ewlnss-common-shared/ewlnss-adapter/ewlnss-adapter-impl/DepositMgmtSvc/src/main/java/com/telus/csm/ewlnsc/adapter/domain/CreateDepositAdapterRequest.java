package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CreateDepositInput;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreateDepositAdapterRequest extends AdapterRequestBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CreateDepositInput input;

	public CreateDepositInput getInput() {
		return input;
	}

	public void setInput(CreateDepositInput input) {
		this.input = input;
	}

}
