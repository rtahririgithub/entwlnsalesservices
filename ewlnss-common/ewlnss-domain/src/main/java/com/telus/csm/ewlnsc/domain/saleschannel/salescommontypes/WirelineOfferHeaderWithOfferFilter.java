
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for WirelineOfferHeaderWithOfferFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineOfferHeaderWithOfferFilter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferHeader">
 *       &lt;sequence>
 *         &lt;element name="offerFilter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferFilter" minOccurs="0"/>
 *         &lt;element name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineOfferHeaderWithOfferFilter", propOrder = {
    "offerFilter",
    "promotionCode"
})
public class WirelineOfferHeaderWithOfferFilter
    extends WirelineOfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelineOfferFilter offerFilter;
    protected String promotionCode;

    /**
     * Gets the value of the offerFilter property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineOfferFilter }
     *     
     */
    public WirelineOfferFilter getOfferFilter() {
        return offerFilter;
    }

    /**
     * Sets the value of the offerFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineOfferFilter }
     *     
     */
    public void setOfferFilter(WirelineOfferFilter value) {
        this.offerFilter = value;
    }

    /**
     * Gets the value of the promotionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Sets the value of the promotionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
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
            WirelineOfferFilter theOfferFilter;
            theOfferFilter = this.getOfferFilter();
            strategy.appendField(locator, this, "offerFilter", buffer, theOfferFilter);
        }
        {
            String thePromotionCode;
            thePromotionCode = this.getPromotionCode();
            strategy.appendField(locator, this, "promotionCode", buffer, thePromotionCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineOfferHeaderWithOfferFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineOfferHeaderWithOfferFilter that = ((WirelineOfferHeaderWithOfferFilter) object);
        {
            WirelineOfferFilter lhsOfferFilter;
            lhsOfferFilter = this.getOfferFilter();
            WirelineOfferFilter rhsOfferFilter;
            rhsOfferFilter = that.getOfferFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerFilter", lhsOfferFilter), LocatorUtils.property(thatLocator, "offerFilter", rhsOfferFilter), lhsOfferFilter, rhsOfferFilter)) {
                return false;
            }
        }
        {
            String lhsPromotionCode;
            lhsPromotionCode = this.getPromotionCode();
            String rhsPromotionCode;
            rhsPromotionCode = that.getPromotionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionCode", lhsPromotionCode), LocatorUtils.property(thatLocator, "promotionCode", rhsPromotionCode), lhsPromotionCode, rhsPromotionCode)) {
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
