package com.telus.csm.ewlnsc.domain;

public class PhoneListingTypeVO {

	private TelephoneNumberTypeVO telephoneNumberType;
	private DirectoryListingTypeVO directoryListingType;
	
	public TelephoneNumberTypeVO getTelephoneNumberType() {
		return telephoneNumberType;
	}
	public void setTelephoneNumberType(TelephoneNumberTypeVO telephoneNumberTypeVO) {
		this.telephoneNumberType = telephoneNumberTypeVO;
	}
	public DirectoryListingTypeVO getDirectoryListingType() {
		return directoryListingType;
	}
	public void setDirectoryListingType(DirectoryListingTypeVO directoryListingTypeVO) {
		this.directoryListingType = directoryListingTypeVO;
	}
	


}
