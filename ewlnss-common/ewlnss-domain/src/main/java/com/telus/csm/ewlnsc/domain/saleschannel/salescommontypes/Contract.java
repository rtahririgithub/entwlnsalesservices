
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Contract complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Contract">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricePlan" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PricePlan" minOccurs="0"/>
 *         &lt;element name="commitmentStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="commitmentEndDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="commitmentTermInMonths" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="optionalServiceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceDetail" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="includedServiceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceDetail" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="otherServiceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceDetail" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="monthlyRecurringChargeAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="futureDatedPricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Contract", propOrder = {
    "pricePlan",
    "commitmentStartDate",
    "commitmentEndDate",
    "commitmentTermInMonths",
    "optionalServiceList",
    "includedServiceList",
    "otherServiceList",
    "monthlyRecurringChargeAmount",
    "futureDatedPricePlanCode"
})
public class Contract
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected PricePlan pricePlan;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date commitmentStartDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date commitmentEndDate;
    @XmlElement(required = true)
    protected BigInteger commitmentTermInMonths;
    protected List<ServiceDetail> optionalServiceList;
    protected List<ServiceDetail> includedServiceList;
    protected List<ServiceDetail> otherServiceList;
    @XmlElement(nillable = true)
    protected Double monthlyRecurringChargeAmount;
    protected String futureDatedPricePlanCode;

    /**
     * Gets the value of the pricePlan property.
     * 
     * @return
     *     possible object is
     *     {@link PricePlan }
     *     
     */
    public PricePlan getPricePlan() {
        return pricePlan;
    }

    /**
     * Sets the value of the pricePlan property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePlan }
     *     
     */
    public void setPricePlan(PricePlan value) {
        this.pricePlan = value;
    }

    /**
     * Gets the value of the commitmentStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCommitmentStartDate() {
        return commitmentStartDate;
    }

    /**
     * Sets the value of the commitmentStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitmentStartDate(Date value) {
        this.commitmentStartDate = value;
    }

    /**
     * Gets the value of the commitmentEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getCommitmentEndDate() {
        return commitmentEndDate;
    }

    /**
     * Sets the value of the commitmentEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommitmentEndDate(Date value) {
        this.commitmentEndDate = value;
    }

    /**
     * Gets the value of the commitmentTermInMonths property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getCommitmentTermInMonths() {
        return commitmentTermInMonths;
    }

    /**
     * Sets the value of the commitmentTermInMonths property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setCommitmentTermInMonths(BigInteger value) {
        this.commitmentTermInMonths = value;
    }

    /**
     * Gets the value of the optionalServiceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionalServiceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionalServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDetail }
     * 
     * 
     */
    public List<ServiceDetail> getOptionalServiceList() {
        if (optionalServiceList == null) {
            optionalServiceList = new ArrayList<ServiceDetail>();
        }
        return this.optionalServiceList;
    }

    /**
     * Gets the value of the includedServiceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedServiceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDetail }
     * 
     * 
     */
    public List<ServiceDetail> getIncludedServiceList() {
        if (includedServiceList == null) {
            includedServiceList = new ArrayList<ServiceDetail>();
        }
        return this.includedServiceList;
    }

    /**
     * Gets the value of the otherServiceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the otherServiceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOtherServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDetail }
     * 
     * 
     */
    public List<ServiceDetail> getOtherServiceList() {
        if (otherServiceList == null) {
            otherServiceList = new ArrayList<ServiceDetail>();
        }
        return this.otherServiceList;
    }

    /**
     * Gets the value of the monthlyRecurringChargeAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonthlyRecurringChargeAmount() {
        return monthlyRecurringChargeAmount;
    }

    /**
     * Sets the value of the monthlyRecurringChargeAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonthlyRecurringChargeAmount(Double value) {
        this.monthlyRecurringChargeAmount = value;
    }

    /**
     * Gets the value of the futureDatedPricePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFutureDatedPricePlanCode() {
        return futureDatedPricePlanCode;
    }

    /**
     * Sets the value of the futureDatedPricePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFutureDatedPricePlanCode(String value) {
        this.futureDatedPricePlanCode = value;
    }

    /**
     * Sets the value of the optionalServiceList property.
     * 
     * @param optionalServiceList
     *     allowed object is
     *     {@link ServiceDetail }
     *     
     */
    public void setOptionalServiceList(List<ServiceDetail> optionalServiceList) {
        this.optionalServiceList = optionalServiceList;
    }

    /**
     * Sets the value of the includedServiceList property.
     * 
     * @param includedServiceList
     *     allowed object is
     *     {@link ServiceDetail }
     *     
     */
    public void setIncludedServiceList(List<ServiceDetail> includedServiceList) {
        this.includedServiceList = includedServiceList;
    }

    /**
     * Sets the value of the otherServiceList property.
     * 
     * @param otherServiceList
     *     allowed object is
     *     {@link ServiceDetail }
     *     
     */
    public void setOtherServiceList(List<ServiceDetail> otherServiceList) {
        this.otherServiceList = otherServiceList;
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
            PricePlan thePricePlan;
            thePricePlan = this.getPricePlan();
            strategy.appendField(locator, this, "pricePlan", buffer, thePricePlan);
        }
        {
            Date theCommitmentStartDate;
            theCommitmentStartDate = this.getCommitmentStartDate();
            strategy.appendField(locator, this, "commitmentStartDate", buffer, theCommitmentStartDate);
        }
        {
            Date theCommitmentEndDate;
            theCommitmentEndDate = this.getCommitmentEndDate();
            strategy.appendField(locator, this, "commitmentEndDate", buffer, theCommitmentEndDate);
        }
        {
            BigInteger theCommitmentTermInMonths;
            theCommitmentTermInMonths = this.getCommitmentTermInMonths();
            strategy.appendField(locator, this, "commitmentTermInMonths", buffer, theCommitmentTermInMonths);
        }
        {
            List<ServiceDetail> theOptionalServiceList;
            theOptionalServiceList = (((this.optionalServiceList!= null)&&(!this.optionalServiceList.isEmpty()))?this.getOptionalServiceList():null);
            strategy.appendField(locator, this, "optionalServiceList", buffer, theOptionalServiceList);
        }
        {
            List<ServiceDetail> theIncludedServiceList;
            theIncludedServiceList = (((this.includedServiceList!= null)&&(!this.includedServiceList.isEmpty()))?this.getIncludedServiceList():null);
            strategy.appendField(locator, this, "includedServiceList", buffer, theIncludedServiceList);
        }
        {
            List<ServiceDetail> theOtherServiceList;
            theOtherServiceList = (((this.otherServiceList!= null)&&(!this.otherServiceList.isEmpty()))?this.getOtherServiceList():null);
            strategy.appendField(locator, this, "otherServiceList", buffer, theOtherServiceList);
        }
        {
            Double theMonthlyRecurringChargeAmount;
            theMonthlyRecurringChargeAmount = this.getMonthlyRecurringChargeAmount();
            strategy.appendField(locator, this, "monthlyRecurringChargeAmount", buffer, theMonthlyRecurringChargeAmount);
        }
        {
            String theFutureDatedPricePlanCode;
            theFutureDatedPricePlanCode = this.getFutureDatedPricePlanCode();
            strategy.appendField(locator, this, "futureDatedPricePlanCode", buffer, theFutureDatedPricePlanCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Contract)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Contract that = ((Contract) object);
        {
            PricePlan lhsPricePlan;
            lhsPricePlan = this.getPricePlan();
            PricePlan rhsPricePlan;
            rhsPricePlan = that.getPricePlan();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlan", lhsPricePlan), LocatorUtils.property(thatLocator, "pricePlan", rhsPricePlan), lhsPricePlan, rhsPricePlan)) {
                return false;
            }
        }
        {
            Date lhsCommitmentStartDate;
            lhsCommitmentStartDate = this.getCommitmentStartDate();
            Date rhsCommitmentStartDate;
            rhsCommitmentStartDate = that.getCommitmentStartDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "commitmentStartDate", lhsCommitmentStartDate), LocatorUtils.property(thatLocator, "commitmentStartDate", rhsCommitmentStartDate), lhsCommitmentStartDate, rhsCommitmentStartDate)) {
                return false;
            }
        }
        {
            Date lhsCommitmentEndDate;
            lhsCommitmentEndDate = this.getCommitmentEndDate();
            Date rhsCommitmentEndDate;
            rhsCommitmentEndDate = that.getCommitmentEndDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "commitmentEndDate", lhsCommitmentEndDate), LocatorUtils.property(thatLocator, "commitmentEndDate", rhsCommitmentEndDate), lhsCommitmentEndDate, rhsCommitmentEndDate)) {
                return false;
            }
        }
        {
            BigInteger lhsCommitmentTermInMonths;
            lhsCommitmentTermInMonths = this.getCommitmentTermInMonths();
            BigInteger rhsCommitmentTermInMonths;
            rhsCommitmentTermInMonths = that.getCommitmentTermInMonths();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "commitmentTermInMonths", lhsCommitmentTermInMonths), LocatorUtils.property(thatLocator, "commitmentTermInMonths", rhsCommitmentTermInMonths), lhsCommitmentTermInMonths, rhsCommitmentTermInMonths)) {
                return false;
            }
        }
        {
            List<ServiceDetail> lhsOptionalServiceList;
            lhsOptionalServiceList = (((this.optionalServiceList!= null)&&(!this.optionalServiceList.isEmpty()))?this.getOptionalServiceList():null);
            List<ServiceDetail> rhsOptionalServiceList;
            rhsOptionalServiceList = (((that.optionalServiceList!= null)&&(!that.optionalServiceList.isEmpty()))?that.getOptionalServiceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "optionalServiceList", lhsOptionalServiceList), LocatorUtils.property(thatLocator, "optionalServiceList", rhsOptionalServiceList), lhsOptionalServiceList, rhsOptionalServiceList)) {
                return false;
            }
        }
        {
            List<ServiceDetail> lhsIncludedServiceList;
            lhsIncludedServiceList = (((this.includedServiceList!= null)&&(!this.includedServiceList.isEmpty()))?this.getIncludedServiceList():null);
            List<ServiceDetail> rhsIncludedServiceList;
            rhsIncludedServiceList = (((that.includedServiceList!= null)&&(!that.includedServiceList.isEmpty()))?that.getIncludedServiceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedServiceList", lhsIncludedServiceList), LocatorUtils.property(thatLocator, "includedServiceList", rhsIncludedServiceList), lhsIncludedServiceList, rhsIncludedServiceList)) {
                return false;
            }
        }
        {
            List<ServiceDetail> lhsOtherServiceList;
            lhsOtherServiceList = (((this.otherServiceList!= null)&&(!this.otherServiceList.isEmpty()))?this.getOtherServiceList():null);
            List<ServiceDetail> rhsOtherServiceList;
            rhsOtherServiceList = (((that.otherServiceList!= null)&&(!that.otherServiceList.isEmpty()))?that.getOtherServiceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "otherServiceList", lhsOtherServiceList), LocatorUtils.property(thatLocator, "otherServiceList", rhsOtherServiceList), lhsOtherServiceList, rhsOtherServiceList)) {
                return false;
            }
        }
        {
            Double lhsMonthlyRecurringChargeAmount;
            lhsMonthlyRecurringChargeAmount = this.getMonthlyRecurringChargeAmount();
            Double rhsMonthlyRecurringChargeAmount;
            rhsMonthlyRecurringChargeAmount = that.getMonthlyRecurringChargeAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "monthlyRecurringChargeAmount", lhsMonthlyRecurringChargeAmount), LocatorUtils.property(thatLocator, "monthlyRecurringChargeAmount", rhsMonthlyRecurringChargeAmount), lhsMonthlyRecurringChargeAmount, rhsMonthlyRecurringChargeAmount)) {
                return false;
            }
        }
        {
            String lhsFutureDatedPricePlanCode;
            lhsFutureDatedPricePlanCode = this.getFutureDatedPricePlanCode();
            String rhsFutureDatedPricePlanCode;
            rhsFutureDatedPricePlanCode = that.getFutureDatedPricePlanCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "futureDatedPricePlanCode", lhsFutureDatedPricePlanCode), LocatorUtils.property(thatLocator, "futureDatedPricePlanCode", rhsFutureDatedPricePlanCode), lhsFutureDatedPricePlanCode, rhsFutureDatedPricePlanCode)) {
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
