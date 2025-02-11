package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Sweetener;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterProductDiscountByWinbackIndRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterProductDiscountByWinbackIndRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterProductDiscountByWinbackIndRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			List<Discount> discountList = product.getDiscountList();
			product.setDiscountList(null);
			for (Discount discount : discountList) {
				if (isSatisfiedBy(o, product, discount, traces)) {
					product.getDiscountList().add(discount);
				}
			}
		}

		logger.debug("FilterProductDiscountByWinbackIndRule", "Passed");
		
		return true;
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, Discount discount, List<TraceVO> traces) {

		// Exclude winback discount if input winback indc for the product is false or not passed
		OfferProductHeader productRequested = null;
		if (offer instanceof Sweetener) {
			productRequested = salesOfferCommonVO.getOffersRequestVO().getSweetenerProductFromRequest(product.getProductTypeCode());
		} else {
			productRequested = salesOfferCommonVO.getOffersRequestVO().getProductFromRequest(product.getProductTypeCode());
		}
		
		boolean winBackRequested = (productRequested != null && Boolean.TRUE.equals(productRequested.isWinBackInd()));
		if (discount.isWinbackInd() && !winBackRequested) {
			TraceVO t = TraceVO.newInstance(this);
			t.setAction(TraceVO.DELETED);
			t.setElementType(TraceVO.DISCOUNT);
			t.setOffer(offer);
			t.setProduct(product);
			t.setDiscount(discount);
			t.setReason("Discount is for winback only");
			traces.add(t);

			logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because discount is for winback only");

			return false;
		}
		
		return true;
	}

}
