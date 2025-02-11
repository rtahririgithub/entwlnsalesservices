
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
import com.telus.tmi.xmlschema.xsd.partner.partner.channelpartnercommon_v2.ChannelPartnerUserProfileType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for OperationHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OperationHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderSubType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originatorApplicationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="salesTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesTransactionTimestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="salesId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="associatedOriginalSales" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="salesId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="eTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="userProfile" type="{http://xmlschema.tmi.telus.com/xsd/Partner/Partner/ChannelPartnerCommon_v2}ChannelPartnerUserProfileType"/>
 *         &lt;element name="agentProfile" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AgentProfile" minOccurs="0"/>
 *         &lt;element name="salesPersonRoleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="brandCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemIntegrationParameterList" maxOccurs="10" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="parameterName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="parameterValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="managerOverrideIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="authorizingUserProfile" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AgentProfile" minOccurs="0"/>
 *         &lt;element name="salesRecommendationIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="refreshInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OperationHeader", propOrder = {
    "salesType",
    "orderType",
    "orderSubType",
    "originatorApplicationId",
    "salesTransactionId",
    "salesTransactionTimestamp",
    "salesId",
    "eTransactionId",
    "associatedOriginalSales",
    "userProfile",
    "agentProfile",
    "salesPersonRoleCode",
    "brandCode",
    "systemIntegrationParameterList",
    "managerOverrideIndicator",
    "authorizingUserProfile",
    "salesRecommendationIndicator",
    "refreshInd"
})
public class OperationHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String salesType;
    @XmlElement(required = true)
    protected String orderType;
    protected String orderSubType;
    protected Long originatorApplicationId;
    protected String salesTransactionId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date salesTransactionTimestamp;
    protected String salesId;
    protected String eTransactionId;
    protected OperationHeader.AssociatedOriginalSales associatedOriginalSales;
    @XmlElement(required = true)
    protected ChannelPartnerUserProfileType userProfile;
    protected AgentProfile agentProfile;
    protected String salesPersonRoleCode;
    @XmlElement(required = true)
    protected String brandCode;
    protected List<OperationHeader.SystemIntegrationParameterList> systemIntegrationParameterList;
    protected Boolean managerOverrideIndicator;
    protected AgentProfile authorizingUserProfile;
    protected Boolean salesRecommendationIndicator;
    protected Boolean refreshInd;

    /**
     * Gets the value of the salesType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesType() {
        return salesType;
    }

    /**
     * Sets the value of the salesType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesType(String value) {
        this.salesType = value;
    }

    /**
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the orderSubType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderSubType() {
        return orderSubType;
    }

    /**
     * Sets the value of the orderSubType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderSubType(String value) {
        this.orderSubType = value;
    }

    /**
     * Gets the value of the originatorApplicationId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOriginatorApplicationId() {
        return originatorApplicationId;
    }

    /**
     * Sets the value of the originatorApplicationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOriginatorApplicationId(Long value) {
        this.originatorApplicationId = value;
    }

    /**
     * Gets the value of the salesTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesTransactionId() {
        return salesTransactionId;
    }

    /**
     * Sets the value of the salesTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesTransactionId(String value) {
        this.salesTransactionId = value;
    }

    /**
     * Gets the value of the salesTransactionTimestamp property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getSalesTransactionTimestamp() {
        return salesTransactionTimestamp;
    }

    /**
     * Sets the value of the salesTransactionTimestamp property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesTransactionTimestamp(Date value) {
        this.salesTransactionTimestamp = value;
    }

    /**
     * Gets the value of the salesId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesId() {
        return salesId;
    }

    /**
     * Sets the value of the salesId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesId(String value) {
        this.salesId = value;
    }

    /**
     * Gets the value of the eTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getETransactionId() {
        return eTransactionId;
    }

    /**
     * Sets the value of the eTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setETransactionId(String value) {
        this.eTransactionId = value;
    }

    /**
     * Gets the value of the associatedOriginalSales property.
     * 
     * @return
     *     possible object is
     *     {@link OperationHeader.AssociatedOriginalSales }
     *     
     */
    public OperationHeader.AssociatedOriginalSales getAssociatedOriginalSales() {
        return associatedOriginalSales;
    }

    /**
     * Sets the value of the associatedOriginalSales property.
     * 
     * @param value
     *     allowed object is
     *     {@link OperationHeader.AssociatedOriginalSales }
     *     
     */
    public void setAssociatedOriginalSales(OperationHeader.AssociatedOriginalSales value) {
        this.associatedOriginalSales = value;
    }

    /**
     * Gets the value of the userProfile property.
     * 
     * @return
     *     possible object is
     *     {@link ChannelPartnerUserProfileType }
     *     
     */
    public ChannelPartnerUserProfileType getUserProfile() {
        return userProfile;
    }

    /**
     * Sets the value of the userProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link ChannelPartnerUserProfileType }
     *     
     */
    public void setUserProfile(ChannelPartnerUserProfileType value) {
        this.userProfile = value;
    }

    /**
     * Gets the value of the agentProfile property.
     * 
     * @return
     *     possible object is
     *     {@link AgentProfile }
     *     
     */
    public AgentProfile getAgentProfile() {
        return agentProfile;
    }

    /**
     * Sets the value of the agentProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgentProfile }
     *     
     */
    public void setAgentProfile(AgentProfile value) {
        this.agentProfile = value;
    }

    /**
     * Gets the value of the salesPersonRoleCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesPersonRoleCode() {
        return salesPersonRoleCode;
    }

    /**
     * Sets the value of the salesPersonRoleCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesPersonRoleCode(String value) {
        this.salesPersonRoleCode = value;
    }

    /**
     * Gets the value of the brandCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBrandCode() {
        return brandCode;
    }

    /**
     * Sets the value of the brandCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBrandCode(String value) {
        this.brandCode = value;
    }

    /**
     * Gets the value of the systemIntegrationParameterList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the systemIntegrationParameterList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSystemIntegrationParameterList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OperationHeader.SystemIntegrationParameterList }
     * 
     * 
     */
    public List<OperationHeader.SystemIntegrationParameterList> getSystemIntegrationParameterList() {
        if (systemIntegrationParameterList == null) {
            systemIntegrationParameterList = new ArrayList<OperationHeader.SystemIntegrationParameterList>();
        }
        return this.systemIntegrationParameterList;
    }

    /**
     * Gets the value of the managerOverrideIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isManagerOverrideIndicator() {
        return managerOverrideIndicator;
    }

    /**
     * Sets the value of the managerOverrideIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setManagerOverrideIndicator(Boolean value) {
        this.managerOverrideIndicator = value;
    }

    /**
     * Gets the value of the authorizingUserProfile property.
     * 
     * @return
     *     possible object is
     *     {@link AgentProfile }
     *     
     */
    public AgentProfile getAuthorizingUserProfile() {
        return authorizingUserProfile;
    }

    /**
     * Sets the value of the authorizingUserProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link AgentProfile }
     *     
     */
    public void setAuthorizingUserProfile(AgentProfile value) {
        this.authorizingUserProfile = value;
    }

    /**
     * Gets the value of the salesRecommendationIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSalesRecommendationIndicator() {
        return salesRecommendationIndicator;
    }

    /**
     * Sets the value of the salesRecommendationIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSalesRecommendationIndicator(Boolean value) {
        this.salesRecommendationIndicator = value;
    }

    /**
     * Gets the value of the refreshInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRefreshInd() {
        return refreshInd;
    }

    /**
     * Sets the value of the refreshInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRefreshInd(Boolean value) {
        this.refreshInd = value;
    }

    /**
     * Sets the value of the systemIntegrationParameterList property.
     * 
     * @param systemIntegrationParameterList
     *     allowed object is
     *     {@link OperationHeader.SystemIntegrationParameterList }
     *     
     */
    public void setSystemIntegrationParameterList(List<OperationHeader.SystemIntegrationParameterList> systemIntegrationParameterList) {
        this.systemIntegrationParameterList = systemIntegrationParameterList;
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
            String theSalesType;
            theSalesType = this.getSalesType();
            strategy.appendField(locator, this, "salesType", buffer, theSalesType);
        }
        {
            String theOrderType;
            theOrderType = this.getOrderType();
            strategy.appendField(locator, this, "orderType", buffer, theOrderType);
        }
        {
            String theOrderSubType;
            theOrderSubType = this.getOrderSubType();
            strategy.appendField(locator, this, "orderSubType", buffer, theOrderSubType);
        }
        {
            Long theOriginatorApplicationId;
            theOriginatorApplicationId = this.getOriginatorApplicationId();
            strategy.appendField(locator, this, "originatorApplicationId", buffer, theOriginatorApplicationId);
        }
        {
            String theSalesTransactionId;
            theSalesTransactionId = this.getSalesTransactionId();
            strategy.appendField(locator, this, "salesTransactionId", buffer, theSalesTransactionId);
        }
        {
            Date theSalesTransactionTimestamp;
            theSalesTransactionTimestamp = this.getSalesTransactionTimestamp();
            strategy.appendField(locator, this, "salesTransactionTimestamp", buffer, theSalesTransactionTimestamp);
        }
        {
            String theSalesId;
            theSalesId = this.getSalesId();
            strategy.appendField(locator, this, "salesId", buffer, theSalesId);
        }
        {
            String theETransactionId;
            theETransactionId = this.getETransactionId();
            strategy.appendField(locator, this, "eTransactionId", buffer, theETransactionId);
        }
        {
            OperationHeader.AssociatedOriginalSales theAssociatedOriginalSales;
            theAssociatedOriginalSales = this.getAssociatedOriginalSales();
            strategy.appendField(locator, this, "associatedOriginalSales", buffer, theAssociatedOriginalSales);
        }
        {
            ChannelPartnerUserProfileType theUserProfile;
            theUserProfile = this.getUserProfile();
            strategy.appendField(locator, this, "userProfile", buffer, theUserProfile);
        }
        {
            AgentProfile theAgentProfile;
            theAgentProfile = this.getAgentProfile();
            strategy.appendField(locator, this, "agentProfile", buffer, theAgentProfile);
        }
        {
            String theSalesPersonRoleCode;
            theSalesPersonRoleCode = this.getSalesPersonRoleCode();
            strategy.appendField(locator, this, "salesPersonRoleCode", buffer, theSalesPersonRoleCode);
        }
        {
            String theBrandCode;
            theBrandCode = this.getBrandCode();
            strategy.appendField(locator, this, "brandCode", buffer, theBrandCode);
        }
        {
            List<OperationHeader.SystemIntegrationParameterList> theSystemIntegrationParameterList;
            theSystemIntegrationParameterList = (((this.systemIntegrationParameterList!= null)&&(!this.systemIntegrationParameterList.isEmpty()))?this.getSystemIntegrationParameterList():null);
            strategy.appendField(locator, this, "systemIntegrationParameterList", buffer, theSystemIntegrationParameterList);
        }
        {
            Boolean theManagerOverrideIndicator;
            theManagerOverrideIndicator = this.isManagerOverrideIndicator();
            strategy.appendField(locator, this, "managerOverrideIndicator", buffer, theManagerOverrideIndicator);
        }
        {
            AgentProfile theAuthorizingUserProfile;
            theAuthorizingUserProfile = this.getAuthorizingUserProfile();
            strategy.appendField(locator, this, "authorizingUserProfile", buffer, theAuthorizingUserProfile);
        }
        {
            Boolean theSalesRecommendationIndicator;
            theSalesRecommendationIndicator = this.isSalesRecommendationIndicator();
            strategy.appendField(locator, this, "salesRecommendationIndicator", buffer, theSalesRecommendationIndicator);
        }
        {
            Boolean theRefreshInd;
            theRefreshInd = this.isRefreshInd();
            strategy.appendField(locator, this, "refreshInd", buffer, theRefreshInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OperationHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OperationHeader that = ((OperationHeader) object);
        {
            String lhsSalesType;
            lhsSalesType = this.getSalesType();
            String rhsSalesType;
            rhsSalesType = that.getSalesType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesType", lhsSalesType), LocatorUtils.property(thatLocator, "salesType", rhsSalesType), lhsSalesType, rhsSalesType)) {
                return false;
            }
        }
        {
            String lhsOrderType;
            lhsOrderType = this.getOrderType();
            String rhsOrderType;
            rhsOrderType = that.getOrderType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderType", lhsOrderType), LocatorUtils.property(thatLocator, "orderType", rhsOrderType), lhsOrderType, rhsOrderType)) {
                return false;
            }
        }
        {
            String lhsOrderSubType;
            lhsOrderSubType = this.getOrderSubType();
            String rhsOrderSubType;
            rhsOrderSubType = that.getOrderSubType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "orderSubType", lhsOrderSubType), LocatorUtils.property(thatLocator, "orderSubType", rhsOrderSubType), lhsOrderSubType, rhsOrderSubType)) {
                return false;
            }
        }
        {
            Long lhsOriginatorApplicationId;
            lhsOriginatorApplicationId = this.getOriginatorApplicationId();
            Long rhsOriginatorApplicationId;
            rhsOriginatorApplicationId = that.getOriginatorApplicationId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "originatorApplicationId", lhsOriginatorApplicationId), LocatorUtils.property(thatLocator, "originatorApplicationId", rhsOriginatorApplicationId), lhsOriginatorApplicationId, rhsOriginatorApplicationId)) {
                return false;
            }
        }
        {
            String lhsSalesTransactionId;
            lhsSalesTransactionId = this.getSalesTransactionId();
            String rhsSalesTransactionId;
            rhsSalesTransactionId = that.getSalesTransactionId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesTransactionId", lhsSalesTransactionId), LocatorUtils.property(thatLocator, "salesTransactionId", rhsSalesTransactionId), lhsSalesTransactionId, rhsSalesTransactionId)) {
                return false;
            }
        }
        {
            Date lhsSalesTransactionTimestamp;
            lhsSalesTransactionTimestamp = this.getSalesTransactionTimestamp();
            Date rhsSalesTransactionTimestamp;
            rhsSalesTransactionTimestamp = that.getSalesTransactionTimestamp();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesTransactionTimestamp", lhsSalesTransactionTimestamp), LocatorUtils.property(thatLocator, "salesTransactionTimestamp", rhsSalesTransactionTimestamp), lhsSalesTransactionTimestamp, rhsSalesTransactionTimestamp)) {
                return false;
            }
        }
        {
            String lhsSalesId;
            lhsSalesId = this.getSalesId();
            String rhsSalesId;
            rhsSalesId = that.getSalesId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesId", lhsSalesId), LocatorUtils.property(thatLocator, "salesId", rhsSalesId), lhsSalesId, rhsSalesId)) {
                return false;
            }
        }
        {
            String lhsETransactionId;
            lhsETransactionId = this.getETransactionId();
            String rhsETransactionId;
            rhsETransactionId = that.getETransactionId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eTransactionId", lhsETransactionId), LocatorUtils.property(thatLocator, "eTransactionId", rhsETransactionId), lhsETransactionId, rhsETransactionId)) {
                return false;
            }
        }
        {
            OperationHeader.AssociatedOriginalSales lhsAssociatedOriginalSales;
            lhsAssociatedOriginalSales = this.getAssociatedOriginalSales();
            OperationHeader.AssociatedOriginalSales rhsAssociatedOriginalSales;
            rhsAssociatedOriginalSales = that.getAssociatedOriginalSales();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedOriginalSales", lhsAssociatedOriginalSales), LocatorUtils.property(thatLocator, "associatedOriginalSales", rhsAssociatedOriginalSales), lhsAssociatedOriginalSales, rhsAssociatedOriginalSales)) {
                return false;
            }
        }
        {
            ChannelPartnerUserProfileType lhsUserProfile;
            lhsUserProfile = this.getUserProfile();
            ChannelPartnerUserProfileType rhsUserProfile;
            rhsUserProfile = that.getUserProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userProfile", lhsUserProfile), LocatorUtils.property(thatLocator, "userProfile", rhsUserProfile), lhsUserProfile, rhsUserProfile)) {
                return false;
            }
        }
        {
            AgentProfile lhsAgentProfile;
            lhsAgentProfile = this.getAgentProfile();
            AgentProfile rhsAgentProfile;
            rhsAgentProfile = that.getAgentProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "agentProfile", lhsAgentProfile), LocatorUtils.property(thatLocator, "agentProfile", rhsAgentProfile), lhsAgentProfile, rhsAgentProfile)) {
                return false;
            }
        }
        {
            String lhsSalesPersonRoleCode;
            lhsSalesPersonRoleCode = this.getSalesPersonRoleCode();
            String rhsSalesPersonRoleCode;
            rhsSalesPersonRoleCode = that.getSalesPersonRoleCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesPersonRoleCode", lhsSalesPersonRoleCode), LocatorUtils.property(thatLocator, "salesPersonRoleCode", rhsSalesPersonRoleCode), lhsSalesPersonRoleCode, rhsSalesPersonRoleCode)) {
                return false;
            }
        }
        {
            String lhsBrandCode;
            lhsBrandCode = this.getBrandCode();
            String rhsBrandCode;
            rhsBrandCode = that.getBrandCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "brandCode", lhsBrandCode), LocatorUtils.property(thatLocator, "brandCode", rhsBrandCode), lhsBrandCode, rhsBrandCode)) {
                return false;
            }
        }
        {
            List<OperationHeader.SystemIntegrationParameterList> lhsSystemIntegrationParameterList;
            lhsSystemIntegrationParameterList = (((this.systemIntegrationParameterList!= null)&&(!this.systemIntegrationParameterList.isEmpty()))?this.getSystemIntegrationParameterList():null);
            List<OperationHeader.SystemIntegrationParameterList> rhsSystemIntegrationParameterList;
            rhsSystemIntegrationParameterList = (((that.systemIntegrationParameterList!= null)&&(!that.systemIntegrationParameterList.isEmpty()))?that.getSystemIntegrationParameterList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "systemIntegrationParameterList", lhsSystemIntegrationParameterList), LocatorUtils.property(thatLocator, "systemIntegrationParameterList", rhsSystemIntegrationParameterList), lhsSystemIntegrationParameterList, rhsSystemIntegrationParameterList)) {
                return false;
            }
        }
        {
            Boolean lhsManagerOverrideIndicator;
            lhsManagerOverrideIndicator = this.isManagerOverrideIndicator();
            Boolean rhsManagerOverrideIndicator;
            rhsManagerOverrideIndicator = that.isManagerOverrideIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "managerOverrideIndicator", lhsManagerOverrideIndicator), LocatorUtils.property(thatLocator, "managerOverrideIndicator", rhsManagerOverrideIndicator), lhsManagerOverrideIndicator, rhsManagerOverrideIndicator)) {
                return false;
            }
        }
        {
            AgentProfile lhsAuthorizingUserProfile;
            lhsAuthorizingUserProfile = this.getAuthorizingUserProfile();
            AgentProfile rhsAuthorizingUserProfile;
            rhsAuthorizingUserProfile = that.getAuthorizingUserProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorizingUserProfile", lhsAuthorizingUserProfile), LocatorUtils.property(thatLocator, "authorizingUserProfile", rhsAuthorizingUserProfile), lhsAuthorizingUserProfile, rhsAuthorizingUserProfile)) {
                return false;
            }
        }
        {
            Boolean lhsSalesRecommendationIndicator;
            lhsSalesRecommendationIndicator = this.isSalesRecommendationIndicator();
            Boolean rhsSalesRecommendationIndicator;
            rhsSalesRecommendationIndicator = that.isSalesRecommendationIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesRecommendationIndicator", lhsSalesRecommendationIndicator), LocatorUtils.property(thatLocator, "salesRecommendationIndicator", rhsSalesRecommendationIndicator), lhsSalesRecommendationIndicator, rhsSalesRecommendationIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsRefreshInd;
            lhsRefreshInd = this.isRefreshInd();
            Boolean rhsRefreshInd;
            rhsRefreshInd = that.isRefreshInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refreshInd", lhsRefreshInd), LocatorUtils.property(thatLocator, "refreshInd", rhsRefreshInd), lhsRefreshInd, rhsRefreshInd)) {
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
     *         &lt;element name="salesId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="eTransactionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "salesId",
        "eTransactionId"
    })
    public static class AssociatedOriginalSales
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String salesId;
        protected String eTransactionId;

        /**
         * Gets the value of the salesId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSalesId() {
            return salesId;
        }

        /**
         * Sets the value of the salesId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSalesId(String value) {
            this.salesId = value;
        }

        /**
         * Gets the value of the eTransactionId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getETransactionId() {
            return eTransactionId;
        }

        /**
         * Sets the value of the eTransactionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setETransactionId(String value) {
            this.eTransactionId = value;
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
                String theSalesId;
                theSalesId = this.getSalesId();
                strategy.appendField(locator, this, "salesId", buffer, theSalesId);
            }
            {
                String theETransactionId;
                theETransactionId = this.getETransactionId();
                strategy.appendField(locator, this, "eTransactionId", buffer, theETransactionId);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof OperationHeader.AssociatedOriginalSales)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final OperationHeader.AssociatedOriginalSales that = ((OperationHeader.AssociatedOriginalSales) object);
            {
                String lhsSalesId;
                lhsSalesId = this.getSalesId();
                String rhsSalesId;
                rhsSalesId = that.getSalesId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "salesId", lhsSalesId), LocatorUtils.property(thatLocator, "salesId", rhsSalesId), lhsSalesId, rhsSalesId)) {
                    return false;
                }
            }
            {
                String lhsETransactionId;
                lhsETransactionId = this.getETransactionId();
                String rhsETransactionId;
                rhsETransactionId = that.getETransactionId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "eTransactionId", lhsETransactionId), LocatorUtils.property(thatLocator, "eTransactionId", rhsETransactionId), lhsETransactionId, rhsETransactionId)) {
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
     *         &lt;element name="parameterName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parameterValue" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "parameterName",
        "parameterValue"
    })
    public static class SystemIntegrationParameterList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String parameterName;
        @XmlElement(required = true)
        protected String parameterValue;

        /**
         * Gets the value of the parameterName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameterName() {
            return parameterName;
        }

        /**
         * Sets the value of the parameterName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameterName(String value) {
            this.parameterName = value;
        }

        /**
         * Gets the value of the parameterValue property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameterValue() {
            return parameterValue;
        }

        /**
         * Sets the value of the parameterValue property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameterValue(String value) {
            this.parameterValue = value;
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
                String theParameterName;
                theParameterName = this.getParameterName();
                strategy.appendField(locator, this, "parameterName", buffer, theParameterName);
            }
            {
                String theParameterValue;
                theParameterValue = this.getParameterValue();
                strategy.appendField(locator, this, "parameterValue", buffer, theParameterValue);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof OperationHeader.SystemIntegrationParameterList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final OperationHeader.SystemIntegrationParameterList that = ((OperationHeader.SystemIntegrationParameterList) object);
            {
                String lhsParameterName;
                lhsParameterName = this.getParameterName();
                String rhsParameterName;
                rhsParameterName = that.getParameterName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "parameterName", lhsParameterName), LocatorUtils.property(thatLocator, "parameterName", rhsParameterName), lhsParameterName, rhsParameterName)) {
                    return false;
                }
            }
            {
                String lhsParameterValue;
                lhsParameterValue = this.getParameterValue();
                String rhsParameterValue;
                rhsParameterValue = that.getParameterValue();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "parameterValue", lhsParameterValue), LocatorUtils.property(thatLocator, "parameterValue", rhsParameterValue), lhsParameterValue, rhsParameterValue)) {
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
