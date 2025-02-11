
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for CallingCirclePhoneNumberList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallingCirclePhoneNumberList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="callingCirclePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallingCirclePhoneNumberList", propOrder = {
    "callingCirclePhoneNumber"
})
public class CallingCirclePhoneNumberList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<String> callingCirclePhoneNumber;

    /**
     * Gets the value of the callingCirclePhoneNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the callingCirclePhoneNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCallingCirclePhoneNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCallingCirclePhoneNumber() {
        if (callingCirclePhoneNumber == null) {
            callingCirclePhoneNumber = new ArrayList<String>();
        }
        return this.callingCirclePhoneNumber;
    }

    /**
     * Sets the value of the callingCirclePhoneNumber property.
     * 
     * @param callingCirclePhoneNumber
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallingCirclePhoneNumber(List<String> callingCirclePhoneNumber) {
        this.callingCirclePhoneNumber = callingCirclePhoneNumber;
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
            List<String> theCallingCirclePhoneNumber;
            theCallingCirclePhoneNumber = (((this.callingCirclePhoneNumber!= null)&&(!this.callingCirclePhoneNumber.isEmpty()))?this.getCallingCirclePhoneNumber():null);
            strategy.appendField(locator, this, "callingCirclePhoneNumber", buffer, theCallingCirclePhoneNumber);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CallingCirclePhoneNumberList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CallingCirclePhoneNumberList that = ((CallingCirclePhoneNumberList) object);
        {
            List<String> lhsCallingCirclePhoneNumber;
            lhsCallingCirclePhoneNumber = (((this.callingCirclePhoneNumber!= null)&&(!this.callingCirclePhoneNumber.isEmpty()))?this.getCallingCirclePhoneNumber():null);
            List<String> rhsCallingCirclePhoneNumber;
            rhsCallingCirclePhoneNumber = (((that.callingCirclePhoneNumber!= null)&&(!that.callingCirclePhoneNumber.isEmpty()))?that.getCallingCirclePhoneNumber():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingCirclePhoneNumber", lhsCallingCirclePhoneNumber), LocatorUtils.property(thatLocator, "callingCirclePhoneNumber", rhsCallingCirclePhoneNumber), lhsCallingCirclePhoneNumber, rhsCallingCirclePhoneNumber)) {
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
