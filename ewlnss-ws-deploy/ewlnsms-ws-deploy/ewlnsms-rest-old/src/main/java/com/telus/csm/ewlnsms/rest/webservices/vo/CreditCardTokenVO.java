package com.telus.csm.ewlnsms.rest.webservices.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class CreditCardTokenVO {
	protected String token;	  
	protected String first6;	 
	protected String last4;
	protected String expiryMonth;
	protected String expiryYear;

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
	public String getExpiryMonth() {
		return expiryMonth;
	}
	public void setExpiryMonth(String expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	public String getExpiryYear() {
		return expiryYear;
	}
	public void setExpiryYear(String expiryYear) {
		this.expiryYear = expiryYear;
	}
}
