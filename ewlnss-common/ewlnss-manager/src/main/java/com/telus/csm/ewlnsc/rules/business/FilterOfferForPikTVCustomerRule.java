package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.STV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;

import java.util.List;

import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferForPikTVCustomerRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferForPikTVCustomerRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterOfferForPikTVCustomerRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		//If customer has PIK TV subscribed, filter out  any TOM offers have PIK TV product, then filter the offer out.
		GetAssignedAndPendingProductResponseVO assignedAndPendingResponseVO = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
		if(assignedAndPendingResponseVO==null){
			logger.debug("FilterOfferForPikTVCustomerRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));

			return true;
		}
		
		//rule not applied if customer is not PikTV 
		if (!WLNOfferUtil.isPresentPikTv(assignedAndPendingResponseVO, salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria())) {
			logger.debug("FilterOfferForPikTVCustomerRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));

			return true;
		}

		// 2018 Aug release for Pik TV
		// filter the offer if they are
		// 1 - STV
		// 2 - Standalone HSIC
		// 3 - TTV + HSIC offer with different templateId
		
		boolean offerContainsHSIC = false;
		boolean offerContainsTTV = false;
		boolean offerContainsSTV = false;
		
		String templateIdTTV = "";
		String templateIdHSIC = "";
		
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			if(HSIC.equalsIgnoreCase(product.getProductTypeCode())){
				offerContainsHSIC = true;
				if (product.getProductTemplateIdentifier() != null) {
					templateIdHSIC = product.getProductTemplateIdentifier().getExternalProductCatalogId();
				}				
			}
			if(TTV.equalsIgnoreCase(product.getProductTypeCode())){
				offerContainsTTV = true;
				if (product.getProductTemplateIdentifier() != null) {
					templateIdTTV = product.getProductTemplateIdentifier().getExternalProductCatalogId();
				}
			}
			if(STV.equalsIgnoreCase(product.getProductTypeCode())){
				offerContainsSTV = true;
			}
		}
		
		if(offerContainsSTV){
			TraceVO t = TraceVO.newInstance(this);
			t.setAction(TraceVO.DELETED);
			t.setElementType(TraceVO.OFFER);
			t.setOffer(o);
			t.setReason("Offer with STV is not available to PIK TV customer");
			traces.add(t);

			logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer with STV is not available to PIK TV customer");

			logger.debug("FilterOfferForPikTVCustomerRule", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));

			return false;
		}
		
		if(offerContainsHSIC){
			if(offerContainsTTV){		// Joined Offer, Bundle Offer, or Coded Based Offer will have offer more than 1 products
				// update WLNOfferUtil method name 
				if(!WLNOfferUtil.getOmsOfferCatalogIdFromConsolidatedAccount(TTV, salesOfferCommonVO).equalsIgnoreCase(templateIdTTV)){
					TraceVO t = TraceVO.newInstance(this);
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.OFFER);
					t.setOffer(o);
					t.setReason("Offer with different ExternalProductCatalogId for TTV is not available to PIK TV customer");
					traces.add(t);

					logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer with different ExternalProductCatalogId for TTV is not available to PIK TV customer");

					logger.debug("FilterOfferForPikTVCustomerRule", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));

					return false;
				}
			} else {					// single HS product in joined offer context should be be filtered
				if(WLNOfferUtil.isJoinedOffers(salesOfferCommonVO.getOffersRequestVO().getOperationHeader()) || 
						WLNOfferUtil.isBundleOffersRequested(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter())) {
					TraceVO t = TraceVO.newInstance(this);
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.OFFER);
					t.setOffer(o);
					t.setReason("Offer with standalone HSIC is not available to PIK TV customer");
					traces.add(t);

					logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer with standalone HSIC is not available to PIK TV customer");

					logger.debug("FilterOfferForPikTVCustomerRule", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));

					return false;
				} else {
					if(!WLNOfferUtil.getOmsOfferCatalogIdFromConsolidatedAccount(HSIC, salesOfferCommonVO).equalsIgnoreCase(templateIdHSIC)){
						TraceVO t = TraceVO.newInstance(this);
						t.setAction(TraceVO.DELETED);
						t.setElementType(TraceVO.OFFER);
						t.setOffer(o);
						t.setReason("HS Split Offer with different HS template identifier for HSIC is not available to PIK TV customer");
						traces.add(t);

						logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer with different template identifier for HSIC is not available to PIK TV customer");

						logger.debug("FilterOfferForPikTVCustomerRule", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));

						return false;
					}
				}
			}
		} else { //no HSIC in offer
			if(WLNOfferUtil.isJoinedOffers(salesOfferCommonVO.getOffersRequestVO().getOperationHeader())|| 
					WLNOfferUtil.isBundleOffersRequested(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter())) { //??
				if(offerContainsTTV){
					TraceVO t = TraceVO.newInstance(this);
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.OFFER);
					t.setOffer(o);
					t.setReason("Offer with TTV without HSIC is not available to PIK TV customer");
					traces.add(t);

					logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer with TTV without HSIC is not available to PIK TV customer");

					logger.debug("FilterOfferForPikTVCustomerRule", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));

					return false;
				}
			}else {
				//If offer has TTV, TTV CID <> existing pikTV CID
				if(offerContainsTTV){
					// update WLNOfferUtil method name 
					if(!WLNOfferUtil.getOmsOfferCatalogIdFromConsolidatedAccount(TTV, salesOfferCommonVO).equalsIgnoreCase(templateIdTTV)){
						TraceVO t = TraceVO.newInstance(this);
						t.setAction(TraceVO.DELETED);
						t.setElementType(TraceVO.OFFER);
						t.setOffer(o);
						t.setReason("Offer with different ExternalProductCatalogId for TTV is not available to PIK TV customer");
						traces.add(t);

						logger.error("Offer id: " + o.getOfferId() + " was filtered out, because offer with different ExternalProductCatalogId for TTV is not available to PIK TV customer");

						logger.debug("FilterOfferForPikTVCustomerRule", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));

						return false;
					}
				}
			}
		}
		
		logger.debug("FilterOfferForPikTVCustomerRule", "Passed" + ": " + WLNOfferUtil.getRuleContextInformation(o));
		
		return true;
	}
}
