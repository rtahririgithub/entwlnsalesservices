
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
 * Indicates whether this is a recommended offer, plan, feature or device based on real time assessment result from PEIA and PUSGS database via the Infor Interaction Advisor (IA) owned by ACE
 * 
 * <p>Java class for SalesRecommendation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesRecommendation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recommendationCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recommendationRankNum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="recommendationStatusCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="recommendationResourceList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="resourceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="recommendationMessageList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesRecommendationMessage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesRecommendation", propOrder = {
    "recommendationCode",
    "recommendationRankNum",
    "recommendationStatusCodeList",
    "recommendationResourceList",
    "recommendationMessageList"
})
public class SalesRecommendation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String recommendationCode;
    protected Long recommendationRankNum;
    protected List<String> recommendationStatusCodeList;
    protected List<SalesRecommendation.RecommendationResourceList> recommendationResourceList;
    protected List<SalesRecommendationMessage> recommendationMessageList;

    /**
     * Gets the value of the recommendationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendationCode() {
        return recommendationCode;
    }

    /**
     * Sets the value of the recommendationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendationCode(String value) {
        this.recommendationCode = value;
    }

    /**
     * Gets the value of the recommendationRankNum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRecommendationRankNum() {
        return recommendationRankNum;
    }

    /**
     * Sets the value of the recommendationRankNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRecommendationRankNum(Long value) {
        this.recommendationRankNum = value;
    }

    /**
     * Gets the value of the recommendationStatusCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recommendationStatusCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecommendationStatusCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getRecommendationStatusCodeList() {
        if (recommendationStatusCodeList == null) {
            recommendationStatusCodeList = new ArrayList<String>();
        }
        return this.recommendationStatusCodeList;
    }

    /**
     * Gets the value of the recommendationResourceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recommendationResourceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecommendationResourceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesRecommendation.RecommendationResourceList }
     * 
     * 
     */
    public List<SalesRecommendation.RecommendationResourceList> getRecommendationResourceList() {
        if (recommendationResourceList == null) {
            recommendationResourceList = new ArrayList<SalesRecommendation.RecommendationResourceList>();
        }
        return this.recommendationResourceList;
    }

    /**
     * Gets the value of the recommendationMessageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recommendationMessageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecommendationMessageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesRecommendationMessage }
     * 
     * 
     */
    public List<SalesRecommendationMessage> getRecommendationMessageList() {
        if (recommendationMessageList == null) {
            recommendationMessageList = new ArrayList<SalesRecommendationMessage>();
        }
        return this.recommendationMessageList;
    }

    /**
     * Sets the value of the recommendationStatusCodeList property.
     * 
     * @param recommendationStatusCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendationStatusCodeList(List<String> recommendationStatusCodeList) {
        this.recommendationStatusCodeList = recommendationStatusCodeList;
    }

    /**
     * Sets the value of the recommendationResourceList property.
     * 
     * @param recommendationResourceList
     *     allowed object is
     *     {@link SalesRecommendation.RecommendationResourceList }
     *     
     */
    public void setRecommendationResourceList(List<SalesRecommendation.RecommendationResourceList> recommendationResourceList) {
        this.recommendationResourceList = recommendationResourceList;
    }

    /**
     * Sets the value of the recommendationMessageList property.
     * 
     * @param recommendationMessageList
     *     allowed object is
     *     {@link SalesRecommendationMessage }
     *     
     */
    public void setRecommendationMessageList(List<SalesRecommendationMessage> recommendationMessageList) {
        this.recommendationMessageList = recommendationMessageList;
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
            String theRecommendationCode;
            theRecommendationCode = this.getRecommendationCode();
            strategy.appendField(locator, this, "recommendationCode", buffer, theRecommendationCode);
        }
        {
            Long theRecommendationRankNum;
            theRecommendationRankNum = this.getRecommendationRankNum();
            strategy.appendField(locator, this, "recommendationRankNum", buffer, theRecommendationRankNum);
        }
        {
            List<String> theRecommendationStatusCodeList;
            theRecommendationStatusCodeList = (((this.recommendationStatusCodeList!= null)&&(!this.recommendationStatusCodeList.isEmpty()))?this.getRecommendationStatusCodeList():null);
            strategy.appendField(locator, this, "recommendationStatusCodeList", buffer, theRecommendationStatusCodeList);
        }
        {
            List<SalesRecommendation.RecommendationResourceList> theRecommendationResourceList;
            theRecommendationResourceList = (((this.recommendationResourceList!= null)&&(!this.recommendationResourceList.isEmpty()))?this.getRecommendationResourceList():null);
            strategy.appendField(locator, this, "recommendationResourceList", buffer, theRecommendationResourceList);
        }
        {
            List<SalesRecommendationMessage> theRecommendationMessageList;
            theRecommendationMessageList = (((this.recommendationMessageList!= null)&&(!this.recommendationMessageList.isEmpty()))?this.getRecommendationMessageList():null);
            strategy.appendField(locator, this, "recommendationMessageList", buffer, theRecommendationMessageList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesRecommendation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesRecommendation that = ((SalesRecommendation) object);
        {
            String lhsRecommendationCode;
            lhsRecommendationCode = this.getRecommendationCode();
            String rhsRecommendationCode;
            rhsRecommendationCode = that.getRecommendationCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationCode", lhsRecommendationCode), LocatorUtils.property(thatLocator, "recommendationCode", rhsRecommendationCode), lhsRecommendationCode, rhsRecommendationCode)) {
                return false;
            }
        }
        {
            Long lhsRecommendationRankNum;
            lhsRecommendationRankNum = this.getRecommendationRankNum();
            Long rhsRecommendationRankNum;
            rhsRecommendationRankNum = that.getRecommendationRankNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationRankNum", lhsRecommendationRankNum), LocatorUtils.property(thatLocator, "recommendationRankNum", rhsRecommendationRankNum), lhsRecommendationRankNum, rhsRecommendationRankNum)) {
                return false;
            }
        }
        {
            List<String> lhsRecommendationStatusCodeList;
            lhsRecommendationStatusCodeList = (((this.recommendationStatusCodeList!= null)&&(!this.recommendationStatusCodeList.isEmpty()))?this.getRecommendationStatusCodeList():null);
            List<String> rhsRecommendationStatusCodeList;
            rhsRecommendationStatusCodeList = (((that.recommendationStatusCodeList!= null)&&(!that.recommendationStatusCodeList.isEmpty()))?that.getRecommendationStatusCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationStatusCodeList", lhsRecommendationStatusCodeList), LocatorUtils.property(thatLocator, "recommendationStatusCodeList", rhsRecommendationStatusCodeList), lhsRecommendationStatusCodeList, rhsRecommendationStatusCodeList)) {
                return false;
            }
        }
        {
            List<SalesRecommendation.RecommendationResourceList> lhsRecommendationResourceList;
            lhsRecommendationResourceList = (((this.recommendationResourceList!= null)&&(!this.recommendationResourceList.isEmpty()))?this.getRecommendationResourceList():null);
            List<SalesRecommendation.RecommendationResourceList> rhsRecommendationResourceList;
            rhsRecommendationResourceList = (((that.recommendationResourceList!= null)&&(!that.recommendationResourceList.isEmpty()))?that.getRecommendationResourceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationResourceList", lhsRecommendationResourceList), LocatorUtils.property(thatLocator, "recommendationResourceList", rhsRecommendationResourceList), lhsRecommendationResourceList, rhsRecommendationResourceList)) {
                return false;
            }
        }
        {
            List<SalesRecommendationMessage> lhsRecommendationMessageList;
            lhsRecommendationMessageList = (((this.recommendationMessageList!= null)&&(!this.recommendationMessageList.isEmpty()))?this.getRecommendationMessageList():null);
            List<SalesRecommendationMessage> rhsRecommendationMessageList;
            rhsRecommendationMessageList = (((that.recommendationMessageList!= null)&&(!that.recommendationMessageList.isEmpty()))?that.getRecommendationMessageList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationMessageList", lhsRecommendationMessageList), LocatorUtils.property(thatLocator, "recommendationMessageList", rhsRecommendationMessageList), lhsRecommendationMessageList, rhsRecommendationMessageList)) {
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
     *         &lt;element name="resourceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="resourceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "resourceTypeCode",
        "resourceId"
    })
    public static class RecommendationResourceList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String resourceTypeCode;
        @XmlElement(required = true)
        protected String resourceId;

        /**
         * Gets the value of the resourceTypeCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResourceTypeCode() {
            return resourceTypeCode;
        }

        /**
         * Sets the value of the resourceTypeCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResourceTypeCode(String value) {
            this.resourceTypeCode = value;
        }

        /**
         * Gets the value of the resourceId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getResourceId() {
            return resourceId;
        }

        /**
         * Sets the value of the resourceId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setResourceId(String value) {
            this.resourceId = value;
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
                String theResourceTypeCode;
                theResourceTypeCode = this.getResourceTypeCode();
                strategy.appendField(locator, this, "resourceTypeCode", buffer, theResourceTypeCode);
            }
            {
                String theResourceId;
                theResourceId = this.getResourceId();
                strategy.appendField(locator, this, "resourceId", buffer, theResourceId);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof SalesRecommendation.RecommendationResourceList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final SalesRecommendation.RecommendationResourceList that = ((SalesRecommendation.RecommendationResourceList) object);
            {
                String lhsResourceTypeCode;
                lhsResourceTypeCode = this.getResourceTypeCode();
                String rhsResourceTypeCode;
                rhsResourceTypeCode = that.getResourceTypeCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "resourceTypeCode", lhsResourceTypeCode), LocatorUtils.property(thatLocator, "resourceTypeCode", rhsResourceTypeCode), lhsResourceTypeCode, rhsResourceTypeCode)) {
                    return false;
                }
            }
            {
                String lhsResourceId;
                lhsResourceId = this.getResourceId();
                String rhsResourceId;
                rhsResourceId = that.getResourceId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "resourceId", lhsResourceId), LocatorUtils.property(thatLocator, "resourceId", rhsResourceId), lhsResourceId, rhsResourceId)) {
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
