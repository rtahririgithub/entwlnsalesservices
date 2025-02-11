package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OfferDetailDelegate;
import com.telus.csm.ewlnsc.domain.GetSalesOfferDetailRequestVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;

public class GetSalesOfferDetailTask extends TaskBase {

	private SalesOfferCommonVO input;
	private GetOfferListAdapterResponse result;
	private GetSalesOfferDetailRequestVO requestVO;
	
	public GetSalesOfferDetailTask(SalesOfferCommonVO commonVO, GetSalesOfferDetailRequestVO requestVO) {
		this.input = commonVO;
		this.requestVO = requestVO;
	}
	
	@Override
	protected void execute() {
		result = new OfferDetailDelegate().getOfferDetail(input, requestVO);
	}

	public GetOfferListAdapterResponse getResult() {
		rethrowException();
		return result;
	}

}
