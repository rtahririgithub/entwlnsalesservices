package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.xsd.customer.customerbill.customer_billing_sub_domain_v2.BillMedia;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v4.AuditInfo;

public class UpdateBillDeliveryAdapterRequest extends AdapterRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int billingSystemId;
	private String billingAccountNumber;
	private List<BillMedia> billMedia;
	private String notificationMethod;
	private List<String> emailAddress;
	private List<String> cellPhoneNumber;
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
	public AuditInfo getAuditInfo() {
		return auditInfo;
	}
	public void setAuditInfo(AuditInfo auditInfo) {
		this.auditInfo = auditInfo;
	}
	public String getNotificationMethod() {
		return notificationMethod;
	}
	public void setNotificationMethod(String notificationMethod) {
		this.notificationMethod = notificationMethod;
	}
	public List<BillMedia> getBillMedia() {
		return billMedia;
	}
	public void setBillMedia(List<BillMedia> billMedia) {
		this.billMedia = billMedia;
	}
	public List<String> getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(List<String> emailAddress) {
		this.emailAddress = emailAddress;
	}
	public List<String> getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(List<String> cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	

	
}
