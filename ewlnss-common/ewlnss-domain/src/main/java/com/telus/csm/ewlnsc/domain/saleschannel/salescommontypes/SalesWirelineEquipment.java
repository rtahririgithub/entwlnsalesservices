
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProductCatalogueItem;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for SalesWirelineEquipment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesWirelineEquipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueItem" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueItem"/>
 *         &lt;element name="minQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="maxQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="purchaseEquipmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesWirelineEquipmentItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="rentalEquipmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesWirelineEquipmentItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="byodEquipmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesWirelineEquipmentItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesWirelineEquipment", propOrder = {
    "productCatalogueItem",
    "minQty",
    "maxQty",
    "purchaseEquipmentList",
    "rentalEquipmentList",
    "byodEquipmentList"
})
public class SalesWirelineEquipment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductCatalogueItem productCatalogueItem;
    protected BigInteger minQty;
    protected BigInteger maxQty;
    protected List<SalesWirelineEquipmentItem> purchaseEquipmentList;
    protected List<SalesWirelineEquipmentItem> rentalEquipmentList;
    protected List<SalesWirelineEquipmentItem> byodEquipmentList;

    /**
     * Gets the value of the productCatalogueItem property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueItem }
     *     
     */
    public ProductCatalogueItem getProductCatalogueItem() {
        return productCatalogueItem;
    }

    /**
     * Sets the value of the productCatalogueItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueItem }
     *     
     */
    public void setProductCatalogueItem(ProductCatalogueItem value) {
        this.productCatalogueItem = value;
    }

    /**
     * Gets the value of the minQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinQty() {
        return minQty;
    }

    /**
     * Sets the value of the minQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinQty(BigInteger value) {
        this.minQty = value;
    }

    /**
     * Gets the value of the maxQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxQty() {
        return maxQty;
    }

    /**
     * Sets the value of the maxQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxQty(BigInteger value) {
        this.maxQty = value;
    }

    /**
     * Gets the value of the purchaseEquipmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the purchaseEquipmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPurchaseEquipmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesWirelineEquipmentItem }
     * 
     * 
     */
    public List<SalesWirelineEquipmentItem> getPurchaseEquipmentList() {
        if (purchaseEquipmentList == null) {
            purchaseEquipmentList = new ArrayList<SalesWirelineEquipmentItem>();
        }
        return this.purchaseEquipmentList;
    }

    /**
     * Gets the value of the rentalEquipmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the rentalEquipmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRentalEquipmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesWirelineEquipmentItem }
     * 
     * 
     */
    public List<SalesWirelineEquipmentItem> getRentalEquipmentList() {
        if (rentalEquipmentList == null) {
            rentalEquipmentList = new ArrayList<SalesWirelineEquipmentItem>();
        }
        return this.rentalEquipmentList;
    }

    /**
     * Gets the value of the byodEquipmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the byodEquipmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getByodEquipmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesWirelineEquipmentItem }
     * 
     * 
     */
    public List<SalesWirelineEquipmentItem> getByodEquipmentList() {
        if (byodEquipmentList == null) {
            byodEquipmentList = new ArrayList<SalesWirelineEquipmentItem>();
        }
        return this.byodEquipmentList;
    }

    /**
     * Sets the value of the purchaseEquipmentList property.
     * 
     * @param purchaseEquipmentList
     *     allowed object is
     *     {@link SalesWirelineEquipmentItem }
     *     
     */
    public void setPurchaseEquipmentList(List<SalesWirelineEquipmentItem> purchaseEquipmentList) {
        this.purchaseEquipmentList = purchaseEquipmentList;
    }

    /**
     * Sets the value of the rentalEquipmentList property.
     * 
     * @param rentalEquipmentList
     *     allowed object is
     *     {@link SalesWirelineEquipmentItem }
     *     
     */
    public void setRentalEquipmentList(List<SalesWirelineEquipmentItem> rentalEquipmentList) {
        this.rentalEquipmentList = rentalEquipmentList;
    }

    /**
     * Sets the value of the byodEquipmentList property.
     * 
     * @param byodEquipmentList
     *     allowed object is
     *     {@link SalesWirelineEquipmentItem }
     *     
     */
    public void setByodEquipmentList(List<SalesWirelineEquipmentItem> byodEquipmentList) {
        this.byodEquipmentList = byodEquipmentList;
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
            ProductCatalogueItem theProductCatalogueItem;
            theProductCatalogueItem = this.getProductCatalogueItem();
            strategy.appendField(locator, this, "productCatalogueItem", buffer, theProductCatalogueItem);
        }
        {
            BigInteger theMinQty;
            theMinQty = this.getMinQty();
            strategy.appendField(locator, this, "minQty", buffer, theMinQty);
        }
        {
            BigInteger theMaxQty;
            theMaxQty = this.getMaxQty();
            strategy.appendField(locator, this, "maxQty", buffer, theMaxQty);
        }
        {
            List<SalesWirelineEquipmentItem> thePurchaseEquipmentList;
            thePurchaseEquipmentList = (((this.purchaseEquipmentList!= null)&&(!this.purchaseEquipmentList.isEmpty()))?this.getPurchaseEquipmentList():null);
            strategy.appendField(locator, this, "purchaseEquipmentList", buffer, thePurchaseEquipmentList);
        }
        {
            List<SalesWirelineEquipmentItem> theRentalEquipmentList;
            theRentalEquipmentList = (((this.rentalEquipmentList!= null)&&(!this.rentalEquipmentList.isEmpty()))?this.getRentalEquipmentList():null);
            strategy.appendField(locator, this, "rentalEquipmentList", buffer, theRentalEquipmentList);
        }
        {
            List<SalesWirelineEquipmentItem> theByodEquipmentList;
            theByodEquipmentList = (((this.byodEquipmentList!= null)&&(!this.byodEquipmentList.isEmpty()))?this.getByodEquipmentList():null);
            strategy.appendField(locator, this, "byodEquipmentList", buffer, theByodEquipmentList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesWirelineEquipment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesWirelineEquipment that = ((SalesWirelineEquipment) object);
        {
            ProductCatalogueItem lhsProductCatalogueItem;
            lhsProductCatalogueItem = this.getProductCatalogueItem();
            ProductCatalogueItem rhsProductCatalogueItem;
            rhsProductCatalogueItem = that.getProductCatalogueItem();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueItem", lhsProductCatalogueItem), LocatorUtils.property(thatLocator, "productCatalogueItem", rhsProductCatalogueItem), lhsProductCatalogueItem, rhsProductCatalogueItem)) {
                return false;
            }
        }
        {
            BigInteger lhsMinQty;
            lhsMinQty = this.getMinQty();
            BigInteger rhsMinQty;
            rhsMinQty = that.getMinQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minQty", lhsMinQty), LocatorUtils.property(thatLocator, "minQty", rhsMinQty), lhsMinQty, rhsMinQty)) {
                return false;
            }
        }
        {
            BigInteger lhsMaxQty;
            lhsMaxQty = this.getMaxQty();
            BigInteger rhsMaxQty;
            rhsMaxQty = that.getMaxQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxQty", lhsMaxQty), LocatorUtils.property(thatLocator, "maxQty", rhsMaxQty), lhsMaxQty, rhsMaxQty)) {
                return false;
            }
        }
        {
            List<SalesWirelineEquipmentItem> lhsPurchaseEquipmentList;
            lhsPurchaseEquipmentList = (((this.purchaseEquipmentList!= null)&&(!this.purchaseEquipmentList.isEmpty()))?this.getPurchaseEquipmentList():null);
            List<SalesWirelineEquipmentItem> rhsPurchaseEquipmentList;
            rhsPurchaseEquipmentList = (((that.purchaseEquipmentList!= null)&&(!that.purchaseEquipmentList.isEmpty()))?that.getPurchaseEquipmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseEquipmentList", lhsPurchaseEquipmentList), LocatorUtils.property(thatLocator, "purchaseEquipmentList", rhsPurchaseEquipmentList), lhsPurchaseEquipmentList, rhsPurchaseEquipmentList)) {
                return false;
            }
        }
        {
            List<SalesWirelineEquipmentItem> lhsRentalEquipmentList;
            lhsRentalEquipmentList = (((this.rentalEquipmentList!= null)&&(!this.rentalEquipmentList.isEmpty()))?this.getRentalEquipmentList():null);
            List<SalesWirelineEquipmentItem> rhsRentalEquipmentList;
            rhsRentalEquipmentList = (((that.rentalEquipmentList!= null)&&(!that.rentalEquipmentList.isEmpty()))?that.getRentalEquipmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rentalEquipmentList", lhsRentalEquipmentList), LocatorUtils.property(thatLocator, "rentalEquipmentList", rhsRentalEquipmentList), lhsRentalEquipmentList, rhsRentalEquipmentList)) {
                return false;
            }
        }
        {
            List<SalesWirelineEquipmentItem> lhsByodEquipmentList;
            lhsByodEquipmentList = (((this.byodEquipmentList!= null)&&(!this.byodEquipmentList.isEmpty()))?this.getByodEquipmentList():null);
            List<SalesWirelineEquipmentItem> rhsByodEquipmentList;
            rhsByodEquipmentList = (((that.byodEquipmentList!= null)&&(!that.byodEquipmentList.isEmpty()))?that.getByodEquipmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "byodEquipmentList", lhsByodEquipmentList), LocatorUtils.property(thatLocator, "byodEquipmentList", rhsByodEquipmentList), lhsByodEquipmentList, rhsByodEquipmentList)) {
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
