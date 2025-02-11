package com.telus.csm.ewlnsc.adapter.ccm.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.customer.customersubdomain_v3.CustomerPreference;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class InsertCustomerPreferenceAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private CustomerPreference customerPreference;

	private AuditInfo auditInfo;
	
	public CustomerPreference getCustomerPreference() {
		return customerPreference;
	}

	public void setCustomerPreference(CustomerPreference customerPreference) {
		this.customerPreference = customerPreference;
	}

	public AuditInfo getAuditInfo() {
		return auditInfo;
	}

	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
