
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Discount;
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
 * <p>Java class for WirelineOfferProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineOfferProductSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelineOfferProductSummary">
 *       &lt;sequence>
 *         &lt;element name="discountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Discount" maxOccurs="100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineOfferProductSummary", propOrder = {
    "discountList"
})
public class WirelineOfferProductSummary
    extends com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.WirelineOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<Discount> discountList;

    /**
     * Gets the value of the discountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the discountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Discount }
     * 
     * 
     */
    public List<Discount> getDiscountList() {
        if (discountList == null) {
            discountList = new ArrayList<Discount>();
        }
        return this.discountList;
    }

    /**
     * Sets the value of the discountList property.
     * 
     * @param discountList
     *     allowed object is
     *     {@link Discount }
     *     
     */
    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
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
            List<Discount> theDiscountList;
            theDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            strategy.appendField(locator, this, "discountList", buffer, theDiscountList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineOfferProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineOfferProductSummary that = ((WirelineOfferProductSummary) object);
        {
            List<Discount> lhsDiscountList;
            lhsDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            List<Discount> rhsDiscountList;
            rhsDiscountList = (((that.discountList!= null)&&(!that.discountList.isEmpty()))?that.getDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountList", lhsDiscountList), LocatorUtils.property(thatLocator, "discountList", rhsDiscountList), lhsDiscountList, rhsDiscountList)) {
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
