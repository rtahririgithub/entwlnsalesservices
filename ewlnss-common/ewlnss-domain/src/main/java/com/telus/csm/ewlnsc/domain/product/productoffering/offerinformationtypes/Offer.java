
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * Extends OfferHeader; defines a single offer with complete offer details.
 * 
 * <p>Java class for Offer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Offer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferHeader">
 *       &lt;sequence>
 *         &lt;element name="offerProduct" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferProduct"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Offer", propOrder = {
    "offerProduct"
})
@XmlSeeAlso({
    Sweetener.class
})
public class Offer
    extends OfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected OfferProduct offerProduct;

    /**
     * Gets the value of the offerProduct property.
     * 
     * @return
     *     possible object is
     *     {@link OfferProduct }
     *     
     */
    public OfferProduct getOfferProduct() {
        return offerProduct;
    }

    /**
     * Sets the value of the offerProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferProduct }
     *     
     */
    public void setOfferProduct(OfferProduct value) {
        this.offerProduct = value;
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
            OfferProduct theOfferProduct;
            theOfferProduct = this.getOfferProduct();
            strategy.appendField(locator, this, "offerProduct", buffer, theOfferProduct);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Offer)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Offer that = ((Offer) object);
        {
            OfferProduct lhsOfferProduct;
            lhsOfferProduct = this.getOfferProduct();
            OfferProduct rhsOfferProduct;
            rhsOfferProduct = that.getOfferProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProduct", lhsOfferProduct), LocatorUtils.property(thatLocator, "offerProduct", rhsOfferProduct), lhsOfferProduct, rhsOfferProduct)) {
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
