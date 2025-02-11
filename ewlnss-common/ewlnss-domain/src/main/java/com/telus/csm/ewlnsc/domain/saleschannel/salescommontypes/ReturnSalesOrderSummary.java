
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
 * <p>Java class for ReturnSalesOrderSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReturnSalesOrderSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="hardwarePricingDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HardwarePricingDetailType" minOccurs="0"/>
 *         &lt;element name="penaltyPricingDetailList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PenaltyChargeDetail" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="rewardBillCredit" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardBillCredit" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReturnSalesOrderSummary", propOrder = {
    "hardwarePricingDetail",
    "penaltyPricingDetailList",
    "rewardBillCredit"
})
public class ReturnSalesOrderSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected HardwarePricingDetailType hardwarePricingDetail;
    protected List<PenaltyChargeDetail> penaltyPricingDetailList;
    protected RewardBillCredit rewardBillCredit;

    /**
     * Gets the value of the hardwarePricingDetail property.
     * 
     * @return
     *     possible object is
     *     {@link HardwarePricingDetailType }
     *     
     */
    public HardwarePricingDetailType getHardwarePricingDetail() {
        return hardwarePricingDetail;
    }

    /**
     * Sets the value of the hardwarePricingDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardwarePricingDetailType }
     *     
     */
    public void setHardwarePricingDetail(HardwarePricingDetailType value) {
        this.hardwarePricingDetail = value;
    }

    /**
     * Gets the value of the penaltyPricingDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the penaltyPricingDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPenaltyPricingDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PenaltyChargeDetail }
     * 
     * 
     */
    public List<PenaltyChargeDetail> getPenaltyPricingDetailList() {
        if (penaltyPricingDetailList == null) {
            penaltyPricingDetailList = new ArrayList<PenaltyChargeDetail>();
        }
        return this.penaltyPricingDetailList;
    }

    /**
     * Gets the value of the rewardBillCredit property.
     * 
     * @return
     *     possible object is
     *     {@link RewardBillCredit }
     *     
     */
    public RewardBillCredit getRewardBillCredit() {
        return rewardBillCredit;
    }

    /**
     * Sets the value of the rewardBillCredit property.
     * 
     * @param value
     *     allowed object is
     *     {@link RewardBillCredit }
     *     
     */
    public void setRewardBillCredit(RewardBillCredit value) {
        this.rewardBillCredit = value;
    }

    /**
     * Sets the value of the penaltyPricingDetailList property.
     * 
     * @param penaltyPricingDetailList
     *     allowed object is
     *     {@link PenaltyChargeDetail }
     *     
     */
    public void setPenaltyPricingDetailList(List<PenaltyChargeDetail> penaltyPricingDetailList) {
        this.penaltyPricingDetailList = penaltyPricingDetailList;
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
            HardwarePricingDetailType theHardwarePricingDetail;
            theHardwarePricingDetail = this.getHardwarePricingDetail();
            strategy.appendField(locator, this, "hardwarePricingDetail", buffer, theHardwarePricingDetail);
        }
        {
            List<PenaltyChargeDetail> thePenaltyPricingDetailList;
            thePenaltyPricingDetailList = (((this.penaltyPricingDetailList!= null)&&(!this.penaltyPricingDetailList.isEmpty()))?this.getPenaltyPricingDetailList():null);
            strategy.appendField(locator, this, "penaltyPricingDetailList", buffer, thePenaltyPricingDetailList);
        }
        {
            RewardBillCredit theRewardBillCredit;
            theRewardBillCredit = this.getRewardBillCredit();
            strategy.appendField(locator, this, "rewardBillCredit", buffer, theRewardBillCredit);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ReturnSalesOrderSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ReturnSalesOrderSummary that = ((ReturnSalesOrderSummary) object);
        {
            HardwarePricingDetailType lhsHardwarePricingDetail;
            lhsHardwarePricingDetail = this.getHardwarePricingDetail();
            HardwarePricingDetailType rhsHardwarePricingDetail;
            rhsHardwarePricingDetail = that.getHardwarePricingDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwarePricingDetail", lhsHardwarePricingDetail), LocatorUtils.property(thatLocator, "hardwarePricingDetail", rhsHardwarePricingDetail), lhsHardwarePricingDetail, rhsHardwarePricingDetail)) {
                return false;
            }
        }
        {
            List<PenaltyChargeDetail> lhsPenaltyPricingDetailList;
            lhsPenaltyPricingDetailList = (((this.penaltyPricingDetailList!= null)&&(!this.penaltyPricingDetailList.isEmpty()))?this.getPenaltyPricingDetailList():null);
            List<PenaltyChargeDetail> rhsPenaltyPricingDetailList;
            rhsPenaltyPricingDetailList = (((that.penaltyPricingDetailList!= null)&&(!that.penaltyPricingDetailList.isEmpty()))?that.getPenaltyPricingDetailList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "penaltyPricingDetailList", lhsPenaltyPricingDetailList), LocatorUtils.property(thatLocator, "penaltyPricingDetailList", rhsPenaltyPricingDetailList), lhsPenaltyPricingDetailList, rhsPenaltyPricingDetailList)) {
                return false;
            }
        }
        {
            RewardBillCredit lhsRewardBillCredit;
            lhsRewardBillCredit = this.getRewardBillCredit();
            RewardBillCredit rhsRewardBillCredit;
            rhsRewardBillCredit = that.getRewardBillCredit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardBillCredit", lhsRewardBillCredit), LocatorUtils.property(thatLocator, "rewardBillCredit", rhsRewardBillCredit), lhsRewardBillCredit, rhsRewardBillCredit)) {
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
