
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for EquipmentSerialNumberSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipmentSerialNumberSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="newSimInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="simProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="simEquipmentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="eSimProfileInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="handsetSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handsetEquipmentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dummyESNInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="handsetProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handsetProductDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="networkTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smartHubDeclineInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="voipDeviceDeclineInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="pairableDeviceInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipmentSerialNumberSet", propOrder = {
    "eId",
    "simSerialNumber",
    "newSimInd",
    "simProductCd",
    "simEquipmentType",
    "eSimProfileInd",
    "handsetSerialNumber",
    "handsetEquipmentType",
    "dummyESNInd",
    "handsetProductCd",
    "handsetProductDescription",
    "networkTypeCode",
    "smartHubDeclineInd",
    "voipDeviceDeclineInd",
    "pairableDeviceInd"
})
@XmlSeeAlso({
    EquipmentSerialNumberDescriptionSetType.class
})
public class EquipmentSerialNumberSet
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String eId;
    protected String simSerialNumber;
    protected Boolean newSimInd;
    protected String simProductCd;
    protected String simEquipmentType;
    protected Boolean eSimProfileInd;
    protected String handsetSerialNumber;
    protected String handsetEquipmentType;
    protected Boolean dummyESNInd;
    protected String handsetProductCd;
    protected MultilingualDescriptionList handsetProductDescription;
    protected String networkTypeCode;
    protected Boolean smartHubDeclineInd;
    protected Boolean voipDeviceDeclineInd;
    protected Boolean pairableDeviceInd;

    /**
     * Gets the value of the eId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEId() {
        return eId;
    }

    /**
     * Sets the value of the eId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEId(String value) {
        this.eId = value;
    }

    /**
     * Gets the value of the simSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimSerialNumber() {
        return simSerialNumber;
    }

    /**
     * Sets the value of the simSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimSerialNumber(String value) {
        this.simSerialNumber = value;
    }

    /**
     * Gets the value of the newSimInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNewSimInd() {
        return newSimInd;
    }

    /**
     * Sets the value of the newSimInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNewSimInd(Boolean value) {
        this.newSimInd = value;
    }

    /**
     * Gets the value of the simProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimProductCd() {
        return simProductCd;
    }

    /**
     * Sets the value of the simProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimProductCd(String value) {
        this.simProductCd = value;
    }

    /**
     * Gets the value of the simEquipmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSimEquipmentType() {
        return simEquipmentType;
    }

    /**
     * Sets the value of the simEquipmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSimEquipmentType(String value) {
        this.simEquipmentType = value;
    }

    /**
     * Gets the value of the eSimProfileInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isESimProfileInd() {
        return eSimProfileInd;
    }

    /**
     * Sets the value of the eSimProfileInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setESimProfileInd(Boolean value) {
        this.eSimProfileInd = value;
    }

    /**
     * Gets the value of the handsetSerialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetSerialNumber() {
        return handsetSerialNumber;
    }

    /**
     * Sets the value of the handsetSerialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetSerialNumber(String value) {
        this.handsetSerialNumber = value;
    }

    /**
     * Gets the value of the handsetEquipmentType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetEquipmentType() {
        return handsetEquipmentType;
    }

    /**
     * Sets the value of the handsetEquipmentType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetEquipmentType(String value) {
        this.handsetEquipmentType = value;
    }

    /**
     * Gets the value of the dummyESNInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDummyESNInd() {
        return dummyESNInd;
    }

    /**
     * Sets the value of the dummyESNInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDummyESNInd(Boolean value) {
        this.dummyESNInd = value;
    }

    /**
     * Gets the value of the handsetProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetProductCd() {
        return handsetProductCd;
    }

    /**
     * Sets the value of the handsetProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetProductCd(String value) {
        this.handsetProductCd = value;
    }

    /**
     * Gets the value of the handsetProductDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getHandsetProductDescription() {
        return handsetProductDescription;
    }

    /**
     * Sets the value of the handsetProductDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setHandsetProductDescription(MultilingualDescriptionList value) {
        this.handsetProductDescription = value;
    }

    /**
     * Gets the value of the networkTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkTypeCode() {
        return networkTypeCode;
    }

    /**
     * Sets the value of the networkTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkTypeCode(String value) {
        this.networkTypeCode = value;
    }

    /**
     * Gets the value of the smartHubDeclineInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSmartHubDeclineInd() {
        return smartHubDeclineInd;
    }

    /**
     * Sets the value of the smartHubDeclineInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSmartHubDeclineInd(Boolean value) {
        this.smartHubDeclineInd = value;
    }

    /**
     * Gets the value of the voipDeviceDeclineInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isVoipDeviceDeclineInd() {
        return voipDeviceDeclineInd;
    }

    /**
     * Sets the value of the voipDeviceDeclineInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setVoipDeviceDeclineInd(Boolean value) {
        this.voipDeviceDeclineInd = value;
    }

    /**
     * Gets the value of the pairableDeviceInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPairableDeviceInd() {
        return pairableDeviceInd;
    }

    /**
     * Sets the value of the pairableDeviceInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPairableDeviceInd(Boolean value) {
        this.pairableDeviceInd = value;
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
            String theEId;
            theEId = this.getEId();
            strategy.appendField(locator, this, "eId", buffer, theEId);
        }
        {
            String theSimSerialNumber;
            theSimSerialNumber = this.getSimSerialNumber();
            strategy.appendField(locator, this, "simSerialNumber", buffer, theSimSerialNumber);
        }
        {
            Boolean theNewSimInd;
            theNewSimInd = this.isNewSimInd();
            strategy.appendField(locator, this, "newSimInd", buffer, theNewSimInd);
        }
        {
            String theSimProductCd;
            theSimProductCd = this.getSimProductCd();
            strategy.appendField(locator, this, "simProductCd", buffer, theSimProductCd);
        }
        {
            String theSimEquipmentType;
            theSimEquipmentType = this.getSimEquipmentType();
            strategy.appendField(locator, this, "simEquipmentType", buffer, theSimEquipmentType);
        }
        {
            Boolean theESimProfileInd;
            theESimProfileInd = this.isESimProfileInd();
            strategy.appendField(locator, this, "eSimProfileInd", buffer, theESimProfileInd);
        }
        {
            String theHandsetSerialNumber;
            theHandsetSerialNumber = this.getHandsetSerialNumber();
            strategy.appendField(locator, this, "handsetSerialNumber", buffer, theHandsetSerialNumber);
        }
        {
            String theHandsetEquipmentType;
            theHandsetEquipmentType = this.getHandsetEquipmentType();
            strategy.appendField(locator, this, "handsetEquipmentType", buffer, theHandsetEquipmentType);
        }
        {
            Boolean theDummyESNInd;
            theDummyESNInd = this.isDummyESNInd();
            strategy.appendField(locator, this, "dummyESNInd", buffer, theDummyESNInd);
        }
        {
            String theHandsetProductCd;
            theHandsetProductCd = this.getHandsetProductCd();
            strategy.appendField(locator, this, "handsetProductCd", buffer, theHandsetProductCd);
        }
        {
            MultilingualDescriptionList theHandsetProductDescription;
            theHandsetProductDescription = this.getHandsetProductDescription();
            strategy.appendField(locator, this, "handsetProductDescription", buffer, theHandsetProductDescription);
        }
        {
            String theNetworkTypeCode;
            theNetworkTypeCode = this.getNetworkTypeCode();
            strategy.appendField(locator, this, "networkTypeCode", buffer, theNetworkTypeCode);
        }
        {
            Boolean theSmartHubDeclineInd;
            theSmartHubDeclineInd = this.isSmartHubDeclineInd();
            strategy.appendField(locator, this, "smartHubDeclineInd", buffer, theSmartHubDeclineInd);
        }
        {
            Boolean theVoipDeviceDeclineInd;
            theVoipDeviceDeclineInd = this.isVoipDeviceDeclineInd();
            strategy.appendField(locator, this, "voipDeviceDeclineInd", buffer, theVoipDeviceDeclineInd);
        }
        {
            Boolean thePairableDeviceInd;
            thePairableDeviceInd = this.isPairableDeviceInd();
            strategy.appendField(locator, this, "pairableDeviceInd", buffer, thePairableDeviceInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EquipmentSerialNumberSet)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EquipmentSerialNumberSet that = ((EquipmentSerialNumberSet) object);
        {
            String lhsEId;
            lhsEId = this.getEId();
            String rhsEId;
            rhsEId = that.getEId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eId", lhsEId), LocatorUtils.property(thatLocator, "eId", rhsEId), lhsEId, rhsEId)) {
                return false;
            }
        }
        {
            String lhsSimSerialNumber;
            lhsSimSerialNumber = this.getSimSerialNumber();
            String rhsSimSerialNumber;
            rhsSimSerialNumber = that.getSimSerialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simSerialNumber", lhsSimSerialNumber), LocatorUtils.property(thatLocator, "simSerialNumber", rhsSimSerialNumber), lhsSimSerialNumber, rhsSimSerialNumber)) {
                return false;
            }
        }
        {
            Boolean lhsNewSimInd;
            lhsNewSimInd = this.isNewSimInd();
            Boolean rhsNewSimInd;
            rhsNewSimInd = that.isNewSimInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newSimInd", lhsNewSimInd), LocatorUtils.property(thatLocator, "newSimInd", rhsNewSimInd), lhsNewSimInd, rhsNewSimInd)) {
                return false;
            }
        }
        {
            String lhsSimProductCd;
            lhsSimProductCd = this.getSimProductCd();
            String rhsSimProductCd;
            rhsSimProductCd = that.getSimProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simProductCd", lhsSimProductCd), LocatorUtils.property(thatLocator, "simProductCd", rhsSimProductCd), lhsSimProductCd, rhsSimProductCd)) {
                return false;
            }
        }
        {
            String lhsSimEquipmentType;
            lhsSimEquipmentType = this.getSimEquipmentType();
            String rhsSimEquipmentType;
            rhsSimEquipmentType = that.getSimEquipmentType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simEquipmentType", lhsSimEquipmentType), LocatorUtils.property(thatLocator, "simEquipmentType", rhsSimEquipmentType), lhsSimEquipmentType, rhsSimEquipmentType)) {
                return false;
            }
        }
        {
            Boolean lhsESimProfileInd;
            lhsESimProfileInd = this.isESimProfileInd();
            Boolean rhsESimProfileInd;
            rhsESimProfileInd = that.isESimProfileInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eSimProfileInd", lhsESimProfileInd), LocatorUtils.property(thatLocator, "eSimProfileInd", rhsESimProfileInd), lhsESimProfileInd, rhsESimProfileInd)) {
                return false;
            }
        }
        {
            String lhsHandsetSerialNumber;
            lhsHandsetSerialNumber = this.getHandsetSerialNumber();
            String rhsHandsetSerialNumber;
            rhsHandsetSerialNumber = that.getHandsetSerialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetSerialNumber", lhsHandsetSerialNumber), LocatorUtils.property(thatLocator, "handsetSerialNumber", rhsHandsetSerialNumber), lhsHandsetSerialNumber, rhsHandsetSerialNumber)) {
                return false;
            }
        }
        {
            String lhsHandsetEquipmentType;
            lhsHandsetEquipmentType = this.getHandsetEquipmentType();
            String rhsHandsetEquipmentType;
            rhsHandsetEquipmentType = that.getHandsetEquipmentType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetEquipmentType", lhsHandsetEquipmentType), LocatorUtils.property(thatLocator, "handsetEquipmentType", rhsHandsetEquipmentType), lhsHandsetEquipmentType, rhsHandsetEquipmentType)) {
                return false;
            }
        }
        {
            Boolean lhsDummyESNInd;
            lhsDummyESNInd = this.isDummyESNInd();
            Boolean rhsDummyESNInd;
            rhsDummyESNInd = that.isDummyESNInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dummyESNInd", lhsDummyESNInd), LocatorUtils.property(thatLocator, "dummyESNInd", rhsDummyESNInd), lhsDummyESNInd, rhsDummyESNInd)) {
                return false;
            }
        }
        {
            String lhsHandsetProductCd;
            lhsHandsetProductCd = this.getHandsetProductCd();
            String rhsHandsetProductCd;
            rhsHandsetProductCd = that.getHandsetProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetProductCd", lhsHandsetProductCd), LocatorUtils.property(thatLocator, "handsetProductCd", rhsHandsetProductCd), lhsHandsetProductCd, rhsHandsetProductCd)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsHandsetProductDescription;
            lhsHandsetProductDescription = this.getHandsetProductDescription();
            MultilingualDescriptionList rhsHandsetProductDescription;
            rhsHandsetProductDescription = that.getHandsetProductDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetProductDescription", lhsHandsetProductDescription), LocatorUtils.property(thatLocator, "handsetProductDescription", rhsHandsetProductDescription), lhsHandsetProductDescription, rhsHandsetProductDescription)) {
                return false;
            }
        }
        {
            String lhsNetworkTypeCode;
            lhsNetworkTypeCode = this.getNetworkTypeCode();
            String rhsNetworkTypeCode;
            rhsNetworkTypeCode = that.getNetworkTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "networkTypeCode", lhsNetworkTypeCode), LocatorUtils.property(thatLocator, "networkTypeCode", rhsNetworkTypeCode), lhsNetworkTypeCode, rhsNetworkTypeCode)) {
                return false;
            }
        }
        {
            Boolean lhsSmartHubDeclineInd;
            lhsSmartHubDeclineInd = this.isSmartHubDeclineInd();
            Boolean rhsSmartHubDeclineInd;
            rhsSmartHubDeclineInd = that.isSmartHubDeclineInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "smartHubDeclineInd", lhsSmartHubDeclineInd), LocatorUtils.property(thatLocator, "smartHubDeclineInd", rhsSmartHubDeclineInd), lhsSmartHubDeclineInd, rhsSmartHubDeclineInd)) {
                return false;
            }
        }
        {
            Boolean lhsVoipDeviceDeclineInd;
            lhsVoipDeviceDeclineInd = this.isVoipDeviceDeclineInd();
            Boolean rhsVoipDeviceDeclineInd;
            rhsVoipDeviceDeclineInd = that.isVoipDeviceDeclineInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "voipDeviceDeclineInd", lhsVoipDeviceDeclineInd), LocatorUtils.property(thatLocator, "voipDeviceDeclineInd", rhsVoipDeviceDeclineInd), lhsVoipDeviceDeclineInd, rhsVoipDeviceDeclineInd)) {
                return false;
            }
        }
        {
            Boolean lhsPairableDeviceInd;
            lhsPairableDeviceInd = this.isPairableDeviceInd();
            Boolean rhsPairableDeviceInd;
            rhsPairableDeviceInd = that.isPairableDeviceInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pairableDeviceInd", lhsPairableDeviceInd), LocatorUtils.property(thatLocator, "pairableDeviceInd", rhsPairableDeviceInd), lhsPairableDeviceInd, rhsPairableDeviceInd)) {
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
