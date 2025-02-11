/**
 * 
 */
package com.telus.csm.ewlnsms.core.domain;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesmanagementservicerequestresponse_v5.CustomerType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.BillingAccountType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.BusinessEntityInformationType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EbillNotificationType;

public class UpdateAccountCoreRequest extends CoreRequestBase {

	private static final long serialVersionUID = 1L;
	
	private BillingAccountType billingAccount;
	private BusinessEntityInformationType businessEntityInfo;
	private CustomerType customer;
	private EbillNotificationType billNotification;
	
	
	public BillingAccountType getBillingAccount() {
		return billingAccount;
	}
	public void setBillingAccount(BillingAccountType billingAccount) {
		this.billingAccount = billingAccount;
	}
	public BusinessEntityInformationType getBusinessEntityInfo() {
		return businessEntityInfo;
	}
	public void setBusinessEntityInfo(BusinessEntityInformationType businessEntityInfo) {
		this.businessEntityInfo = businessEntityInfo;
	}
	public CustomerType getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerType customer) {
		this.customer = customer;
	}
	public EbillNotificationType getBillNotification() {
		return billNotification;
	}
	public void setBillNotification(EbillNotificationType billNotification) {
		this.billNotification = billNotification;
	}
}
