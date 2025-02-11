
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for AccountBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountHolderName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" minOccurs="0"/>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="prepaidIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="availableForAddOnIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="billCycleCloseMonthDay" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="billingDeliveryMethodList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingDeliveryMethodType" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="billingLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredContactLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="delinquentStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clpStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="creditProgram" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditProgram" minOccurs="0"/>
 *         &lt;element name="businessEntity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BusinessEntityInformationType" minOccurs="0"/>
 *         &lt;element name="waiveLatePaymentInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="portProtectionInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountBase", propOrder = {
    "billingAccountNumber",
    "accountTypeCode",
    "accountSubTypeCode",
    "accountHolderName",
    "brandCode",
    "billingAddress",
    "prepaidIndicator",
    "availableForAddOnIndicator",
    "billCycleCloseMonthDay",
    "billingDeliveryMethodList",
    "billingLanguageCode",
    "accountStatusCode",
    "preferredContactLanguageCode",
    "delinquentStatusInd",
    "clpStatusInd",
    "creditProgram",
    "businessEntity",
    "waiveLatePaymentInd",
    "portProtectionInd"
})
@XmlSeeAlso({
    AccountBaseTaxationType.class
})
public class AccountBase
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String billingAccountNumber;
    protected String accountTypeCode;
    protected String accountSubTypeCode;
    protected Name accountHolderName;
    protected String brandCode;
    protected Address billingAddress;
    protected Boolean prepaidIndicator;
    protected Boolean availableForAddOnIndicator;
    protected Integer billCycleCloseMonthDay;
    protected List<BillingDeliveryMethodType> billingDeliveryMethodList;
    protected String billingLanguageCode;
    protected String accountStatusCode;
    protected String preferredContactLanguageCode;
    protected Boolean delinquentStatusInd;
    protected Boolean clpStatusInd;
    protected CreditProgram creditProgram;
    protected BusinessEntityInformationType businessEntity;
    protected Boolean waiveLatePaymentInd;
    protected Boolean portProtectionInd;

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
     * Gets the value of the billingDeliveryMethodList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billingDeliveryMethodList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillingDeliveryMethodList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillingDeliveryMethodType }
     * 
     * 
     */
    public List<BillingDeliveryMethodType> getBillingDeliveryMethodList() {
        if (billingDeliveryMethodList == null) {
            billingDeliveryMethodList = new ArrayList<BillingDeliveryMethodType>();
        }
        return this.billingDeliveryMethodList;
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
     * Gets the value of the creditProgram property.
     * 
     * @return
     *     possible object is
     *     {@link CreditProgram }
     *     
     */
    public CreditProgram getCreditProgram() {
        return creditProgram;
    }

    /**
     * Sets the value of the creditProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditProgram }
     *     
     */
    public void setCreditProgram(CreditProgram value) {
        this.creditProgram = value;
    }

    /**
     * Gets the value of the businessEntity property.
     * 
     * @return
     *     possible object is
     *     {@link BusinessEntityInformationType }
     *     
     */
    public BusinessEntityInformationType getBusinessEntity() {
        return businessEntity;
    }

    /**
     * Sets the value of the businessEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BusinessEntityInformationType }
     *     
     */
    public void setBusinessEntity(BusinessEntityInformationType value) {
        this.businessEntity = value;
    }

    /**
     * Gets the value of the waiveLatePaymentInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWaiveLatePaymentInd() {
        return waiveLatePaymentInd;
    }

    /**
     * Sets the value of the waiveLatePaymentInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWaiveLatePaymentInd(Boolean value) {
        this.waiveLatePaymentInd = value;
    }

    /**
     * Gets the value of the portProtectionInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPortProtectionInd() {
        return portProtectionInd;
    }

    /**
     * Sets the value of the portProtectionInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPortProtectionInd(Boolean value) {
        this.portProtectionInd = value;
    }

    /**
     * Sets the value of the billingDeliveryMethodList property.
     * 
     * @param billingDeliveryMethodList
     *     allowed object is
     *     {@link BillingDeliveryMethodType }
     *     
     */
    public void setBillingDeliveryMethodList(List<BillingDeliveryMethodType> billingDeliveryMethodList) {
        this.billingDeliveryMethodList = billingDeliveryMethodList;
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
            Name theAccountHolderName;
            theAccountHolderName = this.getAccountHolderName();
            strategy.appendField(locator, this, "accountHolderName", buffer, theAccountHolderName);
        }
        {
            String theBrandCode;
            theBrandCode = this.getBrandCode();
            strategy.appendField(locator, this, "brandCode", buffer, theBrandCode);
        }
        {
            Address theBillingAddress;
            theBillingAddress = this.getBillingAddress();
            strategy.appendField(locator, this, "billingAddress", buffer, theBillingAddress);
        }
        {
            Boolean thePrepaidIndicator;
            thePrepaidIndicator = this.isPrepaidIndicator();
            strategy.appendField(locator, this, "prepaidIndicator", buffer, thePrepaidIndicator);
        }
        {
            Boolean theAvailableForAddOnIndicator;
            theAvailableForAddOnIndicator = this.isAvailableForAddOnIndicator();
            strategy.appendField(locator, this, "availableForAddOnIndicator", buffer, theAvailableForAddOnIndicator);
        }
        {
            Integer theBillCycleCloseMonthDay;
            theBillCycleCloseMonthDay = this.getBillCycleCloseMonthDay();
            strategy.appendField(locator, this, "billCycleCloseMonthDay", buffer, theBillCycleCloseMonthDay);
        }
        {
            List<BillingDeliveryMethodType> theBillingDeliveryMethodList;
            theBillingDeliveryMethodList = (((this.billingDeliveryMethodList!= null)&&(!this.billingDeliveryMethodList.isEmpty()))?this.getBillingDeliveryMethodList():null);
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
            String thePreferredContactLanguageCode;
            thePreferredContactLanguageCode = this.getPreferredContactLanguageCode();
            strategy.appendField(locator, this, "preferredContactLanguageCode", buffer, thePreferredContactLanguageCode);
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
            CreditProgram theCreditProgram;
            theCreditProgram = this.getCreditProgram();
            strategy.appendField(locator, this, "creditProgram", buffer, theCreditProgram);
        }
        {
            BusinessEntityInformationType theBusinessEntity;
            theBusinessEntity = this.getBusinessEntity();
            strategy.appendField(locator, this, "businessEntity", buffer, theBusinessEntity);
        }
        {
            Boolean theWaiveLatePaymentInd;
            theWaiveLatePaymentInd = this.isWaiveLatePaymentInd();
            strategy.appendField(locator, this, "waiveLatePaymentInd", buffer, theWaiveLatePaymentInd);
        }
        {
            Boolean thePortProtectionInd;
            thePortProtectionInd = this.isPortProtectionInd();
            strategy.appendField(locator, this, "portProtectionInd", buffer, thePortProtectionInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountBase)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountBase that = ((AccountBase) object);
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
            Name lhsAccountHolderName;
            lhsAccountHolderName = this.getAccountHolderName();
            Name rhsAccountHolderName;
            rhsAccountHolderName = that.getAccountHolderName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountHolderName", lhsAccountHolderName), LocatorUtils.property(thatLocator, "accountHolderName", rhsAccountHolderName), lhsAccountHolderName, rhsAccountHolderName)) {
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
            Address lhsBillingAddress;
            lhsBillingAddress = this.getBillingAddress();
            Address rhsBillingAddress;
            rhsBillingAddress = that.getBillingAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAddress", lhsBillingAddress), LocatorUtils.property(thatLocator, "billingAddress", rhsBillingAddress), lhsBillingAddress, rhsBillingAddress)) {
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
            Boolean lhsAvailableForAddOnIndicator;
            lhsAvailableForAddOnIndicator = this.isAvailableForAddOnIndicator();
            Boolean rhsAvailableForAddOnIndicator;
            rhsAvailableForAddOnIndicator = that.isAvailableForAddOnIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "availableForAddOnIndicator", lhsAvailableForAddOnIndicator), LocatorUtils.property(thatLocator, "availableForAddOnIndicator", rhsAvailableForAddOnIndicator), lhsAvailableForAddOnIndicator, rhsAvailableForAddOnIndicator)) {
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
            List<BillingDeliveryMethodType> lhsBillingDeliveryMethodList;
            lhsBillingDeliveryMethodList = (((this.billingDeliveryMethodList!= null)&&(!this.billingDeliveryMethodList.isEmpty()))?this.getBillingDeliveryMethodList():null);
            List<BillingDeliveryMethodType> rhsBillingDeliveryMethodList;
            rhsBillingDeliveryMethodList = (((that.billingDeliveryMethodList!= null)&&(!that.billingDeliveryMethodList.isEmpty()))?that.getBillingDeliveryMethodList():null);
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
            String lhsPreferredContactLanguageCode;
            lhsPreferredContactLanguageCode = this.getPreferredContactLanguageCode();
            String rhsPreferredContactLanguageCode;
            rhsPreferredContactLanguageCode = that.getPreferredContactLanguageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preferredContactLanguageCode", lhsPreferredContactLanguageCode), LocatorUtils.property(thatLocator, "preferredContactLanguageCode", rhsPreferredContactLanguageCode), lhsPreferredContactLanguageCode, rhsPreferredContactLanguageCode)) {
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
            CreditProgram lhsCreditProgram;
            lhsCreditProgram = this.getCreditProgram();
            CreditProgram rhsCreditProgram;
            rhsCreditProgram = that.getCreditProgram();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditProgram", lhsCreditProgram), LocatorUtils.property(thatLocator, "creditProgram", rhsCreditProgram), lhsCreditProgram, rhsCreditProgram)) {
                return false;
            }
        }
        {
            BusinessEntityInformationType lhsBusinessEntity;
            lhsBusinessEntity = this.getBusinessEntity();
            BusinessEntityInformationType rhsBusinessEntity;
            rhsBusinessEntity = that.getBusinessEntity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessEntity", lhsBusinessEntity), LocatorUtils.property(thatLocator, "businessEntity", rhsBusinessEntity), lhsBusinessEntity, rhsBusinessEntity)) {
                return false;
            }
        }
        {
            Boolean lhsWaiveLatePaymentInd;
            lhsWaiveLatePaymentInd = this.isWaiveLatePaymentInd();
            Boolean rhsWaiveLatePaymentInd;
            rhsWaiveLatePaymentInd = that.isWaiveLatePaymentInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "waiveLatePaymentInd", lhsWaiveLatePaymentInd), LocatorUtils.property(thatLocator, "waiveLatePaymentInd", rhsWaiveLatePaymentInd), lhsWaiveLatePaymentInd, rhsWaiveLatePaymentInd)) {
                return false;
            }
        }
        {
            Boolean lhsPortProtectionInd;
            lhsPortProtectionInd = this.isPortProtectionInd();
            Boolean rhsPortProtectionInd;
            rhsPortProtectionInd = that.isPortProtectionInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portProtectionInd", lhsPortProtectionInd), LocatorUtils.property(thatLocator, "portProtectionInd", rhsPortProtectionInd), lhsPortProtectionInd, rhsPortProtectionInd)) {
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
