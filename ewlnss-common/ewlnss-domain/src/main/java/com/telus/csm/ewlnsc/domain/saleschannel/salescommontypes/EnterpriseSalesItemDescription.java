
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
 * <p>Java class for EnterpriseSalesItemDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnterpriseSalesItemDescription">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesItemDescription">
 *       &lt;sequence>
 *         &lt;element name="salesItemProductCatalogueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="salesItemParentCatalogueId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="materialItemCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnterpriseSalesItemDescription", propOrder = {
    "salesItemProductCatalogueId",
    "salesItemParentCatalogueId",
    "materialItemCode"
})
public class EnterpriseSalesItemDescription
    extends SalesItemDescription
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String salesItemProductCatalogueId;
    protected String salesItemParentCatalogueId;
    protected String materialItemCode;

    /**
     * Gets the value of the salesItemProductCatalogueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesItemProductCatalogueId() {
        return salesItemProductCatalogueId;
    }

    /**
     * Sets the value of the salesItemProductCatalogueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesItemProductCatalogueId(String value) {
        this.salesItemProductCatalogueId = value;
    }

    /**
     * Gets the value of the salesItemParentCatalogueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesItemParentCatalogueId() {
        return salesItemParentCatalogueId;
    }

    /**
     * Sets the value of the salesItemParentCatalogueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesItemParentCatalogueId(String value) {
        this.salesItemParentCatalogueId = value;
    }

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
            String theSalesItemProductCatalogueId;
            theSalesItemProductCatalogueId = this.getSalesItemProductCatalogueId();
            strategy.appendField(locator, this, "salesItemProductCatalogueId", buffer, theSalesItemProductCatalogueId);
        }
        {
            String theSalesItemParentCatalogueId;
            theSalesItemParentCatalogueId = this.getSalesItemParentCatalogueId();
            strategy.appendField(locator, this, "salesItemParentCatalogueId", buffer, theSalesItemParentCatalogueId);
        }
        {
            String theMaterialItemCode;
            theMaterialItemCode = this.getMaterialItemCode();
            strategy.appendField(locator, this, "materialItemCode", buffer, theMaterialItemCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EnterpriseSalesItemDescription)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final EnterpriseSalesItemDescription that = ((EnterpriseSalesItemDescription) object);
        {
            String lhsSalesItemProductCatalogueId;
            lhsSalesItemProductCatalogueId = this.getSalesItemProductCatalogueId();
            String rhsSalesItemProductCatalogueId;
            rhsSalesItemProductCatalogueId = that.getSalesItemProductCatalogueId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemProductCatalogueId", lhsSalesItemProductCatalogueId), LocatorUtils.property(thatLocator, "salesItemProductCatalogueId", rhsSalesItemProductCatalogueId), lhsSalesItemProductCatalogueId, rhsSalesItemProductCatalogueId)) {
                return false;
            }
        }
        {
            String lhsSalesItemParentCatalogueId;
            lhsSalesItemParentCatalogueId = this.getSalesItemParentCatalogueId();
            String rhsSalesItemParentCatalogueId;
            rhsSalesItemParentCatalogueId = that.getSalesItemParentCatalogueId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemParentCatalogueId", lhsSalesItemParentCatalogueId), LocatorUtils.property(thatLocator, "salesItemParentCatalogueId", rhsSalesItemParentCatalogueId), lhsSalesItemParentCatalogueId, rhsSalesItemParentCatalogueId)) {
                return false;
            }
        }
        {
            String lhsMaterialItemCode;
            lhsMaterialItemCode = this.getMaterialItemCode();
            String rhsMaterialItemCode;
            rhsMaterialItemCode = that.getMaterialItemCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "materialItemCode", lhsMaterialItemCode), LocatorUtils.property(thatLocator, "materialItemCode", rhsMaterialItemCode), lhsMaterialItemCode, rhsMaterialItemCode)) {
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
