package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.DepositCalulationResult;

public class CalculateDepositResponse extends AdapterResponseBase {

	private DepositCalulationResult depositCalulationResult;

	public DepositCalulationResult getDepositCalulationResult() {
		return depositCalulationResult;
	}

	public void setDepositCalulationResult(DepositCalulationResult depositCalulationResult) {
		this.depositCalulationResult = depositCalulationResult;
	}
}
