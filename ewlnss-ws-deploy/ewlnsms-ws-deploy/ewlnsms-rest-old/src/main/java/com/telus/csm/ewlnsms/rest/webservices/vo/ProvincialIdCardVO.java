package com.telus.csm.ewlnsms.rest.webservices.vo;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class ProvincialIdCardVO {
	protected String provincialIdNum;
	protected String provinceCd;
	
	public String getProvincialIdNum() {
		return provincialIdNum;
	}
	public void setProvincialIdNum(String provincialIdNum) {
		this.provincialIdNum = provincialIdNum;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	
	
}
