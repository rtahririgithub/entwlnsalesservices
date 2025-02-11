package com.telus.csm.ewlnsms.rest.webservices.vo;

public class AccountInfoVO {

	private String title;	
	private String firstName;	
	private String lastName;	
	private String fullName;	
	private String preferredLangCd;	
	private String mediaType;	
	private String emailForEbill;	
	private AddressVO address;	

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
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getPreferredLangCd() {
		return preferredLangCd;
	}
	public void setPreferredLangCd(String preferredLangCd) {
		this.preferredLangCd = preferredLangCd;
	}
	public String getMediaType() {
		return mediaType;
	}
	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}
	public String getEmailForEbill() {
		return emailForEbill;
	}
	public void setEmailForEbill(String emailForEbill) {
		this.emailForEbill = emailForEbill;
	}
	public AddressVO getAddress() {
		return address;
	}
	public void setAddress(AddressVO address) {
		this.address = address;
	}
	public void setupDemo() {
		setTitle("Mrs");	
		setFirstName("Eve");	
		setLastName("Alpha");	
		setPreferredLangCd("EN");	
		AddressVO addressVO = new AddressVO();
		addressVO.setupDemo();
		setAddress(addressVO);	
	}
}
