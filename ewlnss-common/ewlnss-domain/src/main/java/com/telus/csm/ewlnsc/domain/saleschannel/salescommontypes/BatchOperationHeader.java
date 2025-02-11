
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
 * <p>Java class for BatchOperationHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BatchOperationHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="originatorApplicationId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="corporateEntity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateEntity"/>
 *         &lt;element name="billingAccountNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
 *                   &lt;element name="parameterValueTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="notificationEmailTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatchOperationHeader", propOrder = {
    "originatorApplicationId",
    "corporateEntity",
    "billingAccountNum",
    "userProfile",
    "agentProfile",
    "salesPersonRoleCode",
    "brandCode",
    "systemIntegrationParameterList",
    "notificationEmailTxt"
})
public class BatchOperationHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Long originatorApplicationId;
    @XmlElement(required = true)
    protected CorporateEntity corporateEntity;
    @XmlElement(required = true)
    protected String billingAccountNum;
    @XmlElement(required = true)
    protected ChannelPartnerUserProfileType userProfile;
    protected AgentProfile agentProfile;
    protected String salesPersonRoleCode;
    @XmlElement(required = true)
    protected String brandCode;
    protected List<BatchOperationHeader.SystemIntegrationParameterList> systemIntegrationParameterList;
    protected String notificationEmailTxt;

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
     * Gets the value of the corporateEntity property.
     * 
     * @return
     *     possible object is
     *     {@link CorporateEntity }
     *     
     */
    public CorporateEntity getCorporateEntity() {
        return corporateEntity;
    }

    /**
     * Sets the value of the corporateEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateEntity }
     *     
     */
    public void setCorporateEntity(CorporateEntity value) {
        this.corporateEntity = value;
    }

    /**
     * Gets the value of the billingAccountNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNum() {
        return billingAccountNum;
    }

    /**
     * Sets the value of the billingAccountNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNum(String value) {
        this.billingAccountNum = value;
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
     * {@link BatchOperationHeader.SystemIntegrationParameterList }
     * 
     * 
     */
    public List<BatchOperationHeader.SystemIntegrationParameterList> getSystemIntegrationParameterList() {
        if (systemIntegrationParameterList == null) {
            systemIntegrationParameterList = new ArrayList<BatchOperationHeader.SystemIntegrationParameterList>();
        }
        return this.systemIntegrationParameterList;
    }

    /**
     * Gets the value of the notificationEmailTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotificationEmailTxt() {
        return notificationEmailTxt;
    }

    /**
     * Sets the value of the notificationEmailTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotificationEmailTxt(String value) {
        this.notificationEmailTxt = value;
    }

    /**
     * Sets the value of the systemIntegrationParameterList property.
     * 
     * @param systemIntegrationParameterList
     *     allowed object is
     *     {@link BatchOperationHeader.SystemIntegrationParameterList }
     *     
     */
    public void setSystemIntegrationParameterList(List<BatchOperationHeader.SystemIntegrationParameterList> systemIntegrationParameterList) {
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
            Long theOriginatorApplicationId;
            theOriginatorApplicationId = this.getOriginatorApplicationId();
            strategy.appendField(locator, this, "originatorApplicationId", buffer, theOriginatorApplicationId);
        }
        {
            CorporateEntity theCorporateEntity;
            theCorporateEntity = this.getCorporateEntity();
            strategy.appendField(locator, this, "corporateEntity", buffer, theCorporateEntity);
        }
        {
            String theBillingAccountNum;
            theBillingAccountNum = this.getBillingAccountNum();
            strategy.appendField(locator, this, "billingAccountNum", buffer, theBillingAccountNum);
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
            List<BatchOperationHeader.SystemIntegrationParameterList> theSystemIntegrationParameterList;
            theSystemIntegrationParameterList = (((this.systemIntegrationParameterList!= null)&&(!this.systemIntegrationParameterList.isEmpty()))?this.getSystemIntegrationParameterList():null);
            strategy.appendField(locator, this, "systemIntegrationParameterList", buffer, theSystemIntegrationParameterList);
        }
        {
            String theNotificationEmailTxt;
            theNotificationEmailTxt = this.getNotificationEmailTxt();
            strategy.appendField(locator, this, "notificationEmailTxt", buffer, theNotificationEmailTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BatchOperationHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BatchOperationHeader that = ((BatchOperationHeader) object);
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
            CorporateEntity lhsCorporateEntity;
            lhsCorporateEntity = this.getCorporateEntity();
            CorporateEntity rhsCorporateEntity;
            rhsCorporateEntity = that.getCorporateEntity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "corporateEntity", lhsCorporateEntity), LocatorUtils.property(thatLocator, "corporateEntity", rhsCorporateEntity), lhsCorporateEntity, rhsCorporateEntity)) {
                return false;
            }
        }
        {
            String lhsBillingAccountNum;
            lhsBillingAccountNum = this.getBillingAccountNum();
            String rhsBillingAccountNum;
            rhsBillingAccountNum = that.getBillingAccountNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNum", lhsBillingAccountNum), LocatorUtils.property(thatLocator, "billingAccountNum", rhsBillingAccountNum), lhsBillingAccountNum, rhsBillingAccountNum)) {
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
            List<BatchOperationHeader.SystemIntegrationParameterList> lhsSystemIntegrationParameterList;
            lhsSystemIntegrationParameterList = (((this.systemIntegrationParameterList!= null)&&(!this.systemIntegrationParameterList.isEmpty()))?this.getSystemIntegrationParameterList():null);
            List<BatchOperationHeader.SystemIntegrationParameterList> rhsSystemIntegrationParameterList;
            rhsSystemIntegrationParameterList = (((that.systemIntegrationParameterList!= null)&&(!that.systemIntegrationParameterList.isEmpty()))?that.getSystemIntegrationParameterList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "systemIntegrationParameterList", lhsSystemIntegrationParameterList), LocatorUtils.property(thatLocator, "systemIntegrationParameterList", rhsSystemIntegrationParameterList), lhsSystemIntegrationParameterList, rhsSystemIntegrationParameterList)) {
                return false;
            }
        }
        {
            String lhsNotificationEmailTxt;
            lhsNotificationEmailTxt = this.getNotificationEmailTxt();
            String rhsNotificationEmailTxt;
            rhsNotificationEmailTxt = that.getNotificationEmailTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "notificationEmailTxt", lhsNotificationEmailTxt), LocatorUtils.property(thatLocator, "notificationEmailTxt", rhsNotificationEmailTxt), lhsNotificationEmailTxt, rhsNotificationEmailTxt)) {
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
     *         &lt;element name="parameterName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="parameterValueTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "parameterValueTxt"
    })
    public static class SystemIntegrationParameterList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String parameterName;
        @XmlElement(required = true)
        protected String parameterValueTxt;

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
         * Gets the value of the parameterValueTxt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameterValueTxt() {
            return parameterValueTxt;
        }

        /**
         * Sets the value of the parameterValueTxt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameterValueTxt(String value) {
            this.parameterValueTxt = value;
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
                String theParameterValueTxt;
                theParameterValueTxt = this.getParameterValueTxt();
                strategy.appendField(locator, this, "parameterValueTxt", buffer, theParameterValueTxt);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof BatchOperationHeader.SystemIntegrationParameterList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final BatchOperationHeader.SystemIntegrationParameterList that = ((BatchOperationHeader.SystemIntegrationParameterList) object);
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
                String lhsParameterValueTxt;
                lhsParameterValueTxt = this.getParameterValueTxt();
                String rhsParameterValueTxt;
                rhsParameterValueTxt = that.getParameterValueTxt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "parameterValueTxt", lhsParameterValueTxt), LocatorUtils.property(thatLocator, "parameterValueTxt", rhsParameterValueTxt), lhsParameterValueTxt, rhsParameterValueTxt)) {
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
