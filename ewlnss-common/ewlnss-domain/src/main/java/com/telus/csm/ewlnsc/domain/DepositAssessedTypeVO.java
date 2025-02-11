package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class DepositAssessedTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Double depositAmt = null;
	private String invoiceNumber = null;

	public Double getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(Double double1) {
		this.depositAmt = double1;
	}

	public String getInvoiceNumber() {
		return invoiceNumber;
	}

	public void setInvoiceNumber(String invoiceNumber) {
		this.invoiceNumber = invoiceNumber;
	}

}
