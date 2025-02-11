package com.telus.csm.ewlnsc.adapter.ccm.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class GetFullCustomerInfoAdapterRequest extends AdapterRequestBase {

	private static final long serialVersionUID = 1L;
	
	private long customerId;
	private AuditInfo auditInfo;

	public GetFullCustomerInfoAdapterRequest(final long custId, final String transId) {
		customerId = custId;
		super.setSalesTransactionId(transId);
	}
	
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	
	@Override
	public String getCacheKey(){
		return "GetFullCustomerInfo_" + customerId + "_txnId_" + getSalesTransactionId();
	}
	
}
