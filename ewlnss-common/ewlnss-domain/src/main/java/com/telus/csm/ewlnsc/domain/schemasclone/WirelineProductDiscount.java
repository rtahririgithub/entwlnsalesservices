
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * Extends OfferHeader; represents the summarized version of a single offer.
 *             
 * 
 * <p>Java class for WirelineProductDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineProductDiscount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OfferProductHeader">
 *       &lt;sequence>
 *         &lt;element name="discountProductCatalogueIdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineProductDiscount", propOrder = {
    "discountProductCatalogueIdList"
})
public class WirelineProductDiscount
    extends OfferProductHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<String> discountProductCatalogueIdList;

    /**
     * Gets the value of the discountProductCatalogueIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the discountProductCatalogueIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscountProductCatalogueIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDiscountProductCatalogueIdList() {
        if (discountProductCatalogueIdList == null) {
            discountProductCatalogueIdList = new ArrayList<String>();
        }
        return this.discountProductCatalogueIdList;
    }

    /**
     * Sets the value of the discountProductCatalogueIdList property.
     * 
     * @param discountProductCatalogueIdList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountProductCatalogueIdList(List<String> discountProductCatalogueIdList) {
        this.discountProductCatalogueIdList = discountProductCatalogueIdList;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<String> theDiscountProductCatalogueIdList;
            theDiscountProductCatalogueIdList = (((this.discountProductCatalogueIdList!= null)&&(!this.discountProductCatalogueIdList.isEmpty()))?this.getDiscountProductCatalogueIdList():null);
            strategy.appendField(locator, this, "discountProductCatalogueIdList", buffer, theDiscountProductCatalogueIdList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineProductDiscount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineProductDiscount that = ((WirelineProductDiscount) object);
        {
            List<String> lhsDiscountProductCatalogueIdList;
            lhsDiscountProductCatalogueIdList = (((this.discountProductCatalogueIdList!= null)&&(!this.discountProductCatalogueIdList.isEmpty()))?this.getDiscountProductCatalogueIdList():null);
            List<String> rhsDiscountProductCatalogueIdList;
            rhsDiscountProductCatalogueIdList = (((that.discountProductCatalogueIdList!= null)&&(!that.discountProductCatalogueIdList.isEmpty()))?that.getDiscountProductCatalogueIdList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountProductCatalogueIdList", lhsDiscountProductCatalogueIdList), LocatorUtils.property(thatLocator, "discountProductCatalogueIdList", rhsDiscountProductCatalogueIdList), lhsDiscountProductCatalogueIdList, rhsDiscountProductCatalogueIdList)) {
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
