
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for Service complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Service">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList"/>
 *         &lt;element name="servicePriceAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="serviceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="kbServiceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="recurringChargeFrequencyPerMonth" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="serviceTerm" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceTerm" minOccurs="0"/>
 *         &lt;element name="serviceAccessTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="oneTimeChargeIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="serviceGroupTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceFamilyTypeCodeList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceFamilyType" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Service", propOrder = {
    "serviceCode",
    "serviceDescriptionTextList",
    "servicePriceAmount",
    "serviceTypeCode",
    "kbServiceTypeCode",
    "recurringChargeFrequencyPerMonth",
    "serviceTerm",
    "serviceAccessTypeCode",
    "oneTimeChargeIndicator",
    "serviceGroupTypeCode",
    "serviceFamilyTypeCodeList"
})
public class Service
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceCode;
    @XmlElement(required = true)
    protected MultilingualCodeDescTextList serviceDescriptionTextList;
    protected Double servicePriceAmount;
    protected String serviceTypeCode;
    protected String kbServiceTypeCode;
    protected BigInteger recurringChargeFrequencyPerMonth;
    protected ServiceTerm serviceTerm;
    protected String serviceAccessTypeCode;
    protected Boolean oneTimeChargeIndicator;
    protected String serviceGroupTypeCode;
    protected List<ServiceFamilyType> serviceFamilyTypeCodeList;

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the serviceDescriptionTextList property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getServiceDescriptionTextList() {
        return serviceDescriptionTextList;
    }

    /**
     * Sets the value of the serviceDescriptionTextList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setServiceDescriptionTextList(MultilingualCodeDescTextList value) {
        this.serviceDescriptionTextList = value;
    }

    /**
     * Gets the value of the servicePriceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getServicePriceAmount() {
        return servicePriceAmount;
    }

    /**
     * Sets the value of the servicePriceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setServicePriceAmount(Double value) {
        this.servicePriceAmount = value;
    }

    /**
     * Gets the value of the serviceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    /**
     * Sets the value of the serviceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCode(String value) {
        this.serviceTypeCode = value;
    }

    /**
     * Gets the value of the kbServiceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKbServiceTypeCode() {
        return kbServiceTypeCode;
    }

    /**
     * Sets the value of the kbServiceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKbServiceTypeCode(String value) {
        this.kbServiceTypeCode = value;
    }

    /**
     * Gets the value of the recurringChargeFrequencyPerMonth property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRecurringChargeFrequencyPerMonth() {
        return recurringChargeFrequencyPerMonth;
    }

    /**
     * Sets the value of the recurringChargeFrequencyPerMonth property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRecurringChargeFrequencyPerMonth(BigInteger value) {
        this.recurringChargeFrequencyPerMonth = value;
    }

    /**
     * Gets the value of the serviceTerm property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceTerm }
     *     
     */
    public ServiceTerm getServiceTerm() {
        return serviceTerm;
    }

    /**
     * Sets the value of the serviceTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceTerm }
     *     
     */
    public void setServiceTerm(ServiceTerm value) {
        this.serviceTerm = value;
    }

    /**
     * Gets the value of the serviceAccessTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceAccessTypeCode() {
        return serviceAccessTypeCode;
    }

    /**
     * Sets the value of the serviceAccessTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceAccessTypeCode(String value) {
        this.serviceAccessTypeCode = value;
    }

    /**
     * Gets the value of the oneTimeChargeIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOneTimeChargeIndicator() {
        return oneTimeChargeIndicator;
    }

    /**
     * Sets the value of the oneTimeChargeIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOneTimeChargeIndicator(Boolean value) {
        this.oneTimeChargeIndicator = value;
    }

    /**
     * Gets the value of the serviceGroupTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceGroupTypeCode() {
        return serviceGroupTypeCode;
    }

    /**
     * Sets the value of the serviceGroupTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceGroupTypeCode(String value) {
        this.serviceGroupTypeCode = value;
    }

    /**
     * Gets the value of the serviceFamilyTypeCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceFamilyTypeCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceFamilyTypeCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceFamilyType }
     * 
     * 
     */
    public List<ServiceFamilyType> getServiceFamilyTypeCodeList() {
        if (serviceFamilyTypeCodeList == null) {
            serviceFamilyTypeCodeList = new ArrayList<ServiceFamilyType>();
        }
        return this.serviceFamilyTypeCodeList;
    }

    /**
     * Sets the value of the serviceFamilyTypeCodeList property.
     * 
     * @param serviceFamilyTypeCodeList
     *     allowed object is
     *     {@link ServiceFamilyType }
     *     
     */
    public void setServiceFamilyTypeCodeList(List<ServiceFamilyType> serviceFamilyTypeCodeList) {
        this.serviceFamilyTypeCodeList = serviceFamilyTypeCodeList;
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
            String theServiceCode;
            theServiceCode = this.getServiceCode();
            strategy.appendField(locator, this, "serviceCode", buffer, theServiceCode);
        }
        {
            MultilingualCodeDescTextList theServiceDescriptionTextList;
            theServiceDescriptionTextList = this.getServiceDescriptionTextList();
            strategy.appendField(locator, this, "serviceDescriptionTextList", buffer, theServiceDescriptionTextList);
        }
        {
            Double theServicePriceAmount;
            theServicePriceAmount = this.getServicePriceAmount();
            strategy.appendField(locator, this, "servicePriceAmount", buffer, theServicePriceAmount);
        }
        {
            String theServiceTypeCode;
            theServiceTypeCode = this.getServiceTypeCode();
            strategy.appendField(locator, this, "serviceTypeCode", buffer, theServiceTypeCode);
        }
        {
            String theKbServiceTypeCode;
            theKbServiceTypeCode = this.getKbServiceTypeCode();
            strategy.appendField(locator, this, "kbServiceTypeCode", buffer, theKbServiceTypeCode);
        }
        {
            BigInteger theRecurringChargeFrequencyPerMonth;
            theRecurringChargeFrequencyPerMonth = this.getRecurringChargeFrequencyPerMonth();
            strategy.appendField(locator, this, "recurringChargeFrequencyPerMonth", buffer, theRecurringChargeFrequencyPerMonth);
        }
        {
            ServiceTerm theServiceTerm;
            theServiceTerm = this.getServiceTerm();
            strategy.appendField(locator, this, "serviceTerm", buffer, theServiceTerm);
        }
        {
            String theServiceAccessTypeCode;
            theServiceAccessTypeCode = this.getServiceAccessTypeCode();
            strategy.appendField(locator, this, "serviceAccessTypeCode", buffer, theServiceAccessTypeCode);
        }
        {
            Boolean theOneTimeChargeIndicator;
            theOneTimeChargeIndicator = this.isOneTimeChargeIndicator();
            strategy.appendField(locator, this, "oneTimeChargeIndicator", buffer, theOneTimeChargeIndicator);
        }
        {
            String theServiceGroupTypeCode;
            theServiceGroupTypeCode = this.getServiceGroupTypeCode();
            strategy.appendField(locator, this, "serviceGroupTypeCode", buffer, theServiceGroupTypeCode);
        }
        {
            List<ServiceFamilyType> theServiceFamilyTypeCodeList;
            theServiceFamilyTypeCodeList = (((this.serviceFamilyTypeCodeList!= null)&&(!this.serviceFamilyTypeCodeList.isEmpty()))?this.getServiceFamilyTypeCodeList():null);
            strategy.appendField(locator, this, "serviceFamilyTypeCodeList", buffer, theServiceFamilyTypeCodeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Service)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Service that = ((Service) object);
        {
            String lhsServiceCode;
            lhsServiceCode = this.getServiceCode();
            String rhsServiceCode;
            rhsServiceCode = that.getServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCode", lhsServiceCode), LocatorUtils.property(thatLocator, "serviceCode", rhsServiceCode), lhsServiceCode, rhsServiceCode)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsServiceDescriptionTextList;
            lhsServiceDescriptionTextList = this.getServiceDescriptionTextList();
            MultilingualCodeDescTextList rhsServiceDescriptionTextList;
            rhsServiceDescriptionTextList = that.getServiceDescriptionTextList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceDescriptionTextList", lhsServiceDescriptionTextList), LocatorUtils.property(thatLocator, "serviceDescriptionTextList", rhsServiceDescriptionTextList), lhsServiceDescriptionTextList, rhsServiceDescriptionTextList)) {
                return false;
            }
        }
        {
            Double lhsServicePriceAmount;
            lhsServicePriceAmount = this.getServicePriceAmount();
            Double rhsServicePriceAmount;
            rhsServicePriceAmount = that.getServicePriceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "servicePriceAmount", lhsServicePriceAmount), LocatorUtils.property(thatLocator, "servicePriceAmount", rhsServicePriceAmount), lhsServicePriceAmount, rhsServicePriceAmount)) {
                return false;
            }
        }
        {
            String lhsServiceTypeCode;
            lhsServiceTypeCode = this.getServiceTypeCode();
            String rhsServiceTypeCode;
            rhsServiceTypeCode = that.getServiceTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTypeCode", lhsServiceTypeCode), LocatorUtils.property(thatLocator, "serviceTypeCode", rhsServiceTypeCode), lhsServiceTypeCode, rhsServiceTypeCode)) {
                return false;
            }
        }
        {
            String lhsKbServiceTypeCode;
            lhsKbServiceTypeCode = this.getKbServiceTypeCode();
            String rhsKbServiceTypeCode;
            rhsKbServiceTypeCode = that.getKbServiceTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "kbServiceTypeCode", lhsKbServiceTypeCode), LocatorUtils.property(thatLocator, "kbServiceTypeCode", rhsKbServiceTypeCode), lhsKbServiceTypeCode, rhsKbServiceTypeCode)) {
                return false;
            }
        }
        {
            BigInteger lhsRecurringChargeFrequencyPerMonth;
            lhsRecurringChargeFrequencyPerMonth = this.getRecurringChargeFrequencyPerMonth();
            BigInteger rhsRecurringChargeFrequencyPerMonth;
            rhsRecurringChargeFrequencyPerMonth = that.getRecurringChargeFrequencyPerMonth();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recurringChargeFrequencyPerMonth", lhsRecurringChargeFrequencyPerMonth), LocatorUtils.property(thatLocator, "recurringChargeFrequencyPerMonth", rhsRecurringChargeFrequencyPerMonth), lhsRecurringChargeFrequencyPerMonth, rhsRecurringChargeFrequencyPerMonth)) {
                return false;
            }
        }
        {
            ServiceTerm lhsServiceTerm;
            lhsServiceTerm = this.getServiceTerm();
            ServiceTerm rhsServiceTerm;
            rhsServiceTerm = that.getServiceTerm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTerm", lhsServiceTerm), LocatorUtils.property(thatLocator, "serviceTerm", rhsServiceTerm), lhsServiceTerm, rhsServiceTerm)) {
                return false;
            }
        }
        {
            String lhsServiceAccessTypeCode;
            lhsServiceAccessTypeCode = this.getServiceAccessTypeCode();
            String rhsServiceAccessTypeCode;
            rhsServiceAccessTypeCode = that.getServiceAccessTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAccessTypeCode", lhsServiceAccessTypeCode), LocatorUtils.property(thatLocator, "serviceAccessTypeCode", rhsServiceAccessTypeCode), lhsServiceAccessTypeCode, rhsServiceAccessTypeCode)) {
                return false;
            }
        }
        {
            Boolean lhsOneTimeChargeIndicator;
            lhsOneTimeChargeIndicator = this.isOneTimeChargeIndicator();
            Boolean rhsOneTimeChargeIndicator;
            rhsOneTimeChargeIndicator = that.isOneTimeChargeIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "oneTimeChargeIndicator", lhsOneTimeChargeIndicator), LocatorUtils.property(thatLocator, "oneTimeChargeIndicator", rhsOneTimeChargeIndicator), lhsOneTimeChargeIndicator, rhsOneTimeChargeIndicator)) {
                return false;
            }
        }
        {
            String lhsServiceGroupTypeCode;
            lhsServiceGroupTypeCode = this.getServiceGroupTypeCode();
            String rhsServiceGroupTypeCode;
            rhsServiceGroupTypeCode = that.getServiceGroupTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceGroupTypeCode", lhsServiceGroupTypeCode), LocatorUtils.property(thatLocator, "serviceGroupTypeCode", rhsServiceGroupTypeCode), lhsServiceGroupTypeCode, rhsServiceGroupTypeCode)) {
                return false;
            }
        }
        {
            List<ServiceFamilyType> lhsServiceFamilyTypeCodeList;
            lhsServiceFamilyTypeCodeList = (((this.serviceFamilyTypeCodeList!= null)&&(!this.serviceFamilyTypeCodeList.isEmpty()))?this.getServiceFamilyTypeCodeList():null);
            List<ServiceFamilyType> rhsServiceFamilyTypeCodeList;
            rhsServiceFamilyTypeCodeList = (((that.serviceFamilyTypeCodeList!= null)&&(!that.serviceFamilyTypeCodeList.isEmpty()))?that.getServiceFamilyTypeCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceFamilyTypeCodeList", lhsServiceFamilyTypeCodeList), LocatorUtils.property(thatLocator, "serviceFamilyTypeCodeList", rhsServiceFamilyTypeCodeList), lhsServiceFamilyTypeCodeList, rhsServiceFamilyTypeCodeList)) {
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
