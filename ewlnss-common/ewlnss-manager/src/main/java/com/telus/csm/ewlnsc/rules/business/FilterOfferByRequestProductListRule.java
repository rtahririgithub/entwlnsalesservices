package com.telus.csm.ewlnsc.rules.business;

import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.SING;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.TTV;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.HSIC;
import static com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants.OIS_ACCESSORIES_CD;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.Constants;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.TransactionalProduct;
import com.telus.tmi.xmlschema.srv.mso.massmarketsalesmgmt.offerinformationsvcrequestresponse_v3.WirelineTransactionalContext;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.ProductComponentIdentifier;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Category;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferCategory;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

/**
* this rule is to check the request product matches the tom offer product
* for omni, this rule is not applied originally before 2019 offer split project 
* for joined offer, 2.if TV input cid can be found, input products cid must exactly match all tom offer products cid.
*                   3. if HS input cid can be found, tom offer cid for HS must be one of them
*                   1. any tom product cid must be in the request 
                    4.if input has both HS/TV, tom should have both HS/TV too (before split offer)
* for split offer, we will apply the same rules except for No.4 
* 
**/

/**
 * BundleBuilder redesign - 2019-07-11 - Gary
	- For the scenario in which only TTV is present in the productList, 
			but the request to OIS contains HSIC

			- The rule will iterate over the products returned from the offer and if the offer 
				contains products that are not in the productList, then it will filter out the offer. 
				
			- This has to be relaxed for the scenario in which there is TTV only in the request, 
				but the call to OIS included HS Zero.

				- Use existing method from WLNOfferUtil.mapOmsCode(offerTemplateId) to get the Tier Cd and if the tier 
					code is HSZERO, then do not filter out the rule.


	- For the scenario in which there are TTV products returned from the offer and TTV 
		product is on the productList but there is no productComponentList being passed into ESS
			- This has to be relaxed to avoid checking the productComponentList since we will no longer 
				have the productComponentList on the ESS request
					
	- Remove code for bundle and joined offers since this is no longer supported.
 */
public class FilterOfferByRequestProductListRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil LOGGER = LoggerUtil.getLogger(FilterOfferByRequestProductListRule.class);
	
//	private static final String OFFER_CATEGORY_TYPE = "OFFER_CATEGORY";
//	private static final String OFFER_CATEGORY_BUNDLE_CODE = "Bundle";
	
	private SalesOfferCommonVO salesOfferCommonVO;
	private WirelineOfferFilter offerFilter;
//	private boolean offerFilterBundleInd;
	
//	Bundle Builder redesign - Gary - 2019-07-11 no more productComponentList
//	private Map<String, Set<String>> prodReqMap; // map fro prod code and prod comp set 
	
	public FilterOfferByRequestProductListRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
		this.offerFilter = salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
	}

	@Override
	public boolean isSatisfiedBy(final Offer offer, final List<TraceVO> traces) {
		boolean result = true;

		LOGGER.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));
		

