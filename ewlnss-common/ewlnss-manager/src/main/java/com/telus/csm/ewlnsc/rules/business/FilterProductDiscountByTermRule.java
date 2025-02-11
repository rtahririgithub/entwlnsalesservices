package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE;

import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterProductDiscountByTermRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterProductDiscountByTermRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public FilterProductDiscountByTermRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			
			/*NWLN-10173 Skip for accessory product type because CPE product don't have term*/
			if (EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(product.getProductTypeCode())) {
				return true;
			}
			
			if (!WLNOfferUtil.isNoChangeOfferProduct(product)) {
				List<Discount> discountList = product.getDiscountList();
				if(discountList == null) {
					continue;
				}
				product.setDiscountList(null);
				for (Discount discount : discountList) {
					if (isSatisfiedBy(o, product, discount, traces)) {
						product.getDiscountList().add(discount);
					}
				}
			}
		}
		
		logger.debug("FilterProductDiscountByTermRule", "Passed");
		
		return true;
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, Discount discount, List<TraceVO> traces) {
		
		// Return discount that matches the input transactionType + Term + productType.
		OfferProductHeader requestedProduct = salesOfferCommonVO.getOffersRequestVO().getProductFromRequest(product.getProductTypeCode());
		
		//Check product
		if (requestedProduct == null) {
			TraceVO t = TraceVO.newInstance(this);
			t.setAction(TraceVO.DELETED);
			t.setElementType(TraceVO.DISCOUNT);
			t.setOffer(offer);
			t.setProduct(product);
			t.setDiscount(discount);
			t.setReason("Discount on product not requested");
			traces.add(t);

			logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because discount on product not requested");

			return false;
		}
		
		// 2018 June Exception release for TTV recontracting
		//Return promotions (discount list) for recontracting eligible products if the promotion recontracting indicator is TRUE. 
		//The promotions for upgrade products will still be returned if the recontracting indicator for the promotion is FALSE.
		Collection<String> transactionTypes = getTransactionTypes(discount);
		
		if(salesOfferCommonVO.isCustomerEligibleForRecontract() &&  WLNOfferUtil.isOfferForRecontracting(offer, salesOfferCommonVO) && 
		   WLNOfferUtil.getRecontractEligibleProductCodeList(offer, salesOfferCommonVO, false).contains(product.getProductTypeCode()) &&
		   (Boolean.TRUE.equals(discount.isRecontractingInd()) || transactionTypes.contains(OIS_PRODUCT_INSTANCE_UPGRADE)) ){
			//hard-coded to include this discount
		} else {
			//check transaction type
			String applicableTranType = WLNOfferUtil.getStatusCodeByProductType(product.getProductTypeCode(), salesOfferCommonVO);
				
			if (applicableTranType != null && !transactionTypes.contains(applicableTranType)) {
				TraceVO t = TraceVO.newInstance(this);
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.DISCOUNT);
				t.setOffer(offer);
				t.setProduct(product);
				t.setDiscount(discount);
				t.setReason("Discount transaction type: " + transactionTypes + " not match with request: " + applicableTranType);
				traces.add(t);
	
				logger.error("Offer with id: " + offer.getOfferId() + "filtered out, because discount transaction type: " + transactionTypes + " not match with request: " + applicableTranType);
	
				return false;			
			}
		}
		
// Bundle Builder redesign = Gary - 2019-07-11 - productList will not have discount
		//check term
//		Collection<Integer> discountTerms = getTerms(discount);
//		Integer requestedTerm;
//		try {
//			requestedTerm = Integer.parseInt(requestedProduct.getContractTermCd());
//		} catch (NumberFormatException e) {
//			requestedTerm = 0;
//		}
//		
//		if (!discountTerms.contains(requestedTerm)) {
//			TraceVO t = TraceVO.newInstance(this);
//			t.setAction(TraceVO.DELETED);
//			t.setElementType(TraceVO.DISCOUNT);
//			t.setOffer(offer);
//			t.setProduct(product);
//			t.setDiscount(discount);
//			t.setReason("Discount does not apply to requested term");
//			traces.add(t);
//
//			logger.error("Offer with id: " + offer.getOfferId() + "filtered out, because discount does not apply to requested term");
//
//			return false;			
//		}
		
		return true;
	}

//	private Collection<Integer> getTerms(Discount discount) {
//		HashSet<Integer> result = new HashSet<Integer>();
//		
//		for (BigInteger term : discount.getContractTermList()) {
//			result.add(term.intValue());
//		}
//		return result;
//	}

	private Collection<String> getTransactionTypes(Discount discount) {
		HashSet<String> result = new HashSet<String>();
		
		for (TransactionType transactionType : discount.getTransactionTypeList()) {
			result.add(transactionType.getTransactionTypeCode());
		}
		return result;
	}
}
