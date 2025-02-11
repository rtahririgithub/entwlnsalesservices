
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
 * <p>Java class for ProductEquipment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductEquipment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="maxNumberOfEquipment" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductEquipment", propOrder = {
    "productTypeCode",
    "maxNumberOfEquipment"
})
public class ProductEquipment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productTypeCode;
    protected int maxNumberOfEquipment;

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
     * Gets the value of the maxNumberOfEquipment property.
     * 
     */
    public int getMaxNumberOfEquipment() {
        return maxNumberOfEquipment;
    }

    /**
     * Sets the value of the maxNumberOfEquipment property.
     * 
     */
    public void setMaxNumberOfEquipment(int value) {
        this.maxNumberOfEquipment = value;
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
            int theMaxNumberOfEquipment;
            theMaxNumberOfEquipment = (true?this.getMaxNumberOfEquipment(): 0);
            strategy.appendField(locator, this, "maxNumberOfEquipment", buffer, theMaxNumberOfEquipment);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductEquipment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductEquipment that = ((ProductEquipment) object);
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
            int lhsMaxNumberOfEquipment;
            lhsMaxNumberOfEquipment = (true?this.getMaxNumberOfEquipment(): 0);
            int rhsMaxNumberOfEquipment;
            rhsMaxNumberOfEquipment = (true?that.getMaxNumberOfEquipment(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxNumberOfEquipment", lhsMaxNumberOfEquipment), LocatorUtils.property(thatLocator, "maxNumberOfEquipment", rhsMaxNumberOfEquipment), lhsMaxNumberOfEquipment, rhsMaxNumberOfEquipment)) {
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
