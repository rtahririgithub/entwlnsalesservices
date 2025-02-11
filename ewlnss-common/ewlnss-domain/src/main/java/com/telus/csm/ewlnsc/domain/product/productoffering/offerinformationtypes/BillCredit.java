
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
 * <p>Java class for BillCredit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillCredit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditReasonCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditReverseReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="recurringCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="immediateBalanceImpactInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="deviceBalanceRelatedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="activationPortinInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillCredit", propOrder = {
    "creditReasonCode",
    "creditReverseReasonCode",
    "creditAmt",
    "recurringCount",
    "immediateBalanceImpactInd",
    "deviceBalanceRelatedInd",
    "activationPortinInd"
})
public class BillCredit
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String creditReasonCode;
    protected String creditReverseReasonCode;
    protected Double creditAmt;
    protected BigInteger recurringCount;
    protected boolean immediateBalanceImpactInd;
    protected boolean deviceBalanceRelatedInd;
    protected boolean activationPortinInd;

    /**
     * Gets the value of the creditReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditReasonCode() {
        return creditReasonCode;
    }

    /**
     * Sets the value of the creditReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditReasonCode(String value) {
        this.creditReasonCode = value;
    }

    /**
     * Gets the value of the creditReverseReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditReverseReasonCode() {
        return creditReverseReasonCode;
    }

    /**
     * Sets the value of the creditReverseReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditReverseReasonCode(String value) {
        this.creditReverseReasonCode = value;
    }

    /**
     * Gets the value of the creditAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCreditAmt() {
        return creditAmt;
    }

    /**
     * Sets the value of the creditAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCreditAmt(Double value) {
        this.creditAmt = value;
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
     * Gets the value of the immediateBalanceImpactInd property.
     * 
     */
    public boolean isImmediateBalanceImpactInd() {
        return immediateBalanceImpactInd;
    }

    /**
     * Sets the value of the immediateBalanceImpactInd property.
     * 
     */
    public void setImmediateBalanceImpactInd(boolean value) {
        this.immediateBalanceImpactInd = value;
    }

    /**
     * Gets the value of the deviceBalanceRelatedInd property.
     * 
     */
    public boolean isDeviceBalanceRelatedInd() {
        return deviceBalanceRelatedInd;
    }

    /**
     * Sets the value of the deviceBalanceRelatedInd property.
     * 
     */
    public void setDeviceBalanceRelatedInd(boolean value) {
        this.deviceBalanceRelatedInd = value;
    }

    /**
     * Gets the value of the activationPortinInd property.
     * 
     */
    public boolean isActivationPortinInd() {
        return activationPortinInd;
    }

    /**
     * Sets the value of the activationPortinInd property.
     * 
     */
    public void setActivationPortinInd(boolean value) {
        this.activationPortinInd = value;
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
            String theCreditReasonCode;
            theCreditReasonCode = this.getCreditReasonCode();
            strategy.appendField(locator, this, "creditReasonCode", buffer, theCreditReasonCode);
        }
        {
            String theCreditReverseReasonCode;
            theCreditReverseReasonCode = this.getCreditReverseReasonCode();
            strategy.appendField(locator, this, "creditReverseReasonCode", buffer, theCreditReverseReasonCode);
        }
        {
            Double theCreditAmt;
            theCreditAmt = this.getCreditAmt();
            strategy.appendField(locator, this, "creditAmt", buffer, theCreditAmt);
        }
        {
            BigInteger theRecurringCount;
            theRecurringCount = this.getRecurringCount();
            strategy.appendField(locator, this, "recurringCount", buffer, theRecurringCount);
        }
        {
            boolean theImmediateBalanceImpactInd;
            theImmediateBalanceImpactInd = (true?this.isImmediateBalanceImpactInd():false);
            strategy.appendField(locator, this, "immediateBalanceImpactInd", buffer, theImmediateBalanceImpactInd);
        }
        {
            boolean theDeviceBalanceRelatedInd;
            theDeviceBalanceRelatedInd = (true?this.isDeviceBalanceRelatedInd():false);
            strategy.appendField(locator, this, "deviceBalanceRelatedInd", buffer, theDeviceBalanceRelatedInd);
        }
        {
            boolean theActivationPortinInd;
            theActivationPortinInd = (true?this.isActivationPortinInd():false);
            strategy.appendField(locator, this, "activationPortinInd", buffer, theActivationPortinInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BillCredit)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BillCredit that = ((BillCredit) object);
        {
            String lhsCreditReasonCode;
            lhsCreditReasonCode = this.getCreditReasonCode();
            String rhsCreditReasonCode;
            rhsCreditReasonCode = that.getCreditReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditReasonCode", lhsCreditReasonCode), LocatorUtils.property(thatLocator, "creditReasonCode", rhsCreditReasonCode), lhsCreditReasonCode, rhsCreditReasonCode)) {
                return false;
            }
        }
        {
            String lhsCreditReverseReasonCode;
            lhsCreditReverseReasonCode = this.getCreditReverseReasonCode();
            String rhsCreditReverseReasonCode;
            rhsCreditReverseReasonCode = that.getCreditReverseReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditReverseReasonCode", lhsCreditReverseReasonCode), LocatorUtils.property(thatLocator, "creditReverseReasonCode", rhsCreditReverseReasonCode), lhsCreditReverseReasonCode, rhsCreditReverseReasonCode)) {
                return false;
            }
        }
        {
            Double lhsCreditAmt;
            lhsCreditAmt = this.getCreditAmt();
            Double rhsCreditAmt;
            rhsCreditAmt = that.getCreditAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditAmt", lhsCreditAmt), LocatorUtils.property(thatLocator, "creditAmt", rhsCreditAmt), lhsCreditAmt, rhsCreditAmt)) {
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
            boolean lhsImmediateBalanceImpactInd;
            lhsImmediateBalanceImpactInd = (true?this.isImmediateBalanceImpactInd():false);
            boolean rhsImmediateBalanceImpactInd;
            rhsImmediateBalanceImpactInd = (true?that.isImmediateBalanceImpactInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "immediateBalanceImpactInd", lhsImmediateBalanceImpactInd), LocatorUtils.property(thatLocator, "immediateBalanceImpactInd", rhsImmediateBalanceImpactInd), lhsImmediateBalanceImpactInd, rhsImmediateBalanceImpactInd)) {
                return false;
            }
        }
        {
            boolean lhsDeviceBalanceRelatedInd;
            lhsDeviceBalanceRelatedInd = (true?this.isDeviceBalanceRelatedInd():false);
            boolean rhsDeviceBalanceRelatedInd;
            rhsDeviceBalanceRelatedInd = (true?that.isDeviceBalanceRelatedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deviceBalanceRelatedInd", lhsDeviceBalanceRelatedInd), LocatorUtils.property(thatLocator, "deviceBalanceRelatedInd", rhsDeviceBalanceRelatedInd), lhsDeviceBalanceRelatedInd, rhsDeviceBalanceRelatedInd)) {
                return false;
            }
        }
        {
            boolean lhsActivationPortinInd;
            lhsActivationPortinInd = (true?this.isActivationPortinInd():false);
            boolean rhsActivationPortinInd;
            rhsActivationPortinInd = (true?that.isActivationPortinInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "activationPortinInd", lhsActivationPortinInd), LocatorUtils.property(thatLocator, "activationPortinInd", rhsActivationPortinInd), lhsActivationPortinInd, rhsActivationPortinInd)) {
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
