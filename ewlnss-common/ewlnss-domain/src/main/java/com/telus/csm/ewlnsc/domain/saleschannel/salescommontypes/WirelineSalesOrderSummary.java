
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

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
 * <p>Java class for WirelineSalesOrderSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineSalesOrderSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesOffer" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferHeader" minOccurs="0"/>
 *         &lt;element name="productList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductDiscount" maxOccurs="3" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineSalesOrderSummary", propOrder = {
    "salesOffer",
    "productList"
})
public class WirelineSalesOrderSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelineOfferHeader salesOffer;
    protected List<WirelineProductDiscount> productList;

    /**
     * Gets the value of the salesOffer property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineOfferHeader }
     *     
     */
    public WirelineOfferHeader getSalesOffer() {
        return salesOffer;
    }

    /**
     * Sets the value of the salesOffer property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineOfferHeader }
     *     
     */
    public void setSalesOffer(WirelineOfferHeader value) {
        this.salesOffer = value;
    }

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
     * {@link WirelineProductDiscount }
     * 
     * 
     */
    public List<WirelineProductDiscount> getProductList() {
        if (productList == null) {
            productList = new ArrayList<WirelineProductDiscount>();
        }
        return this.productList;
    }

    /**
     * Sets the value of the productList property.
     * 
     * @param productList
     *     allowed object is
     *     {@link WirelineProductDiscount }
     *     
     */
    public void setProductList(List<WirelineProductDiscount> productList) {
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
            WirelineOfferHeader theSalesOffer;
            theSalesOffer = this.getSalesOffer();
            strategy.appendField(locator, this, "salesOffer", buffer, theSalesOffer);
        }
        {
            List<WirelineProductDiscount> theProductList;
            theProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            strategy.appendField(locator, this, "productList", buffer, theProductList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineSalesOrderSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineSalesOrderSummary that = ((WirelineSalesOrderSummary) object);
        {
            WirelineOfferHeader lhsSalesOffer;
            lhsSalesOffer = this.getSalesOffer();
            WirelineOfferHeader rhsSalesOffer;
            rhsSalesOffer = that.getSalesOffer();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesOffer", lhsSalesOffer), LocatorUtils.property(thatLocator, "salesOffer", rhsSalesOffer), lhsSalesOffer, rhsSalesOffer)) {
                return false;
            }
        }
        {
            List<WirelineProductDiscount> lhsProductList;
            lhsProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            List<WirelineProductDiscount> rhsProductList;
            rhsProductList = (((that.productList!= null)&&(!that.productList.isEmpty()))?that.getProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productList", lhsProductList), LocatorUtils.property(thatLocator, "productList", rhsProductList), lhsProductList, rhsProductList)) {
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
