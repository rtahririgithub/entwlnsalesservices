package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillingAccount;

public class GetBillingAccountAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	private BillingAccount billingAccount;


	public BillingAccount getBillingAccount() {
		return billingAccount;
	}


	public void setBillingAccount(BillingAccount billingAccount) {
		this.billingAccount = billingAccount;
	} 
}
