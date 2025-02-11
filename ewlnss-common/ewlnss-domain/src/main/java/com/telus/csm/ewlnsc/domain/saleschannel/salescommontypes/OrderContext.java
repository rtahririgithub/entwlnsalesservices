
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
 * <p>Java class for OrderContext complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *         &lt;element name="orderContactPhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelecomContact" minOccurs="0"/>
 *         &lt;element name="preferredContactMethodTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderContext", propOrder = {
    "orderEmailAddress",
    "orderContactPhoneNumber",
    "preferredContactMethodTxt"
})
public class OrderContext
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected ElectronicContact orderEmailAddress;
    protected TelecomContact orderContactPhoneNumber;
    @XmlElement(required = true)
    protected String preferredContactMethodTxt;

    /**
     * Gets the value of the orderEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ElectronicContact }
     *     
     */
    public ElectronicContact getOrderEmailAddress() {
        return orderEmailAddress;
    }

    /**
     * Sets the value of the orderEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectronicContact }
     *     
     */
    public void setOrderEmailAddress(ElectronicContact value) {
        this.orderEmailAddress = value;
    }

    /**
     * Gets the value of the orderContactPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TelecomContact }
     *     
     */
    public TelecomContact getOrderContactPhoneNumber() {
        return orderContactPhoneNumber;
    }

    /**
     * Sets the value of the orderContactPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomContact }
     *     
     */
    public void setOrderContactPhoneNumber(TelecomContact value) {
        this.orderContactPhoneNumber = value;
    }

    /**
     * Gets the value of the preferredContactMethodTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredContactMethodTxt() {
        return preferredContactMethodTxt;
    }

    /**
     * Sets the value of the preferredContactMethodTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredContactMethodTxt(String value) {
        this.preferredContactMethodTxt = value;
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
            ElectronicContact theOrderEmailAddress;
            theOrderEmailAddress = this.getOrderEmailAddress();
            strategy.appendField(locator, this, "orderEmailAddress", buffer, theOrderEmailAddress);
        }
        {
            TelecomContact theOrderContactPhoneNumber;
            theOrderContactPhoneNumber = this.getOrderContactPhoneNumber();
            strategy.appendField(locator, this, "orderContactPhoneNumber", buffer, theOrderContactPhoneNumber);
        }
        {
            String thePreferredContactMethodTxt;
            thePreferredContactMethodTxt = this.getPreferredContactMethodTxt();
            strategy.appendField(locator, this, "preferredContactMethodTxt", buffer, thePreferredContactMethodTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OrderContext)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OrderContext that = ((OrderContext) object);
        {
            ElectronicContact lhsOrderEmailAddress;
            lhsOrderEmailAddress = this.getOrderEmailAddress();
            ElectronicContact rhsOrderEmailAddress;
            rhsOrderEmailAddress = that.getOrderEmailAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderEmailAddress", lhsOrderEmailAddress), LocatorUtils.property(thatLocator, "orderEmailAddress", rhsOrderEmailAddress), lhsOrderEmailAddress, rhsOrderEmailAddress)) {
                return false;
            }
        }
        {
            TelecomContact lhsOrderContactPhoneNumber;
            lhsOrderContactPhoneNumber = this.getOrderContactPhoneNumber();
            TelecomContact rhsOrderContactPhoneNumber;
            rhsOrderContactPhoneNumber = that.getOrderContactPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderContactPhoneNumber", lhsOrderContactPhoneNumber), LocatorUtils.property(thatLocator, "orderContactPhoneNumber", rhsOrderContactPhoneNumber), lhsOrderContactPhoneNumber, rhsOrderContactPhoneNumber)) {
                return false;
            }
        }
        {
            String lhsPreferredContactMethodTxt;
            lhsPreferredContactMethodTxt = this.getPreferredContactMethodTxt();
            String rhsPreferredContactMethodTxt;
            rhsPreferredContactMethodTxt = that.getPreferredContactMethodTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preferredContactMethodTxt", lhsPreferredContactMethodTxt), LocatorUtils.property(thatLocator, "preferredContactMethodTxt", rhsPreferredContactMethodTxt), lhsPreferredContactMethodTxt, rhsPreferredContactMethodTxt)) {
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
