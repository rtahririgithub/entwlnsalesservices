package com.telus.csm.ewlnsc.adapter.ccm.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.customercommon_v3.Address;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class UpdateCustomerOfficialAddressAdapterRequest extends AdapterRequestBase {
	
	private static final long serialVersionUID = 1L;
	
	private long customerId;
	private Address officialAddress;
	private AuditInfo auditInfo;
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public Address getOfficialAddress() {
		return officialAddress;
	}
	public void setOfficialAddress(Address officialAddress) {
		this.officialAddress = officialAddress;
	}
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

}
