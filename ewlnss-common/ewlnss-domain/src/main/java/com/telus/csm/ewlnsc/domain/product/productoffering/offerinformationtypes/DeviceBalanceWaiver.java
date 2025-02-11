
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
 * <p>Java class for DeviceBalanceWaiver complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DeviceBalanceWaiver">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waiveAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="waiveType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType"/>
 *         &lt;element name="maximumDaysRemainingCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DeviceBalanceWaiver", propOrder = {
    "waiveAmt",
    "waiveType",
    "maximumDaysRemainingCount"
})
public class DeviceBalanceWaiver
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double waiveAmt;
    @XmlElement(required = true)
    protected DiscountType waiveType;
    protected BigInteger maximumDaysRemainingCount;

    /**
     * Gets the value of the waiveAmt property.
     * 
     */
    public double getWaiveAmt() {
        return waiveAmt;
    }

    /**
     * Sets the value of the waiveAmt property.
     * 
     */
    public void setWaiveAmt(double value) {
        this.waiveAmt = value;
    }

    /**
     * Gets the value of the waiveType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getWaiveType() {
        return waiveType;
    }

    /**
     * Sets the value of the waiveType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setWaiveType(DiscountType value) {
        this.waiveType = value;
    }

    /**
     * Gets the value of the maximumDaysRemainingCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaximumDaysRemainingCount() {
        return maximumDaysRemainingCount;
    }

    /**
     * Sets the value of the maximumDaysRemainingCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaximumDaysRemainingCount(BigInteger value) {
        this.maximumDaysRemainingCount = value;
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
            double theWaiveAmt;
            theWaiveAmt = (true?this.getWaiveAmt(): 0.0D);
            strategy.appendField(locator, this, "waiveAmt", buffer, theWaiveAmt);
        }
        {
            DiscountType theWaiveType;
            theWaiveType = this.getWaiveType();
            strategy.appendField(locator, this, "waiveType", buffer, theWaiveType);
        }
        {
            BigInteger theMaximumDaysRemainingCount;
            theMaximumDaysRemainingCount = this.getMaximumDaysRemainingCount();
            strategy.appendField(locator, this, "maximumDaysRemainingCount", buffer, theMaximumDaysRemainingCount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DeviceBalanceWaiver)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DeviceBalanceWaiver that = ((DeviceBalanceWaiver) object);
        {
            double lhsWaiveAmt;
            lhsWaiveAmt = (true?this.getWaiveAmt(): 0.0D);
            double rhsWaiveAmt;
            rhsWaiveAmt = (true?that.getWaiveAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "waiveAmt", lhsWaiveAmt), LocatorUtils.property(thatLocator, "waiveAmt", rhsWaiveAmt), lhsWaiveAmt, rhsWaiveAmt)) {
                return false;
            }
        }
        {
            DiscountType lhsWaiveType;
            lhsWaiveType = this.getWaiveType();
            DiscountType rhsWaiveType;
            rhsWaiveType = that.getWaiveType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "waiveType", lhsWaiveType), LocatorUtils.property(thatLocator, "waiveType", rhsWaiveType), lhsWaiveType, rhsWaiveType)) {
                return false;
            }
        }
        {
            BigInteger lhsMaximumDaysRemainingCount;
            lhsMaximumDaysRemainingCount = this.getMaximumDaysRemainingCount();
            BigInteger rhsMaximumDaysRemainingCount;
            rhsMaximumDaysRemainingCount = that.getMaximumDaysRemainingCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximumDaysRemainingCount", lhsMaximumDaysRemainingCount), LocatorUtils.property(thatLocator, "maximumDaysRemainingCount", rhsMaximumDaysRemainingCount), lhsMaximumDaysRemainingCount, rhsMaximumDaysRemainingCount)) {
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
