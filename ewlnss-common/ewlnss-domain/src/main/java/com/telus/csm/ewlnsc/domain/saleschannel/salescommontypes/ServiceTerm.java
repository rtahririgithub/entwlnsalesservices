
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
 * <p>Java class for ServiceTerm complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceTerm">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="termDuration" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="termUnit" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceTerm", propOrder = {
    "termDuration",
    "termUnit"
})
public class ServiceTerm
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected long termDuration;
    @XmlElement(required = true)
    protected String termUnit;

    /**
     * Gets the value of the termDuration property.
     * 
     */
    public long getTermDuration() {
        return termDuration;
    }

    /**
     * Sets the value of the termDuration property.
     * 
     */
    public void setTermDuration(long value) {
        this.termDuration = value;
    }

    /**
     * Gets the value of the termUnit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTermUnit() {
        return termUnit;
    }

    /**
     * Sets the value of the termUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTermUnit(String value) {
        this.termUnit = value;
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
            long theTermDuration;
            theTermDuration = (true?this.getTermDuration(): 0L);
            strategy.appendField(locator, this, "termDuration", buffer, theTermDuration);
        }
        {
            String theTermUnit;
            theTermUnit = this.getTermUnit();
            strategy.appendField(locator, this, "termUnit", buffer, theTermUnit);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceTerm)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceTerm that = ((ServiceTerm) object);
        {
            long lhsTermDuration;
            lhsTermDuration = (true?this.getTermDuration(): 0L);
            long rhsTermDuration;
            rhsTermDuration = (true?that.getTermDuration(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "termDuration", lhsTermDuration), LocatorUtils.property(thatLocator, "termDuration", rhsTermDuration), lhsTermDuration, rhsTermDuration)) {
                return false;
            }
        }
        {
            String lhsTermUnit;
            lhsTermUnit = this.getTermUnit();
            String rhsTermUnit;
            rhsTermUnit = that.getTermUnit();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "termUnit", lhsTermUnit), LocatorUtils.property(thatLocator, "termUnit", rhsTermUnit), lhsTermUnit, rhsTermUnit)) {
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
