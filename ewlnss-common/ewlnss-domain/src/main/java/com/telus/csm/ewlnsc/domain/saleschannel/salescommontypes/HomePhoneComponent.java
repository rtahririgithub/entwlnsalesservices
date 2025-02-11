
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
 * <p>Java class for HomePhoneComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HomePhoneComponent">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}HomePhoneComponentHeader">
 *       &lt;sequence>
 *         &lt;element name="productOrderHeader" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductOrderHeader"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HomePhoneComponent", propOrder = {
    "productOrderHeader"
})
public class HomePhoneComponent
    extends HomePhoneComponentHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductOrderHeader productOrderHeader;

    /**
     * Gets the value of the productOrderHeader property.
     * 
     * @return
     *     possible object is
     *     {@link ProductOrderHeader }
     *     
     */
    public ProductOrderHeader getProductOrderHeader() {
        return productOrderHeader;
    }

    /**
     * Sets the value of the productOrderHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductOrderHeader }
     *     
     */
    public void setProductOrderHeader(ProductOrderHeader value) {
        this.productOrderHeader = value;
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
            ProductOrderHeader theProductOrderHeader;
            theProductOrderHeader = this.getProductOrderHeader();
            strategy.appendField(locator, this, "productOrderHeader", buffer, theProductOrderHeader);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HomePhoneComponent)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final HomePhoneComponent that = ((HomePhoneComponent) object);
        {
            ProductOrderHeader lhsProductOrderHeader;
            lhsProductOrderHeader = this.getProductOrderHeader();
            ProductOrderHeader rhsProductOrderHeader;
            rhsProductOrderHeader = that.getProductOrderHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productOrderHeader", lhsProductOrderHeader), LocatorUtils.property(thatLocator, "productOrderHeader", rhsProductOrderHeader), lhsProductOrderHeader, rhsProductOrderHeader)) {
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
