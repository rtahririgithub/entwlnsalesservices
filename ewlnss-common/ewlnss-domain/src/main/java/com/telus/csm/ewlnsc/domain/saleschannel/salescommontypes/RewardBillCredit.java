
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
 * <p>Java class for RewardBillCredit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RewardBillCredit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountDeliveryTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjustmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="adjustmentReversalCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditInstallmentAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="numberOfInstallment" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RewardBillCredit", propOrder = {
    "discountTypeCode",
    "discountDeliveryTypeCode",
    "creditReasonCode",
    "adjustmentCode",
    "adjustmentReversalCode",
    "creditInstallmentAmt",
    "numberOfInstallment"
})
public class RewardBillCredit
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String discountTypeCode;
    protected String discountDeliveryTypeCode;
    protected String creditReasonCode;
    protected String adjustmentCode;
    protected String adjustmentReversalCode;
    protected Double creditInstallmentAmt;
    protected Integer numberOfInstallment;

    /**
     * Gets the value of the discountTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountTypeCode() {
        return discountTypeCode;
    }

    /**
     * Sets the value of the discountTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountTypeCode(String value) {
        this.discountTypeCode = value;
    }

    /**
     * Gets the value of the discountDeliveryTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountDeliveryTypeCode() {
        return discountDeliveryTypeCode;
    }

    /**
     * Sets the value of the discountDeliveryTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountDeliveryTypeCode(String value) {
        this.discountDeliveryTypeCode = value;
    }

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
     * Gets the value of the adjustmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustmentCode() {
        return adjustmentCode;
    }

    /**
     * Sets the value of the adjustmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustmentCode(String value) {
        this.adjustmentCode = value;
    }

    /**
     * Gets the value of the adjustmentReversalCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdjustmentReversalCode() {
        return adjustmentReversalCode;
    }

    /**
     * Sets the value of the adjustmentReversalCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdjustmentReversalCode(String value) {
        this.adjustmentReversalCode = value;
    }

    /**
     * Gets the value of the creditInstallmentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCreditInstallmentAmt() {
        return creditInstallmentAmt;
    }

    /**
     * Sets the value of the creditInstallmentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCreditInstallmentAmt(Double value) {
        this.creditInstallmentAmt = value;
    }

    /**
     * Gets the value of the numberOfInstallment property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getNumberOfInstallment() {
        return numberOfInstallment;
    }

    /**
     * Sets the value of the numberOfInstallment property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setNumberOfInstallment(Integer value) {
        this.numberOfInstallment = value;
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
            String theDiscountTypeCode;
            theDiscountTypeCode = this.getDiscountTypeCode();
            strategy.appendField(locator, this, "discountTypeCode", buffer, theDiscountTypeCode);
        }
        {
            String theDiscountDeliveryTypeCode;
            theDiscountDeliveryTypeCode = this.getDiscountDeliveryTypeCode();
            strategy.appendField(locator, this, "discountDeliveryTypeCode", buffer, theDiscountDeliveryTypeCode);
        }
        {
            String theCreditReasonCode;
            theCreditReasonCode = this.getCreditReasonCode();
            strategy.appendField(locator, this, "creditReasonCode", buffer, theCreditReasonCode);
        }
        {
            String theAdjustmentCode;
            theAdjustmentCode = this.getAdjustmentCode();
            strategy.appendField(locator, this, "adjustmentCode", buffer, theAdjustmentCode);
        }
        {
            String theAdjustmentReversalCode;
            theAdjustmentReversalCode = this.getAdjustmentReversalCode();
            strategy.appendField(locator, this, "adjustmentReversalCode", buffer, theAdjustmentReversalCode);
        }
        {
            Double theCreditInstallmentAmt;
            theCreditInstallmentAmt = this.getCreditInstallmentAmt();
            strategy.appendField(locator, this, "creditInstallmentAmt", buffer, theCreditInstallmentAmt);
        }
        {
            Integer theNumberOfInstallment;
            theNumberOfInstallment = this.getNumberOfInstallment();
            strategy.appendField(locator, this, "numberOfInstallment", buffer, theNumberOfInstallment);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RewardBillCredit)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RewardBillCredit that = ((RewardBillCredit) object);
        {
            String lhsDiscountTypeCode;
            lhsDiscountTypeCode = this.getDiscountTypeCode();
            String rhsDiscountTypeCode;
            rhsDiscountTypeCode = that.getDiscountTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountTypeCode", lhsDiscountTypeCode), LocatorUtils.property(thatLocator, "discountTypeCode", rhsDiscountTypeCode), lhsDiscountTypeCode, rhsDiscountTypeCode)) {
                return false;
            }
        }
        {
            String lhsDiscountDeliveryTypeCode;
            lhsDiscountDeliveryTypeCode = this.getDiscountDeliveryTypeCode();
            String rhsDiscountDeliveryTypeCode;
            rhsDiscountDeliveryTypeCode = that.getDiscountDeliveryTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountDeliveryTypeCode", lhsDiscountDeliveryTypeCode), LocatorUtils.property(thatLocator, "discountDeliveryTypeCode", rhsDiscountDeliveryTypeCode), lhsDiscountDeliveryTypeCode, rhsDiscountDeliveryTypeCode)) {
                return false;
            }
        }
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
            String lhsAdjustmentCode;
            lhsAdjustmentCode = this.getAdjustmentCode();
            String rhsAdjustmentCode;
            rhsAdjustmentCode = that.getAdjustmentCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "adjustmentCode", lhsAdjustmentCode), LocatorUtils.property(thatLocator, "adjustmentCode", rhsAdjustmentCode), lhsAdjustmentCode, rhsAdjustmentCode)) {
                return false;
            }
        }
        {
            String lhsAdjustmentReversalCode;
            lhsAdjustmentReversalCode = this.getAdjustmentReversalCode();
            String rhsAdjustmentReversalCode;
            rhsAdjustmentReversalCode = that.getAdjustmentReversalCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "adjustmentReversalCode", lhsAdjustmentReversalCode), LocatorUtils.property(thatLocator, "adjustmentReversalCode", rhsAdjustmentReversalCode), lhsAdjustmentReversalCode, rhsAdjustmentReversalCode)) {
                return false;
            }
        }
        {
            Double lhsCreditInstallmentAmt;
            lhsCreditInstallmentAmt = this.getCreditInstallmentAmt();
            Double rhsCreditInstallmentAmt;
            rhsCreditInstallmentAmt = that.getCreditInstallmentAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditInstallmentAmt", lhsCreditInstallmentAmt), LocatorUtils.property(thatLocator, "creditInstallmentAmt", rhsCreditInstallmentAmt), lhsCreditInstallmentAmt, rhsCreditInstallmentAmt)) {
                return false;
            }
        }
        {
            Integer lhsNumberOfInstallment;
            lhsNumberOfInstallment = this.getNumberOfInstallment();
            Integer rhsNumberOfInstallment;
            rhsNumberOfInstallment = that.getNumberOfInstallment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "numberOfInstallment", lhsNumberOfInstallment), LocatorUtils.property(thatLocator, "numberOfInstallment", rhsNumberOfInstallment), lhsNumberOfInstallment, rhsNumberOfInstallment)) {
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
