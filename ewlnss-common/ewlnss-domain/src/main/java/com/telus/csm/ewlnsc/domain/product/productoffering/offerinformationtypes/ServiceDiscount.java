
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
 * <p>Java class for ServiceDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceDiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountedServiceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="useDefaultMonthsInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="monthCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDiscount", propOrder = {
    "discountedServiceCode",
    "useDefaultMonthsInd",
    "monthCount"
})
public class ServiceDiscount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String discountedServiceCode;
    protected boolean useDefaultMonthsInd;
    protected BigInteger monthCount;

    /**
     * Gets the value of the discountedServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountedServiceCode() {
        return discountedServiceCode;
    }

    /**
     * Sets the value of the discountedServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountedServiceCode(String value) {
        this.discountedServiceCode = value;
    }

    /**
     * Gets the value of the useDefaultMonthsInd property.
     * 
     */
    public boolean isUseDefaultMonthsInd() {
        return useDefaultMonthsInd;
    }

    /**
     * Sets the value of the useDefaultMonthsInd property.
     * 
     */
    public void setUseDefaultMonthsInd(boolean value) {
        this.useDefaultMonthsInd = value;
    }

    /**
     * Gets the value of the monthCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMonthCount() {
        return monthCount;
    }

    /**
     * Sets the value of the monthCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMonthCount(BigInteger value) {
        this.monthCount = value;
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
            String theDiscountedServiceCode;
            theDiscountedServiceCode = this.getDiscountedServiceCode();
            strategy.appendField(locator, this, "discountedServiceCode", buffer, theDiscountedServiceCode);
        }
        {
            boolean theUseDefaultMonthsInd;
            theUseDefaultMonthsInd = (true?this.isUseDefaultMonthsInd():false);
            strategy.appendField(locator, this, "useDefaultMonthsInd", buffer, theUseDefaultMonthsInd);
        }
        {
            BigInteger theMonthCount;
            theMonthCount = this.getMonthCount();
            strategy.appendField(locator, this, "monthCount", buffer, theMonthCount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceDiscount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceDiscount that = ((ServiceDiscount) object);
        {
            String lhsDiscountedServiceCode;
            lhsDiscountedServiceCode = this.getDiscountedServiceCode();
            String rhsDiscountedServiceCode;
            rhsDiscountedServiceCode = that.getDiscountedServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountedServiceCode", lhsDiscountedServiceCode), LocatorUtils.property(thatLocator, "discountedServiceCode", rhsDiscountedServiceCode), lhsDiscountedServiceCode, rhsDiscountedServiceCode)) {
                return false;
            }
        }
        {
            boolean lhsUseDefaultMonthsInd;
            lhsUseDefaultMonthsInd = (true?this.isUseDefaultMonthsInd():false);
            boolean rhsUseDefaultMonthsInd;
            rhsUseDefaultMonthsInd = (true?that.isUseDefaultMonthsInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "useDefaultMonthsInd", lhsUseDefaultMonthsInd), LocatorUtils.property(thatLocator, "useDefaultMonthsInd", rhsUseDefaultMonthsInd), lhsUseDefaultMonthsInd, rhsUseDefaultMonthsInd)) {
                return false;
            }
        }
        {
            BigInteger lhsMonthCount;
            lhsMonthCount = this.getMonthCount();
            BigInteger rhsMonthCount;
            rhsMonthCount = that.getMonthCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "monthCount", lhsMonthCount), LocatorUtils.property(thatLocator, "monthCount", rhsMonthCount), lhsMonthCount, rhsMonthCount)) {
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
