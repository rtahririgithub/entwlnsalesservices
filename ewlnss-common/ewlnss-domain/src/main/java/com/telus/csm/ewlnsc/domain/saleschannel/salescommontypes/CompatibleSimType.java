
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
 * <p>Java class for CompatibleSimType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CompatibleSimType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eSimProfileInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CompatibleSimType", propOrder = {
    "productId",
    "productCode",
    "eSimProfileInd"
})
public class CompatibleSimType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected long productId;
    @XmlElement(required = true)
    protected String productCode;
    protected Boolean eSimProfileInd;

    /**
     * Gets the value of the productId property.
     * 
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     */
    public void setProductId(long value) {
        this.productId = value;
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

    /**
     * Gets the value of the eSimProfileInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isESimProfileInd() {
        return eSimProfileInd;
    }

    /**
     * Sets the value of the eSimProfileInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setESimProfileInd(Boolean value) {
        this.eSimProfileInd = value;
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
            long theProductId;
            theProductId = (true?this.getProductId(): 0L);
            strategy.appendField(locator, this, "productId", buffer, theProductId);
        }
        {
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        {
            Boolean theESimProfileInd;
            theESimProfileInd = this.isESimProfileInd();
            strategy.appendField(locator, this, "eSimProfileInd", buffer, theESimProfileInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CompatibleSimType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CompatibleSimType that = ((CompatibleSimType) object);
        {
            long lhsProductId;
            lhsProductId = (true?this.getProductId(): 0L);
            long rhsProductId;
            rhsProductId = (true?that.getProductId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productId", lhsProductId), LocatorUtils.property(thatLocator, "productId", rhsProductId), lhsProductId, rhsProductId)) {
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
        {
            Boolean lhsESimProfileInd;
            lhsESimProfileInd = this.isESimProfileInd();
            Boolean rhsESimProfileInd;
            rhsESimProfileInd = that.isESimProfileInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eSimProfileInd", lhsESimProfileInd), LocatorUtils.property(thatLocator, "eSimProfileInd", rhsESimProfileInd), lhsESimProfileInd, rhsESimProfileInd)) {
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
