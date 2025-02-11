
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
 * Indicators used to specify tax exemption
 * 
 * <p>Java class for TaxExemptionIndicatorsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TaxExemptionIndicatorsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="gstExemptedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="pstExemptedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="hstExemptedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TaxExemptionIndicatorsType", propOrder = {
    "gstExemptedInd",
    "pstExemptedInd",
    "hstExemptedInd"
})
public class TaxExemptionIndicatorsType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected boolean gstExemptedInd;
    protected boolean pstExemptedInd;
    protected boolean hstExemptedInd;

    /**
     * Gets the value of the gstExemptedInd property.
     * 
     */
    public boolean isGstExemptedInd() {
        return gstExemptedInd;
    }

    /**
     * Sets the value of the gstExemptedInd property.
     * 
     */
    public void setGstExemptedInd(boolean value) {
        this.gstExemptedInd = value;
    }

    /**
     * Gets the value of the pstExemptedInd property.
     * 
     */
    public boolean isPstExemptedInd() {
        return pstExemptedInd;
    }

    /**
     * Sets the value of the pstExemptedInd property.
     * 
     */
    public void setPstExemptedInd(boolean value) {
        this.pstExemptedInd = value;
    }

    /**
     * Gets the value of the hstExemptedInd property.
     * 
     */
    public boolean isHstExemptedInd() {
        return hstExemptedInd;
    }

    /**
     * Sets the value of the hstExemptedInd property.
     * 
     */
    public void setHstExemptedInd(boolean value) {
        this.hstExemptedInd = value;
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
            boolean theGstExemptedInd;
            theGstExemptedInd = (true?this.isGstExemptedInd():false);
            strategy.appendField(locator, this, "gstExemptedInd", buffer, theGstExemptedInd);
        }
        {
            boolean thePstExemptedInd;
            thePstExemptedInd = (true?this.isPstExemptedInd():false);
            strategy.appendField(locator, this, "pstExemptedInd", buffer, thePstExemptedInd);
        }
        {
            boolean theHstExemptedInd;
            theHstExemptedInd = (true?this.isHstExemptedInd():false);
            strategy.appendField(locator, this, "hstExemptedInd", buffer, theHstExemptedInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TaxExemptionIndicatorsType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TaxExemptionIndicatorsType that = ((TaxExemptionIndicatorsType) object);
        {
            boolean lhsGstExemptedInd;
            lhsGstExemptedInd = (true?this.isGstExemptedInd():false);
            boolean rhsGstExemptedInd;
            rhsGstExemptedInd = (true?that.isGstExemptedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gstExemptedInd", lhsGstExemptedInd), LocatorUtils.property(thatLocator, "gstExemptedInd", rhsGstExemptedInd), lhsGstExemptedInd, rhsGstExemptedInd)) {
                return false;
            }
        }
        {
            boolean lhsPstExemptedInd;
            lhsPstExemptedInd = (true?this.isPstExemptedInd():false);
            boolean rhsPstExemptedInd;
            rhsPstExemptedInd = (true?that.isPstExemptedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pstExemptedInd", lhsPstExemptedInd), LocatorUtils.property(thatLocator, "pstExemptedInd", rhsPstExemptedInd), lhsPstExemptedInd, rhsPstExemptedInd)) {
                return false;
            }
        }
        {
            boolean lhsHstExemptedInd;
            lhsHstExemptedInd = (true?this.isHstExemptedInd():false);
            boolean rhsHstExemptedInd;
            rhsHstExemptedInd = (true?that.isHstExemptedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hstExemptedInd", lhsHstExemptedInd), LocatorUtils.property(thatLocator, "hstExemptedInd", rhsHstExemptedInd), lhsHstExemptedInd, rhsHstExemptedInd)) {
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
