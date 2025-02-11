
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 * <p>Java class for PricePlanDetailURLItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricePlanDetailURLItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="locale" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}localeType"/>
 *         &lt;element name="pricePlanDetailURL" type="{http://www.w3.org/2001/XMLSchema}anyURI"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricePlanDetailURLItem", propOrder = {
    "locale",
    "pricePlanDetailURL"
})
public class PricePlanDetailURLItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String locale;
    @XmlElement(required = true)
    @XmlSchemaType(name = "anyURI")
    protected String pricePlanDetailURL;

    /**
     * Gets the value of the locale property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocale() {
        return locale;
    }

    /**
     * Sets the value of the locale property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocale(String value) {
        this.locale = value;
    }

    /**
     * Gets the value of the pricePlanDetailURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanDetailURL() {
        return pricePlanDetailURL;
    }

    /**
     * Sets the value of the pricePlanDetailURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanDetailURL(String value) {
        this.pricePlanDetailURL = value;
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
            String theLocale;
            theLocale = this.getLocale();
            strategy.appendField(locator, this, "locale", buffer, theLocale);
        }
        {
            String thePricePlanDetailURL;
            thePricePlanDetailURL = this.getPricePlanDetailURL();
            strategy.appendField(locator, this, "pricePlanDetailURL", buffer, thePricePlanDetailURL);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PricePlanDetailURLItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PricePlanDetailURLItem that = ((PricePlanDetailURLItem) object);
        {
            String lhsLocale;
            lhsLocale = this.getLocale();
            String rhsLocale;
            rhsLocale = that.getLocale();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locale", lhsLocale), LocatorUtils.property(thatLocator, "locale", rhsLocale), lhsLocale, rhsLocale)) {
                return false;
            }
        }
        {
            String lhsPricePlanDetailURL;
            lhsPricePlanDetailURL = this.getPricePlanDetailURL();
            String rhsPricePlanDetailURL;
            rhsPricePlanDetailURL = that.getPricePlanDetailURL();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanDetailURL", lhsPricePlanDetailURL), LocatorUtils.property(thatLocator, "pricePlanDetailURL", rhsPricePlanDetailURL), lhsPricePlanDetailURL, rhsPricePlanDetailURL)) {
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
