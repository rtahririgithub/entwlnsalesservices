package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class ServiceAddress implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String addressId;
	private String streetNum;
	private String streetName;
	private String unitType;
	private String vector;
	private String unit;
	private String city;
	private String provinceCd;
	private String countryCd;
	private String postalCd;
	private String ruralRouteNumber;
	
	//QC61508
    private String ratingNpaNxx;
    
	public String getRatingNpaNxx() {
		return ratingNpaNxx;
	}
	public void setRatingNpaNxx(String ratingNpaNxx) {
		this.ratingNpaNxx = ratingNpaNxx;
	}
	private String poBox;
	//+++
	private String npa;
	
	public String getRuralRouteNumber() {
    return ruralRouteNumber;
	
	}
	public void setRuralRouteNumber(String ruralRouteNumber) {
		this.ruralRouteNumber = ruralRouteNumber;
	}
	public String getPoBox() {
		return poBox;
	}
	public void setPoBox(String poBox) {
		this.poBox = poBox;
	}
	public String getNpa() {
		return npa;
	}
	public void setNpa(String npa) {
		this.npa = npa;
	}

	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getStreetNum() {
		return streetNum;
	}
	public void setStreetNum(String streetNum) {
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
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
}
