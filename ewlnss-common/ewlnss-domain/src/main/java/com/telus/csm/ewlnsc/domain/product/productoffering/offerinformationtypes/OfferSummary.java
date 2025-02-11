
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.EnterpriseSalesOfferSummary;
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
 * <p>Java class for OfferSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferHeader">
 *       &lt;sequence>
 *         &lt;element name="offerProductSummary" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferProductSummary"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferSummary", propOrder = {
    "offerProductSummary"
})
@XmlSeeAlso({
    EnterpriseSalesOfferSummary.class,
    SweetenerSummary.class
})
public class OfferSummary
    extends OfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected OfferProductSummary offerProductSummary;

    /**
     * Gets the value of the offerProductSummary property.
     * 
     * @return
     *     possible object is
     *     {@link OfferProductSummary }
     *     
     */
    public OfferProductSummary getOfferProductSummary() {
        return offerProductSummary;
    }

    /**
     * Sets the value of the offerProductSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferProductSummary }
     *     
     */
    public void setOfferProductSummary(OfferProductSummary value) {
        this.offerProductSummary = value;
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
            OfferProductSummary theOfferProductSummary;
            theOfferProductSummary = this.getOfferProductSummary();
            strategy.appendField(locator, this, "offerProductSummary", buffer, theOfferProductSummary);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OfferSummary that = ((OfferSummary) object);
        {
            OfferProductSummary lhsOfferProductSummary;
            lhsOfferProductSummary = this.getOfferProductSummary();
            OfferProductSummary rhsOfferProductSummary;
            rhsOfferProductSummary = that.getOfferProductSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProductSummary", lhsOfferProductSummary), LocatorUtils.property(thatLocator, "offerProductSummary", rhsOfferProductSummary), lhsOfferProductSummary, rhsOfferProductSummary)) {
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
