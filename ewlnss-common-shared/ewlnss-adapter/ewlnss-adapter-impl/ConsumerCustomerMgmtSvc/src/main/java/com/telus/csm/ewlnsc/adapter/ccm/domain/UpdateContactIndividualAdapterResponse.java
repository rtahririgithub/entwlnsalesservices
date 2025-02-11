package com.telus.csm.ewlnsc.adapter.ccm.domain;
import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.SystemType;

public class UpdateContactIndividualAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;
	
	private ContactIndividual customerContactIndividual;
	private List<SystemType> emailSyncSystemList;
	
	public ContactIndividual getCustomerContactIndividual() {
		return customerContactIndividual;
	}
	public void setCustomerContactIndividual(ContactIndividual customerContactIndividual) {
		this.customerContactIndividual = customerContactIndividual;
	}
	public List<SystemType> getEmailSyncSystemList() {
		return emailSyncSystemList;
	}
	public void setEmailSyncSystemList(List<SystemType> emailSyncSystemList) {
		this.emailSyncSystemList = emailSyncSystemList;
	}
	
}
