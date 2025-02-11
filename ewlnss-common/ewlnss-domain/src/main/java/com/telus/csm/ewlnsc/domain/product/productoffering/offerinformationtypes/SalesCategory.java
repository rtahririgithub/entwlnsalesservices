
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
 * Generic sales category definition
 * 
 * <p>Java class for SalesCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="salesCategoryNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2" minOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesCategory", propOrder = {
    "salesCategoryCode",
    "salesCategoryNameList"
})
public class SalesCategory
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String salesCategoryCode;
    @XmlElement(required = true)
    protected List<Description> salesCategoryNameList;

    /**
     * Gets the value of the salesCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesCategoryCode() {
        return salesCategoryCode;
    }

    /**
     * Sets the value of the salesCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesCategoryCode(String value) {
        this.salesCategoryCode = value;
    }

    /**
     * Gets the value of the salesCategoryNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesCategoryNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesCategoryNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getSalesCategoryNameList() {
        if (salesCategoryNameList == null) {
            salesCategoryNameList = new ArrayList<Description>();
        }
        return this.salesCategoryNameList;
    }

    /**
     * Sets the value of the salesCategoryNameList property.
     * 
     * @param salesCategoryNameList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setSalesCategoryNameList(List<Description> salesCategoryNameList) {
        this.salesCategoryNameList = salesCategoryNameList;
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
            String theSalesCategoryCode;
            theSalesCategoryCode = this.getSalesCategoryCode();
            strategy.appendField(locator, this, "salesCategoryCode", buffer, theSalesCategoryCode);
        }
        {
            List<Description> theSalesCategoryNameList;
            theSalesCategoryNameList = (((this.salesCategoryNameList!= null)&&(!this.salesCategoryNameList.isEmpty()))?this.getSalesCategoryNameList():null);
            strategy.appendField(locator, this, "salesCategoryNameList", buffer, theSalesCategoryNameList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesCategory)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesCategory that = ((SalesCategory) object);
        {
            String lhsSalesCategoryCode;
            lhsSalesCategoryCode = this.getSalesCategoryCode();
            String rhsSalesCategoryCode;
            rhsSalesCategoryCode = that.getSalesCategoryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesCategoryCode", lhsSalesCategoryCode), LocatorUtils.property(thatLocator, "salesCategoryCode", rhsSalesCategoryCode), lhsSalesCategoryCode, rhsSalesCategoryCode)) {
                return false;
            }
        }
        {
            List<Description> lhsSalesCategoryNameList;
            lhsSalesCategoryNameList = (((this.salesCategoryNameList!= null)&&(!this.salesCategoryNameList.isEmpty()))?this.getSalesCategoryNameList():null);
            List<Description> rhsSalesCategoryNameList;
            rhsSalesCategoryNameList = (((that.salesCategoryNameList!= null)&&(!that.salesCategoryNameList.isEmpty()))?that.getSalesCategoryNameList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesCategoryNameList", lhsSalesCategoryNameList), LocatorUtils.property(thatLocator, "salesCategoryNameList", rhsSalesCategoryNameList), lhsSalesCategoryNameList, rhsSalesCategoryNameList)) {
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
