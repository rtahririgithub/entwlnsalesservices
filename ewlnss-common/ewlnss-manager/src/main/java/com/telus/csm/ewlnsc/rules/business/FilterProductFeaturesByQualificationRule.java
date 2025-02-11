package com.telus.csm.ewlnsc.rules.business;

import java.util.ArrayList;
import java.util.List;

import com.telus.csm.ewlnsc.adapter.scis.domain.Product;
import com.telus.csm.ewlnsc.adapter.scis.domain.ProductQualification;
import com.telus.csm.ewlnsc.domain.SalesOfferCommonVO;
import com.telus.csm.ewlnsc.domain.TraceVO;
import com.telus.csm.ewlnsc.rules.base.AbstractSpecification;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Offer;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.ProductCatalogueItem;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProduct;

/*
 * When TOM offer contains SING product, if any of the features (calling
 * features) in the TOM offer are not eligible according to the
 * AvailabilityService (thru ProductQualification) then only those feature(s)
 * will be filtered out from the TOM offer.. Not eligible means one of these
 * conditions: Calling Feature's Internal Name is excluded from
 * productQualification response Or Calling Feature's Internal Name is included
 * but has unavailable=true. [CL unavailalbeInd = true]
 * 
 * Internal Name can be retrieved for the TOM offer calling feature by: -
 * Finding the Internal Name from PODS for this productCatalogueId.
 */

public class FilterProductFeaturesByQualificationRule  extends AbstractSpecification<Offer,TraceVO>{

	private SalesOfferCommonVO salesOfferCommonVO;
	String productCode;

	public FilterProductFeaturesByQualificationRule(SalesOfferCommonVO salesOfferCommonVO, String productCode) {
		this.salesOfferCommonVO = salesOfferCommonVO;
		this.productCode = productCode;
	}

	@Override
	public boolean isSatisfiedBy(Offer offer, List<TraceVO> traces) {
		boolean isSatisfied = false;

		
		List<WirelineOfferProduct> wirelineOfferProductList = offer.getOfferProduct().getWirelineOfferProductList();

		for (WirelineOfferProduct wirelineOfferProduct : wirelineOfferProductList) {
			if (productCode.equalsIgnoreCase(wirelineOfferProduct.getProductTypeCode())) {
				List<ProductCatalogueItem> qualifiedFeatureList = new ArrayList<ProductCatalogueItem>();
				List<ProductCatalogueItem> includedProductCatalogueItemList = wirelineOfferProduct.getFeature().getIncludedProductCatalogueItemList();
				
				isSatisfied = filterOutProductFeautures(isSatisfied, qualifiedFeatureList, includedProductCatalogueItemList);

				wirelineOfferProduct.getFeature().setIncludedProductCatalogueItemList(qualifiedFeatureList);

				List<ProductCatalogueItem> optionalProductCatalogueItemList = wirelineOfferProduct.getFeature().getOptionalProductCatalogueItemList();

				isSatisfied = filterOutProductFeautures(isSatisfied, qualifiedFeatureList, optionalProductCatalogueItemList);
				
				wirelineOfferProduct.getFeature().setOptionalProductCatalogueItemList(qualifiedFeatureList);
			}
		}

		return isSatisfied;
	}

	private boolean filterOutProductFeautures(boolean isSatisfied, List<ProductCatalogueItem> qualifiedFeatureList,
			List<ProductCatalogueItem> productCatalogueItemList) {
		for(ProductCatalogueItem productCatalogueItem : productCatalogueItemList) {
			List<Description> productCatalogueNameList = productCatalogueItem.getProductCatalogueNameList();
			
			for(Description productCatalogueName : productCatalogueNameList) {
				String featureName = productCatalogueName.getDescriptionText();
				
				List<ProductQualification> productQualificationList = salesOfferCommonVO
						.getProductQualificationAdapterResponseVO().getProductQualificationList();

				for (ProductQualification productQualification : productQualificationList) {
					List<Product> productList = productQualification.getProductList();

					for (Product product : productList) {
						if (productCode.equalsIgnoreCase(product.getProductTypeCd())) {
							if(featureName.equalsIgnoreCase(product.getCallingFeature())) {
								if (product.getUnavailableInd() == null || !product.getUnavailableInd().booleanValue()) {
									qualifiedFeatureList.add(productCatalogueItem);
									isSatisfied = true;
								}
							}
						}
					}
				}
				
			}
		}
		return isSatisfied;
	}
}
