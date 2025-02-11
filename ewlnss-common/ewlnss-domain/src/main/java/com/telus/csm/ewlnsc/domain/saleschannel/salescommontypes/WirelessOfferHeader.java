
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessOfferHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerCodeAliasName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerProgramId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipmentContextIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="offerFilter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessOfferFilter" minOccurs="0"/>
 *         &lt;element name="assignedOfferIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="customerAssignedOfferId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipmentPromotionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equipmentPromotionVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perspectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="selectedCoupon" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CouponIdentifier" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessOfferHeader", propOrder = {
    "offerId",
    "offerCode",
    "offerCodeAliasName",
    "offerProgramId",
    "systemID",
    "equipmentContextIndicator",
    "offerFilter",
    "assignedOfferIndicator",
    "customerAssignedOfferId",
    "equipmentPromotionCd",
    "equipmentPromotionVersion",
    "perspectiveDate",
    "selectedCoupon"
})
public class WirelessOfferHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String offerId;
    protected String offerCode;
    protected String offerCodeAliasName;
    protected String offerProgramId;
    protected String systemID;
    protected Boolean equipmentContextIndicator;
    protected WirelessOfferFilter offerFilter;
    protected Boolean assignedOfferIndicator;
    protected String customerAssignedOfferId;
    protected String equipmentPromotionCd;
    protected String equipmentPromotionVersion;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date perspectiveDate;
    protected CouponIdentifier selectedCoupon;

    /**
     * Gets the value of the offerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferId() {
        return offerId;
    }

    /**
     * Sets the value of the offerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferId(String value) {
        this.offerId = value;
    }

    /**
     * Gets the value of the offerCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferCode() {
        return offerCode;
    }

    /**
     * Sets the value of the offerCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferCode(String value) {
        this.offerCode = value;
    }

    /**
     * Gets the value of the offerCodeAliasName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferCodeAliasName() {
        return offerCodeAliasName;
    }

    /**
     * Sets the value of the offerCodeAliasName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferCodeAliasName(String value) {
        this.offerCodeAliasName = value;
    }

    /**
     * Gets the value of the offerProgramId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferProgramId() {
        return offerProgramId;
    }

    /**
     * Sets the value of the offerProgramId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferProgramId(String value) {
        this.offerProgramId = value;
    }

    /**
     * Gets the value of the systemID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemID() {
        return systemID;
    }

    /**
     * Sets the value of the systemID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemID(String value) {
        this.systemID = value;
    }

    /**
     * Gets the value of the equipmentContextIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEquipmentContextIndicator() {
        return equipmentContextIndicator;
    }

    /**
     * Sets the value of the equipmentContextIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEquipmentContextIndicator(Boolean value) {
        this.equipmentContextIndicator = value;
    }

    /**
     * Gets the value of the offerFilter property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessOfferFilter }
     *     
     */
    public WirelessOfferFilter getOfferFilter() {
        return offerFilter;
    }

    /**
     * Sets the value of the offerFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessOfferFilter }
     *     
     */
    public void setOfferFilter(WirelessOfferFilter value) {
        this.offerFilter = value;
    }

    /**
     * Gets the value of the assignedOfferIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssignedOfferIndicator() {
        return assignedOfferIndicator;
    }

    /**
     * Sets the value of the assignedOfferIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssignedOfferIndicator(Boolean value) {
        this.assignedOfferIndicator = value;
    }

    /**
     * Gets the value of the customerAssignedOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerAssignedOfferId() {
        return customerAssignedOfferId;
    }

    /**
     * Sets the value of the customerAssignedOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerAssignedOfferId(String value) {
        this.customerAssignedOfferId = value;
    }

    /**
     * Gets the value of the equipmentPromotionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentPromotionCd() {
        return equipmentPromotionCd;
    }

    /**
     * Sets the value of the equipmentPromotionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentPromotionCd(String value) {
        this.equipmentPromotionCd = value;
    }

    /**
     * Gets the value of the equipmentPromotionVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEquipmentPromotionVersion() {
        return equipmentPromotionVersion;
    }

    /**
     * Sets the value of the equipmentPromotionVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEquipmentPromotionVersion(String value) {
        this.equipmentPromotionVersion = value;
    }

    /**
     * Gets the value of the perspectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPerspectiveDate() {
        return perspectiveDate;
    }

    /**
     * Sets the value of the perspectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerspectiveDate(Date value) {
        this.perspectiveDate = value;
    }

    /**
     * Gets the value of the selectedCoupon property.
     * 
     * @return
     *     possible object is
     *     {@link CouponIdentifier }
     *     
     */
    public CouponIdentifier getSelectedCoupon() {
        return selectedCoupon;
    }

    /**
     * Sets the value of the selectedCoupon property.
     * 
     * @param value
     *     allowed object is
     *     {@link CouponIdentifier }
     *     
     */
    public void setSelectedCoupon(CouponIdentifier value) {
        this.selectedCoupon = value;
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
            String theOfferId;
            theOfferId = this.getOfferId();
            strategy.appendField(locator, this, "offerId", buffer, theOfferId);
        }
        {
            String theOfferCode;
            theOfferCode = this.getOfferCode();
            strategy.appendField(locator, this, "offerCode", buffer, theOfferCode);
        }
        {
            String theOfferCodeAliasName;
            theOfferCodeAliasName = this.getOfferCodeAliasName();
            strategy.appendField(locator, this, "offerCodeAliasName", buffer, theOfferCodeAliasName);
        }
        {
            String theOfferProgramId;
            theOfferProgramId = this.getOfferProgramId();
            strategy.appendField(locator, this, "offerProgramId", buffer, theOfferProgramId);
        }
        {
            String theSystemID;
            theSystemID = this.getSystemID();
            strategy.appendField(locator, this, "systemID", buffer, theSystemID);
        }
        {
            Boolean theEquipmentContextIndicator;
            theEquipmentContextIndicator = this.isEquipmentContextIndicator();
            strategy.appendField(locator, this, "equipmentContextIndicator", buffer, theEquipmentContextIndicator);
        }
        {
            WirelessOfferFilter theOfferFilter;
            theOfferFilter = this.getOfferFilter();
            strategy.appendField(locator, this, "offerFilter", buffer, theOfferFilter);
        }
        {
            Boolean theAssignedOfferIndicator;
            theAssignedOfferIndicator = this.isAssignedOfferIndicator();
            strategy.appendField(locator, this, "assignedOfferIndicator", buffer, theAssignedOfferIndicator);
        }
        {
            String theCustomerAssignedOfferId;
            theCustomerAssignedOfferId = this.getCustomerAssignedOfferId();
            strategy.appendField(locator, this, "customerAssignedOfferId", buffer, theCustomerAssignedOfferId);
        }
        {
            String theEquipmentPromotionCd;
            theEquipmentPromotionCd = this.getEquipmentPromotionCd();
            strategy.appendField(locator, this, "equipmentPromotionCd", buffer, theEquipmentPromotionCd);
        }
        {
            String theEquipmentPromotionVersion;
            theEquipmentPromotionVersion = this.getEquipmentPromotionVersion();
            strategy.appendField(locator, this, "equipmentPromotionVersion", buffer, theEquipmentPromotionVersion);
        }
        {
            Date thePerspectiveDate;
            thePerspectiveDate = this.getPerspectiveDate();
            strategy.appendField(locator, this, "perspectiveDate", buffer, thePerspectiveDate);
        }
        {
            CouponIdentifier theSelectedCoupon;
            theSelectedCoupon = this.getSelectedCoupon();
            strategy.appendField(locator, this, "selectedCoupon", buffer, theSelectedCoupon);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessOfferHeader that = ((WirelessOfferHeader) object);
        {
            String lhsOfferId;
            lhsOfferId = this.getOfferId();
            String rhsOfferId;
            rhsOfferId = that.getOfferId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerId", lhsOfferId), LocatorUtils.property(thatLocator, "offerId", rhsOfferId), lhsOfferId, rhsOfferId)) {
                return false;
            }
        }
        {
            String lhsOfferCode;
            lhsOfferCode = this.getOfferCode();
            String rhsOfferCode;
            rhsOfferCode = that.getOfferCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCode", lhsOfferCode), LocatorUtils.property(thatLocator, "offerCode", rhsOfferCode), lhsOfferCode, rhsOfferCode)) {
                return false;
            }
        }
        {
            String lhsOfferCodeAliasName;
            lhsOfferCodeAliasName = this.getOfferCodeAliasName();
            String rhsOfferCodeAliasName;
            rhsOfferCodeAliasName = that.getOfferCodeAliasName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCodeAliasName", lhsOfferCodeAliasName), LocatorUtils.property(thatLocator, "offerCodeAliasName", rhsOfferCodeAliasName), lhsOfferCodeAliasName, rhsOfferCodeAliasName)) {
                return false;
            }
        }
        {
            String lhsOfferProgramId;
            lhsOfferProgramId = this.getOfferProgramId();
            String rhsOfferProgramId;
            rhsOfferProgramId = that.getOfferProgramId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProgramId", lhsOfferProgramId), LocatorUtils.property(thatLocator, "offerProgramId", rhsOfferProgramId), lhsOfferProgramId, rhsOfferProgramId)) {
                return false;
            }
        }
        {
            String lhsSystemID;
            lhsSystemID = this.getSystemID();
            String rhsSystemID;
            rhsSystemID = that.getSystemID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "systemID", lhsSystemID), LocatorUtils.property(thatLocator, "systemID", rhsSystemID), lhsSystemID, rhsSystemID)) {
                return false;
            }
        }
        {
            Boolean lhsEquipmentContextIndicator;
            lhsEquipmentContextIndicator = this.isEquipmentContextIndicator();
            Boolean rhsEquipmentContextIndicator;
            rhsEquipmentContextIndicator = that.isEquipmentContextIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentContextIndicator", lhsEquipmentContextIndicator), LocatorUtils.property(thatLocator, "equipmentContextIndicator", rhsEquipmentContextIndicator), lhsEquipmentContextIndicator, rhsEquipmentContextIndicator)) {
                return false;
            }
        }
        {
            WirelessOfferFilter lhsOfferFilter;
            lhsOfferFilter = this.getOfferFilter();
            WirelessOfferFilter rhsOfferFilter;
            rhsOfferFilter = that.getOfferFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerFilter", lhsOfferFilter), LocatorUtils.property(thatLocator, "offerFilter", rhsOfferFilter), lhsOfferFilter, rhsOfferFilter)) {
                return false;
            }
        }
        {
            Boolean lhsAssignedOfferIndicator;
            lhsAssignedOfferIndicator = this.isAssignedOfferIndicator();
            Boolean rhsAssignedOfferIndicator;
            rhsAssignedOfferIndicator = that.isAssignedOfferIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assignedOfferIndicator", lhsAssignedOfferIndicator), LocatorUtils.property(thatLocator, "assignedOfferIndicator", rhsAssignedOfferIndicator), lhsAssignedOfferIndicator, rhsAssignedOfferIndicator)) {
                return false;
            }
        }
        {
            String lhsCustomerAssignedOfferId;
            lhsCustomerAssignedOfferId = this.getCustomerAssignedOfferId();
            String rhsCustomerAssignedOfferId;
            rhsCustomerAssignedOfferId = that.getCustomerAssignedOfferId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerAssignedOfferId", lhsCustomerAssignedOfferId), LocatorUtils.property(thatLocator, "customerAssignedOfferId", rhsCustomerAssignedOfferId), lhsCustomerAssignedOfferId, rhsCustomerAssignedOfferId)) {
                return false;
            }
        }
        {
            String lhsEquipmentPromotionCd;
            lhsEquipmentPromotionCd = this.getEquipmentPromotionCd();
            String rhsEquipmentPromotionCd;
            rhsEquipmentPromotionCd = that.getEquipmentPromotionCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentPromotionCd", lhsEquipmentPromotionCd), LocatorUtils.property(thatLocator, "equipmentPromotionCd", rhsEquipmentPromotionCd), lhsEquipmentPromotionCd, rhsEquipmentPromotionCd)) {
                return false;
            }
        }
        {
            String lhsEquipmentPromotionVersion;
            lhsEquipmentPromotionVersion = this.getEquipmentPromotionVersion();
            String rhsEquipmentPromotionVersion;
            rhsEquipmentPromotionVersion = that.getEquipmentPromotionVersion();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentPromotionVersion", lhsEquipmentPromotionVersion), LocatorUtils.property(thatLocator, "equipmentPromotionVersion", rhsEquipmentPromotionVersion), lhsEquipmentPromotionVersion, rhsEquipmentPromotionVersion)) {
                return false;
            }
        }
        {
            Date lhsPerspectiveDate;
            lhsPerspectiveDate = this.getPerspectiveDate();
            Date rhsPerspectiveDate;
            rhsPerspectiveDate = that.getPerspectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perspectiveDate", lhsPerspectiveDate), LocatorUtils.property(thatLocator, "perspectiveDate", rhsPerspectiveDate), lhsPerspectiveDate, rhsPerspectiveDate)) {
                return false;
            }
        }
        {
            CouponIdentifier lhsSelectedCoupon;
            lhsSelectedCoupon = this.getSelectedCoupon();
            CouponIdentifier rhsSelectedCoupon;
            rhsSelectedCoupon = that.getSelectedCoupon();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedCoupon", lhsSelectedCoupon), LocatorUtils.property(thatLocator, "selectedCoupon", rhsSelectedCoupon), lhsSelectedCoupon, rhsSelectedCoupon)) {
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
