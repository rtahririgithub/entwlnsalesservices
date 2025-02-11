/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;

import com.telus.csm.ewlnsc.domain.MoneyVO;

/**
 * @author x145592
 *
 */
public class TaxAmountVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private MoneyVO amount = null;
	private String taxTypeCd = null;
	private Float taxRateAmt = null;
	
	public MoneyVO getAmount() {
		return amount;
	}
	
	public void setAmount(MoneyVO amount) {
		this.amount = amount;
	}
	
	public String getTaxTypeCd() {
		return taxTypeCd;
	}
	
	public void setTaxTypeCd(String taxType) {
		this.taxTypeCd = taxType;
	}
	
	public Float getTaxRateAmt() {
		return taxRateAmt;
	}
	
	public void setTaxRateAmt(Float taxRate) {
		this.taxRateAmt = taxRate;
	}

	
}
