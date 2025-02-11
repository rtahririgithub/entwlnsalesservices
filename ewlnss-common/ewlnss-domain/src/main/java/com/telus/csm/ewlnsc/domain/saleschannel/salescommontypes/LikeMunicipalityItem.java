
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for LikeMunicipalityItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LikeMunicipalityItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="municipalityName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinceStateCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LikeMunicipalityItem", propOrder = {
    "municipalityName",
    "provinceStateCd"
})
public class LikeMunicipalityItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String municipalityName;
    @XmlElement(required = true)
    protected String provinceStateCd;

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
     * Gets the value of the provinceStateCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceStateCd() {
        return provinceStateCd;
    }

    /**
     * Sets the value of the provinceStateCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceStateCd(String value) {
        this.provinceStateCd = value;
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
            String theProvinceStateCd;
            theProvinceStateCd = this.getProvinceStateCd();
            strategy.appendField(locator, this, "provinceStateCd", buffer, theProvinceStateCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof LikeMunicipalityItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final LikeMunicipalityItem that = ((LikeMunicipalityItem) object);
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
            String lhsProvinceStateCd;
            lhsProvinceStateCd = this.getProvinceStateCd();
            String rhsProvinceStateCd;
            rhsProvinceStateCd = that.getProvinceStateCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceStateCd", lhsProvinceStateCd), LocatorUtils.property(thatLocator, "provinceStateCd", rhsProvinceStateCd), lhsProvinceStateCd, rhsProvinceStateCd)) {
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
