package com.telus.csm.ewlnsc.domain.price.rule;

import java.util.List;

public class InstallFeeRuleVO {
	private List<String> omsTemplates;
	private String installFeeProductCatalogueId;
	private String installFeeParentProductCatalogueId;
	private List<String> termList;
	private Double price;

	public List<String> getOmsTemplates() {
		return omsTemplates;
	}

	public void setOmsTemplates(List<String> omsTemplates) {
		this.omsTemplates = omsTemplates;
	}

	public String getInstallFeeProductCatalogueId() {
		return installFeeProductCatalogueId;
	}

	public void setInstallFeeProductCatalogueId(String installFeeProductCatalogueId) {
		this.installFeeProductCatalogueId = installFeeProductCatalogueId;
	}

	public String getInstallFeeParentProductCatalogueId() {
		return installFeeParentProductCatalogueId;
	}

	public void setInstallFeeParentProductCatalogueId(String installFeeParentProductCatalogueId) {
		this.installFeeParentProductCatalogueId = installFeeParentProductCatalogueId;
	}

	public List<String> getTermList() {
		return termList;
	}

	public void setTermList(List<String> termList) {
		this.termList = termList;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
