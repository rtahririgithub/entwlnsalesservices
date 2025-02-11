package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.GetOfferListAdapterResponse;
import com.telus.csm.ewlnsc.delegate.OfferSummaryListDelegate;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;

/*
 * change made on April 2, 2018: a new parameter has been added to this class, boolean callForRecontractEligibleInd, the reason is that for each instance of this class, that parameter
 * will indicate if the instance is to get re-contract eligible offers or to get regular offers.
 * 
 * Temporary change done on July 29,2019: add new parameter catalogId, this will hold the catalogIds of the same HSIC family of the existing product, this for the purpose of multiple calls to OIS.
 * one call per HSIC catalogId
 *
 */
public class GetOfferListForCustomerTask extends TaskBase {

	private SalesOfferCommonVO input;
	private GetOfferListAdapterResponse result;
	private boolean callForRecontractEligibleInd;
	private String catalogId;
	
	public GetOfferListForCustomerTask(SalesOfferCommonVO commonVO,boolean callForRecontractEligibleInd,String catalogId) {
		this.input = commonVO;
		this.callForRecontractEligibleInd=callForRecontractEligibleInd;
		this.catalogId=catalogId;
	}
	
	@Override
	protected void execute() {
		result = new OfferSummaryListDelegate().getOfferSummaryList(input,callForRecontractEligibleInd,catalogId);
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
