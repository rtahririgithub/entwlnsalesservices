
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
 * <p>Java class for ProductTypeQualification complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductTypeQualification">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="qualifiedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductTypeQualification", propOrder = {
    "productTypeCode",
    "qualifiedInd"
})
public class ProductTypeQualification
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productTypeCode;
    protected boolean qualifiedInd;

    /**
     * Gets the value of the productTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCode() {
        return productTypeCode;
    }

    /**
     * Sets the value of the productTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCode(String value) {
        this.productTypeCode = value;
    }

    /**
     * Gets the value of the qualifiedInd property.
     * 
     */
    public boolean isQualifiedInd() {
        return qualifiedInd;
    }

    /**
     * Sets the value of the qualifiedInd property.
     * 
     */
    public void setQualifiedInd(boolean value) {
        this.qualifiedInd = value;
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
            String theProductTypeCode;
            theProductTypeCode = this.getProductTypeCode();
            strategy.appendField(locator, this, "productTypeCode", buffer, theProductTypeCode);
        }
        {
            boolean theQualifiedInd;
            theQualifiedInd = (true?this.isQualifiedInd():false);
            strategy.appendField(locator, this, "qualifiedInd", buffer, theQualifiedInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductTypeQualification)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductTypeQualification that = ((ProductTypeQualification) object);
        {
            String lhsProductTypeCode;
            lhsProductTypeCode = this.getProductTypeCode();
            String rhsProductTypeCode;
            rhsProductTypeCode = that.getProductTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeCode", lhsProductTypeCode), LocatorUtils.property(thatLocator, "productTypeCode", rhsProductTypeCode), lhsProductTypeCode, rhsProductTypeCode)) {
                return false;
            }
        }
        {
            boolean lhsQualifiedInd;
            lhsQualifiedInd = (true?this.isQualifiedInd():false);
            boolean rhsQualifiedInd;
            rhsQualifiedInd = (true?that.isQualifiedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "qualifiedInd", lhsQualifiedInd), LocatorUtils.property(thatLocator, "qualifiedInd", rhsQualifiedInd), lhsQualifiedInd, rhsQualifiedInd)) {
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
