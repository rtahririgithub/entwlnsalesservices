package com.telus.csm.ewlnsc.rules.business;


import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;

public class ServiceAddressRule extends AbstractSpecification<Offer,TraceVO> {

	private boolean result = true;
	private SalesOfferCommonVO salesOfferCommonVO;

	public ServiceAddressRule() {
	}

	public ServiceAddressRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	public boolean isSatisfiedBy(Offer o, List<TraceVO> errors) {
		
		if (o.getOfferProduct() == null) {

			result = false;
//			errors.add(Trace.ESS_ERROR);
		}

		return result;
	}
}
