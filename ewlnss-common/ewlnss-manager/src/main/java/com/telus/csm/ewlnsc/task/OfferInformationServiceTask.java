package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IOfferInformationServiceAdapter;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.adapter.domain.GetOfferListByOfferIdentifierListAdapterRequest;

public class OfferInformationServiceTask extends TaskBase {
	
	private GetOfferListByOfferIdentifierListAdapterRequest input;
	private GetOfferListAdapterResponse result;
	private IOfferInformationServiceAdapter adapter;
	
	public OfferInformationServiceTask(IOfferInformationServiceAdapter adapter, GetOfferListByOfferIdentifierListAdapterRequest input) {
		this.input=input;
		this.adapter = adapter;
	}
	
	public GetOfferListByOfferIdentifierListAdapterRequest getInput() {
		return input;
	}

	public GetOfferListAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	@Override
	protected void execute() {
		result = adapter.getOfferListByOfferIdentifierList(input);
	}

}
