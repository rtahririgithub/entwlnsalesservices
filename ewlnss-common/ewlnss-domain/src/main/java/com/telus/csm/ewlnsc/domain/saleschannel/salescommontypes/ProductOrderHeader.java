
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
 * <p>Java class for ProductOrderHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductOrderHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productOrderType" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductOrderType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductOrderHeader", propOrder = {
    "productOrderType"
})
public class ProductOrderHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected ProductOrderType productOrderType;

    /**
     * Gets the value of the productOrderType property.
     * 
     * @return
     *     possible object is
     *     {@link ProductOrderType }
     *     
     */
    public ProductOrderType getProductOrderType() {
        return productOrderType;
    }

    /**
     * Sets the value of the productOrderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductOrderType }
     *     
     */
    public void setProductOrderType(ProductOrderType value) {
        this.productOrderType = value;
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
            ProductOrderType theProductOrderType;
            theProductOrderType = this.getProductOrderType();
            strategy.appendField(locator, this, "productOrderType", buffer, theProductOrderType);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductOrderHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductOrderHeader that = ((ProductOrderHeader) object);
        {
            ProductOrderType lhsProductOrderType;
            lhsProductOrderType = this.getProductOrderType();
            ProductOrderType rhsProductOrderType;
            rhsProductOrderType = that.getProductOrderType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productOrderType", lhsProductOrderType), LocatorUtils.property(thatLocator, "productOrderType", rhsProductOrderType), lhsProductOrderType, rhsProductOrderType)) {
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