// 	Bundle Builder redesign - Gary - 2019-07-11 - no more bundle/join offer		
//                                 - only check if offerFilter.getProductList is empty
//		if ((offerFilter.isBundleInd()==null || !offerFilter.isBundleInd()) &&  CollectionUtils.isEmpty(offerFilter.getProductList())) {
		if (CollectionUtils.isEmpty(offerFilter.getProductList())) { 
			LOGGER.debug("FilterOfferByRequestProductListRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(offer));

			return true;
		}
		
		
		
		// Step 3: TOM offer is not eligible if the requested offerFilter productList does not 
		// contain a product type that the TOM offer contains.
		// TODO Petru for existing offer we need to revisit
		final Set<String> prodReqSet = new HashSet<String>();
		for (final OfferProductHeader elem : offerFilter.getProductList()) {
			if (!StringUtils.isEmpty(elem.getProductTypeCd())) {
				prodReqSet.add(elem.getProductTypeCd());
			}
			

		}

		// if the offerFilter productList includes both HSIC and TTV products, then the TOM offer 
		// that contain HSIC Product but do not contain TTV product will be filtered 
		// out (this is to suppress the stand-alone HS offer which would disconnect the TTV)
		// NWLN-7598 - remove the logic for Join Offer and bundle offer, the rule will apply to both Join Offer and split offer
		if (!prodReqSet.isEmpty()) {
			boolean bothHsicAndTtvInd = false;
			if (prodReqSet.contains(HSIC) && prodReqSet.contains(TTV)) {
				bothHsicAndTtvInd = true;
			}
			boolean hsicFound = false;
			boolean ttvFound = false;
			boolean accessoryFound = false;
			final List<WirelineOfferProduct> tomProdList = offer.getOfferProduct().getWirelineOfferProductList(); 
			for (final WirelineOfferProduct tomProdElem : tomProdList) {
				//if (!WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)){
					String tomProdType = tomProdElem.getProductTypeCode();
					//GM 2018-10-17 modify rule not to filter out offer if the product is not in the cart and the offer has the product as no change 
					
					//BundleBuilder Redesign - Gary - 2019-07-11 - Do not filter out any HSZERO and HSIC must not be existing for the customer
					if ( (!prodReqSet.contains(tomProdType)/* && !WLNOfferUtil.isNoChangeOfferProduct(tomProdElem) */) 
						  &&
						 ( this.checkTtvHsciHszero(offer, this.offerFilter, this.salesOfferCommonVO)))  
					{
						log(offer, traces, "Offer filtered out due to product type not in the request and the offer product is not no-change.");
	
						LOGGER.debug("FilterOfferByRequestProductListRule", "Failed");
	
						return false;
					}
					
					
					if (HSIC.equals(tomProdType)) {
						hsicFound = true;
					}
					if (TTV.equals(tomProdType)) {
						ttvFound = true;
						// When the request offerFilter productList element contains TTV product AND productList.productComponent is 
						// specified, TOM Offers that contains a TTV product but does not contain the exact same list of product-plans 
					} 
					if (OIS_ACCESSORIES_CD.equals(tomProdType)) {
						accessoryFound = true;
					}
					
			}
			
			//NWLN-10173 this logic is not applied to filter accessory
			if (!accessoryFound) {
				// NWLN-7598 - add the logic to filter out offer without HSIC or TTV if request have both HSIC and TTV but not SING
				if(bothHsicAndTtvInd && !prodReqSet.contains(SING) && !hsicFound && !ttvFound){
					log(offer, traces, "Offer filtered out due to offer do not have either HSIC and TTV and the request did not include SING prod type");

					LOGGER.debug("FilterOfferByRequestProductListRule", "Failed");

					return false;
				}
			}
		}
		
		LOGGER.debug("FilterOfferByRequestProductListRule", (result ? "Passed" : "Failed") + ": " + WLNOfferUtil.getRuleContextInformation(offer));
		return result;
	}
	

	/***************************************************************************/   
	/* be relaxed for the scenario in which there is TTV only in the request,  */
	/* but the call to OIS included HS Zero.                                   */
	/***************************************************************************/
	private boolean checkTtvHsciHszero(Offer offer, WirelineOfferFilter offerFilter, SalesOfferCommonVO salesOfferCommonVO){
		
		boolean requestTtvOnly         = false;
		
		
		/************************************/
		/* Only TTV in request              */ 
		/************************************/
		List<OfferProductHeader> productList = offerFilter.getProductList();
		if ( (productList == null) || 
			 (productList.size() != 1) ||
			 (productList.get(0).getProductTypeCd() == null) ||
			 (productList.get(0).getProductTypeCd().equalsIgnoreCase(TTV))){
			requestTtvOnly = true;  // TTV is not the only product in the request
		}
		LOGGER.debug("FilterOfferByRequestProductListRule", "request has TTV only = " + requestTtvOnly);
		
		/**
		 * Re-purposing this method on July 30,2019:
		 * 		- if there is TTV only in the request do not filter out the HSIC product because is not contained in the request
		 * 			- This will cover for HS Zero scenarios and for Existing HS different of HS Zero.
		 */
		
		if(requestTtvOnly && offerContainsHSIC(offer)){
			return false; //Will not filter out the offer
		}else{
			return true; //May filter out the offer
		}
		
		/****************************************/
		/* if subscriber currently has HISC     */
		/* filter out offer                     */
		/****************************************/ 
//		boolean hasExistingHsic = false;
//		boolean oisHasHsZero       = false;
//		if (WLNOfferUtil.isProductExists(HSIC, salesOfferCommonVO)){ 
//			hasExistingHsic = true;  //Has existing HSIC product
//		}
//		LOGGER.debug("FilterOfferByRequestProductListRule", "Subscriber has HSIC = " + hasExistingHsic);
//		
		
//		/************************************/
//		/* TOM offer has HSZERO ?           */
//		/************************************/
//		String offerTemplateId  = "";
//		String omsCode          = "";
//		
//		offerTemplateId = WLNOfferUtil.getHsicExternalProductCatalogueId(offer);
//		if (offerTemplateId != null){
//			omsCode = WLNOfferUtil.mapOmsCode(offerTemplateId);
//			if (Constants.HSZERO.equalsIgnoreCase(omsCode)){
//				oisHasHsZero = true;
//			}
//		}
//		
//		LOGGER.debug("FilterOfferByRequestProductListRule", "OIS has HSZERO = " + oisHasHsZero);
//		
//		if (requestTtvOnly && !hasExistingHsic && oisHasHsZero){
//			return false;  //Will not filter out the offer
//		} else {
//			return true;   //May filter out the offer
//		}
	}

	
	private boolean offerContainsHSIC(Offer offer) {
		
		if(offer.getOfferProduct()!=null && !CollectionUtils.isEmpty(offer.getOfferProduct().getWirelineOfferProductList())){
			for(WirelineOfferProduct product : offer.getOfferProduct().getWirelineOfferProductList()){
				if(HSIC.equalsIgnoreCase(product.getProductTypeCode())){
					return true;
				}
			}
		}
		
		return false;
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
