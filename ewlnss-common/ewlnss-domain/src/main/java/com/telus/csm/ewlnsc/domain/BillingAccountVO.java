package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class BillingAccountVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String billingAccountNumber = null;
	private String accountTypeCd = null;
	private String accountSubTypeCd = null;
	private String accountMasterSourceTypeCd = null;

	public String getBillingAccountNumber() {
		return billingAccountNumber;
	}

	public void setBillingAccountNumber(String billingAccountNumber) {
		this.billingAccountNumber = billingAccountNumber;
	}

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

	public String getAccountMasterSourceTypeCd() {
		return accountMasterSourceTypeCd;
	}

	public void setAccountMasterSourceTypeCd(String accountMasterSourceTypeCd) {
		this.accountMasterSourceTypeCd = accountMasterSourceTypeCd;
	}

}
