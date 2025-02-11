
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
 * <p>Java class for AccountSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="account" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Account"/>
 *         &lt;element name="creditInformation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccountEligibilityInformation" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountSummary", propOrder = {
    "account",
    "creditInformation"
})
public class AccountSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Account account;
    protected AccountEligibilityInformation creditInformation;

    /**
     * Gets the value of the account property.
     * 
     * @return
     *     possible object is
     *     {@link Account }
     *     
     */
    public Account getAccount() {
        return account;
    }

    /**
     * Sets the value of the account property.
     * 
     * @param value
     *     allowed object is
     *     {@link Account }
     *     
     */
    public void setAccount(Account value) {
        this.account = value;
    }

    /**
     * Gets the value of the creditInformation property.
     * 
     * @return
     *     possible object is
     *     {@link AccountEligibilityInformation }
     *     
     */
    public AccountEligibilityInformation getCreditInformation() {
        return creditInformation;
    }

    /**
     * Sets the value of the creditInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountEligibilityInformation }
     *     
     */
    public void setCreditInformation(AccountEligibilityInformation value) {
        this.creditInformation = value;
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
            Account theAccount;
            theAccount = this.getAccount();
            strategy.appendField(locator, this, "account", buffer, theAccount);
        }
        {
            AccountEligibilityInformation theCreditInformation;
            theCreditInformation = this.getCreditInformation();
            strategy.appendField(locator, this, "creditInformation", buffer, theCreditInformation);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountSummary that = ((AccountSummary) object);
        {
            Account lhsAccount;
            lhsAccount = this.getAccount();
            Account rhsAccount;
            rhsAccount = that.getAccount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "account", lhsAccount), LocatorUtils.property(thatLocator, "account", rhsAccount), lhsAccount, rhsAccount)) {
                return false;
            }
        }
        {
            AccountEligibilityInformation lhsCreditInformation;
            lhsCreditInformation = this.getCreditInformation();
            AccountEligibilityInformation rhsCreditInformation;
            rhsCreditInformation = that.getCreditInformation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditInformation", lhsCreditInformation), LocatorUtils.property(thatLocator, "creditInformation", rhsCreditInformation), lhsCreditInformation, rhsCreditInformation)) {
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
