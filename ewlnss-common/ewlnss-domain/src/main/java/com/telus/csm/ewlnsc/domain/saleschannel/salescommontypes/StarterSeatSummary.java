
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
 * VoIP Phone Number containing system generated WLS phone number (not visible to the client), VoIP local phone number, or VoIP toll-free phone number
 * 
 * <p>Java class for StarterSeatSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StarterSeatSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seatDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Seat"/>
 *         &lt;element name="serviceAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address"/>
 *         &lt;element name="consumedVOIPLines" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="maximumAllowedVOIPLines" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="seatStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StarterSeatSummary", propOrder = {
    "seatDetail",
    "serviceAddress",
    "consumedVOIPLines",
    "maximumAllowedVOIPLines",
    "seatStatusCd"
})
public class StarterSeatSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Seat seatDetail;
    @XmlElement(required = true)
    protected Address serviceAddress;
    protected long consumedVOIPLines;
    protected long maximumAllowedVOIPLines;
    protected String seatStatusCd;

    /**
     * Gets the value of the seatDetail property.
     * 
     * @return
     *     possible object is
     *     {@link Seat }
     *     
     */
    public Seat getSeatDetail() {
        return seatDetail;
    }

    /**
     * Sets the value of the seatDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seat }
     *     
     */
    public void setSeatDetail(Seat value) {
        this.seatDetail = value;
    }

    /**
     * Gets the value of the serviceAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getServiceAddress() {
        return serviceAddress;
    }

    /**
     * Sets the value of the serviceAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setServiceAddress(Address value) {
        this.serviceAddress = value;
    }

    /**
     * Gets the value of the consumedVOIPLines property.
     * 
     */
    public long getConsumedVOIPLines() {
        return consumedVOIPLines;
    }

    /**
     * Sets the value of the consumedVOIPLines property.
     * 
     */
    public void setConsumedVOIPLines(long value) {
        this.consumedVOIPLines = value;
    }

    /**
     * Gets the value of the maximumAllowedVOIPLines property.
     * 
     */
    public long getMaximumAllowedVOIPLines() {
        return maximumAllowedVOIPLines;
    }

    /**
     * Sets the value of the maximumAllowedVOIPLines property.
     * 
     */
    public void setMaximumAllowedVOIPLines(long value) {
        this.maximumAllowedVOIPLines = value;
    }

    /**
     * Gets the value of the seatStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatStatusCd() {
        return seatStatusCd;
    }

    /**
     * Sets the value of the seatStatusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatStatusCd(String value) {
        this.seatStatusCd = value;
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
            Seat theSeatDetail;
            theSeatDetail = this.getSeatDetail();
            strategy.appendField(locator, this, "seatDetail", buffer, theSeatDetail);
        }
        {
            Address theServiceAddress;
            theServiceAddress = this.getServiceAddress();
            strategy.appendField(locator, this, "serviceAddress", buffer, theServiceAddress);
        }
        {
            long theConsumedVOIPLines;
            theConsumedVOIPLines = (true?this.getConsumedVOIPLines(): 0L);
            strategy.appendField(locator, this, "consumedVOIPLines", buffer, theConsumedVOIPLines);
        }
        {
            long theMaximumAllowedVOIPLines;
            theMaximumAllowedVOIPLines = (true?this.getMaximumAllowedVOIPLines(): 0L);
            strategy.appendField(locator, this, "maximumAllowedVOIPLines", buffer, theMaximumAllowedVOIPLines);
        }
        {
            String theSeatStatusCd;
            theSeatStatusCd = this.getSeatStatusCd();
            strategy.appendField(locator, this, "seatStatusCd", buffer, theSeatStatusCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof StarterSeatSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final StarterSeatSummary that = ((StarterSeatSummary) object);
        {
            Seat lhsSeatDetail;
            lhsSeatDetail = this.getSeatDetail();
            Seat rhsSeatDetail;
            rhsSeatDetail = that.getSeatDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seatDetail", lhsSeatDetail), LocatorUtils.property(thatLocator, "seatDetail", rhsSeatDetail), lhsSeatDetail, rhsSeatDetail)) {
                return false;
            }
        }
        {
            Address lhsServiceAddress;
            lhsServiceAddress = this.getServiceAddress();
            Address rhsServiceAddress;
            rhsServiceAddress = that.getServiceAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddress", lhsServiceAddress), LocatorUtils.property(thatLocator, "serviceAddress", rhsServiceAddress), lhsServiceAddress, rhsServiceAddress)) {
                return false;
            }
        }
        {
            long lhsConsumedVOIPLines;
            lhsConsumedVOIPLines = (true?this.getConsumedVOIPLines(): 0L);
            long rhsConsumedVOIPLines;
            rhsConsumedVOIPLines = (true?that.getConsumedVOIPLines(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "consumedVOIPLines", lhsConsumedVOIPLines), LocatorUtils.property(thatLocator, "consumedVOIPLines", rhsConsumedVOIPLines), lhsConsumedVOIPLines, rhsConsumedVOIPLines)) {
                return false;
            }
        }
        {
            long lhsMaximumAllowedVOIPLines;
            lhsMaximumAllowedVOIPLines = (true?this.getMaximumAllowedVOIPLines(): 0L);
            long rhsMaximumAllowedVOIPLines;
            rhsMaximumAllowedVOIPLines = (true?that.getMaximumAllowedVOIPLines(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maximumAllowedVOIPLines", lhsMaximumAllowedVOIPLines), LocatorUtils.property(thatLocator, "maximumAllowedVOIPLines", rhsMaximumAllowedVOIPLines), lhsMaximumAllowedVOIPLines, rhsMaximumAllowedVOIPLines)) {
                return false;
            }
        }
        {
            String lhsSeatStatusCd;
            lhsSeatStatusCd = this.getSeatStatusCd();
            String rhsSeatStatusCd;
            rhsSeatStatusCd = that.getSeatStatusCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seatStatusCd", lhsSeatStatusCd), LocatorUtils.property(thatLocator, "seatStatusCd", rhsSeatStatusCd), lhsSeatStatusCd, rhsSeatStatusCd)) {
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
