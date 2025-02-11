package com.telus.csm.ewlnsis.core.domain;

import com.telus.csm.ewlnsc.domain.CoreRequestBase;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.AccessoryOfferCriteria;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SalesAvailableOfferFilterCriteria;

public class GetAvailableOfferSummaryListCoreRequest extends CoreRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AccessoryOfferCriteria accessoryOfferCriteria;
	
	private SalesAvailableOfferFilterCriteria salesOfferCriteria;

	public AccessoryOfferCriteria getAccessoryOfferCriteria() {
		return accessoryOfferCriteria;
	}

	public void setAccessoryOfferCriteria(AccessoryOfferCriteria accessoryOfferCriteria) {
		this.accessoryOfferCriteria = accessoryOfferCriteria;
	}

	public SalesAvailableOfferFilterCriteria getSalesOfferCriteria() {
		return salesOfferCriteria;
	}

	public void setSalesOfferCriteria(SalesAvailableOfferFilterCriteria salesOfferCriteria) {
		this.salesOfferCriteria = salesOfferCriteria;
	}
	
	
}
