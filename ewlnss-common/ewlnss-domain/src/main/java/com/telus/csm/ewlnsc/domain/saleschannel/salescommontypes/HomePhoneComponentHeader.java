
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for HomePhoneComponentHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HomePhoneComponentHeader">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductHeader">
 *       &lt;sequence>
 *         &lt;element name="appointmentDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AppointmentDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HomePhoneComponentHeader", propOrder = {
    "appointmentDetail"
})
@XmlSeeAlso({
    HomePhoneComponent.class
})
public class HomePhoneComponentHeader
    extends WirelineProductHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected AppointmentDetail appointmentDetail;

    /**
     * Gets the value of the appointmentDetail property.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentDetail }
     *     
     */
    public AppointmentDetail getAppointmentDetail() {
        return appointmentDetail;
    }

    /**
     * Sets the value of the appointmentDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentDetail }
     *     
     */
    public void setAppointmentDetail(AppointmentDetail value) {
        this.appointmentDetail = value;
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
            AppointmentDetail theAppointmentDetail;
            theAppointmentDetail = this.getAppointmentDetail();
            strategy.appendField(locator, this, "appointmentDetail", buffer, theAppointmentDetail);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HomePhoneComponentHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final HomePhoneComponentHeader that = ((HomePhoneComponentHeader) object);
        {
            AppointmentDetail lhsAppointmentDetail;
            lhsAppointmentDetail = this.getAppointmentDetail();
            AppointmentDetail rhsAppointmentDetail;
            rhsAppointmentDetail = that.getAppointmentDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appointmentDetail", lhsAppointmentDetail), LocatorUtils.property(thatLocator, "appointmentDetail", rhsAppointmentDetail), lhsAppointmentDetail, rhsAppointmentDetail)) {
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
