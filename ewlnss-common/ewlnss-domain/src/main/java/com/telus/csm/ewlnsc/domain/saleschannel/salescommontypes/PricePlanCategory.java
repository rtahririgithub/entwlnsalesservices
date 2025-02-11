
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PricePlanCategory complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricePlanCategory">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricePlanCategoryDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList"/>
 *         &lt;element name="pricePlanCategoryTypeText" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricePlanCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricePlanCategory", propOrder = {
    "pricePlanCategoryDescriptionTextList",
    "pricePlanCategoryTypeText",
    "pricePlanCategoryCode"
})
public class PricePlanCategory
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected MultilingualCodeDescTextList pricePlanCategoryDescriptionTextList;
    @XmlElement(required = true)
    protected String pricePlanCategoryTypeText;
    @XmlElement(required = true)
    protected String pricePlanCategoryCode;

    /**
     * Gets the value of the pricePlanCategoryDescriptionTextList property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getPricePlanCategoryDescriptionTextList() {
        return pricePlanCategoryDescriptionTextList;
    }

    /**
     * Sets the value of the pricePlanCategoryDescriptionTextList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setPricePlanCategoryDescriptionTextList(MultilingualCodeDescTextList value) {
        this.pricePlanCategoryDescriptionTextList = value;
    }

    /**
     * Gets the value of the pricePlanCategoryTypeText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanCategoryTypeText() {
        return pricePlanCategoryTypeText;
    }

    /**
     * Sets the value of the pricePlanCategoryTypeText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanCategoryTypeText(String value) {
        this.pricePlanCategoryTypeText = value;
    }

    /**
     * Gets the value of the pricePlanCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanCategoryCode() {
        return pricePlanCategoryCode;
    }

    /**
     * Sets the value of the pricePlanCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanCategoryCode(String value) {
        this.pricePlanCategoryCode = value;
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
            MultilingualCodeDescTextList thePricePlanCategoryDescriptionTextList;
            thePricePlanCategoryDescriptionTextList = this.getPricePlanCategoryDescriptionTextList();
            strategy.appendField(locator, this, "pricePlanCategoryDescriptionTextList", buffer, thePricePlanCategoryDescriptionTextList);
        }
        {
            String thePricePlanCategoryTypeText;
            thePricePlanCategoryTypeText = this.getPricePlanCategoryTypeText();
            strategy.appendField(locator, this, "pricePlanCategoryTypeText", buffer, thePricePlanCategoryTypeText);
        }
        {
            String thePricePlanCategoryCode;
            thePricePlanCategoryCode = this.getPricePlanCategoryCode();
            strategy.appendField(locator, this, "pricePlanCategoryCode", buffer, thePricePlanCategoryCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PricePlanCategory)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PricePlanCategory that = ((PricePlanCategory) object);
        {
            MultilingualCodeDescTextList lhsPricePlanCategoryDescriptionTextList;
            lhsPricePlanCategoryDescriptionTextList = this.getPricePlanCategoryDescriptionTextList();
            MultilingualCodeDescTextList rhsPricePlanCategoryDescriptionTextList;
            rhsPricePlanCategoryDescriptionTextList = that.getPricePlanCategoryDescriptionTextList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCategoryDescriptionTextList", lhsPricePlanCategoryDescriptionTextList), LocatorUtils.property(thatLocator, "pricePlanCategoryDescriptionTextList", rhsPricePlanCategoryDescriptionTextList), lhsPricePlanCategoryDescriptionTextList, rhsPricePlanCategoryDescriptionTextList)) {
                return false;
            }
        }
        {
            String lhsPricePlanCategoryTypeText;
            lhsPricePlanCategoryTypeText = this.getPricePlanCategoryTypeText();
            String rhsPricePlanCategoryTypeText;
            rhsPricePlanCategoryTypeText = that.getPricePlanCategoryTypeText();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCategoryTypeText", lhsPricePlanCategoryTypeText), LocatorUtils.property(thatLocator, "pricePlanCategoryTypeText", rhsPricePlanCategoryTypeText), lhsPricePlanCategoryTypeText, rhsPricePlanCategoryTypeText)) {
                return false;
            }
        }
        {
            String lhsPricePlanCategoryCode;
            lhsPricePlanCategoryCode = this.getPricePlanCategoryCode();
            String rhsPricePlanCategoryCode;
            rhsPricePlanCategoryCode = that.getPricePlanCategoryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCategoryCode", lhsPricePlanCategoryCode), LocatorUtils.property(thatLocator, "pricePlanCategoryCode", rhsPricePlanCategoryCode), lhsPricePlanCategoryCode, rhsPricePlanCategoryCode)) {
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
