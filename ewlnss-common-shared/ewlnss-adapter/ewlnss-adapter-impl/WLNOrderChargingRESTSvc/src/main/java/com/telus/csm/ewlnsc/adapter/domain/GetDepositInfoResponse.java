package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

public class GetDepositInfoResponse extends WlnOPRestSvcResponseBase {

	private Double totalRequiredDeposit;
	private Double depositAdjustmentAmount;

	public Double getTotalRequiredDeposit() {
		return totalRequiredDeposit;
	}

	public void setTotalRequiredDeposit(Double totalRequiredDeposit) {
		this.totalRequiredDeposit = totalRequiredDeposit;
	}

	public Double getDepositAdjustmentAmount() {
		return depositAdjustmentAmount;
	}

	public void setDepositAdjustmentAmount(Double depositAdjustmentAmount) {
		this.depositAdjustmentAmount = depositAdjustmentAmount;
	}

	public Double getDepositOnHand() {
		return depositOnHand;
	}

	public void setDepositOnHand(Double depositOnHand) {
		this.depositOnHand = depositOnHand;
	}

	private Double depositOnHand;

}
