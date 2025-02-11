
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
 * <p>Java class for TaxPolicyType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxPolicyType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="provinceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pstRateAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="gstRateAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="hstRateAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="taxationMethodCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="minimumPSTTaxableAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxPolicyType", propOrder = {
    "provinceCode",
    "pstRateAmount",
    "gstRateAmount",
    "hstRateAmount",
    "taxationMethodCode",
    "minimumPSTTaxableAmount"
})
public class TaxPolicyType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String provinceCode;
    protected double pstRateAmount;
    protected double gstRateAmount;
    protected double hstRateAmount;
    @XmlElement(required = true)
    protected String taxationMethodCode;
    protected double minimumPSTTaxableAmount;

    /**
     * Gets the value of the provinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the value of the provinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCode(String value) {
        this.provinceCode = value;
    }

    /**
     * Gets the value of the pstRateAmount property.
     * 
     */
    public double getPstRateAmount() {
        return pstRateAmount;
    }

    /**
     * Sets the value of the pstRateAmount property.
     * 
     */
    public void setPstRateAmount(double value) {
        this.pstRateAmount = value;
    }

    /**
     * Gets the value of the gstRateAmount property.
     * 
     */
    public double getGstRateAmount() {
        return gstRateAmount;
    }

    /**
     * Sets the value of the gstRateAmount property.
     * 
     */
    public void setGstRateAmount(double value) {
        this.gstRateAmount = value;
    }

    /**
     * Gets the value of the hstRateAmount property.
     * 
     */
    public double getHstRateAmount() {
        return hstRateAmount;
    }

    /**
     * Sets the value of the hstRateAmount property.
     * 
     */
    public void setHstRateAmount(double value) {
        this.hstRateAmount = value;
    }

    /**
     * Gets the value of the taxationMethodCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTaxationMethodCode() {
        return taxationMethodCode;
    }

    /**
     * Sets the value of the taxationMethodCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTaxationMethodCode(String value) {
        this.taxationMethodCode = value;
    }

    /**
     * Gets the value of the minimumPSTTaxableAmount property.
     * 
     */
    public double getMinimumPSTTaxableAmount() {
        return minimumPSTTaxableAmount;
    }

    /**
     * Sets the value of the minimumPSTTaxableAmount property.
     * 
     */
    public void setMinimumPSTTaxableAmount(double value) {
        this.minimumPSTTaxableAmount = value;
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
            String theProvinceCode;
            theProvinceCode = this.getProvinceCode();
            strategy.appendField(locator, this, "provinceCode", buffer, theProvinceCode);
        }
        {
            double thePstRateAmount;
            thePstRateAmount = (true?this.getPstRateAmount(): 0.0D);
            strategy.appendField(locator, this, "pstRateAmount", buffer, thePstRateAmount);
        }
        {
            double theGstRateAmount;
            theGstRateAmount = (true?this.getGstRateAmount(): 0.0D);
            strategy.appendField(locator, this, "gstRateAmount", buffer, theGstRateAmount);
        }
        {
            double theHstRateAmount;
            theHstRateAmount = (true?this.getHstRateAmount(): 0.0D);
            strategy.appendField(locator, this, "hstRateAmount", buffer, theHstRateAmount);
        }
        {
            String theTaxationMethodCode;
            theTaxationMethodCode = this.getTaxationMethodCode();
            strategy.appendField(locator, this, "taxationMethodCode", buffer, theTaxationMethodCode);
        }
        {
            double theMinimumPSTTaxableAmount;
            theMinimumPSTTaxableAmount = (true?this.getMinimumPSTTaxableAmount(): 0.0D);
            strategy.appendField(locator, this, "minimumPSTTaxableAmount", buffer, theMinimumPSTTaxableAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TaxPolicyType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TaxPolicyType that = ((TaxPolicyType) object);
        {
            String lhsProvinceCode;
            lhsProvinceCode = this.getProvinceCode();
            String rhsProvinceCode;
            rhsProvinceCode = that.getProvinceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceCode", lhsProvinceCode), LocatorUtils.property(thatLocator, "provinceCode", rhsProvinceCode), lhsProvinceCode, rhsProvinceCode)) {
                return false;
            }
        }
        {
            double lhsPstRateAmount;
            lhsPstRateAmount = (true?this.getPstRateAmount(): 0.0D);
            double rhsPstRateAmount;
            rhsPstRateAmount = (true?that.getPstRateAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pstRateAmount", lhsPstRateAmount), LocatorUtils.property(thatLocator, "pstRateAmount", rhsPstRateAmount), lhsPstRateAmount, rhsPstRateAmount)) {
                return false;
            }
        }
        {
            double lhsGstRateAmount;
            lhsGstRateAmount = (true?this.getGstRateAmount(): 0.0D);
            double rhsGstRateAmount;
            rhsGstRateAmount = (true?that.getGstRateAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gstRateAmount", lhsGstRateAmount), LocatorUtils.property(thatLocator, "gstRateAmount", rhsGstRateAmount), lhsGstRateAmount, rhsGstRateAmount)) {
                return false;
            }
        }
        {
            double lhsHstRateAmount;
            lhsHstRateAmount = (true?this.getHstRateAmount(): 0.0D);
            double rhsHstRateAmount;
            rhsHstRateAmount = (true?that.getHstRateAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hstRateAmount", lhsHstRateAmount), LocatorUtils.property(thatLocator, "hstRateAmount", rhsHstRateAmount), lhsHstRateAmount, rhsHstRateAmount)) {
                return false;
            }
        }
        {
            String lhsTaxationMethodCode;
            lhsTaxationMethodCode = this.getTaxationMethodCode();
            String rhsTaxationMethodCode;
            rhsTaxationMethodCode = that.getTaxationMethodCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxationMethodCode", lhsTaxationMethodCode), LocatorUtils.property(thatLocator, "taxationMethodCode", rhsTaxationMethodCode), lhsTaxationMethodCode, rhsTaxationMethodCode)) {
                return false;
            }
        }
        {
            double lhsMinimumPSTTaxableAmount;
            lhsMinimumPSTTaxableAmount = (true?this.getMinimumPSTTaxableAmount(): 0.0D);
            double rhsMinimumPSTTaxableAmount;
            rhsMinimumPSTTaxableAmount = (true?that.getMinimumPSTTaxableAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumPSTTaxableAmount", lhsMinimumPSTTaxableAmount), LocatorUtils.property(thatLocator, "minimumPSTTaxableAmount", rhsMinimumPSTTaxableAmount), lhsMinimumPSTTaxableAmount, rhsMinimumPSTTaxableAmount)) {
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
