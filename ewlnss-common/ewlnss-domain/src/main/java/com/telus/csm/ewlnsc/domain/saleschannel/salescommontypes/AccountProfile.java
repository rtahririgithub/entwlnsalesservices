
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for AccountProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="account" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Account"/>
 *         &lt;element name="personalCreditInformation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PersonalCreditInformation" minOccurs="0"/>
 *         &lt;element name="businessCreditInformation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessCreditInformation" minOccurs="0"/>
 *         &lt;element name="contactPhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelecomContact" minOccurs="0"/>
 *         &lt;element name="businessPhoneNumber" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelecomContact" minOccurs="0"/>
 *         &lt;element name="contactName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" minOccurs="0"/>
 *         &lt;element name="emailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *         &lt;element name="bigWirelessAccountIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="wirelessSubscriberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Subscriber" maxOccurs="1000" minOccurs="0"/>
 *         &lt;element name="wirelineProductList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductSummary" maxOccurs="500" minOccurs="0"/>
 *         &lt;element name="starterSeatSummaryList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}StarterSeatSummary" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountProfile", propOrder = {
    "account",
    "personalCreditInformation",
    "businessCreditInformation",
    "contactPhoneNumber",
    "businessPhoneNumber",
    "contactName",
    "emailAddress",
    "bigWirelessAccountIndicator",
    "wirelessSubscriberList",
    "wirelineProductList",
    "starterSeatSummaryList"
})
public class AccountProfile
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Account account;
    protected PersonalCreditInformation personalCreditInformation;
    protected BusinessCreditInformation businessCreditInformation;
    protected TelecomContact contactPhoneNumber;
    protected TelecomContact businessPhoneNumber;
    protected Name contactName;
    protected ElectronicContact emailAddress;
    protected Boolean bigWirelessAccountIndicator;
    protected List<Subscriber> wirelessSubscriberList;
    protected List<WirelineProductSummary> wirelineProductList;
    protected List<StarterSeatSummary> starterSeatSummaryList;

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
     * Gets the value of the contactPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link TelecomContact }
     *     
     */
    public TelecomContact getContactPhoneNumber() {
        return contactPhoneNumber;
    }

    /**
     * Sets the value of the contactPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelecomContact }
     *     
     */
    public void setContactPhoneNumber(TelecomContact value) {
        this.contactPhoneNumber = value;
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
     * Gets the value of the contactName property.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getContactName() {
        return contactName;
    }

    /**
     * Sets the value of the contactName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setContactName(Name value) {
        this.contactName = value;
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
     * Gets the value of the bigWirelessAccountIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBigWirelessAccountIndicator() {
        return bigWirelessAccountIndicator;
    }

    /**
     * Sets the value of the bigWirelessAccountIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBigWirelessAccountIndicator(Boolean value) {
        this.bigWirelessAccountIndicator = value;
    }

    /**
     * Gets the value of the wirelessSubscriberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelessSubscriberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelessSubscriberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subscriber }
     * 
     * 
     */
    public List<Subscriber> getWirelessSubscriberList() {
        if (wirelessSubscriberList == null) {
            wirelessSubscriberList = new ArrayList<Subscriber>();
        }
        return this.wirelessSubscriberList;
    }

    /**
     * Gets the value of the wirelineProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelineProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelineProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineProductSummary }
     * 
     * 
     */
    public List<WirelineProductSummary> getWirelineProductList() {
        if (wirelineProductList == null) {
            wirelineProductList = new ArrayList<WirelineProductSummary>();
        }
        return this.wirelineProductList;
    }

    /**
     * Gets the value of the starterSeatSummaryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the starterSeatSummaryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStarterSeatSummaryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link StarterSeatSummary }
     * 
     * 
     */
    public List<StarterSeatSummary> getStarterSeatSummaryList() {
        if (starterSeatSummaryList == null) {
            starterSeatSummaryList = new ArrayList<StarterSeatSummary>();
        }
        return this.starterSeatSummaryList;
    }

    /**
     * Sets the value of the wirelessSubscriberList property.
     * 
     * @param wirelessSubscriberList
     *     allowed object is
     *     {@link Subscriber }
     *     
     */
    public void setWirelessSubscriberList(List<Subscriber> wirelessSubscriberList) {
        this.wirelessSubscriberList = wirelessSubscriberList;
    }

    /**
     * Sets the value of the wirelineProductList property.
     * 
     * @param wirelineProductList
     *     allowed object is
     *     {@link WirelineProductSummary }
     *     
     */
    public void setWirelineProductList(List<WirelineProductSummary> wirelineProductList) {
        this.wirelineProductList = wirelineProductList;
    }

    /**
     * Sets the value of the starterSeatSummaryList property.
     * 
     * @param starterSeatSummaryList
     *     allowed object is
     *     {@link StarterSeatSummary }
     *     
     */
    public void setStarterSeatSummaryList(List<StarterSeatSummary> starterSeatSummaryList) {
        this.starterSeatSummaryList = starterSeatSummaryList;
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
            TelecomContact theContactPhoneNumber;
            theContactPhoneNumber = this.getContactPhoneNumber();
            strategy.appendField(locator, this, "contactPhoneNumber", buffer, theContactPhoneNumber);
        }
        {
            TelecomContact theBusinessPhoneNumber;
            theBusinessPhoneNumber = this.getBusinessPhoneNumber();
            strategy.appendField(locator, this, "businessPhoneNumber", buffer, theBusinessPhoneNumber);
        }
        {
            Name theContactName;
            theContactName = this.getContactName();
            strategy.appendField(locator, this, "contactName", buffer, theContactName);
        }
        {
            ElectronicContact theEmailAddress;
            theEmailAddress = this.getEmailAddress();
            strategy.appendField(locator, this, "emailAddress", buffer, theEmailAddress);
        }
        {
            Boolean theBigWirelessAccountIndicator;
            theBigWirelessAccountIndicator = this.isBigWirelessAccountIndicator();
            strategy.appendField(locator, this, "bigWirelessAccountIndicator", buffer, theBigWirelessAccountIndicator);
        }
        {
            List<Subscriber> theWirelessSubscriberList;
            theWirelessSubscriberList = (((this.wirelessSubscriberList!= null)&&(!this.wirelessSubscriberList.isEmpty()))?this.getWirelessSubscriberList():null);
            strategy.appendField(locator, this, "wirelessSubscriberList", buffer, theWirelessSubscriberList);
        }
        {
            List<WirelineProductSummary> theWirelineProductList;
            theWirelineProductList = (((this.wirelineProductList!= null)&&(!this.wirelineProductList.isEmpty()))?this.getWirelineProductList():null);
            strategy.appendField(locator, this, "wirelineProductList", buffer, theWirelineProductList);
        }
        {
            List<StarterSeatSummary> theStarterSeatSummaryList;
            theStarterSeatSummaryList = (((this.starterSeatSummaryList!= null)&&(!this.starterSeatSummaryList.isEmpty()))?this.getStarterSeatSummaryList():null);
            strategy.appendField(locator, this, "starterSeatSummaryList", buffer, theStarterSeatSummaryList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountProfile)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountProfile that = ((AccountProfile) object);
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
            TelecomContact lhsContactPhoneNumber;
            lhsContactPhoneNumber = this.getContactPhoneNumber();
            TelecomContact rhsContactPhoneNumber;
            rhsContactPhoneNumber = that.getContactPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contactPhoneNumber", lhsContactPhoneNumber), LocatorUtils.property(thatLocator, "contactPhoneNumber", rhsContactPhoneNumber), lhsContactPhoneNumber, rhsContactPhoneNumber)) {
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
            Name lhsContactName;
            lhsContactName = this.getContactName();
            Name rhsContactName;
            rhsContactName = that.getContactName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contactName", lhsContactName), LocatorUtils.property(thatLocator, "contactName", rhsContactName), lhsContactName, rhsContactName)) {
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
            Boolean lhsBigWirelessAccountIndicator;
            lhsBigWirelessAccountIndicator = this.isBigWirelessAccountIndicator();
            Boolean rhsBigWirelessAccountIndicator;
            rhsBigWirelessAccountIndicator = that.isBigWirelessAccountIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bigWirelessAccountIndicator", lhsBigWirelessAccountIndicator), LocatorUtils.property(thatLocator, "bigWirelessAccountIndicator", rhsBigWirelessAccountIndicator), lhsBigWirelessAccountIndicator, rhsBigWirelessAccountIndicator)) {
                return false;
            }
        }
        {
            List<Subscriber> lhsWirelessSubscriberList;
            lhsWirelessSubscriberList = (((this.wirelessSubscriberList!= null)&&(!this.wirelessSubscriberList.isEmpty()))?this.getWirelessSubscriberList():null);
            List<Subscriber> rhsWirelessSubscriberList;
            rhsWirelessSubscriberList = (((that.wirelessSubscriberList!= null)&&(!that.wirelessSubscriberList.isEmpty()))?that.getWirelessSubscriberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessSubscriberList", lhsWirelessSubscriberList), LocatorUtils.property(thatLocator, "wirelessSubscriberList", rhsWirelessSubscriberList), lhsWirelessSubscriberList, rhsWirelessSubscriberList)) {
                return false;
            }
        }
        {
            List<WirelineProductSummary> lhsWirelineProductList;
            lhsWirelineProductList = (((this.wirelineProductList!= null)&&(!this.wirelineProductList.isEmpty()))?this.getWirelineProductList():null);
            List<WirelineProductSummary> rhsWirelineProductList;
            rhsWirelineProductList = (((that.wirelineProductList!= null)&&(!that.wirelineProductList.isEmpty()))?that.getWirelineProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineProductList", lhsWirelineProductList), LocatorUtils.property(thatLocator, "wirelineProductList", rhsWirelineProductList), lhsWirelineProductList, rhsWirelineProductList)) {
                return false;
            }
        }
        {
            List<StarterSeatSummary> lhsStarterSeatSummaryList;
            lhsStarterSeatSummaryList = (((this.starterSeatSummaryList!= null)&&(!this.starterSeatSummaryList.isEmpty()))?this.getStarterSeatSummaryList():null);
            List<StarterSeatSummary> rhsStarterSeatSummaryList;
            rhsStarterSeatSummaryList = (((that.starterSeatSummaryList!= null)&&(!that.starterSeatSummaryList.isEmpty()))?that.getStarterSeatSummaryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "starterSeatSummaryList", lhsStarterSeatSummaryList), LocatorUtils.property(thatLocator, "starterSeatSummaryList", rhsStarterSeatSummaryList), lhsStarterSeatSummaryList, rhsStarterSeatSummaryList)) {
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
