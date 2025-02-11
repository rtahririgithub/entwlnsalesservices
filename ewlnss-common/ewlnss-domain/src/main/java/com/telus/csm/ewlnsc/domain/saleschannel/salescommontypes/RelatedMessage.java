
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
 * <p>Java class for RelatedMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RelatedMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="relatedErrorTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="relatedErrorCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="relatedErrorMessageTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RelatedMessage", propOrder = {
    "relatedErrorTypeCd",
    "relatedErrorCd",
    "relatedErrorMessageTxt"
})
public class RelatedMessage
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String relatedErrorTypeCd;
    @XmlElement(required = true)
    protected String relatedErrorCd;
    protected String relatedErrorMessageTxt;

    /**
     * Gets the value of the relatedErrorTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedErrorTypeCd() {
        return relatedErrorTypeCd;
    }

    /**
     * Sets the value of the relatedErrorTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedErrorTypeCd(String value) {
        this.relatedErrorTypeCd = value;
    }

    /**
     * Gets the value of the relatedErrorCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedErrorCd() {
        return relatedErrorCd;
    }

    /**
     * Sets the value of the relatedErrorCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedErrorCd(String value) {
        this.relatedErrorCd = value;
    }

    /**
     * Gets the value of the relatedErrorMessageTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRelatedErrorMessageTxt() {
        return relatedErrorMessageTxt;
    }

    /**
     * Sets the value of the relatedErrorMessageTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRelatedErrorMessageTxt(String value) {
        this.relatedErrorMessageTxt = value;
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
            String theRelatedErrorTypeCd;
            theRelatedErrorTypeCd = this.getRelatedErrorTypeCd();
            strategy.appendField(locator, this, "relatedErrorTypeCd", buffer, theRelatedErrorTypeCd);
        }
        {
            String theRelatedErrorCd;
            theRelatedErrorCd = this.getRelatedErrorCd();
            strategy.appendField(locator, this, "relatedErrorCd", buffer, theRelatedErrorCd);
        }
        {
            String theRelatedErrorMessageTxt;
            theRelatedErrorMessageTxt = this.getRelatedErrorMessageTxt();
            strategy.appendField(locator, this, "relatedErrorMessageTxt", buffer, theRelatedErrorMessageTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RelatedMessage)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RelatedMessage that = ((RelatedMessage) object);
        {
            String lhsRelatedErrorTypeCd;
            lhsRelatedErrorTypeCd = this.getRelatedErrorTypeCd();
            String rhsRelatedErrorTypeCd;
            rhsRelatedErrorTypeCd = that.getRelatedErrorTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedErrorTypeCd", lhsRelatedErrorTypeCd), LocatorUtils.property(thatLocator, "relatedErrorTypeCd", rhsRelatedErrorTypeCd), lhsRelatedErrorTypeCd, rhsRelatedErrorTypeCd)) {
                return false;
            }
        }
        {
            String lhsRelatedErrorCd;
            lhsRelatedErrorCd = this.getRelatedErrorCd();
            String rhsRelatedErrorCd;
            rhsRelatedErrorCd = that.getRelatedErrorCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedErrorCd", lhsRelatedErrorCd), LocatorUtils.property(thatLocator, "relatedErrorCd", rhsRelatedErrorCd), lhsRelatedErrorCd, rhsRelatedErrorCd)) {
                return false;
            }
        }
        {
            String lhsRelatedErrorMessageTxt;
            lhsRelatedErrorMessageTxt = this.getRelatedErrorMessageTxt();
            String rhsRelatedErrorMessageTxt;
            rhsRelatedErrorMessageTxt = that.getRelatedErrorMessageTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedErrorMessageTxt", lhsRelatedErrorMessageTxt), LocatorUtils.property(thatLocator, "relatedErrorMessageTxt", rhsRelatedErrorMessageTxt), lhsRelatedErrorMessageTxt, rhsRelatedErrorMessageTxt)) {
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
