
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.lang3.StringUtils;


public class AddressBaseVO extends GeoSpatialTypeVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long addressId;
	protected String addressTypeCode;
	protected List<String> additionalAddressInformation;
	protected List<String> renderedAddress;
	protected Long addrAssgnmtId;
	protected String addressAssignmentSubTypeCode;
	protected String addressAssignmentTypeCode;
	protected String addressMatchingStatusCode;
	protected String addressSearchText;
	protected String canadaPostBuildName;
	protected String canadaPostLocnName;
	protected String canadaPostRecordType;
	protected String careOf;
	protected String emailAddressText;
	protected String externalAddressId;
	protected Long externalAddressSourceId;
	protected String externalServiceAddressId;
	protected String mailingTypeCode;
	protected Long relateAddressAssignmentId;
	protected String validateAddressIndicator;
	protected String stationName;
	protected String stationQualifier;
	protected String stationTypeCode;
	protected String fleetMailOfficeName;
	protected String hmcsName;
	protected long masterSourceId;	
	protected XMLGregorianCalendar lastUpdateTimeStamp;   

	protected AddressTypeVO addressType;
    protected String attention;
    protected String primaryLine;
    protected String secondaryLine;
    protected String city;
    //protected ProvinceCodeVO province;
    protected String postalCode;
    protected String country;
    protected String civicNo;
    protected String civicNoSuffix;
    protected String streetNumber;
    protected String streetNumberSuffix;
    protected String streetDirection;
    protected String streetName;
    protected String streetType;
    protected String unitType;
    protected String unit;
    protected String poBox;
    protected String ruralLocation;
    protected String ruralQualifier;
    protected String ruralSite;
    protected String ruralCompartment;
    protected String ruralDeliveryType;
    protected String ruralGroup;
    protected String ruralNumber;
    protected String ruralType;
    protected String zipGeoCode;
    protected String foreignState;
    protected String provinceStateCode;
    protected String municipalityName;
    
    //use by create customer
    protected String countryCode;
    protected String postOfficeBoxNumber;
    protected String postalZipCode;
    protected String streetDirectionCode;
    protected String streetTypeCode;
    protected String unitTypeCode;
    protected String unitNumber;
    protected String civicNumber;
    protected String civicNumberSuffix;
    protected String ruralRouteNumber;
    protected String ruralRouteTypeCode;

    protected String addressNumberPrefixTxt;
    protected String streetDirectionSuffixCd;
    protected String streetDirectionPrefixCd;
    protected String streetNamePreModifierText;
    
	protected String streetNamePostModifierText;
    protected String streetTypeSuffixCd;
    protected String streetTypePrefixCd;
    protected String unitName; //possible value could be unit
    protected String buildingTypeCd;
    protected String buildingName;
    protected String floorTypeCd;
    protected String floorName;
    protected String landmarkName;
    protected String placeName;
    
    
    
    public String getFloorTypeCd() {
		return floorTypeCd;
	}

	public void setFloorTypeCd(String floorTypeCd) {
		this.floorTypeCd = floorTypeCd;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getLandmarkName() {
		return landmarkName;
	}

	public void setLandmarkName(String landmarkName) {
		this.landmarkName = landmarkName;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getBuildingTypeCd() {
		return buildingTypeCd;
	}

	public void setBuildingTypeCd(String buildingTypeCd) {
		this.buildingTypeCd = buildingTypeCd;
	}

	public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

    
    public String getStreetNamePreModifierText() {
		return streetNamePreModifierText;
	}

	public void setStreetNamePreModifierText(String streetNamePreModifierText) {
		this.streetNamePreModifierText = streetNamePreModifierText;
	}

	public String getStreetNamePostModifierText() {
		return streetNamePostModifierText;
	}

	public void setStreetNamePostModifierText(String streetNamePostModifierText) {
		this.streetNamePostModifierText = streetNamePostModifierText;
	}

	public String getStreetTypeSuffixCd() {
		return streetTypeSuffixCd;
	}

	public void setStreetTypeSuffixCd(String streetTypeSuffixCd) {
		this.streetTypeSuffixCd = streetTypeSuffixCd;
	}

	public String getStreetTypePrefixCd() {
		return streetTypePrefixCd;
	}

	public void setStreetTypePrefixCd(String streetTypePrefixCd) {
		this.streetTypePrefixCd = streetTypePrefixCd;
	}

	public String getStreetDirectionSuffixCd() {
		return streetDirectionSuffixCd;
	}

	public void setStreetDirectionSuffixCd(String streetDirectionSuffixCd) {
		this.streetDirectionSuffixCd = streetDirectionSuffixCd;
	}

	public String getStreetDirectionPrefixCd() {
		return streetDirectionPrefixCd;
	}

	public void setStreetDirectionPrefixCd(String streetDirectionPrefixCd) {
		this.streetDirectionPrefixCd = streetDirectionPrefixCd;
	}

	public String getAddressNumberPrefixTxt() {
		return addressNumberPrefixTxt;
	}

	public void setAddressNumberPrefixTxt(String addressNumberPrefixTxt) {
		this.addressNumberPrefixTxt = addressNumberPrefixTxt;
	}

	public String getProvinceStateCode() {
		return provinceStateCode;
	}

	public void setProvinceStateCode(String provinceStateCode) {
		this.provinceStateCode = provinceStateCode;
	}

	public String getMunicipalityName() {
		return municipalityName;
	}

	public void setMunicipalityName(String municipalityName) {
		this.municipalityName = municipalityName;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public String getAddressTypeCode() {
		return addressTypeCode;
	}

	public void setAddressTypeCode(String addressTypeCode) {
		this.addressTypeCode = addressTypeCode;
	}

	public List<String> getAdditionalAddressInformation() {
		if(additionalAddressInformation==null){
			additionalAddressInformation = new ArrayList<String>();
		}
		return additionalAddressInformation;
	}

	public void setAdditionalAddressInformation(
			List<String> additionalAddressInformation) {
		this.additionalAddressInformation = additionalAddressInformation;
	}

	public List<String> getRenderedAddress() {
		if(renderedAddress==null){
			renderedAddress = new ArrayList<String>();
		}
		return renderedAddress;
	}

	public void setRenderedAddress(List<String> renderedAddress) {
		this.renderedAddress = renderedAddress;
	}

	public Long getAddrAssgnmtId() {
		return addrAssgnmtId;
	}

	public void setAddrAssgnmtId(Long addrAssgnmtId) {
		this.addrAssgnmtId = addrAssgnmtId;
	}

	public String getAddressAssignmentSubTypeCode() {
		return addressAssignmentSubTypeCode;
	}

	public void setAddressAssignmentSubTypeCode(String addressAssignmentSubTypeCode) {
		this.addressAssignmentSubTypeCode = addressAssignmentSubTypeCode;
	}

	public String getAddressAssignmentTypeCode() {
		return addressAssignmentTypeCode;
	}

	public void setAddressAssignmentTypeCode(String addressAssignmentTypeCode) {
		this.addressAssignmentTypeCode = addressAssignmentTypeCode;
	}

	public String getAddressMatchingStatusCode() {
		return addressMatchingStatusCode;
	}

	public void setAddressMatchingStatusCode(String addressMatchingStatusCode) {
		this.addressMatchingStatusCode = addressMatchingStatusCode;
	}

	public String getAddressSearchText() {
		return addressSearchText;
	}

	public void setAddressSearchText(String addressSearchText) {
		this.addressSearchText = addressSearchText;
	}

	public String getCanadaPostBuildName() {
		return canadaPostBuildName;
	}

	public void setCanadaPostBuildName(String canadaPostBuildName) {
		this.canadaPostBuildName = canadaPostBuildName;
	}

	public String getCanadaPostLocnName() {
		return canadaPostLocnName;
	}

	public void setCanadaPostLocnName(String canadaPostLocnName) {
		this.canadaPostLocnName = canadaPostLocnName;
	}

	public String getCanadaPostRecordType() {
		return canadaPostRecordType;
	}

	public void setCanadaPostRecordType(String canadaPostRecordType) {
		this.canadaPostRecordType = canadaPostRecordType;
	}

	public String getCareOf() {
		return careOf;
	}

	public void setCareOf(String careOf) {
		this.careOf = careOf;
	}

	public String getEmailAddressText() {
		return emailAddressText;
	}

	public void setEmailAddressText(String emailAddressText) {
		this.emailAddressText = emailAddressText;
	}

	public String getExternalAddressId() {
		return externalAddressId;
	}

	public void setExternalAddressId(String externalAddressId) {
		this.externalAddressId = externalAddressId;
	}

	public Long getExternalAddressSourceId() {
		return externalAddressSourceId;
	}

	public void setExternalAddressSourceId(Long externalAddressSourceId) {
		this.externalAddressSourceId = externalAddressSourceId;
	}

	public String getExternalServiceAddressId() {
		return externalServiceAddressId;
	}

	public void setExternalServiceAddressId(String externalServiceAddressId) {
		this.externalServiceAddressId = externalServiceAddressId;
	}

	public String getMailingTypeCode() {
		return mailingTypeCode;
	}

	public void setMailingTypeCode(String mailingTypeCode) {
		this.mailingTypeCode = mailingTypeCode;
	}

	public Long getRelateAddressAssignmentId() {
		return relateAddressAssignmentId;
	}

	public void setRelateAddressAssignmentId(Long relateAddressAssignmentId) {
		this.relateAddressAssignmentId = relateAddressAssignmentId;
	}

	public String getValidateAddressIndicator() {
		return validateAddressIndicator;
	}

	public void setValidateAddressIndicator(String validateAddressIndicator) {
		this.validateAddressIndicator = validateAddressIndicator;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationQualifier() {
		return stationQualifier;
	}

	public void setStationQualifier(String stationQualifier) {
		this.stationQualifier = stationQualifier;
	}

	public String getStationTypeCode() {
		return stationTypeCode;
	}

	public void setStationTypeCode(String stationTypeCode) {
		this.stationTypeCode = stationTypeCode;
	}

	public String getFleetMailOfficeName() {
		return fleetMailOfficeName;
	}

	public void setFleetMailOfficeName(String fleetMailOfficeName) {
		this.fleetMailOfficeName = fleetMailOfficeName;
	}

	public String getHmcsName() {
		return hmcsName;
	}

	public void setHmcsName(String hmcsName) {
		this.hmcsName = hmcsName;
	}

	public long getMasterSourceId() {
		return masterSourceId;
	}

	public void setMasterSourceId(long masterSourceId) {
		this.masterSourceId = masterSourceId;
	}
	
	public XMLGregorianCalendar getLastUpdateTimeStamp() {
		return lastUpdateTimeStamp;
	}

	public void setLastUpdateTimeStamp(XMLGregorianCalendar lastUpdateTimeStamp) {
		this.lastUpdateTimeStamp = lastUpdateTimeStamp;
	}

	/**
     * Gets the value of the addressType property.
     * 
     * @return
     *     possible object is
     *     {@link AddressTypeVO }
     *     
     */
    public AddressTypeVO getAddressType() {
        return addressType;
    }

    /**
     * Sets the value of the addressType property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddressTypeVO }
     *     
     */
    public void setAddressType(AddressTypeVO value) {
        this.addressType = value;
    }

    /**
     * Gets the value of the attention property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAttention() {
        return attention;
    }

    /**
     * Sets the value of the attention property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAttention(String value) {
        this.attention = value;
    }

    /**
     * Gets the value of the primaryLine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrimaryLine() {
        return primaryLine;
    }

    /**
     * Sets the value of the primaryLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrimaryLine(String value) {
        this.primaryLine = value;
    }

    /**
     * Gets the value of the secondaryLine property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSecondaryLine() {
        return secondaryLine;
    }

    /**
     * Sets the value of the secondaryLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSecondaryLine(String value) {
        this.secondaryLine = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the province property.
     * 
     * @return
     *     possible object is
     *     {@link ProvinceCodeVO }
     *     
     */
/*    public ProvinceCodeVO getProvince() {
        return province;
    }

    *//**
     * Sets the value of the province property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProvinceCodeVO }
     *     
     *//*
    public void setProvince(ProvinceCodeVO value) {
        this.province = value;
    }
    *//**
     * 
     * @param value
     *//*
    public void setProvince(String value) {
        this.province = ProvinceCodeVO.valueOf(value);
    }*/
    
    
    
    
    /**
     * Gets the value of the postalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Sets the value of the postalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalCode(String value) {
        this.postalCode = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the civicNo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivicNo() {
        return civicNo;
    }

    /**
     * Sets the value of the civicNo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivicNo(String value) {
        this.civicNo = value;
    }

    /**
     * Gets the value of the civicNoSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivicNoSuffix() {
        return civicNoSuffix;
    }

    /**
     * Sets the value of the civicNoSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivicNoSuffix(String value) {
        this.civicNoSuffix = value;
    }

    /**
     * Gets the value of the streetNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Sets the value of the streetNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNumber(String value) {
        this.streetNumber = value;
    }

    /**
     * Gets the value of the streetNumberSuffix property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetNumberSuffix() {
        return streetNumberSuffix;
    }

    /**
     * Sets the value of the streetNumberSuffix property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNumberSuffix(String value) {
        this.streetNumberSuffix = value;
    }

    /**
     * Gets the value of the streetDirection property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetDirection() {
        return streetDirection;
    }

    /**
     * Sets the value of the streetDirection property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetDirection(String value) {
        this.streetDirection = value;
    }

    /**
     * Gets the value of the streetName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the value of the streetName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetName(String value) {
        this.streetName = value;
    }

    /**
     * Gets the value of the streetType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetType() {
        return streetType;
    }

    /**
     * Sets the value of the streetType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetType(String value) {
        this.streetType = value;
    }

    /**
     * Gets the value of the unitType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitType() {
        return unitType;
    }

    /**
     * Sets the value of the unitType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitType(String value) {
        this.unitType = value;
    }

    /**
     * Gets the value of the unit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets the value of the unit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnit(String value) {
        this.unit = value;
    }

    /**
     * Gets the value of the poBox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * Sets the value of the poBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoBox(String value) {
        this.poBox = value;
    }

    /**
     * Gets the value of the ruralLocation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralLocation() {
        return ruralLocation;
    }

    /**
     * Sets the value of the ruralLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralLocation(String value) {
        this.ruralLocation = value;
    }

    /**
     * Gets the value of the ruralQualifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralQualifier() {
        return ruralQualifier;
    }

    /**
     * Sets the value of the ruralQualifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralQualifier(String value) {
        this.ruralQualifier = value;
    }

    /**
     * Gets the value of the ruralSite property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralSite() {
        return ruralSite;
    }

    /**
     * Sets the value of the ruralSite property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralSite(String value) {
        this.ruralSite = value;
    }

    /**
     * Gets the value of the ruralCompartment property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralCompartment() {
        return ruralCompartment;
    }

    /**
     * Sets the value of the ruralCompartment property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralCompartment(String value) {
        this.ruralCompartment = value;
    }

    /**
     * Gets the value of the ruralDeliveryType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralDeliveryType() {
        return ruralDeliveryType;
    }

    /**
     * Sets the value of the ruralDeliveryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralDeliveryType(String value) {
        this.ruralDeliveryType = value;
    }

    /**
     * Gets the value of the ruralGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralGroup() {
        return ruralGroup;
    }

    /**
     * Sets the value of the ruralGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralGroup(String value) {
        this.ruralGroup = value;
    }

    /**
     * Gets the value of the ruralNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralNumber() {
        return ruralNumber;
    }

    /**
     * Sets the value of the ruralNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralNumber(String value) {
        this.ruralNumber = value;
    }

    /**
     * Gets the value of the ruralType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralType() {
        return ruralType;
    }

    /**
     * Sets the value of the ruralType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralType(String value) {
        this.ruralType = value;
    }

    /**
     * Gets the value of the zipGeoCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZipGeoCode() {
        return zipGeoCode;
    }

    /**
     * Sets the value of the zipGeoCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZipGeoCode(String value) {
        this.zipGeoCode = value;
    }

    /**
     * Gets the value of the foreignState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForeignState() {
        return foreignState;
    }

    /**
     * Sets the value of the foreignState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForeignState(String value) {
        this.foreignState = value;
    }

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getPostOfficeBoxNumber() {
		return postOfficeBoxNumber;
	}

	public void setPostOfficeBoxNumber(String postOfficeBoxNumber) {
		this.postOfficeBoxNumber = postOfficeBoxNumber;
	}

	public String getPostalZipCode() {
		return postalZipCode;
	}

	public void setPostalZipCode(String postalZipCode) {
		this.postalZipCode = postalZipCode;
	}

	public String getStreetDirectionCode() {
		return streetDirectionCode;
	}

	public void setStreetDirectionCode(String streetDirectionCode) {
		this.streetDirectionCode = streetDirectionCode;
	}

	public String getStreetTypeCode() {
		return streetTypeCode;
	}

	public void setStreetTypeCode(String streetTypeCode) {
		this.streetTypeCode = streetTypeCode;
	}

	public String getUnitTypeCode() {
		return unitTypeCode;
	}

	public void setUnitTypeCode(String unitTypeCode) {
		this.unitTypeCode = unitTypeCode;
	}

	public String getUnitNumber() {
		return unitNumber;
	}

	public void setUnitNumber(String unitNumber) {
		this.unitNumber = unitNumber;
	}

	public String getCivicNumber() {
		return civicNumber;
	}

	public void setCivicNumber(String civicNumber) {
		this.civicNumber = civicNumber;
	}

	public String getCivicNumberSuffix() {
		return civicNumberSuffix;
	}

	public void setCivicNumberSuffix(String civicNumberSuffix) {
		this.civicNumberSuffix = civicNumberSuffix;
	}

	public String getRuralRouteNumber() {
		return ruralRouteNumber;
	}

	public void setRuralRouteNumber(String ruralRouteNumber) {
		this.ruralRouteNumber = ruralRouteNumber;
	}

	public String getRuralRouteTypeCode() {
		return ruralRouteTypeCode;
	}

	public void setRuralRouteTypeCode(String ruralRouteTypeCode) {
		this.ruralRouteTypeCode = ruralRouteTypeCode;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AddressBaseVO){
			AddressBaseVO addressBaseVO = (AddressBaseVO) obj;
			boolean identical = true;

			// identical = compareLongs(identical, addressId, addressBaseVO.getAddressId());
			// identical = compareStrings(identical, addressTypeCode, addressBaseVO.getAddressTypeCode());
			// identical = compareStringLists(identical, additionalAddressInformation, addressBaseVO.getAdditionalAddressInformation());
			// identical = compareStringLists(identical, renderedAddress, addressBaseVO.getRenderedAddress());
			// identical = compareLongs(identical, addrAssgnmtId, addressBaseVO.getAddrAssgnmtId());
			// identical = compareStrings(identical, addressAssignmentSubTypeCode, addressBaseVO.getAddressAssignmentSubTypeCode());
			// identical = compareStrings(identical, addressAssignmentTypeCode, addressBaseVO.getAddressAssignmentTypeCode());
			// identical = compareStrings(identical, addressMatchingStatusCode, addressBaseVO.getAddressMatchingStatusCode());
			// identical = compareStrings(identical, addressSearchText, addressBaseVO.getAddressSearchText());
			// identical = compareStrings(identical, canadaPostBuildName, addressBaseVO.getCanadaPostBuildName());
			// identical = compareStrings(identical, canadaPostLocnName, addressBaseVO.getCanadaPostLocnName());
			// identical = compareStrings(identical, canadaPostRecordType, addressBaseVO.getCanadaPostRecordType());
			// identical = compareStrings(identical, careOf, addressBaseVO.getCareOf());
			// identical = compareStrings(identical, emailAddressText, addressBaseVO.getEmailAddressText());
			// identical = compareStrings(identical, externalAddressId, addressBaseVO.getExternalAddressId());
			// identical = compareLongs(identical, externalAddressSourceId, addressBaseVO.getExternalAddressSourceId());
			// identical = compareStrings(identical, externalServiceAddressId, addressBaseVO.getExternalServiceAddressId());
			// identical = compareStrings(identical, mailingTypeCode, addressBaseVO.getMailingTypeCode());
			// identical = compareLongs(identical, relateAddressAssignmentId, addressBaseVO.getRelateAddressAssignmentId());
			// identical = compareStrings(identical, validateAddressIndicator, addressBaseVO.getValidateAddressIndicator());
			// identical = compareStrings(identical, stationName, addressBaseVO.getStationName());
			// identical = compareStrings(identical, stationQualifier, addressBaseVO.getStationQualifier());
			// identical = compareStrings(identical, stationTypeCode, addressBaseVO.getStationTypeCode());
			// identical = compareStrings(identical, fleetMailOfficeName, addressBaseVO.getFleetMailOfficeName());
			// identical = compareStrings(identical, hmcsName, addressBaseVO.getHmcsName());
			// identical = compareLongs(identical, masterSourceId, addressBaseVO.getMasterSourceId());
			// identical = compareXMLGregorianCalendar(identical, lastUpdateTimeStamp, addressBaseVO.getLastUpdateTimeStamp());
			// identical = compareAddressTypeVO(identical, addressType, addressBaseVO.getAddressType());
			// identical = compareStrings(identical, attention, addressBaseVO.getAttention());
			// identical = compareStrings(identical, primaryLine, addressBaseVO.getPrimaryLine());
			// identical = compareStrings(identical, secondaryLine, addressBaseVO.getSecondaryLine());
			identical = compareStrings(identical, city, addressBaseVO.getCity());
			//identical = compareProvinceCodeVO(identical, province, addressBaseVO.getProvince());
			// identical = compareStrings(identical, postalCode, addressBaseVO.getPostalCode());
			// identical = compareStrings(identical, country, addressBaseVO.getCountry());
			identical = compareStrings(identical, civicNo, addressBaseVO.getCivicNo());
			identical = compareStrings(identical, civicNoSuffix, addressBaseVO.getCivicNoSuffix());
			identical = compareStrings(identical, streetNumber, addressBaseVO.getStreetNumber());
			identical = compareStrings(identical, streetNumberSuffix, addressBaseVO.getStreetNumberSuffix());
			identical = compareStrings(identical, streetDirection, addressBaseVO.getStreetDirection());
			identical = compareStrings(identical, streetName, addressBaseVO.getStreetName());
			// identical = compareStrings(identical, streetType, addressBaseVO.getStreetType());
			identical = compareStrings(identical, unitType, addressBaseVO.getUnitType());
			identical = compareStrings(identical, unit, addressBaseVO.getUnit());
			identical = compareStrings(identical, poBox, addressBaseVO.getPoBox());
			// identical = compareStrings(identical, ruralLocation, addressBaseVO.getRuralLocation());
			// identical = compareStrings(identical, ruralQualifier, addressBaseVO.getRuralQualifier());
			// identical = compareStrings(identical, ruralSite, addressBaseVO.getRuralSite());
			// identical = compareStrings(identical, ruralCompartment, addressBaseVO.getRuralCompartment());
			// identical = compareStrings(identical, ruralDeliveryType, addressBaseVO.getRuralDeliveryType());
			// identical = compareStrings(identical, ruralGroup, addressBaseVO.getRuralGroup());
			identical = compareStrings(identical, ruralNumber, addressBaseVO.getRuralNumber());
			identical = compareStrings(identical, ruralType, addressBaseVO.getRuralType());
			// identical = compareStrings(identical, zipGeoCode, addressBaseVO.getZipGeoCode());
			identical = compareStrings(identical, foreignState, addressBaseVO.getForeignState());
			identical = compareStrings(identical, provinceStateCode, addressBaseVO.getProvinceStateCode());
			identical = compareStrings(identical, municipalityName, addressBaseVO.getMunicipalityName());
			// identical = compareStrings(identical, countryCode, addressBaseVO.getCountryCode());
			identical = compareStrings(identical, postOfficeBoxNumber, addressBaseVO.getPostOfficeBoxNumber());
			// identical = compareStrings(identical, postalZipCode, addressBaseVO.getPostalZipCode());
			identical = compareStrings(identical, streetDirectionCode, addressBaseVO.getStreetDirectionCode());
			// identical = compareStrings(identical, streetTypeCode, addressBaseVO.getStreetTypeCode());
			identical = compareStrings(identical, unitTypeCode, addressBaseVO.getUnitTypeCode());
			identical = compareStrings(identical, unitNumber, addressBaseVO.getUnitNumber());
			identical = compareStrings(identical, civicNumber, addressBaseVO.getCivicNumber());
			identical = compareStrings(identical, civicNumberSuffix, addressBaseVO.getCivicNumberSuffix());
			identical = compareStrings(identical, ruralRouteNumber, addressBaseVO.getRuralRouteNumber());
			identical = compareStrings(identical, ruralRouteTypeCode, addressBaseVO.getRuralRouteTypeCode());
			
			return identical;
		} else {
			// TODO Auto-generated method stub
			return super.equals(obj);			
		}
	}
	
	protected boolean compareStrings(boolean identical, String s1, String s2){
		try{
			String s1a = "";
			String s2a = "";
			
			if(!StringUtils.isBlank(s1)){
				s1a = s1.trim();
			}
			
			if(!StringUtils.isBlank(s2)){
				s2a = s2.trim();
			}
			
			if(identical){
				if(StringUtils.isBlank(s1a) && StringUtils.isBlank(s2a)){
					return true;
				} else if(!StringUtils.isBlank(s1a) && !StringUtils.isBlank(s2a)){
					return s1a.equals(s2a);	
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}	
		} catch(Throwable t){
			return false;
		}
	}
	
	protected boolean compareLongs(boolean identical, Long l1, Long l2){
		try{
			if(identical){
				if(l1 == null && l2 == null){
					return true;
				} else if(l1 != null && l2 != null){
					return l1.longValue()==l2.longValue();	
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}
		} catch(Throwable t){
			return false;
		}
	}
	
	protected boolean compareStringLists(boolean identical, List<String> s1, List<String> s2){
		try{
			if(identical){
				if((s1 == null || s1.size() == 0) && (s2 == null || s2.size() == 0)){
					return true;
				} else if((s1 != null && s1.size() > 0) && (s2 != null && s2.size() > 0)){
					return new HashSet<String>(s1).equals(new HashSet<String>(s2));
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}
		} catch(Throwable t){
			return false;
		}
	}
	
	protected boolean compareAddressTypeVO(boolean identical, AddressTypeVO a1, AddressTypeVO a2){
		try{
			if(identical){
				if(a1 == null && a2 == null){
					return true;
				} else if(a1 != null && a2 != null){
					return a1.value().equalsIgnoreCase(a2.value());	
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}
		} catch(Throwable t){
			return false;
		}
	}
	
	protected boolean compareProvinceCodeVO(boolean identical, ProvinceCodeVO p1, ProvinceCodeVO p2){
		try{
			if(identical){
				if(p1 == null && p2 == null){
					return true;
				} else if(p1 != null && p2 != null){
					return p1.value().equalsIgnoreCase(p2.value());	
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}
		} catch(Throwable t){
			return false;
		}
	}
	
	protected boolean compareXMLGregorianCalendar(boolean identical, XMLGregorianCalendar c1, XMLGregorianCalendar c2){
		try{
			if(identical){
				if(c1 == null && c2 == null){
					return true;
				} else if(c1 != null && c2 != null){
					return c1.compare(c2) == 0;	
				} else {
					return true;	// treat that as identical if only 1 of them have values
				}
			} else {
				return identical;
			}
		} catch(Throwable t){
			return false;
		}
	}
	
}
