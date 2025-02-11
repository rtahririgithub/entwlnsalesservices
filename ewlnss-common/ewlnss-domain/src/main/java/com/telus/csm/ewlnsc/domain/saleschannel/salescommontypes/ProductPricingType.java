
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
 * <p>Java class for ProductPricingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductPricingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="suggestedPriceAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="priceAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PriceAmountAndTax" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductPricingType", propOrder = {
    "productCd",
    "suggestedPriceAmt",
    "priceAmount"
})
public class ProductPricingType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productCd;
    protected double suggestedPriceAmt;
    protected PriceAmountAndTax priceAmount;

    /**
     * Gets the value of the productCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCd() {
        return productCd;
    }

    /**
     * Sets the value of the productCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCd(String value) {
        this.productCd = value;
    }

    /**
     * Gets the value of the suggestedPriceAmt property.
     * 
     */
    public double getSuggestedPriceAmt() {
        return suggestedPriceAmt;
    }

    /**
     * Sets the value of the suggestedPriceAmt property.
     * 
     */
    public void setSuggestedPriceAmt(double value) {
        this.suggestedPriceAmt = value;
    }

    /**
     * Gets the value of the priceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public PriceAmountAndTax getPriceAmount() {
        return priceAmount;
    }

    /**
     * Sets the value of the priceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public void setPriceAmount(PriceAmountAndTax value) {
        this.priceAmount = value;
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
            String theProductCd;
            theProductCd = this.getProductCd();
            strategy.appendField(locator, this, "productCd", buffer, theProductCd);
        }
        {
            double theSuggestedPriceAmt;
            theSuggestedPriceAmt = (true?this.getSuggestedPriceAmt(): 0.0D);
            strategy.appendField(locator, this, "suggestedPriceAmt", buffer, theSuggestedPriceAmt);
        }
        {
            PriceAmountAndTax thePriceAmount;
            thePriceAmount = this.getPriceAmount();
            strategy.appendField(locator, this, "priceAmount", buffer, thePriceAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductPricingType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductPricingType that = ((ProductPricingType) object);
        {
            String lhsProductCd;
            lhsProductCd = this.getProductCd();
            String rhsProductCd;
            rhsProductCd = that.getProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCd", lhsProductCd), LocatorUtils.property(thatLocator, "productCd", rhsProductCd), lhsProductCd, rhsProductCd)) {
                return false;
            }
        }
        {
            double lhsSuggestedPriceAmt;
            lhsSuggestedPriceAmt = (true?this.getSuggestedPriceAmt(): 0.0D);
            double rhsSuggestedPriceAmt;
            rhsSuggestedPriceAmt = (true?that.getSuggestedPriceAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "suggestedPriceAmt", lhsSuggestedPriceAmt), LocatorUtils.property(thatLocator, "suggestedPriceAmt", rhsSuggestedPriceAmt), lhsSuggestedPriceAmt, rhsSuggestedPriceAmt)) {
                return false;
            }
        }
        {
            PriceAmountAndTax lhsPriceAmount;
            lhsPriceAmount = this.getPriceAmount();
            PriceAmountAndTax rhsPriceAmount;
            rhsPriceAmount = that.getPriceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "priceAmount", lhsPriceAmount), LocatorUtils.property(thatLocator, "priceAmount", rhsPriceAmount), lhsPriceAmount, rhsPriceAmount)) {
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
