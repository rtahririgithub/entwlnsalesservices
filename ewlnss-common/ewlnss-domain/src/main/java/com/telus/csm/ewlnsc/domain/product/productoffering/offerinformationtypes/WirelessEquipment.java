
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
 * 
 *                 if allEquipmentInd = true then for any equipment user select, discount will always be allProductOfferDiscountAmt
 *                 else search the handset/SIM from offerEquipmentItemList and find the discount.
 *             
 * 
 * <p>Java class for WirelessEquipment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessEquipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="allEquipmentDiscountDeliveryType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountDeliveryType" minOccurs="0"/>
 *         &lt;element name="allEquipmentOfferDiscountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="allEquipmentDiscountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType" minOccurs="0"/>
 *         &lt;element name="allEquipmentInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="wirelessEquipmentItemList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessEquipmentItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessEquipment", propOrder = {
    "allEquipmentDiscountDeliveryType",
    "allEquipmentOfferDiscountAmt",
    "allEquipmentDiscountType",
    "allEquipmentInd",
    "wirelessEquipmentItemList"
})
public class WirelessEquipment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected DiscountDeliveryType allEquipmentDiscountDeliveryType;
    protected Double allEquipmentOfferDiscountAmt;
    protected DiscountType allEquipmentDiscountType;
    protected boolean allEquipmentInd;
    protected List<WirelessEquipmentItem> wirelessEquipmentItemList;

    /**
     * Gets the value of the allEquipmentDiscountDeliveryType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountDeliveryType }
     *     
     */
    public DiscountDeliveryType getAllEquipmentDiscountDeliveryType() {
        return allEquipmentDiscountDeliveryType;
    }

    /**
     * Sets the value of the allEquipmentDiscountDeliveryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountDeliveryType }
     *     
     */
    public void setAllEquipmentDiscountDeliveryType(DiscountDeliveryType value) {
        this.allEquipmentDiscountDeliveryType = value;
    }

    /**
     * Gets the value of the allEquipmentOfferDiscountAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAllEquipmentOfferDiscountAmt() {
        return allEquipmentOfferDiscountAmt;
    }

    /**
     * Sets the value of the allEquipmentOfferDiscountAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAllEquipmentOfferDiscountAmt(Double value) {
        this.allEquipmentOfferDiscountAmt = value;
    }

    /**
     * Gets the value of the allEquipmentDiscountType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getAllEquipmentDiscountType() {
        return allEquipmentDiscountType;
    }

    /**
     * Sets the value of the allEquipmentDiscountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setAllEquipmentDiscountType(DiscountType value) {
        this.allEquipmentDiscountType = value;
    }

    /**
     * Gets the value of the allEquipmentInd property.
     * 
     */
    public boolean isAllEquipmentInd() {
        return allEquipmentInd;
    }

    /**
     * Sets the value of the allEquipmentInd property.
     * 
     */
    public void setAllEquipmentInd(boolean value) {
        this.allEquipmentInd = value;
    }

    /**
     * Gets the value of the wirelessEquipmentItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelessEquipmentItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelessEquipmentItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelessEquipmentItem }
     * 
     * 
     */
    public List<WirelessEquipmentItem> getWirelessEquipmentItemList() {
        if (wirelessEquipmentItemList == null) {
            wirelessEquipmentItemList = new ArrayList<WirelessEquipmentItem>();
        }
        return this.wirelessEquipmentItemList;
    }

    /**
     * Sets the value of the wirelessEquipmentItemList property.
     * 
     * @param wirelessEquipmentItemList
     *     allowed object is
     *     {@link WirelessEquipmentItem }
     *     
     */
    public void setWirelessEquipmentItemList(List<WirelessEquipmentItem> wirelessEquipmentItemList) {
        this.wirelessEquipmentItemList = wirelessEquipmentItemList;
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
            DiscountDeliveryType theAllEquipmentDiscountDeliveryType;
            theAllEquipmentDiscountDeliveryType = this.getAllEquipmentDiscountDeliveryType();
            strategy.appendField(locator, this, "allEquipmentDiscountDeliveryType", buffer, theAllEquipmentDiscountDeliveryType);
        }
        {
            Double theAllEquipmentOfferDiscountAmt;
            theAllEquipmentOfferDiscountAmt = this.getAllEquipmentOfferDiscountAmt();
            strategy.appendField(locator, this, "allEquipmentOfferDiscountAmt", buffer, theAllEquipmentOfferDiscountAmt);
        }
        {
            DiscountType theAllEquipmentDiscountType;
            theAllEquipmentDiscountType = this.getAllEquipmentDiscountType();
            strategy.appendField(locator, this, "allEquipmentDiscountType", buffer, theAllEquipmentDiscountType);
        }
        {
            boolean theAllEquipmentInd;
            theAllEquipmentInd = (true?this.isAllEquipmentInd():false);
            strategy.appendField(locator, this, "allEquipmentInd", buffer, theAllEquipmentInd);
        }
        {
            List<WirelessEquipmentItem> theWirelessEquipmentItemList;
            theWirelessEquipmentItemList = (((this.wirelessEquipmentItemList!= null)&&(!this.wirelessEquipmentItemList.isEmpty()))?this.getWirelessEquipmentItemList():null);
            strategy.appendField(locator, this, "wirelessEquipmentItemList", buffer, theWirelessEquipmentItemList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessEquipment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessEquipment that = ((WirelessEquipment) object);
        {
            DiscountDeliveryType lhsAllEquipmentDiscountDeliveryType;
            lhsAllEquipmentDiscountDeliveryType = this.getAllEquipmentDiscountDeliveryType();
            DiscountDeliveryType rhsAllEquipmentDiscountDeliveryType;
            rhsAllEquipmentDiscountDeliveryType = that.getAllEquipmentDiscountDeliveryType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allEquipmentDiscountDeliveryType", lhsAllEquipmentDiscountDeliveryType), LocatorUtils.property(thatLocator, "allEquipmentDiscountDeliveryType", rhsAllEquipmentDiscountDeliveryType), lhsAllEquipmentDiscountDeliveryType, rhsAllEquipmentDiscountDeliveryType)) {
                return false;
            }
        }
        {
            Double lhsAllEquipmentOfferDiscountAmt;
            lhsAllEquipmentOfferDiscountAmt = this.getAllEquipmentOfferDiscountAmt();
            Double rhsAllEquipmentOfferDiscountAmt;
            rhsAllEquipmentOfferDiscountAmt = that.getAllEquipmentOfferDiscountAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allEquipmentOfferDiscountAmt", lhsAllEquipmentOfferDiscountAmt), LocatorUtils.property(thatLocator, "allEquipmentOfferDiscountAmt", rhsAllEquipmentOfferDiscountAmt), lhsAllEquipmentOfferDiscountAmt, rhsAllEquipmentOfferDiscountAmt)) {
                return false;
            }
        }
        {
            DiscountType lhsAllEquipmentDiscountType;
            lhsAllEquipmentDiscountType = this.getAllEquipmentDiscountType();
            DiscountType rhsAllEquipmentDiscountType;
            rhsAllEquipmentDiscountType = that.getAllEquipmentDiscountType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allEquipmentDiscountType", lhsAllEquipmentDiscountType), LocatorUtils.property(thatLocator, "allEquipmentDiscountType", rhsAllEquipmentDiscountType), lhsAllEquipmentDiscountType, rhsAllEquipmentDiscountType)) {
                return false;
            }
        }
        {
            boolean lhsAllEquipmentInd;
            lhsAllEquipmentInd = (true?this.isAllEquipmentInd():false);
            boolean rhsAllEquipmentInd;
            rhsAllEquipmentInd = (true?that.isAllEquipmentInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allEquipmentInd", lhsAllEquipmentInd), LocatorUtils.property(thatLocator, "allEquipmentInd", rhsAllEquipmentInd), lhsAllEquipmentInd, rhsAllEquipmentInd)) {
                return false;
            }
        }
        {
            List<WirelessEquipmentItem> lhsWirelessEquipmentItemList;
            lhsWirelessEquipmentItemList = (((this.wirelessEquipmentItemList!= null)&&(!this.wirelessEquipmentItemList.isEmpty()))?this.getWirelessEquipmentItemList():null);
            List<WirelessEquipmentItem> rhsWirelessEquipmentItemList;
            rhsWirelessEquipmentItemList = (((that.wirelessEquipmentItemList!= null)&&(!that.wirelessEquipmentItemList.isEmpty()))?that.getWirelessEquipmentItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessEquipmentItemList", lhsWirelessEquipmentItemList), LocatorUtils.property(thatLocator, "wirelessEquipmentItemList", rhsWirelessEquipmentItemList), lhsWirelessEquipmentItemList, rhsWirelessEquipmentItemList)) {
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
