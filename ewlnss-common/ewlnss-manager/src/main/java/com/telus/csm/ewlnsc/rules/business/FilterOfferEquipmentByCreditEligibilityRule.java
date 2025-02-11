package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.PaymentOption;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipment;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineEquipmentItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferEquipmentByCreditEligibilityRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferEquipmentByCreditEligibilityRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterOfferEquipmentByCreditEligibilityRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		//check if the Offer is re-contract eligible to skip the rule
		
		// 2018 June Exception release for TTV recontracting
		// skip the rule only for like-for-like recontract instead of all recontract scenarios
		//if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(o, salesOfferCommonVO)){

		GetCreditEligibilityAdapterResponse creditCheck = salesOfferCommonVO.getCreditEligibilityAdapterResponseVO();
		
		if (creditCheck != null && (Boolean.TRUE.equals(creditCheck.getFraudInd()) || Boolean.TRUE.equals(creditCheck.getCollectionInd()))) {
			for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
				/*
				 * NWLN-10173 logic for accessories - Modify this rule to filter out the offer for fraud/in treatment/restricted customers.
				 * */
				if (EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(product.getProductTypeCode())) {
						TraceVO t = TraceVO.newInstance(this);
						t.setAction(TraceVO.DELETED);
						t.setElementType(TraceVO.OFFER);
						t.setOffer(o);
						t.setReason("Accessory offer was filtered out because of bad credit, offerId: " + o.getOfferId());
						traces.add(t);
						logger.error("Accessory Offer with id: " + o.getOfferId() + " was filtered out, because of bad credit");
						logger.debug("FilterOfferEquipmentByCreditEligibilityRule", "Failed");
						return false;
				} else {
					/*
					 * logic for non-accessories: i.e. TV/HS
					 * */
					if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForLikeForLikeRecontracting(o, salesOfferCommonVO)){
						return true;
					}
					//If Fraud/In Treatment is TRUE, then filter out all rental HS modems and TV STBs/PVRs (standard equipment). 
					if (!WLNOfferUtil.isNoChangeOfferProduct(product)){
						List<WirelineEquipment> equipmentList = product.getWirelineEquipmentList();
						for (WirelineEquipment x : equipmentList) {
							//filter rental equipment
							List<WirelineEquipmentItem> rentalList = x.getRentalEquipmentList();
							x.setRentalEquipmentList(null);
							for (WirelineEquipmentItem rentalItem : rentalList) {
								TraceVO t = TraceVO.newInstance(this);
								t.setAction(TraceVO.DELETED);
								t.setElementType(TraceVO.EQUIPMENT);
								t.setOffer(o);
								t.setProduct(product);
								t.setEquipmentItem(rentalItem);
								t.setReason("Rental equipment deleted due to bad credit");
								traces.add(t);

								logger.error("Offer id: " + o.getOfferId() + " was filtered out, because rental equipment deleted due to bad credit");
							}
						}	
					}
				}
			}
			
		}
		
		logger.debug("FilterOfferEquipmentByCreditEligibilityRule", "Passed");

		return true;
	}
}
