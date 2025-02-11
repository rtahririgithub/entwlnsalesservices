
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
 * <p>Java class for TaxAmount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxAmount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pstAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="gstAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="hstAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="taxationPolicy" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxPolicyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxAmount", propOrder = {
    "pstAmount",
    "gstAmount",
    "hstAmount",
    "taxationPolicy"
})
public class TaxAmount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double pstAmount;
    protected double gstAmount;
    protected double hstAmount;
    protected TaxPolicyType taxationPolicy;

    /**
     * Gets the value of the pstAmount property.
     * 
     */
    public double getPstAmount() {
        return pstAmount;
    }

    /**
     * Sets the value of the pstAmount property.
     * 
     */
    public void setPstAmount(double value) {
        this.pstAmount = value;
    }

    /**
     * Gets the value of the gstAmount property.
     * 
     */
    public double getGstAmount() {
        return gstAmount;
    }

    /**
     * Sets the value of the gstAmount property.
     * 
     */
    public void setGstAmount(double value) {
        this.gstAmount = value;
    }

    /**
     * Gets the value of the hstAmount property.
     * 
     */
    public double getHstAmount() {
        return hstAmount;
    }

    /**
     * Sets the value of the hstAmount property.
     * 
     */
    public void setHstAmount(double value) {
        this.hstAmount = value;
    }

    /**
     * Gets the value of the taxationPolicy property.
     * 
     * @return
     *     possible object is
     *     {@link TaxPolicyType }
     *     
     */
    public TaxPolicyType getTaxationPolicy() {
        return taxationPolicy;
    }

    /**
     * Sets the value of the taxationPolicy property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxPolicyType }
     *     
     */
    public void setTaxationPolicy(TaxPolicyType value) {
        this.taxationPolicy = value;
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
            double thePstAmount;
            thePstAmount = (true?this.getPstAmount(): 0.0D);
            strategy.appendField(locator, this, "pstAmount", buffer, thePstAmount);
        }
        {
            double theGstAmount;
            theGstAmount = (true?this.getGstAmount(): 0.0D);
            strategy.appendField(locator, this, "gstAmount", buffer, theGstAmount);
        }
        {
            double theHstAmount;
            theHstAmount = (true?this.getHstAmount(): 0.0D);
            strategy.appendField(locator, this, "hstAmount", buffer, theHstAmount);
        }
        {
            TaxPolicyType theTaxationPolicy;
            theTaxationPolicy = this.getTaxationPolicy();
            strategy.appendField(locator, this, "taxationPolicy", buffer, theTaxationPolicy);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TaxAmount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TaxAmount that = ((TaxAmount) object);
        {
            double lhsPstAmount;
            lhsPstAmount = (true?this.getPstAmount(): 0.0D);
            double rhsPstAmount;
            rhsPstAmount = (true?that.getPstAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pstAmount", lhsPstAmount), LocatorUtils.property(thatLocator, "pstAmount", rhsPstAmount), lhsPstAmount, rhsPstAmount)) {
                return false;
            }
        }
        {
            double lhsGstAmount;
            lhsGstAmount = (true?this.getGstAmount(): 0.0D);
            double rhsGstAmount;
            rhsGstAmount = (true?that.getGstAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gstAmount", lhsGstAmount), LocatorUtils.property(thatLocator, "gstAmount", rhsGstAmount), lhsGstAmount, rhsGstAmount)) {
                return false;
            }
        }
        {
            double lhsHstAmount;
            lhsHstAmount = (true?this.getHstAmount(): 0.0D);
            double rhsHstAmount;
            rhsHstAmount = (true?that.getHstAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hstAmount", lhsHstAmount), LocatorUtils.property(thatLocator, "hstAmount", rhsHstAmount), lhsHstAmount, rhsHstAmount)) {
                return false;
            }
        }
        {
            TaxPolicyType lhsTaxationPolicy;
            lhsTaxationPolicy = this.getTaxationPolicy();
            TaxPolicyType rhsTaxationPolicy;
            rhsTaxationPolicy = that.getTaxationPolicy();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxationPolicy", lhsTaxationPolicy), LocatorUtils.property(thatLocator, "taxationPolicy", rhsTaxationPolicy), lhsTaxationPolicy, rhsTaxationPolicy)) {
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
