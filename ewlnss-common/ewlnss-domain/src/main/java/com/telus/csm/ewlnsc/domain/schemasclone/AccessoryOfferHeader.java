
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for AccessoryOfferHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOfferHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerHeader" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferHeader"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOfferHeader", propOrder = {
    "offerHeader"
})
public class AccessoryOfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected WirelineOfferHeader offerHeader;

    /**
     * Gets the value of the offerHeader property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineOfferHeader }
     *     
     */
    public WirelineOfferHeader getOfferHeader() {
        return offerHeader;
    }

    /**
     * Sets the value of the offerHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineOfferHeader }
     *     
     */
    public void setOfferHeader(WirelineOfferHeader value) {
        this.offerHeader = value;
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
            WirelineOfferHeader theOfferHeader;
            theOfferHeader = this.getOfferHeader();
            strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOfferHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOfferHeader that = ((AccessoryOfferHeader) object);
        {
            WirelineOfferHeader lhsOfferHeader;
            lhsOfferHeader = this.getOfferHeader();
            WirelineOfferHeader rhsOfferHeader;
            rhsOfferHeader = that.getOfferHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
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
