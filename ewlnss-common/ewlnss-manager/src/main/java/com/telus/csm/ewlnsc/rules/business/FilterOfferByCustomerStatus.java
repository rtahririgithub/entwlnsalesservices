package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_UPGRADE;
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

public class FilterOfferByCustomerStatus extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferByCustomerStatus.class);

	private SalesOfferCommonVO commonVO;
	
	public FilterOfferByCustomerStatus(SalesOfferCommonVO commonVO) {
		this.commonVO=commonVO;
	}
	
	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		boolean result = true;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
		
		/**
		 * Checking if the customer has been flagged as 'in-treatment' or 'fraud' from CreditEligibilityResponse
		 */
		boolean customerBadEligibilityInd = WLNOfferUtil.isCustomerBadEligibility(commonVO);
		
		if(customerBadEligibilityInd){
			for(WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()){
				if (!WLNOfferUtil.isNoChangeOfferProduct(product)){
					
					/* 
					 * NWLN-10173 Standalone Device Financing:  Does NOT apply to accessory offers because user can still order accessories if they choose to purchase.
					 and CPE product don't have transaction type. 
					 * */
					boolean isProductAccessory = EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD.equalsIgnoreCase(product.getProductTypeCode());
					
					if (!isProductAccessory && product.getTransactionTypeList()!=null && !product.getTransactionTypeList().isEmpty()){
						for(TransactionType transactionType : product.getTransactionTypeList()){
							if(!OIS_PRODUCT_INSTANCE_UPGRADE.equalsIgnoreCase(transactionType.getTransactionTypeCode())){
								TraceVO t = TraceVO.newInstance(this);
								t.setAction(TraceVO.DELETED);
								t.setElementType(TraceVO.OFFER);
								t.setOffer(o);
								t.setReason("Offer with transactionType: " + transactionType.getTransactionTypeCode() + " and offerId: " + o.getOfferId() + " was filtered out due to customer status has been flagged as 'Fraud' or 'in-treatment', only 'Upgrade' offers are allowed");
								traces.add(t);

								logger.error("Offer with transactionType: " + transactionType.getTransactionTypeCode() + " and offerId: " + o.getOfferId() + " was filtered out due to customer status has been flagged as 'Fraud' or 'in-treatment', only 'Upgrade' offers are allowed");

								result=false;
							}
						}
					}
				}
			}
		}

		logger.debug("FilterOfferByCustomerStatus", result ? "Passed" : "Failed");
		
		return result;
	}

}
