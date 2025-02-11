
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.CatalogueOffer;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessOfferDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}CatalogueOffer">
 *       &lt;sequence>
 *         &lt;element name="assignedOfferIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="offerAssignment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessOfferAssignment" minOccurs="0"/>
 *         &lt;element name="promotionalOfferItem" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PromotionalOfferItem" minOccurs="0"/>
 *         &lt;element name="rewardBillCreditList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardBillCredit" maxOccurs="25" minOccurs="0"/>
 *         &lt;element name="offerBillDiscountPlanList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BillDiscountPlan" maxOccurs="25" minOccurs="0"/>
 *         &lt;element name="salesItemDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesItemDescription" maxOccurs="300" minOccurs="0"/>
 *         &lt;element name="rewardOption" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardOption" minOccurs="0"/>
 *         &lt;element name="corporateAllocation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateAllocation" minOccurs="0"/>
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
@XmlType(name = "WirelessOfferDetail", propOrder = {
    "assignedOfferIndicator",
    "offerAssignment",
    "promotionalOfferItem",
    "rewardBillCreditList",
    "offerBillDiscountPlanList",
    "salesItemDescriptionList",
    "rewardOption",
    "corporateAllocation",
    "recommendedIndicator"
})
public class WirelessOfferDetail
    extends CatalogueOffer
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Boolean assignedOfferIndicator;
    protected WirelessOfferAssignment offerAssignment;
    protected PromotionalOfferItem promotionalOfferItem;
    protected List<RewardBillCredit> rewardBillCreditList;
    protected List<BillDiscountPlan> offerBillDiscountPlanList;
    protected List<SalesItemDescription> salesItemDescriptionList;
    protected RewardOption rewardOption;
    protected CorporateAllocation corporateAllocation;
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
     * Gets the value of the promotionalOfferItem property.
     * 
     * @return
     *     possible object is
     *     {@link PromotionalOfferItem }
     *     
     */
    public PromotionalOfferItem getPromotionalOfferItem() {
        return promotionalOfferItem;
    }

    /**
     * Sets the value of the promotionalOfferItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link PromotionalOfferItem }
     *     
     */
    public void setPromotionalOfferItem(PromotionalOfferItem value) {
        this.promotionalOfferItem = value;
    }

    /**
     * Gets the value of the rewardBillCreditList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardBillCreditList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardBillCreditList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RewardBillCredit }
     * 
     * 
     */
    public List<RewardBillCredit> getRewardBillCreditList() {
        if (rewardBillCreditList == null) {
            rewardBillCreditList = new ArrayList<RewardBillCredit>();
        }
        return this.rewardBillCreditList;
    }

    /**
     * Gets the value of the offerBillDiscountPlanList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerBillDiscountPlanList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferBillDiscountPlanList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillDiscountPlan }
     * 
     * 
     */
    public List<BillDiscountPlan> getOfferBillDiscountPlanList() {
        if (offerBillDiscountPlanList == null) {
            offerBillDiscountPlanList = new ArrayList<BillDiscountPlan>();
        }
        return this.offerBillDiscountPlanList;
    }

    /**
     * Gets the value of the salesItemDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesItemDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesItemDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesItemDescription }
     * 
     * 
     */
    public List<SalesItemDescription> getSalesItemDescriptionList() {
        if (salesItemDescriptionList == null) {
            salesItemDescriptionList = new ArrayList<SalesItemDescription>();
        }
        return this.salesItemDescriptionList;
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
     * Gets the value of the corporateAllocation property.
     * 
     * @return
     *     possible object is
     *     {@link CorporateAllocation }
     *     
     */
    public CorporateAllocation getCorporateAllocation() {
        return corporateAllocation;
    }

    /**
     * Sets the value of the corporateAllocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateAllocation }
     *     
     */
    public void setCorporateAllocation(CorporateAllocation value) {
        this.corporateAllocation = value;
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
     * Sets the value of the rewardBillCreditList property.
     * 
     * @param rewardBillCreditList
     *     allowed object is
     *     {@link RewardBillCredit }
     *     
     */
    public void setRewardBillCreditList(List<RewardBillCredit> rewardBillCreditList) {
        this.rewardBillCreditList = rewardBillCreditList;
    }

    /**
     * Sets the value of the offerBillDiscountPlanList property.
     * 
     * @param offerBillDiscountPlanList
     *     allowed object is
     *     {@link BillDiscountPlan }
     *     
     */
    public void setOfferBillDiscountPlanList(List<BillDiscountPlan> offerBillDiscountPlanList) {
        this.offerBillDiscountPlanList = offerBillDiscountPlanList;
    }

    /**
     * Sets the value of the salesItemDescriptionList property.
     * 
     * @param salesItemDescriptionList
     *     allowed object is
     *     {@link SalesItemDescription }
     *     
     */
    public void setSalesItemDescriptionList(List<SalesItemDescription> salesItemDescriptionList) {
        this.salesItemDescriptionList = salesItemDescriptionList;
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
            PromotionalOfferItem thePromotionalOfferItem;
            thePromotionalOfferItem = this.getPromotionalOfferItem();
            strategy.appendField(locator, this, "promotionalOfferItem", buffer, thePromotionalOfferItem);
        }
        {
            List<RewardBillCredit> theRewardBillCreditList;
            theRewardBillCreditList = (((this.rewardBillCreditList!= null)&&(!this.rewardBillCreditList.isEmpty()))?this.getRewardBillCreditList():null);
            strategy.appendField(locator, this, "rewardBillCreditList", buffer, theRewardBillCreditList);
        }
        {
            List<BillDiscountPlan> theOfferBillDiscountPlanList;
            theOfferBillDiscountPlanList = (((this.offerBillDiscountPlanList!= null)&&(!this.offerBillDiscountPlanList.isEmpty()))?this.getOfferBillDiscountPlanList():null);
            strategy.appendField(locator, this, "offerBillDiscountPlanList", buffer, theOfferBillDiscountPlanList);
        }
        {
            List<SalesItemDescription> theSalesItemDescriptionList;
            theSalesItemDescriptionList = (((this.salesItemDescriptionList!= null)&&(!this.salesItemDescriptionList.isEmpty()))?this.getSalesItemDescriptionList():null);
            strategy.appendField(locator, this, "salesItemDescriptionList", buffer, theSalesItemDescriptionList);
        }
        {
            RewardOption theRewardOption;
            theRewardOption = this.getRewardOption();
            strategy.appendField(locator, this, "rewardOption", buffer, theRewardOption);
        }
        {
            CorporateAllocation theCorporateAllocation;
            theCorporateAllocation = this.getCorporateAllocation();
            strategy.appendField(locator, this, "corporateAllocation", buffer, theCorporateAllocation);
        }
        {
            Boolean theRecommendedIndicator;
            theRecommendedIndicator = this.isRecommendedIndicator();
            strategy.appendField(locator, this, "recommendedIndicator", buffer, theRecommendedIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelessOfferDetail that = ((WirelessOfferDetail) object);
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
            PromotionalOfferItem lhsPromotionalOfferItem;
            lhsPromotionalOfferItem = this.getPromotionalOfferItem();
            PromotionalOfferItem rhsPromotionalOfferItem;
            rhsPromotionalOfferItem = that.getPromotionalOfferItem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionalOfferItem", lhsPromotionalOfferItem), LocatorUtils.property(thatLocator, "promotionalOfferItem", rhsPromotionalOfferItem), lhsPromotionalOfferItem, rhsPromotionalOfferItem)) {
                return false;
            }
        }
        {
            List<RewardBillCredit> lhsRewardBillCreditList;
            lhsRewardBillCreditList = (((this.rewardBillCreditList!= null)&&(!this.rewardBillCreditList.isEmpty()))?this.getRewardBillCreditList():null);
            List<RewardBillCredit> rhsRewardBillCreditList;
            rhsRewardBillCreditList = (((that.rewardBillCreditList!= null)&&(!that.rewardBillCreditList.isEmpty()))?that.getRewardBillCreditList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardBillCreditList", lhsRewardBillCreditList), LocatorUtils.property(thatLocator, "rewardBillCreditList", rhsRewardBillCreditList), lhsRewardBillCreditList, rhsRewardBillCreditList)) {
                return false;
            }
        }
        {
            List<BillDiscountPlan> lhsOfferBillDiscountPlanList;
            lhsOfferBillDiscountPlanList = (((this.offerBillDiscountPlanList!= null)&&(!this.offerBillDiscountPlanList.isEmpty()))?this.getOfferBillDiscountPlanList():null);
            List<BillDiscountPlan> rhsOfferBillDiscountPlanList;
            rhsOfferBillDiscountPlanList = (((that.offerBillDiscountPlanList!= null)&&(!that.offerBillDiscountPlanList.isEmpty()))?that.getOfferBillDiscountPlanList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerBillDiscountPlanList", lhsOfferBillDiscountPlanList), LocatorUtils.property(thatLocator, "offerBillDiscountPlanList", rhsOfferBillDiscountPlanList), lhsOfferBillDiscountPlanList, rhsOfferBillDiscountPlanList)) {
                return false;
            }
        }
        {
            List<SalesItemDescription> lhsSalesItemDescriptionList;
            lhsSalesItemDescriptionList = (((this.salesItemDescriptionList!= null)&&(!this.salesItemDescriptionList.isEmpty()))?this.getSalesItemDescriptionList():null);
            List<SalesItemDescription> rhsSalesItemDescriptionList;
            rhsSalesItemDescriptionList = (((that.salesItemDescriptionList!= null)&&(!that.salesItemDescriptionList.isEmpty()))?that.getSalesItemDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemDescriptionList", lhsSalesItemDescriptionList), LocatorUtils.property(thatLocator, "salesItemDescriptionList", rhsSalesItemDescriptionList), lhsSalesItemDescriptionList, rhsSalesItemDescriptionList)) {
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
            CorporateAllocation lhsCorporateAllocation;
            lhsCorporateAllocation = this.getCorporateAllocation();
            CorporateAllocation rhsCorporateAllocation;
            rhsCorporateAllocation = that.getCorporateAllocation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "corporateAllocation", lhsCorporateAllocation), LocatorUtils.property(thatLocator, "corporateAllocation", rhsCorporateAllocation), lhsCorporateAllocation, rhsCorporateAllocation)) {
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

}
