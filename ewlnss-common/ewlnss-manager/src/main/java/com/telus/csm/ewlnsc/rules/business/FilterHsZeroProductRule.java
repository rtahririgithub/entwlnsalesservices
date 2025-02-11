package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;

import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.TransactionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;


/**
 * 
 * Filter out offer when
 *    1 - request is activation
 *    2 - request has TTV and HSIC
 *    3 - offer has HSZERO
 *    
 * @author x159148
 *
 */
public class FilterHsZeroProductRule extends AbstractSpecification<Offer,TraceVO> {

 
	private WirelineOfferFilter offerFilter;
	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterHsZeroProductRule.class);
	

	public FilterHsZeroProductRule(SalesOfferCommonVO salesOfferCommonVO) { 
		this.offerFilter        = salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));
		
		for (WirelineOfferProduct product : offer.getOfferProduct().getWirelineOfferProductList()) {
			if (!isSatisfiedBy(offer, product, traces)) {
				 
				logger.debug("FilterHsZeroProductRule", "Failed");
				log(offer, traces, "Offer filtered out due to (1) Not activation or (2) does not have both TTV and HSIC or (3) OIS does not have HSZERO.");
				return false;
			}
		}
		
		logger.debug("FilterHsZeroProductRule", "Passed");

		return true;
	}
	
	/************************************/
	/* perform rules validation         */
	/************************************/
	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, List<TraceVO> traces) {
		
		/**
		 * filter out offer if
		 *    1 - is activation
		 *    2 - request has TTV and HSIC
		 *    3 - tom offer has HSZERO
		 */
		boolean result = true;
		
		if (this.isActvation(product) && 
			this.hasHsicTtvInRequest(offerFilter) &&
			this.isOfferHasHsZero(offer))
		{
			result = false;
		} 

		return result;
		
	}
	
	/************************************/
	/* is an activation request?        */
	/************************************/
	private boolean isActvation(WirelineOfferProduct product){
		boolean result = false;
		
		if ( (product != null) &&
			 (product.getTransactionTypeList() != null) &&
			 (product.getTransactionTypeList().size() > 0))
		{
			for (TransactionType transactionType : product.getTransactionTypeList()){
				if (EnterpriseWLNSalesServicesConstants.OIS_PRODUCT_INSTANCE_ACTIVATION.equalsIgnoreCase(transactionType.getTransactionTypeCode())){
					result = true;
				}
			}
		}
		
		logger.debug("FilterHsZeroProductRule", "isActivation? = " + result);
		return result;
	}
	
	/************************************/
	/* is request has TTV and HSIC      */
	/************************************/
	private boolean hasHsicTtvInRequest(WirelineOfferFilter offerFilter){
		boolean result  = false;
		boolean hasHsic = false;
		boolean hasTtv  = false;
		
		if ( ( offerFilter != null) &&
		     ( offerFilter.getProductList() != null) &&
		     ( offerFilter.getProductList().size() > 0)) 
		{
			for (OfferProductHeader product : offerFilter.getProductList()){
				if (TTV.equalsIgnoreCase(product.getProductTypeCd())){
					hasTtv = true;
				} else if (HSIC.equalsIgnoreCase(product.getProductTypeCd())){
					hasHsic = true;
				}
			}
			
			result = hasHsic && hasTtv;
		}
		
		logger.debug("FilterHsZeroProductRule", "hasHsic? = " + hasHsic + " hasTtv? = " + hasTtv);
		return result;
	}


	
	
	/************************************/
	/* TOM offer has HSZERO ?           */
	/************************************/
	private boolean isOfferHasHsZero(Offer offer) {
		
		boolean result = false;
		String  offerTemplateId  = null;
		String  omsCode          = ""; 
		
		offerTemplateId = WLNOfferUtil.getHsicExternalProductCatalogueId(offer);
		if (offerTemplateId != null){
			omsCode         = WLNOfferUtil.mapOmsCode(offerTemplateId);
			if (Constants.HSZERO.equalsIgnoreCase(omsCode)){
				result = true;
			}
		}
		
		logger.debug("FilterHsZeroProductRule", "offer has HSZERO ? = " + result);
		
		return result;
	}
	
	private void log(final Offer o, final List<TraceVO> traces, final String msg) {
		logger.error("Offer id: " + o.getOfferId() + " was filterred out, because " + msg);

		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.OFFER);
		t.setOffer(o);
		t.setReason(msg);
		traces.add(t);
	}
}
