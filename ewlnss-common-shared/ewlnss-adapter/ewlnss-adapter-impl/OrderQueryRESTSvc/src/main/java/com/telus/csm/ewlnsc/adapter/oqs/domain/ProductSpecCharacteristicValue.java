package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;

/**
 * 
 * @author Jose.Mena
 *
 */
public class ProductSpecCharacteristicValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String displayValue;
	private String value;

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
}
