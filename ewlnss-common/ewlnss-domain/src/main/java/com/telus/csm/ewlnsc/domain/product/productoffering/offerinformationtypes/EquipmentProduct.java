
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for EquipmentProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipmentProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipmentProductCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="equipmentProductId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipmentProduct", propOrder = {
    "equipmentProductCode",
    "equipmentProductId"
})
public class EquipmentProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String equipmentProductCode;
    protected long equipmentProductId;

    /**
     * Gets the value of the equipmentProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentProductCode() {
        return equipmentProductCode;
    }

    /**
     * Sets the value of the equipmentProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentProductCode(String value) {
        this.equipmentProductCode = value;
    }

    /**
     * Gets the value of the equipmentProductId property.
     * 
     */
    public long getEquipmentProductId() {
        return equipmentProductId;
    }

    /**
     * Sets the value of the equipmentProductId property.
     * 
     */
    public void setEquipmentProductId(long value) {
        this.equipmentProductId = value;
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
            String theEquipmentProductCode;
            theEquipmentProductCode = this.getEquipmentProductCode();
            strategy.appendField(locator, this, "equipmentProductCode", buffer, theEquipmentProductCode);
        }
        {
            long theEquipmentProductId;
            theEquipmentProductId = (true?this.getEquipmentProductId(): 0L);
            strategy.appendField(locator, this, "equipmentProductId", buffer, theEquipmentProductId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EquipmentProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EquipmentProduct that = ((EquipmentProduct) object);
        {
            String lhsEquipmentProductCode;
            lhsEquipmentProductCode = this.getEquipmentProductCode();
            String rhsEquipmentProductCode;
            rhsEquipmentProductCode = that.getEquipmentProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentProductCode", lhsEquipmentProductCode), LocatorUtils.property(thatLocator, "equipmentProductCode", rhsEquipmentProductCode), lhsEquipmentProductCode, rhsEquipmentProductCode)) {
                return false;
            }
        }
        {
            long lhsEquipmentProductId;
            lhsEquipmentProductId = (true?this.getEquipmentProductId(): 0L);
            long rhsEquipmentProductId;
            rhsEquipmentProductId = (true?that.getEquipmentProductId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentProductId", lhsEquipmentProductId), LocatorUtils.property(thatLocator, "equipmentProductId", rhsEquipmentProductId), lhsEquipmentProductId, rhsEquipmentProductId)) {
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
