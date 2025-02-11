
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
 * Wireless Equipment in a business account context.
 * 
 * <p>Java class for BusinessWirelessEquipment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessWirelessEquipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="purchasePrice" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PurchasePrice" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessWirelessEquipment", propOrder = {
    "purchasePrice"
})
public class BusinessWirelessEquipment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected PurchasePrice purchasePrice;

    /**
     * Gets the value of the purchasePrice property.
     * 
     * @return
     *     possible object is
     *     {@link PurchasePrice }
     *     
     */
    public PurchasePrice getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Sets the value of the purchasePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link PurchasePrice }
     *     
     */
    public void setPurchasePrice(PurchasePrice value) {
        this.purchasePrice = value;
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
            PurchasePrice thePurchasePrice;
            thePurchasePrice = this.getPurchasePrice();
            strategy.appendField(locator, this, "purchasePrice", buffer, thePurchasePrice);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BusinessWirelessEquipment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BusinessWirelessEquipment that = ((BusinessWirelessEquipment) object);
        {
            PurchasePrice lhsPurchasePrice;
            lhsPurchasePrice = this.getPurchasePrice();
            PurchasePrice rhsPurchasePrice;
            rhsPurchasePrice = that.getPurchasePrice();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchasePrice", lhsPurchasePrice), LocatorUtils.property(thatLocator, "purchasePrice", rhsPurchasePrice), lhsPurchasePrice, rhsPurchasePrice)) {
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
