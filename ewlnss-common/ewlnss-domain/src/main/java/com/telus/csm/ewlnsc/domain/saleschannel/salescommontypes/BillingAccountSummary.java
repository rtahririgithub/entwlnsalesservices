
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.customer.customer.customermanagementcommontypes_v3.Address;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for BillingAccountSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillingAccountSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="billingAddress" type="{http://xmlschema.tmi.telus.com/xsd/Customer/Customer/CustomerManagementCommonTypes_v3}Address" minOccurs="0"/>
 *         &lt;element name="billingAccountNumber" type="{http://xmlschema.tmi.telus.com/xsd/Customer/BaseTypes/CustomerCommon_v3}billingAccountNumberType" minOccurs="0"/>
 *         &lt;element name="balanceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillingAccountSummary", propOrder = {
    "billingAddress",
    "billingAccountNumber",
    "balanceAmt"
})
public class BillingAccountSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Address billingAddress;
    protected String billingAccountNumber;
    protected Double balanceAmt;

    /**
     * Gets the value of the billingAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getBillingAddress() {
        return billingAddress;
    }

    /**
     * Sets the value of the billingAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setBillingAddress(Address value) {
        this.billingAddress = value;
    }

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the balanceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBalanceAmt() {
        return balanceAmt;
    }

    /**
     * Sets the value of the balanceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBalanceAmt(Double value) {
        this.balanceAmt = value;
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
            Address theBillingAddress;
            theBillingAddress = this.getBillingAddress();
            strategy.appendField(locator, this, "billingAddress", buffer, theBillingAddress);
        }
        {
            String theBillingAccountNumber;
            theBillingAccountNumber = this.getBillingAccountNumber();
            strategy.appendField(locator, this, "billingAccountNumber", buffer, theBillingAccountNumber);
        }
        {
            Double theBalanceAmt;
            theBalanceAmt = this.getBalanceAmt();
            strategy.appendField(locator, this, "balanceAmt", buffer, theBalanceAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BillingAccountSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BillingAccountSummary that = ((BillingAccountSummary) object);
        {
            Address lhsBillingAddress;
            lhsBillingAddress = this.getBillingAddress();
            Address rhsBillingAddress;
            rhsBillingAddress = that.getBillingAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAddress", lhsBillingAddress), LocatorUtils.property(thatLocator, "billingAddress", rhsBillingAddress), lhsBillingAddress, rhsBillingAddress)) {
                return false;
            }
        }
        {
            String lhsBillingAccountNumber;
            lhsBillingAccountNumber = this.getBillingAccountNumber();
            String rhsBillingAccountNumber;
            rhsBillingAccountNumber = that.getBillingAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNumber", lhsBillingAccountNumber), LocatorUtils.property(thatLocator, "billingAccountNumber", rhsBillingAccountNumber), lhsBillingAccountNumber, rhsBillingAccountNumber)) {
                return false;
            }
        }
        {
            Double lhsBalanceAmt;
            lhsBalanceAmt = this.getBalanceAmt();
            Double rhsBalanceAmt;
            rhsBalanceAmt = that.getBalanceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "balanceAmt", lhsBalanceAmt), LocatorUtils.property(thatLocator, "balanceAmt", rhsBalanceAmt), lhsBalanceAmt, rhsBalanceAmt)) {
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
