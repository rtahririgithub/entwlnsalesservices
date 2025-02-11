package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.delegate.CheckProductFeasibilityDelegator;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.CheckProductFeasibility;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductComponent;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

/*If the TOM offer contains HSIC with a product-plan that is not eligible according to the AvailabilityService results (through ProductQualification service) then the offer will be filtered out..
	Not eligible means one of these conditions: 
highSpeedPackCd of TOM offer's product-plan is excluded from productQualification response
Or highSpeedPackCd of TOM offer's product-plan is included but has unqualified=true.

highSpeedPackCd can be retrieved for the TOM offer product plan by:
-	finding the externalProductCatalogId from PODS for the product-plan by OIS productCatalogueId (or this is included in OIS response also)
-	Finding the highSpeedWSSKey from WSS_PROD_OMS_CID_MAP  by the externalProductCatalogId 
-	Finding the highSpeedPackCd from WSS_PRODUCT_TIER refpds table by highSpeedWSSKey


if serviceAdressId is empty then skip this rule
*/

public class HsicProductPlanNotQualifiedRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(HsicProductPlanNotQualifiedRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public HsicProductPlanNotQualifiedRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = true;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));

		loopThroughProducts: for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct()
				.getWirelineOfferProductList()) {

			if (!WLNOfferUtil.isNoChangeOfferProduct(wirelineOfferProduct) && isHsicProduct(wirelineOfferProduct.getProductTypeCode())) {

				isSatisfied = false;

				for (ProductComponent productComponent : wirelineOfferProduct.getProductComponentList()) {
					for (ProductCatalogueItem productCatalogueItem : productComponent.getProductCatalogueItemList()) {

						for (ProductQualification productQualification : getPproductQualificationList()) {
							for (Product qualificationProduct : productQualification.getProductList()) {
								if (isHsicProduct(qualificationProduct.getProductTypeCd())
										&& serviceIsAvailable(qualificationProduct, productCatalogueItem
												.getProductCatalogueIdentifier().getExternalProductCatalogId())) {

									isSatisfied = true;
									break loopThroughProducts;

								}
							}
						}
					}
				}
			}
		}

		if(!isSatisfied) {
			TraceVO t = TraceVO.newInstance(this);
			t.setRuleName("HsicProductPlanNotQualifiedRule");
			t.setAction(TraceVO.DELETED);
			t.setElementType(TraceVO.OFFER);
			t.setOffer(offer);
			t.setReason("Offer is filtered out because HSIC is not qualified.");
			traces.add(t);
	
			logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because HSIC is not qualified");
		}
		
		logger.debug("HsicProductPlanNotQualifiedRule", isSatisfied ? "Passed" : "Failed");

		return isSatisfied;
	}

	private boolean isHsicProduct(String productCode) {
		return EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(productCode);
	}

	private boolean serviceIsAvailable(Product qualificationProduct, String externalProductCatalogId) {
		boolean serviceIsAvailable = false;

		String highSpeedPackCd = WLNOfferUtil.mapOmsCode(externalProductCatalogId);

		if (highSpeedPackCd.equalsIgnoreCase(qualificationProduct.getProductTierCd())
				&& (qualificationProduct.getUnavailableInd() == null
						|| qualificationProduct.getUnavailableInd().booleanValue() == false)) {
			serviceIsAvailable = true;
			logger.debug("HsicProductPlanNotQualifiedRule",
					"Mapped highSpeedPackCd from offer: " + highSpeedPackCd + " and product.getProductTierCd: "
							+ qualificationProduct.getProductTierCd() + " and product.getUnavailableInd: ["
							+ qualificationProduct.getUnavailableInd() + "]");
		}else{
			logger.debug("HsicProductPlanNotQualifiedRule", "HSIC product with High Speed Pack code: " + highSpeedPackCd + " is not Qualified. ExternalCatalogId from offer is: " + externalProductCatalogId);
		}

		return serviceIsAvailable;
	}

	private List<ProductQualification> getPproductQualificationList() {
		return salesOfferCommonVO.getProductQualificationAdapterResponseVO() == null
				|| salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList() == null
						? new ArrayList<ProductQualification>()
						: salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList();
	}
}
