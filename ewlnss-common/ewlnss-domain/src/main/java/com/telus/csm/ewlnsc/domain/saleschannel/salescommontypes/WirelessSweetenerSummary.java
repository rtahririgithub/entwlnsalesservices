
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessSweetenerSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessSweetenerSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sweetenerId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sweetenerCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sweetenerVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sweetenerDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2"/>
 *         &lt;element name="eligibleDeviceTypeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="10"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="eligibleTermList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="5"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessSweetenerSummary", propOrder = {
    "sweetenerId",
    "sweetenerCode",
    "sweetenerVersion",
    "sweetenerDescriptionList",
    "eligibleDeviceTypeList",
    "effectiveDate",
    "expiryDate",
    "eligibleTermList"
})
@XmlSeeAlso({
    WirelessSweetenerDetail.class
})
public class WirelessSweetenerSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String sweetenerId;
    @XmlElement(required = true)
    protected String sweetenerCode;
    protected String sweetenerVersion;
    @XmlElement(required = true)
    protected List<Description> sweetenerDescriptionList;
    @XmlElement(required = true)
    protected List<String> eligibleDeviceTypeList;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date effectiveDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date expiryDate;
    @XmlElement(required = true)
    protected List<BigInteger> eligibleTermList;

    /**
     * Gets the value of the sweetenerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSweetenerId() {
        return sweetenerId;
    }

    /**
     * Sets the value of the sweetenerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSweetenerId(String value) {
        this.sweetenerId = value;
    }

    /**
     * Gets the value of the sweetenerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSweetenerCode() {
        return sweetenerCode;
    }

    /**
     * Sets the value of the sweetenerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSweetenerCode(String value) {
        this.sweetenerCode = value;
    }

    /**
     * Gets the value of the sweetenerVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSweetenerVersion() {
        return sweetenerVersion;
    }

    /**
     * Sets the value of the sweetenerVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSweetenerVersion(String value) {
        this.sweetenerVersion = value;
    }

    /**
     * Gets the value of the sweetenerDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the sweetenerDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSweetenerDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getSweetenerDescriptionList() {
        if (sweetenerDescriptionList == null) {
            sweetenerDescriptionList = new ArrayList<Description>();
        }
        return this.sweetenerDescriptionList;
    }

    /**
     * Gets the value of the eligibleDeviceTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibleDeviceTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibleDeviceTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEligibleDeviceTypeList() {
        if (eligibleDeviceTypeList == null) {
            eligibleDeviceTypeList = new ArrayList<String>();
        }
        return this.eligibleDeviceTypeList;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveDate(Date value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryDate(Date value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the eligibleTermList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibleTermList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibleTermList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getEligibleTermList() {
        if (eligibleTermList == null) {
            eligibleTermList = new ArrayList<BigInteger>();
        }
        return this.eligibleTermList;
    }

    /**
     * Sets the value of the sweetenerDescriptionList property.
     * 
     * @param sweetenerDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setSweetenerDescriptionList(List<Description> sweetenerDescriptionList) {
        this.sweetenerDescriptionList = sweetenerDescriptionList;
    }

    /**
     * Sets the value of the eligibleDeviceTypeList property.
     * 
     * @param eligibleDeviceTypeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibleDeviceTypeList(List<String> eligibleDeviceTypeList) {
        this.eligibleDeviceTypeList = eligibleDeviceTypeList;
    }

    /**
     * Sets the value of the eligibleTermList property.
     * 
     * @param eligibleTermList
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setEligibleTermList(List<BigInteger> eligibleTermList) {
        this.eligibleTermList = eligibleTermList;
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
            String theSweetenerId;
            theSweetenerId = this.getSweetenerId();
            strategy.appendField(locator, this, "sweetenerId", buffer, theSweetenerId);
        }
        {
            String theSweetenerCode;
            theSweetenerCode = this.getSweetenerCode();
            strategy.appendField(locator, this, "sweetenerCode", buffer, theSweetenerCode);
        }
        {
            String theSweetenerVersion;
            theSweetenerVersion = this.getSweetenerVersion();
            strategy.appendField(locator, this, "sweetenerVersion", buffer, theSweetenerVersion);
        }
        {
            List<Description> theSweetenerDescriptionList;
            theSweetenerDescriptionList = (((this.sweetenerDescriptionList!= null)&&(!this.sweetenerDescriptionList.isEmpty()))?this.getSweetenerDescriptionList():null);
            strategy.appendField(locator, this, "sweetenerDescriptionList", buffer, theSweetenerDescriptionList);
        }
        {
            List<String> theEligibleDeviceTypeList;
            theEligibleDeviceTypeList = (((this.eligibleDeviceTypeList!= null)&&(!this.eligibleDeviceTypeList.isEmpty()))?this.getEligibleDeviceTypeList():null);
            strategy.appendField(locator, this, "eligibleDeviceTypeList", buffer, theEligibleDeviceTypeList);
        }
        {
            Date theEffectiveDate;
            theEffectiveDate = this.getEffectiveDate();
            strategy.appendField(locator, this, "effectiveDate", buffer, theEffectiveDate);
        }
        {
            Date theExpiryDate;
            theExpiryDate = this.getExpiryDate();
            strategy.appendField(locator, this, "expiryDate", buffer, theExpiryDate);
        }
        {
            List<BigInteger> theEligibleTermList;
            theEligibleTermList = (((this.eligibleTermList!= null)&&(!this.eligibleTermList.isEmpty()))?this.getEligibleTermList():null);
            strategy.appendField(locator, this, "eligibleTermList", buffer, theEligibleTermList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessSweetenerSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessSweetenerSummary that = ((WirelessSweetenerSummary) object);
        {
            String lhsSweetenerId;
            lhsSweetenerId = this.getSweetenerId();
            String rhsSweetenerId;
            rhsSweetenerId = that.getSweetenerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerId", lhsSweetenerId), LocatorUtils.property(thatLocator, "sweetenerId", rhsSweetenerId), lhsSweetenerId, rhsSweetenerId)) {
                return false;
            }
        }
        {
            String lhsSweetenerCode;
            lhsSweetenerCode = this.getSweetenerCode();
            String rhsSweetenerCode;
            rhsSweetenerCode = that.getSweetenerCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerCode", lhsSweetenerCode), LocatorUtils.property(thatLocator, "sweetenerCode", rhsSweetenerCode), lhsSweetenerCode, rhsSweetenerCode)) {
                return false;
            }
        }
        {
            String lhsSweetenerVersion;
            lhsSweetenerVersion = this.getSweetenerVersion();
            String rhsSweetenerVersion;
            rhsSweetenerVersion = that.getSweetenerVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerVersion", lhsSweetenerVersion), LocatorUtils.property(thatLocator, "sweetenerVersion", rhsSweetenerVersion), lhsSweetenerVersion, rhsSweetenerVersion)) {
                return false;
            }
        }
        {
            List<Description> lhsSweetenerDescriptionList;
            lhsSweetenerDescriptionList = (((this.sweetenerDescriptionList!= null)&&(!this.sweetenerDescriptionList.isEmpty()))?this.getSweetenerDescriptionList():null);
            List<Description> rhsSweetenerDescriptionList;
            rhsSweetenerDescriptionList = (((that.sweetenerDescriptionList!= null)&&(!that.sweetenerDescriptionList.isEmpty()))?that.getSweetenerDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sweetenerDescriptionList", lhsSweetenerDescriptionList), LocatorUtils.property(thatLocator, "sweetenerDescriptionList", rhsSweetenerDescriptionList), lhsSweetenerDescriptionList, rhsSweetenerDescriptionList)) {
                return false;
            }
        }
        {
            List<String> lhsEligibleDeviceTypeList;
            lhsEligibleDeviceTypeList = (((this.eligibleDeviceTypeList!= null)&&(!this.eligibleDeviceTypeList.isEmpty()))?this.getEligibleDeviceTypeList():null);
            List<String> rhsEligibleDeviceTypeList;
            rhsEligibleDeviceTypeList = (((that.eligibleDeviceTypeList!= null)&&(!that.eligibleDeviceTypeList.isEmpty()))?that.getEligibleDeviceTypeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleDeviceTypeList", lhsEligibleDeviceTypeList), LocatorUtils.property(thatLocator, "eligibleDeviceTypeList", rhsEligibleDeviceTypeList), lhsEligibleDeviceTypeList, rhsEligibleDeviceTypeList)) {
                return false;
            }
        }
        {
            Date lhsEffectiveDate;
            lhsEffectiveDate = this.getEffectiveDate();
            Date rhsEffectiveDate;
            rhsEffectiveDate = that.getEffectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveDate", lhsEffectiveDate), LocatorUtils.property(thatLocator, "effectiveDate", rhsEffectiveDate), lhsEffectiveDate, rhsEffectiveDate)) {
                return false;
            }
        }
        {
            Date lhsExpiryDate;
            lhsExpiryDate = this.getExpiryDate();
            Date rhsExpiryDate;
            rhsExpiryDate = that.getExpiryDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "expiryDate", lhsExpiryDate), LocatorUtils.property(thatLocator, "expiryDate", rhsExpiryDate), lhsExpiryDate, rhsExpiryDate)) {
                return false;
            }
        }
        {
            List<BigInteger> lhsEligibleTermList;
            lhsEligibleTermList = (((this.eligibleTermList!= null)&&(!this.eligibleTermList.isEmpty()))?this.getEligibleTermList():null);
            List<BigInteger> rhsEligibleTermList;
            rhsEligibleTermList = (((that.eligibleTermList!= null)&&(!that.eligibleTermList.isEmpty()))?that.getEligibleTermList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleTermList", lhsEligibleTermList), LocatorUtils.property(thatLocator, "eligibleTermList", rhsEligibleTermList), lhsEligibleTermList, rhsEligibleTermList)) {
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
