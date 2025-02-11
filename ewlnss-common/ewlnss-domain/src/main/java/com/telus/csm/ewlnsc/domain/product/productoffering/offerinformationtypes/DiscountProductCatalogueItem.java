
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for DiscountProductCatalogueItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DiscountProductCatalogueItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueItem">
 *       &lt;sequence>
 *         &lt;element name="discountPriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="recurringCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DiscountProductCatalogueItem", propOrder = {
    "discountPriceAmt",
    "recurringCount",
    "discountType"
})
public class DiscountProductCatalogueItem
    extends ProductCatalogueItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Double discountPriceAmt;
    protected BigInteger recurringCount;
    protected DiscountType discountType;

    /**
     * Gets the value of the discountPriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscountPriceAmt() {
        return discountPriceAmt;
    }

    /**
     * Sets the value of the discountPriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscountPriceAmt(Double value) {
        this.discountPriceAmt = value;
    }

    /**
     * Gets the value of the recurringCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRecurringCount() {
        return recurringCount;
    }

    /**
     * Sets the value of the recurringCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRecurringCount(BigInteger value) {
        this.recurringCount = value;
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
            Double theDiscountPriceAmt;
            theDiscountPriceAmt = this.getDiscountPriceAmt();
            strategy.appendField(locator, this, "discountPriceAmt", buffer, theDiscountPriceAmt);
        }
        {
            BigInteger theRecurringCount;
            theRecurringCount = this.getRecurringCount();
            strategy.appendField(locator, this, "recurringCount", buffer, theRecurringCount);
        }
        {
            DiscountType theDiscountType;
            theDiscountType = this.getDiscountType();
            strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DiscountProductCatalogueItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final DiscountProductCatalogueItem that = ((DiscountProductCatalogueItem) object);
        {
            Double lhsDiscountPriceAmt;
            lhsDiscountPriceAmt = this.getDiscountPriceAmt();
            Double rhsDiscountPriceAmt;
            rhsDiscountPriceAmt = that.getDiscountPriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountPriceAmt", lhsDiscountPriceAmt), LocatorUtils.property(thatLocator, "discountPriceAmt", rhsDiscountPriceAmt), lhsDiscountPriceAmt, rhsDiscountPriceAmt)) {
                return false;
            }
        }
        {
            BigInteger lhsRecurringCount;
            lhsRecurringCount = this.getRecurringCount();
            BigInteger rhsRecurringCount;
            rhsRecurringCount = that.getRecurringCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recurringCount", lhsRecurringCount), LocatorUtils.property(thatLocator, "recurringCount", rhsRecurringCount), lhsRecurringCount, rhsRecurringCount)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
