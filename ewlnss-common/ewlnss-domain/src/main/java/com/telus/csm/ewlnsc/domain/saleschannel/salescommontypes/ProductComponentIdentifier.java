
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for ProductComponentIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductComponentIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="parentProductCatalogueIdentifier" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductComponentIdentifier", propOrder = {
    "productCatalogueIdentifier",
    "parentProductCatalogueIdentifier"
})
@XmlSeeAlso({
    WirelineEquipmentHeader.class
})
public class ProductComponentIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productCatalogueIdentifier;
    @XmlElement(required = true)
    protected String parentProductCatalogueIdentifier;

    /**
     * Gets the value of the productCatalogueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatalogueIdentifier() {
        return productCatalogueIdentifier;
    }

    /**
     * Sets the value of the productCatalogueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatalogueIdentifier(String value) {
        this.productCatalogueIdentifier = value;
    }

    /**
     * Gets the value of the parentProductCatalogueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentProductCatalogueIdentifier() {
        return parentProductCatalogueIdentifier;
    }

    /**
     * Sets the value of the parentProductCatalogueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentProductCatalogueIdentifier(String value) {
        this.parentProductCatalogueIdentifier = value;
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
            String theProductCatalogueIdentifier;
            theProductCatalogueIdentifier = this.getProductCatalogueIdentifier();
            strategy.appendField(locator, this, "productCatalogueIdentifier", buffer, theProductCatalogueIdentifier);
        }
        {
            String theParentProductCatalogueIdentifier;
            theParentProductCatalogueIdentifier = this.getParentProductCatalogueIdentifier();
            strategy.appendField(locator, this, "parentProductCatalogueIdentifier", buffer, theParentProductCatalogueIdentifier);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductComponentIdentifier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductComponentIdentifier that = ((ProductComponentIdentifier) object);
        {
            String lhsProductCatalogueIdentifier;
            lhsProductCatalogueIdentifier = this.getProductCatalogueIdentifier();
            String rhsProductCatalogueIdentifier;
            rhsProductCatalogueIdentifier = that.getProductCatalogueIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueIdentifier", lhsProductCatalogueIdentifier), LocatorUtils.property(thatLocator, "productCatalogueIdentifier", rhsProductCatalogueIdentifier), lhsProductCatalogueIdentifier, rhsProductCatalogueIdentifier)) {
                return false;
            }
        }
        {
            String lhsParentProductCatalogueIdentifier;
            lhsParentProductCatalogueIdentifier = this.getParentProductCatalogueIdentifier();
            String rhsParentProductCatalogueIdentifier;
            rhsParentProductCatalogueIdentifier = that.getParentProductCatalogueIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "parentProductCatalogueIdentifier", lhsParentProductCatalogueIdentifier), LocatorUtils.property(thatLocator, "parentProductCatalogueIdentifier", rhsParentProductCatalogueIdentifier), lhsParentProductCatalogueIdentifier, rhsParentProductCatalogueIdentifier)) {
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
