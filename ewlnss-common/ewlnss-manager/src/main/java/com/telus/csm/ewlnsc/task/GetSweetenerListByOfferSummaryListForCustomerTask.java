package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OfferSummaryListDelegate;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;


public class GetSweetenerListByOfferSummaryListForCustomerTask extends TaskBase {

	private SalesOfferCommonVO input;
	private GetOfferListAdapterResponse result;
	
	public GetSweetenerListByOfferSummaryListForCustomerTask(SalesOfferCommonVO commonVO) {
		this.input = commonVO;
	}
	
	@Override
	protected void execute() {
		result = new OfferSummaryListDelegate().getSweetenerOfferList(input);
	}

	public GetOfferListAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}

}