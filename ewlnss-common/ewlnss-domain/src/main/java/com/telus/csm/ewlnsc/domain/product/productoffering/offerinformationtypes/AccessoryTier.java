
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for AccessoryTier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryTier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minItemQty" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="maxItemQty" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType"/>
 *         &lt;element name="discountAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryTier", propOrder = {
    "minItemQty",
    "maxItemQty",
    "discountType",
    "discountAmt"
})
public class AccessoryTier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected BigInteger minItemQty;
    @XmlElement(required = true)
    protected BigInteger maxItemQty;
    @XmlElement(required = true)
    protected DiscountType discountType;
    protected double discountAmt;

    /**
     * Gets the value of the minItemQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinItemQty() {
        return minItemQty;
    }

    /**
     * Sets the value of the minItemQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinItemQty(BigInteger value) {
        this.minItemQty = value;
    }

    /**
     * Gets the value of the maxItemQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxItemQty() {
        return maxItemQty;
    }

    /**
     * Sets the value of the maxItemQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxItemQty(BigInteger value) {
        this.maxItemQty = value;
    }

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
     */
    public double getDiscountAmt() {
        return discountAmt;
    }

    /**
     * Sets the value of the discountAmt property.
     * 
     */
    public void setDiscountAmt(double value) {
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
        {
            BigInteger theMinItemQty;
            theMinItemQty = this.getMinItemQty();
            strategy.appendField(locator, this, "minItemQty", buffer, theMinItemQty);
        }
        {
            BigInteger theMaxItemQty;
            theMaxItemQty = this.getMaxItemQty();
            strategy.appendField(locator, this, "maxItemQty", buffer, theMaxItemQty);
        }
        {
            DiscountType theDiscountType;
            theDiscountType = this.getDiscountType();
            strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
        }
        {
            double theDiscountAmt;
            theDiscountAmt = (true?this.getDiscountAmt(): 0.0D);
            strategy.appendField(locator, this, "discountAmt", buffer, theDiscountAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryTier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryTier that = ((AccessoryTier) object);
        {
            BigInteger lhsMinItemQty;
            lhsMinItemQty = this.getMinItemQty();
            BigInteger rhsMinItemQty;
            rhsMinItemQty = that.getMinItemQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minItemQty", lhsMinItemQty), LocatorUtils.property(thatLocator, "minItemQty", rhsMinItemQty), lhsMinItemQty, rhsMinItemQty)) {
                return false;
            }
        }
        {
            BigInteger lhsMaxItemQty;
            lhsMaxItemQty = this.getMaxItemQty();
            BigInteger rhsMaxItemQty;
            rhsMaxItemQty = that.getMaxItemQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxItemQty", lhsMaxItemQty), LocatorUtils.property(thatLocator, "maxItemQty", rhsMaxItemQty), lhsMaxItemQty, rhsMaxItemQty)) {
                return false;
            }
        }
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
            double lhsDiscountAmt;
            lhsDiscountAmt = (true?this.getDiscountAmt(): 0.0D);
            double rhsDiscountAmt;
            rhsDiscountAmt = (true?that.getDiscountAmt(): 0.0D);
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
