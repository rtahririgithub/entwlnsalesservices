package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CreditProfileVO  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String sin;
	private HealthCardVO healthCard;
	private PassportVO passport;
	private ProvincialIdCardVO provincialIdCard;
	private CreditCardTokenVO creditCardToken;
	private String applicationProvinceCd;
	private PersonalInfoVO personalInfo;
	private CreditAddressVO address;
	protected DriverLicenseVO driverLicense;

	
	public DriverLicenseVO getDriverLicense() {
		return driverLicense;
	}
	public void setDriverLicense(DriverLicenseVO driverLicense) {
		this.driverLicense = driverLicense;
	}
	public String getSin() {
		return sin;
	}
	public void setSin(String sin) {
		this.sin = sin;
	}
	public HealthCardVO getHealthCard() {
		return healthCard;
	}
	public void setHealthCard(HealthCardVO healthCard) {
		this.healthCard = healthCard;
	}
	public PassportVO getPassport() {
		return passport;
	}
	public void setPassport(PassportVO passport) {
		this.passport = passport;
	}
	public ProvincialIdCardVO getProvincialIdCard() {
		return provincialIdCard;
	}
	public void setProvincialIdCard(ProvincialIdCardVO provincialIdCard) {
		this.provincialIdCard = provincialIdCard;
	}
	public CreditCardTokenVO getCreditCardToken() {
		return creditCardToken;
	}
	public void setCreditCardToken(CreditCardTokenVO creditCardToken) {
		this.creditCardToken = creditCardToken;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getApplicationProvinceCd() {
		return applicationProvinceCd;
	}
	public void setApplicationProvinceCd(String applicationProvinceCd) {
		this.applicationProvinceCd = applicationProvinceCd;
	}
	public PersonalInfoVO getPersonalInfo() {
		return personalInfo;
	}
	public void setPersonalInfo(PersonalInfoVO personalInfo) {
		this.personalInfo = personalInfo;
	}
	public CreditAddressVO getAddress() {
		return address;
	}
	public void setAddress(CreditAddressVO address) {
		this.address = address;
	}	
}
