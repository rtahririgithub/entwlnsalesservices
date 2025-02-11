
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for WirelineSalesOrderOfferHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineSalesOrderOfferHeader">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferHeader">
 *       &lt;sequence>
 *         &lt;element name="offerFilter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineSalesOrderOfferFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineSalesOrderOfferHeader", propOrder = {
    "offerFilter"
})
public class WirelineSalesOrderOfferHeader
    extends WirelineOfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelineSalesOrderOfferFilter offerFilter;

    /**
     * Gets the value of the offerFilter property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineSalesOrderOfferFilter }
     *     
     */
    public WirelineSalesOrderOfferFilter getOfferFilter() {
        return offerFilter;
    }

    /**
     * Sets the value of the offerFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineSalesOrderOfferFilter }
     *     
     */
    public void setOfferFilter(WirelineSalesOrderOfferFilter value) {
        this.offerFilter = value;
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
            WirelineSalesOrderOfferFilter theOfferFilter;
            theOfferFilter = this.getOfferFilter();
            strategy.appendField(locator, this, "offerFilter", buffer, theOfferFilter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineSalesOrderOfferHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineSalesOrderOfferHeader that = ((WirelineSalesOrderOfferHeader) object);
        {
            WirelineSalesOrderOfferFilter lhsOfferFilter;
            lhsOfferFilter = this.getOfferFilter();
            WirelineSalesOrderOfferFilter rhsOfferFilter;
            rhsOfferFilter = that.getOfferFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerFilter", lhsOfferFilter), LocatorUtils.property(thatLocator, "offerFilter", rhsOfferFilter), lhsOfferFilter, rhsOfferFilter)) {
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
