
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
 * <p>Java class for AgentProfile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AgentProfile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="channelOrganizationTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelOrganizationInternalId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelOrganizationNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="employeeId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="loginId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="portalRoleId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="knowbilityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userPrivilegeRoleCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AgentProfile", propOrder = {
    "channelOrganizationTypeCd",
    "channelOrganizationInternalId",
    "channelOrganizationNum",
    "employeeId",
    "loginId",
    "portalRoleId",
    "knowbilityId",
    "userPrivilegeRoleCodeList"
})
public class AgentProfile
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String channelOrganizationTypeCd;
    @XmlElement(required = true)
    protected String channelOrganizationInternalId;
    @XmlElement(required = true)
    protected String channelOrganizationNum;
    @XmlElement(required = true)
    protected String employeeId;
    @XmlElement(required = true)
    protected String loginId;
    protected String portalRoleId;
    @XmlElement(required = true)
    protected String knowbilityId;
    protected List<String> userPrivilegeRoleCodeList;

    /**
     * Gets the value of the channelOrganizationTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelOrganizationTypeCd() {
        return channelOrganizationTypeCd;
    }

    /**
     * Sets the value of the channelOrganizationTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelOrganizationTypeCd(String value) {
        this.channelOrganizationTypeCd = value;
    }

    /**
     * Gets the value of the channelOrganizationInternalId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelOrganizationInternalId() {
        return channelOrganizationInternalId;
    }

    /**
     * Sets the value of the channelOrganizationInternalId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelOrganizationInternalId(String value) {
        this.channelOrganizationInternalId = value;
    }

    /**
     * Gets the value of the channelOrganizationNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelOrganizationNum() {
        return channelOrganizationNum;
    }

    /**
     * Sets the value of the channelOrganizationNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelOrganizationNum(String value) {
        this.channelOrganizationNum = value;
    }

    /**
     * Gets the value of the employeeId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets the value of the employeeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmployeeId(String value) {
        this.employeeId = value;
    }

    /**
     * Gets the value of the loginId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLoginId() {
        return loginId;
    }

    /**
     * Sets the value of the loginId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLoginId(String value) {
        this.loginId = value;
    }

    /**
     * Gets the value of the portalRoleId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortalRoleId() {
        return portalRoleId;
    }

    /**
     * Sets the value of the portalRoleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortalRoleId(String value) {
        this.portalRoleId = value;
    }

    /**
     * Gets the value of the knowbilityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKnowbilityId() {
        return knowbilityId;
    }

    /**
     * Sets the value of the knowbilityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKnowbilityId(String value) {
        this.knowbilityId = value;
    }

    /**
     * Gets the value of the userPrivilegeRoleCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the userPrivilegeRoleCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUserPrivilegeRoleCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getUserPrivilegeRoleCodeList() {
        if (userPrivilegeRoleCodeList == null) {
            userPrivilegeRoleCodeList = new ArrayList<String>();
        }
        return this.userPrivilegeRoleCodeList;
    }

    /**
     * Sets the value of the userPrivilegeRoleCodeList property.
     * 
     * @param userPrivilegeRoleCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserPrivilegeRoleCodeList(List<String> userPrivilegeRoleCodeList) {
        this.userPrivilegeRoleCodeList = userPrivilegeRoleCodeList;
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
            String theChannelOrganizationTypeCd;
            theChannelOrganizationTypeCd = this.getChannelOrganizationTypeCd();
            strategy.appendField(locator, this, "channelOrganizationTypeCd", buffer, theChannelOrganizationTypeCd);
        }
        {
            String theChannelOrganizationInternalId;
            theChannelOrganizationInternalId = this.getChannelOrganizationInternalId();
            strategy.appendField(locator, this, "channelOrganizationInternalId", buffer, theChannelOrganizationInternalId);
        }
        {
            String theChannelOrganizationNum;
            theChannelOrganizationNum = this.getChannelOrganizationNum();
            strategy.appendField(locator, this, "channelOrganizationNum", buffer, theChannelOrganizationNum);
        }
        {
            String theEmployeeId;
            theEmployeeId = this.getEmployeeId();
            strategy.appendField(locator, this, "employeeId", buffer, theEmployeeId);
        }
        {
            String theLoginId;
            theLoginId = this.getLoginId();
            strategy.appendField(locator, this, "loginId", buffer, theLoginId);
        }
        {
            String thePortalRoleId;
            thePortalRoleId = this.getPortalRoleId();
            strategy.appendField(locator, this, "portalRoleId", buffer, thePortalRoleId);
        }
        {
            String theKnowbilityId;
            theKnowbilityId = this.getKnowbilityId();
            strategy.appendField(locator, this, "knowbilityId", buffer, theKnowbilityId);
        }
        {
            List<String> theUserPrivilegeRoleCodeList;
            theUserPrivilegeRoleCodeList = (((this.userPrivilegeRoleCodeList!= null)&&(!this.userPrivilegeRoleCodeList.isEmpty()))?this.getUserPrivilegeRoleCodeList():null);
            strategy.appendField(locator, this, "userPrivilegeRoleCodeList", buffer, theUserPrivilegeRoleCodeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AgentProfile)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AgentProfile that = ((AgentProfile) object);
        {
            String lhsChannelOrganizationTypeCd;
            lhsChannelOrganizationTypeCd = this.getChannelOrganizationTypeCd();
            String rhsChannelOrganizationTypeCd;
            rhsChannelOrganizationTypeCd = that.getChannelOrganizationTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "channelOrganizationTypeCd", lhsChannelOrganizationTypeCd), LocatorUtils.property(thatLocator, "channelOrganizationTypeCd", rhsChannelOrganizationTypeCd), lhsChannelOrganizationTypeCd, rhsChannelOrganizationTypeCd)) {
                return false;
            }
        }
        {
            String lhsChannelOrganizationInternalId;
            lhsChannelOrganizationInternalId = this.getChannelOrganizationInternalId();
            String rhsChannelOrganizationInternalId;
            rhsChannelOrganizationInternalId = that.getChannelOrganizationInternalId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "channelOrganizationInternalId", lhsChannelOrganizationInternalId), LocatorUtils.property(thatLocator, "channelOrganizationInternalId", rhsChannelOrganizationInternalId), lhsChannelOrganizationInternalId, rhsChannelOrganizationInternalId)) {
                return false;
            }
        }
        {
            String lhsChannelOrganizationNum;
            lhsChannelOrganizationNum = this.getChannelOrganizationNum();
            String rhsChannelOrganizationNum;
            rhsChannelOrganizationNum = that.getChannelOrganizationNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "channelOrganizationNum", lhsChannelOrganizationNum), LocatorUtils.property(thatLocator, "channelOrganizationNum", rhsChannelOrganizationNum), lhsChannelOrganizationNum, rhsChannelOrganizationNum)) {
                return false;
            }
        }
        {
            String lhsEmployeeId;
            lhsEmployeeId = this.getEmployeeId();
            String rhsEmployeeId;
            rhsEmployeeId = that.getEmployeeId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "employeeId", lhsEmployeeId), LocatorUtils.property(thatLocator, "employeeId", rhsEmployeeId), lhsEmployeeId, rhsEmployeeId)) {
                return false;
            }
        }
        {
            String lhsLoginId;
            lhsLoginId = this.getLoginId();
            String rhsLoginId;
            rhsLoginId = that.getLoginId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "loginId", lhsLoginId), LocatorUtils.property(thatLocator, "loginId", rhsLoginId), lhsLoginId, rhsLoginId)) {
                return false;
            }
        }
        {
            String lhsPortalRoleId;
            lhsPortalRoleId = this.getPortalRoleId();
            String rhsPortalRoleId;
            rhsPortalRoleId = that.getPortalRoleId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portalRoleId", lhsPortalRoleId), LocatorUtils.property(thatLocator, "portalRoleId", rhsPortalRoleId), lhsPortalRoleId, rhsPortalRoleId)) {
                return false;
            }
        }
        {
            String lhsKnowbilityId;
            lhsKnowbilityId = this.getKnowbilityId();
            String rhsKnowbilityId;
            rhsKnowbilityId = that.getKnowbilityId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "knowbilityId", lhsKnowbilityId), LocatorUtils.property(thatLocator, "knowbilityId", rhsKnowbilityId), lhsKnowbilityId, rhsKnowbilityId)) {
                return false;
            }
        }
        {
            List<String> lhsUserPrivilegeRoleCodeList;
            lhsUserPrivilegeRoleCodeList = (((this.userPrivilegeRoleCodeList!= null)&&(!this.userPrivilegeRoleCodeList.isEmpty()))?this.getUserPrivilegeRoleCodeList():null);
            List<String> rhsUserPrivilegeRoleCodeList;
            rhsUserPrivilegeRoleCodeList = (((that.userPrivilegeRoleCodeList!= null)&&(!that.userPrivilegeRoleCodeList.isEmpty()))?that.getUserPrivilegeRoleCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "userPrivilegeRoleCodeList", lhsUserPrivilegeRoleCodeList), LocatorUtils.property(thatLocator, "userPrivilegeRoleCodeList", rhsUserPrivilegeRoleCodeList), lhsUserPrivilegeRoleCodeList, rhsUserPrivilegeRoleCodeList)) {
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
