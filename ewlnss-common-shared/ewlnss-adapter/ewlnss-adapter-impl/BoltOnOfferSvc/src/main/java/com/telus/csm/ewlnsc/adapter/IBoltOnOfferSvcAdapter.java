package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

/**
 * 
 * @author Jose.Mena
 *
 */
public interface IBoltOnOfferSvcAdapter extends IAdapterBase {

	/**
	 * Retrieves the list of existing products that are eligible for receiving a gift
	 * 
	 * @param request
	 * @return
	 * @
	 */
	public GetAvailableServiceInstanceListAdapterResponse getAvailableServiceInstanceList(final GetAvailableServiceInstanceListAdapterRequest request);
}
