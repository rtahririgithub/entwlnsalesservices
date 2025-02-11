
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
 * HSIA object for HSIA migration
 * 
 * <p>Java class for HsiaInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HsiaInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="migrationIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clientAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maxVoipCapacityNumber" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="specialInstructionTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="migrationReasonCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationIdentifierTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HsiaInformation", propOrder = {
    "migrationIndicator",
    "clientAccountNumber",
    "phoneNumber",
    "serviceCategoryCode",
    "maxVoipCapacityNumber",
    "specialInstructionTxt",
    "migrationReasonCode",
    "locationIdentifierTxt"
})
public class HsiaInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Boolean migrationIndicator;
    protected String clientAccountNumber;
    protected String phoneNumber;
    protected String serviceCategoryCode;
    protected Integer maxVoipCapacityNumber;
    protected String specialInstructionTxt;
    protected String migrationReasonCode;
    protected String locationIdentifierTxt;

    /**
     * Gets the value of the migrationIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMigrationIndicator() {
        return migrationIndicator;
    }

    /**
     * Sets the value of the migrationIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMigrationIndicator(Boolean value) {
        this.migrationIndicator = value;
    }

    /**
     * Gets the value of the clientAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClientAccountNumber() {
        return clientAccountNumber;
    }

    /**
     * Sets the value of the clientAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClientAccountNumber(String value) {
        this.clientAccountNumber = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the serviceCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCategoryCode() {
        return serviceCategoryCode;
    }

    /**
     * Sets the value of the serviceCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCategoryCode(String value) {
        this.serviceCategoryCode = value;
    }

    /**
     * Gets the value of the maxVoipCapacityNumber property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxVoipCapacityNumber() {
        return maxVoipCapacityNumber;
    }

    /**
     * Sets the value of the maxVoipCapacityNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxVoipCapacityNumber(Integer value) {
        this.maxVoipCapacityNumber = value;
    }

    /**
     * Gets the value of the specialInstructionTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialInstructionTxt() {
        return specialInstructionTxt;
    }

    /**
     * Sets the value of the specialInstructionTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialInstructionTxt(String value) {
        this.specialInstructionTxt = value;
    }

    /**
     * Gets the value of the migrationReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMigrationReasonCode() {
        return migrationReasonCode;
    }

    /**
     * Sets the value of the migrationReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMigrationReasonCode(String value) {
        this.migrationReasonCode = value;
    }

    /**
     * Gets the value of the locationIdentifierTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationIdentifierTxt() {
        return locationIdentifierTxt;
    }

    /**
     * Sets the value of the locationIdentifierTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationIdentifierTxt(String value) {
        this.locationIdentifierTxt = value;
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
            Boolean theMigrationIndicator;
            theMigrationIndicator = this.isMigrationIndicator();
            strategy.appendField(locator, this, "migrationIndicator", buffer, theMigrationIndicator);
        }
        {
            String theClientAccountNumber;
            theClientAccountNumber = this.getClientAccountNumber();
            strategy.appendField(locator, this, "clientAccountNumber", buffer, theClientAccountNumber);
        }
        {
            String thePhoneNumber;
            thePhoneNumber = this.getPhoneNumber();
            strategy.appendField(locator, this, "phoneNumber", buffer, thePhoneNumber);
        }
        {
            String theServiceCategoryCode;
            theServiceCategoryCode = this.getServiceCategoryCode();
            strategy.appendField(locator, this, "serviceCategoryCode", buffer, theServiceCategoryCode);
        }
        {
            Integer theMaxVoipCapacityNumber;
            theMaxVoipCapacityNumber = this.getMaxVoipCapacityNumber();
            strategy.appendField(locator, this, "maxVoipCapacityNumber", buffer, theMaxVoipCapacityNumber);
        }
        {
            String theSpecialInstructionTxt;
            theSpecialInstructionTxt = this.getSpecialInstructionTxt();
            strategy.appendField(locator, this, "specialInstructionTxt", buffer, theSpecialInstructionTxt);
        }
        {
            String theMigrationReasonCode;
            theMigrationReasonCode = this.getMigrationReasonCode();
            strategy.appendField(locator, this, "migrationReasonCode", buffer, theMigrationReasonCode);
        }
        {
            String theLocationIdentifierTxt;
            theLocationIdentifierTxt = this.getLocationIdentifierTxt();
            strategy.appendField(locator, this, "locationIdentifierTxt", buffer, theLocationIdentifierTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof HsiaInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final HsiaInformation that = ((HsiaInformation) object);
        {
            Boolean lhsMigrationIndicator;
            lhsMigrationIndicator = this.isMigrationIndicator();
            Boolean rhsMigrationIndicator;
            rhsMigrationIndicator = that.isMigrationIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "migrationIndicator", lhsMigrationIndicator), LocatorUtils.property(thatLocator, "migrationIndicator", rhsMigrationIndicator), lhsMigrationIndicator, rhsMigrationIndicator)) {
                return false;
            }
        }
        {
            String lhsClientAccountNumber;
            lhsClientAccountNumber = this.getClientAccountNumber();
            String rhsClientAccountNumber;
            rhsClientAccountNumber = that.getClientAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "clientAccountNumber", lhsClientAccountNumber), LocatorUtils.property(thatLocator, "clientAccountNumber", rhsClientAccountNumber), lhsClientAccountNumber, rhsClientAccountNumber)) {
                return false;
            }
        }
        {
            String lhsPhoneNumber;
            lhsPhoneNumber = this.getPhoneNumber();
            String rhsPhoneNumber;
            rhsPhoneNumber = that.getPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumber", lhsPhoneNumber), LocatorUtils.property(thatLocator, "phoneNumber", rhsPhoneNumber), lhsPhoneNumber, rhsPhoneNumber)) {
                return false;
            }
        }
        {
            String lhsServiceCategoryCode;
            lhsServiceCategoryCode = this.getServiceCategoryCode();
            String rhsServiceCategoryCode;
            rhsServiceCategoryCode = that.getServiceCategoryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCategoryCode", lhsServiceCategoryCode), LocatorUtils.property(thatLocator, "serviceCategoryCode", rhsServiceCategoryCode), lhsServiceCategoryCode, rhsServiceCategoryCode)) {
                return false;
            }
        }
        {
            Integer lhsMaxVoipCapacityNumber;
            lhsMaxVoipCapacityNumber = this.getMaxVoipCapacityNumber();
            Integer rhsMaxVoipCapacityNumber;
            rhsMaxVoipCapacityNumber = that.getMaxVoipCapacityNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxVoipCapacityNumber", lhsMaxVoipCapacityNumber), LocatorUtils.property(thatLocator, "maxVoipCapacityNumber", rhsMaxVoipCapacityNumber), lhsMaxVoipCapacityNumber, rhsMaxVoipCapacityNumber)) {
                return false;
            }
        }
        {
            String lhsSpecialInstructionTxt;
            lhsSpecialInstructionTxt = this.getSpecialInstructionTxt();
            String rhsSpecialInstructionTxt;
            rhsSpecialInstructionTxt = that.getSpecialInstructionTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "specialInstructionTxt", lhsSpecialInstructionTxt), LocatorUtils.property(thatLocator, "specialInstructionTxt", rhsSpecialInstructionTxt), lhsSpecialInstructionTxt, rhsSpecialInstructionTxt)) {
                return false;
            }
        }
        {
            String lhsMigrationReasonCode;
            lhsMigrationReasonCode = this.getMigrationReasonCode();
            String rhsMigrationReasonCode;
            rhsMigrationReasonCode = that.getMigrationReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "migrationReasonCode", lhsMigrationReasonCode), LocatorUtils.property(thatLocator, "migrationReasonCode", rhsMigrationReasonCode), lhsMigrationReasonCode, rhsMigrationReasonCode)) {
                return false;
            }
        }
        {
            String lhsLocationIdentifierTxt;
            lhsLocationIdentifierTxt = this.getLocationIdentifierTxt();
            String rhsLocationIdentifierTxt;
            rhsLocationIdentifierTxt = that.getLocationIdentifierTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationIdentifierTxt", lhsLocationIdentifierTxt), LocatorUtils.property(thatLocator, "locationIdentifierTxt", rhsLocationIdentifierTxt), lhsLocationIdentifierTxt, rhsLocationIdentifierTxt)) {
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
