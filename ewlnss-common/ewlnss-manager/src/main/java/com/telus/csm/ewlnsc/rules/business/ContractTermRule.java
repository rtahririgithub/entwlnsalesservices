package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class ContractTermRule extends AbstractSpecification<Offer,TraceVO> {

	private static final String TERM_ZERO = "0";

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(ContractTermRule.class);
	
	private SalesOfferCommonVO salesOfferCommonVO;
	private WirelineOfferFilter offerFilter;
	private String filterTermReq;
	private Map<String, String> prodTermReqMap;

	public ContractTermRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
		this.offerFilter = salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
	}

	@Override
	public boolean isSatisfiedBy(final Offer o, final List<TraceVO> traces) {
		boolean result = true;
		boolean skip = false;
		
			filterTermReq = offerFilter.getOfferContractTermCd();
			
			final List<WirelineOfferProduct> tomProdList = o.getOfferProduct().getWirelineOfferProductList();
	
			LOGGER.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));
			
			// Bundle Builder redsign - Gary - 2019-07-11 - additional check 'promotionIdentifierCheck'
			if ((TERM_ZERO.equals(filterTermReq) && offerFilter.getProductList().isEmpty()) || this.promotionIdentifierCheck(offerFilter)) {
				// new customer
				if (salesOfferCommonVO.getAssignedAndPendingProductsResponseVO() == null) { // new customer scenario
					for (final WirelineOfferProduct tomProdElem : tomProdList) {
						List<BigInteger> tomTermList = tomProdElem.getContractTermList();
						if (!tomTermList.isEmpty()) {
							String tomTerm = tomTermList.get(0).toString();
							if(!promotionIdentifierCheck(offerFilter)){ //Do not execute logic below for promotionCriteria scenarios
								if ( !TERM_ZERO.equals(tomTerm)) {
									log(o, tomTerm, tomProdElem, traces, "due to different term than 0. offerProdTerm=" + tomTerm);
									result = false;
									break;
								}
							}							
						} else {
							LOGGER.error("Data issue. No term configured in TOM for offerId=" + o.getOfferId() + " and ProductType=" + tomProdElem.getProductTypeCode());
						}
					}
				} else {					
					// 2018 June Exception release for TTV recontracting
					// Fix for QC 65443
					for (final WirelineOfferProduct tomProdElem : tomProdList) {
						if (!WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)){
							List<BigInteger> temTermList = tomProdElem.getContractTermList();
							if (!temTermList.isEmpty()) {
								String tomTerm = temTermList.get(0).toString();
								String existTerm = WLNOfferUtil.getProductTermCodeForExistingCustomer(tomProdElem.getProductTypeCode(), salesOfferCommonVO);
								if (existTerm == null || "MTM".equalsIgnoreCase(existTerm)) {									
									existTerm = TERM_ZERO;
								}
								// existing customer but not eligible for re-contracting
								// existing customer eligibile for partially re-contracting 
							if (!salesOfferCommonVO.isCustomerEligibleForRecontract() || (salesOfferCommonVO.isCustomerEligibleForRecontract()
									&& !WLNOfferUtil.getCustomerEligibleForRecontractingProduct(salesOfferCommonVO).contains(tomProdElem.getProductTypeCode()))) {

								if(TERM_ZERO.equalsIgnoreCase(existTerm)){
									//Do not filter out product if the existing term is Zero, ESS should return all term offers.
									LOGGER.debug("ContractTermRule ->", "Customer has existing MTM plan on product: " + tomProdElem.getProductTypeCode() + " leaving all terms for this product.");
								}else{
									if (!tomTerm.equalsIgnoreCase(existTerm)) {
										log(o, tomTerm, tomProdElem, traces,
												"due to different term than existing prod term. " + tomTerm + "::"
														+ existTerm);
										result = false;
										break;
									}
								}								
							}
							} else {
								LOGGER.error("Data issue. No term configured in TOM for offerId=" + o.getOfferId() + " and ProductType=" + tomProdElem.getProductTypeCode());
							}
						}
					}
				} 
			} else if (filterTermReq == null && !offerFilter.getProductList().isEmpty()) {
				prodTermReqMap = new HashMap<String, String>();
				for (final OfferProductHeader elem : offerFilter.getProductList()) {
					String term = elem.getContractTermCd();
					if (!StringUtils.isEmpty(term)) {
						prodTermReqMap.put(elem.getProductTypeCd(), term);
					}
				}
				
				for (final WirelineOfferProduct tomProdElem : tomProdList) {
					if (!WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)){
						List<BigInteger> termList = tomProdElem.getContractTermList();
						if (!termList.isEmpty()) {
							String tomTerm = termList.get(0).toString();
							String key = tomProdElem.getProductTypeCode();
							
							// if prod term is pass in the request filter out the offer that doesn't have the same term value
							if (prodTermReqMap.get(key) != null && !prodTermReqMap.get(key).equalsIgnoreCase(tomTerm)) {
								result = false;
								log(o, tomTerm, tomProdElem, traces, "due to different term than the one in offerFilter->ProductList. " + tomTerm + "::" + prodTermReqMap.get(key));
								break;
							}
							
							// if product exist filter our the offer if them term is not >= than the existing term
							String existTerm = WLNOfferUtil.getProductTermCodeForExistingCustomer(tomProdElem.getProductTypeCode(), salesOfferCommonVO);
							if (existTerm == null || "MTM".equalsIgnoreCase(existTerm)) {
								existTerm = TERM_ZERO;
							}
							if (!TERM_ZERO.equals(existTerm) && !tomTerm.equals(existTerm)) {
								result = false;
								// NWLN-7598 - update log comments
								log(o, tomTerm, tomProdElem, traces, "due to different term than the one in existing customer product. " + tomTerm + "::" + existTerm);
								break;
							}
						} else {
							LOGGER.error("Data issue. No term configured in TOM for offerId=" + o.getOfferId() + " and ProductType=" + tomProdElem.getProductTypeCode());
						}
					}	
				}
			}
			else {
				LOGGER.debug("ContractTermRule", "Skip this rule due to no productList nor offerContractTerm was passed in the request, offerId=" + o.getOfferId());
				skip = true;
			}
	/*
			final GetAssignedAndPendingProductResponseVO assgnPendRespVO = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
			if (assgnPendRespVO != null) {
				final SalesOfferCriteriaVO offerCriteriaVO = salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria();
				final ServiceAddressVO reqSvcAddr = offerCriteriaVO.getServiceAddress();
				final List<SubscribedServiceIdentifierVO> reqSubscribedSvcIds = offerCriteriaVO.getSubscribedServiceIdentifier();
				
				final List<SubscribedProductInfoRestVO> assignedProductList = assgnPendRespVO.getAssignedProductListByServiceAddressAndServiceId(reqSvcAddr, reqSubscribedSvcIds);
				
				for (final SubscribedProductInfoRestVO subscribedElem : assignedProductList) {
					List<ProductInstanceInfoRestVO> prodInstanceList = subscribedElem.getProductInstance();
					if (prodInstanceList != null) {
						for (ProductInstanceInfoRestVO productInstance : prodInstanceList) {
							String omsOfferCatalogId = productInstance.getOmsOfferCatalogId();
							String existTermCd = productInstance.getTermCd();
							
							LOGGER.debug("isSatisfiedBy", "Existing product=" + subscribedElem.getProductTypeCd() + " has omsOfferCatalogId=" + omsOfferCatalogId + " and termCd=" + existTermCd);
							
							for (final WirelineOfferProduct tomProdElem : tomProdList) {
								if (tomProdElem.getProductTypeCode().equalsIgnoreCase(subscribedElem.getProductTypeCd())) {
									List<BigInteger> tomTermList = tomProdElem.getContractTermList();
									if (!tomTermList.isEmpty()) {
										String tomTerm = tomTermList.get(0).toString();
										if (!TERM_ZERO.equals(existTermCd) && !"MTM".equalsIgnoreCase(existTermCd) && !tomTerm.equalsIgnoreCase(existTermCd)) {
											LOGGER.error("Offer with id: " + o.getOfferId() + " filtered out due to existing product term different than TOM product term." + existTermCd + "::" + tomTerm);
											Trace t = Trace.newInstance(this);
											t.setAction(Trace.DELETED);
											t.setElementType(Trace.OFFER);
											t.setOfferId(Long.toString(o.getOfferId()));
											t.setTerm(tomTerm);
											t.setProductCode(tomProdElem.getProductTypeCode());
											t.setReason("Offer filtered out due to different existing term than the one in TOM product term" + existTermCd + "::" + tomTerm);
											traces.add(t);
											result = false;
											break;
										}
									} else {
										LOGGER.error("Data issue. No term configured in TOM for offerId=" + o.getOfferId() + " and ProductType=" + tomProdElem.getProductTypeCode());
									}
								}
							}						
						}
					}
				}
			}		
	*/		
	
		if(skip){
			LOGGER.debug("ContractTermRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));
		} else {
			LOGGER.debug("ContractTermRule", (result ? "Passed" : "Failed") + ": " + WLNOfferUtil.getRuleContextInformation(o));	
		}
		return result;
	}
	
	/***********************************************/
	/* Bundle Builder redesign - Gary - 2019-07-11 */
	/***********************************************/
	private boolean promotionIdentifierCheck(WirelineOfferFilter offerFilter){
		boolean result = false;
		
		String filterTermReq = offerFilter.getOfferContractTermCd();
		
		/* if filterTermReq is null and offerFilter.productList is null but the promotionIdentifierList is not empty */
		if ( (filterTermReq == null || filterTermReq.trim().length() == 0) && 
		   ( (offerFilter.getProductList() == null) || (offerFilter.getProductList().size() == 0)) &&
		   ( (offerFilter.getPromotionIdentifierList() != null) && (offerFilter.getPromotionIdentifierList().size() > 0)))
		{
			result = true;
		}
		
		LOGGER.debug("ContractTermRule", "promotionIdentifierCheck - " + (result ? "Passed" : "Failed") );
		return result;
	}

	private void log(final Offer o, final String term, final WirelineOfferProduct prod, final List<TraceVO> traces, final String msg) {
		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.OFFER);
		t.setOffer(o);
		t.setTerm(term);
		t.setProduct(prod);
		t.setReason(msg);
		traces.add(t);

		LOGGER.error("Offer id: " + o.getOfferId() + " was filtered out, because " + msg);

	}
}
