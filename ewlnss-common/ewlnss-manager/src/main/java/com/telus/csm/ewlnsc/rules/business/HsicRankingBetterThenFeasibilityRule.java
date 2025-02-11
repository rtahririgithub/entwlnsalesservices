package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
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

public class HsicRankingBetterThenFeasibilityRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(HsicRankingBetterThenFeasibilityRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public HsicRankingBetterThenFeasibilityRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = false;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));

		String hiSpeedPackCode = WLNOfferUtil.mapOmsCode(getHsicExternalProductCatalogueId(offer));

		if (StringUtils.isBlank(hiSpeedPackCode)
				|| this.salesOfferCommonVO.getCheckFeasibilityResponseVO() == null) {
			isSatisfied = true;
		} else {
			ProductFeasibilityInfoWrapper productFeasibilityInfoWrapper = new ProductFeasibilityInfoWrapper(
					this.salesOfferCommonVO.getCheckFeasibilityResponseVO());

			
//			if (productFeasibilityInfoWrapper.getFeasibilityResult() == false
//					&& WLNOfferUtil.feasibilityHasTierNotFeasible(salesOfferCommonVO.getCheckFeasibilityResponseVO()) && productFeasibilityInfoWrapper.getBestAvailableConfigurationInd() == false) {
//				isSatisfied = false;
//			} else {
				if(WLNOfferUtil.feasibilityHasTierNotFeasible(salesOfferCommonVO.getCheckFeasibilityResponseVO())){ //Only fire the logic of this rule if this error is thrown.
					int feasibilityServicePlanRanking = calculateLowestRankingForPP(
							productFeasibilityInfoWrapper.getServicePlan());
					int offerPlanRanking = calculateLowestRankingForPP(hiSpeedPackCode);

					if (offerPlanRanking >= feasibilityServicePlanRanking) {
						isSatisfied = true;
					} else {
						TraceVO t = TraceVO.newInstance(this);
						t.setRuleName("HsicRankingBetterThenFeasibilityRule");
						t.setAction(TraceVO.DELETED);
						t.setElementType(TraceVO.OFFER);
						t.setOffer(offer);
						t.setReason("Offer is filtered out. HSIC product-plan [" + hiSpeedPackCode + "] ranking is "
								+ offerPlanRanking + ". The best ranked feasible plan ranking is "
								+ feasibilityServicePlanRanking);
						traces.add(t);

						logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because HSIC product-plan [" + hiSpeedPackCode + "] ranking is "
								+ offerPlanRanking + ". The best ranked feasible plan ranking is "
								+ feasibilityServicePlanRanking);
					}
				}else{
					logger.debug("HsicRankingBetterThenFeasibilityRule", "Skipping rule since Feasibility didn't returned error for Tier not feasible");
					isSatisfied = true;
				}
//			}
		}

		logger.debug("HsicRankingBetterThenFeasibilityRule", isSatisfied ? "Passed" : "Failed");

		return isSatisfied;
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
		List<ProductQualification> productQualificationList = this.salesOfferCommonVO
				.getProductQualificationAdapterResponseVO().getProductQualificationList();

		int tomOfferPPRanking = 100000;

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

		return tomOfferPPRanking;
	}


}
