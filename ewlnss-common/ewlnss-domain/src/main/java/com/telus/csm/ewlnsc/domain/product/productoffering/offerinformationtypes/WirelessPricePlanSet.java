
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
 * <p>Java class for WirelessPricePlanSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessPricePlanSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="enforcementId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="pricePlanCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="pricePlanGroupCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessPricePlanSet", propOrder = {
    "enforcementId",
    "pricePlanCodeList",
    "pricePlanGroupCodeList"
})
public class WirelessPricePlanSet
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected int enforcementId;
    protected List<String> pricePlanCodeList;
    protected List<String> pricePlanGroupCodeList;

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
     * Gets the value of the pricePlanCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPricePlanCodeList() {
        if (pricePlanCodeList == null) {
            pricePlanCodeList = new ArrayList<String>();
        }
        return this.pricePlanCodeList;
    }

    /**
     * Gets the value of the pricePlanGroupCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanGroupCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanGroupCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPricePlanGroupCodeList() {
        if (pricePlanGroupCodeList == null) {
            pricePlanGroupCodeList = new ArrayList<String>();
        }
        return this.pricePlanGroupCodeList;
    }

    /**
     * Sets the value of the pricePlanCodeList property.
     * 
     * @param pricePlanCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanCodeList(List<String> pricePlanCodeList) {
        this.pricePlanCodeList = pricePlanCodeList;
    }

    /**
     * Sets the value of the pricePlanGroupCodeList property.
     * 
     * @param pricePlanGroupCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanGroupCodeList(List<String> pricePlanGroupCodeList) {
        this.pricePlanGroupCodeList = pricePlanGroupCodeList;
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
            List<String> thePricePlanCodeList;
            thePricePlanCodeList = (((this.pricePlanCodeList!= null)&&(!this.pricePlanCodeList.isEmpty()))?this.getPricePlanCodeList():null);
            strategy.appendField(locator, this, "pricePlanCodeList", buffer, thePricePlanCodeList);
        }
        {
            List<String> thePricePlanGroupCodeList;
            thePricePlanGroupCodeList = (((this.pricePlanGroupCodeList!= null)&&(!this.pricePlanGroupCodeList.isEmpty()))?this.getPricePlanGroupCodeList():null);
            strategy.appendField(locator, this, "pricePlanGroupCodeList", buffer, thePricePlanGroupCodeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessPricePlanSet)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessPricePlanSet that = ((WirelessPricePlanSet) object);
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
            List<String> lhsPricePlanCodeList;
            lhsPricePlanCodeList = (((this.pricePlanCodeList!= null)&&(!this.pricePlanCodeList.isEmpty()))?this.getPricePlanCodeList():null);
            List<String> rhsPricePlanCodeList;
            rhsPricePlanCodeList = (((that.pricePlanCodeList!= null)&&(!that.pricePlanCodeList.isEmpty()))?that.getPricePlanCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCodeList", lhsPricePlanCodeList), LocatorUtils.property(thatLocator, "pricePlanCodeList", rhsPricePlanCodeList), lhsPricePlanCodeList, rhsPricePlanCodeList)) {
                return false;
            }
        }
        {
            List<String> lhsPricePlanGroupCodeList;
            lhsPricePlanGroupCodeList = (((this.pricePlanGroupCodeList!= null)&&(!this.pricePlanGroupCodeList.isEmpty()))?this.getPricePlanGroupCodeList():null);
            List<String> rhsPricePlanGroupCodeList;
            rhsPricePlanGroupCodeList = (((that.pricePlanGroupCodeList!= null)&&(!that.pricePlanGroupCodeList.isEmpty()))?that.getPricePlanGroupCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanGroupCodeList", lhsPricePlanGroupCodeList), LocatorUtils.property(thatLocator, "pricePlanGroupCodeList", rhsPricePlanGroupCodeList), lhsPricePlanGroupCodeList, rhsPricePlanGroupCodeList)) {
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
