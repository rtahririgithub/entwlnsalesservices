package com.telus.csm.ewlnsc.adapter.woscs.domain;

import java.util.List;

public class ProductCharacteristic {
	
    private String name;
    private String value;
    private String catalogAttributeId;
    private String description;
    private Boolean enabledInd;
    private Boolean visibleInd;
    private Boolean mandatoryInd;
    private String displayValue;
    private List<ValidValues> validValues;
    private String mask;
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getCatalogAttributeId() {
		return catalogAttributeId;
	}
	public void setCatalogAttributeId(String catalogAttributeId) {
		this.catalogAttributeId = catalogAttributeId;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisplayValue() {
		return displayValue;
	}
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	public List<ValidValues> getValidValues() {
		return validValues;
	}
	public void setValidValues(List<ValidValues> validValues) {
		this.validValues = validValues;
	}
	public Boolean getEnabledInd() {
		return enabledInd;
	}
	public void setEnabledInd(Boolean enabledInd) {
		this.enabledInd = enabledInd;
	}
	public Boolean getVisibleInd() {
		return visibleInd;
	}
	public Boolean getMandatoryInd() {
		return mandatoryInd;
	}
	public void setVisibleInd(Boolean visibleInd) {
		this.visibleInd = visibleInd;
	}
	public void setMandatoryInd(Boolean mandatoryInd) {
		this.mandatoryInd = mandatoryInd;
	}
	public String getMask() {
		return mask;
	}
	public void setMask(String mask) {
		this.mask = mask;
	}


}