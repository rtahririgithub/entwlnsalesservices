
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for Usage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Usage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="unitOfMeasure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="usageRegionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usageCategoryCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Usage", propOrder = {
    "amount",
    "unitOfMeasure",
    "usageRegionCd",
    "usageCategoryCd"
})
@XmlSeeAlso({
    TopUsage.class
})
public class Usage
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double amount;
    @XmlElement(required = true)
    protected String unitOfMeasure;
    protected String usageRegionCd;
    protected String usageCategoryCd;

    /**
     * Gets the value of the amount property.
     * 
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Sets the value of the amount property.
     * 
     */
    public void setAmount(double value) {
        this.amount = value;
    }

    /**
     * Gets the value of the unitOfMeasure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitOfMeasure() {
        return unitOfMeasure;
    }

    /**
     * Sets the value of the unitOfMeasure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitOfMeasure(String value) {
        this.unitOfMeasure = value;
    }

    /**
     * Gets the value of the usageRegionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageRegionCd() {
        return usageRegionCd;
    }

    /**
     * Sets the value of the usageRegionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageRegionCd(String value) {
        this.usageRegionCd = value;
    }

    /**
     * Gets the value of the usageCategoryCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsageCategoryCd() {
        return usageCategoryCd;
    }

    /**
     * Sets the value of the usageCategoryCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsageCategoryCd(String value) {
        this.usageCategoryCd = value;
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
            double theAmount;
            theAmount = (true?this.getAmount(): 0.0D);
            strategy.appendField(locator, this, "amount", buffer, theAmount);
        }
        {
            String theUnitOfMeasure;
            theUnitOfMeasure = this.getUnitOfMeasure();
            strategy.appendField(locator, this, "unitOfMeasure", buffer, theUnitOfMeasure);
        }
        {
            String theUsageRegionCd;
            theUsageRegionCd = this.getUsageRegionCd();
            strategy.appendField(locator, this, "usageRegionCd", buffer, theUsageRegionCd);
        }
        {
            String theUsageCategoryCd;
            theUsageCategoryCd = this.getUsageCategoryCd();
            strategy.appendField(locator, this, "usageCategoryCd", buffer, theUsageCategoryCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Usage)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Usage that = ((Usage) object);
        {
            double lhsAmount;
            lhsAmount = (true?this.getAmount(): 0.0D);
            double rhsAmount;
            rhsAmount = (true?that.getAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "amount", lhsAmount), LocatorUtils.property(thatLocator, "amount", rhsAmount), lhsAmount, rhsAmount)) {
                return false;
            }
        }
        {
            String lhsUnitOfMeasure;
            lhsUnitOfMeasure = this.getUnitOfMeasure();
            String rhsUnitOfMeasure;
            rhsUnitOfMeasure = that.getUnitOfMeasure();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "unitOfMeasure", lhsUnitOfMeasure), LocatorUtils.property(thatLocator, "unitOfMeasure", rhsUnitOfMeasure), lhsUnitOfMeasure, rhsUnitOfMeasure)) {
                return false;
            }
        }
        {
            String lhsUsageRegionCd;
            lhsUsageRegionCd = this.getUsageRegionCd();
            String rhsUsageRegionCd;
            rhsUsageRegionCd = that.getUsageRegionCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usageRegionCd", lhsUsageRegionCd), LocatorUtils.property(thatLocator, "usageRegionCd", rhsUsageRegionCd), lhsUsageRegionCd, rhsUsageRegionCd)) {
                return false;
            }
        }
        {
            String lhsUsageCategoryCd;
            lhsUsageCategoryCd = this.getUsageCategoryCd();
            String rhsUsageCategoryCd;
            rhsUsageCategoryCd = that.getUsageCategoryCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usageCategoryCd", lhsUsageCategoryCd), LocatorUtils.property(thatLocator, "usageCategoryCd", rhsUsageCategoryCd), lhsUsageCategoryCd, rhsUsageCategoryCd)) {
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
