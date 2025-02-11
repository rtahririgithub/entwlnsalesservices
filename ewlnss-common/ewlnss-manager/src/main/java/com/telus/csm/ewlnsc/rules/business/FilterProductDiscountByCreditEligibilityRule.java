package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.adapter.domain.GetCreditEligibilityAdapterResponse;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterProductDiscountByCreditEligibilityRule extends AbstractSpecification<Offer,TraceVO> {

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterProductDiscountByCreditEligibilityRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			List<Discount> discountList = product.getDiscountList();
			product.setDiscountList(null);
			for (Discount discount : discountList) {
				if (isSatisfiedBy(o, product, discount, traces)) {
					product.getDiscountList().add(discount);
				}
			}
		}
		
		return true;
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, Discount discount, List<TraceVO> traces) {

		//If Fraud/In Treatment is TRUE, then filter out any discounts with acquisition products..
		//	Only return discount with upgrade products
		GetCreditEligibilityAdapterResponse creditCheck = salesOfferCommonVO.getCreditEligibilityAdapterResponseVO();
		if (creditCheck != null && (Boolean.TRUE.equals(creditCheck.getFraudInd()) || Boolean.TRUE.equals(creditCheck.getCollectionInd()))) {
			for (TransactionType transactionType : discount.getTransactionTypeList()) {
				//TODO - confirm value
				//TODO - "activation" move to Constants
				if ("activation".equalsIgnoreCase(transactionType.getTransactionTypeCode())) {
					TraceVO t = TraceVO.newInstance(this);
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.DISCOUNT);
					t.setOffer(offer);
					t.setProduct(product);
					t.setDiscount(discount);
					t.setReason("Discount with acquisition products deleted due to bad credit");
					traces.add(t);

					return false;
				}
			}
		}
		
		return true;
	}

}
