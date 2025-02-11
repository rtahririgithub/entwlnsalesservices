package com.telus.csm.ewlnsc.delegate;

import com.telus.csm.ewlnsc.adapter.IBoltOnOfferSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.csm.ewlnss.adapter.factory.AdapterFactory;

public class AvailableServiceInstanceListDelegate {
	
	private AvailableServiceInstanceListDelegate() {
	}
	
	public static GetAvailableServiceInstanceListAdapterResponse execute(final GetAvailableServiceInstanceListAdapterRequest req) {
		final IBoltOnOfferSvcAdapter adapter = AdapterFactory.getAdapter(IBoltOnOfferSvcAdapter.class);

		final GetAvailableServiceInstanceListAdapterResponse resp = adapter.getAvailableServiceInstanceList(req);

		return resp;
	}
}
