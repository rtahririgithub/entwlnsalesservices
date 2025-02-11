package com.telus.csm.ewlnsc.domain;

import java.math.BigDecimal;
import java.util.List;

public class CalculateDepositResponseVO {
	protected BigDecimal totalDepositAmt;
    protected BigDecimal depositAdjustmentAmt;
    protected BigDecimal depositOnHandAmt;
    protected List<ProdductDepositVO> productDepositList;
    protected String calculationResultMessageCd;
    protected String calculationResultReasonCd;
    
    public List<ProdductDepositVO> getProductDepositList() {
		return productDepositList;
	}
	public void setProductDepositList(List<ProdductDepositVO> productDepositList) {
		this.productDepositList = productDepositList;
	}
    public BigDecimal getTotalDepositAmt() {
		return totalDepositAmt;
	}
	public void setTotalDepositAmt(BigDecimal totalDepositAmt) {
		this.totalDepositAmt = totalDepositAmt;
	}
	public BigDecimal getDepositAdjustmentAmt() {
		return depositAdjustmentAmt;
	}
	public void setDepositAdjustmentAmt(BigDecimal depositAdjustmentAmt) {
		this.depositAdjustmentAmt = depositAdjustmentAmt;
	}
	public BigDecimal getDepositOnHandAmt() {
		return depositOnHandAmt;
	}
	public void setDepositOnHandAmt(BigDecimal depositOnHandAmt) {
		this.depositOnHandAmt = depositOnHandAmt;
	}
	public String getCalculationResultMessageCd() {
		return calculationResultMessageCd;
	}
	public void setCalculationResultMessageCd(String calculationResultMessageCd) {
		this.calculationResultMessageCd = calculationResultMessageCd;
	}
	public String getCalculationResultReasonCd() {
		return calculationResultReasonCd;
	}
	public void setCalculationResultReasonCd(String calculationResultReasonCd) {
		this.calculationResultReasonCd = calculationResultReasonCd;
	}
	
    
	

}
