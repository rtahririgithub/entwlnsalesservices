
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class AddressVO
    extends AddressBaseVO
implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected String streetVector;
    protected String legalLandDescription;
    protected String addressTypeInfo;
    protected String coid;
    protected String rateCenter;
    protected String switchNumber;
    protected String switchType;
    protected String serviceAddressId;
    protected String CLLICode;
    
    //+++
    protected String npa;
    //QC61508
    protected String ratingNpaNxx;

    protected Boolean pikTvAddress = Boolean.FALSE;
	
    public String getRatingNpaNxx() {
		return ratingNpaNxx;
	}

	public void setRatingNpaNxx(String ratingNpaNxx) {
		this.ratingNpaNxx = ratingNpaNxx;
	}

	public String getNpa() {
		return npa;
	}

	public void setNpa(String npa) {
		this.npa = npa;
	}

	public String getCLLICode() {
		return CLLICode;
	}

	public void setCLLICode(String cLLICode) {
		CLLICode = cLLICode;
	}

	/**
     * Gets the value of the streetVector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetVector() {
        return streetVector;
    }

    /**
     * Sets the value of the streetVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetVector(String value) {
        this.streetVector = value;
    }

    /**
     * Gets the value of the legalLandDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalLandDescription() {
        return legalLandDescription;
    }

    /**
     * Sets the value of the legalLandDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalLandDescription(String value) {
        this.legalLandDescription = value;
    }

    /**
     * Gets the value of the addressTypeInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressTypeInfo() {
        return addressTypeInfo;
    }

    /**
     * Sets the value of the addressTypeInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressTypeInfo(String value) {
        this.addressTypeInfo = value;
    }

    /**
     * Gets the value of the coid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoid() {
        return coid;
    }

    /**
     * Sets the value of the coid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoid(String value) {
        this.coid = value;
    }

    /**
     * Gets the value of the rateCenter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCenter() {
        return rateCenter;
    }

    /**
     * Sets the value of the rateCenter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCenter(String value) {
        this.rateCenter = value;
    }

    /**
     * Gets the value of the switchNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwitchNumber() {
        return switchNumber;
    }

    /**
     * Sets the value of the switchNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwitchNumber(String value) {
        this.switchNumber = value;
    }

    /**
     * Gets the value of the switchType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwitchType() {
        return switchType;
    }

    /**
     * Sets the value of the switchType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwitchType(String value) {
        this.switchType = value;
    }

    /**
     * Gets the value of the serviceAddressId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceAddressId() {
        return serviceAddressId;
    }

    /**
     * Sets the value of the serviceAddressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceAddressId(String value) {
        this.serviceAddressId = value;
    }
    
	public Boolean isPikTvAddress() {
		return pikTvAddress;
	}

	public void setPikTvAddress(Boolean pikTvAddress) {
		this.pikTvAddress = pikTvAddress;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof AddressVO){
			AddressVO addressVO = (AddressVO) obj;
			boolean identical = true;
			
			identical = super.equals(((AddressBaseVO) addressVO));
			
			identical = compareStrings(identical, streetVector, addressVO.getStreetVector());
			// identical = compareStrings(identical, legalLandDescription, addressVO.getLegalLandDescription());
			// identical = compareStrings(identical, addressTypeInfo, addressVO.getAddressTypeInfo());
			// identical = compareStrings(identical, coid, addressVO.getCoid());
			// identical = compareStrings(identical, rateCenter, addressVO.getRateCenter());
			// identical = compareStrings(identical, switchNumber, addressVO.getSwitchNumber());
			// identical = compareStrings(identical, switchType, addressVO.getSwitchType());
			// identical = compareStrings(identical, serviceAddressId, addressVO.getServiceAddressId());
			
			return identical;
		} else {
			// TODO Auto-generated method stub
			return super.equals(obj);			
		}
	}
}
