package com.telus.csm.ewlnsc.domain;

import java.math.BigDecimal;


public class ProdductDepositVO {
	protected String productNameCd;
	protected String serviceTypeCd;
    protected String assignedProductID;
    protected BigDecimal monthlyChargeAmt;
    protected BigDecimal assessedDepositAmt;
	
    public String getProductNameCd() {
		return productNameCd;
	}
	public void setProductNameCd(String productNameCd) {
		this.productNameCd = productNameCd;
	}
	public String getServiceTypeCd() {
		return serviceTypeCd;
	}
	public void setServiceTypeCd(String serviceTypeCd) {
		this.serviceTypeCd = serviceTypeCd;
	}
	public String getAssignedProductID() {
		return assignedProductID;
	}
	public void setAssignedProductID(String assignedProductID) {
		this.assignedProductID = assignedProductID;
	}
	public BigDecimal getMonthlyChargeAmt() {
		return monthlyChargeAmt;
	}
	public void setMonthlyChargeAmt(BigDecimal monthlyChargeAmt) {
		this.monthlyChargeAmt = monthlyChargeAmt;
	}
	public BigDecimal getAssessedDepositAmt() {
		return assessedDepositAmt;
	}
	public void setAssessedDepositAmt(BigDecimal assessedDepositAmt) {
		this.assessedDepositAmt = assessedDepositAmt;
	}

}
