
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for BenefitBreakdownsummaryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BenefitBreakdownsummaryType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="totalDiscountAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="totalEligibleSubscriberCnt" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="totalContributingSubscriberCnt" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="perSubcriberDiscountAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="impactedSubscriberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberBenefitSummaryType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BenefitBreakdownsummaryType", propOrder = {
    "totalDiscountAmt",
    "totalEligibleSubscriberCnt",
    "totalContributingSubscriberCnt",
    "perSubcriberDiscountAmt",
    "impactedSubscriberList"
})
public class BenefitBreakdownsummaryType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double totalDiscountAmt;
    @XmlElement(required = true)
    protected BigInteger totalEligibleSubscriberCnt;
    @XmlElement(required = true)
    protected BigInteger totalContributingSubscriberCnt;
    protected double perSubcriberDiscountAmt;
    protected List<SubscriberBenefitSummaryType> impactedSubscriberList;

    /**
     * Gets the value of the totalDiscountAmt property.
     * 
     */
    public double getTotalDiscountAmt() {
        return totalDiscountAmt;
    }

    /**
     * Sets the value of the totalDiscountAmt property.
     * 
     */
    public void setTotalDiscountAmt(double value) {
        this.totalDiscountAmt = value;
    }

    /**
     * Gets the value of the totalEligibleSubscriberCnt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalEligibleSubscriberCnt() {
        return totalEligibleSubscriberCnt;
    }

    /**
     * Sets the value of the totalEligibleSubscriberCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalEligibleSubscriberCnt(BigInteger value) {
        this.totalEligibleSubscriberCnt = value;
    }

    /**
     * Gets the value of the totalContributingSubscriberCnt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getTotalContributingSubscriberCnt() {
        return totalContributingSubscriberCnt;
    }

    /**
     * Sets the value of the totalContributingSubscriberCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setTotalContributingSubscriberCnt(BigInteger value) {
        this.totalContributingSubscriberCnt = value;
    }

    /**
     * Gets the value of the perSubcriberDiscountAmt property.
     * 
     */
    public double getPerSubcriberDiscountAmt() {
        return perSubcriberDiscountAmt;
    }

    /**
     * Sets the value of the perSubcriberDiscountAmt property.
     * 
     */
    public void setPerSubcriberDiscountAmt(double value) {
        this.perSubcriberDiscountAmt = value;
    }

    /**
     * Gets the value of the impactedSubscriberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the impactedSubscriberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImpactedSubscriberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SubscriberBenefitSummaryType }
     * 
     * 
     */
    public List<SubscriberBenefitSummaryType> getImpactedSubscriberList() {
        if (impactedSubscriberList == null) {
            impactedSubscriberList = new ArrayList<SubscriberBenefitSummaryType>();
        }
        return this.impactedSubscriberList;
    }

    /**
     * Sets the value of the impactedSubscriberList property.
     * 
     * @param impactedSubscriberList
     *     allowed object is
     *     {@link SubscriberBenefitSummaryType }
     *     
     */
    public void setImpactedSubscriberList(List<SubscriberBenefitSummaryType> impactedSubscriberList) {
        this.impactedSubscriberList = impactedSubscriberList;
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
            double theTotalDiscountAmt;
            theTotalDiscountAmt = (true?this.getTotalDiscountAmt(): 0.0D);
            strategy.appendField(locator, this, "totalDiscountAmt", buffer, theTotalDiscountAmt);
        }
        {
            BigInteger theTotalEligibleSubscriberCnt;
            theTotalEligibleSubscriberCnt = this.getTotalEligibleSubscriberCnt();
            strategy.appendField(locator, this, "totalEligibleSubscriberCnt", buffer, theTotalEligibleSubscriberCnt);
        }
        {
            BigInteger theTotalContributingSubscriberCnt;
            theTotalContributingSubscriberCnt = this.getTotalContributingSubscriberCnt();
            strategy.appendField(locator, this, "totalContributingSubscriberCnt", buffer, theTotalContributingSubscriberCnt);
        }
        {
            double thePerSubcriberDiscountAmt;
            thePerSubcriberDiscountAmt = (true?this.getPerSubcriberDiscountAmt(): 0.0D);
            strategy.appendField(locator, this, "perSubcriberDiscountAmt", buffer, thePerSubcriberDiscountAmt);
        }
        {
            List<SubscriberBenefitSummaryType> theImpactedSubscriberList;
            theImpactedSubscriberList = (((this.impactedSubscriberList!= null)&&(!this.impactedSubscriberList.isEmpty()))?this.getImpactedSubscriberList():null);
            strategy.appendField(locator, this, "impactedSubscriberList", buffer, theImpactedSubscriberList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BenefitBreakdownsummaryType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BenefitBreakdownsummaryType that = ((BenefitBreakdownsummaryType) object);
        {
            double lhsTotalDiscountAmt;
            lhsTotalDiscountAmt = (true?this.getTotalDiscountAmt(): 0.0D);
            double rhsTotalDiscountAmt;
            rhsTotalDiscountAmt = (true?that.getTotalDiscountAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalDiscountAmt", lhsTotalDiscountAmt), LocatorUtils.property(thatLocator, "totalDiscountAmt", rhsTotalDiscountAmt), lhsTotalDiscountAmt, rhsTotalDiscountAmt)) {
                return false;
            }
        }
        {
            BigInteger lhsTotalEligibleSubscriberCnt;
            lhsTotalEligibleSubscriberCnt = this.getTotalEligibleSubscriberCnt();
            BigInteger rhsTotalEligibleSubscriberCnt;
            rhsTotalEligibleSubscriberCnt = that.getTotalEligibleSubscriberCnt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalEligibleSubscriberCnt", lhsTotalEligibleSubscriberCnt), LocatorUtils.property(thatLocator, "totalEligibleSubscriberCnt", rhsTotalEligibleSubscriberCnt), lhsTotalEligibleSubscriberCnt, rhsTotalEligibleSubscriberCnt)) {
                return false;
            }
        }
        {
            BigInteger lhsTotalContributingSubscriberCnt;
            lhsTotalContributingSubscriberCnt = this.getTotalContributingSubscriberCnt();
            BigInteger rhsTotalContributingSubscriberCnt;
            rhsTotalContributingSubscriberCnt = that.getTotalContributingSubscriberCnt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalContributingSubscriberCnt", lhsTotalContributingSubscriberCnt), LocatorUtils.property(thatLocator, "totalContributingSubscriberCnt", rhsTotalContributingSubscriberCnt), lhsTotalContributingSubscriberCnt, rhsTotalContributingSubscriberCnt)) {
                return false;
            }
        }
        {
            double lhsPerSubcriberDiscountAmt;
            lhsPerSubcriberDiscountAmt = (true?this.getPerSubcriberDiscountAmt(): 0.0D);
            double rhsPerSubcriberDiscountAmt;
            rhsPerSubcriberDiscountAmt = (true?that.getPerSubcriberDiscountAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perSubcriberDiscountAmt", lhsPerSubcriberDiscountAmt), LocatorUtils.property(thatLocator, "perSubcriberDiscountAmt", rhsPerSubcriberDiscountAmt), lhsPerSubcriberDiscountAmt, rhsPerSubcriberDiscountAmt)) {
                return false;
            }
        }
        {
            List<SubscriberBenefitSummaryType> lhsImpactedSubscriberList;
            lhsImpactedSubscriberList = (((this.impactedSubscriberList!= null)&&(!this.impactedSubscriberList.isEmpty()))?this.getImpactedSubscriberList():null);
            List<SubscriberBenefitSummaryType> rhsImpactedSubscriberList;
            rhsImpactedSubscriberList = (((that.impactedSubscriberList!= null)&&(!that.impactedSubscriberList.isEmpty()))?that.getImpactedSubscriberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "impactedSubscriberList", lhsImpactedSubscriberList), LocatorUtils.property(thatLocator, "impactedSubscriberList", rhsImpactedSubscriberList), lhsImpactedSubscriberList, rhsImpactedSubscriberList)) {
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
