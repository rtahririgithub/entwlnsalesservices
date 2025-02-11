package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetConsolidatedAccountProfileAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterRequest;
import com.telus.csm.ewlnsc.adapter.scis.domain.GetProductQualificationAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface ISalesCustomerInfoRestSvcAdapter extends IAdapterBase {

	public GetProductQualificationAdapterResponse getProductQualification(final GetProductQualificationAdapterRequest param);
	
	public GetConsolidatedAccountProfileAdapterResponse getConsolidateAcc(final GetConsolidatedAccountProfileAdapterRequest param);
	
}
