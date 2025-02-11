
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
 * <p>Java class for PenaltyChargeDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PenaltyChargeDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rewardTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chargeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chargeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chargeAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="waiverCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="chargeWaiver" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ChargeWaiver" minOccurs="0"/>
 *         &lt;element name="finalChargeAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PriceAmountAndTax" minOccurs="0"/>
 *         &lt;element name="applyChargeOnBillIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PenaltyChargeDetail", propOrder = {
    "rewardTypeCode",
    "chargeTypeCode",
    "chargeCode",
    "chargeAmount",
    "waiverCode",
    "chargeWaiver",
    "finalChargeAmount",
    "applyChargeOnBillIndicator"
})
public class PenaltyChargeDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String rewardTypeCode;
    @XmlElement(required = true)
    protected String chargeTypeCode;
    @XmlElement(required = true)
    protected String chargeCode;
    protected double chargeAmount;
    protected String waiverCode;
    protected ChargeWaiver chargeWaiver;
    protected PriceAmountAndTax finalChargeAmount;
    protected Boolean applyChargeOnBillIndicator;

    /**
     * Gets the value of the rewardTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRewardTypeCode() {
        return rewardTypeCode;
    }

    /**
     * Sets the value of the rewardTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRewardTypeCode(String value) {
        this.rewardTypeCode = value;
    }

    /**
     * Gets the value of the chargeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeTypeCode() {
        return chargeTypeCode;
    }

    /**
     * Sets the value of the chargeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeTypeCode(String value) {
        this.chargeTypeCode = value;
    }

    /**
     * Gets the value of the chargeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChargeCode() {
        return chargeCode;
    }

    /**
     * Sets the value of the chargeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChargeCode(String value) {
        this.chargeCode = value;
    }

    /**
     * Gets the value of the chargeAmount property.
     * 
     */
    public double getChargeAmount() {
        return chargeAmount;
    }

    /**
     * Sets the value of the chargeAmount property.
     * 
     */
    public void setChargeAmount(double value) {
        this.chargeAmount = value;
    }

    /**
     * Gets the value of the waiverCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaiverCode() {
        return waiverCode;
    }

    /**
     * Sets the value of the waiverCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaiverCode(String value) {
        this.waiverCode = value;
    }

    /**
     * Gets the value of the chargeWaiver property.
     * 
     * @return
     *     possible object is
     *     {@link ChargeWaiver }
     *     
     */
    public ChargeWaiver getChargeWaiver() {
        return chargeWaiver;
    }

    /**
     * Sets the value of the chargeWaiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChargeWaiver }
     *     
     */
    public void setChargeWaiver(ChargeWaiver value) {
        this.chargeWaiver = value;
    }

    /**
     * Gets the value of the finalChargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public PriceAmountAndTax getFinalChargeAmount() {
        return finalChargeAmount;
    }

    /**
     * Sets the value of the finalChargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public void setFinalChargeAmount(PriceAmountAndTax value) {
        this.finalChargeAmount = value;
    }

    /**
     * Gets the value of the applyChargeOnBillIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isApplyChargeOnBillIndicator() {
        return applyChargeOnBillIndicator;
    }

    /**
     * Sets the value of the applyChargeOnBillIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setApplyChargeOnBillIndicator(Boolean value) {
        this.applyChargeOnBillIndicator = value;
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
            String theRewardTypeCode;
            theRewardTypeCode = this.getRewardTypeCode();
            strategy.appendField(locator, this, "rewardTypeCode", buffer, theRewardTypeCode);
        }
        {
            String theChargeTypeCode;
            theChargeTypeCode = this.getChargeTypeCode();
            strategy.appendField(locator, this, "chargeTypeCode", buffer, theChargeTypeCode);
        }
        {
            String theChargeCode;
            theChargeCode = this.getChargeCode();
            strategy.appendField(locator, this, "chargeCode", buffer, theChargeCode);
        }
        {
            double theChargeAmount;
            theChargeAmount = (true?this.getChargeAmount(): 0.0D);
            strategy.appendField(locator, this, "chargeAmount", buffer, theChargeAmount);
        }
        {
            String theWaiverCode;
            theWaiverCode = this.getWaiverCode();
            strategy.appendField(locator, this, "waiverCode", buffer, theWaiverCode);
        }
        {
            ChargeWaiver theChargeWaiver;
            theChargeWaiver = this.getChargeWaiver();
            strategy.appendField(locator, this, "chargeWaiver", buffer, theChargeWaiver);
        }
        {
            PriceAmountAndTax theFinalChargeAmount;
            theFinalChargeAmount = this.getFinalChargeAmount();
            strategy.appendField(locator, this, "finalChargeAmount", buffer, theFinalChargeAmount);
        }
        {
            Boolean theApplyChargeOnBillIndicator;
            theApplyChargeOnBillIndicator = this.isApplyChargeOnBillIndicator();
            strategy.appendField(locator, this, "applyChargeOnBillIndicator", buffer, theApplyChargeOnBillIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PenaltyChargeDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PenaltyChargeDetail that = ((PenaltyChargeDetail) object);
        {
            String lhsRewardTypeCode;
            lhsRewardTypeCode = this.getRewardTypeCode();
            String rhsRewardTypeCode;
            rhsRewardTypeCode = that.getRewardTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardTypeCode", lhsRewardTypeCode), LocatorUtils.property(thatLocator, "rewardTypeCode", rhsRewardTypeCode), lhsRewardTypeCode, rhsRewardTypeCode)) {
                return false;
            }
        }
        {
            String lhsChargeTypeCode;
            lhsChargeTypeCode = this.getChargeTypeCode();
            String rhsChargeTypeCode;
            rhsChargeTypeCode = that.getChargeTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "chargeTypeCode", lhsChargeTypeCode), LocatorUtils.property(thatLocator, "chargeTypeCode", rhsChargeTypeCode), lhsChargeTypeCode, rhsChargeTypeCode)) {
                return false;
            }
        }
        {
            String lhsChargeCode;
            lhsChargeCode = this.getChargeCode();
            String rhsChargeCode;
            rhsChargeCode = that.getChargeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "chargeCode", lhsChargeCode), LocatorUtils.property(thatLocator, "chargeCode", rhsChargeCode), lhsChargeCode, rhsChargeCode)) {
                return false;
            }
        }
        {
            double lhsChargeAmount;
            lhsChargeAmount = (true?this.getChargeAmount(): 0.0D);
            double rhsChargeAmount;
            rhsChargeAmount = (true?that.getChargeAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "chargeAmount", lhsChargeAmount), LocatorUtils.property(thatLocator, "chargeAmount", rhsChargeAmount), lhsChargeAmount, rhsChargeAmount)) {
                return false;
            }
        }
        {
            String lhsWaiverCode;
            lhsWaiverCode = this.getWaiverCode();
            String rhsWaiverCode;
            rhsWaiverCode = that.getWaiverCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "waiverCode", lhsWaiverCode), LocatorUtils.property(thatLocator, "waiverCode", rhsWaiverCode), lhsWaiverCode, rhsWaiverCode)) {
                return false;
            }
        }
        {
            ChargeWaiver lhsChargeWaiver;
            lhsChargeWaiver = this.getChargeWaiver();
            ChargeWaiver rhsChargeWaiver;
            rhsChargeWaiver = that.getChargeWaiver();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "chargeWaiver", lhsChargeWaiver), LocatorUtils.property(thatLocator, "chargeWaiver", rhsChargeWaiver), lhsChargeWaiver, rhsChargeWaiver)) {
                return false;
            }
        }
        {
            PriceAmountAndTax lhsFinalChargeAmount;
            lhsFinalChargeAmount = this.getFinalChargeAmount();
            PriceAmountAndTax rhsFinalChargeAmount;
            rhsFinalChargeAmount = that.getFinalChargeAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "finalChargeAmount", lhsFinalChargeAmount), LocatorUtils.property(thatLocator, "finalChargeAmount", rhsFinalChargeAmount), lhsFinalChargeAmount, rhsFinalChargeAmount)) {
                return false;
            }
        }
        {
            Boolean lhsApplyChargeOnBillIndicator;
            lhsApplyChargeOnBillIndicator = this.isApplyChargeOnBillIndicator();
            Boolean rhsApplyChargeOnBillIndicator;
            rhsApplyChargeOnBillIndicator = that.isApplyChargeOnBillIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "applyChargeOnBillIndicator", lhsApplyChargeOnBillIndicator), LocatorUtils.property(thatLocator, "applyChargeOnBillIndicator", rhsApplyChargeOnBillIndicator), lhsApplyChargeOnBillIndicator, rhsApplyChargeOnBillIndicator)) {
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
