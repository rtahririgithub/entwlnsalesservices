package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class InstallmentOptionVO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String installmentOptionCd = null;
	private MoneyVO downPaymentAmount = null;
	private Integer numberOfInstallments = null;
	private MoneyVO installmentAmount = null;
	
	public String getInstallmentOptionCd() {
		return installmentOptionCd;
	}
	public void setInstallmentOptionCd(String installmentOptionCd) {
		this.installmentOptionCd = installmentOptionCd;
	}
	public MoneyVO getDownPaymentAmount() {
		return downPaymentAmount;
	}
	public void setDownPaymentAmount(MoneyVO downPaymentAmount) {
		this.downPaymentAmount = downPaymentAmount;
	}
	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}
	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	public MoneyVO getInstallmentAmount() {
		return installmentAmount;
	}
	public void setInstallmentAmount(MoneyVO installmentAmount) {
		this.installmentAmount = installmentAmount;
	}
}
