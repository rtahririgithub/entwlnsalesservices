package com.telus.csm.ewlnsc.rules.business;

import java.math.BigInteger;
import java.util.List;


import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
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
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

/*Filter out the TOM offer if it contains a HSIC product AND the offer's product-plan has a better ranking than the best ranked feasible plan from the checkProductFeasibility (better rank= lower number)
Refer to Availability/Feasibility rules for more info on how to determine best ranked feasible plan.

Given:
-	Product in a TOM offer includes "HSIC"
-	Product-plan in TOM offer has a ranking of 2 
When:
-	Best ranking feasible plan for the customer's address has a ranking of 5
Then:
-	Offer will be filtered out*/

public class SubscribedHsicPPRankingWorseThenOfferRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(SubscribedHsicPPRankingWorseThenOfferRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;
	private WirelineOfferFilter offerFilter;
	public SubscribedHsicPPRankingWorseThenOfferRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
		this.offerFilter = salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getOfferFilter();
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = false;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));

		if(!validateExistingHsicProduct(salesOfferCommonVO)) {
			logger.debug("SubscribedHsicPPRankingWorseThenOfferRule", "Passed");
			logger.debug("SubscribedHsicPPRankingWorseThenOfferRule", "Skipped" + ": " + WLNOfferUtil.getRuleContextInformation(offer));

			return true;
		}
		
		String hiSpeedPackCode = WLNOfferUtil.mapOmsCode(getHsicExternalProductCatalogueId(offer));

		String subscribedProductTierCd = getHsicSubscribedProductTierCdFromAssignedAndPendingProductsResponse();
		
		if (hiSpeedPackCode == null || "".equals(hiSpeedPackCode)) {
			isSatisfied = true;
		} else { 
			int subscribedProductPlanRanking = calculateLowestRankingForPP(subscribedProductTierCd);
			int offerPlanRanking = calculateLowestRankingForPP(hiSpeedPackCode);
			String oisTerm=null;
			String currentTerm= WLNOfferUtil.getContracTermInMonthByYears(getCurrentHsicTerm());
			
			final List<WirelineOfferProduct> oisProdList = offer.getOfferProduct().getWirelineOfferProductList();
			
			for (final WirelineOfferProduct oisProdElem : oisProdList) {
				if (!WLNOfferUtil.isNoChangeOfferProduct(oisProdElem)){
					if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(oisProdElem.getProductTypeCode())){
						List<BigInteger> oisTermList = oisProdElem.getContractTermList();
						if (!oisTermList.isEmpty()) {
							oisTerm = oisTermList.get(0).toString();
						}
						break;
					}
				} else  if ( WLNOfferUtil.isNoChangeOfferProduct(oisProdElem) && EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(oisProdElem.getProductTypeCode())){
					
					logger.debug("SubscribedHsicPPRankingWorseThenOfferRule", "Passed" + ": " + WLNOfferUtil.getRuleContextInformation(offer));
					return true;
				}
					
			}
			
			//if(oisTerm.equals(currentTerm)){
			if(oisTerm != null && currentTerm.equals(oisTerm)){
				if (offerPlanRanking < subscribedProductPlanRanking) {
					isSatisfied = true;
				}
			}
			
			if (oisTerm != null){
                int offerTerm = Integer.valueOf(oisTerm);
                
                if(EnterpriseWLNSalesServicesConstants.ZERO.equals(currentTerm) && offerTerm > 0){
                      if (offerPlanRanking <= subscribedProductPlanRanking) {
                            isSatisfied = true;
                      }
                }

			}
			
			// 2018 Aug release for Pik TV
			// For customer with existing HSIC, return Pik TV offer with same HS Tier 
			
			//2018, August 3 - Split offers change.
			//below logic will apply only for joined offers.
			
			// NWLN-7598 - current offer has HS product. 
			//the rule is to make sure the HS product ranking is less than or equal to your existing HS ranking.(higher tier)
			//this rule should be applied to both joined and split offer. 
//			if (WLNOfferUtil.isJoinedOffers(salesOfferCommonVO.getOffersRequestVO().getOperationHeader())
//					|| WLNOfferUtil.isBundleOffersRequested(offerFilter)) {
				boolean isOfferHavePikTV = false;
				for (WirelineOfferProduct product : offer.getOfferProduct().getWirelineOfferProductList()) {
					String templateId = null;
					if (product.getProductTemplateIdentifier() != null) {
						templateId = product.getProductTemplateIdentifier().getExternalProductCatalogId();

						if (WLNOfferUtil.isPikTvCatalogId(templateId)) {
							isOfferHavePikTV = true; //this offer has piktv template cid for TV or HS
						}
					}
				}

				if (isOfferHavePikTV && offerPlanRanking <= subscribedProductPlanRanking) {
					isSatisfied = true;
				}
