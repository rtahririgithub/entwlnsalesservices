
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for DepositInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepositInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionItemList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="5"/>
 *         &lt;element name="depositAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="depositReasonCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="depositReasonDescriptionText" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList"/>
 *         &lt;element name="payDepositFromCommissionIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="payDepositByChequeIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="payOnBillIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepositInformation", propOrder = {
    "actionItemList",
    "depositAmount",
    "depositReasonCode",
    "depositReasonDescriptionText",
    "payDepositFromCommissionIndicator",
    "payDepositByChequeIndicator",
    "payOnBillIndicator"
})
public class DepositInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<String> actionItemList;
    protected double depositAmount;
    @XmlElement(required = true)
    protected String depositReasonCode;
    @XmlElement(required = true)
    protected MultilingualCodeDescTextList depositReasonDescriptionText;
    protected Boolean payDepositFromCommissionIndicator;
    protected Boolean payDepositByChequeIndicator;
    protected Boolean payOnBillIndicator;

    /**
     * Gets the value of the actionItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actionItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActionItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getActionItemList() {
        if (actionItemList == null) {
            actionItemList = new ArrayList<String>();
        }
        return this.actionItemList;
    }

    /**
     * Gets the value of the depositAmount property.
     * 
     */
    public double getDepositAmount() {
        return depositAmount;
    }

    /**
     * Sets the value of the depositAmount property.
     * 
     */
    public void setDepositAmount(double value) {
        this.depositAmount = value;
    }

    /**
     * Gets the value of the depositReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositReasonCode() {
        return depositReasonCode;
    }

    /**
     * Sets the value of the depositReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositReasonCode(String value) {
        this.depositReasonCode = value;
    }

    /**
     * Gets the value of the depositReasonDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getDepositReasonDescriptionText() {
        return depositReasonDescriptionText;
    }

    /**
     * Sets the value of the depositReasonDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setDepositReasonDescriptionText(MultilingualCodeDescTextList value) {
        this.depositReasonDescriptionText = value;
    }

    /**
     * Gets the value of the payDepositFromCommissionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPayDepositFromCommissionIndicator() {
        return payDepositFromCommissionIndicator;
    }

    /**
     * Sets the value of the payDepositFromCommissionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPayDepositFromCommissionIndicator(Boolean value) {
        this.payDepositFromCommissionIndicator = value;
    }

    /**
     * Gets the value of the payDepositByChequeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPayDepositByChequeIndicator() {
        return payDepositByChequeIndicator;
    }

    /**
     * Sets the value of the payDepositByChequeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPayDepositByChequeIndicator(Boolean value) {
        this.payDepositByChequeIndicator = value;
    }

    /**
     * Gets the value of the payOnBillIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPayOnBillIndicator() {
        return payOnBillIndicator;
    }

    /**
     * Sets the value of the payOnBillIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPayOnBillIndicator(Boolean value) {
        this.payOnBillIndicator = value;
    }

    /**
     * Sets the value of the actionItemList property.
     * 
     * @param actionItemList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionItemList(List<String> actionItemList) {
        this.actionItemList = actionItemList;
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
            List<String> theActionItemList;
            theActionItemList = (((this.actionItemList!= null)&&(!this.actionItemList.isEmpty()))?this.getActionItemList():null);
            strategy.appendField(locator, this, "actionItemList", buffer, theActionItemList);
        }
        {
            double theDepositAmount;
            theDepositAmount = (true?this.getDepositAmount(): 0.0D);
            strategy.appendField(locator, this, "depositAmount", buffer, theDepositAmount);
        }
        {
            String theDepositReasonCode;
            theDepositReasonCode = this.getDepositReasonCode();
            strategy.appendField(locator, this, "depositReasonCode", buffer, theDepositReasonCode);
        }
        {
            MultilingualCodeDescTextList theDepositReasonDescriptionText;
            theDepositReasonDescriptionText = this.getDepositReasonDescriptionText();
            strategy.appendField(locator, this, "depositReasonDescriptionText", buffer, theDepositReasonDescriptionText);
        }
        {
            Boolean thePayDepositFromCommissionIndicator;
            thePayDepositFromCommissionIndicator = this.isPayDepositFromCommissionIndicator();
            strategy.appendField(locator, this, "payDepositFromCommissionIndicator", buffer, thePayDepositFromCommissionIndicator);
        }
        {
            Boolean thePayDepositByChequeIndicator;
            thePayDepositByChequeIndicator = this.isPayDepositByChequeIndicator();
            strategy.appendField(locator, this, "payDepositByChequeIndicator", buffer, thePayDepositByChequeIndicator);
        }
        {
            Boolean thePayOnBillIndicator;
            thePayOnBillIndicator = this.isPayOnBillIndicator();
            strategy.appendField(locator, this, "payOnBillIndicator", buffer, thePayOnBillIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DepositInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DepositInformation that = ((DepositInformation) object);
        {
            List<String> lhsActionItemList;
            lhsActionItemList = (((this.actionItemList!= null)&&(!this.actionItemList.isEmpty()))?this.getActionItemList():null);
            List<String> rhsActionItemList;
            rhsActionItemList = (((that.actionItemList!= null)&&(!that.actionItemList.isEmpty()))?that.getActionItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "actionItemList", lhsActionItemList), LocatorUtils.property(thatLocator, "actionItemList", rhsActionItemList), lhsActionItemList, rhsActionItemList)) {
                return false;
            }
        }
        {
            double lhsDepositAmount;
            lhsDepositAmount = (true?this.getDepositAmount(): 0.0D);
            double rhsDepositAmount;
            rhsDepositAmount = (true?that.getDepositAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositAmount", lhsDepositAmount), LocatorUtils.property(thatLocator, "depositAmount", rhsDepositAmount), lhsDepositAmount, rhsDepositAmount)) {
                return false;
            }
        }
        {
            String lhsDepositReasonCode;
            lhsDepositReasonCode = this.getDepositReasonCode();
            String rhsDepositReasonCode;
            rhsDepositReasonCode = that.getDepositReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositReasonCode", lhsDepositReasonCode), LocatorUtils.property(thatLocator, "depositReasonCode", rhsDepositReasonCode), lhsDepositReasonCode, rhsDepositReasonCode)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsDepositReasonDescriptionText;
            lhsDepositReasonDescriptionText = this.getDepositReasonDescriptionText();
            MultilingualCodeDescTextList rhsDepositReasonDescriptionText;
            rhsDepositReasonDescriptionText = that.getDepositReasonDescriptionText();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositReasonDescriptionText", lhsDepositReasonDescriptionText), LocatorUtils.property(thatLocator, "depositReasonDescriptionText", rhsDepositReasonDescriptionText), lhsDepositReasonDescriptionText, rhsDepositReasonDescriptionText)) {
                return false;
            }
        }
        {
            Boolean lhsPayDepositFromCommissionIndicator;
            lhsPayDepositFromCommissionIndicator = this.isPayDepositFromCommissionIndicator();
            Boolean rhsPayDepositFromCommissionIndicator;
            rhsPayDepositFromCommissionIndicator = that.isPayDepositFromCommissionIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payDepositFromCommissionIndicator", lhsPayDepositFromCommissionIndicator), LocatorUtils.property(thatLocator, "payDepositFromCommissionIndicator", rhsPayDepositFromCommissionIndicator), lhsPayDepositFromCommissionIndicator, rhsPayDepositFromCommissionIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsPayDepositByChequeIndicator;
            lhsPayDepositByChequeIndicator = this.isPayDepositByChequeIndicator();
            Boolean rhsPayDepositByChequeIndicator;
            rhsPayDepositByChequeIndicator = that.isPayDepositByChequeIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payDepositByChequeIndicator", lhsPayDepositByChequeIndicator), LocatorUtils.property(thatLocator, "payDepositByChequeIndicator", rhsPayDepositByChequeIndicator), lhsPayDepositByChequeIndicator, rhsPayDepositByChequeIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsPayOnBillIndicator;
            lhsPayOnBillIndicator = this.isPayOnBillIndicator();
            Boolean rhsPayOnBillIndicator;
            rhsPayOnBillIndicator = that.isPayOnBillIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payOnBillIndicator", lhsPayOnBillIndicator), LocatorUtils.property(thatLocator, "payOnBillIndicator", rhsPayOnBillIndicator), lhsPayOnBillIndicator, rhsPayOnBillIndicator)) {
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
