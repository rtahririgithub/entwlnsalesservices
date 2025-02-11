package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class TvInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected Boolean hdChannelInd;;
	protected List<PackInfoRestVO> tvPackList;
	protected List<PackInfoRestVO> tvComboList;
	protected List<PromotionInfoRestVO> promotionList;

	public Boolean getHdChannelInd() {
		return hdChannelInd;
	}

	public void setHdChannelInd(Boolean hdChannelInd) {
		this.hdChannelInd = hdChannelInd;
	}

	public List<PromotionInfoRestVO> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionInfoRestVO> promotionList) {
		this.promotionList = promotionList;
	}

	public List<PackInfoRestVO> getTvPackList() {
		return tvPackList;
	}

	public void setTvPackList(List<PackInfoRestVO> tvPackList) {
		this.tvPackList = tvPackList;
	}

	public List<PackInfoRestVO> getTvComboList() {
		return tvComboList;
	}

	public void setTvComboList(List<PackInfoRestVO> tvComboList) {
		this.tvComboList = tvComboList;
	}
}
