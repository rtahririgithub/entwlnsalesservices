
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
 * <p>Java class for SalesOrderItemReceiptDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOrderItemReceiptDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessoryOrderItem" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccessoryItemReceiptDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOrderItemReceiptDetail", propOrder = {
    "accessoryOrderItem"
})
public class SalesOrderItemReceiptDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected AccessoryItemReceiptDetail accessoryOrderItem;

    /**
     * Gets the value of the accessoryOrderItem property.
     * 
     * @return
     *     possible object is
     *     {@link AccessoryItemReceiptDetail }
     *     
     */
    public AccessoryItemReceiptDetail getAccessoryOrderItem() {
        return accessoryOrderItem;
    }

    /**
     * Sets the value of the accessoryOrderItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessoryItemReceiptDetail }
     *     
     */
    public void setAccessoryOrderItem(AccessoryItemReceiptDetail value) {
        this.accessoryOrderItem = value;
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
            AccessoryItemReceiptDetail theAccessoryOrderItem;
            theAccessoryOrderItem = this.getAccessoryOrderItem();
            strategy.appendField(locator, this, "accessoryOrderItem", buffer, theAccessoryOrderItem);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesOrderItemReceiptDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesOrderItemReceiptDetail that = ((SalesOrderItemReceiptDetail) object);
        {
            AccessoryItemReceiptDetail lhsAccessoryOrderItem;
            lhsAccessoryOrderItem = this.getAccessoryOrderItem();
            AccessoryItemReceiptDetail rhsAccessoryOrderItem;
            rhsAccessoryOrderItem = that.getAccessoryOrderItem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accessoryOrderItem", lhsAccessoryOrderItem), LocatorUtils.property(thatLocator, "accessoryOrderItem", rhsAccessoryOrderItem), lhsAccessoryOrderItem, rhsAccessoryOrderItem)) {
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
