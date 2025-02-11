
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
 * <p>Java class for EarlyDeviceUpgradeFee complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EarlyDeviceUpgradeFee">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="feeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="feeAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="taxAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxAmount" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EarlyDeviceUpgradeFee", propOrder = {
    "feeTypeCode",
    "feeCode",
    "feeAmount",
    "taxAmount"
})
public class EarlyDeviceUpgradeFee
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String feeTypeCode;
    @XmlElement(required = true)
    protected String feeCode;
    protected double feeAmount;
    protected TaxAmount taxAmount;

    /**
     * Gets the value of the feeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeTypeCode() {
        return feeTypeCode;
    }

    /**
     * Sets the value of the feeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeTypeCode(String value) {
        this.feeTypeCode = value;
    }

    /**
     * Gets the value of the feeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeeCode() {
        return feeCode;
    }

    /**
     * Sets the value of the feeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeeCode(String value) {
        this.feeCode = value;
    }

    /**
     * Gets the value of the feeAmount property.
     * 
     */
    public double getFeeAmount() {
        return feeAmount;
    }

    /**
     * Sets the value of the feeAmount property.
     * 
     */
    public void setFeeAmount(double value) {
        this.feeAmount = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setTaxAmount(TaxAmount value) {
        this.taxAmount = value;
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
            String theFeeTypeCode;
            theFeeTypeCode = this.getFeeTypeCode();
            strategy.appendField(locator, this, "feeTypeCode", buffer, theFeeTypeCode);
        }
        {
            String theFeeCode;
            theFeeCode = this.getFeeCode();
            strategy.appendField(locator, this, "feeCode", buffer, theFeeCode);
        }
        {
            double theFeeAmount;
            theFeeAmount = (true?this.getFeeAmount(): 0.0D);
            strategy.appendField(locator, this, "feeAmount", buffer, theFeeAmount);
        }
        {
            TaxAmount theTaxAmount;
            theTaxAmount = this.getTaxAmount();
            strategy.appendField(locator, this, "taxAmount", buffer, theTaxAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EarlyDeviceUpgradeFee)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EarlyDeviceUpgradeFee that = ((EarlyDeviceUpgradeFee) object);
        {
            String lhsFeeTypeCode;
            lhsFeeTypeCode = this.getFeeTypeCode();
            String rhsFeeTypeCode;
            rhsFeeTypeCode = that.getFeeTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "feeTypeCode", lhsFeeTypeCode), LocatorUtils.property(thatLocator, "feeTypeCode", rhsFeeTypeCode), lhsFeeTypeCode, rhsFeeTypeCode)) {
                return false;
            }
        }
        {
            String lhsFeeCode;
            lhsFeeCode = this.getFeeCode();
            String rhsFeeCode;
            rhsFeeCode = that.getFeeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "feeCode", lhsFeeCode), LocatorUtils.property(thatLocator, "feeCode", rhsFeeCode), lhsFeeCode, rhsFeeCode)) {
                return false;
            }
        }
        {
            double lhsFeeAmount;
            lhsFeeAmount = (true?this.getFeeAmount(): 0.0D);
            double rhsFeeAmount;
            rhsFeeAmount = (true?that.getFeeAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "feeAmount", lhsFeeAmount), LocatorUtils.property(thatLocator, "feeAmount", rhsFeeAmount), lhsFeeAmount, rhsFeeAmount)) {
                return false;
            }
        }
        {
            TaxAmount lhsTaxAmount;
            lhsTaxAmount = this.getTaxAmount();
            TaxAmount rhsTaxAmount;
            rhsTaxAmount = that.getTaxAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxAmount", lhsTaxAmount), LocatorUtils.property(thatLocator, "taxAmount", rhsTaxAmount), lhsTaxAmount, rhsTaxAmount)) {
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