//			}

			
			if(!isSatisfied){
				TraceVO t = TraceVO.newInstance(this);

				t.setRuleName("SubscribedHsicPPRankingWorseThenOfferRule");
				t.setAction(TraceVO.DELETED);
				t.setElementType(TraceVO.OFFER);
				t.setOffer(offer);
				t.setReason("Offer is filtered out. HSIC product-plan [" + hiSpeedPackCode + "] ranking is "
						+ offerPlanRanking + ". The existing plan ranking is "
						+ subscribedProductPlanRanking);
				traces.add(t);

				logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because HSIC product-plan [" + hiSpeedPackCode + "] ranking is "
						+ offerPlanRanking + ". The existing plan ranking is "
						+ subscribedProductPlanRanking);
			}
		}

		logger.debug("SubscribedHsicPPRankingWorseThenOfferRule", (isSatisfied ? "Passed" : "Failed") + ": " + WLNOfferUtil.getRuleContextInformation(offer));
		
		return isSatisfied;
	}

	private boolean validateExistingHsicProduct(
			SalesOfferCommonVO commonVO) {
		boolean result=false;
		if(salesOfferCommonVO.getAssignedAndPendingProductsResponseVO()==null){
			return result;
		}else{
			GetAssignedAndPendingProductResponseVO assignedAndPendingProductsResponseVO = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProducts = assignedAndPendingProductsResponseVO.getAssignedProductListByServiceAddressAndServiceId(commonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), commonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			if(assignedProducts==null || assignedProducts.isEmpty()){
				return result;
			}else{
				if(!assignedProducts.isEmpty()){
					for(SubscribedProductInfoRestVO subscribedProduct : assignedProducts){
						if(EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())){
							result=true;
							break;
						}
					}
				}
			}
		}
		return result;
	}

	private String getCurrentHsicTerm() {
		String term=null;
		if(salesOfferCommonVO!=null && salesOfferCommonVO.getAssignedAndPendingProductsResponseVO()!=null){
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProductList = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			for (SubscribedProductInfoRestVO subscribedProduct : assignedProductList) {
				if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
					term = subscribedProduct.getProductInstance().get(0).getTermCd();
					break;
				}
			}
		}
		return term != null ? term : "MTM";
	}

	private String getHsicSubscribedProductTierCdFromAssignedAndPendingProductsResponse() {
		String subscribedProductTierCd = "";

		if(this.salesOfferCommonVO
				.getAssignedAndPendingProductsResponseVO() != null) {
			GetAssignedAndPendingProductResponseVO assignedAndPendingProducts = salesOfferCommonVO.getAssignedAndPendingProductsResponseVO();
			List<SubscribedProductInfoRestVO> assignedProductList = assignedAndPendingProducts.getAssignedProductListByServiceAddressAndServiceId(salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getServiceAddress(), salesOfferCommonVO.getOffersRequestVO().getSalesOfferCriteria().getSubscribedServiceIdentifier());
			for (SubscribedProductInfoRestVO subscribedProduct : assignedProductList) {
				if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(subscribedProduct.getProductTypeCd())) {
					subscribedProductTierCd = subscribedProduct.getProductTierCd();
					break;
				}
			}
			
		}
		return subscribedProductTierCd;
	}

	private String getHsicExternalProductCatalogueId(Offer offer) {
		String externalproductCatalogueId = null;

		for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {

			if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())) {
				for (ProductComponent productComponent : wirelineOfferProduct.getProductComponentList()) {
					for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {
						externalproductCatalogueId = productCatalogueItem.getProductCatalogueIdentifier()
								.getExternalProductCatalogId();

						break;
					}
				}
			}
		}
		return externalproductCatalogueId;
	}

	private int calculateLowestRankingForPP(String hiSpeedPackCode) {
		
		int tomOfferPPRanking = 100000;

		if(this.salesOfferCommonVO
				.getProductQualificationAdapterResponseVO() != null && this.salesOfferCommonVO
				.getProductQualificationAdapterResponseVO().getProductQualificationList() != null) {

			List<ProductQualification> productQualificationList = this.salesOfferCommonVO
					.getProductQualificationAdapterResponseVO().getProductQualificationList();


			for (ProductQualification productQualification : productQualificationList) {
				for (Product product : productQualification.getProductList()) {
					if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(product.getProductTypeCd())
							&& hiSpeedPackCode.equalsIgnoreCase(product.getProductTierCd())) {
						if (product.getProductRanking() < tomOfferPPRanking) {
							tomOfferPPRanking = product.getProductRanking();
						}
					}
				}
			}

		}

		return tomOfferPPRanking;
	}
}
