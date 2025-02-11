
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
 * <p>Java class for AddOn complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AddOn">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="includedProductCatalogueItemList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="optionalProductCatalogueItemList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AddOn", propOrder = {
    "includedProductCatalogueItemList",
    "optionalProductCatalogueItemList"
})
public class AddOn
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<ProductCatalogueItem> includedProductCatalogueItemList;
    protected List<ProductCatalogueItem> optionalProductCatalogueItemList;

    /**
     * Gets the value of the includedProductCatalogueItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedProductCatalogueItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedProductCatalogueItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCatalogueItem }
     * 
     * 
     */
    public List<ProductCatalogueItem> getIncludedProductCatalogueItemList() {
        if (includedProductCatalogueItemList == null) {
            includedProductCatalogueItemList = new ArrayList<ProductCatalogueItem>();
        }
        return this.includedProductCatalogueItemList;
    }

    /**
     * Gets the value of the optionalProductCatalogueItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the optionalProductCatalogueItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOptionalProductCatalogueItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCatalogueItem }
     * 
     * 
     */
    public List<ProductCatalogueItem> getOptionalProductCatalogueItemList() {
        if (optionalProductCatalogueItemList == null) {
            optionalProductCatalogueItemList = new ArrayList<ProductCatalogueItem>();
        }
        return this.optionalProductCatalogueItemList;
    }

    /**
     * Sets the value of the includedProductCatalogueItemList property.
     * 
     * @param includedProductCatalogueItemList
     *     allowed object is
     *     {@link ProductCatalogueItem }
     *     
     */
    public void setIncludedProductCatalogueItemList(List<ProductCatalogueItem> includedProductCatalogueItemList) {
        this.includedProductCatalogueItemList = includedProductCatalogueItemList;
    }

    /**
     * Sets the value of the optionalProductCatalogueItemList property.
     * 
     * @param optionalProductCatalogueItemList
     *     allowed object is
     *     {@link ProductCatalogueItem }
     *     
     */
    public void setOptionalProductCatalogueItemList(List<ProductCatalogueItem> optionalProductCatalogueItemList) {
        this.optionalProductCatalogueItemList = optionalProductCatalogueItemList;
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
            List<ProductCatalogueItem> theIncludedProductCatalogueItemList;
            theIncludedProductCatalogueItemList = (((this.includedProductCatalogueItemList!= null)&&(!this.includedProductCatalogueItemList.isEmpty()))?this.getIncludedProductCatalogueItemList():null);
            strategy.appendField(locator, this, "includedProductCatalogueItemList", buffer, theIncludedProductCatalogueItemList);
        }
        {
            List<ProductCatalogueItem> theOptionalProductCatalogueItemList;
            theOptionalProductCatalogueItemList = (((this.optionalProductCatalogueItemList!= null)&&(!this.optionalProductCatalogueItemList.isEmpty()))?this.getOptionalProductCatalogueItemList():null);
            strategy.appendField(locator, this, "optionalProductCatalogueItemList", buffer, theOptionalProductCatalogueItemList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AddOn)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AddOn that = ((AddOn) object);
        {
            List<ProductCatalogueItem> lhsIncludedProductCatalogueItemList;
            lhsIncludedProductCatalogueItemList = (((this.includedProductCatalogueItemList!= null)&&(!this.includedProductCatalogueItemList.isEmpty()))?this.getIncludedProductCatalogueItemList():null);
            List<ProductCatalogueItem> rhsIncludedProductCatalogueItemList;
            rhsIncludedProductCatalogueItemList = (((that.includedProductCatalogueItemList!= null)&&(!that.includedProductCatalogueItemList.isEmpty()))?that.getIncludedProductCatalogueItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedProductCatalogueItemList", lhsIncludedProductCatalogueItemList), LocatorUtils.property(thatLocator, "includedProductCatalogueItemList", rhsIncludedProductCatalogueItemList), lhsIncludedProductCatalogueItemList, rhsIncludedProductCatalogueItemList)) {
                return false;
            }
        }
        {
            List<ProductCatalogueItem> lhsOptionalProductCatalogueItemList;
            lhsOptionalProductCatalogueItemList = (((this.optionalProductCatalogueItemList!= null)&&(!this.optionalProductCatalogueItemList.isEmpty()))?this.getOptionalProductCatalogueItemList():null);
            List<ProductCatalogueItem> rhsOptionalProductCatalogueItemList;
            rhsOptionalProductCatalogueItemList = (((that.optionalProductCatalogueItemList!= null)&&(!that.optionalProductCatalogueItemList.isEmpty()))?that.getOptionalProductCatalogueItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "optionalProductCatalogueItemList", lhsOptionalProductCatalogueItemList), LocatorUtils.property(thatLocator, "optionalProductCatalogueItemList", rhsOptionalProductCatalogueItemList), lhsOptionalProductCatalogueItemList, rhsOptionalProductCatalogueItemList)) {
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
