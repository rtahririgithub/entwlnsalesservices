
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for AccessoryGroupProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryGroupProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productSubCategoryId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productClassId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productVendorId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productManufacturerId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryGroupProduct", propOrder = {
    "productCategoryId",
    "productSubCategoryId",
    "productClassId",
    "productVendorId",
    "productManufacturerId",
    "productCode"
})
@XmlSeeAlso({
    AccessoryGroupDiscountProduct.class
})
public class AccessoryGroupProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Long productCategoryId;
    protected Long productSubCategoryId;
    protected Long productClassId;
    protected Long productVendorId;
    protected Long productManufacturerId;
    protected String productCode;

    /**
     * Gets the value of the productCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductCategoryId() {
        return productCategoryId;
    }

    /**
     * Sets the value of the productCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductCategoryId(Long value) {
        this.productCategoryId = value;
    }

    /**
     * Gets the value of the productSubCategoryId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductSubCategoryId() {
        return productSubCategoryId;
    }

    /**
     * Sets the value of the productSubCategoryId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductSubCategoryId(Long value) {
        this.productSubCategoryId = value;
    }

    /**
     * Gets the value of the productClassId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductClassId() {
        return productClassId;
    }

    /**
     * Sets the value of the productClassId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductClassId(Long value) {
        this.productClassId = value;
    }

    /**
     * Gets the value of the productVendorId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductVendorId() {
        return productVendorId;
    }

    /**
     * Sets the value of the productVendorId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductVendorId(Long value) {
        this.productVendorId = value;
    }

    /**
     * Gets the value of the productManufacturerId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductManufacturerId() {
        return productManufacturerId;
    }

    /**
     * Sets the value of the productManufacturerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductManufacturerId(Long value) {
        this.productManufacturerId = value;
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
            Long theProductCategoryId;
            theProductCategoryId = this.getProductCategoryId();
            strategy.appendField(locator, this, "productCategoryId", buffer, theProductCategoryId);
        }
        {
            Long theProductSubCategoryId;
            theProductSubCategoryId = this.getProductSubCategoryId();
            strategy.appendField(locator, this, "productSubCategoryId", buffer, theProductSubCategoryId);
        }
        {
            Long theProductClassId;
            theProductClassId = this.getProductClassId();
            strategy.appendField(locator, this, "productClassId", buffer, theProductClassId);
        }
        {
            Long theProductVendorId;
            theProductVendorId = this.getProductVendorId();
            strategy.appendField(locator, this, "productVendorId", buffer, theProductVendorId);
        }
        {
            Long theProductManufacturerId;
            theProductManufacturerId = this.getProductManufacturerId();
            strategy.appendField(locator, this, "productManufacturerId", buffer, theProductManufacturerId);
        }
        {
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryGroupProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryGroupProduct that = ((AccessoryGroupProduct) object);
        {
            Long lhsProductCategoryId;
            lhsProductCategoryId = this.getProductCategoryId();
            Long rhsProductCategoryId;
            rhsProductCategoryId = that.getProductCategoryId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCategoryId", lhsProductCategoryId), LocatorUtils.property(thatLocator, "productCategoryId", rhsProductCategoryId), lhsProductCategoryId, rhsProductCategoryId)) {
                return false;
            }
        }
        {
            Long lhsProductSubCategoryId;
            lhsProductSubCategoryId = this.getProductSubCategoryId();
            Long rhsProductSubCategoryId;
            rhsProductSubCategoryId = that.getProductSubCategoryId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productSubCategoryId", lhsProductSubCategoryId), LocatorUtils.property(thatLocator, "productSubCategoryId", rhsProductSubCategoryId), lhsProductSubCategoryId, rhsProductSubCategoryId)) {
                return false;
            }
        }
        {
            Long lhsProductClassId;
            lhsProductClassId = this.getProductClassId();
            Long rhsProductClassId;
            rhsProductClassId = that.getProductClassId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productClassId", lhsProductClassId), LocatorUtils.property(thatLocator, "productClassId", rhsProductClassId), lhsProductClassId, rhsProductClassId)) {
                return false;
            }
        }
        {
            Long lhsProductVendorId;
            lhsProductVendorId = this.getProductVendorId();
            Long rhsProductVendorId;
            rhsProductVendorId = that.getProductVendorId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productVendorId", lhsProductVendorId), LocatorUtils.property(thatLocator, "productVendorId", rhsProductVendorId), lhsProductVendorId, rhsProductVendorId)) {
                return false;
            }
        }
        {
            Long lhsProductManufacturerId;
            lhsProductManufacturerId = this.getProductManufacturerId();
            Long rhsProductManufacturerId;
            rhsProductManufacturerId = that.getProductManufacturerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productManufacturerId", lhsProductManufacturerId), LocatorUtils.property(thatLocator, "productManufacturerId", rhsProductManufacturerId), lhsProductManufacturerId, rhsProductManufacturerId)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
