
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for QuantityAllowed complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QuantityAllowed">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minNum" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="maxNum" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QuantityAllowed", propOrder = {
    "minNum",
    "maxNum"
})
public class QuantityAllowed
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected BigInteger minNum;
    @XmlElement(required = true)
    protected BigInteger maxNum;

    /**
     * Gets the value of the minNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinNum() {
        return minNum;
    }

    /**
     * Sets the value of the minNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinNum(BigInteger value) {
        this.minNum = value;
    }

    /**
     * Gets the value of the maxNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxNum() {
        return maxNum;
    }

    /**
     * Sets the value of the maxNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxNum(BigInteger value) {
        this.maxNum = value;
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
            BigInteger theMinNum;
            theMinNum = this.getMinNum();
            strategy.appendField(locator, this, "minNum", buffer, theMinNum);
        }
        {
            BigInteger theMaxNum;
            theMaxNum = this.getMaxNum();
            strategy.appendField(locator, this, "maxNum", buffer, theMaxNum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof QuantityAllowed)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final QuantityAllowed that = ((QuantityAllowed) object);
        {
            BigInteger lhsMinNum;
            lhsMinNum = this.getMinNum();
            BigInteger rhsMinNum;
            rhsMinNum = that.getMinNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minNum", lhsMinNum), LocatorUtils.property(thatLocator, "minNum", rhsMinNum), lhsMinNum, rhsMinNum)) {
                return false;
            }
        }
        {
            BigInteger lhsMaxNum;
            lhsMaxNum = this.getMaxNum();
            BigInteger rhsMaxNum;
            rhsMaxNum = that.getMaxNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxNum", lhsMaxNum), LocatorUtils.property(thatLocator, "maxNum", rhsMaxNum), lhsMaxNum, rhsMaxNum)) {
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
