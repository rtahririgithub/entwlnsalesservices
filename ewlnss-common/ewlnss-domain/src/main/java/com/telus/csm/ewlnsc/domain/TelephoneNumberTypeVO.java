package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;


public class TelephoneNumberTypeVO implements Serializable{

	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getPhoneNumberType() {
		return phoneNumberType;
	}

	public void setPhoneNumberType(String phoneNumberType) {
		this.phoneNumberType = phoneNumberType;
	}

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String phoneNumberType = null;

	  private Long phoneNumber = null;

	
}
