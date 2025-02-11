
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * Wireline promotion discount
 * 
 * <p>Java class for WirelinePromotionDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelinePromotionDiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductCatalogueIdentifier"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelinePromotionDiscount", propOrder = {
    "discountIdentifier"
})
public class WirelinePromotionDiscount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductCatalogueIdentifier discountIdentifier;

    /**
     * Gets the value of the discountIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getDiscountIdentifier() {
        return discountIdentifier;
    }

    /**
     * Sets the value of the discountIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setDiscountIdentifier(ProductCatalogueIdentifier value) {
        this.discountIdentifier = value;
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
            ProductCatalogueIdentifier theDiscountIdentifier;
            theDiscountIdentifier = this.getDiscountIdentifier();
            strategy.appendField(locator, this, "discountIdentifier", buffer, theDiscountIdentifier);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelinePromotionDiscount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelinePromotionDiscount that = ((WirelinePromotionDiscount) object);
        {
            ProductCatalogueIdentifier lhsDiscountIdentifier;
            lhsDiscountIdentifier = this.getDiscountIdentifier();
            ProductCatalogueIdentifier rhsDiscountIdentifier;
            rhsDiscountIdentifier = that.getDiscountIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountIdentifier", lhsDiscountIdentifier), LocatorUtils.property(thatLocator, "discountIdentifier", rhsDiscountIdentifier), lhsDiscountIdentifier, rhsDiscountIdentifier)) {
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
