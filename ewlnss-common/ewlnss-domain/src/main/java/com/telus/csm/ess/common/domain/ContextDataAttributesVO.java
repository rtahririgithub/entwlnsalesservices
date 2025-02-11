package com.telus.csm.ess.common.domain;

import java.io.Serializable;

public class ContextDataAttributesVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String attributeTypeTxt;
	public String getAttributeTypeTxt() {
		return attributeTypeTxt;
	}
	public void setAttributeTypeTxt(String attributeTypeTxt) {
		this.attributeTypeTxt = attributeTypeTxt;
	}
	public String getAttributeValueTxt() {
		return attributeValueTxt;
	}
	public void setAttributeValueTxt(String attributeValueTxt) {
		this.attributeValueTxt = attributeValueTxt;
	}
	private String attributeValueTxt;

}
