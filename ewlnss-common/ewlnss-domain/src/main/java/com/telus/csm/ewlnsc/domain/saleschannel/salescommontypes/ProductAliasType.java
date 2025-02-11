
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
 * Product Alias Ids, set to differentiate between different ids and names used by Vendors.
 * 
 * <p>Java class for ProductAliasType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductAliasType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productAliasName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productAliasId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productAliasCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductAliasType", propOrder = {
    "productAliasName",
    "productAliasId",
    "productAliasCd"
})
public class ProductAliasType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String productAliasName;
    protected String productAliasId;
    protected String productAliasCd;

    /**
     * Gets the value of the productAliasName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductAliasName() {
        return productAliasName;
    }

    /**
     * Sets the value of the productAliasName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductAliasName(String value) {
        this.productAliasName = value;
    }

    /**
     * Gets the value of the productAliasId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductAliasId() {
        return productAliasId;
    }

    /**
     * Sets the value of the productAliasId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductAliasId(String value) {
        this.productAliasId = value;
    }

    /**
     * Gets the value of the productAliasCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductAliasCd() {
        return productAliasCd;
    }

    /**
     * Sets the value of the productAliasCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductAliasCd(String value) {
        this.productAliasCd = value;
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
            String theProductAliasName;
            theProductAliasName = this.getProductAliasName();
            strategy.appendField(locator, this, "productAliasName", buffer, theProductAliasName);
        }
        {
            String theProductAliasId;
            theProductAliasId = this.getProductAliasId();
            strategy.appendField(locator, this, "productAliasId", buffer, theProductAliasId);
        }
        {
            String theProductAliasCd;
            theProductAliasCd = this.getProductAliasCd();
            strategy.appendField(locator, this, "productAliasCd", buffer, theProductAliasCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductAliasType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductAliasType that = ((ProductAliasType) object);
        {
            String lhsProductAliasName;
            lhsProductAliasName = this.getProductAliasName();
            String rhsProductAliasName;
            rhsProductAliasName = that.getProductAliasName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productAliasName", lhsProductAliasName), LocatorUtils.property(thatLocator, "productAliasName", rhsProductAliasName), lhsProductAliasName, rhsProductAliasName)) {
                return false;
            }
        }
        {
            String lhsProductAliasId;
            lhsProductAliasId = this.getProductAliasId();
            String rhsProductAliasId;
            rhsProductAliasId = that.getProductAliasId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productAliasId", lhsProductAliasId), LocatorUtils.property(thatLocator, "productAliasId", rhsProductAliasId), lhsProductAliasId, rhsProductAliasId)) {
                return false;
            }
        }
        {
            String lhsProductAliasCd;
            lhsProductAliasCd = this.getProductAliasCd();
            String rhsProductAliasCd;
            rhsProductAliasCd = that.getProductAliasCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productAliasCd", lhsProductAliasCd), LocatorUtils.property(thatLocator, "productAliasCd", rhsProductAliasCd), lhsProductAliasCd, rhsProductAliasCd)) {
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
