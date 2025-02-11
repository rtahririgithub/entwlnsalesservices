
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for WirelineEquipmentHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineEquipmentHeader">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier">
 *       &lt;sequence>
 *         &lt;element name="materialItemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="deliveryMethodTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="byodIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineEquipmentHeader", propOrder = {
    "materialItemCode",
    "deliveryMethodTypeCd",
    "byodIndicator"
})
public class WirelineEquipmentHeader
    extends ProductComponentIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String materialItemCode;
    @XmlElement(required = true)
    protected String deliveryMethodTypeCd;
    protected boolean byodIndicator;

    /**
     * Gets the value of the materialItemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialItemCode() {
        return materialItemCode;
    }

    /**
     * Sets the value of the materialItemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialItemCode(String value) {
        this.materialItemCode = value;
    }

    /**
     * Gets the value of the deliveryMethodTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeliveryMethodTypeCd() {
        return deliveryMethodTypeCd;
    }

    /**
     * Sets the value of the deliveryMethodTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryMethodTypeCd(String value) {
        this.deliveryMethodTypeCd = value;
    }

    /**
     * Gets the value of the byodIndicator property.
     * 
     */
    public boolean isByodIndicator() {
        return byodIndicator;
    }

    /**
     * Sets the value of the byodIndicator property.
     * 
     */
    public void setByodIndicator(boolean value) {
        this.byodIndicator = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            String theMaterialItemCode;
            theMaterialItemCode = this.getMaterialItemCode();
            strategy.appendField(locator, this, "materialItemCode", buffer, theMaterialItemCode);
        }
        {
            String theDeliveryMethodTypeCd;
            theDeliveryMethodTypeCd = this.getDeliveryMethodTypeCd();
            strategy.appendField(locator, this, "deliveryMethodTypeCd", buffer, theDeliveryMethodTypeCd);
        }
        {
            boolean theByodIndicator;
            theByodIndicator = (true?this.isByodIndicator():false);
            strategy.appendField(locator, this, "byodIndicator", buffer, theByodIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineEquipmentHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineEquipmentHeader that = ((WirelineEquipmentHeader) object);
        {
            String lhsMaterialItemCode;
            lhsMaterialItemCode = this.getMaterialItemCode();
            String rhsMaterialItemCode;
            rhsMaterialItemCode = that.getMaterialItemCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "materialItemCode", lhsMaterialItemCode), LocatorUtils.property(thatLocator, "materialItemCode", rhsMaterialItemCode), lhsMaterialItemCode, rhsMaterialItemCode)) {
                return false;
            }
        }
        {
            String lhsDeliveryMethodTypeCd;
            lhsDeliveryMethodTypeCd = this.getDeliveryMethodTypeCd();
            String rhsDeliveryMethodTypeCd;
            rhsDeliveryMethodTypeCd = that.getDeliveryMethodTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deliveryMethodTypeCd", lhsDeliveryMethodTypeCd), LocatorUtils.property(thatLocator, "deliveryMethodTypeCd", rhsDeliveryMethodTypeCd), lhsDeliveryMethodTypeCd, rhsDeliveryMethodTypeCd)) {
                return false;
            }
        }
        {
            boolean lhsByodIndicator;
            lhsByodIndicator = (true?this.isByodIndicator():false);
            boolean rhsByodIndicator;
            rhsByodIndicator = (true?that.isByodIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "byodIndicator", lhsByodIndicator), LocatorUtils.property(thatLocator, "byodIndicator", rhsByodIndicator), lhsByodIndicator, rhsByodIndicator)) {
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
