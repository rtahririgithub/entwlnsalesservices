package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.STV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferByPendingProductRule extends AbstractSpecification<Offer,TraceVO> {

	private SalesOfferCommonVO salesOfferCommonVO;
	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferByPendingProductRule.class);

	public FilterOfferByPendingProductRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		//check if the Offer is re-contract eligible to skip the rule
		
		// 2018 June Exception release for TTV recontracting
		// skip the rule only for like-for-like recontract instead of all recontract scenarios
		//if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(o, salesOfferCommonVO)){
		//October 18,2018 - Enable this rule again for all recontract scenarios.
//		if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForLikeForLikeRecontracting(o, salesOfferCommonVO)){
//			logger.debug("FilterOfferByPendingProductRule", "Skipping rule due to customer and offer are eligible for re-contracting.");
//			return true;
//		}
		
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			if (!isSatisfiedBy(o, product, traces)) {
				logger.debug("FilterOfferByPendingProductRule", "Failed");

				return false;
			}
		}
		
		logger.debug("FilterOfferByPendingProductRule", "Passed");

		return true;
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, List<TraceVO> traces) {
		

		//1. When there is a pending order for SING then
		//		Omit any TOM offer that contains a SING product.

		if (WLNOfferUtil.hasPendingProduct(SING, salesOfferCommonVO)) {
			if (offerHasProduct(offer, SING)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a SING product is not available to customer with pending SING product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because offer contains a SING product is not available to customer with pending SING product");

				return false;
			}
		}

		
		//2. When there is a pending order for STV then
		//		Omit any TOM offer that contains a STV product.

		if (WLNOfferUtil.hasPendingProduct(STV, salesOfferCommonVO)) {
			if (offerHasProduct(offer, STV)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a STV product is not available to customer with pending STV product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a STV product is not available to customer with pending STV product");

				return false;
			}
		}

		
		//3. When there is a pending order for HSIC or TTV then
		//		Omit any TOM offer that contains a HSIC product.
		//		Omit any TOM offer that contains a TTV product.
		//   But keep offer when customer and offer is eligible for recontracting 

		if (WLNOfferUtil.hasPendingProduct(HSIC, salesOfferCommonVO) || WLNOfferUtil.hasPendingProduct(TTV, salesOfferCommonVO)) {
			// 2018 June Exception release for TTV recontracting
			// skip the rule only for like-for-like recontract instead of all recontract scenarios
			if (!(salesOfferCommonVO.isCustomerEligibleForRecontract() && WLNOfferUtil.isOfferForLikeForLikeRecontracting(offer, salesOfferCommonVO))) {
				if (offerHasProduct(offer, HSIC)) {
					TraceVO t = TraceVO.newInstance(this);
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.OFFER);
					t.setOffer(offer);
					t.setProduct(product);
					t.setReason("Offer contains a HSIC product is not available to customer with pending HSIC or TTV product");
					traces.add(t);

					logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a HSIC product is not available to customer with pending HSIC or TTV product");

					return false;
				}
				if (offerHasProduct(offer, TTV)) {
					TraceVO t = TraceVO.newInstance(this);
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.OFFER);
					t.setOffer(offer);
					t.setProduct(product);
					t.setReason("Offer contains a TTV product is not available to customer with pending HSIC or TTV product");
					traces.add(t);

					logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a TTV product is not available to customer with pending HSIC or TTV product");

					return false;
				}
			}
		}


		//4. if OPTIK is pending, filter out HSIC or TTV
		//   Also do not include recontracting offer    
		if (WLNOfferUtil.hasPendingOPTIKProduct(salesOfferCommonVO)) {
			if (offerHasProduct(offer, HSIC)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a HSIC product is not available to customer with pending OPTIK product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a HSIC product is not available to customer with pending OPTIK product");

				return false;
			}
			if (offerHasProduct(offer, TTV)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a TTV product is not available to customer with pending OPTIK product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a TTV product is not available to customer with pending OPTIK product");

				return false;
			}
		}

		
		//5. When there is a pending order for PIK TV TVX then
		//		Omit any TOM offer that contains a HSIC product.
		//		Omit any TOM offer that contains a TTV product.
		//		Omit any TOM offer that contains a STV product.

		if (WLNOfferUtil.isPendingPikTv(salesOfferCommonVO.getAssignedAndPendingProductsResponseVO(), salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria(),salesOfferCommonVO.getServiceAddressResponseVO())) {
			if (offerHasProduct(offer, HSIC)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a HSIC product is not available to customer with pending TVX product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a HSIC product is not available to customer with pending TVX product");

				return false;
			}
			if (offerHasProduct(offer, TTV)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a TTV product is not available to customer with pending TVX product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a TTV product is not available to customer with pending TVX product");

				return false;
			}
			if (offerHasProduct(offer, STV)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setProduct(product);
				t.setReason("Offer contains a STV product is not available to customer with pending TVX product");
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " filtered out, because offer contains a STV product is not available to customer with pending TVX product");

				return false;
			}
		}

		return true; 
	}

	private boolean offerHasProduct(Offer o, String productType) {

		for (WirelineOfferProduct offerProduct : o.getOfferProduct().getWirelineOfferProductList()) {
			if (offerProduct.getProductTypeCode().equalsIgnoreCase(productType)) {
				return true;
			}
		}
		
		return false;
	}

}
