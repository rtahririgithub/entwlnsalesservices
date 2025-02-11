package com.telus.csm.ewlnsc.adapter;

import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterResponse;
import com.telus.csm.ewlnss.adapter.common.IAdapterBase;

public interface IMarketOfferServiceAdapter extends IAdapterBase{
	
	public GetMarketOfferDetailAdapterResponse getMarketOfferDetail(final GetMarketOfferDetailAdapterRequest request);

}
