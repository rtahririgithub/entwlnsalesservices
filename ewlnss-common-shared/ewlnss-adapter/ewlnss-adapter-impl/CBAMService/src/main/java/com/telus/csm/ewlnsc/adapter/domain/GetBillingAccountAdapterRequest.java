package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;

public class GetBillingAccountAdapterRequest extends AdapterRequestBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String billingAccountNumber;
	private int billingSystemId;
	
	
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	public int getBillingSystemId() {
		return billingSystemId;
	}
	public void setBillingSystemId(int billingSystemId) {
		this.billingSystemId = billingSystemId;
	}
	
	public String getCacheKey() {
		return "getBillingAccount_" + billingAccountNumber + "_txnId_" + getSalesTransactionId();
	}

}
