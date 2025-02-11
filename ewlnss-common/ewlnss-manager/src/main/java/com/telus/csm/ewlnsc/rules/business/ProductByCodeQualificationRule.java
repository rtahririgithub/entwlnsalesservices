package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

/*
 * If the TOM offer contains SING product and the SING product is not eligible
 * according to the AvailabilityService (thru ProductQualification) then the
 * offer will be filtered out.
 * 
 * Not eligible means one of these conditions: - SING Product is excluded from
 * productQualification response - Or All the SING elements from
 * productQualification response are set at unavailableInd=true. [CL
 * unavailalbeInd = true]
 */

/*
 * If the TOM offer contains HSIC product and the HSIC product is not eligible
 * according to the AvailabilityService (thru ProductQualification) then the
 * offer will be filtered.
 * 
 * Not eligible means one of these conditions: HSIC Product is excluded from
 * productQualification response Or All the HSIC elements from
 * productQualification response are set at unavailableInd=true. [CL
 * unavailalbeInd = true]
 */

/*If the TOM offer contains TTV product and the TTV product is not eligible according to the AvailabilityService (thru ProductQualification) then the offer will be filtered.

	Not eligible means one of these conditions: 
	TTV Product is excluded from productQualification response
	Or All the TTV elements from productQualification response are set at unavailableInd=true. [CL unavailalbeInd = true] 
	Note:   TTV is only qualified when at least one productQualification result exists with productType=TTV, is qualified, and has a productTierCd != TVX 
*/

public class ProductByCodeQualificationRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(ProductByCodeQualificationRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public ProductByCodeQualificationRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {

		for (WirelineOfferProduct wirelineOfferProduct : offer.getOfferProduct().getWirelineOfferProductList()) {
			if (!WLNOfferUtil.isNoChangeOfferProduct(wirelineOfferProduct)){
				for (ProductQualification productQualification : getProductQualificationList()) {

					if (!isProductQualified(wirelineOfferProduct.getProductTypeCode(), wirelineOfferProduct.getProductTemplateIdentifier(),
							productQualification.getProductList())) {
						logger.debug("isSatisfiedBy", "Failed: " + WLNOfferUtil.getRuleContextInformation(offer));
						return false;
					}
				}
			}
		}

		logger.debug("isSatisfiedBy", "Passed: " + WLNOfferUtil.getRuleContextInformation(offer));

		return true;
	}

	private boolean isProductQualified(String productTypeCode, ProductCatalogueIdentifier catalogueIdentifier, List<Product> qualificationProductList) {

		boolean isQualified = false;
		boolean isPikOffer = false;

		// Is this a PikTV offer?
		if(catalogueIdentifier != null && !StringUtils.isBlank(catalogueIdentifier.getExternalProductCatalogId())) {
			isPikOffer = WLNOfferUtil.isPikTvCatalogId(catalogueIdentifier.getExternalProductCatalogId());
		}

		for (Product qualificationProduct : qualificationProductList) {
			// NWLN-11152 Filter PIK offer with TVX qualification unavailableInd
			if (isPikOffer) {
				if ("TVX".equalsIgnoreCase(qualificationProduct.getProductTierCd())
						&& (qualificationProduct.getUnavailableInd() == null
								|| !qualificationProduct.getUnavailableInd().booleanValue())) {
					isQualified = true;
					break;
				}
			}else if (productTypeCode.equalsIgnoreCase(qualificationProduct.getProductTypeCd())
					&& (qualificationProduct.getUnavailableInd() == null
							|| !qualificationProduct.getUnavailableInd().booleanValue())) {

				if (EnterpriseWLNSalesServicesConstants.TTV.equalsIgnoreCase(productTypeCode)) {

					if (qualificationProduct.getProductTierCd() != null
							// 2018 Aug release for Pik TV
							// TVX is a valid productTierCd
							// && !"TVX".equalsIgnoreCase(qualificationProduct.getProductTierCd())
							&& !"".equalsIgnoreCase(qualificationProduct.getProductTierCd())) {

						isQualified = true;
						break;
					}
				} else {
					isQualified = true;
					break;
				}

			}
		}

		return isQualified;
	}

	private List<ProductQualification> getProductQualificationList() {

		return salesOfferCommonVO.getProductQualificationAdapterResponseVO() == null
				|| salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList() == null
						? new ArrayList<ProductQualification>()
						: salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList();
	}
}
