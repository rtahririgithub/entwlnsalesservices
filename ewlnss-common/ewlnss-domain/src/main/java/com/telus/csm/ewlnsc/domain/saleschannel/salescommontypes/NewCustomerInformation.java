
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
 * <p>Java class for NewCustomerInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NewCustomerInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mainAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="titleName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="servicePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessPhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessPhoneExtensionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobilePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredLanguageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="emailAddressTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="authorizedName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditProfile" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CustomerCreditInformation" minOccurs="0"/>
 *         &lt;element name="emailDeclinedInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="emailDeclinedReasonCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preferredContactTimePeriodCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NewCustomerInformation", propOrder = {
    "mainAddress",
    "titleName",
    "firstName",
    "lastName",
    "servicePhoneNumber",
    "pin",
    "businessPhoneNumber",
    "businessPhoneExtensionNumber",
    "mobilePhoneNumber",
    "preferredLanguageCd",
    "emailAddressTxt",
    "authorizedName",
    "creditProfile",
    "emailDeclinedInd",
    "emailDeclinedReasonCd",
    "preferredContactTimePeriodCd"
})
public class NewCustomerInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Address mainAddress;
    protected String titleName;
    protected String firstName;
    protected String lastName;
    protected String servicePhoneNumber;
    protected String pin;
    protected String businessPhoneNumber;
    protected String businessPhoneExtensionNumber;
    protected String mobilePhoneNumber;
    protected String preferredLanguageCd;
    protected String emailAddressTxt;
    protected String authorizedName;
    protected CustomerCreditInformation creditProfile;
    protected Boolean emailDeclinedInd;
    protected String emailDeclinedReasonCd;
    protected String preferredContactTimePeriodCd;

    /**
     * Gets the value of the mainAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getMainAddress() {
        return mainAddress;
    }

    /**
     * Sets the value of the mainAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setMainAddress(Address value) {
        this.mainAddress = value;
    }

    /**
     * Gets the value of the titleName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * Sets the value of the titleName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitleName(String value) {
        this.titleName = value;
    }

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
     * Gets the value of the servicePhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServicePhoneNumber() {
        return servicePhoneNumber;
    }

    /**
     * Sets the value of the servicePhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServicePhoneNumber(String value) {
        this.servicePhoneNumber = value;
    }

    /**
     * Gets the value of the pin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPin() {
        return pin;
    }

    /**
     * Sets the value of the pin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPin(String value) {
        this.pin = value;
    }

    /**
     * Gets the value of the businessPhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPhoneNumber() {
        return businessPhoneNumber;
    }

    /**
     * Sets the value of the businessPhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPhoneNumber(String value) {
        this.businessPhoneNumber = value;
    }

    /**
     * Gets the value of the businessPhoneExtensionNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPhoneExtensionNumber() {
        return businessPhoneExtensionNumber;
    }

    /**
     * Sets the value of the businessPhoneExtensionNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPhoneExtensionNumber(String value) {
        this.businessPhoneExtensionNumber = value;
    }

    /**
     * Gets the value of the mobilePhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    /**
     * Sets the value of the mobilePhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMobilePhoneNumber(String value) {
        this.mobilePhoneNumber = value;
    }

    /**
     * Gets the value of the preferredLanguageCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredLanguageCd() {
        return preferredLanguageCd;
    }

    /**
     * Sets the value of the preferredLanguageCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredLanguageCd(String value) {
        this.preferredLanguageCd = value;
    }

    /**
     * Gets the value of the emailAddressTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAddressTxt() {
        return emailAddressTxt;
    }

    /**
     * Sets the value of the emailAddressTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAddressTxt(String value) {
        this.emailAddressTxt = value;
    }

    /**
     * Gets the value of the authorizedName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuthorizedName() {
        return authorizedName;
    }

    /**
     * Sets the value of the authorizedName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuthorizedName(String value) {
        this.authorizedName = value;
    }

    /**
     * Gets the value of the creditProfile property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerCreditInformation }
     *     
     */
    public CustomerCreditInformation getCreditProfile() {
        return creditProfile;
    }

    /**
     * Sets the value of the creditProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerCreditInformation }
     *     
     */
    public void setCreditProfile(CustomerCreditInformation value) {
        this.creditProfile = value;
    }

    /**
     * Gets the value of the emailDeclinedInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEmailDeclinedInd() {
        return emailDeclinedInd;
    }

    /**
     * Sets the value of the emailDeclinedInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEmailDeclinedInd(Boolean value) {
        this.emailDeclinedInd = value;
    }

    /**
     * Gets the value of the emailDeclinedReasonCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailDeclinedReasonCd() {
        return emailDeclinedReasonCd;
    }

    /**
     * Sets the value of the emailDeclinedReasonCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailDeclinedReasonCd(String value) {
        this.emailDeclinedReasonCd = value;
    }

    /**
     * Gets the value of the preferredContactTimePeriodCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPreferredContactTimePeriodCd() {
        return preferredContactTimePeriodCd;
    }

    /**
     * Sets the value of the preferredContactTimePeriodCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPreferredContactTimePeriodCd(String value) {
        this.preferredContactTimePeriodCd = value;
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
            Address theMainAddress;
            theMainAddress = this.getMainAddress();
            strategy.appendField(locator, this, "mainAddress", buffer, theMainAddress);
        }
        {
            String theTitleName;
            theTitleName = this.getTitleName();
            strategy.appendField(locator, this, "titleName", buffer, theTitleName);
        }
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
            String theServicePhoneNumber;
            theServicePhoneNumber = this.getServicePhoneNumber();
            strategy.appendField(locator, this, "servicePhoneNumber", buffer, theServicePhoneNumber);
        }
        {
            String thePin;
            thePin = this.getPin();
            strategy.appendField(locator, this, "pin", buffer, thePin);
        }
        {
            String theBusinessPhoneNumber;
            theBusinessPhoneNumber = this.getBusinessPhoneNumber();
            strategy.appendField(locator, this, "businessPhoneNumber", buffer, theBusinessPhoneNumber);
        }
        {
            String theBusinessPhoneExtensionNumber;
            theBusinessPhoneExtensionNumber = this.getBusinessPhoneExtensionNumber();
            strategy.appendField(locator, this, "businessPhoneExtensionNumber", buffer, theBusinessPhoneExtensionNumber);
        }
        {
            String theMobilePhoneNumber;
            theMobilePhoneNumber = this.getMobilePhoneNumber();
            strategy.appendField(locator, this, "mobilePhoneNumber", buffer, theMobilePhoneNumber);
        }
        {
            String thePreferredLanguageCd;
            thePreferredLanguageCd = this.getPreferredLanguageCd();
            strategy.appendField(locator, this, "preferredLanguageCd", buffer, thePreferredLanguageCd);
        }
        {
            String theEmailAddressTxt;
            theEmailAddressTxt = this.getEmailAddressTxt();
            strategy.appendField(locator, this, "emailAddressTxt", buffer, theEmailAddressTxt);
        }
        {
            String theAuthorizedName;
            theAuthorizedName = this.getAuthorizedName();
            strategy.appendField(locator, this, "authorizedName", buffer, theAuthorizedName);
        }
        {
            CustomerCreditInformation theCreditProfile;
            theCreditProfile = this.getCreditProfile();
            strategy.appendField(locator, this, "creditProfile", buffer, theCreditProfile);
        }
        {
            Boolean theEmailDeclinedInd;
            theEmailDeclinedInd = this.isEmailDeclinedInd();
            strategy.appendField(locator, this, "emailDeclinedInd", buffer, theEmailDeclinedInd);
        }
        {
            String theEmailDeclinedReasonCd;
            theEmailDeclinedReasonCd = this.getEmailDeclinedReasonCd();
            strategy.appendField(locator, this, "emailDeclinedReasonCd", buffer, theEmailDeclinedReasonCd);
        }
        {
            String thePreferredContactTimePeriodCd;
            thePreferredContactTimePeriodCd = this.getPreferredContactTimePeriodCd();
            strategy.appendField(locator, this, "preferredContactTimePeriodCd", buffer, thePreferredContactTimePeriodCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof NewCustomerInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final NewCustomerInformation that = ((NewCustomerInformation) object);
        {
            Address lhsMainAddress;
            lhsMainAddress = this.getMainAddress();
            Address rhsMainAddress;
            rhsMainAddress = that.getMainAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mainAddress", lhsMainAddress), LocatorUtils.property(thatLocator, "mainAddress", rhsMainAddress), lhsMainAddress, rhsMainAddress)) {
                return false;
            }
        }
        {
            String lhsTitleName;
            lhsTitleName = this.getTitleName();
            String rhsTitleName;
            rhsTitleName = that.getTitleName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "titleName", lhsTitleName), LocatorUtils.property(thatLocator, "titleName", rhsTitleName), lhsTitleName, rhsTitleName)) {
                return false;
            }
        }
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
            String lhsServicePhoneNumber;
            lhsServicePhoneNumber = this.getServicePhoneNumber();
            String rhsServicePhoneNumber;
            rhsServicePhoneNumber = that.getServicePhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "servicePhoneNumber", lhsServicePhoneNumber), LocatorUtils.property(thatLocator, "servicePhoneNumber", rhsServicePhoneNumber), lhsServicePhoneNumber, rhsServicePhoneNumber)) {
                return false;
            }
        }
        {
            String lhsPin;
            lhsPin = this.getPin();
            String rhsPin;
            rhsPin = that.getPin();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pin", lhsPin), LocatorUtils.property(thatLocator, "pin", rhsPin), lhsPin, rhsPin)) {
                return false;
            }
        }
        {
            String lhsBusinessPhoneNumber;
            lhsBusinessPhoneNumber = this.getBusinessPhoneNumber();
            String rhsBusinessPhoneNumber;
            rhsBusinessPhoneNumber = that.getBusinessPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessPhoneNumber", lhsBusinessPhoneNumber), LocatorUtils.property(thatLocator, "businessPhoneNumber", rhsBusinessPhoneNumber), lhsBusinessPhoneNumber, rhsBusinessPhoneNumber)) {
                return false;
            }
        }
        {
            String lhsBusinessPhoneExtensionNumber;
            lhsBusinessPhoneExtensionNumber = this.getBusinessPhoneExtensionNumber();
            String rhsBusinessPhoneExtensionNumber;
            rhsBusinessPhoneExtensionNumber = that.getBusinessPhoneExtensionNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessPhoneExtensionNumber", lhsBusinessPhoneExtensionNumber), LocatorUtils.property(thatLocator, "businessPhoneExtensionNumber", rhsBusinessPhoneExtensionNumber), lhsBusinessPhoneExtensionNumber, rhsBusinessPhoneExtensionNumber)) {
                return false;
            }
        }
        {
            String lhsMobilePhoneNumber;
            lhsMobilePhoneNumber = this.getMobilePhoneNumber();
            String rhsMobilePhoneNumber;
            rhsMobilePhoneNumber = that.getMobilePhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mobilePhoneNumber", lhsMobilePhoneNumber), LocatorUtils.property(thatLocator, "mobilePhoneNumber", rhsMobilePhoneNumber), lhsMobilePhoneNumber, rhsMobilePhoneNumber)) {
                return false;
            }
        }
        {
            String lhsPreferredLanguageCd;
            lhsPreferredLanguageCd = this.getPreferredLanguageCd();
            String rhsPreferredLanguageCd;
            rhsPreferredLanguageCd = that.getPreferredLanguageCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preferredLanguageCd", lhsPreferredLanguageCd), LocatorUtils.property(thatLocator, "preferredLanguageCd", rhsPreferredLanguageCd), lhsPreferredLanguageCd, rhsPreferredLanguageCd)) {
                return false;
            }
        }
        {
            String lhsEmailAddressTxt;
            lhsEmailAddressTxt = this.getEmailAddressTxt();
            String rhsEmailAddressTxt;
            rhsEmailAddressTxt = that.getEmailAddressTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "emailAddressTxt", lhsEmailAddressTxt), LocatorUtils.property(thatLocator, "emailAddressTxt", rhsEmailAddressTxt), lhsEmailAddressTxt, rhsEmailAddressTxt)) {
                return false;
            }
        }
        {
            String lhsAuthorizedName;
            lhsAuthorizedName = this.getAuthorizedName();
            String rhsAuthorizedName;
            rhsAuthorizedName = that.getAuthorizedName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "authorizedName", lhsAuthorizedName), LocatorUtils.property(thatLocator, "authorizedName", rhsAuthorizedName), lhsAuthorizedName, rhsAuthorizedName)) {
                return false;
            }
        }
        {
            CustomerCreditInformation lhsCreditProfile;
            lhsCreditProfile = this.getCreditProfile();
            CustomerCreditInformation rhsCreditProfile;
            rhsCreditProfile = that.getCreditProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditProfile", lhsCreditProfile), LocatorUtils.property(thatLocator, "creditProfile", rhsCreditProfile), lhsCreditProfile, rhsCreditProfile)) {
                return false;
            }
        }
        {
            Boolean lhsEmailDeclinedInd;
            lhsEmailDeclinedInd = this.isEmailDeclinedInd();
            Boolean rhsEmailDeclinedInd;
            rhsEmailDeclinedInd = that.isEmailDeclinedInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "emailDeclinedInd", lhsEmailDeclinedInd), LocatorUtils.property(thatLocator, "emailDeclinedInd", rhsEmailDeclinedInd), lhsEmailDeclinedInd, rhsEmailDeclinedInd)) {
                return false;
            }
        }
        {
            String lhsEmailDeclinedReasonCd;
            lhsEmailDeclinedReasonCd = this.getEmailDeclinedReasonCd();
            String rhsEmailDeclinedReasonCd;
            rhsEmailDeclinedReasonCd = that.getEmailDeclinedReasonCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "emailDeclinedReasonCd", lhsEmailDeclinedReasonCd), LocatorUtils.property(thatLocator, "emailDeclinedReasonCd", rhsEmailDeclinedReasonCd), lhsEmailDeclinedReasonCd, rhsEmailDeclinedReasonCd)) {
                return false;
            }
        }
        {
            String lhsPreferredContactTimePeriodCd;
            lhsPreferredContactTimePeriodCd = this.getPreferredContactTimePeriodCd();
            String rhsPreferredContactTimePeriodCd;
            rhsPreferredContactTimePeriodCd = that.getPreferredContactTimePeriodCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preferredContactTimePeriodCd", lhsPreferredContactTimePeriodCd), LocatorUtils.property(thatLocator, "preferredContactTimePeriodCd", rhsPreferredContactTimePeriodCd), lhsPreferredContactTimePeriodCd, rhsPreferredContactTimePeriodCd)) {
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
