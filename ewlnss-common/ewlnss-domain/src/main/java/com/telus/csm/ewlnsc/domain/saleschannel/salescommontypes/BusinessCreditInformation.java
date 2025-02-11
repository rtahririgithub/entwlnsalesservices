
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for BusinessCreditInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessCreditInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="incorporationNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incorporationDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessCreditInformation", propOrder = {
    "incorporationNumber",
    "incorporationDate"
})
public class BusinessCreditInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String incorporationNumber;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date incorporationDate;

    /**
     * Gets the value of the incorporationNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIncorporationNumber() {
        return incorporationNumber;
    }

    /**
     * Sets the value of the incorporationNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncorporationNumber(String value) {
        this.incorporationNumber = value;
    }

    /**
     * Gets the value of the incorporationDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getIncorporationDate() {
        return incorporationDate;
    }

    /**
     * Sets the value of the incorporationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIncorporationDate(Date value) {
        this.incorporationDate = value;
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
            String theIncorporationNumber;
            theIncorporationNumber = this.getIncorporationNumber();
            strategy.appendField(locator, this, "incorporationNumber", buffer, theIncorporationNumber);
        }
        {
            Date theIncorporationDate;
            theIncorporationDate = this.getIncorporationDate();
            strategy.appendField(locator, this, "incorporationDate", buffer, theIncorporationDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BusinessCreditInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BusinessCreditInformation that = ((BusinessCreditInformation) object);
        {
            String lhsIncorporationNumber;
            lhsIncorporationNumber = this.getIncorporationNumber();
            String rhsIncorporationNumber;
            rhsIncorporationNumber = that.getIncorporationNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "incorporationNumber", lhsIncorporationNumber), LocatorUtils.property(thatLocator, "incorporationNumber", rhsIncorporationNumber), lhsIncorporationNumber, rhsIncorporationNumber)) {
                return false;
            }
        }
        {
            Date lhsIncorporationDate;
            lhsIncorporationDate = this.getIncorporationDate();
            Date rhsIncorporationDate;
            rhsIncorporationDate = that.getIncorporationDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "incorporationDate", lhsIncorporationDate), LocatorUtils.property(thatLocator, "incorporationDate", rhsIncorporationDate), lhsIncorporationDate, rhsIncorporationDate)) {
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
