
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
 * <p>Java class for OrderContract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderContract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="order" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Order"/>
 *         &lt;element name="pricePlanIncludedFeature" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="includedFeatureList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OrderServiceItem" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="pricePlanServiceAllowanceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Usage" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rewardCommittmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardCommitment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accountInfo" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccountBase" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderContract", propOrder = {
    "order",
    "pricePlanIncludedFeature",
    "pricePlanServiceAllowanceList",
    "rewardCommittmentList",
    "accountInfo"
})
public class OrderContract
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Order order;
    protected OrderContract.PricePlanIncludedFeature pricePlanIncludedFeature;
    protected List<Usage> pricePlanServiceAllowanceList;
    protected List<RewardCommitment> rewardCommittmentList;
    protected AccountBase accountInfo;

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link Order }
     *     
     */
    public Order getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link Order }
     *     
     */
    public void setOrder(Order value) {
        this.order = value;
    }

    /**
     * Gets the value of the pricePlanIncludedFeature property.
     * 
     * @return
     *     possible object is
     *     {@link OrderContract.PricePlanIncludedFeature }
     *     
     */
    public OrderContract.PricePlanIncludedFeature getPricePlanIncludedFeature() {
        return pricePlanIncludedFeature;
    }

    /**
     * Sets the value of the pricePlanIncludedFeature property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderContract.PricePlanIncludedFeature }
     *     
     */
    public void setPricePlanIncludedFeature(OrderContract.PricePlanIncludedFeature value) {
        this.pricePlanIncludedFeature = value;
    }

    /**
     * Gets the value of the pricePlanServiceAllowanceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanServiceAllowanceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanServiceAllowanceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Usage }
     * 
     * 
     */
    public List<Usage> getPricePlanServiceAllowanceList() {
        if (pricePlanServiceAllowanceList == null) {
            pricePlanServiceAllowanceList = new ArrayList<Usage>();
        }
        return this.pricePlanServiceAllowanceList;
    }

    /**
     * Gets the value of the rewardCommittmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardCommittmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardCommittmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RewardCommitment }
     * 
     * 
     */
    public List<RewardCommitment> getRewardCommittmentList() {
        if (rewardCommittmentList == null) {
            rewardCommittmentList = new ArrayList<RewardCommitment>();
        }
        return this.rewardCommittmentList;
    }

    /**
     * Gets the value of the accountInfo property.
     * 
     * @return
     *     possible object is
     *     {@link AccountBase }
     *     
     */
    public AccountBase getAccountInfo() {
        return accountInfo;
    }

    /**
     * Sets the value of the accountInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountBase }
     *     
     */
    public void setAccountInfo(AccountBase value) {
        this.accountInfo = value;
    }

    /**
     * Sets the value of the pricePlanServiceAllowanceList property.
     * 
     * @param pricePlanServiceAllowanceList
     *     allowed object is
     *     {@link Usage }
     *     
     */
    public void setPricePlanServiceAllowanceList(List<Usage> pricePlanServiceAllowanceList) {
        this.pricePlanServiceAllowanceList = pricePlanServiceAllowanceList;
    }

    /**
     * Sets the value of the rewardCommittmentList property.
     * 
     * @param rewardCommittmentList
     *     allowed object is
     *     {@link RewardCommitment }
     *     
     */
    public void setRewardCommittmentList(List<RewardCommitment> rewardCommittmentList) {
        this.rewardCommittmentList = rewardCommittmentList;
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
            Order theOrder;
            theOrder = this.getOrder();
            strategy.appendField(locator, this, "order", buffer, theOrder);
        }
        {
            OrderContract.PricePlanIncludedFeature thePricePlanIncludedFeature;
            thePricePlanIncludedFeature = this.getPricePlanIncludedFeature();
            strategy.appendField(locator, this, "pricePlanIncludedFeature", buffer, thePricePlanIncludedFeature);
        }
        {
            List<Usage> thePricePlanServiceAllowanceList;
            thePricePlanServiceAllowanceList = (((this.pricePlanServiceAllowanceList!= null)&&(!this.pricePlanServiceAllowanceList.isEmpty()))?this.getPricePlanServiceAllowanceList():null);
            strategy.appendField(locator, this, "pricePlanServiceAllowanceList", buffer, thePricePlanServiceAllowanceList);
        }
        {
            List<RewardCommitment> theRewardCommittmentList;
            theRewardCommittmentList = (((this.rewardCommittmentList!= null)&&(!this.rewardCommittmentList.isEmpty()))?this.getRewardCommittmentList():null);
            strategy.appendField(locator, this, "rewardCommittmentList", buffer, theRewardCommittmentList);
        }
        {
            AccountBase theAccountInfo;
            theAccountInfo = this.getAccountInfo();
            strategy.appendField(locator, this, "accountInfo", buffer, theAccountInfo);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OrderContract)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OrderContract that = ((OrderContract) object);
        {
            Order lhsOrder;
            lhsOrder = this.getOrder();
            Order rhsOrder;
            rhsOrder = that.getOrder();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "order", lhsOrder), LocatorUtils.property(thatLocator, "order", rhsOrder), lhsOrder, rhsOrder)) {
                return false;
            }
        }
        {
            OrderContract.PricePlanIncludedFeature lhsPricePlanIncludedFeature;
            lhsPricePlanIncludedFeature = this.getPricePlanIncludedFeature();
            OrderContract.PricePlanIncludedFeature rhsPricePlanIncludedFeature;
            rhsPricePlanIncludedFeature = that.getPricePlanIncludedFeature();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanIncludedFeature", lhsPricePlanIncludedFeature), LocatorUtils.property(thatLocator, "pricePlanIncludedFeature", rhsPricePlanIncludedFeature), lhsPricePlanIncludedFeature, rhsPricePlanIncludedFeature)) {
                return false;
            }
        }
        {
            List<Usage> lhsPricePlanServiceAllowanceList;
            lhsPricePlanServiceAllowanceList = (((this.pricePlanServiceAllowanceList!= null)&&(!this.pricePlanServiceAllowanceList.isEmpty()))?this.getPricePlanServiceAllowanceList():null);
            List<Usage> rhsPricePlanServiceAllowanceList;
            rhsPricePlanServiceAllowanceList = (((that.pricePlanServiceAllowanceList!= null)&&(!that.pricePlanServiceAllowanceList.isEmpty()))?that.getPricePlanServiceAllowanceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanServiceAllowanceList", lhsPricePlanServiceAllowanceList), LocatorUtils.property(thatLocator, "pricePlanServiceAllowanceList", rhsPricePlanServiceAllowanceList), lhsPricePlanServiceAllowanceList, rhsPricePlanServiceAllowanceList)) {
                return false;
            }
        }
        {
            List<RewardCommitment> lhsRewardCommittmentList;
            lhsRewardCommittmentList = (((this.rewardCommittmentList!= null)&&(!this.rewardCommittmentList.isEmpty()))?this.getRewardCommittmentList():null);
            List<RewardCommitment> rhsRewardCommittmentList;
            rhsRewardCommittmentList = (((that.rewardCommittmentList!= null)&&(!that.rewardCommittmentList.isEmpty()))?that.getRewardCommittmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardCommittmentList", lhsRewardCommittmentList), LocatorUtils.property(thatLocator, "rewardCommittmentList", rhsRewardCommittmentList), lhsRewardCommittmentList, rhsRewardCommittmentList)) {
                return false;
            }
        }
        {
            AccountBase lhsAccountInfo;
            lhsAccountInfo = this.getAccountInfo();
            AccountBase rhsAccountInfo;
            rhsAccountInfo = that.getAccountInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountInfo", lhsAccountInfo), LocatorUtils.property(thatLocator, "accountInfo", rhsAccountInfo), lhsAccountInfo, rhsAccountInfo)) {
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
     *         &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="includedFeatureList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OrderServiceItem" maxOccurs="unbounded" minOccurs="0"/>
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
        "pricePlanCode",
        "includedFeatureList"
    })
    public static class PricePlanIncludedFeature
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String pricePlanCode;
        protected List<OrderServiceItem> includedFeatureList;

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
         * Gets the value of the includedFeatureList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the includedFeatureList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getIncludedFeatureList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link OrderServiceItem }
         * 
         * 
         */
        public List<OrderServiceItem> getIncludedFeatureList() {
            if (includedFeatureList == null) {
                includedFeatureList = new ArrayList<OrderServiceItem>();
            }
            return this.includedFeatureList;
        }

        /**
         * Sets the value of the includedFeatureList property.
         * 
         * @param includedFeatureList
         *     allowed object is
         *     {@link OrderServiceItem }
         *     
         */
        public void setIncludedFeatureList(List<OrderServiceItem> includedFeatureList) {
            this.includedFeatureList = includedFeatureList;
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
                String thePricePlanCode;
                thePricePlanCode = this.getPricePlanCode();
                strategy.appendField(locator, this, "pricePlanCode", buffer, thePricePlanCode);
            }
            {
                List<OrderServiceItem> theIncludedFeatureList;
                theIncludedFeatureList = (((this.includedFeatureList!= null)&&(!this.includedFeatureList.isEmpty()))?this.getIncludedFeatureList():null);
                strategy.appendField(locator, this, "includedFeatureList", buffer, theIncludedFeatureList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof OrderContract.PricePlanIncludedFeature)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final OrderContract.PricePlanIncludedFeature that = ((OrderContract.PricePlanIncludedFeature) object);
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
                List<OrderServiceItem> lhsIncludedFeatureList;
                lhsIncludedFeatureList = (((this.includedFeatureList!= null)&&(!this.includedFeatureList.isEmpty()))?this.getIncludedFeatureList():null);
                List<OrderServiceItem> rhsIncludedFeatureList;
                rhsIncludedFeatureList = (((that.includedFeatureList!= null)&&(!that.includedFeatureList.isEmpty()))?that.getIncludedFeatureList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "includedFeatureList", lhsIncludedFeatureList), LocatorUtils.property(thatLocator, "includedFeatureList", rhsIncludedFeatureList), lhsIncludedFeatureList, rhsIncludedFeatureList)) {
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
