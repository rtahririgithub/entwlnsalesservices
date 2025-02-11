
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for AccessoryEquipmentProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryEquipmentProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="equipmentCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="equipmentSubCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="equipmentClassId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="equipmentVendorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="equipmentManufacturerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="equipmentProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryEquipmentProduct", propOrder = {
    "equipmentCategoryId",
    "equipmentSubCategoryId",
    "equipmentClassId",
    "equipmentVendorId",
    "equipmentManufacturerId",
    "equipmentProductCode"
})
public class AccessoryEquipmentProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Long equipmentCategoryId;
    protected Long equipmentSubCategoryId;
    protected Long equipmentClassId;
    protected Long equipmentVendorId;
    protected Long equipmentManufacturerId;
    protected String equipmentProductCode;

    /**
     * Gets the value of the equipmentCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEquipmentCategoryId() {
        return equipmentCategoryId;
    }

    /**
     * Sets the value of the equipmentCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEquipmentCategoryId(Long value) {
        this.equipmentCategoryId = value;
    }

    /**
     * Gets the value of the equipmentSubCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEquipmentSubCategoryId() {
        return equipmentSubCategoryId;
    }

    /**
     * Sets the value of the equipmentSubCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEquipmentSubCategoryId(Long value) {
        this.equipmentSubCategoryId = value;
    }

    /**
     * Gets the value of the equipmentClassId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEquipmentClassId() {
        return equipmentClassId;
    }

    /**
     * Sets the value of the equipmentClassId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEquipmentClassId(Long value) {
        this.equipmentClassId = value;
    }

    /**
     * Gets the value of the equipmentVendorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEquipmentVendorId() {
        return equipmentVendorId;
    }

    /**
     * Sets the value of the equipmentVendorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEquipmentVendorId(Long value) {
        this.equipmentVendorId = value;
    }

    /**
     * Gets the value of the equipmentManufacturerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getEquipmentManufacturerId() {
        return equipmentManufacturerId;
    }

    /**
     * Sets the value of the equipmentManufacturerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setEquipmentManufacturerId(Long value) {
        this.equipmentManufacturerId = value;
    }

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
            Long theEquipmentCategoryId;
            theEquipmentCategoryId = this.getEquipmentCategoryId();
            strategy.appendField(locator, this, "equipmentCategoryId", buffer, theEquipmentCategoryId);
        }
        {
            Long theEquipmentSubCategoryId;
            theEquipmentSubCategoryId = this.getEquipmentSubCategoryId();
            strategy.appendField(locator, this, "equipmentSubCategoryId", buffer, theEquipmentSubCategoryId);
        }
        {
            Long theEquipmentClassId;
            theEquipmentClassId = this.getEquipmentClassId();
            strategy.appendField(locator, this, "equipmentClassId", buffer, theEquipmentClassId);
        }
        {
            Long theEquipmentVendorId;
            theEquipmentVendorId = this.getEquipmentVendorId();
            strategy.appendField(locator, this, "equipmentVendorId", buffer, theEquipmentVendorId);
        }
        {
            Long theEquipmentManufacturerId;
            theEquipmentManufacturerId = this.getEquipmentManufacturerId();
            strategy.appendField(locator, this, "equipmentManufacturerId", buffer, theEquipmentManufacturerId);
        }
        {
            String theEquipmentProductCode;
            theEquipmentProductCode = this.getEquipmentProductCode();
            strategy.appendField(locator, this, "equipmentProductCode", buffer, theEquipmentProductCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryEquipmentProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryEquipmentProduct that = ((AccessoryEquipmentProduct) object);
        {
            Long lhsEquipmentCategoryId;
            lhsEquipmentCategoryId = this.getEquipmentCategoryId();
            Long rhsEquipmentCategoryId;
            rhsEquipmentCategoryId = that.getEquipmentCategoryId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentCategoryId", lhsEquipmentCategoryId), LocatorUtils.property(thatLocator, "equipmentCategoryId", rhsEquipmentCategoryId), lhsEquipmentCategoryId, rhsEquipmentCategoryId)) {
                return false;
            }
        }
        {
            Long lhsEquipmentSubCategoryId;
            lhsEquipmentSubCategoryId = this.getEquipmentSubCategoryId();
            Long rhsEquipmentSubCategoryId;
            rhsEquipmentSubCategoryId = that.getEquipmentSubCategoryId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentSubCategoryId", lhsEquipmentSubCategoryId), LocatorUtils.property(thatLocator, "equipmentSubCategoryId", rhsEquipmentSubCategoryId), lhsEquipmentSubCategoryId, rhsEquipmentSubCategoryId)) {
                return false;
            }
        }
        {
            Long lhsEquipmentClassId;
            lhsEquipmentClassId = this.getEquipmentClassId();
            Long rhsEquipmentClassId;
            rhsEquipmentClassId = that.getEquipmentClassId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentClassId", lhsEquipmentClassId), LocatorUtils.property(thatLocator, "equipmentClassId", rhsEquipmentClassId), lhsEquipmentClassId, rhsEquipmentClassId)) {
                return false;
            }
        }
        {
            Long lhsEquipmentVendorId;
            lhsEquipmentVendorId = this.getEquipmentVendorId();
            Long rhsEquipmentVendorId;
            rhsEquipmentVendorId = that.getEquipmentVendorId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentVendorId", lhsEquipmentVendorId), LocatorUtils.property(thatLocator, "equipmentVendorId", rhsEquipmentVendorId), lhsEquipmentVendorId, rhsEquipmentVendorId)) {
                return false;
            }
        }
        {
            Long lhsEquipmentManufacturerId;
            lhsEquipmentManufacturerId = this.getEquipmentManufacturerId();
            Long rhsEquipmentManufacturerId;
            rhsEquipmentManufacturerId = that.getEquipmentManufacturerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentManufacturerId", lhsEquipmentManufacturerId), LocatorUtils.property(thatLocator, "equipmentManufacturerId", rhsEquipmentManufacturerId), lhsEquipmentManufacturerId, rhsEquipmentManufacturerId)) {
                return false;
            }
        }
        {
            String lhsEquipmentProductCode;
            lhsEquipmentProductCode = this.getEquipmentProductCode();
            String rhsEquipmentProductCode;
            rhsEquipmentProductCode = that.getEquipmentProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentProductCode", lhsEquipmentProductCode), LocatorUtils.property(thatLocator, "equipmentProductCode", rhsEquipmentProductCode), lhsEquipmentProductCode, rhsEquipmentProductCode)) {
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
