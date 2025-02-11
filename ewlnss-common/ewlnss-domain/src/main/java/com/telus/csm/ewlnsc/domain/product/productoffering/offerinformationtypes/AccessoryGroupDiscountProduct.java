
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
 * <p>Java class for AccessoryGroupDiscountProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryGroupDiscountProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryGroupProduct">
 *       &lt;sequence>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType" minOccurs="0"/>
 *         &lt;element name="discountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryGroupDiscountProduct", propOrder = {
    "discountType",
    "discountAmt"
})
public class AccessoryGroupDiscountProduct
    extends AccessoryGroupProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected DiscountType discountType;
    protected Double discountAmt;

    /**
     * Gets the value of the discountType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Sets the value of the discountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setDiscountType(DiscountType value) {
        this.discountType = value;
    }

    /**
     * Gets the value of the discountAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscountAmt() {
        return discountAmt;
    }

    /**
     * Sets the value of the discountAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscountAmt(Double value) {
        this.discountAmt = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            DiscountType theDiscountType;
            theDiscountType = this.getDiscountType();
            strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
        }
        {
            Double theDiscountAmt;
            theDiscountAmt = this.getDiscountAmt();
            strategy.appendField(locator, this, "discountAmt", buffer, theDiscountAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryGroupDiscountProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AccessoryGroupDiscountProduct that = ((AccessoryGroupDiscountProduct) object);
        {
            DiscountType lhsDiscountType;
            lhsDiscountType = this.getDiscountType();
            DiscountType rhsDiscountType;
            rhsDiscountType = that.getDiscountType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountType", lhsDiscountType), LocatorUtils.property(thatLocator, "discountType", rhsDiscountType), lhsDiscountType, rhsDiscountType)) {
                return false;
            }
        }
        {
            Double lhsDiscountAmt;
            lhsDiscountAmt = this.getDiscountAmt();
            Double rhsDiscountAmt;
            rhsDiscountAmt = that.getDiscountAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountAmt", lhsDiscountAmt), LocatorUtils.property(thatLocator, "discountAmt", rhsDiscountAmt), lhsDiscountAmt, rhsDiscountAmt)) {
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
