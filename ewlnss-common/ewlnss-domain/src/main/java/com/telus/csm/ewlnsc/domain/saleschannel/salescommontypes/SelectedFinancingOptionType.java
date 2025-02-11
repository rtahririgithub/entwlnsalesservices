
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
 * <p>Java class for SelectedFinancingOptionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SelectedFinancingOptionType">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}FinancingOptionsType">
 *       &lt;sequence>
 *         &lt;element name="payOnBillInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SelectedFinancingOptionType", propOrder = {
    "payOnBillInd"
})
public class SelectedFinancingOptionType
    extends FinancingOptionsType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Boolean payOnBillInd;

    /**
     * Gets the value of the payOnBillInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPayOnBillInd() {
        return payOnBillInd;
    }

    /**
     * Sets the value of the payOnBillInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPayOnBillInd(Boolean value) {
        this.payOnBillInd = value;
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
        super.appendFields(locator, buffer, strategy);
        {
            Boolean thePayOnBillInd;
            thePayOnBillInd = this.isPayOnBillInd();
            strategy.appendField(locator, this, "payOnBillInd", buffer, thePayOnBillInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SelectedFinancingOptionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final SelectedFinancingOptionType that = ((SelectedFinancingOptionType) object);
        {
            Boolean lhsPayOnBillInd;
            lhsPayOnBillInd = this.isPayOnBillInd();
            Boolean rhsPayOnBillInd;
            rhsPayOnBillInd = that.isPayOnBillInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "payOnBillInd", lhsPayOnBillInd), LocatorUtils.property(thatLocator, "payOnBillInd", rhsPayOnBillInd), lhsPayOnBillInd, rhsPayOnBillInd)) {
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
