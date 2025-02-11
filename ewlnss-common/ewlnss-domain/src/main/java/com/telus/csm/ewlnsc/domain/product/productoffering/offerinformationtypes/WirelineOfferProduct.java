
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
 * <p>Java class for WirelineOfferProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineOfferProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelineOfferProductSummary">
 *       &lt;sequence>
 *         &lt;element name="wirelineEquipmentList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelineEquipment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="addOn" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AddOn" minOccurs="0"/>
 *         &lt;element name="discountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Discount" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="feature" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Feature" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineOfferProduct", propOrder = {
    "wirelineEquipmentList",
    "addOn",
    "discountList",
    "feature"
})
public class WirelineOfferProduct
    extends WirelineOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<WirelineEquipment> wirelineEquipmentList;
    protected AddOn addOn;
    protected List<Discount> discountList;
    protected Feature feature;

    /**
     * Gets the value of the wirelineEquipmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelineEquipmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelineEquipmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineEquipment }
     * 
     * 
     */
    public List<WirelineEquipment> getWirelineEquipmentList() {
        if (wirelineEquipmentList == null) {
            wirelineEquipmentList = new ArrayList<WirelineEquipment>();
        }
        return this.wirelineEquipmentList;
    }

    /**
     * Gets the value of the addOn property.
     * 
     * @return
     *     possible object is
     *     {@link AddOn }
     *     
     */
    public AddOn getAddOn() {
        return addOn;
    }

    /**
     * Sets the value of the addOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddOn }
     *     
     */
    public void setAddOn(AddOn value) {
        this.addOn = value;
    }

    /**
     * Gets the value of the discountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the discountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Discount }
     * 
     * 
     */
    public List<Discount> getDiscountList() {
        if (discountList == null) {
            discountList = new ArrayList<Discount>();
        }
        return this.discountList;
    }

    /**
     * Gets the value of the feature property.
     * 
     * @return
     *     possible object is
     *     {@link Feature }
     *     
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * Sets the value of the feature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Feature }
     *     
     */
    public void setFeature(Feature value) {
        this.feature = value;
    }

    /**
     * Sets the value of the wirelineEquipmentList property.
     * 
     * @param wirelineEquipmentList
     *     allowed object is
     *     {@link WirelineEquipment }
     *     
     */
    public void setWirelineEquipmentList(List<WirelineEquipment> wirelineEquipmentList) {
        this.wirelineEquipmentList = wirelineEquipmentList;
    }

    /**
     * Sets the value of the discountList property.
     * 
     * @param discountList
     *     allowed object is
     *     {@link Discount }
     *     
     */
    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<WirelineEquipment> theWirelineEquipmentList;
            theWirelineEquipmentList = (((this.wirelineEquipmentList!= null)&&(!this.wirelineEquipmentList.isEmpty()))?this.getWirelineEquipmentList():null);
            strategy.appendField(locator, this, "wirelineEquipmentList", buffer, theWirelineEquipmentList);
        }
        {
            AddOn theAddOn;
            theAddOn = this.getAddOn();
            strategy.appendField(locator, this, "addOn", buffer, theAddOn);
        }
        {
            List<Discount> theDiscountList;
            theDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            strategy.appendField(locator, this, "discountList", buffer, theDiscountList);
        }
        {
            Feature theFeature;
            theFeature = this.getFeature();
            strategy.appendField(locator, this, "feature", buffer, theFeature);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineOfferProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineOfferProduct that = ((WirelineOfferProduct) object);
        {
            List<WirelineEquipment> lhsWirelineEquipmentList;
            lhsWirelineEquipmentList = (((this.wirelineEquipmentList!= null)&&(!this.wirelineEquipmentList.isEmpty()))?this.getWirelineEquipmentList():null);
            List<WirelineEquipment> rhsWirelineEquipmentList;
            rhsWirelineEquipmentList = (((that.wirelineEquipmentList!= null)&&(!that.wirelineEquipmentList.isEmpty()))?that.getWirelineEquipmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineEquipmentList", lhsWirelineEquipmentList), LocatorUtils.property(thatLocator, "wirelineEquipmentList", rhsWirelineEquipmentList), lhsWirelineEquipmentList, rhsWirelineEquipmentList)) {
                return false;
            }
        }
        {
            AddOn lhsAddOn;
            lhsAddOn = this.getAddOn();
            AddOn rhsAddOn;
            rhsAddOn = that.getAddOn();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "addOn", lhsAddOn), LocatorUtils.property(thatLocator, "addOn", rhsAddOn), lhsAddOn, rhsAddOn)) {
                return false;
            }
        }
        {
            List<Discount> lhsDiscountList;
            lhsDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            List<Discount> rhsDiscountList;
            rhsDiscountList = (((that.discountList!= null)&&(!that.discountList.isEmpty()))?that.getDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountList", lhsDiscountList), LocatorUtils.property(thatLocator, "discountList", rhsDiscountList), lhsDiscountList, rhsDiscountList)) {
                return false;
            }
        }
        {
            Feature lhsFeature;
            lhsFeature = this.getFeature();
            Feature rhsFeature;
            rhsFeature = that.getFeature();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "feature", lhsFeature), LocatorUtils.property(thatLocator, "feature", rhsFeature), lhsFeature, rhsFeature)) {
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
