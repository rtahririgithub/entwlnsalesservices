/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.util.List;

/**
 * @author x145592
 *
 */
public class TotalTaxVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private Float totalTaxAmount = null;  
	private List<TaxAmountVO> taxesApplied = null;

	public Float getTotalTaxAmount() {
		return totalTaxAmount;
	}

	public void setTotalTaxAmount(Float totalTaxAmount) {
		this.totalTaxAmount = totalTaxAmount;
	}

	public List<TaxAmountVO> getTaxesApplied() {
		return taxesApplied;
	}

	public void setTaxesApplied(List<TaxAmountVO> taxesApplied) {
		this.taxesApplied = taxesApplied;
	}

}
