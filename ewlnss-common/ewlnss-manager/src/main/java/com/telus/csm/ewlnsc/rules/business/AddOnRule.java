package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;

public class AddOnRule extends AbstractSpecification<Offer,TraceVO> {

	private SalesOfferCommonVO salesOfferCommonVO;

	public AddOnRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {

		//TODO
		//Retrieves available TV channel/Packs by GTM.
		//Filters out those add-on that are not available to the location

		return true;
	}

}
