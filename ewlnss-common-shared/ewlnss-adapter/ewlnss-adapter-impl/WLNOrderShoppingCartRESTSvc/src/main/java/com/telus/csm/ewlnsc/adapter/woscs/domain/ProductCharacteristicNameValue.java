package com.telus.csm.ewlnsc.adapter.woscs.domain;

public class ProductCharacteristicNameValue {
	
    private String name;
    private String value;
    private Boolean enabledInd;
    private String description;

	public Boolean getEnabledInd() {
		return enabledInd;
	}
	public void setEnabledInd(Boolean enabledInd) {
		this.enabledInd = enabledInd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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

}