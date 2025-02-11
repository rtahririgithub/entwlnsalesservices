package com.telus.csm.ewlnsc.adapter.transformer;

import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositRequest;
import com.telus.csm.ewlnsc.adapter.domain.CalculateDepositResponse;
import com.telus.tmi.xmlschema.srv.cmo.ordermgmt.orderdepositcalculatorproxyservicerequestresponse_v1.CalculateDeposit;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditdeposittypes_v1.DepositCalulationResult;


public class OrderDepositCalculatorTransformer {

	public static CalculateDeposit transform(CalculateDepositRequest createCustomerRequestDO) {
		// TODO Auto-generated method stub
		return createCustomerRequestDO.getCalculateDeposit();
	}

	public static CalculateDepositResponse transform(DepositCalulationResult resp) {
		// TODO Auto-generated method stub
		CalculateDepositResponse calculateDepositResponse = new CalculateDepositResponse();
		calculateDepositResponse.setDepositCalulationResult(resp);
		return calculateDepositResponse;
	}

}
