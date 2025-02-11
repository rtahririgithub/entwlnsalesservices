
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
 * <p>Java class for OrderReferenceAssociation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OrderReferenceAssociation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="referenceCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="referenceValueTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OrderReferenceAssociation", propOrder = {
    "referenceCd",
    "referenceValueTxt"
})
public class OrderReferenceAssociation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String referenceCd;
    @XmlElement(required = true)
    protected String referenceValueTxt;

    /**
     * Gets the value of the referenceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceCd() {
        return referenceCd;
    }

    /**
     * Sets the value of the referenceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceCd(String value) {
        this.referenceCd = value;
    }

    /**
     * Gets the value of the referenceValueTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReferenceValueTxt() {
        return referenceValueTxt;
    }

    /**
     * Sets the value of the referenceValueTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReferenceValueTxt(String value) {
        this.referenceValueTxt = value;
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
            String theReferenceCd;
            theReferenceCd = this.getReferenceCd();
            strategy.appendField(locator, this, "referenceCd", buffer, theReferenceCd);
        }
        {
            String theReferenceValueTxt;
            theReferenceValueTxt = this.getReferenceValueTxt();
            strategy.appendField(locator, this, "referenceValueTxt", buffer, theReferenceValueTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OrderReferenceAssociation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OrderReferenceAssociation that = ((OrderReferenceAssociation) object);
        {
            String lhsReferenceCd;
            lhsReferenceCd = this.getReferenceCd();
            String rhsReferenceCd;
            rhsReferenceCd = that.getReferenceCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceCd", lhsReferenceCd), LocatorUtils.property(thatLocator, "referenceCd", rhsReferenceCd), lhsReferenceCd, rhsReferenceCd)) {
                return false;
            }
        }
        {
            String lhsReferenceValueTxt;
            lhsReferenceValueTxt = this.getReferenceValueTxt();
            String rhsReferenceValueTxt;
            rhsReferenceValueTxt = that.getReferenceValueTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referenceValueTxt", lhsReferenceValueTxt), LocatorUtils.property(thatLocator, "referenceValueTxt", rhsReferenceValueTxt), lhsReferenceValueTxt, rhsReferenceValueTxt)) {
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
