
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * Default link type = 'OFFERASSOC'
 * 
 * <p>Java class for SalesItemPerk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesItemPerk">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingAccountNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountMasterSourceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="linkedAccountSummary" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="billingAccountNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="accountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="accountSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="accountMasterSourceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="accountStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="subscriberPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="billingAccountAuthenticationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="newAccountAssociationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="wirelineProductTypeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="wirelessPerkList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessPerk" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="wirelinePerkList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelinePerk" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesItemPerk", propOrder = {
    "billingAccountNum",
    "accountMasterSourceTypeCd",
    "linkedAccountSummary",
    "wirelessPerkList",
    "wirelinePerkList"
})
public class SalesItemPerk
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String billingAccountNum;
    protected String accountMasterSourceTypeCd;
    protected SalesItemPerk.LinkedAccountSummary linkedAccountSummary;
    protected List<WirelessPerk> wirelessPerkList;
    protected List<WirelinePerk> wirelinePerkList;

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
     * Gets the value of the accountMasterSourceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountMasterSourceTypeCd() {
        return accountMasterSourceTypeCd;
    }

    /**
     * Sets the value of the accountMasterSourceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountMasterSourceTypeCd(String value) {
        this.accountMasterSourceTypeCd = value;
    }

    /**
     * Gets the value of the linkedAccountSummary property.
     * 
     * @return
     *     possible object is
     *     {@link SalesItemPerk.LinkedAccountSummary }
     *     
     */
    public SalesItemPerk.LinkedAccountSummary getLinkedAccountSummary() {
        return linkedAccountSummary;
    }

    /**
     * Sets the value of the linkedAccountSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesItemPerk.LinkedAccountSummary }
     *     
     */
    public void setLinkedAccountSummary(SalesItemPerk.LinkedAccountSummary value) {
        this.linkedAccountSummary = value;
    }

    /**
     * Gets the value of the wirelessPerkList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelessPerkList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelessPerkList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelessPerk }
     * 
     * 
     */
    public List<WirelessPerk> getWirelessPerkList() {
        if (wirelessPerkList == null) {
            wirelessPerkList = new ArrayList<WirelessPerk>();
        }
        return this.wirelessPerkList;
    }

    /**
     * Gets the value of the wirelinePerkList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelinePerkList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelinePerkList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelinePerk }
     * 
     * 
     */
    public List<WirelinePerk> getWirelinePerkList() {
        if (wirelinePerkList == null) {
            wirelinePerkList = new ArrayList<WirelinePerk>();
        }
        return this.wirelinePerkList;
    }

    /**
     * Sets the value of the wirelessPerkList property.
     * 
     * @param wirelessPerkList
     *     allowed object is
     *     {@link WirelessPerk }
     *     
     */
    public void setWirelessPerkList(List<WirelessPerk> wirelessPerkList) {
        this.wirelessPerkList = wirelessPerkList;
    }

    /**
     * Sets the value of the wirelinePerkList property.
     * 
     * @param wirelinePerkList
     *     allowed object is
     *     {@link WirelinePerk }
     *     
     */
    public void setWirelinePerkList(List<WirelinePerk> wirelinePerkList) {
        this.wirelinePerkList = wirelinePerkList;
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
            String theBillingAccountNum;
            theBillingAccountNum = this.getBillingAccountNum();
            strategy.appendField(locator, this, "billingAccountNum", buffer, theBillingAccountNum);
        }
        {
            String theAccountMasterSourceTypeCd;
            theAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
            strategy.appendField(locator, this, "accountMasterSourceTypeCd", buffer, theAccountMasterSourceTypeCd);
        }
        {
            SalesItemPerk.LinkedAccountSummary theLinkedAccountSummary;
            theLinkedAccountSummary = this.getLinkedAccountSummary();
            strategy.appendField(locator, this, "linkedAccountSummary", buffer, theLinkedAccountSummary);
        }
        {
            List<WirelessPerk> theWirelessPerkList;
            theWirelessPerkList = (((this.wirelessPerkList!= null)&&(!this.wirelessPerkList.isEmpty()))?this.getWirelessPerkList():null);
            strategy.appendField(locator, this, "wirelessPerkList", buffer, theWirelessPerkList);
        }
        {
            List<WirelinePerk> theWirelinePerkList;
            theWirelinePerkList = (((this.wirelinePerkList!= null)&&(!this.wirelinePerkList.isEmpty()))?this.getWirelinePerkList():null);
            strategy.appendField(locator, this, "wirelinePerkList", buffer, theWirelinePerkList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesItemPerk)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesItemPerk that = ((SalesItemPerk) object);
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
            String lhsAccountMasterSourceTypeCd;
            lhsAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
            String rhsAccountMasterSourceTypeCd;
            rhsAccountMasterSourceTypeCd = that.getAccountMasterSourceTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountMasterSourceTypeCd", lhsAccountMasterSourceTypeCd), LocatorUtils.property(thatLocator, "accountMasterSourceTypeCd", rhsAccountMasterSourceTypeCd), lhsAccountMasterSourceTypeCd, rhsAccountMasterSourceTypeCd)) {
                return false;
            }
        }
        {
            SalesItemPerk.LinkedAccountSummary lhsLinkedAccountSummary;
            lhsLinkedAccountSummary = this.getLinkedAccountSummary();
            SalesItemPerk.LinkedAccountSummary rhsLinkedAccountSummary;
            rhsLinkedAccountSummary = that.getLinkedAccountSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "linkedAccountSummary", lhsLinkedAccountSummary), LocatorUtils.property(thatLocator, "linkedAccountSummary", rhsLinkedAccountSummary), lhsLinkedAccountSummary, rhsLinkedAccountSummary)) {
                return false;
            }
        }
        {
            List<WirelessPerk> lhsWirelessPerkList;
            lhsWirelessPerkList = (((this.wirelessPerkList!= null)&&(!this.wirelessPerkList.isEmpty()))?this.getWirelessPerkList():null);
            List<WirelessPerk> rhsWirelessPerkList;
            rhsWirelessPerkList = (((that.wirelessPerkList!= null)&&(!that.wirelessPerkList.isEmpty()))?that.getWirelessPerkList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessPerkList", lhsWirelessPerkList), LocatorUtils.property(thatLocator, "wirelessPerkList", rhsWirelessPerkList), lhsWirelessPerkList, rhsWirelessPerkList)) {
                return false;
            }
        }
        {
            List<WirelinePerk> lhsWirelinePerkList;
            lhsWirelinePerkList = (((this.wirelinePerkList!= null)&&(!this.wirelinePerkList.isEmpty()))?this.getWirelinePerkList():null);
            List<WirelinePerk> rhsWirelinePerkList;
            rhsWirelinePerkList = (((that.wirelinePerkList!= null)&&(!that.wirelinePerkList.isEmpty()))?that.getWirelinePerkList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelinePerkList", lhsWirelinePerkList), LocatorUtils.property(thatLocator, "wirelinePerkList", rhsWirelinePerkList), lhsWirelinePerkList, rhsWirelinePerkList)) {
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
     *         &lt;element name="billingAccountNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="accountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="accountSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="accountMasterSourceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="accountStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="subscriberPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="billingAccountAuthenticationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="newAccountAssociationInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="wirelineProductTypeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "billingAccountNum",
        "accountTypeCd",
        "accountSubTypeCd",
        "accountMasterSourceTypeCd",
        "accountStatusCode",
        "subscriberPhoneNum",
        "billingAccountAuthenticationInd",
        "newAccountAssociationInd",
        "wirelineProductTypeList"
    })
    public static class LinkedAccountSummary
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String billingAccountNum;
        protected String accountTypeCd;
        protected String accountSubTypeCd;
        protected String accountMasterSourceTypeCd;
        protected String accountStatusCode;
        protected String subscriberPhoneNum;
        protected Boolean billingAccountAuthenticationInd;
        protected Boolean newAccountAssociationInd;
        protected List<String> wirelineProductTypeList;

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
         * Gets the value of the accountTypeCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountTypeCd() {
            return accountTypeCd;
        }

        /**
         * Sets the value of the accountTypeCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountTypeCd(String value) {
            this.accountTypeCd = value;
        }

        /**
         * Gets the value of the accountSubTypeCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountSubTypeCd() {
            return accountSubTypeCd;
        }

        /**
         * Sets the value of the accountSubTypeCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountSubTypeCd(String value) {
            this.accountSubTypeCd = value;
        }

        /**
         * Gets the value of the accountMasterSourceTypeCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAccountMasterSourceTypeCd() {
            return accountMasterSourceTypeCd;
        }

        /**
         * Sets the value of the accountMasterSourceTypeCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAccountMasterSourceTypeCd(String value) {
            this.accountMasterSourceTypeCd = value;
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
         * Gets the value of the subscriberPhoneNum property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSubscriberPhoneNum() {
            return subscriberPhoneNum;
        }

        /**
         * Sets the value of the subscriberPhoneNum property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSubscriberPhoneNum(String value) {
            this.subscriberPhoneNum = value;
        }

        /**
         * Gets the value of the billingAccountAuthenticationInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isBillingAccountAuthenticationInd() {
            return billingAccountAuthenticationInd;
        }

        /**
         * Sets the value of the billingAccountAuthenticationInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setBillingAccountAuthenticationInd(Boolean value) {
            this.billingAccountAuthenticationInd = value;
        }

        /**
         * Gets the value of the newAccountAssociationInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isNewAccountAssociationInd() {
            return newAccountAssociationInd;
        }

        /**
         * Sets the value of the newAccountAssociationInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setNewAccountAssociationInd(Boolean value) {
            this.newAccountAssociationInd = value;
        }

        /**
         * Gets the value of the wirelineProductTypeList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the wirelineProductTypeList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getWirelineProductTypeList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getWirelineProductTypeList() {
            if (wirelineProductTypeList == null) {
                wirelineProductTypeList = new ArrayList<String>();
            }
            return this.wirelineProductTypeList;
        }

        /**
         * Sets the value of the wirelineProductTypeList property.
         * 
         * @param wirelineProductTypeList
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setWirelineProductTypeList(List<String> wirelineProductTypeList) {
            this.wirelineProductTypeList = wirelineProductTypeList;
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
                String theBillingAccountNum;
                theBillingAccountNum = this.getBillingAccountNum();
                strategy.appendField(locator, this, "billingAccountNum", buffer, theBillingAccountNum);
            }
            {
                String theAccountTypeCd;
                theAccountTypeCd = this.getAccountTypeCd();
                strategy.appendField(locator, this, "accountTypeCd", buffer, theAccountTypeCd);
            }
            {
                String theAccountSubTypeCd;
                theAccountSubTypeCd = this.getAccountSubTypeCd();
                strategy.appendField(locator, this, "accountSubTypeCd", buffer, theAccountSubTypeCd);
            }
            {
                String theAccountMasterSourceTypeCd;
                theAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
                strategy.appendField(locator, this, "accountMasterSourceTypeCd", buffer, theAccountMasterSourceTypeCd);
            }
            {
                String theAccountStatusCode;
                theAccountStatusCode = this.getAccountStatusCode();
                strategy.appendField(locator, this, "accountStatusCode", buffer, theAccountStatusCode);
            }
            {
                String theSubscriberPhoneNum;
                theSubscriberPhoneNum = this.getSubscriberPhoneNum();
                strategy.appendField(locator, this, "subscriberPhoneNum", buffer, theSubscriberPhoneNum);
            }
            {
                Boolean theBillingAccountAuthenticationInd;
                theBillingAccountAuthenticationInd = this.isBillingAccountAuthenticationInd();
                strategy.appendField(locator, this, "billingAccountAuthenticationInd", buffer, theBillingAccountAuthenticationInd);
            }
            {
                Boolean theNewAccountAssociationInd;
                theNewAccountAssociationInd = this.isNewAccountAssociationInd();
                strategy.appendField(locator, this, "newAccountAssociationInd", buffer, theNewAccountAssociationInd);
            }
            {
                List<String> theWirelineProductTypeList;
                theWirelineProductTypeList = (((this.wirelineProductTypeList!= null)&&(!this.wirelineProductTypeList.isEmpty()))?this.getWirelineProductTypeList():null);
                strategy.appendField(locator, this, "wirelineProductTypeList", buffer, theWirelineProductTypeList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof SalesItemPerk.LinkedAccountSummary)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final SalesItemPerk.LinkedAccountSummary that = ((SalesItemPerk.LinkedAccountSummary) object);
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
                String lhsAccountTypeCd;
                lhsAccountTypeCd = this.getAccountTypeCd();
                String rhsAccountTypeCd;
                rhsAccountTypeCd = that.getAccountTypeCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "accountTypeCd", lhsAccountTypeCd), LocatorUtils.property(thatLocator, "accountTypeCd", rhsAccountTypeCd), lhsAccountTypeCd, rhsAccountTypeCd)) {
                    return false;
                }
            }
            {
                String lhsAccountSubTypeCd;
                lhsAccountSubTypeCd = this.getAccountSubTypeCd();
                String rhsAccountSubTypeCd;
                rhsAccountSubTypeCd = that.getAccountSubTypeCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "accountSubTypeCd", lhsAccountSubTypeCd), LocatorUtils.property(thatLocator, "accountSubTypeCd", rhsAccountSubTypeCd), lhsAccountSubTypeCd, rhsAccountSubTypeCd)) {
                    return false;
                }
            }
            {
                String lhsAccountMasterSourceTypeCd;
                lhsAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
                String rhsAccountMasterSourceTypeCd;
                rhsAccountMasterSourceTypeCd = that.getAccountMasterSourceTypeCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "accountMasterSourceTypeCd", lhsAccountMasterSourceTypeCd), LocatorUtils.property(thatLocator, "accountMasterSourceTypeCd", rhsAccountMasterSourceTypeCd), lhsAccountMasterSourceTypeCd, rhsAccountMasterSourceTypeCd)) {
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
                String lhsSubscriberPhoneNum;
                lhsSubscriberPhoneNum = this.getSubscriberPhoneNum();
                String rhsSubscriberPhoneNum;
                rhsSubscriberPhoneNum = that.getSubscriberPhoneNum();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberPhoneNum", lhsSubscriberPhoneNum), LocatorUtils.property(thatLocator, "subscriberPhoneNum", rhsSubscriberPhoneNum), lhsSubscriberPhoneNum, rhsSubscriberPhoneNum)) {
                    return false;
                }
            }
            {
                Boolean lhsBillingAccountAuthenticationInd;
                lhsBillingAccountAuthenticationInd = this.isBillingAccountAuthenticationInd();
                Boolean rhsBillingAccountAuthenticationInd;
                rhsBillingAccountAuthenticationInd = that.isBillingAccountAuthenticationInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountAuthenticationInd", lhsBillingAccountAuthenticationInd), LocatorUtils.property(thatLocator, "billingAccountAuthenticationInd", rhsBillingAccountAuthenticationInd), lhsBillingAccountAuthenticationInd, rhsBillingAccountAuthenticationInd)) {
                    return false;
                }
            }
            {
                Boolean lhsNewAccountAssociationInd;
                lhsNewAccountAssociationInd = this.isNewAccountAssociationInd();
                Boolean rhsNewAccountAssociationInd;
                rhsNewAccountAssociationInd = that.isNewAccountAssociationInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "newAccountAssociationInd", lhsNewAccountAssociationInd), LocatorUtils.property(thatLocator, "newAccountAssociationInd", rhsNewAccountAssociationInd), lhsNewAccountAssociationInd, rhsNewAccountAssociationInd)) {
                    return false;
                }
            }
            {
                List<String> lhsWirelineProductTypeList;
                lhsWirelineProductTypeList = (((this.wirelineProductTypeList!= null)&&(!this.wirelineProductTypeList.isEmpty()))?this.getWirelineProductTypeList():null);
                List<String> rhsWirelineProductTypeList;
                rhsWirelineProductTypeList = (((that.wirelineProductTypeList!= null)&&(!that.wirelineProductTypeList.isEmpty()))?that.getWirelineProductTypeList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineProductTypeList", lhsWirelineProductTypeList), LocatorUtils.property(thatLocator, "wirelineProductTypeList", rhsWirelineProductTypeList), lhsWirelineProductTypeList, rhsWirelineProductTypeList)) {
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
