package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class AccountProfileRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String accountTypeCd;
	protected String accountSubTypeCd;
	protected String payChannelNum;
	protected String billingAccountNum;
	protected String accountMasterSourceId;
	protected String billingAccountPIN;
	protected String billPreferredLangCd;
	protected String accountStatus;
	protected String accountCreationDt;
	protected String customerId;
	protected String customerPreferredLangCd;
	protected NameInfoRestVO accountHolderName;
	protected List<String> authorizedNameList;
	protected AddressRestVO billingAddress;
	protected List<String> billingDeliveryMethodList;
	protected String emailForEbill;
	protected Double accountBalance;
	protected BundleDiscountInfoRestVO bundleDiscountInfo;
	protected Boolean selfServeInd;
	protected String billCycleCd;
	protected String customerSegmentCd;
	protected String contactPhoneNum;
	protected NameInfoRestVO contactName;
	protected String preferredContactTimePeriodCd;
	protected String emailAddress;
	protected Boolean preAuthorizedPaymentInd;
	protected String brandCd;
	protected Boolean availableForAddOnInd;
	protected String billCycleCloseMonthDay;
	protected String billingLangCd;
	protected List<SubscriberInfoRestVO> subscriberList;
	protected List<SubscribedProductInfoRestVO> subscribedProductList;
	protected String ebillDeclineReasonCode;
	protected String smsForEbillNotificationNum;
	protected String emailForEbillNotificationNum;
	

	public String getAccountTypeCd() {
		return accountTypeCd;
	}

	public void setAccountTypeCd(String accountTypeCd) {
		this.accountTypeCd = accountTypeCd;
	}

	public String getAccountSubTypeCd() {
		return accountSubTypeCd;
	}

	public void setAccountSubTypeCd(String accountSubTypeCd) {
		this.accountSubTypeCd = accountSubTypeCd;
	}

	public String getPayChannelNum() {
		return payChannelNum;
	}

	public void setPayChannelNum(String payChannelNum) {
		this.payChannelNum = payChannelNum;
	}

	public String getBillingAccountNum() {
		return billingAccountNum;
	}

	public void setBillingAccountNum(String billingAccountNum) {
		this.billingAccountNum = billingAccountNum;
	}

	public String getAccountMasterSourceId() {
		return accountMasterSourceId;
	}

	public void setAccountMasterSourceId(String accountMasterSourceId) {
		this.accountMasterSourceId = accountMasterSourceId;
	}

	public String getBillingAccountPIN() {
		return billingAccountPIN;
	}

	public void setBillingAccountPIN(String billingAccountPIN) {
		this.billingAccountPIN = billingAccountPIN;
	}

	public String getBillPreferredLangCd() {
		return billPreferredLangCd;
	}

	public void setBillPreferredLangCd(String billPreferredLangCd) {
		this.billPreferredLangCd = billPreferredLangCd;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getAccountCreationDt() {
		return accountCreationDt;
	}

	public void setAccountCreationDt(String accountCreationDt) {
		this.accountCreationDt = accountCreationDt;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerPreferredLangCd() {
		return customerPreferredLangCd;
	}

	public void setCustomerPreferredLangCd(String customerPreferredLangCd) {
		this.customerPreferredLangCd = customerPreferredLangCd;
	}

	public NameInfoRestVO getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(NameInfoRestVO accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public List<String> getAuthorizedNameList() {
		return authorizedNameList;
	}

	public void setAuthorizedNameList(List<String> authorizedNameList) {
		this.authorizedNameList = authorizedNameList;
	}

	public AddressRestVO getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(AddressRestVO billingAddress) {
		this.billingAddress = billingAddress;
	}

	public List<String> getBillingDeliveryMethodList() {
		return billingDeliveryMethodList;
	}

	public void setBillingDeliveryMethodList(
			List<String> billingDeliveryMethodList) {
		this.billingDeliveryMethodList = billingDeliveryMethodList;
	}

	public String getEmailForEbill() {
		return emailForEbill;
	}

	public void setEmailForEbill(String emailForEbill) {
		this.emailForEbill = emailForEbill;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	public BundleDiscountInfoRestVO getBundleDiscountInfo() {
		return bundleDiscountInfo;
	}

	public void setBundleDiscountInfo(
			BundleDiscountInfoRestVO bundleDiscountInfo) {
		this.bundleDiscountInfo = bundleDiscountInfo;
	}

	public Boolean getSelfServeInd() {
		return selfServeInd;
	}

	public void setSelfServeInd(Boolean selfServeInd) {
		this.selfServeInd = selfServeInd;
	}

	public String getBillCycleCd() {
		return billCycleCd;
	}

	public void setBillCycleCd(String billCycleCd) {
		this.billCycleCd = billCycleCd;
	}

	public String getCustomerSegmentCd() {
		return customerSegmentCd;
	}

	public void setCustomerSegmentCd(String customerSegmentCd) {
		this.customerSegmentCd = customerSegmentCd;
	}

	public String getContactPhoneNum() {
		return contactPhoneNum;
	}

	public void setContactPhoneNum(String contactPhoneNum) {
		this.contactPhoneNum = contactPhoneNum;
	}

	public NameInfoRestVO getContactName() {
		return contactName;
	}

	public void setContactName(NameInfoRestVO contactName) {
		this.contactName = contactName;
	}

	public String getPreferredContactTimePeriodCd() {
		return preferredContactTimePeriodCd;
	}

	public void setPreferredContactTimePeriodCd(
			String preferredContactTimePeriodCd) {
		this.preferredContactTimePeriodCd = preferredContactTimePeriodCd;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Boolean getPreAuthorizedPaymentInd() {
		return preAuthorizedPaymentInd;
	}

	public void setPreAuthorizedPaymentInd(Boolean preAuthorizedPaymentInd) {
		this.preAuthorizedPaymentInd = preAuthorizedPaymentInd;
	}

	public String getBrandCd() {
		return brandCd;
	}

	public void setBrandCd(String brandCd) {
		this.brandCd = brandCd;
	}

	public Boolean getAvailableForAddOnInd() {
		return availableForAddOnInd;
	}

	public void setAvailableForAddOnInd(Boolean availableForAddOnInd) {
		this.availableForAddOnInd = availableForAddOnInd;
	}

	public String getBillCycleCloseMonthDay() {
		return billCycleCloseMonthDay;
	}

	public void setBillCycleCloseMonthDay(String billCycleCloseMonthDay) {
		this.billCycleCloseMonthDay = billCycleCloseMonthDay;
	}

	public String getBillingLangCd() {
		return billingLangCd;
	}

	public void setBillingLangCd(String billingLangCd) {
		this.billingLangCd = billingLangCd;
	}

	public List<SubscriberInfoRestVO> getSubscriberList() {
		return subscriberList;
	}

	public void setSubscriberList(List<SubscriberInfoRestVO> subscriberList) {
		this.subscriberList = subscriberList;
	}

	public List<SubscribedProductInfoRestVO> getSubscribedProductList() {
		return subscribedProductList;
	}

	public void setSubscribedProductList(
			List<SubscribedProductInfoRestVO> subscribedProductList) {
		this.subscribedProductList = subscribedProductList;
	}

	public String getEbillDeclineReasonCode() {
		return ebillDeclineReasonCode;
	}

	public void setEbillDeclineReasonCode(String ebillDeclineReasonCode) {
		this.ebillDeclineReasonCode = ebillDeclineReasonCode;
	}

	public String getSmsForEbillNotificationNum() {
		return smsForEbillNotificationNum;
	}

	public void setSmsForEbillNotificationNum(String smsForEbillNotificationNum) {
		this.smsForEbillNotificationNum = smsForEbillNotificationNum;
	}

	public String getEmailForEbillNotificationNum() {
		return emailForEbillNotificationNum;
	}

	public void setEmailForEbillNotificationNum(String emailForEbillNotificationNum) {
		this.emailForEbillNotificationNum = emailForEbillNotificationNum;
	}
}
