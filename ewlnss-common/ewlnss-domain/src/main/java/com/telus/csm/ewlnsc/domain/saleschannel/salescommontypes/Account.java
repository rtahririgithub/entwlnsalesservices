
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Account complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Account">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payChannelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prepaidIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountMasterSourceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAccountPIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCycleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billCycleCloseMonthDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="billingAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="billingDeliveryMethodList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="mediaTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="billingLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountCreationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="delinquentStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clpStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="prepaidAccountInfo" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PrepaidAccount" minOccurs="0"/>
 *         &lt;element name="accountHolderName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" minOccurs="0"/>
 *         &lt;element name="accountHolderPostalCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authorizedNameList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="authorizedName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" maxOccurs="100"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="accountBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="teamMemberConcessionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredContactLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="effectiveWirelessProductType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="availableForAddOnIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="selfserveIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="preAuthorizedPaymentMethodIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerSegmentCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nPlayDiscountInfo" maxOccurs="10" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="nPlayDiscountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="nPlayDiscountDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="subsidiaryEntityId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eBillNotificationTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ebillDeclinedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ebillDeclinedReasonCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Account", propOrder = {
    "payChannelNumber",
    "billingAccountNumber",
    "accountTypeCode",
    "accountSubTypeCode",
    "prepaidIndicator",
    "brandCode",
    "accountMasterSourceType",
    "billingAccountPIN",
    "billCycleCode",
    "billCycleCloseMonthDay",
    "billingAddress",
    "billingDeliveryMethodList",
    "billingLanguageCode",
    "accountStatusCode",
    "accountCreationDate",
    "delinquentStatusInd",
    "clpStatusInd",
    "prepaidAccountInfo",
    "accountHolderName",
    "accountHolderPostalCd",
    "authorizedNameList",
    "accountBalanceAmount",
    "teamMemberConcessionCode",
    "preferredContactLanguageCode",
    "effectiveWirelessProductType",
    "availableForAddOnIndicator",
    "selfserveIndicator",
    "preAuthorizedPaymentMethodIndicator",
    "customerId",
    "customerSegmentCd",
    "nPlayDiscountInfo",
    "subsidiaryEntityId",
    "eBillNotificationTypeCd",
    "ebillDeclinedInd",
    "ebillDeclinedReasonCd"
})
public class Account
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String payChannelNumber;
    protected String billingAccountNumber;
    protected String accountTypeCode;
    protected String accountSubTypeCode;
    protected Boolean prepaidIndicator;
    protected String brandCode;
    protected String accountMasterSourceType;
    protected String billingAccountPIN;
    protected String billCycleCode;
    protected Integer billCycleCloseMonthDay;
    protected Address billingAddress;
    protected Account.BillingDeliveryMethodList billingDeliveryMethodList;
    protected String billingLanguageCode;
    protected String accountStatusCode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date accountCreationDate;
    protected Boolean delinquentStatusInd;
    protected Boolean clpStatusInd;
    protected PrepaidAccount prepaidAccountInfo;
    protected Name accountHolderName;
    protected String accountHolderPostalCd;
    protected Account.AuthorizedNameList authorizedNameList;
    protected Double accountBalanceAmount;
    protected String teamMemberConcessionCode;
    protected String preferredContactLanguageCode;
    protected String effectiveWirelessProductType;
    protected Boolean availableForAddOnIndicator;
    protected Boolean selfserveIndicator;
    protected Boolean preAuthorizedPaymentMethodIndicator;
    protected String customerId;
    protected String customerSegmentCd;
    protected List<Account.NPlayDiscountInfo> nPlayDiscountInfo;
    protected String subsidiaryEntityId;
    protected String eBillNotificationTypeCd;
    protected Boolean ebillDeclinedInd;
    protected String ebillDeclinedReasonCd;

    /**
     * Gets the value of the payChannelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayChannelNumber() {
        return payChannelNumber;
    }

    /**
     * Sets the value of the payChannelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayChannelNumber(String value) {
        this.payChannelNumber = value;
    }

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

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
     * Gets the value of the prepaidIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPrepaidIndicator() {
        return prepaidIndicator;
    }

    /**
     * Sets the value of the prepaidIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPrepaidIndicator(Boolean value) {
        this.prepaidIndicator = value;
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
     * Gets the value of the accountMasterSourceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountMasterSourceType() {
        return accountMasterSourceType;
    }

    /**
     * Sets the value of the accountMasterSourceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountMasterSourceType(String value) {
        this.accountMasterSourceType = value;
    }

    /**
     * Gets the value of the billingAccountPIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountPIN() {
        return billingAccountPIN;
    }

    /**
     * Sets the value of the billingAccountPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountPIN(String value) {
        this.billingAccountPIN = value;
    }

    /**
     * Gets the value of the billCycleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillCycleCode() {
        return billCycleCode;
    }

    /**
     * Sets the value of the billCycleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillCycleCode(String value) {
        this.billCycleCode = value;
    }

    /**
     * Gets the value of the billCycleCloseMonthDay property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getBillCycleCloseMonthDay() {
        return billCycleCloseMonthDay;
    }

    /**
     * Sets the value of the billCycleCloseMonthDay property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setBillCycleCloseMonthDay(Integer value) {
        this.billCycleCloseMonthDay = value;
    }

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setBillingAddress(Address value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the billingDeliveryMethodList property.
     * 
     * @return
     *     possible object is
     *     {@link Account.BillingDeliveryMethodList }
     *     
     */
    public Account.BillingDeliveryMethodList getBillingDeliveryMethodList() {
        return billingDeliveryMethodList;
    }

    /**
     * Sets the value of the billingDeliveryMethodList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Account.BillingDeliveryMethodList }
     *     
     */
    public void setBillingDeliveryMethodList(Account.BillingDeliveryMethodList value) {
        this.billingDeliveryMethodList = value;
    }

    /**
     * Gets the value of the billingLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingLanguageCode() {
        return billingLanguageCode;
    }

    /**
     * Sets the value of the billingLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingLanguageCode(String value) {
        this.billingLanguageCode = value;
    }

    /**
     * Gets the value of the accountStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountStatusCode() {
        return accountStatusCode;
    }

    /**
     * Sets the value of the accountStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountStatusCode(String value) {
        this.accountStatusCode = value;
    }

    /**
     * Gets the value of the accountCreationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAccountCreationDate() {
        return accountCreationDate;
    }

    /**
     * Sets the value of the accountCreationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountCreationDate(Date value) {
        this.accountCreationDate = value;
    }

    /**
     * Gets the value of the delinquentStatusInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDelinquentStatusInd() {
        return delinquentStatusInd;
    }

    /**
     * Sets the value of the delinquentStatusInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDelinquentStatusInd(Boolean value) {
        this.delinquentStatusInd = value;
    }

    /**
     * Gets the value of the clpStatusInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClpStatusInd() {
        return clpStatusInd;
    }

    /**
     * Sets the value of the clpStatusInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClpStatusInd(Boolean value) {
        this.clpStatusInd = value;
    }

    /**
     * Gets the value of the prepaidAccountInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PrepaidAccount }
     *     
     */
    public PrepaidAccount getPrepaidAccountInfo() {
        return prepaidAccountInfo;
    }

    /**
     * Sets the value of the prepaidAccountInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrepaidAccount }
     *     
     */
    public void setPrepaidAccountInfo(PrepaidAccount value) {
        this.prepaidAccountInfo = value;
    }

    /**
     * Gets the value of the accountHolderName property.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getAccountHolderName() {
        return accountHolderName;
    }

    /**
     * Sets the value of the accountHolderName property.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setAccountHolderName(Name value) {
        this.accountHolderName = value;
    }

    /**
     * Gets the value of the accountHolderPostalCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountHolderPostalCd() {
        return accountHolderPostalCd;
    }

    /**
     * Sets the value of the accountHolderPostalCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountHolderPostalCd(String value) {
        this.accountHolderPostalCd = value;
    }

    /**
     * Gets the value of the authorizedNameList property.
     * 
     * @return
     *     possible object is
     *     {@link Account.AuthorizedNameList }
     *     
     */
    public Account.AuthorizedNameList getAuthorizedNameList() {
        return authorizedNameList;
    }

    /**
     * Sets the value of the authorizedNameList property.
     * 
     * @param value
     *     allowed object is
     *     {@link Account.AuthorizedNameList }
     *     
     */
    public void setAuthorizedNameList(Account.AuthorizedNameList value) {
        this.authorizedNameList = value;
    }

    /**
     * Gets the value of the accountBalanceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAccountBalanceAmount() {
        return accountBalanceAmount;
    }

    /**
     * Sets the value of the accountBalanceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAccountBalanceAmount(Double value) {
        this.accountBalanceAmount = value;
    }

    /**
     * Gets the value of the teamMemberConcessionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeamMemberConcessionCode() {
        return teamMemberConcessionCode;
    }

    /**
     * Sets the value of the teamMemberConcessionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeamMemberConcessionCode(String value) {
        this.teamMemberConcessionCode = value;
    }

    /**
     * Gets the value of the preferredContactLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredContactLanguageCode() {
        return preferredContactLanguageCode;
    }

    /**
     * Sets the value of the preferredContactLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredContactLanguageCode(String value) {
        this.preferredContactLanguageCode = value;
    }

    /**
     * Gets the value of the effectiveWirelessProductType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEffectiveWirelessProductType() {
        return effectiveWirelessProductType;
    }

    /**
     * Sets the value of the effectiveWirelessProductType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveWirelessProductType(String value) {
        this.effectiveWirelessProductType = value;
    }

    /**
     * Gets the value of the availableForAddOnIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAvailableForAddOnIndicator() {
        return availableForAddOnIndicator;
    }

    /**
     * Sets the value of the availableForAddOnIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAvailableForAddOnIndicator(Boolean value) {
        this.availableForAddOnIndicator = value;
    }

    /**
     * Gets the value of the selfserveIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSelfserveIndicator() {
        return selfserveIndicator;
    }

    /**
     * Sets the value of the selfserveIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSelfserveIndicator(Boolean value) {
        this.selfserveIndicator = value;
    }

    /**
     * Gets the value of the preAuthorizedPaymentMethodIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreAuthorizedPaymentMethodIndicator() {
        return preAuthorizedPaymentMethodIndicator;
    }

    /**
     * Sets the value of the preAuthorizedPaymentMethodIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreAuthorizedPaymentMethodIndicator(Boolean value) {
        this.preAuthorizedPaymentMethodIndicator = value;
    }

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the customerSegmentCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerSegmentCd() {
        return customerSegmentCd;
    }

    /**
     * Sets the value of the customerSegmentCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerSegmentCd(String value) {
        this.customerSegmentCd = value;
    }

    /**
     * Gets the value of the nPlayDiscountInfo property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the nPlayDiscountInfo property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getNPlayDiscountInfo().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Account.NPlayDiscountInfo }
     * 
     * 
     */
    public List<Account.NPlayDiscountInfo> getNPlayDiscountInfo() {
        if (nPlayDiscountInfo == null) {
            nPlayDiscountInfo = new ArrayList<Account.NPlayDiscountInfo>();
        }
        return this.nPlayDiscountInfo;
    }

    /**
     * Gets the value of the subsidiaryEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidiaryEntityId() {
        return subsidiaryEntityId;
    }

    /**
     * Sets the value of the subsidiaryEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidiaryEntityId(String value) {
        this.subsidiaryEntityId = value;
    }

    /**
     * Gets the value of the eBillNotificationTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEBillNotificationTypeCd() {
        return eBillNotificationTypeCd;
    }

    /**
     * Sets the value of the eBillNotificationTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEBillNotificationTypeCd(String value) {
        this.eBillNotificationTypeCd = value;
    }

    /**
     * Gets the value of the ebillDeclinedInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEbillDeclinedInd() {
        return ebillDeclinedInd;
    }

    /**
     * Sets the value of the ebillDeclinedInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEbillDeclinedInd(Boolean value) {
        this.ebillDeclinedInd = value;
    }

    /**
     * Gets the value of the ebillDeclinedReasonCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEbillDeclinedReasonCd() {
        return ebillDeclinedReasonCd;
    }

    /**
     * Sets the value of the ebillDeclinedReasonCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEbillDeclinedReasonCd(String value) {
        this.ebillDeclinedReasonCd = value;
    }

    /**
     * Sets the value of the nPlayDiscountInfo property.
     * 
     * @param nPlayDiscountInfo
     *     allowed object is
     *     {@link Account.NPlayDiscountInfo }
     *     
     */
    public void setNPlayDiscountInfo(List<Account.NPlayDiscountInfo> nPlayDiscountInfo) {
        this.nPlayDiscountInfo = nPlayDiscountInfo;
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
            String thePayChannelNumber;
            thePayChannelNumber = this.getPayChannelNumber();
            strategy.appendField(locator, this, "payChannelNumber", buffer, thePayChannelNumber);
        }
        {
            String theBillingAccountNumber;
            theBillingAccountNumber = this.getBillingAccountNumber();
            strategy.appendField(locator, this, "billingAccountNumber", buffer, theBillingAccountNumber);
        }
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
            Boolean thePrepaidIndicator;
            thePrepaidIndicator = this.isPrepaidIndicator();
            strategy.appendField(locator, this, "prepaidIndicator", buffer, thePrepaidIndicator);
        }
        {
            String theBrandCode;
            theBrandCode = this.getBrandCode();
            strategy.appendField(locator, this, "brandCode", buffer, theBrandCode);
        }
        {
            String theAccountMasterSourceType;
            theAccountMasterSourceType = this.getAccountMasterSourceType();
            strategy.appendField(locator, this, "accountMasterSourceType", buffer, theAccountMasterSourceType);
        }
        {
            String theBillingAccountPIN;
            theBillingAccountPIN = this.getBillingAccountPIN();
            strategy.appendField(locator, this, "billingAccountPIN", buffer, theBillingAccountPIN);
        }
        {
            String theBillCycleCode;
            theBillCycleCode = this.getBillCycleCode();
            strategy.appendField(locator, this, "billCycleCode", buffer, theBillCycleCode);
        }
        {
            Integer theBillCycleCloseMonthDay;
            theBillCycleCloseMonthDay = this.getBillCycleCloseMonthDay();
            strategy.appendField(locator, this, "billCycleCloseMonthDay", buffer, theBillCycleCloseMonthDay);
        }
        {
            Address theBillingAddress;
            theBillingAddress = this.getBillingAddress();
            strategy.appendField(locator, this, "billingAddress", buffer, theBillingAddress);
        }
        {
            Account.BillingDeliveryMethodList theBillingDeliveryMethodList;
            theBillingDeliveryMethodList = this.getBillingDeliveryMethodList();
            strategy.appendField(locator, this, "billingDeliveryMethodList", buffer, theBillingDeliveryMethodList);
        }
        {
            String theBillingLanguageCode;
            theBillingLanguageCode = this.getBillingLanguageCode();
            strategy.appendField(locator, this, "billingLanguageCode", buffer, theBillingLanguageCode);
        }
        {
            String theAccountStatusCode;
            theAccountStatusCode = this.getAccountStatusCode();
            strategy.appendField(locator, this, "accountStatusCode", buffer, theAccountStatusCode);
        }
        {
            Date theAccountCreationDate;
            theAccountCreationDate = this.getAccountCreationDate();
            strategy.appendField(locator, this, "accountCreationDate", buffer, theAccountCreationDate);
        }
        {
            Boolean theDelinquentStatusInd;
            theDelinquentStatusInd = this.isDelinquentStatusInd();
            strategy.appendField(locator, this, "delinquentStatusInd", buffer, theDelinquentStatusInd);
        }
        {
            Boolean theClpStatusInd;
            theClpStatusInd = this.isClpStatusInd();
            strategy.appendField(locator, this, "clpStatusInd", buffer, theClpStatusInd);
        }
        {
            PrepaidAccount thePrepaidAccountInfo;
            thePrepaidAccountInfo = this.getPrepaidAccountInfo();
            strategy.appendField(locator, this, "prepaidAccountInfo", buffer, thePrepaidAccountInfo);
        }
        {
            Name theAccountHolderName;
            theAccountHolderName = this.getAccountHolderName();
            strategy.appendField(locator, this, "accountHolderName", buffer, theAccountHolderName);
        }
        {
            String theAccountHolderPostalCd;
            theAccountHolderPostalCd = this.getAccountHolderPostalCd();
            strategy.appendField(locator, this, "accountHolderPostalCd", buffer, theAccountHolderPostalCd);
        }
        {
            Account.AuthorizedNameList theAuthorizedNameList;
            theAuthorizedNameList = this.getAuthorizedNameList();
            strategy.appendField(locator, this, "authorizedNameList", buffer, theAuthorizedNameList);
        }
        {
            Double theAccountBalanceAmount;
            theAccountBalanceAmount = this.getAccountBalanceAmount();
            strategy.appendField(locator, this, "accountBalanceAmount", buffer, theAccountBalanceAmount);
        }
        {
            String theTeamMemberConcessionCode;
            theTeamMemberConcessionCode = this.getTeamMemberConcessionCode();
            strategy.appendField(locator, this, "teamMemberConcessionCode", buffer, theTeamMemberConcessionCode);
        }
        {
            String thePreferredContactLanguageCode;
            thePreferredContactLanguageCode = this.getPreferredContactLanguageCode();
            strategy.appendField(locator, this, "preferredContactLanguageCode", buffer, thePreferredContactLanguageCode);
        }
        {
            String theEffectiveWirelessProductType;
            theEffectiveWirelessProductType = this.getEffectiveWirelessProductType();
            strategy.appendField(locator, this, "effectiveWirelessProductType", buffer, theEffectiveWirelessProductType);
        }
        {
            Boolean theAvailableForAddOnIndicator;
            theAvailableForAddOnIndicator = this.isAvailableForAddOnIndicator();
            strategy.appendField(locator, this, "availableForAddOnIndicator", buffer, theAvailableForAddOnIndicator);
        }
        {
            Boolean theSelfserveIndicator;
            theSelfserveIndicator = this.isSelfserveIndicator();
            strategy.appendField(locator, this, "selfserveIndicator", buffer, theSelfserveIndicator);
        }
        {
            Boolean thePreAuthorizedPaymentMethodIndicator;
            thePreAuthorizedPaymentMethodIndicator = this.isPreAuthorizedPaymentMethodIndicator();
            strategy.appendField(locator, this, "preAuthorizedPaymentMethodIndicator", buffer, thePreAuthorizedPaymentMethodIndicator);
        }
        {
            String theCustomerId;
            theCustomerId = this.getCustomerId();
            strategy.appendField(locator, this, "customerId", buffer, theCustomerId);
        }
        {
            String theCustomerSegmentCd;
            theCustomerSegmentCd = this.getCustomerSegmentCd();
            strategy.appendField(locator, this, "customerSegmentCd", buffer, theCustomerSegmentCd);
        }
        {
            List<Account.NPlayDiscountInfo> theNPlayDiscountInfo;
            theNPlayDiscountInfo = (((this.nPlayDiscountInfo!= null)&&(!this.nPlayDiscountInfo.isEmpty()))?this.getNPlayDiscountInfo():null);
            strategy.appendField(locator, this, "nPlayDiscountInfo", buffer, theNPlayDiscountInfo);
        }
        {
            String theSubsidiaryEntityId;
            theSubsidiaryEntityId = this.getSubsidiaryEntityId();
            strategy.appendField(locator, this, "subsidiaryEntityId", buffer, theSubsidiaryEntityId);
        }
        {
            String theEBillNotificationTypeCd;
            theEBillNotificationTypeCd = this.getEBillNotificationTypeCd();
            strategy.appendField(locator, this, "eBillNotificationTypeCd", buffer, theEBillNotificationTypeCd);
        }
        {
            Boolean theEbillDeclinedInd;
            theEbillDeclinedInd = this.isEbillDeclinedInd();
            strategy.appendField(locator, this, "ebillDeclinedInd", buffer, theEbillDeclinedInd);
        }
        {
            String theEbillDeclinedReasonCd;
            theEbillDeclinedReasonCd = this.getEbillDeclinedReasonCd();
            strategy.appendField(locator, this, "ebillDeclinedReasonCd", buffer, theEbillDeclinedReasonCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Account)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Account that = ((Account) object);
        {
            String lhsPayChannelNumber;
            lhsPayChannelNumber = this.getPayChannelNumber();
            String rhsPayChannelNumber;
            rhsPayChannelNumber = that.getPayChannelNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payChannelNumber", lhsPayChannelNumber), LocatorUtils.property(thatLocator, "payChannelNumber", rhsPayChannelNumber), lhsPayChannelNumber, rhsPayChannelNumber)) {
                return false;
            }
        }
        {
            String lhsBillingAccountNumber;
            lhsBillingAccountNumber = this.getBillingAccountNumber();
            String rhsBillingAccountNumber;
            rhsBillingAccountNumber = that.getBillingAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNumber", lhsBillingAccountNumber), LocatorUtils.property(thatLocator, "billingAccountNumber", rhsBillingAccountNumber), lhsBillingAccountNumber, rhsBillingAccountNumber)) {
                return false;
            }
        }
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
            Boolean lhsPrepaidIndicator;
            lhsPrepaidIndicator = this.isPrepaidIndicator();
            Boolean rhsPrepaidIndicator;
            rhsPrepaidIndicator = that.isPrepaidIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prepaidIndicator", lhsPrepaidIndicator), LocatorUtils.property(thatLocator, "prepaidIndicator", rhsPrepaidIndicator), lhsPrepaidIndicator, rhsPrepaidIndicator)) {
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
            String lhsAccountMasterSourceType;
            lhsAccountMasterSourceType = this.getAccountMasterSourceType();
            String rhsAccountMasterSourceType;
            rhsAccountMasterSourceType = that.getAccountMasterSourceType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountMasterSourceType", lhsAccountMasterSourceType), LocatorUtils.property(thatLocator, "accountMasterSourceType", rhsAccountMasterSourceType), lhsAccountMasterSourceType, rhsAccountMasterSourceType)) {
                return false;
            }
        }
        {
            String lhsBillingAccountPIN;
            lhsBillingAccountPIN = this.getBillingAccountPIN();
            String rhsBillingAccountPIN;
            rhsBillingAccountPIN = that.getBillingAccountPIN();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountPIN", lhsBillingAccountPIN), LocatorUtils.property(thatLocator, "billingAccountPIN", rhsBillingAccountPIN), lhsBillingAccountPIN, rhsBillingAccountPIN)) {
                return false;
            }
        }
        {
            String lhsBillCycleCode;
            lhsBillCycleCode = this.getBillCycleCode();
            String rhsBillCycleCode;
            rhsBillCycleCode = that.getBillCycleCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billCycleCode", lhsBillCycleCode), LocatorUtils.property(thatLocator, "billCycleCode", rhsBillCycleCode), lhsBillCycleCode, rhsBillCycleCode)) {
                return false;
            }
        }
        {
            Integer lhsBillCycleCloseMonthDay;
            lhsBillCycleCloseMonthDay = this.getBillCycleCloseMonthDay();
            Integer rhsBillCycleCloseMonthDay;
            rhsBillCycleCloseMonthDay = that.getBillCycleCloseMonthDay();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billCycleCloseMonthDay", lhsBillCycleCloseMonthDay), LocatorUtils.property(thatLocator, "billCycleCloseMonthDay", rhsBillCycleCloseMonthDay), lhsBillCycleCloseMonthDay, rhsBillCycleCloseMonthDay)) {
                return false;
            }
        }
        {
            Address lhsBillingAddress;
            lhsBillingAddress = this.getBillingAddress();
            Address rhsBillingAddress;
            rhsBillingAddress = that.getBillingAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAddress", lhsBillingAddress), LocatorUtils.property(thatLocator, "billingAddress", rhsBillingAddress), lhsBillingAddress, rhsBillingAddress)) {
                return false;
            }
        }
        {
            Account.BillingDeliveryMethodList lhsBillingDeliveryMethodList;
            lhsBillingDeliveryMethodList = this.getBillingDeliveryMethodList();
            Account.BillingDeliveryMethodList rhsBillingDeliveryMethodList;
            rhsBillingDeliveryMethodList = that.getBillingDeliveryMethodList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingDeliveryMethodList", lhsBillingDeliveryMethodList), LocatorUtils.property(thatLocator, "billingDeliveryMethodList", rhsBillingDeliveryMethodList), lhsBillingDeliveryMethodList, rhsBillingDeliveryMethodList)) {
                return false;
            }
        }
        {
            String lhsBillingLanguageCode;
            lhsBillingLanguageCode = this.getBillingLanguageCode();
            String rhsBillingLanguageCode;
            rhsBillingLanguageCode = that.getBillingLanguageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingLanguageCode", lhsBillingLanguageCode), LocatorUtils.property(thatLocator, "billingLanguageCode", rhsBillingLanguageCode), lhsBillingLanguageCode, rhsBillingLanguageCode)) {
                return false;
            }
        }
        {
            String lhsAccountStatusCode;
            lhsAccountStatusCode = this.getAccountStatusCode();
            String rhsAccountStatusCode;
            rhsAccountStatusCode = that.getAccountStatusCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountStatusCode", lhsAccountStatusCode), LocatorUtils.property(thatLocator, "accountStatusCode", rhsAccountStatusCode), lhsAccountStatusCode, rhsAccountStatusCode)) {
                return false;
            }
        }
        {
            Date lhsAccountCreationDate;
            lhsAccountCreationDate = this.getAccountCreationDate();
            Date rhsAccountCreationDate;
            rhsAccountCreationDate = that.getAccountCreationDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountCreationDate", lhsAccountCreationDate), LocatorUtils.property(thatLocator, "accountCreationDate", rhsAccountCreationDate), lhsAccountCreationDate, rhsAccountCreationDate)) {
                return false;
            }
        }
        {
            Boolean lhsDelinquentStatusInd;
            lhsDelinquentStatusInd = this.isDelinquentStatusInd();
            Boolean rhsDelinquentStatusInd;
            rhsDelinquentStatusInd = that.isDelinquentStatusInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "delinquentStatusInd", lhsDelinquentStatusInd), LocatorUtils.property(thatLocator, "delinquentStatusInd", rhsDelinquentStatusInd), lhsDelinquentStatusInd, rhsDelinquentStatusInd)) {
                return false;
            }
        }
        {
            Boolean lhsClpStatusInd;
            lhsClpStatusInd = this.isClpStatusInd();
            Boolean rhsClpStatusInd;
            rhsClpStatusInd = that.isClpStatusInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "clpStatusInd", lhsClpStatusInd), LocatorUtils.property(thatLocator, "clpStatusInd", rhsClpStatusInd), lhsClpStatusInd, rhsClpStatusInd)) {
                return false;
            }
        }
        {
            PrepaidAccount lhsPrepaidAccountInfo;
            lhsPrepaidAccountInfo = this.getPrepaidAccountInfo();
            PrepaidAccount rhsPrepaidAccountInfo;
            rhsPrepaidAccountInfo = that.getPrepaidAccountInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prepaidAccountInfo", lhsPrepaidAccountInfo), LocatorUtils.property(thatLocator, "prepaidAccountInfo", rhsPrepaidAccountInfo), lhsPrepaidAccountInfo, rhsPrepaidAccountInfo)) {
                return false;
            }
        }
        {
            Name lhsAccountHolderName;
            lhsAccountHolderName = this.getAccountHolderName();
            Name rhsAccountHolderName;
            rhsAccountHolderName = that.getAccountHolderName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountHolderName", lhsAccountHolderName), LocatorUtils.property(thatLocator, "accountHolderName", rhsAccountHolderName), lhsAccountHolderName, rhsAccountHolderName)) {
                return false;
            }
        }
        {
            String lhsAccountHolderPostalCd;
            lhsAccountHolderPostalCd = this.getAccountHolderPostalCd();
            String rhsAccountHolderPostalCd;
            rhsAccountHolderPostalCd = that.getAccountHolderPostalCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountHolderPostalCd", lhsAccountHolderPostalCd), LocatorUtils.property(thatLocator, "accountHolderPostalCd", rhsAccountHolderPostalCd), lhsAccountHolderPostalCd, rhsAccountHolderPostalCd)) {
                return false;
            }
        }
        {
            Account.AuthorizedNameList lhsAuthorizedNameList;
            lhsAuthorizedNameList = this.getAuthorizedNameList();
            Account.AuthorizedNameList rhsAuthorizedNameList;
            rhsAuthorizedNameList = that.getAuthorizedNameList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorizedNameList", lhsAuthorizedNameList), LocatorUtils.property(thatLocator, "authorizedNameList", rhsAuthorizedNameList), lhsAuthorizedNameList, rhsAuthorizedNameList)) {
                return false;
            }
        }
        {
            Double lhsAccountBalanceAmount;
            lhsAccountBalanceAmount = this.getAccountBalanceAmount();
            Double rhsAccountBalanceAmount;
            rhsAccountBalanceAmount = that.getAccountBalanceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountBalanceAmount", lhsAccountBalanceAmount), LocatorUtils.property(thatLocator, "accountBalanceAmount", rhsAccountBalanceAmount), lhsAccountBalanceAmount, rhsAccountBalanceAmount)) {
                return false;
            }
        }
        {
            String lhsTeamMemberConcessionCode;
            lhsTeamMemberConcessionCode = this.getTeamMemberConcessionCode();
            String rhsTeamMemberConcessionCode;
            rhsTeamMemberConcessionCode = that.getTeamMemberConcessionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "teamMemberConcessionCode", lhsTeamMemberConcessionCode), LocatorUtils.property(thatLocator, "teamMemberConcessionCode", rhsTeamMemberConcessionCode), lhsTeamMemberConcessionCode, rhsTeamMemberConcessionCode)) {
                return false;
            }
        }
        {
            String lhsPreferredContactLanguageCode;
            lhsPreferredContactLanguageCode = this.getPreferredContactLanguageCode();
            String rhsPreferredContactLanguageCode;
            rhsPreferredContactLanguageCode = that.getPreferredContactLanguageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preferredContactLanguageCode", lhsPreferredContactLanguageCode), LocatorUtils.property(thatLocator, "preferredContactLanguageCode", rhsPreferredContactLanguageCode), lhsPreferredContactLanguageCode, rhsPreferredContactLanguageCode)) {
                return false;
            }
        }
        {
            String lhsEffectiveWirelessProductType;
            lhsEffectiveWirelessProductType = this.getEffectiveWirelessProductType();
            String rhsEffectiveWirelessProductType;
            rhsEffectiveWirelessProductType = that.getEffectiveWirelessProductType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveWirelessProductType", lhsEffectiveWirelessProductType), LocatorUtils.property(thatLocator, "effectiveWirelessProductType", rhsEffectiveWirelessProductType), lhsEffectiveWirelessProductType, rhsEffectiveWirelessProductType)) {
                return false;
            }
        }
        {
            Boolean lhsAvailableForAddOnIndicator;
            lhsAvailableForAddOnIndicator = this.isAvailableForAddOnIndicator();
            Boolean rhsAvailableForAddOnIndicator;
            rhsAvailableForAddOnIndicator = that.isAvailableForAddOnIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "availableForAddOnIndicator", lhsAvailableForAddOnIndicator), LocatorUtils.property(thatLocator, "availableForAddOnIndicator", rhsAvailableForAddOnIndicator), lhsAvailableForAddOnIndicator, rhsAvailableForAddOnIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsSelfserveIndicator;
            lhsSelfserveIndicator = this.isSelfserveIndicator();
            Boolean rhsSelfserveIndicator;
            rhsSelfserveIndicator = that.isSelfserveIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selfserveIndicator", lhsSelfserveIndicator), LocatorUtils.property(thatLocator, "selfserveIndicator", rhsSelfserveIndicator), lhsSelfserveIndicator, rhsSelfserveIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsPreAuthorizedPaymentMethodIndicator;
            lhsPreAuthorizedPaymentMethodIndicator = this.isPreAuthorizedPaymentMethodIndicator();
            Boolean rhsPreAuthorizedPaymentMethodIndicator;
            rhsPreAuthorizedPaymentMethodIndicator = that.isPreAuthorizedPaymentMethodIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preAuthorizedPaymentMethodIndicator", lhsPreAuthorizedPaymentMethodIndicator), LocatorUtils.property(thatLocator, "preAuthorizedPaymentMethodIndicator", rhsPreAuthorizedPaymentMethodIndicator), lhsPreAuthorizedPaymentMethodIndicator, rhsPreAuthorizedPaymentMethodIndicator)) {
                return false;
            }
        }
        {
            String lhsCustomerId;
            lhsCustomerId = this.getCustomerId();
            String rhsCustomerId;
            rhsCustomerId = that.getCustomerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerId", lhsCustomerId), LocatorUtils.property(thatLocator, "customerId", rhsCustomerId), lhsCustomerId, rhsCustomerId)) {
                return false;
            }
        }
        {
            String lhsCustomerSegmentCd;
            lhsCustomerSegmentCd = this.getCustomerSegmentCd();
            String rhsCustomerSegmentCd;
            rhsCustomerSegmentCd = that.getCustomerSegmentCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerSegmentCd", lhsCustomerSegmentCd), LocatorUtils.property(thatLocator, "customerSegmentCd", rhsCustomerSegmentCd), lhsCustomerSegmentCd, rhsCustomerSegmentCd)) {
                return false;
            }
        }
        {
            List<Account.NPlayDiscountInfo> lhsNPlayDiscountInfo;
            lhsNPlayDiscountInfo = (((this.nPlayDiscountInfo!= null)&&(!this.nPlayDiscountInfo.isEmpty()))?this.getNPlayDiscountInfo():null);
            List<Account.NPlayDiscountInfo> rhsNPlayDiscountInfo;
            rhsNPlayDiscountInfo = (((that.nPlayDiscountInfo!= null)&&(!that.nPlayDiscountInfo.isEmpty()))?that.getNPlayDiscountInfo():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nPlayDiscountInfo", lhsNPlayDiscountInfo), LocatorUtils.property(thatLocator, "nPlayDiscountInfo", rhsNPlayDiscountInfo), lhsNPlayDiscountInfo, rhsNPlayDiscountInfo)) {
                return false;
            }
        }
        {
            String lhsSubsidiaryEntityId;
            lhsSubsidiaryEntityId = this.getSubsidiaryEntityId();
            String rhsSubsidiaryEntityId;
            rhsSubsidiaryEntityId = that.getSubsidiaryEntityId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subsidiaryEntityId", lhsSubsidiaryEntityId), LocatorUtils.property(thatLocator, "subsidiaryEntityId", rhsSubsidiaryEntityId), lhsSubsidiaryEntityId, rhsSubsidiaryEntityId)) {
                return false;
            }
        }
        {
            String lhsEBillNotificationTypeCd;
            lhsEBillNotificationTypeCd = this.getEBillNotificationTypeCd();
            String rhsEBillNotificationTypeCd;
            rhsEBillNotificationTypeCd = that.getEBillNotificationTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eBillNotificationTypeCd", lhsEBillNotificationTypeCd), LocatorUtils.property(thatLocator, "eBillNotificationTypeCd", rhsEBillNotificationTypeCd), lhsEBillNotificationTypeCd, rhsEBillNotificationTypeCd)) {
                return false;
            }
        }
        {
            Boolean lhsEbillDeclinedInd;
            lhsEbillDeclinedInd = this.isEbillDeclinedInd();
            Boolean rhsEbillDeclinedInd;
            rhsEbillDeclinedInd = that.isEbillDeclinedInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ebillDeclinedInd", lhsEbillDeclinedInd), LocatorUtils.property(thatLocator, "ebillDeclinedInd", rhsEbillDeclinedInd), lhsEbillDeclinedInd, rhsEbillDeclinedInd)) {
                return false;
            }
        }
        {
            String lhsEbillDeclinedReasonCd;
            lhsEbillDeclinedReasonCd = this.getEbillDeclinedReasonCd();
            String rhsEbillDeclinedReasonCd;
            rhsEbillDeclinedReasonCd = that.getEbillDeclinedReasonCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ebillDeclinedReasonCd", lhsEbillDeclinedReasonCd), LocatorUtils.property(thatLocator, "ebillDeclinedReasonCd", rhsEbillDeclinedReasonCd), lhsEbillDeclinedReasonCd, rhsEbillDeclinedReasonCd)) {
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
     *         &lt;element name="authorizedName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" maxOccurs="100"/>
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
        "authorizedName"
    })
    public static class AuthorizedNameList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected List<Name> authorizedName;

        /**
         * Gets the value of the authorizedName property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the authorizedName property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAuthorizedName().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Name }
         * 
         * 
         */
        public List<Name> getAuthorizedName() {
            if (authorizedName == null) {
                authorizedName = new ArrayList<Name>();
            }
            return this.authorizedName;
        }

        /**
         * Sets the value of the authorizedName property.
         * 
         * @param authorizedName
         *     allowed object is
         *     {@link Name }
         *     
         */
        public void setAuthorizedName(List<Name> authorizedName) {
            this.authorizedName = authorizedName;
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
                List<Name> theAuthorizedName;
                theAuthorizedName = (((this.authorizedName!= null)&&(!this.authorizedName.isEmpty()))?this.getAuthorizedName():null);
                strategy.appendField(locator, this, "authorizedName", buffer, theAuthorizedName);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Account.AuthorizedNameList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Account.AuthorizedNameList that = ((Account.AuthorizedNameList) object);
            {
                List<Name> lhsAuthorizedName;
                lhsAuthorizedName = (((this.authorizedName!= null)&&(!this.authorizedName.isEmpty()))?this.getAuthorizedName():null);
                List<Name> rhsAuthorizedName;
                rhsAuthorizedName = (((that.authorizedName!= null)&&(!that.authorizedName.isEmpty()))?that.getAuthorizedName():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "authorizedName", lhsAuthorizedName), LocatorUtils.property(thatLocator, "authorizedName", rhsAuthorizedName), lhsAuthorizedName, rhsAuthorizedName)) {
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
     *         &lt;element name="mediaTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100"/>
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
        "mediaTypeCode"
    })
    public static class BillingDeliveryMethodList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected List<String> mediaTypeCode;

        /**
         * Gets the value of the mediaTypeCode property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the mediaTypeCode property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getMediaTypeCode().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getMediaTypeCode() {
            if (mediaTypeCode == null) {
                mediaTypeCode = new ArrayList<String>();
            }
            return this.mediaTypeCode;
        }

        /**
         * Sets the value of the mediaTypeCode property.
         * 
         * @param mediaTypeCode
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setMediaTypeCode(List<String> mediaTypeCode) {
            this.mediaTypeCode = mediaTypeCode;
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
                List<String> theMediaTypeCode;
                theMediaTypeCode = (((this.mediaTypeCode!= null)&&(!this.mediaTypeCode.isEmpty()))?this.getMediaTypeCode():null);
                strategy.appendField(locator, this, "mediaTypeCode", buffer, theMediaTypeCode);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Account.BillingDeliveryMethodList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Account.BillingDeliveryMethodList that = ((Account.BillingDeliveryMethodList) object);
            {
                List<String> lhsMediaTypeCode;
                lhsMediaTypeCode = (((this.mediaTypeCode!= null)&&(!this.mediaTypeCode.isEmpty()))?this.getMediaTypeCode():null);
                List<String> rhsMediaTypeCode;
                rhsMediaTypeCode = (((that.mediaTypeCode!= null)&&(!that.mediaTypeCode.isEmpty()))?that.getMediaTypeCode():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "mediaTypeCode", lhsMediaTypeCode), LocatorUtils.property(thatLocator, "mediaTypeCode", rhsMediaTypeCode), lhsMediaTypeCode, rhsMediaTypeCode)) {
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
     *         &lt;element name="nPlayDiscountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="nPlayDiscountDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
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
        "nPlayDiscountCode",
        "nPlayDiscountDescription"
    })
    public static class NPlayDiscountInfo
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String nPlayDiscountCode;
        protected MultilingualDescriptionList nPlayDiscountDescription;

        /**
         * Gets the value of the nPlayDiscountCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNPlayDiscountCode() {
            return nPlayDiscountCode;
        }

        /**
         * Sets the value of the nPlayDiscountCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNPlayDiscountCode(String value) {
            this.nPlayDiscountCode = value;
        }

        /**
         * Gets the value of the nPlayDiscountDescription property.
         * 
         * @return
         *     possible object is
         *     {@link MultilingualDescriptionList }
         *     
         */
        public MultilingualDescriptionList getNPlayDiscountDescription() {
            return nPlayDiscountDescription;
        }

        /**
         * Sets the value of the nPlayDiscountDescription property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultilingualDescriptionList }
         *     
         */
        public void setNPlayDiscountDescription(MultilingualDescriptionList value) {
            this.nPlayDiscountDescription = value;
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
                String theNPlayDiscountCode;
                theNPlayDiscountCode = this.getNPlayDiscountCode();
                strategy.appendField(locator, this, "nPlayDiscountCode", buffer, theNPlayDiscountCode);
            }
            {
                MultilingualDescriptionList theNPlayDiscountDescription;
                theNPlayDiscountDescription = this.getNPlayDiscountDescription();
                strategy.appendField(locator, this, "nPlayDiscountDescription", buffer, theNPlayDiscountDescription);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof Account.NPlayDiscountInfo)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final Account.NPlayDiscountInfo that = ((Account.NPlayDiscountInfo) object);
            {
                String lhsNPlayDiscountCode;
                lhsNPlayDiscountCode = this.getNPlayDiscountCode();
                String rhsNPlayDiscountCode;
                rhsNPlayDiscountCode = that.getNPlayDiscountCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "nPlayDiscountCode", lhsNPlayDiscountCode), LocatorUtils.property(thatLocator, "nPlayDiscountCode", rhsNPlayDiscountCode), lhsNPlayDiscountCode, rhsNPlayDiscountCode)) {
                    return false;
                }
            }
            {
                MultilingualDescriptionList lhsNPlayDiscountDescription;
                lhsNPlayDiscountDescription = this.getNPlayDiscountDescription();
                MultilingualDescriptionList rhsNPlayDiscountDescription;
                rhsNPlayDiscountDescription = that.getNPlayDiscountDescription();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "nPlayDiscountDescription", lhsNPlayDiscountDescription), LocatorUtils.property(thatLocator, "nPlayDiscountDescription", rhsNPlayDiscountDescription), lhsNPlayDiscountDescription, rhsNPlayDiscountDescription)) {
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
