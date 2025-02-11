
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for WirelessServiceSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessServiceSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enforcementId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="restrictionId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="serviceCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serviceGroupCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessServiceSet", propOrder = {
    "enforcementId",
    "restrictionId",
    "serviceCodeList",
    "serviceGroupCodeList"
})
public class WirelessServiceSet
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected int enforcementId;
    protected Integer restrictionId;
    protected List<String> serviceCodeList;
    protected List<String> serviceGroupCodeList;

    /**
     * Gets the value of the enforcementId property.
     * 
     */
    public int getEnforcementId() {
        return enforcementId;
    }

    /**
     * Sets the value of the enforcementId property.
     * 
     */
    public void setEnforcementId(int value) {
        this.enforcementId = value;
    }

    /**
     * Gets the value of the restrictionId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getRestrictionId() {
        return restrictionId;
    }

    /**
     * Sets the value of the restrictionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setRestrictionId(Integer value) {
        this.restrictionId = value;
    }

    /**
     * Gets the value of the serviceCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServiceCodeList() {
        if (serviceCodeList == null) {
            serviceCodeList = new ArrayList<String>();
        }
        return this.serviceCodeList;
    }

    /**
     * Gets the value of the serviceGroupCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceGroupCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceGroupCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServiceGroupCodeList() {
        if (serviceGroupCodeList == null) {
            serviceGroupCodeList = new ArrayList<String>();
        }
        return this.serviceGroupCodeList;
    }

    /**
     * Sets the value of the serviceCodeList property.
     * 
     * @param serviceCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCodeList(List<String> serviceCodeList) {
        this.serviceCodeList = serviceCodeList;
    }

    /**
     * Sets the value of the serviceGroupCodeList property.
     * 
     * @param serviceGroupCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceGroupCodeList(List<String> serviceGroupCodeList) {
        this.serviceGroupCodeList = serviceGroupCodeList;
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
            int theEnforcementId;
            theEnforcementId = (true?this.getEnforcementId(): 0);
            strategy.appendField(locator, this, "enforcementId", buffer, theEnforcementId);
        }
        {
            Integer theRestrictionId;
            theRestrictionId = this.getRestrictionId();
            strategy.appendField(locator, this, "restrictionId", buffer, theRestrictionId);
        }
        {
            List<String> theServiceCodeList;
            theServiceCodeList = (((this.serviceCodeList!= null)&&(!this.serviceCodeList.isEmpty()))?this.getServiceCodeList():null);
            strategy.appendField(locator, this, "serviceCodeList", buffer, theServiceCodeList);
        }
        {
            List<String> theServiceGroupCodeList;
            theServiceGroupCodeList = (((this.serviceGroupCodeList!= null)&&(!this.serviceGroupCodeList.isEmpty()))?this.getServiceGroupCodeList():null);
            strategy.appendField(locator, this, "serviceGroupCodeList", buffer, theServiceGroupCodeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessServiceSet)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessServiceSet that = ((WirelessServiceSet) object);
        {
            int lhsEnforcementId;
            lhsEnforcementId = (true?this.getEnforcementId(): 0);
            int rhsEnforcementId;
            rhsEnforcementId = (true?that.getEnforcementId(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "enforcementId", lhsEnforcementId), LocatorUtils.property(thatLocator, "enforcementId", rhsEnforcementId), lhsEnforcementId, rhsEnforcementId)) {
                return false;
            }
        }
        {
            Integer lhsRestrictionId;
            lhsRestrictionId = this.getRestrictionId();
            Integer rhsRestrictionId;
            rhsRestrictionId = that.getRestrictionId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "restrictionId", lhsRestrictionId), LocatorUtils.property(thatLocator, "restrictionId", rhsRestrictionId), lhsRestrictionId, rhsRestrictionId)) {
                return false;
            }
        }
        {
            List<String> lhsServiceCodeList;
            lhsServiceCodeList = (((this.serviceCodeList!= null)&&(!this.serviceCodeList.isEmpty()))?this.getServiceCodeList():null);
            List<String> rhsServiceCodeList;
            rhsServiceCodeList = (((that.serviceCodeList!= null)&&(!that.serviceCodeList.isEmpty()))?that.getServiceCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCodeList", lhsServiceCodeList), LocatorUtils.property(thatLocator, "serviceCodeList", rhsServiceCodeList), lhsServiceCodeList, rhsServiceCodeList)) {
                return false;
            }
        }
        {
            List<String> lhsServiceGroupCodeList;
            lhsServiceGroupCodeList = (((this.serviceGroupCodeList!= null)&&(!this.serviceGroupCodeList.isEmpty()))?this.getServiceGroupCodeList():null);
            List<String> rhsServiceGroupCodeList;
            rhsServiceGroupCodeList = (((that.serviceGroupCodeList!= null)&&(!that.serviceGroupCodeList.isEmpty()))?that.getServiceGroupCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceGroupCodeList", lhsServiceGroupCodeList), LocatorUtils.property(thatLocator, "serviceGroupCodeList", rhsServiceGroupCodeList), lhsServiceGroupCodeList, rhsServiceGroupCodeList)) {
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
