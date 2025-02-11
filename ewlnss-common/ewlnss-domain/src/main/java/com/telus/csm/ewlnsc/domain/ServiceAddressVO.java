package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class ServiceAddressVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serviceAddressId;
	private String provinceCode;
	private String cityCode;

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getServiceAddressId() {
		return serviceAddressId;
	}

	public void setServiceAddressId(String serviceAddressId) {
		this.serviceAddressId = serviceAddressId;
	}
	
}
