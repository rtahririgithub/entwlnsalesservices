
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for Category complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Category">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="categoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="categoryDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Category", propOrder = {
    "categoryCode",
    "categoryDescriptionList"
})
public class Category
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String categoryCode;
    @XmlElement(required = true)
    protected List<Description> categoryDescriptionList;

    /**
     * Gets the value of the categoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryCode() {
        return categoryCode;
    }

    /**
     * Sets the value of the categoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryCode(String value) {
        this.categoryCode = value;
    }

    /**
     * Gets the value of the categoryDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the categoryDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategoryDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getCategoryDescriptionList() {
        if (categoryDescriptionList == null) {
            categoryDescriptionList = new ArrayList<Description>();
        }
        return this.categoryDescriptionList;
    }

    /**
     * Sets the value of the categoryDescriptionList property.
     * 
     * @param categoryDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setCategoryDescriptionList(List<Description> categoryDescriptionList) {
        this.categoryDescriptionList = categoryDescriptionList;
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
            String theCategoryCode;
            theCategoryCode = this.getCategoryCode();
            strategy.appendField(locator, this, "categoryCode", buffer, theCategoryCode);
        }
        {
            List<Description> theCategoryDescriptionList;
            theCategoryDescriptionList = (((this.categoryDescriptionList!= null)&&(!this.categoryDescriptionList.isEmpty()))?this.getCategoryDescriptionList():null);
            strategy.appendField(locator, this, "categoryDescriptionList", buffer, theCategoryDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Category)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Category that = ((Category) object);
        {
            String lhsCategoryCode;
            lhsCategoryCode = this.getCategoryCode();
            String rhsCategoryCode;
            rhsCategoryCode = that.getCategoryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "categoryCode", lhsCategoryCode), LocatorUtils.property(thatLocator, "categoryCode", rhsCategoryCode), lhsCategoryCode, rhsCategoryCode)) {
                return false;
            }
        }
        {
            List<Description> lhsCategoryDescriptionList;
            lhsCategoryDescriptionList = (((this.categoryDescriptionList!= null)&&(!this.categoryDescriptionList.isEmpty()))?this.getCategoryDescriptionList():null);
            List<Description> rhsCategoryDescriptionList;
            rhsCategoryDescriptionList = (((that.categoryDescriptionList!= null)&&(!that.categoryDescriptionList.isEmpty()))?that.getCategoryDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "categoryDescriptionList", lhsCategoryDescriptionList), LocatorUtils.property(thatLocator, "categoryDescriptionList", rhsCategoryDescriptionList), lhsCategoryDescriptionList, rhsCategoryDescriptionList)) {
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
