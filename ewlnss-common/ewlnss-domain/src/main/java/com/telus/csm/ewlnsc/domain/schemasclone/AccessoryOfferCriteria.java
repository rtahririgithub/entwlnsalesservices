
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.telus.csm.ewlnsc.domain.schemasclone.AccessoryOfferCriteria;
import com.telus.csm.ewlnsc.domain.schemasclone.AccessoryOfferFilter;
import com.telus.csm.ewlnsc.domain.schemasclone.ServiceAddressBase;
import com.telus.csm.ewlnsc.domain.schemasclone.ServiceIdentifier;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelessSalesOrderSummary;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineSalesOrderSummary;


public class AccessoryOfferCriteria
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String customerId;
    protected String billingAccountNumber;
    protected ServiceAddressBase serviceAddress;
    protected List<ServiceIdentifier> subscribedServiceIdentifierList;
    protected WirelessSalesOrderSummary associatedWirelessSalesSummary;
    protected List<WirelineSalesOrderSummary> associatedWirelineSalesSummaryList;
    protected AccessoryOfferFilter offerFilter;

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
     * Gets the value of the serviceAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddressBase }
     *     
     */
    public ServiceAddressBase getServiceAddress() {
        return serviceAddress;
    }

    /**
     * Sets the value of the serviceAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddressBase }
     *     
     */
    public void setServiceAddress(ServiceAddressBase value) {
        this.serviceAddress = value;
    }

    /**
     * Gets the value of the subscribedServiceIdentifierList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subscribedServiceIdentifierList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubscribedServiceIdentifierList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceIdentifier }
     * 
     * 
     */
    public List<ServiceIdentifier> getSubscribedServiceIdentifierList() {
        if (subscribedServiceIdentifierList == null) {
            subscribedServiceIdentifierList = new ArrayList<ServiceIdentifier>();
        }
        return this.subscribedServiceIdentifierList;
    }

    /**
     * Gets the value of the associatedWirelessSalesSummary property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessSalesOrderSummary }
     *     
     */
    public WirelessSalesOrderSummary getAssociatedWirelessSalesSummary() {
        return associatedWirelessSalesSummary;
    }

    /**
     * Sets the value of the associatedWirelessSalesSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessSalesOrderSummary }
     *     
     */
    public void setAssociatedWirelessSalesSummary(WirelessSalesOrderSummary value) {
        this.associatedWirelessSalesSummary = value;
    }

    /**
     * Gets the value of the associatedWirelineSalesSummaryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedWirelineSalesSummaryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedWirelineSalesSummaryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineSalesOrderSummary }
     * 
     * 
     */
    public List<WirelineSalesOrderSummary> getAssociatedWirelineSalesSummaryList() {
        if (associatedWirelineSalesSummaryList == null) {
            associatedWirelineSalesSummaryList = new ArrayList<WirelineSalesOrderSummary>();
        }
        return this.associatedWirelineSalesSummaryList;
    }

    /**
     * Gets the value of the offerFilter property.
     * 
     * @return
     *     possible object is
     *     {@link AccessoryOfferFilter }
     *     
     */
    public AccessoryOfferFilter getOfferFilter() {
        return offerFilter;
    }

    /**
     * Sets the value of the offerFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessoryOfferFilter }
     *     
     */
    public void setOfferFilter(AccessoryOfferFilter value) {
        this.offerFilter = value;
    }

    /**
     * Sets the value of the subscribedServiceIdentifierList property.
     * 
     * @param subscribedServiceIdentifierList
     *     allowed object is
     *     {@link ServiceIdentifier }
     *     
     */
    public void setSubscribedServiceIdentifierList(List<ServiceIdentifier> subscribedServiceIdentifierList) {
        this.subscribedServiceIdentifierList = subscribedServiceIdentifierList;
    }

    /**
     * Sets the value of the associatedWirelineSalesSummaryList property.
     * 
     * @param associatedWirelineSalesSummaryList
     *     allowed object is
     *     {@link WirelineSalesOrderSummary }
     *     
     */
    public void setAssociatedWirelineSalesSummaryList(List<WirelineSalesOrderSummary> associatedWirelineSalesSummaryList) {
        this.associatedWirelineSalesSummaryList = associatedWirelineSalesSummaryList;
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
            String theBillingAccountNumber;
            theBillingAccountNumber = this.getBillingAccountNumber();
            strategy.appendField(locator, this, "billingAccountNumber", buffer, theBillingAccountNumber);
        }
        {
            ServiceAddressBase theServiceAddress;
            theServiceAddress = this.getServiceAddress();
            strategy.appendField(locator, this, "serviceAddress", buffer, theServiceAddress);
        }
        {
            List<ServiceIdentifier> theSubscribedServiceIdentifierList;
            theSubscribedServiceIdentifierList = (((this.subscribedServiceIdentifierList!= null)&&(!this.subscribedServiceIdentifierList.isEmpty()))?this.getSubscribedServiceIdentifierList():null);
            strategy.appendField(locator, this, "subscribedServiceIdentifierList", buffer, theSubscribedServiceIdentifierList);
        }
        {
            WirelessSalesOrderSummary theAssociatedWirelessSalesSummary;
            theAssociatedWirelessSalesSummary = this.getAssociatedWirelessSalesSummary();
            strategy.appendField(locator, this, "associatedWirelessSalesSummary", buffer, theAssociatedWirelessSalesSummary);
        }
        {
            List<WirelineSalesOrderSummary> theAssociatedWirelineSalesSummaryList;
            theAssociatedWirelineSalesSummaryList = (((this.associatedWirelineSalesSummaryList!= null)&&(!this.associatedWirelineSalesSummaryList.isEmpty()))?this.getAssociatedWirelineSalesSummaryList():null);
            strategy.appendField(locator, this, "associatedWirelineSalesSummaryList", buffer, theAssociatedWirelineSalesSummaryList);
        }
        {
            AccessoryOfferFilter theOfferFilter;
            theOfferFilter = this.getOfferFilter();
            strategy.appendField(locator, this, "offerFilter", buffer, theOfferFilter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOfferCriteria)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOfferCriteria that = ((AccessoryOfferCriteria) object);
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
            String lhsBillingAccountNumber;
            lhsBillingAccountNumber = this.getBillingAccountNumber();
            String rhsBillingAccountNumber;
            rhsBillingAccountNumber = that.getBillingAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNumber", lhsBillingAccountNumber), LocatorUtils.property(thatLocator, "billingAccountNumber", rhsBillingAccountNumber), lhsBillingAccountNumber, rhsBillingAccountNumber)) {
                return false;
            }
        }
        {
            ServiceAddressBase lhsServiceAddress;
            lhsServiceAddress = this.getServiceAddress();
            ServiceAddressBase rhsServiceAddress;
            rhsServiceAddress = that.getServiceAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddress", lhsServiceAddress), LocatorUtils.property(thatLocator, "serviceAddress", rhsServiceAddress), lhsServiceAddress, rhsServiceAddress)) {
                return false;
            }
        }
        {
            List<ServiceIdentifier> lhsSubscribedServiceIdentifierList;
            lhsSubscribedServiceIdentifierList = (((this.subscribedServiceIdentifierList!= null)&&(!this.subscribedServiceIdentifierList.isEmpty()))?this.getSubscribedServiceIdentifierList():null);
            List<ServiceIdentifier> rhsSubscribedServiceIdentifierList;
            rhsSubscribedServiceIdentifierList = (((that.subscribedServiceIdentifierList!= null)&&(!that.subscribedServiceIdentifierList.isEmpty()))?that.getSubscribedServiceIdentifierList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscribedServiceIdentifierList", lhsSubscribedServiceIdentifierList), LocatorUtils.property(thatLocator, "subscribedServiceIdentifierList", rhsSubscribedServiceIdentifierList), lhsSubscribedServiceIdentifierList, rhsSubscribedServiceIdentifierList)) {
                return false;
            }
        }
        {
            WirelessSalesOrderSummary lhsAssociatedWirelessSalesSummary;
            lhsAssociatedWirelessSalesSummary = this.getAssociatedWirelessSalesSummary();
            WirelessSalesOrderSummary rhsAssociatedWirelessSalesSummary;
            rhsAssociatedWirelessSalesSummary = that.getAssociatedWirelessSalesSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedWirelessSalesSummary", lhsAssociatedWirelessSalesSummary), LocatorUtils.property(thatLocator, "associatedWirelessSalesSummary", rhsAssociatedWirelessSalesSummary), lhsAssociatedWirelessSalesSummary, rhsAssociatedWirelessSalesSummary)) {
                return false;
            }
        }
        {
            List<WirelineSalesOrderSummary> lhsAssociatedWirelineSalesSummaryList;
            lhsAssociatedWirelineSalesSummaryList = (((this.associatedWirelineSalesSummaryList!= null)&&(!this.associatedWirelineSalesSummaryList.isEmpty()))?this.getAssociatedWirelineSalesSummaryList():null);
            List<WirelineSalesOrderSummary> rhsAssociatedWirelineSalesSummaryList;
            rhsAssociatedWirelineSalesSummaryList = (((that.associatedWirelineSalesSummaryList!= null)&&(!that.associatedWirelineSalesSummaryList.isEmpty()))?that.getAssociatedWirelineSalesSummaryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedWirelineSalesSummaryList", lhsAssociatedWirelineSalesSummaryList), LocatorUtils.property(thatLocator, "associatedWirelineSalesSummaryList", rhsAssociatedWirelineSalesSummaryList), lhsAssociatedWirelineSalesSummaryList, rhsAssociatedWirelineSalesSummaryList)) {
                return false;
            }
        }
        {
            AccessoryOfferFilter lhsOfferFilter;
            lhsOfferFilter = this.getOfferFilter();
            AccessoryOfferFilter rhsOfferFilter;
            rhsOfferFilter = that.getOfferFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerFilter", lhsOfferFilter), LocatorUtils.property(thatLocator, "offerFilter", rhsOfferFilter), lhsOfferFilter, rhsOfferFilter)) {
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
