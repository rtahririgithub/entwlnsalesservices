package com.telus.csm.ewlnsc.grid.domain;

import java.io.Serializable;

public class WirelineCacheControl implements Serializable, Entity<String> {
	
	private static final long serialVersionUID = 1L;

	private String key;
	private String value;

	@Override
	public String getId() {
		return key;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}