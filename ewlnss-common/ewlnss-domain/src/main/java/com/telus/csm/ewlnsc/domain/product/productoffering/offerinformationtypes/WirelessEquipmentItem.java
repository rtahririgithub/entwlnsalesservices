
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
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
 * <p>Java class for WirelessEquipmentItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessEquipmentItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipment" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}EquipmentProduct"/>
 *         &lt;element name="discountDeliveryType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountDeliveryType"/>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType"/>
 *         &lt;element name="discountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="equipmentBasePrice" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}EquipmentBasePrice" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessEquipmentItem", propOrder = {
    "equipment",
    "discountDeliveryType",
    "discountType",
    "discountAmt",
    "equipmentBasePrice"
})
public class WirelessEquipmentItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected EquipmentProduct equipment;
    @XmlElement(required = true)
    protected DiscountDeliveryType discountDeliveryType;
    @XmlElement(required = true)
    protected DiscountType discountType;
    protected Double discountAmt;
    protected EquipmentBasePrice equipmentBasePrice;

    /**
     * Gets the value of the equipment property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentProduct }
     *     
     */
    public EquipmentProduct getEquipment() {
        return equipment;
    }

    /**
     * Sets the value of the equipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentProduct }
     *     
     */
    public void setEquipment(EquipmentProduct value) {
        this.equipment = value;
    }

    /**
     * Gets the value of the discountDeliveryType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountDeliveryType }
     *     
     */
    public DiscountDeliveryType getDiscountDeliveryType() {
        return discountDeliveryType;
    }

    /**
     * Sets the value of the discountDeliveryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountDeliveryType }
     *     
     */
    public void setDiscountDeliveryType(DiscountDeliveryType value) {
        this.discountDeliveryType = value;
    }

    /**
     * Gets the value of the discountType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Sets the value of the discountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setDiscountType(DiscountType value) {
        this.discountType = value;
    }

    /**
     * Gets the value of the discountAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscountAmt() {
        return discountAmt;
    }

    /**
     * Sets the value of the discountAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscountAmt(Double value) {
        this.discountAmt = value;
    }

    /**
     * Gets the value of the equipmentBasePrice property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentBasePrice }
     *     
     */
    public EquipmentBasePrice getEquipmentBasePrice() {
        return equipmentBasePrice;
    }

    /**
     * Sets the value of the equipmentBasePrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentBasePrice }
     *     
     */
    public void setEquipmentBasePrice(EquipmentBasePrice value) {
        this.equipmentBasePrice = value;
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
            EquipmentProduct theEquipment;
            theEquipment = this.getEquipment();
            strategy.appendField(locator, this, "equipment", buffer, theEquipment);
        }
        {
            DiscountDeliveryType theDiscountDeliveryType;
            theDiscountDeliveryType = this.getDiscountDeliveryType();
            strategy.appendField(locator, this, "discountDeliveryType", buffer, theDiscountDeliveryType);
        }
        {
            DiscountType theDiscountType;
            theDiscountType = this.getDiscountType();
            strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
        }
        {
            Double theDiscountAmt;
            theDiscountAmt = this.getDiscountAmt();
            strategy.appendField(locator, this, "discountAmt", buffer, theDiscountAmt);
        }
        {
            EquipmentBasePrice theEquipmentBasePrice;
            theEquipmentBasePrice = this.getEquipmentBasePrice();
            strategy.appendField(locator, this, "equipmentBasePrice", buffer, theEquipmentBasePrice);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessEquipmentItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessEquipmentItem that = ((WirelessEquipmentItem) object);
        {
            EquipmentProduct lhsEquipment;
            lhsEquipment = this.getEquipment();
            EquipmentProduct rhsEquipment;
            rhsEquipment = that.getEquipment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipment", lhsEquipment), LocatorUtils.property(thatLocator, "equipment", rhsEquipment), lhsEquipment, rhsEquipment)) {
                return false;
            }
        }
        {
            DiscountDeliveryType lhsDiscountDeliveryType;
            lhsDiscountDeliveryType = this.getDiscountDeliveryType();
            DiscountDeliveryType rhsDiscountDeliveryType;
            rhsDiscountDeliveryType = that.getDiscountDeliveryType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountDeliveryType", lhsDiscountDeliveryType), LocatorUtils.property(thatLocator, "discountDeliveryType", rhsDiscountDeliveryType), lhsDiscountDeliveryType, rhsDiscountDeliveryType)) {
                return false;
            }
        }
        {
            DiscountType lhsDiscountType;
            lhsDiscountType = this.getDiscountType();
            DiscountType rhsDiscountType;
            rhsDiscountType = that.getDiscountType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountType", lhsDiscountType), LocatorUtils.property(thatLocator, "discountType", rhsDiscountType), lhsDiscountType, rhsDiscountType)) {
                return false;
            }
        }
        {
            Double lhsDiscountAmt;
            lhsDiscountAmt = this.getDiscountAmt();
            Double rhsDiscountAmt;
            rhsDiscountAmt = that.getDiscountAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountAmt", lhsDiscountAmt), LocatorUtils.property(thatLocator, "discountAmt", rhsDiscountAmt), lhsDiscountAmt, rhsDiscountAmt)) {
                return false;
            }
        }
        {
            EquipmentBasePrice lhsEquipmentBasePrice;
            lhsEquipmentBasePrice = this.getEquipmentBasePrice();
            EquipmentBasePrice rhsEquipmentBasePrice;
            rhsEquipmentBasePrice = that.getEquipmentBasePrice();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentBasePrice", lhsEquipmentBasePrice), LocatorUtils.property(thatLocator, "equipmentBasePrice", rhsEquipmentBasePrice), lhsEquipmentBasePrice, rhsEquipmentBasePrice)) {
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
