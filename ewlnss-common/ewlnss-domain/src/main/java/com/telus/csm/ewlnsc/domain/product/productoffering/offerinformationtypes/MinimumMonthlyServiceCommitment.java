
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for MinimumMonthlyServiceCommitment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MinimumMonthlyServiceCommitment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minimumCommitmentTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="minimumCommitmentAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinimumMonthlyServiceCommitment", propOrder = {
    "minimumCommitmentTypeCode",
    "minimumCommitmentAmt"
})
public class MinimumMonthlyServiceCommitment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String minimumCommitmentTypeCode;
    protected Double minimumCommitmentAmt;

    /**
     * Gets the value of the minimumCommitmentTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinimumCommitmentTypeCode() {
        return minimumCommitmentTypeCode;
    }

    /**
     * Sets the value of the minimumCommitmentTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinimumCommitmentTypeCode(String value) {
        this.minimumCommitmentTypeCode = value;
    }

    /**
     * Gets the value of the minimumCommitmentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getMinimumCommitmentAmt() {
        return minimumCommitmentAmt;
    }

    /**
     * Sets the value of the minimumCommitmentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setMinimumCommitmentAmt(Double value) {
        this.minimumCommitmentAmt = value;
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
            String theMinimumCommitmentTypeCode;
            theMinimumCommitmentTypeCode = this.getMinimumCommitmentTypeCode();
            strategy.appendField(locator, this, "minimumCommitmentTypeCode", buffer, theMinimumCommitmentTypeCode);
        }
        {
            Double theMinimumCommitmentAmt;
            theMinimumCommitmentAmt = this.getMinimumCommitmentAmt();
            strategy.appendField(locator, this, "minimumCommitmentAmt", buffer, theMinimumCommitmentAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MinimumMonthlyServiceCommitment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final MinimumMonthlyServiceCommitment that = ((MinimumMonthlyServiceCommitment) object);
        {
            String lhsMinimumCommitmentTypeCode;
            lhsMinimumCommitmentTypeCode = this.getMinimumCommitmentTypeCode();
            String rhsMinimumCommitmentTypeCode;
            rhsMinimumCommitmentTypeCode = that.getMinimumCommitmentTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumCommitmentTypeCode", lhsMinimumCommitmentTypeCode), LocatorUtils.property(thatLocator, "minimumCommitmentTypeCode", rhsMinimumCommitmentTypeCode), lhsMinimumCommitmentTypeCode, rhsMinimumCommitmentTypeCode)) {
                return false;
            }
        }
        {
            Double lhsMinimumCommitmentAmt;
            lhsMinimumCommitmentAmt = this.getMinimumCommitmentAmt();
            Double rhsMinimumCommitmentAmt;
            rhsMinimumCommitmentAmt = that.getMinimumCommitmentAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumCommitmentAmt", lhsMinimumCommitmentAmt), LocatorUtils.property(thatLocator, "minimumCommitmentAmt", rhsMinimumCommitmentAmt), lhsMinimumCommitmentAmt, rhsMinimumCommitmentAmt)) {
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
