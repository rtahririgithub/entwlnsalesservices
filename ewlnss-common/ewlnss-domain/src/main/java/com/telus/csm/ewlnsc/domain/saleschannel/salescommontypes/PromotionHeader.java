
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
 * <p>Java class for PromotionHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PromotionHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="selectedCoupon" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CouponIdentifier" minOccurs="0"/>
 *         &lt;element name="validationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="promotionIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PromotionIdentifier"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PromotionHeader", propOrder = {
    "selectedCoupon",
    "validationCode",
    "promotionIdentifier"
})
public class PromotionHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected CouponIdentifier selectedCoupon;
    protected String validationCode;
    @XmlElement(required = true)
    protected PromotionIdentifier promotionIdentifier;

    /**
     * Gets the value of the selectedCoupon property.
     * 
     * @return
     *     possible object is
     *     {@link CouponIdentifier }
     *     
     */
    public CouponIdentifier getSelectedCoupon() {
        return selectedCoupon;
    }

    /**
     * Sets the value of the selectedCoupon property.
     * 
     * @param value
     *     allowed object is
     *     {@link CouponIdentifier }
     *     
     */
    public void setSelectedCoupon(CouponIdentifier value) {
        this.selectedCoupon = value;
    }

    /**
     * Gets the value of the validationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValidationCode() {
        return validationCode;
    }

    /**
     * Sets the value of the validationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValidationCode(String value) {
        this.validationCode = value;
    }

    /**
     * Gets the value of the promotionIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link PromotionIdentifier }
     *     
     */
    public PromotionIdentifier getPromotionIdentifier() {
        return promotionIdentifier;
    }

    /**
     * Sets the value of the promotionIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link PromotionIdentifier }
     *     
     */
    public void setPromotionIdentifier(PromotionIdentifier value) {
        this.promotionIdentifier = value;
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
            CouponIdentifier theSelectedCoupon;
            theSelectedCoupon = this.getSelectedCoupon();
            strategy.appendField(locator, this, "selectedCoupon", buffer, theSelectedCoupon);
        }
        {
            String theValidationCode;
            theValidationCode = this.getValidationCode();
            strategy.appendField(locator, this, "validationCode", buffer, theValidationCode);
        }
        {
            PromotionIdentifier thePromotionIdentifier;
            thePromotionIdentifier = this.getPromotionIdentifier();
            strategy.appendField(locator, this, "promotionIdentifier", buffer, thePromotionIdentifier);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PromotionHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PromotionHeader that = ((PromotionHeader) object);
        {
            CouponIdentifier lhsSelectedCoupon;
            lhsSelectedCoupon = this.getSelectedCoupon();
            CouponIdentifier rhsSelectedCoupon;
            rhsSelectedCoupon = that.getSelectedCoupon();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedCoupon", lhsSelectedCoupon), LocatorUtils.property(thatLocator, "selectedCoupon", rhsSelectedCoupon), lhsSelectedCoupon, rhsSelectedCoupon)) {
                return false;
            }
        }
        {
            String lhsValidationCode;
            lhsValidationCode = this.getValidationCode();
            String rhsValidationCode;
            rhsValidationCode = that.getValidationCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validationCode", lhsValidationCode), LocatorUtils.property(thatLocator, "validationCode", rhsValidationCode), lhsValidationCode, rhsValidationCode)) {
                return false;
            }
        }
        {
            PromotionIdentifier lhsPromotionIdentifier;
            lhsPromotionIdentifier = this.getPromotionIdentifier();
            PromotionIdentifier rhsPromotionIdentifier;
            rhsPromotionIdentifier = that.getPromotionIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionIdentifier", lhsPromotionIdentifier), LocatorUtils.property(thatLocator, "promotionIdentifier", rhsPromotionIdentifier), lhsPromotionIdentifier, rhsPromotionIdentifier)) {
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
