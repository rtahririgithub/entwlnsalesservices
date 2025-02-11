
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
 * <p>Java class for PricePlanCategoryList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricePlanCategoryList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricePlanCategory" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PricePlanCategory" maxOccurs="10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricePlanCategoryList", propOrder = {
    "pricePlanCategory"
})
public class PricePlanCategoryList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<PricePlanCategory> pricePlanCategory;

    /**
     * Gets the value of the pricePlanCategory property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanCategory property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanCategory().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanCategory }
     * 
     * 
     */
    public List<PricePlanCategory> getPricePlanCategory() {
        if (pricePlanCategory == null) {
            pricePlanCategory = new ArrayList<PricePlanCategory>();
        }
        return this.pricePlanCategory;
    }

    /**
     * Sets the value of the pricePlanCategory property.
     * 
     * @param pricePlanCategory
     *     allowed object is
     *     {@link PricePlanCategory }
     *     
     */
    public void setPricePlanCategory(List<PricePlanCategory> pricePlanCategory) {
        this.pricePlanCategory = pricePlanCategory;
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
            List<PricePlanCategory> thePricePlanCategory;
            thePricePlanCategory = (((this.pricePlanCategory!= null)&&(!this.pricePlanCategory.isEmpty()))?this.getPricePlanCategory():null);
            strategy.appendField(locator, this, "pricePlanCategory", buffer, thePricePlanCategory);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PricePlanCategoryList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PricePlanCategoryList that = ((PricePlanCategoryList) object);
        {
            List<PricePlanCategory> lhsPricePlanCategory;
            lhsPricePlanCategory = (((this.pricePlanCategory!= null)&&(!this.pricePlanCategory.isEmpty()))?this.getPricePlanCategory():null);
            List<PricePlanCategory> rhsPricePlanCategory;
            rhsPricePlanCategory = (((that.pricePlanCategory!= null)&&(!that.pricePlanCategory.isEmpty()))?that.getPricePlanCategory():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCategory", lhsPricePlanCategory), LocatorUtils.property(thatLocator, "pricePlanCategory", rhsPricePlanCategory), lhsPricePlanCategory, rhsPricePlanCategory)) {
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
