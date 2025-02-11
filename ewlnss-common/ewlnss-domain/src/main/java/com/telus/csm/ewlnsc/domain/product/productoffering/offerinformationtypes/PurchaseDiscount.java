
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for PurchaseDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PurchaseDiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="purchaseDiscountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contractTermId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchaseDiscount", propOrder = {
    "purchaseDiscountCode",
    "contractTermId"
})
public class PurchaseDiscount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String purchaseDiscountCode;
    @XmlElement(required = true)
    protected BigInteger contractTermId;

    /**
     * Gets the value of the purchaseDiscountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPurchaseDiscountCode() {
        return purchaseDiscountCode;
    }

    /**
     * Sets the value of the purchaseDiscountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPurchaseDiscountCode(String value) {
        this.purchaseDiscountCode = value;
    }

    /**
     * Gets the value of the contractTermId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getContractTermId() {
        return contractTermId;
    }

    /**
     * Sets the value of the contractTermId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTermId(BigInteger value) {
        this.contractTermId = value;
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
            String thePurchaseDiscountCode;
            thePurchaseDiscountCode = this.getPurchaseDiscountCode();
            strategy.appendField(locator, this, "purchaseDiscountCode", buffer, thePurchaseDiscountCode);
        }
        {
            BigInteger theContractTermId;
            theContractTermId = this.getContractTermId();
            strategy.appendField(locator, this, "contractTermId", buffer, theContractTermId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PurchaseDiscount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PurchaseDiscount that = ((PurchaseDiscount) object);
        {
            String lhsPurchaseDiscountCode;
            lhsPurchaseDiscountCode = this.getPurchaseDiscountCode();
            String rhsPurchaseDiscountCode;
            rhsPurchaseDiscountCode = that.getPurchaseDiscountCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseDiscountCode", lhsPurchaseDiscountCode), LocatorUtils.property(thatLocator, "purchaseDiscountCode", rhsPurchaseDiscountCode), lhsPurchaseDiscountCode, rhsPurchaseDiscountCode)) {
                return false;
            }
        }
        {
            BigInteger lhsContractTermId;
            lhsContractTermId = this.getContractTermId();
            BigInteger rhsContractTermId;
            rhsContractTermId = that.getContractTermId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermId", lhsContractTermId), LocatorUtils.property(thatLocator, "contractTermId", rhsContractTermId), lhsContractTermId, rhsContractTermId)) {
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
