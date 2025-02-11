
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
 * <p>Java class for PriceAmountAndTax complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PriceAmountAndTax">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preTaxPriceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="taxIncludedPriceAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
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
@XmlType(name = "PriceAmountAndTax", propOrder = {
    "preTaxPriceAmount",
    "taxIncludedPriceAmount",
    "taxAmount"
})
public class PriceAmountAndTax
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double preTaxPriceAmount;
    protected Double taxIncludedPriceAmount;
    protected TaxAmount taxAmount;

    /**
     * Gets the value of the preTaxPriceAmount property.
     * 
     */
    public double getPreTaxPriceAmount() {
        return preTaxPriceAmount;
    }

    /**
     * Sets the value of the preTaxPriceAmount property.
     * 
     */
    public void setPreTaxPriceAmount(double value) {
        this.preTaxPriceAmount = value;
    }

    /**
     * Gets the value of the taxIncludedPriceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTaxIncludedPriceAmount() {
        return taxIncludedPriceAmount;
    }

    /**
     * Sets the value of the taxIncludedPriceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTaxIncludedPriceAmount(Double value) {
        this.taxIncludedPriceAmount = value;
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
            double thePreTaxPriceAmount;
            thePreTaxPriceAmount = (true?this.getPreTaxPriceAmount(): 0.0D);
            strategy.appendField(locator, this, "preTaxPriceAmount", buffer, thePreTaxPriceAmount);
        }
        {
            Double theTaxIncludedPriceAmount;
            theTaxIncludedPriceAmount = this.getTaxIncludedPriceAmount();
            strategy.appendField(locator, this, "taxIncludedPriceAmount", buffer, theTaxIncludedPriceAmount);
        }
        {
            TaxAmount theTaxAmount;
            theTaxAmount = this.getTaxAmount();
            strategy.appendField(locator, this, "taxAmount", buffer, theTaxAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PriceAmountAndTax)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PriceAmountAndTax that = ((PriceAmountAndTax) object);
        {
            double lhsPreTaxPriceAmount;
            lhsPreTaxPriceAmount = (true?this.getPreTaxPriceAmount(): 0.0D);
            double rhsPreTaxPriceAmount;
            rhsPreTaxPriceAmount = (true?that.getPreTaxPriceAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preTaxPriceAmount", lhsPreTaxPriceAmount), LocatorUtils.property(thatLocator, "preTaxPriceAmount", rhsPreTaxPriceAmount), lhsPreTaxPriceAmount, rhsPreTaxPriceAmount)) {
                return false;
            }
        }
        {
            Double lhsTaxIncludedPriceAmount;
            lhsTaxIncludedPriceAmount = this.getTaxIncludedPriceAmount();
            Double rhsTaxIncludedPriceAmount;
            rhsTaxIncludedPriceAmount = that.getTaxIncludedPriceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxIncludedPriceAmount", lhsTaxIncludedPriceAmount), LocatorUtils.property(thatLocator, "taxIncludedPriceAmount", rhsTaxIncludedPriceAmount), lhsTaxIncludedPriceAmount, rhsTaxIncludedPriceAmount)) {
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
