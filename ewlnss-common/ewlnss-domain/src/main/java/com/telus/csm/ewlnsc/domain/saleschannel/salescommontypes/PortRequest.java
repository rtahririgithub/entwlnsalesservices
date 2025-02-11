
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
 * <p>Java class for PortRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PortRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="portTransferAuthConsentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="serviceAddressVerifiedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="name" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Name" minOccurs="0"/>
 *         &lt;element name="address" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PortRequestAddress" minOccurs="0"/>
 *         &lt;element name="dslServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="addressChangeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldDSLServiceResellerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldServiceProviderPIN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldServiceProviderAccountNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oldServiceProviderESN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="specialInstructionsText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alternateContactNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="alternateContactEmailTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portDirectionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="autoActivateInd" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PortRequest", propOrder = {
    "phoneNumber",
    "portTransferAuthConsentIndicator",
    "serviceAddressVerifiedIndicator",
    "name",
    "address",
    "dslServiceCode",
    "addressChangeCode",
    "oldDSLServiceResellerName",
    "oldServiceProviderPIN",
    "oldServiceProviderAccountNumber",
    "oldServiceProviderESN",
    "specialInstructionsText",
    "alternateContactNumber",
    "alternateContactEmailTxt",
    "portDirectionCode",
    "autoActivateInd"
})
public class PortRequest
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String phoneNumber;
    protected boolean portTransferAuthConsentIndicator;
    protected Boolean serviceAddressVerifiedIndicator;
    protected Name name;
    protected PortRequestAddress address;
    protected String dslServiceCode;
    protected String addressChangeCode;
    protected String oldDSLServiceResellerName;
    protected String oldServiceProviderPIN;
    protected String oldServiceProviderAccountNumber;
    protected String oldServiceProviderESN;
    protected String specialInstructionsText;
    protected String alternateContactNumber;
    protected String alternateContactEmailTxt;
    protected String portDirectionCode;
    protected Object autoActivateInd;

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
     * Gets the value of the portTransferAuthConsentIndicator property.
     * 
     */
    public boolean isPortTransferAuthConsentIndicator() {
        return portTransferAuthConsentIndicator;
    }

    /**
     * Sets the value of the portTransferAuthConsentIndicator property.
     * 
     */
    public void setPortTransferAuthConsentIndicator(boolean value) {
        this.portTransferAuthConsentIndicator = value;
    }

    /**
     * Gets the value of the serviceAddressVerifiedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isServiceAddressVerifiedIndicator() {
        return serviceAddressVerifiedIndicator;
    }

    /**
     * Sets the value of the serviceAddressVerifiedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setServiceAddressVerifiedIndicator(Boolean value) {
        this.serviceAddressVerifiedIndicator = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link Name }
     *     
     */
    public Name getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setName(Name value) {
        this.name = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link PortRequestAddress }
     *     
     */
    public PortRequestAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link PortRequestAddress }
     *     
     */
    public void setAddress(PortRequestAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the dslServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDslServiceCode() {
        return dslServiceCode;
    }

    /**
     * Sets the value of the dslServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDslServiceCode(String value) {
        this.dslServiceCode = value;
    }

    /**
     * Gets the value of the addressChangeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddressChangeCode() {
        return addressChangeCode;
    }

    /**
     * Sets the value of the addressChangeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddressChangeCode(String value) {
        this.addressChangeCode = value;
    }

    /**
     * Gets the value of the oldDSLServiceResellerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldDSLServiceResellerName() {
        return oldDSLServiceResellerName;
    }

    /**
     * Sets the value of the oldDSLServiceResellerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldDSLServiceResellerName(String value) {
        this.oldDSLServiceResellerName = value;
    }

    /**
     * Gets the value of the oldServiceProviderPIN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldServiceProviderPIN() {
        return oldServiceProviderPIN;
    }

    /**
     * Sets the value of the oldServiceProviderPIN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldServiceProviderPIN(String value) {
        this.oldServiceProviderPIN = value;
    }

    /**
     * Gets the value of the oldServiceProviderAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldServiceProviderAccountNumber() {
        return oldServiceProviderAccountNumber;
    }

    /**
     * Sets the value of the oldServiceProviderAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldServiceProviderAccountNumber(String value) {
        this.oldServiceProviderAccountNumber = value;
    }

    /**
     * Gets the value of the oldServiceProviderESN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOldServiceProviderESN() {
        return oldServiceProviderESN;
    }

    /**
     * Sets the value of the oldServiceProviderESN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOldServiceProviderESN(String value) {
        this.oldServiceProviderESN = value;
    }

    /**
     * Gets the value of the specialInstructionsText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialInstructionsText() {
        return specialInstructionsText;
    }

    /**
     * Sets the value of the specialInstructionsText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialInstructionsText(String value) {
        this.specialInstructionsText = value;
    }

    /**
     * Gets the value of the alternateContactNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateContactNumber() {
        return alternateContactNumber;
    }

    /**
     * Sets the value of the alternateContactNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateContactNumber(String value) {
        this.alternateContactNumber = value;
    }

    /**
     * Gets the value of the alternateContactEmailTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAlternateContactEmailTxt() {
        return alternateContactEmailTxt;
    }

    /**
     * Sets the value of the alternateContactEmailTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAlternateContactEmailTxt(String value) {
        this.alternateContactEmailTxt = value;
    }

    /**
     * Gets the value of the portDirectionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortDirectionCode() {
        return portDirectionCode;
    }

    /**
     * Sets the value of the portDirectionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortDirectionCode(String value) {
        this.portDirectionCode = value;
    }

    /**
     * Gets the value of the autoActivateInd property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAutoActivateInd() {
        return autoActivateInd;
    }

    /**
     * Sets the value of the autoActivateInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAutoActivateInd(Object value) {
        this.autoActivateInd = value;
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
            String thePhoneNumber;
            thePhoneNumber = this.getPhoneNumber();
            strategy.appendField(locator, this, "phoneNumber", buffer, thePhoneNumber);
        }
        {
            boolean thePortTransferAuthConsentIndicator;
            thePortTransferAuthConsentIndicator = (true?this.isPortTransferAuthConsentIndicator():false);
            strategy.appendField(locator, this, "portTransferAuthConsentIndicator", buffer, thePortTransferAuthConsentIndicator);
        }
        {
            Boolean theServiceAddressVerifiedIndicator;
            theServiceAddressVerifiedIndicator = this.isServiceAddressVerifiedIndicator();
            strategy.appendField(locator, this, "serviceAddressVerifiedIndicator", buffer, theServiceAddressVerifiedIndicator);
        }
        {
            Name theName;
            theName = this.getName();
            strategy.appendField(locator, this, "name", buffer, theName);
        }
        {
            PortRequestAddress theAddress;
            theAddress = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theAddress);
        }
        {
            String theDslServiceCode;
            theDslServiceCode = this.getDslServiceCode();
            strategy.appendField(locator, this, "dslServiceCode", buffer, theDslServiceCode);
        }
        {
            String theAddressChangeCode;
            theAddressChangeCode = this.getAddressChangeCode();
            strategy.appendField(locator, this, "addressChangeCode", buffer, theAddressChangeCode);
        }
        {
            String theOldDSLServiceResellerName;
            theOldDSLServiceResellerName = this.getOldDSLServiceResellerName();
            strategy.appendField(locator, this, "oldDSLServiceResellerName", buffer, theOldDSLServiceResellerName);
        }
        {
            String theOldServiceProviderPIN;
            theOldServiceProviderPIN = this.getOldServiceProviderPIN();
            strategy.appendField(locator, this, "oldServiceProviderPIN", buffer, theOldServiceProviderPIN);
        }
        {
            String theOldServiceProviderAccountNumber;
            theOldServiceProviderAccountNumber = this.getOldServiceProviderAccountNumber();
            strategy.appendField(locator, this, "oldServiceProviderAccountNumber", buffer, theOldServiceProviderAccountNumber);
        }
        {
            String theOldServiceProviderESN;
            theOldServiceProviderESN = this.getOldServiceProviderESN();
            strategy.appendField(locator, this, "oldServiceProviderESN", buffer, theOldServiceProviderESN);
        }
        {
            String theSpecialInstructionsText;
            theSpecialInstructionsText = this.getSpecialInstructionsText();
            strategy.appendField(locator, this, "specialInstructionsText", buffer, theSpecialInstructionsText);
        }
        {
            String theAlternateContactNumber;
            theAlternateContactNumber = this.getAlternateContactNumber();
            strategy.appendField(locator, this, "alternateContactNumber", buffer, theAlternateContactNumber);
        }
        {
            String theAlternateContactEmailTxt;
            theAlternateContactEmailTxt = this.getAlternateContactEmailTxt();
            strategy.appendField(locator, this, "alternateContactEmailTxt", buffer, theAlternateContactEmailTxt);
        }
        {
            String thePortDirectionCode;
            thePortDirectionCode = this.getPortDirectionCode();
            strategy.appendField(locator, this, "portDirectionCode", buffer, thePortDirectionCode);
        }
        {
            Object theAutoActivateInd;
            theAutoActivateInd = this.getAutoActivateInd();
            strategy.appendField(locator, this, "autoActivateInd", buffer, theAutoActivateInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PortRequest)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PortRequest that = ((PortRequest) object);
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
            boolean lhsPortTransferAuthConsentIndicator;
            lhsPortTransferAuthConsentIndicator = (true?this.isPortTransferAuthConsentIndicator():false);
            boolean rhsPortTransferAuthConsentIndicator;
            rhsPortTransferAuthConsentIndicator = (true?that.isPortTransferAuthConsentIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portTransferAuthConsentIndicator", lhsPortTransferAuthConsentIndicator), LocatorUtils.property(thatLocator, "portTransferAuthConsentIndicator", rhsPortTransferAuthConsentIndicator), lhsPortTransferAuthConsentIndicator, rhsPortTransferAuthConsentIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsServiceAddressVerifiedIndicator;
            lhsServiceAddressVerifiedIndicator = this.isServiceAddressVerifiedIndicator();
            Boolean rhsServiceAddressVerifiedIndicator;
            rhsServiceAddressVerifiedIndicator = that.isServiceAddressVerifiedIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddressVerifiedIndicator", lhsServiceAddressVerifiedIndicator), LocatorUtils.property(thatLocator, "serviceAddressVerifiedIndicator", rhsServiceAddressVerifiedIndicator), lhsServiceAddressVerifiedIndicator, rhsServiceAddressVerifiedIndicator)) {
                return false;
            }
        }
        {
            Name lhsName;
            lhsName = this.getName();
            Name rhsName;
            rhsName = that.getName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "name", lhsName), LocatorUtils.property(thatLocator, "name", rhsName), lhsName, rhsName)) {
                return false;
            }
        }
        {
            PortRequestAddress lhsAddress;
            lhsAddress = this.getAddress();
            PortRequestAddress rhsAddress;
            rhsAddress = that.getAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "address", lhsAddress), LocatorUtils.property(thatLocator, "address", rhsAddress), lhsAddress, rhsAddress)) {
                return false;
            }
        }
        {
            String lhsDslServiceCode;
            lhsDslServiceCode = this.getDslServiceCode();
            String rhsDslServiceCode;
            rhsDslServiceCode = that.getDslServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dslServiceCode", lhsDslServiceCode), LocatorUtils.property(thatLocator, "dslServiceCode", rhsDslServiceCode), lhsDslServiceCode, rhsDslServiceCode)) {
                return false;
            }
        }
        {
            String lhsAddressChangeCode;
            lhsAddressChangeCode = this.getAddressChangeCode();
            String rhsAddressChangeCode;
            rhsAddressChangeCode = that.getAddressChangeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "addressChangeCode", lhsAddressChangeCode), LocatorUtils.property(thatLocator, "addressChangeCode", rhsAddressChangeCode), lhsAddressChangeCode, rhsAddressChangeCode)) {
                return false;
            }
        }
        {
            String lhsOldDSLServiceResellerName;
            lhsOldDSLServiceResellerName = this.getOldDSLServiceResellerName();
            String rhsOldDSLServiceResellerName;
            rhsOldDSLServiceResellerName = that.getOldDSLServiceResellerName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oldDSLServiceResellerName", lhsOldDSLServiceResellerName), LocatorUtils.property(thatLocator, "oldDSLServiceResellerName", rhsOldDSLServiceResellerName), lhsOldDSLServiceResellerName, rhsOldDSLServiceResellerName)) {
                return false;
            }
        }
        {
            String lhsOldServiceProviderPIN;
            lhsOldServiceProviderPIN = this.getOldServiceProviderPIN();
            String rhsOldServiceProviderPIN;
            rhsOldServiceProviderPIN = that.getOldServiceProviderPIN();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oldServiceProviderPIN", lhsOldServiceProviderPIN), LocatorUtils.property(thatLocator, "oldServiceProviderPIN", rhsOldServiceProviderPIN), lhsOldServiceProviderPIN, rhsOldServiceProviderPIN)) {
                return false;
            }
        }
        {
            String lhsOldServiceProviderAccountNumber;
            lhsOldServiceProviderAccountNumber = this.getOldServiceProviderAccountNumber();
            String rhsOldServiceProviderAccountNumber;
            rhsOldServiceProviderAccountNumber = that.getOldServiceProviderAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oldServiceProviderAccountNumber", lhsOldServiceProviderAccountNumber), LocatorUtils.property(thatLocator, "oldServiceProviderAccountNumber", rhsOldServiceProviderAccountNumber), lhsOldServiceProviderAccountNumber, rhsOldServiceProviderAccountNumber)) {
                return false;
            }
        }
        {
            String lhsOldServiceProviderESN;
            lhsOldServiceProviderESN = this.getOldServiceProviderESN();
            String rhsOldServiceProviderESN;
            rhsOldServiceProviderESN = that.getOldServiceProviderESN();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oldServiceProviderESN", lhsOldServiceProviderESN), LocatorUtils.property(thatLocator, "oldServiceProviderESN", rhsOldServiceProviderESN), lhsOldServiceProviderESN, rhsOldServiceProviderESN)) {
                return false;
            }
        }
        {
            String lhsSpecialInstructionsText;
            lhsSpecialInstructionsText = this.getSpecialInstructionsText();
            String rhsSpecialInstructionsText;
            rhsSpecialInstructionsText = that.getSpecialInstructionsText();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "specialInstructionsText", lhsSpecialInstructionsText), LocatorUtils.property(thatLocator, "specialInstructionsText", rhsSpecialInstructionsText), lhsSpecialInstructionsText, rhsSpecialInstructionsText)) {
                return false;
            }
        }
        {
            String lhsAlternateContactNumber;
            lhsAlternateContactNumber = this.getAlternateContactNumber();
            String rhsAlternateContactNumber;
            rhsAlternateContactNumber = that.getAlternateContactNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "alternateContactNumber", lhsAlternateContactNumber), LocatorUtils.property(thatLocator, "alternateContactNumber", rhsAlternateContactNumber), lhsAlternateContactNumber, rhsAlternateContactNumber)) {
                return false;
            }
        }
        {
            String lhsAlternateContactEmailTxt;
            lhsAlternateContactEmailTxt = this.getAlternateContactEmailTxt();
            String rhsAlternateContactEmailTxt;
            rhsAlternateContactEmailTxt = that.getAlternateContactEmailTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "alternateContactEmailTxt", lhsAlternateContactEmailTxt), LocatorUtils.property(thatLocator, "alternateContactEmailTxt", rhsAlternateContactEmailTxt), lhsAlternateContactEmailTxt, rhsAlternateContactEmailTxt)) {
                return false;
            }
        }
        {
            String lhsPortDirectionCode;
            lhsPortDirectionCode = this.getPortDirectionCode();
            String rhsPortDirectionCode;
            rhsPortDirectionCode = that.getPortDirectionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portDirectionCode", lhsPortDirectionCode), LocatorUtils.property(thatLocator, "portDirectionCode", rhsPortDirectionCode), lhsPortDirectionCode, rhsPortDirectionCode)) {
                return false;
            }
        }
        {
            Object lhsAutoActivateInd;
            lhsAutoActivateInd = this.getAutoActivateInd();
            Object rhsAutoActivateInd;
            rhsAutoActivateInd = that.getAutoActivateInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "autoActivateInd", lhsAutoActivateInd), LocatorUtils.property(thatLocator, "autoActivateInd", rhsAutoActivateInd), lhsAutoActivateInd, rhsAutoActivateInd)) {
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
