
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for SalesHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceAddressBase"/>
 *         &lt;element name="subscribedServiceIdentifierList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceIdentifier" maxOccurs="3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesHeader", propOrder = {
    "customerId",
    "billingAccountNumber",
    "serviceAddress",
    "subscribedServiceIdentifierList"
})
public class SalesHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String customerId;
    protected String billingAccountNumber;
    @XmlElement(required = true)
    protected ServiceAddressBase serviceAddress;
    protected List<ServiceIdentifier> subscribedServiceIdentifierList;

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
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesHeader that = ((SalesHeader) object);
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
