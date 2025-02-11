
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
 * <p>Java class for OrderReference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderReference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="referenceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderReference", propOrder = {
    "referenceId",
    "referenceTypeCd"
})
public class OrderReference
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String referenceId;
    @XmlElement(required = true)
    protected String referenceTypeCd;

    /**
     * Gets the value of the referenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceId() {
        return referenceId;
    }

    /**
     * Sets the value of the referenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceId(String value) {
        this.referenceId = value;
    }

    /**
     * Gets the value of the referenceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceTypeCd() {
        return referenceTypeCd;
    }

    /**
     * Sets the value of the referenceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceTypeCd(String value) {
        this.referenceTypeCd = value;
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
            String theReferenceId;
            theReferenceId = this.getReferenceId();
            strategy.appendField(locator, this, "referenceId", buffer, theReferenceId);
        }
        {
            String theReferenceTypeCd;
            theReferenceTypeCd = this.getReferenceTypeCd();
            strategy.appendField(locator, this, "referenceTypeCd", buffer, theReferenceTypeCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OrderReference)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OrderReference that = ((OrderReference) object);
        {
            String lhsReferenceId;
            lhsReferenceId = this.getReferenceId();
            String rhsReferenceId;
            rhsReferenceId = that.getReferenceId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceId", lhsReferenceId), LocatorUtils.property(thatLocator, "referenceId", rhsReferenceId), lhsReferenceId, rhsReferenceId)) {
                return false;
            }
        }
        {
            String lhsReferenceTypeCd;
            lhsReferenceTypeCd = this.getReferenceTypeCd();
            String rhsReferenceTypeCd;
            rhsReferenceTypeCd = that.getReferenceTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceTypeCd", lhsReferenceTypeCd), LocatorUtils.property(thatLocator, "referenceTypeCd", rhsReferenceTypeCd), lhsReferenceTypeCd, rhsReferenceTypeCd)) {
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
