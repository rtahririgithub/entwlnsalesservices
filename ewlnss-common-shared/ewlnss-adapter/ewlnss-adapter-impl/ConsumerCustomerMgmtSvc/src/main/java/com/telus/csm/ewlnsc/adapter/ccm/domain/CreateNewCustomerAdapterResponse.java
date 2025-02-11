package com.telus.csm.ewlnsc.adapter.ccm.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.Customer;

public class CreateNewCustomerAdapterResponse extends AdapterResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Customer newCustomer;

	public Customer getNewCustomer() {
		return newCustomer;
	}

	public void setNewCustomer(Customer newCustomer) {
		this.newCustomer = newCustomer;
	}
	
}
