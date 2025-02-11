
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
 * <p>Java class for AccountDataSharingSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountDataSharingSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSharingGroupList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="dataSharingGroupCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="dataSharingContributionTotalAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="totalIncludedDataAllowance" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Usage" minOccurs="0"/>
 *                   &lt;element name="contributorList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberDataSharingSummary" maxOccurs="unbounded" minOccurs="0"/>
 *                   &lt;element name="accessorList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberDataSharingSummary" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="dataSharingServiceDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesItemDescription" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountDataSharingSummary", propOrder = {
    "dataSharingGroupList",
    "dataSharingServiceDescriptionList"
})
public class AccountDataSharingSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<AccountDataSharingSummary.DataSharingGroupList> dataSharingGroupList;
    protected List<SalesItemDescription> dataSharingServiceDescriptionList;

    /**
     * Gets the value of the dataSharingGroupList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSharingGroupList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSharingGroupList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountDataSharingSummary.DataSharingGroupList }
     * 
     * 
     */
    public List<AccountDataSharingSummary.DataSharingGroupList> getDataSharingGroupList() {
        if (dataSharingGroupList == null) {
            dataSharingGroupList = new ArrayList<AccountDataSharingSummary.DataSharingGroupList>();
        }
        return this.dataSharingGroupList;
    }

    /**
     * Gets the value of the dataSharingServiceDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSharingServiceDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSharingServiceDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesItemDescription }
     * 
     * 
     */
    public List<SalesItemDescription> getDataSharingServiceDescriptionList() {
        if (dataSharingServiceDescriptionList == null) {
            dataSharingServiceDescriptionList = new ArrayList<SalesItemDescription>();
        }
        return this.dataSharingServiceDescriptionList;
    }

    /**
     * Sets the value of the dataSharingGroupList property.
     * 
     * @param dataSharingGroupList
     *     allowed object is
     *     {@link AccountDataSharingSummary.DataSharingGroupList }
     *     
     */
    public void setDataSharingGroupList(List<AccountDataSharingSummary.DataSharingGroupList> dataSharingGroupList) {
        this.dataSharingGroupList = dataSharingGroupList;
    }

    /**
     * Sets the value of the dataSharingServiceDescriptionList property.
     * 
     * @param dataSharingServiceDescriptionList
     *     allowed object is
     *     {@link SalesItemDescription }
     *     
     */
    public void setDataSharingServiceDescriptionList(List<SalesItemDescription> dataSharingServiceDescriptionList) {
        this.dataSharingServiceDescriptionList = dataSharingServiceDescriptionList;
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
            List<AccountDataSharingSummary.DataSharingGroupList> theDataSharingGroupList;
            theDataSharingGroupList = (((this.dataSharingGroupList!= null)&&(!this.dataSharingGroupList.isEmpty()))?this.getDataSharingGroupList():null);
            strategy.appendField(locator, this, "dataSharingGroupList", buffer, theDataSharingGroupList);
        }
        {
            List<SalesItemDescription> theDataSharingServiceDescriptionList;
            theDataSharingServiceDescriptionList = (((this.dataSharingServiceDescriptionList!= null)&&(!this.dataSharingServiceDescriptionList.isEmpty()))?this.getDataSharingServiceDescriptionList():null);
            strategy.appendField(locator, this, "dataSharingServiceDescriptionList", buffer, theDataSharingServiceDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountDataSharingSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountDataSharingSummary that = ((AccountDataSharingSummary) object);
        {
            List<AccountDataSharingSummary.DataSharingGroupList> lhsDataSharingGroupList;
            lhsDataSharingGroupList = (((this.dataSharingGroupList!= null)&&(!this.dataSharingGroupList.isEmpty()))?this.getDataSharingGroupList():null);
            List<AccountDataSharingSummary.DataSharingGroupList> rhsDataSharingGroupList;
            rhsDataSharingGroupList = (((that.dataSharingGroupList!= null)&&(!that.dataSharingGroupList.isEmpty()))?that.getDataSharingGroupList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingGroupList", lhsDataSharingGroupList), LocatorUtils.property(thatLocator, "dataSharingGroupList", rhsDataSharingGroupList), lhsDataSharingGroupList, rhsDataSharingGroupList)) {
                return false;
            }
        }
        {
            List<SalesItemDescription> lhsDataSharingServiceDescriptionList;
            lhsDataSharingServiceDescriptionList = (((this.dataSharingServiceDescriptionList!= null)&&(!this.dataSharingServiceDescriptionList.isEmpty()))?this.getDataSharingServiceDescriptionList():null);
            List<SalesItemDescription> rhsDataSharingServiceDescriptionList;
            rhsDataSharingServiceDescriptionList = (((that.dataSharingServiceDescriptionList!= null)&&(!that.dataSharingServiceDescriptionList.isEmpty()))?that.getDataSharingServiceDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingServiceDescriptionList", lhsDataSharingServiceDescriptionList), LocatorUtils.property(thatLocator, "dataSharingServiceDescriptionList", rhsDataSharingServiceDescriptionList), lhsDataSharingServiceDescriptionList, rhsDataSharingServiceDescriptionList)) {
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
     *         &lt;element name="dataSharingGroupCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="dataSharingContributionTotalAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="totalIncludedDataAllowance" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Usage" minOccurs="0"/>
     *         &lt;element name="contributorList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberDataSharingSummary" maxOccurs="unbounded" minOccurs="0"/>
     *         &lt;element name="accessorList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberDataSharingSummary" maxOccurs="unbounded" minOccurs="0"/>
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
        "dataSharingGroupCode",
        "dataSharingContributionTotalAmt",
        "totalIncludedDataAllowance",
        "contributorList",
        "accessorList"
    })
    public static class DataSharingGroupList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String dataSharingGroupCode;
        protected double dataSharingContributionTotalAmt;
        protected Usage totalIncludedDataAllowance;
        protected List<SubscriberDataSharingSummary> contributorList;
        protected List<SubscriberDataSharingSummary> accessorList;

        /**
         * Gets the value of the dataSharingGroupCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDataSharingGroupCode() {
            return dataSharingGroupCode;
        }

        /**
         * Sets the value of the dataSharingGroupCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDataSharingGroupCode(String value) {
            this.dataSharingGroupCode = value;
        }

        /**
         * Gets the value of the dataSharingContributionTotalAmt property.
         * 
         */
        public double getDataSharingContributionTotalAmt() {
            return dataSharingContributionTotalAmt;
        }

        /**
         * Sets the value of the dataSharingContributionTotalAmt property.
         * 
         */
        public void setDataSharingContributionTotalAmt(double value) {
            this.dataSharingContributionTotalAmt = value;
        }

        /**
         * Gets the value of the totalIncludedDataAllowance property.
         * 
         * @return
         *     possible object is
         *     {@link Usage }
         *     
         */
        public Usage getTotalIncludedDataAllowance() {
            return totalIncludedDataAllowance;
        }

        /**
         * Sets the value of the totalIncludedDataAllowance property.
         * 
         * @param value
         *     allowed object is
         *     {@link Usage }
         *     
         */
        public void setTotalIncludedDataAllowance(Usage value) {
            this.totalIncludedDataAllowance = value;
        }

        /**
         * Gets the value of the contributorList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the contributorList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getContributorList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SubscriberDataSharingSummary }
         * 
         * 
         */
        public List<SubscriberDataSharingSummary> getContributorList() {
            if (contributorList == null) {
                contributorList = new ArrayList<SubscriberDataSharingSummary>();
            }
            return this.contributorList;
        }

        /**
         * Gets the value of the accessorList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the accessorList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getAccessorList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link SubscriberDataSharingSummary }
         * 
         * 
         */
        public List<SubscriberDataSharingSummary> getAccessorList() {
            if (accessorList == null) {
                accessorList = new ArrayList<SubscriberDataSharingSummary>();
            }
            return this.accessorList;
        }

        /**
         * Sets the value of the contributorList property.
         * 
         * @param contributorList
         *     allowed object is
         *     {@link SubscriberDataSharingSummary }
         *     
         */
        public void setContributorList(List<SubscriberDataSharingSummary> contributorList) {
            this.contributorList = contributorList;
        }

        /**
         * Sets the value of the accessorList property.
         * 
         * @param accessorList
         *     allowed object is
         *     {@link SubscriberDataSharingSummary }
         *     
         */
        public void setAccessorList(List<SubscriberDataSharingSummary> accessorList) {
            this.accessorList = accessorList;
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
                String theDataSharingGroupCode;
                theDataSharingGroupCode = this.getDataSharingGroupCode();
                strategy.appendField(locator, this, "dataSharingGroupCode", buffer, theDataSharingGroupCode);
            }
            {
                double theDataSharingContributionTotalAmt;
                theDataSharingContributionTotalAmt = (true?this.getDataSharingContributionTotalAmt(): 0.0D);
                strategy.appendField(locator, this, "dataSharingContributionTotalAmt", buffer, theDataSharingContributionTotalAmt);
            }
            {
                Usage theTotalIncludedDataAllowance;
                theTotalIncludedDataAllowance = this.getTotalIncludedDataAllowance();
                strategy.appendField(locator, this, "totalIncludedDataAllowance", buffer, theTotalIncludedDataAllowance);
            }
            {
                List<SubscriberDataSharingSummary> theContributorList;
                theContributorList = (((this.contributorList!= null)&&(!this.contributorList.isEmpty()))?this.getContributorList():null);
                strategy.appendField(locator, this, "contributorList", buffer, theContributorList);
            }
            {
                List<SubscriberDataSharingSummary> theAccessorList;
                theAccessorList = (((this.accessorList!= null)&&(!this.accessorList.isEmpty()))?this.getAccessorList():null);
                strategy.appendField(locator, this, "accessorList", buffer, theAccessorList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AccountDataSharingSummary.DataSharingGroupList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AccountDataSharingSummary.DataSharingGroupList that = ((AccountDataSharingSummary.DataSharingGroupList) object);
            {
                String lhsDataSharingGroupCode;
                lhsDataSharingGroupCode = this.getDataSharingGroupCode();
                String rhsDataSharingGroupCode;
                rhsDataSharingGroupCode = that.getDataSharingGroupCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingGroupCode", lhsDataSharingGroupCode), LocatorUtils.property(thatLocator, "dataSharingGroupCode", rhsDataSharingGroupCode), lhsDataSharingGroupCode, rhsDataSharingGroupCode)) {
                    return false;
                }
            }
            {
                double lhsDataSharingContributionTotalAmt;
                lhsDataSharingContributionTotalAmt = (true?this.getDataSharingContributionTotalAmt(): 0.0D);
                double rhsDataSharingContributionTotalAmt;
                rhsDataSharingContributionTotalAmt = (true?that.getDataSharingContributionTotalAmt(): 0.0D);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingContributionTotalAmt", lhsDataSharingContributionTotalAmt), LocatorUtils.property(thatLocator, "dataSharingContributionTotalAmt", rhsDataSharingContributionTotalAmt), lhsDataSharingContributionTotalAmt, rhsDataSharingContributionTotalAmt)) {
                    return false;
                }
            }
            {
                Usage lhsTotalIncludedDataAllowance;
                lhsTotalIncludedDataAllowance = this.getTotalIncludedDataAllowance();
                Usage rhsTotalIncludedDataAllowance;
                rhsTotalIncludedDataAllowance = that.getTotalIncludedDataAllowance();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "totalIncludedDataAllowance", lhsTotalIncludedDataAllowance), LocatorUtils.property(thatLocator, "totalIncludedDataAllowance", rhsTotalIncludedDataAllowance), lhsTotalIncludedDataAllowance, rhsTotalIncludedDataAllowance)) {
                    return false;
                }
            }
            {
                List<SubscriberDataSharingSummary> lhsContributorList;
                lhsContributorList = (((this.contributorList!= null)&&(!this.contributorList.isEmpty()))?this.getContributorList():null);
                List<SubscriberDataSharingSummary> rhsContributorList;
                rhsContributorList = (((that.contributorList!= null)&&(!that.contributorList.isEmpty()))?that.getContributorList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "contributorList", lhsContributorList), LocatorUtils.property(thatLocator, "contributorList", rhsContributorList), lhsContributorList, rhsContributorList)) {
                    return false;
                }
            }
            {
                List<SubscriberDataSharingSummary> lhsAccessorList;
                lhsAccessorList = (((this.accessorList!= null)&&(!this.accessorList.isEmpty()))?this.getAccessorList():null);
                List<SubscriberDataSharingSummary> rhsAccessorList;
                rhsAccessorList = (((that.accessorList!= null)&&(!that.accessorList.isEmpty()))?that.getAccessorList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "accessorList", lhsAccessorList), LocatorUtils.property(thatLocator, "accessorList", rhsAccessorList), lhsAccessorList, rhsAccessorList)) {
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
