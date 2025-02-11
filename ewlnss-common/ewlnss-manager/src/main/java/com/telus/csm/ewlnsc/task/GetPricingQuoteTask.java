package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IWLNOrderChargingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteRequest;
import com.telus.csm.ewlnsc.adapter.domain.quote.QuoteResponse;

import commonj.work.Work;


/*
 * This class is not used as the OP call is moved to the Post Task (WirelinePostTaskStrategy).
 */

public class GetPricingQuoteTask extends TaskBase implements Work{

	private QuoteRequest input;
	private QuoteResponse result;
	private IWLNOrderChargingRestSvcAdapter adapter;
	
	public GetPricingQuoteTask(QuoteRequest input, IWLNOrderChargingRestSvcAdapter adapter){
		this.input = input;
		this.adapter = adapter;
	}
	
	public QuoteResponse getResult() {
		rethrowException();
		return result;
	}
	
	@Override
	protected void execute() {
		result = this.adapter.getQuote(input);
	}

}
