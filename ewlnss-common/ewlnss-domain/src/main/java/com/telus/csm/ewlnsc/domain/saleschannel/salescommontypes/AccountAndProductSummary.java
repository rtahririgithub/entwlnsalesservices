
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
 * FFH and Mobility Account and Product and Subscriber Summary information
 * 
 * <p>Java class for AccountAndProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountAndProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountMasterSourceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="accountStatusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountHolderName" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" minOccurs="0"/>
 *         &lt;element name="subscribedProductList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductSummary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="subscriberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberBase" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountAndProductSummary", propOrder = {
    "billingAccountNumber",
    "accountMasterSourceTypeCd",
    "billingAddress",
    "accountStatusCode",
    "accountHolderName",
    "subscribedProductList",
    "subscriberList"
})
public class AccountAndProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String billingAccountNumber;
    protected String accountMasterSourceTypeCd;
    protected Address billingAddress;
    protected String accountStatusCode;
    protected Name accountHolderName;
    protected List<WirelineProductSummary> subscribedProductList;
    protected List<SubscriberBase> subscriberList;

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
     * Gets the value of the subscribedProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscribedProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscribedProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineProductSummary }
     * 
     * 
     */
    public List<WirelineProductSummary> getSubscribedProductList() {
        if (subscribedProductList == null) {
            subscribedProductList = new ArrayList<WirelineProductSummary>();
        }
        return this.subscribedProductList;
    }

    /**
     * Gets the value of the subscriberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscriberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscriberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubscriberBase }
     * 
     * 
     */
    public List<SubscriberBase> getSubscriberList() {
        if (subscriberList == null) {
            subscriberList = new ArrayList<SubscriberBase>();
        }
        return this.subscriberList;
    }

    /**
     * Sets the value of the subscribedProductList property.
     * 
     * @param subscribedProductList
     *     allowed object is
     *     {@link WirelineProductSummary }
     *     
     */
    public void setSubscribedProductList(List<WirelineProductSummary> subscribedProductList) {
        this.subscribedProductList = subscribedProductList;
    }

    /**
     * Sets the value of the subscriberList property.
     * 
     * @param subscriberList
     *     allowed object is
     *     {@link SubscriberBase }
     *     
     */
    public void setSubscriberList(List<SubscriberBase> subscriberList) {
        this.subscriberList = subscriberList;
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
            String theAccountMasterSourceTypeCd;
            theAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
            strategy.appendField(locator, this, "accountMasterSourceTypeCd", buffer, theAccountMasterSourceTypeCd);
        }
        {
            Address theBillingAddress;
            theBillingAddress = this.getBillingAddress();
            strategy.appendField(locator, this, "billingAddress", buffer, theBillingAddress);
        }
        {
            String theAccountStatusCode;
            theAccountStatusCode = this.getAccountStatusCode();
            strategy.appendField(locator, this, "accountStatusCode", buffer, theAccountStatusCode);
        }
        {
            Name theAccountHolderName;
            theAccountHolderName = this.getAccountHolderName();
            strategy.appendField(locator, this, "accountHolderName", buffer, theAccountHolderName);
        }
        {
            List<WirelineProductSummary> theSubscribedProductList;
            theSubscribedProductList = (((this.subscribedProductList!= null)&&(!this.subscribedProductList.isEmpty()))?this.getSubscribedProductList():null);
            strategy.appendField(locator, this, "subscribedProductList", buffer, theSubscribedProductList);
        }
        {
            List<SubscriberBase> theSubscriberList;
            theSubscriberList = (((this.subscriberList!= null)&&(!this.subscriberList.isEmpty()))?this.getSubscriberList():null);
            strategy.appendField(locator, this, "subscriberList", buffer, theSubscriberList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountAndProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountAndProductSummary that = ((AccountAndProductSummary) object);
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
            String lhsAccountMasterSourceTypeCd;
            lhsAccountMasterSourceTypeCd = this.getAccountMasterSourceTypeCd();
            String rhsAccountMasterSourceTypeCd;
            rhsAccountMasterSourceTypeCd = that.getAccountMasterSourceTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountMasterSourceTypeCd", lhsAccountMasterSourceTypeCd), LocatorUtils.property(thatLocator, "accountMasterSourceTypeCd", rhsAccountMasterSourceTypeCd), lhsAccountMasterSourceTypeCd, rhsAccountMasterSourceTypeCd)) {
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
            String lhsAccountStatusCode;
            lhsAccountStatusCode = this.getAccountStatusCode();
            String rhsAccountStatusCode;
            rhsAccountStatusCode = that.getAccountStatusCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountStatusCode", lhsAccountStatusCode), LocatorUtils.property(thatLocator, "accountStatusCode", rhsAccountStatusCode), lhsAccountStatusCode, rhsAccountStatusCode)) {
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
            List<WirelineProductSummary> lhsSubscribedProductList;
            lhsSubscribedProductList = (((this.subscribedProductList!= null)&&(!this.subscribedProductList.isEmpty()))?this.getSubscribedProductList():null);
            List<WirelineProductSummary> rhsSubscribedProductList;
            rhsSubscribedProductList = (((that.subscribedProductList!= null)&&(!that.subscribedProductList.isEmpty()))?that.getSubscribedProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscribedProductList", lhsSubscribedProductList), LocatorUtils.property(thatLocator, "subscribedProductList", rhsSubscribedProductList), lhsSubscribedProductList, rhsSubscribedProductList)) {
                return false;
            }
        }
        {
            List<SubscriberBase> lhsSubscriberList;
            lhsSubscriberList = (((this.subscriberList!= null)&&(!this.subscriberList.isEmpty()))?this.getSubscriberList():null);
            List<SubscriberBase> rhsSubscriberList;
            rhsSubscriberList = (((that.subscriberList!= null)&&(!that.subscriberList.isEmpty()))?that.getSubscriberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberList", lhsSubscriberList), LocatorUtils.property(thatLocator, "subscriberList", rhsSubscriberList), lhsSubscriberList, rhsSubscriberList)) {
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
