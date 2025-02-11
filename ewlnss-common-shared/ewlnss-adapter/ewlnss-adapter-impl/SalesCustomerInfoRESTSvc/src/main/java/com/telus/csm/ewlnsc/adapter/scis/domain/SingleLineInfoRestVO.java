package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class SingleLineInfoRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected List<SubscriptionNumberInfoRestVO> subscriptionNumList;
	protected Boolean singleLineNumListedInd;
	protected List<FeatureInfoRestVO> callingFeatureList;
	protected List<FeatureInfoRestVO> tollPlanList;
	protected Boolean wholesaleAdslInd;
	protected List<String> featuresPackList;
	protected List<String> homePhonePackList;
	protected List<PromotionInfoRestVO> promotionList;
	protected List<String> homePhonePackIdList;


	public List<String> getHomePhonePackIdList() {
		return homePhonePackIdList;
	}

	public void setHomePhonePackIdList(List<String> homePhonePackIdList) {
		this.homePhonePackIdList = homePhonePackIdList;
	}

	public List<SubscriptionNumberInfoRestVO> getSubscriptionNumList() {
		return subscriptionNumList;
	}

	public void setSubscriptionNumList(
			List<SubscriptionNumberInfoRestVO> subscriptionNumList) {
		this.subscriptionNumList = subscriptionNumList;
	}

	public Boolean getSingleLineNumListedInd() {
		return singleLineNumListedInd;
	}

	public void setSingleLineNumListedInd(Boolean singleLineNumListedInd) {
		this.singleLineNumListedInd = singleLineNumListedInd;
	}

	public List<FeatureInfoRestVO> getCallingFeatureList() {
		return callingFeatureList;
	}

	public void setCallingFeatureList(List<FeatureInfoRestVO> callingFeatureList) {
		this.callingFeatureList = callingFeatureList;
	}

	public List<FeatureInfoRestVO> getTollPlanList() {
		return tollPlanList;
	}

	public void setTollPlanList(List<FeatureInfoRestVO> tollPlanList) {
		this.tollPlanList = tollPlanList;
	}

	public Boolean getWholesaleAdslInd() {
		return wholesaleAdslInd;
	}

	public void setWholesaleAdslInd(Boolean wholesaleAdslInd) {
		this.wholesaleAdslInd = wholesaleAdslInd;
	}

	public List<String> getFeaturesPackList() {
		return featuresPackList;
	}

	public void setFeaturesPackList(List<String> featuresPackList) {
		this.featuresPackList = featuresPackList;
	}

	public List<String> getHomePhonePackList() {
		return homePhonePackList;
	}

	public void setHomePhonePackList(List<String> homePhonePackList) {
		this.homePhonePackList = homePhonePackList;
	}

	public List<PromotionInfoRestVO> getPromotionList() {
		return promotionList;
	}

	public void setPromotionList(List<PromotionInfoRestVO> promotionList) {
		this.promotionList = promotionList;
	}

}
