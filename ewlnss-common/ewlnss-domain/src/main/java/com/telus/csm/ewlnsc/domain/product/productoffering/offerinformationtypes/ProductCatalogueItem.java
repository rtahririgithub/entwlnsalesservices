
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for ProductCatalogueItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductCatalogueItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier"/>
 *         &lt;element name="productCatalogueNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2"/>
 *         &lt;element name="parentProductCatalogueIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier"/>
 *         &lt;element name="productCatalogueBasePriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="packEligibleItemInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="serviceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="itemRankNum" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductCatalogueItem", propOrder = {
    "productCatalogueIdentifier",
    "productCatalogueNameList",
    "parentProductCatalogueIdentifier",
    "productCatalogueBasePriceAmt",
    "packEligibleItemInd",
    "serviceTypeCode",
    "itemRankNum"
})
@XmlSeeAlso({
    DiscountProductCatalogueItem.class
})
public class ProductCatalogueItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductCatalogueIdentifier productCatalogueIdentifier;
    @XmlElement(required = true)
    protected List<Description> productCatalogueNameList;
    @XmlElement(required = true)
    protected ProductCatalogueIdentifier parentProductCatalogueIdentifier;
    protected Double productCatalogueBasePriceAmt;
    protected Boolean packEligibleItemInd;
    protected String serviceTypeCode;
    protected BigInteger itemRankNum;

    /**
     * Gets the value of the productCatalogueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getProductCatalogueIdentifier() {
        return productCatalogueIdentifier;
    }

    /**
     * Sets the value of the productCatalogueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setProductCatalogueIdentifier(ProductCatalogueIdentifier value) {
        this.productCatalogueIdentifier = value;
    }

    /**
     * Gets the value of the productCatalogueNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCatalogueNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCatalogueNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getProductCatalogueNameList() {
        if (productCatalogueNameList == null) {
            productCatalogueNameList = new ArrayList<Description>();
        }
        return this.productCatalogueNameList;
    }

    /**
     * Gets the value of the parentProductCatalogueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getParentProductCatalogueIdentifier() {
        return parentProductCatalogueIdentifier;
    }

    /**
     * Sets the value of the parentProductCatalogueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setParentProductCatalogueIdentifier(ProductCatalogueIdentifier value) {
        this.parentProductCatalogueIdentifier = value;
    }

    /**
     * Gets the value of the productCatalogueBasePriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getProductCatalogueBasePriceAmt() {
        return productCatalogueBasePriceAmt;
    }

    /**
     * Sets the value of the productCatalogueBasePriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setProductCatalogueBasePriceAmt(Double value) {
        this.productCatalogueBasePriceAmt = value;
    }

    /**
     * Gets the value of the packEligibleItemInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPackEligibleItemInd() {
        return packEligibleItemInd;
    }

    /**
     * Sets the value of the packEligibleItemInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPackEligibleItemInd(Boolean value) {
        this.packEligibleItemInd = value;
    }

    /**
     * Gets the value of the serviceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    /**
     * Sets the value of the serviceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCode(String value) {
        this.serviceTypeCode = value;
    }

    /**
     * Gets the value of the itemRankNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getItemRankNum() {
        return itemRankNum;
    }

    /**
     * Sets the value of the itemRankNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setItemRankNum(BigInteger value) {
        this.itemRankNum = value;
    }

    /**
     * Sets the value of the productCatalogueNameList property.
     * 
     * @param productCatalogueNameList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setProductCatalogueNameList(List<Description> productCatalogueNameList) {
        this.productCatalogueNameList = productCatalogueNameList;
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
            ProductCatalogueIdentifier theProductCatalogueIdentifier;
            theProductCatalogueIdentifier = this.getProductCatalogueIdentifier();
            strategy.appendField(locator, this, "productCatalogueIdentifier", buffer, theProductCatalogueIdentifier);
        }
        {
            List<Description> theProductCatalogueNameList;
            theProductCatalogueNameList = (((this.productCatalogueNameList!= null)&&(!this.productCatalogueNameList.isEmpty()))?this.getProductCatalogueNameList():null);
            strategy.appendField(locator, this, "productCatalogueNameList", buffer, theProductCatalogueNameList);
        }
        {
            ProductCatalogueIdentifier theParentProductCatalogueIdentifier;
            theParentProductCatalogueIdentifier = this.getParentProductCatalogueIdentifier();
            strategy.appendField(locator, this, "parentProductCatalogueIdentifier", buffer, theParentProductCatalogueIdentifier);
        }
        {
            Double theProductCatalogueBasePriceAmt;
            theProductCatalogueBasePriceAmt = this.getProductCatalogueBasePriceAmt();
            strategy.appendField(locator, this, "productCatalogueBasePriceAmt", buffer, theProductCatalogueBasePriceAmt);
        }
        {
            Boolean thePackEligibleItemInd;
            thePackEligibleItemInd = this.isPackEligibleItemInd();
            strategy.appendField(locator, this, "packEligibleItemInd", buffer, thePackEligibleItemInd);
        }
        {
            String theServiceTypeCode;
            theServiceTypeCode = this.getServiceTypeCode();
            strategy.appendField(locator, this, "serviceTypeCode", buffer, theServiceTypeCode);
        }
        {
            BigInteger theItemRankNum;
            theItemRankNum = this.getItemRankNum();
            strategy.appendField(locator, this, "itemRankNum", buffer, theItemRankNum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductCatalogueItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductCatalogueItem that = ((ProductCatalogueItem) object);
        {
            ProductCatalogueIdentifier lhsProductCatalogueIdentifier;
            lhsProductCatalogueIdentifier = this.getProductCatalogueIdentifier();
            ProductCatalogueIdentifier rhsProductCatalogueIdentifier;
            rhsProductCatalogueIdentifier = that.getProductCatalogueIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueIdentifier", lhsProductCatalogueIdentifier), LocatorUtils.property(thatLocator, "productCatalogueIdentifier", rhsProductCatalogueIdentifier), lhsProductCatalogueIdentifier, rhsProductCatalogueIdentifier)) {
                return false;
            }
        }
        {
            List<Description> lhsProductCatalogueNameList;
            lhsProductCatalogueNameList = (((this.productCatalogueNameList!= null)&&(!this.productCatalogueNameList.isEmpty()))?this.getProductCatalogueNameList():null);
            List<Description> rhsProductCatalogueNameList;
            rhsProductCatalogueNameList = (((that.productCatalogueNameList!= null)&&(!that.productCatalogueNameList.isEmpty()))?that.getProductCatalogueNameList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueNameList", lhsProductCatalogueNameList), LocatorUtils.property(thatLocator, "productCatalogueNameList", rhsProductCatalogueNameList), lhsProductCatalogueNameList, rhsProductCatalogueNameList)) {
                return false;
            }
        }
        {
            ProductCatalogueIdentifier lhsParentProductCatalogueIdentifier;
            lhsParentProductCatalogueIdentifier = this.getParentProductCatalogueIdentifier();
            ProductCatalogueIdentifier rhsParentProductCatalogueIdentifier;
            rhsParentProductCatalogueIdentifier = that.getParentProductCatalogueIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "parentProductCatalogueIdentifier", lhsParentProductCatalogueIdentifier), LocatorUtils.property(thatLocator, "parentProductCatalogueIdentifier", rhsParentProductCatalogueIdentifier), lhsParentProductCatalogueIdentifier, rhsParentProductCatalogueIdentifier)) {
                return false;
            }
        }
        {
            Double lhsProductCatalogueBasePriceAmt;
            lhsProductCatalogueBasePriceAmt = this.getProductCatalogueBasePriceAmt();
            Double rhsProductCatalogueBasePriceAmt;
            rhsProductCatalogueBasePriceAmt = that.getProductCatalogueBasePriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueBasePriceAmt", lhsProductCatalogueBasePriceAmt), LocatorUtils.property(thatLocator, "productCatalogueBasePriceAmt", rhsProductCatalogueBasePriceAmt), lhsProductCatalogueBasePriceAmt, rhsProductCatalogueBasePriceAmt)) {
                return false;
            }
        }
        {
            Boolean lhsPackEligibleItemInd;
            lhsPackEligibleItemInd = this.isPackEligibleItemInd();
            Boolean rhsPackEligibleItemInd;
            rhsPackEligibleItemInd = that.isPackEligibleItemInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "packEligibleItemInd", lhsPackEligibleItemInd), LocatorUtils.property(thatLocator, "packEligibleItemInd", rhsPackEligibleItemInd), lhsPackEligibleItemInd, rhsPackEligibleItemInd)) {
                return false;
            }
        }
        {
            String lhsServiceTypeCode;
            lhsServiceTypeCode = this.getServiceTypeCode();
            String rhsServiceTypeCode;
            rhsServiceTypeCode = that.getServiceTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTypeCode", lhsServiceTypeCode), LocatorUtils.property(thatLocator, "serviceTypeCode", rhsServiceTypeCode), lhsServiceTypeCode, rhsServiceTypeCode)) {
                return false;
            }
        }
        {
            BigInteger lhsItemRankNum;
            lhsItemRankNum = this.getItemRankNum();
            BigInteger rhsItemRankNum;
            rhsItemRankNum = that.getItemRankNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "itemRankNum", lhsItemRankNum), LocatorUtils.property(thatLocator, "itemRankNum", rhsItemRankNum), lhsItemRankNum, rhsItemRankNum)) {
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
