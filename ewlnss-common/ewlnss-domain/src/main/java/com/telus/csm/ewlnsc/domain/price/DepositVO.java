/**
 * 
 */
package com.telus.csm.ewlnsc.domain.price;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author x145592
 *
 */
public class DepositVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String depositType = null;
	private BigDecimal depositAmt = null;
	private Boolean estimatedDepositInd = null;

	public String getDepositType() {
		return depositType;
	}

	public void setDepositType(String depositType) {
		this.depositType = depositType;
	}

	public BigDecimal getDepositAmt() {
		return depositAmt;
	}

	public void setDepositAmt(BigDecimal depositAmt) {
		this.depositAmt = depositAmt;
	}

	public Boolean getEstimatedDepositInd() {
		return estimatedDepositInd;
	}

	public void setEstimatedDepositInd(Boolean estimatedDepositInd) {
		this.estimatedDepositInd = estimatedDepositInd;
	}

}
