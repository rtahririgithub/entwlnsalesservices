
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
 * <p>Java class for HardwarePricingDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HardwarePricingDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="suggestedPriceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="offerDiscountAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="discretionaryDiscountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="bibDiscountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="bibTaxAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxAmount" minOccurs="0"/>
 *         &lt;element name="financedAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="financeTaxAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxAmount" minOccurs="0"/>
 *         &lt;element name="purchaseCreditAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="hardwareFinalPriceAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PriceAmountAndTax" minOccurs="0"/>
 *         &lt;element name="hardwarePricingChangedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="redeemedAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="installmentAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="monthlyInstallmentAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="currentBalanceUsedForPurchaseAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="financeTermNum" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HardwarePricingDetailType", propOrder = {
    "suggestedPriceAmount",
    "offerDiscountAmount",
    "discretionaryDiscountAmt",
    "bibDiscountAmt",
    "bibTaxAmount",
    "financedAmount",
    "financeTaxAmount",
    "purchaseCreditAmount",
    "hardwareFinalPriceAmount",
    "hardwarePricingChangedInd",
    "redeemedAmount",
    "installmentAmount",
    "monthlyInstallmentAmount",
    "currentBalanceUsedForPurchaseAmt",
    "financeTermNum"
})
public class HardwarePricingDetailType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double suggestedPriceAmount;
    protected Double offerDiscountAmount;
    protected Double discretionaryDiscountAmt;
    protected Double bibDiscountAmt;
    protected TaxAmount bibTaxAmount;
    protected Double financedAmount;
    protected TaxAmount financeTaxAmount;
    protected Double purchaseCreditAmount;
    protected PriceAmountAndTax hardwareFinalPriceAmount;
    protected Boolean hardwarePricingChangedInd;
    protected Double redeemedAmount;
    protected Double installmentAmount;
    protected Double monthlyInstallmentAmount;
    protected Double currentBalanceUsedForPurchaseAmt;
    protected BigInteger financeTermNum;

    /**
     * Gets the value of the suggestedPriceAmount property.
     * 
     */
    public double getSuggestedPriceAmount() {
        return suggestedPriceAmount;
    }

    /**
     * Sets the value of the suggestedPriceAmount property.
     * 
     */
    public void setSuggestedPriceAmount(double value) {
        this.suggestedPriceAmount = value;
    }

    /**
     * Gets the value of the offerDiscountAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOfferDiscountAmount() {
        return offerDiscountAmount;
    }

    /**
     * Sets the value of the offerDiscountAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOfferDiscountAmount(Double value) {
        this.offerDiscountAmount = value;
    }

    /**
     * Gets the value of the discretionaryDiscountAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscretionaryDiscountAmt() {
        return discretionaryDiscountAmt;
    }

    /**
     * Sets the value of the discretionaryDiscountAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscretionaryDiscountAmt(Double value) {
        this.discretionaryDiscountAmt = value;
    }

    /**
     * Gets the value of the bibDiscountAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBibDiscountAmt() {
        return bibDiscountAmt;
    }

    /**
     * Sets the value of the bibDiscountAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBibDiscountAmt(Double value) {
        this.bibDiscountAmt = value;
    }

    /**
     * Gets the value of the bibTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getBibTaxAmount() {
        return bibTaxAmount;
    }

    /**
     * Sets the value of the bibTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setBibTaxAmount(TaxAmount value) {
        this.bibTaxAmount = value;
    }

    /**
     * Gets the value of the financedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFinancedAmount() {
        return financedAmount;
    }

    /**
     * Sets the value of the financedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFinancedAmount(Double value) {
        this.financedAmount = value;
    }

    /**
     * Gets the value of the financeTaxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getFinanceTaxAmount() {
        return financeTaxAmount;
    }

    /**
     * Sets the value of the financeTaxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setFinanceTaxAmount(TaxAmount value) {
        this.financeTaxAmount = value;
    }

    /**
     * Gets the value of the purchaseCreditAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPurchaseCreditAmount() {
        return purchaseCreditAmount;
    }

    /**
     * Sets the value of the purchaseCreditAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPurchaseCreditAmount(Double value) {
        this.purchaseCreditAmount = value;
    }

    /**
     * Gets the value of the hardwareFinalPriceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public PriceAmountAndTax getHardwareFinalPriceAmount() {
        return hardwareFinalPriceAmount;
    }

    /**
     * Sets the value of the hardwareFinalPriceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public void setHardwareFinalPriceAmount(PriceAmountAndTax value) {
        this.hardwareFinalPriceAmount = value;
    }

    /**
     * Gets the value of the hardwarePricingChangedInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isHardwarePricingChangedInd() {
        return hardwarePricingChangedInd;
    }

    /**
     * Sets the value of the hardwarePricingChangedInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setHardwarePricingChangedInd(Boolean value) {
        this.hardwarePricingChangedInd = value;
    }

    /**
     * Gets the value of the redeemedAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getRedeemedAmount() {
        return redeemedAmount;
    }

    /**
     * Sets the value of the redeemedAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setRedeemedAmount(Double value) {
        this.redeemedAmount = value;
    }

    /**
     * Gets the value of the installmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInstallmentAmount() {
        return installmentAmount;
    }

    /**
     * Sets the value of the installmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInstallmentAmount(Double value) {
        this.installmentAmount = value;
    }

    /**
     * Gets the value of the monthlyInstallmentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonthlyInstallmentAmount() {
        return monthlyInstallmentAmount;
    }

    /**
     * Sets the value of the monthlyInstallmentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonthlyInstallmentAmount(Double value) {
        this.monthlyInstallmentAmount = value;
    }

    /**
     * Gets the value of the currentBalanceUsedForPurchaseAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getCurrentBalanceUsedForPurchaseAmt() {
        return currentBalanceUsedForPurchaseAmt;
    }

    /**
     * Sets the value of the currentBalanceUsedForPurchaseAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setCurrentBalanceUsedForPurchaseAmt(Double value) {
        this.currentBalanceUsedForPurchaseAmt = value;
    }

    /**
     * Gets the value of the financeTermNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getFinanceTermNum() {
        return financeTermNum;
    }

    /**
     * Sets the value of the financeTermNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setFinanceTermNum(BigInteger value) {
        this.financeTermNum = value;
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
            double theSuggestedPriceAmount;
            theSuggestedPriceAmount = (true?this.getSuggestedPriceAmount(): 0.0D);
            strategy.appendField(locator, this, "suggestedPriceAmount", buffer, theSuggestedPriceAmount);
        }
        {
            Double theOfferDiscountAmount;
            theOfferDiscountAmount = this.getOfferDiscountAmount();
            strategy.appendField(locator, this, "offerDiscountAmount", buffer, theOfferDiscountAmount);
        }
        {
            Double theDiscretionaryDiscountAmt;
            theDiscretionaryDiscountAmt = this.getDiscretionaryDiscountAmt();
            strategy.appendField(locator, this, "discretionaryDiscountAmt", buffer, theDiscretionaryDiscountAmt);
        }
        {
            Double theBibDiscountAmt;
            theBibDiscountAmt = this.getBibDiscountAmt();
            strategy.appendField(locator, this, "bibDiscountAmt", buffer, theBibDiscountAmt);
        }
        {
            TaxAmount theBibTaxAmount;
            theBibTaxAmount = this.getBibTaxAmount();
            strategy.appendField(locator, this, "bibTaxAmount", buffer, theBibTaxAmount);
        }
        {
            Double theFinancedAmount;
            theFinancedAmount = this.getFinancedAmount();
            strategy.appendField(locator, this, "financedAmount", buffer, theFinancedAmount);
        }
        {
            TaxAmount theFinanceTaxAmount;
            theFinanceTaxAmount = this.getFinanceTaxAmount();
            strategy.appendField(locator, this, "financeTaxAmount", buffer, theFinanceTaxAmount);
        }
        {
            Double thePurchaseCreditAmount;
            thePurchaseCreditAmount = this.getPurchaseCreditAmount();
            strategy.appendField(locator, this, "purchaseCreditAmount", buffer, thePurchaseCreditAmount);
        }
        {
            PriceAmountAndTax theHardwareFinalPriceAmount;
            theHardwareFinalPriceAmount = this.getHardwareFinalPriceAmount();
            strategy.appendField(locator, this, "hardwareFinalPriceAmount", buffer, theHardwareFinalPriceAmount);
        }
        {
            Boolean theHardwarePricingChangedInd;
            theHardwarePricingChangedInd = this.isHardwarePricingChangedInd();
            strategy.appendField(locator, this, "hardwarePricingChangedInd", buffer, theHardwarePricingChangedInd);
        }
        {
            Double theRedeemedAmount;
            theRedeemedAmount = this.getRedeemedAmount();
            strategy.appendField(locator, this, "redeemedAmount", buffer, theRedeemedAmount);
        }
        {
            Double theInstallmentAmount;
            theInstallmentAmount = this.getInstallmentAmount();
            strategy.appendField(locator, this, "installmentAmount", buffer, theInstallmentAmount);
        }
        {
            Double theMonthlyInstallmentAmount;
            theMonthlyInstallmentAmount = this.getMonthlyInstallmentAmount();
            strategy.appendField(locator, this, "monthlyInstallmentAmount", buffer, theMonthlyInstallmentAmount);
        }
        {
            Double theCurrentBalanceUsedForPurchaseAmt;
            theCurrentBalanceUsedForPurchaseAmt = this.getCurrentBalanceUsedForPurchaseAmt();
            strategy.appendField(locator, this, "currentBalanceUsedForPurchaseAmt", buffer, theCurrentBalanceUsedForPurchaseAmt);
        }
        {
            BigInteger theFinanceTermNum;
            theFinanceTermNum = this.getFinanceTermNum();
            strategy.appendField(locator, this, "financeTermNum", buffer, theFinanceTermNum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HardwarePricingDetailType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HardwarePricingDetailType that = ((HardwarePricingDetailType) object);
        {
            double lhsSuggestedPriceAmount;
            lhsSuggestedPriceAmount = (true?this.getSuggestedPriceAmount(): 0.0D);
            double rhsSuggestedPriceAmount;
            rhsSuggestedPriceAmount = (true?that.getSuggestedPriceAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "suggestedPriceAmount", lhsSuggestedPriceAmount), LocatorUtils.property(thatLocator, "suggestedPriceAmount", rhsSuggestedPriceAmount), lhsSuggestedPriceAmount, rhsSuggestedPriceAmount)) {
                return false;
            }
        }
        {
            Double lhsOfferDiscountAmount;
            lhsOfferDiscountAmount = this.getOfferDiscountAmount();
            Double rhsOfferDiscountAmount;
            rhsOfferDiscountAmount = that.getOfferDiscountAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerDiscountAmount", lhsOfferDiscountAmount), LocatorUtils.property(thatLocator, "offerDiscountAmount", rhsOfferDiscountAmount), lhsOfferDiscountAmount, rhsOfferDiscountAmount)) {
                return false;
            }
        }
        {
            Double lhsDiscretionaryDiscountAmt;
            lhsDiscretionaryDiscountAmt = this.getDiscretionaryDiscountAmt();
            Double rhsDiscretionaryDiscountAmt;
            rhsDiscretionaryDiscountAmt = that.getDiscretionaryDiscountAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discretionaryDiscountAmt", lhsDiscretionaryDiscountAmt), LocatorUtils.property(thatLocator, "discretionaryDiscountAmt", rhsDiscretionaryDiscountAmt), lhsDiscretionaryDiscountAmt, rhsDiscretionaryDiscountAmt)) {
                return false;
            }
        }
        {
            Double lhsBibDiscountAmt;
            lhsBibDiscountAmt = this.getBibDiscountAmt();
            Double rhsBibDiscountAmt;
            rhsBibDiscountAmt = that.getBibDiscountAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bibDiscountAmt", lhsBibDiscountAmt), LocatorUtils.property(thatLocator, "bibDiscountAmt", rhsBibDiscountAmt), lhsBibDiscountAmt, rhsBibDiscountAmt)) {
                return false;
            }
        }
        {
            TaxAmount lhsBibTaxAmount;
            lhsBibTaxAmount = this.getBibTaxAmount();
            TaxAmount rhsBibTaxAmount;
            rhsBibTaxAmount = that.getBibTaxAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bibTaxAmount", lhsBibTaxAmount), LocatorUtils.property(thatLocator, "bibTaxAmount", rhsBibTaxAmount), lhsBibTaxAmount, rhsBibTaxAmount)) {
                return false;
            }
        }
        {
            Double lhsFinancedAmount;
            lhsFinancedAmount = this.getFinancedAmount();
            Double rhsFinancedAmount;
            rhsFinancedAmount = that.getFinancedAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "financedAmount", lhsFinancedAmount), LocatorUtils.property(thatLocator, "financedAmount", rhsFinancedAmount), lhsFinancedAmount, rhsFinancedAmount)) {
                return false;
            }
        }
        {
            TaxAmount lhsFinanceTaxAmount;
            lhsFinanceTaxAmount = this.getFinanceTaxAmount();
            TaxAmount rhsFinanceTaxAmount;
            rhsFinanceTaxAmount = that.getFinanceTaxAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "financeTaxAmount", lhsFinanceTaxAmount), LocatorUtils.property(thatLocator, "financeTaxAmount", rhsFinanceTaxAmount), lhsFinanceTaxAmount, rhsFinanceTaxAmount)) {
                return false;
            }
        }
        {
            Double lhsPurchaseCreditAmount;
            lhsPurchaseCreditAmount = this.getPurchaseCreditAmount();
            Double rhsPurchaseCreditAmount;
            rhsPurchaseCreditAmount = that.getPurchaseCreditAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseCreditAmount", lhsPurchaseCreditAmount), LocatorUtils.property(thatLocator, "purchaseCreditAmount", rhsPurchaseCreditAmount), lhsPurchaseCreditAmount, rhsPurchaseCreditAmount)) {
                return false;
            }
        }
        {
            PriceAmountAndTax lhsHardwareFinalPriceAmount;
            lhsHardwareFinalPriceAmount = this.getHardwareFinalPriceAmount();
            PriceAmountAndTax rhsHardwareFinalPriceAmount;
            rhsHardwareFinalPriceAmount = that.getHardwareFinalPriceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwareFinalPriceAmount", lhsHardwareFinalPriceAmount), LocatorUtils.property(thatLocator, "hardwareFinalPriceAmount", rhsHardwareFinalPriceAmount), lhsHardwareFinalPriceAmount, rhsHardwareFinalPriceAmount)) {
                return false;
            }
        }
        {
            Boolean lhsHardwarePricingChangedInd;
            lhsHardwarePricingChangedInd = this.isHardwarePricingChangedInd();
            Boolean rhsHardwarePricingChangedInd;
            rhsHardwarePricingChangedInd = that.isHardwarePricingChangedInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwarePricingChangedInd", lhsHardwarePricingChangedInd), LocatorUtils.property(thatLocator, "hardwarePricingChangedInd", rhsHardwarePricingChangedInd), lhsHardwarePricingChangedInd, rhsHardwarePricingChangedInd)) {
                return false;
            }
        }
        {
            Double lhsRedeemedAmount;
            lhsRedeemedAmount = this.getRedeemedAmount();
            Double rhsRedeemedAmount;
            rhsRedeemedAmount = that.getRedeemedAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "redeemedAmount", lhsRedeemedAmount), LocatorUtils.property(thatLocator, "redeemedAmount", rhsRedeemedAmount), lhsRedeemedAmount, rhsRedeemedAmount)) {
                return false;
            }
        }
        {
            Double lhsInstallmentAmount;
            lhsInstallmentAmount = this.getInstallmentAmount();
            Double rhsInstallmentAmount;
            rhsInstallmentAmount = that.getInstallmentAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "installmentAmount", lhsInstallmentAmount), LocatorUtils.property(thatLocator, "installmentAmount", rhsInstallmentAmount), lhsInstallmentAmount, rhsInstallmentAmount)) {
                return false;
            }
        }
        {
            Double lhsMonthlyInstallmentAmount;
            lhsMonthlyInstallmentAmount = this.getMonthlyInstallmentAmount();
            Double rhsMonthlyInstallmentAmount;
            rhsMonthlyInstallmentAmount = that.getMonthlyInstallmentAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "monthlyInstallmentAmount", lhsMonthlyInstallmentAmount), LocatorUtils.property(thatLocator, "monthlyInstallmentAmount", rhsMonthlyInstallmentAmount), lhsMonthlyInstallmentAmount, rhsMonthlyInstallmentAmount)) {
                return false;
            }
        }
        {
            Double lhsCurrentBalanceUsedForPurchaseAmt;
            lhsCurrentBalanceUsedForPurchaseAmt = this.getCurrentBalanceUsedForPurchaseAmt();
            Double rhsCurrentBalanceUsedForPurchaseAmt;
            rhsCurrentBalanceUsedForPurchaseAmt = that.getCurrentBalanceUsedForPurchaseAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "currentBalanceUsedForPurchaseAmt", lhsCurrentBalanceUsedForPurchaseAmt), LocatorUtils.property(thatLocator, "currentBalanceUsedForPurchaseAmt", rhsCurrentBalanceUsedForPurchaseAmt), lhsCurrentBalanceUsedForPurchaseAmt, rhsCurrentBalanceUsedForPurchaseAmt)) {
                return false;
            }
        }
        {
            BigInteger lhsFinanceTermNum;
            lhsFinanceTermNum = this.getFinanceTermNum();
            BigInteger rhsFinanceTermNum;
            rhsFinanceTermNum = that.getFinanceTermNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "financeTermNum", lhsFinanceTermNum), LocatorUtils.property(thatLocator, "financeTermNum", rhsFinanceTermNum), lhsFinanceTermNum, rhsFinanceTermNum)) {
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
