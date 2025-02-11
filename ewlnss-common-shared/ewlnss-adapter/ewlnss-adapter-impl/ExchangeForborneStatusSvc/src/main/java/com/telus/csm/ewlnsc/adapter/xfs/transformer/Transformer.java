package com.telus.csm.ewlnsc.adapter.xfs.transformer;

import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterRequest;
import com.telus.csm.ewlnsc.adapter.xfs.domain.ExchangeForborneStatusAdapterResponse;
import com.telus.tmi.xmlschema.srv.rmo.ordermgmt.exchangeforbornestatussvcrequestresponse_v1.GetForborneStatusByNpaNxxList;
import com.telus.tmi.xmlschema.srv.rmo.ordermgmt.exchangeforbornestatussvcrequestresponse_v1.GetForborneStatusByNpaNxxListResponse;

public class Transformer {
	
	private Transformer(){
		
	}

	public static GetForborneStatusByNpaNxxList transform(final ExchangeForborneStatusAdapterRequest param) {
		final GetForborneStatusByNpaNxxList result = new GetForborneStatusByNpaNxxList();
		
		result.setNpaNxxList(param.getNpaNxxList());
		result.setCustomerType(param.getCustomerType());
		result.setEffectiveDate(param.getEffectiveDate());
	    
	    return result;
	}

	public static ExchangeForborneStatusAdapterResponse transform(final GetForborneStatusByNpaNxxListResponse param) {
		final ExchangeForborneStatusAdapterResponse result = new ExchangeForborneStatusAdapterResponse();
		
		result.setExchangeForborneStatusList(param.getExchangeForborneStatusList());
		result.setSuccessfulProcessInd(true);
		
		return result;
	}

}
