
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
 * <p>Java class for DeliveryOptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeliveryOptionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="warehouseShipmentInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeliveryOptionType", propOrder = {
    "warehouseShipmentInd"
})
public class DeliveryOptionType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected boolean warehouseShipmentInd;

    /**
     * Gets the value of the warehouseShipmentInd property.
     * 
     */
    public boolean isWarehouseShipmentInd() {
        return warehouseShipmentInd;
    }

    /**
     * Sets the value of the warehouseShipmentInd property.
     * 
     */
    public void setWarehouseShipmentInd(boolean value) {
        this.warehouseShipmentInd = value;
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
            boolean theWarehouseShipmentInd;
            theWarehouseShipmentInd = (true?this.isWarehouseShipmentInd():false);
            strategy.appendField(locator, this, "warehouseShipmentInd", buffer, theWarehouseShipmentInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DeliveryOptionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DeliveryOptionType that = ((DeliveryOptionType) object);
        {
            boolean lhsWarehouseShipmentInd;
            lhsWarehouseShipmentInd = (true?this.isWarehouseShipmentInd():false);
            boolean rhsWarehouseShipmentInd;
            rhsWarehouseShipmentInd = (true?that.isWarehouseShipmentInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warehouseShipmentInd", lhsWarehouseShipmentInd), LocatorUtils.property(thatLocator, "warehouseShipmentInd", rhsWarehouseShipmentInd), lhsWarehouseShipmentInd, rhsWarehouseShipmentInd)) {
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
