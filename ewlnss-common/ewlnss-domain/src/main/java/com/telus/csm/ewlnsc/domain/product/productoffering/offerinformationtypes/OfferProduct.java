
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
 * <p>Java class for OfferProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wirelessOfferProduct" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessOfferProduct" minOccurs="0"/>
 *         &lt;element name="wirelineOfferProductList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelineOfferProduct" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accessoryOfferProduct" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryOfferProduct" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferProduct", propOrder = {
    "wirelessOfferProduct",
    "wirelineOfferProductList",
    "accessoryOfferProduct"
})
public class OfferProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelessOfferProduct wirelessOfferProduct;
    protected List<WirelineOfferProduct> wirelineOfferProductList;
    protected AccessoryOfferProduct accessoryOfferProduct;

    /**
     * Gets the value of the wirelessOfferProduct property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessOfferProduct }
     *     
     */
    public WirelessOfferProduct getWirelessOfferProduct() {
        return wirelessOfferProduct;
    }

    /**
     * Sets the value of the wirelessOfferProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessOfferProduct }
     *     
     */
    public void setWirelessOfferProduct(WirelessOfferProduct value) {
        this.wirelessOfferProduct = value;
    }

    /**
     * Gets the value of the wirelineOfferProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelineOfferProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelineOfferProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineOfferProduct }
     * 
     * 
     */
    public List<WirelineOfferProduct> getWirelineOfferProductList() {
        if (wirelineOfferProductList == null) {
            wirelineOfferProductList = new ArrayList<WirelineOfferProduct>();
        }
        return this.wirelineOfferProductList;
    }

    /**
     * Gets the value of the accessoryOfferProduct property.
     * 
     * @return
     *     possible object is
     *     {@link AccessoryOfferProduct }
     *     
     */
    public AccessoryOfferProduct getAccessoryOfferProduct() {
        return accessoryOfferProduct;
    }

    /**
     * Sets the value of the accessoryOfferProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessoryOfferProduct }
     *     
     */
    public void setAccessoryOfferProduct(AccessoryOfferProduct value) {
        this.accessoryOfferProduct = value;
    }

    /**
     * Sets the value of the wirelineOfferProductList property.
     * 
     * @param wirelineOfferProductList
     *     allowed object is
     *     {@link WirelineOfferProduct }
     *     
     */
    public void setWirelineOfferProductList(List<WirelineOfferProduct> wirelineOfferProductList) {
        this.wirelineOfferProductList = wirelineOfferProductList;
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
            WirelessOfferProduct theWirelessOfferProduct;
            theWirelessOfferProduct = this.getWirelessOfferProduct();
            strategy.appendField(locator, this, "wirelessOfferProduct", buffer, theWirelessOfferProduct);
        }
        {
            List<WirelineOfferProduct> theWirelineOfferProductList;
            theWirelineOfferProductList = (((this.wirelineOfferProductList!= null)&&(!this.wirelineOfferProductList.isEmpty()))?this.getWirelineOfferProductList():null);
            strategy.appendField(locator, this, "wirelineOfferProductList", buffer, theWirelineOfferProductList);
        }
        {
            AccessoryOfferProduct theAccessoryOfferProduct;
            theAccessoryOfferProduct = this.getAccessoryOfferProduct();
            strategy.appendField(locator, this, "accessoryOfferProduct", buffer, theAccessoryOfferProduct);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OfferProduct that = ((OfferProduct) object);
        {
            WirelessOfferProduct lhsWirelessOfferProduct;
            lhsWirelessOfferProduct = this.getWirelessOfferProduct();
            WirelessOfferProduct rhsWirelessOfferProduct;
            rhsWirelessOfferProduct = that.getWirelessOfferProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessOfferProduct", lhsWirelessOfferProduct), LocatorUtils.property(thatLocator, "wirelessOfferProduct", rhsWirelessOfferProduct), lhsWirelessOfferProduct, rhsWirelessOfferProduct)) {
                return false;
            }
        }
        {
            List<WirelineOfferProduct> lhsWirelineOfferProductList;
            lhsWirelineOfferProductList = (((this.wirelineOfferProductList!= null)&&(!this.wirelineOfferProductList.isEmpty()))?this.getWirelineOfferProductList():null);
            List<WirelineOfferProduct> rhsWirelineOfferProductList;
            rhsWirelineOfferProductList = (((that.wirelineOfferProductList!= null)&&(!that.wirelineOfferProductList.isEmpty()))?that.getWirelineOfferProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineOfferProductList", lhsWirelineOfferProductList), LocatorUtils.property(thatLocator, "wirelineOfferProductList", rhsWirelineOfferProductList), lhsWirelineOfferProductList, rhsWirelineOfferProductList)) {
                return false;
            }
        }
        {
            AccessoryOfferProduct lhsAccessoryOfferProduct;
            lhsAccessoryOfferProduct = this.getAccessoryOfferProduct();
            AccessoryOfferProduct rhsAccessoryOfferProduct;
            rhsAccessoryOfferProduct = that.getAccessoryOfferProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accessoryOfferProduct", lhsAccessoryOfferProduct), LocatorUtils.property(thatLocator, "accessoryOfferProduct", rhsAccessoryOfferProduct), lhsAccessoryOfferProduct, rhsAccessoryOfferProduct)) {
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
