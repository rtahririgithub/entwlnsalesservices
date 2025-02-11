package com.telus.csm.ewlnsms.rest.webservices.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CreditCardVO {
	protected String token;	  
	protected String first6;	 
	protected String last4;
	protected int expiryMonth;
	protected int expiryYear;
	protected String cardVerificationData;
	protected String holderName;

	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getFirst6() {
		return first6;
	}
	public void setFirst6(String first6) {
		this.first6 = first6;
	}
	public String getLast4() {
		return last4;
	}
	public void setLast4(String last4) {
		this.last4 = last4;
	}
	public int getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public int getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(int expiryYear) {
		this.expiryYear = expiryYear;
	}
	public String getCardVerificationData() {
		return cardVerificationData;
	}
	public void setCardVerificationData(String cardVerificationData) {
		this.cardVerificationData = cardVerificationData;
	}
	public String getHolderName() {
		return holderName;
	}
	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}
	
}
