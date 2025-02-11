package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnsc.adapter.oqs.domain.ProductOffering;

/**
 * 
 * @author Jose.Mena
 *
 */

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String productStatusDesc;
	private String serviceType;
	private String actionType;
	private String catalogId;
	private String offerCatatalogId;
	private String resourceID;
    private boolean isLevelOne;
	private Object totalOfferingPrice; // TODO: As suggested: The correct data
										// type for this element will be
										// reviewed in the future (It was not
										// clear from the schema in
										// Documentation)
    private boolean isMandatory;
	
    private boolean isEnabled;
    private boolean isVisible;
	private String parentProductSerialNumber;
	private List<ProductCharacteristicValue> productCharacteristics;
	private String productStatus;
	private String productSerialNumber;
	private String name;
	private String description;
	private List<ProductOffering> productOffering;
	private List<Product> productComponents;
	protected String payChannelId;
	protected String parentCatalogId;

	
    public boolean isLevelOne() {
		return isLevelOne;
	}

	public void setLevelOne(boolean isLevelOne) {
		this.isLevelOne = isLevelOne;
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

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	
	public String getPayChannelId() {
		return payChannelId;
	}

	public void setPayChannelId(String payChannelId) {
		this.payChannelId = payChannelId;
	}

	public String getParentCatalogId() {
		return parentCatalogId;
	}

	public void setParentCatalogId(String parentCatalogId) {
		this.parentCatalogId = parentCatalogId;
	}

	public String getProductStatusDesc() {
		return productStatusDesc;
	}

	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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

	public String getOfferCatatalogId() {
		return offerCatatalogId;
	}

	public void setOfferCatatalogId(String offerCatatalogId) {
		this.offerCatatalogId = offerCatatalogId;
	}

	public String getResourceID() {
		return resourceID;
	}

	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}

	public Object getTotalOfferingPrice() {
		return totalOfferingPrice;
	}

	public void setTotalOfferingPrice(Object totalOfferingPrice) {
		this.totalOfferingPrice = totalOfferingPrice;
	}
	
	public String getParentProductSerialNumber() {
		return parentProductSerialNumber;
	}

	public void setParentProductSerialNumber(String parentProductSerialNumber) {
		this.parentProductSerialNumber = parentProductSerialNumber;
	}

	public List<ProductCharacteristicValue> getProductCharacteristics() {
		return productCharacteristics;
	}

	public void setProductCharacteristics(List<ProductCharacteristicValue> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getProductSerialNumber() {
		return productSerialNumber;
	}

	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProductOffering> getProductOffering() {
		if (productOffering == null) {
			productOffering = new ArrayList<ProductOffering>();
		}
		return productOffering;
	}

	public void setProductOffering(List<ProductOffering> productOffering) {
		this.productOffering = productOffering;
	}

	public List<Product> getProductComponents() {
		if (productComponents == null) {
			productComponents = new ArrayList<Product>();
		}
		return productComponents;
	}

	public void setProductComponents(List<Product> productComponents) {
		this.productComponents = productComponents;
	}

}
