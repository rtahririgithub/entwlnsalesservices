package com.telus.csm.ewlnsc.adapter.domain.quote;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {
	
    private String actionType;
    private String catalogId;
    private String description;
    private String name;
    private String productSerialNumber;
	private String maxQuantity;
	private String minQuantity;
    private String payChannelId;
    private String serviceInstanceId;
    private String productStatus;    
    private String productStatusDesc;
    private String serviceType;
    private Boolean isMandatory;
    private Boolean isEnabled;
    private Boolean isVisible;
    private Boolean isSellable;
    private List<ProductOffering> productOffering;
    private List<ProductComponent> productComponents;
    private String resourceID;
    private String parentProductSerialNumber;
    private List<ProductCharacteristic> productCharacteristics;
    
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
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void addProductComponent(ProductComponent param) {
		if (productComponents == null) {
			productComponents = new ArrayList<ProductComponent>();
		}
		productComponents.add(param);
	}
	public List<ProductComponent> getProductComponents() {
		return productComponents;
	}
	public void setProductComponents(List<ProductComponent> productComponents) {
		this.productComponents = productComponents;
	}
	
	public String getProductSerialNumber() {
		return productSerialNumber;
	}
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}
	
	public void addProductOffering(ProductOffering param) {
		if (productOffering == null) {
			productOffering = new ArrayList<ProductOffering>();
		}
		productOffering.add(param);
	}
	public List<ProductOffering> getProductOffering() {
		return productOffering;
	}
	public void setProductOffering(List<ProductOffering> productOffering) {
		this.productOffering = productOffering;
	}
	public String getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public String getPayChannelId() {
		return payChannelId;
	}
	public void setPayChannelId(String payChannelId) {
		this.payChannelId = payChannelId;
	}
	public String getServiceInstanceId() {
		return serviceInstanceId;
	}
	public void setServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
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
	public Boolean getIsMandatory() {
		return isMandatory;
	}
	public Boolean getIsEnabled() {
		return isEnabled;
	}
	public Boolean getIsVisible() {
		return isVisible;
	}
	public Boolean getIsSellable() {
		return isSellable;
	}
	public void setIsMandatory(Boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	public void setIsEnabled(Boolean isEnabled) {
		this.isEnabled = isEnabled;
	}
	public void setIsVisible(Boolean isVisible) {
		this.isVisible = isVisible;
	}
	public void setIsSellable(Boolean isSellable) {
		this.isSellable = isSellable;
	}
	public String getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(String minQuantity) {
		this.minQuantity = minQuantity;
	}
	public String getResourceID() {
		return resourceID;
	}
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}
	public String getParentProductSerialNumber() {
		return parentProductSerialNumber;
	}
	public List<ProductCharacteristic> getProductCharacteristics() {
		return productCharacteristics;
	}
	public void setParentProductSerialNumber(String parentProductSerialNumber) {
		this.parentProductSerialNumber = parentProductSerialNumber;
	}
	public void setProductCharacteristics(List<ProductCharacteristic> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}

}