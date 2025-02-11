package com.telus.csm.ewlnsc.adapter.woscs.domain;

import java.util.ArrayList;
import java.util.List;

public class ProductComponent {
	
    private String actionType;
    private String catalogId;
    private String description;
    private String name;
    private String serviceType;
    private String productSerialNumber;
    private String productStatus;
    private String productStatusDesc;
    private String parentProductSerialNumber;
    private Boolean isMandatory;
    private Boolean isEnabled;
    private Boolean isVisible;
    private Boolean isSellable;
    private String parentCatalogId;
    private String maxQuantity;
    private String minQuantity;
    private String serviceInstanceId;
    private List<ProductCharacteristic> productCharacteristics;
    private List<ProductOffering> productOffering;
    private List<ProductComponent> productComponents;
    private String resourceID;
    
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
	
	public void addProductCharacteristic(ProductCharacteristic param) {
		if(param == null) {
			return;
		}
		if (productCharacteristics == null) {
			productCharacteristics = new ArrayList<ProductCharacteristic>();
		}
		productCharacteristics.add(param);
	}
	public List<ProductCharacteristic> getProductCharacteristics() {
		return productCharacteristics;
	}
	public void setProductCharacteristics(List<ProductCharacteristic> productCharacteristics) {
		this.productCharacteristics = productCharacteristics;
	}
	
	public String getServiceType() {
		return serviceType;
	}
	public void setServiceType(String serviceType) {
		this.serviceType = serviceType;
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
	
	public String getProductSerialNumber() {
		return productSerialNumber;
	}
	public void setProductSerialNumber(String productSerialNumber) {
		this.productSerialNumber = productSerialNumber;
	}
	
	public String getProductStatus() {
		return productStatus;
	}
	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}
    public String getParentProductSerialNumber() {
		return parentProductSerialNumber;
	}
	public void setParentProductSerialNumber(String parentProductSerialNumber) {
		this.parentProductSerialNumber = parentProductSerialNumber;
	}
	
	public String getMaxQuantity() {
		return maxQuantity;
	}
	public void setMaxQuantity(String maxQuantity) {
		this.maxQuantity = maxQuantity;
	}
	public void addProductComponent(ProductComponent param) {
		if(param == null) {
			return;
		}
		if (productComponents == null) {
			productComponents = new ArrayList<ProductComponent>();
		}
		productComponents.add(param);
	}
	public List<ProductComponent> getProductComponents() {
		return productComponents;
	}
	public String getServiceInstanceId() {
		return serviceInstanceId;
	}
	public void setServiceInstanceId(String serviceInstanceId) {
		this.serviceInstanceId = serviceInstanceId;
	}
	public void setProductComponents(List<ProductComponent> productComponents) {
		this.productComponents = productComponents;
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
	public String getParentCatalogId() {
		return parentCatalogId;
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
	public void setParentCatalogId(String parentCatalogId) {
		this.parentCatalogId = parentCatalogId;
	}
	public String getMinQuantity() {
		return minQuantity;
	}
	public void setMinQuantity(String minQuantity) {
		this.minQuantity = minQuantity;
	}
	public String getProductStatusDesc() {
		return productStatusDesc;
	}
	public void setProductStatusDesc(String productStatusDesc) {
		this.productStatusDesc = productStatusDesc;
	}
	public String getResourceID() {
		return resourceID;
	}
	public void setResourceID(String resourceID) {
		this.resourceID = resourceID;
	}


}