
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
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
 * Can we use the logic around preselected and read-only to move that SOC into the manadatory group.
 * 
 * <p>Java class for ServiceDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="service" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Service"/>
 *         &lt;element name="serviceMandatoryIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="readOnlyIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="preselectedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="validationParameterList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ValidationParameterList" minOccurs="0"/>
 *         &lt;element name="promoFeatureIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="promoExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="associatedServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerAsBonusIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="currentlySubscribedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="carryoverIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="autoRenewableIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mandatoryAutoRenewableIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="additionalServiceInformationTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
 *         &lt;element name="boundServiceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}BoundServiceList" minOccurs="0"/>
 *         &lt;element name="callingCircleParameter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCircleParameterDetail" minOccurs="0"/>
 *         &lt;element name="customizedServiceIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="service911Indicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nonMscContributionIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dataSharingServiceIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dataSharingGroupList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DataSharingGroupSummary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="featureRecommendation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}FeatureRecommendation" minOccurs="0"/>
 *         &lt;element name="includedAllowanceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Usage" maxOccurs="9" minOccurs="0"/>
 *         &lt;element name="mandatoryDataAddOnInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="headOfficeCallingPhoneNumberList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="futureDateEligibleInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="equipmentWarrantyAddOnInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nonDiscountableIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="internationalDialingIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="internationalRoamingIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="internationalServiceIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowPreselectIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="recommendedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="businessAppServiceIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="billChargeComplianceServiceInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceDetail", propOrder = {
    "service",
    "serviceMandatoryIndicator",
    "readOnlyIndicator",
    "preselectedIndicator",
    "validationParameterList",
    "promoFeatureIndicator",
    "promoExpiryDate",
    "associatedServiceCode",
    "offerAsBonusIndicator",
    "currentlySubscribedIndicator",
    "carryoverIndicator",
    "autoRenewableIndicator",
    "mandatoryAutoRenewableIndicator",
    "additionalServiceInformationTextList",
    "boundServiceList",
    "callingCircleParameter",
    "customizedServiceIndicator",
    "service911Indicator",
    "nonMscContributionIndicator",
    "dataSharingServiceIndicator",
    "dataSharingGroupList",
    "featureRecommendation",
    "includedAllowanceList",
    "mandatoryDataAddOnInd",
    "headOfficeCallingPhoneNumberList",
    "futureDateEligibleInd",
    "equipmentWarrantyAddOnInd",
    "nonDiscountableIndicator",
    "internationalDialingIndicator",
    "internationalRoamingIndicator",
    "internationalServiceIndicator",
    "allowPreselectIndicator",
    "recommendedIndicator",
    "businessAppServiceIndicator",
    "billChargeComplianceServiceInd"
})
public class ServiceDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected Service service;
    protected boolean serviceMandatoryIndicator;
    protected Boolean readOnlyIndicator;
    protected Boolean preselectedIndicator;
    protected ValidationParameterList validationParameterList;
    protected Boolean promoFeatureIndicator;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date promoExpiryDate;
    protected String associatedServiceCode;
    protected Boolean offerAsBonusIndicator;
    protected Boolean currentlySubscribedIndicator;
    protected Boolean carryoverIndicator;
    protected Boolean autoRenewableIndicator;
    protected Boolean mandatoryAutoRenewableIndicator;
    protected MultilingualCodeDescTextList additionalServiceInformationTextList;
    protected BoundServiceList boundServiceList;
    protected CallingCircleParameterDetail callingCircleParameter;
    protected Boolean customizedServiceIndicator;
    protected Boolean service911Indicator;
    protected Boolean nonMscContributionIndicator;
    protected Boolean dataSharingServiceIndicator;
    protected List<DataSharingGroupSummary> dataSharingGroupList;
    protected FeatureRecommendation featureRecommendation;
    protected List<Usage> includedAllowanceList;
    protected Boolean mandatoryDataAddOnInd;
    protected List<String> headOfficeCallingPhoneNumberList;
    protected Boolean futureDateEligibleInd;
    protected Boolean equipmentWarrantyAddOnInd;
    protected Boolean nonDiscountableIndicator;
    protected Boolean internationalDialingIndicator;
    protected Boolean internationalRoamingIndicator;
    protected Boolean internationalServiceIndicator;
    protected Boolean allowPreselectIndicator;
    protected Boolean recommendedIndicator;
    protected Boolean businessAppServiceIndicator;
    protected Boolean billChargeComplianceServiceInd;

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
     * Gets the value of the serviceMandatoryIndicator property.
     * 
     */
    public boolean isServiceMandatoryIndicator() {
        return serviceMandatoryIndicator;
    }

    /**
     * Sets the value of the serviceMandatoryIndicator property.
     * 
     */
    public void setServiceMandatoryIndicator(boolean value) {
        this.serviceMandatoryIndicator = value;
    }

    /**
     * Gets the value of the readOnlyIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReadOnlyIndicator() {
        return readOnlyIndicator;
    }

    /**
     * Sets the value of the readOnlyIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReadOnlyIndicator(Boolean value) {
        this.readOnlyIndicator = value;
    }

    /**
     * Gets the value of the preselectedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreselectedIndicator() {
        return preselectedIndicator;
    }

    /**
     * Sets the value of the preselectedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreselectedIndicator(Boolean value) {
        this.preselectedIndicator = value;
    }

    /**
     * Gets the value of the validationParameterList property.
     * 
     * @return
     *     possible object is
     *     {@link ValidationParameterList }
     *     
     */
    public ValidationParameterList getValidationParameterList() {
        return validationParameterList;
    }

    /**
     * Sets the value of the validationParameterList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ValidationParameterList }
     *     
     */
    public void setValidationParameterList(ValidationParameterList value) {
        this.validationParameterList = value;
    }

    /**
     * Gets the value of the promoFeatureIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPromoFeatureIndicator() {
        return promoFeatureIndicator;
    }

    /**
     * Sets the value of the promoFeatureIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPromoFeatureIndicator(Boolean value) {
        this.promoFeatureIndicator = value;
    }

    /**
     * Gets the value of the promoExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPromoExpiryDate() {
        return promoExpiryDate;
    }

    /**
     * Sets the value of the promoExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromoExpiryDate(Date value) {
        this.promoExpiryDate = value;
    }

    /**
     * Gets the value of the associatedServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedServiceCode() {
        return associatedServiceCode;
    }

    /**
     * Sets the value of the associatedServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedServiceCode(String value) {
        this.associatedServiceCode = value;
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
     * Gets the value of the currentlySubscribedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCurrentlySubscribedIndicator() {
        return currentlySubscribedIndicator;
    }

    /**
     * Sets the value of the currentlySubscribedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCurrentlySubscribedIndicator(Boolean value) {
        this.currentlySubscribedIndicator = value;
    }

    /**
     * Gets the value of the carryoverIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCarryoverIndicator() {
        return carryoverIndicator;
    }

    /**
     * Sets the value of the carryoverIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCarryoverIndicator(Boolean value) {
        this.carryoverIndicator = value;
    }

    /**
     * Gets the value of the autoRenewableIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAutoRenewableIndicator() {
        return autoRenewableIndicator;
    }

    /**
     * Sets the value of the autoRenewableIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAutoRenewableIndicator(Boolean value) {
        this.autoRenewableIndicator = value;
    }

    /**
     * Gets the value of the mandatoryAutoRenewableIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryAutoRenewableIndicator() {
        return mandatoryAutoRenewableIndicator;
    }

    /**
     * Sets the value of the mandatoryAutoRenewableIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryAutoRenewableIndicator(Boolean value) {
        this.mandatoryAutoRenewableIndicator = value;
    }

    /**
     * Gets the value of the additionalServiceInformationTextList property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getAdditionalServiceInformationTextList() {
        return additionalServiceInformationTextList;
    }

    /**
     * Sets the value of the additionalServiceInformationTextList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setAdditionalServiceInformationTextList(MultilingualCodeDescTextList value) {
        this.additionalServiceInformationTextList = value;
    }

    /**
     * Gets the value of the boundServiceList property.
     * 
     * @return
     *     possible object is
     *     {@link BoundServiceList }
     *     
     */
    public BoundServiceList getBoundServiceList() {
        return boundServiceList;
    }

    /**
     * Sets the value of the boundServiceList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BoundServiceList }
     *     
     */
    public void setBoundServiceList(BoundServiceList value) {
        this.boundServiceList = value;
    }

    /**
     * Gets the value of the callingCircleParameter property.
     * 
     * @return
     *     possible object is
     *     {@link CallingCircleParameterDetail }
     *     
     */
    public CallingCircleParameterDetail getCallingCircleParameter() {
        return callingCircleParameter;
    }

    /**
     * Sets the value of the callingCircleParameter property.
     * 
     * @param value
     *     allowed object is
     *     {@link CallingCircleParameterDetail }
     *     
     */
    public void setCallingCircleParameter(CallingCircleParameterDetail value) {
        this.callingCircleParameter = value;
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
     * Gets the value of the service911Indicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isService911Indicator() {
        return service911Indicator;
    }

    /**
     * Sets the value of the service911Indicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setService911Indicator(Boolean value) {
        this.service911Indicator = value;
    }

    /**
     * Gets the value of the nonMscContributionIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonMscContributionIndicator() {
        return nonMscContributionIndicator;
    }

    /**
     * Sets the value of the nonMscContributionIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonMscContributionIndicator(Boolean value) {
        this.nonMscContributionIndicator = value;
    }

    /**
     * Gets the value of the dataSharingServiceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDataSharingServiceIndicator() {
        return dataSharingServiceIndicator;
    }

    /**
     * Sets the value of the dataSharingServiceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDataSharingServiceIndicator(Boolean value) {
        this.dataSharingServiceIndicator = value;
    }

    /**
     * Gets the value of the dataSharingGroupList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSharingGroupList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSharingGroupList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSharingGroupSummary }
     * 
     * 
     */
    public List<DataSharingGroupSummary> getDataSharingGroupList() {
        if (dataSharingGroupList == null) {
            dataSharingGroupList = new ArrayList<DataSharingGroupSummary>();
        }
        return this.dataSharingGroupList;
    }

    /**
     * Gets the value of the featureRecommendation property.
     * 
     * @return
     *     possible object is
     *     {@link FeatureRecommendation }
     *     
     */
    public FeatureRecommendation getFeatureRecommendation() {
        return featureRecommendation;
    }

    /**
     * Sets the value of the featureRecommendation property.
     * 
     * @param value
     *     allowed object is
     *     {@link FeatureRecommendation }
     *     
     */
    public void setFeatureRecommendation(FeatureRecommendation value) {
        this.featureRecommendation = value;
    }

    /**
     * Gets the value of the includedAllowanceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedAllowanceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedAllowanceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Usage }
     * 
     * 
     */
    public List<Usage> getIncludedAllowanceList() {
        if (includedAllowanceList == null) {
            includedAllowanceList = new ArrayList<Usage>();
        }
        return this.includedAllowanceList;
    }

    /**
     * Gets the value of the mandatoryDataAddOnInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryDataAddOnInd() {
        return mandatoryDataAddOnInd;
    }

    /**
     * Sets the value of the mandatoryDataAddOnInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryDataAddOnInd(Boolean value) {
        this.mandatoryDataAddOnInd = value;
    }

    /**
     * Gets the value of the headOfficeCallingPhoneNumberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the headOfficeCallingPhoneNumberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getHeadOfficeCallingPhoneNumberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getHeadOfficeCallingPhoneNumberList() {
        if (headOfficeCallingPhoneNumberList == null) {
            headOfficeCallingPhoneNumberList = new ArrayList<String>();
        }
        return this.headOfficeCallingPhoneNumberList;
    }

    /**
     * Gets the value of the futureDateEligibleInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFutureDateEligibleInd() {
        return futureDateEligibleInd;
    }

    /**
     * Sets the value of the futureDateEligibleInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFutureDateEligibleInd(Boolean value) {
        this.futureDateEligibleInd = value;
    }

    /**
     * Gets the value of the equipmentWarrantyAddOnInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEquipmentWarrantyAddOnInd() {
        return equipmentWarrantyAddOnInd;
    }

    /**
     * Sets the value of the equipmentWarrantyAddOnInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEquipmentWarrantyAddOnInd(Boolean value) {
        this.equipmentWarrantyAddOnInd = value;
    }

    /**
     * Gets the value of the nonDiscountableIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonDiscountableIndicator() {
        return nonDiscountableIndicator;
    }

    /**
     * Sets the value of the nonDiscountableIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonDiscountableIndicator(Boolean value) {
        this.nonDiscountableIndicator = value;
    }

    /**
     * Gets the value of the internationalDialingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInternationalDialingIndicator() {
        return internationalDialingIndicator;
    }

    /**
     * Sets the value of the internationalDialingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInternationalDialingIndicator(Boolean value) {
        this.internationalDialingIndicator = value;
    }

    /**
     * Gets the value of the internationalRoamingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInternationalRoamingIndicator() {
        return internationalRoamingIndicator;
    }

    /**
     * Sets the value of the internationalRoamingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInternationalRoamingIndicator(Boolean value) {
        this.internationalRoamingIndicator = value;
    }

    /**
     * Gets the value of the internationalServiceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInternationalServiceIndicator() {
        return internationalServiceIndicator;
    }

    /**
     * Sets the value of the internationalServiceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInternationalServiceIndicator(Boolean value) {
        this.internationalServiceIndicator = value;
    }

    /**
     * Gets the value of the allowPreselectIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowPreselectIndicator() {
        return allowPreselectIndicator;
    }

    /**
     * Sets the value of the allowPreselectIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowPreselectIndicator(Boolean value) {
        this.allowPreselectIndicator = value;
    }

    /**
     * Gets the value of the recommendedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecommendedIndicator() {
        return recommendedIndicator;
    }

    /**
     * Sets the value of the recommendedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecommendedIndicator(Boolean value) {
        this.recommendedIndicator = value;
    }

    /**
     * Gets the value of the businessAppServiceIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessAppServiceIndicator() {
        return businessAppServiceIndicator;
    }

    /**
     * Sets the value of the businessAppServiceIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessAppServiceIndicator(Boolean value) {
        this.businessAppServiceIndicator = value;
    }

    /**
     * Gets the value of the billChargeComplianceServiceInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBillChargeComplianceServiceInd() {
        return billChargeComplianceServiceInd;
    }

    /**
     * Sets the value of the billChargeComplianceServiceInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBillChargeComplianceServiceInd(Boolean value) {
        this.billChargeComplianceServiceInd = value;
    }

    /**
     * Sets the value of the dataSharingGroupList property.
     * 
     * @param dataSharingGroupList
     *     allowed object is
     *     {@link DataSharingGroupSummary }
     *     
     */
    public void setDataSharingGroupList(List<DataSharingGroupSummary> dataSharingGroupList) {
        this.dataSharingGroupList = dataSharingGroupList;
    }

    /**
     * Sets the value of the includedAllowanceList property.
     * 
     * @param includedAllowanceList
     *     allowed object is
     *     {@link Usage }
     *     
     */
    public void setIncludedAllowanceList(List<Usage> includedAllowanceList) {
        this.includedAllowanceList = includedAllowanceList;
    }

    /**
     * Sets the value of the headOfficeCallingPhoneNumberList property.
     * 
     * @param headOfficeCallingPhoneNumberList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeadOfficeCallingPhoneNumberList(List<String> headOfficeCallingPhoneNumberList) {
        this.headOfficeCallingPhoneNumberList = headOfficeCallingPhoneNumberList;
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
            boolean theServiceMandatoryIndicator;
            theServiceMandatoryIndicator = (true?this.isServiceMandatoryIndicator():false);
            strategy.appendField(locator, this, "serviceMandatoryIndicator", buffer, theServiceMandatoryIndicator);
        }
        {
            Boolean theReadOnlyIndicator;
            theReadOnlyIndicator = this.isReadOnlyIndicator();
            strategy.appendField(locator, this, "readOnlyIndicator", buffer, theReadOnlyIndicator);
        }
        {
            Boolean thePreselectedIndicator;
            thePreselectedIndicator = this.isPreselectedIndicator();
            strategy.appendField(locator, this, "preselectedIndicator", buffer, thePreselectedIndicator);
        }
        {
            ValidationParameterList theValidationParameterList;
            theValidationParameterList = this.getValidationParameterList();
            strategy.appendField(locator, this, "validationParameterList", buffer, theValidationParameterList);
        }
        {
            Boolean thePromoFeatureIndicator;
            thePromoFeatureIndicator = this.isPromoFeatureIndicator();
            strategy.appendField(locator, this, "promoFeatureIndicator", buffer, thePromoFeatureIndicator);
        }
        {
            Date thePromoExpiryDate;
            thePromoExpiryDate = this.getPromoExpiryDate();
            strategy.appendField(locator, this, "promoExpiryDate", buffer, thePromoExpiryDate);
        }
        {
            String theAssociatedServiceCode;
            theAssociatedServiceCode = this.getAssociatedServiceCode();
            strategy.appendField(locator, this, "associatedServiceCode", buffer, theAssociatedServiceCode);
        }
        {
            Boolean theOfferAsBonusIndicator;
            theOfferAsBonusIndicator = this.isOfferAsBonusIndicator();
            strategy.appendField(locator, this, "offerAsBonusIndicator", buffer, theOfferAsBonusIndicator);
        }
        {
            Boolean theCurrentlySubscribedIndicator;
            theCurrentlySubscribedIndicator = this.isCurrentlySubscribedIndicator();
            strategy.appendField(locator, this, "currentlySubscribedIndicator", buffer, theCurrentlySubscribedIndicator);
        }
        {
            Boolean theCarryoverIndicator;
            theCarryoverIndicator = this.isCarryoverIndicator();
            strategy.appendField(locator, this, "carryoverIndicator", buffer, theCarryoverIndicator);
        }
        {
            Boolean theAutoRenewableIndicator;
            theAutoRenewableIndicator = this.isAutoRenewableIndicator();
            strategy.appendField(locator, this, "autoRenewableIndicator", buffer, theAutoRenewableIndicator);
        }
        {
            Boolean theMandatoryAutoRenewableIndicator;
            theMandatoryAutoRenewableIndicator = this.isMandatoryAutoRenewableIndicator();
            strategy.appendField(locator, this, "mandatoryAutoRenewableIndicator", buffer, theMandatoryAutoRenewableIndicator);
        }
        {
            MultilingualCodeDescTextList theAdditionalServiceInformationTextList;
            theAdditionalServiceInformationTextList = this.getAdditionalServiceInformationTextList();
            strategy.appendField(locator, this, "additionalServiceInformationTextList", buffer, theAdditionalServiceInformationTextList);
        }
        {
            BoundServiceList theBoundServiceList;
            theBoundServiceList = this.getBoundServiceList();
            strategy.appendField(locator, this, "boundServiceList", buffer, theBoundServiceList);
        }
        {
            CallingCircleParameterDetail theCallingCircleParameter;
            theCallingCircleParameter = this.getCallingCircleParameter();
            strategy.appendField(locator, this, "callingCircleParameter", buffer, theCallingCircleParameter);
        }
        {
            Boolean theCustomizedServiceIndicator;
            theCustomizedServiceIndicator = this.isCustomizedServiceIndicator();
            strategy.appendField(locator, this, "customizedServiceIndicator", buffer, theCustomizedServiceIndicator);
        }
        {
            Boolean theService911Indicator;
            theService911Indicator = this.isService911Indicator();
            strategy.appendField(locator, this, "service911Indicator", buffer, theService911Indicator);
        }
        {
            Boolean theNonMscContributionIndicator;
            theNonMscContributionIndicator = this.isNonMscContributionIndicator();
            strategy.appendField(locator, this, "nonMscContributionIndicator", buffer, theNonMscContributionIndicator);
        }
        {
            Boolean theDataSharingServiceIndicator;
            theDataSharingServiceIndicator = this.isDataSharingServiceIndicator();
            strategy.appendField(locator, this, "dataSharingServiceIndicator", buffer, theDataSharingServiceIndicator);
        }
        {
            List<DataSharingGroupSummary> theDataSharingGroupList;
            theDataSharingGroupList = (((this.dataSharingGroupList!= null)&&(!this.dataSharingGroupList.isEmpty()))?this.getDataSharingGroupList():null);
            strategy.appendField(locator, this, "dataSharingGroupList", buffer, theDataSharingGroupList);
        }
        {
            FeatureRecommendation theFeatureRecommendation;
            theFeatureRecommendation = this.getFeatureRecommendation();
            strategy.appendField(locator, this, "featureRecommendation", buffer, theFeatureRecommendation);
        }
        {
            List<Usage> theIncludedAllowanceList;
            theIncludedAllowanceList = (((this.includedAllowanceList!= null)&&(!this.includedAllowanceList.isEmpty()))?this.getIncludedAllowanceList():null);
            strategy.appendField(locator, this, "includedAllowanceList", buffer, theIncludedAllowanceList);
        }
        {
            Boolean theMandatoryDataAddOnInd;
            theMandatoryDataAddOnInd = this.isMandatoryDataAddOnInd();
            strategy.appendField(locator, this, "mandatoryDataAddOnInd", buffer, theMandatoryDataAddOnInd);
        }
        {
            List<String> theHeadOfficeCallingPhoneNumberList;
            theHeadOfficeCallingPhoneNumberList = (((this.headOfficeCallingPhoneNumberList!= null)&&(!this.headOfficeCallingPhoneNumberList.isEmpty()))?this.getHeadOfficeCallingPhoneNumberList():null);
            strategy.appendField(locator, this, "headOfficeCallingPhoneNumberList", buffer, theHeadOfficeCallingPhoneNumberList);
        }
        {
            Boolean theFutureDateEligibleInd;
            theFutureDateEligibleInd = this.isFutureDateEligibleInd();
            strategy.appendField(locator, this, "futureDateEligibleInd", buffer, theFutureDateEligibleInd);
        }
        {
            Boolean theEquipmentWarrantyAddOnInd;
            theEquipmentWarrantyAddOnInd = this.isEquipmentWarrantyAddOnInd();
            strategy.appendField(locator, this, "equipmentWarrantyAddOnInd", buffer, theEquipmentWarrantyAddOnInd);
        }
        {
            Boolean theNonDiscountableIndicator;
            theNonDiscountableIndicator = this.isNonDiscountableIndicator();
            strategy.appendField(locator, this, "nonDiscountableIndicator", buffer, theNonDiscountableIndicator);
        }
        {
            Boolean theInternationalDialingIndicator;
            theInternationalDialingIndicator = this.isInternationalDialingIndicator();
            strategy.appendField(locator, this, "internationalDialingIndicator", buffer, theInternationalDialingIndicator);
        }
        {
            Boolean theInternationalRoamingIndicator;
            theInternationalRoamingIndicator = this.isInternationalRoamingIndicator();
            strategy.appendField(locator, this, "internationalRoamingIndicator", buffer, theInternationalRoamingIndicator);
        }
        {
            Boolean theInternationalServiceIndicator;
            theInternationalServiceIndicator = this.isInternationalServiceIndicator();
            strategy.appendField(locator, this, "internationalServiceIndicator", buffer, theInternationalServiceIndicator);
        }
        {
            Boolean theAllowPreselectIndicator;
            theAllowPreselectIndicator = this.isAllowPreselectIndicator();
            strategy.appendField(locator, this, "allowPreselectIndicator", buffer, theAllowPreselectIndicator);
        }
        {
            Boolean theRecommendedIndicator;
            theRecommendedIndicator = this.isRecommendedIndicator();
            strategy.appendField(locator, this, "recommendedIndicator", buffer, theRecommendedIndicator);
        }
        {
            Boolean theBusinessAppServiceIndicator;
            theBusinessAppServiceIndicator = this.isBusinessAppServiceIndicator();
            strategy.appendField(locator, this, "businessAppServiceIndicator", buffer, theBusinessAppServiceIndicator);
        }
        {
            Boolean theBillChargeComplianceServiceInd;
            theBillChargeComplianceServiceInd = this.isBillChargeComplianceServiceInd();
            strategy.appendField(locator, this, "billChargeComplianceServiceInd", buffer, theBillChargeComplianceServiceInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceDetail that = ((ServiceDetail) object);
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
            boolean lhsServiceMandatoryIndicator;
            lhsServiceMandatoryIndicator = (true?this.isServiceMandatoryIndicator():false);
            boolean rhsServiceMandatoryIndicator;
            rhsServiceMandatoryIndicator = (true?that.isServiceMandatoryIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceMandatoryIndicator", lhsServiceMandatoryIndicator), LocatorUtils.property(thatLocator, "serviceMandatoryIndicator", rhsServiceMandatoryIndicator), lhsServiceMandatoryIndicator, rhsServiceMandatoryIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsReadOnlyIndicator;
            lhsReadOnlyIndicator = this.isReadOnlyIndicator();
            Boolean rhsReadOnlyIndicator;
            rhsReadOnlyIndicator = that.isReadOnlyIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "readOnlyIndicator", lhsReadOnlyIndicator), LocatorUtils.property(thatLocator, "readOnlyIndicator", rhsReadOnlyIndicator), lhsReadOnlyIndicator, rhsReadOnlyIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsPreselectedIndicator;
            lhsPreselectedIndicator = this.isPreselectedIndicator();
            Boolean rhsPreselectedIndicator;
            rhsPreselectedIndicator = that.isPreselectedIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preselectedIndicator", lhsPreselectedIndicator), LocatorUtils.property(thatLocator, "preselectedIndicator", rhsPreselectedIndicator), lhsPreselectedIndicator, rhsPreselectedIndicator)) {
                return false;
            }
        }
        {
            ValidationParameterList lhsValidationParameterList;
            lhsValidationParameterList = this.getValidationParameterList();
            ValidationParameterList rhsValidationParameterList;
            rhsValidationParameterList = that.getValidationParameterList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validationParameterList", lhsValidationParameterList), LocatorUtils.property(thatLocator, "validationParameterList", rhsValidationParameterList), lhsValidationParameterList, rhsValidationParameterList)) {
                return false;
            }
        }
        {
            Boolean lhsPromoFeatureIndicator;
            lhsPromoFeatureIndicator = this.isPromoFeatureIndicator();
            Boolean rhsPromoFeatureIndicator;
            rhsPromoFeatureIndicator = that.isPromoFeatureIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promoFeatureIndicator", lhsPromoFeatureIndicator), LocatorUtils.property(thatLocator, "promoFeatureIndicator", rhsPromoFeatureIndicator), lhsPromoFeatureIndicator, rhsPromoFeatureIndicator)) {
                return false;
            }
        }
        {
            Date lhsPromoExpiryDate;
            lhsPromoExpiryDate = this.getPromoExpiryDate();
            Date rhsPromoExpiryDate;
            rhsPromoExpiryDate = that.getPromoExpiryDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promoExpiryDate", lhsPromoExpiryDate), LocatorUtils.property(thatLocator, "promoExpiryDate", rhsPromoExpiryDate), lhsPromoExpiryDate, rhsPromoExpiryDate)) {
                return false;
            }
        }
        {
            String lhsAssociatedServiceCode;
            lhsAssociatedServiceCode = this.getAssociatedServiceCode();
            String rhsAssociatedServiceCode;
            rhsAssociatedServiceCode = that.getAssociatedServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedServiceCode", lhsAssociatedServiceCode), LocatorUtils.property(thatLocator, "associatedServiceCode", rhsAssociatedServiceCode), lhsAssociatedServiceCode, rhsAssociatedServiceCode)) {
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
            Boolean lhsCurrentlySubscribedIndicator;
            lhsCurrentlySubscribedIndicator = this.isCurrentlySubscribedIndicator();
            Boolean rhsCurrentlySubscribedIndicator;
            rhsCurrentlySubscribedIndicator = that.isCurrentlySubscribedIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "currentlySubscribedIndicator", lhsCurrentlySubscribedIndicator), LocatorUtils.property(thatLocator, "currentlySubscribedIndicator", rhsCurrentlySubscribedIndicator), lhsCurrentlySubscribedIndicator, rhsCurrentlySubscribedIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsCarryoverIndicator;
            lhsCarryoverIndicator = this.isCarryoverIndicator();
            Boolean rhsCarryoverIndicator;
            rhsCarryoverIndicator = that.isCarryoverIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "carryoverIndicator", lhsCarryoverIndicator), LocatorUtils.property(thatLocator, "carryoverIndicator", rhsCarryoverIndicator), lhsCarryoverIndicator, rhsCarryoverIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsAutoRenewableIndicator;
            lhsAutoRenewableIndicator = this.isAutoRenewableIndicator();
            Boolean rhsAutoRenewableIndicator;
            rhsAutoRenewableIndicator = that.isAutoRenewableIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "autoRenewableIndicator", lhsAutoRenewableIndicator), LocatorUtils.property(thatLocator, "autoRenewableIndicator", rhsAutoRenewableIndicator), lhsAutoRenewableIndicator, rhsAutoRenewableIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsMandatoryAutoRenewableIndicator;
            lhsMandatoryAutoRenewableIndicator = this.isMandatoryAutoRenewableIndicator();
            Boolean rhsMandatoryAutoRenewableIndicator;
            rhsMandatoryAutoRenewableIndicator = that.isMandatoryAutoRenewableIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mandatoryAutoRenewableIndicator", lhsMandatoryAutoRenewableIndicator), LocatorUtils.property(thatLocator, "mandatoryAutoRenewableIndicator", rhsMandatoryAutoRenewableIndicator), lhsMandatoryAutoRenewableIndicator, rhsMandatoryAutoRenewableIndicator)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsAdditionalServiceInformationTextList;
            lhsAdditionalServiceInformationTextList = this.getAdditionalServiceInformationTextList();
            MultilingualCodeDescTextList rhsAdditionalServiceInformationTextList;
            rhsAdditionalServiceInformationTextList = that.getAdditionalServiceInformationTextList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "additionalServiceInformationTextList", lhsAdditionalServiceInformationTextList), LocatorUtils.property(thatLocator, "additionalServiceInformationTextList", rhsAdditionalServiceInformationTextList), lhsAdditionalServiceInformationTextList, rhsAdditionalServiceInformationTextList)) {
                return false;
            }
        }
        {
            BoundServiceList lhsBoundServiceList;
            lhsBoundServiceList = this.getBoundServiceList();
            BoundServiceList rhsBoundServiceList;
            rhsBoundServiceList = that.getBoundServiceList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "boundServiceList", lhsBoundServiceList), LocatorUtils.property(thatLocator, "boundServiceList", rhsBoundServiceList), lhsBoundServiceList, rhsBoundServiceList)) {
                return false;
            }
        }
        {
            CallingCircleParameterDetail lhsCallingCircleParameter;
            lhsCallingCircleParameter = this.getCallingCircleParameter();
            CallingCircleParameterDetail rhsCallingCircleParameter;
            rhsCallingCircleParameter = that.getCallingCircleParameter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "callingCircleParameter", lhsCallingCircleParameter), LocatorUtils.property(thatLocator, "callingCircleParameter", rhsCallingCircleParameter), lhsCallingCircleParameter, rhsCallingCircleParameter)) {
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
            Boolean lhsService911Indicator;
            lhsService911Indicator = this.isService911Indicator();
            Boolean rhsService911Indicator;
            rhsService911Indicator = that.isService911Indicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "service911Indicator", lhsService911Indicator), LocatorUtils.property(thatLocator, "service911Indicator", rhsService911Indicator), lhsService911Indicator, rhsService911Indicator)) {
                return false;
            }
        }
        {
            Boolean lhsNonMscContributionIndicator;
            lhsNonMscContributionIndicator = this.isNonMscContributionIndicator();
            Boolean rhsNonMscContributionIndicator;
            rhsNonMscContributionIndicator = that.isNonMscContributionIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nonMscContributionIndicator", lhsNonMscContributionIndicator), LocatorUtils.property(thatLocator, "nonMscContributionIndicator", rhsNonMscContributionIndicator), lhsNonMscContributionIndicator, rhsNonMscContributionIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsDataSharingServiceIndicator;
            lhsDataSharingServiceIndicator = this.isDataSharingServiceIndicator();
            Boolean rhsDataSharingServiceIndicator;
            rhsDataSharingServiceIndicator = that.isDataSharingServiceIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingServiceIndicator", lhsDataSharingServiceIndicator), LocatorUtils.property(thatLocator, "dataSharingServiceIndicator", rhsDataSharingServiceIndicator), lhsDataSharingServiceIndicator, rhsDataSharingServiceIndicator)) {
                return false;
            }
        }
        {
            List<DataSharingGroupSummary> lhsDataSharingGroupList;
            lhsDataSharingGroupList = (((this.dataSharingGroupList!= null)&&(!this.dataSharingGroupList.isEmpty()))?this.getDataSharingGroupList():null);
            List<DataSharingGroupSummary> rhsDataSharingGroupList;
            rhsDataSharingGroupList = (((that.dataSharingGroupList!= null)&&(!that.dataSharingGroupList.isEmpty()))?that.getDataSharingGroupList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingGroupList", lhsDataSharingGroupList), LocatorUtils.property(thatLocator, "dataSharingGroupList", rhsDataSharingGroupList), lhsDataSharingGroupList, rhsDataSharingGroupList)) {
                return false;
            }
        }
        {
            FeatureRecommendation lhsFeatureRecommendation;
            lhsFeatureRecommendation = this.getFeatureRecommendation();
            FeatureRecommendation rhsFeatureRecommendation;
            rhsFeatureRecommendation = that.getFeatureRecommendation();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "featureRecommendation", lhsFeatureRecommendation), LocatorUtils.property(thatLocator, "featureRecommendation", rhsFeatureRecommendation), lhsFeatureRecommendation, rhsFeatureRecommendation)) {
                return false;
            }
        }
        {
            List<Usage> lhsIncludedAllowanceList;
            lhsIncludedAllowanceList = (((this.includedAllowanceList!= null)&&(!this.includedAllowanceList.isEmpty()))?this.getIncludedAllowanceList():null);
            List<Usage> rhsIncludedAllowanceList;
            rhsIncludedAllowanceList = (((that.includedAllowanceList!= null)&&(!that.includedAllowanceList.isEmpty()))?that.getIncludedAllowanceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedAllowanceList", lhsIncludedAllowanceList), LocatorUtils.property(thatLocator, "includedAllowanceList", rhsIncludedAllowanceList), lhsIncludedAllowanceList, rhsIncludedAllowanceList)) {
                return false;
            }
        }
        {
            Boolean lhsMandatoryDataAddOnInd;
            lhsMandatoryDataAddOnInd = this.isMandatoryDataAddOnInd();
            Boolean rhsMandatoryDataAddOnInd;
            rhsMandatoryDataAddOnInd = that.isMandatoryDataAddOnInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mandatoryDataAddOnInd", lhsMandatoryDataAddOnInd), LocatorUtils.property(thatLocator, "mandatoryDataAddOnInd", rhsMandatoryDataAddOnInd), lhsMandatoryDataAddOnInd, rhsMandatoryDataAddOnInd)) {
                return false;
            }
        }
        {
            List<String> lhsHeadOfficeCallingPhoneNumberList;
            lhsHeadOfficeCallingPhoneNumberList = (((this.headOfficeCallingPhoneNumberList!= null)&&(!this.headOfficeCallingPhoneNumberList.isEmpty()))?this.getHeadOfficeCallingPhoneNumberList():null);
            List<String> rhsHeadOfficeCallingPhoneNumberList;
            rhsHeadOfficeCallingPhoneNumberList = (((that.headOfficeCallingPhoneNumberList!= null)&&(!that.headOfficeCallingPhoneNumberList.isEmpty()))?that.getHeadOfficeCallingPhoneNumberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "headOfficeCallingPhoneNumberList", lhsHeadOfficeCallingPhoneNumberList), LocatorUtils.property(thatLocator, "headOfficeCallingPhoneNumberList", rhsHeadOfficeCallingPhoneNumberList), lhsHeadOfficeCallingPhoneNumberList, rhsHeadOfficeCallingPhoneNumberList)) {
                return false;
            }
        }
        {
            Boolean lhsFutureDateEligibleInd;
            lhsFutureDateEligibleInd = this.isFutureDateEligibleInd();
            Boolean rhsFutureDateEligibleInd;
            rhsFutureDateEligibleInd = that.isFutureDateEligibleInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "futureDateEligibleInd", lhsFutureDateEligibleInd), LocatorUtils.property(thatLocator, "futureDateEligibleInd", rhsFutureDateEligibleInd), lhsFutureDateEligibleInd, rhsFutureDateEligibleInd)) {
                return false;
            }
        }
        {
            Boolean lhsEquipmentWarrantyAddOnInd;
            lhsEquipmentWarrantyAddOnInd = this.isEquipmentWarrantyAddOnInd();
            Boolean rhsEquipmentWarrantyAddOnInd;
            rhsEquipmentWarrantyAddOnInd = that.isEquipmentWarrantyAddOnInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentWarrantyAddOnInd", lhsEquipmentWarrantyAddOnInd), LocatorUtils.property(thatLocator, "equipmentWarrantyAddOnInd", rhsEquipmentWarrantyAddOnInd), lhsEquipmentWarrantyAddOnInd, rhsEquipmentWarrantyAddOnInd)) {
                return false;
            }
        }
        {
            Boolean lhsNonDiscountableIndicator;
            lhsNonDiscountableIndicator = this.isNonDiscountableIndicator();
            Boolean rhsNonDiscountableIndicator;
            rhsNonDiscountableIndicator = that.isNonDiscountableIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nonDiscountableIndicator", lhsNonDiscountableIndicator), LocatorUtils.property(thatLocator, "nonDiscountableIndicator", rhsNonDiscountableIndicator), lhsNonDiscountableIndicator, rhsNonDiscountableIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsInternationalDialingIndicator;
            lhsInternationalDialingIndicator = this.isInternationalDialingIndicator();
            Boolean rhsInternationalDialingIndicator;
            rhsInternationalDialingIndicator = that.isInternationalDialingIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "internationalDialingIndicator", lhsInternationalDialingIndicator), LocatorUtils.property(thatLocator, "internationalDialingIndicator", rhsInternationalDialingIndicator), lhsInternationalDialingIndicator, rhsInternationalDialingIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsInternationalRoamingIndicator;
            lhsInternationalRoamingIndicator = this.isInternationalRoamingIndicator();
            Boolean rhsInternationalRoamingIndicator;
            rhsInternationalRoamingIndicator = that.isInternationalRoamingIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "internationalRoamingIndicator", lhsInternationalRoamingIndicator), LocatorUtils.property(thatLocator, "internationalRoamingIndicator", rhsInternationalRoamingIndicator), lhsInternationalRoamingIndicator, rhsInternationalRoamingIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsInternationalServiceIndicator;
            lhsInternationalServiceIndicator = this.isInternationalServiceIndicator();
            Boolean rhsInternationalServiceIndicator;
            rhsInternationalServiceIndicator = that.isInternationalServiceIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "internationalServiceIndicator", lhsInternationalServiceIndicator), LocatorUtils.property(thatLocator, "internationalServiceIndicator", rhsInternationalServiceIndicator), lhsInternationalServiceIndicator, rhsInternationalServiceIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsAllowPreselectIndicator;
            lhsAllowPreselectIndicator = this.isAllowPreselectIndicator();
            Boolean rhsAllowPreselectIndicator;
            rhsAllowPreselectIndicator = that.isAllowPreselectIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allowPreselectIndicator", lhsAllowPreselectIndicator), LocatorUtils.property(thatLocator, "allowPreselectIndicator", rhsAllowPreselectIndicator), lhsAllowPreselectIndicator, rhsAllowPreselectIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsRecommendedIndicator;
            lhsRecommendedIndicator = this.isRecommendedIndicator();
            Boolean rhsRecommendedIndicator;
            rhsRecommendedIndicator = that.isRecommendedIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendedIndicator", lhsRecommendedIndicator), LocatorUtils.property(thatLocator, "recommendedIndicator", rhsRecommendedIndicator), lhsRecommendedIndicator, rhsRecommendedIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsBusinessAppServiceIndicator;
            lhsBusinessAppServiceIndicator = this.isBusinessAppServiceIndicator();
            Boolean rhsBusinessAppServiceIndicator;
            rhsBusinessAppServiceIndicator = that.isBusinessAppServiceIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessAppServiceIndicator", lhsBusinessAppServiceIndicator), LocatorUtils.property(thatLocator, "businessAppServiceIndicator", rhsBusinessAppServiceIndicator), lhsBusinessAppServiceIndicator, rhsBusinessAppServiceIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsBillChargeComplianceServiceInd;
            lhsBillChargeComplianceServiceInd = this.isBillChargeComplianceServiceInd();
            Boolean rhsBillChargeComplianceServiceInd;
            rhsBillChargeComplianceServiceInd = that.isBillChargeComplianceServiceInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billChargeComplianceServiceInd", lhsBillChargeComplianceServiceInd), LocatorUtils.property(thatLocator, "billChargeComplianceServiceInd", rhsBillChargeComplianceServiceInd), lhsBillChargeComplianceServiceInd, rhsBillChargeComplianceServiceInd)) {
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
