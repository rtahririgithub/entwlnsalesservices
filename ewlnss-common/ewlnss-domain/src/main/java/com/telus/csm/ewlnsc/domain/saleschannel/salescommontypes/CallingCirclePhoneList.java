
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for CallingCirclePhoneList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallingCirclePhoneList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="callingCirclePhone" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCirclePhone" maxOccurs="100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallingCirclePhoneList", propOrder = {
    "callingCirclePhone"
})
public class CallingCirclePhoneList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<CallingCirclePhone> callingCirclePhone;

    /**
     * Gets the value of the callingCirclePhone property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the callingCirclePhone property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCallingCirclePhone().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CallingCirclePhone }
     * 
     * 
     */
    public List<CallingCirclePhone> getCallingCirclePhone() {
        if (callingCirclePhone == null) {
            callingCirclePhone = new ArrayList<CallingCirclePhone>();
        }
        return this.callingCirclePhone;
    }

    /**
     * Sets the value of the callingCirclePhone property.
     * 
     * @param callingCirclePhone
     *     allowed object is
     *     {@link CallingCirclePhone }
     *     
     */
    public void setCallingCirclePhone(List<CallingCirclePhone> callingCirclePhone) {
        this.callingCirclePhone = callingCirclePhone;
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
            List<CallingCirclePhone> theCallingCirclePhone;
            theCallingCirclePhone = (((this.callingCirclePhone!= null)&&(!this.callingCirclePhone.isEmpty()))?this.getCallingCirclePhone():null);
            strategy.appendField(locator, this, "callingCirclePhone", buffer, theCallingCirclePhone);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CallingCirclePhoneList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CallingCirclePhoneList that = ((CallingCirclePhoneList) object);
        {
            List<CallingCirclePhone> lhsCallingCirclePhone;
            lhsCallingCirclePhone = (((this.callingCirclePhone!= null)&&(!this.callingCirclePhone.isEmpty()))?this.getCallingCirclePhone():null);
            List<CallingCirclePhone> rhsCallingCirclePhone;
            rhsCallingCirclePhone = (((that.callingCirclePhone!= null)&&(!that.callingCirclePhone.isEmpty()))?that.getCallingCirclePhone():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingCirclePhone", lhsCallingCirclePhone), LocatorUtils.property(thatLocator, "callingCirclePhone", rhsCallingCirclePhone), lhsCallingCirclePhone, rhsCallingCirclePhone)) {
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
