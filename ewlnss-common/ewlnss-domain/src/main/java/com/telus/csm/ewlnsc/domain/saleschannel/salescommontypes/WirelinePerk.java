
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
 * <p>Java class for WirelinePerk complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelinePerk">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PerkSummary">
 *       &lt;sequence>
 *         &lt;element name="productPromotionList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelinePromotion" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelinePerk", propOrder = {
    "productPromotionList"
})
public class WirelinePerk
    extends PerkSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<WirelinePromotion> productPromotionList;

    /**
     * Gets the value of the productPromotionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productPromotionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductPromotionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelinePromotion }
     * 
     * 
     */
    public List<WirelinePromotion> getProductPromotionList() {
        if (productPromotionList == null) {
            productPromotionList = new ArrayList<WirelinePromotion>();
        }
        return this.productPromotionList;
    }

    /**
     * Sets the value of the productPromotionList property.
     * 
     * @param productPromotionList
     *     allowed object is
     *     {@link WirelinePromotion }
     *     
     */
    public void setProductPromotionList(List<WirelinePromotion> productPromotionList) {
        this.productPromotionList = productPromotionList;
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
            List<WirelinePromotion> theProductPromotionList;
            theProductPromotionList = (((this.productPromotionList!= null)&&(!this.productPromotionList.isEmpty()))?this.getProductPromotionList():null);
            strategy.appendField(locator, this, "productPromotionList", buffer, theProductPromotionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelinePerk)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelinePerk that = ((WirelinePerk) object);
        {
            List<WirelinePromotion> lhsProductPromotionList;
            lhsProductPromotionList = (((this.productPromotionList!= null)&&(!this.productPromotionList.isEmpty()))?this.getProductPromotionList():null);
            List<WirelinePromotion> rhsProductPromotionList;
            rhsProductPromotionList = (((that.productPromotionList!= null)&&(!that.productPromotionList.isEmpty()))?that.getProductPromotionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productPromotionList", lhsProductPromotionList), LocatorUtils.property(thatLocator, "productPromotionList", rhsProductPromotionList), lhsProductPromotionList, rhsProductPromotionList)) {
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
