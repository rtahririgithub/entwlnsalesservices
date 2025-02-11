package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Jose.Mena
 *
 */
public class CreditAssessmentVO {

	private String creditValueCd;
	private List<ProductTypeQualificationVO> productTypeQualificationList = new ArrayList<ProductTypeQualificationVO>();
	private String assessmentMessageCd;
	private Boolean promotionalGiftInd;
	private List<String> fraudMessageCdList = new ArrayList<String>();
	
	public String getCreditValueCd() {
		return creditValueCd;
	}

	public void setCreditValueCd(String creditValueCd) {
		this.creditValueCd = creditValueCd;
	}

	public List<ProductTypeQualificationVO> getProductTypeQualificationList() {
		return productTypeQualificationList;
	}

	public void setProductTypeQualificationList(List<ProductTypeQualificationVO> productTypeQualificationList) {
		this.productTypeQualificationList = productTypeQualificationList;
	}

	public String getAssessmentMessageCd() {
		return assessmentMessageCd;
	}

	public void setAssessmentMessageCd(String assessmentMessageCd) {
		this.assessmentMessageCd = assessmentMessageCd;
	}

	public Boolean getPromotionalGiftInd() {
		return promotionalGiftInd;
	}

	public void setPromotionalGiftInd(Boolean promotionalGiftInd) {
		this.promotionalGiftInd = promotionalGiftInd;
	}

	public List<String> getFraudMessageCdList() {
		return fraudMessageCdList;
	}

	public void setFraudMessageCdList(List<String> fraudMessageCdList) {
		this.fraudMessageCdList = fraudMessageCdList;
	}

}
