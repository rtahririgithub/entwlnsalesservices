
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
 * <p>Java class for NewSubscriberDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewSubscriberDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="birthDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="voicemailLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactLanguageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contactEmailAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ElectronicContact" minOccurs="0"/>
 *         &lt;element name="businessUser" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesOrderBusinessUserType" minOccurs="0"/>
 *         &lt;element name="reasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberAdditionalLineTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewSubscriberDetail", propOrder = {
    "firstName",
    "lastName",
    "birthDate",
    "voicemailLanguageCode",
    "contactLanguageCode",
    "contactEmailAddress",
    "businessUser",
    "reasonCode",
    "subscriberAdditionalLineTxt"
})
public class NewSubscriberDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String firstName;
    @XmlElement(required = true)
    protected String lastName;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date birthDate;
    protected String voicemailLanguageCode;
    protected String contactLanguageCode;
    protected ElectronicContact contactEmailAddress;
    protected SalesOrderBusinessUserType businessUser;
    protected String reasonCode;
    protected String subscriberAdditionalLineTxt;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
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
     * Gets the value of the voicemailLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVoicemailLanguageCode() {
        return voicemailLanguageCode;
    }

    /**
     * Sets the value of the voicemailLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVoicemailLanguageCode(String value) {
        this.voicemailLanguageCode = value;
    }

    /**
     * Gets the value of the contactLanguageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContactLanguageCode() {
        return contactLanguageCode;
    }

    /**
     * Sets the value of the contactLanguageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContactLanguageCode(String value) {
        this.contactLanguageCode = value;
    }

    /**
     * Gets the value of the contactEmailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link ElectronicContact }
     *     
     */
    public ElectronicContact getContactEmailAddress() {
        return contactEmailAddress;
    }

    /**
     * Sets the value of the contactEmailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link ElectronicContact }
     *     
     */
    public void setContactEmailAddress(ElectronicContact value) {
        this.contactEmailAddress = value;
    }

    /**
     * Gets the value of the businessUser property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOrderBusinessUserType }
     *     
     */
    public SalesOrderBusinessUserType getBusinessUser() {
        return businessUser;
    }

    /**
     * Sets the value of the businessUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOrderBusinessUserType }
     *     
     */
    public void setBusinessUser(SalesOrderBusinessUserType value) {
        this.businessUser = value;
    }

    /**
     * Gets the value of the reasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReasonCode() {
        return reasonCode;
    }

    /**
     * Sets the value of the reasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReasonCode(String value) {
        this.reasonCode = value;
    }

    /**
     * Gets the value of the subscriberAdditionalLineTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberAdditionalLineTxt() {
        return subscriberAdditionalLineTxt;
    }

    /**
     * Sets the value of the subscriberAdditionalLineTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberAdditionalLineTxt(String value) {
        this.subscriberAdditionalLineTxt = value;
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
            String theFirstName;
            theFirstName = this.getFirstName();
            strategy.appendField(locator, this, "firstName", buffer, theFirstName);
        }
        {
            String theLastName;
            theLastName = this.getLastName();
            strategy.appendField(locator, this, "lastName", buffer, theLastName);
        }
        {
            Date theBirthDate;
            theBirthDate = this.getBirthDate();
            strategy.appendField(locator, this, "birthDate", buffer, theBirthDate);
        }
        {
            String theVoicemailLanguageCode;
            theVoicemailLanguageCode = this.getVoicemailLanguageCode();
            strategy.appendField(locator, this, "voicemailLanguageCode", buffer, theVoicemailLanguageCode);
        }
        {
            String theContactLanguageCode;
            theContactLanguageCode = this.getContactLanguageCode();
            strategy.appendField(locator, this, "contactLanguageCode", buffer, theContactLanguageCode);
        }
        {
            ElectronicContact theContactEmailAddress;
            theContactEmailAddress = this.getContactEmailAddress();
            strategy.appendField(locator, this, "contactEmailAddress", buffer, theContactEmailAddress);
        }
        {
            SalesOrderBusinessUserType theBusinessUser;
            theBusinessUser = this.getBusinessUser();
            strategy.appendField(locator, this, "businessUser", buffer, theBusinessUser);
        }
        {
            String theReasonCode;
            theReasonCode = this.getReasonCode();
            strategy.appendField(locator, this, "reasonCode", buffer, theReasonCode);
        }
        {
            String theSubscriberAdditionalLineTxt;
            theSubscriberAdditionalLineTxt = this.getSubscriberAdditionalLineTxt();
            strategy.appendField(locator, this, "subscriberAdditionalLineTxt", buffer, theSubscriberAdditionalLineTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof NewSubscriberDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final NewSubscriberDetail that = ((NewSubscriberDetail) object);
        {
            String lhsFirstName;
            lhsFirstName = this.getFirstName();
            String rhsFirstName;
            rhsFirstName = that.getFirstName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstName", lhsFirstName), LocatorUtils.property(thatLocator, "firstName", rhsFirstName), lhsFirstName, rhsFirstName)) {
                return false;
            }
        }
        {
            String lhsLastName;
            lhsLastName = this.getLastName();
            String rhsLastName;
            rhsLastName = that.getLastName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastName", lhsLastName), LocatorUtils.property(thatLocator, "lastName", rhsLastName), lhsLastName, rhsLastName)) {
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
            String lhsVoicemailLanguageCode;
            lhsVoicemailLanguageCode = this.getVoicemailLanguageCode();
            String rhsVoicemailLanguageCode;
            rhsVoicemailLanguageCode = that.getVoicemailLanguageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "voicemailLanguageCode", lhsVoicemailLanguageCode), LocatorUtils.property(thatLocator, "voicemailLanguageCode", rhsVoicemailLanguageCode), lhsVoicemailLanguageCode, rhsVoicemailLanguageCode)) {
                return false;
            }
        }
        {
            String lhsContactLanguageCode;
            lhsContactLanguageCode = this.getContactLanguageCode();
            String rhsContactLanguageCode;
            rhsContactLanguageCode = that.getContactLanguageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contactLanguageCode", lhsContactLanguageCode), LocatorUtils.property(thatLocator, "contactLanguageCode", rhsContactLanguageCode), lhsContactLanguageCode, rhsContactLanguageCode)) {
                return false;
            }
        }
        {
            ElectronicContact lhsContactEmailAddress;
            lhsContactEmailAddress = this.getContactEmailAddress();
            ElectronicContact rhsContactEmailAddress;
            rhsContactEmailAddress = that.getContactEmailAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contactEmailAddress", lhsContactEmailAddress), LocatorUtils.property(thatLocator, "contactEmailAddress", rhsContactEmailAddress), lhsContactEmailAddress, rhsContactEmailAddress)) {
                return false;
            }
        }
        {
            SalesOrderBusinessUserType lhsBusinessUser;
            lhsBusinessUser = this.getBusinessUser();
            SalesOrderBusinessUserType rhsBusinessUser;
            rhsBusinessUser = that.getBusinessUser();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessUser", lhsBusinessUser), LocatorUtils.property(thatLocator, "businessUser", rhsBusinessUser), lhsBusinessUser, rhsBusinessUser)) {
                return false;
            }
        }
        {
            String lhsReasonCode;
            lhsReasonCode = this.getReasonCode();
            String rhsReasonCode;
            rhsReasonCode = that.getReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "reasonCode", lhsReasonCode), LocatorUtils.property(thatLocator, "reasonCode", rhsReasonCode), lhsReasonCode, rhsReasonCode)) {
                return false;
            }
        }
        {
            String lhsSubscriberAdditionalLineTxt;
            lhsSubscriberAdditionalLineTxt = this.getSubscriberAdditionalLineTxt();
            String rhsSubscriberAdditionalLineTxt;
            rhsSubscriberAdditionalLineTxt = that.getSubscriberAdditionalLineTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberAdditionalLineTxt", lhsSubscriberAdditionalLineTxt), LocatorUtils.property(thatLocator, "subscriberAdditionalLineTxt", rhsSubscriberAdditionalLineTxt), lhsSubscriberAdditionalLineTxt, rhsSubscriberAdditionalLineTxt)) {
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
