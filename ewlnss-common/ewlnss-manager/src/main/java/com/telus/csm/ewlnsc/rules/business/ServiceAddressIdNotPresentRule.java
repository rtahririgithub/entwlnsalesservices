package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;

/*
if requestserviceAdressId is empty or null
*/

public class ServiceAddressIdNotPresentRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(ServiceAddressIdNotPresentRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public ServiceAddressIdNotPresentRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = false;

		if (isServiceAddressIdNotPresent()) {
			isSatisfied = true;
			
			TraceVO t = TraceVO.newInstance(this);
			t.setRuleName("ServiceAddressIdNotPresentRule");
//			t.setAction(Trace.DELETED);
			t.setOffer(offer);
//			t.setProduct(product);
			t.setReason("Service Address Id is not present in request");
			
			traces.add(t);

		}

		logger.info("ServiceAddressIdNotPresentRule", isSatisfied ? "Passed" : "Failed");

		return isSatisfied;
	}

	private boolean isServiceAddressIdNotPresent() {

		if (salesOfferCommonVO.getOffersRequestVO() == null
				|| salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria() == null
				|| salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress() == null
				|| salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress()
						.getServiceAddressId() == null
						|| salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress()
								.getServiceAddressId().isEmpty()) {

			return true;
		}

		return false;
	}
}
