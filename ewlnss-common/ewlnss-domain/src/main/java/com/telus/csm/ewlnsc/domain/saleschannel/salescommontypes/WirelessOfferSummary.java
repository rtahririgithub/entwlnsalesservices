
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.srv.mso.channelsalesmgmt.enterprisesalesinformationservicerequestresponse_v5.BundleOfferSummary;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.OfferPromotionDiscount;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.ProductOfferPrice;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.CatalogueOfferSummary;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessOfferSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}CatalogueOfferSummary">
 *       &lt;sequence>
 *         &lt;element name="assignedOfferIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="offerAssignment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessOfferAssignment" minOccurs="0"/>
 *         &lt;element name="eligibleOfferProductList" maxOccurs="100" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="productCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="productDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *                   &lt;element name="offerPromotionDiscount" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}OfferPromotionDiscount"/>
 *                   &lt;element name="productOfferPrice" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}ProductOfferPrice"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="rewardOption" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardOption" minOccurs="0"/>
 *         &lt;element name="recommendedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessOfferSummary", propOrder = {
    "assignedOfferIndicator",
    "offerAssignment",
    "eligibleOfferProductList",
    "rewardOption",
    "recommendedIndicator"
})
@XmlSeeAlso({
    BundleOfferSummary.class
})
public class WirelessOfferSummary
    extends CatalogueOfferSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Boolean assignedOfferIndicator;
    protected WirelessOfferAssignment offerAssignment;
    protected List<WirelessOfferSummary.EligibleOfferProductList> eligibleOfferProductList;
    protected RewardOption rewardOption;
    protected Boolean recommendedIndicator;

    /**
     * Gets the value of the assignedOfferIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssignedOfferIndicator() {
        return assignedOfferIndicator;
    }

    /**
     * Sets the value of the assignedOfferIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssignedOfferIndicator(Boolean value) {
        this.assignedOfferIndicator = value;
    }

    /**
     * Gets the value of the offerAssignment property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessOfferAssignment }
     *     
     */
    public WirelessOfferAssignment getOfferAssignment() {
        return offerAssignment;
    }

    /**
     * Sets the value of the offerAssignment property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessOfferAssignment }
     *     
     */
    public void setOfferAssignment(WirelessOfferAssignment value) {
        this.offerAssignment = value;
    }

    /**
     * Gets the value of the eligibleOfferProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibleOfferProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibleOfferProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelessOfferSummary.EligibleOfferProductList }
     * 
     * 
     */
    public List<WirelessOfferSummary.EligibleOfferProductList> getEligibleOfferProductList() {
        if (eligibleOfferProductList == null) {
            eligibleOfferProductList = new ArrayList<WirelessOfferSummary.EligibleOfferProductList>();
        }
        return this.eligibleOfferProductList;
    }

    /**
     * Gets the value of the rewardOption property.
     * 
     * @return
     *     possible object is
     *     {@link RewardOption }
     *     
     */
    public RewardOption getRewardOption() {
        return rewardOption;
    }

    /**
     * Sets the value of the rewardOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link RewardOption }
     *     
     */
    public void setRewardOption(RewardOption value) {
        this.rewardOption = value;
    }

    /**
     * Gets the value of the recommendedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecommendedIndicator() {
        return recommendedIndicator;
    }

    /**
     * Sets the value of the recommendedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecommendedIndicator(Boolean value) {
        this.recommendedIndicator = value;
    }

    /**
     * Sets the value of the eligibleOfferProductList property.
     * 
     * @param eligibleOfferProductList
     *     allowed object is
     *     {@link WirelessOfferSummary.EligibleOfferProductList }
     *     
     */
    public void setEligibleOfferProductList(List<WirelessOfferSummary.EligibleOfferProductList> eligibleOfferProductList) {
        this.eligibleOfferProductList = eligibleOfferProductList;
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
            Boolean theAssignedOfferIndicator;
            theAssignedOfferIndicator = this.isAssignedOfferIndicator();
            strategy.appendField(locator, this, "assignedOfferIndicator", buffer, theAssignedOfferIndicator);
        }
        {
            WirelessOfferAssignment theOfferAssignment;
            theOfferAssignment = this.getOfferAssignment();
            strategy.appendField(locator, this, "offerAssignment", buffer, theOfferAssignment);
        }
        {
            List<WirelessOfferSummary.EligibleOfferProductList> theEligibleOfferProductList;
            theEligibleOfferProductList = (((this.eligibleOfferProductList!= null)&&(!this.eligibleOfferProductList.isEmpty()))?this.getEligibleOfferProductList():null);
            strategy.appendField(locator, this, "eligibleOfferProductList", buffer, theEligibleOfferProductList);
        }
        {
            RewardOption theRewardOption;
            theRewardOption = this.getRewardOption();
            strategy.appendField(locator, this, "rewardOption", buffer, theRewardOption);
        }
        {
            Boolean theRecommendedIndicator;
            theRecommendedIndicator = this.isRecommendedIndicator();
            strategy.appendField(locator, this, "recommendedIndicator", buffer, theRecommendedIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelessOfferSummary that = ((WirelessOfferSummary) object);
        {
            Boolean lhsAssignedOfferIndicator;
            lhsAssignedOfferIndicator = this.isAssignedOfferIndicator();
            Boolean rhsAssignedOfferIndicator;
            rhsAssignedOfferIndicator = that.isAssignedOfferIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assignedOfferIndicator", lhsAssignedOfferIndicator), LocatorUtils.property(thatLocator, "assignedOfferIndicator", rhsAssignedOfferIndicator), lhsAssignedOfferIndicator, rhsAssignedOfferIndicator)) {
                return false;
            }
        }
        {
            WirelessOfferAssignment lhsOfferAssignment;
            lhsOfferAssignment = this.getOfferAssignment();
            WirelessOfferAssignment rhsOfferAssignment;
            rhsOfferAssignment = that.getOfferAssignment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerAssignment", lhsOfferAssignment), LocatorUtils.property(thatLocator, "offerAssignment", rhsOfferAssignment), lhsOfferAssignment, rhsOfferAssignment)) {
                return false;
            }
        }
        {
            List<WirelessOfferSummary.EligibleOfferProductList> lhsEligibleOfferProductList;
            lhsEligibleOfferProductList = (((this.eligibleOfferProductList!= null)&&(!this.eligibleOfferProductList.isEmpty()))?this.getEligibleOfferProductList():null);
            List<WirelessOfferSummary.EligibleOfferProductList> rhsEligibleOfferProductList;
            rhsEligibleOfferProductList = (((that.eligibleOfferProductList!= null)&&(!that.eligibleOfferProductList.isEmpty()))?that.getEligibleOfferProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleOfferProductList", lhsEligibleOfferProductList), LocatorUtils.property(thatLocator, "eligibleOfferProductList", rhsEligibleOfferProductList), lhsEligibleOfferProductList, rhsEligibleOfferProductList)) {
                return false;
            }
        }
        {
            RewardOption lhsRewardOption;
            lhsRewardOption = this.getRewardOption();
            RewardOption rhsRewardOption;
            rhsRewardOption = that.getRewardOption();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardOption", lhsRewardOption), LocatorUtils.property(thatLocator, "rewardOption", rhsRewardOption), lhsRewardOption, rhsRewardOption)) {
                return false;
            }
        }
        {
            Boolean lhsRecommendedIndicator;
            lhsRecommendedIndicator = this.isRecommendedIndicator();
            Boolean rhsRecommendedIndicator;
            rhsRecommendedIndicator = that.isRecommendedIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendedIndicator", lhsRecommendedIndicator), LocatorUtils.property(thatLocator, "recommendedIndicator", rhsRecommendedIndicator), lhsRecommendedIndicator, rhsRecommendedIndicator)) {
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
     *         &lt;element name="productCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="productDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
     *         &lt;element name="offerPromotionDiscount" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}OfferPromotionDiscount"/>
     *         &lt;element name="productOfferPrice" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}ProductOfferPrice"/>
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
        "productCd",
        "productDescriptionList",
        "offerPromotionDiscount",
        "productOfferPrice"
    })
    public static class EligibleOfferProductList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String productCd;
        protected List<Description> productDescriptionList;
        @XmlElement(required = true)
        protected OfferPromotionDiscount offerPromotionDiscount;
        @XmlElement(required = true)
        protected ProductOfferPrice productOfferPrice;

        /**
         * Gets the value of the productCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductCd() {
            return productCd;
        }

        /**
         * Sets the value of the productCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductCd(String value) {
            this.productCd = value;
        }

        /**
         * Gets the value of the productDescriptionList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the productDescriptionList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getProductDescriptionList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Description }
         * 
         * 
         */
        public List<Description> getProductDescriptionList() {
            if (productDescriptionList == null) {
                productDescriptionList = new ArrayList<Description>();
            }
            return this.productDescriptionList;
        }

        /**
         * Gets the value of the offerPromotionDiscount property.
         * 
         * @return
         *     possible object is
         *     {@link OfferPromotionDiscount }
         *     
         */
        public OfferPromotionDiscount getOfferPromotionDiscount() {
            return offerPromotionDiscount;
        }

        /**
         * Sets the value of the offerPromotionDiscount property.
         * 
         * @param value
         *     allowed object is
         *     {@link OfferPromotionDiscount }
         *     
         */
        public void setOfferPromotionDiscount(OfferPromotionDiscount value) {
            this.offerPromotionDiscount = value;
        }

        /**
         * Gets the value of the productOfferPrice property.
         * 
         * @return
         *     possible object is
         *     {@link ProductOfferPrice }
         *     
         */
        public ProductOfferPrice getProductOfferPrice() {
            return productOfferPrice;
        }

        /**
         * Sets the value of the productOfferPrice property.
         * 
         * @param value
         *     allowed object is
         *     {@link ProductOfferPrice }
         *     
         */
        public void setProductOfferPrice(ProductOfferPrice value) {
            this.productOfferPrice = value;
        }

        /**
         * Sets the value of the productDescriptionList property.
         * 
         * @param productDescriptionList
         *     allowed object is
         *     {@link Description }
         *     
         */
        public void setProductDescriptionList(List<Description> productDescriptionList) {
            this.productDescriptionList = productDescriptionList;
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
                String theProductCd;
                theProductCd = this.getProductCd();
                strategy.appendField(locator, this, "productCd", buffer, theProductCd);
            }
            {
                List<Description> theProductDescriptionList;
                theProductDescriptionList = (((this.productDescriptionList!= null)&&(!this.productDescriptionList.isEmpty()))?this.getProductDescriptionList():null);
                strategy.appendField(locator, this, "productDescriptionList", buffer, theProductDescriptionList);
            }
            {
                OfferPromotionDiscount theOfferPromotionDiscount;
                theOfferPromotionDiscount = this.getOfferPromotionDiscount();
                strategy.appendField(locator, this, "offerPromotionDiscount", buffer, theOfferPromotionDiscount);
            }
            {
                ProductOfferPrice theProductOfferPrice;
                theProductOfferPrice = this.getProductOfferPrice();
                strategy.appendField(locator, this, "productOfferPrice", buffer, theProductOfferPrice);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof WirelessOfferSummary.EligibleOfferProductList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final WirelessOfferSummary.EligibleOfferProductList that = ((WirelessOfferSummary.EligibleOfferProductList) object);
            {
                String lhsProductCd;
                lhsProductCd = this.getProductCd();
                String rhsProductCd;
                rhsProductCd = that.getProductCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productCd", lhsProductCd), LocatorUtils.property(thatLocator, "productCd", rhsProductCd), lhsProductCd, rhsProductCd)) {
                    return false;
                }
            }
            {
                List<Description> lhsProductDescriptionList;
                lhsProductDescriptionList = (((this.productDescriptionList!= null)&&(!this.productDescriptionList.isEmpty()))?this.getProductDescriptionList():null);
                List<Description> rhsProductDescriptionList;
                rhsProductDescriptionList = (((that.productDescriptionList!= null)&&(!that.productDescriptionList.isEmpty()))?that.getProductDescriptionList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productDescriptionList", lhsProductDescriptionList), LocatorUtils.property(thatLocator, "productDescriptionList", rhsProductDescriptionList), lhsProductDescriptionList, rhsProductDescriptionList)) {
                    return false;
                }
            }
            {
                OfferPromotionDiscount lhsOfferPromotionDiscount;
                lhsOfferPromotionDiscount = this.getOfferPromotionDiscount();
                OfferPromotionDiscount rhsOfferPromotionDiscount;
                rhsOfferPromotionDiscount = that.getOfferPromotionDiscount();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerPromotionDiscount", lhsOfferPromotionDiscount), LocatorUtils.property(thatLocator, "offerPromotionDiscount", rhsOfferPromotionDiscount), lhsOfferPromotionDiscount, rhsOfferPromotionDiscount)) {
                    return false;
                }
            }
            {
                ProductOfferPrice lhsProductOfferPrice;
                lhsProductOfferPrice = this.getProductOfferPrice();
                ProductOfferPrice rhsProductOfferPrice;
                rhsProductOfferPrice = that.getProductOfferPrice();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productOfferPrice", lhsProductOfferPrice), LocatorUtils.property(thatLocator, "productOfferPrice", rhsProductOfferPrice), lhsProductOfferPrice, rhsProductOfferPrice)) {
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
