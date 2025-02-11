
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
 * <p>Java class for RewardAccount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RewardAccount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="balanceAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="statusCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monthlyDrawdownAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RewardAccount", propOrder = {
    "typeCode",
    "balanceAmount",
    "statusCode",
    "monthlyDrawdownAmount"
})
public class RewardAccount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String typeCode;
    protected double balanceAmount;
    protected String statusCode;
    protected Double monthlyDrawdownAmount;

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the balanceAmount property.
     * 
     */
    public double getBalanceAmount() {
        return balanceAmount;
    }

    /**
     * Sets the value of the balanceAmount property.
     * 
     */
    public void setBalanceAmount(double value) {
        this.balanceAmount = value;
    }

    /**
     * Gets the value of the statusCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStatusCode() {
        return statusCode;
    }

    /**
     * Sets the value of the statusCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStatusCode(String value) {
        this.statusCode = value;
    }

    /**
     * Gets the value of the monthlyDrawdownAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMonthlyDrawdownAmount() {
        return monthlyDrawdownAmount;
    }

    /**
     * Sets the value of the monthlyDrawdownAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMonthlyDrawdownAmount(Double value) {
        this.monthlyDrawdownAmount = value;
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
            String theTypeCode;
            theTypeCode = this.getTypeCode();
            strategy.appendField(locator, this, "typeCode", buffer, theTypeCode);
        }
        {
            double theBalanceAmount;
            theBalanceAmount = (true?this.getBalanceAmount(): 0.0D);
            strategy.appendField(locator, this, "balanceAmount", buffer, theBalanceAmount);
        }
        {
            String theStatusCode;
            theStatusCode = this.getStatusCode();
            strategy.appendField(locator, this, "statusCode", buffer, theStatusCode);
        }
        {
            Double theMonthlyDrawdownAmount;
            theMonthlyDrawdownAmount = this.getMonthlyDrawdownAmount();
            strategy.appendField(locator, this, "monthlyDrawdownAmount", buffer, theMonthlyDrawdownAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RewardAccount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RewardAccount that = ((RewardAccount) object);
        {
            String lhsTypeCode;
            lhsTypeCode = this.getTypeCode();
            String rhsTypeCode;
            rhsTypeCode = that.getTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "typeCode", lhsTypeCode), LocatorUtils.property(thatLocator, "typeCode", rhsTypeCode), lhsTypeCode, rhsTypeCode)) {
                return false;
            }
        }
        {
            double lhsBalanceAmount;
            lhsBalanceAmount = (true?this.getBalanceAmount(): 0.0D);
            double rhsBalanceAmount;
            rhsBalanceAmount = (true?that.getBalanceAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "balanceAmount", lhsBalanceAmount), LocatorUtils.property(thatLocator, "balanceAmount", rhsBalanceAmount), lhsBalanceAmount, rhsBalanceAmount)) {
                return false;
            }
        }
        {
            String lhsStatusCode;
            lhsStatusCode = this.getStatusCode();
            String rhsStatusCode;
            rhsStatusCode = that.getStatusCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "statusCode", lhsStatusCode), LocatorUtils.property(thatLocator, "statusCode", rhsStatusCode), lhsStatusCode, rhsStatusCode)) {
                return false;
            }
        }
        {
            Double lhsMonthlyDrawdownAmount;
            lhsMonthlyDrawdownAmount = this.getMonthlyDrawdownAmount();
            Double rhsMonthlyDrawdownAmount;
            rhsMonthlyDrawdownAmount = that.getMonthlyDrawdownAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "monthlyDrawdownAmount", lhsMonthlyDrawdownAmount), LocatorUtils.property(thatLocator, "monthlyDrawdownAmount", rhsMonthlyDrawdownAmount), lhsMonthlyDrawdownAmount, rhsMonthlyDrawdownAmount)) {
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
