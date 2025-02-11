package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;

public class CheckFeasibilityResponseIsNotAvailableRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FeasibilityRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public CheckFeasibilityResponseIsNotAvailableRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = false;

		isSatisfied = this.salesOfferCommonVO.getCheckFeasibilityResponseVO() == null
				|| this.salesOfferCommonVO.getCheckFeasibilityResponseVO().isMsgHasError();

		if (isSatisfied) {
			TraceVO t = TraceVO.newInstance(this);
			t.setRuleName("HsicRankingBetterThenFeasibilityRule");
			t.setElementType(TraceVO.OFFER);
			t.setOffer(offer);
			t.setReason("Skipping feasibility rules because CheckFeasibilityResponse is not available");

			traces.add(t);
		}

		// 2018 June Exception release for TTV recontracting
		// Existing code issue, it return false when CheckFeasibilityResponseVO is valid, it should return true instead
		logger.info("CheckFeasibilityResponseIsNotAvailableRule", (!isSatisfied) ? "Passed" : "Failed");
		
		return !isSatisfied;
	}
}
