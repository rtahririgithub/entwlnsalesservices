
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
 * Deprecated. To be removed in V6.x. AccountProductSummary provides a product summary of on Wireline Billing Associated associated with the Customer 
 * 
 * <p>Java class for AccountProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="payChannelNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscribedProductSummaryList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductSummary" maxOccurs="100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountProductSummary", propOrder = {
    "accountNumber",
    "payChannelNumber",
    "subscribedProductSummaryList"
})
public class AccountProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String accountNumber;
    protected String payChannelNumber;
    @XmlElement(required = true)
    protected List<WirelineProductSummary> subscribedProductSummaryList;

    /**
     * Gets the value of the accountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /**
     * Sets the value of the accountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountNumber(String value) {
        this.accountNumber = value;
    }

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
     * Gets the value of the subscribedProductSummaryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscribedProductSummaryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscribedProductSummaryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineProductSummary }
     * 
     * 
     */
    public List<WirelineProductSummary> getSubscribedProductSummaryList() {
        if (subscribedProductSummaryList == null) {
            subscribedProductSummaryList = new ArrayList<WirelineProductSummary>();
        }
        return this.subscribedProductSummaryList;
    }

    /**
     * Sets the value of the subscribedProductSummaryList property.
     * 
     * @param subscribedProductSummaryList
     *     allowed object is
     *     {@link WirelineProductSummary }
     *     
     */
    public void setSubscribedProductSummaryList(List<WirelineProductSummary> subscribedProductSummaryList) {
        this.subscribedProductSummaryList = subscribedProductSummaryList;
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
            String theAccountNumber;
            theAccountNumber = this.getAccountNumber();
            strategy.appendField(locator, this, "accountNumber", buffer, theAccountNumber);
        }
        {
            String thePayChannelNumber;
            thePayChannelNumber = this.getPayChannelNumber();
            strategy.appendField(locator, this, "payChannelNumber", buffer, thePayChannelNumber);
        }
        {
            List<WirelineProductSummary> theSubscribedProductSummaryList;
            theSubscribedProductSummaryList = (((this.subscribedProductSummaryList!= null)&&(!this.subscribedProductSummaryList.isEmpty()))?this.getSubscribedProductSummaryList():null);
            strategy.appendField(locator, this, "subscribedProductSummaryList", buffer, theSubscribedProductSummaryList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountProductSummary that = ((AccountProductSummary) object);
        {
            String lhsAccountNumber;
            lhsAccountNumber = this.getAccountNumber();
            String rhsAccountNumber;
            rhsAccountNumber = that.getAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountNumber", lhsAccountNumber), LocatorUtils.property(thatLocator, "accountNumber", rhsAccountNumber), lhsAccountNumber, rhsAccountNumber)) {
                return false;
            }
        }
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
            List<WirelineProductSummary> lhsSubscribedProductSummaryList;
            lhsSubscribedProductSummaryList = (((this.subscribedProductSummaryList!= null)&&(!this.subscribedProductSummaryList.isEmpty()))?this.getSubscribedProductSummaryList():null);
            List<WirelineProductSummary> rhsSubscribedProductSummaryList;
            rhsSubscribedProductSummaryList = (((that.subscribedProductSummaryList!= null)&&(!that.subscribedProductSummaryList.isEmpty()))?that.getSubscribedProductSummaryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscribedProductSummaryList", lhsSubscribedProductSummaryList), LocatorUtils.property(thatLocator, "subscribedProductSummaryList", rhsSubscribedProductSummaryList), lhsSubscribedProductSummaryList, rhsSubscribedProductSummaryList)) {
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
