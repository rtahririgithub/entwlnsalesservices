package com.telus.csm.ewlnsc.domain;


public class DisconnectServiceAddressTypeVO {

	 private AddressVO fullAddress = null;
	 private String serviceAddressId;

	public String getServiceAddressId() {
		return serviceAddressId;
	}

	public void setServiceAddressId(String serviceAddress) {
		this.serviceAddressId = serviceAddress;
	}
	 
	public AddressVO getFullAddress() {
		return fullAddress;
	}

	public void setFullAddress(AddressVO fullAddress) {
		this.fullAddress = fullAddress;
	}
	 
}
