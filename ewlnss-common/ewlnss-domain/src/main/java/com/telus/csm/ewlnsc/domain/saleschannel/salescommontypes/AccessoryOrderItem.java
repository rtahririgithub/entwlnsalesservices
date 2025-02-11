
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
 * <p>Java class for AccessoryOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOrderItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccessoryProduct" maxOccurs="100"/>
 *         &lt;element name="pricingDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccessoryOrderPricingType" minOccurs="0"/>
 *         &lt;element name="deliveryOption" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DeliveryOptionType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOrderItem", propOrder = {
    "productList",
    "pricingDetail",
    "deliveryOption"
})
public class AccessoryOrderItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<AccessoryProduct> productList;
    protected AccessoryOrderPricingType pricingDetail;
    protected DeliveryOptionType deliveryOption;

    /**
     * Gets the value of the productList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryProduct }
     * 
     * 
     */
    public List<AccessoryProduct> getProductList() {
        if (productList == null) {
            productList = new ArrayList<AccessoryProduct>();
        }
        return this.productList;
    }

    /**
     * Gets the value of the pricingDetail property.
     * 
     * @return
     *     possible object is
     *     {@link AccessoryOrderPricingType }
     *     
     */
    public AccessoryOrderPricingType getPricingDetail() {
        return pricingDetail;
    }

    /**
     * Sets the value of the pricingDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessoryOrderPricingType }
     *     
     */
    public void setPricingDetail(AccessoryOrderPricingType value) {
        this.pricingDetail = value;
    }

    /**
     * Gets the value of the deliveryOption property.
     * 
     * @return
     *     possible object is
     *     {@link DeliveryOptionType }
     *     
     */
    public DeliveryOptionType getDeliveryOption() {
        return deliveryOption;
    }

    /**
     * Sets the value of the deliveryOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeliveryOptionType }
     *     
     */
    public void setDeliveryOption(DeliveryOptionType value) {
        this.deliveryOption = value;
    }

    /**
     * Sets the value of the productList property.
     * 
     * @param productList
     *     allowed object is
     *     {@link AccessoryProduct }
     *     
     */
    public void setProductList(List<AccessoryProduct> productList) {
        this.productList = productList;
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
            List<AccessoryProduct> theProductList;
            theProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            strategy.appendField(locator, this, "productList", buffer, theProductList);
        }
        {
            AccessoryOrderPricingType thePricingDetail;
            thePricingDetail = this.getPricingDetail();
            strategy.appendField(locator, this, "pricingDetail", buffer, thePricingDetail);
        }
        {
            DeliveryOptionType theDeliveryOption;
            theDeliveryOption = this.getDeliveryOption();
            strategy.appendField(locator, this, "deliveryOption", buffer, theDeliveryOption);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOrderItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOrderItem that = ((AccessoryOrderItem) object);
        {
            List<AccessoryProduct> lhsProductList;
            lhsProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            List<AccessoryProduct> rhsProductList;
            rhsProductList = (((that.productList!= null)&&(!that.productList.isEmpty()))?that.getProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productList", lhsProductList), LocatorUtils.property(thatLocator, "productList", rhsProductList), lhsProductList, rhsProductList)) {
                return false;
            }
        }
        {
            AccessoryOrderPricingType lhsPricingDetail;
            lhsPricingDetail = this.getPricingDetail();
            AccessoryOrderPricingType rhsPricingDetail;
            rhsPricingDetail = that.getPricingDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricingDetail", lhsPricingDetail), LocatorUtils.property(thatLocator, "pricingDetail", rhsPricingDetail), lhsPricingDetail, rhsPricingDetail)) {
                return false;
            }
        }
        {
            DeliveryOptionType lhsDeliveryOption;
            lhsDeliveryOption = this.getDeliveryOption();
            DeliveryOptionType rhsDeliveryOption;
            rhsDeliveryOption = that.getDeliveryOption();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deliveryOption", lhsDeliveryOption), LocatorUtils.property(thatLocator, "deliveryOption", rhsDeliveryOption), lhsDeliveryOption, rhsDeliveryOption)) {
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
