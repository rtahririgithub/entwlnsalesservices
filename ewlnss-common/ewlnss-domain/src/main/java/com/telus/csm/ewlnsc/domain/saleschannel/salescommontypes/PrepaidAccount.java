
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PrepaidAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PrepaidAccount">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Handset">
 *       &lt;sequence>
 *         &lt;element name="balance" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="inTrustBalance" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="inTrustBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *                   &lt;element name="inTrustBalanceExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PrepaidAccount", propOrder = {
    "balance",
    "inTrustBalance"
})
public class PrepaidAccount
    extends Handset
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Double balance;
    protected PrepaidAccount.InTrustBalance inTrustBalance;

    /**
     * Gets the value of the balance property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets the value of the balance property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBalance(Double value) {
        this.balance = value;
    }

    /**
     * Gets the value of the inTrustBalance property.
     * 
     * @return
     *     possible object is
     *     {@link PrepaidAccount.InTrustBalance }
     *     
     */
    public PrepaidAccount.InTrustBalance getInTrustBalance() {
        return inTrustBalance;
    }

    /**
     * Sets the value of the inTrustBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link PrepaidAccount.InTrustBalance }
     *     
     */
    public void setInTrustBalance(PrepaidAccount.InTrustBalance value) {
        this.inTrustBalance = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            Double theBalance;
            theBalance = this.getBalance();
            strategy.appendField(locator, this, "balance", buffer, theBalance);
        }
        {
            PrepaidAccount.InTrustBalance theInTrustBalance;
            theInTrustBalance = this.getInTrustBalance();
            strategy.appendField(locator, this, "inTrustBalance", buffer, theInTrustBalance);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PrepaidAccount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PrepaidAccount that = ((PrepaidAccount) object);
        {
            Double lhsBalance;
            lhsBalance = this.getBalance();
            Double rhsBalance;
            rhsBalance = that.getBalance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "balance", lhsBalance), LocatorUtils.property(thatLocator, "balance", rhsBalance), lhsBalance, rhsBalance)) {
                return false;
            }
        }
        {
            PrepaidAccount.InTrustBalance lhsInTrustBalance;
            lhsInTrustBalance = this.getInTrustBalance();
            PrepaidAccount.InTrustBalance rhsInTrustBalance;
            rhsInTrustBalance = that.getInTrustBalance();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inTrustBalance", lhsInTrustBalance), LocatorUtils.property(thatLocator, "inTrustBalance", rhsInTrustBalance), lhsInTrustBalance, rhsInTrustBalance)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="inTrustBalanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
     *         &lt;element name="inTrustBalanceExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "inTrustBalanceAmount",
        "inTrustBalanceExpiryDate"
    })
    public static class InTrustBalance
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected double inTrustBalanceAmount;
        @XmlElement(type = String.class)
        @XmlJavaTypeAdapter(DateAdapter.class)
        @XmlSchemaType(name = "date")
        protected Date inTrustBalanceExpiryDate;

        /**
         * Gets the value of the inTrustBalanceAmount property.
         * 
         */
        public double getInTrustBalanceAmount() {
            return inTrustBalanceAmount;
        }

        /**
         * Sets the value of the inTrustBalanceAmount property.
         * 
         */
        public void setInTrustBalanceAmount(double value) {
            this.inTrustBalanceAmount = value;
        }

        /**
         * Gets the value of the inTrustBalanceExpiryDate property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public Date getInTrustBalanceExpiryDate() {
            return inTrustBalanceExpiryDate;
        }

        /**
         * Sets the value of the inTrustBalanceExpiryDate property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInTrustBalanceExpiryDate(Date value) {
            this.inTrustBalanceExpiryDate = value;
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
                double theInTrustBalanceAmount;
                theInTrustBalanceAmount = (true?this.getInTrustBalanceAmount(): 0.0D);
                strategy.appendField(locator, this, "inTrustBalanceAmount", buffer, theInTrustBalanceAmount);
            }
            {
                Date theInTrustBalanceExpiryDate;
                theInTrustBalanceExpiryDate = this.getInTrustBalanceExpiryDate();
                strategy.appendField(locator, this, "inTrustBalanceExpiryDate", buffer, theInTrustBalanceExpiryDate);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof PrepaidAccount.InTrustBalance)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final PrepaidAccount.InTrustBalance that = ((PrepaidAccount.InTrustBalance) object);
            {
                double lhsInTrustBalanceAmount;
                lhsInTrustBalanceAmount = (true?this.getInTrustBalanceAmount(): 0.0D);
                double rhsInTrustBalanceAmount;
                rhsInTrustBalanceAmount = (true?that.getInTrustBalanceAmount(): 0.0D);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "inTrustBalanceAmount", lhsInTrustBalanceAmount), LocatorUtils.property(thatLocator, "inTrustBalanceAmount", rhsInTrustBalanceAmount), lhsInTrustBalanceAmount, rhsInTrustBalanceAmount)) {
                    return false;
                }
            }
            {
                Date lhsInTrustBalanceExpiryDate;
                lhsInTrustBalanceExpiryDate = this.getInTrustBalanceExpiryDate();
                Date rhsInTrustBalanceExpiryDate;
                rhsInTrustBalanceExpiryDate = that.getInTrustBalanceExpiryDate();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "inTrustBalanceExpiryDate", lhsInTrustBalanceExpiryDate), LocatorUtils.property(thatLocator, "inTrustBalanceExpiryDate", rhsInTrustBalanceExpiryDate), lhsInTrustBalanceExpiryDate, rhsInTrustBalanceExpiryDate)) {
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

}
