
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for RewardOption complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RewardOption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rewardTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rewardTypeDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="minRewardBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="maxRewardBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="monthlyDrawdownAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RewardOption", propOrder = {
    "rewardTypeCode",
    "rewardTypeDescriptionList",
    "minRewardBalanceAmount",
    "maxRewardBalanceAmount",
    "monthlyDrawdownAmount"
})
public class RewardOption
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String rewardTypeCode;
    protected List<Description> rewardTypeDescriptionList;
    protected double minRewardBalanceAmount;
    protected double maxRewardBalanceAmount;
    protected Double monthlyDrawdownAmount;

    /**
     * Gets the value of the rewardTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRewardTypeCode() {
        return rewardTypeCode;
    }

    /**
     * Sets the value of the rewardTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRewardTypeCode(String value) {
        this.rewardTypeCode = value;
    }

    /**
     * Gets the value of the rewardTypeDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardTypeDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardTypeDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getRewardTypeDescriptionList() {
        if (rewardTypeDescriptionList == null) {
            rewardTypeDescriptionList = new ArrayList<Description>();
        }
        return this.rewardTypeDescriptionList;
    }

    /**
     * Gets the value of the minRewardBalanceAmount property.
     * 
     */
    public double getMinRewardBalanceAmount() {
        return minRewardBalanceAmount;
    }

    /**
     * Sets the value of the minRewardBalanceAmount property.
     * 
     */
    public void setMinRewardBalanceAmount(double value) {
        this.minRewardBalanceAmount = value;
    }

    /**
     * Gets the value of the maxRewardBalanceAmount property.
     * 
     */
    public double getMaxRewardBalanceAmount() {
        return maxRewardBalanceAmount;
    }

    /**
     * Sets the value of the maxRewardBalanceAmount property.
     * 
     */
    public void setMaxRewardBalanceAmount(double value) {
        this.maxRewardBalanceAmount = value;
    }

    /**
     * Gets the value of the monthlyDrawdownAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonthlyDrawdownAmount() {
        return monthlyDrawdownAmount;
    }

    /**
     * Sets the value of the monthlyDrawdownAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonthlyDrawdownAmount(Double value) {
        this.monthlyDrawdownAmount = value;
    }

    /**
     * Sets the value of the rewardTypeDescriptionList property.
     * 
     * @param rewardTypeDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setRewardTypeDescriptionList(List<Description> rewardTypeDescriptionList) {
        this.rewardTypeDescriptionList = rewardTypeDescriptionList;
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
            String theRewardTypeCode;
            theRewardTypeCode = this.getRewardTypeCode();
            strategy.appendField(locator, this, "rewardTypeCode", buffer, theRewardTypeCode);
        }
        {
            List<Description> theRewardTypeDescriptionList;
            theRewardTypeDescriptionList = (((this.rewardTypeDescriptionList!= null)&&(!this.rewardTypeDescriptionList.isEmpty()))?this.getRewardTypeDescriptionList():null);
            strategy.appendField(locator, this, "rewardTypeDescriptionList", buffer, theRewardTypeDescriptionList);
        }
        {
            double theMinRewardBalanceAmount;
            theMinRewardBalanceAmount = (true?this.getMinRewardBalanceAmount(): 0.0D);
            strategy.appendField(locator, this, "minRewardBalanceAmount", buffer, theMinRewardBalanceAmount);
        }
        {
            double theMaxRewardBalanceAmount;
            theMaxRewardBalanceAmount = (true?this.getMaxRewardBalanceAmount(): 0.0D);
            strategy.appendField(locator, this, "maxRewardBalanceAmount", buffer, theMaxRewardBalanceAmount);
        }
        {
            Double theMonthlyDrawdownAmount;
            theMonthlyDrawdownAmount = this.getMonthlyDrawdownAmount();
            strategy.appendField(locator, this, "monthlyDrawdownAmount", buffer, theMonthlyDrawdownAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RewardOption)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RewardOption that = ((RewardOption) object);
        {
            String lhsRewardTypeCode;
            lhsRewardTypeCode = this.getRewardTypeCode();
            String rhsRewardTypeCode;
            rhsRewardTypeCode = that.getRewardTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardTypeCode", lhsRewardTypeCode), LocatorUtils.property(thatLocator, "rewardTypeCode", rhsRewardTypeCode), lhsRewardTypeCode, rhsRewardTypeCode)) {
                return false;
            }
        }
        {
            List<Description> lhsRewardTypeDescriptionList;
            lhsRewardTypeDescriptionList = (((this.rewardTypeDescriptionList!= null)&&(!this.rewardTypeDescriptionList.isEmpty()))?this.getRewardTypeDescriptionList():null);
            List<Description> rhsRewardTypeDescriptionList;
            rhsRewardTypeDescriptionList = (((that.rewardTypeDescriptionList!= null)&&(!that.rewardTypeDescriptionList.isEmpty()))?that.getRewardTypeDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardTypeDescriptionList", lhsRewardTypeDescriptionList), LocatorUtils.property(thatLocator, "rewardTypeDescriptionList", rhsRewardTypeDescriptionList), lhsRewardTypeDescriptionList, rhsRewardTypeDescriptionList)) {
                return false;
            }
        }
        {
            double lhsMinRewardBalanceAmount;
            lhsMinRewardBalanceAmount = (true?this.getMinRewardBalanceAmount(): 0.0D);
            double rhsMinRewardBalanceAmount;
            rhsMinRewardBalanceAmount = (true?that.getMinRewardBalanceAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minRewardBalanceAmount", lhsMinRewardBalanceAmount), LocatorUtils.property(thatLocator, "minRewardBalanceAmount", rhsMinRewardBalanceAmount), lhsMinRewardBalanceAmount, rhsMinRewardBalanceAmount)) {
                return false;
            }
        }
        {
            double lhsMaxRewardBalanceAmount;
            lhsMaxRewardBalanceAmount = (true?this.getMaxRewardBalanceAmount(): 0.0D);
            double rhsMaxRewardBalanceAmount;
            rhsMaxRewardBalanceAmount = (true?that.getMaxRewardBalanceAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxRewardBalanceAmount", lhsMaxRewardBalanceAmount), LocatorUtils.property(thatLocator, "maxRewardBalanceAmount", rhsMaxRewardBalanceAmount), lhsMaxRewardBalanceAmount, rhsMaxRewardBalanceAmount)) {
                return false;
            }
        }
        {
            Double lhsMonthlyDrawdownAmount;
            lhsMonthlyDrawdownAmount = this.getMonthlyDrawdownAmount();
            Double rhsMonthlyDrawdownAmount;
            rhsMonthlyDrawdownAmount = that.getMonthlyDrawdownAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "monthlyDrawdownAmount", lhsMonthlyDrawdownAmount), LocatorUtils.property(thatLocator, "monthlyDrawdownAmount", rhsMonthlyDrawdownAmount), lhsMonthlyDrawdownAmount, rhsMonthlyDrawdownAmount)) {
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
