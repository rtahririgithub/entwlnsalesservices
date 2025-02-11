package com.telus.csm.ewlnsms.rest.webservices.vo;

import java.io.Serializable;

import javax.xml.datatype.XMLGregorianCalendar;

import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class DriverLicenseVO implements Serializable
{
	private static final long serialVersionUID = 1L;

    protected String driverLicenseNum;
    protected String provinceCd;
    protected XMLGregorianCalendar expiryDate;

    /**
     * Gets the value of the driverLicenseNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDriverLicenseNum() {
        return driverLicenseNum;
    }

    /**
     * Sets the value of the driverLicenseNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDriverLicenseNum(String value) {
        this.driverLicenseNum = value;
    }

    /**
     * Gets the value of the provinceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCd() {
        return provinceCd;
    }

    /**
     * Sets the value of the provinceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCd(String value) {
        this.provinceCd = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setExpiryDate(XMLGregorianCalendar value) {
        this.expiryDate = value;
    }

}
