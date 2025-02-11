package com.telus.csm.ewlnsc.rules.business;


import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferWithPikTVRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferWithPikTVRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterOfferWithPikTVRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		//check if the Offer is re-contract eligible to skip the rule
		// 2018 June Exception release for TTV recontracting
		// skip the rule only for like-for-like recontract instead of all recontract scenarios
		//if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(o, salesOfferCommonVO)){
		if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForLikeForLikeRecontracting(o, salesOfferCommonVO)){
			logger.debug("FilterOfferWithPikTVRule", "Passed");
			return true;
		}

		//If any TOM offers have PIK TV product, then filter the offer out.

		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			String templateId = null;
			if (product.getProductTemplateIdentifier() != null) {
				templateId = product.getProductTemplateIdentifier().getExternalProductCatalogId();
			}
			if(WLNOfferUtil.isPikTvCatalogId(templateId)){
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(o);
				t.setProduct(product);
				t.setReason("Offer has PikTV product");
				traces.add(t);

				logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer has PikTV product");

				logger.debug("FilterOfferWithPikTVRule", "Failed");

				return false;
			}
			/*if (OMS_OFFER_CAT_ID_PIK_TV.equalsIgnoreCase(templateId)) {
				Trace t = Trace.newInstance(this);
				t.setAction(Trace.DELETED);
				t.setElementType(Trace.OFFER);
				t.setOffer(o);
				t.setProduct(product);
				t.setReason("Offer has PikTV product");
				traces.add(t);
				return false;
			}*/
		}
		
		logger.debug("FilterOfferWithPikTVRule", "Passed");
		
		return true;
	}

}
