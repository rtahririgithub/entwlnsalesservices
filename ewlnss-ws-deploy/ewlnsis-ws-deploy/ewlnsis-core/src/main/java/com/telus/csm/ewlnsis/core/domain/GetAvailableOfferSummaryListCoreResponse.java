package com.telus.csm.ewlnsis.core.domain;

import java.util.List;

import com.telus.csm.ewlnsc.domain.CoreResponseBase;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseSalesOfferSummary;

public class GetAvailableOfferSummaryListCoreResponse extends CoreResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<AccessoryOffer> accessoryOfferSummaryList;
	private List<EnterpriseSalesOfferSummary> salesOfferSummaryList;
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
	
	

}
