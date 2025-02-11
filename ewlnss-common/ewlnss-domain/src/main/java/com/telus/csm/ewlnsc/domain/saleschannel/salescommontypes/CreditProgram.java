
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for CreditProgram complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditProgram">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditLimitProgramInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noDeviceProgramInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="securityDepositProgramInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditProgram", propOrder = {
    "creditLimitProgramInd",
    "noDeviceProgramInd",
    "securityDepositProgramInd"
})
@XmlSeeAlso({
    CreditProgramDetail.class
})
public class CreditProgram
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Boolean creditLimitProgramInd;
    protected Boolean noDeviceProgramInd;
    protected Boolean securityDepositProgramInd;

    /**
     * Gets the value of the creditLimitProgramInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreditLimitProgramInd() {
        return creditLimitProgramInd;
    }

    /**
     * Sets the value of the creditLimitProgramInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreditLimitProgramInd(Boolean value) {
        this.creditLimitProgramInd = value;
    }

    /**
     * Gets the value of the noDeviceProgramInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoDeviceProgramInd() {
        return noDeviceProgramInd;
    }

    /**
     * Sets the value of the noDeviceProgramInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoDeviceProgramInd(Boolean value) {
        this.noDeviceProgramInd = value;
    }

    /**
     * Gets the value of the securityDepositProgramInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSecurityDepositProgramInd() {
        return securityDepositProgramInd;
    }

    /**
     * Sets the value of the securityDepositProgramInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSecurityDepositProgramInd(Boolean value) {
        this.securityDepositProgramInd = value;
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
            Boolean theCreditLimitProgramInd;
            theCreditLimitProgramInd = this.isCreditLimitProgramInd();
            strategy.appendField(locator, this, "creditLimitProgramInd", buffer, theCreditLimitProgramInd);
        }
        {
            Boolean theNoDeviceProgramInd;
            theNoDeviceProgramInd = this.isNoDeviceProgramInd();
            strategy.appendField(locator, this, "noDeviceProgramInd", buffer, theNoDeviceProgramInd);
        }
        {
            Boolean theSecurityDepositProgramInd;
            theSecurityDepositProgramInd = this.isSecurityDepositProgramInd();
            strategy.appendField(locator, this, "securityDepositProgramInd", buffer, theSecurityDepositProgramInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreditProgram)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CreditProgram that = ((CreditProgram) object);
        {
            Boolean lhsCreditLimitProgramInd;
            lhsCreditLimitProgramInd = this.isCreditLimitProgramInd();
            Boolean rhsCreditLimitProgramInd;
            rhsCreditLimitProgramInd = that.isCreditLimitProgramInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitProgramInd", lhsCreditLimitProgramInd), LocatorUtils.property(thatLocator, "creditLimitProgramInd", rhsCreditLimitProgramInd), lhsCreditLimitProgramInd, rhsCreditLimitProgramInd)) {
                return false;
            }
        }
        {
            Boolean lhsNoDeviceProgramInd;
            lhsNoDeviceProgramInd = this.isNoDeviceProgramInd();
            Boolean rhsNoDeviceProgramInd;
            rhsNoDeviceProgramInd = that.isNoDeviceProgramInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "noDeviceProgramInd", lhsNoDeviceProgramInd), LocatorUtils.property(thatLocator, "noDeviceProgramInd", rhsNoDeviceProgramInd), lhsNoDeviceProgramInd, rhsNoDeviceProgramInd)) {
                return false;
            }
        }
        {
            Boolean lhsSecurityDepositProgramInd;
            lhsSecurityDepositProgramInd = this.isSecurityDepositProgramInd();
            Boolean rhsSecurityDepositProgramInd;
            rhsSecurityDepositProgramInd = that.isSecurityDepositProgramInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "securityDepositProgramInd", lhsSecurityDepositProgramInd), LocatorUtils.property(thatLocator, "securityDepositProgramInd", rhsSecurityDepositProgramInd), lhsSecurityDepositProgramInd, rhsSecurityDepositProgramInd)) {
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
