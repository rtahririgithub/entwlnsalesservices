package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class CharacteristicVO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private Object value;
	private String displayName;
	private List<CharacteristicOptionVO> options = null;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public List<CharacteristicOptionVO> getOptions() {
		return options;
	}

	public void setOptions(List<CharacteristicOptionVO> options) {
		this.options = options;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CharacteristicVO [name=").append(name).append(", value=").append(value).append(", displayName=")
				.append(displayName).append(", options=").append(options).append("]");
		return builder.toString();
	}

}