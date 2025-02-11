
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * Base Offer identifier information.
 * 
 * <p>Java class for OfferIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="programCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perspectiveDate" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}perspectiveDate" minOccurs="0"/>
 *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="offerTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceSystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferIdentifier", propOrder = {
    "programId",
    "programCode",
    "perspectiveDate",
    "offerId",
    "offerTypeCode",
    "sourceSystemId"
})
public class OfferIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Long programId;
    protected String programCode;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    protected Date perspectiveDate;
    protected long offerId;
    @XmlElement(required = true)
    protected String offerTypeCode;
    protected String sourceSystemId;

    /**
     * Gets the value of the programId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProgramId(Long value) {
        this.programId = value;
    }

    /**
     * Gets the value of the programCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the value of the programCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramCode(String value) {
        this.programCode = value;
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

    /**
     * Gets the value of the offerId property.
     * 
     */
    public long getOfferId() {
        return offerId;
    }

    /**
     * Sets the value of the offerId property.
     * 
     */
    public void setOfferId(long value) {
        this.offerId = value;
    }

    /**
     * Gets the value of the offerTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferTypeCode() {
        return offerTypeCode;
    }

    /**
     * Sets the value of the offerTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferTypeCode(String value) {
        this.offerTypeCode = value;
    }

    /**
     * Gets the value of the sourceSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystemId() {
        return sourceSystemId;
    }

    /**
     * Sets the value of the sourceSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystemId(String value) {
        this.sourceSystemId = value;
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
            Long theProgramId;
            theProgramId = this.getProgramId();
            strategy.appendField(locator, this, "programId", buffer, theProgramId);
        }
        {
            String theProgramCode;
            theProgramCode = this.getProgramCode();
            strategy.appendField(locator, this, "programCode", buffer, theProgramCode);
        }
        {
            Date thePerspectiveDate;
            thePerspectiveDate = this.getPerspectiveDate();
            strategy.appendField(locator, this, "perspectiveDate", buffer, thePerspectiveDate);
        }
        {
            long theOfferId;
            theOfferId = (true?this.getOfferId(): 0L);
            strategy.appendField(locator, this, "offerId", buffer, theOfferId);
        }
        {
            String theOfferTypeCode;
            theOfferTypeCode = this.getOfferTypeCode();
            strategy.appendField(locator, this, "offerTypeCode", buffer, theOfferTypeCode);
        }
        {
            String theSourceSystemId;
            theSourceSystemId = this.getSourceSystemId();
            strategy.appendField(locator, this, "sourceSystemId", buffer, theSourceSystemId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferIdentifier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OfferIdentifier that = ((OfferIdentifier) object);
        {
            Long lhsProgramId;
            lhsProgramId = this.getProgramId();
            Long rhsProgramId;
            rhsProgramId = that.getProgramId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programId", lhsProgramId), LocatorUtils.property(thatLocator, "programId", rhsProgramId), lhsProgramId, rhsProgramId)) {
                return false;
            }
        }
        {
            String lhsProgramCode;
            lhsProgramCode = this.getProgramCode();
            String rhsProgramCode;
            rhsProgramCode = that.getProgramCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programCode", lhsProgramCode), LocatorUtils.property(thatLocator, "programCode", rhsProgramCode), lhsProgramCode, rhsProgramCode)) {
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
        {
            long lhsOfferId;
            lhsOfferId = (true?this.getOfferId(): 0L);
            long rhsOfferId;
            rhsOfferId = (true?that.getOfferId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerId", lhsOfferId), LocatorUtils.property(thatLocator, "offerId", rhsOfferId), lhsOfferId, rhsOfferId)) {
                return false;
            }
        }
        {
            String lhsOfferTypeCode;
            lhsOfferTypeCode = this.getOfferTypeCode();
            String rhsOfferTypeCode;
            rhsOfferTypeCode = that.getOfferTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerTypeCode", lhsOfferTypeCode), LocatorUtils.property(thatLocator, "offerTypeCode", rhsOfferTypeCode), lhsOfferTypeCode, rhsOfferTypeCode)) {
                return false;
            }
        }
        {
            String lhsSourceSystemId;
            lhsSourceSystemId = this.getSourceSystemId();
            String rhsSourceSystemId;
            rhsSourceSystemId = that.getSourceSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceSystemId", lhsSourceSystemId), LocatorUtils.property(thatLocator, "sourceSystemId", rhsSourceSystemId), lhsSourceSystemId, rhsSourceSystemId)) {
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
