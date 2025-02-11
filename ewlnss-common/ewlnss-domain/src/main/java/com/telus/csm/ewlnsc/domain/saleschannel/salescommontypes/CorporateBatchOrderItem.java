
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
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for CorporateBatchOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorporateBatchOrderItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="lineItemNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAccountNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}NewSubscriberDetail" minOccurs="0"/>
 *         &lt;element name="headOfficeCallingPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberPhoneNum" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="phoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="autoAssignPhoneNum" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="npa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="nxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="phoneNumProvinceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="phoneNumCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="portInInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="portRequest" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PortRequest" minOccurs="0"/>
 *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pricePlanTransactionActionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceTransactionAction" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractTermInMonths" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="contractEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="simOnlyTransaction" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="deviceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="simSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handsetSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handsetProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorporateBatchOrderItem", propOrder = {
    "lineItemNum",
    "orderTypeCode",
    "orderSubTypeCode",
    "billingAccountNum",
    "subscriberDetail",
    "headOfficeCallingPhoneNumber",
    "subscriberPhoneNum",
    "portInInd",
    "portRequest",
    "offerId",
    "pricePlanCode",
    "pricePlanTransactionActionCode",
    "serviceItemList",
    "contractTermInMonths",
    "contractEffectiveDate",
    "simOnlyTransaction",
    "simSerialNumber",
    "handsetSerialNumber",
    "simProductCd",
    "handsetProductCd"
})
public class CorporateBatchOrderItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String lineItemNum;
    protected String orderTypeCode;
    protected String orderSubTypeCode;
    protected String billingAccountNum;
    protected NewSubscriberDetail subscriberDetail;
    protected String headOfficeCallingPhoneNumber;
    protected CorporateBatchOrderItem.SubscriberPhoneNum subscriberPhoneNum;
    protected Boolean portInInd;
    protected PortRequest portRequest;
    protected String offerId;
    protected String pricePlanCode;
    protected String pricePlanTransactionActionCode;
    protected List<ServiceTransactionAction> serviceItemList;
    protected BigInteger contractTermInMonths;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date contractEffectiveDate;
    protected CorporateBatchOrderItem.SimOnlyTransaction simOnlyTransaction;
    protected String simSerialNumber;
    protected String handsetSerialNumber;
    protected String simProductCd;
    protected String handsetProductCd;

    /**
     * Gets the value of the lineItemNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLineItemNum() {
        return lineItemNum;
    }

    /**
     * Sets the value of the lineItemNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLineItemNum(String value) {
        this.lineItemNum = value;
    }

    /**
     * Gets the value of the orderTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTypeCode() {
        return orderTypeCode;
    }

    /**
     * Sets the value of the orderTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTypeCode(String value) {
        this.orderTypeCode = value;
    }

    /**
     * Gets the value of the orderSubTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderSubTypeCode() {
        return orderSubTypeCode;
    }

    /**
     * Sets the value of the orderSubTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderSubTypeCode(String value) {
        this.orderSubTypeCode = value;
    }

    /**
     * Gets the value of the billingAccountNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNum() {
        return billingAccountNum;
    }

    /**
     * Sets the value of the billingAccountNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNum(String value) {
        this.billingAccountNum = value;
    }

    /**
     * Gets the value of the subscriberDetail property.
     * 
     * @return
     *     possible object is
     *     {@link NewSubscriberDetail }
     *     
     */
    public NewSubscriberDetail getSubscriberDetail() {
        return subscriberDetail;
    }

    /**
     * Sets the value of the subscriberDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link NewSubscriberDetail }
     *     
     */
    public void setSubscriberDetail(NewSubscriberDetail value) {
        this.subscriberDetail = value;
    }

    /**
     * Gets the value of the headOfficeCallingPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeadOfficeCallingPhoneNumber() {
        return headOfficeCallingPhoneNumber;
    }

    /**
     * Sets the value of the headOfficeCallingPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeadOfficeCallingPhoneNumber(String value) {
        this.headOfficeCallingPhoneNumber = value;
    }

    /**
     * Gets the value of the subscriberPhoneNum property.
     * 
     * @return
     *     possible object is
     *     {@link CorporateBatchOrderItem.SubscriberPhoneNum }
     *     
     */
    public CorporateBatchOrderItem.SubscriberPhoneNum getSubscriberPhoneNum() {
        return subscriberPhoneNum;
    }

    /**
     * Sets the value of the subscriberPhoneNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateBatchOrderItem.SubscriberPhoneNum }
     *     
     */
    public void setSubscriberPhoneNum(CorporateBatchOrderItem.SubscriberPhoneNum value) {
        this.subscriberPhoneNum = value;
    }

    /**
     * Gets the value of the portInInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPortInInd() {
        return portInInd;
    }

    /**
     * Sets the value of the portInInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPortInInd(Boolean value) {
        this.portInInd = value;
    }

    /**
     * Gets the value of the portRequest property.
     * 
     * @return
     *     possible object is
     *     {@link PortRequest }
     *     
     */
    public PortRequest getPortRequest() {
        return portRequest;
    }

    /**
     * Sets the value of the portRequest property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortRequest }
     *     
     */
    public void setPortRequest(PortRequest value) {
        this.portRequest = value;
    }

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
     * Gets the value of the pricePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanCode() {
        return pricePlanCode;
    }

    /**
     * Sets the value of the pricePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanCode(String value) {
        this.pricePlanCode = value;
    }

    /**
     * Gets the value of the pricePlanTransactionActionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanTransactionActionCode() {
        return pricePlanTransactionActionCode;
    }

    /**
     * Sets the value of the pricePlanTransactionActionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanTransactionActionCode(String value) {
        this.pricePlanTransactionActionCode = value;
    }

    /**
     * Gets the value of the serviceItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceTransactionAction }
     * 
     * 
     */
    public List<ServiceTransactionAction> getServiceItemList() {
        if (serviceItemList == null) {
            serviceItemList = new ArrayList<ServiceTransactionAction>();
        }
        return this.serviceItemList;
    }

    /**
     * Gets the value of the contractTermInMonths property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getContractTermInMonths() {
        return contractTermInMonths;
    }

    /**
     * Sets the value of the contractTermInMonths property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTermInMonths(BigInteger value) {
        this.contractTermInMonths = value;
    }

    /**
     * Gets the value of the contractEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getContractEffectiveDate() {
        return contractEffectiveDate;
    }

    /**
     * Sets the value of the contractEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractEffectiveDate(Date value) {
        this.contractEffectiveDate = value;
    }

    /**
     * Gets the value of the simOnlyTransaction property.
     * 
     * @return
     *     possible object is
     *     {@link CorporateBatchOrderItem.SimOnlyTransaction }
     *     
     */
    public CorporateBatchOrderItem.SimOnlyTransaction getSimOnlyTransaction() {
        return simOnlyTransaction;
    }

    /**
     * Sets the value of the simOnlyTransaction property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateBatchOrderItem.SimOnlyTransaction }
     *     
     */
    public void setSimOnlyTransaction(CorporateBatchOrderItem.SimOnlyTransaction value) {
        this.simOnlyTransaction = value;
    }

    /**
     * Gets the value of the simSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    /**
     * Sets the value of the simSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimSerialNumber(String value) {
        this.simSerialNumber = value;
    }

    /**
     * Gets the value of the handsetSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetSerialNumber() {
        return handsetSerialNumber;
    }

    /**
     * Sets the value of the handsetSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetSerialNumber(String value) {
        this.handsetSerialNumber = value;
    }

    /**
     * Gets the value of the simProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimProductCd() {
        return simProductCd;
    }

    /**
     * Sets the value of the simProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimProductCd(String value) {
        this.simProductCd = value;
    }

    /**
     * Gets the value of the handsetProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetProductCd() {
        return handsetProductCd;
    }

    /**
     * Sets the value of the handsetProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetProductCd(String value) {
        this.handsetProductCd = value;
    }

    /**
     * Sets the value of the serviceItemList property.
     * 
     * @param serviceItemList
     *     allowed object is
     *     {@link ServiceTransactionAction }
     *     
     */
    public void setServiceItemList(List<ServiceTransactionAction> serviceItemList) {
        this.serviceItemList = serviceItemList;
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
            String theLineItemNum;
            theLineItemNum = this.getLineItemNum();
            strategy.appendField(locator, this, "lineItemNum", buffer, theLineItemNum);
        }
        {
            String theOrderTypeCode;
            theOrderTypeCode = this.getOrderTypeCode();
            strategy.appendField(locator, this, "orderTypeCode", buffer, theOrderTypeCode);
        }
        {
            String theOrderSubTypeCode;
            theOrderSubTypeCode = this.getOrderSubTypeCode();
            strategy.appendField(locator, this, "orderSubTypeCode", buffer, theOrderSubTypeCode);
        }
        {
            String theBillingAccountNum;
            theBillingAccountNum = this.getBillingAccountNum();
            strategy.appendField(locator, this, "billingAccountNum", buffer, theBillingAccountNum);
        }
        {
            NewSubscriberDetail theSubscriberDetail;
            theSubscriberDetail = this.getSubscriberDetail();
            strategy.appendField(locator, this, "subscriberDetail", buffer, theSubscriberDetail);
        }
        {
            String theHeadOfficeCallingPhoneNumber;
            theHeadOfficeCallingPhoneNumber = this.getHeadOfficeCallingPhoneNumber();
            strategy.appendField(locator, this, "headOfficeCallingPhoneNumber", buffer, theHeadOfficeCallingPhoneNumber);
        }
        {
            CorporateBatchOrderItem.SubscriberPhoneNum theSubscriberPhoneNum;
            theSubscriberPhoneNum = this.getSubscriberPhoneNum();
            strategy.appendField(locator, this, "subscriberPhoneNum", buffer, theSubscriberPhoneNum);
        }
        {
            Boolean thePortInInd;
            thePortInInd = this.isPortInInd();
            strategy.appendField(locator, this, "portInInd", buffer, thePortInInd);
        }
        {
            PortRequest thePortRequest;
            thePortRequest = this.getPortRequest();
            strategy.appendField(locator, this, "portRequest", buffer, thePortRequest);
        }
        {
            String theOfferId;
            theOfferId = this.getOfferId();
            strategy.appendField(locator, this, "offerId", buffer, theOfferId);
        }
        {
            String thePricePlanCode;
            thePricePlanCode = this.getPricePlanCode();
            strategy.appendField(locator, this, "pricePlanCode", buffer, thePricePlanCode);
        }
        {
            String thePricePlanTransactionActionCode;
            thePricePlanTransactionActionCode = this.getPricePlanTransactionActionCode();
            strategy.appendField(locator, this, "pricePlanTransactionActionCode", buffer, thePricePlanTransactionActionCode);
        }
        {
            List<ServiceTransactionAction> theServiceItemList;
            theServiceItemList = (((this.serviceItemList!= null)&&(!this.serviceItemList.isEmpty()))?this.getServiceItemList():null);
            strategy.appendField(locator, this, "serviceItemList", buffer, theServiceItemList);
        }
        {
            BigInteger theContractTermInMonths;
            theContractTermInMonths = this.getContractTermInMonths();
            strategy.appendField(locator, this, "contractTermInMonths", buffer, theContractTermInMonths);
        }
        {
            Date theContractEffectiveDate;
            theContractEffectiveDate = this.getContractEffectiveDate();
            strategy.appendField(locator, this, "contractEffectiveDate", buffer, theContractEffectiveDate);
        }
        {
            CorporateBatchOrderItem.SimOnlyTransaction theSimOnlyTransaction;
            theSimOnlyTransaction = this.getSimOnlyTransaction();
            strategy.appendField(locator, this, "simOnlyTransaction", buffer, theSimOnlyTransaction);
        }
        {
            String theSimSerialNumber;
            theSimSerialNumber = this.getSimSerialNumber();
            strategy.appendField(locator, this, "simSerialNumber", buffer, theSimSerialNumber);
        }
        {
            String theHandsetSerialNumber;
            theHandsetSerialNumber = this.getHandsetSerialNumber();
            strategy.appendField(locator, this, "handsetSerialNumber", buffer, theHandsetSerialNumber);
        }
        {
            String theSimProductCd;
            theSimProductCd = this.getSimProductCd();
            strategy.appendField(locator, this, "simProductCd", buffer, theSimProductCd);
        }
        {
            String theHandsetProductCd;
            theHandsetProductCd = this.getHandsetProductCd();
            strategy.appendField(locator, this, "handsetProductCd", buffer, theHandsetProductCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CorporateBatchOrderItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CorporateBatchOrderItem that = ((CorporateBatchOrderItem) object);
        {
            String lhsLineItemNum;
            lhsLineItemNum = this.getLineItemNum();
            String rhsLineItemNum;
            rhsLineItemNum = that.getLineItemNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lineItemNum", lhsLineItemNum), LocatorUtils.property(thatLocator, "lineItemNum", rhsLineItemNum), lhsLineItemNum, rhsLineItemNum)) {
                return false;
            }
        }
        {
            String lhsOrderTypeCode;
            lhsOrderTypeCode = this.getOrderTypeCode();
            String rhsOrderTypeCode;
            rhsOrderTypeCode = that.getOrderTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderTypeCode", lhsOrderTypeCode), LocatorUtils.property(thatLocator, "orderTypeCode", rhsOrderTypeCode), lhsOrderTypeCode, rhsOrderTypeCode)) {
                return false;
            }
        }
        {
            String lhsOrderSubTypeCode;
            lhsOrderSubTypeCode = this.getOrderSubTypeCode();
            String rhsOrderSubTypeCode;
            rhsOrderSubTypeCode = that.getOrderSubTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderSubTypeCode", lhsOrderSubTypeCode), LocatorUtils.property(thatLocator, "orderSubTypeCode", rhsOrderSubTypeCode), lhsOrderSubTypeCode, rhsOrderSubTypeCode)) {
                return false;
            }
        }
        {
            String lhsBillingAccountNum;
            lhsBillingAccountNum = this.getBillingAccountNum();
            String rhsBillingAccountNum;
            rhsBillingAccountNum = that.getBillingAccountNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNum", lhsBillingAccountNum), LocatorUtils.property(thatLocator, "billingAccountNum", rhsBillingAccountNum), lhsBillingAccountNum, rhsBillingAccountNum)) {
                return false;
            }
        }
        {
            NewSubscriberDetail lhsSubscriberDetail;
            lhsSubscriberDetail = this.getSubscriberDetail();
            NewSubscriberDetail rhsSubscriberDetail;
            rhsSubscriberDetail = that.getSubscriberDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberDetail", lhsSubscriberDetail), LocatorUtils.property(thatLocator, "subscriberDetail", rhsSubscriberDetail), lhsSubscriberDetail, rhsSubscriberDetail)) {
                return false;
            }
        }
        {
            String lhsHeadOfficeCallingPhoneNumber;
            lhsHeadOfficeCallingPhoneNumber = this.getHeadOfficeCallingPhoneNumber();
            String rhsHeadOfficeCallingPhoneNumber;
            rhsHeadOfficeCallingPhoneNumber = that.getHeadOfficeCallingPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "headOfficeCallingPhoneNumber", lhsHeadOfficeCallingPhoneNumber), LocatorUtils.property(thatLocator, "headOfficeCallingPhoneNumber", rhsHeadOfficeCallingPhoneNumber), lhsHeadOfficeCallingPhoneNumber, rhsHeadOfficeCallingPhoneNumber)) {
                return false;
            }
        }
        {
            CorporateBatchOrderItem.SubscriberPhoneNum lhsSubscriberPhoneNum;
            lhsSubscriberPhoneNum = this.getSubscriberPhoneNum();
            CorporateBatchOrderItem.SubscriberPhoneNum rhsSubscriberPhoneNum;
            rhsSubscriberPhoneNum = that.getSubscriberPhoneNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberPhoneNum", lhsSubscriberPhoneNum), LocatorUtils.property(thatLocator, "subscriberPhoneNum", rhsSubscriberPhoneNum), lhsSubscriberPhoneNum, rhsSubscriberPhoneNum)) {
                return false;
            }
        }
        {
            Boolean lhsPortInInd;
            lhsPortInInd = this.isPortInInd();
            Boolean rhsPortInInd;
            rhsPortInInd = that.isPortInInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portInInd", lhsPortInInd), LocatorUtils.property(thatLocator, "portInInd", rhsPortInInd), lhsPortInInd, rhsPortInInd)) {
                return false;
            }
        }
        {
            PortRequest lhsPortRequest;
            lhsPortRequest = this.getPortRequest();
            PortRequest rhsPortRequest;
            rhsPortRequest = that.getPortRequest();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portRequest", lhsPortRequest), LocatorUtils.property(thatLocator, "portRequest", rhsPortRequest), lhsPortRequest, rhsPortRequest)) {
                return false;
            }
        }
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
            String lhsPricePlanCode;
            lhsPricePlanCode = this.getPricePlanCode();
            String rhsPricePlanCode;
            rhsPricePlanCode = that.getPricePlanCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCode", lhsPricePlanCode), LocatorUtils.property(thatLocator, "pricePlanCode", rhsPricePlanCode), lhsPricePlanCode, rhsPricePlanCode)) {
                return false;
            }
        }
        {
            String lhsPricePlanTransactionActionCode;
            lhsPricePlanTransactionActionCode = this.getPricePlanTransactionActionCode();
            String rhsPricePlanTransactionActionCode;
            rhsPricePlanTransactionActionCode = that.getPricePlanTransactionActionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanTransactionActionCode", lhsPricePlanTransactionActionCode), LocatorUtils.property(thatLocator, "pricePlanTransactionActionCode", rhsPricePlanTransactionActionCode), lhsPricePlanTransactionActionCode, rhsPricePlanTransactionActionCode)) {
                return false;
            }
        }
        {
            List<ServiceTransactionAction> lhsServiceItemList;
            lhsServiceItemList = (((this.serviceItemList!= null)&&(!this.serviceItemList.isEmpty()))?this.getServiceItemList():null);
            List<ServiceTransactionAction> rhsServiceItemList;
            rhsServiceItemList = (((that.serviceItemList!= null)&&(!that.serviceItemList.isEmpty()))?that.getServiceItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceItemList", lhsServiceItemList), LocatorUtils.property(thatLocator, "serviceItemList", rhsServiceItemList), lhsServiceItemList, rhsServiceItemList)) {
                return false;
            }
        }
        {
            BigInteger lhsContractTermInMonths;
            lhsContractTermInMonths = this.getContractTermInMonths();
            BigInteger rhsContractTermInMonths;
            rhsContractTermInMonths = that.getContractTermInMonths();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermInMonths", lhsContractTermInMonths), LocatorUtils.property(thatLocator, "contractTermInMonths", rhsContractTermInMonths), lhsContractTermInMonths, rhsContractTermInMonths)) {
                return false;
            }
        }
        {
            Date lhsContractEffectiveDate;
            lhsContractEffectiveDate = this.getContractEffectiveDate();
            Date rhsContractEffectiveDate;
            rhsContractEffectiveDate = that.getContractEffectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractEffectiveDate", lhsContractEffectiveDate), LocatorUtils.property(thatLocator, "contractEffectiveDate", rhsContractEffectiveDate), lhsContractEffectiveDate, rhsContractEffectiveDate)) {
                return false;
            }
        }
        {
            CorporateBatchOrderItem.SimOnlyTransaction lhsSimOnlyTransaction;
            lhsSimOnlyTransaction = this.getSimOnlyTransaction();
            CorporateBatchOrderItem.SimOnlyTransaction rhsSimOnlyTransaction;
            rhsSimOnlyTransaction = that.getSimOnlyTransaction();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simOnlyTransaction", lhsSimOnlyTransaction), LocatorUtils.property(thatLocator, "simOnlyTransaction", rhsSimOnlyTransaction), lhsSimOnlyTransaction, rhsSimOnlyTransaction)) {
                return false;
            }
        }
        {
            String lhsSimSerialNumber;
            lhsSimSerialNumber = this.getSimSerialNumber();
            String rhsSimSerialNumber;
            rhsSimSerialNumber = that.getSimSerialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simSerialNumber", lhsSimSerialNumber), LocatorUtils.property(thatLocator, "simSerialNumber", rhsSimSerialNumber), lhsSimSerialNumber, rhsSimSerialNumber)) {
                return false;
            }
        }
        {
            String lhsHandsetSerialNumber;
            lhsHandsetSerialNumber = this.getHandsetSerialNumber();
            String rhsHandsetSerialNumber;
            rhsHandsetSerialNumber = that.getHandsetSerialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetSerialNumber", lhsHandsetSerialNumber), LocatorUtils.property(thatLocator, "handsetSerialNumber", rhsHandsetSerialNumber), lhsHandsetSerialNumber, rhsHandsetSerialNumber)) {
                return false;
            }
        }
        {
            String lhsSimProductCd;
            lhsSimProductCd = this.getSimProductCd();
            String rhsSimProductCd;
            rhsSimProductCd = that.getSimProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simProductCd", lhsSimProductCd), LocatorUtils.property(thatLocator, "simProductCd", rhsSimProductCd), lhsSimProductCd, rhsSimProductCd)) {
                return false;
            }
        }
        {
            String lhsHandsetProductCd;
            lhsHandsetProductCd = this.getHandsetProductCd();
            String rhsHandsetProductCd;
            rhsHandsetProductCd = that.getHandsetProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetProductCd", lhsHandsetProductCd), LocatorUtils.property(thatLocator, "handsetProductCd", rhsHandsetProductCd), lhsHandsetProductCd, rhsHandsetProductCd)) {
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
     *         &lt;element name="deviceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "deviceTypeCd"
    })
    public static class SimOnlyTransaction
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String deviceTypeCd;

        /**
         * Gets the value of the deviceTypeCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDeviceTypeCd() {
            return deviceTypeCd;
        }

        /**
         * Sets the value of the deviceTypeCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDeviceTypeCd(String value) {
            this.deviceTypeCd = value;
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
                String theDeviceTypeCd;
                theDeviceTypeCd = this.getDeviceTypeCd();
                strategy.appendField(locator, this, "deviceTypeCd", buffer, theDeviceTypeCd);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CorporateBatchOrderItem.SimOnlyTransaction)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CorporateBatchOrderItem.SimOnlyTransaction that = ((CorporateBatchOrderItem.SimOnlyTransaction) object);
            {
                String lhsDeviceTypeCd;
                lhsDeviceTypeCd = this.getDeviceTypeCd();
                String rhsDeviceTypeCd;
                rhsDeviceTypeCd = that.getDeviceTypeCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "deviceTypeCd", lhsDeviceTypeCd), LocatorUtils.property(thatLocator, "deviceTypeCd", rhsDeviceTypeCd), lhsDeviceTypeCd, rhsDeviceTypeCd)) {
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
     *         &lt;element name="phoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="autoAssignPhoneNum" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="npa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="nxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="phoneNumProvinceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="phoneNumCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "phoneNum",
        "autoAssignPhoneNum"
    })
    public static class SubscriberPhoneNum
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String phoneNum;
        protected CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum autoAssignPhoneNum;

        /**
         * Gets the value of the phoneNum property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPhoneNum() {
            return phoneNum;
        }

        /**
         * Sets the value of the phoneNum property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPhoneNum(String value) {
            this.phoneNum = value;
        }

        /**
         * Gets the value of the autoAssignPhoneNum property.
         * 
         * @return
         *     possible object is
         *     {@link CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum }
         *     
         */
        public CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum getAutoAssignPhoneNum() {
            return autoAssignPhoneNum;
        }

        /**
         * Sets the value of the autoAssignPhoneNum property.
         * 
         * @param value
         *     allowed object is
         *     {@link CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum }
         *     
         */
        public void setAutoAssignPhoneNum(CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum value) {
            this.autoAssignPhoneNum = value;
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
                String thePhoneNum;
                thePhoneNum = this.getPhoneNum();
                strategy.appendField(locator, this, "phoneNum", buffer, thePhoneNum);
            }
            {
                CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum theAutoAssignPhoneNum;
                theAutoAssignPhoneNum = this.getAutoAssignPhoneNum();
                strategy.appendField(locator, this, "autoAssignPhoneNum", buffer, theAutoAssignPhoneNum);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CorporateBatchOrderItem.SubscriberPhoneNum)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CorporateBatchOrderItem.SubscriberPhoneNum that = ((CorporateBatchOrderItem.SubscriberPhoneNum) object);
            {
                String lhsPhoneNum;
                lhsPhoneNum = this.getPhoneNum();
                String rhsPhoneNum;
                rhsPhoneNum = that.getPhoneNum();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNum", lhsPhoneNum), LocatorUtils.property(thatLocator, "phoneNum", rhsPhoneNum), lhsPhoneNum, rhsPhoneNum)) {
                    return false;
                }
            }
            {
                CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum lhsAutoAssignPhoneNum;
                lhsAutoAssignPhoneNum = this.getAutoAssignPhoneNum();
                CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum rhsAutoAssignPhoneNum;
                rhsAutoAssignPhoneNum = that.getAutoAssignPhoneNum();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "autoAssignPhoneNum", lhsAutoAssignPhoneNum), LocatorUtils.property(thatLocator, "autoAssignPhoneNum", rhsAutoAssignPhoneNum), lhsAutoAssignPhoneNum, rhsAutoAssignPhoneNum)) {
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
         *         &lt;element name="npa" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="nxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="phoneNumProvinceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="phoneNumCityName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
            "npa",
            "nxx",
            "phoneNumProvinceCd",
            "phoneNumCityName"
        })
        public static class AutoAssignPhoneNum
            implements Serializable, Equals, ToString
        {

            private final static long serialVersionUID = 2L;
            protected String npa;
            protected String nxx;
            protected String phoneNumProvinceCd;
            protected String phoneNumCityName;

            /**
             * Gets the value of the npa property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNpa() {
                return npa;
            }

            /**
             * Sets the value of the npa property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNpa(String value) {
                this.npa = value;
            }

            /**
             * Gets the value of the nxx property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getNxx() {
                return nxx;
            }

            /**
             * Sets the value of the nxx property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setNxx(String value) {
                this.nxx = value;
            }

            /**
             * Gets the value of the phoneNumProvinceCd property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPhoneNumProvinceCd() {
                return phoneNumProvinceCd;
            }

            /**
             * Sets the value of the phoneNumProvinceCd property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPhoneNumProvinceCd(String value) {
                this.phoneNumProvinceCd = value;
            }

            /**
             * Gets the value of the phoneNumCityName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPhoneNumCityName() {
                return phoneNumCityName;
            }

            /**
             * Sets the value of the phoneNumCityName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPhoneNumCityName(String value) {
                this.phoneNumCityName = value;
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
                    String theNpa;
                    theNpa = this.getNpa();
                    strategy.appendField(locator, this, "npa", buffer, theNpa);
                }
                {
                    String theNxx;
                    theNxx = this.getNxx();
                    strategy.appendField(locator, this, "nxx", buffer, theNxx);
                }
                {
                    String thePhoneNumProvinceCd;
                    thePhoneNumProvinceCd = this.getPhoneNumProvinceCd();
                    strategy.appendField(locator, this, "phoneNumProvinceCd", buffer, thePhoneNumProvinceCd);
                }
                {
                    String thePhoneNumCityName;
                    thePhoneNumCityName = this.getPhoneNumCityName();
                    strategy.appendField(locator, this, "phoneNumCityName", buffer, thePhoneNumCityName);
                }
                return buffer;
            }

            public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
                if (!(object instanceof CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum)) {
                    return false;
                }
                if (this == object) {
                    return true;
                }
                final CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum that = ((CorporateBatchOrderItem.SubscriberPhoneNum.AutoAssignPhoneNum) object);
                {
                    String lhsNpa;
                    lhsNpa = this.getNpa();
                    String rhsNpa;
                    rhsNpa = that.getNpa();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "npa", lhsNpa), LocatorUtils.property(thatLocator, "npa", rhsNpa), lhsNpa, rhsNpa)) {
                        return false;
                    }
                }
                {
                    String lhsNxx;
                    lhsNxx = this.getNxx();
                    String rhsNxx;
                    rhsNxx = that.getNxx();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "nxx", lhsNxx), LocatorUtils.property(thatLocator, "nxx", rhsNxx), lhsNxx, rhsNxx)) {
                        return false;
                    }
                }
                {
                    String lhsPhoneNumProvinceCd;
                    lhsPhoneNumProvinceCd = this.getPhoneNumProvinceCd();
                    String rhsPhoneNumProvinceCd;
                    rhsPhoneNumProvinceCd = that.getPhoneNumProvinceCd();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumProvinceCd", lhsPhoneNumProvinceCd), LocatorUtils.property(thatLocator, "phoneNumProvinceCd", rhsPhoneNumProvinceCd), lhsPhoneNumProvinceCd, rhsPhoneNumProvinceCd)) {
                        return false;
                    }
                }
                {
                    String lhsPhoneNumCityName;
                    lhsPhoneNumCityName = this.getPhoneNumCityName();
                    String rhsPhoneNumCityName;
                    rhsPhoneNumCityName = that.getPhoneNumCityName();
                    if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumCityName", lhsPhoneNumCityName), LocatorUtils.property(thatLocator, "phoneNumCityName", rhsPhoneNumCityName), lhsPhoneNumCityName, rhsPhoneNumCityName)) {
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
