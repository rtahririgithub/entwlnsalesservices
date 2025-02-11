
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
 * Maps to the information returned from the RewardService.getRewardCommitment.
 * 
 * <p>Java class for RewardCommitment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RewardCommitment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="duration" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ContractDuration"/>
 *         &lt;element name="rewardList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Reward" maxOccurs="5" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RewardCommitment", propOrder = {
    "duration",
    "rewardList"
})
public class RewardCommitment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ContractDuration duration;
    protected List<Reward> rewardList;

    /**
     * Gets the value of the duration property.
     * 
     * @return
     *     possible object is
     *     {@link ContractDuration }
     *     
     */
    public ContractDuration getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractDuration }
     *     
     */
    public void setDuration(ContractDuration value) {
        this.duration = value;
    }

    /**
     * Gets the value of the rewardList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rewardList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRewardList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reward }
     * 
     * 
     */
    public List<Reward> getRewardList() {
        if (rewardList == null) {
            rewardList = new ArrayList<Reward>();
        }
        return this.rewardList;
    }

    /**
     * Sets the value of the rewardList property.
     * 
     * @param rewardList
     *     allowed object is
     *     {@link Reward }
     *     
     */
    public void setRewardList(List<Reward> rewardList) {
        this.rewardList = rewardList;
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
            ContractDuration theDuration;
            theDuration = this.getDuration();
            strategy.appendField(locator, this, "duration", buffer, theDuration);
        }
        {
            List<Reward> theRewardList;
            theRewardList = (((this.rewardList!= null)&&(!this.rewardList.isEmpty()))?this.getRewardList():null);
            strategy.appendField(locator, this, "rewardList", buffer, theRewardList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RewardCommitment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RewardCommitment that = ((RewardCommitment) object);
        {
            ContractDuration lhsDuration;
            lhsDuration = this.getDuration();
            ContractDuration rhsDuration;
            rhsDuration = that.getDuration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "duration", lhsDuration), LocatorUtils.property(thatLocator, "duration", rhsDuration), lhsDuration, rhsDuration)) {
                return false;
            }
        }
        {
            List<Reward> lhsRewardList;
            lhsRewardList = (((this.rewardList!= null)&&(!this.rewardList.isEmpty()))?this.getRewardList():null);
            List<Reward> rhsRewardList;
            rhsRewardList = (((that.rewardList!= null)&&(!that.rewardList.isEmpty()))?that.getRewardList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardList", lhsRewardList), LocatorUtils.property(thatLocator, "rewardList", rhsRewardList), lhsRewardList, rhsRewardList)) {
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
