package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.cmo.billingaccountmgmt.depositmanagementservicerequestresponse_v1.CancelDepositOutput;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CancelDepositAdapterResponse extends AdapterResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private CancelDepositOutput output;

	public CancelDepositOutput getOutput() {
		return output;
	}

	public void setOutput(CancelDepositOutput output) {
		this.output = output;
	}

}
