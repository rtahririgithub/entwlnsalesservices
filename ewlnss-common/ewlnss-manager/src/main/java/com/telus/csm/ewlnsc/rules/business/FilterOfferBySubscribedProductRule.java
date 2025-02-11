package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterOfferBySubscribedProductRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferBySubscribedProductRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterOfferBySubscribedProductRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			if (!WLNOfferUtil.isNoChangeOfferProduct(product) && !EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(product.getProductTypeCode())) {
				if (!isSatisfiedBy(o, product, traces)) {
					logger.debug("FilterOfferBySubscribedProductRule", "Failed");
					return false;
				}
			}
		}

		logger.debug("FilterOfferBySubscribedProductRule", "Passed");

		return true;
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, List<TraceVO> traces) {
		
		//1. If the customer does have a subscribed product of given product type (eg HSIC), then TOM offers with the "Activation" transaction type will be filtered out..
		//2. If the customer does not have a subscribed product of a given product type (eg HSIC),  then TOM offers with the "Upgrade" transaction type for that product will be filtered out. 

		//TODO need to review the implementation
		// These two rules are implemented by matching the transaction types from the Offer product transaction type against the customer applicable trasnaction type for the product
		
		ArrayList<String> productTranTypes = new ArrayList<String>();
		List<TransactionType> tranType = product.getTransactionTypeList();
		for (TransactionType x : tranType) {
			productTranTypes.add(x.getTransactionTypeCode());
		}
		
		String applicableTranType = WLNOfferUtil.getStatusCodeByProductType(product.getProductTypeCode(), salesOfferCommonVO);
		if (!productTranTypes.contains(applicableTranType)) {
			TraceVO t = TraceVO.newInstance(this);
			t.setAction(TraceVO.DELETED);
			t.setElementType(TraceVO.OFFER);
			t.setOffer(offer);
			t.setProduct(product);
			t.setReason("Offer has transaction type not compatible with the customer subscriber product. Offer Transaction type=" + productTranTypes + "; Customer Transaction type=" + applicableTranType);
			traces.add(t);

			logger.error("Offer id: " + offer.getOfferId() + " was filterred out due to offer has transaction type not compatible with the customer subscriber product. Offer Transaction type=" + productTranTypes + "; Customer Transaction type=" + applicableTranType);

			return false;
		}
		
		return true;
	}

}
