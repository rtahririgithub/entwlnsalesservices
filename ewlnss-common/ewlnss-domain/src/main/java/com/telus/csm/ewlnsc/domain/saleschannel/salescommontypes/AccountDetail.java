
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
 * <p>Java class for AccountDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="account" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Account"/>
 *         &lt;element name="personalCreditInformation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PersonalCreditInformation" minOccurs="0"/>
 *         &lt;element name="businessCreditInformation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessCreditInformation" minOccurs="0"/>
 *         &lt;element name="homePhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelecomContact" minOccurs="0"/>
 *         &lt;element name="emailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *         &lt;element name="businessPhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelecomContact" minOccurs="0"/>
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountDetail", propOrder = {
    "account",
    "personalCreditInformation",
    "businessCreditInformation",
    "homePhoneNumber",
    "emailAddress",
    "businessPhoneNumber",
    "pin"
})
public class AccountDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Account account;
    protected PersonalCreditInformation personalCreditInformation;
    protected BusinessCreditInformation businessCreditInformation;
    protected TelecomContact homePhoneNumber;
    protected ElectronicContact emailAddress;
    protected TelecomContact businessPhoneNumber;
    protected String pin;

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
     * Gets the value of the personalCreditInformation property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalCreditInformation }
     *     
     */
    public PersonalCreditInformation getPersonalCreditInformation() {
        return personalCreditInformation;
    }

    /**
     * Sets the value of the personalCreditInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalCreditInformation }
     *     
     */
    public void setPersonalCreditInformation(PersonalCreditInformation value) {
        this.personalCreditInformation = value;
    }

    /**
     * Gets the value of the businessCreditInformation property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessCreditInformation }
     *     
     */
    public BusinessCreditInformation getBusinessCreditInformation() {
        return businessCreditInformation;
    }

    /**
     * Sets the value of the businessCreditInformation property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessCreditInformation }
     *     
     */
    public void setBusinessCreditInformation(BusinessCreditInformation value) {
        this.businessCreditInformation = value;
    }

    /**
     * Gets the value of the homePhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TelecomContact }
     *     
     */
    public TelecomContact getHomePhoneNumber() {
        return homePhoneNumber;
    }

    /**
     * Sets the value of the homePhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomContact }
     *     
     */
    public void setHomePhoneNumber(TelecomContact value) {
        this.homePhoneNumber = value;
    }

    /**
     * Gets the value of the emailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ElectronicContact }
     *     
     */
    public ElectronicContact getEmailAddress() {
        return emailAddress;
    }

    /**
     * Sets the value of the emailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectronicContact }
     *     
     */
    public void setEmailAddress(ElectronicContact value) {
        this.emailAddress = value;
    }

    /**
     * Gets the value of the businessPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TelecomContact }
     *     
     */
    public TelecomContact getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    /**
     * Sets the value of the businessPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomContact }
     *     
     */
    public void setBusinessPhoneNumber(TelecomContact value) {
        this.businessPhoneNumber = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin(String value) {
        this.pin = value;
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
            PersonalCreditInformation thePersonalCreditInformation;
            thePersonalCreditInformation = this.getPersonalCreditInformation();
            strategy.appendField(locator, this, "personalCreditInformation", buffer, thePersonalCreditInformation);
        }
        {
            BusinessCreditInformation theBusinessCreditInformation;
            theBusinessCreditInformation = this.getBusinessCreditInformation();
            strategy.appendField(locator, this, "businessCreditInformation", buffer, theBusinessCreditInformation);
        }
        {
            TelecomContact theHomePhoneNumber;
            theHomePhoneNumber = this.getHomePhoneNumber();
            strategy.appendField(locator, this, "homePhoneNumber", buffer, theHomePhoneNumber);
        }
        {
            ElectronicContact theEmailAddress;
            theEmailAddress = this.getEmailAddress();
            strategy.appendField(locator, this, "emailAddress", buffer, theEmailAddress);
        }
        {
            TelecomContact theBusinessPhoneNumber;
            theBusinessPhoneNumber = this.getBusinessPhoneNumber();
            strategy.appendField(locator, this, "businessPhoneNumber", buffer, theBusinessPhoneNumber);
        }
        {
            String thePin;
            thePin = this.getPin();
            strategy.appendField(locator, this, "pin", buffer, thePin);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountDetail that = ((AccountDetail) object);
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
            PersonalCreditInformation lhsPersonalCreditInformation;
            lhsPersonalCreditInformation = this.getPersonalCreditInformation();
            PersonalCreditInformation rhsPersonalCreditInformation;
            rhsPersonalCreditInformation = that.getPersonalCreditInformation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "personalCreditInformation", lhsPersonalCreditInformation), LocatorUtils.property(thatLocator, "personalCreditInformation", rhsPersonalCreditInformation), lhsPersonalCreditInformation, rhsPersonalCreditInformation)) {
                return false;
            }
        }
        {
            BusinessCreditInformation lhsBusinessCreditInformation;
            lhsBusinessCreditInformation = this.getBusinessCreditInformation();
            BusinessCreditInformation rhsBusinessCreditInformation;
            rhsBusinessCreditInformation = that.getBusinessCreditInformation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessCreditInformation", lhsBusinessCreditInformation), LocatorUtils.property(thatLocator, "businessCreditInformation", rhsBusinessCreditInformation), lhsBusinessCreditInformation, rhsBusinessCreditInformation)) {
                return false;
            }
        }
        {
            TelecomContact lhsHomePhoneNumber;
            lhsHomePhoneNumber = this.getHomePhoneNumber();
            TelecomContact rhsHomePhoneNumber;
            rhsHomePhoneNumber = that.getHomePhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "homePhoneNumber", lhsHomePhoneNumber), LocatorUtils.property(thatLocator, "homePhoneNumber", rhsHomePhoneNumber), lhsHomePhoneNumber, rhsHomePhoneNumber)) {
                return false;
            }
        }
        {
            ElectronicContact lhsEmailAddress;
            lhsEmailAddress = this.getEmailAddress();
            ElectronicContact rhsEmailAddress;
            rhsEmailAddress = that.getEmailAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "emailAddress", lhsEmailAddress), LocatorUtils.property(thatLocator, "emailAddress", rhsEmailAddress), lhsEmailAddress, rhsEmailAddress)) {
                return false;
            }
        }
        {
            TelecomContact lhsBusinessPhoneNumber;
            lhsBusinessPhoneNumber = this.getBusinessPhoneNumber();
            TelecomContact rhsBusinessPhoneNumber;
            rhsBusinessPhoneNumber = that.getBusinessPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessPhoneNumber", lhsBusinessPhoneNumber), LocatorUtils.property(thatLocator, "businessPhoneNumber", rhsBusinessPhoneNumber), lhsBusinessPhoneNumber, rhsBusinessPhoneNumber)) {
                return false;
            }
        }
        {
            String lhsPin;
            lhsPin = this.getPin();
            String rhsPin;
            rhsPin = that.getPin();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pin", lhsPin), LocatorUtils.property(thatLocator, "pin", rhsPin), lhsPin, rhsPin)) {
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
