
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
 * 
 *             Gift Discount payment plan defines the details of the gift discount given to the customer,
 *             and the potential payment details of the unsubsidised portion of the gift's value that the customer is liable for.
 *             A gift discount is given as a SUBSIDY on the partial or full value of the gift.
 *             Unsubsidized portion of the gift value can either be financed over a defined number of installments (term), or requested to be paid immediately.
 *           
 * 
 * <p>Java class for GiftDiscountPaymentPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="GiftDiscountPaymentPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="paymentTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="paymentAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="paymentInstallmentCnt" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="immediatePaymentInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "GiftDiscountPaymentPlan", propOrder = {
    "paymentTypeCd",
    "paymentAmt",
    "paymentInstallmentCnt",
    "immediatePaymentInd"
})
public class GiftDiscountPaymentPlan
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String paymentTypeCd;
    protected double paymentAmt;
    protected BigInteger paymentInstallmentCnt;
    protected Boolean immediatePaymentInd;

    /**
     * Gets the value of the paymentTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentTypeCd() {
        return paymentTypeCd;
    }

    /**
     * Sets the value of the paymentTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentTypeCd(String value) {
        this.paymentTypeCd = value;
    }

    /**
     * Gets the value of the paymentAmt property.
     * 
     */
    public double getPaymentAmt() {
        return paymentAmt;
    }

    /**
     * Sets the value of the paymentAmt property.
     * 
     */
    public void setPaymentAmt(double value) {
        this.paymentAmt = value;
    }

    /**
     * Gets the value of the paymentInstallmentCnt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPaymentInstallmentCnt() {
        return paymentInstallmentCnt;
    }

    /**
     * Sets the value of the paymentInstallmentCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPaymentInstallmentCnt(BigInteger value) {
        this.paymentInstallmentCnt = value;
    }

    /**
     * Gets the value of the immediatePaymentInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isImmediatePaymentInd() {
        return immediatePaymentInd;
    }

    /**
     * Sets the value of the immediatePaymentInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setImmediatePaymentInd(Boolean value) {
        this.immediatePaymentInd = value;
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
            String thePaymentTypeCd;
            thePaymentTypeCd = this.getPaymentTypeCd();
            strategy.appendField(locator, this, "paymentTypeCd", buffer, thePaymentTypeCd);
        }
        {
            double thePaymentAmt;
            thePaymentAmt = (true?this.getPaymentAmt(): 0.0D);
            strategy.appendField(locator, this, "paymentAmt", buffer, thePaymentAmt);
        }
        {
            BigInteger thePaymentInstallmentCnt;
            thePaymentInstallmentCnt = this.getPaymentInstallmentCnt();
            strategy.appendField(locator, this, "paymentInstallmentCnt", buffer, thePaymentInstallmentCnt);
        }
        {
            Boolean theImmediatePaymentInd;
            theImmediatePaymentInd = this.isImmediatePaymentInd();
            strategy.appendField(locator, this, "immediatePaymentInd", buffer, theImmediatePaymentInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof GiftDiscountPaymentPlan)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final GiftDiscountPaymentPlan that = ((GiftDiscountPaymentPlan) object);
        {
            String lhsPaymentTypeCd;
            lhsPaymentTypeCd = this.getPaymentTypeCd();
            String rhsPaymentTypeCd;
            rhsPaymentTypeCd = that.getPaymentTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentTypeCd", lhsPaymentTypeCd), LocatorUtils.property(thatLocator, "paymentTypeCd", rhsPaymentTypeCd), lhsPaymentTypeCd, rhsPaymentTypeCd)) {
                return false;
            }
        }
        {
            double lhsPaymentAmt;
            lhsPaymentAmt = (true?this.getPaymentAmt(): 0.0D);
            double rhsPaymentAmt;
            rhsPaymentAmt = (true?that.getPaymentAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentAmt", lhsPaymentAmt), LocatorUtils.property(thatLocator, "paymentAmt", rhsPaymentAmt), lhsPaymentAmt, rhsPaymentAmt)) {
                return false;
            }
        }
        {
            BigInteger lhsPaymentInstallmentCnt;
            lhsPaymentInstallmentCnt = this.getPaymentInstallmentCnt();
            BigInteger rhsPaymentInstallmentCnt;
            rhsPaymentInstallmentCnt = that.getPaymentInstallmentCnt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentInstallmentCnt", lhsPaymentInstallmentCnt), LocatorUtils.property(thatLocator, "paymentInstallmentCnt", rhsPaymentInstallmentCnt), lhsPaymentInstallmentCnt, rhsPaymentInstallmentCnt)) {
                return false;
            }
        }
        {
            Boolean lhsImmediatePaymentInd;
            lhsImmediatePaymentInd = this.isImmediatePaymentInd();
            Boolean rhsImmediatePaymentInd;
            rhsImmediatePaymentInd = that.isImmediatePaymentInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "immediatePaymentInd", lhsImmediatePaymentInd), LocatorUtils.property(thatLocator, "immediatePaymentInd", rhsImmediatePaymentInd), lhsImmediatePaymentInd, rhsImmediatePaymentInd)) {
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
