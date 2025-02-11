
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
 * Summarized version of the 'OfferProduct' type.
 * 
 * <p>Java class for OfferProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wirelessOfferProductSummary" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessOfferProductSummary" minOccurs="0"/>
 *         &lt;element name="wirelineOfferProductSummaryList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelineOfferProductSummary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accessoryOfferProductSummary" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryOfferProductSummary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferProductSummary", propOrder = {
    "wirelessOfferProductSummary",
    "wirelineOfferProductSummaryList",
    "accessoryOfferProductSummary"
})
public class OfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelessOfferProductSummary wirelessOfferProductSummary;
    protected List<WirelineOfferProductSummary> wirelineOfferProductSummaryList;
    protected AccessoryOfferProductSummary accessoryOfferProductSummary;

    /**
     * Gets the value of the wirelessOfferProductSummary property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessOfferProductSummary }
     *     
     */
    public WirelessOfferProductSummary getWirelessOfferProductSummary() {
        return wirelessOfferProductSummary;
    }

    /**
     * Sets the value of the wirelessOfferProductSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessOfferProductSummary }
     *     
     */
    public void setWirelessOfferProductSummary(WirelessOfferProductSummary value) {
        this.wirelessOfferProductSummary = value;
    }

    /**
     * Gets the value of the wirelineOfferProductSummaryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelineOfferProductSummaryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelineOfferProductSummaryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineOfferProductSummary }
     * 
     * 
     */
    public List<WirelineOfferProductSummary> getWirelineOfferProductSummaryList() {
        if (wirelineOfferProductSummaryList == null) {
            wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
        }
        return this.wirelineOfferProductSummaryList;
    }

    /**
     * Gets the value of the accessoryOfferProductSummary property.
     * 
     * @return
     *     possible object is
     *     {@link AccessoryOfferProductSummary }
     *     
     */
    public AccessoryOfferProductSummary getAccessoryOfferProductSummary() {
        return accessoryOfferProductSummary;
    }

    /**
     * Sets the value of the accessoryOfferProductSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessoryOfferProductSummary }
     *     
     */
    public void setAccessoryOfferProductSummary(AccessoryOfferProductSummary value) {
        this.accessoryOfferProductSummary = value;
    }

    /**
     * Sets the value of the wirelineOfferProductSummaryList property.
     * 
     * @param wirelineOfferProductSummaryList
     *     allowed object is
     *     {@link WirelineOfferProductSummary }
     *     
     */
    public void setWirelineOfferProductSummaryList(List<WirelineOfferProductSummary> wirelineOfferProductSummaryList) {
        this.wirelineOfferProductSummaryList = wirelineOfferProductSummaryList;
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
            WirelessOfferProductSummary theWirelessOfferProductSummary;
            theWirelessOfferProductSummary = this.getWirelessOfferProductSummary();
            strategy.appendField(locator, this, "wirelessOfferProductSummary", buffer, theWirelessOfferProductSummary);
        }
        {
            List<WirelineOfferProductSummary> theWirelineOfferProductSummaryList;
            theWirelineOfferProductSummaryList = (((this.wirelineOfferProductSummaryList!= null)&&(!this.wirelineOfferProductSummaryList.isEmpty()))?this.getWirelineOfferProductSummaryList():null);
            strategy.appendField(locator, this, "wirelineOfferProductSummaryList", buffer, theWirelineOfferProductSummaryList);
        }
        {
            AccessoryOfferProductSummary theAccessoryOfferProductSummary;
            theAccessoryOfferProductSummary = this.getAccessoryOfferProductSummary();
            strategy.appendField(locator, this, "accessoryOfferProductSummary", buffer, theAccessoryOfferProductSummary);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OfferProductSummary that = ((OfferProductSummary) object);
        {
            WirelessOfferProductSummary lhsWirelessOfferProductSummary;
            lhsWirelessOfferProductSummary = this.getWirelessOfferProductSummary();
            WirelessOfferProductSummary rhsWirelessOfferProductSummary;
            rhsWirelessOfferProductSummary = that.getWirelessOfferProductSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessOfferProductSummary", lhsWirelessOfferProductSummary), LocatorUtils.property(thatLocator, "wirelessOfferProductSummary", rhsWirelessOfferProductSummary), lhsWirelessOfferProductSummary, rhsWirelessOfferProductSummary)) {
                return false;
            }
        }
        {
            List<WirelineOfferProductSummary> lhsWirelineOfferProductSummaryList;
            lhsWirelineOfferProductSummaryList = (((this.wirelineOfferProductSummaryList!= null)&&(!this.wirelineOfferProductSummaryList.isEmpty()))?this.getWirelineOfferProductSummaryList():null);
            List<WirelineOfferProductSummary> rhsWirelineOfferProductSummaryList;
            rhsWirelineOfferProductSummaryList = (((that.wirelineOfferProductSummaryList!= null)&&(!that.wirelineOfferProductSummaryList.isEmpty()))?that.getWirelineOfferProductSummaryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineOfferProductSummaryList", lhsWirelineOfferProductSummaryList), LocatorUtils.property(thatLocator, "wirelineOfferProductSummaryList", rhsWirelineOfferProductSummaryList), lhsWirelineOfferProductSummaryList, rhsWirelineOfferProductSummaryList)) {
                return false;
            }
        }
        {
            AccessoryOfferProductSummary lhsAccessoryOfferProductSummary;
            lhsAccessoryOfferProductSummary = this.getAccessoryOfferProductSummary();
            AccessoryOfferProductSummary rhsAccessoryOfferProductSummary;
            rhsAccessoryOfferProductSummary = that.getAccessoryOfferProductSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accessoryOfferProductSummary", lhsAccessoryOfferProductSummary), LocatorUtils.property(thatLocator, "accessoryOfferProductSummary", rhsAccessoryOfferProductSummary), lhsAccessoryOfferProductSummary, rhsAccessoryOfferProductSummary)) {
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
