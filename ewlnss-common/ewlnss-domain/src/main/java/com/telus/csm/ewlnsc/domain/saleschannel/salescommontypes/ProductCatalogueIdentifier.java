
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
 * <p>Java class for ProductCatalogueIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductCatalogueIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueItemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="externalProductCatalogueItemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "productCatalogueItemId",
    "externalProductCatalogueItemId"
})
public class ProductCatalogueIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productCatalogueItemId;
    protected String externalProductCatalogueItemId;

    /**
     * Gets the value of the productCatalogueItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatalogueItemId() {
        return productCatalogueItemId;
    }

    /**
     * Sets the value of the productCatalogueItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatalogueItemId(String value) {
        this.productCatalogueItemId = value;
    }

    /**
     * Gets the value of the externalProductCatalogueItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalProductCatalogueItemId() {
        return externalProductCatalogueItemId;
    }

    /**
     * Sets the value of the externalProductCatalogueItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalProductCatalogueItemId(String value) {
        this.externalProductCatalogueItemId = value;
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
            String theProductCatalogueItemId;
            theProductCatalogueItemId = this.getProductCatalogueItemId();
            strategy.appendField(locator, this, "productCatalogueItemId", buffer, theProductCatalogueItemId);
        }
        {
            String theExternalProductCatalogueItemId;
            theExternalProductCatalogueItemId = this.getExternalProductCatalogueItemId();
            strategy.appendField(locator, this, "externalProductCatalogueItemId", buffer, theExternalProductCatalogueItemId);
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
            String lhsProductCatalogueItemId;
            lhsProductCatalogueItemId = this.getProductCatalogueItemId();
            String rhsProductCatalogueItemId;
            rhsProductCatalogueItemId = that.getProductCatalogueItemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueItemId", lhsProductCatalogueItemId), LocatorUtils.property(thatLocator, "productCatalogueItemId", rhsProductCatalogueItemId), lhsProductCatalogueItemId, rhsProductCatalogueItemId)) {
                return false;
            }
        }
        {
            String lhsExternalProductCatalogueItemId;
            lhsExternalProductCatalogueItemId = this.getExternalProductCatalogueItemId();
            String rhsExternalProductCatalogueItemId;
            rhsExternalProductCatalogueItemId = that.getExternalProductCatalogueItemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "externalProductCatalogueItemId", lhsExternalProductCatalogueItemId), LocatorUtils.property(thatLocator, "externalProductCatalogueItemId", rhsExternalProductCatalogueItemId), lhsExternalProductCatalogueItemId, rhsExternalProductCatalogueItemId)) {
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
