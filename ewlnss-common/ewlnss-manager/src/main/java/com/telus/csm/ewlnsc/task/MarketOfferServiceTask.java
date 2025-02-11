package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IMarketOfferServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetMarketOfferDetailAdapterResponse;

public class MarketOfferServiceTask extends TaskBase {
	
	private GetMarketOfferDetailAdapterRequest input;
	private GetMarketOfferDetailAdapterResponse result;
	private IMarketOfferServiceAdapter adapter;
	
	public MarketOfferServiceTask(IMarketOfferServiceAdapter adapter, GetMarketOfferDetailAdapterRequest input){
		this.input=input;
		this.adapter=adapter;
	}
	
	public GetMarketOfferDetailAdapterRequest getInput() {
		return input;
	}

	public GetMarketOfferDetailAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	@Override
	protected void execute() {
		result = adapter.getMarketOfferDetail(input);
	}

}
