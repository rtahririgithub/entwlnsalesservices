
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Subscriber complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Subscriber">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phoneNumberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PhoneNumberType" maxOccurs="3"/>
 *         &lt;element name="subscriberId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="subscriptionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name"/>
 *         &lt;element name="subscriberStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contract" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Contract" minOccurs="0"/>
 *         &lt;element name="equipment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}EquipmentSerialNumberSet" minOccurs="0"/>
 *         &lt;element name="equipmentWarranty" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}EquipmentWarrantySummary" minOccurs="0"/>
 *         &lt;element name="subscriberProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="revenueBandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="userValueRating" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rewardAccountList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardAccount" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="rewardCommitmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardCommitment" maxOccurs="5" minOccurs="0"/>
 *         &lt;element name="earlyDeviceUpgradeFee" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}EarlyDeviceUpgradeFee" minOccurs="0"/>
 *         &lt;element name="existingOffer" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="offerDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
 *                   &lt;element name="offerDetailedDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
 *                   &lt;element name="offerTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="termInMonths" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *                   &lt;element name="tabTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tabDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="subscriberPreference" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberPreference" minOccurs="0"/>
 *         &lt;element name="businessUser" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberBusinessUserType" minOccurs="0"/>
 *         &lt;element name="emailAddressList" maxOccurs="10" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="emailTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="emailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Subscriber", propOrder = {
    "phoneNumberList",
    "subscriberId",
    "subscriptionId",
    "name",
    "subscriberStatusCode",
    "subscriberRoleCode",
    "contract",
    "equipment",
    "equipmentWarranty",
    "subscriberProvinceCode",
    "serviceStartDate",
    "revenueBandCode",
    "address",
    "brandCode",
    "productType",
    "userValueRating",
    "rewardAccountList",
    "rewardCommitmentList",
    "earlyDeviceUpgradeFee",
    "existingOffer",
    "subscriberPreference",
    "businessUser",
    "emailAddressList"
})
public class Subscriber
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<PhoneNumberType> phoneNumberList;
    @XmlElement(required = true)
    protected String subscriberId;
    protected String subscriptionId;
    @XmlElement(required = true)
    protected Name name;
    protected String subscriberStatusCode;
    protected String subscriberRoleCode;
    protected Contract contract;
    protected EquipmentSerialNumberSet equipment;
    protected EquipmentWarrantySummary equipmentWarranty;
    protected String subscriberProvinceCode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date serviceStartDate;
    protected String revenueBandCode;
    protected Address address;
    protected String brandCode;
    protected String productType;
    protected String userValueRating;
    protected List<RewardAccount> rewardAccountList;
    protected List<RewardCommitment> rewardCommitmentList;
    protected EarlyDeviceUpgradeFee earlyDeviceUpgradeFee;
    protected Subscriber.ExistingOffer existingOffer;
    protected SubscriberPreference subscriberPreference;
    protected SubscriberBusinessUserType businessUser;
    protected List<Subscriber.EmailAddressList> emailAddressList;

    /**
     * Gets the value of the phoneNumberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the phoneNumberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPhoneNumberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNumberType }
     * 
     * 
     */
    public List<PhoneNumberType> getPhoneNumberList() {
        if (phoneNumberList == null) {
            phoneNumberList = new ArrayList<PhoneNumberType>();
        }
        return this.phoneNumberList;
    }

    /**
     * Gets the value of the subscriberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * Sets the value of the subscriberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberId(String value) {
        this.subscriberId = value;
    }

    /**
     * Gets the value of the subscriptionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the value of the subscriptionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionId(String value) {
        this.subscriptionId = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setName(Name value) {
        this.name = value;
    }

    /**
     * Gets the value of the subscriberStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberStatusCode() {
        return subscriberStatusCode;
    }

    /**
     * Sets the value of the subscriberStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberStatusCode(String value) {
        this.subscriberStatusCode = value;
    }

    /**
     * Gets the value of the subscriberRoleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberRoleCode() {
        return subscriberRoleCode;
    }

    /**
     * Sets the value of the subscriberRoleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberRoleCode(String value) {
        this.subscriberRoleCode = value;
    }

    /**
     * Gets the value of the contract property.
     * 
     * @return
     *     possible object is
     *     {@link Contract }
     *     
     */
    public Contract getContract() {
        return contract;
    }

    /**
     * Sets the value of the contract property.
     * 
     * @param value
     *     allowed object is
     *     {@link Contract }
     *     
     */
    public void setContract(Contract value) {
        this.contract = value;
    }

    /**
     * Gets the value of the equipment property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentSerialNumberSet }
     *     
     */
    public EquipmentSerialNumberSet getEquipment() {
        return equipment;
    }

    /**
     * Sets the value of the equipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentSerialNumberSet }
     *     
     */
    public void setEquipment(EquipmentSerialNumberSet value) {
        this.equipment = value;
    }

    /**
     * Gets the value of the equipmentWarranty property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentWarrantySummary }
     *     
     */
    public EquipmentWarrantySummary getEquipmentWarranty() {
        return equipmentWarranty;
    }

    /**
     * Sets the value of the equipmentWarranty property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentWarrantySummary }
     *     
     */
    public void setEquipmentWarranty(EquipmentWarrantySummary value) {
        this.equipmentWarranty = value;
    }

    /**
     * Gets the value of the subscriberProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberProvinceCode() {
        return subscriberProvinceCode;
    }

    /**
     * Sets the value of the subscriberProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberProvinceCode(String value) {
        this.subscriberProvinceCode = value;
    }

    /**
     * Gets the value of the serviceStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getServiceStartDate() {
        return serviceStartDate;
    }

    /**
     * Sets the value of the serviceStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceStartDate(Date value) {
        this.serviceStartDate = value;
    }

    /**
     * Gets the value of the revenueBandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRevenueBandCode() {
        return revenueBandCode;
    }

    /**
     * Sets the value of the revenueBandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRevenueBandCode(String value) {
        this.revenueBandCode = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
    }

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the userValueRating property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserValueRating() {
        return userValueRating;
    }

    /**
     * Sets the value of the userValueRating property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserValueRating(String value) {
        this.userValueRating = value;
    }

    /**
     * Gets the value of the rewardAccountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardAccountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardAccountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RewardAccount }
     * 
     * 
     */
    public List<RewardAccount> getRewardAccountList() {
        if (rewardAccountList == null) {
            rewardAccountList = new ArrayList<RewardAccount>();
        }
        return this.rewardAccountList;
    }

    /**
     * Gets the value of the rewardCommitmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardCommitmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardCommitmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RewardCommitment }
     * 
     * 
     */
    public List<RewardCommitment> getRewardCommitmentList() {
        if (rewardCommitmentList == null) {
            rewardCommitmentList = new ArrayList<RewardCommitment>();
        }
        return this.rewardCommitmentList;
    }

    /**
     * Gets the value of the earlyDeviceUpgradeFee property.
     * 
     * @return
     *     possible object is
     *     {@link EarlyDeviceUpgradeFee }
     *     
     */
    public EarlyDeviceUpgradeFee getEarlyDeviceUpgradeFee() {
        return earlyDeviceUpgradeFee;
    }

    /**
     * Sets the value of the earlyDeviceUpgradeFee property.
     * 
     * @param value
     *     allowed object is
     *     {@link EarlyDeviceUpgradeFee }
     *     
     */
    public void setEarlyDeviceUpgradeFee(EarlyDeviceUpgradeFee value) {
        this.earlyDeviceUpgradeFee = value;
    }

    /**
     * Gets the value of the existingOffer property.
     * 
     * @return
     *     possible object is
     *     {@link Subscriber.ExistingOffer }
     *     
     */
    public Subscriber.ExistingOffer getExistingOffer() {
        return existingOffer;
    }

    /**
     * Sets the value of the existingOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Subscriber.ExistingOffer }
     *     
     */
    public void setExistingOffer(Subscriber.ExistingOffer value) {
        this.existingOffer = value;
    }

    /**
     * Gets the value of the subscriberPreference property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberPreference }
     *     
     */
    public SubscriberPreference getSubscriberPreference() {
        return subscriberPreference;
    }

    /**
     * Sets the value of the subscriberPreference property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberPreference }
     *     
     */
    public void setSubscriberPreference(SubscriberPreference value) {
        this.subscriberPreference = value;
    }

    /**
     * Gets the value of the businessUser property.
     * 
     * @return
     *     possible object is
     *     {@link SubscriberBusinessUserType }
     *     
     */
    public SubscriberBusinessUserType getBusinessUser() {
        return businessUser;
    }

    /**
     * Sets the value of the businessUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubscriberBusinessUserType }
     *     
     */
    public void setBusinessUser(SubscriberBusinessUserType value) {
        this.businessUser = value;
    }

    /**
     * Gets the value of the emailAddressList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the emailAddressList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmailAddressList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Subscriber.EmailAddressList }
     * 
     * 
     */
    public List<Subscriber.EmailAddressList> getEmailAddressList() {
        if (emailAddressList == null) {
            emailAddressList = new ArrayList<Subscriber.EmailAddressList>();
        }
        return this.emailAddressList;
    }

    /**
     * Sets the value of the phoneNumberList property.
     * 
     * @param phoneNumberList
     *     allowed object is
     *     {@link PhoneNumberType }
     *     
     */
    public void setPhoneNumberList(List<PhoneNumberType> phoneNumberList) {
        this.phoneNumberList = phoneNumberList;
    }

    /**
     * Sets the value of the rewardAccountList property.
     * 
     * @param rewardAccountList
     *     allowed object is
     *     {@link RewardAccount }
     *     
     */
    public void setRewardAccountList(List<RewardAccount> rewardAccountList) {
        this.rewardAccountList = rewardAccountList;
    }

    /**
     * Sets the value of the rewardCommitmentList property.
     * 
     * @param rewardCommitmentList
     *     allowed object is
     *     {@link RewardCommitment }
     *     
     */
    public void setRewardCommitmentList(List<RewardCommitment> rewardCommitmentList) {
        this.rewardCommitmentList = rewardCommitmentList;
    }

    /**
     * Sets the value of the emailAddressList property.
     * 
     * @param emailAddressList
     *     allowed object is
     *     {@link Subscriber.EmailAddressList }
     *     
     */
    public void setEmailAddressList(List<Subscriber.EmailAddressList> emailAddressList) {
        this.emailAddressList = emailAddressList;
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
            List<PhoneNumberType> thePhoneNumberList;
            thePhoneNumberList = (((this.phoneNumberList!= null)&&(!this.phoneNumberList.isEmpty()))?this.getPhoneNumberList():null);
            strategy.appendField(locator, this, "phoneNumberList", buffer, thePhoneNumberList);
        }
        {
            String theSubscriberId;
            theSubscriberId = this.getSubscriberId();
            strategy.appendField(locator, this, "subscriberId", buffer, theSubscriberId);
        }
        {
            String theSubscriptionId;
            theSubscriptionId = this.getSubscriptionId();
            strategy.appendField(locator, this, "subscriptionId", buffer, theSubscriptionId);
        }
        {
            Name theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            String theSubscriberStatusCode;
            theSubscriberStatusCode = this.getSubscriberStatusCode();
            strategy.appendField(locator, this, "subscriberStatusCode", buffer, theSubscriberStatusCode);
        }
        {
            String theSubscriberRoleCode;
            theSubscriberRoleCode = this.getSubscriberRoleCode();
            strategy.appendField(locator, this, "subscriberRoleCode", buffer, theSubscriberRoleCode);
        }
        {
            Contract theContract;
            theContract = this.getContract();
            strategy.appendField(locator, this, "contract", buffer, theContract);
        }
        {
            EquipmentSerialNumberSet theEquipment;
            theEquipment = this.getEquipment();
            strategy.appendField(locator, this, "equipment", buffer, theEquipment);
        }
        {
            EquipmentWarrantySummary theEquipmentWarranty;
            theEquipmentWarranty = this.getEquipmentWarranty();
            strategy.appendField(locator, this, "equipmentWarranty", buffer, theEquipmentWarranty);
        }
        {
            String theSubscriberProvinceCode;
            theSubscriberProvinceCode = this.getSubscriberProvinceCode();
            strategy.appendField(locator, this, "subscriberProvinceCode", buffer, theSubscriberProvinceCode);
        }
        {
            Date theServiceStartDate;
            theServiceStartDate = this.getServiceStartDate();
            strategy.appendField(locator, this, "serviceStartDate", buffer, theServiceStartDate);
        }
        {
            String theRevenueBandCode;
            theRevenueBandCode = this.getRevenueBandCode();
            strategy.appendField(locator, this, "revenueBandCode", buffer, theRevenueBandCode);
        }
        {
            Address theAddress;
            theAddress = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theAddress);
        }
        {
            String theBrandCode;
            theBrandCode = this.getBrandCode();
            strategy.appendField(locator, this, "brandCode", buffer, theBrandCode);
        }
        {
            String theProductType;
            theProductType = this.getProductType();
            strategy.appendField(locator, this, "productType", buffer, theProductType);
        }
        {
            String theUserValueRating;
            theUserValueRating = this.getUserValueRating();
            strategy.appendField(locator, this, "userValueRating", buffer, theUserValueRating);
        }
        {
            List<RewardAccount> theRewardAccountList;
            theRewardAccountList = (((this.rewardAccountList!= null)&&(!this.rewardAccountList.isEmpty()))?this.getRewardAccountList():null);
            strategy.appendField(locator, this, "rewardAccountList", buffer, theRewardAccountList);
        }
        {
            List<RewardCommitment> theRewardCommitmentList;
            theRewardCommitmentList = (((this.rewardCommitmentList!= null)&&(!this.rewardCommitmentList.isEmpty()))?this.getRewardCommitmentList():null);
            strategy.appendField(locator, this, "rewardCommitmentList", buffer, theRewardCommitmentList);
        }
        {
            EarlyDeviceUpgradeFee theEarlyDeviceUpgradeFee;
            theEarlyDeviceUpgradeFee = this.getEarlyDeviceUpgradeFee();
            strategy.appendField(locator, this, "earlyDeviceUpgradeFee", buffer, theEarlyDeviceUpgradeFee);
        }
        {
            Subscriber.ExistingOffer theExistingOffer;
            theExistingOffer = this.getExistingOffer();
            strategy.appendField(locator, this, "existingOffer", buffer, theExistingOffer);
        }
        {
            SubscriberPreference theSubscriberPreference;
            theSubscriberPreference = this.getSubscriberPreference();
            strategy.appendField(locator, this, "subscriberPreference", buffer, theSubscriberPreference);
        }
        {
            SubscriberBusinessUserType theBusinessUser;
            theBusinessUser = this.getBusinessUser();
            strategy.appendField(locator, this, "businessUser", buffer, theBusinessUser);
        }
        {
            List<Subscriber.EmailAddressList> theEmailAddressList;
            theEmailAddressList = (((this.emailAddressList!= null)&&(!this.emailAddressList.isEmpty()))?this.getEmailAddressList():null);
            strategy.appendField(locator, this, "emailAddressList", buffer, theEmailAddressList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Subscriber)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Subscriber that = ((Subscriber) object);
        {
            List<PhoneNumberType> lhsPhoneNumberList;
            lhsPhoneNumberList = (((this.phoneNumberList!= null)&&(!this.phoneNumberList.isEmpty()))?this.getPhoneNumberList():null);
            List<PhoneNumberType> rhsPhoneNumberList;
            rhsPhoneNumberList = (((that.phoneNumberList!= null)&&(!that.phoneNumberList.isEmpty()))?that.getPhoneNumberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumberList", lhsPhoneNumberList), LocatorUtils.property(thatLocator, "phoneNumberList", rhsPhoneNumberList), lhsPhoneNumberList, rhsPhoneNumberList)) {
                return false;
            }
        }
        {
            String lhsSubscriberId;
            lhsSubscriberId = this.getSubscriberId();
            String rhsSubscriberId;
            rhsSubscriberId = that.getSubscriberId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberId", lhsSubscriberId), LocatorUtils.property(thatLocator, "subscriberId", rhsSubscriberId), lhsSubscriberId, rhsSubscriberId)) {
                return false;
            }
        }
        {
            String lhsSubscriptionId;
            lhsSubscriptionId = this.getSubscriptionId();
            String rhsSubscriptionId;
            rhsSubscriptionId = that.getSubscriptionId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriptionId", lhsSubscriptionId), LocatorUtils.property(thatLocator, "subscriptionId", rhsSubscriptionId), lhsSubscriptionId, rhsSubscriptionId)) {
                return false;
            }
        }
        {
            Name lhsName;
            lhsName = this.getName();
            Name rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            String lhsSubscriberStatusCode;
            lhsSubscriberStatusCode = this.getSubscriberStatusCode();
            String rhsSubscriberStatusCode;
            rhsSubscriberStatusCode = that.getSubscriberStatusCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberStatusCode", lhsSubscriberStatusCode), LocatorUtils.property(thatLocator, "subscriberStatusCode", rhsSubscriberStatusCode), lhsSubscriberStatusCode, rhsSubscriberStatusCode)) {
                return false;
            }
        }
        {
            String lhsSubscriberRoleCode;
            lhsSubscriberRoleCode = this.getSubscriberRoleCode();
            String rhsSubscriberRoleCode;
            rhsSubscriberRoleCode = that.getSubscriberRoleCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberRoleCode", lhsSubscriberRoleCode), LocatorUtils.property(thatLocator, "subscriberRoleCode", rhsSubscriberRoleCode), lhsSubscriberRoleCode, rhsSubscriberRoleCode)) {
                return false;
            }
        }
        {
            Contract lhsContract;
            lhsContract = this.getContract();
            Contract rhsContract;
            rhsContract = that.getContract();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contract", lhsContract), LocatorUtils.property(thatLocator, "contract", rhsContract), lhsContract, rhsContract)) {
                return false;
            }
        }
        {
            EquipmentSerialNumberSet lhsEquipment;
            lhsEquipment = this.getEquipment();
            EquipmentSerialNumberSet rhsEquipment;
            rhsEquipment = that.getEquipment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipment", lhsEquipment), LocatorUtils.property(thatLocator, "equipment", rhsEquipment), lhsEquipment, rhsEquipment)) {
                return false;
            }
        }
        {
            EquipmentWarrantySummary lhsEquipmentWarranty;
            lhsEquipmentWarranty = this.getEquipmentWarranty();
            EquipmentWarrantySummary rhsEquipmentWarranty;
            rhsEquipmentWarranty = that.getEquipmentWarranty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentWarranty", lhsEquipmentWarranty), LocatorUtils.property(thatLocator, "equipmentWarranty", rhsEquipmentWarranty), lhsEquipmentWarranty, rhsEquipmentWarranty)) {
                return false;
            }
        }
        {
            String lhsSubscriberProvinceCode;
            lhsSubscriberProvinceCode = this.getSubscriberProvinceCode();
            String rhsSubscriberProvinceCode;
            rhsSubscriberProvinceCode = that.getSubscriberProvinceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberProvinceCode", lhsSubscriberProvinceCode), LocatorUtils.property(thatLocator, "subscriberProvinceCode", rhsSubscriberProvinceCode), lhsSubscriberProvinceCode, rhsSubscriberProvinceCode)) {
                return false;
            }
        }
        {
            Date lhsServiceStartDate;
            lhsServiceStartDate = this.getServiceStartDate();
            Date rhsServiceStartDate;
            rhsServiceStartDate = that.getServiceStartDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceStartDate", lhsServiceStartDate), LocatorUtils.property(thatLocator, "serviceStartDate", rhsServiceStartDate), lhsServiceStartDate, rhsServiceStartDate)) {
                return false;
            }
        }
        {
            String lhsRevenueBandCode;
            lhsRevenueBandCode = this.getRevenueBandCode();
            String rhsRevenueBandCode;
            rhsRevenueBandCode = that.getRevenueBandCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "revenueBandCode", lhsRevenueBandCode), LocatorUtils.property(thatLocator, "revenueBandCode", rhsRevenueBandCode), lhsRevenueBandCode, rhsRevenueBandCode)) {
                return false;
            }
        }
        {
            Address lhsAddress;
            lhsAddress = this.getAddress();
            Address rhsAddress;
            rhsAddress = that.getAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "address", lhsAddress), LocatorUtils.property(thatLocator, "address", rhsAddress), lhsAddress, rhsAddress)) {
                return false;
            }
        }
        {
            String lhsBrandCode;
            lhsBrandCode = this.getBrandCode();
            String rhsBrandCode;
            rhsBrandCode = that.getBrandCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "brandCode", lhsBrandCode), LocatorUtils.property(thatLocator, "brandCode", rhsBrandCode), lhsBrandCode, rhsBrandCode)) {
                return false;
            }
        }
        {
            String lhsProductType;
            lhsProductType = this.getProductType();
            String rhsProductType;
            rhsProductType = that.getProductType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productType", lhsProductType), LocatorUtils.property(thatLocator, "productType", rhsProductType), lhsProductType, rhsProductType)) {
                return false;
            }
        }
        {
            String lhsUserValueRating;
            lhsUserValueRating = this.getUserValueRating();
            String rhsUserValueRating;
            rhsUserValueRating = that.getUserValueRating();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userValueRating", lhsUserValueRating), LocatorUtils.property(thatLocator, "userValueRating", rhsUserValueRating), lhsUserValueRating, rhsUserValueRating)) {
                return false;
            }
        }
        {
            List<RewardAccount> lhsRewardAccountList;
            lhsRewardAccountList = (((this.rewardAccountList!= null)&&(!this.rewardAccountList.isEmpty()))?this.getRewardAccountList():null);
            List<RewardAccount> rhsRewardAccountList;
            rhsRewardAccountList = (((that.rewardAccountList!= null)&&(!that.rewardAccountList.isEmpty()))?that.getRewardAccountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardAccountList", lhsRewardAccountList), LocatorUtils.property(thatLocator, "rewardAccountList", rhsRewardAccountList), lhsRewardAccountList, rhsRewardAccountList)) {
                return false;
            }
        }
        {
            List<RewardCommitment> lhsRewardCommitmentList;
            lhsRewardCommitmentList = (((this.rewardCommitmentList!= null)&&(!this.rewardCommitmentList.isEmpty()))?this.getRewardCommitmentList():null);
            List<RewardCommitment> rhsRewardCommitmentList;
            rhsRewardCommitmentList = (((that.rewardCommitmentList!= null)&&(!that.rewardCommitmentList.isEmpty()))?that.getRewardCommitmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardCommitmentList", lhsRewardCommitmentList), LocatorUtils.property(thatLocator, "rewardCommitmentList", rhsRewardCommitmentList), lhsRewardCommitmentList, rhsRewardCommitmentList)) {
                return false;
            }
        }
        {
            EarlyDeviceUpgradeFee lhsEarlyDeviceUpgradeFee;
            lhsEarlyDeviceUpgradeFee = this.getEarlyDeviceUpgradeFee();
            EarlyDeviceUpgradeFee rhsEarlyDeviceUpgradeFee;
            rhsEarlyDeviceUpgradeFee = that.getEarlyDeviceUpgradeFee();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "earlyDeviceUpgradeFee", lhsEarlyDeviceUpgradeFee), LocatorUtils.property(thatLocator, "earlyDeviceUpgradeFee", rhsEarlyDeviceUpgradeFee), lhsEarlyDeviceUpgradeFee, rhsEarlyDeviceUpgradeFee)) {
                return false;
            }
        }
        {
            Subscriber.ExistingOffer lhsExistingOffer;
            lhsExistingOffer = this.getExistingOffer();
            Subscriber.ExistingOffer rhsExistingOffer;
            rhsExistingOffer = that.getExistingOffer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "existingOffer", lhsExistingOffer), LocatorUtils.property(thatLocator, "existingOffer", rhsExistingOffer), lhsExistingOffer, rhsExistingOffer)) {
                return false;
            }
        }
        {
            SubscriberPreference lhsSubscriberPreference;
            lhsSubscriberPreference = this.getSubscriberPreference();
            SubscriberPreference rhsSubscriberPreference;
            rhsSubscriberPreference = that.getSubscriberPreference();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberPreference", lhsSubscriberPreference), LocatorUtils.property(thatLocator, "subscriberPreference", rhsSubscriberPreference), lhsSubscriberPreference, rhsSubscriberPreference)) {
                return false;
            }
        }
        {
            SubscriberBusinessUserType lhsBusinessUser;
            lhsBusinessUser = this.getBusinessUser();
            SubscriberBusinessUserType rhsBusinessUser;
            rhsBusinessUser = that.getBusinessUser();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessUser", lhsBusinessUser), LocatorUtils.property(thatLocator, "businessUser", rhsBusinessUser), lhsBusinessUser, rhsBusinessUser)) {
                return false;
            }
        }
        {
            List<Subscriber.EmailAddressList> lhsEmailAddressList;
            lhsEmailAddressList = (((this.emailAddressList!= null)&&(!this.emailAddressList.isEmpty()))?this.getEmailAddressList():null);
            List<Subscriber.EmailAddressList> rhsEmailAddressList;
            rhsEmailAddressList = (((that.emailAddressList!= null)&&(!that.emailAddressList.isEmpty()))?that.getEmailAddressList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "emailAddressList", lhsEmailAddressList), LocatorUtils.property(thatLocator, "emailAddressList", rhsEmailAddressList), lhsEmailAddressList, rhsEmailAddressList)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="emailTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="emailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "emailTypeCode",
        "emailAddress"
    })
    public static class EmailAddressList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String emailTypeCode;
        @XmlElement(required = true)
        protected ElectronicContact emailAddress;

        /**
         * Gets the value of the emailTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEmailTypeCode() {
            return emailTypeCode;
        }

        /**
         * Sets the value of the emailTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEmailTypeCode(String value) {
            this.emailTypeCode = value;
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
                String theEmailTypeCode;
                theEmailTypeCode = this.getEmailTypeCode();
                strategy.appendField(locator, this, "emailTypeCode", buffer, theEmailTypeCode);
            }
            {
                ElectronicContact theEmailAddress;
                theEmailAddress = this.getEmailAddress();
                strategy.appendField(locator, this, "emailAddress", buffer, theEmailAddress);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Subscriber.EmailAddressList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Subscriber.EmailAddressList that = ((Subscriber.EmailAddressList) object);
            {
                String lhsEmailTypeCode;
                lhsEmailTypeCode = this.getEmailTypeCode();
                String rhsEmailTypeCode;
                rhsEmailTypeCode = that.getEmailTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "emailTypeCode", lhsEmailTypeCode), LocatorUtils.property(thatLocator, "emailTypeCode", rhsEmailTypeCode), lhsEmailTypeCode, rhsEmailTypeCode)) {
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
            return true;
        }

        public boolean equals(Object object) {
            final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
            return equals(null, null, object, strategy);
        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="offerDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
     *         &lt;element name="offerDetailedDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
     *         &lt;element name="offerTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="termInMonths" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
     *         &lt;element name="tabTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tabDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "offerId",
        "offerDescriptionTextList",
        "offerDetailedDescriptionTextList",
        "offerTypeCode",
        "termInMonths",
        "tabTypeCode",
        "tabDescriptionTextList"
    })
    public static class ExistingOffer
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String offerId;
        protected MultilingualCodeDescTextList offerDescriptionTextList;
        protected MultilingualCodeDescTextList offerDetailedDescriptionTextList;
        protected String offerTypeCode;
        protected BigInteger termInMonths;
        protected String tabTypeCode;
        protected MultilingualCodeDescTextList tabDescriptionTextList;

        /**
         * Gets the value of the offerId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOfferId() {
            return offerId;
        }

        /**
         * Sets the value of the offerId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOfferId(String value) {
            this.offerId = value;
        }

        /**
         * Gets the value of the offerDescriptionTextList property.
         * 
         * @return
         *     possible object is
         *     {@link MultilingualCodeDescTextList }
         *     
         */
        public MultilingualCodeDescTextList getOfferDescriptionTextList() {
            return offerDescriptionTextList;
        }

        /**
         * Sets the value of the offerDescriptionTextList property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultilingualCodeDescTextList }
         *     
         */
        public void setOfferDescriptionTextList(MultilingualCodeDescTextList value) {
            this.offerDescriptionTextList = value;
        }

        /**
         * Gets the value of the offerDetailedDescriptionTextList property.
         * 
         * @return
         *     possible object is
         *     {@link MultilingualCodeDescTextList }
         *     
         */
        public MultilingualCodeDescTextList getOfferDetailedDescriptionTextList() {
            return offerDetailedDescriptionTextList;
        }

        /**
         * Sets the value of the offerDetailedDescriptionTextList property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultilingualCodeDescTextList }
         *     
         */
        public void setOfferDetailedDescriptionTextList(MultilingualCodeDescTextList value) {
            this.offerDetailedDescriptionTextList = value;
        }

        /**
         * Gets the value of the offerTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOfferTypeCode() {
            return offerTypeCode;
        }

        /**
         * Sets the value of the offerTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOfferTypeCode(String value) {
            this.offerTypeCode = value;
        }

        /**
         * Gets the value of the termInMonths property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getTermInMonths() {
            return termInMonths;
        }

        /**
         * Sets the value of the termInMonths property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setTermInMonths(BigInteger value) {
            this.termInMonths = value;
        }

        /**
         * Gets the value of the tabTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTabTypeCode() {
            return tabTypeCode;
        }

        /**
         * Sets the value of the tabTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTabTypeCode(String value) {
            this.tabTypeCode = value;
        }

        /**
         * Gets the value of the tabDescriptionTextList property.
         * 
         * @return
         *     possible object is
         *     {@link MultilingualCodeDescTextList }
         *     
         */
        public MultilingualCodeDescTextList getTabDescriptionTextList() {
            return tabDescriptionTextList;
        }

        /**
         * Sets the value of the tabDescriptionTextList property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultilingualCodeDescTextList }
         *     
         */
        public void setTabDescriptionTextList(MultilingualCodeDescTextList value) {
            this.tabDescriptionTextList = value;
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
                String theOfferId;
                theOfferId = this.getOfferId();
                strategy.appendField(locator, this, "offerId", buffer, theOfferId);
            }
            {
                MultilingualCodeDescTextList theOfferDescriptionTextList;
                theOfferDescriptionTextList = this.getOfferDescriptionTextList();
                strategy.appendField(locator, this, "offerDescriptionTextList", buffer, theOfferDescriptionTextList);
            }
            {
                MultilingualCodeDescTextList theOfferDetailedDescriptionTextList;
                theOfferDetailedDescriptionTextList = this.getOfferDetailedDescriptionTextList();
                strategy.appendField(locator, this, "offerDetailedDescriptionTextList", buffer, theOfferDetailedDescriptionTextList);
            }
            {
                String theOfferTypeCode;
                theOfferTypeCode = this.getOfferTypeCode();
                strategy.appendField(locator, this, "offerTypeCode", buffer, theOfferTypeCode);
            }
            {
                BigInteger theTermInMonths;
                theTermInMonths = this.getTermInMonths();
                strategy.appendField(locator, this, "termInMonths", buffer, theTermInMonths);
            }
            {
                String theTabTypeCode;
                theTabTypeCode = this.getTabTypeCode();
                strategy.appendField(locator, this, "tabTypeCode", buffer, theTabTypeCode);
            }
            {
                MultilingualCodeDescTextList theTabDescriptionTextList;
                theTabDescriptionTextList = this.getTabDescriptionTextList();
                strategy.appendField(locator, this, "tabDescriptionTextList", buffer, theTabDescriptionTextList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Subscriber.ExistingOffer)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Subscriber.ExistingOffer that = ((Subscriber.ExistingOffer) object);
            {
                String lhsOfferId;
                lhsOfferId = this.getOfferId();
                String rhsOfferId;
                rhsOfferId = that.getOfferId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerId", lhsOfferId), LocatorUtils.property(thatLocator, "offerId", rhsOfferId), lhsOfferId, rhsOfferId)) {
                    return false;
                }
            }
            {
                MultilingualCodeDescTextList lhsOfferDescriptionTextList;
                lhsOfferDescriptionTextList = this.getOfferDescriptionTextList();
                MultilingualCodeDescTextList rhsOfferDescriptionTextList;
                rhsOfferDescriptionTextList = that.getOfferDescriptionTextList();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerDescriptionTextList", lhsOfferDescriptionTextList), LocatorUtils.property(thatLocator, "offerDescriptionTextList", rhsOfferDescriptionTextList), lhsOfferDescriptionTextList, rhsOfferDescriptionTextList)) {
                    return false;
                }
            }
            {
                MultilingualCodeDescTextList lhsOfferDetailedDescriptionTextList;
                lhsOfferDetailedDescriptionTextList = this.getOfferDetailedDescriptionTextList();
                MultilingualCodeDescTextList rhsOfferDetailedDescriptionTextList;
                rhsOfferDetailedDescriptionTextList = that.getOfferDetailedDescriptionTextList();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerDetailedDescriptionTextList", lhsOfferDetailedDescriptionTextList), LocatorUtils.property(thatLocator, "offerDetailedDescriptionTextList", rhsOfferDetailedDescriptionTextList), lhsOfferDetailedDescriptionTextList, rhsOfferDetailedDescriptionTextList)) {
                    return false;
                }
            }
            {
                String lhsOfferTypeCode;
                lhsOfferTypeCode = this.getOfferTypeCode();
                String rhsOfferTypeCode;
                rhsOfferTypeCode = that.getOfferTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerTypeCode", lhsOfferTypeCode), LocatorUtils.property(thatLocator, "offerTypeCode", rhsOfferTypeCode), lhsOfferTypeCode, rhsOfferTypeCode)) {
                    return false;
                }
            }
            {
                BigInteger lhsTermInMonths;
                lhsTermInMonths = this.getTermInMonths();
                BigInteger rhsTermInMonths;
                rhsTermInMonths = that.getTermInMonths();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "termInMonths", lhsTermInMonths), LocatorUtils.property(thatLocator, "termInMonths", rhsTermInMonths), lhsTermInMonths, rhsTermInMonths)) {
                    return false;
                }
            }
            {
                String lhsTabTypeCode;
                lhsTabTypeCode = this.getTabTypeCode();
                String rhsTabTypeCode;
                rhsTabTypeCode = that.getTabTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "tabTypeCode", lhsTabTypeCode), LocatorUtils.property(thatLocator, "tabTypeCode", rhsTabTypeCode), lhsTabTypeCode, rhsTabTypeCode)) {
                    return false;
                }
            }
            {
                MultilingualCodeDescTextList lhsTabDescriptionTextList;
                lhsTabDescriptionTextList = this.getTabDescriptionTextList();
                MultilingualCodeDescTextList rhsTabDescriptionTextList;
                rhsTabDescriptionTextList = that.getTabDescriptionTextList();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "tabDescriptionTextList", lhsTabDescriptionTextList), LocatorUtils.property(thatLocator, "tabDescriptionTextList", rhsTabDescriptionTextList), lhsTabDescriptionTextList, rhsTabDescriptionTextList)) {
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

}
