package com.telus.csm.ewlnsms.rest.webservices.vo;

public class AddressVO {

	private Long addressId;
	private Integer streetNum;
	private String streetName;
	private String unitType;
	private String vector;
	private String unitNum;
	private String city;
	private String provinceCd;
	private String countryCd;
	private String postalCd;
	private String poBoxStationName;
	private String poBoxNum;
	private String rrNum;

	public Long getAddressId() {
		return addressId;
	}
	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}
	public Integer getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(Integer streetNum) {
		this.streetNum = streetNum;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getUnitType() {
		return unitType;
	}
	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	public String getVector() {
		return vector;
	}
	public void setVector(String vector) {
		this.vector = vector;
	}
	public String getUnitNum() {
		return unitNum;
	}
	public void setUnitNum(String unitNum) {
		this.unitNum = unitNum;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getProvinceCd() {
		return provinceCd;
	}
	public void setProvinceCd(String provinceCd) {
		this.provinceCd = provinceCd;
	}
	public String getCountryCd() {
		return countryCd;
	}
	public void setCountryCd(String countryCd) {
		this.countryCd = countryCd;
	}
	public String getPostalCd() {
		return postalCd;
	}
	public void setPostalCd(String postalCd) {
		this.postalCd = postalCd;
	}
	public String getPoBoxStationName() {
		return poBoxStationName;
	}
	public void setPoBoxStationName(String poBoxStationName) {
		this.poBoxStationName = poBoxStationName;
	}
	public String getPoBoxNum() {
		return poBoxNum;
	}
	public void setPoBoxNum(String poBoxNum) {
		this.poBoxNum = poBoxNum;
	}
	public String getRrNum() {
		return rrNum;
	}
	public void setRrNum(String rrNum) {
		this.rrNum = rrNum;
	}
	public void setupDemo() {
		setStreetNum(123);
		setStreetName("Main St");
		setCity("Rock");
		setProvinceCd("ON");
		setCountryCd("CAN");
		setPostalCd("W2E1Q1");	
	}

}
