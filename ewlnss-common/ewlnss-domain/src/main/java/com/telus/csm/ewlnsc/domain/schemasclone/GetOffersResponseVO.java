package com.telus.csm.ewlnsc.domain.schemasclone;

import java.util.List;

import com.telus.csm.ewlnsc.domain.schemasclone.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.schemasclone.EnterpriseSalesOfferSummary;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetenerOfferSummary;


public class GetOffersResponseVO extends CoreResponseBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccessoryOffer> accessoryOfferSummaryList;
	
	private List<EnterpriseSalesOfferSummary> salesOfferSummaryList;

	private List<SweetenerOfferSummary> sweetenerOfferSummaryList;

	public List<AccessoryOffer> getAccessoryOfferSummaryList() {
		return accessoryOfferSummaryList;
	}

	public void setAccessoryOfferSummaryList(List<AccessoryOffer> accessoryOfferSummaryList) {
		this.accessoryOfferSummaryList = accessoryOfferSummaryList;
	}

	public List<EnterpriseSalesOfferSummary> getSalesOfferSummaryList() {
		return salesOfferSummaryList;
	}

	public void setSalesOfferSummaryList(List<EnterpriseSalesOfferSummary> salesOfferSummaryList) {
		this.salesOfferSummaryList = salesOfferSummaryList;
	}

	public List<SweetenerOfferSummary> getSweetenerOfferSummaryList() {
		return sweetenerOfferSummaryList;
	}

	public void setSweetenerOfferSummaryList(List<SweetenerOfferSummary> sweetenerOfferSummaryList) {
		this.sweetenerOfferSummaryList = sweetenerOfferSummaryList;
	}
}
