
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
 * <p>Java class for AccountBaseTaxationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountBaseTaxationType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccountBase">
 *       &lt;sequence>
 *         &lt;element name="taxExemptionIndicators" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxExemptionIndicatorsType" minOccurs="0"/>
 *         &lt;element name="taxCertificationNumbers" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxCertificationNumberType" minOccurs="0"/>
 *         &lt;element name="accountMasterSourceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountBaseTaxationType", propOrder = {
    "taxExemptionIndicators",
    "taxCertificationNumbers",
    "accountMasterSourceTypeCd"
})
public class AccountBaseTaxationType
    extends AccountBase
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected TaxExemptionIndicatorsType taxExemptionIndicators;
    protected TaxCertificationNumberType taxCertificationNumbers;
    protected String accountMasterSourceTypeCd;

    /**
     * Gets the value of the taxExemptionIndicators property.
     * 
     * @return
     *     possible object is
     *     {@link TaxExemptionIndicatorsType }
     *     
     */
    public TaxExemptionIndicatorsType getTaxExemptionIndicators() {
        return taxExemptionIndicators;
    }

    /**
     * Sets the value of the taxExemptionIndicators property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxExemptionIndicatorsType }
     *     
     */
    public void setTaxExemptionIndicators(TaxExemptionIndicatorsType value) {
        this.taxExemptionIndicators = value;
    }

    /**
     * Gets the value of the taxCertificationNumbers property.
     * 
     * @return
     *     possible object is
     *     {@link TaxCertificationNumberType }
     *     
     */
    public TaxCertificationNumberType getTaxCertificationNumbers() {
        return taxCertificationNumbers;
    }

    /**
     * Sets the value of the taxCertificationNumbers property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxCertificationNumberType }
     *     
     */
    public void setTaxCertificationNumbers(TaxCertificationNumberType value) {
        this.taxCertificationNumbers = value;
    }

    /**
     * Gets the value of the accountMasterSourceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountMasterSourceTypeCd() {
        return accountMasterSourceTypeCd;
    }

    /**
     * Sets the value of the accountMasterSourceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountMasterSourceTypeCd(String value) {
        this.accountMasterSourceTypeCd = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            TaxExemptionIndicatorsType theTaxExemptionIndicators;
            theTaxExemptionIndicators = this.getTaxExemptionIndicators();
            strategy.appendField(locator, this, "taxExemptionIndicators", buffer, theTaxExemptionIndicators);
        }
        {
            TaxCertificationNumberType theTaxCertificationNumbers;
            theTaxCertificationNumbers = this.getTaxCertificationNumbers();
            strategy.appendField(locator, this, "taxCertificationNumbers", buffer, theTaxCertificationNumbers);
        }
        {
            String theAccountMasterSourceTypeCd;
            theAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
            strategy.appendField(locator, this, "accountMasterSourceTypeCd", buffer, theAccountMasterSourceTypeCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountBaseTaxationType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AccountBaseTaxationType that = ((AccountBaseTaxationType) object);
        {
            TaxExemptionIndicatorsType lhsTaxExemptionIndicators;
            lhsTaxExemptionIndicators = this.getTaxExemptionIndicators();
            TaxExemptionIndicatorsType rhsTaxExemptionIndicators;
            rhsTaxExemptionIndicators = that.getTaxExemptionIndicators();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxExemptionIndicators", lhsTaxExemptionIndicators), LocatorUtils.property(thatLocator, "taxExemptionIndicators", rhsTaxExemptionIndicators), lhsTaxExemptionIndicators, rhsTaxExemptionIndicators)) {
                return false;
            }
        }
        {
            TaxCertificationNumberType lhsTaxCertificationNumbers;
            lhsTaxCertificationNumbers = this.getTaxCertificationNumbers();
            TaxCertificationNumberType rhsTaxCertificationNumbers;
            rhsTaxCertificationNumbers = that.getTaxCertificationNumbers();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxCertificationNumbers", lhsTaxCertificationNumbers), LocatorUtils.property(thatLocator, "taxCertificationNumbers", rhsTaxCertificationNumbers), lhsTaxCertificationNumbers, rhsTaxCertificationNumbers)) {
                return false;
            }
        }
        {
            String lhsAccountMasterSourceTypeCd;
            lhsAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
            String rhsAccountMasterSourceTypeCd;
            rhsAccountMasterSourceTypeCd = that.getAccountMasterSourceTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountMasterSourceTypeCd", lhsAccountMasterSourceTypeCd), LocatorUtils.property(thatLocator, "accountMasterSourceTypeCd", rhsAccountMasterSourceTypeCd), lhsAccountMasterSourceTypeCd, rhsAccountMasterSourceTypeCd)) {
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
