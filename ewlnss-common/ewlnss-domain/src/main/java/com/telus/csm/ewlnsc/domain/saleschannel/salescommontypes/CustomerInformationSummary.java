
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
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for CustomerInformationSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerInformationSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://xmlschema.tmi.telus.com/xsd/Customer/BaseTypes/CustomerCommon_v3}lastNameType" minOccurs="0"/>
 *         &lt;element name="dateOfBirth" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="totalBalance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="activeWirelineAccountsList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="activeWirelineAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingAccountSummary" maxOccurs="100" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="closedWirelineAccountsWithBalanceList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="closedWirelineAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingAccountSummary" maxOccurs="100" minOccurs="0"/>
 *                   &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cancelledWirelineAccountsList">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="cancelledWirelineAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingAccountSummary" maxOccurs="100" minOccurs="0"/>
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
@XmlType(name = "CustomerInformationSummary", propOrder = {
    "customerId",
    "firstName",
    "lastName",
    "dateOfBirth",
    "totalBalance",
    "activeWirelineAccountsList",
    "closedWirelineAccountsWithBalanceList",
    "cancelledWirelineAccountsList"
})
public class CustomerInformationSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String customerId;
    protected String firstName;
    protected String lastName;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date dateOfBirth;
    protected Double totalBalance;
    @XmlElement(required = true)
    protected CustomerInformationSummary.ActiveWirelineAccountsList activeWirelineAccountsList;
    @XmlElement(required = true)
    protected CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList closedWirelineAccountsWithBalanceList;
    @XmlElement(required = true)
    protected CustomerInformationSummary.CancelledWirelineAccountsList cancelledWirelineAccountsList;

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
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(Date value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the totalBalance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalBalance() {
        return totalBalance;
    }

    /**
     * Sets the value of the totalBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalBalance(Double value) {
        this.totalBalance = value;
    }

    /**
     * Gets the value of the activeWirelineAccountsList property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInformationSummary.ActiveWirelineAccountsList }
     *     
     */
    public CustomerInformationSummary.ActiveWirelineAccountsList getActiveWirelineAccountsList() {
        return activeWirelineAccountsList;
    }

    /**
     * Sets the value of the activeWirelineAccountsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInformationSummary.ActiveWirelineAccountsList }
     *     
     */
    public void setActiveWirelineAccountsList(CustomerInformationSummary.ActiveWirelineAccountsList value) {
        this.activeWirelineAccountsList = value;
    }

    /**
     * Gets the value of the closedWirelineAccountsWithBalanceList property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList }
     *     
     */
    public CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList getClosedWirelineAccountsWithBalanceList() {
        return closedWirelineAccountsWithBalanceList;
    }

    /**
     * Sets the value of the closedWirelineAccountsWithBalanceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList }
     *     
     */
    public void setClosedWirelineAccountsWithBalanceList(CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList value) {
        this.closedWirelineAccountsWithBalanceList = value;
    }

    /**
     * Gets the value of the cancelledWirelineAccountsList property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerInformationSummary.CancelledWirelineAccountsList }
     *     
     */
    public CustomerInformationSummary.CancelledWirelineAccountsList getCancelledWirelineAccountsList() {
        return cancelledWirelineAccountsList;
    }

    /**
     * Sets the value of the cancelledWirelineAccountsList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerInformationSummary.CancelledWirelineAccountsList }
     *     
     */
    public void setCancelledWirelineAccountsList(CustomerInformationSummary.CancelledWirelineAccountsList value) {
        this.cancelledWirelineAccountsList = value;
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
            String theCustomerId;
            theCustomerId = this.getCustomerId();
            strategy.appendField(locator, this, "customerId", buffer, theCustomerId);
        }
        {
            String theFirstName;
            theFirstName = this.getFirstName();
            strategy.appendField(locator, this, "firstName", buffer, theFirstName);
        }
        {
            String theLastName;
            theLastName = this.getLastName();
            strategy.appendField(locator, this, "lastName", buffer, theLastName);
        }
        {
            Date theDateOfBirth;
            theDateOfBirth = this.getDateOfBirth();
            strategy.appendField(locator, this, "dateOfBirth", buffer, theDateOfBirth);
        }
        {
            Double theTotalBalance;
            theTotalBalance = this.getTotalBalance();
            strategy.appendField(locator, this, "totalBalance", buffer, theTotalBalance);
        }
        {
            CustomerInformationSummary.ActiveWirelineAccountsList theActiveWirelineAccountsList;
            theActiveWirelineAccountsList = this.getActiveWirelineAccountsList();
            strategy.appendField(locator, this, "activeWirelineAccountsList", buffer, theActiveWirelineAccountsList);
        }
        {
            CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList theClosedWirelineAccountsWithBalanceList;
            theClosedWirelineAccountsWithBalanceList = this.getClosedWirelineAccountsWithBalanceList();
            strategy.appendField(locator, this, "closedWirelineAccountsWithBalanceList", buffer, theClosedWirelineAccountsWithBalanceList);
        }
        {
            CustomerInformationSummary.CancelledWirelineAccountsList theCancelledWirelineAccountsList;
            theCancelledWirelineAccountsList = this.getCancelledWirelineAccountsList();
            strategy.appendField(locator, this, "cancelledWirelineAccountsList", buffer, theCancelledWirelineAccountsList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CustomerInformationSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CustomerInformationSummary that = ((CustomerInformationSummary) object);
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
            String lhsFirstName;
            lhsFirstName = this.getFirstName();
            String rhsFirstName;
            rhsFirstName = that.getFirstName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstName", lhsFirstName), LocatorUtils.property(thatLocator, "firstName", rhsFirstName), lhsFirstName, rhsFirstName)) {
                return false;
            }
        }
        {
            String lhsLastName;
            lhsLastName = this.getLastName();
            String rhsLastName;
            rhsLastName = that.getLastName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastName", lhsLastName), LocatorUtils.property(thatLocator, "lastName", rhsLastName), lhsLastName, rhsLastName)) {
                return false;
            }
        }
        {
            Date lhsDateOfBirth;
            lhsDateOfBirth = this.getDateOfBirth();
            Date rhsDateOfBirth;
            rhsDateOfBirth = that.getDateOfBirth();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dateOfBirth", lhsDateOfBirth), LocatorUtils.property(thatLocator, "dateOfBirth", rhsDateOfBirth), lhsDateOfBirth, rhsDateOfBirth)) {
                return false;
            }
        }
        {
            Double lhsTotalBalance;
            lhsTotalBalance = this.getTotalBalance();
            Double rhsTotalBalance;
            rhsTotalBalance = that.getTotalBalance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalBalance", lhsTotalBalance), LocatorUtils.property(thatLocator, "totalBalance", rhsTotalBalance), lhsTotalBalance, rhsTotalBalance)) {
                return false;
            }
        }
        {
            CustomerInformationSummary.ActiveWirelineAccountsList lhsActiveWirelineAccountsList;
            lhsActiveWirelineAccountsList = this.getActiveWirelineAccountsList();
            CustomerInformationSummary.ActiveWirelineAccountsList rhsActiveWirelineAccountsList;
            rhsActiveWirelineAccountsList = that.getActiveWirelineAccountsList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "activeWirelineAccountsList", lhsActiveWirelineAccountsList), LocatorUtils.property(thatLocator, "activeWirelineAccountsList", rhsActiveWirelineAccountsList), lhsActiveWirelineAccountsList, rhsActiveWirelineAccountsList)) {
                return false;
            }
        }
        {
            CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList lhsClosedWirelineAccountsWithBalanceList;
            lhsClosedWirelineAccountsWithBalanceList = this.getClosedWirelineAccountsWithBalanceList();
            CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList rhsClosedWirelineAccountsWithBalanceList;
            rhsClosedWirelineAccountsWithBalanceList = that.getClosedWirelineAccountsWithBalanceList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "closedWirelineAccountsWithBalanceList", lhsClosedWirelineAccountsWithBalanceList), LocatorUtils.property(thatLocator, "closedWirelineAccountsWithBalanceList", rhsClosedWirelineAccountsWithBalanceList), lhsClosedWirelineAccountsWithBalanceList, rhsClosedWirelineAccountsWithBalanceList)) {
                return false;
            }
        }
        {
            CustomerInformationSummary.CancelledWirelineAccountsList lhsCancelledWirelineAccountsList;
            lhsCancelledWirelineAccountsList = this.getCancelledWirelineAccountsList();
            CustomerInformationSummary.CancelledWirelineAccountsList rhsCancelledWirelineAccountsList;
            rhsCancelledWirelineAccountsList = that.getCancelledWirelineAccountsList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cancelledWirelineAccountsList", lhsCancelledWirelineAccountsList), LocatorUtils.property(thatLocator, "cancelledWirelineAccountsList", rhsCancelledWirelineAccountsList), lhsCancelledWirelineAccountsList, rhsCancelledWirelineAccountsList)) {
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
     *         &lt;element name="activeWirelineAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingAccountSummary" maxOccurs="100" minOccurs="0"/>
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
        "activeWirelineAccount"
    })
    public static class ActiveWirelineAccountsList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected List<BillingAccountSummary> activeWirelineAccount;

        /**
         * Gets the value of the activeWirelineAccount property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the activeWirelineAccount property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getActiveWirelineAccount().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BillingAccountSummary }
         * 
         * 
         */
        public List<BillingAccountSummary> getActiveWirelineAccount() {
            if (activeWirelineAccount == null) {
                activeWirelineAccount = new ArrayList<BillingAccountSummary>();
            }
            return this.activeWirelineAccount;
        }

        /**
         * Sets the value of the activeWirelineAccount property.
         * 
         * @param activeWirelineAccount
         *     allowed object is
         *     {@link BillingAccountSummary }
         *     
         */
        public void setActiveWirelineAccount(List<BillingAccountSummary> activeWirelineAccount) {
            this.activeWirelineAccount = activeWirelineAccount;
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
                List<BillingAccountSummary> theActiveWirelineAccount;
                theActiveWirelineAccount = (((this.activeWirelineAccount!= null)&&(!this.activeWirelineAccount.isEmpty()))?this.getActiveWirelineAccount():null);
                strategy.appendField(locator, this, "activeWirelineAccount", buffer, theActiveWirelineAccount);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CustomerInformationSummary.ActiveWirelineAccountsList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CustomerInformationSummary.ActiveWirelineAccountsList that = ((CustomerInformationSummary.ActiveWirelineAccountsList) object);
            {
                List<BillingAccountSummary> lhsActiveWirelineAccount;
                lhsActiveWirelineAccount = (((this.activeWirelineAccount!= null)&&(!this.activeWirelineAccount.isEmpty()))?this.getActiveWirelineAccount():null);
                List<BillingAccountSummary> rhsActiveWirelineAccount;
                rhsActiveWirelineAccount = (((that.activeWirelineAccount!= null)&&(!that.activeWirelineAccount.isEmpty()))?that.getActiveWirelineAccount():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "activeWirelineAccount", lhsActiveWirelineAccount), LocatorUtils.property(thatLocator, "activeWirelineAccount", rhsActiveWirelineAccount), lhsActiveWirelineAccount, rhsActiveWirelineAccount)) {
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
     *         &lt;element name="cancelledWirelineAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingAccountSummary" maxOccurs="100" minOccurs="0"/>
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
        "cancelledWirelineAccount"
    })
    public static class CancelledWirelineAccountsList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected List<BillingAccountSummary> cancelledWirelineAccount;

        /**
         * Gets the value of the cancelledWirelineAccount property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the cancelledWirelineAccount property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getCancelledWirelineAccount().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BillingAccountSummary }
         * 
         * 
         */
        public List<BillingAccountSummary> getCancelledWirelineAccount() {
            if (cancelledWirelineAccount == null) {
                cancelledWirelineAccount = new ArrayList<BillingAccountSummary>();
            }
            return this.cancelledWirelineAccount;
        }

        /**
         * Sets the value of the cancelledWirelineAccount property.
         * 
         * @param cancelledWirelineAccount
         *     allowed object is
         *     {@link BillingAccountSummary }
         *     
         */
        public void setCancelledWirelineAccount(List<BillingAccountSummary> cancelledWirelineAccount) {
            this.cancelledWirelineAccount = cancelledWirelineAccount;
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
                List<BillingAccountSummary> theCancelledWirelineAccount;
                theCancelledWirelineAccount = (((this.cancelledWirelineAccount!= null)&&(!this.cancelledWirelineAccount.isEmpty()))?this.getCancelledWirelineAccount():null);
                strategy.appendField(locator, this, "cancelledWirelineAccount", buffer, theCancelledWirelineAccount);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CustomerInformationSummary.CancelledWirelineAccountsList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CustomerInformationSummary.CancelledWirelineAccountsList that = ((CustomerInformationSummary.CancelledWirelineAccountsList) object);
            {
                List<BillingAccountSummary> lhsCancelledWirelineAccount;
                lhsCancelledWirelineAccount = (((this.cancelledWirelineAccount!= null)&&(!this.cancelledWirelineAccount.isEmpty()))?this.getCancelledWirelineAccount():null);
                List<BillingAccountSummary> rhsCancelledWirelineAccount;
                rhsCancelledWirelineAccount = (((that.cancelledWirelineAccount!= null)&&(!that.cancelledWirelineAccount.isEmpty()))?that.getCancelledWirelineAccount():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "cancelledWirelineAccount", lhsCancelledWirelineAccount), LocatorUtils.property(thatLocator, "cancelledWirelineAccount", rhsCancelledWirelineAccount), lhsCancelledWirelineAccount, rhsCancelledWirelineAccount)) {
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
     *         &lt;element name="closedWirelineAccount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillingAccountSummary" maxOccurs="100" minOccurs="0"/>
     *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double"/>
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
        "closedWirelineAccount",
        "balance"
    })
    public static class ClosedWirelineAccountsWithBalanceList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected List<BillingAccountSummary> closedWirelineAccount;
        protected double balance;

        /**
         * Gets the value of the closedWirelineAccount property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the closedWirelineAccount property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getClosedWirelineAccount().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link BillingAccountSummary }
         * 
         * 
         */
        public List<BillingAccountSummary> getClosedWirelineAccount() {
            if (closedWirelineAccount == null) {
                closedWirelineAccount = new ArrayList<BillingAccountSummary>();
            }
            return this.closedWirelineAccount;
        }

        /**
         * Gets the value of the balance property.
         * 
         */
        public double getBalance() {
            return balance;
        }

        /**
         * Sets the value of the balance property.
         * 
         */
        public void setBalance(double value) {
            this.balance = value;
        }

        /**
         * Sets the value of the closedWirelineAccount property.
         * 
         * @param closedWirelineAccount
         *     allowed object is
         *     {@link BillingAccountSummary }
         *     
         */
        public void setClosedWirelineAccount(List<BillingAccountSummary> closedWirelineAccount) {
            this.closedWirelineAccount = closedWirelineAccount;
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
                List<BillingAccountSummary> theClosedWirelineAccount;
                theClosedWirelineAccount = (((this.closedWirelineAccount!= null)&&(!this.closedWirelineAccount.isEmpty()))?this.getClosedWirelineAccount():null);
                strategy.appendField(locator, this, "closedWirelineAccount", buffer, theClosedWirelineAccount);
            }
            {
                double theBalance;
                theBalance = (true?this.getBalance(): 0.0D);
                strategy.appendField(locator, this, "balance", buffer, theBalance);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList that = ((CustomerInformationSummary.ClosedWirelineAccountsWithBalanceList) object);
            {
                List<BillingAccountSummary> lhsClosedWirelineAccount;
                lhsClosedWirelineAccount = (((this.closedWirelineAccount!= null)&&(!this.closedWirelineAccount.isEmpty()))?this.getClosedWirelineAccount():null);
                List<BillingAccountSummary> rhsClosedWirelineAccount;
                rhsClosedWirelineAccount = (((that.closedWirelineAccount!= null)&&(!that.closedWirelineAccount.isEmpty()))?that.getClosedWirelineAccount():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "closedWirelineAccount", lhsClosedWirelineAccount), LocatorUtils.property(thatLocator, "closedWirelineAccount", rhsClosedWirelineAccount), lhsClosedWirelineAccount, rhsClosedWirelineAccount)) {
                    return false;
                }
            }
            {
                double lhsBalance;
                lhsBalance = (true?this.getBalance(): 0.0D);
                double rhsBalance;
                rhsBalance = (true?that.getBalance(): 0.0D);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "balance", lhsBalance), LocatorUtils.property(thatLocator, "balance", rhsBalance), lhsBalance, rhsBalance)) {
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
