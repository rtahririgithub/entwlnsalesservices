
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PortRequestAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortRequestAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="municipalityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceStateCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="postalZipCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="countryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetDirectionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortRequestAddress", propOrder = {
    "municipalityName",
    "provinceStateCode",
    "postalZipCode",
    "countryCode",
    "streetNumber",
    "streetDirectionCode",
    "streetName"
})
public class PortRequestAddress
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String municipalityName;
    protected String provinceStateCode;
    protected String postalZipCode;
    protected String countryCode;
    protected String streetNumber;
    protected String streetDirectionCode;
    protected String streetName;

    /**
     * Gets the value of the municipalityName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityName() {
        return municipalityName;
    }

    /**
     * Sets the value of the municipalityName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityName(String value) {
        this.municipalityName = value;
    }

    /**
     * Gets the value of the provinceStateCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceStateCode() {
        return provinceStateCode;
    }

    /**
     * Sets the value of the provinceStateCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceStateCode(String value) {
        this.provinceStateCode = value;
    }

    /**
     * Gets the value of the postalZipCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPostalZipCode() {
        return postalZipCode;
    }

    /**
     * Sets the value of the postalZipCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPostalZipCode(String value) {
        this.postalZipCode = value;
    }

    /**
     * Gets the value of the countryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * Sets the value of the countryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountryCode(String value) {
        this.countryCode = value;
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
     * Gets the value of the streetDirectionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetDirectionCode() {
        return streetDirectionCode;
    }

    /**
     * Sets the value of the streetDirectionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetDirectionCode(String value) {
        this.streetDirectionCode = value;
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

    public String toString() {
        final ToStringStrategy strategy = JAXBToStringStrategy.INSTANCE;
        final StringBuilder buffer = new StringBuilder();
        append(null, buffer, strategy);
        return buffer.toString();
    }

    public StringBuilder append(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        strategy.appendStart(locator, this, buffer);
        appendFields(locator, buffer, strategy);
        strategy.appendEnd(locator, this, buffer);
        return buffer;
    }

    public StringBuilder appendFields(ObjectLocator locator, StringBuilder buffer, ToStringStrategy strategy) {
        {
            String theMunicipalityName;
            theMunicipalityName = this.getMunicipalityName();
            strategy.appendField(locator, this, "municipalityName", buffer, theMunicipalityName);
        }
        {
            String theProvinceStateCode;
            theProvinceStateCode = this.getProvinceStateCode();
            strategy.appendField(locator, this, "provinceStateCode", buffer, theProvinceStateCode);
        }
        {
            String thePostalZipCode;
            thePostalZipCode = this.getPostalZipCode();
            strategy.appendField(locator, this, "postalZipCode", buffer, thePostalZipCode);
        }
        {
            String theCountryCode;
            theCountryCode = this.getCountryCode();
            strategy.appendField(locator, this, "countryCode", buffer, theCountryCode);
        }
        {
            String theStreetNumber;
            theStreetNumber = this.getStreetNumber();
            strategy.appendField(locator, this, "streetNumber", buffer, theStreetNumber);
        }
        {
            String theStreetDirectionCode;
            theStreetDirectionCode = this.getStreetDirectionCode();
            strategy.appendField(locator, this, "streetDirectionCode", buffer, theStreetDirectionCode);
        }
        {
            String theStreetName;
            theStreetName = this.getStreetName();
            strategy.appendField(locator, this, "streetName", buffer, theStreetName);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PortRequestAddress)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PortRequestAddress that = ((PortRequestAddress) object);
        {
            String lhsMunicipalityName;
            lhsMunicipalityName = this.getMunicipalityName();
            String rhsMunicipalityName;
            rhsMunicipalityName = that.getMunicipalityName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "municipalityName", lhsMunicipalityName), LocatorUtils.property(thatLocator, "municipalityName", rhsMunicipalityName), lhsMunicipalityName, rhsMunicipalityName)) {
                return false;
            }
        }
        {
            String lhsProvinceStateCode;
            lhsProvinceStateCode = this.getProvinceStateCode();
            String rhsProvinceStateCode;
            rhsProvinceStateCode = that.getProvinceStateCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceStateCode", lhsProvinceStateCode), LocatorUtils.property(thatLocator, "provinceStateCode", rhsProvinceStateCode), lhsProvinceStateCode, rhsProvinceStateCode)) {
                return false;
            }
        }
        {
            String lhsPostalZipCode;
            lhsPostalZipCode = this.getPostalZipCode();
            String rhsPostalZipCode;
            rhsPostalZipCode = that.getPostalZipCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "postalZipCode", lhsPostalZipCode), LocatorUtils.property(thatLocator, "postalZipCode", rhsPostalZipCode), lhsPostalZipCode, rhsPostalZipCode)) {
                return false;
            }
        }
        {
            String lhsCountryCode;
            lhsCountryCode = this.getCountryCode();
            String rhsCountryCode;
            rhsCountryCode = that.getCountryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "countryCode", lhsCountryCode), LocatorUtils.property(thatLocator, "countryCode", rhsCountryCode), lhsCountryCode, rhsCountryCode)) {
                return false;
            }
        }
        {
            String lhsStreetNumber;
            lhsStreetNumber = this.getStreetNumber();
            String rhsStreetNumber;
            rhsStreetNumber = that.getStreetNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetNumber", lhsStreetNumber), LocatorUtils.property(thatLocator, "streetNumber", rhsStreetNumber), lhsStreetNumber, rhsStreetNumber)) {
                return false;
            }
        }
        {
            String lhsStreetDirectionCode;
            lhsStreetDirectionCode = this.getStreetDirectionCode();
            String rhsStreetDirectionCode;
            rhsStreetDirectionCode = that.getStreetDirectionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetDirectionCode", lhsStreetDirectionCode), LocatorUtils.property(thatLocator, "streetDirectionCode", rhsStreetDirectionCode), lhsStreetDirectionCode, rhsStreetDirectionCode)) {
                return false;
            }
        }
        {
            String lhsStreetName;
            lhsStreetName = this.getStreetName();
            String rhsStreetName;
            rhsStreetName = that.getStreetName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetName", lhsStreetName), LocatorUtils.property(thatLocator, "streetName", rhsStreetName), lhsStreetName, rhsStreetName)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
