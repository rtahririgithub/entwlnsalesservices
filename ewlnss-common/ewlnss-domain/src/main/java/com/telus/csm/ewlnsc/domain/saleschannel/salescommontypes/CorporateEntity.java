
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * To define CBUCID or RCID
 * 
 * <p>Java class for CorporateEntity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorporateEntity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="entitySourceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorporateEntity", propOrder = {
    "entityId",
    "entitySourceTypeCd"
})
@XmlSeeAlso({
    CorporateEntitySummary.class
})
public class CorporateEntity
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String entityId;
    @XmlElement(required = true)
    protected String entitySourceTypeCd;

    /**
     * Gets the value of the entityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets the value of the entityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntityId(String value) {
        this.entityId = value;
    }

    /**
     * Gets the value of the entitySourceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEntitySourceTypeCd() {
        return entitySourceTypeCd;
    }

    /**
     * Sets the value of the entitySourceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEntitySourceTypeCd(String value) {
        this.entitySourceTypeCd = value;
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
            String theEntityId;
            theEntityId = this.getEntityId();
            strategy.appendField(locator, this, "entityId", buffer, theEntityId);
        }
        {
            String theEntitySourceTypeCd;
            theEntitySourceTypeCd = this.getEntitySourceTypeCd();
            strategy.appendField(locator, this, "entitySourceTypeCd", buffer, theEntitySourceTypeCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CorporateEntity)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CorporateEntity that = ((CorporateEntity) object);
        {
            String lhsEntityId;
            lhsEntityId = this.getEntityId();
            String rhsEntityId;
            rhsEntityId = that.getEntityId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entityId", lhsEntityId), LocatorUtils.property(thatLocator, "entityId", rhsEntityId), lhsEntityId, rhsEntityId)) {
                return false;
            }
        }
        {
            String lhsEntitySourceTypeCd;
            lhsEntitySourceTypeCd = this.getEntitySourceTypeCd();
            String rhsEntitySourceTypeCd;
            rhsEntitySourceTypeCd = that.getEntitySourceTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "entitySourceTypeCd", lhsEntitySourceTypeCd), LocatorUtils.property(thatLocator, "entitySourceTypeCd", rhsEntitySourceTypeCd), lhsEntitySourceTypeCd, rhsEntitySourceTypeCd)) {
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
