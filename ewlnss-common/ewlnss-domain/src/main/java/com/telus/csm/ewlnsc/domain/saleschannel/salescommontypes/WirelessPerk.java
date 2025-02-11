
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.BillCredit;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessPerk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessPerk">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PerkSummary">
 *       &lt;sequence>
 *         &lt;element name="billCreditList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}BillCredit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="billDiscountPlan" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillDiscountPlan" minOccurs="0"/>
 *         &lt;element name="serviceList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="serviceDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessPerk", propOrder = {
    "billCreditList",
    "billDiscountPlan",
    "serviceList"
})
public class WirelessPerk
    extends PerkSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<BillCredit> billCreditList;
    protected BillDiscountPlan billDiscountPlan;
    protected List<WirelessPerk.ServiceList> serviceList;

    /**
     * Gets the value of the billCreditList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billCreditList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillCreditList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillCredit }
     * 
     * 
     */
    public List<BillCredit> getBillCreditList() {
        if (billCreditList == null) {
            billCreditList = new ArrayList<BillCredit>();
        }
        return this.billCreditList;
    }

    /**
     * Gets the value of the billDiscountPlan property.
     * 
     * @return
     *     possible object is
     *     {@link BillDiscountPlan }
     *     
     */
    public BillDiscountPlan getBillDiscountPlan() {
        return billDiscountPlan;
    }

    /**
     * Sets the value of the billDiscountPlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link BillDiscountPlan }
     *     
     */
    public void setBillDiscountPlan(BillDiscountPlan value) {
        this.billDiscountPlan = value;
    }

    /**
     * Gets the value of the serviceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelessPerk.ServiceList }
     * 
     * 
     */
    public List<WirelessPerk.ServiceList> getServiceList() {
        if (serviceList == null) {
            serviceList = new ArrayList<WirelessPerk.ServiceList>();
        }
        return this.serviceList;
    }

    /**
     * Sets the value of the billCreditList property.
     * 
     * @param billCreditList
     *     allowed object is
     *     {@link BillCredit }
     *     
     */
    public void setBillCreditList(List<BillCredit> billCreditList) {
        this.billCreditList = billCreditList;
    }

    /**
     * Sets the value of the serviceList property.
     * 
     * @param serviceList
     *     allowed object is
     *     {@link WirelessPerk.ServiceList }
     *     
     */
    public void setServiceList(List<WirelessPerk.ServiceList> serviceList) {
        this.serviceList = serviceList;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<BillCredit> theBillCreditList;
            theBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
            strategy.appendField(locator, this, "billCreditList", buffer, theBillCreditList);
        }
        {
            BillDiscountPlan theBillDiscountPlan;
            theBillDiscountPlan = this.getBillDiscountPlan();
            strategy.appendField(locator, this, "billDiscountPlan", buffer, theBillDiscountPlan);
        }
        {
            List<WirelessPerk.ServiceList> theServiceList;
            theServiceList = (((this.serviceList!= null)&&(!this.serviceList.isEmpty()))?this.getServiceList():null);
            strategy.appendField(locator, this, "serviceList", buffer, theServiceList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessPerk)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelessPerk that = ((WirelessPerk) object);
        {
            List<BillCredit> lhsBillCreditList;
            lhsBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
            List<BillCredit> rhsBillCreditList;
            rhsBillCreditList = (((that.billCreditList!= null)&&(!that.billCreditList.isEmpty()))?that.getBillCreditList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billCreditList", lhsBillCreditList), LocatorUtils.property(thatLocator, "billCreditList", rhsBillCreditList), lhsBillCreditList, rhsBillCreditList)) {
                return false;
            }
        }
        {
            BillDiscountPlan lhsBillDiscountPlan;
            lhsBillDiscountPlan = this.getBillDiscountPlan();
            BillDiscountPlan rhsBillDiscountPlan;
            rhsBillDiscountPlan = that.getBillDiscountPlan();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billDiscountPlan", lhsBillDiscountPlan), LocatorUtils.property(thatLocator, "billDiscountPlan", rhsBillDiscountPlan), lhsBillDiscountPlan, rhsBillDiscountPlan)) {
                return false;
            }
        }
        {
            List<WirelessPerk.ServiceList> lhsServiceList;
            lhsServiceList = (((this.serviceList!= null)&&(!this.serviceList.isEmpty()))?this.getServiceList():null);
            List<WirelessPerk.ServiceList> rhsServiceList;
            rhsServiceList = (((that.serviceList!= null)&&(!that.serviceList.isEmpty()))?that.getServiceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceList", lhsServiceList), LocatorUtils.property(thatLocator, "serviceList", rhsServiceList), lhsServiceList, rhsServiceList)) {
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
     *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="serviceDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
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
        "serviceCode",
        "serviceDescriptionList"
    })
    public static class ServiceList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String serviceCode;
        protected List<Description> serviceDescriptionList;

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
         * Gets the value of the serviceDescriptionList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the serviceDescriptionList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getServiceDescriptionList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Description }
         * 
         * 
         */
        public List<Description> getServiceDescriptionList() {
            if (serviceDescriptionList == null) {
                serviceDescriptionList = new ArrayList<Description>();
            }
            return this.serviceDescriptionList;
        }

        /**
         * Sets the value of the serviceDescriptionList property.
         * 
         * @param serviceDescriptionList
         *     allowed object is
         *     {@link Description }
         *     
         */
        public void setServiceDescriptionList(List<Description> serviceDescriptionList) {
            this.serviceDescriptionList = serviceDescriptionList;
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
                List<Description> theServiceDescriptionList;
                theServiceDescriptionList = (((this.serviceDescriptionList!= null)&&(!this.serviceDescriptionList.isEmpty()))?this.getServiceDescriptionList():null);
                strategy.appendField(locator, this, "serviceDescriptionList", buffer, theServiceDescriptionList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof WirelessPerk.ServiceList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final WirelessPerk.ServiceList that = ((WirelessPerk.ServiceList) object);
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
                List<Description> lhsServiceDescriptionList;
                lhsServiceDescriptionList = (((this.serviceDescriptionList!= null)&&(!this.serviceDescriptionList.isEmpty()))?this.getServiceDescriptionList():null);
                List<Description> rhsServiceDescriptionList;
                rhsServiceDescriptionList = (((that.serviceDescriptionList!= null)&&(!that.serviceDescriptionList.isEmpty()))?that.getServiceDescriptionList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceDescriptionList", lhsServiceDescriptionList), LocatorUtils.property(thatLocator, "serviceDescriptionList", rhsServiceDescriptionList), lhsServiceDescriptionList, rhsServiceDescriptionList)) {
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
