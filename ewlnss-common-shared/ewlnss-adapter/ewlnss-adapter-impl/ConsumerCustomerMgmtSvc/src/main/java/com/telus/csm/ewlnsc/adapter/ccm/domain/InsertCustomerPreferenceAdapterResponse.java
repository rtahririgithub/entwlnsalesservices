package com.telus.csm.ewlnsc.adapter.ccm.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.CustomerPreference;

public class InsertCustomerPreferenceAdapterResponse extends AdapterResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private CustomerPreference customerPreference;

	public CustomerPreference getCustomerPreference() {
		return customerPreference;
	}

	public void setCustomerPreference(CustomerPreference customerPreference) {
		this.customerPreference = customerPreference;
	}

}
