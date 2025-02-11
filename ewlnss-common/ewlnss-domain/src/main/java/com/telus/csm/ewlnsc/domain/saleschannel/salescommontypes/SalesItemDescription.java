
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for SalesItemDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesItemDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesItemTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="salesItemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="salesItemDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesItemDescription", propOrder = {
    "salesItemTypeCd",
    "salesItemCode",
    "salesItemDescription"
})
@XmlSeeAlso({
    EnterpriseSalesItemDescription.class
})
public class SalesItemDescription
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String salesItemTypeCd;
    @XmlElement(required = true)
    protected String salesItemCode;
    @XmlElement(required = true)
    protected MultilingualDescriptionList salesItemDescription;

    /**
     * Gets the value of the salesItemTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesItemTypeCd() {
        return salesItemTypeCd;
    }

    /**
     * Sets the value of the salesItemTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesItemTypeCd(String value) {
        this.salesItemTypeCd = value;
    }

    /**
     * Gets the value of the salesItemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSalesItemCode() {
        return salesItemCode;
    }

    /**
     * Sets the value of the salesItemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSalesItemCode(String value) {
        this.salesItemCode = value;
    }

    /**
     * Gets the value of the salesItemDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getSalesItemDescription() {
        return salesItemDescription;
    }

    /**
     * Sets the value of the salesItemDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setSalesItemDescription(MultilingualDescriptionList value) {
        this.salesItemDescription = value;
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
            String theSalesItemTypeCd;
            theSalesItemTypeCd = this.getSalesItemTypeCd();
            strategy.appendField(locator, this, "salesItemTypeCd", buffer, theSalesItemTypeCd);
        }
        {
            String theSalesItemCode;
            theSalesItemCode = this.getSalesItemCode();
            strategy.appendField(locator, this, "salesItemCode", buffer, theSalesItemCode);
        }
        {
            MultilingualDescriptionList theSalesItemDescription;
            theSalesItemDescription = this.getSalesItemDescription();
            strategy.appendField(locator, this, "salesItemDescription", buffer, theSalesItemDescription);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesItemDescription)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesItemDescription that = ((SalesItemDescription) object);
        {
            String lhsSalesItemTypeCd;
            lhsSalesItemTypeCd = this.getSalesItemTypeCd();
            String rhsSalesItemTypeCd;
            rhsSalesItemTypeCd = that.getSalesItemTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemTypeCd", lhsSalesItemTypeCd), LocatorUtils.property(thatLocator, "salesItemTypeCd", rhsSalesItemTypeCd), lhsSalesItemTypeCd, rhsSalesItemTypeCd)) {
                return false;
            }
        }
        {
            String lhsSalesItemCode;
            lhsSalesItemCode = this.getSalesItemCode();
            String rhsSalesItemCode;
            rhsSalesItemCode = that.getSalesItemCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemCode", lhsSalesItemCode), LocatorUtils.property(thatLocator, "salesItemCode", rhsSalesItemCode), lhsSalesItemCode, rhsSalesItemCode)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsSalesItemDescription;
            lhsSalesItemDescription = this.getSalesItemDescription();
            MultilingualDescriptionList rhsSalesItemDescription;
            rhsSalesItemDescription = that.getSalesItemDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemDescription", lhsSalesItemDescription), LocatorUtils.property(thatLocator, "salesItemDescription", rhsSalesItemDescription), lhsSalesItemDescription, rhsSalesItemDescription)) {
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
