package com.telus.csm.ewlnsc.domain;

import java.util.ArrayList;
import java.util.List;

import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.AccessoryOfferCriteria;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.SweetnerOfferFilterCriteria;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.OfferProductHeader;


public class GetOffersRequestVO extends CoreRequestBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private AssociatedWirelessSalesSummaryVO associatedWirelessSummary;
	
	private SalesOfferCriteriaVO salesOfferCriteria;
	
	private SweetnerOfferFilterCriteria sweetenerOfferFilterCriteria;

	private AccessoryOfferCriteria accessoryOfferCriteria;


	public SweetnerOfferFilterCriteria getSweetenerOfferFilterCriteria() {
		return sweetenerOfferFilterCriteria;
	}

	public void setSweetenerOfferFilterCriteria(SweetnerOfferFilterCriteria sweetenerOfferFilterCriteria) {
		this.sweetenerOfferFilterCriteria = sweetenerOfferFilterCriteria;
	}

	public AssociatedWirelessSalesSummaryVO getAssociatedWirelessSummary() {
		return associatedWirelessSummary;
	}

	public void setAssociatedWirelessSummary(AssociatedWirelessSalesSummaryVO associatedWirelessSummary) {
		this.associatedWirelessSummary = associatedWirelessSummary;
	}

	public SalesOfferCriteriaVO getSalesOfferCriteria() {
		return salesOfferCriteria;
	}

	public void setSalesOfferCriteria(SalesOfferCriteriaVO salesOfferCriteria) {
		this.salesOfferCriteria = salesOfferCriteria;
	}

	public OfferProductHeader getProductFromRequest(String productType) {
		
		if (getSalesOfferCriteria() != null
				&& getSalesOfferCriteria().getOfferFilter() != null
				&& getSalesOfferCriteria().getOfferFilter().getProductList() != null) {
		for (OfferProductHeader product : getSalesOfferCriteria().getOfferFilter().getProductList()) {
			if (productType.equals(product.getProductTypeCd()))
				return product;
		}
		}

		return null;
	}

	public OfferProductHeader getSweetenerProductFromRequest(String productType) {
		
		if (getSweetenerOfferFilterCriteria() != null
				&& getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList() != null
				&& !getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().isEmpty()
				&& getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().get(0).getProductList() != null) {
			for (OfferProductHeader product : getSweetenerOfferFilterCriteria().getAssociatedWirelineSalesSummaryList().get(0).getProductList()) {
				if (productType.equals(product.getProductTypeCd()))
					return product;
			}
		}

		return null;
	}

	public AccessoryOfferCriteria getAccessoryOfferCriteria() {
		return accessoryOfferCriteria;
	}

	public void setAccessoryOfferCriteria(AccessoryOfferCriteria accessoryOfferCriteria) {
		this.accessoryOfferCriteria = accessoryOfferCriteria;
	}
	
	public List<String> getProductFromRequest() {
		List<String> productList = new ArrayList<String>();

		if (getSalesOfferCriteria() != null && getSalesOfferCriteria().getOfferFilter() != null
				&& getSalesOfferCriteria().getOfferFilter().getProductList() != null) {
			for (OfferProductHeader product : getSalesOfferCriteria().getOfferFilter().getProductList()) {
				productList.add(product.getProductTypeCd());
			}
		}

		return productList;
	}
}
