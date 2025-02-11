package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;


import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.orderdepositcalculatorproxyservicerequestresponse_v1.CalculateDeposit;

public class CalculateDepositRequest extends AdapterRequestBase {
    
	CalculateDeposit calculateDeposit;

	public CalculateDepositRequest(CalculateDeposit calculateDeposit){
		this.calculateDeposit = calculateDeposit;
	}
	public CalculateDeposit getCalculateDeposit() {
		return calculateDeposit;
	}


}
