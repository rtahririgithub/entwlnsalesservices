package com.telus.csm.ewlnsc.adapter.ccm.domain;
import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.ContactIndividual;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class UpdateContactIndividualAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private ContactIndividual customerContact;
	private AuditInfo auditInfo;
	
	public ContactIndividual getCustomerContact() {
		return customerContact;
	}
	public void setCustomerContact(ContactIndividual customerContact) {
		this.customerContact = customerContact;
	}
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
