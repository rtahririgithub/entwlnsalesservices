
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
 * Payment details - currently, used for payment of purchase Equipment
 * 
 * <p>Java class for PaymentOption complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentOption">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentOptionTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="paymentPlanId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" minOccurs="0"/>
 *         &lt;element name="paymentPlanParentId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" minOccurs="0"/>
 *         &lt;element name="paymentInstallmentQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="paymentInstallmentAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentOption", propOrder = {
    "paymentOptionTypeCd",
    "paymentAmt",
    "paymentPlanId",
    "paymentPlanParentId",
    "paymentInstallmentQty",
    "paymentInstallmentAmt"
})
public class PaymentOption
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String paymentOptionTypeCd;
    protected Double paymentAmt;
    protected ProductCatalogueIdentifier paymentPlanId;
    protected ProductCatalogueIdentifier paymentPlanParentId;
    protected BigInteger paymentInstallmentQty;
    protected Double paymentInstallmentAmt;

    /**
     * Gets the value of the paymentOptionTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentOptionTypeCd() {
        return paymentOptionTypeCd;
    }

    /**
     * Sets the value of the paymentOptionTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentOptionTypeCd(String value) {
        this.paymentOptionTypeCd = value;
    }

    /**
     * Gets the value of the paymentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPaymentAmt() {
        return paymentAmt;
    }

    /**
     * Sets the value of the paymentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPaymentAmt(Double value) {
        this.paymentAmt = value;
    }

    /**
     * Gets the value of the paymentPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getPaymentPlanId() {
        return paymentPlanId;
    }

    /**
     * Sets the value of the paymentPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setPaymentPlanId(ProductCatalogueIdentifier value) {
        this.paymentPlanId = value;
    }

    /**
     * Gets the value of the paymentPlanParentId property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getPaymentPlanParentId() {
        return paymentPlanParentId;
    }

    /**
     * Sets the value of the paymentPlanParentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setPaymentPlanParentId(ProductCatalogueIdentifier value) {
        this.paymentPlanParentId = value;
    }

    /**
     * Gets the value of the paymentInstallmentQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPaymentInstallmentQty() {
        return paymentInstallmentQty;
    }

    /**
     * Sets the value of the paymentInstallmentQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPaymentInstallmentQty(BigInteger value) {
        this.paymentInstallmentQty = value;
    }

    /**
     * Gets the value of the paymentInstallmentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPaymentInstallmentAmt() {
        return paymentInstallmentAmt;
    }

    /**
     * Sets the value of the paymentInstallmentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPaymentInstallmentAmt(Double value) {
        this.paymentInstallmentAmt = value;
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
            String thePaymentOptionTypeCd;
            thePaymentOptionTypeCd = this.getPaymentOptionTypeCd();
            strategy.appendField(locator, this, "paymentOptionTypeCd", buffer, thePaymentOptionTypeCd);
        }
        {
            Double thePaymentAmt;
            thePaymentAmt = this.getPaymentAmt();
            strategy.appendField(locator, this, "paymentAmt", buffer, thePaymentAmt);
        }
        {
            ProductCatalogueIdentifier thePaymentPlanId;
            thePaymentPlanId = this.getPaymentPlanId();
            strategy.appendField(locator, this, "paymentPlanId", buffer, thePaymentPlanId);
        }
        {
            ProductCatalogueIdentifier thePaymentPlanParentId;
            thePaymentPlanParentId = this.getPaymentPlanParentId();
            strategy.appendField(locator, this, "paymentPlanParentId", buffer, thePaymentPlanParentId);
        }
        {
            BigInteger thePaymentInstallmentQty;
            thePaymentInstallmentQty = this.getPaymentInstallmentQty();
            strategy.appendField(locator, this, "paymentInstallmentQty", buffer, thePaymentInstallmentQty);
        }
        {
            Double thePaymentInstallmentAmt;
            thePaymentInstallmentAmt = this.getPaymentInstallmentAmt();
            strategy.appendField(locator, this, "paymentInstallmentAmt", buffer, thePaymentInstallmentAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PaymentOption)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PaymentOption that = ((PaymentOption) object);
        {
            String lhsPaymentOptionTypeCd;
            lhsPaymentOptionTypeCd = this.getPaymentOptionTypeCd();
            String rhsPaymentOptionTypeCd;
            rhsPaymentOptionTypeCd = that.getPaymentOptionTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentOptionTypeCd", lhsPaymentOptionTypeCd), LocatorUtils.property(thatLocator, "paymentOptionTypeCd", rhsPaymentOptionTypeCd), lhsPaymentOptionTypeCd, rhsPaymentOptionTypeCd)) {
                return false;
            }
        }
        {
            Double lhsPaymentAmt;
            lhsPaymentAmt = this.getPaymentAmt();
            Double rhsPaymentAmt;
            rhsPaymentAmt = that.getPaymentAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentAmt", lhsPaymentAmt), LocatorUtils.property(thatLocator, "paymentAmt", rhsPaymentAmt), lhsPaymentAmt, rhsPaymentAmt)) {
                return false;
            }
        }
        {
            ProductCatalogueIdentifier lhsPaymentPlanId;
            lhsPaymentPlanId = this.getPaymentPlanId();
            ProductCatalogueIdentifier rhsPaymentPlanId;
            rhsPaymentPlanId = that.getPaymentPlanId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentPlanId", lhsPaymentPlanId), LocatorUtils.property(thatLocator, "paymentPlanId", rhsPaymentPlanId), lhsPaymentPlanId, rhsPaymentPlanId)) {
                return false;
            }
        }
        {
            ProductCatalogueIdentifier lhsPaymentPlanParentId;
            lhsPaymentPlanParentId = this.getPaymentPlanParentId();
            ProductCatalogueIdentifier rhsPaymentPlanParentId;
            rhsPaymentPlanParentId = that.getPaymentPlanParentId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentPlanParentId", lhsPaymentPlanParentId), LocatorUtils.property(thatLocator, "paymentPlanParentId", rhsPaymentPlanParentId), lhsPaymentPlanParentId, rhsPaymentPlanParentId)) {
                return false;
            }
        }
        {
            BigInteger lhsPaymentInstallmentQty;
            lhsPaymentInstallmentQty = this.getPaymentInstallmentQty();
            BigInteger rhsPaymentInstallmentQty;
            rhsPaymentInstallmentQty = that.getPaymentInstallmentQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentInstallmentQty", lhsPaymentInstallmentQty), LocatorUtils.property(thatLocator, "paymentInstallmentQty", rhsPaymentInstallmentQty), lhsPaymentInstallmentQty, rhsPaymentInstallmentQty)) {
                return false;
            }
        }
        {
            Double lhsPaymentInstallmentAmt;
            lhsPaymentInstallmentAmt = this.getPaymentInstallmentAmt();
            Double rhsPaymentInstallmentAmt;
            rhsPaymentInstallmentAmt = that.getPaymentInstallmentAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentInstallmentAmt", lhsPaymentInstallmentAmt), LocatorUtils.property(thatLocator, "paymentInstallmentAmt", rhsPaymentInstallmentAmt), lhsPaymentInstallmentAmt, rhsPaymentInstallmentAmt)) {
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
