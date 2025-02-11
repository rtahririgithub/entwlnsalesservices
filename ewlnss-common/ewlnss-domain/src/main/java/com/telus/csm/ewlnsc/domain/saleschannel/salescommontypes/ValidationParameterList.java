
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for ValidationParameterList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ValidationParameterList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="validationParameter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ValidationParameterDetail" maxOccurs="50"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ValidationParameterList", propOrder = {
    "validationParameter"
})
public class ValidationParameterList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<ValidationParameterDetail> validationParameter;

    /**
     * Gets the value of the validationParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validationParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidationParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidationParameterDetail }
     * 
     * 
     */
    public List<ValidationParameterDetail> getValidationParameter() {
        if (validationParameter == null) {
            validationParameter = new ArrayList<ValidationParameterDetail>();
        }
        return this.validationParameter;
    }

    /**
     * Sets the value of the validationParameter property.
     * 
     * @param validationParameter
     *     allowed object is
     *     {@link ValidationParameterDetail }
     *     
     */
    public void setValidationParameter(List<ValidationParameterDetail> validationParameter) {
        this.validationParameter = validationParameter;
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
            List<ValidationParameterDetail> theValidationParameter;
            theValidationParameter = (((this.validationParameter!= null)&&(!this.validationParameter.isEmpty()))?this.getValidationParameter():null);
            strategy.appendField(locator, this, "validationParameter", buffer, theValidationParameter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ValidationParameterList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ValidationParameterList that = ((ValidationParameterList) object);
        {
            List<ValidationParameterDetail> lhsValidationParameter;
            lhsValidationParameter = (((this.validationParameter!= null)&&(!this.validationParameter.isEmpty()))?this.getValidationParameter():null);
            List<ValidationParameterDetail> rhsValidationParameter;
            rhsValidationParameter = (((that.validationParameter!= null)&&(!that.validationParameter.isEmpty()))?that.getValidationParameter():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validationParameter", lhsValidationParameter), LocatorUtils.property(thatLocator, "validationParameter", rhsValidationParameter), lhsValidationParameter, rhsValidationParameter)) {
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
