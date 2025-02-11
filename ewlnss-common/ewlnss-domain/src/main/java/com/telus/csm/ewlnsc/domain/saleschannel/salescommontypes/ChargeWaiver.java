
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.DiscountType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for ChargeWaiver complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ChargeWaiver">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="waiveAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="waiveType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}DiscountType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ChargeWaiver", propOrder = {
    "waiveAmt",
    "waiveType"
})
public class ChargeWaiver
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double waiveAmt;
    @XmlElement(required = true)
    protected DiscountType waiveType;

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
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ChargeWaiver)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ChargeWaiver that = ((ChargeWaiver) object);
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
