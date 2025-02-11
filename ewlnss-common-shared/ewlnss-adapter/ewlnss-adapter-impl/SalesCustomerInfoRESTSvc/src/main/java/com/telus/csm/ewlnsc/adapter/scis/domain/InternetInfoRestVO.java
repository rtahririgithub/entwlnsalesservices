package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class InternetInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected String internetTypeCd;
	protected List<PromotionInfoRestVO> promotionList;
	@JsonProperty("addOnPacks")
	protected List<AddOnPackInfoRestVO> addOnPacks;

	public String getInternetTypeCd() {
		return internetTypeCd;
	}

	public void setInternetTypeCd(String internetTypeCd) {
		this.internetTypeCd = internetTypeCd;
	}

	public List<PromotionInfoRestVO> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionInfoRestVO> promotionList) {
		this.promotionList = promotionList;
	}

	public List<AddOnPackInfoRestVO> getAddOnPacks() {
		return addOnPacks;
	}

	public void setAddOnPacks(List<AddOnPackInfoRestVO> addOnPacks) {
		this.addOnPacks = addOnPacks;
	}
	

}
