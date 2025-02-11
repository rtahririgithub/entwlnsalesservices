
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for EnterpriseOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnterpriseOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderContext" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OrderContext" minOccurs="0"/>
 *         &lt;element name="billingAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerDetail">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="serviceAddressDetail">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="fmsAddressId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="selectedProvinceCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="selectedOffer" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferHeader" minOccurs="0"/>
 *         &lt;element name="internetProduct" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HsicComponent" minOccurs="0"/>
 *         &lt;element name="televisionProduct" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelevisionComponent" minOccurs="0"/>
 *         &lt;element name="homePhoneProduct" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HomePhoneComponent" minOccurs="0"/>
 *         &lt;element name="deposit" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}FfhDeposit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnterpriseOrder", propOrder = {
    "orderContext",
    "billingAccountNumber",
    "customerDetail",
    "serviceAddressDetail",
    "selectedOffer",
    "internetProduct",
    "televisionProduct",
    "homePhoneProduct",
    "deposit"
})
public class EnterpriseOrder
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected OrderContext orderContext;
    protected String billingAccountNumber;
    @XmlElement(required = true)
    protected EnterpriseOrder.CustomerDetail customerDetail;
    @XmlElement(required = true)
    protected EnterpriseOrder.ServiceAddressDetail serviceAddressDetail;
    protected WirelineOfferHeader selectedOffer;
    protected HsicComponent internetProduct;
    protected TelevisionComponent televisionProduct;
    protected HomePhoneComponent homePhoneProduct;
    protected FfhDeposit deposit;

    /**
     * Gets the value of the orderContext property.
     * 
     * @return
     *     possible object is
     *     {@link OrderContext }
     *     
     */
    public OrderContext getOrderContext() {
        return orderContext;
    }

    /**
     * Sets the value of the orderContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderContext }
     *     
     */
    public void setOrderContext(OrderContext value) {
        this.orderContext = value;
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
     * Gets the value of the customerDetail property.
     * 
     * @return
     *     possible object is
     *     {@link EnterpriseOrder.CustomerDetail }
     *     
     */
    public EnterpriseOrder.CustomerDetail getCustomerDetail() {
        return customerDetail;
    }

    /**
     * Sets the value of the customerDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnterpriseOrder.CustomerDetail }
     *     
     */
    public void setCustomerDetail(EnterpriseOrder.CustomerDetail value) {
        this.customerDetail = value;
    }

    /**
     * Gets the value of the serviceAddressDetail property.
     * 
     * @return
     *     possible object is
     *     {@link EnterpriseOrder.ServiceAddressDetail }
     *     
     */
    public EnterpriseOrder.ServiceAddressDetail getServiceAddressDetail() {
        return serviceAddressDetail;
    }

    /**
     * Sets the value of the serviceAddressDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link EnterpriseOrder.ServiceAddressDetail }
     *     
     */
    public void setServiceAddressDetail(EnterpriseOrder.ServiceAddressDetail value) {
        this.serviceAddressDetail = value;
    }

    /**
     * Gets the value of the selectedOffer property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineOfferHeader }
     *     
     */
    public WirelineOfferHeader getSelectedOffer() {
        return selectedOffer;
    }

    /**
     * Sets the value of the selectedOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineOfferHeader }
     *     
     */
    public void setSelectedOffer(WirelineOfferHeader value) {
        this.selectedOffer = value;
    }

    /**
     * Gets the value of the internetProduct property.
     * 
     * @return
     *     possible object is
     *     {@link HsicComponent }
     *     
     */
    public HsicComponent getInternetProduct() {
        return internetProduct;
    }

    /**
     * Sets the value of the internetProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link HsicComponent }
     *     
     */
    public void setInternetProduct(HsicComponent value) {
        this.internetProduct = value;
    }

    /**
     * Gets the value of the televisionProduct property.
     * 
     * @return
     *     possible object is
     *     {@link TelevisionComponent }
     *     
     */
    public TelevisionComponent getTelevisionProduct() {
        return televisionProduct;
    }

    /**
     * Sets the value of the televisionProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelevisionComponent }
     *     
     */
    public void setTelevisionProduct(TelevisionComponent value) {
        this.televisionProduct = value;
    }

    /**
     * Gets the value of the homePhoneProduct property.
     * 
     * @return
     *     possible object is
     *     {@link HomePhoneComponent }
     *     
     */
    public HomePhoneComponent getHomePhoneProduct() {
        return homePhoneProduct;
    }

    /**
     * Sets the value of the homePhoneProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link HomePhoneComponent }
     *     
     */
    public void setHomePhoneProduct(HomePhoneComponent value) {
        this.homePhoneProduct = value;
    }

    /**
     * Gets the value of the deposit property.
     * 
     * @return
     *     possible object is
     *     {@link FfhDeposit }
     *     
     */
    public FfhDeposit getDeposit() {
        return deposit;
    }

    /**
     * Sets the value of the deposit property.
     * 
     * @param value
     *     allowed object is
     *     {@link FfhDeposit }
     *     
     */
    public void setDeposit(FfhDeposit value) {
        this.deposit = value;
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
            OrderContext theOrderContext;
            theOrderContext = this.getOrderContext();
            strategy.appendField(locator, this, "orderContext", buffer, theOrderContext);
        }
        {
            String theBillingAccountNumber;
            theBillingAccountNumber = this.getBillingAccountNumber();
            strategy.appendField(locator, this, "billingAccountNumber", buffer, theBillingAccountNumber);
        }
        {
            EnterpriseOrder.CustomerDetail theCustomerDetail;
            theCustomerDetail = this.getCustomerDetail();
            strategy.appendField(locator, this, "customerDetail", buffer, theCustomerDetail);
        }
        {
            EnterpriseOrder.ServiceAddressDetail theServiceAddressDetail;
            theServiceAddressDetail = this.getServiceAddressDetail();
            strategy.appendField(locator, this, "serviceAddressDetail", buffer, theServiceAddressDetail);
        }
        {
            WirelineOfferHeader theSelectedOffer;
            theSelectedOffer = this.getSelectedOffer();
            strategy.appendField(locator, this, "selectedOffer", buffer, theSelectedOffer);
        }
        {
            HsicComponent theInternetProduct;
            theInternetProduct = this.getInternetProduct();
            strategy.appendField(locator, this, "internetProduct", buffer, theInternetProduct);
        }
        {
            TelevisionComponent theTelevisionProduct;
            theTelevisionProduct = this.getTelevisionProduct();
            strategy.appendField(locator, this, "televisionProduct", buffer, theTelevisionProduct);
        }
        {
            HomePhoneComponent theHomePhoneProduct;
            theHomePhoneProduct = this.getHomePhoneProduct();
            strategy.appendField(locator, this, "homePhoneProduct", buffer, theHomePhoneProduct);
        }
        {
            FfhDeposit theDeposit;
            theDeposit = this.getDeposit();
            strategy.appendField(locator, this, "deposit", buffer, theDeposit);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EnterpriseOrder)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EnterpriseOrder that = ((EnterpriseOrder) object);
        {
            OrderContext lhsOrderContext;
            lhsOrderContext = this.getOrderContext();
            OrderContext rhsOrderContext;
            rhsOrderContext = that.getOrderContext();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderContext", lhsOrderContext), LocatorUtils.property(thatLocator, "orderContext", rhsOrderContext), lhsOrderContext, rhsOrderContext)) {
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
            EnterpriseOrder.CustomerDetail lhsCustomerDetail;
            lhsCustomerDetail = this.getCustomerDetail();
            EnterpriseOrder.CustomerDetail rhsCustomerDetail;
            rhsCustomerDetail = that.getCustomerDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerDetail", lhsCustomerDetail), LocatorUtils.property(thatLocator, "customerDetail", rhsCustomerDetail), lhsCustomerDetail, rhsCustomerDetail)) {
                return false;
            }
        }
        {
            EnterpriseOrder.ServiceAddressDetail lhsServiceAddressDetail;
            lhsServiceAddressDetail = this.getServiceAddressDetail();
            EnterpriseOrder.ServiceAddressDetail rhsServiceAddressDetail;
            rhsServiceAddressDetail = that.getServiceAddressDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddressDetail", lhsServiceAddressDetail), LocatorUtils.property(thatLocator, "serviceAddressDetail", rhsServiceAddressDetail), lhsServiceAddressDetail, rhsServiceAddressDetail)) {
                return false;
            }
        }
        {
            WirelineOfferHeader lhsSelectedOffer;
            lhsSelectedOffer = this.getSelectedOffer();
            WirelineOfferHeader rhsSelectedOffer;
            rhsSelectedOffer = that.getSelectedOffer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedOffer", lhsSelectedOffer), LocatorUtils.property(thatLocator, "selectedOffer", rhsSelectedOffer), lhsSelectedOffer, rhsSelectedOffer)) {
                return false;
            }
        }
        {
            HsicComponent lhsInternetProduct;
            lhsInternetProduct = this.getInternetProduct();
            HsicComponent rhsInternetProduct;
            rhsInternetProduct = that.getInternetProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "internetProduct", lhsInternetProduct), LocatorUtils.property(thatLocator, "internetProduct", rhsInternetProduct), lhsInternetProduct, rhsInternetProduct)) {
                return false;
            }
        }
        {
            TelevisionComponent lhsTelevisionProduct;
            lhsTelevisionProduct = this.getTelevisionProduct();
            TelevisionComponent rhsTelevisionProduct;
            rhsTelevisionProduct = that.getTelevisionProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "televisionProduct", lhsTelevisionProduct), LocatorUtils.property(thatLocator, "televisionProduct", rhsTelevisionProduct), lhsTelevisionProduct, rhsTelevisionProduct)) {
                return false;
            }
        }
        {
            HomePhoneComponent lhsHomePhoneProduct;
            lhsHomePhoneProduct = this.getHomePhoneProduct();
            HomePhoneComponent rhsHomePhoneProduct;
            rhsHomePhoneProduct = that.getHomePhoneProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "homePhoneProduct", lhsHomePhoneProduct), LocatorUtils.property(thatLocator, "homePhoneProduct", rhsHomePhoneProduct), lhsHomePhoneProduct, rhsHomePhoneProduct)) {
                return false;
            }
        }
        {
            FfhDeposit lhsDeposit;
            lhsDeposit = this.getDeposit();
            FfhDeposit rhsDeposit;
            rhsDeposit = that.getDeposit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deposit", lhsDeposit), LocatorUtils.property(thatLocator, "deposit", rhsDeposit), lhsDeposit, rhsDeposit)) {
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
    @XmlType(name = "", propOrder = {
        "customerId"
    })
    public static class CustomerDetail
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String customerId;

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
                String theCustomerId;
                theCustomerId = this.getCustomerId();
                strategy.appendField(locator, this, "customerId", buffer, theCustomerId);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof EnterpriseOrder.CustomerDetail)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final EnterpriseOrder.CustomerDetail that = ((EnterpriseOrder.CustomerDetail) object);
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
     *         &lt;element name="fmsAddressId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="selectedProvinceCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "fmsAddressId",
        "selectedProvinceCd"
    })
    public static class ServiceAddressDetail
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String fmsAddressId;
        @XmlElement(required = true)
        protected String selectedProvinceCd;

        /**
         * Gets the value of the fmsAddressId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFmsAddressId() {
            return fmsAddressId;
        }

        /**
         * Sets the value of the fmsAddressId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFmsAddressId(String value) {
            this.fmsAddressId = value;
        }

        /**
         * Gets the value of the selectedProvinceCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSelectedProvinceCd() {
            return selectedProvinceCd;
        }

        /**
         * Sets the value of the selectedProvinceCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSelectedProvinceCd(String value) {
            this.selectedProvinceCd = value;
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
                String theFmsAddressId;
                theFmsAddressId = this.getFmsAddressId();
                strategy.appendField(locator, this, "fmsAddressId", buffer, theFmsAddressId);
            }
            {
                String theSelectedProvinceCd;
                theSelectedProvinceCd = this.getSelectedProvinceCd();
                strategy.appendField(locator, this, "selectedProvinceCd", buffer, theSelectedProvinceCd);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof EnterpriseOrder.ServiceAddressDetail)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final EnterpriseOrder.ServiceAddressDetail that = ((EnterpriseOrder.ServiceAddressDetail) object);
            {
                String lhsFmsAddressId;
                lhsFmsAddressId = this.getFmsAddressId();
                String rhsFmsAddressId;
                rhsFmsAddressId = that.getFmsAddressId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "fmsAddressId", lhsFmsAddressId), LocatorUtils.property(thatLocator, "fmsAddressId", rhsFmsAddressId), lhsFmsAddressId, rhsFmsAddressId)) {
                    return false;
                }
            }
            {
                String lhsSelectedProvinceCd;
                lhsSelectedProvinceCd = this.getSelectedProvinceCd();
                String rhsSelectedProvinceCd;
                rhsSelectedProvinceCd = that.getSelectedProvinceCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedProvinceCd", lhsSelectedProvinceCd), LocatorUtils.property(thatLocator, "selectedProvinceCd", rhsSelectedProvinceCd), lhsSelectedProvinceCd, rhsSelectedProvinceCd)) {
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
