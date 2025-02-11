package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class PhoneNumberRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String phoneNum;
	protected String phoneNumExt;
	protected String phoneNumTypeCd;

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPhoneNumExt() {
		return phoneNumExt;
	}

	public void setPhoneNumExt(String phoneNumExt) {
		this.phoneNumExt = phoneNumExt;
	}

	public String getPhoneNumTypeCd() {
		return phoneNumTypeCd;
	}

	public void setPhoneNumTypeCd(String phoneNumTypeCd) {
		this.phoneNumTypeCd = phoneNumTypeCd;
	}

}
