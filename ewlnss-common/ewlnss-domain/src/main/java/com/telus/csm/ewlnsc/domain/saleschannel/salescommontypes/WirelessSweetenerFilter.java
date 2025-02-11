
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
 * <p>Java class for WirelessSweetenerFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessSweetenerFilter">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessOfferFilter">
 *       &lt;sequence>
 *         &lt;element name="offerCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditClassCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessSweetenerFilter", propOrder = {
    "offerCategoryCode",
    "creditClassCode"
})
public class WirelessSweetenerFilter
    extends WirelessOfferFilter
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String offerCategoryCode;
    protected String creditClassCode;

    /**
     * Gets the value of the offerCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferCategoryCode() {
        return offerCategoryCode;
    }

    /**
     * Sets the value of the offerCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferCategoryCode(String value) {
        this.offerCategoryCode = value;
    }

    /**
     * Gets the value of the creditClassCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditClassCode() {
        return creditClassCode;
    }

    /**
     * Sets the value of the creditClassCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditClassCode(String value) {
        this.creditClassCode = value;
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
            String theOfferCategoryCode;
            theOfferCategoryCode = this.getOfferCategoryCode();
            strategy.appendField(locator, this, "offerCategoryCode", buffer, theOfferCategoryCode);
        }
        {
            String theCreditClassCode;
            theCreditClassCode = this.getCreditClassCode();
            strategy.appendField(locator, this, "creditClassCode", buffer, theCreditClassCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessSweetenerFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelessSweetenerFilter that = ((WirelessSweetenerFilter) object);
        {
            String lhsOfferCategoryCode;
            lhsOfferCategoryCode = this.getOfferCategoryCode();
            String rhsOfferCategoryCode;
            rhsOfferCategoryCode = that.getOfferCategoryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCategoryCode", lhsOfferCategoryCode), LocatorUtils.property(thatLocator, "offerCategoryCode", rhsOfferCategoryCode), lhsOfferCategoryCode, rhsOfferCategoryCode)) {
                return false;
            }
        }
        {
            String lhsCreditClassCode;
            lhsCreditClassCode = this.getCreditClassCode();
            String rhsCreditClassCode;
            rhsCreditClassCode = that.getCreditClassCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditClassCode", lhsCreditClassCode), LocatorUtils.property(thatLocator, "creditClassCode", rhsCreditClassCode), lhsCreditClassCode, rhsCreditClassCode)) {
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
