package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetServiceAddressDetailsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.SearchServiceAddressAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IServiceAddressMgmtSvcAdapter extends IAdapterBase {
	
	/**
	 * 
	 * @param SearchServiceAddressAdapterRequest
	 * @return
	 */
	public SearchServiceAddressAdapterResponse searchServiceAddress(final SearchServiceAddressAdapterRequest param);

	
	/**
	 * 
	 * @param GetServiceAddressDetailsRequestDO
	 * @return GetServiceAddressDetailsResponseDO
	 */
	public GetServiceAddressDetailsAdapterResponse getServiceAddressDetails(final GetServiceAddressDetailsAdapterRequest request);
	
}
