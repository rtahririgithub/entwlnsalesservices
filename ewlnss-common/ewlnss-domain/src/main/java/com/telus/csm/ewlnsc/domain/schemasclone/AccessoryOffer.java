
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.OfferProduct;
import com.telus.tmi.xmlschema.xsd.product.productoffering.offerinformationtypes_v3.Program;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for AccessoryOffer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOffer">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerProgram" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Program"/>
 *         &lt;element name="offerHeader" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}OfferHeader"/>
 *         &lt;element name="offerProduct" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferProduct" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOffer", propOrder = {
    "offerProgram",
    "offerHeader",
    "offerProduct"
})
public class AccessoryOffer
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Program offerProgram;
    @XmlElement(required = true)
    protected OfferHeader offerHeader;
    protected OfferProduct offerProduct;

    /**
     * Gets the value of the offerProgram property.
     * 
     * @return
     *     possible object is
     *     {@link Program }
     *     
     */
    public Program getOfferProgram() {
        return offerProgram;
    }

    /**
     * Sets the value of the offerProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link Program }
     *     
     */
    public void setOfferProgram(Program value) {
        this.offerProgram = value;
    }

    /**
     * Gets the value of the offerHeader property.
     * 
     * @return
     *     possible object is
     *     {@link OfferHeader }
     *     
     */
    public OfferHeader getOfferHeader() {
        return offerHeader;
    }

    /**
     * Sets the value of the offerHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferHeader }
     *     
     */
    public void setOfferHeader(OfferHeader value) {
        this.offerHeader = value;
    }

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
        {
            Program theOfferProgram;
            theOfferProgram = this.getOfferProgram();
            strategy.appendField(locator, this, "offerProgram", buffer, theOfferProgram);
        }
        {
            OfferHeader theOfferHeader;
            theOfferHeader = this.getOfferHeader();
            strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
        }
        {
            OfferProduct theOfferProduct;
            theOfferProduct = this.getOfferProduct();
            strategy.appendField(locator, this, "offerProduct", buffer, theOfferProduct);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOffer)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOffer that = ((AccessoryOffer) object);
        {
            Program lhsOfferProgram;
            lhsOfferProgram = this.getOfferProgram();
            Program rhsOfferProgram;
            rhsOfferProgram = that.getOfferProgram();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProgram", lhsOfferProgram), LocatorUtils.property(thatLocator, "offerProgram", rhsOfferProgram), lhsOfferProgram, rhsOfferProgram)) {
                return false;
            }
        }
        {
            OfferHeader lhsOfferHeader;
            lhsOfferHeader = this.getOfferHeader();
            OfferHeader rhsOfferHeader;
            rhsOfferHeader = that.getOfferHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
                return false;
            }
        }
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
