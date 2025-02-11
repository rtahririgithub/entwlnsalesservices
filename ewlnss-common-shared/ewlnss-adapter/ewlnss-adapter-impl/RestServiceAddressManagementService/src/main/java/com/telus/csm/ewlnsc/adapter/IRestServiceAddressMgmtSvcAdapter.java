package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.AddressByFmsAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IRestServiceAddressMgmtSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param searchFmsAddress
	 * @return JsonObject
	 */
	public AddressByFmsAdapterResponse searchFmsAddress(String fmsId, String provinceCode );
 
}
