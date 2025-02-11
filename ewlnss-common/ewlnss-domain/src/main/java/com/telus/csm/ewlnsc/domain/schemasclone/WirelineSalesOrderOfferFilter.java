
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for WirelineSalesOrderOfferFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineSalesOrderOfferFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerContractTermCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bundleInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="npaNxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineSalesOrderOfferFilter", propOrder = {
    "accountTypeCode",
    "accountSubTypeCode",
    "offerContractTermCd",
    "bundleInd",
    "npaNxx"
})
public class WirelineSalesOrderOfferFilter
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String accountTypeCode;
    protected String accountSubTypeCode;
    protected String offerContractTermCd;
    protected Boolean bundleInd;
    protected String npaNxx;

    /**
     * Gets the value of the accountTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    /**
     * Sets the value of the accountTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountTypeCode(String value) {
        this.accountTypeCode = value;
    }

    /**
     * Gets the value of the accountSubTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSubTypeCode() {
        return accountSubTypeCode;
    }

    /**
     * Sets the value of the accountSubTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSubTypeCode(String value) {
        this.accountSubTypeCode = value;
    }

    /**
     * Gets the value of the offerContractTermCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferContractTermCd() {
        return offerContractTermCd;
    }

    /**
     * Sets the value of the offerContractTermCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferContractTermCd(String value) {
        this.offerContractTermCd = value;
    }

    /**
     * Gets the value of the bundleInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBundleInd() {
        return bundleInd;
    }

    /**
     * Sets the value of the bundleInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBundleInd(Boolean value) {
        this.bundleInd = value;
    }

    /**
     * Gets the value of the npaNxx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpaNxx() {
        return npaNxx;
    }

    /**
     * Sets the value of the npaNxx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpaNxx(String value) {
        this.npaNxx = value;
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
            String theAccountTypeCode;
            theAccountTypeCode = this.getAccountTypeCode();
            strategy.appendField(locator, this, "accountTypeCode", buffer, theAccountTypeCode);
        }
        {
            String theAccountSubTypeCode;
            theAccountSubTypeCode = this.getAccountSubTypeCode();
            strategy.appendField(locator, this, "accountSubTypeCode", buffer, theAccountSubTypeCode);
        }
        {
            String theOfferContractTermCd;
            theOfferContractTermCd = this.getOfferContractTermCd();
            strategy.appendField(locator, this, "offerContractTermCd", buffer, theOfferContractTermCd);
        }
        {
            Boolean theBundleInd;
            theBundleInd = this.isBundleInd();
            strategy.appendField(locator, this, "bundleInd", buffer, theBundleInd);
        }
        {
            String theNpaNxx;
            theNpaNxx = this.getNpaNxx();
            strategy.appendField(locator, this, "npaNxx", buffer, theNpaNxx);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineSalesOrderOfferFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineSalesOrderOfferFilter that = ((WirelineSalesOrderOfferFilter) object);
        {
            String lhsAccountTypeCode;
            lhsAccountTypeCode = this.getAccountTypeCode();
            String rhsAccountTypeCode;
            rhsAccountTypeCode = that.getAccountTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountTypeCode", lhsAccountTypeCode), LocatorUtils.property(thatLocator, "accountTypeCode", rhsAccountTypeCode), lhsAccountTypeCode, rhsAccountTypeCode)) {
                return false;
            }
        }
        {
            String lhsAccountSubTypeCode;
            lhsAccountSubTypeCode = this.getAccountSubTypeCode();
            String rhsAccountSubTypeCode;
            rhsAccountSubTypeCode = that.getAccountSubTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountSubTypeCode", lhsAccountSubTypeCode), LocatorUtils.property(thatLocator, "accountSubTypeCode", rhsAccountSubTypeCode), lhsAccountSubTypeCode, rhsAccountSubTypeCode)) {
                return false;
            }
        }
        {
            String lhsOfferContractTermCd;
            lhsOfferContractTermCd = this.getOfferContractTermCd();
            String rhsOfferContractTermCd;
            rhsOfferContractTermCd = that.getOfferContractTermCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerContractTermCd", lhsOfferContractTermCd), LocatorUtils.property(thatLocator, "offerContractTermCd", rhsOfferContractTermCd), lhsOfferContractTermCd, rhsOfferContractTermCd)) {
                return false;
            }
        }
        {
            Boolean lhsBundleInd;
            lhsBundleInd = this.isBundleInd();
            Boolean rhsBundleInd;
            rhsBundleInd = that.isBundleInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleInd", lhsBundleInd), LocatorUtils.property(thatLocator, "bundleInd", rhsBundleInd), lhsBundleInd, rhsBundleInd)) {
                return false;
            }
        }
        {
            String lhsNpaNxx;
            lhsNpaNxx = this.getNpaNxx();
            String rhsNpaNxx;
            rhsNpaNxx = that.getNpaNxx();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "npaNxx", lhsNpaNxx), LocatorUtils.property(thatLocator, "npaNxx", rhsNpaNxx), lhsNpaNxx, rhsNpaNxx)) {
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
