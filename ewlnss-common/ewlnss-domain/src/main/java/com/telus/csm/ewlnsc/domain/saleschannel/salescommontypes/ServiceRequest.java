
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 * <p>Java class for ServiceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceRequestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceRequestDateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="serviceRequestStatusCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceRequest", propOrder = {
    "serviceRequestId",
    "serviceRequestDateTime",
    "serviceRequestStatusCd",
    "referenceNumber"
})
public class ServiceRequest
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceRequestId;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date serviceRequestDateTime;
    @XmlElement(required = true)
    protected String serviceRequestStatusCd;
    protected String referenceNumber;

    /**
     * Gets the value of the serviceRequestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRequestId() {
        return serviceRequestId;
    }

    /**
     * Sets the value of the serviceRequestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRequestId(String value) {
        this.serviceRequestId = value;
    }

    /**
     * Gets the value of the serviceRequestDateTime property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getServiceRequestDateTime() {
        return serviceRequestDateTime;
    }

    /**
     * Sets the value of the serviceRequestDateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRequestDateTime(Date value) {
        this.serviceRequestDateTime = value;
    }

    /**
     * Gets the value of the serviceRequestStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceRequestStatusCd() {
        return serviceRequestStatusCd;
    }

    /**
     * Sets the value of the serviceRequestStatusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceRequestStatusCd(String value) {
        this.serviceRequestStatusCd = value;
    }

    /**
     * Gets the value of the referenceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceNumber() {
        return referenceNumber;
    }

    /**
     * Sets the value of the referenceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceNumber(String value) {
        this.referenceNumber = value;
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
            String theServiceRequestId;
            theServiceRequestId = this.getServiceRequestId();
            strategy.appendField(locator, this, "serviceRequestId", buffer, theServiceRequestId);
        }
        {
            Date theServiceRequestDateTime;
            theServiceRequestDateTime = this.getServiceRequestDateTime();
            strategy.appendField(locator, this, "serviceRequestDateTime", buffer, theServiceRequestDateTime);
        }
        {
            String theServiceRequestStatusCd;
            theServiceRequestStatusCd = this.getServiceRequestStatusCd();
            strategy.appendField(locator, this, "serviceRequestStatusCd", buffer, theServiceRequestStatusCd);
        }
        {
            String theReferenceNumber;
            theReferenceNumber = this.getReferenceNumber();
            strategy.appendField(locator, this, "referenceNumber", buffer, theReferenceNumber);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceRequest that = ((ServiceRequest) object);
        {
            String lhsServiceRequestId;
            lhsServiceRequestId = this.getServiceRequestId();
            String rhsServiceRequestId;
            rhsServiceRequestId = that.getServiceRequestId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceRequestId", lhsServiceRequestId), LocatorUtils.property(thatLocator, "serviceRequestId", rhsServiceRequestId), lhsServiceRequestId, rhsServiceRequestId)) {
                return false;
            }
        }
        {
            Date lhsServiceRequestDateTime;
            lhsServiceRequestDateTime = this.getServiceRequestDateTime();
            Date rhsServiceRequestDateTime;
            rhsServiceRequestDateTime = that.getServiceRequestDateTime();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceRequestDateTime", lhsServiceRequestDateTime), LocatorUtils.property(thatLocator, "serviceRequestDateTime", rhsServiceRequestDateTime), lhsServiceRequestDateTime, rhsServiceRequestDateTime)) {
                return false;
            }
        }
        {
            String lhsServiceRequestStatusCd;
            lhsServiceRequestStatusCd = this.getServiceRequestStatusCd();
            String rhsServiceRequestStatusCd;
            rhsServiceRequestStatusCd = that.getServiceRequestStatusCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceRequestStatusCd", lhsServiceRequestStatusCd), LocatorUtils.property(thatLocator, "serviceRequestStatusCd", rhsServiceRequestStatusCd), lhsServiceRequestStatusCd, rhsServiceRequestStatusCd)) {
                return false;
            }
        }
        {
            String lhsReferenceNumber;
            lhsReferenceNumber = this.getReferenceNumber();
            String rhsReferenceNumber;
            rhsReferenceNumber = that.getReferenceNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceNumber", lhsReferenceNumber), LocatorUtils.property(thatLocator, "referenceNumber", rhsReferenceNumber), lhsReferenceNumber, rhsReferenceNumber)) {
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
