
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
import com.telus.framework.xml.bind.DateAdapter;
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
 * <p>Java class for ServiceItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceGroupTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionActionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="autoRenewIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="autoRenewFundsSourceCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="purchaseFundsSourceCode" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="callingCirclePhoneNumberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCirclePhoneNumberList" minOccurs="0"/>
 *         &lt;element name="featureRecommendation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}FeatureRecommendation" minOccurs="0"/>
 *         &lt;element name="serviceEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="nonDiscountableIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="serviceDescText" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="servicePriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="paymentFrequencyNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mandatoryDataServiceIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="easyTabAutoExpiryFinancingIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="serviceFamilyTypeCodeList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceFamilyType" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceItem", propOrder = {
    "serviceCode",
    "serviceGroupTypeCode",
    "serviceTypeCode",
    "transactionActionCode",
    "autoRenewIndicator",
    "autoRenewFundsSourceCode",
    "purchaseFundsSourceCode",
    "callingCirclePhoneNumberList",
    "featureRecommendation",
    "serviceEffectiveDate",
    "nonDiscountableIndicator",
    "serviceDescText",
    "servicePriceAmt",
    "paymentFrequencyNum",
    "mandatoryDataServiceIndicator",
    "easyTabAutoExpiryFinancingIndicator",
    "serviceFamilyTypeCodeList"
})
public class ServiceItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceCode;
    protected String serviceGroupTypeCode;
    @XmlElement(required = true)
    protected String serviceTypeCode;
    @XmlElement(required = true)
    protected String transactionActionCode;
    protected Boolean autoRenewIndicator;
    protected Integer autoRenewFundsSourceCode;
    protected Integer purchaseFundsSourceCode;
    protected CallingCirclePhoneNumberList callingCirclePhoneNumberList;
    protected FeatureRecommendation featureRecommendation;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date serviceEffectiveDate;
    protected Boolean nonDiscountableIndicator;
    protected MultilingualDescriptionList serviceDescText;
    protected Double servicePriceAmt;
    protected String paymentFrequencyNum;
    protected Boolean mandatoryDataServiceIndicator;
    protected Boolean easyTabAutoExpiryFinancingIndicator;
    protected List<ServiceFamilyType> serviceFamilyTypeCodeList;

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the serviceGroupTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceGroupTypeCode() {
        return serviceGroupTypeCode;
    }

    /**
     * Sets the value of the serviceGroupTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceGroupTypeCode(String value) {
        this.serviceGroupTypeCode = value;
    }

    /**
     * Gets the value of the serviceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    /**
     * Sets the value of the serviceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCode(String value) {
        this.serviceTypeCode = value;
    }

    /**
     * Gets the value of the transactionActionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionActionCode() {
        return transactionActionCode;
    }

    /**
     * Sets the value of the transactionActionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionActionCode(String value) {
        this.transactionActionCode = value;
    }

    /**
     * Gets the value of the autoRenewIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoRenewIndicator() {
        return autoRenewIndicator;
    }

    /**
     * Sets the value of the autoRenewIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoRenewIndicator(Boolean value) {
        this.autoRenewIndicator = value;
    }

    /**
     * Gets the value of the autoRenewFundsSourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAutoRenewFundsSourceCode() {
        return autoRenewFundsSourceCode;
    }

    /**
     * Sets the value of the autoRenewFundsSourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAutoRenewFundsSourceCode(Integer value) {
        this.autoRenewFundsSourceCode = value;
    }

    /**
     * Gets the value of the purchaseFundsSourceCode property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPurchaseFundsSourceCode() {
        return purchaseFundsSourceCode;
    }

    /**
     * Sets the value of the purchaseFundsSourceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPurchaseFundsSourceCode(Integer value) {
        this.purchaseFundsSourceCode = value;
    }

    /**
     * Gets the value of the callingCirclePhoneNumberList property.
     * 
     * @return
     *     possible object is
     *     {@link CallingCirclePhoneNumberList }
     *     
     */
    public CallingCirclePhoneNumberList getCallingCirclePhoneNumberList() {
        return callingCirclePhoneNumberList;
    }

    /**
     * Sets the value of the callingCirclePhoneNumberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallingCirclePhoneNumberList }
     *     
     */
    public void setCallingCirclePhoneNumberList(CallingCirclePhoneNumberList value) {
        this.callingCirclePhoneNumberList = value;
    }

    /**
     * Gets the value of the featureRecommendation property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureRecommendation }
     *     
     */
    public FeatureRecommendation getFeatureRecommendation() {
        return featureRecommendation;
    }

    /**
     * Sets the value of the featureRecommendation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureRecommendation }
     *     
     */
    public void setFeatureRecommendation(FeatureRecommendation value) {
        this.featureRecommendation = value;
    }

    /**
     * Gets the value of the serviceEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getServiceEffectiveDate() {
        return serviceEffectiveDate;
    }

    /**
     * Sets the value of the serviceEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEffectiveDate(Date value) {
        this.serviceEffectiveDate = value;
    }

    /**
     * Gets the value of the nonDiscountableIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonDiscountableIndicator() {
        return nonDiscountableIndicator;
    }

    /**
     * Sets the value of the nonDiscountableIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonDiscountableIndicator(Boolean value) {
        this.nonDiscountableIndicator = value;
    }

    /**
     * Gets the value of the serviceDescText property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getServiceDescText() {
        return serviceDescText;
    }

    /**
     * Sets the value of the serviceDescText property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setServiceDescText(MultilingualDescriptionList value) {
        this.serviceDescText = value;
    }

    /**
     * Gets the value of the servicePriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getServicePriceAmt() {
        return servicePriceAmt;
    }

    /**
     * Sets the value of the servicePriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setServicePriceAmt(Double value) {
        this.servicePriceAmt = value;
    }

    /**
     * Gets the value of the paymentFrequencyNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentFrequencyNum() {
        return paymentFrequencyNum;
    }

    /**
     * Sets the value of the paymentFrequencyNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentFrequencyNum(String value) {
        this.paymentFrequencyNum = value;
    }

    /**
     * Gets the value of the mandatoryDataServiceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryDataServiceIndicator() {
        return mandatoryDataServiceIndicator;
    }

    /**
     * Sets the value of the mandatoryDataServiceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryDataServiceIndicator(Boolean value) {
        this.mandatoryDataServiceIndicator = value;
    }

    /**
     * Gets the value of the easyTabAutoExpiryFinancingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEasyTabAutoExpiryFinancingIndicator() {
        return easyTabAutoExpiryFinancingIndicator;
    }

    /**
     * Sets the value of the easyTabAutoExpiryFinancingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEasyTabAutoExpiryFinancingIndicator(Boolean value) {
        this.easyTabAutoExpiryFinancingIndicator = value;
    }

    /**
     * Gets the value of the serviceFamilyTypeCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceFamilyTypeCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceFamilyTypeCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFamilyType }
     * 
     * 
     */
    public List<ServiceFamilyType> getServiceFamilyTypeCodeList() {
        if (serviceFamilyTypeCodeList == null) {
            serviceFamilyTypeCodeList = new ArrayList<ServiceFamilyType>();
        }
        return this.serviceFamilyTypeCodeList;
    }

    /**
     * Sets the value of the serviceFamilyTypeCodeList property.
     * 
     * @param serviceFamilyTypeCodeList
     *     allowed object is
     *     {@link ServiceFamilyType }
     *     
     */
    public void setServiceFamilyTypeCodeList(List<ServiceFamilyType> serviceFamilyTypeCodeList) {
        this.serviceFamilyTypeCodeList = serviceFamilyTypeCodeList;
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
            String theServiceCode;
            theServiceCode = this.getServiceCode();
            strategy.appendField(locator, this, "serviceCode", buffer, theServiceCode);
        }
        {
            String theServiceGroupTypeCode;
            theServiceGroupTypeCode = this.getServiceGroupTypeCode();
            strategy.appendField(locator, this, "serviceGroupTypeCode", buffer, theServiceGroupTypeCode);
        }
        {
            String theServiceTypeCode;
            theServiceTypeCode = this.getServiceTypeCode();
            strategy.appendField(locator, this, "serviceTypeCode", buffer, theServiceTypeCode);
        }
        {
            String theTransactionActionCode;
            theTransactionActionCode = this.getTransactionActionCode();
            strategy.appendField(locator, this, "transactionActionCode", buffer, theTransactionActionCode);
        }
        {
            Boolean theAutoRenewIndicator;
            theAutoRenewIndicator = this.isAutoRenewIndicator();
            strategy.appendField(locator, this, "autoRenewIndicator", buffer, theAutoRenewIndicator);
        }
        {
            Integer theAutoRenewFundsSourceCode;
            theAutoRenewFundsSourceCode = this.getAutoRenewFundsSourceCode();
            strategy.appendField(locator, this, "autoRenewFundsSourceCode", buffer, theAutoRenewFundsSourceCode);
        }
        {
            Integer thePurchaseFundsSourceCode;
            thePurchaseFundsSourceCode = this.getPurchaseFundsSourceCode();
            strategy.appendField(locator, this, "purchaseFundsSourceCode", buffer, thePurchaseFundsSourceCode);
        }
        {
            CallingCirclePhoneNumberList theCallingCirclePhoneNumberList;
            theCallingCirclePhoneNumberList = this.getCallingCirclePhoneNumberList();
            strategy.appendField(locator, this, "callingCirclePhoneNumberList", buffer, theCallingCirclePhoneNumberList);
        }
        {
            FeatureRecommendation theFeatureRecommendation;
            theFeatureRecommendation = this.getFeatureRecommendation();
            strategy.appendField(locator, this, "featureRecommendation", buffer, theFeatureRecommendation);
        }
        {
            Date theServiceEffectiveDate;
            theServiceEffectiveDate = this.getServiceEffectiveDate();
            strategy.appendField(locator, this, "serviceEffectiveDate", buffer, theServiceEffectiveDate);
        }
        {
            Boolean theNonDiscountableIndicator;
            theNonDiscountableIndicator = this.isNonDiscountableIndicator();
            strategy.appendField(locator, this, "nonDiscountableIndicator", buffer, theNonDiscountableIndicator);
        }
        {
            MultilingualDescriptionList theServiceDescText;
            theServiceDescText = this.getServiceDescText();
            strategy.appendField(locator, this, "serviceDescText", buffer, theServiceDescText);
        }
        {
            Double theServicePriceAmt;
            theServicePriceAmt = this.getServicePriceAmt();
            strategy.appendField(locator, this, "servicePriceAmt", buffer, theServicePriceAmt);
        }
        {
            String thePaymentFrequencyNum;
            thePaymentFrequencyNum = this.getPaymentFrequencyNum();
            strategy.appendField(locator, this, "paymentFrequencyNum", buffer, thePaymentFrequencyNum);
        }
        {
            Boolean theMandatoryDataServiceIndicator;
            theMandatoryDataServiceIndicator = this.isMandatoryDataServiceIndicator();
            strategy.appendField(locator, this, "mandatoryDataServiceIndicator", buffer, theMandatoryDataServiceIndicator);
        }
        {
            Boolean theEasyTabAutoExpiryFinancingIndicator;
            theEasyTabAutoExpiryFinancingIndicator = this.isEasyTabAutoExpiryFinancingIndicator();
            strategy.appendField(locator, this, "easyTabAutoExpiryFinancingIndicator", buffer, theEasyTabAutoExpiryFinancingIndicator);
        }
        {
            List<ServiceFamilyType> theServiceFamilyTypeCodeList;
            theServiceFamilyTypeCodeList = (((this.serviceFamilyTypeCodeList!= null)&&(!this.serviceFamilyTypeCodeList.isEmpty()))?this.getServiceFamilyTypeCodeList():null);
            strategy.appendField(locator, this, "serviceFamilyTypeCodeList", buffer, theServiceFamilyTypeCodeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceItem that = ((ServiceItem) object);
        {
            String lhsServiceCode;
            lhsServiceCode = this.getServiceCode();
            String rhsServiceCode;
            rhsServiceCode = that.getServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCode", lhsServiceCode), LocatorUtils.property(thatLocator, "serviceCode", rhsServiceCode), lhsServiceCode, rhsServiceCode)) {
                return false;
            }
        }
        {
            String lhsServiceGroupTypeCode;
            lhsServiceGroupTypeCode = this.getServiceGroupTypeCode();
            String rhsServiceGroupTypeCode;
            rhsServiceGroupTypeCode = that.getServiceGroupTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceGroupTypeCode", lhsServiceGroupTypeCode), LocatorUtils.property(thatLocator, "serviceGroupTypeCode", rhsServiceGroupTypeCode), lhsServiceGroupTypeCode, rhsServiceGroupTypeCode)) {
                return false;
            }
        }
        {
            String lhsServiceTypeCode;
            lhsServiceTypeCode = this.getServiceTypeCode();
            String rhsServiceTypeCode;
            rhsServiceTypeCode = that.getServiceTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTypeCode", lhsServiceTypeCode), LocatorUtils.property(thatLocator, "serviceTypeCode", rhsServiceTypeCode), lhsServiceTypeCode, rhsServiceTypeCode)) {
                return false;
            }
        }
        {
            String lhsTransactionActionCode;
            lhsTransactionActionCode = this.getTransactionActionCode();
            String rhsTransactionActionCode;
            rhsTransactionActionCode = that.getTransactionActionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionActionCode", lhsTransactionActionCode), LocatorUtils.property(thatLocator, "transactionActionCode", rhsTransactionActionCode), lhsTransactionActionCode, rhsTransactionActionCode)) {
                return false;
            }
        }
        {
            Boolean lhsAutoRenewIndicator;
            lhsAutoRenewIndicator = this.isAutoRenewIndicator();
            Boolean rhsAutoRenewIndicator;
            rhsAutoRenewIndicator = that.isAutoRenewIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "autoRenewIndicator", lhsAutoRenewIndicator), LocatorUtils.property(thatLocator, "autoRenewIndicator", rhsAutoRenewIndicator), lhsAutoRenewIndicator, rhsAutoRenewIndicator)) {
                return false;
            }
        }
        {
            Integer lhsAutoRenewFundsSourceCode;
            lhsAutoRenewFundsSourceCode = this.getAutoRenewFundsSourceCode();
            Integer rhsAutoRenewFundsSourceCode;
            rhsAutoRenewFundsSourceCode = that.getAutoRenewFundsSourceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "autoRenewFundsSourceCode", lhsAutoRenewFundsSourceCode), LocatorUtils.property(thatLocator, "autoRenewFundsSourceCode", rhsAutoRenewFundsSourceCode), lhsAutoRenewFundsSourceCode, rhsAutoRenewFundsSourceCode)) {
                return false;
            }
        }
        {
            Integer lhsPurchaseFundsSourceCode;
            lhsPurchaseFundsSourceCode = this.getPurchaseFundsSourceCode();
            Integer rhsPurchaseFundsSourceCode;
            rhsPurchaseFundsSourceCode = that.getPurchaseFundsSourceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseFundsSourceCode", lhsPurchaseFundsSourceCode), LocatorUtils.property(thatLocator, "purchaseFundsSourceCode", rhsPurchaseFundsSourceCode), lhsPurchaseFundsSourceCode, rhsPurchaseFundsSourceCode)) {
                return false;
            }
        }
        {
            CallingCirclePhoneNumberList lhsCallingCirclePhoneNumberList;
            lhsCallingCirclePhoneNumberList = this.getCallingCirclePhoneNumberList();
            CallingCirclePhoneNumberList rhsCallingCirclePhoneNumberList;
            rhsCallingCirclePhoneNumberList = that.getCallingCirclePhoneNumberList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingCirclePhoneNumberList", lhsCallingCirclePhoneNumberList), LocatorUtils.property(thatLocator, "callingCirclePhoneNumberList", rhsCallingCirclePhoneNumberList), lhsCallingCirclePhoneNumberList, rhsCallingCirclePhoneNumberList)) {
                return false;
            }
        }
        {
            FeatureRecommendation lhsFeatureRecommendation;
            lhsFeatureRecommendation = this.getFeatureRecommendation();
            FeatureRecommendation rhsFeatureRecommendation;
            rhsFeatureRecommendation = that.getFeatureRecommendation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureRecommendation", lhsFeatureRecommendation), LocatorUtils.property(thatLocator, "featureRecommendation", rhsFeatureRecommendation), lhsFeatureRecommendation, rhsFeatureRecommendation)) {
                return false;
            }
        }
        {
            Date lhsServiceEffectiveDate;
            lhsServiceEffectiveDate = this.getServiceEffectiveDate();
            Date rhsServiceEffectiveDate;
            rhsServiceEffectiveDate = that.getServiceEffectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEffectiveDate", lhsServiceEffectiveDate), LocatorUtils.property(thatLocator, "serviceEffectiveDate", rhsServiceEffectiveDate), lhsServiceEffectiveDate, rhsServiceEffectiveDate)) {
                return false;
            }
        }
        {
            Boolean lhsNonDiscountableIndicator;
            lhsNonDiscountableIndicator = this.isNonDiscountableIndicator();
            Boolean rhsNonDiscountableIndicator;
            rhsNonDiscountableIndicator = that.isNonDiscountableIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nonDiscountableIndicator", lhsNonDiscountableIndicator), LocatorUtils.property(thatLocator, "nonDiscountableIndicator", rhsNonDiscountableIndicator), lhsNonDiscountableIndicator, rhsNonDiscountableIndicator)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsServiceDescText;
            lhsServiceDescText = this.getServiceDescText();
            MultilingualDescriptionList rhsServiceDescText;
            rhsServiceDescText = that.getServiceDescText();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceDescText", lhsServiceDescText), LocatorUtils.property(thatLocator, "serviceDescText", rhsServiceDescText), lhsServiceDescText, rhsServiceDescText)) {
                return false;
            }
        }
        {
            Double lhsServicePriceAmt;
            lhsServicePriceAmt = this.getServicePriceAmt();
            Double rhsServicePriceAmt;
            rhsServicePriceAmt = that.getServicePriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "servicePriceAmt", lhsServicePriceAmt), LocatorUtils.property(thatLocator, "servicePriceAmt", rhsServicePriceAmt), lhsServicePriceAmt, rhsServicePriceAmt)) {
                return false;
            }
        }
        {
            String lhsPaymentFrequencyNum;
            lhsPaymentFrequencyNum = this.getPaymentFrequencyNum();
            String rhsPaymentFrequencyNum;
            rhsPaymentFrequencyNum = that.getPaymentFrequencyNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentFrequencyNum", lhsPaymentFrequencyNum), LocatorUtils.property(thatLocator, "paymentFrequencyNum", rhsPaymentFrequencyNum), lhsPaymentFrequencyNum, rhsPaymentFrequencyNum)) {
                return false;
            }
        }
        {
            Boolean lhsMandatoryDataServiceIndicator;
            lhsMandatoryDataServiceIndicator = this.isMandatoryDataServiceIndicator();
            Boolean rhsMandatoryDataServiceIndicator;
            rhsMandatoryDataServiceIndicator = that.isMandatoryDataServiceIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mandatoryDataServiceIndicator", lhsMandatoryDataServiceIndicator), LocatorUtils.property(thatLocator, "mandatoryDataServiceIndicator", rhsMandatoryDataServiceIndicator), lhsMandatoryDataServiceIndicator, rhsMandatoryDataServiceIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsEasyTabAutoExpiryFinancingIndicator;
            lhsEasyTabAutoExpiryFinancingIndicator = this.isEasyTabAutoExpiryFinancingIndicator();
            Boolean rhsEasyTabAutoExpiryFinancingIndicator;
            rhsEasyTabAutoExpiryFinancingIndicator = that.isEasyTabAutoExpiryFinancingIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "easyTabAutoExpiryFinancingIndicator", lhsEasyTabAutoExpiryFinancingIndicator), LocatorUtils.property(thatLocator, "easyTabAutoExpiryFinancingIndicator", rhsEasyTabAutoExpiryFinancingIndicator), lhsEasyTabAutoExpiryFinancingIndicator, rhsEasyTabAutoExpiryFinancingIndicator)) {
                return false;
            }
        }
        {
            List<ServiceFamilyType> lhsServiceFamilyTypeCodeList;
            lhsServiceFamilyTypeCodeList = (((this.serviceFamilyTypeCodeList!= null)&&(!this.serviceFamilyTypeCodeList.isEmpty()))?this.getServiceFamilyTypeCodeList():null);
            List<ServiceFamilyType> rhsServiceFamilyTypeCodeList;
            rhsServiceFamilyTypeCodeList = (((that.serviceFamilyTypeCodeList!= null)&&(!that.serviceFamilyTypeCodeList.isEmpty()))?that.getServiceFamilyTypeCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceFamilyTypeCodeList", lhsServiceFamilyTypeCodeList), LocatorUtils.property(thatLocator, "serviceFamilyTypeCodeList", rhsServiceFamilyTypeCodeList), lhsServiceFamilyTypeCodeList, rhsServiceFamilyTypeCodeList)) {
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
