package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HomePhoneProductTypeVO extends ProductTypeBaseVO implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	
	private String productTypeCd;

	private HomePhoneNumberDetailTypeVO phoneNumber;

	private List<SmartRingTypeVO> smartRingPhoneList;

	private List<FFHFeatureTypeVO> features;

	public List<SmartRingTypeVO> getSmartRingPhoneList() {
		return smartRingPhoneList;
	}

	public void setSmartRingPhoneList(List<SmartRingTypeVO> smartRingPhoneList) {
		this.smartRingPhoneList = smartRingPhoneList;
	}

	public HomePhoneNumberDetailTypeVO getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(HomePhoneNumberDetailTypeVO phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<FFHFeatureTypeVO> getFeatures() {
		if(features == null) {
			return new ArrayList<FFHFeatureTypeVO>();
		}
		return features;
	}

	public void setFeatures(List<FFHFeatureTypeVO> features) {
		this.features = features;
	}

	public String getProductTypeCd() {
		return productTypeCd;
	}

	public void setProductTypeCd(String productType) {
		this.productTypeCd = productType;
	}

}
