package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.grid.domain.CatalogueItemDO;
import com.telus.csm.ewlnsc.helper.CommonWLNGridHelper;
import com.telus.csm.ewlnsc.helper.WLNOfferUtil;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.csm.ewlnsc.util.EnterpriseWLNSalesServicesConstants;
import com.telus.csm.ewlnsc.util.LoggerUtil;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Feature;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

public class CallingFeatureRule extends AbstractSpecification<Offer,TraceVO> {

	private static final LoggerUtil logger = LoggerUtil.getLogger(CallingFeatureRule.class);

	private SalesOfferCommonVO salesOfferCommonVO;

	public CallingFeatureRule(SalesOfferCommonVO salesOfferCommonVO) {
		this.salesOfferCommonVO = salesOfferCommonVO;
	}

	@Override
	public boolean isSatisfiedBy(Offer o, List<TraceVO> traces) {
		logger.debug("isSatisfiedBy", WLNOfferUtil.getRuleContextInformation(o));

		if (salesOfferCommonVO.getProductQualificationAdapterResponseVO() == null || salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList()==null ||  salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList().isEmpty()) {
			return true;
		}
		
		List<Product> productList = salesOfferCommonVO.getProductQualificationAdapterResponseVO().getProductQualificationList()
				.get(0).getProductList();
		
		if (productList == null || productList.isEmpty()) {
			return true;
		}

		// Based on productQualification results,
		// filter out any calling features in the market offer not eligible in
		// the Availability results.
		for (WirelineOfferProduct product : o.getOfferProduct().getWirelineOfferProductList()) {
			if (EnterpriseWLNSalesServicesConstants.SING.equals(product.getProductTypeCode())) {
				Feature feature = product.getFeature();
				if (feature != null) {
					List<ProductCatalogueItem> includedFeatureItemList = feature.getIncludedProductCatalogueItemList();
					feature.setIncludedProductCatalogueItemList(null);
					for (ProductCatalogueItem featureItem : includedFeatureItemList) {
						if (isSatisfiedBy(o, product, featureItem, productList, traces)) {
							feature.getIncludedProductCatalogueItemList().add(featureItem);
						}
					}

					List<ProductCatalogueItem> optionalFeatureItemList = feature.getOptionalProductCatalogueItemList();
					feature.setOptionalProductCatalogueItemList(null);
					for (ProductCatalogueItem featureItem : optionalFeatureItemList) {
						if (isSatisfiedBy(o, product, featureItem, productList, traces)) {
							feature.getOptionalProductCatalogueItemList().add(featureItem);
						}
					}

					// If the remaining feature available is less than the min
					// value configured, then filter out the offer.
					if (feature.getMinQty() != null) {
						int total = feature.getIncludedProductCatalogueItemList().size() + feature.getOptionalProductCatalogueItemList().size();
						if (total < feature.getMinQty().intValue()) {
							TraceVO t = TraceVO.newInstance(this);
							t.setAction(TraceVO.DELETED);
							t.setElementType(TraceVO.OFFER);
							t.setOffer(o);
							t.setReason("Available calling features less than the min value: " + feature.getMinQty().intValue());
							traces.add(t);

							logger.error("Offer id: " + o.getOfferId() + " was filtered out, because available calling features less than the min value: " + feature.getMinQty().intValue());

							logger.debug("CallingFeatureRule", "Failed.");

							return false;
						}
					}
				}
			}
		}

		logger.debug("CallingFeatureRule", "Passed.");

		return true;
	}

	private boolean isSatisfiedBy(Offer offer, WirelineOfferProduct product, ProductCatalogueItem featureItem,
			List<Product> productList, List<TraceVO> traces) {
//		
//		List<WirelineOfferProduct> wirelineOfferProductList = new ArrayList<WirelineOfferProduct>();
//		wirelineOfferProductList.add(product);
//
//		String productCatalogId = featureItem.getProductCatalogueIdentifier().getProductCatalogueId();
//		CommonWLNGridHelper gridHelper = CommonWLNGridHelper.getInstance();
//		CatalogueItemDO catalogueItemDO = gridHelper.getCatalogueItemById(productCatalogId);
//
////		for (Product x : productList) {
////			if (catalogueItemDO.getInternalName().equalsIgnoreCase(x.getCallingFeature()) && !Boolean.FALSE.equals(x.getUnavailableInd())) {
////				return true;
////			}
////		}
//
//		Trace t = Trace.newInstance(this);
//		t.setAction(Trace.DELETED);
//		t.setElementType(Trace.CALLING_FEATURE);
//		t.setOffer(offer);
//		t.setProduct(product);
//		t.setReason("Calling feature not available: " + catalogueItemDO.getInternalName());
//		traces.add(t);
//
//		logger.error("Calling feature not available: " + catalogueItemDO.getInternalName());

		return true;

	}

}
