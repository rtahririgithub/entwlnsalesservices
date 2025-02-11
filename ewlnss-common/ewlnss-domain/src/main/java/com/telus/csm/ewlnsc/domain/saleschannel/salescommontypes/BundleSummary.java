
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescriptionList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Bundle summary object
 * 
 * <p>Java class for BundleSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundleId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="bundleCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bundleType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="hsiaType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="wirelessOfferCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bundleDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescriptionList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleSummary", propOrder = {
    "bundleId",
    "bundleCd",
    "bundleType",
    "hsiaType",
    "provinceCd",
    "wirelessOfferCd",
    "bundleDescription"
})
public class BundleSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Long bundleId;
    protected String bundleCd;
    protected String bundleType;
    protected String hsiaType;
    protected String provinceCd;
    protected String wirelessOfferCd;
    protected MultilingualCodeDescriptionList bundleDescription;

    /**
     * Gets the value of the bundleId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBundleId() {
        return bundleId;
    }

    /**
     * Sets the value of the bundleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBundleId(Long value) {
        this.bundleId = value;
    }

    /**
     * Gets the value of the bundleCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundleCd() {
        return bundleCd;
    }

    /**
     * Sets the value of the bundleCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundleCd(String value) {
        this.bundleCd = value;
    }

    /**
     * Gets the value of the bundleType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBundleType() {
        return bundleType;
    }

    /**
     * Sets the value of the bundleType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBundleType(String value) {
        this.bundleType = value;
    }

    /**
     * Gets the value of the hsiaType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHsiaType() {
        return hsiaType;
    }

    /**
     * Sets the value of the hsiaType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHsiaType(String value) {
        this.hsiaType = value;
    }

    /**
     * Gets the value of the provinceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCd() {
        return provinceCd;
    }

    /**
     * Sets the value of the provinceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCd(String value) {
        this.provinceCd = value;
    }

    /**
     * Gets the value of the wirelessOfferCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWirelessOfferCd() {
        return wirelessOfferCd;
    }

    /**
     * Sets the value of the wirelessOfferCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWirelessOfferCd(String value) {
        this.wirelessOfferCd = value;
    }

    /**
     * Gets the value of the bundleDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescriptionList }
     *     
     */
    public MultilingualCodeDescriptionList getBundleDescription() {
        return bundleDescription;
    }

    /**
     * Sets the value of the bundleDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescriptionList }
     *     
     */
    public void setBundleDescription(MultilingualCodeDescriptionList value) {
        this.bundleDescription = value;
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
            Long theBundleId;
            theBundleId = this.getBundleId();
            strategy.appendField(locator, this, "bundleId", buffer, theBundleId);
        }
        {
            String theBundleCd;
            theBundleCd = this.getBundleCd();
            strategy.appendField(locator, this, "bundleCd", buffer, theBundleCd);
        }
        {
            String theBundleType;
            theBundleType = this.getBundleType();
            strategy.appendField(locator, this, "bundleType", buffer, theBundleType);
        }
        {
            String theHsiaType;
            theHsiaType = this.getHsiaType();
            strategy.appendField(locator, this, "hsiaType", buffer, theHsiaType);
        }
        {
            String theProvinceCd;
            theProvinceCd = this.getProvinceCd();
            strategy.appendField(locator, this, "provinceCd", buffer, theProvinceCd);
        }
        {
            String theWirelessOfferCd;
            theWirelessOfferCd = this.getWirelessOfferCd();
            strategy.appendField(locator, this, "wirelessOfferCd", buffer, theWirelessOfferCd);
        }
        {
            MultilingualCodeDescriptionList theBundleDescription;
            theBundleDescription = this.getBundleDescription();
            strategy.appendField(locator, this, "bundleDescription", buffer, theBundleDescription);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BundleSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BundleSummary that = ((BundleSummary) object);
        {
            Long lhsBundleId;
            lhsBundleId = this.getBundleId();
            Long rhsBundleId;
            rhsBundleId = that.getBundleId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleId", lhsBundleId), LocatorUtils.property(thatLocator, "bundleId", rhsBundleId), lhsBundleId, rhsBundleId)) {
                return false;
            }
        }
        {
            String lhsBundleCd;
            lhsBundleCd = this.getBundleCd();
            String rhsBundleCd;
            rhsBundleCd = that.getBundleCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleCd", lhsBundleCd), LocatorUtils.property(thatLocator, "bundleCd", rhsBundleCd), lhsBundleCd, rhsBundleCd)) {
                return false;
            }
        }
        {
            String lhsBundleType;
            lhsBundleType = this.getBundleType();
            String rhsBundleType;
            rhsBundleType = that.getBundleType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleType", lhsBundleType), LocatorUtils.property(thatLocator, "bundleType", rhsBundleType), lhsBundleType, rhsBundleType)) {
                return false;
            }
        }
        {
            String lhsHsiaType;
            lhsHsiaType = this.getHsiaType();
            String rhsHsiaType;
            rhsHsiaType = that.getHsiaType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hsiaType", lhsHsiaType), LocatorUtils.property(thatLocator, "hsiaType", rhsHsiaType), lhsHsiaType, rhsHsiaType)) {
                return false;
            }
        }
        {
            String lhsProvinceCd;
            lhsProvinceCd = this.getProvinceCd();
            String rhsProvinceCd;
            rhsProvinceCd = that.getProvinceCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceCd", lhsProvinceCd), LocatorUtils.property(thatLocator, "provinceCd", rhsProvinceCd), lhsProvinceCd, rhsProvinceCd)) {
                return false;
            }
        }
        {
            String lhsWirelessOfferCd;
            lhsWirelessOfferCd = this.getWirelessOfferCd();
            String rhsWirelessOfferCd;
            rhsWirelessOfferCd = that.getWirelessOfferCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessOfferCd", lhsWirelessOfferCd), LocatorUtils.property(thatLocator, "wirelessOfferCd", rhsWirelessOfferCd), lhsWirelessOfferCd, rhsWirelessOfferCd)) {
                return false;
            }
        }
        {
            MultilingualCodeDescriptionList lhsBundleDescription;
            lhsBundleDescription = this.getBundleDescription();
            MultilingualCodeDescriptionList rhsBundleDescription;
            rhsBundleDescription = that.getBundleDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleDescription", lhsBundleDescription), LocatorUtils.property(thatLocator, "bundleDescription", rhsBundleDescription), lhsBundleDescription, rhsBundleDescription)) {
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
