package com.telus.csm.ewlnsc.domain;

import java.util.List;

import com.telus.csm.ewlnsc.domain.schemasclone.SweetenerOfferSummary;

public class GetAvailableSweetenerOfferListResponseVO extends CoreResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<SweetenerOfferSummary> sweetenerOfferSummaryList;

	public List<SweetenerOfferSummary> getSweetenerOfferSummaryList() {
		return sweetenerOfferSummaryList;
	}

	public void setSweetenerOfferSummaryList(List<SweetenerOfferSummary> sweetenerOfferSummaryList) {
		this.sweetenerOfferSummaryList = sweetenerOfferSummaryList;
	}
}