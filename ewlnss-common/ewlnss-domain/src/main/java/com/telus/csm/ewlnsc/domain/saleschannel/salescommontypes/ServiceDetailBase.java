
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * service detail base
 * 
 * <p>Java class for ServiceDetailBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceDetailBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Service"/>
 *         &lt;element name="customizedServiceIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="offerAsBonusIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ineligibleReasonCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ineligibleReasonDescriptionText" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDetailBase", propOrder = {
    "service",
    "customizedServiceIndicator",
    "offerAsBonusIndicator",
    "ineligibleReasonCode",
    "ineligibleReasonDescriptionText"
})
public class ServiceDetailBase
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Service service;
    protected Boolean customizedServiceIndicator;
    protected Boolean offerAsBonusIndicator;
    @XmlElement(required = true)
    protected String ineligibleReasonCode;
    @XmlElement(required = true)
    protected MultilingualCodeDescTextList ineligibleReasonDescriptionText;

    /**
     * Gets the value of the service property.
     * 
     * @return
     *     possible object is
     *     {@link Service }
     *     
     */
    public Service getService() {
        return service;
    }

    /**
     * Sets the value of the service property.
     * 
     * @param value
     *     allowed object is
     *     {@link Service }
     *     
     */
    public void setService(Service value) {
        this.service = value;
    }

    /**
     * Gets the value of the customizedServiceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomizedServiceIndicator() {
        return customizedServiceIndicator;
    }

    /**
     * Sets the value of the customizedServiceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomizedServiceIndicator(Boolean value) {
        this.customizedServiceIndicator = value;
    }

    /**
     * Gets the value of the offerAsBonusIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOfferAsBonusIndicator() {
        return offerAsBonusIndicator;
    }

    /**
     * Sets the value of the offerAsBonusIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOfferAsBonusIndicator(Boolean value) {
        this.offerAsBonusIndicator = value;
    }

    /**
     * Gets the value of the ineligibleReasonCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIneligibleReasonCode() {
        return ineligibleReasonCode;
    }

    /**
     * Sets the value of the ineligibleReasonCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIneligibleReasonCode(String value) {
        this.ineligibleReasonCode = value;
    }

    /**
     * Gets the value of the ineligibleReasonDescriptionText property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getIneligibleReasonDescriptionText() {
        return ineligibleReasonDescriptionText;
    }

    /**
     * Sets the value of the ineligibleReasonDescriptionText property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setIneligibleReasonDescriptionText(MultilingualCodeDescTextList value) {
        this.ineligibleReasonDescriptionText = value;
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
            Service theService;
            theService = this.getService();
            strategy.appendField(locator, this, "service", buffer, theService);
        }
        {
            Boolean theCustomizedServiceIndicator;
            theCustomizedServiceIndicator = this.isCustomizedServiceIndicator();
            strategy.appendField(locator, this, "customizedServiceIndicator", buffer, theCustomizedServiceIndicator);
        }
        {
            Boolean theOfferAsBonusIndicator;
            theOfferAsBonusIndicator = this.isOfferAsBonusIndicator();
            strategy.appendField(locator, this, "offerAsBonusIndicator", buffer, theOfferAsBonusIndicator);
        }
        {
            String theIneligibleReasonCode;
            theIneligibleReasonCode = this.getIneligibleReasonCode();
            strategy.appendField(locator, this, "ineligibleReasonCode", buffer, theIneligibleReasonCode);
        }
        {
            MultilingualCodeDescTextList theIneligibleReasonDescriptionText;
            theIneligibleReasonDescriptionText = this.getIneligibleReasonDescriptionText();
            strategy.appendField(locator, this, "ineligibleReasonDescriptionText", buffer, theIneligibleReasonDescriptionText);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceDetailBase)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceDetailBase that = ((ServiceDetailBase) object);
        {
            Service lhsService;
            lhsService = this.getService();
            Service rhsService;
            rhsService = that.getService();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "service", lhsService), LocatorUtils.property(thatLocator, "service", rhsService), lhsService, rhsService)) {
                return false;
            }
        }
        {
            Boolean lhsCustomizedServiceIndicator;
            lhsCustomizedServiceIndicator = this.isCustomizedServiceIndicator();
            Boolean rhsCustomizedServiceIndicator;
            rhsCustomizedServiceIndicator = that.isCustomizedServiceIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customizedServiceIndicator", lhsCustomizedServiceIndicator), LocatorUtils.property(thatLocator, "customizedServiceIndicator", rhsCustomizedServiceIndicator), lhsCustomizedServiceIndicator, rhsCustomizedServiceIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsOfferAsBonusIndicator;
            lhsOfferAsBonusIndicator = this.isOfferAsBonusIndicator();
            Boolean rhsOfferAsBonusIndicator;
            rhsOfferAsBonusIndicator = that.isOfferAsBonusIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerAsBonusIndicator", lhsOfferAsBonusIndicator), LocatorUtils.property(thatLocator, "offerAsBonusIndicator", rhsOfferAsBonusIndicator), lhsOfferAsBonusIndicator, rhsOfferAsBonusIndicator)) {
                return false;
            }
        }
        {
            String lhsIneligibleReasonCode;
            lhsIneligibleReasonCode = this.getIneligibleReasonCode();
            String rhsIneligibleReasonCode;
            rhsIneligibleReasonCode = that.getIneligibleReasonCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ineligibleReasonCode", lhsIneligibleReasonCode), LocatorUtils.property(thatLocator, "ineligibleReasonCode", rhsIneligibleReasonCode), lhsIneligibleReasonCode, rhsIneligibleReasonCode)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsIneligibleReasonDescriptionText;
            lhsIneligibleReasonDescriptionText = this.getIneligibleReasonDescriptionText();
            MultilingualCodeDescTextList rhsIneligibleReasonDescriptionText;
            rhsIneligibleReasonDescriptionText = that.getIneligibleReasonDescriptionText();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ineligibleReasonDescriptionText", lhsIneligibleReasonDescriptionText), LocatorUtils.property(thatLocator, "ineligibleReasonDescriptionText", rhsIneligibleReasonDescriptionText), lhsIneligibleReasonDescriptionText, rhsIneligibleReasonDescriptionText)) {
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
