package com.telus.csm.ewlnsc.domain;

public class DirectoryListingTypeVO {

	private Boolean addressListedInd;
	private Boolean nameListedInd;
	private Boolean listedInd;
	
	public Boolean getAddressListedInd() {
		return addressListedInd;
	}

	public Boolean getNameListedInd() {
		return nameListedInd;
	}

	public Boolean getListedInd() {
		return listedInd;
	}

	public void setAddressListedInd(Boolean addressListedInd) {
		this.addressListedInd = addressListedInd;
		
	}

	public void setListedInd(Boolean listedInd) {
		this.listedInd = listedInd;
		
	}

	public void setNameListedInd(Boolean nameListedInd) {
		this.nameListedInd = nameListedInd;
		
	}

}
