package com.telus.csm.ewlnsc.adapter.domain;

import java.util.List;

public class CodeValueTypeVO {

	protected List<String> value;
	protected String code;
	public List<String> getValue() {
		return value;
	}
	public void setValue(List<String> value) {
		this.value = value;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "CodeValueTypeVO [value=" + value + ", code=" + code + "]";
	}
	
	
	
}
