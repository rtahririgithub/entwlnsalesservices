package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class VerificationErrorVO implements Serializable
{
	private static final long serialVersionUID = 1L;

    private String errorCd;
    private String errorTxt;
	public String getErrorCd() {
		return errorCd;
	}
	public void setErrorCd(String errorCd) {
		this.errorCd = errorCd;
	}
	public String getErrorTxt() {
		return errorTxt;
	}
	public void setErrorTxt(String errorTxt) {
		this.errorTxt = errorTxt;
	}

 
}
