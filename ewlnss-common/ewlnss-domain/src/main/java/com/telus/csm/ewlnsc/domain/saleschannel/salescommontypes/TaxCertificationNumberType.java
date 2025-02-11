
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
 * Tax certification number for cop. account
 * 
 * <p>Java class for TaxCertificationNumberType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxCertificationNumberType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gstCertificateNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pstCertificateNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="hstCertificateNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxCertificationNumberType", propOrder = {
    "gstCertificateNumber",
    "pstCertificateNumber",
    "hstCertificateNumber"
})
public class TaxCertificationNumberType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String gstCertificateNumber;
    @XmlElement(required = true)
    protected String pstCertificateNumber;
    @XmlElement(required = true)
    protected String hstCertificateNumber;

    /**
     * Gets the value of the gstCertificateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGstCertificateNumber() {
        return gstCertificateNumber;
    }

    /**
     * Sets the value of the gstCertificateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGstCertificateNumber(String value) {
        this.gstCertificateNumber = value;
    }

    /**
     * Gets the value of the pstCertificateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPstCertificateNumber() {
        return pstCertificateNumber;
    }

    /**
     * Sets the value of the pstCertificateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPstCertificateNumber(String value) {
        this.pstCertificateNumber = value;
    }

    /**
     * Gets the value of the hstCertificateNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHstCertificateNumber() {
        return hstCertificateNumber;
    }

    /**
     * Sets the value of the hstCertificateNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHstCertificateNumber(String value) {
        this.hstCertificateNumber = value;
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
            String theGstCertificateNumber;
            theGstCertificateNumber = this.getGstCertificateNumber();
            strategy.appendField(locator, this, "gstCertificateNumber", buffer, theGstCertificateNumber);
        }
        {
            String thePstCertificateNumber;
            thePstCertificateNumber = this.getPstCertificateNumber();
            strategy.appendField(locator, this, "pstCertificateNumber", buffer, thePstCertificateNumber);
        }
        {
            String theHstCertificateNumber;
            theHstCertificateNumber = this.getHstCertificateNumber();
            strategy.appendField(locator, this, "hstCertificateNumber", buffer, theHstCertificateNumber);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TaxCertificationNumberType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TaxCertificationNumberType that = ((TaxCertificationNumberType) object);
        {
            String lhsGstCertificateNumber;
            lhsGstCertificateNumber = this.getGstCertificateNumber();
            String rhsGstCertificateNumber;
            rhsGstCertificateNumber = that.getGstCertificateNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gstCertificateNumber", lhsGstCertificateNumber), LocatorUtils.property(thatLocator, "gstCertificateNumber", rhsGstCertificateNumber), lhsGstCertificateNumber, rhsGstCertificateNumber)) {
                return false;
            }
        }
        {
            String lhsPstCertificateNumber;
            lhsPstCertificateNumber = this.getPstCertificateNumber();
            String rhsPstCertificateNumber;
            rhsPstCertificateNumber = that.getPstCertificateNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pstCertificateNumber", lhsPstCertificateNumber), LocatorUtils.property(thatLocator, "pstCertificateNumber", rhsPstCertificateNumber), lhsPstCertificateNumber, rhsPstCertificateNumber)) {
                return false;
            }
        }
        {
            String lhsHstCertificateNumber;
            lhsHstCertificateNumber = this.getHstCertificateNumber();
            String rhsHstCertificateNumber;
            rhsHstCertificateNumber = that.getHstCertificateNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hstCertificateNumber", lhsHstCertificateNumber), LocatorUtils.property(thatLocator, "hstCertificateNumber", rhsHstCertificateNumber), lhsHstCertificateNumber, rhsHstCertificateNumber)) {
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
