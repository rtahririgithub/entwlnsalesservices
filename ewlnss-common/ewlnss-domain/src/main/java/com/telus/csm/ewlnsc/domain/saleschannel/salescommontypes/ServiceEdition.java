
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualNameList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * eg. Business connect RC edition
 * 
 * <p>Java class for ServiceEdition complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceEdition">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceEditionCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceEditionName" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualNameList" minOccurs="0"/>
 *         &lt;element name="serviceEditionDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="pricePlanFamilyTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceEditionRankNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceEditionTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceEdition", propOrder = {
    "serviceEditionCd",
    "serviceEditionName",
    "serviceEditionDescription",
    "pricePlanFamilyTypeCd",
    "serviceEditionRankNum",
    "serviceEditionTypeCd"
})
public class ServiceEdition
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceEditionCd;
    protected MultilingualNameList serviceEditionName;
    protected MultilingualDescriptionList serviceEditionDescription;
    protected String pricePlanFamilyTypeCd;
    protected String serviceEditionRankNum;
    protected String serviceEditionTypeCd;

    /**
     * Gets the value of the serviceEditionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEditionCd() {
        return serviceEditionCd;
    }

    /**
     * Sets the value of the serviceEditionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEditionCd(String value) {
        this.serviceEditionCd = value;
    }

    /**
     * Gets the value of the serviceEditionName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualNameList }
     *     
     */
    public MultilingualNameList getServiceEditionName() {
        return serviceEditionName;
    }

    /**
     * Sets the value of the serviceEditionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualNameList }
     *     
     */
    public void setServiceEditionName(MultilingualNameList value) {
        this.serviceEditionName = value;
    }

    /**
     * Gets the value of the serviceEditionDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getServiceEditionDescription() {
        return serviceEditionDescription;
    }

    /**
     * Sets the value of the serviceEditionDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setServiceEditionDescription(MultilingualDescriptionList value) {
        this.serviceEditionDescription = value;
    }

    /**
     * Gets the value of the pricePlanFamilyTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanFamilyTypeCd() {
        return pricePlanFamilyTypeCd;
    }

    /**
     * Sets the value of the pricePlanFamilyTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanFamilyTypeCd(String value) {
        this.pricePlanFamilyTypeCd = value;
    }

    /**
     * Gets the value of the serviceEditionRankNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEditionRankNum() {
        return serviceEditionRankNum;
    }

    /**
     * Sets the value of the serviceEditionRankNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEditionRankNum(String value) {
        this.serviceEditionRankNum = value;
    }

    /**
     * Gets the value of the serviceEditionTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceEditionTypeCd() {
        return serviceEditionTypeCd;
    }

    /**
     * Sets the value of the serviceEditionTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceEditionTypeCd(String value) {
        this.serviceEditionTypeCd = value;
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
            String theServiceEditionCd;
            theServiceEditionCd = this.getServiceEditionCd();
            strategy.appendField(locator, this, "serviceEditionCd", buffer, theServiceEditionCd);
        }
        {
            MultilingualNameList theServiceEditionName;
            theServiceEditionName = this.getServiceEditionName();
            strategy.appendField(locator, this, "serviceEditionName", buffer, theServiceEditionName);
        }
        {
            MultilingualDescriptionList theServiceEditionDescription;
            theServiceEditionDescription = this.getServiceEditionDescription();
            strategy.appendField(locator, this, "serviceEditionDescription", buffer, theServiceEditionDescription);
        }
        {
            String thePricePlanFamilyTypeCd;
            thePricePlanFamilyTypeCd = this.getPricePlanFamilyTypeCd();
            strategy.appendField(locator, this, "pricePlanFamilyTypeCd", buffer, thePricePlanFamilyTypeCd);
        }
        {
            String theServiceEditionRankNum;
            theServiceEditionRankNum = this.getServiceEditionRankNum();
            strategy.appendField(locator, this, "serviceEditionRankNum", buffer, theServiceEditionRankNum);
        }
        {
            String theServiceEditionTypeCd;
            theServiceEditionTypeCd = this.getServiceEditionTypeCd();
            strategy.appendField(locator, this, "serviceEditionTypeCd", buffer, theServiceEditionTypeCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceEdition)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceEdition that = ((ServiceEdition) object);
        {
            String lhsServiceEditionCd;
            lhsServiceEditionCd = this.getServiceEditionCd();
            String rhsServiceEditionCd;
            rhsServiceEditionCd = that.getServiceEditionCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEditionCd", lhsServiceEditionCd), LocatorUtils.property(thatLocator, "serviceEditionCd", rhsServiceEditionCd), lhsServiceEditionCd, rhsServiceEditionCd)) {
                return false;
            }
        }
        {
            MultilingualNameList lhsServiceEditionName;
            lhsServiceEditionName = this.getServiceEditionName();
            MultilingualNameList rhsServiceEditionName;
            rhsServiceEditionName = that.getServiceEditionName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEditionName", lhsServiceEditionName), LocatorUtils.property(thatLocator, "serviceEditionName", rhsServiceEditionName), lhsServiceEditionName, rhsServiceEditionName)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsServiceEditionDescription;
            lhsServiceEditionDescription = this.getServiceEditionDescription();
            MultilingualDescriptionList rhsServiceEditionDescription;
            rhsServiceEditionDescription = that.getServiceEditionDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEditionDescription", lhsServiceEditionDescription), LocatorUtils.property(thatLocator, "serviceEditionDescription", rhsServiceEditionDescription), lhsServiceEditionDescription, rhsServiceEditionDescription)) {
                return false;
            }
        }
        {
            String lhsPricePlanFamilyTypeCd;
            lhsPricePlanFamilyTypeCd = this.getPricePlanFamilyTypeCd();
            String rhsPricePlanFamilyTypeCd;
            rhsPricePlanFamilyTypeCd = that.getPricePlanFamilyTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanFamilyTypeCd", lhsPricePlanFamilyTypeCd), LocatorUtils.property(thatLocator, "pricePlanFamilyTypeCd", rhsPricePlanFamilyTypeCd), lhsPricePlanFamilyTypeCd, rhsPricePlanFamilyTypeCd)) {
                return false;
            }
        }
        {
            String lhsServiceEditionRankNum;
            lhsServiceEditionRankNum = this.getServiceEditionRankNum();
            String rhsServiceEditionRankNum;
            rhsServiceEditionRankNum = that.getServiceEditionRankNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEditionRankNum", lhsServiceEditionRankNum), LocatorUtils.property(thatLocator, "serviceEditionRankNum", rhsServiceEditionRankNum), lhsServiceEditionRankNum, rhsServiceEditionRankNum)) {
                return false;
            }
        }
        {
            String lhsServiceEditionTypeCd;
            lhsServiceEditionTypeCd = this.getServiceEditionTypeCd();
            String rhsServiceEditionTypeCd;
            rhsServiceEditionTypeCd = that.getServiceEditionTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEditionTypeCd", lhsServiceEditionTypeCd), LocatorUtils.property(thatLocator, "serviceEditionTypeCd", rhsServiceEditionTypeCd), lhsServiceEditionTypeCd, rhsServiceEditionTypeCd)) {
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
