
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for AppointmentDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AppointmentDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="appointmentId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="appointmentDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AppointmentDetail", propOrder = {
    "appointmentId",
    "appointmentDate"
})
public class AppointmentDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected long appointmentId;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date appointmentDate;

    /**
     * Gets the value of the appointmentId property.
     * 
     */
    public long getAppointmentId() {
        return appointmentId;
    }

    /**
     * Sets the value of the appointmentId property.
     * 
     */
    public void setAppointmentId(long value) {
        this.appointmentId = value;
    }

    /**
     * Gets the value of the appointmentDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getAppointmentDate() {
        return appointmentDate;
    }

    /**
     * Sets the value of the appointmentDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppointmentDate(Date value) {
        this.appointmentDate = value;
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
            long theAppointmentId;
            theAppointmentId = (true?this.getAppointmentId(): 0L);
            strategy.appendField(locator, this, "appointmentId", buffer, theAppointmentId);
        }
        {
            Date theAppointmentDate;
            theAppointmentDate = this.getAppointmentDate();
            strategy.appendField(locator, this, "appointmentDate", buffer, theAppointmentDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AppointmentDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AppointmentDetail that = ((AppointmentDetail) object);
        {
            long lhsAppointmentId;
            lhsAppointmentId = (true?this.getAppointmentId(): 0L);
            long rhsAppointmentId;
            rhsAppointmentId = (true?that.getAppointmentId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appointmentId", lhsAppointmentId), LocatorUtils.property(thatLocator, "appointmentId", rhsAppointmentId), lhsAppointmentId, rhsAppointmentId)) {
                return false;
            }
        }
        {
            Date lhsAppointmentDate;
            lhsAppointmentDate = this.getAppointmentDate();
            Date rhsAppointmentDate;
            rhsAppointmentDate = that.getAppointmentDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appointmentDate", lhsAppointmentDate), LocatorUtils.property(thatLocator, "appointmentDate", rhsAppointmentDate), lhsAppointmentDate, rhsAppointmentDate)) {
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
