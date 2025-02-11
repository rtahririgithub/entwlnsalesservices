
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for ServiceIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceReferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceIdentifier", propOrder = {
    "serviceId",
    "serviceReferenceId"
})
public class ServiceIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceId;
    protected String serviceReferenceId;

    /**
     * Gets the value of the serviceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * Sets the value of the serviceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceId(String value) {
        this.serviceId = value;
    }

    /**
     * Gets the value of the serviceReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceReferenceId() {
        return serviceReferenceId;
    }

    /**
     * Sets the value of the serviceReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceReferenceId(String value) {
        this.serviceReferenceId = value;
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
            String theServiceId;
            theServiceId = this.getServiceId();
            strategy.appendField(locator, this, "serviceId", buffer, theServiceId);
        }
        {
            String theServiceReferenceId;
            theServiceReferenceId = this.getServiceReferenceId();
            strategy.appendField(locator, this, "serviceReferenceId", buffer, theServiceReferenceId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceIdentifier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceIdentifier that = ((ServiceIdentifier) object);
        {
            String lhsServiceId;
            lhsServiceId = this.getServiceId();
            String rhsServiceId;
            rhsServiceId = that.getServiceId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceId", lhsServiceId), LocatorUtils.property(thatLocator, "serviceId", rhsServiceId), lhsServiceId, rhsServiceId)) {
                return false;
            }
        }
        {
            String lhsServiceReferenceId;
            lhsServiceReferenceId = this.getServiceReferenceId();
            String rhsServiceReferenceId;
            rhsServiceReferenceId = that.getServiceReferenceId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceReferenceId", lhsServiceReferenceId), LocatorUtils.property(thatLocator, "serviceReferenceId", rhsServiceReferenceId), lhsServiceReferenceId, rhsServiceReferenceId)) {
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
