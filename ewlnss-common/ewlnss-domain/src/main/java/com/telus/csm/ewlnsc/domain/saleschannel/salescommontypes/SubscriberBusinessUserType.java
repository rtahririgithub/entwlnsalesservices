
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
 * <p>Java class for SubscriberBusinessUserType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberBusinessUserType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seat" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Seat" minOccurs="0"/>
 *         &lt;element name="existingBundle" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BundleSummary" minOccurs="0"/>
 *         &lt;element name="availableVoipLineQuantity" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberBusinessUserType", propOrder = {
    "seat",
    "existingBundle",
    "availableVoipLineQuantity"
})
public class SubscriberBusinessUserType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Seat seat;
    protected BundleSummary existingBundle;
    protected Long availableVoipLineQuantity;

    /**
     * Gets the value of the seat property.
     * 
     * @return
     *     possible object is
     *     {@link Seat }
     *     
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Sets the value of the seat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seat }
     *     
     */
    public void setSeat(Seat value) {
        this.seat = value;
    }

    /**
     * Gets the value of the existingBundle property.
     * 
     * @return
     *     possible object is
     *     {@link BundleSummary }
     *     
     */
    public BundleSummary getExistingBundle() {
        return existingBundle;
    }

    /**
     * Sets the value of the existingBundle property.
     * 
     * @param value
     *     allowed object is
     *     {@link BundleSummary }
     *     
     */
    public void setExistingBundle(BundleSummary value) {
        this.existingBundle = value;
    }

    /**
     * Gets the value of the availableVoipLineQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getAvailableVoipLineQuantity() {
        return availableVoipLineQuantity;
    }

    /**
     * Sets the value of the availableVoipLineQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setAvailableVoipLineQuantity(Long value) {
        this.availableVoipLineQuantity = value;
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
            Seat theSeat;
            theSeat = this.getSeat();
            strategy.appendField(locator, this, "seat", buffer, theSeat);
        }
        {
            BundleSummary theExistingBundle;
            theExistingBundle = this.getExistingBundle();
            strategy.appendField(locator, this, "existingBundle", buffer, theExistingBundle);
        }
        {
            Long theAvailableVoipLineQuantity;
            theAvailableVoipLineQuantity = this.getAvailableVoipLineQuantity();
            strategy.appendField(locator, this, "availableVoipLineQuantity", buffer, theAvailableVoipLineQuantity);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SubscriberBusinessUserType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SubscriberBusinessUserType that = ((SubscriberBusinessUserType) object);
        {
            Seat lhsSeat;
            lhsSeat = this.getSeat();
            Seat rhsSeat;
            rhsSeat = that.getSeat();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seat", lhsSeat), LocatorUtils.property(thatLocator, "seat", rhsSeat), lhsSeat, rhsSeat)) {
                return false;
            }
        }
        {
            BundleSummary lhsExistingBundle;
            lhsExistingBundle = this.getExistingBundle();
            BundleSummary rhsExistingBundle;
            rhsExistingBundle = that.getExistingBundle();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "existingBundle", lhsExistingBundle), LocatorUtils.property(thatLocator, "existingBundle", rhsExistingBundle), lhsExistingBundle, rhsExistingBundle)) {
                return false;
            }
        }
        {
            Long lhsAvailableVoipLineQuantity;
            lhsAvailableVoipLineQuantity = this.getAvailableVoipLineQuantity();
            Long rhsAvailableVoipLineQuantity;
            rhsAvailableVoipLineQuantity = that.getAvailableVoipLineQuantity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "availableVoipLineQuantity", lhsAvailableVoipLineQuantity), LocatorUtils.property(thatLocator, "availableVoipLineQuantity", rhsAvailableVoipLineQuantity), lhsAvailableVoipLineQuantity, rhsAvailableVoipLineQuantity)) {
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
