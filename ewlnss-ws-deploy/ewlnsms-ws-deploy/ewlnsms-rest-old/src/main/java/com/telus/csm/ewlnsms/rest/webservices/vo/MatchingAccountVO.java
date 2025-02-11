package com.telus.csm.ewlnsms.rest.webservices.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class MatchingAccountVO {

	private AddressVO billingAddress;	  
	private String billingAccountNum;	 
	private String primaryServicePhoneNum;
	private Double balance;

	public AddressVO getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(AddressVO billingAddress) {
		this.billingAddress = billingAddress;
	}
	public String getBillingAccountNum() {
		return billingAccountNum;
	}
	public void setBillingAccountNum(String billingAccountNum) {
		this.billingAccountNum = billingAccountNum;
	}
	public String getPrimaryServicePhoneNum() {
		return primaryServicePhoneNum;
	}
	public void setPrimaryServicePhoneNum(String primaryServicePhoneNum) {
		this.primaryServicePhoneNum = primaryServicePhoneNum;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
