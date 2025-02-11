
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
 * <p>Java class for CustomerPayItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerPayItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="payItemTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="payItemAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerPayItem", propOrder = {
    "payItemTypeCd",
    "payItemAmount"
})
public class CustomerPayItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String payItemTypeCd;
    protected double payItemAmount;

    /**
     * Gets the value of the payItemTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayItemTypeCd() {
        return payItemTypeCd;
    }

    /**
     * Sets the value of the payItemTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayItemTypeCd(String value) {
        this.payItemTypeCd = value;
    }

    /**
     * Gets the value of the payItemAmount property.
     * 
     */
    public double getPayItemAmount() {
        return payItemAmount;
    }

    /**
     * Sets the value of the payItemAmount property.
     * 
     */
    public void setPayItemAmount(double value) {
        this.payItemAmount = value;
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
            String thePayItemTypeCd;
            thePayItemTypeCd = this.getPayItemTypeCd();
            strategy.appendField(locator, this, "payItemTypeCd", buffer, thePayItemTypeCd);
        }
        {
            double thePayItemAmount;
            thePayItemAmount = (true?this.getPayItemAmount(): 0.0D);
            strategy.appendField(locator, this, "payItemAmount", buffer, thePayItemAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CustomerPayItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CustomerPayItem that = ((CustomerPayItem) object);
        {
            String lhsPayItemTypeCd;
            lhsPayItemTypeCd = this.getPayItemTypeCd();
            String rhsPayItemTypeCd;
            rhsPayItemTypeCd = that.getPayItemTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payItemTypeCd", lhsPayItemTypeCd), LocatorUtils.property(thatLocator, "payItemTypeCd", rhsPayItemTypeCd), lhsPayItemTypeCd, rhsPayItemTypeCd)) {
                return false;
            }
        }
        {
            double lhsPayItemAmount;
            lhsPayItemAmount = (true?this.getPayItemAmount(): 0.0D);
            double rhsPayItemAmount;
            rhsPayItemAmount = (true?that.getPayItemAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payItemAmount", lhsPayItemAmount), LocatorUtils.property(thatLocator, "payItemAmount", rhsPayItemAmount), lhsPayItemAmount, rhsPayItemAmount)) {
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
