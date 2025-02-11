package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL) 
public class AddressRestVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected String streetNum;
	protected String streetName;
	protected String unitTypeCd;
	protected String unitNum;
	protected String city;
	protected String provinceCd;
	protected String countryCd;
	protected String postalCd;
	protected String addressTypeCd;
	protected String poBoxStationName;
	protected String poBoxNum;
	protected String rrNum;
	protected String serviceAddressId;

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

	public String getUnitTypeCd() {
		return unitTypeCd;
	}

	public void setUnitTypeCd(String unitTypeCd) {
		this.unitTypeCd = unitTypeCd;
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

	public String getAddressTypeCd() {
		return addressTypeCd;
	}

	public void setAddressTypeCd(String addressTypeCd) {
		this.addressTypeCd = addressTypeCd;
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

	public String getServiceAddressId() {
		return serviceAddressId;
	}

	public void setServiceAddressId(String serviceAddressId) {
		this.serviceAddressId = serviceAddressId;
	}
	
}
