package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ess.rest.domain.AccessoryOffer;
import com.telus.csm.ewlnsc.domain.CartItemVO;
import com.telus.csm.ewlnsc.domain.GetOffersResponseVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;

public class ValidateAccessoryOfferRule extends AbstractSpecification<CartItemVO,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(ValidateAccessoryOfferRule.class);

	private GetOffersResponseVO getOffersResponseVO;

	public ValidateAccessoryOfferRule(GetOffersResponseVO getOffersResponseVO) {
		this.getOffersResponseVO = getOffersResponseVO;
	}

	@Override
	public boolean isSatisfiedBy(CartItemVO salesItemVO, List<TraceVO> traces) {

		boolean offerNotFoundInTom = true;
//TODO Petru		
//		for(AccessoryOffer accessoryOffer : salesItemVO.getCartItem().getAccessoryOfferItem().getSelectedAccessoryOffers()) {
//			String accessoryOfferId = accessoryOffer.getOfferHeader().getOfferId() + "";
//			
//			boolean offerFoundInTom = false;
//			
//			for(com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.AccessoryOffer tomAccessoryOffer : getOffersResponseVO.getAccessoryOfferSummaryList()) {
//				if(accessoryOfferId.equalsIgnoreCase(tomAccessoryOffer.getOfferHeader().getOfferId())){
//					offerFoundInTom = true;
//				}
//			}
//			
//			if(!offerFoundInTom) {
//				offerNotFoundInTom = false;
//				
//				Trace t = Trace.newInstance(this);
//				t.setAction(Trace.OFFER);
//				t.setElementType(Trace.OFFER);
//				t.setOfferId(accessoryOfferId);
//				t.setRuleName("ValidateAccessoryOfferRule");
//				t.setReason(EnterpriseWLNSalesServicesConstants.OFFER_VALIDATION_RULE_MESSAGE);
//				traces.add(t);
//
//				logger.error("Offer id: " + accessoryOfferId + " was not found in TOM");
//			}
//		}

		return offerNotFoundInTom;
	}

}
