
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for OfferCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Category" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferCategory", propOrder = {
    "categoryTypeCode",
    "categoryList"
})
public class OfferCategory
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String categoryTypeCode;
    protected List<Category> categoryList;

    /**
     * Gets the value of the categoryTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryTypeCode() {
        return categoryTypeCode;
    }

    /**
     * Sets the value of the categoryTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryTypeCode(String value) {
        this.categoryTypeCode = value;
    }

    /**
     * Gets the value of the categoryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Category }
     * 
     * 
     */
    public List<Category> getCategoryList() {
        if (categoryList == null) {
            categoryList = new ArrayList<Category>();
        }
        return this.categoryList;
    }

    /**
     * Sets the value of the categoryList property.
     * 
     * @param categoryList
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
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
            String theCategoryTypeCode;
            theCategoryTypeCode = this.getCategoryTypeCode();
            strategy.appendField(locator, this, "categoryTypeCode", buffer, theCategoryTypeCode);
        }
        {
            List<Category> theCategoryList;
            theCategoryList = (((this.categoryList!= null)&&(!this.categoryList.isEmpty()))?this.getCategoryList():null);
            strategy.appendField(locator, this, "categoryList", buffer, theCategoryList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferCategory)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OfferCategory that = ((OfferCategory) object);
        {
            String lhsCategoryTypeCode;
            lhsCategoryTypeCode = this.getCategoryTypeCode();
            String rhsCategoryTypeCode;
            rhsCategoryTypeCode = that.getCategoryTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "categoryTypeCode", lhsCategoryTypeCode), LocatorUtils.property(thatLocator, "categoryTypeCode", rhsCategoryTypeCode), lhsCategoryTypeCode, rhsCategoryTypeCode)) {
                return false;
            }
        }
        {
            List<Category> lhsCategoryList;
            lhsCategoryList = (((this.categoryList!= null)&&(!this.categoryList.isEmpty()))?this.getCategoryList():null);
            List<Category> rhsCategoryList;
            rhsCategoryList = (((that.categoryList!= null)&&(!that.categoryList.isEmpty()))?that.getCategoryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "categoryList", lhsCategoryList), LocatorUtils.property(thatLocator, "categoryList", rhsCategoryList), lhsCategoryList, rhsCategoryList)) {
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
