package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.util.ArrayList;
import java.util.List;

public class AddressVerificationSummaryVO {

	private String addressCxt;	
	private Integer matchNum;	
	private List<AddressVO> matchAddressList;	
	private Boolean validAddressInd;	
	private List<VerificationErrorVO> verificationErrorList;

	public String getAddressCxt() {
		return addressCxt;
	}
	public void setAddressCxt(String addressCxt) {
		this.addressCxt = addressCxt;
	}
	public Integer getMatchNum() {
		return matchNum;
	}
	public void setMatchNum(Integer matchNum) {
		this.matchNum = matchNum;
	}
	public List<AddressVO> getMatchAddressList() {
		
		if (matchAddressList == null) {
			matchAddressList = new ArrayList<AddressVO>();
		}

		return matchAddressList;
	}
	public void setMatchAddressList(List<AddressVO> matchAddressList) {
		this.matchAddressList = matchAddressList;
	}
	public Boolean getValidAddressInd() {
		return validAddressInd;
	}
	public void setValidAddressInd(Boolean validAddressInd) {
		this.validAddressInd = validAddressInd;
	}
	public List<VerificationErrorVO> getVerificationErrorList() {
		
		if (verificationErrorList == null) {
			verificationErrorList = new ArrayList<VerificationErrorVO>();
		}
		
		return verificationErrorList;
	}
	public void setVerificationErrorList(List<VerificationErrorVO> verificationErrorList) {
		this.verificationErrorList = verificationErrorList;
	}	

}
