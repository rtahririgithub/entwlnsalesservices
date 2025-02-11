
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
 * <p>Java class for MinimumCommitment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MinimumCommitment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="minimumRatePlanValueAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="minimumFeaturesValueAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="minimumCombinedValueAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MinimumCommitment", propOrder = {
    "minimumRatePlanValueAmount",
    "minimumFeaturesValueAmount",
    "minimumCombinedValueAmount"
})
public class MinimumCommitment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double minimumRatePlanValueAmount;
    protected double minimumFeaturesValueAmount;
    protected double minimumCombinedValueAmount;

    /**
     * Gets the value of the minimumRatePlanValueAmount property.
     * 
     */
    public double getMinimumRatePlanValueAmount() {
        return minimumRatePlanValueAmount;
    }

    /**
     * Sets the value of the minimumRatePlanValueAmount property.
     * 
     */
    public void setMinimumRatePlanValueAmount(double value) {
        this.minimumRatePlanValueAmount = value;
    }

    /**
     * Gets the value of the minimumFeaturesValueAmount property.
     * 
     */
    public double getMinimumFeaturesValueAmount() {
        return minimumFeaturesValueAmount;
    }

    /**
     * Sets the value of the minimumFeaturesValueAmount property.
     * 
     */
    public void setMinimumFeaturesValueAmount(double value) {
        this.minimumFeaturesValueAmount = value;
    }

    /**
     * Gets the value of the minimumCombinedValueAmount property.
     * 
     */
    public double getMinimumCombinedValueAmount() {
        return minimumCombinedValueAmount;
    }

    /**
     * Sets the value of the minimumCombinedValueAmount property.
     * 
     */
    public void setMinimumCombinedValueAmount(double value) {
        this.minimumCombinedValueAmount = value;
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
            double theMinimumRatePlanValueAmount;
            theMinimumRatePlanValueAmount = (true?this.getMinimumRatePlanValueAmount(): 0.0D);
            strategy.appendField(locator, this, "minimumRatePlanValueAmount", buffer, theMinimumRatePlanValueAmount);
        }
        {
            double theMinimumFeaturesValueAmount;
            theMinimumFeaturesValueAmount = (true?this.getMinimumFeaturesValueAmount(): 0.0D);
            strategy.appendField(locator, this, "minimumFeaturesValueAmount", buffer, theMinimumFeaturesValueAmount);
        }
        {
            double theMinimumCombinedValueAmount;
            theMinimumCombinedValueAmount = (true?this.getMinimumCombinedValueAmount(): 0.0D);
            strategy.appendField(locator, this, "minimumCombinedValueAmount", buffer, theMinimumCombinedValueAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MinimumCommitment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final MinimumCommitment that = ((MinimumCommitment) object);
        {
            double lhsMinimumRatePlanValueAmount;
            lhsMinimumRatePlanValueAmount = (true?this.getMinimumRatePlanValueAmount(): 0.0D);
            double rhsMinimumRatePlanValueAmount;
            rhsMinimumRatePlanValueAmount = (true?that.getMinimumRatePlanValueAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumRatePlanValueAmount", lhsMinimumRatePlanValueAmount), LocatorUtils.property(thatLocator, "minimumRatePlanValueAmount", rhsMinimumRatePlanValueAmount), lhsMinimumRatePlanValueAmount, rhsMinimumRatePlanValueAmount)) {
                return false;
            }
        }
        {
            double lhsMinimumFeaturesValueAmount;
            lhsMinimumFeaturesValueAmount = (true?this.getMinimumFeaturesValueAmount(): 0.0D);
            double rhsMinimumFeaturesValueAmount;
            rhsMinimumFeaturesValueAmount = (true?that.getMinimumFeaturesValueAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumFeaturesValueAmount", lhsMinimumFeaturesValueAmount), LocatorUtils.property(thatLocator, "minimumFeaturesValueAmount", rhsMinimumFeaturesValueAmount), lhsMinimumFeaturesValueAmount, rhsMinimumFeaturesValueAmount)) {
                return false;
            }
        }
        {
            double lhsMinimumCombinedValueAmount;
            lhsMinimumCombinedValueAmount = (true?this.getMinimumCombinedValueAmount(): 0.0D);
            double rhsMinimumCombinedValueAmount;
            rhsMinimumCombinedValueAmount = (true?that.getMinimumCombinedValueAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumCombinedValueAmount", lhsMinimumCombinedValueAmount), LocatorUtils.property(thatLocator, "minimumCombinedValueAmount", rhsMinimumCombinedValueAmount), lhsMinimumCombinedValueAmount, rhsMinimumCombinedValueAmount)) {
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
