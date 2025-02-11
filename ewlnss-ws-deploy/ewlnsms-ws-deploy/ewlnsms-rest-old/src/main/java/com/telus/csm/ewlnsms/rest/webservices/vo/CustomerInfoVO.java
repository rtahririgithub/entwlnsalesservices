package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.util.ArrayList;
import java.util.List;

public class CustomerInfoVO {

	private String title;
	private String firstName;
	private String lastName;
	private String preferredLangCd;
	private String pin;
	private String servicePhoneNum;
	private String businessPhoneNum;
	private String mobilePhoneNum;
	private String email;
	private String emailDeclinedReasonCd;
	private String authorizedName;
	private String preferredContactTimePeriodCd;
	private CreditProfileVO creditProfile;
	private List<String> ebillNotificationTypeList;

	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPreferredLangCd() {
		return preferredLangCd;
	}
	public void setPreferredLangCd(String preferredLangCd) {
		this.preferredLangCd = preferredLangCd;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getServicePhoneNum() {
		return servicePhoneNum;
	}
	public void setServicePhoneNum(String servicePhoneNum) {
		this.servicePhoneNum = servicePhoneNum;
	}
	public String getBusinessPhoneNum() {
		return businessPhoneNum;
	}
	public void setBusinessPhoneNum(String businessPhoneNum) {
		this.businessPhoneNum = businessPhoneNum;
	}
	public String getMobilePhoneNum() {
		return mobilePhoneNum;
	}
	public void setMobilePhoneNum(String mobilePhoneNum) {
		this.mobilePhoneNum = mobilePhoneNum;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmailDeclinedReasonCd() {
		return emailDeclinedReasonCd;
	}
	public void setEmailDeclinedReasonCd(String emailDeclinedReasonCd) {
		this.emailDeclinedReasonCd = emailDeclinedReasonCd;
	}
	public String getAuthorizedName() {
		return authorizedName;
	}
	public void setAuthorizedName(String authorizedName) {
		this.authorizedName = authorizedName;
	}
	public String getPreferredContactTimePeriodCd() {
		return preferredContactTimePeriodCd;
	}
	public void setPreferredContactTimePeriodCd(String preferredContactTimePeriodCd) {
		this.preferredContactTimePeriodCd = preferredContactTimePeriodCd;
	}
	public CreditProfileVO getCreditProfile() {
		return creditProfile;
	}
	public void setCreditProfile(CreditProfileVO creditProfile) {
		this.creditProfile = creditProfile;
	}
	public List<String> getEbillNotificationTypeList() {
		
		if (ebillNotificationTypeList == null) {
			ebillNotificationTypeList = new ArrayList<String>();
		}
		
		return ebillNotificationTypeList;
	}
	public void setEbillNotificationTypeList(List<String> ebillNotificationTypeList) {
		this.ebillNotificationTypeList = ebillNotificationTypeList;
	}
	public void setupDemo() {
		setTitle("Mr");
		setFirstName("Adam");
		setLastName("Omega");
		setPreferredLangCd("EN");
	}

}
