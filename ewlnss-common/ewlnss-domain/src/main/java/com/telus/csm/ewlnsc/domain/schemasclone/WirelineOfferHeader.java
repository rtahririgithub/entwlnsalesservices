
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelineOfferHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineOfferHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerProgramId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perspectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineOfferHeader", propOrder = {
    "offerInstanceId",
    "offerId",
    "offerCode",
    "offerProgramId",
    "systemId",
    "perspectiveDate"
})
@XmlSeeAlso({
    WirelineSalesOrderOfferHeader.class,
    WirelineOfferHeaderWithOfferFilter.class
})
public class WirelineOfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String offerInstanceId;
    protected String offerId;
    protected String offerCode;
    protected String offerProgramId;
    protected String systemId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date perspectiveDate;

    /**
     * Gets the value of the offerInstanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferInstanceId() {
        return offerInstanceId;
    }

    /**
     * Sets the value of the offerInstanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferInstanceId(String value) {
        this.offerInstanceId = value;
    }

    /**
     * Gets the value of the offerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * Sets the value of the offerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferId(String value) {
        this.offerId = value;
    }

    /**
     * Gets the value of the offerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferCode() {
        return offerCode;
    }

    /**
     * Sets the value of the offerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferCode(String value) {
        this.offerCode = value;
    }

    /**
     * Gets the value of the offerProgramId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferProgramId() {
        return offerProgramId;
    }

    /**
     * Sets the value of the offerProgramId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferProgramId(String value) {
        this.offerProgramId = value;
    }

    /**
     * Gets the value of the systemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the value of the systemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * Gets the value of the perspectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPerspectiveDate() {
        return perspectiveDate;
    }

    /**
     * Sets the value of the perspectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerspectiveDate(Date value) {
        this.perspectiveDate = value;
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
            String theOfferInstanceId;
            theOfferInstanceId = this.getOfferInstanceId();
            strategy.appendField(locator, this, "offerInstanceId", buffer, theOfferInstanceId);
        }
        {
            String theOfferId;
            theOfferId = this.getOfferId();
            strategy.appendField(locator, this, "offerId", buffer, theOfferId);
        }
        {
            String theOfferCode;
            theOfferCode = this.getOfferCode();
            strategy.appendField(locator, this, "offerCode", buffer, theOfferCode);
        }
        {
            String theOfferProgramId;
            theOfferProgramId = this.getOfferProgramId();
            strategy.appendField(locator, this, "offerProgramId", buffer, theOfferProgramId);
        }
        {
            String theSystemId;
            theSystemId = this.getSystemId();
            strategy.appendField(locator, this, "systemId", buffer, theSystemId);
        }
        {
            Date thePerspectiveDate;
            thePerspectiveDate = this.getPerspectiveDate();
            strategy.appendField(locator, this, "perspectiveDate", buffer, thePerspectiveDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineOfferHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineOfferHeader that = ((WirelineOfferHeader) object);
        {
            String lhsOfferInstanceId;
            lhsOfferInstanceId = this.getOfferInstanceId();
            String rhsOfferInstanceId;
            rhsOfferInstanceId = that.getOfferInstanceId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerInstanceId", lhsOfferInstanceId), LocatorUtils.property(thatLocator, "offerInstanceId", rhsOfferInstanceId), lhsOfferInstanceId, rhsOfferInstanceId)) {
                return false;
            }
        }
        {
            String lhsOfferId;
            lhsOfferId = this.getOfferId();
            String rhsOfferId;
            rhsOfferId = that.getOfferId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerId", lhsOfferId), LocatorUtils.property(thatLocator, "offerId", rhsOfferId), lhsOfferId, rhsOfferId)) {
                return false;
            }
        }
        {
            String lhsOfferCode;
            lhsOfferCode = this.getOfferCode();
            String rhsOfferCode;
            rhsOfferCode = that.getOfferCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCode", lhsOfferCode), LocatorUtils.property(thatLocator, "offerCode", rhsOfferCode), lhsOfferCode, rhsOfferCode)) {
                return false;
            }
        }
        {
            String lhsOfferProgramId;
            lhsOfferProgramId = this.getOfferProgramId();
            String rhsOfferProgramId;
            rhsOfferProgramId = that.getOfferProgramId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProgramId", lhsOfferProgramId), LocatorUtils.property(thatLocator, "offerProgramId", rhsOfferProgramId), lhsOfferProgramId, rhsOfferProgramId)) {
                return false;
            }
        }
        {
            String lhsSystemId;
            lhsSystemId = this.getSystemId();
            String rhsSystemId;
            rhsSystemId = that.getSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "systemId", lhsSystemId), LocatorUtils.property(thatLocator, "systemId", rhsSystemId), lhsSystemId, rhsSystemId)) {
                return false;
            }
        }
        {
            Date lhsPerspectiveDate;
            lhsPerspectiveDate = this.getPerspectiveDate();
            Date rhsPerspectiveDate;
            rhsPerspectiveDate = that.getPerspectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perspectiveDate", lhsPerspectiveDate), LocatorUtils.property(thatLocator, "perspectiveDate", rhsPerspectiveDate), lhsPerspectiveDate, rhsPerspectiveDate)) {
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
