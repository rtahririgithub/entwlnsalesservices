
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for DiscountDeliveryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountDeliveryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountDeliveryTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountDeliveryType", propOrder = {
    "discountDeliveryTypeCode"
})
public class DiscountDeliveryType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String discountDeliveryTypeCode;

    /**
     * Gets the value of the discountDeliveryTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountDeliveryTypeCode() {
        return discountDeliveryTypeCode;
    }

    /**
     * Sets the value of the discountDeliveryTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountDeliveryTypeCode(String value) {
        this.discountDeliveryTypeCode = value;
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
            String theDiscountDeliveryTypeCode;
            theDiscountDeliveryTypeCode = this.getDiscountDeliveryTypeCode();
            strategy.appendField(locator, this, "discountDeliveryTypeCode", buffer, theDiscountDeliveryTypeCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DiscountDeliveryType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DiscountDeliveryType that = ((DiscountDeliveryType) object);
        {
            String lhsDiscountDeliveryTypeCode;
            lhsDiscountDeliveryTypeCode = this.getDiscountDeliveryTypeCode();
            String rhsDiscountDeliveryTypeCode;
            rhsDiscountDeliveryTypeCode = that.getDiscountDeliveryTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountDeliveryTypeCode", lhsDiscountDeliveryTypeCode), LocatorUtils.property(thatLocator, "discountDeliveryTypeCode", rhsDiscountDeliveryTypeCode), lhsDiscountDeliveryTypeCode, rhsDiscountDeliveryTypeCode)) {
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
