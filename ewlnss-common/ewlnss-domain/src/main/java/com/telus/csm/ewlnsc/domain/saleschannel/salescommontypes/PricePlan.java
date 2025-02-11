
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
import com.telus.tmi.xmlschema.xsd.service.basetypes.serviceorderreferencetypes_v5.RatedFeature;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PricePlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricePlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricePlanCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="pricePlanDescriptionTextList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList"/>
 *         &lt;element name="pricePlanAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pricePlanCategoryList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PricePlanCategoryList" minOccurs="0"/>
 *         &lt;element name="pricePlanDetailURLList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PricePlanDetailURLList" minOccurs="0"/>
 *         &lt;element name="validTermInMonthsList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="existingPricePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="futureDatedPricePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="grandfatherPricePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="customizedPricePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="offerPricePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="businessAnywherePricePlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowCarryoverIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="allowKeepExistingPlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mustChooseIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="nonDiscountableIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="includedAllowanceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Usage" maxOccurs="9" minOccurs="0"/>
 *         &lt;element name="callingCircleParameter" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CallingCircleParameterDetail" minOccurs="0"/>
 *         &lt;element name="seatTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="includedWirelessBackupInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="breakContractEnforcementIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="availableForSaleIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="dataSharingIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="dataSharingGroupList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DataSharingGroupSummary" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="includedFeatureList" type="{http://xmlschema.tmi.telus.com/xsd/Service/BaseTypes/ServiceOrderReferenceTypes_v5}RatedFeature" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="flexPlanInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="serviceEdition" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceEdition" minOccurs="0"/>
 *         &lt;element name="recommendedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="comboPlanIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricePlan", propOrder = {
    "pricePlanCode",
    "pricePlanDescriptionTextList",
    "pricePlanAmount",
    "pricePlanCategoryList",
    "pricePlanDetailURLList",
    "validTermInMonthsList",
    "existingPricePlanIndicator",
    "futureDatedPricePlanIndicator",
    "grandfatherPricePlanIndicator",
    "customizedPricePlanIndicator",
    "offerPricePlanIndicator",
    "businessAnywherePricePlanIndicator",
    "allowCarryoverIndicator",
    "allowKeepExistingPlanIndicator",
    "mustChooseIndicator",
    "nonDiscountableIndicator",
    "includedAllowanceList",
    "callingCircleParameter",
    "seatTypeCode",
    "includedWirelessBackupInd",
    "breakContractEnforcementIndicator",
    "availableForSaleIndicator",
    "dataSharingIndicator",
    "dataSharingGroupList",
    "includedFeatureList",
    "flexPlanInd",
    "serviceEdition",
    "recommendedIndicator",
    "comboPlanIndicator"
})
public class PricePlan
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String pricePlanCode;
    @XmlElement(required = true)
    protected MultilingualCodeDescTextList pricePlanDescriptionTextList;
    protected double pricePlanAmount;
    protected PricePlanCategoryList pricePlanCategoryList;
    protected PricePlanDetailURLList pricePlanDetailURLList;
    protected List<BigInteger> validTermInMonthsList;
    protected Boolean existingPricePlanIndicator;
    protected Boolean futureDatedPricePlanIndicator;
    protected Boolean grandfatherPricePlanIndicator;
    protected Boolean customizedPricePlanIndicator;
    protected Boolean offerPricePlanIndicator;
    protected Boolean businessAnywherePricePlanIndicator;
    protected Boolean allowCarryoverIndicator;
    protected Boolean allowKeepExistingPlanIndicator;
    protected Boolean mustChooseIndicator;
    protected Boolean nonDiscountableIndicator;
    protected List<Usage> includedAllowanceList;
    protected CallingCircleParameterDetail callingCircleParameter;
    protected String seatTypeCode;
    protected Boolean includedWirelessBackupInd;
    protected Boolean breakContractEnforcementIndicator;
    protected boolean availableForSaleIndicator;
    protected Boolean dataSharingIndicator;
    protected List<DataSharingGroupSummary> dataSharingGroupList;
    protected List<RatedFeature> includedFeatureList;
    protected Boolean flexPlanInd;
    protected ServiceEdition serviceEdition;
    protected Boolean recommendedIndicator;
    protected Boolean comboPlanIndicator;

    /**
     * Gets the value of the pricePlanCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPricePlanCode() {
        return pricePlanCode;
    }

    /**
     * Sets the value of the pricePlanCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanCode(String value) {
        this.pricePlanCode = value;
    }

    /**
     * Gets the value of the pricePlanDescriptionTextList property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getPricePlanDescriptionTextList() {
        return pricePlanDescriptionTextList;
    }

    /**
     * Sets the value of the pricePlanDescriptionTextList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setPricePlanDescriptionTextList(MultilingualCodeDescTextList value) {
        this.pricePlanDescriptionTextList = value;
    }

    /**
     * Gets the value of the pricePlanAmount property.
     * 
     */
    public double getPricePlanAmount() {
        return pricePlanAmount;
    }

    /**
     * Sets the value of the pricePlanAmount property.
     * 
     */
    public void setPricePlanAmount(double value) {
        this.pricePlanAmount = value;
    }

    /**
     * Gets the value of the pricePlanCategoryList property.
     * 
     * @return
     *     possible object is
     *     {@link PricePlanCategoryList }
     *     
     */
    public PricePlanCategoryList getPricePlanCategoryList() {
        return pricePlanCategoryList;
    }

    /**
     * Sets the value of the pricePlanCategoryList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePlanCategoryList }
     *     
     */
    public void setPricePlanCategoryList(PricePlanCategoryList value) {
        this.pricePlanCategoryList = value;
    }

    /**
     * Gets the value of the pricePlanDetailURLList property.
     * 
     * @return
     *     possible object is
     *     {@link PricePlanDetailURLList }
     *     
     */
    public PricePlanDetailURLList getPricePlanDetailURLList() {
        return pricePlanDetailURLList;
    }

    /**
     * Sets the value of the pricePlanDetailURLList property.
     * 
     * @param value
     *     allowed object is
     *     {@link PricePlanDetailURLList }
     *     
     */
    public void setPricePlanDetailURLList(PricePlanDetailURLList value) {
        this.pricePlanDetailURLList = value;
    }

    /**
     * Gets the value of the validTermInMonthsList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validTermInMonthsList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidTermInMonthsList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getValidTermInMonthsList() {
        if (validTermInMonthsList == null) {
            validTermInMonthsList = new ArrayList<BigInteger>();
        }
        return this.validTermInMonthsList;
    }

    /**
     * Gets the value of the existingPricePlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isExistingPricePlanIndicator() {
        return existingPricePlanIndicator;
    }

    /**
     * Sets the value of the existingPricePlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setExistingPricePlanIndicator(Boolean value) {
        this.existingPricePlanIndicator = value;
    }

    /**
     * Gets the value of the futureDatedPricePlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFutureDatedPricePlanIndicator() {
        return futureDatedPricePlanIndicator;
    }

    /**
     * Sets the value of the futureDatedPricePlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFutureDatedPricePlanIndicator(Boolean value) {
        this.futureDatedPricePlanIndicator = value;
    }

    /**
     * Gets the value of the grandfatherPricePlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isGrandfatherPricePlanIndicator() {
        return grandfatherPricePlanIndicator;
    }

    /**
     * Sets the value of the grandfatherPricePlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setGrandfatherPricePlanIndicator(Boolean value) {
        this.grandfatherPricePlanIndicator = value;
    }

    /**
     * Gets the value of the customizedPricePlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCustomizedPricePlanIndicator() {
        return customizedPricePlanIndicator;
    }

    /**
     * Sets the value of the customizedPricePlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCustomizedPricePlanIndicator(Boolean value) {
        this.customizedPricePlanIndicator = value;
    }

    /**
     * Gets the value of the offerPricePlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOfferPricePlanIndicator() {
        return offerPricePlanIndicator;
    }

    /**
     * Sets the value of the offerPricePlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOfferPricePlanIndicator(Boolean value) {
        this.offerPricePlanIndicator = value;
    }

    /**
     * Gets the value of the businessAnywherePricePlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBusinessAnywherePricePlanIndicator() {
        return businessAnywherePricePlanIndicator;
    }

    /**
     * Sets the value of the businessAnywherePricePlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBusinessAnywherePricePlanIndicator(Boolean value) {
        this.businessAnywherePricePlanIndicator = value;
    }

    /**
     * Gets the value of the allowCarryoverIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowCarryoverIndicator() {
        return allowCarryoverIndicator;
    }

    /**
     * Sets the value of the allowCarryoverIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowCarryoverIndicator(Boolean value) {
        this.allowCarryoverIndicator = value;
    }

    /**
     * Gets the value of the allowKeepExistingPlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAllowKeepExistingPlanIndicator() {
        return allowKeepExistingPlanIndicator;
    }

    /**
     * Sets the value of the allowKeepExistingPlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAllowKeepExistingPlanIndicator(Boolean value) {
        this.allowKeepExistingPlanIndicator = value;
    }

    /**
     * Gets the value of the mustChooseIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMustChooseIndicator() {
        return mustChooseIndicator;
    }

    /**
     * Sets the value of the mustChooseIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMustChooseIndicator(Boolean value) {
        this.mustChooseIndicator = value;
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
     * Gets the value of the seatTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatTypeCode() {
        return seatTypeCode;
    }

    /**
     * Sets the value of the seatTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatTypeCode(String value) {
        this.seatTypeCode = value;
    }

    /**
     * Gets the value of the includedWirelessBackupInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIncludedWirelessBackupInd() {
        return includedWirelessBackupInd;
    }

    /**
     * Sets the value of the includedWirelessBackupInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIncludedWirelessBackupInd(Boolean value) {
        this.includedWirelessBackupInd = value;
    }

    /**
     * Gets the value of the breakContractEnforcementIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBreakContractEnforcementIndicator() {
        return breakContractEnforcementIndicator;
    }

    /**
     * Sets the value of the breakContractEnforcementIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBreakContractEnforcementIndicator(Boolean value) {
        this.breakContractEnforcementIndicator = value;
    }

    /**
     * Gets the value of the availableForSaleIndicator property.
     * 
     */
    public boolean isAvailableForSaleIndicator() {
        return availableForSaleIndicator;
    }

    /**
     * Sets the value of the availableForSaleIndicator property.
     * 
     */
    public void setAvailableForSaleIndicator(boolean value) {
        this.availableForSaleIndicator = value;
    }

    /**
     * Gets the value of the dataSharingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDataSharingIndicator() {
        return dataSharingIndicator;
    }

    /**
     * Sets the value of the dataSharingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDataSharingIndicator(Boolean value) {
        this.dataSharingIndicator = value;
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
     * Gets the value of the includedFeatureList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the includedFeatureList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncludedFeatureList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RatedFeature }
     * 
     * 
     */
    public List<RatedFeature> getIncludedFeatureList() {
        if (includedFeatureList == null) {
            includedFeatureList = new ArrayList<RatedFeature>();
        }
        return this.includedFeatureList;
    }

    /**
     * Gets the value of the flexPlanInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isFlexPlanInd() {
        return flexPlanInd;
    }

    /**
     * Sets the value of the flexPlanInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setFlexPlanInd(Boolean value) {
        this.flexPlanInd = value;
    }

    /**
     * Gets the value of the serviceEdition property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEdition }
     *     
     */
    public ServiceEdition getServiceEdition() {
        return serviceEdition;
    }

    /**
     * Sets the value of the serviceEdition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEdition }
     *     
     */
    public void setServiceEdition(ServiceEdition value) {
        this.serviceEdition = value;
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
     * Gets the value of the comboPlanIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComboPlanIndicator() {
        return comboPlanIndicator;
    }

    /**
     * Sets the value of the comboPlanIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComboPlanIndicator(Boolean value) {
        this.comboPlanIndicator = value;
    }

    /**
     * Sets the value of the validTermInMonthsList property.
     * 
     * @param validTermInMonthsList
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidTermInMonthsList(List<BigInteger> validTermInMonthsList) {
        this.validTermInMonthsList = validTermInMonthsList;
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
     * Sets the value of the includedFeatureList property.
     * 
     * @param includedFeatureList
     *     allowed object is
     *     {@link RatedFeature }
     *     
     */
    public void setIncludedFeatureList(List<RatedFeature> includedFeatureList) {
        this.includedFeatureList = includedFeatureList;
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
            String thePricePlanCode;
            thePricePlanCode = this.getPricePlanCode();
            strategy.appendField(locator, this, "pricePlanCode", buffer, thePricePlanCode);
        }
        {
            MultilingualCodeDescTextList thePricePlanDescriptionTextList;
            thePricePlanDescriptionTextList = this.getPricePlanDescriptionTextList();
            strategy.appendField(locator, this, "pricePlanDescriptionTextList", buffer, thePricePlanDescriptionTextList);
        }
        {
            double thePricePlanAmount;
            thePricePlanAmount = (true?this.getPricePlanAmount(): 0.0D);
            strategy.appendField(locator, this, "pricePlanAmount", buffer, thePricePlanAmount);
        }
        {
            PricePlanCategoryList thePricePlanCategoryList;
            thePricePlanCategoryList = this.getPricePlanCategoryList();
            strategy.appendField(locator, this, "pricePlanCategoryList", buffer, thePricePlanCategoryList);
        }
        {
            PricePlanDetailURLList thePricePlanDetailURLList;
            thePricePlanDetailURLList = this.getPricePlanDetailURLList();
            strategy.appendField(locator, this, "pricePlanDetailURLList", buffer, thePricePlanDetailURLList);
        }
        {
            List<BigInteger> theValidTermInMonthsList;
            theValidTermInMonthsList = (((this.validTermInMonthsList!= null)&&(!this.validTermInMonthsList.isEmpty()))?this.getValidTermInMonthsList():null);
            strategy.appendField(locator, this, "validTermInMonthsList", buffer, theValidTermInMonthsList);
        }
        {
            Boolean theExistingPricePlanIndicator;
            theExistingPricePlanIndicator = this.isExistingPricePlanIndicator();
            strategy.appendField(locator, this, "existingPricePlanIndicator", buffer, theExistingPricePlanIndicator);
        }
        {
            Boolean theFutureDatedPricePlanIndicator;
            theFutureDatedPricePlanIndicator = this.isFutureDatedPricePlanIndicator();
            strategy.appendField(locator, this, "futureDatedPricePlanIndicator", buffer, theFutureDatedPricePlanIndicator);
        }
        {
            Boolean theGrandfatherPricePlanIndicator;
            theGrandfatherPricePlanIndicator = this.isGrandfatherPricePlanIndicator();
            strategy.appendField(locator, this, "grandfatherPricePlanIndicator", buffer, theGrandfatherPricePlanIndicator);
        }
        {
            Boolean theCustomizedPricePlanIndicator;
            theCustomizedPricePlanIndicator = this.isCustomizedPricePlanIndicator();
            strategy.appendField(locator, this, "customizedPricePlanIndicator", buffer, theCustomizedPricePlanIndicator);
        }
        {
            Boolean theOfferPricePlanIndicator;
            theOfferPricePlanIndicator = this.isOfferPricePlanIndicator();
            strategy.appendField(locator, this, "offerPricePlanIndicator", buffer, theOfferPricePlanIndicator);
        }
        {
            Boolean theBusinessAnywherePricePlanIndicator;
            theBusinessAnywherePricePlanIndicator = this.isBusinessAnywherePricePlanIndicator();
            strategy.appendField(locator, this, "businessAnywherePricePlanIndicator", buffer, theBusinessAnywherePricePlanIndicator);
        }
        {
            Boolean theAllowCarryoverIndicator;
            theAllowCarryoverIndicator = this.isAllowCarryoverIndicator();
            strategy.appendField(locator, this, "allowCarryoverIndicator", buffer, theAllowCarryoverIndicator);
        }
        {
            Boolean theAllowKeepExistingPlanIndicator;
            theAllowKeepExistingPlanIndicator = this.isAllowKeepExistingPlanIndicator();
            strategy.appendField(locator, this, "allowKeepExistingPlanIndicator", buffer, theAllowKeepExistingPlanIndicator);
        }
        {
            Boolean theMustChooseIndicator;
            theMustChooseIndicator = this.isMustChooseIndicator();
            strategy.appendField(locator, this, "mustChooseIndicator", buffer, theMustChooseIndicator);
        }
        {
            Boolean theNonDiscountableIndicator;
            theNonDiscountableIndicator = this.isNonDiscountableIndicator();
            strategy.appendField(locator, this, "nonDiscountableIndicator", buffer, theNonDiscountableIndicator);
        }
        {
            List<Usage> theIncludedAllowanceList;
            theIncludedAllowanceList = (((this.includedAllowanceList!= null)&&(!this.includedAllowanceList.isEmpty()))?this.getIncludedAllowanceList():null);
            strategy.appendField(locator, this, "includedAllowanceList", buffer, theIncludedAllowanceList);
        }
        {
            CallingCircleParameterDetail theCallingCircleParameter;
            theCallingCircleParameter = this.getCallingCircleParameter();
            strategy.appendField(locator, this, "callingCircleParameter", buffer, theCallingCircleParameter);
        }
        {
            String theSeatTypeCode;
            theSeatTypeCode = this.getSeatTypeCode();
            strategy.appendField(locator, this, "seatTypeCode", buffer, theSeatTypeCode);
        }
        {
            Boolean theIncludedWirelessBackupInd;
            theIncludedWirelessBackupInd = this.isIncludedWirelessBackupInd();
            strategy.appendField(locator, this, "includedWirelessBackupInd", buffer, theIncludedWirelessBackupInd);
        }
        {
            Boolean theBreakContractEnforcementIndicator;
            theBreakContractEnforcementIndicator = this.isBreakContractEnforcementIndicator();
            strategy.appendField(locator, this, "breakContractEnforcementIndicator", buffer, theBreakContractEnforcementIndicator);
        }
        {
            boolean theAvailableForSaleIndicator;
            theAvailableForSaleIndicator = (true?this.isAvailableForSaleIndicator():false);
            strategy.appendField(locator, this, "availableForSaleIndicator", buffer, theAvailableForSaleIndicator);
        }
        {
            Boolean theDataSharingIndicator;
            theDataSharingIndicator = this.isDataSharingIndicator();
            strategy.appendField(locator, this, "dataSharingIndicator", buffer, theDataSharingIndicator);
        }
        {
            List<DataSharingGroupSummary> theDataSharingGroupList;
            theDataSharingGroupList = (((this.dataSharingGroupList!= null)&&(!this.dataSharingGroupList.isEmpty()))?this.getDataSharingGroupList():null);
            strategy.appendField(locator, this, "dataSharingGroupList", buffer, theDataSharingGroupList);
        }
        {
            List<RatedFeature> theIncludedFeatureList;
            theIncludedFeatureList = (((this.includedFeatureList!= null)&&(!this.includedFeatureList.isEmpty()))?this.getIncludedFeatureList():null);
            strategy.appendField(locator, this, "includedFeatureList", buffer, theIncludedFeatureList);
        }
        {
            Boolean theFlexPlanInd;
            theFlexPlanInd = this.isFlexPlanInd();
            strategy.appendField(locator, this, "flexPlanInd", buffer, theFlexPlanInd);
        }
        {
            ServiceEdition theServiceEdition;
            theServiceEdition = this.getServiceEdition();
            strategy.appendField(locator, this, "serviceEdition", buffer, theServiceEdition);
        }
        {
            Boolean theRecommendedIndicator;
            theRecommendedIndicator = this.isRecommendedIndicator();
            strategy.appendField(locator, this, "recommendedIndicator", buffer, theRecommendedIndicator);
        }
        {
            Boolean theComboPlanIndicator;
            theComboPlanIndicator = this.isComboPlanIndicator();
            strategy.appendField(locator, this, "comboPlanIndicator", buffer, theComboPlanIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PricePlan)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PricePlan that = ((PricePlan) object);
        {
            String lhsPricePlanCode;
            lhsPricePlanCode = this.getPricePlanCode();
            String rhsPricePlanCode;
            rhsPricePlanCode = that.getPricePlanCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCode", lhsPricePlanCode), LocatorUtils.property(thatLocator, "pricePlanCode", rhsPricePlanCode), lhsPricePlanCode, rhsPricePlanCode)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsPricePlanDescriptionTextList;
            lhsPricePlanDescriptionTextList = this.getPricePlanDescriptionTextList();
            MultilingualCodeDescTextList rhsPricePlanDescriptionTextList;
            rhsPricePlanDescriptionTextList = that.getPricePlanDescriptionTextList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanDescriptionTextList", lhsPricePlanDescriptionTextList), LocatorUtils.property(thatLocator, "pricePlanDescriptionTextList", rhsPricePlanDescriptionTextList), lhsPricePlanDescriptionTextList, rhsPricePlanDescriptionTextList)) {
                return false;
            }
        }
        {
            double lhsPricePlanAmount;
            lhsPricePlanAmount = (true?this.getPricePlanAmount(): 0.0D);
            double rhsPricePlanAmount;
            rhsPricePlanAmount = (true?that.getPricePlanAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanAmount", lhsPricePlanAmount), LocatorUtils.property(thatLocator, "pricePlanAmount", rhsPricePlanAmount), lhsPricePlanAmount, rhsPricePlanAmount)) {
                return false;
            }
        }
        {
            PricePlanCategoryList lhsPricePlanCategoryList;
            lhsPricePlanCategoryList = this.getPricePlanCategoryList();
            PricePlanCategoryList rhsPricePlanCategoryList;
            rhsPricePlanCategoryList = that.getPricePlanCategoryList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCategoryList", lhsPricePlanCategoryList), LocatorUtils.property(thatLocator, "pricePlanCategoryList", rhsPricePlanCategoryList), lhsPricePlanCategoryList, rhsPricePlanCategoryList)) {
                return false;
            }
        }
        {
            PricePlanDetailURLList lhsPricePlanDetailURLList;
            lhsPricePlanDetailURLList = this.getPricePlanDetailURLList();
            PricePlanDetailURLList rhsPricePlanDetailURLList;
            rhsPricePlanDetailURLList = that.getPricePlanDetailURLList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanDetailURLList", lhsPricePlanDetailURLList), LocatorUtils.property(thatLocator, "pricePlanDetailURLList", rhsPricePlanDetailURLList), lhsPricePlanDetailURLList, rhsPricePlanDetailURLList)) {
                return false;
            }
        }
        {
            List<BigInteger> lhsValidTermInMonthsList;
            lhsValidTermInMonthsList = (((this.validTermInMonthsList!= null)&&(!this.validTermInMonthsList.isEmpty()))?this.getValidTermInMonthsList():null);
            List<BigInteger> rhsValidTermInMonthsList;
            rhsValidTermInMonthsList = (((that.validTermInMonthsList!= null)&&(!that.validTermInMonthsList.isEmpty()))?that.getValidTermInMonthsList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validTermInMonthsList", lhsValidTermInMonthsList), LocatorUtils.property(thatLocator, "validTermInMonthsList", rhsValidTermInMonthsList), lhsValidTermInMonthsList, rhsValidTermInMonthsList)) {
                return false;
            }
        }
        {
            Boolean lhsExistingPricePlanIndicator;
            lhsExistingPricePlanIndicator = this.isExistingPricePlanIndicator();
            Boolean rhsExistingPricePlanIndicator;
            rhsExistingPricePlanIndicator = that.isExistingPricePlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "existingPricePlanIndicator", lhsExistingPricePlanIndicator), LocatorUtils.property(thatLocator, "existingPricePlanIndicator", rhsExistingPricePlanIndicator), lhsExistingPricePlanIndicator, rhsExistingPricePlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsFutureDatedPricePlanIndicator;
            lhsFutureDatedPricePlanIndicator = this.isFutureDatedPricePlanIndicator();
            Boolean rhsFutureDatedPricePlanIndicator;
            rhsFutureDatedPricePlanIndicator = that.isFutureDatedPricePlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "futureDatedPricePlanIndicator", lhsFutureDatedPricePlanIndicator), LocatorUtils.property(thatLocator, "futureDatedPricePlanIndicator", rhsFutureDatedPricePlanIndicator), lhsFutureDatedPricePlanIndicator, rhsFutureDatedPricePlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsGrandfatherPricePlanIndicator;
            lhsGrandfatherPricePlanIndicator = this.isGrandfatherPricePlanIndicator();
            Boolean rhsGrandfatherPricePlanIndicator;
            rhsGrandfatherPricePlanIndicator = that.isGrandfatherPricePlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "grandfatherPricePlanIndicator", lhsGrandfatherPricePlanIndicator), LocatorUtils.property(thatLocator, "grandfatherPricePlanIndicator", rhsGrandfatherPricePlanIndicator), lhsGrandfatherPricePlanIndicator, rhsGrandfatherPricePlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsCustomizedPricePlanIndicator;
            lhsCustomizedPricePlanIndicator = this.isCustomizedPricePlanIndicator();
            Boolean rhsCustomizedPricePlanIndicator;
            rhsCustomizedPricePlanIndicator = that.isCustomizedPricePlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customizedPricePlanIndicator", lhsCustomizedPricePlanIndicator), LocatorUtils.property(thatLocator, "customizedPricePlanIndicator", rhsCustomizedPricePlanIndicator), lhsCustomizedPricePlanIndicator, rhsCustomizedPricePlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsOfferPricePlanIndicator;
            lhsOfferPricePlanIndicator = this.isOfferPricePlanIndicator();
            Boolean rhsOfferPricePlanIndicator;
            rhsOfferPricePlanIndicator = that.isOfferPricePlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerPricePlanIndicator", lhsOfferPricePlanIndicator), LocatorUtils.property(thatLocator, "offerPricePlanIndicator", rhsOfferPricePlanIndicator), lhsOfferPricePlanIndicator, rhsOfferPricePlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsBusinessAnywherePricePlanIndicator;
            lhsBusinessAnywherePricePlanIndicator = this.isBusinessAnywherePricePlanIndicator();
            Boolean rhsBusinessAnywherePricePlanIndicator;
            rhsBusinessAnywherePricePlanIndicator = that.isBusinessAnywherePricePlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessAnywherePricePlanIndicator", lhsBusinessAnywherePricePlanIndicator), LocatorUtils.property(thatLocator, "businessAnywherePricePlanIndicator", rhsBusinessAnywherePricePlanIndicator), lhsBusinessAnywherePricePlanIndicator, rhsBusinessAnywherePricePlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsAllowCarryoverIndicator;
            lhsAllowCarryoverIndicator = this.isAllowCarryoverIndicator();
            Boolean rhsAllowCarryoverIndicator;
            rhsAllowCarryoverIndicator = that.isAllowCarryoverIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allowCarryoverIndicator", lhsAllowCarryoverIndicator), LocatorUtils.property(thatLocator, "allowCarryoverIndicator", rhsAllowCarryoverIndicator), lhsAllowCarryoverIndicator, rhsAllowCarryoverIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsAllowKeepExistingPlanIndicator;
            lhsAllowKeepExistingPlanIndicator = this.isAllowKeepExistingPlanIndicator();
            Boolean rhsAllowKeepExistingPlanIndicator;
            rhsAllowKeepExistingPlanIndicator = that.isAllowKeepExistingPlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "allowKeepExistingPlanIndicator", lhsAllowKeepExistingPlanIndicator), LocatorUtils.property(thatLocator, "allowKeepExistingPlanIndicator", rhsAllowKeepExistingPlanIndicator), lhsAllowKeepExistingPlanIndicator, rhsAllowKeepExistingPlanIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsMustChooseIndicator;
            lhsMustChooseIndicator = this.isMustChooseIndicator();
            Boolean rhsMustChooseIndicator;
            rhsMustChooseIndicator = that.isMustChooseIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mustChooseIndicator", lhsMustChooseIndicator), LocatorUtils.property(thatLocator, "mustChooseIndicator", rhsMustChooseIndicator), lhsMustChooseIndicator, rhsMustChooseIndicator)) {
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
            List<Usage> lhsIncludedAllowanceList;
            lhsIncludedAllowanceList = (((this.includedAllowanceList!= null)&&(!this.includedAllowanceList.isEmpty()))?this.getIncludedAllowanceList():null);
            List<Usage> rhsIncludedAllowanceList;
            rhsIncludedAllowanceList = (((that.includedAllowanceList!= null)&&(!that.includedAllowanceList.isEmpty()))?that.getIncludedAllowanceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedAllowanceList", lhsIncludedAllowanceList), LocatorUtils.property(thatLocator, "includedAllowanceList", rhsIncludedAllowanceList), lhsIncludedAllowanceList, rhsIncludedAllowanceList)) {
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
            String lhsSeatTypeCode;
            lhsSeatTypeCode = this.getSeatTypeCode();
            String rhsSeatTypeCode;
            rhsSeatTypeCode = that.getSeatTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seatTypeCode", lhsSeatTypeCode), LocatorUtils.property(thatLocator, "seatTypeCode", rhsSeatTypeCode), lhsSeatTypeCode, rhsSeatTypeCode)) {
                return false;
            }
        }
        {
            Boolean lhsIncludedWirelessBackupInd;
            lhsIncludedWirelessBackupInd = this.isIncludedWirelessBackupInd();
            Boolean rhsIncludedWirelessBackupInd;
            rhsIncludedWirelessBackupInd = that.isIncludedWirelessBackupInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedWirelessBackupInd", lhsIncludedWirelessBackupInd), LocatorUtils.property(thatLocator, "includedWirelessBackupInd", rhsIncludedWirelessBackupInd), lhsIncludedWirelessBackupInd, rhsIncludedWirelessBackupInd)) {
                return false;
            }
        }
        {
            Boolean lhsBreakContractEnforcementIndicator;
            lhsBreakContractEnforcementIndicator = this.isBreakContractEnforcementIndicator();
            Boolean rhsBreakContractEnforcementIndicator;
            rhsBreakContractEnforcementIndicator = that.isBreakContractEnforcementIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "breakContractEnforcementIndicator", lhsBreakContractEnforcementIndicator), LocatorUtils.property(thatLocator, "breakContractEnforcementIndicator", rhsBreakContractEnforcementIndicator), lhsBreakContractEnforcementIndicator, rhsBreakContractEnforcementIndicator)) {
                return false;
            }
        }
        {
            boolean lhsAvailableForSaleIndicator;
            lhsAvailableForSaleIndicator = (true?this.isAvailableForSaleIndicator():false);
            boolean rhsAvailableForSaleIndicator;
            rhsAvailableForSaleIndicator = (true?that.isAvailableForSaleIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "availableForSaleIndicator", lhsAvailableForSaleIndicator), LocatorUtils.property(thatLocator, "availableForSaleIndicator", rhsAvailableForSaleIndicator), lhsAvailableForSaleIndicator, rhsAvailableForSaleIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsDataSharingIndicator;
            lhsDataSharingIndicator = this.isDataSharingIndicator();
            Boolean rhsDataSharingIndicator;
            rhsDataSharingIndicator = that.isDataSharingIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingIndicator", lhsDataSharingIndicator), LocatorUtils.property(thatLocator, "dataSharingIndicator", rhsDataSharingIndicator), lhsDataSharingIndicator, rhsDataSharingIndicator)) {
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
            List<RatedFeature> lhsIncludedFeatureList;
            lhsIncludedFeatureList = (((this.includedFeatureList!= null)&&(!this.includedFeatureList.isEmpty()))?this.getIncludedFeatureList():null);
            List<RatedFeature> rhsIncludedFeatureList;
            rhsIncludedFeatureList = (((that.includedFeatureList!= null)&&(!that.includedFeatureList.isEmpty()))?that.getIncludedFeatureList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedFeatureList", lhsIncludedFeatureList), LocatorUtils.property(thatLocator, "includedFeatureList", rhsIncludedFeatureList), lhsIncludedFeatureList, rhsIncludedFeatureList)) {
                return false;
            }
        }
        {
            Boolean lhsFlexPlanInd;
            lhsFlexPlanInd = this.isFlexPlanInd();
            Boolean rhsFlexPlanInd;
            rhsFlexPlanInd = that.isFlexPlanInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "flexPlanInd", lhsFlexPlanInd), LocatorUtils.property(thatLocator, "flexPlanInd", rhsFlexPlanInd), lhsFlexPlanInd, rhsFlexPlanInd)) {
                return false;
            }
        }
        {
            ServiceEdition lhsServiceEdition;
            lhsServiceEdition = this.getServiceEdition();
            ServiceEdition rhsServiceEdition;
            rhsServiceEdition = that.getServiceEdition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEdition", lhsServiceEdition), LocatorUtils.property(thatLocator, "serviceEdition", rhsServiceEdition), lhsServiceEdition, rhsServiceEdition)) {
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
            Boolean lhsComboPlanIndicator;
            lhsComboPlanIndicator = this.isComboPlanIndicator();
            Boolean rhsComboPlanIndicator;
            rhsComboPlanIndicator = that.isComboPlanIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "comboPlanIndicator", lhsComboPlanIndicator), LocatorUtils.property(thatLocator, "comboPlanIndicator", rhsComboPlanIndicator), lhsComboPlanIndicator, rhsComboPlanIndicator)) {
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
