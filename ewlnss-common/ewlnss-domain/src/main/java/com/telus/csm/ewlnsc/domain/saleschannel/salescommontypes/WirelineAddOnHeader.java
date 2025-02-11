
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
 * <p>Java class for WirelineAddOnHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineAddOnHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="addOn" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineAddOnHeader", propOrder = {
    "addOn"
})
public class WirelineAddOnHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductComponentIdentifier addOn;

    /**
     * Gets the value of the addOn property.
     * 
     * @return
     *     possible object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public ProductComponentIdentifier getAddOn() {
        return addOn;
    }

    /**
     * Sets the value of the addOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setAddOn(ProductComponentIdentifier value) {
        this.addOn = value;
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
            ProductComponentIdentifier theAddOn;
            theAddOn = this.getAddOn();
            strategy.appendField(locator, this, "addOn", buffer, theAddOn);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineAddOnHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineAddOnHeader that = ((WirelineAddOnHeader) object);
        {
            ProductComponentIdentifier lhsAddOn;
            lhsAddOn = this.getAddOn();
            ProductComponentIdentifier rhsAddOn;
            rhsAddOn = that.getAddOn();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "addOn", lhsAddOn), LocatorUtils.property(thatLocator, "addOn", rhsAddOn), lhsAddOn, rhsAddOn)) {
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
