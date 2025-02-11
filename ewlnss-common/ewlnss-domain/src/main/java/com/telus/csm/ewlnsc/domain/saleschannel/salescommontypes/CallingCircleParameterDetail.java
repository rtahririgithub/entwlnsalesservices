
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
 * <p>Java class for CallingCircleParameterDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CallingCircleParameterDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="callingCirclePhoneList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCirclePhoneList" minOccurs="0"/>
 *         &lt;element name="maxCallingCircleSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallingCircleParameterDetail", propOrder = {
    "callingCirclePhoneList",
    "maxCallingCircleSize"
})
public class CallingCircleParameterDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected CallingCirclePhoneList callingCirclePhoneList;
    protected int maxCallingCircleSize;

    /**
     * Gets the value of the callingCirclePhoneList property.
     * 
     * @return
     *     possible object is
     *     {@link CallingCirclePhoneList }
     *     
     */
    public CallingCirclePhoneList getCallingCirclePhoneList() {
        return callingCirclePhoneList;
    }

    /**
     * Sets the value of the callingCirclePhoneList property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallingCirclePhoneList }
     *     
     */
    public void setCallingCirclePhoneList(CallingCirclePhoneList value) {
        this.callingCirclePhoneList = value;
    }

    /**
     * Gets the value of the maxCallingCircleSize property.
     * 
     */
    public int getMaxCallingCircleSize() {
        return maxCallingCircleSize;
    }

    /**
     * Sets the value of the maxCallingCircleSize property.
     * 
     */
    public void setMaxCallingCircleSize(int value) {
        this.maxCallingCircleSize = value;
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
            CallingCirclePhoneList theCallingCirclePhoneList;
            theCallingCirclePhoneList = this.getCallingCirclePhoneList();
            strategy.appendField(locator, this, "callingCirclePhoneList", buffer, theCallingCirclePhoneList);
        }
        {
            int theMaxCallingCircleSize;
            theMaxCallingCircleSize = (true?this.getMaxCallingCircleSize(): 0);
            strategy.appendField(locator, this, "maxCallingCircleSize", buffer, theMaxCallingCircleSize);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CallingCircleParameterDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CallingCircleParameterDetail that = ((CallingCircleParameterDetail) object);
        {
            CallingCirclePhoneList lhsCallingCirclePhoneList;
            lhsCallingCirclePhoneList = this.getCallingCirclePhoneList();
            CallingCirclePhoneList rhsCallingCirclePhoneList;
            rhsCallingCirclePhoneList = that.getCallingCirclePhoneList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingCirclePhoneList", lhsCallingCirclePhoneList), LocatorUtils.property(thatLocator, "callingCirclePhoneList", rhsCallingCirclePhoneList), lhsCallingCirclePhoneList, rhsCallingCirclePhoneList)) {
                return false;
            }
        }
        {
            int lhsMaxCallingCircleSize;
            lhsMaxCallingCircleSize = (true?this.getMaxCallingCircleSize(): 0);
            int rhsMaxCallingCircleSize;
            rhsMaxCallingCircleSize = (true?that.getMaxCallingCircleSize(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxCallingCircleSize", lhsMaxCallingCircleSize), LocatorUtils.property(thatLocator, "maxCallingCircleSize", rhsMaxCallingCircleSize), lhsMaxCallingCircleSize, rhsMaxCallingCircleSize)) {
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
