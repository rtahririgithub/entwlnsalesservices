
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * To define corporate account summary
 * 
 * <p>Java class for CorporateAllocation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorporateAllocation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="currentAllocationAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *         &lt;element name="totalAllocationAmount" type="{http://www.w3.org/2001/XMLSchema}decimal"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorporateAllocation", propOrder = {
    "currentAllocationAmount",
    "totalAllocationAmount"
})
public class CorporateAllocation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected BigDecimal currentAllocationAmount;
    @XmlElement(required = true)
    protected BigDecimal totalAllocationAmount;

    /**
     * Gets the value of the currentAllocationAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCurrentAllocationAmount() {
        return currentAllocationAmount;
    }

    /**
     * Sets the value of the currentAllocationAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCurrentAllocationAmount(BigDecimal value) {
        this.currentAllocationAmount = value;
    }

    /**
     * Gets the value of the totalAllocationAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAllocationAmount() {
        return totalAllocationAmount;
    }

    /**
     * Sets the value of the totalAllocationAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAllocationAmount(BigDecimal value) {
        this.totalAllocationAmount = value;
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
            BigDecimal theCurrentAllocationAmount;
            theCurrentAllocationAmount = this.getCurrentAllocationAmount();
            strategy.appendField(locator, this, "currentAllocationAmount", buffer, theCurrentAllocationAmount);
        }
        {
            BigDecimal theTotalAllocationAmount;
            theTotalAllocationAmount = this.getTotalAllocationAmount();
            strategy.appendField(locator, this, "totalAllocationAmount", buffer, theTotalAllocationAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CorporateAllocation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CorporateAllocation that = ((CorporateAllocation) object);
        {
            BigDecimal lhsCurrentAllocationAmount;
            lhsCurrentAllocationAmount = this.getCurrentAllocationAmount();
            BigDecimal rhsCurrentAllocationAmount;
            rhsCurrentAllocationAmount = that.getCurrentAllocationAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "currentAllocationAmount", lhsCurrentAllocationAmount), LocatorUtils.property(thatLocator, "currentAllocationAmount", rhsCurrentAllocationAmount), lhsCurrentAllocationAmount, rhsCurrentAllocationAmount)) {
                return false;
            }
        }
        {
            BigDecimal lhsTotalAllocationAmount;
            lhsTotalAllocationAmount = this.getTotalAllocationAmount();
            BigDecimal rhsTotalAllocationAmount;
            rhsTotalAllocationAmount = that.getTotalAllocationAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalAllocationAmount", lhsTotalAllocationAmount), LocatorUtils.property(thatLocator, "totalAllocationAmount", rhsTotalAllocationAmount), lhsTotalAllocationAmount, rhsTotalAllocationAmount)) {
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
