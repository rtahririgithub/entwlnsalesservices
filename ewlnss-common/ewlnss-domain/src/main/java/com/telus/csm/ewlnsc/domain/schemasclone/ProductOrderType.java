
package com.telus.csm.ewlnsc.domain.schemasclone;

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
 * <p>Java class for ProductOrderType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductOrderType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productOrderTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productOrderSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductOrderType", propOrder = {
    "productOrderTypeCd",
    "productOrderSubTypeCd"
})
public class ProductOrderType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String productOrderTypeCd;
    protected String productOrderSubTypeCd;

    /**
     * Gets the value of the productOrderTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOrderTypeCd() {
        return productOrderTypeCd;
    }

    /**
     * Sets the value of the productOrderTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOrderTypeCd(String value) {
        this.productOrderTypeCd = value;
    }

    /**
     * Gets the value of the productOrderSubTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductOrderSubTypeCd() {
        return productOrderSubTypeCd;
    }

    /**
     * Sets the value of the productOrderSubTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductOrderSubTypeCd(String value) {
        this.productOrderSubTypeCd = value;
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
            String theProductOrderTypeCd;
            theProductOrderTypeCd = this.getProductOrderTypeCd();
            strategy.appendField(locator, this, "productOrderTypeCd", buffer, theProductOrderTypeCd);
        }
        {
            String theProductOrderSubTypeCd;
            theProductOrderSubTypeCd = this.getProductOrderSubTypeCd();
            strategy.appendField(locator, this, "productOrderSubTypeCd", buffer, theProductOrderSubTypeCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductOrderType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductOrderType that = ((ProductOrderType) object);
        {
            String lhsProductOrderTypeCd;
            lhsProductOrderTypeCd = this.getProductOrderTypeCd();
            String rhsProductOrderTypeCd;
            rhsProductOrderTypeCd = that.getProductOrderTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productOrderTypeCd", lhsProductOrderTypeCd), LocatorUtils.property(thatLocator, "productOrderTypeCd", rhsProductOrderTypeCd), lhsProductOrderTypeCd, rhsProductOrderTypeCd)) {
                return false;
            }
        }
        {
            String lhsProductOrderSubTypeCd;
            lhsProductOrderSubTypeCd = this.getProductOrderSubTypeCd();
            String rhsProductOrderSubTypeCd;
            rhsProductOrderSubTypeCd = that.getProductOrderSubTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productOrderSubTypeCd", lhsProductOrderSubTypeCd), LocatorUtils.property(thatLocator, "productOrderSubTypeCd", rhsProductOrderSubTypeCd), lhsProductOrderSubTypeCd, rhsProductOrderSubTypeCd)) {
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
