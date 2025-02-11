
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
 * <p>Java class for BundleWirelessOfferInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleWirelessOfferInfoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="aomOfferCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oomOfferCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="originalOffersAvailableNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="offersRedeemedNum" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="effectiveStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="effectiveEndDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleWirelessOfferInfoType", propOrder = {
    "code",
    "aomOfferCode",
    "oomOfferCode",
    "description",
    "originalOffersAvailableNum",
    "offersRedeemedNum",
    "effectiveStartDate",
    "effectiveEndDate"
})
public class BundleWirelessOfferInfoType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String code;
    protected String aomOfferCode;
    protected String oomOfferCode;
    protected MultilingualDescriptionList description;
    protected Integer originalOffersAvailableNum;
    protected Integer offersRedeemedNum;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date effectiveStartDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date effectiveEndDate;

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the aomOfferCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAomOfferCode() {
        return aomOfferCode;
    }

    /**
     * Sets the value of the aomOfferCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAomOfferCode(String value) {
        this.aomOfferCode = value;
    }

    /**
     * Gets the value of the oomOfferCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOomOfferCode() {
        return oomOfferCode;
    }

    /**
     * Sets the value of the oomOfferCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOomOfferCode(String value) {
        this.oomOfferCode = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setDescription(MultilingualDescriptionList value) {
        this.description = value;
    }

    /**
     * Gets the value of the originalOffersAvailableNum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOriginalOffersAvailableNum() {
        return originalOffersAvailableNum;
    }

    /**
     * Sets the value of the originalOffersAvailableNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOriginalOffersAvailableNum(Integer value) {
        this.originalOffersAvailableNum = value;
    }

    /**
     * Gets the value of the offersRedeemedNum property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOffersRedeemedNum() {
        return offersRedeemedNum;
    }

    /**
     * Sets the value of the offersRedeemedNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOffersRedeemedNum(Integer value) {
        this.offersRedeemedNum = value;
    }

    /**
     * Gets the value of the effectiveStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEffectiveStartDate() {
        return effectiveStartDate;
    }

    /**
     * Sets the value of the effectiveStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveStartDate(Date value) {
        this.effectiveStartDate = value;
    }

    /**
     * Gets the value of the effectiveEndDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEffectiveEndDate() {
        return effectiveEndDate;
    }

    /**
     * Sets the value of the effectiveEndDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveEndDate(Date value) {
        this.effectiveEndDate = value;
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
            String theCode;
            theCode = this.getCode();
            strategy.appendField(locator, this, "code", buffer, theCode);
        }
        {
            String theAomOfferCode;
            theAomOfferCode = this.getAomOfferCode();
            strategy.appendField(locator, this, "aomOfferCode", buffer, theAomOfferCode);
        }
        {
            String theOomOfferCode;
            theOomOfferCode = this.getOomOfferCode();
            strategy.appendField(locator, this, "oomOfferCode", buffer, theOomOfferCode);
        }
        {
            MultilingualDescriptionList theDescription;
            theDescription = this.getDescription();
            strategy.appendField(locator, this, "description", buffer, theDescription);
        }
        {
            Integer theOriginalOffersAvailableNum;
            theOriginalOffersAvailableNum = this.getOriginalOffersAvailableNum();
            strategy.appendField(locator, this, "originalOffersAvailableNum", buffer, theOriginalOffersAvailableNum);
        }
        {
            Integer theOffersRedeemedNum;
            theOffersRedeemedNum = this.getOffersRedeemedNum();
            strategy.appendField(locator, this, "offersRedeemedNum", buffer, theOffersRedeemedNum);
        }
        {
            Date theEffectiveStartDate;
            theEffectiveStartDate = this.getEffectiveStartDate();
            strategy.appendField(locator, this, "effectiveStartDate", buffer, theEffectiveStartDate);
        }
        {
            Date theEffectiveEndDate;
            theEffectiveEndDate = this.getEffectiveEndDate();
            strategy.appendField(locator, this, "effectiveEndDate", buffer, theEffectiveEndDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BundleWirelessOfferInfoType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BundleWirelessOfferInfoType that = ((BundleWirelessOfferInfoType) object);
        {
            String lhsCode;
            lhsCode = this.getCode();
            String rhsCode;
            rhsCode = that.getCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "code", lhsCode), LocatorUtils.property(thatLocator, "code", rhsCode), lhsCode, rhsCode)) {
                return false;
            }
        }
        {
            String lhsAomOfferCode;
            lhsAomOfferCode = this.getAomOfferCode();
            String rhsAomOfferCode;
            rhsAomOfferCode = that.getAomOfferCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "aomOfferCode", lhsAomOfferCode), LocatorUtils.property(thatLocator, "aomOfferCode", rhsAomOfferCode), lhsAomOfferCode, rhsAomOfferCode)) {
                return false;
            }
        }
        {
            String lhsOomOfferCode;
            lhsOomOfferCode = this.getOomOfferCode();
            String rhsOomOfferCode;
            rhsOomOfferCode = that.getOomOfferCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oomOfferCode", lhsOomOfferCode), LocatorUtils.property(thatLocator, "oomOfferCode", rhsOomOfferCode), lhsOomOfferCode, rhsOomOfferCode)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsDescription;
            lhsDescription = this.getDescription();
            MultilingualDescriptionList rhsDescription;
            rhsDescription = that.getDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "description", lhsDescription), LocatorUtils.property(thatLocator, "description", rhsDescription), lhsDescription, rhsDescription)) {
                return false;
            }
        }
        {
            Integer lhsOriginalOffersAvailableNum;
            lhsOriginalOffersAvailableNum = this.getOriginalOffersAvailableNum();
            Integer rhsOriginalOffersAvailableNum;
            rhsOriginalOffersAvailableNum = that.getOriginalOffersAvailableNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "originalOffersAvailableNum", lhsOriginalOffersAvailableNum), LocatorUtils.property(thatLocator, "originalOffersAvailableNum", rhsOriginalOffersAvailableNum), lhsOriginalOffersAvailableNum, rhsOriginalOffersAvailableNum)) {
                return false;
            }
        }
        {
            Integer lhsOffersRedeemedNum;
            lhsOffersRedeemedNum = this.getOffersRedeemedNum();
            Integer rhsOffersRedeemedNum;
            rhsOffersRedeemedNum = that.getOffersRedeemedNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offersRedeemedNum", lhsOffersRedeemedNum), LocatorUtils.property(thatLocator, "offersRedeemedNum", rhsOffersRedeemedNum), lhsOffersRedeemedNum, rhsOffersRedeemedNum)) {
                return false;
            }
        }
        {
            Date lhsEffectiveStartDate;
            lhsEffectiveStartDate = this.getEffectiveStartDate();
            Date rhsEffectiveStartDate;
            rhsEffectiveStartDate = that.getEffectiveStartDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveStartDate", lhsEffectiveStartDate), LocatorUtils.property(thatLocator, "effectiveStartDate", rhsEffectiveStartDate), lhsEffectiveStartDate, rhsEffectiveStartDate)) {
                return false;
            }
        }
        {
            Date lhsEffectiveEndDate;
            lhsEffectiveEndDate = this.getEffectiveEndDate();
            Date rhsEffectiveEndDate;
            rhsEffectiveEndDate = that.getEffectiveEndDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveEndDate", lhsEffectiveEndDate), LocatorUtils.property(thatLocator, "effectiveEndDate", rhsEffectiveEndDate), lhsEffectiveEndDate, rhsEffectiveEndDate)) {
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
