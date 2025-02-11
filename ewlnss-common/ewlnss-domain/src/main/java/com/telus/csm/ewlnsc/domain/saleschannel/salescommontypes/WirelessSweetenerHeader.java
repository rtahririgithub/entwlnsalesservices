
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
 * <p>Java class for WirelessSweetenerHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessSweetenerHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sweetenerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sweetenerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sweetenerVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sweetenerFilter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessSweetenerFilter" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessSweetenerHeader", propOrder = {
    "sweetenerId",
    "sweetenerCode",
    "sweetenerVersion",
    "sweetenerFilter"
})
public class WirelessSweetenerHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String sweetenerId;
    protected String sweetenerCode;
    protected String sweetenerVersion;
    protected WirelessSweetenerFilter sweetenerFilter;

    /**
     * Gets the value of the sweetenerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSweetenerId() {
        return sweetenerId;
    }

    /**
     * Sets the value of the sweetenerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSweetenerId(String value) {
        this.sweetenerId = value;
    }

    /**
     * Gets the value of the sweetenerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSweetenerCode() {
        return sweetenerCode;
    }

    /**
     * Sets the value of the sweetenerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSweetenerCode(String value) {
        this.sweetenerCode = value;
    }

    /**
     * Gets the value of the sweetenerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSweetenerVersion() {
        return sweetenerVersion;
    }

    /**
     * Sets the value of the sweetenerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSweetenerVersion(String value) {
        this.sweetenerVersion = value;
    }

    /**
     * Gets the value of the sweetenerFilter property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessSweetenerFilter }
     *     
     */
    public WirelessSweetenerFilter getSweetenerFilter() {
        return sweetenerFilter;
    }

    /**
     * Sets the value of the sweetenerFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessSweetenerFilter }
     *     
     */
    public void setSweetenerFilter(WirelessSweetenerFilter value) {
        this.sweetenerFilter = value;
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
            String theSweetenerId;
            theSweetenerId = this.getSweetenerId();
            strategy.appendField(locator, this, "sweetenerId", buffer, theSweetenerId);
        }
        {
            String theSweetenerCode;
            theSweetenerCode = this.getSweetenerCode();
            strategy.appendField(locator, this, "sweetenerCode", buffer, theSweetenerCode);
        }
        {
            String theSweetenerVersion;
            theSweetenerVersion = this.getSweetenerVersion();
            strategy.appendField(locator, this, "sweetenerVersion", buffer, theSweetenerVersion);
        }
        {
            WirelessSweetenerFilter theSweetenerFilter;
            theSweetenerFilter = this.getSweetenerFilter();
            strategy.appendField(locator, this, "sweetenerFilter", buffer, theSweetenerFilter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessSweetenerHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessSweetenerHeader that = ((WirelessSweetenerHeader) object);
        {
            String lhsSweetenerId;
            lhsSweetenerId = this.getSweetenerId();
            String rhsSweetenerId;
            rhsSweetenerId = that.getSweetenerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerId", lhsSweetenerId), LocatorUtils.property(thatLocator, "sweetenerId", rhsSweetenerId), lhsSweetenerId, rhsSweetenerId)) {
                return false;
            }
        }
        {
            String lhsSweetenerCode;
            lhsSweetenerCode = this.getSweetenerCode();
            String rhsSweetenerCode;
            rhsSweetenerCode = that.getSweetenerCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerCode", lhsSweetenerCode), LocatorUtils.property(thatLocator, "sweetenerCode", rhsSweetenerCode), lhsSweetenerCode, rhsSweetenerCode)) {
                return false;
            }
        }
        {
            String lhsSweetenerVersion;
            lhsSweetenerVersion = this.getSweetenerVersion();
            String rhsSweetenerVersion;
            rhsSweetenerVersion = that.getSweetenerVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerVersion", lhsSweetenerVersion), LocatorUtils.property(thatLocator, "sweetenerVersion", rhsSweetenerVersion), lhsSweetenerVersion, rhsSweetenerVersion)) {
                return false;
            }
        }
        {
            WirelessSweetenerFilter lhsSweetenerFilter;
            lhsSweetenerFilter = this.getSweetenerFilter();
            WirelessSweetenerFilter rhsSweetenerFilter;
            rhsSweetenerFilter = that.getSweetenerFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerFilter", lhsSweetenerFilter), LocatorUtils.property(thatLocator, "sweetenerFilter", rhsSweetenerFilter), lhsSweetenerFilter, rhsSweetenerFilter)) {
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
