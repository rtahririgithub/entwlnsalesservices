
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for ProductCatalogueIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductCatalogueIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="externalProductCatalogId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductCatalogueIdentifier", propOrder = {
    "productCatalogueId",
    "externalProductCatalogId"
})
public class ProductCatalogueIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productCatalogueId;
    protected String externalProductCatalogId;

    /**
     * Gets the value of the productCatalogueId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatalogueId() {
        return productCatalogueId;
    }

    /**
     * Sets the value of the productCatalogueId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatalogueId(String value) {
        this.productCatalogueId = value;
    }

    /**
     * Gets the value of the externalProductCatalogId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalProductCatalogId() {
        return externalProductCatalogId;
    }

    /**
     * Sets the value of the externalProductCatalogId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalProductCatalogId(String value) {
        this.externalProductCatalogId = value;
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
            String theProductCatalogueId;
            theProductCatalogueId = this.getProductCatalogueId();
            strategy.appendField(locator, this, "productCatalogueId", buffer, theProductCatalogueId);
        }
        {
            String theExternalProductCatalogId;
            theExternalProductCatalogId = this.getExternalProductCatalogId();
            strategy.appendField(locator, this, "externalProductCatalogId", buffer, theExternalProductCatalogId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductCatalogueIdentifier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductCatalogueIdentifier that = ((ProductCatalogueIdentifier) object);
        {
            String lhsProductCatalogueId;
            lhsProductCatalogueId = this.getProductCatalogueId();
            String rhsProductCatalogueId;
            rhsProductCatalogueId = that.getProductCatalogueId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueId", lhsProductCatalogueId), LocatorUtils.property(thatLocator, "productCatalogueId", rhsProductCatalogueId), lhsProductCatalogueId, rhsProductCatalogueId)) {
                return false;
            }
        }
        {
            String lhsExternalProductCatalogId;
            lhsExternalProductCatalogId = this.getExternalProductCatalogId();
            String rhsExternalProductCatalogId;
            rhsExternalProductCatalogId = that.getExternalProductCatalogId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "externalProductCatalogId", lhsExternalProductCatalogId), LocatorUtils.property(thatLocator, "externalProductCatalogId", rhsExternalProductCatalogId), lhsExternalProductCatalogId, rhsExternalProductCatalogId)) {
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
