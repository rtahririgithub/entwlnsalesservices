
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
 * <p>Java class for CreditProgramDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditProgramDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditProgram">
 *       &lt;sequence>
 *         &lt;element name="creditAssessmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditProgramDetail", propOrder = {
    "creditAssessmentId"
})
public class CreditProgramDetail
    extends CreditProgram
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String creditAssessmentId;

    /**
     * Gets the value of the creditAssessmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditAssessmentId() {
        return creditAssessmentId;
    }

    /**
     * Sets the value of the creditAssessmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditAssessmentId(String value) {
        this.creditAssessmentId = value;
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
            String theCreditAssessmentId;
            theCreditAssessmentId = this.getCreditAssessmentId();
            strategy.appendField(locator, this, "creditAssessmentId", buffer, theCreditAssessmentId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreditProgramDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final CreditProgramDetail that = ((CreditProgramDetail) object);
        {
            String lhsCreditAssessmentId;
            lhsCreditAssessmentId = this.getCreditAssessmentId();
            String rhsCreditAssessmentId;
            rhsCreditAssessmentId = that.getCreditAssessmentId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditAssessmentId", lhsCreditAssessmentId), LocatorUtils.property(thatLocator, "creditAssessmentId", rhsCreditAssessmentId), lhsCreditAssessmentId, rhsCreditAssessmentId)) {
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
