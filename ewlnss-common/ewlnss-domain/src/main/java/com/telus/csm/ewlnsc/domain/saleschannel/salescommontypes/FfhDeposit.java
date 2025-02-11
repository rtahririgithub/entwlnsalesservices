
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
 * <p>Java class for FfhDeposit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FfhDeposit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depositTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositInvoiceNumber" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="depositAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FfhDeposit", propOrder = {
    "depositTypeCd",
    "depositInvoiceNumber",
    "depositAmt"
})
public class FfhDeposit
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String depositTypeCd;
    protected Long depositInvoiceNumber;
    protected Double depositAmt;

    /**
     * Gets the value of the depositTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositTypeCd() {
        return depositTypeCd;
    }

    /**
     * Sets the value of the depositTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositTypeCd(String value) {
        this.depositTypeCd = value;
    }

    /**
     * Gets the value of the depositInvoiceNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getDepositInvoiceNumber() {
        return depositInvoiceNumber;
    }

    /**
     * Sets the value of the depositInvoiceNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setDepositInvoiceNumber(Long value) {
        this.depositInvoiceNumber = value;
    }

    /**
     * Gets the value of the depositAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDepositAmt() {
        return depositAmt;
    }

    /**
     * Sets the value of the depositAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDepositAmt(Double value) {
        this.depositAmt = value;
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
            String theDepositTypeCd;
            theDepositTypeCd = this.getDepositTypeCd();
            strategy.appendField(locator, this, "depositTypeCd", buffer, theDepositTypeCd);
        }
        {
            Long theDepositInvoiceNumber;
            theDepositInvoiceNumber = this.getDepositInvoiceNumber();
            strategy.appendField(locator, this, "depositInvoiceNumber", buffer, theDepositInvoiceNumber);
        }
        {
            Double theDepositAmt;
            theDepositAmt = this.getDepositAmt();
            strategy.appendField(locator, this, "depositAmt", buffer, theDepositAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FfhDeposit)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FfhDeposit that = ((FfhDeposit) object);
        {
            String lhsDepositTypeCd;
            lhsDepositTypeCd = this.getDepositTypeCd();
            String rhsDepositTypeCd;
            rhsDepositTypeCd = that.getDepositTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositTypeCd", lhsDepositTypeCd), LocatorUtils.property(thatLocator, "depositTypeCd", rhsDepositTypeCd), lhsDepositTypeCd, rhsDepositTypeCd)) {
                return false;
            }
        }
        {
            Long lhsDepositInvoiceNumber;
            lhsDepositInvoiceNumber = this.getDepositInvoiceNumber();
            Long rhsDepositInvoiceNumber;
            rhsDepositInvoiceNumber = that.getDepositInvoiceNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositInvoiceNumber", lhsDepositInvoiceNumber), LocatorUtils.property(thatLocator, "depositInvoiceNumber", rhsDepositInvoiceNumber), lhsDepositInvoiceNumber, rhsDepositInvoiceNumber)) {
                return false;
            }
        }
        {
            Double lhsDepositAmt;
            lhsDepositAmt = this.getDepositAmt();
            Double rhsDepositAmt;
            rhsDepositAmt = that.getDepositAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositAmt", lhsDepositAmt), LocatorUtils.property(thatLocator, "depositAmt", rhsDepositAmt), lhsDepositAmt, rhsDepositAmt)) {
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
