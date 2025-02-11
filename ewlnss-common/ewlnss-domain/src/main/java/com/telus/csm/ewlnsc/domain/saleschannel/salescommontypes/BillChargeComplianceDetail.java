
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
 * <p>Java class for BillChargeComplianceDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillChargeComplianceDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="complianceZoneCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="complianceInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="consentRequiredThresholdAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="zoneServiceItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceItem" maxOccurs="50" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillChargeComplianceDetail", propOrder = {
    "complianceZoneCd",
    "complianceInd",
    "consentRequiredThresholdAmt",
    "zoneServiceItemList"
})
public class BillChargeComplianceDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String complianceZoneCd;
    protected boolean complianceInd;
    protected double consentRequiredThresholdAmt;
    protected List<ServiceItem> zoneServiceItemList;

    /**
     * Gets the value of the complianceZoneCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComplianceZoneCd() {
        return complianceZoneCd;
    }

    /**
     * Sets the value of the complianceZoneCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComplianceZoneCd(String value) {
        this.complianceZoneCd = value;
    }

    /**
     * Gets the value of the complianceInd property.
     * 
     */
    public boolean isComplianceInd() {
        return complianceInd;
    }

    /**
     * Sets the value of the complianceInd property.
     * 
     */
    public void setComplianceInd(boolean value) {
        this.complianceInd = value;
    }

    /**
     * Gets the value of the consentRequiredThresholdAmt property.
     * 
     */
    public double getConsentRequiredThresholdAmt() {
        return consentRequiredThresholdAmt;
    }

    /**
     * Sets the value of the consentRequiredThresholdAmt property.
     * 
     */
    public void setConsentRequiredThresholdAmt(double value) {
        this.consentRequiredThresholdAmt = value;
    }

    /**
     * Gets the value of the zoneServiceItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the zoneServiceItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getZoneServiceItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceItem }
     * 
     * 
     */
    public List<ServiceItem> getZoneServiceItemList() {
        if (zoneServiceItemList == null) {
            zoneServiceItemList = new ArrayList<ServiceItem>();
        }
        return this.zoneServiceItemList;
    }

    /**
     * Sets the value of the zoneServiceItemList property.
     * 
     * @param zoneServiceItemList
     *     allowed object is
     *     {@link ServiceItem }
     *     
     */
    public void setZoneServiceItemList(List<ServiceItem> zoneServiceItemList) {
        this.zoneServiceItemList = zoneServiceItemList;
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
            String theComplianceZoneCd;
            theComplianceZoneCd = this.getComplianceZoneCd();
            strategy.appendField(locator, this, "complianceZoneCd", buffer, theComplianceZoneCd);
        }
        {
            boolean theComplianceInd;
            theComplianceInd = (true?this.isComplianceInd():false);
            strategy.appendField(locator, this, "complianceInd", buffer, theComplianceInd);
        }
        {
            double theConsentRequiredThresholdAmt;
            theConsentRequiredThresholdAmt = (true?this.getConsentRequiredThresholdAmt(): 0.0D);
            strategy.appendField(locator, this, "consentRequiredThresholdAmt", buffer, theConsentRequiredThresholdAmt);
        }
        {
            List<ServiceItem> theZoneServiceItemList;
            theZoneServiceItemList = (((this.zoneServiceItemList!= null)&&(!this.zoneServiceItemList.isEmpty()))?this.getZoneServiceItemList():null);
            strategy.appendField(locator, this, "zoneServiceItemList", buffer, theZoneServiceItemList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BillChargeComplianceDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BillChargeComplianceDetail that = ((BillChargeComplianceDetail) object);
        {
            String lhsComplianceZoneCd;
            lhsComplianceZoneCd = this.getComplianceZoneCd();
            String rhsComplianceZoneCd;
            rhsComplianceZoneCd = that.getComplianceZoneCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "complianceZoneCd", lhsComplianceZoneCd), LocatorUtils.property(thatLocator, "complianceZoneCd", rhsComplianceZoneCd), lhsComplianceZoneCd, rhsComplianceZoneCd)) {
                return false;
            }
        }
        {
            boolean lhsComplianceInd;
            lhsComplianceInd = (true?this.isComplianceInd():false);
            boolean rhsComplianceInd;
            rhsComplianceInd = (true?that.isComplianceInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "complianceInd", lhsComplianceInd), LocatorUtils.property(thatLocator, "complianceInd", rhsComplianceInd), lhsComplianceInd, rhsComplianceInd)) {
                return false;
            }
        }
        {
            double lhsConsentRequiredThresholdAmt;
            lhsConsentRequiredThresholdAmt = (true?this.getConsentRequiredThresholdAmt(): 0.0D);
            double rhsConsentRequiredThresholdAmt;
            rhsConsentRequiredThresholdAmt = (true?that.getConsentRequiredThresholdAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "consentRequiredThresholdAmt", lhsConsentRequiredThresholdAmt), LocatorUtils.property(thatLocator, "consentRequiredThresholdAmt", rhsConsentRequiredThresholdAmt), lhsConsentRequiredThresholdAmt, rhsConsentRequiredThresholdAmt)) {
                return false;
            }
        }
        {
            List<ServiceItem> lhsZoneServiceItemList;
            lhsZoneServiceItemList = (((this.zoneServiceItemList!= null)&&(!this.zoneServiceItemList.isEmpty()))?this.getZoneServiceItemList():null);
            List<ServiceItem> rhsZoneServiceItemList;
            rhsZoneServiceItemList = (((that.zoneServiceItemList!= null)&&(!that.zoneServiceItemList.isEmpty()))?that.getZoneServiceItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "zoneServiceItemList", lhsZoneServiceItemList), LocatorUtils.property(thatLocator, "zoneServiceItemList", rhsZoneServiceItemList), lhsZoneServiceItemList, rhsZoneServiceItemList)) {
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
