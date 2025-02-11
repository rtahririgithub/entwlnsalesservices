package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class PersonalCreditInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Boolean creditRestrictInd;
	protected String creditValueCd;
	protected Boolean promotionalGiftInd; 

	public Boolean getCreditRestrictInd() {
		return creditRestrictInd;
	}

	public void setCreditRestrictInd(Boolean creditRestrictInd) {
		this.creditRestrictInd = creditRestrictInd;
	}

	public String getCreditValueCd() {
		return creditValueCd;
	}

	public void setCreditValueCd(String creditValueCd) {
		this.creditValueCd = creditValueCd;
	}

	public Boolean getPromotionalGiftInd() {
		return promotionalGiftInd;
	}

	public void setPromotionalGiftInd(Boolean promotionalGiftInd) {
		this.promotionalGiftInd = promotionalGiftInd;
	}

}
