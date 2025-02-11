package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CancelDepositInput;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CancelDepositAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;

	private CancelDepositInput input;

	public CancelDepositInput getInput() {
		return input;
	}

	public void setInput(CancelDepositInput input) {
		this.input = input;
	}

}
