
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for EquipmentBasePrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipmentBasePrice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="basePriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipmentBasePrice", propOrder = {
    "basePriceAmt"
})
public class EquipmentBasePrice
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Double basePriceAmt;

    /**
     * Gets the value of the basePriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBasePriceAmt() {
        return basePriceAmt;
    }

    /**
     * Sets the value of the basePriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBasePriceAmt(Double value) {
        this.basePriceAmt = value;
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
            Double theBasePriceAmt;
            theBasePriceAmt = this.getBasePriceAmt();
            strategy.appendField(locator, this, "basePriceAmt", buffer, theBasePriceAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EquipmentBasePrice)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EquipmentBasePrice that = ((EquipmentBasePrice) object);
        {
            Double lhsBasePriceAmt;
            lhsBasePriceAmt = this.getBasePriceAmt();
            Double rhsBasePriceAmt;
            rhsBasePriceAmt = that.getBasePriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "basePriceAmt", lhsBasePriceAmt), LocatorUtils.property(thatLocator, "basePriceAmt", rhsBasePriceAmt), lhsBasePriceAmt, rhsBasePriceAmt)) {
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
