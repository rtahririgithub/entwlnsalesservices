package com.telus.csm.ewlnsc.domain.price.rule;

import java.util.List;

public class TTVEquipmentDiscountRuleVO {

	private List<String> omsTemplates;
	private String basePlanProductCatalogueId;
	private List<String> termList;
	private List<String> rentalMicList1;
	private int maxQtyForRentalMicList1;
	private List<String> rentalMicList2;
	private int maxQtyForRentalMicList2;

	public List<String> getOmsTemplates() {
		return omsTemplates;
	}

	public void setOmsTemplates(List<String> omsTemplates) {
		this.omsTemplates = omsTemplates;
	}

	public String getBasePlanProductCatalogueId() {
		return basePlanProductCatalogueId;
	}

	public void setBasePlanProductCatalogueId(String basePlanProductCatalogueId) {
		this.basePlanProductCatalogueId = basePlanProductCatalogueId;
	}

	public List<String> getTermList() {
		return termList;
	}

	public void setTermList(List<String> termList) {
		this.termList = termList;
	}

	public List<String> getRentalMicList1() {
		return rentalMicList1;
	}

	public void setRentalMicList1(List<String> rentalMicList1) {
		this.rentalMicList1 = rentalMicList1;
	}

	public int getMaxQtyForRentalMicList1() {
		return maxQtyForRentalMicList1;
	}

	public void setMaxQtyForRentalMicList1(int maxQtyForRentalMicList1) {
		this.maxQtyForRentalMicList1 = maxQtyForRentalMicList1;
	}

	public List<String> getRentalMicList2() {
		return rentalMicList2;
	}

	public void setRentalMicList2(List<String> rentalMicList2) {
		this.rentalMicList2 = rentalMicList2;
	}

	public int getMaxQtyForRentalMicList2() {
		return maxQtyForRentalMicList2;
	}

	public void setMaxQtyForRentalMicList2(int maxQtyForRentalMicList2) {
		this.maxQtyForRentalMicList2 = maxQtyForRentalMicList2;
	}

}
