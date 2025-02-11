package com.telus.csm.ewlnsc.rules.business;

import java.util.List;

import com.telus.csm.ewlnsc.adapter.domain.CheckProductFeasibilityAdapterResponse;
import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.delegate.CheckProductFeasibilityDelegator;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

/*Filter out the TOM offer if it contains a HSIC product AND the offer's product-plan has a better ranking than the best ranked feasible plan from the checkProductFeasibility (better rank= lower number)
Refer to Availability/Feasibility rules for more info on how to determine best ranked feasible plan.

Given:
-	Product in a TOM offer includes "HSIC"
-	Product-plan in TOM offer has a ranking of 2 
When:
-	Best ranking feasible plan for the customer's address has a ranking of 5
Then:
-	Offer will be filtered out*/

public class SubscribedHsicPPRankingEqualToOfferRule extends AbstractSpecification<Offer,TraceVO> {

	private SalesOfferCommonVO salesOfferCommonVO;

	public SubscribedHsicPPRankingEqualToOfferRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = false;

		String hiSpeedPackCode = WLNOfferUtil.mapOmsCode(getHsicExternalProductCatalogueId(offer));

		ProductFeasibilityInfoWrapper productFeasibilityInfoWrapper = new ProductFeasibilityInfoWrapper(this.salesOfferCommonVO.getCheckFeasibilityResponseVO());
		
		if (hiSpeedPackCode == null || "".equals(hiSpeedPackCode)) {
			isSatisfied = true;
		} else {
			if (productFeasibilityInfoWrapper.getFeasibilityResult() == false
					&& productFeasibilityInfoWrapper.getBestAvailableConfigurationInd() == false) {
				isSatisfied = false;
			} else {
				int feasibilityServicePlanRanking = calculateLowestRankingForPP(productFeasibilityInfoWrapper.getServicePlan());
				int offerPlanRanking = calculateLowestRankingForPP(hiSpeedPackCode);

				if (offerPlanRanking < feasibilityServicePlanRanking) {
					isSatisfied = false;
				}
			}
		}

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
	
	private class ProductFeasibilityInfoWrapper{
		private String feasibilityResult = null;
		private String servicePlan = null;
		private String bestAvailableConfigurationInd = null;
		CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse;
		
		ProductFeasibilityInfoWrapper(CheckProductFeasibilityAdapterResponse checkProductFeasibilityAdapterResponse){
			this.checkProductFeasibilityAdapterResponse = checkProductFeasibilityAdapterResponse;
			init();
		}
		
		private void init() {
			for (ProductFeasibilityInfo productFeasibilityInfo : checkProductFeasibilityAdapterResponse
					.getProductFeasibilityInfoList().getProductFeasibilityInfo()) {
				feasibilityResult = productFeasibilityInfo.getFeasibilityResult();

				if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productFeasibilityInfo.getProductSpecification().getName())) {
					for (ProductSpecificationCharacteristic productSpecificationCharacteristic : productFeasibilityInfo
							.getProductSpecification().getProductSpecificationCharacteristics()) {
						if ("servicePlan".equalsIgnoreCase(productSpecificationCharacteristic.getName())) {
							for (CompositeProductSpecificationCharacteristicValue value : productSpecificationCharacteristic
									.getProductSpecificationCharacteristicValues()) {
								servicePlan = value.getValue();
							}
						}

						if ("bestAvailableConfigurationInd"
								.equalsIgnoreCase(productSpecificationCharacteristic.getName())) {
							for (CompositeProductSpecificationCharacteristicValue value : productSpecificationCharacteristic
									.getProductSpecificationCharacteristicValues()) {
								bestAvailableConfigurationInd = value.getValue();
							}
						}
					}
				}
			}

		}

		public boolean getFeasibilityResult() {
			return Boolean.getBoolean(feasibilityResult);
		}

		public String getServicePlan() {
			return servicePlan;
		}

		public boolean getBestAvailableConfigurationInd() {
			return Boolean.getBoolean(bestAvailableConfigurationInd);
		}


	}

}
