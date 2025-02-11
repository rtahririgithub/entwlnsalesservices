package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.CPE;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

// NWLN-7598 - new rule to filter out regular HS offer, without Mediaroom templateId, when customer activate Mediaroom TTV or Pik TV 
// Pre-Condition
// -	Only run the rule for split offer, bundleInd is false, offer does not contain both HS and TTV
// -	Only run when product list if the request contains TTV
// -	Only run for new customer or existing customer with HS and/or SL and/or STV
// -	Only run for getAvailableOfferSummary with product list in the request
// -	Do not run for existing customer with TTV
// -	Do not run for SL offer


/**
 * Bundle Builder Redesign - 2019-07-11 - Gary
 *  - ESS will no longer contain the productComponentList 
 *  - Relax condition that checks the productCatalogIds from the request vs the productCatalogIds 
 *    returned for the TTV products and always populate the templateIdTTVFromOffer
 */

public class FilterOfferByOfferTemplateId  extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(FilterOfferByRequestProductListRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;
	private WirelineOfferFilter offerFilter;
	private Map<String,List<WirelineOfferProduct>> offerProductsMap;
		
	public FilterOfferByOfferTemplateId(SalesOfferCommonVO salesOfferCommonVO, Map<String,List<WirelineOfferProduct>> offerProductsMap){
		this.salesOfferCommonVO = salesOfferCommonVO;
		this.offerFilter = salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
		this.offerProductsMap = offerProductsMap;
	}
	
	@Override
	public boolean isSatisfiedBy(final Offer o, final List<TraceVO> traces) {
		boolean result = true;
		
		// NWLN-7598 - only run the rule for split offer, bundleInd is false, offer does not contain both HS and TTV 
		if(WLNOfferUtil.isJoinedOffers(salesOfferCommonVO.getOffersRequestVO().getOperationHeader()) || 	// Joined Offers
				(offerFilter.isBundleInd()!=null && offerFilter.isBundleInd()) ||							// Bundle Offers
				WLNOfferUtil.isValidMultipleTTVProductOffer(o)) {											// Split offers but also have multiple products including HS and TTV
			LOGGER.debug("FilterOfferByOfferTemplateId", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));

			return true;
		}
		
		// NWLN-7598 - create a map to contains all the productCatalogueIdentifier from each product in the request
		Map<String,List<String>> prodReqMap = new HashMap<String,List<String>>(); 
		for (final OfferProductHeader elem : offerFilter.getProductList()) {
			if (!StringUtils.isEmpty(elem.getProductTypeCd())) {
				
				// Bundle Builder redesign - Gary - 2019-07-11 - ESS request has no productComponentList anymore
				List<String> productCatalogueIdentifierList = new ArrayList<String>();
//				for(ProductComponentIdentifier productComponentIdentifier: elem.getProductComponentList()){
//					productCatalogueIdentifierList.add(productComponentIdentifier.getProductCatalogueIdentifier());
//				}
				
				prodReqMap.put(elem.getProductTypeCd(), productCatalogueIdentifierList);
			}
		}
		
		// NWLN-7598 - do not run for SL offer
		if(prodReqMap.keySet().size() == 1 && prodReqMap.keySet().contains(SING)){
			LOGGER.debug("FilterOfferByOfferTemplateId", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));

			return true;
		}
		
		boolean offerHasHS = false;
		boolean offerHasTTV = false;
		boolean offerHasSING = false;
		boolean offerHasCPE = false;
		
		String templateIdHSIC = "";
		String templateIdTTV = "";
		String templateIdCPE = "";
		
		// NWLN-7598 - only run when product list if the request contains TTV
		// NWLN-7598 - do not run for existing customer with TTV
		if(prodReqMap.keySet().contains(TTV) && !WLNOfferUtil.isTTVExists(salesOfferCommonVO)){
			// NWLN-7598 - find out what template Id is from the offer
			for(WirelineOfferProduct wirelineOfferProduct: o.getOfferProduct().getWirelineOfferProductList()){
				if(wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(HSIC)){
					offerHasHS = true;
					templateIdHSIC = wirelineOfferProduct.getProductTemplateIdentifier().getExternalProductCatalogId();
				} else if(wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(TTV)){
					offerHasTTV = true;
					templateIdTTV = wirelineOfferProduct.getProductTemplateIdentifier().getExternalProductCatalogId();
				} else if(wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(SING)){
					offerHasSING = true;
				} else if(wirelineOfferProduct.getProductTypeCode().equalsIgnoreCase(CPE) && wirelineOfferProduct.getProductTemplateProductTypeCode().equalsIgnoreCase(HSIC)){
					offerHasCPE = true;
					templateIdCPE = wirelineOfferProduct.getProductTemplateIdentifier().getExternalProductCatalogId();
				}
			}
			
			// NWLN-7598 - do not run for SL offer
			if(offerHasSING && !offerHasHS && !offerHasTTV && !offerHasCPE){
				LOGGER.debug("FilterOfferByOfferTemplateId", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));

				return true;
			}
			
			// NWLN-7598 - loop through all the reminding offers after all rules has been process, find out all the template Id of TTV product
			List<String> templateIdTTVFromOffer = new ArrayList<String>();
			if(!offerProductsMap.isEmpty()) {
				Set<String> offerIdList = offerProductsMap.keySet();
				for(String offerIdFromProductsMap : offerIdList) {
					
					// Bundle Builder resign - Gary - 2019-07-11 - not use the productCatalogueId anymore, not need for the following
//					List<String> productCatalogIdList = new ArrayList<String>();
					
					List<WirelineOfferProduct> offerProductList = offerProductsMap.get(offerIdFromProductsMap);
					for(WirelineOfferProduct offerProduct : offerProductList) {
						if(TTV.equals(offerProduct.getProductTypeCode())){
//							for(ProductComponent productComponent: offerProduct.getProductComponentList()){
//								for(ProductCatalogueItem productCatalogueItem: productComponent.getProductCatalogueItemList()){
//									if(productCatalogueItem.getProductCatalogueIdentifier() != null){
//										productCatalogIdList.add(productCatalogueItem.getProductCatalogueIdentifier().getProductCatalogueId());	
//									}
//								}
//							}
							
							// Bundle Builder resign - Gary - 2019-07-11 - Since prodReqMap.value will be empty, there will be nothing to remove
//							productCatalogIdList.removeAll(prodReqMap.get(TTV));
														
							// Bundle Builder resign - Gary - 2019-07-11 - always populate the templateIdTTVFromOffer
//							if(productCatalogIdList.isEmpty() &&
							if (offerProduct.getProductTemplateIdentifier() != null && 
								offerProduct.getProductTemplateIdentifier().getExternalProductCatalogId() != null){
								templateIdTTVFromOffer.add(offerProduct.getProductTemplateIdentifier().getExternalProductCatalogId());	
							}							
						}
					}
				}
			}
			
			// NWLN-7598 - if the current offer has HS, but the templateId is not matching with any TTV templateId from the offer list, filter the current offer out
			if(offerHasHS && !templateIdTTVFromOffer.contains(templateIdHSIC)){
				log(o, traces, "HS Offer filtered out due to because its template id didn't match with any TTV template id in the list.");

				LOGGER.debug("FilterOfferByOfferTemplateId", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));
				return false;
			}
			
			if(offerHasCPE && !templateIdTTVFromOffer.contains(templateIdCPE)){
				log(o, traces, "CPE Offer filtered out due to because its template id didn't match with any TTV template id in the list.");

				LOGGER.debug("FilterOfferByOfferTemplateId", "Failed" + ": " + WLNOfferUtil.getRuleContextInformation(o));
				return false;
			}
		}
		
		return result;
	}
	
	private void log(final Offer o, final List<TraceVO> traces, final String msg) {
		LOGGER.error("Offer id: " + o.getOfferId() + " was filterred out, because " + msg);

		TraceVO t = TraceVO.newInstance(this);
		t.setAction(TraceVO.DELETED);
		t.setElementType(TraceVO.OFFER);
		t.setOffer(o);
		t.setReason(msg);
		traces.add(t);
	}
}
