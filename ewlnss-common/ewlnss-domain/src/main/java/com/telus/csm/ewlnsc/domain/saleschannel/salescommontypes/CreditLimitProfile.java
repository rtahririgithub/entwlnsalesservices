
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

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
 * <p>Java class for CreditLimitProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditLimitProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditLimitAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="creditLimitThresholdAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="creditLimitMaxContractTerm" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="creditLimitMaxPricePlanAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditLimitProfile", propOrder = {
    "creditLimitAmount",
    "creditLimitThresholdAmount",
    "creditLimitMaxContractTerm",
    "creditLimitMaxPricePlanAmount"
})
public class CreditLimitProfile
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Double creditLimitAmount;
    protected Double creditLimitThresholdAmount;
    protected BigInteger creditLimitMaxContractTerm;
    protected Double creditLimitMaxPricePlanAmount;

    /**
     * Gets the value of the creditLimitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCreditLimitAmount() {
        return creditLimitAmount;
    }

    /**
     * Sets the value of the creditLimitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCreditLimitAmount(Double value) {
        this.creditLimitAmount = value;
    }

    /**
     * Gets the value of the creditLimitThresholdAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCreditLimitThresholdAmount() {
        return creditLimitThresholdAmount;
    }

    /**
     * Sets the value of the creditLimitThresholdAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCreditLimitThresholdAmount(Double value) {
        this.creditLimitThresholdAmount = value;
    }

    /**
     * Gets the value of the creditLimitMaxContractTerm property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCreditLimitMaxContractTerm() {
        return creditLimitMaxContractTerm;
    }

    /**
     * Sets the value of the creditLimitMaxContractTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCreditLimitMaxContractTerm(BigInteger value) {
        this.creditLimitMaxContractTerm = value;
    }

    /**
     * Gets the value of the creditLimitMaxPricePlanAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCreditLimitMaxPricePlanAmount() {
        return creditLimitMaxPricePlanAmount;
    }

    /**
     * Sets the value of the creditLimitMaxPricePlanAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCreditLimitMaxPricePlanAmount(Double value) {
        this.creditLimitMaxPricePlanAmount = value;
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
            Double theCreditLimitAmount;
            theCreditLimitAmount = this.getCreditLimitAmount();
            strategy.appendField(locator, this, "creditLimitAmount", buffer, theCreditLimitAmount);
        }
        {
            Double theCreditLimitThresholdAmount;
            theCreditLimitThresholdAmount = this.getCreditLimitThresholdAmount();
            strategy.appendField(locator, this, "creditLimitThresholdAmount", buffer, theCreditLimitThresholdAmount);
        }
        {
            BigInteger theCreditLimitMaxContractTerm;
            theCreditLimitMaxContractTerm = this.getCreditLimitMaxContractTerm();
            strategy.appendField(locator, this, "creditLimitMaxContractTerm", buffer, theCreditLimitMaxContractTerm);
        }
        {
            Double theCreditLimitMaxPricePlanAmount;
            theCreditLimitMaxPricePlanAmount = this.getCreditLimitMaxPricePlanAmount();
            strategy.appendField(locator, this, "creditLimitMaxPricePlanAmount", buffer, theCreditLimitMaxPricePlanAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreditLimitProfile)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CreditLimitProfile that = ((CreditLimitProfile) object);
        {
            Double lhsCreditLimitAmount;
            lhsCreditLimitAmount = this.getCreditLimitAmount();
            Double rhsCreditLimitAmount;
            rhsCreditLimitAmount = that.getCreditLimitAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitAmount", lhsCreditLimitAmount), LocatorUtils.property(thatLocator, "creditLimitAmount", rhsCreditLimitAmount), lhsCreditLimitAmount, rhsCreditLimitAmount)) {
                return false;
            }
        }
        {
            Double lhsCreditLimitThresholdAmount;
            lhsCreditLimitThresholdAmount = this.getCreditLimitThresholdAmount();
            Double rhsCreditLimitThresholdAmount;
            rhsCreditLimitThresholdAmount = that.getCreditLimitThresholdAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitThresholdAmount", lhsCreditLimitThresholdAmount), LocatorUtils.property(thatLocator, "creditLimitThresholdAmount", rhsCreditLimitThresholdAmount), lhsCreditLimitThresholdAmount, rhsCreditLimitThresholdAmount)) {
                return false;
            }
        }
        {
            BigInteger lhsCreditLimitMaxContractTerm;
            lhsCreditLimitMaxContractTerm = this.getCreditLimitMaxContractTerm();
            BigInteger rhsCreditLimitMaxContractTerm;
            rhsCreditLimitMaxContractTerm = that.getCreditLimitMaxContractTerm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitMaxContractTerm", lhsCreditLimitMaxContractTerm), LocatorUtils.property(thatLocator, "creditLimitMaxContractTerm", rhsCreditLimitMaxContractTerm), lhsCreditLimitMaxContractTerm, rhsCreditLimitMaxContractTerm)) {
                return false;
            }
        }
        {
            Double lhsCreditLimitMaxPricePlanAmount;
            lhsCreditLimitMaxPricePlanAmount = this.getCreditLimitMaxPricePlanAmount();
            Double rhsCreditLimitMaxPricePlanAmount;
            rhsCreditLimitMaxPricePlanAmount = that.getCreditLimitMaxPricePlanAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitMaxPricePlanAmount", lhsCreditLimitMaxPricePlanAmount), LocatorUtils.property(thatLocator, "creditLimitMaxPricePlanAmount", rhsCreditLimitMaxPricePlanAmount), lhsCreditLimitMaxPricePlanAmount, rhsCreditLimitMaxPricePlanAmount)) {
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
