
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Name;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for BundleInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundleTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bundleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bundleId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="bundleNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Name" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="bundleDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="hsiaTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="30" minOccurs="0"/>
 *         &lt;element name="bundleItemList" maxOccurs="50" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="bundleItemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                   &lt;element name="offerMappingId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *                   &lt;element name="seatTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="tierTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="seatSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="mandatoryInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="mutuallyExclusiveInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="minQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="maxQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="availableLineNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *                   &lt;element name="effectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *                   &lt;element name="effectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                   &lt;element name="bundleWirelessOffer" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleWirelessOfferInfoType"/>
 *                   &lt;element name="eligibleServiceEditionTypeCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleInfoType", propOrder = {
    "bundleTypeCode",
    "bundleCode",
    "bundleId",
    "bundleNameList",
    "bundleDescription",
    "hsiaTypeCode",
    "provinceCodeList",
    "bundleItemList"
})
public class BundleInfoType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String bundleTypeCode;
    protected String bundleCode;
    protected Long bundleId;
    protected List<Name> bundleNameList;
    protected MultilingualDescriptionList bundleDescription;
    protected String hsiaTypeCode;
    protected List<String> provinceCodeList;
    protected List<BundleInfoType.BundleItemList> bundleItemList;

    /**
     * Gets the value of the bundleTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundleTypeCode() {
        return bundleTypeCode;
    }

    /**
     * Sets the value of the bundleTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundleTypeCode(String value) {
        this.bundleTypeCode = value;
    }

    /**
     * Gets the value of the bundleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundleCode() {
        return bundleCode;
    }

    /**
     * Sets the value of the bundleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundleCode(String value) {
        this.bundleCode = value;
    }

    /**
     * Gets the value of the bundleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBundleId() {
        return bundleId;
    }

    /**
     * Sets the value of the bundleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBundleId(Long value) {
        this.bundleId = value;
    }

    /**
     * Gets the value of the bundleNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bundleNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBundleNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Name }
     * 
     * 
     */
    public List<Name> getBundleNameList() {
        if (bundleNameList == null) {
            bundleNameList = new ArrayList<Name>();
        }
        return this.bundleNameList;
    }

    /**
     * Gets the value of the bundleDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getBundleDescription() {
        return bundleDescription;
    }

    /**
     * Sets the value of the bundleDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setBundleDescription(MultilingualDescriptionList value) {
        this.bundleDescription = value;
    }

    /**
     * Gets the value of the hsiaTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHsiaTypeCode() {
        return hsiaTypeCode;
    }

    /**
     * Sets the value of the hsiaTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHsiaTypeCode(String value) {
        this.hsiaTypeCode = value;
    }

    /**
     * Gets the value of the provinceCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the provinceCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvinceCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProvinceCodeList() {
        if (provinceCodeList == null) {
            provinceCodeList = new ArrayList<String>();
        }
        return this.provinceCodeList;
    }

    /**
     * Gets the value of the bundleItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the bundleItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBundleItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BundleInfoType.BundleItemList }
     * 
     * 
     */
    public List<BundleInfoType.BundleItemList> getBundleItemList() {
        if (bundleItemList == null) {
            bundleItemList = new ArrayList<BundleInfoType.BundleItemList>();
        }
        return this.bundleItemList;
    }

    /**
     * Sets the value of the bundleNameList property.
     * 
     * @param bundleNameList
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setBundleNameList(List<Name> bundleNameList) {
        this.bundleNameList = bundleNameList;
    }

    /**
     * Sets the value of the provinceCodeList property.
     * 
     * @param provinceCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCodeList(List<String> provinceCodeList) {
        this.provinceCodeList = provinceCodeList;
    }

    /**
     * Sets the value of the bundleItemList property.
     * 
     * @param bundleItemList
     *     allowed object is
     *     {@link BundleInfoType.BundleItemList }
     *     
     */
    public void setBundleItemList(List<BundleInfoType.BundleItemList> bundleItemList) {
        this.bundleItemList = bundleItemList;
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
            String theBundleTypeCode;
            theBundleTypeCode = this.getBundleTypeCode();
            strategy.appendField(locator, this, "bundleTypeCode", buffer, theBundleTypeCode);
        }
        {
            String theBundleCode;
            theBundleCode = this.getBundleCode();
            strategy.appendField(locator, this, "bundleCode", buffer, theBundleCode);
        }
        {
            Long theBundleId;
            theBundleId = this.getBundleId();
            strategy.appendField(locator, this, "bundleId", buffer, theBundleId);
        }
        {
            List<Name> theBundleNameList;
            theBundleNameList = (((this.bundleNameList!= null)&&(!this.bundleNameList.isEmpty()))?this.getBundleNameList():null);
            strategy.appendField(locator, this, "bundleNameList", buffer, theBundleNameList);
        }
        {
            MultilingualDescriptionList theBundleDescription;
            theBundleDescription = this.getBundleDescription();
            strategy.appendField(locator, this, "bundleDescription", buffer, theBundleDescription);
        }
        {
            String theHsiaTypeCode;
            theHsiaTypeCode = this.getHsiaTypeCode();
            strategy.appendField(locator, this, "hsiaTypeCode", buffer, theHsiaTypeCode);
        }
        {
            List<String> theProvinceCodeList;
            theProvinceCodeList = (((this.provinceCodeList!= null)&&(!this.provinceCodeList.isEmpty()))?this.getProvinceCodeList():null);
            strategy.appendField(locator, this, "provinceCodeList", buffer, theProvinceCodeList);
        }
        {
            List<BundleInfoType.BundleItemList> theBundleItemList;
            theBundleItemList = (((this.bundleItemList!= null)&&(!this.bundleItemList.isEmpty()))?this.getBundleItemList():null);
            strategy.appendField(locator, this, "bundleItemList", buffer, theBundleItemList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BundleInfoType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BundleInfoType that = ((BundleInfoType) object);
        {
            String lhsBundleTypeCode;
            lhsBundleTypeCode = this.getBundleTypeCode();
            String rhsBundleTypeCode;
            rhsBundleTypeCode = that.getBundleTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleTypeCode", lhsBundleTypeCode), LocatorUtils.property(thatLocator, "bundleTypeCode", rhsBundleTypeCode), lhsBundleTypeCode, rhsBundleTypeCode)) {
                return false;
            }
        }
        {
            String lhsBundleCode;
            lhsBundleCode = this.getBundleCode();
            String rhsBundleCode;
            rhsBundleCode = that.getBundleCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleCode", lhsBundleCode), LocatorUtils.property(thatLocator, "bundleCode", rhsBundleCode), lhsBundleCode, rhsBundleCode)) {
                return false;
            }
        }
        {
            Long lhsBundleId;
            lhsBundleId = this.getBundleId();
            Long rhsBundleId;
            rhsBundleId = that.getBundleId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleId", lhsBundleId), LocatorUtils.property(thatLocator, "bundleId", rhsBundleId), lhsBundleId, rhsBundleId)) {
                return false;
            }
        }
        {
            List<Name> lhsBundleNameList;
            lhsBundleNameList = (((this.bundleNameList!= null)&&(!this.bundleNameList.isEmpty()))?this.getBundleNameList():null);
            List<Name> rhsBundleNameList;
            rhsBundleNameList = (((that.bundleNameList!= null)&&(!that.bundleNameList.isEmpty()))?that.getBundleNameList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleNameList", lhsBundleNameList), LocatorUtils.property(thatLocator, "bundleNameList", rhsBundleNameList), lhsBundleNameList, rhsBundleNameList)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsBundleDescription;
            lhsBundleDescription = this.getBundleDescription();
            MultilingualDescriptionList rhsBundleDescription;
            rhsBundleDescription = that.getBundleDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleDescription", lhsBundleDescription), LocatorUtils.property(thatLocator, "bundleDescription", rhsBundleDescription), lhsBundleDescription, rhsBundleDescription)) {
                return false;
            }
        }
        {
            String lhsHsiaTypeCode;
            lhsHsiaTypeCode = this.getHsiaTypeCode();
            String rhsHsiaTypeCode;
            rhsHsiaTypeCode = that.getHsiaTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hsiaTypeCode", lhsHsiaTypeCode), LocatorUtils.property(thatLocator, "hsiaTypeCode", rhsHsiaTypeCode), lhsHsiaTypeCode, rhsHsiaTypeCode)) {
                return false;
            }
        }
        {
            List<String> lhsProvinceCodeList;
            lhsProvinceCodeList = (((this.provinceCodeList!= null)&&(!this.provinceCodeList.isEmpty()))?this.getProvinceCodeList():null);
            List<String> rhsProvinceCodeList;
            rhsProvinceCodeList = (((that.provinceCodeList!= null)&&(!that.provinceCodeList.isEmpty()))?that.getProvinceCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceCodeList", lhsProvinceCodeList), LocatorUtils.property(thatLocator, "provinceCodeList", rhsProvinceCodeList), lhsProvinceCodeList, rhsProvinceCodeList)) {
                return false;
            }
        }
        {
            List<BundleInfoType.BundleItemList> lhsBundleItemList;
            lhsBundleItemList = (((this.bundleItemList!= null)&&(!this.bundleItemList.isEmpty()))?this.getBundleItemList():null);
            List<BundleInfoType.BundleItemList> rhsBundleItemList;
            rhsBundleItemList = (((that.bundleItemList!= null)&&(!that.bundleItemList.isEmpty()))?that.getBundleItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleItemList", lhsBundleItemList), LocatorUtils.property(thatLocator, "bundleItemList", rhsBundleItemList), lhsBundleItemList, rhsBundleItemList)) {
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
     *         &lt;element name="bundleItemId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *         &lt;element name="offerMappingId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
     *         &lt;element name="seatTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="tierTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="seatSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="mandatoryInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="mutuallyExclusiveInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="minQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="maxQuantity" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="availableLineNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
     *         &lt;element name="effectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
     *         &lt;element name="effectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *         &lt;element name="bundleWirelessOffer" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleWirelessOfferInfoType"/>
     *         &lt;element name="eligibleServiceEditionTypeCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
        "bundleItemId",
        "offerMappingId",
        "seatTypeCode",
        "tierTypeCode",
        "seatSubTypeCode",
        "mandatoryInd",
        "mutuallyExclusiveInd",
        "minQuantity",
        "maxQuantity",
        "availableLineNumber",
        "effectiveStartDate",
        "effectiveEndDate",
        "bundleWirelessOffer",
        "eligibleServiceEditionTypeCodeList"
    })
    public static class BundleItemList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected Long bundleItemId;
        protected Long offerMappingId;
        protected String seatTypeCode;
        protected String tierTypeCode;
        protected String seatSubTypeCode;
        protected Boolean mandatoryInd;
        protected Boolean mutuallyExclusiveInd;
        protected Integer minQuantity;
        protected Integer maxQuantity;
        protected Integer availableLineNumber;
        @XmlElement(required = true, type = String.class)
        @XmlJavaTypeAdapter(DateTimeAdapter.class)
        @XmlSchemaType(name = "dateTime")
        protected Date effectiveStartDate;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(DateTimeAdapter.class)
        @XmlSchemaType(name = "dateTime")
        protected Date effectiveEndDate;
        @XmlElement(required = true)
        protected BundleWirelessOfferInfoType bundleWirelessOffer;
        protected List<String> eligibleServiceEditionTypeCodeList;

        /**
         * Gets the value of the bundleItemId property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getBundleItemId() {
            return bundleItemId;
        }

        /**
         * Sets the value of the bundleItemId property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setBundleItemId(Long value) {
            this.bundleItemId = value;
        }

        /**
         * Gets the value of the offerMappingId property.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getOfferMappingId() {
            return offerMappingId;
        }

        /**
         * Sets the value of the offerMappingId property.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setOfferMappingId(Long value) {
            this.offerMappingId = value;
        }

        /**
         * Gets the value of the seatTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSeatTypeCode() {
            return seatTypeCode;
        }

        /**
         * Sets the value of the seatTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSeatTypeCode(String value) {
            this.seatTypeCode = value;
        }

        /**
         * Gets the value of the tierTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTierTypeCode() {
            return tierTypeCode;
        }

        /**
         * Sets the value of the tierTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTierTypeCode(String value) {
            this.tierTypeCode = value;
        }

        /**
         * Gets the value of the seatSubTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSeatSubTypeCode() {
            return seatSubTypeCode;
        }

        /**
         * Sets the value of the seatSubTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSeatSubTypeCode(String value) {
            this.seatSubTypeCode = value;
        }

        /**
         * Gets the value of the mandatoryInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isMandatoryInd() {
            return mandatoryInd;
        }

        /**
         * Sets the value of the mandatoryInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setMandatoryInd(Boolean value) {
            this.mandatoryInd = value;
        }

        /**
         * Gets the value of the mutuallyExclusiveInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isMutuallyExclusiveInd() {
            return mutuallyExclusiveInd;
        }

        /**
         * Sets the value of the mutuallyExclusiveInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setMutuallyExclusiveInd(Boolean value) {
            this.mutuallyExclusiveInd = value;
        }

        /**
         * Gets the value of the minQuantity property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getMinQuantity() {
            return minQuantity;
        }

        /**
         * Sets the value of the minQuantity property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setMinQuantity(Integer value) {
            this.minQuantity = value;
        }

        /**
         * Gets the value of the maxQuantity property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getMaxQuantity() {
            return maxQuantity;
        }

        /**
         * Sets the value of the maxQuantity property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setMaxQuantity(Integer value) {
            this.maxQuantity = value;
        }

        /**
         * Gets the value of the availableLineNumber property.
         * 
         * @return
         *     possible object is
         *     {@link Integer }
         *     
         */
        public Integer getAvailableLineNumber() {
            return availableLineNumber;
        }

        /**
         * Sets the value of the availableLineNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link Integer }
         *     
         */
        public void setAvailableLineNumber(Integer value) {
            this.availableLineNumber = value;
        }

        /**
         * Gets the value of the effectiveStartDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getEffectiveStartDate() {
            return effectiveStartDate;
        }

        /**
         * Sets the value of the effectiveStartDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEffectiveStartDate(Date value) {
            this.effectiveStartDate = value;
        }

        /**
         * Gets the value of the effectiveEndDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getEffectiveEndDate() {
            return effectiveEndDate;
        }

        /**
         * Sets the value of the effectiveEndDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEffectiveEndDate(Date value) {
            this.effectiveEndDate = value;
        }

        /**
         * Gets the value of the bundleWirelessOffer property.
         * 
         * @return
         *     possible object is
         *     {@link BundleWirelessOfferInfoType }
         *     
         */
        public BundleWirelessOfferInfoType getBundleWirelessOffer() {
            return bundleWirelessOffer;
        }

        /**
         * Sets the value of the bundleWirelessOffer property.
         * 
         * @param value
         *     allowed object is
         *     {@link BundleWirelessOfferInfoType }
         *     
         */
        public void setBundleWirelessOffer(BundleWirelessOfferInfoType value) {
            this.bundleWirelessOffer = value;
        }

        /**
         * Gets the value of the eligibleServiceEditionTypeCodeList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the eligibleServiceEditionTypeCodeList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEligibleServiceEditionTypeCodeList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link String }
         * 
         * 
         */
        public List<String> getEligibleServiceEditionTypeCodeList() {
            if (eligibleServiceEditionTypeCodeList == null) {
                eligibleServiceEditionTypeCodeList = new ArrayList<String>();
            }
            return this.eligibleServiceEditionTypeCodeList;
        }

        /**
         * Sets the value of the eligibleServiceEditionTypeCodeList property.
         * 
         * @param eligibleServiceEditionTypeCodeList
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEligibleServiceEditionTypeCodeList(List<String> eligibleServiceEditionTypeCodeList) {
            this.eligibleServiceEditionTypeCodeList = eligibleServiceEditionTypeCodeList;
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
                Long theBundleItemId;
                theBundleItemId = this.getBundleItemId();
                strategy.appendField(locator, this, "bundleItemId", buffer, theBundleItemId);
            }
            {
                Long theOfferMappingId;
                theOfferMappingId = this.getOfferMappingId();
                strategy.appendField(locator, this, "offerMappingId", buffer, theOfferMappingId);
            }
            {
                String theSeatTypeCode;
                theSeatTypeCode = this.getSeatTypeCode();
                strategy.appendField(locator, this, "seatTypeCode", buffer, theSeatTypeCode);
            }
            {
                String theTierTypeCode;
                theTierTypeCode = this.getTierTypeCode();
                strategy.appendField(locator, this, "tierTypeCode", buffer, theTierTypeCode);
            }
            {
                String theSeatSubTypeCode;
                theSeatSubTypeCode = this.getSeatSubTypeCode();
                strategy.appendField(locator, this, "seatSubTypeCode", buffer, theSeatSubTypeCode);
            }
            {
                Boolean theMandatoryInd;
                theMandatoryInd = this.isMandatoryInd();
                strategy.appendField(locator, this, "mandatoryInd", buffer, theMandatoryInd);
            }
            {
                Boolean theMutuallyExclusiveInd;
                theMutuallyExclusiveInd = this.isMutuallyExclusiveInd();
                strategy.appendField(locator, this, "mutuallyExclusiveInd", buffer, theMutuallyExclusiveInd);
            }
            {
                Integer theMinQuantity;
                theMinQuantity = this.getMinQuantity();
                strategy.appendField(locator, this, "minQuantity", buffer, theMinQuantity);
            }
            {
                Integer theMaxQuantity;
                theMaxQuantity = this.getMaxQuantity();
                strategy.appendField(locator, this, "maxQuantity", buffer, theMaxQuantity);
            }
            {
                Integer theAvailableLineNumber;
                theAvailableLineNumber = this.getAvailableLineNumber();
                strategy.appendField(locator, this, "availableLineNumber", buffer, theAvailableLineNumber);
            }
            {
                Date theEffectiveStartDate;
                theEffectiveStartDate = this.getEffectiveStartDate();
                strategy.appendField(locator, this, "effectiveStartDate", buffer, theEffectiveStartDate);
            }
            {
                Date theEffectiveEndDate;
                theEffectiveEndDate = this.getEffectiveEndDate();
                strategy.appendField(locator, this, "effectiveEndDate", buffer, theEffectiveEndDate);
            }
            {
                BundleWirelessOfferInfoType theBundleWirelessOffer;
                theBundleWirelessOffer = this.getBundleWirelessOffer();
                strategy.appendField(locator, this, "bundleWirelessOffer", buffer, theBundleWirelessOffer);
            }
            {
                List<String> theEligibleServiceEditionTypeCodeList;
                theEligibleServiceEditionTypeCodeList = (((this.eligibleServiceEditionTypeCodeList!= null)&&(!this.eligibleServiceEditionTypeCodeList.isEmpty()))?this.getEligibleServiceEditionTypeCodeList():null);
                strategy.appendField(locator, this, "eligibleServiceEditionTypeCodeList", buffer, theEligibleServiceEditionTypeCodeList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof BundleInfoType.BundleItemList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final BundleInfoType.BundleItemList that = ((BundleInfoType.BundleItemList) object);
            {
                Long lhsBundleItemId;
                lhsBundleItemId = this.getBundleItemId();
                Long rhsBundleItemId;
                rhsBundleItemId = that.getBundleItemId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleItemId", lhsBundleItemId), LocatorUtils.property(thatLocator, "bundleItemId", rhsBundleItemId), lhsBundleItemId, rhsBundleItemId)) {
                    return false;
                }
            }
            {
                Long lhsOfferMappingId;
                lhsOfferMappingId = this.getOfferMappingId();
                Long rhsOfferMappingId;
                rhsOfferMappingId = that.getOfferMappingId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "offerMappingId", lhsOfferMappingId), LocatorUtils.property(thatLocator, "offerMappingId", rhsOfferMappingId), lhsOfferMappingId, rhsOfferMappingId)) {
                    return false;
                }
            }
            {
                String lhsSeatTypeCode;
                lhsSeatTypeCode = this.getSeatTypeCode();
                String rhsSeatTypeCode;
                rhsSeatTypeCode = that.getSeatTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "seatTypeCode", lhsSeatTypeCode), LocatorUtils.property(thatLocator, "seatTypeCode", rhsSeatTypeCode), lhsSeatTypeCode, rhsSeatTypeCode)) {
                    return false;
                }
            }
            {
                String lhsTierTypeCode;
                lhsTierTypeCode = this.getTierTypeCode();
                String rhsTierTypeCode;
                rhsTierTypeCode = that.getTierTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "tierTypeCode", lhsTierTypeCode), LocatorUtils.property(thatLocator, "tierTypeCode", rhsTierTypeCode), lhsTierTypeCode, rhsTierTypeCode)) {
                    return false;
                }
            }
            {
                String lhsSeatSubTypeCode;
                lhsSeatSubTypeCode = this.getSeatSubTypeCode();
                String rhsSeatSubTypeCode;
                rhsSeatSubTypeCode = that.getSeatSubTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "seatSubTypeCode", lhsSeatSubTypeCode), LocatorUtils.property(thatLocator, "seatSubTypeCode", rhsSeatSubTypeCode), lhsSeatSubTypeCode, rhsSeatSubTypeCode)) {
                    return false;
                }
            }
            {
                Boolean lhsMandatoryInd;
                lhsMandatoryInd = this.isMandatoryInd();
                Boolean rhsMandatoryInd;
                rhsMandatoryInd = that.isMandatoryInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "mandatoryInd", lhsMandatoryInd), LocatorUtils.property(thatLocator, "mandatoryInd", rhsMandatoryInd), lhsMandatoryInd, rhsMandatoryInd)) {
                    return false;
                }
            }
            {
                Boolean lhsMutuallyExclusiveInd;
                lhsMutuallyExclusiveInd = this.isMutuallyExclusiveInd();
                Boolean rhsMutuallyExclusiveInd;
                rhsMutuallyExclusiveInd = that.isMutuallyExclusiveInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "mutuallyExclusiveInd", lhsMutuallyExclusiveInd), LocatorUtils.property(thatLocator, "mutuallyExclusiveInd", rhsMutuallyExclusiveInd), lhsMutuallyExclusiveInd, rhsMutuallyExclusiveInd)) {
                    return false;
                }
            }
            {
                Integer lhsMinQuantity;
                lhsMinQuantity = this.getMinQuantity();
                Integer rhsMinQuantity;
                rhsMinQuantity = that.getMinQuantity();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "minQuantity", lhsMinQuantity), LocatorUtils.property(thatLocator, "minQuantity", rhsMinQuantity), lhsMinQuantity, rhsMinQuantity)) {
                    return false;
                }
            }
            {
                Integer lhsMaxQuantity;
                lhsMaxQuantity = this.getMaxQuantity();
                Integer rhsMaxQuantity;
                rhsMaxQuantity = that.getMaxQuantity();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "maxQuantity", lhsMaxQuantity), LocatorUtils.property(thatLocator, "maxQuantity", rhsMaxQuantity), lhsMaxQuantity, rhsMaxQuantity)) {
                    return false;
                }
            }
            {
                Integer lhsAvailableLineNumber;
                lhsAvailableLineNumber = this.getAvailableLineNumber();
                Integer rhsAvailableLineNumber;
                rhsAvailableLineNumber = that.getAvailableLineNumber();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "availableLineNumber", lhsAvailableLineNumber), LocatorUtils.property(thatLocator, "availableLineNumber", rhsAvailableLineNumber), lhsAvailableLineNumber, rhsAvailableLineNumber)) {
                    return false;
                }
            }
            {
                Date lhsEffectiveStartDate;
                lhsEffectiveStartDate = this.getEffectiveStartDate();
                Date rhsEffectiveStartDate;
                rhsEffectiveStartDate = that.getEffectiveStartDate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveStartDate", lhsEffectiveStartDate), LocatorUtils.property(thatLocator, "effectiveStartDate", rhsEffectiveStartDate), lhsEffectiveStartDate, rhsEffectiveStartDate)) {
                    return false;
                }
            }
            {
                Date lhsEffectiveEndDate;
                lhsEffectiveEndDate = this.getEffectiveEndDate();
                Date rhsEffectiveEndDate;
                rhsEffectiveEndDate = that.getEffectiveEndDate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveEndDate", lhsEffectiveEndDate), LocatorUtils.property(thatLocator, "effectiveEndDate", rhsEffectiveEndDate), lhsEffectiveEndDate, rhsEffectiveEndDate)) {
                    return false;
                }
            }
            {
                BundleWirelessOfferInfoType lhsBundleWirelessOffer;
                lhsBundleWirelessOffer = this.getBundleWirelessOffer();
                BundleWirelessOfferInfoType rhsBundleWirelessOffer;
                rhsBundleWirelessOffer = that.getBundleWirelessOffer();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleWirelessOffer", lhsBundleWirelessOffer), LocatorUtils.property(thatLocator, "bundleWirelessOffer", rhsBundleWirelessOffer), lhsBundleWirelessOffer, rhsBundleWirelessOffer)) {
                    return false;
                }
            }
            {
                List<String> lhsEligibleServiceEditionTypeCodeList;
                lhsEligibleServiceEditionTypeCodeList = (((this.eligibleServiceEditionTypeCodeList!= null)&&(!this.eligibleServiceEditionTypeCodeList.isEmpty()))?this.getEligibleServiceEditionTypeCodeList():null);
                List<String> rhsEligibleServiceEditionTypeCodeList;
                rhsEligibleServiceEditionTypeCodeList = (((that.eligibleServiceEditionTypeCodeList!= null)&&(!that.eligibleServiceEditionTypeCodeList.isEmpty()))?that.getEligibleServiceEditionTypeCodeList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleServiceEditionTypeCodeList", lhsEligibleServiceEditionTypeCodeList), LocatorUtils.property(thatLocator, "eligibleServiceEditionTypeCodeList", rhsEligibleServiceEditionTypeCodeList), lhsEligibleServiceEditionTypeCodeList, rhsEligibleServiceEditionTypeCodeList)) {
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
