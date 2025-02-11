package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.DiscountProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterSweetenerOfferByAccessoryOfferListIncompatiblePricePlansRule extends AbstractSpecification<Offer,TraceVO> {

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterSweetenerOfferByAccessoryOfferListIncompatiblePricePlansRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(final Offer inputSweetenerOffer, List<TraceVO> traces) {
		// If the offer returned by OIS has incompatible price plans that are found in the incoming from ESS
		// selected price plans, filter out this offer. 
		if ( (salesOfferCommonVO != null) &&
			 (salesOfferCommonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse() != null) &&
			 (salesOfferCommonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().getOfferList() != null) &&
			 (!salesOfferCommonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().getOfferList().isEmpty()) &&
			 (inputSweetenerOffer != null) &&
			 (inputSweetenerOffer.getOfferProduct() != null) &&
			 (inputSweetenerOffer.getOfferProduct().getWirelineOfferProductList() != null) &&
			 (!inputSweetenerOffer.getOfferProduct().getWirelineOfferProductList().isEmpty()) ) {
			for (Offer accessoryOffer : salesOfferCommonVO.getAccessoryOfferListByOfferIdentifierListAdapterResponse().getOfferList()) {
				if ( (accessoryOffer != null) &&
					 (accessoryOffer.getOfferProduct() != null) &&
					 (accessoryOffer.getOfferProduct().getAccessoryOfferProduct() != null) &&
					 (accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList() != null) &&
					 (!accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList().isEmpty()) ) {
					for (ProductCatalogueIdentifier accessoryProductCatalogueIdentifier : accessoryOffer.getOfferProduct().getAccessoryOfferProduct().getIncompatibleDiscountIdList()) {
						if ( (accessoryProductCatalogueIdentifier != null) &&
							 (accessoryProductCatalogueIdentifier.getProductCatalogueId() != null) &&
							 (!accessoryProductCatalogueIdentifier.getProductCatalogueId().isEmpty()) ) {
							for (WirelineOfferProduct sweetenerWirelineOfferProduct : inputSweetenerOffer.getOfferProduct().getWirelineOfferProductList()) {
								if ( (sweetenerWirelineOfferProduct != null) &&
									 (sweetenerWirelineOfferProduct.getDiscountList() != null) &&
									 (!sweetenerWirelineOfferProduct.getDiscountList().isEmpty()) ) {
									for (Discount sweetenerDiscount : sweetenerWirelineOfferProduct.getDiscountList()) {
										if ( (sweetenerDiscount != null) &&
											 (sweetenerDiscount.getDiscountProductCatalogueItemList() != null) &&
											 (!sweetenerDiscount.getDiscountProductCatalogueItemList().isEmpty()) ) {
											for (DiscountProductCatalogueItem sweetenerDiscountProductCatalogueItem : sweetenerDiscount.getDiscountProductCatalogueItemList()) {
												if ( (sweetenerDiscountProductCatalogueItem != null) &&
													 (sweetenerDiscountProductCatalogueItem.getProductCatalogueIdentifier() != null) &&
													 (sweetenerDiscountProductCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId() != null) &&
													 (!sweetenerDiscountProductCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId().isEmpty()) &&
													 (accessoryProductCatalogueIdentifier.getProductCatalogueId().equals(sweetenerDiscountProductCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId())) ) {
													// If the ids are same - filter out that offer.
													TraceVO t = TraceVO.newInstance(this);
													t.setAction(TraceVO.DELETED);
													t.setElementType(TraceVO.OFFER);
													t.setOfferId(String.valueOf(inputSweetenerOffer.getOfferId()));
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
				}
			}
		}
		
		return true;
	}
}
