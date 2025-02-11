package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueIdentifier;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductEligiblity;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class FilterAccessoryOfferByTemplateProduct extends FilterOfferByProductEligibilityRule {

	private static final LoggerUtil logger = LoggerUtil.getLogger(FilterAccessoryOfferByTemplateProduct.class);

	public FilterAccessoryOfferByTemplateProduct(SalesOfferCommonVO commonVO,
			Map<String, List<ProductEligiblity>> eligibilityProductsMap,
			Map<String, List<WirelineOfferProduct>> offerProductsMap, List<String> requestedProductList) {
		super(commonVO, eligibilityProductsMap, offerProductsMap, requestedProductList);
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		boolean isSatisfiedBy = false;
		String functionName = "FilterAccessoryOfferByTemplateProduct->isSatisfiedBy";
		String productTemplateProductTypeCode = null;
		ProductCatalogueIdentifier productCatalogueIdentifier = null;

		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));

		// get product template product type code and catalogue from wireline offer
		// product
		if (o.getOfferProduct() != null && o.getOfferProduct().getWirelineOfferProductList() != null) {
			for (WirelineOfferProduct offerProduct : o.getOfferProduct().getWirelineOfferProductList()) {
				if (EnterpriseWLNSalesServicesConstants.CPE.equalsIgnoreCase(offerProduct.getProductTypeCode())
						&& !StringUtils.isEmpty(offerProduct.getProductTemplateProductTypeCode())
						&& !EnterpriseWLNSalesServicesConstants.CPE
								.equalsIgnoreCase(offerProduct.getProductTemplateProductTypeCode())) {
					productTemplateProductTypeCode = offerProduct.getProductTemplateProductTypeCode();
					productCatalogueIdentifier = offerProduct.getProductTemplateIdentifier();
					break;
				}
			}
		}

		// if productTemplateProductTypeCode/productCatalogueIdentifier is not returned
		// for this offer, skip the rule
		if (productTemplateProductTypeCode == null || productCatalogueIdentifier == null) {
			logger.debug(functionName,
					"skipping rule because no productTemplateProductTypeCode/productCatalogueIdentifier was returned for this offer");
			return true;
		}

		// create CPE productEligibility list
		List<ProductEligiblity> productEligiblityList = new ArrayList<ProductEligiblity>();
		ProductEligiblity productEligiblity = new ProductEligiblity();
		productEligiblity.setProductTypeCd(productTemplateProductTypeCode);
		productEligiblity.setProductTemplateIdentifier(productCatalogueIdentifier);
		productEligiblityList.add(productEligiblity);

		if (productsAreExistingFromReturnedOffers(o, productEligiblityList)) {
			logger.debug(functionName, "Required products for offer: " + String.valueOf(o.getOfferId())
					+ " were found among the list of offers returned by OIS.");
			return true;
		} else {
			String msg = "Required products for offer: " + String.valueOf(o.getOfferId())
					+ " were NOT found in the subscribed products of the customer nor Returned offers from OIS.";
			logger.debug(functionName, msg);
			isSatisfiedBy = false;
			log(o, traces, msg);
		}

		return isSatisfiedBy;
	}
}
