
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
 * <p>Java class for ServiceSupportFeeItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceSupportFeeItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceSupportFeeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceSupportFeeTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="creditReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceSupportFeeItem", propOrder = {
    "serviceSupportFeeCode",
    "serviceSupportFeeTypeCode",
    "creditReasonCode"
})
public class ServiceSupportFeeItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceSupportFeeCode;
    @XmlElement(required = true)
    protected String serviceSupportFeeTypeCode;
    protected String creditReasonCode;

    /**
     * Gets the value of the serviceSupportFeeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceSupportFeeCode() {
        return serviceSupportFeeCode;
    }

    /**
     * Sets the value of the serviceSupportFeeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceSupportFeeCode(String value) {
        this.serviceSupportFeeCode = value;
    }

    /**
     * Gets the value of the serviceSupportFeeTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceSupportFeeTypeCode() {
        return serviceSupportFeeTypeCode;
    }

    /**
     * Sets the value of the serviceSupportFeeTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceSupportFeeTypeCode(String value) {
        this.serviceSupportFeeTypeCode = value;
    }

    /**
     * Gets the value of the creditReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditReasonCode() {
        return creditReasonCode;
    }

    /**
     * Sets the value of the creditReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditReasonCode(String value) {
        this.creditReasonCode = value;
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
            String theServiceSupportFeeCode;
            theServiceSupportFeeCode = this.getServiceSupportFeeCode();
            strategy.appendField(locator, this, "serviceSupportFeeCode", buffer, theServiceSupportFeeCode);
        }
        {
            String theServiceSupportFeeTypeCode;
            theServiceSupportFeeTypeCode = this.getServiceSupportFeeTypeCode();
            strategy.appendField(locator, this, "serviceSupportFeeTypeCode", buffer, theServiceSupportFeeTypeCode);
        }
        {
            String theCreditReasonCode;
            theCreditReasonCode = this.getCreditReasonCode();
            strategy.appendField(locator, this, "creditReasonCode", buffer, theCreditReasonCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceSupportFeeItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceSupportFeeItem that = ((ServiceSupportFeeItem) object);
        {
            String lhsServiceSupportFeeCode;
            lhsServiceSupportFeeCode = this.getServiceSupportFeeCode();
            String rhsServiceSupportFeeCode;
            rhsServiceSupportFeeCode = that.getServiceSupportFeeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceSupportFeeCode", lhsServiceSupportFeeCode), LocatorUtils.property(thatLocator, "serviceSupportFeeCode", rhsServiceSupportFeeCode), lhsServiceSupportFeeCode, rhsServiceSupportFeeCode)) {
                return false;
            }
        }
        {
            String lhsServiceSupportFeeTypeCode;
            lhsServiceSupportFeeTypeCode = this.getServiceSupportFeeTypeCode();
            String rhsServiceSupportFeeTypeCode;
            rhsServiceSupportFeeTypeCode = that.getServiceSupportFeeTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceSupportFeeTypeCode", lhsServiceSupportFeeTypeCode), LocatorUtils.property(thatLocator, "serviceSupportFeeTypeCode", rhsServiceSupportFeeTypeCode), lhsServiceSupportFeeTypeCode, rhsServiceSupportFeeTypeCode)) {
                return false;
            }
        }
        {
            String lhsCreditReasonCode;
            lhsCreditReasonCode = this.getCreditReasonCode();
            String rhsCreditReasonCode;
            rhsCreditReasonCode = that.getCreditReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditReasonCode", lhsCreditReasonCode), LocatorUtils.property(thatLocator, "creditReasonCode", rhsCreditReasonCode), lhsCreditReasonCode, rhsCreditReasonCode)) {
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
