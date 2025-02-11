package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineProductDiscount;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineSalesOrderSummary;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;

public class FilterAccessoryOfferByIncompatiblePricePlansRule extends AbstractSpecification<Offer,TraceVO> {

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterAccessoryOfferByIncompatiblePricePlansRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(final Offer inputOffer, List<TraceVO> traces) {
		// If the offer returned by OIS has incompatible price plans that are found in the incoming from ESS
		// selected price plans, filter out this offer. 
		if ( (salesOfferCommonVO != null) &&
			 (salesOfferCommonVO.getOffersRequestVO() != null) &&
			 (salesOfferCommonVO.getOffersRequestVO().getAccessoryOfferCriteria() != null) &&
			 (salesOfferCommonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList() != null) &&
			 (!salesOfferCommonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList().isEmpty()) &&
			 (inputOffer != null) &&
			 (inputOffer.getOfferProduct() != null) &&
			 (inputOffer.getOfferProduct().getAccessoryOfferProduct() != null) &&
			 (inputOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList() != null) &&
			 (!inputOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList().isEmpty()) ) {
			for (WirelineSalesOrderSummary wirelineSalesOrderSummary : salesOfferCommonVO.getOffersRequestVO().getAccessoryOfferCriteria().getAssociatedWirelineSalesSummaryList()) {
				if ( (wirelineSalesOrderSummary!= null) &&
					 (wirelineSalesOrderSummary.getProductList() != null) &&
					 (!wirelineSalesOrderSummary.getProductList().isEmpty()) ) {
					for (WirelineProductDiscount wirelineProductDiscount : wirelineSalesOrderSummary.getProductList()) {
						if ( (wirelineProductDiscount != null) &&
							 (wirelineProductDiscount.getDiscountProductCatalogueIdList() != null) &&
							 (!wirelineProductDiscount.getDiscountProductCatalogueIdList().isEmpty()) ) {
							for (String discountProductCatalogueId : wirelineProductDiscount.getDiscountProductCatalogueIdList()) {
								for (ProductCatalogueIdentifier productCatalogueIdentifier : inputOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList()) {
									if ( (productCatalogueIdentifier != null) &&
										 (productCatalogueIdentifier.getProductCatalogueId() != null) &&
										 (!productCatalogueIdentifier.getProductCatalogueId().isEmpty()) &&
										 (discountProductCatalogueId != null) &&
										 (!discountProductCatalogueId.isEmpty()) &&
										 (discountProductCatalogueId.equals(productCatalogueIdentifier.getProductCatalogueId())) ) {
										// If the ids are same - filter out that offer.
										TraceVO t = TraceVO.newInstance(this);
										t.setAction(TraceVO.DELETED);
										t.setElementType(TraceVO.OFFER);
										t.setOfferId(String.valueOf(inputOffer.getOfferId()));
										t.setReason("Offer has price plans that are incompatible with the seleted price plans in the ESS request.");
										traces.add(t);

										return false;
									}
								}
							}
						}
					}
				}
			}
		}
		
		return true;
	}
}
