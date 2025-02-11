
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
 * <p>Java class for BundleOrderContactDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleOrderContactDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contactName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" minOccurs="0"/>
 *         &lt;element name="contactPhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelecomContact" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleOrderContactDetailType", propOrder = {
    "contactName",
    "contactPhoneNumber"
})
public class BundleOrderContactDetailType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Name contactName;
    protected TelecomContact contactPhoneNumber;

    /**
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setContactName(Name value) {
        this.contactName = value;
    }

    /**
     * Gets the value of the contactPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TelecomContact }
     *     
     */
    public TelecomContact getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    /**
     * Sets the value of the contactPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomContact }
     *     
     */
    public void setContactPhoneNumber(TelecomContact value) {
        this.contactPhoneNumber = value;
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
            Name theContactName;
            theContactName = this.getContactName();
            strategy.appendField(locator, this, "contactName", buffer, theContactName);
        }
        {
            TelecomContact theContactPhoneNumber;
            theContactPhoneNumber = this.getContactPhoneNumber();
            strategy.appendField(locator, this, "contactPhoneNumber", buffer, theContactPhoneNumber);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BundleOrderContactDetailType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BundleOrderContactDetailType that = ((BundleOrderContactDetailType) object);
        {
            Name lhsContactName;
            lhsContactName = this.getContactName();
            Name rhsContactName;
            rhsContactName = that.getContactName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contactName", lhsContactName), LocatorUtils.property(thatLocator, "contactName", rhsContactName), lhsContactName, rhsContactName)) {
                return false;
            }
        }
        {
            TelecomContact lhsContactPhoneNumber;
            lhsContactPhoneNumber = this.getContactPhoneNumber();
            TelecomContact rhsContactPhoneNumber;
            rhsContactPhoneNumber = that.getContactPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contactPhoneNumber", lhsContactPhoneNumber), LocatorUtils.property(thatLocator, "contactPhoneNumber", rhsContactPhoneNumber), lhsContactPhoneNumber, rhsContactPhoneNumber)) {
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
