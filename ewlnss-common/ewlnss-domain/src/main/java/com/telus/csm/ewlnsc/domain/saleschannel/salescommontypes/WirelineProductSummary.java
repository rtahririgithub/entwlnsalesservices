
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
 * <p>Java class for WirelineProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productLine" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesProductLine" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicePlanCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recurringPayChannelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oneTimePayChannelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productInstance">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="productInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="serviceInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="depositAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="productCatalogId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="productSuppressionInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="termCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="offerCatalogId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="includedForDepositCalculationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="singleLineComponent" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="subscriptionNumberList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="subscriptionNumber" maxOccurs="10">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="singleLineNumberListedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="callingFeatureList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="callingFeature" maxOccurs="100">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="callingFeatureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="callingFeatureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="tollPlanList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="tollPlan" maxOccurs="100">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="tollCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="tollPlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                             &lt;element name="wholesaleAdslInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                             &lt;element name="featuresPackList" minOccurs="0">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="featuresPack" maxOccurs="100">
 *                                         &lt;complexType>
 *                                           &lt;complexContent>
 *                                             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                               &lt;sequence>
 *                                                 &lt;element name="featuresPackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                                 &lt;element name="featuresPackCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                               &lt;/sequence>
 *                                             &lt;/restriction>
 *                                           &lt;/complexContent>
 *                                         &lt;/complexType>
 *                                       &lt;/element>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="internetComponent" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="internetTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="ttvComponent" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="hdChannelInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                   &lt;element name="equipmentList" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="equipment" maxOccurs="100">
 *                               &lt;complexType>
 *                                 &lt;complexContent>
 *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                                     &lt;sequence>
 *                                       &lt;element name="equipmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                       &lt;element name="equipmentAcquisitionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                                     &lt;/sequence>
 *                                   &lt;/restriction>
 *                                 &lt;/complexContent>
 *                               &lt;/complexType>
 *                             &lt;/element>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="subscriptionStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productInPendingOrderStatusInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="actionTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="servicePhoneNumberList" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="servicePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineProductSummary", propOrder = {
    "productLine",
    "productType",
    "serviceType",
    "servicePlanCd",
    "recurringPayChannelNumber",
    "oneTimePayChannelNumber",
    "productInstance",
    "subscriptionStatusCode",
    "productInPendingOrderStatusInd",
    "actionTypeCd",
    "serviceAddress",
    "servicePhoneNumberList",
    "customerId"
})
public class WirelineProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected SalesProductLine productLine;
    @XmlElement(required = true)
    protected String productType;
    protected String serviceType;
    protected String servicePlanCd;
    protected String recurringPayChannelNumber;
    protected String oneTimePayChannelNumber;
    @XmlElement(required = true)
    protected WirelineProductSummary.ProductInstance productInstance;
    protected String subscriptionStatusCode;
    protected Boolean productInPendingOrderStatusInd;
    protected String actionTypeCd;
    protected Address serviceAddress;
    protected WirelineProductSummary.ServicePhoneNumberList servicePhoneNumberList;
    protected String customerId;

    /**
     * Gets the value of the productLine property.
     * 
     * @return
     *     possible object is
     *     {@link SalesProductLine }
     *     
     */
    public SalesProductLine getProductLine() {
        return productLine;
    }

    /**
     * Sets the value of the productLine property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesProductLine }
     *     
     */
    public void setProductLine(SalesProductLine value) {
        this.productLine = value;
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
     * Gets the value of the serviceType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * Sets the value of the serviceType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceType(String value) {
        this.serviceType = value;
    }

    /**
     * Gets the value of the servicePlanCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePlanCd() {
        return servicePlanCd;
    }

    /**
     * Sets the value of the servicePlanCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePlanCd(String value) {
        this.servicePlanCd = value;
    }

    /**
     * Gets the value of the recurringPayChannelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecurringPayChannelNumber() {
        return recurringPayChannelNumber;
    }

    /**
     * Sets the value of the recurringPayChannelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecurringPayChannelNumber(String value) {
        this.recurringPayChannelNumber = value;
    }

    /**
     * Gets the value of the oneTimePayChannelNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOneTimePayChannelNumber() {
        return oneTimePayChannelNumber;
    }

    /**
     * Sets the value of the oneTimePayChannelNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOneTimePayChannelNumber(String value) {
        this.oneTimePayChannelNumber = value;
    }

    /**
     * Gets the value of the productInstance property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineProductSummary.ProductInstance }
     *     
     */
    public WirelineProductSummary.ProductInstance getProductInstance() {
        return productInstance;
    }

    /**
     * Sets the value of the productInstance property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineProductSummary.ProductInstance }
     *     
     */
    public void setProductInstance(WirelineProductSummary.ProductInstance value) {
        this.productInstance = value;
    }

    /**
     * Gets the value of the subscriptionStatusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriptionStatusCode() {
        return subscriptionStatusCode;
    }

    /**
     * Sets the value of the subscriptionStatusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriptionStatusCode(String value) {
        this.subscriptionStatusCode = value;
    }

    /**
     * Gets the value of the productInPendingOrderStatusInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isProductInPendingOrderStatusInd() {
        return productInPendingOrderStatusInd;
    }

    /**
     * Sets the value of the productInPendingOrderStatusInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setProductInPendingOrderStatusInd(Boolean value) {
        this.productInPendingOrderStatusInd = value;
    }

    /**
     * Gets the value of the actionTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionTypeCd() {
        return actionTypeCd;
    }

    /**
     * Sets the value of the actionTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionTypeCd(String value) {
        this.actionTypeCd = value;
    }

    /**
     * Gets the value of the serviceAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getServiceAddress() {
        return serviceAddress;
    }

    /**
     * Sets the value of the serviceAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setServiceAddress(Address value) {
        this.serviceAddress = value;
    }

    /**
     * Gets the value of the servicePhoneNumberList property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineProductSummary.ServicePhoneNumberList }
     *     
     */
    public WirelineProductSummary.ServicePhoneNumberList getServicePhoneNumberList() {
        return servicePhoneNumberList;
    }

    /**
     * Sets the value of the servicePhoneNumberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineProductSummary.ServicePhoneNumberList }
     *     
     */
    public void setServicePhoneNumberList(WirelineProductSummary.ServicePhoneNumberList value) {
        this.servicePhoneNumberList = value;
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
            SalesProductLine theProductLine;
            theProductLine = this.getProductLine();
            strategy.appendField(locator, this, "productLine", buffer, theProductLine);
        }
        {
            String theProductType;
            theProductType = this.getProductType();
            strategy.appendField(locator, this, "productType", buffer, theProductType);
        }
        {
            String theServiceType;
            theServiceType = this.getServiceType();
            strategy.appendField(locator, this, "serviceType", buffer, theServiceType);
        }
        {
            String theServicePlanCd;
            theServicePlanCd = this.getServicePlanCd();
            strategy.appendField(locator, this, "servicePlanCd", buffer, theServicePlanCd);
        }
        {
            String theRecurringPayChannelNumber;
            theRecurringPayChannelNumber = this.getRecurringPayChannelNumber();
            strategy.appendField(locator, this, "recurringPayChannelNumber", buffer, theRecurringPayChannelNumber);
        }
        {
            String theOneTimePayChannelNumber;
            theOneTimePayChannelNumber = this.getOneTimePayChannelNumber();
            strategy.appendField(locator, this, "oneTimePayChannelNumber", buffer, theOneTimePayChannelNumber);
        }
        {
            WirelineProductSummary.ProductInstance theProductInstance;
            theProductInstance = this.getProductInstance();
            strategy.appendField(locator, this, "productInstance", buffer, theProductInstance);
        }
        {
            String theSubscriptionStatusCode;
            theSubscriptionStatusCode = this.getSubscriptionStatusCode();
            strategy.appendField(locator, this, "subscriptionStatusCode", buffer, theSubscriptionStatusCode);
        }
        {
            Boolean theProductInPendingOrderStatusInd;
            theProductInPendingOrderStatusInd = this.isProductInPendingOrderStatusInd();
            strategy.appendField(locator, this, "productInPendingOrderStatusInd", buffer, theProductInPendingOrderStatusInd);
        }
        {
            String theActionTypeCd;
            theActionTypeCd = this.getActionTypeCd();
            strategy.appendField(locator, this, "actionTypeCd", buffer, theActionTypeCd);
        }
        {
            Address theServiceAddress;
            theServiceAddress = this.getServiceAddress();
            strategy.appendField(locator, this, "serviceAddress", buffer, theServiceAddress);
        }
        {
            WirelineProductSummary.ServicePhoneNumberList theServicePhoneNumberList;
            theServicePhoneNumberList = this.getServicePhoneNumberList();
            strategy.appendField(locator, this, "servicePhoneNumberList", buffer, theServicePhoneNumberList);
        }
        {
            String theCustomerId;
            theCustomerId = this.getCustomerId();
            strategy.appendField(locator, this, "customerId", buffer, theCustomerId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineProductSummary that = ((WirelineProductSummary) object);
        {
            SalesProductLine lhsProductLine;
            lhsProductLine = this.getProductLine();
            SalesProductLine rhsProductLine;
            rhsProductLine = that.getProductLine();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productLine", lhsProductLine), LocatorUtils.property(thatLocator, "productLine", rhsProductLine), lhsProductLine, rhsProductLine)) {
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
            String lhsServiceType;
            lhsServiceType = this.getServiceType();
            String rhsServiceType;
            rhsServiceType = that.getServiceType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceType", lhsServiceType), LocatorUtils.property(thatLocator, "serviceType", rhsServiceType), lhsServiceType, rhsServiceType)) {
                return false;
            }
        }
        {
            String lhsServicePlanCd;
            lhsServicePlanCd = this.getServicePlanCd();
            String rhsServicePlanCd;
            rhsServicePlanCd = that.getServicePlanCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "servicePlanCd", lhsServicePlanCd), LocatorUtils.property(thatLocator, "servicePlanCd", rhsServicePlanCd), lhsServicePlanCd, rhsServicePlanCd)) {
                return false;
            }
        }
        {
            String lhsRecurringPayChannelNumber;
            lhsRecurringPayChannelNumber = this.getRecurringPayChannelNumber();
            String rhsRecurringPayChannelNumber;
            rhsRecurringPayChannelNumber = that.getRecurringPayChannelNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recurringPayChannelNumber", lhsRecurringPayChannelNumber), LocatorUtils.property(thatLocator, "recurringPayChannelNumber", rhsRecurringPayChannelNumber), lhsRecurringPayChannelNumber, rhsRecurringPayChannelNumber)) {
                return false;
            }
        }
        {
            String lhsOneTimePayChannelNumber;
            lhsOneTimePayChannelNumber = this.getOneTimePayChannelNumber();
            String rhsOneTimePayChannelNumber;
            rhsOneTimePayChannelNumber = that.getOneTimePayChannelNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oneTimePayChannelNumber", lhsOneTimePayChannelNumber), LocatorUtils.property(thatLocator, "oneTimePayChannelNumber", rhsOneTimePayChannelNumber), lhsOneTimePayChannelNumber, rhsOneTimePayChannelNumber)) {
                return false;
            }
        }
        {
            WirelineProductSummary.ProductInstance lhsProductInstance;
            lhsProductInstance = this.getProductInstance();
            WirelineProductSummary.ProductInstance rhsProductInstance;
            rhsProductInstance = that.getProductInstance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productInstance", lhsProductInstance), LocatorUtils.property(thatLocator, "productInstance", rhsProductInstance), lhsProductInstance, rhsProductInstance)) {
                return false;
            }
        }
        {
            String lhsSubscriptionStatusCode;
            lhsSubscriptionStatusCode = this.getSubscriptionStatusCode();
            String rhsSubscriptionStatusCode;
            rhsSubscriptionStatusCode = that.getSubscriptionStatusCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriptionStatusCode", lhsSubscriptionStatusCode), LocatorUtils.property(thatLocator, "subscriptionStatusCode", rhsSubscriptionStatusCode), lhsSubscriptionStatusCode, rhsSubscriptionStatusCode)) {
                return false;
            }
        }
        {
            Boolean lhsProductInPendingOrderStatusInd;
            lhsProductInPendingOrderStatusInd = this.isProductInPendingOrderStatusInd();
            Boolean rhsProductInPendingOrderStatusInd;
            rhsProductInPendingOrderStatusInd = that.isProductInPendingOrderStatusInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productInPendingOrderStatusInd", lhsProductInPendingOrderStatusInd), LocatorUtils.property(thatLocator, "productInPendingOrderStatusInd", rhsProductInPendingOrderStatusInd), lhsProductInPendingOrderStatusInd, rhsProductInPendingOrderStatusInd)) {
                return false;
            }
        }
        {
            String lhsActionTypeCd;
            lhsActionTypeCd = this.getActionTypeCd();
            String rhsActionTypeCd;
            rhsActionTypeCd = that.getActionTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "actionTypeCd", lhsActionTypeCd), LocatorUtils.property(thatLocator, "actionTypeCd", rhsActionTypeCd), lhsActionTypeCd, rhsActionTypeCd)) {
                return false;
            }
        }
        {
            Address lhsServiceAddress;
            lhsServiceAddress = this.getServiceAddress();
            Address rhsServiceAddress;
            rhsServiceAddress = that.getServiceAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddress", lhsServiceAddress), LocatorUtils.property(thatLocator, "serviceAddress", rhsServiceAddress), lhsServiceAddress, rhsServiceAddress)) {
                return false;
            }
        }
        {
            WirelineProductSummary.ServicePhoneNumberList lhsServicePhoneNumberList;
            lhsServicePhoneNumberList = this.getServicePhoneNumberList();
            WirelineProductSummary.ServicePhoneNumberList rhsServicePhoneNumberList;
            rhsServicePhoneNumberList = that.getServicePhoneNumberList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "servicePhoneNumberList", lhsServicePhoneNumberList), LocatorUtils.property(thatLocator, "servicePhoneNumberList", rhsServicePhoneNumberList), lhsServicePhoneNumberList, rhsServicePhoneNumberList)) {
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
     *         &lt;element name="productInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="serviceInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="depositAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="productCatalogId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="productSuppressionInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="termCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="offerCatalogId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="includedForDepositCalculationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="singleLineComponent" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="subscriptionNumberList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="subscriptionNumber" maxOccurs="10">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="singleLineNumberListedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="callingFeatureList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="callingFeature" maxOccurs="100">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="callingFeatureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="callingFeatureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="tollPlanList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="tollPlan" maxOccurs="100">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="tollCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="tollPlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                   &lt;element name="wholesaleAdslInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                   &lt;element name="featuresPackList" minOccurs="0">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="featuresPack" maxOccurs="100">
     *                               &lt;complexType>
     *                                 &lt;complexContent>
     *                                   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                                     &lt;sequence>
     *                                       &lt;element name="featuresPackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                       &lt;element name="featuresPackCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                                     &lt;/sequence>
     *                                   &lt;/restriction>
     *                                 &lt;/complexContent>
     *                               &lt;/complexType>
     *                             &lt;/element>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="internetComponent" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="internetTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="ttvComponent" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="hdChannelInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *         &lt;element name="equipmentList" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="equipment" maxOccurs="100">
     *                     &lt;complexType>
     *                       &lt;complexContent>
     *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                           &lt;sequence>
     *                             &lt;element name="equipmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                             &lt;element name="equipmentAcquisitionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *                           &lt;/sequence>
     *                         &lt;/restriction>
     *                       &lt;/complexContent>
     *                     &lt;/complexType>
     *                   &lt;/element>
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
    @XmlType(name = "", propOrder = {
        "productInstanceId",
        "serviceId",
        "serviceInstanceId",
        "productName",
        "depositAmount",
        "resourceId",
        "productCatalogId",
        "productSuppressionInd",
        "termCd",
        "offerCatalogId",
        "includedForDepositCalculationInd",
        "singleLineComponent",
        "internetComponent",
        "ttvComponent",
        "equipmentList"
    })
    public static class ProductInstance
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String productInstanceId;
        protected String serviceId;
        protected String serviceInstanceId;
        protected String productName;
        protected Double depositAmount;
        protected String resourceId;
        protected String productCatalogId;
        protected Boolean productSuppressionInd;
        protected String termCd;
        protected String offerCatalogId;
        protected Boolean includedForDepositCalculationInd;
        protected WirelineProductSummary.ProductInstance.SingleLineComponent singleLineComponent;
        protected WirelineProductSummary.ProductInstance.InternetComponent internetComponent;
        protected WirelineProductSummary.ProductInstance.TtvComponent ttvComponent;
        protected WirelineProductSummary.ProductInstance.EquipmentList equipmentList;

        /**
         * Gets the value of the productInstanceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductInstanceId() {
            return productInstanceId;
        }

        /**
         * Sets the value of the productInstanceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductInstanceId(String value) {
            this.productInstanceId = value;
        }

        /**
         * Gets the value of the serviceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceId() {
            return serviceId;
        }

        /**
         * Sets the value of the serviceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceId(String value) {
            this.serviceId = value;
        }

        /**
         * Gets the value of the serviceInstanceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getServiceInstanceId() {
            return serviceInstanceId;
        }

        /**
         * Sets the value of the serviceInstanceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServiceInstanceId(String value) {
            this.serviceInstanceId = value;
        }

        /**
         * Gets the value of the productName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductName() {
            return productName;
        }

        /**
         * Sets the value of the productName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductName(String value) {
            this.productName = value;
        }

        /**
         * Gets the value of the depositAmount property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getDepositAmount() {
            return depositAmount;
        }

        /**
         * Sets the value of the depositAmount property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setDepositAmount(Double value) {
            this.depositAmount = value;
        }

        /**
         * Gets the value of the resourceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResourceId() {
            return resourceId;
        }

        /**
         * Sets the value of the resourceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResourceId(String value) {
            this.resourceId = value;
        }

        /**
         * Gets the value of the productCatalogId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductCatalogId() {
            return productCatalogId;
        }

        /**
         * Sets the value of the productCatalogId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductCatalogId(String value) {
            this.productCatalogId = value;
        }

        /**
         * Gets the value of the productSuppressionInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isProductSuppressionInd() {
            return productSuppressionInd;
        }

        /**
         * Sets the value of the productSuppressionInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setProductSuppressionInd(Boolean value) {
            this.productSuppressionInd = value;
        }

        /**
         * Gets the value of the termCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTermCd() {
            return termCd;
        }

        /**
         * Sets the value of the termCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTermCd(String value) {
            this.termCd = value;
        }

        /**
         * Gets the value of the offerCatalogId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOfferCatalogId() {
            return offerCatalogId;
        }

        /**
         * Sets the value of the offerCatalogId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOfferCatalogId(String value) {
            this.offerCatalogId = value;
        }

        /**
         * Gets the value of the includedForDepositCalculationInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isIncludedForDepositCalculationInd() {
            return includedForDepositCalculationInd;
        }

        /**
         * Sets the value of the includedForDepositCalculationInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setIncludedForDepositCalculationInd(Boolean value) {
            this.includedForDepositCalculationInd = value;
        }

        /**
         * Gets the value of the singleLineComponent property.
         * 
         * @return
         *     possible object is
         *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent }
         *     
         */
        public WirelineProductSummary.ProductInstance.SingleLineComponent getSingleLineComponent() {
            return singleLineComponent;
        }

        /**
         * Sets the value of the singleLineComponent property.
         * 
         * @param value
         *     allowed object is
         *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent }
         *     
         */
        public void setSingleLineComponent(WirelineProductSummary.ProductInstance.SingleLineComponent value) {
            this.singleLineComponent = value;
        }

        /**
         * Gets the value of the internetComponent property.
         * 
         * @return
         *     possible object is
         *     {@link WirelineProductSummary.ProductInstance.InternetComponent }
         *     
         */
        public WirelineProductSummary.ProductInstance.InternetComponent getInternetComponent() {
            return internetComponent;
        }

        /**
         * Sets the value of the internetComponent property.
         * 
         * @param value
         *     allowed object is
         *     {@link WirelineProductSummary.ProductInstance.InternetComponent }
         *     
         */
        public void setInternetComponent(WirelineProductSummary.ProductInstance.InternetComponent value) {
            this.internetComponent = value;
        }

        /**
         * Gets the value of the ttvComponent property.
         * 
         * @return
         *     possible object is
         *     {@link WirelineProductSummary.ProductInstance.TtvComponent }
         *     
         */
        public WirelineProductSummary.ProductInstance.TtvComponent getTtvComponent() {
            return ttvComponent;
        }

        /**
         * Sets the value of the ttvComponent property.
         * 
         * @param value
         *     allowed object is
         *     {@link WirelineProductSummary.ProductInstance.TtvComponent }
         *     
         */
        public void setTtvComponent(WirelineProductSummary.ProductInstance.TtvComponent value) {
            this.ttvComponent = value;
        }

        /**
         * Gets the value of the equipmentList property.
         * 
         * @return
         *     possible object is
         *     {@link WirelineProductSummary.ProductInstance.EquipmentList }
         *     
         */
        public WirelineProductSummary.ProductInstance.EquipmentList getEquipmentList() {
            return equipmentList;
        }

        /**
         * Sets the value of the equipmentList property.
         * 
         * @param value
         *     allowed object is
         *     {@link WirelineProductSummary.ProductInstance.EquipmentList }
         *     
         */
        public void setEquipmentList(WirelineProductSummary.ProductInstance.EquipmentList value) {
            this.equipmentList = value;
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
                String theProductInstanceId;
                theProductInstanceId = this.getProductInstanceId();
                strategy.appendField(locator, this, "productInstanceId", buffer, theProductInstanceId);
            }
            {
                String theServiceId;
                theServiceId = this.getServiceId();
                strategy.appendField(locator, this, "serviceId", buffer, theServiceId);
            }
            {
                String theServiceInstanceId;
                theServiceInstanceId = this.getServiceInstanceId();
                strategy.appendField(locator, this, "serviceInstanceId", buffer, theServiceInstanceId);
            }
            {
                String theProductName;
                theProductName = this.getProductName();
                strategy.appendField(locator, this, "productName", buffer, theProductName);
            }
            {
                Double theDepositAmount;
                theDepositAmount = this.getDepositAmount();
                strategy.appendField(locator, this, "depositAmount", buffer, theDepositAmount);
            }
            {
                String theResourceId;
                theResourceId = this.getResourceId();
                strategy.appendField(locator, this, "resourceId", buffer, theResourceId);
            }
            {
                String theProductCatalogId;
                theProductCatalogId = this.getProductCatalogId();
                strategy.appendField(locator, this, "productCatalogId", buffer, theProductCatalogId);
            }
            {
                Boolean theProductSuppressionInd;
                theProductSuppressionInd = this.isProductSuppressionInd();
                strategy.appendField(locator, this, "productSuppressionInd", buffer, theProductSuppressionInd);
            }
            {
                String theTermCd;
                theTermCd = this.getTermCd();
                strategy.appendField(locator, this, "termCd", buffer, theTermCd);
            }
            {
                String theOfferCatalogId;
                theOfferCatalogId = this.getOfferCatalogId();
                strategy.appendField(locator, this, "offerCatalogId", buffer, theOfferCatalogId);
            }
            {
                Boolean theIncludedForDepositCalculationInd;
                theIncludedForDepositCalculationInd = this.isIncludedForDepositCalculationInd();
                strategy.appendField(locator, this, "includedForDepositCalculationInd", buffer, theIncludedForDepositCalculationInd);
            }
            {
                WirelineProductSummary.ProductInstance.SingleLineComponent theSingleLineComponent;
                theSingleLineComponent = this.getSingleLineComponent();
                strategy.appendField(locator, this, "singleLineComponent", buffer, theSingleLineComponent);
            }
            {
                WirelineProductSummary.ProductInstance.InternetComponent theInternetComponent;
                theInternetComponent = this.getInternetComponent();
                strategy.appendField(locator, this, "internetComponent", buffer, theInternetComponent);
            }
            {
                WirelineProductSummary.ProductInstance.TtvComponent theTtvComponent;
                theTtvComponent = this.getTtvComponent();
                strategy.appendField(locator, this, "ttvComponent", buffer, theTtvComponent);
            }
            {
                WirelineProductSummary.ProductInstance.EquipmentList theEquipmentList;
                theEquipmentList = this.getEquipmentList();
                strategy.appendField(locator, this, "equipmentList", buffer, theEquipmentList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof WirelineProductSummary.ProductInstance)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final WirelineProductSummary.ProductInstance that = ((WirelineProductSummary.ProductInstance) object);
            {
                String lhsProductInstanceId;
                lhsProductInstanceId = this.getProductInstanceId();
                String rhsProductInstanceId;
                rhsProductInstanceId = that.getProductInstanceId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productInstanceId", lhsProductInstanceId), LocatorUtils.property(thatLocator, "productInstanceId", rhsProductInstanceId), lhsProductInstanceId, rhsProductInstanceId)) {
                    return false;
                }
            }
            {
                String lhsServiceId;
                lhsServiceId = this.getServiceId();
                String rhsServiceId;
                rhsServiceId = that.getServiceId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceId", lhsServiceId), LocatorUtils.property(thatLocator, "serviceId", rhsServiceId), lhsServiceId, rhsServiceId)) {
                    return false;
                }
            }
            {
                String lhsServiceInstanceId;
                lhsServiceInstanceId = this.getServiceInstanceId();
                String rhsServiceInstanceId;
                rhsServiceInstanceId = that.getServiceInstanceId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceInstanceId", lhsServiceInstanceId), LocatorUtils.property(thatLocator, "serviceInstanceId", rhsServiceInstanceId), lhsServiceInstanceId, rhsServiceInstanceId)) {
                    return false;
                }
            }
            {
                String lhsProductName;
                lhsProductName = this.getProductName();
                String rhsProductName;
                rhsProductName = that.getProductName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productName", lhsProductName), LocatorUtils.property(thatLocator, "productName", rhsProductName), lhsProductName, rhsProductName)) {
                    return false;
                }
            }
            {
                Double lhsDepositAmount;
                lhsDepositAmount = this.getDepositAmount();
                Double rhsDepositAmount;
                rhsDepositAmount = that.getDepositAmount();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "depositAmount", lhsDepositAmount), LocatorUtils.property(thatLocator, "depositAmount", rhsDepositAmount), lhsDepositAmount, rhsDepositAmount)) {
                    return false;
                }
            }
            {
                String lhsResourceId;
                lhsResourceId = this.getResourceId();
                String rhsResourceId;
                rhsResourceId = that.getResourceId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "resourceId", lhsResourceId), LocatorUtils.property(thatLocator, "resourceId", rhsResourceId), lhsResourceId, rhsResourceId)) {
                    return false;
                }
            }
            {
                String lhsProductCatalogId;
                lhsProductCatalogId = this.getProductCatalogId();
                String rhsProductCatalogId;
                rhsProductCatalogId = that.getProductCatalogId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogId", lhsProductCatalogId), LocatorUtils.property(thatLocator, "productCatalogId", rhsProductCatalogId), lhsProductCatalogId, rhsProductCatalogId)) {
                    return false;
                }
            }
            {
                Boolean lhsProductSuppressionInd;
                lhsProductSuppressionInd = this.isProductSuppressionInd();
                Boolean rhsProductSuppressionInd;
                rhsProductSuppressionInd = that.isProductSuppressionInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productSuppressionInd", lhsProductSuppressionInd), LocatorUtils.property(thatLocator, "productSuppressionInd", rhsProductSuppressionInd), lhsProductSuppressionInd, rhsProductSuppressionInd)) {
                    return false;
                }
            }
            {
                String lhsTermCd;
                lhsTermCd = this.getTermCd();
                String rhsTermCd;
                rhsTermCd = that.getTermCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "termCd", lhsTermCd), LocatorUtils.property(thatLocator, "termCd", rhsTermCd), lhsTermCd, rhsTermCd)) {
                    return false;
                }
            }
            {
                String lhsOfferCatalogId;
                lhsOfferCatalogId = this.getOfferCatalogId();
                String rhsOfferCatalogId;
                rhsOfferCatalogId = that.getOfferCatalogId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCatalogId", lhsOfferCatalogId), LocatorUtils.property(thatLocator, "offerCatalogId", rhsOfferCatalogId), lhsOfferCatalogId, rhsOfferCatalogId)) {
                    return false;
                }
            }
            {
                Boolean lhsIncludedForDepositCalculationInd;
                lhsIncludedForDepositCalculationInd = this.isIncludedForDepositCalculationInd();
                Boolean rhsIncludedForDepositCalculationInd;
                rhsIncludedForDepositCalculationInd = that.isIncludedForDepositCalculationInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "includedForDepositCalculationInd", lhsIncludedForDepositCalculationInd), LocatorUtils.property(thatLocator, "includedForDepositCalculationInd", rhsIncludedForDepositCalculationInd), lhsIncludedForDepositCalculationInd, rhsIncludedForDepositCalculationInd)) {
                    return false;
                }
            }
            {
                WirelineProductSummary.ProductInstance.SingleLineComponent lhsSingleLineComponent;
                lhsSingleLineComponent = this.getSingleLineComponent();
                WirelineProductSummary.ProductInstance.SingleLineComponent rhsSingleLineComponent;
                rhsSingleLineComponent = that.getSingleLineComponent();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "singleLineComponent", lhsSingleLineComponent), LocatorUtils.property(thatLocator, "singleLineComponent", rhsSingleLineComponent), lhsSingleLineComponent, rhsSingleLineComponent)) {
                    return false;
                }
            }
            {
                WirelineProductSummary.ProductInstance.InternetComponent lhsInternetComponent;
                lhsInternetComponent = this.getInternetComponent();
                WirelineProductSummary.ProductInstance.InternetComponent rhsInternetComponent;
                rhsInternetComponent = that.getInternetComponent();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "internetComponent", lhsInternetComponent), LocatorUtils.property(thatLocator, "internetComponent", rhsInternetComponent), lhsInternetComponent, rhsInternetComponent)) {
                    return false;
                }
            }
            {
                WirelineProductSummary.ProductInstance.TtvComponent lhsTtvComponent;
                lhsTtvComponent = this.getTtvComponent();
                WirelineProductSummary.ProductInstance.TtvComponent rhsTtvComponent;
                rhsTtvComponent = that.getTtvComponent();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "ttvComponent", lhsTtvComponent), LocatorUtils.property(thatLocator, "ttvComponent", rhsTtvComponent), lhsTtvComponent, rhsTtvComponent)) {
                    return false;
                }
            }
            {
                WirelineProductSummary.ProductInstance.EquipmentList lhsEquipmentList;
                lhsEquipmentList = this.getEquipmentList();
                WirelineProductSummary.ProductInstance.EquipmentList rhsEquipmentList;
                rhsEquipmentList = that.getEquipmentList();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentList", lhsEquipmentList), LocatorUtils.property(thatLocator, "equipmentList", rhsEquipmentList), lhsEquipmentList, rhsEquipmentList)) {
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
         *         &lt;element name="equipment" maxOccurs="100">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="equipmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                   &lt;element name="equipmentAcquisitionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        @XmlType(name = "", propOrder = {
            "equipment"
        })
        public static class EquipmentList
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            @XmlElement(required = true)
            protected List<WirelineProductSummary.ProductInstance.EquipmentList.Equipment> equipment;

            /**
             * Gets the value of the equipment property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the equipment property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getEquipment().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link WirelineProductSummary.ProductInstance.EquipmentList.Equipment }
             * 
             * 
             */
            public List<WirelineProductSummary.ProductInstance.EquipmentList.Equipment> getEquipment() {
                if (equipment == null) {
                    equipment = new ArrayList<WirelineProductSummary.ProductInstance.EquipmentList.Equipment>();
                }
                return this.equipment;
            }

            /**
             * Sets the value of the equipment property.
             * 
             * @param equipment
             *     allowed object is
             *     {@link WirelineProductSummary.ProductInstance.EquipmentList.Equipment }
             *     
             */
            public void setEquipment(List<WirelineProductSummary.ProductInstance.EquipmentList.Equipment> equipment) {
                this.equipment = equipment;
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
                    List<WirelineProductSummary.ProductInstance.EquipmentList.Equipment> theEquipment;
                    theEquipment = (((this.equipment!= null)&&(!this.equipment.isEmpty()))?this.getEquipment():null);
                    strategy.appendField(locator, this, "equipment", buffer, theEquipment);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof WirelineProductSummary.ProductInstance.EquipmentList)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final WirelineProductSummary.ProductInstance.EquipmentList that = ((WirelineProductSummary.ProductInstance.EquipmentList) object);
                {
                    List<WirelineProductSummary.ProductInstance.EquipmentList.Equipment> lhsEquipment;
                    lhsEquipment = (((this.equipment!= null)&&(!this.equipment.isEmpty()))?this.getEquipment():null);
                    List<WirelineProductSummary.ProductInstance.EquipmentList.Equipment> rhsEquipment;
                    rhsEquipment = (((that.equipment!= null)&&(!that.equipment.isEmpty()))?that.getEquipment():null);
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "equipment", lhsEquipment), LocatorUtils.property(thatLocator, "equipment", rhsEquipment), lhsEquipment, rhsEquipment)) {
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
             *         &lt;element name="equipmentName" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *         &lt;element name="equipmentAcquisitionType" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                "equipmentName",
                "equipmentAcquisitionType"
            })
            public static class Equipment
                implements Serializable, Equals, ToString
            {

                private final static long serialVersionUID = 2L;
                @XmlElement(required = true)
                protected String equipmentName;
                @XmlElement(required = true)
                protected String equipmentAcquisitionType;

                /**
                 * Gets the value of the equipmentName property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEquipmentName() {
                    return equipmentName;
                }

                /**
                 * Sets the value of the equipmentName property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEquipmentName(String value) {
                    this.equipmentName = value;
                }

                /**
                 * Gets the value of the equipmentAcquisitionType property.
                 * 
                 * @return
                 *     possible object is
                 *     {@link String }
                 *     
                 */
                public String getEquipmentAcquisitionType() {
                    return equipmentAcquisitionType;
                }

                /**
                 * Sets the value of the equipmentAcquisitionType property.
                 * 
                 * @param value
                 *     allowed object is
                 *     {@link String }
                 *     
                 */
                public void setEquipmentAcquisitionType(String value) {
                    this.equipmentAcquisitionType = value;
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
                        String theEquipmentName;
                        theEquipmentName = this.getEquipmentName();
                        strategy.appendField(locator, this, "equipmentName", buffer, theEquipmentName);
                    }
                    {
                        String theEquipmentAcquisitionType;
                        theEquipmentAcquisitionType = this.getEquipmentAcquisitionType();
                        strategy.appendField(locator, this, "equipmentAcquisitionType", buffer, theEquipmentAcquisitionType);
                    }
                    return buffer;
                }

                public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                    if (!(object instanceof WirelineProductSummary.ProductInstance.EquipmentList.Equipment)) {
                        return false;
                    }
                    if (this == object) {
                        return true;
                    }
                    final WirelineProductSummary.ProductInstance.EquipmentList.Equipment that = ((WirelineProductSummary.ProductInstance.EquipmentList.Equipment) object);
                    {
                        String lhsEquipmentName;
                        lhsEquipmentName = this.getEquipmentName();
                        String rhsEquipmentName;
                        rhsEquipmentName = that.getEquipmentName();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentName", lhsEquipmentName), LocatorUtils.property(thatLocator, "equipmentName", rhsEquipmentName), lhsEquipmentName, rhsEquipmentName)) {
                            return false;
                        }
                    }
                    {
                        String lhsEquipmentAcquisitionType;
                        lhsEquipmentAcquisitionType = this.getEquipmentAcquisitionType();
                        String rhsEquipmentAcquisitionType;
                        rhsEquipmentAcquisitionType = that.getEquipmentAcquisitionType();
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentAcquisitionType", lhsEquipmentAcquisitionType), LocatorUtils.property(thatLocator, "equipmentAcquisitionType", rhsEquipmentAcquisitionType), lhsEquipmentAcquisitionType, rhsEquipmentAcquisitionType)) {
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
         *         &lt;element name="internetTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "internetTypeCd"
        })
        public static class InternetComponent
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            protected String internetTypeCd;

            /**
             * Gets the value of the internetTypeCd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getInternetTypeCd() {
                return internetTypeCd;
            }

            /**
             * Sets the value of the internetTypeCd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setInternetTypeCd(String value) {
                this.internetTypeCd = value;
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
                    String theInternetTypeCd;
                    theInternetTypeCd = this.getInternetTypeCd();
                    strategy.appendField(locator, this, "internetTypeCd", buffer, theInternetTypeCd);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof WirelineProductSummary.ProductInstance.InternetComponent)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final WirelineProductSummary.ProductInstance.InternetComponent that = ((WirelineProductSummary.ProductInstance.InternetComponent) object);
                {
                    String lhsInternetTypeCd;
                    lhsInternetTypeCd = this.getInternetTypeCd();
                    String rhsInternetTypeCd;
                    rhsInternetTypeCd = that.getInternetTypeCd();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "internetTypeCd", lhsInternetTypeCd), LocatorUtils.property(thatLocator, "internetTypeCd", rhsInternetTypeCd), lhsInternetTypeCd, rhsInternetTypeCd)) {
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
         *         &lt;element name="subscriptionNumberList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="subscriptionNumber" maxOccurs="10">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="singleLineNumberListedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="callingFeatureList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="callingFeature" maxOccurs="100">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="callingFeatureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="callingFeatureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="tollPlanList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="tollPlan" maxOccurs="100">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="tollCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="tollPlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
         *                 &lt;/sequence>
         *               &lt;/restriction>
         *             &lt;/complexContent>
         *           &lt;/complexType>
         *         &lt;/element>
         *         &lt;element name="wholesaleAdslInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
         *         &lt;element name="featuresPackList" minOccurs="0">
         *           &lt;complexType>
         *             &lt;complexContent>
         *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                 &lt;sequence>
         *                   &lt;element name="featuresPack" maxOccurs="100">
         *                     &lt;complexType>
         *                       &lt;complexContent>
         *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *                           &lt;sequence>
         *                             &lt;element name="featuresPackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                             &lt;element name="featuresPackCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
         *                           &lt;/sequence>
         *                         &lt;/restriction>
         *                       &lt;/complexContent>
         *                     &lt;/complexType>
         *                   &lt;/element>
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
        @XmlType(name = "", propOrder = {
            "subscriptionNumberList",
            "singleLineNumberListedInd",
            "callingFeatureList",
            "tollPlanList",
            "wholesaleAdslInd",
            "featuresPackList"
        })
        public static class SingleLineComponent
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            protected WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList subscriptionNumberList;
            protected Boolean singleLineNumberListedInd;
            protected WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList callingFeatureList;
            protected WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList tollPlanList;
            protected Boolean wholesaleAdslInd;
            protected WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList featuresPackList;

            /**
             * Gets the value of the subscriptionNumberList property.
             * 
             * @return
             *     possible object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList }
             *     
             */
            public WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList getSubscriptionNumberList() {
                return subscriptionNumberList;
            }

            /**
             * Sets the value of the subscriptionNumberList property.
             * 
             * @param value
             *     allowed object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList }
             *     
             */
            public void setSubscriptionNumberList(WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList value) {
                this.subscriptionNumberList = value;
            }

            /**
             * Gets the value of the singleLineNumberListedInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isSingleLineNumberListedInd() {
                return singleLineNumberListedInd;
            }

            /**
             * Sets the value of the singleLineNumberListedInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setSingleLineNumberListedInd(Boolean value) {
                this.singleLineNumberListedInd = value;
            }

            /**
             * Gets the value of the callingFeatureList property.
             * 
             * @return
             *     possible object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList }
             *     
             */
            public WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList getCallingFeatureList() {
                return callingFeatureList;
            }

            /**
             * Sets the value of the callingFeatureList property.
             * 
             * @param value
             *     allowed object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList }
             *     
             */
            public void setCallingFeatureList(WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList value) {
                this.callingFeatureList = value;
            }

            /**
             * Gets the value of the tollPlanList property.
             * 
             * @return
             *     possible object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList }
             *     
             */
            public WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList getTollPlanList() {
                return tollPlanList;
            }

            /**
             * Sets the value of the tollPlanList property.
             * 
             * @param value
             *     allowed object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList }
             *     
             */
            public void setTollPlanList(WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList value) {
                this.tollPlanList = value;
            }

            /**
             * Gets the value of the wholesaleAdslInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isWholesaleAdslInd() {
                return wholesaleAdslInd;
            }

            /**
             * Sets the value of the wholesaleAdslInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setWholesaleAdslInd(Boolean value) {
                this.wholesaleAdslInd = value;
            }

            /**
             * Gets the value of the featuresPackList property.
             * 
             * @return
             *     possible object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList }
             *     
             */
            public WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList getFeaturesPackList() {
                return featuresPackList;
            }

            /**
             * Sets the value of the featuresPackList property.
             * 
             * @param value
             *     allowed object is
             *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList }
             *     
             */
            public void setFeaturesPackList(WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList value) {
                this.featuresPackList = value;
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
                    WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList theSubscriptionNumberList;
                    theSubscriptionNumberList = this.getSubscriptionNumberList();
                    strategy.appendField(locator, this, "subscriptionNumberList", buffer, theSubscriptionNumberList);
                }
                {
                    Boolean theSingleLineNumberListedInd;
                    theSingleLineNumberListedInd = this.isSingleLineNumberListedInd();
                    strategy.appendField(locator, this, "singleLineNumberListedInd", buffer, theSingleLineNumberListedInd);
                }
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList theCallingFeatureList;
                    theCallingFeatureList = this.getCallingFeatureList();
                    strategy.appendField(locator, this, "callingFeatureList", buffer, theCallingFeatureList);
                }
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList theTollPlanList;
                    theTollPlanList = this.getTollPlanList();
                    strategy.appendField(locator, this, "tollPlanList", buffer, theTollPlanList);
                }
                {
                    Boolean theWholesaleAdslInd;
                    theWholesaleAdslInd = this.isWholesaleAdslInd();
                    strategy.appendField(locator, this, "wholesaleAdslInd", buffer, theWholesaleAdslInd);
                }
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList theFeaturesPackList;
                    theFeaturesPackList = this.getFeaturesPackList();
                    strategy.appendField(locator, this, "featuresPackList", buffer, theFeaturesPackList);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final WirelineProductSummary.ProductInstance.SingleLineComponent that = ((WirelineProductSummary.ProductInstance.SingleLineComponent) object);
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList lhsSubscriptionNumberList;
                    lhsSubscriptionNumberList = this.getSubscriptionNumberList();
                    WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList rhsSubscriptionNumberList;
                    rhsSubscriptionNumberList = that.getSubscriptionNumberList();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriptionNumberList", lhsSubscriptionNumberList), LocatorUtils.property(thatLocator, "subscriptionNumberList", rhsSubscriptionNumberList), lhsSubscriptionNumberList, rhsSubscriptionNumberList)) {
                        return false;
                    }
                }
                {
                    Boolean lhsSingleLineNumberListedInd;
                    lhsSingleLineNumberListedInd = this.isSingleLineNumberListedInd();
                    Boolean rhsSingleLineNumberListedInd;
                    rhsSingleLineNumberListedInd = that.isSingleLineNumberListedInd();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "singleLineNumberListedInd", lhsSingleLineNumberListedInd), LocatorUtils.property(thatLocator, "singleLineNumberListedInd", rhsSingleLineNumberListedInd), lhsSingleLineNumberListedInd, rhsSingleLineNumberListedInd)) {
                        return false;
                    }
                }
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList lhsCallingFeatureList;
                    lhsCallingFeatureList = this.getCallingFeatureList();
                    WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList rhsCallingFeatureList;
                    rhsCallingFeatureList = that.getCallingFeatureList();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "callingFeatureList", lhsCallingFeatureList), LocatorUtils.property(thatLocator, "callingFeatureList", rhsCallingFeatureList), lhsCallingFeatureList, rhsCallingFeatureList)) {
                        return false;
                    }
                }
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList lhsTollPlanList;
                    lhsTollPlanList = this.getTollPlanList();
                    WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList rhsTollPlanList;
                    rhsTollPlanList = that.getTollPlanList();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "tollPlanList", lhsTollPlanList), LocatorUtils.property(thatLocator, "tollPlanList", rhsTollPlanList), lhsTollPlanList, rhsTollPlanList)) {
                        return false;
                    }
                }
                {
                    Boolean lhsWholesaleAdslInd;
                    lhsWholesaleAdslInd = this.isWholesaleAdslInd();
                    Boolean rhsWholesaleAdslInd;
                    rhsWholesaleAdslInd = that.isWholesaleAdslInd();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "wholesaleAdslInd", lhsWholesaleAdslInd), LocatorUtils.property(thatLocator, "wholesaleAdslInd", rhsWholesaleAdslInd), lhsWholesaleAdslInd, rhsWholesaleAdslInd)) {
                        return false;
                    }
                }
                {
                    WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList lhsFeaturesPackList;
                    lhsFeaturesPackList = this.getFeaturesPackList();
                    WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList rhsFeaturesPackList;
                    rhsFeaturesPackList = that.getFeaturesPackList();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "featuresPackList", lhsFeaturesPackList), LocatorUtils.property(thatLocator, "featuresPackList", rhsFeaturesPackList), lhsFeaturesPackList, rhsFeaturesPackList)) {
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
             *         &lt;element name="callingFeature" maxOccurs="100">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="callingFeatureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="callingFeatureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            @XmlType(name = "", propOrder = {
                "callingFeature"
            })
            public static class CallingFeatureList
                implements Serializable, Equals, ToString
            {

                private final static long serialVersionUID = 2L;
                @XmlElement(required = true)
                protected List<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> callingFeature;

                /**
                 * Gets the value of the callingFeature property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the callingFeature property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getCallingFeature().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature }
                 * 
                 * 
                 */
                public List<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> getCallingFeature() {
                    if (callingFeature == null) {
                        callingFeature = new ArrayList<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature>();
                    }
                    return this.callingFeature;
                }

                /**
                 * Sets the value of the callingFeature property.
                 * 
                 * @param callingFeature
                 *     allowed object is
                 *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature }
                 *     
                 */
                public void setCallingFeature(List<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> callingFeature) {
                    this.callingFeature = callingFeature;
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
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> theCallingFeature;
                        theCallingFeature = (((this.callingFeature!= null)&&(!this.callingFeature.isEmpty()))?this.getCallingFeature():null);
                        strategy.appendField(locator, this, "callingFeature", buffer, theCallingFeature);
                    }
                    return buffer;
                }

                public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                    if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList)) {
                        return false;
                    }
                    if (this == object) {
                        return true;
                    }
                    final WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList) object);
                    {
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> lhsCallingFeature;
                        lhsCallingFeature = (((this.callingFeature!= null)&&(!this.callingFeature.isEmpty()))?this.getCallingFeature():null);
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature> rhsCallingFeature;
                        rhsCallingFeature = (((that.callingFeature!= null)&&(!that.callingFeature.isEmpty()))?that.getCallingFeature():null);
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "callingFeature", lhsCallingFeature), LocatorUtils.property(thatLocator, "callingFeature", rhsCallingFeature), lhsCallingFeature, rhsCallingFeature)) {
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
                 *         &lt;element name="callingFeatureName" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="callingFeatureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                    "callingFeatureName",
                    "callingFeatureCode"
                })
                public static class CallingFeature
                    implements Serializable, Equals, ToString
                {

                    private final static long serialVersionUID = 2L;
                    @XmlElement(required = true)
                    protected String callingFeatureName;
                    @XmlElement(required = true)
                    protected String callingFeatureCode;

                    /**
                     * Gets the value of the callingFeatureName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCallingFeatureName() {
                        return callingFeatureName;
                    }

                    /**
                     * Sets the value of the callingFeatureName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCallingFeatureName(String value) {
                        this.callingFeatureName = value;
                    }

                    /**
                     * Gets the value of the callingFeatureCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCallingFeatureCode() {
                        return callingFeatureCode;
                    }

                    /**
                     * Sets the value of the callingFeatureCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCallingFeatureCode(String value) {
                        this.callingFeatureCode = value;
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
                            String theCallingFeatureName;
                            theCallingFeatureName = this.getCallingFeatureName();
                            strategy.appendField(locator, this, "callingFeatureName", buffer, theCallingFeatureName);
                        }
                        {
                            String theCallingFeatureCode;
                            theCallingFeatureCode = this.getCallingFeatureCode();
                            strategy.appendField(locator, this, "callingFeatureCode", buffer, theCallingFeatureCode);
                        }
                        return buffer;
                    }

                    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                        if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature)) {
                            return false;
                        }
                        if (this == object) {
                            return true;
                        }
                        final WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.CallingFeatureList.CallingFeature) object);
                        {
                            String lhsCallingFeatureName;
                            lhsCallingFeatureName = this.getCallingFeatureName();
                            String rhsCallingFeatureName;
                            rhsCallingFeatureName = that.getCallingFeatureName();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingFeatureName", lhsCallingFeatureName), LocatorUtils.property(thatLocator, "callingFeatureName", rhsCallingFeatureName), lhsCallingFeatureName, rhsCallingFeatureName)) {
                                return false;
                            }
                        }
                        {
                            String lhsCallingFeatureCode;
                            lhsCallingFeatureCode = this.getCallingFeatureCode();
                            String rhsCallingFeatureCode;
                            rhsCallingFeatureCode = that.getCallingFeatureCode();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingFeatureCode", lhsCallingFeatureCode), LocatorUtils.property(thatLocator, "callingFeatureCode", rhsCallingFeatureCode), lhsCallingFeatureCode, rhsCallingFeatureCode)) {
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
             *         &lt;element name="featuresPack" maxOccurs="100">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="featuresPackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="featuresPackCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            @XmlType(name = "", propOrder = {
                "featuresPack"
            })
            public static class FeaturesPackList
                implements Serializable, Equals, ToString
            {

                private final static long serialVersionUID = 2L;
                @XmlElement(required = true)
                protected List<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> featuresPack;

                /**
                 * Gets the value of the featuresPack property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the featuresPack property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getFeaturesPack().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack }
                 * 
                 * 
                 */
                public List<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> getFeaturesPack() {
                    if (featuresPack == null) {
                        featuresPack = new ArrayList<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack>();
                    }
                    return this.featuresPack;
                }

                /**
                 * Sets the value of the featuresPack property.
                 * 
                 * @param featuresPack
                 *     allowed object is
                 *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack }
                 *     
                 */
                public void setFeaturesPack(List<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> featuresPack) {
                    this.featuresPack = featuresPack;
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
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> theFeaturesPack;
                        theFeaturesPack = (((this.featuresPack!= null)&&(!this.featuresPack.isEmpty()))?this.getFeaturesPack():null);
                        strategy.appendField(locator, this, "featuresPack", buffer, theFeaturesPack);
                    }
                    return buffer;
                }

                public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                    if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList)) {
                        return false;
                    }
                    if (this == object) {
                        return true;
                    }
                    final WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList) object);
                    {
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> lhsFeaturesPack;
                        lhsFeaturesPack = (((this.featuresPack!= null)&&(!this.featuresPack.isEmpty()))?this.getFeaturesPack():null);
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack> rhsFeaturesPack;
                        rhsFeaturesPack = (((that.featuresPack!= null)&&(!that.featuresPack.isEmpty()))?that.getFeaturesPack():null);
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "featuresPack", lhsFeaturesPack), LocatorUtils.property(thatLocator, "featuresPack", rhsFeaturesPack), lhsFeaturesPack, rhsFeaturesPack)) {
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
                 *         &lt;element name="featuresPackName" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="featuresPackCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                    "featuresPackName",
                    "featuresPackCode"
                })
                public static class FeaturesPack
                    implements Serializable, Equals, ToString
                {

                    private final static long serialVersionUID = 2L;
                    @XmlElement(required = true)
                    protected String featuresPackName;
                    @XmlElement(required = true)
                    protected String featuresPackCode;

                    /**
                     * Gets the value of the featuresPackName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getFeaturesPackName() {
                        return featuresPackName;
                    }

                    /**
                     * Sets the value of the featuresPackName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setFeaturesPackName(String value) {
                        this.featuresPackName = value;
                    }

                    /**
                     * Gets the value of the featuresPackCode property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getFeaturesPackCode() {
                        return featuresPackCode;
                    }

                    /**
                     * Sets the value of the featuresPackCode property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setFeaturesPackCode(String value) {
                        this.featuresPackCode = value;
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
                            String theFeaturesPackName;
                            theFeaturesPackName = this.getFeaturesPackName();
                            strategy.appendField(locator, this, "featuresPackName", buffer, theFeaturesPackName);
                        }
                        {
                            String theFeaturesPackCode;
                            theFeaturesPackCode = this.getFeaturesPackCode();
                            strategy.appendField(locator, this, "featuresPackCode", buffer, theFeaturesPackCode);
                        }
                        return buffer;
                    }

                    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                        if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack)) {
                            return false;
                        }
                        if (this == object) {
                            return true;
                        }
                        final WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.FeaturesPackList.FeaturesPack) object);
                        {
                            String lhsFeaturesPackName;
                            lhsFeaturesPackName = this.getFeaturesPackName();
                            String rhsFeaturesPackName;
                            rhsFeaturesPackName = that.getFeaturesPackName();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "featuresPackName", lhsFeaturesPackName), LocatorUtils.property(thatLocator, "featuresPackName", rhsFeaturesPackName), lhsFeaturesPackName, rhsFeaturesPackName)) {
                                return false;
                            }
                        }
                        {
                            String lhsFeaturesPackCode;
                            lhsFeaturesPackCode = this.getFeaturesPackCode();
                            String rhsFeaturesPackCode;
                            rhsFeaturesPackCode = that.getFeaturesPackCode();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "featuresPackCode", lhsFeaturesPackCode), LocatorUtils.property(thatLocator, "featuresPackCode", rhsFeaturesPackCode), lhsFeaturesPackCode, rhsFeaturesPackCode)) {
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
             *         &lt;element name="subscriptionNumber" maxOccurs="10">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            @XmlType(name = "", propOrder = {
                "subscriptionNumber"
            })
            public static class SubscriptionNumberList
                implements Serializable, Equals, ToString
            {

                private final static long serialVersionUID = 2L;
                @XmlElement(required = true)
                protected List<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> subscriptionNumber;

                /**
                 * Gets the value of the subscriptionNumber property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the subscriptionNumber property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getSubscriptionNumber().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber }
                 * 
                 * 
                 */
                public List<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> getSubscriptionNumber() {
                    if (subscriptionNumber == null) {
                        subscriptionNumber = new ArrayList<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber>();
                    }
                    return this.subscriptionNumber;
                }

                /**
                 * Sets the value of the subscriptionNumber property.
                 * 
                 * @param subscriptionNumber
                 *     allowed object is
                 *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber }
                 *     
                 */
                public void setSubscriptionNumber(List<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> subscriptionNumber) {
                    this.subscriptionNumber = subscriptionNumber;
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
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> theSubscriptionNumber;
                        theSubscriptionNumber = (((this.subscriptionNumber!= null)&&(!this.subscriptionNumber.isEmpty()))?this.getSubscriptionNumber():null);
                        strategy.appendField(locator, this, "subscriptionNumber", buffer, theSubscriptionNumber);
                    }
                    return buffer;
                }

                public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                    if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList)) {
                        return false;
                    }
                    if (this == object) {
                        return true;
                    }
                    final WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList) object);
                    {
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> lhsSubscriptionNumber;
                        lhsSubscriptionNumber = (((this.subscriptionNumber!= null)&&(!this.subscriptionNumber.isEmpty()))?this.getSubscriptionNumber():null);
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber> rhsSubscriptionNumber;
                        rhsSubscriptionNumber = (((that.subscriptionNumber!= null)&&(!that.subscriptionNumber.isEmpty()))?that.getSubscriptionNumber():null);
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriptionNumber", lhsSubscriptionNumber), LocatorUtils.property(thatLocator, "subscriptionNumber", rhsSubscriptionNumber), lhsSubscriptionNumber, rhsSubscriptionNumber)) {
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
                 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                    "code",
                    "phoneNumber"
                })
                public static class SubscriptionNumber
                    implements Serializable, Equals, ToString
                {

                    private final static long serialVersionUID = 2L;
                    @XmlElement(required = true)
                    protected String code;
                    @XmlElement(required = true)
                    protected String phoneNumber;

                    /**
                     * Gets the value of the code property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getCode() {
                        return code;
                    }

                    /**
                     * Sets the value of the code property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setCode(String value) {
                        this.code = value;
                    }

                    /**
                     * Gets the value of the phoneNumber property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getPhoneNumber() {
                        return phoneNumber;
                    }

                    /**
                     * Sets the value of the phoneNumber property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setPhoneNumber(String value) {
                        this.phoneNumber = value;
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
                            String theCode;
                            theCode = this.getCode();
                            strategy.appendField(locator, this, "code", buffer, theCode);
                        }
                        {
                            String thePhoneNumber;
                            thePhoneNumber = this.getPhoneNumber();
                            strategy.appendField(locator, this, "phoneNumber", buffer, thePhoneNumber);
                        }
                        return buffer;
                    }

                    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                        if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber)) {
                            return false;
                        }
                        if (this == object) {
                            return true;
                        }
                        final WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.SubscriptionNumberList.SubscriptionNumber) object);
                        {
                            String lhsCode;
                            lhsCode = this.getCode();
                            String rhsCode;
                            rhsCode = that.getCode();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "code", lhsCode), LocatorUtils.property(thatLocator, "code", rhsCode), lhsCode, rhsCode)) {
                                return false;
                            }
                        }
                        {
                            String lhsPhoneNumber;
                            lhsPhoneNumber = this.getPhoneNumber();
                            String rhsPhoneNumber;
                            rhsPhoneNumber = that.getPhoneNumber();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumber", lhsPhoneNumber), LocatorUtils.property(thatLocator, "phoneNumber", rhsPhoneNumber), lhsPhoneNumber, rhsPhoneNumber)) {
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
             *         &lt;element name="tollPlan" maxOccurs="100">
             *           &lt;complexType>
             *             &lt;complexContent>
             *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
             *                 &lt;sequence>
             *                   &lt;element name="tollCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
             *                   &lt;element name="tollPlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
            @XmlType(name = "", propOrder = {
                "tollPlan"
            })
            public static class TollPlanList
                implements Serializable, Equals, ToString
            {

                private final static long serialVersionUID = 2L;
                @XmlElement(required = true)
                protected List<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> tollPlan;

                /**
                 * Gets the value of the tollPlan property.
                 * 
                 * <p>
                 * This accessor method returns a reference to the live list,
                 * not a snapshot. Therefore any modification you make to the
                 * returned list will be present inside the JAXB object.
                 * This is why there is not a <CODE>set</CODE> method for the tollPlan property.
                 * 
                 * <p>
                 * For example, to add a new item, do as follows:
                 * <pre>
                 *    getTollPlan().add(newItem);
                 * </pre>
                 * 
                 * 
                 * <p>
                 * Objects of the following type(s) are allowed in the list
                 * {@link WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan }
                 * 
                 * 
                 */
                public List<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> getTollPlan() {
                    if (tollPlan == null) {
                        tollPlan = new ArrayList<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan>();
                    }
                    return this.tollPlan;
                }

                /**
                 * Sets the value of the tollPlan property.
                 * 
                 * @param tollPlan
                 *     allowed object is
                 *     {@link WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan }
                 *     
                 */
                public void setTollPlan(List<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> tollPlan) {
                    this.tollPlan = tollPlan;
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
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> theTollPlan;
                        theTollPlan = (((this.tollPlan!= null)&&(!this.tollPlan.isEmpty()))?this.getTollPlan():null);
                        strategy.appendField(locator, this, "tollPlan", buffer, theTollPlan);
                    }
                    return buffer;
                }

                public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                    if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList)) {
                        return false;
                    }
                    if (this == object) {
                        return true;
                    }
                    final WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList) object);
                    {
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> lhsTollPlan;
                        lhsTollPlan = (((this.tollPlan!= null)&&(!this.tollPlan.isEmpty()))?this.getTollPlan():null);
                        List<WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan> rhsTollPlan;
                        rhsTollPlan = (((that.tollPlan!= null)&&(!that.tollPlan.isEmpty()))?that.getTollPlan():null);
                        if (!strategy.equals(LocatorUtils.property(thisLocator, "tollPlan", lhsTollPlan), LocatorUtils.property(thatLocator, "tollPlan", rhsTollPlan), lhsTollPlan, rhsTollPlan)) {
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
                 *         &lt;element name="tollCategoryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
                 *         &lt;element name="tollPlanName" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
                    "tollCategoryName",
                    "tollPlanName"
                })
                public static class TollPlan
                    implements Serializable, Equals, ToString
                {

                    private final static long serialVersionUID = 2L;
                    @XmlElement(required = true)
                    protected String tollCategoryName;
                    @XmlElement(required = true)
                    protected String tollPlanName;

                    /**
                     * Gets the value of the tollCategoryName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getTollCategoryName() {
                        return tollCategoryName;
                    }

                    /**
                     * Sets the value of the tollCategoryName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setTollCategoryName(String value) {
                        this.tollCategoryName = value;
                    }

                    /**
                     * Gets the value of the tollPlanName property.
                     * 
                     * @return
                     *     possible object is
                     *     {@link String }
                     *     
                     */
                    public String getTollPlanName() {
                        return tollPlanName;
                    }

                    /**
                     * Sets the value of the tollPlanName property.
                     * 
                     * @param value
                     *     allowed object is
                     *     {@link String }
                     *     
                     */
                    public void setTollPlanName(String value) {
                        this.tollPlanName = value;
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
                            String theTollCategoryName;
                            theTollCategoryName = this.getTollCategoryName();
                            strategy.appendField(locator, this, "tollCategoryName", buffer, theTollCategoryName);
                        }
                        {
                            String theTollPlanName;
                            theTollPlanName = this.getTollPlanName();
                            strategy.appendField(locator, this, "tollPlanName", buffer, theTollPlanName);
                        }
                        return buffer;
                    }

                    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                        if (!(object instanceof WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan)) {
                            return false;
                        }
                        if (this == object) {
                            return true;
                        }
                        final WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan that = ((WirelineProductSummary.ProductInstance.SingleLineComponent.TollPlanList.TollPlan) object);
                        {
                            String lhsTollCategoryName;
                            lhsTollCategoryName = this.getTollCategoryName();
                            String rhsTollCategoryName;
                            rhsTollCategoryName = that.getTollCategoryName();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "tollCategoryName", lhsTollCategoryName), LocatorUtils.property(thatLocator, "tollCategoryName", rhsTollCategoryName), lhsTollCategoryName, rhsTollCategoryName)) {
                                return false;
                            }
                        }
                        {
                            String lhsTollPlanName;
                            lhsTollPlanName = this.getTollPlanName();
                            String rhsTollPlanName;
                            rhsTollPlanName = that.getTollPlanName();
                            if (!strategy.equals(LocatorUtils.property(thisLocator, "tollPlanName", lhsTollPlanName), LocatorUtils.property(thatLocator, "tollPlanName", rhsTollPlanName), lhsTollPlanName, rhsTollPlanName)) {
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
         *         &lt;element name="hdChannelInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
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
            "hdChannelInd"
        })
        public static class TtvComponent
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            protected Boolean hdChannelInd;

            /**
             * Gets the value of the hdChannelInd property.
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isHdChannelInd() {
                return hdChannelInd;
            }

            /**
             * Sets the value of the hdChannelInd property.
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setHdChannelInd(Boolean value) {
                this.hdChannelInd = value;
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
                    Boolean theHdChannelInd;
                    theHdChannelInd = this.isHdChannelInd();
                    strategy.appendField(locator, this, "hdChannelInd", buffer, theHdChannelInd);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof WirelineProductSummary.ProductInstance.TtvComponent)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final WirelineProductSummary.ProductInstance.TtvComponent that = ((WirelineProductSummary.ProductInstance.TtvComponent) object);
                {
                    Boolean lhsHdChannelInd;
                    lhsHdChannelInd = this.isHdChannelInd();
                    Boolean rhsHdChannelInd;
                    rhsHdChannelInd = that.isHdChannelInd();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "hdChannelInd", lhsHdChannelInd), LocatorUtils.property(thatLocator, "hdChannelInd", rhsHdChannelInd), lhsHdChannelInd, rhsHdChannelInd)) {
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
     *         &lt;element name="servicePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100"/>
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
        "servicePhoneNumber"
    })
    public static class ServicePhoneNumberList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected List<String> servicePhoneNumber;

        /**
         * Gets the value of the servicePhoneNumber property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the servicePhoneNumber property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServicePhoneNumber().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getServicePhoneNumber() {
            if (servicePhoneNumber == null) {
                servicePhoneNumber = new ArrayList<String>();
            }
            return this.servicePhoneNumber;
        }

        /**
         * Sets the value of the servicePhoneNumber property.
         * 
         * @param servicePhoneNumber
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setServicePhoneNumber(List<String> servicePhoneNumber) {
            this.servicePhoneNumber = servicePhoneNumber;
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
                List<String> theServicePhoneNumber;
                theServicePhoneNumber = (((this.servicePhoneNumber!= null)&&(!this.servicePhoneNumber.isEmpty()))?this.getServicePhoneNumber():null);
                strategy.appendField(locator, this, "servicePhoneNumber", buffer, theServicePhoneNumber);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof WirelineProductSummary.ServicePhoneNumberList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final WirelineProductSummary.ServicePhoneNumberList that = ((WirelineProductSummary.ServicePhoneNumberList) object);
            {
                List<String> lhsServicePhoneNumber;
                lhsServicePhoneNumber = (((this.servicePhoneNumber!= null)&&(!this.servicePhoneNumber.isEmpty()))?this.getServicePhoneNumber():null);
                List<String> rhsServicePhoneNumber;
                rhsServicePhoneNumber = (((that.servicePhoneNumber!= null)&&(!that.servicePhoneNumber.isEmpty()))?that.getServicePhoneNumber():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "servicePhoneNumber", lhsServicePhoneNumber), LocatorUtils.property(thatLocator, "servicePhoneNumber", rhsServicePhoneNumber), lhsServicePhoneNumber, rhsServicePhoneNumber)) {
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
