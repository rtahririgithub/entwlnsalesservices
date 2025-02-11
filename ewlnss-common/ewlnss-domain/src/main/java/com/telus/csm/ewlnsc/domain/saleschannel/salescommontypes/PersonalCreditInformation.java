
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
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CreditIdentification;
import com.telus.tmi.xmlschema.xsd.customer.customer.wirelinecreditprofilemanagementtypes_v1.CreditValue;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PersonalCreditInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonalCreditInformation">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Customer/BaseTypes/CreditCommon_v1}CreditIdentification">
 *       &lt;sequence>
 *         &lt;element name="creditCard" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditCard" minOccurs="0"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="creditRestrictInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="creditValue" type="{http://xmlschema.tmi.telus.com/xsd/Customer/Customer/WirelineCreditProfileManagementTypes_v1}CreditValue" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonalCreditInformation", propOrder = {
    "creditCard",
    "birthDate",
    "creditRestrictInd",
    "creditValue"
})
public class PersonalCreditInformation
    extends CreditIdentification
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected CreditCard creditCard;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date birthDate;
    protected Boolean creditRestrictInd;
    protected CreditValue creditValue;

    /**
     * Gets the value of the creditCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the value of the creditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCreditCard(CreditCard value) {
        this.creditCard = value;
    }

    /**
     * Gets the value of the birthDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the value of the birthDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBirthDate(Date value) {
        this.birthDate = value;
    }

    /**
     * Gets the value of the creditRestrictInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreditRestrictInd() {
        return creditRestrictInd;
    }

    /**
     * Sets the value of the creditRestrictInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreditRestrictInd(Boolean value) {
        this.creditRestrictInd = value;
    }

    /**
     * Gets the value of the creditValue property.
     * 
     * @return
     *     possible object is
     *     {@link CreditValue }
     *     
     */
    public CreditValue getCreditValue() {
        return creditValue;
    }

    /**
     * Sets the value of the creditValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditValue }
     *     
     */
    public void setCreditValue(CreditValue value) {
        this.creditValue = value;
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
            CreditCard theCreditCard;
            theCreditCard = this.getCreditCard();
            strategy.appendField(locator, this, "creditCard", buffer, theCreditCard);
        }
        {
            Date theBirthDate;
            theBirthDate = this.getBirthDate();
            strategy.appendField(locator, this, "birthDate", buffer, theBirthDate);
        }
        {
            Boolean theCreditRestrictInd;
            theCreditRestrictInd = this.isCreditRestrictInd();
            strategy.appendField(locator, this, "creditRestrictInd", buffer, theCreditRestrictInd);
        }
        {
            CreditValue theCreditValue;
            theCreditValue = this.getCreditValue();
            strategy.appendField(locator, this, "creditValue", buffer, theCreditValue);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PersonalCreditInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final PersonalCreditInformation that = ((PersonalCreditInformation) object);
        {
            CreditCard lhsCreditCard;
            lhsCreditCard = this.getCreditCard();
            CreditCard rhsCreditCard;
            rhsCreditCard = that.getCreditCard();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCard", lhsCreditCard), LocatorUtils.property(thatLocator, "creditCard", rhsCreditCard), lhsCreditCard, rhsCreditCard)) {
                return false;
            }
        }
        {
            Date lhsBirthDate;
            lhsBirthDate = this.getBirthDate();
            Date rhsBirthDate;
            rhsBirthDate = that.getBirthDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "birthDate", lhsBirthDate), LocatorUtils.property(thatLocator, "birthDate", rhsBirthDate), lhsBirthDate, rhsBirthDate)) {
                return false;
            }
        }
        {
            Boolean lhsCreditRestrictInd;
            lhsCreditRestrictInd = this.isCreditRestrictInd();
            Boolean rhsCreditRestrictInd;
            rhsCreditRestrictInd = that.isCreditRestrictInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditRestrictInd", lhsCreditRestrictInd), LocatorUtils.property(thatLocator, "creditRestrictInd", rhsCreditRestrictInd), lhsCreditRestrictInd, rhsCreditRestrictInd)) {
                return false;
            }
        }
        {
            CreditValue lhsCreditValue;
            lhsCreditValue = this.getCreditValue();
            CreditValue rhsCreditValue;
            rhsCreditValue = that.getCreditValue();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditValue", lhsCreditValue), LocatorUtils.property(thatLocator, "creditValue", rhsCreditValue), lhsCreditValue, rhsCreditValue)) {
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
