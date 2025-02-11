
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * Wireless Equipment in a business account context.
 * 
 * <p>Java class for PurchasePrice complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PurchasePrice">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="devicePurchasePriceAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="devicePurchasePriceApplyOnBillIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PurchasePrice", propOrder = {
    "devicePurchasePriceAmt",
    "devicePurchasePriceApplyOnBillIndicator"
})
public class PurchasePrice
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double devicePurchasePriceAmt;
    protected boolean devicePurchasePriceApplyOnBillIndicator;

    /**
     * Gets the value of the devicePurchasePriceAmt property.
     * 
     */
    public double getDevicePurchasePriceAmt() {
        return devicePurchasePriceAmt;
    }

    /**
     * Sets the value of the devicePurchasePriceAmt property.
     * 
     */
    public void setDevicePurchasePriceAmt(double value) {
        this.devicePurchasePriceAmt = value;
    }

    /**
     * Gets the value of the devicePurchasePriceApplyOnBillIndicator property.
     * 
     */
    public boolean isDevicePurchasePriceApplyOnBillIndicator() {
        return devicePurchasePriceApplyOnBillIndicator;
    }

    /**
     * Sets the value of the devicePurchasePriceApplyOnBillIndicator property.
     * 
     */
    public void setDevicePurchasePriceApplyOnBillIndicator(boolean value) {
        this.devicePurchasePriceApplyOnBillIndicator = value;
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
            double theDevicePurchasePriceAmt;
            theDevicePurchasePriceAmt = (true?this.getDevicePurchasePriceAmt(): 0.0D);
            strategy.appendField(locator, this, "devicePurchasePriceAmt", buffer, theDevicePurchasePriceAmt);
        }
        {
            boolean theDevicePurchasePriceApplyOnBillIndicator;
            theDevicePurchasePriceApplyOnBillIndicator = (true?this.isDevicePurchasePriceApplyOnBillIndicator():false);
            strategy.appendField(locator, this, "devicePurchasePriceApplyOnBillIndicator", buffer, theDevicePurchasePriceApplyOnBillIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PurchasePrice)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PurchasePrice that = ((PurchasePrice) object);
        {
            double lhsDevicePurchasePriceAmt;
            lhsDevicePurchasePriceAmt = (true?this.getDevicePurchasePriceAmt(): 0.0D);
            double rhsDevicePurchasePriceAmt;
            rhsDevicePurchasePriceAmt = (true?that.getDevicePurchasePriceAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "devicePurchasePriceAmt", lhsDevicePurchasePriceAmt), LocatorUtils.property(thatLocator, "devicePurchasePriceAmt", rhsDevicePurchasePriceAmt), lhsDevicePurchasePriceAmt, rhsDevicePurchasePriceAmt)) {
                return false;
            }
        }
        {
            boolean lhsDevicePurchasePriceApplyOnBillIndicator;
            lhsDevicePurchasePriceApplyOnBillIndicator = (true?this.isDevicePurchasePriceApplyOnBillIndicator():false);
            boolean rhsDevicePurchasePriceApplyOnBillIndicator;
            rhsDevicePurchasePriceApplyOnBillIndicator = (true?that.isDevicePurchasePriceApplyOnBillIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "devicePurchasePriceApplyOnBillIndicator", lhsDevicePurchasePriceApplyOnBillIndicator), LocatorUtils.property(thatLocator, "devicePurchasePriceApplyOnBillIndicator", rhsDevicePurchasePriceApplyOnBillIndicator), lhsDevicePurchasePriceApplyOnBillIndicator, rhsDevicePurchasePriceApplyOnBillIndicator)) {
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
