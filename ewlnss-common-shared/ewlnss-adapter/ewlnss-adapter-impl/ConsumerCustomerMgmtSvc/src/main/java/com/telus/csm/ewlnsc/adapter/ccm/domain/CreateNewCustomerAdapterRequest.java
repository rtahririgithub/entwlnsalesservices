package com.telus.csm.ewlnsc.adapter.ccm.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditProfileData;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.Customer;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class CreateNewCustomerAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private Customer customer;
	private CreditProfileData creditProfile;
	private List<ContactIndividual> customerContactsList;
	private AuditInfo auditInfo;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public CreditProfileData getCreditProfile() {
		return creditProfile;
	}
	public void setCreditProfile(CreditProfileData creditProfile) {
		this.creditProfile = creditProfile;
	}
	public List<ContactIndividual> getCustomerContactsList() {
		return customerContactsList;
	}
	public void setCustomerContactsList(List<ContactIndividual> customerContactsList) {
		this.customerContactsList = customerContactsList;
	}
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
