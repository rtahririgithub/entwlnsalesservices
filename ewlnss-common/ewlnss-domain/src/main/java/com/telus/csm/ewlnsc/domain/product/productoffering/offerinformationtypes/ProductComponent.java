
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.telus.csm.ewlnsc.domain.CharacteristicVO;
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
 * <p>Java class for ProductComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductComponent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueItemList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueItem" maxOccurs="unbounded"/>
 *         &lt;element name="marketingDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="marketingProductPriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="includedServiceConstraintList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}IncludedServiceConstraint" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductComponent", propOrder = {
    "productCatalogueItemList",
    "marketingDescriptionList",
    "marketingProductPriceAmt",
    "includedServiceConstraintList"
})
public class ProductComponent
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<ProductCatalogueItem> productCatalogueItemList;
    protected List<Description> marketingDescriptionList;
    protected Double marketingProductPriceAmt;
    protected List<IncludedServiceConstraint> includedServiceConstraintList;
	private List<CharacteristicVO> characteristics;//FIFA-SHS changes: product level characteristic

    /**
     * Gets the value of the productCatalogueItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCatalogueItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCatalogueItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCatalogueItem }
     * 
     * 
     */
    public List<ProductCatalogueItem> getProductCatalogueItemList() {
        if (productCatalogueItemList == null) {
            productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
        }
        return this.productCatalogueItemList;
    }

    /**
     * Gets the value of the marketingDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketingDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketingDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getMarketingDescriptionList() {
        if (marketingDescriptionList == null) {
            marketingDescriptionList = new ArrayList<Description>();
        }
        return this.marketingDescriptionList;
    }

    /**
     * Gets the value of the marketingProductPriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMarketingProductPriceAmt() {
        return marketingProductPriceAmt;
    }

    /**
     * Sets the value of the marketingProductPriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMarketingProductPriceAmt(Double value) {
        this.marketingProductPriceAmt = value;
    }

    /**
     * Gets the value of the includedServiceConstraintList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedServiceConstraintList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedServiceConstraintList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link IncludedServiceConstraint }
     * 
     * 
     */
    public List<IncludedServiceConstraint> getIncludedServiceConstraintList() {
        if (includedServiceConstraintList == null) {
            includedServiceConstraintList = new ArrayList<IncludedServiceConstraint>();
        }
        return this.includedServiceConstraintList;
    }

    /**
     * Sets the value of the productCatalogueItemList property.
     * 
     * @param productCatalogueItemList
     *     allowed object is
     *     {@link ProductCatalogueItem }
     *     
     */
    public void setProductCatalogueItemList(List<ProductCatalogueItem> productCatalogueItemList) {
        this.productCatalogueItemList = productCatalogueItemList;
    }

    /**
     * Sets the value of the marketingDescriptionList property.
     * 
     * @param marketingDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setMarketingDescriptionList(List<Description> marketingDescriptionList) {
        this.marketingDescriptionList = marketingDescriptionList;
    }

    /**
     * Sets the value of the includedServiceConstraintList property.
     * 
     * @param includedServiceConstraintList
     *     allowed object is
     *     {@link IncludedServiceConstraint }
     *     
     */
    public void setIncludedServiceConstraintList(List<IncludedServiceConstraint> includedServiceConstraintList) {
        this.includedServiceConstraintList = includedServiceConstraintList;
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
            List<ProductCatalogueItem> theProductCatalogueItemList;
            theProductCatalogueItemList = (((this.productCatalogueItemList!= null)&&(!this.productCatalogueItemList.isEmpty()))?this.getProductCatalogueItemList():null);
            strategy.appendField(locator, this, "productCatalogueItemList", buffer, theProductCatalogueItemList);
        }
        {
            List<Description> theMarketingDescriptionList;
            theMarketingDescriptionList = (((this.marketingDescriptionList!= null)&&(!this.marketingDescriptionList.isEmpty()))?this.getMarketingDescriptionList():null);
            strategy.appendField(locator, this, "marketingDescriptionList", buffer, theMarketingDescriptionList);
        }
        {
            Double theMarketingProductPriceAmt;
            theMarketingProductPriceAmt = this.getMarketingProductPriceAmt();
            strategy.appendField(locator, this, "marketingProductPriceAmt", buffer, theMarketingProductPriceAmt);
        }
        {
            List<IncludedServiceConstraint> theIncludedServiceConstraintList;
            theIncludedServiceConstraintList = (((this.includedServiceConstraintList!= null)&&(!this.includedServiceConstraintList.isEmpty()))?this.getIncludedServiceConstraintList():null);
            strategy.appendField(locator, this, "includedServiceConstraintList", buffer, theIncludedServiceConstraintList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductComponent)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductComponent that = ((ProductComponent) object);
        {
            List<ProductCatalogueItem> lhsProductCatalogueItemList;
            lhsProductCatalogueItemList = (((this.productCatalogueItemList!= null)&&(!this.productCatalogueItemList.isEmpty()))?this.getProductCatalogueItemList():null);
            List<ProductCatalogueItem> rhsProductCatalogueItemList;
            rhsProductCatalogueItemList = (((that.productCatalogueItemList!= null)&&(!that.productCatalogueItemList.isEmpty()))?that.getProductCatalogueItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueItemList", lhsProductCatalogueItemList), LocatorUtils.property(thatLocator, "productCatalogueItemList", rhsProductCatalogueItemList), lhsProductCatalogueItemList, rhsProductCatalogueItemList)) {
                return false;
            }
        }
        {
            List<Description> lhsMarketingDescriptionList;
            lhsMarketingDescriptionList = (((this.marketingDescriptionList!= null)&&(!this.marketingDescriptionList.isEmpty()))?this.getMarketingDescriptionList():null);
            List<Description> rhsMarketingDescriptionList;
            rhsMarketingDescriptionList = (((that.marketingDescriptionList!= null)&&(!that.marketingDescriptionList.isEmpty()))?that.getMarketingDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "marketingDescriptionList", lhsMarketingDescriptionList), LocatorUtils.property(thatLocator, "marketingDescriptionList", rhsMarketingDescriptionList), lhsMarketingDescriptionList, rhsMarketingDescriptionList)) {
                return false;
            }
        }
        {
            Double lhsMarketingProductPriceAmt;
            lhsMarketingProductPriceAmt = this.getMarketingProductPriceAmt();
            Double rhsMarketingProductPriceAmt;
            rhsMarketingProductPriceAmt = that.getMarketingProductPriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "marketingProductPriceAmt", lhsMarketingProductPriceAmt), LocatorUtils.property(thatLocator, "marketingProductPriceAmt", rhsMarketingProductPriceAmt), lhsMarketingProductPriceAmt, rhsMarketingProductPriceAmt)) {
                return false;
            }
        }
        {
            List<IncludedServiceConstraint> lhsIncludedServiceConstraintList;
            lhsIncludedServiceConstraintList = (((this.includedServiceConstraintList!= null)&&(!this.includedServiceConstraintList.isEmpty()))?this.getIncludedServiceConstraintList():null);
            List<IncludedServiceConstraint> rhsIncludedServiceConstraintList;
            rhsIncludedServiceConstraintList = (((that.includedServiceConstraintList!= null)&&(!that.includedServiceConstraintList.isEmpty()))?that.getIncludedServiceConstraintList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedServiceConstraintList", lhsIncludedServiceConstraintList), LocatorUtils.property(thatLocator, "includedServiceConstraintList", rhsIncludedServiceConstraintList), lhsIncludedServiceConstraintList, rhsIncludedServiceConstraintList)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

	public List<CharacteristicVO> getCharacteristics() {
		if (characteristics==null) {
			characteristics =new ArrayList<CharacteristicVO>();
		}
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicVO> characteristics) {
		this.characteristics = characteristics;
	}

	public void addCharacteristic(CharacteristicVO characteristic) {
		getCharacteristics().add( characteristic );
	}
}
