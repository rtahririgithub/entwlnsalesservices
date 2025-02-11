
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
 * <p>Java class for PaymentInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentReasonCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentMethodTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PriceAmountAndTax"/>
 *         &lt;element name="paymentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentResponseCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditCard" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditCard" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentInformation", propOrder = {
    "paymentReasonCode",
    "paymentMethodTypeCode",
    "paymentAmount",
    "paymentId",
    "paymentResponseCode",
    "creditCard"
})
public class PaymentInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String paymentReasonCode;
    @XmlElement(required = true)
    protected String paymentMethodTypeCode;
    @XmlElement(required = true)
    protected PriceAmountAndTax paymentAmount;
    protected String paymentId;
    protected String paymentResponseCode;
    protected CreditCard creditCard;

    /**
     * Gets the value of the paymentReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentReasonCode() {
        return paymentReasonCode;
    }

    /**
     * Sets the value of the paymentReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentReasonCode(String value) {
        this.paymentReasonCode = value;
    }

    /**
     * Gets the value of the paymentMethodTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentMethodTypeCode() {
        return paymentMethodTypeCode;
    }

    /**
     * Sets the value of the paymentMethodTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentMethodTypeCode(String value) {
        this.paymentMethodTypeCode = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public PriceAmountAndTax getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public void setPaymentAmount(PriceAmountAndTax value) {
        this.paymentAmount = value;
    }

    /**
     * Gets the value of the paymentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentId() {
        return paymentId;
    }

    /**
     * Sets the value of the paymentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentId(String value) {
        this.paymentId = value;
    }

    /**
     * Gets the value of the paymentResponseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentResponseCode() {
        return paymentResponseCode;
    }

    /**
     * Sets the value of the paymentResponseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentResponseCode(String value) {
        this.paymentResponseCode = value;
    }

    /**
     * Gets the value of the creditCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the value of the creditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCreditCard(CreditCard value) {
        this.creditCard = value;
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
            String thePaymentReasonCode;
            thePaymentReasonCode = this.getPaymentReasonCode();
            strategy.appendField(locator, this, "paymentReasonCode", buffer, thePaymentReasonCode);
        }
        {
            String thePaymentMethodTypeCode;
            thePaymentMethodTypeCode = this.getPaymentMethodTypeCode();
            strategy.appendField(locator, this, "paymentMethodTypeCode", buffer, thePaymentMethodTypeCode);
        }
        {
            PriceAmountAndTax thePaymentAmount;
            thePaymentAmount = this.getPaymentAmount();
            strategy.appendField(locator, this, "paymentAmount", buffer, thePaymentAmount);
        }
        {
            String thePaymentId;
            thePaymentId = this.getPaymentId();
            strategy.appendField(locator, this, "paymentId", buffer, thePaymentId);
        }
        {
            String thePaymentResponseCode;
            thePaymentResponseCode = this.getPaymentResponseCode();
            strategy.appendField(locator, this, "paymentResponseCode", buffer, thePaymentResponseCode);
        }
        {
            CreditCard theCreditCard;
            theCreditCard = this.getCreditCard();
            strategy.appendField(locator, this, "creditCard", buffer, theCreditCard);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PaymentInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PaymentInformation that = ((PaymentInformation) object);
        {
            String lhsPaymentReasonCode;
            lhsPaymentReasonCode = this.getPaymentReasonCode();
            String rhsPaymentReasonCode;
            rhsPaymentReasonCode = that.getPaymentReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentReasonCode", lhsPaymentReasonCode), LocatorUtils.property(thatLocator, "paymentReasonCode", rhsPaymentReasonCode), lhsPaymentReasonCode, rhsPaymentReasonCode)) {
                return false;
            }
        }
        {
            String lhsPaymentMethodTypeCode;
            lhsPaymentMethodTypeCode = this.getPaymentMethodTypeCode();
            String rhsPaymentMethodTypeCode;
            rhsPaymentMethodTypeCode = that.getPaymentMethodTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentMethodTypeCode", lhsPaymentMethodTypeCode), LocatorUtils.property(thatLocator, "paymentMethodTypeCode", rhsPaymentMethodTypeCode), lhsPaymentMethodTypeCode, rhsPaymentMethodTypeCode)) {
                return false;
            }
        }
        {
            PriceAmountAndTax lhsPaymentAmount;
            lhsPaymentAmount = this.getPaymentAmount();
            PriceAmountAndTax rhsPaymentAmount;
            rhsPaymentAmount = that.getPaymentAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentAmount", lhsPaymentAmount), LocatorUtils.property(thatLocator, "paymentAmount", rhsPaymentAmount), lhsPaymentAmount, rhsPaymentAmount)) {
                return false;
            }
        }
        {
            String lhsPaymentId;
            lhsPaymentId = this.getPaymentId();
            String rhsPaymentId;
            rhsPaymentId = that.getPaymentId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentId", lhsPaymentId), LocatorUtils.property(thatLocator, "paymentId", rhsPaymentId), lhsPaymentId, rhsPaymentId)) {
                return false;
            }
        }
        {
            String lhsPaymentResponseCode;
            lhsPaymentResponseCode = this.getPaymentResponseCode();
            String rhsPaymentResponseCode;
            rhsPaymentResponseCode = that.getPaymentResponseCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentResponseCode", lhsPaymentResponseCode), LocatorUtils.property(thatLocator, "paymentResponseCode", rhsPaymentResponseCode), lhsPaymentResponseCode, rhsPaymentResponseCode)) {
                return false;
            }
        }
        {
            CreditCard lhsCreditCard;
            lhsCreditCard = this.getCreditCard();
            CreditCard rhsCreditCard;
            rhsCreditCard = that.getCreditCard();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCard", lhsCreditCard), LocatorUtils.property(thatLocator, "creditCard", rhsCreditCard), lhsCreditCard, rhsCreditCard)) {
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
