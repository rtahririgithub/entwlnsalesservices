
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
 * <p>Java class for AccessoryOfferDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOfferDiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerHeader" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OfferHeader"/>
 *         &lt;element name="discountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOfferDiscount", propOrder = {
    "offerHeader",
    "discountTypeCd",
    "discountAmount"
})
public class AccessoryOfferDiscount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected OfferHeader offerHeader;
    protected String discountTypeCd;
    protected Double discountAmount;

    /**
     * Gets the value of the offerHeader property.
     * 
     * @return
     *     possible object is
     *     {@link OfferHeader }
     *     
     */
    public OfferHeader getOfferHeader() {
        return offerHeader;
    }

    /**
     * Sets the value of the offerHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferHeader }
     *     
     */
    public void setOfferHeader(OfferHeader value) {
        this.offerHeader = value;
    }

    /**
     * Gets the value of the discountTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountTypeCd() {
        return discountTypeCd;
    }

    /**
     * Sets the value of the discountTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountTypeCd(String value) {
        this.discountTypeCd = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscountAmount(Double value) {
        this.discountAmount = value;
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
            OfferHeader theOfferHeader;
            theOfferHeader = this.getOfferHeader();
            strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
        }
        {
            String theDiscountTypeCd;
            theDiscountTypeCd = this.getDiscountTypeCd();
            strategy.appendField(locator, this, "discountTypeCd", buffer, theDiscountTypeCd);
        }
        {
            Double theDiscountAmount;
            theDiscountAmount = this.getDiscountAmount();
            strategy.appendField(locator, this, "discountAmount", buffer, theDiscountAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOfferDiscount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOfferDiscount that = ((AccessoryOfferDiscount) object);
        {
            OfferHeader lhsOfferHeader;
            lhsOfferHeader = this.getOfferHeader();
            OfferHeader rhsOfferHeader;
            rhsOfferHeader = that.getOfferHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
                return false;
            }
        }
        {
            String lhsDiscountTypeCd;
            lhsDiscountTypeCd = this.getDiscountTypeCd();
            String rhsDiscountTypeCd;
            rhsDiscountTypeCd = that.getDiscountTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountTypeCd", lhsDiscountTypeCd), LocatorUtils.property(thatLocator, "discountTypeCd", rhsDiscountTypeCd), lhsDiscountTypeCd, rhsDiscountTypeCd)) {
                return false;
            }
        }
        {
            Double lhsDiscountAmount;
            lhsDiscountAmount = this.getDiscountAmount();
            Double rhsDiscountAmount;
            rhsDiscountAmount = that.getDiscountAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountAmount", lhsDiscountAmount), LocatorUtils.property(thatLocator, "discountAmount", rhsDiscountAmount), lhsDiscountAmount, rhsDiscountAmount)) {
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
