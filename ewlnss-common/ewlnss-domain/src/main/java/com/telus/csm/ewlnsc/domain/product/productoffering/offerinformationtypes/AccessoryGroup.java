
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for AccessoryGroup complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryGroup">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="itemQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="discountLevelCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType" minOccurs="0"/>
 *         &lt;element name="discountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="productList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryGroupDiscountProduct" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="tierList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryTier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="excludedProductList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryGroupProduct" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryGroup", propOrder = {
    "itemQty",
    "discountLevelCd",
    "discountType",
    "discountAmt",
    "productList",
    "tierList",
    "excludedProductList"
})
public class AccessoryGroup
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected BigInteger itemQty;
    @XmlElement(required = true)
    protected String discountLevelCd;
    protected DiscountType discountType;
    protected Double discountAmt;
    protected List<AccessoryGroupDiscountProduct> productList;
    protected List<AccessoryTier> tierList;
    protected List<AccessoryGroupProduct> excludedProductList;

    /**
     * Gets the value of the itemQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getItemQty() {
        return itemQty;
    }

    /**
     * Sets the value of the itemQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setItemQty(BigInteger value) {
        this.itemQty = value;
    }

    /**
     * Gets the value of the discountLevelCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountLevelCd() {
        return discountLevelCd;
    }

    /**
     * Sets the value of the discountLevelCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountLevelCd(String value) {
        this.discountLevelCd = value;
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
     * {@link AccessoryGroupDiscountProduct }
     * 
     * 
     */
    public List<AccessoryGroupDiscountProduct> getProductList() {
        if (productList == null) {
            productList = new ArrayList<AccessoryGroupDiscountProduct>();
        }
        return this.productList;
    }

    /**
     * Gets the value of the tierList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tierList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTierList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryTier }
     * 
     * 
     */
    public List<AccessoryTier> getTierList() {
        if (tierList == null) {
            tierList = new ArrayList<AccessoryTier>();
        }
        return this.tierList;
    }

    /**
     * Gets the value of the excludedProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the excludedProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getExcludedProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryGroupProduct }
     * 
     * 
     */
    public List<AccessoryGroupProduct> getExcludedProductList() {
        if (excludedProductList == null) {
            excludedProductList = new ArrayList<AccessoryGroupProduct>();
        }
        return this.excludedProductList;
    }

    /**
     * Sets the value of the productList property.
     * 
     * @param productList
     *     allowed object is
     *     {@link AccessoryGroupDiscountProduct }
     *     
     */
    public void setProductList(List<AccessoryGroupDiscountProduct> productList) {
        this.productList = productList;
    }

    /**
     * Sets the value of the tierList property.
     * 
     * @param tierList
     *     allowed object is
     *     {@link AccessoryTier }
     *     
     */
    public void setTierList(List<AccessoryTier> tierList) {
        this.tierList = tierList;
    }

    /**
     * Sets the value of the excludedProductList property.
     * 
     * @param excludedProductList
     *     allowed object is
     *     {@link AccessoryGroupProduct }
     *     
     */
    public void setExcludedProductList(List<AccessoryGroupProduct> excludedProductList) {
        this.excludedProductList = excludedProductList;
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
            BigInteger theItemQty;
            theItemQty = this.getItemQty();
            strategy.appendField(locator, this, "itemQty", buffer, theItemQty);
        }
        {
            String theDiscountLevelCd;
            theDiscountLevelCd = this.getDiscountLevelCd();
            strategy.appendField(locator, this, "discountLevelCd", buffer, theDiscountLevelCd);
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
            List<AccessoryGroupDiscountProduct> theProductList;
            theProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            strategy.appendField(locator, this, "productList", buffer, theProductList);
        }
        {
            List<AccessoryTier> theTierList;
            theTierList = (((this.tierList!= null)&&(!this.tierList.isEmpty()))?this.getTierList():null);
            strategy.appendField(locator, this, "tierList", buffer, theTierList);
        }
        {
            List<AccessoryGroupProduct> theExcludedProductList;
            theExcludedProductList = (((this.excludedProductList!= null)&&(!this.excludedProductList.isEmpty()))?this.getExcludedProductList():null);
            strategy.appendField(locator, this, "excludedProductList", buffer, theExcludedProductList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryGroup)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryGroup that = ((AccessoryGroup) object);
        {
            BigInteger lhsItemQty;
            lhsItemQty = this.getItemQty();
            BigInteger rhsItemQty;
            rhsItemQty = that.getItemQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "itemQty", lhsItemQty), LocatorUtils.property(thatLocator, "itemQty", rhsItemQty), lhsItemQty, rhsItemQty)) {
                return false;
            }
        }
        {
            String lhsDiscountLevelCd;
            lhsDiscountLevelCd = this.getDiscountLevelCd();
            String rhsDiscountLevelCd;
            rhsDiscountLevelCd = that.getDiscountLevelCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountLevelCd", lhsDiscountLevelCd), LocatorUtils.property(thatLocator, "discountLevelCd", rhsDiscountLevelCd), lhsDiscountLevelCd, rhsDiscountLevelCd)) {
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
            List<AccessoryGroupDiscountProduct> lhsProductList;
            lhsProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            List<AccessoryGroupDiscountProduct> rhsProductList;
            rhsProductList = (((that.productList!= null)&&(!that.productList.isEmpty()))?that.getProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productList", lhsProductList), LocatorUtils.property(thatLocator, "productList", rhsProductList), lhsProductList, rhsProductList)) {
                return false;
            }
        }
        {
            List<AccessoryTier> lhsTierList;
            lhsTierList = (((this.tierList!= null)&&(!this.tierList.isEmpty()))?this.getTierList():null);
            List<AccessoryTier> rhsTierList;
            rhsTierList = (((that.tierList!= null)&&(!that.tierList.isEmpty()))?that.getTierList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "tierList", lhsTierList), LocatorUtils.property(thatLocator, "tierList", rhsTierList), lhsTierList, rhsTierList)) {
                return false;
            }
        }
        {
            List<AccessoryGroupProduct> lhsExcludedProductList;
            lhsExcludedProductList = (((this.excludedProductList!= null)&&(!this.excludedProductList.isEmpty()))?this.getExcludedProductList():null);
            List<AccessoryGroupProduct> rhsExcludedProductList;
            rhsExcludedProductList = (((that.excludedProductList!= null)&&(!that.excludedProductList.isEmpty()))?that.getExcludedProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "excludedProductList", lhsExcludedProductList), LocatorUtils.property(thatLocator, "excludedProductList", rhsExcludedProductList), lhsExcludedProductList, rhsExcludedProductList)) {
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
