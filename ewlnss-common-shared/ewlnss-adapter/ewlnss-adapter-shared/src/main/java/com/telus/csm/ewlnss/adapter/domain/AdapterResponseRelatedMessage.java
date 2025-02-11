package com.telus.csm.ewlnss.adapter.domain;

import java.io.Serializable;

/**
 * 
 * @author Jose.Mena
 *
 */
public class AdapterResponseRelatedMessage implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String relatedErrorTypeCd;
	private String relatedErrorCd;
	private String relatedErrorMessageTxt;

	public String getRelatedErrorTypeCd() {
		return relatedErrorTypeCd;
	}

	public void setRelatedErrorTypeCd(String relatedErrorTypeCd) {
		this.relatedErrorTypeCd = relatedErrorTypeCd;
	}

	public String getRelatedErrorCd() {
		return relatedErrorCd;
	}

	public void setRelatedErrorCd(String relatedErrorCd) {
		this.relatedErrorCd = relatedErrorCd;
	}

	public String getRelatedErrorMessageTxt() {
		return relatedErrorMessageTxt;
	}

	public void setRelatedErrorMessageTxt(String relatedErrorMessageTxt) {
		this.relatedErrorMessageTxt = relatedErrorMessageTxt;
	}

}
