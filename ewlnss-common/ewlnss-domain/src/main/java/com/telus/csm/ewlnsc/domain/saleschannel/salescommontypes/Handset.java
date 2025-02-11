
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for Handset complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Handset">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serialNumber" type="{http://xmlschema.tmi.telus.com/xsd/Resource/Resource/CommonLogicalResourceTypes_v6}serialNoType" minOccurs="0"/>
 *         &lt;element name="usimID" type="{http://xmlschema.tmi.telus.com/xsd/Resource/Resource/CommonLogicalResourceTypes_v6}serialNoType" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="originalEquipmentValueAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Handset", propOrder = {
    "serialNumber",
    "usimID",
    "productCode",
    "originalEquipmentValueAmount"
})
@XmlSeeAlso({
    ProductSummary.class,
    PrepaidAccount.class,
    Equipment.class
})
public class Handset
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String serialNumber;
    protected String usimID;
    protected String productCode;
    protected Double originalEquipmentValueAmount;

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the usimID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsimID() {
        return usimID;
    }

    /**
     * Sets the value of the usimID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsimID(String value) {
        this.usimID = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the originalEquipmentValueAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getOriginalEquipmentValueAmount() {
        return originalEquipmentValueAmount;
    }

    /**
     * Sets the value of the originalEquipmentValueAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setOriginalEquipmentValueAmount(Double value) {
        this.originalEquipmentValueAmount = value;
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
            String theSerialNumber;
            theSerialNumber = this.getSerialNumber();
            strategy.appendField(locator, this, "serialNumber", buffer, theSerialNumber);
        }
        {
            String theUsimID;
            theUsimID = this.getUsimID();
            strategy.appendField(locator, this, "usimID", buffer, theUsimID);
        }
        {
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        {
            Double theOriginalEquipmentValueAmount;
            theOriginalEquipmentValueAmount = this.getOriginalEquipmentValueAmount();
            strategy.appendField(locator, this, "originalEquipmentValueAmount", buffer, theOriginalEquipmentValueAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Handset)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Handset that = ((Handset) object);
        {
            String lhsSerialNumber;
            lhsSerialNumber = this.getSerialNumber();
            String rhsSerialNumber;
            rhsSerialNumber = that.getSerialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serialNumber", lhsSerialNumber), LocatorUtils.property(thatLocator, "serialNumber", rhsSerialNumber), lhsSerialNumber, rhsSerialNumber)) {
                return false;
            }
        }
        {
            String lhsUsimID;
            lhsUsimID = this.getUsimID();
            String rhsUsimID;
            rhsUsimID = that.getUsimID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usimID", lhsUsimID), LocatorUtils.property(thatLocator, "usimID", rhsUsimID), lhsUsimID, rhsUsimID)) {
                return false;
            }
        }
        {
            String lhsProductCode;
            lhsProductCode = this.getProductCode();
            String rhsProductCode;
            rhsProductCode = that.getProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCode", lhsProductCode), LocatorUtils.property(thatLocator, "productCode", rhsProductCode), lhsProductCode, rhsProductCode)) {
                return false;
            }
        }
        {
            Double lhsOriginalEquipmentValueAmount;
            lhsOriginalEquipmentValueAmount = this.getOriginalEquipmentValueAmount();
            Double rhsOriginalEquipmentValueAmount;
            rhsOriginalEquipmentValueAmount = that.getOriginalEquipmentValueAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "originalEquipmentValueAmount", lhsOriginalEquipmentValueAmount), LocatorUtils.property(thatLocator, "originalEquipmentValueAmount", rhsOriginalEquipmentValueAmount), lhsOriginalEquipmentValueAmount, rhsOriginalEquipmentValueAmount)) {
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
