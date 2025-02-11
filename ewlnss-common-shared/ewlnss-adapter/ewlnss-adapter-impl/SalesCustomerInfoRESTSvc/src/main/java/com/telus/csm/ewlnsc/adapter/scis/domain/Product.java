package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY) 
public class Product implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String productLine;
	private String productTypeCd;
	private String callingFeature;
	private String maxTvProfile;
	private String productTierCd;
	private int productRanking;
	private Boolean unavailableInd;
	private String unavailableReasonCd;
	private String unqualifiedReasonCd;
	
	//OMS9 - Alejandro: September 07, 2018, adding productCatalogueId
	private String productCatalogueId;
	
	public String getProductLine() {
		return productLine;
	}
	public void setProductLine(String productLine) {
		this.productLine = productLine;
	}
	public String getProductTypeCd() {
		return productTypeCd;
	}
	public void setProductTypeCd(String productTypeCd) {
		this.productTypeCd = productTypeCd;
	}
	public String getCallingFeature() {
		return callingFeature;
	}
	public void setCallingFeature(String callingFeature) {
		this.callingFeature = callingFeature;
	}
	public String getMaxTvProfile() {
		return maxTvProfile;
	}
	public void setMaxTvProfile(String maxTvProfile) {
		this.maxTvProfile = maxTvProfile;
	}
	public String getProductTierCd() {
		return productTierCd;
	}
	public void setProductTierCd(String productTierCd) {
		this.productTierCd = productTierCd;
	}
	public int getProductRanking() {
		return productRanking;
	}
	public void setProductRanking(int productRanking) {
		this.productRanking = productRanking;
	}
	public Boolean getUnavailableInd() {
		return unavailableInd;
	}
	public void setUnavailableInd(Boolean unavailableInd) {
		this.unavailableInd = unavailableInd;
	}
	public String getUnavailableReasonCd() {
		return unavailableReasonCd;
	}
	public void setUnavailableReasonCd(String unavailableReasonCd) {
		this.unavailableReasonCd = unavailableReasonCd;
	}
	public String getUnqualifiedReasonCd() {
		return unqualifiedReasonCd;
	}
	public void setUnqualifiedReasonCd(String unqualifiedReasonCd) {
		this.unqualifiedReasonCd = unqualifiedReasonCd;
	}
	public String getProductCatalogueId() {
		return productCatalogueId;
	}
	public void setProductCatalogueId(String productCatalogueId) {
		this.productCatalogueId = productCatalogueId;
	}
	
	

}
