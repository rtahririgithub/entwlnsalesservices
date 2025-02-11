package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class PaymentOptionVO implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String payOptionType = null;
	
	private String payOptionTerm = null;
	
	/**
	 * Get payOptionType
	 * @return payOptionType
	**/
	public String getPayOptionType() {
		return payOptionType;
	}

	public void setPayOptionType(String payOptionType) {
		this.payOptionType = payOptionType;
	}
	
	/**
	 * Get payOptionTerm
	 * @return payOptionTerm
	**/
	public String getPayOptionTerm() {
		return payOptionTerm;
	}

	public void setPayOptionTerm(String payOptionTerm) {
		this.payOptionTerm = payOptionTerm;
	}
	
}
