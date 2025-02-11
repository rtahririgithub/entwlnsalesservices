package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Jose.Mena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOffering implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String description;
	private String id;
	private String name;
	private String actionType;
	private String catalogId;
	private List<ProductOfferingPrice> productOfferingPrice;
	private String ocWaiveReasonTxt;
	private boolean isOcWaiveAllowed;
	private boolean isOcWaived;
	private boolean isActivityOneTimeCharges;
	private boolean isImplementedOneTimeCharges;
	private boolean isMandatory;
	private boolean isEnabled;
	private String ocWaiveReasonType;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getActionType() {
		return actionType;
	}

	public void setActionType(String actionType) {
		this.actionType = actionType;
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

	public String getOcWaiveReasonTxt() {
		return ocWaiveReasonTxt;
	}

	public void setOcWaiveReasonTxt(String ocWaiveReasonTxt) {
		this.ocWaiveReasonTxt = ocWaiveReasonTxt;
	}

	public String getOcWaiveReasonType() {
		return ocWaiveReasonType;
	}

	public void setOcWaiveReasonType(String ocWaiveReasonType) {
		this.ocWaiveReasonType = ocWaiveReasonType;
	}

	public boolean isOcWaiveAllowed() {
		return isOcWaiveAllowed;
	}

	public void setOcWaiveAllowed(boolean isOcWaiveAllowed) {
		this.isOcWaiveAllowed = isOcWaiveAllowed;
	}

	public boolean isOcWaived() {
		return isOcWaived;
	}

	public void setOcWaived(boolean isOcWaived) {
		this.isOcWaived = isOcWaived;
	}

	public boolean isActivityOneTimeCharges() {
		return isActivityOneTimeCharges;
	}

	public void setActivityOneTimeCharges(boolean isActivityOneTimeCharges) {
		this.isActivityOneTimeCharges = isActivityOneTimeCharges;
	}

	public boolean isImplementedOneTimeCharges() {
		return isImplementedOneTimeCharges;
	}

	public void setImplementedOneTimeCharges(boolean isImplementedOneTimeCharges) {
		this.isImplementedOneTimeCharges = isImplementedOneTimeCharges;
	}

	public boolean isMandatory() {
		return isMandatory;
	}

	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}

	public boolean isEnabled() {
		return isEnabled;
	}

	public void setEnabled(boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
}
