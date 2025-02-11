
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

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
 * <p>Java class for DevicePaymentType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DevicePaymentType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hardwarePricing" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HardwarePricingDetailType" minOccurs="0"/>
 *         &lt;element name="hardwarePriceTaxAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="applyHardwarePriceOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="newSimPriceAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="simPriceTaxAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="applyNewSimPriceOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="receiptEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DevicePaymentType", propOrder = {
    "hardwarePricing",
    "hardwarePriceTaxAmt",
    "applyHardwarePriceOnBillInd",
    "newSimPriceAmount",
    "simPriceTaxAmt",
    "applyNewSimPriceOnBillInd",
    "receiptEmailAddress"
})
public class DevicePaymentType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected HardwarePricingDetailType hardwarePricing;
    protected Double hardwarePriceTaxAmt;
    protected Boolean applyHardwarePriceOnBillInd;
    protected Double newSimPriceAmount;
    protected Double simPriceTaxAmt;
    protected Boolean applyNewSimPriceOnBillInd;
    protected ElectronicContact receiptEmailAddress;

    /**
     * Gets the value of the hardwarePricing property.
     * 
     * @return
     *     possible object is
     *     {@link HardwarePricingDetailType }
     *     
     */
    public HardwarePricingDetailType getHardwarePricing() {
        return hardwarePricing;
    }

    /**
     * Sets the value of the hardwarePricing property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardwarePricingDetailType }
     *     
     */
    public void setHardwarePricing(HardwarePricingDetailType value) {
        this.hardwarePricing = value;
    }

    /**
     * Gets the value of the hardwarePriceTaxAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getHardwarePriceTaxAmt() {
        return hardwarePriceTaxAmt;
    }

    /**
     * Sets the value of the hardwarePriceTaxAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setHardwarePriceTaxAmt(Double value) {
        this.hardwarePriceTaxAmt = value;
    }

    /**
     * Gets the value of the applyHardwarePriceOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyHardwarePriceOnBillInd() {
        return applyHardwarePriceOnBillInd;
    }

    /**
     * Sets the value of the applyHardwarePriceOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyHardwarePriceOnBillInd(Boolean value) {
        this.applyHardwarePriceOnBillInd = value;
    }

    /**
     * Gets the value of the newSimPriceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getNewSimPriceAmount() {
        return newSimPriceAmount;
    }

    /**
     * Sets the value of the newSimPriceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setNewSimPriceAmount(Double value) {
        this.newSimPriceAmount = value;
    }

    /**
     * Gets the value of the simPriceTaxAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSimPriceTaxAmt() {
        return simPriceTaxAmt;
    }

    /**
     * Sets the value of the simPriceTaxAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSimPriceTaxAmt(Double value) {
        this.simPriceTaxAmt = value;
    }

    /**
     * Gets the value of the applyNewSimPriceOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyNewSimPriceOnBillInd() {
        return applyNewSimPriceOnBillInd;
    }

    /**
     * Sets the value of the applyNewSimPriceOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyNewSimPriceOnBillInd(Boolean value) {
        this.applyNewSimPriceOnBillInd = value;
    }

    /**
     * Gets the value of the receiptEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ElectronicContact }
     *     
     */
    public ElectronicContact getReceiptEmailAddress() {
        return receiptEmailAddress;
    }

    /**
     * Sets the value of the receiptEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectronicContact }
     *     
     */
    public void setReceiptEmailAddress(ElectronicContact value) {
        this.receiptEmailAddress = value;
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
            HardwarePricingDetailType theHardwarePricing;
            theHardwarePricing = this.getHardwarePricing();
            strategy.appendField(locator, this, "hardwarePricing", buffer, theHardwarePricing);
        }
        {
            Double theHardwarePriceTaxAmt;
            theHardwarePriceTaxAmt = this.getHardwarePriceTaxAmt();
            strategy.appendField(locator, this, "hardwarePriceTaxAmt", buffer, theHardwarePriceTaxAmt);
        }
        {
            Boolean theApplyHardwarePriceOnBillInd;
            theApplyHardwarePriceOnBillInd = this.isApplyHardwarePriceOnBillInd();
            strategy.appendField(locator, this, "applyHardwarePriceOnBillInd", buffer, theApplyHardwarePriceOnBillInd);
        }
        {
            Double theNewSimPriceAmount;
            theNewSimPriceAmount = this.getNewSimPriceAmount();
            strategy.appendField(locator, this, "newSimPriceAmount", buffer, theNewSimPriceAmount);
        }
        {
            Double theSimPriceTaxAmt;
            theSimPriceTaxAmt = this.getSimPriceTaxAmt();
            strategy.appendField(locator, this, "simPriceTaxAmt", buffer, theSimPriceTaxAmt);
        }
        {
            Boolean theApplyNewSimPriceOnBillInd;
            theApplyNewSimPriceOnBillInd = this.isApplyNewSimPriceOnBillInd();
            strategy.appendField(locator, this, "applyNewSimPriceOnBillInd", buffer, theApplyNewSimPriceOnBillInd);
        }
        {
            ElectronicContact theReceiptEmailAddress;
            theReceiptEmailAddress = this.getReceiptEmailAddress();
            strategy.appendField(locator, this, "receiptEmailAddress", buffer, theReceiptEmailAddress);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DevicePaymentType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DevicePaymentType that = ((DevicePaymentType) object);
        {
            HardwarePricingDetailType lhsHardwarePricing;
            lhsHardwarePricing = this.getHardwarePricing();
            HardwarePricingDetailType rhsHardwarePricing;
            rhsHardwarePricing = that.getHardwarePricing();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwarePricing", lhsHardwarePricing), LocatorUtils.property(thatLocator, "hardwarePricing", rhsHardwarePricing), lhsHardwarePricing, rhsHardwarePricing)) {
                return false;
            }
        }
        {
            Double lhsHardwarePriceTaxAmt;
            lhsHardwarePriceTaxAmt = this.getHardwarePriceTaxAmt();
            Double rhsHardwarePriceTaxAmt;
            rhsHardwarePriceTaxAmt = that.getHardwarePriceTaxAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwarePriceTaxAmt", lhsHardwarePriceTaxAmt), LocatorUtils.property(thatLocator, "hardwarePriceTaxAmt", rhsHardwarePriceTaxAmt), lhsHardwarePriceTaxAmt, rhsHardwarePriceTaxAmt)) {
                return false;
            }
        }
        {
            Boolean lhsApplyHardwarePriceOnBillInd;
            lhsApplyHardwarePriceOnBillInd = this.isApplyHardwarePriceOnBillInd();
            Boolean rhsApplyHardwarePriceOnBillInd;
            rhsApplyHardwarePriceOnBillInd = that.isApplyHardwarePriceOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyHardwarePriceOnBillInd", lhsApplyHardwarePriceOnBillInd), LocatorUtils.property(thatLocator, "applyHardwarePriceOnBillInd", rhsApplyHardwarePriceOnBillInd), lhsApplyHardwarePriceOnBillInd, rhsApplyHardwarePriceOnBillInd)) {
                return false;
            }
        }
        {
            Double lhsNewSimPriceAmount;
            lhsNewSimPriceAmount = this.getNewSimPriceAmount();
            Double rhsNewSimPriceAmount;
            rhsNewSimPriceAmount = that.getNewSimPriceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newSimPriceAmount", lhsNewSimPriceAmount), LocatorUtils.property(thatLocator, "newSimPriceAmount", rhsNewSimPriceAmount), lhsNewSimPriceAmount, rhsNewSimPriceAmount)) {
                return false;
            }
        }
        {
            Double lhsSimPriceTaxAmt;
            lhsSimPriceTaxAmt = this.getSimPriceTaxAmt();
            Double rhsSimPriceTaxAmt;
            rhsSimPriceTaxAmt = that.getSimPriceTaxAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simPriceTaxAmt", lhsSimPriceTaxAmt), LocatorUtils.property(thatLocator, "simPriceTaxAmt", rhsSimPriceTaxAmt), lhsSimPriceTaxAmt, rhsSimPriceTaxAmt)) {
                return false;
            }
        }
        {
            Boolean lhsApplyNewSimPriceOnBillInd;
            lhsApplyNewSimPriceOnBillInd = this.isApplyNewSimPriceOnBillInd();
            Boolean rhsApplyNewSimPriceOnBillInd;
            rhsApplyNewSimPriceOnBillInd = that.isApplyNewSimPriceOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyNewSimPriceOnBillInd", lhsApplyNewSimPriceOnBillInd), LocatorUtils.property(thatLocator, "applyNewSimPriceOnBillInd", rhsApplyNewSimPriceOnBillInd), lhsApplyNewSimPriceOnBillInd, rhsApplyNewSimPriceOnBillInd)) {
                return false;
            }
        }
        {
            ElectronicContact lhsReceiptEmailAddress;
            lhsReceiptEmailAddress = this.getReceiptEmailAddress();
            ElectronicContact rhsReceiptEmailAddress;
            rhsReceiptEmailAddress = that.getReceiptEmailAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "receiptEmailAddress", lhsReceiptEmailAddress), LocatorUtils.property(thatLocator, "receiptEmailAddress", rhsReceiptEmailAddress), lhsReceiptEmailAddress, rhsReceiptEmailAddress)) {
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
