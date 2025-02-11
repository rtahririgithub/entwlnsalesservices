
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
 * <p>Java class for ValidationParameterDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationParameterDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="featureCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mutuallyExclusiveIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationParameterDetail", propOrder = {
    "featureCode",
    "mutuallyExclusiveIndicator"
})
public class ValidationParameterDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String featureCode;
    protected boolean mutuallyExclusiveIndicator;

    /**
     * Gets the value of the featureCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeatureCode() {
        return featureCode;
    }

    /**
     * Sets the value of the featureCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeatureCode(String value) {
        this.featureCode = value;
    }

    /**
     * Gets the value of the mutuallyExclusiveIndicator property.
     * 
     */
    public boolean isMutuallyExclusiveIndicator() {
        return mutuallyExclusiveIndicator;
    }

    /**
     * Sets the value of the mutuallyExclusiveIndicator property.
     * 
     */
    public void setMutuallyExclusiveIndicator(boolean value) {
        this.mutuallyExclusiveIndicator = value;
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
            String theFeatureCode;
            theFeatureCode = this.getFeatureCode();
            strategy.appendField(locator, this, "featureCode", buffer, theFeatureCode);
        }
        {
            boolean theMutuallyExclusiveIndicator;
            theMutuallyExclusiveIndicator = (true?this.isMutuallyExclusiveIndicator():false);
            strategy.appendField(locator, this, "mutuallyExclusiveIndicator", buffer, theMutuallyExclusiveIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ValidationParameterDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ValidationParameterDetail that = ((ValidationParameterDetail) object);
        {
            String lhsFeatureCode;
            lhsFeatureCode = this.getFeatureCode();
            String rhsFeatureCode;
            rhsFeatureCode = that.getFeatureCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureCode", lhsFeatureCode), LocatorUtils.property(thatLocator, "featureCode", rhsFeatureCode), lhsFeatureCode, rhsFeatureCode)) {
                return false;
            }
        }
        {
            boolean lhsMutuallyExclusiveIndicator;
            lhsMutuallyExclusiveIndicator = (true?this.isMutuallyExclusiveIndicator():false);
            boolean rhsMutuallyExclusiveIndicator;
            rhsMutuallyExclusiveIndicator = (true?that.isMutuallyExclusiveIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mutuallyExclusiveIndicator", lhsMutuallyExclusiveIndicator), LocatorUtils.property(thatLocator, "mutuallyExclusiveIndicator", rhsMutuallyExclusiveIndicator), lhsMutuallyExclusiveIndicator, rhsMutuallyExclusiveIndicator)) {
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
