package com.telus.csm.ewlnsc.adapter.ccm.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumercustomermgmtsvcrequestresponse_v2.FullCustomer;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class GetFullCustomerInfoAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;
	
	private FullCustomer fullCustomer;
	private AuditInfo auditInfo;
	

	public FullCustomer getFullCustomer() {
		return fullCustomer;
	}
	public void setFullCustomer(FullCustomer fullCustomer) {
		this.fullCustomer = fullCustomer;
	}
	
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
}
