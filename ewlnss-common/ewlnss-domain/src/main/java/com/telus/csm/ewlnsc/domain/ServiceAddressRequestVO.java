package com.telus.csm.ewlnsc.domain;

import com.telus.tmi.xmlschema.srv.smo.ordermgmt.serviceaddressmanagementservicerequestresponse_v1.ServiceAddress;


public class ServiceAddressRequestVO extends OperationHeaderVO {

	private static final long serialVersionUID = 1L;

	private String phoneNumber;
	private ServiceAddress serviceAddress;
	private String addressId;
	private String provinceCode;
	private String npaNxx;
	
	public String getNpaNxx() {
		return npaNxx;
	}

	public void setNpaNxx(String npaNxx) {
		this.npaNxx = npaNxx;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public ServiceAddress getServiceAddress() {
		return serviceAddress;
	}
	
	public void setServiceAddress(ServiceAddress serviceAddress) {
		this.serviceAddress = serviceAddress;
	}
	
	public String getAddressId() {
		return addressId;
	}
	
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	
	public String getProvinceCode() {
		return provinceCode;
	}
	
	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	
}
