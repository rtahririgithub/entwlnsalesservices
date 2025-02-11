package com.telus.csm.ewlnsc.adapter;


import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetFIFAPoductOfferingAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IFIFAProductOfferingRestSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param sarch wireline promotions from TOM
	 * @return JsonObject
	 */
	public GetFIFAPoductOfferingAdapterResponse getProductOfferingQualification(GetFIFAPoductOfferingAdapterRequest requestDO);
 
}
