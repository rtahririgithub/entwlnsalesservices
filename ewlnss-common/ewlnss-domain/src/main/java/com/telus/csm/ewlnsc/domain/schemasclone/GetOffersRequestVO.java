package com.telus.csm.ewlnsc.domain.schemasclone;

import com.telus.csm.ewlnsc.domain.schemasclone.AccessoryOfferCriteria;
import com.telus.csm.ewlnsc.domain.schemasclone.OfferProductHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetnerOfferFilterCriteria;

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
		for (OfferProductHeader product : getSalesOfferCriteria().getOfferFilter().getProductList()) {
			if (productType.equals(product.getProductTypeCd()))
				return product;
		}

		return null;
	}

	public AccessoryOfferCriteria getAccessoryOfferCriteria() {
		return accessoryOfferCriteria;
	}

	public void setAccessoryOfferCriteria(AccessoryOfferCriteria accessoryOfferCriteria) {
		this.accessoryOfferCriteria = accessoryOfferCriteria;
	}
}
