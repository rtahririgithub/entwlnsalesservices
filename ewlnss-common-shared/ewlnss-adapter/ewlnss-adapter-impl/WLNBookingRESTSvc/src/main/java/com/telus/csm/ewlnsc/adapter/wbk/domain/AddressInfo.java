package com.telus.csm.ewlnsc.adapter.wbk.domain;

/**
 * 
 * @author Jose.Mena
 *
 */
public class AddressInfo {

	private String fmsAddressId;
	private String city;
	private String province;

	public String getFmsAddressId() {
		return fmsAddressId;
	}

	public void setFmsAddressId(String fmsAddressId) {
		this.fmsAddressId = fmsAddressId;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

}
