
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
 * VoIP Phone Number containing system generated WLS phone number (not visible to the client), VoIP local phone number, or VoIP toll-free phone number
 * 
 * <p>Java class for PhoneNumberType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhoneNumberType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="telephoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="telephoneNumberTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhoneNumberType", propOrder = {
    "telephoneNumber",
    "telephoneNumberTypeCode"
})
public class PhoneNumberType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String telephoneNumber;
    protected String telephoneNumberTypeCode;

    /**
     * Gets the value of the telephoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets the value of the telephoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNumber(String value) {
        this.telephoneNumber = value;
    }

    /**
     * Gets the value of the telephoneNumberTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelephoneNumberTypeCode() {
        return telephoneNumberTypeCode;
    }

    /**
     * Sets the value of the telephoneNumberTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelephoneNumberTypeCode(String value) {
        this.telephoneNumberTypeCode = value;
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
            String theTelephoneNumber;
            theTelephoneNumber = this.getTelephoneNumber();
            strategy.appendField(locator, this, "telephoneNumber", buffer, theTelephoneNumber);
        }
        {
            String theTelephoneNumberTypeCode;
            theTelephoneNumberTypeCode = this.getTelephoneNumberTypeCode();
            strategy.appendField(locator, this, "telephoneNumberTypeCode", buffer, theTelephoneNumberTypeCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PhoneNumberType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PhoneNumberType that = ((PhoneNumberType) object);
        {
            String lhsTelephoneNumber;
            lhsTelephoneNumber = this.getTelephoneNumber();
            String rhsTelephoneNumber;
            rhsTelephoneNumber = that.getTelephoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "telephoneNumber", lhsTelephoneNumber), LocatorUtils.property(thatLocator, "telephoneNumber", rhsTelephoneNumber), lhsTelephoneNumber, rhsTelephoneNumber)) {
                return false;
            }
        }
        {
            String lhsTelephoneNumberTypeCode;
            lhsTelephoneNumberTypeCode = this.getTelephoneNumberTypeCode();
            String rhsTelephoneNumberTypeCode;
            rhsTelephoneNumberTypeCode = that.getTelephoneNumberTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "telephoneNumberTypeCode", lhsTelephoneNumberTypeCode), LocatorUtils.property(thatLocator, "telephoneNumberTypeCode", rhsTelephoneNumberTypeCode), lhsTelephoneNumberTypeCode, rhsTelephoneNumberTypeCode)) {
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
