package com.telus.csm.ewlnsc.adapter.domain.quote;

import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOffering {

	private String name;
	private String catalogId;
	private List<ProductOfferingPrice> productOfferingPrice = null;
	private String isOcWaiveAllowed;
	private String isOcWaived;
	private String isImplementedOneTimeCharges;
	private String description;
	private String id;
	private String actionType;
	private String isMandatory;
	private String isEnabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCatalogId() {
		return catalogId;
	}

	public void setCatalogId(String catalogId) {
		this.catalogId = catalogId;
	}

	public List<ProductOfferingPrice> getProductOfferingPrice() {
		return productOfferingPrice;
	}

	public void setProductOfferingPrice(List<ProductOfferingPrice> productOfferingPrice) {
		this.productOfferingPrice = productOfferingPrice;
	}

	public String getIsOcWaiveAllowed() {
		return isOcWaiveAllowed;
	}

	public void setIsOcWaiveAllowed(String isOcWaiveAllowed) {
		this.isOcWaiveAllowed = isOcWaiveAllowed;
	}

	public String getIsOcWaived() {
		return isOcWaived;
	}

	public void setIsOcWaived(String isOcWaived) {
		this.isOcWaived = isOcWaived;
	}

	public String getIsImplementedOneTimeCharges() {
		return isImplementedOneTimeCharges;
	}

	public void setIsImplementedOneTimeCharges(String isImplementedOneTimeCharges) {
		this.isImplementedOneTimeCharges = isImplementedOneTimeCharges;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getIsMandatory() {
		return isMandatory;
	}

	public void setIsMandatory(String isMandatory) {
		this.isMandatory = isMandatory;
	}

	public String getIsEnabled() {
		return isEnabled;
	}

	public void setIsEnabled(String isEnabled) {
		this.isEnabled = isEnabled;
	}

}