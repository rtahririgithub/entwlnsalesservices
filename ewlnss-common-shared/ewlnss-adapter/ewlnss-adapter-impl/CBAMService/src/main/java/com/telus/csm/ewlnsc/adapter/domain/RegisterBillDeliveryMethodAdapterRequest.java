package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillMedia;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class RegisterBillDeliveryMethodAdapterRequest extends AdapterRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int billingSystemId;
	private String billingAccountNumber;
	private BillMedia billMedia;
	private AuditInfo auditInfo;
	
	public int getBillingSystemId() {
		return billingSystemId;
	}
	public void setBillingSystemId(int billingSystemId) {
		this.billingSystemId = billingSystemId;
	}
	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}
	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}
	public BillMedia getBillMedia() {
		return billMedia;
	}
	public void setBillMedia(BillMedia billMedia) {
		this.billMedia = billMedia;
	}
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}

	
}
