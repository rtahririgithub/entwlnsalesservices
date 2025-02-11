package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.SubscribedProductInfoRestVO;
import com.telus.csm.ewlnsc.domain.GetAssignedAndPendingProductResponseVO;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.WirelineOfferFilter;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;


/**
 * 1) Whenever the customer has subscribed to TTV,  we should not be including any offers that have HSIC productType but not TTV productType.
 * 
 * 2) Existing customer: If there is a subscribed TTV product, then TOM offers that include the TTV product MUST have 
	  the same productTemplateId (OMS Offer ID) as that subscribed TTV product. Any TOM offers containing TTV(or HS)  products 
	  but a different productTemplateId will be filtered out. 
 * 
 * @author Alejandro.Hernandez
 *
 */
public class FilterOfferBySubscribedProductsAndCIdRule extends AbstractSpecification<Offer,TraceVO>{

	private SalesOfferCommonVO salesOfferCommonVO;
	private WirelineOfferFilter offerFilter;
	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterOfferBySubscribedProductsAndCIdRule.class);
	
	public FilterOfferBySubscribedProductsAndCIdRule(SalesOfferCommonVO commonVO){
		this.salesOfferCommonVO=commonVO;
		this.offerFilter = commonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
	}
	
	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		boolean isSatisfied = true;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));

		if(salesOfferCommonVO!=null && salesOfferCommonVO.getAssignedAndPendingProductsResponseVO()==null){
			logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Rule skipped since no products has been retrieved for the customer.");
			logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));

			return true;
		}
		
		//If offer and customer are re-contracting skip the rule
		
		if(salesOfferCommonVO.isCustomerEligibleForRecontract() && WLNOfferUtil.isOfferForLikeForLikeRecontracting(o, salesOfferCommonVO)){
			logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Rule skipped as customer and offer are eligible for re-contracting. OfferId: " + o.getOfferId());
			logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));
			
			return true;
		}
		
		
		boolean subHasTTVInd=false;
		boolean offerHasHsic=false;
		boolean offerHasTTV=false;
		boolean offerHasCPE=false;
		String subscribedOmsOfferCatalogId="";
		StringBuilder sbReason = new StringBuilder();
		boolean offerHSNotMatchingCatalogIds=false;
		boolean offerTTVNotMatchingCatalogIds=false;
		boolean offerCPENotMatchingCatalogIds=false;
		
		if(salesOfferCommonVO!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> subscribedProducts = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			if(subscribedProducts!=null && !subscribedProducts.isEmpty()){
				for(SubscribedProductInfoRestVO subscribedProduct : subscribedProducts){
					if(EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
						subHasTTVInd=true;
						subscribedOmsOfferCatalogId = subscribedProduct.getProductInstance().get(0).getOmsOfferCatalogId();
						break;
					}
				}
				
				if(subHasTTVInd){
					//Checking products coming from offer
					boolean offerHasNoChangeHSIC = false;
					boolean offerHasNoChangeTTV  = false;
					boolean offerHasNoChangeCPE  = false;
					final List<WirelineOfferProduct> oisProductList = o.getOfferProduct().getWirelineOfferProductList();
					for (final WirelineOfferProduct tomProdElem : oisProductList) {
						    //2019 offer split, adding check for HS not matching CID
							if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(tomProdElem.getProductTypeCode())) {
								offerHasHsic = true;
								if(WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)) offerHasNoChangeHSIC = true;
								
								// NWLN-7598 - check hsic offer matching the template id as in the offer
								if (!subscribedOmsOfferCatalogId.equals(tomProdElem.getProductTemplateIdentifier().getExternalProductCatalogId())) {
									offerHSNotMatchingCatalogIds = true;
								}
							}
							if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(tomProdElem.getProductTypeCode())) {
								offerHasTTV = true;
								if(WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)) offerHasNoChangeTTV = true;
								if (!subscribedOmsOfferCatalogId.equals(tomProdElem.getProductTemplateIdentifier().getExternalProductCatalogId())) {
									offerTTVNotMatchingCatalogIds = true;
								}
	
							}	
							if (EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(tomProdElem.getProductTypeCode()) && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(tomProdElem.getProductTemplateProductTypeCode())) {
								offerHasCPE = true;
								if(WLNOfferUtil.isNoChangeOfferProduct(tomProdElem)) offerHasNoChangeCPE = true;
								
								if (!subscribedOmsOfferCatalogId.equals(tomProdElem.getProductTemplateIdentifier().getExternalProductCatalogId())) {
									offerCPENotMatchingCatalogIds = true;
								}
							}
					}
					
					// NWLN-7598 - for Joined offer, bundle offer, or any offer have more than 1 product with HS and TTV product
					//			 - use the existing logic: check the TTV matching indicator only
					if(WLNOfferUtil.isJoinedOffers(salesOfferCommonVO.getOffersRequestVO().getOperationHeader()) || 
							WLNOfferUtil.isBundleOffersRequested(offerFilter) ||
							WLNOfferUtil.isValidMultipleTTVProductOffer(o)) {  //code based offer has two products in single offer when using split offer
						if(offerHasHsic && !offerHasTTV){
							isSatisfied=false;
							sbReason.append("Offer:" + o.getOfferId() + " filtered out since offer contains HSIC without TTV");
						}
						
						if(offerHasTTV && !offerHasHsic){
							isSatisfied=false;
							sbReason.append("Offer: " + o.getOfferId() + " filtered out since offer contains TTV without HSIC");
						}
						if(offerTTVNotMatchingCatalogIds){
							isSatisfied=false;
							sbReason.append("Offer: " + o.getOfferId() + " filtered out since subscribed TTV product catalog id doesn't match product catalog Id from Offer: TTV product.");
						}						
					} else {
						// NWLN-7598 - for split offer, check weather HS product or TTV product has the same template Id for offer or not
						if(offerHasHsic && offerHSNotMatchingCatalogIds){
							isSatisfied=false;
							sbReason.append("Offer: " + o.getOfferId() + " filtered out since subscribed TTV product catalog id doesn't match product catalog Id from Split Offer: HS product.");
						}
						if(offerHasTTV && offerTTVNotMatchingCatalogIds){
							isSatisfied=false;
							sbReason.append("Offer: " + o.getOfferId() + " filtered out since subscribed TTV product catalog id doesn't match product catalog Id from Split Offer: TTV product.");
						}
						if(offerHasCPE && offerCPENotMatchingCatalogIds){
							isSatisfied=false;
							sbReason.append("Offer: " + o.getOfferId() + " filtered out since subscribed TTV product catalog id doesn't match product catalog Id from Split Offer: CPE product.");
						}
					}
					
					if(!isSatisfied){
						TraceVO t = TraceVO.newInstance(this);
						t.setAction(TraceVO.DELETED);
						t.setElementType(TraceVO.OFFER);
						t.setOffer(o);
						t.setReason(sbReason.toString());
						traces.add(t);

						logger.error("Offer id: " + o.getOfferId() + " was filtered out, because " + sbReason.toString());

						logger.debug("FilterOfferBySubscribedProductsAndCIdRule", (isSatisfied ? "Passed" : "Failed") + ": " + WLNOfferUtil.getRuleContextInformation(o));
						return isSatisfied;
					}
				}else{
					logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Rule skipped since customer has no TTV product subscribed.");

					logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));
					return true;
				}
			}else{
				logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Rule skipped since customer has no subscribed products.");

				logger.debug("FilterOfferBySubscribedProductsAndCIdRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(o));
				return true;
			}
		}

		logger.debug("FilterOfferBySubscribedProductsAndCIdRule", (isSatisfied ? "Passed" : "Failed") + ": " + WLNOfferUtil.getRuleContextInformation(o));
	
		return isSatisfied;
	}
	
	
	
}
