package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.rules.base.Specification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;

public class FeasibilityRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FeasibilityRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FeasibilityRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> errors) {

		boolean isSatisfied = false;
		
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));

		//check if the Offer is re-contract eligible to skip the rule
		
		// 2018 June Exception release for TTV recontracting
		// skip the rule only for like-for-like recontract instead of all recontract scenarios
		//if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(offer, salesOfferCommonVO)){
		if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForLikeForLikeRecontracting(offer, salesOfferCommonVO)){
			logger.info("FeasibilityRule", "Rule skipped due to Customer is eligible for recontracting and Offer is eligible for Recontracting");
			return true;
		}
		
		if(checkFeasibilityResponseIsNotAvailable(offer, errors)) {
			return true;
		}
		
		
		Specification<Offer,TraceVO> filterOfferByFeasibilitySpec = 
			//	(new CheckFeasibilityResponseIsNotAvailableRule(salesOfferCommonVO))
			//	.and
				(new ProductNotFeasibleRule(salesOfferCommonVO))
				.and(new FilterProductsByNoPortsAvailableRule(salesOfferCommonVO))
				.and(new HsicRankingBetterThenFeasibilityRule(salesOfferCommonVO))
				//.and(new SubscribedHsicPPRankingWorseThenOfferRule(salesOfferCommonVO))
				;

		isSatisfied = filterOfferByFeasibilitySpec.isSatisfiedBy(offer, errors);

		logger.debug("FeasibilityRule", isSatisfied ? "Passed" : "Failed");
		
		return isSatisfied;

	}

	private boolean checkFeasibilityResponseIsNotAvailable(Offer offer, List<TraceVO> traces) {
		boolean isNotAvailable = false;
		
		if(this.salesOfferCommonVO.getCheckFeasibilityResponseVO() == null
				|| this.salesOfferCommonVO.getCheckFeasibilityResponseVO().isMsgHasError()) {
			TraceVO t = TraceVO.newInstance(this);
			t.setRuleName("FeasibilityRule");
			t.setElementType(TraceVO.OFFER);
			t.setOffer(offer);
			t.setReason("Skipping feasibility rules because CheckFeasibilityResponse is not available");

			traces.add(t);

			isNotAvailable = true;

			logger.error("Skipping feasibility rules because CheckFeasibilityResponse is not available");
			return isNotAvailable;
		}
		
		if(salesOfferCommonVO.getCheckFeasibilityResponseVO()!=null && salesOfferCommonVO.getCheckFeasibilityResponseVO().getResponseMessageList()!=null && salesOfferCommonVO.getCheckFeasibilityResponseVO().getResponseMessageList().getResponseMessage()!=null && !salesOfferCommonVO.getCheckFeasibilityResponseVO().getResponseMessageList().getResponseMessage().isEmpty()){
			
			if(!WLNOfferUtil.checkProductFeasibilityContainsNoPortsAvailable(salesOfferCommonVO.getCheckFeasibilityResponseVO()) && !WLNOfferUtil.feasibilityHasTierNotFeasible(salesOfferCommonVO.getCheckFeasibilityResponseVO())){
				TraceVO t = TraceVO.newInstance(this);
				t.setRuleName("FeasibilityRule");
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setReason("Skipping Feasibility check since FeasibilitySvc didn't returned Error for No Ports nor HS Tier not Feasible");

				traces.add(t);

				isNotAvailable = true;

				logger.error("Skipping Feasibility check since FeasibilitySvc didn't returned Error for No Ports nor HS Tier not Feasible");
				return isNotAvailable;

			}else{
				if(WLNOfferUtil.checkProductFeasibilityContainsNoPortsAvailable(salesOfferCommonVO.getCheckFeasibilityResponseVO())){
					salesOfferCommonVO.setNoPortsAvailableInd(true);
				}
			}
			
		}
		return isNotAvailable;
	}
}

