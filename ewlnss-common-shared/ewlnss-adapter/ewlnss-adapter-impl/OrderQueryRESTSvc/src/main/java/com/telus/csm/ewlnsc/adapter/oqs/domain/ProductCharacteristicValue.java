package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 * 
 * @author Jose.Mena
 *
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductCharacteristicValue implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String catalogAttributeId;
//	private boolean configurableInd;
//	private boolean visibleInd;
	private boolean enabledInd;
	private String valueType;
	private String displayValue;
	private String value;
	private List<ProductSpecCharacteristicValue> validValues;
	private String description;
	private String name;

	public String getCatalogAttributeId() {
		return catalogAttributeId;
	}

	public void setCatalogAttributeId(String catalogAttributeId) {
		this.catalogAttributeId = catalogAttributeId;
	}

	public boolean isEnabledInd() {
		return enabledInd;
	}

	public void setEnabledInd(boolean enabledInd) {
		this.enabledInd = enabledInd;
	}

	public String getValueType() {
		return valueType;
	}

	public void setValueType(String valueType) {
		this.valueType = valueType;
	}

	public String getDisplayValue() {
		return displayValue;
	}

	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<ProductSpecCharacteristicValue> getValidValues() {
		return validValues;
	}

	public void setValidValues(List<ProductSpecCharacteristicValue> validValues) {
		this.validValues = validValues;
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
}
