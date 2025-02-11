package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.srv.smo.ordermgmt.feasibilityservicerequestresponse_v3.ProductFeasibilityInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.CompositeProductSpecificationCharacteristicValue;
import com.telus.tmi.xmlschema.xsd.product.productspecification.product_specification_types_v2.ProductSpecificationCharacteristic;

/*All TOM offers with a HSIC product will be filtered out if the ASF checkProductFeasibility operation returns "feasibilityResult=false" for that product AND the "bestAvailableConfigurationInd=false'.
*/

public class ProductNotFeasibleRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductNotFeasibleRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public ProductNotFeasibleRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = true;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(offer));
		
		boolean feasibilityHasTierNotFeasibleInd = WLNOfferUtil.feasibilityHasTierNotFeasible(salesOfferCommonVO.getCheckFeasibilityResponseVO());

		List<WirelineOfferProduct> wirelineOfferProductList = offer.getOfferProduct().getWirelineOfferProductList();

		for (WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList) {
          if (!WLNOfferUtil.isNoChangeOfferProduct(wirelineOfferProduct)) {
			if (EnterpriseWLNSalesServicesConstants.HSIC.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())
					|| EnterpriseWLNSalesServicesConstants.TTV
							.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())) {
				if (!isProductFeasible(wirelineOfferProduct.getProductTypeCode(),feasibilityHasTierNotFeasibleInd)) {
					isSatisfied = false;
					
					TraceVO t = TraceVO.newInstance(this);
					t.setRuleName("ProductNotFeasibleRule");
					t.setAction(TraceVO.DELETED);
					t.setElementType(TraceVO.OFFER);
					t.setOffer(offer);
					t.setReason("Offer is filtered out because " + wirelineOfferProduct.getProductTypeCode() + " product is not feasible");
					traces.add(t);

					logger.error("Offer id: " + offer.getOfferId() + " was filtered out, because " + wirelineOfferProduct.getProductTypeCode() + " product is not feasible");

					break;
				}
			}
          }	
		}

		logger.debug("ProductNotFeasibleRule", isSatisfied ? "Passed" : "Failed");

		return isSatisfied;
	}

	private boolean isProductFeasible(String productCode,boolean feasibilityHasTierNotFeasibleInd) {
		boolean isFeasible = true;

		for (ProductFeasibilityInfo productFeasibilityInfo : getProductFeasibilityInfoList()) {
			if (productCode.equalsIgnoreCase(productFeasibilityInfo.getProductSpecification().getName())) {
				if(feasibilityHasTierNotFeasibleInd){
					if (Boolean.FALSE.toString().equalsIgnoreCase(productFeasibilityInfo.getFeasibilityResult())
							&& isBestAvailableConfigurationInd(productFeasibilityInfo) == false) {

						isFeasible = false;
						break;
					}
				}
			}
		}
		return isFeasible;
	}

	private List<ProductFeasibilityInfo> getProductFeasibilityInfoList() {
		return salesOfferCommonVO.getCheckFeasibilityResponseVO() == null
				|| salesOfferCommonVO.getCheckFeasibilityResponseVO().getProductFeasibilityInfoList() == null
				|| salesOfferCommonVO.getCheckFeasibilityResponseVO().getProductFeasibilityInfoList()
						.getProductFeasibilityInfo() == null ? new ArrayList<ProductFeasibilityInfo>()
								: salesOfferCommonVO.getCheckFeasibilityResponseVO().getProductFeasibilityInfoList()
										.getProductFeasibilityInfo();
	}

	private boolean isBestAvailableConfigurationInd(ProductFeasibilityInfo productFeasibilityInfo) {

		for (ProductSpecificationCharacteristic productSpecificationCharacteristic : productFeasibilityInfo
				.getProductSpecification().getProductSpecificationCharacteristics()) {

			if ("bestAvailableConfigurationInd".equalsIgnoreCase(productSpecificationCharacteristic.getName())) {
				for (CompositeProductSpecificationCharacteristicValue value : productSpecificationCharacteristic
						.getProductSpecificationCharacteristicValues()) {

					if (Boolean.TRUE.toString().equalsIgnoreCase(value.getValue())) {
						return true;
					}
				}
			}
		}
		return false;
	}
}
