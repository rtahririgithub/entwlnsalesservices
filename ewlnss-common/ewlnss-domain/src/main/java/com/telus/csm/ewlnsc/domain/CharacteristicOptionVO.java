package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class CharacteristicOptionVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
	private boolean isDefault;

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

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CharacteristicOptionVO [name=").append(name).append(", value=").append(value)
				.append(", isDefault=").append(isDefault).append("]");
		return builder.toString();
	}

}