
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for WirelessOfferFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homeCityCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="marketProvinceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subMarketCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="accountSegementCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberSegmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="contractTerm" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="clientOwnedEquipmentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="npaNxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="activationPortInIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="corporateEntity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateEntity" minOccurs="0"/>
 *         &lt;element name="selectedCreditProgram" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditProgramDetail" minOccurs="0"/>
 *         &lt;element name="promotionIdentifierList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PromotionIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessOfferFilter", propOrder = {
    "accountTypeCode",
    "accountSubTypeCode",
    "homeProvinceCode",
    "homeCityCode",
    "marketProvinceCode",
    "subMarketCode",
    "accountSegementCode",
    "subscriberSegmentCode",
    "productCode",
    "contractTerm",
    "clientOwnedEquipmentIndicator",
    "npaNxx",
    "activationPortInIndicator",
    "corporateEntity",
    "selectedCreditProgram",
    "promotionIdentifierList"
})
@XmlSeeAlso({
    WirelessSweetenerFilter.class
})
public class WirelessOfferFilter
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String accountTypeCode;
    protected String accountSubTypeCode;
    protected String homeProvinceCode;
    protected String homeCityCode;
    protected String marketProvinceCode;
    protected String subMarketCode;
    protected String accountSegementCode;
    protected String subscriberSegmentCode;
    protected String productCode;
    protected BigInteger contractTerm;
    protected Boolean clientOwnedEquipmentIndicator;
    protected String npaNxx;
    protected Boolean activationPortInIndicator;
    protected CorporateEntity corporateEntity;
    protected CreditProgramDetail selectedCreditProgram;
    protected List<PromotionIdentifier> promotionIdentifierList;

    /**
     * Gets the value of the accountTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountTypeCode() {
        return accountTypeCode;
    }

    /**
     * Sets the value of the accountTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountTypeCode(String value) {
        this.accountTypeCode = value;
    }

    /**
     * Gets the value of the accountSubTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSubTypeCode() {
        return accountSubTypeCode;
    }

    /**
     * Sets the value of the accountSubTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSubTypeCode(String value) {
        this.accountSubTypeCode = value;
    }

    /**
     * Gets the value of the homeProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeProvinceCode() {
        return homeProvinceCode;
    }

    /**
     * Sets the value of the homeProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeProvinceCode(String value) {
        this.homeProvinceCode = value;
    }

    /**
     * Gets the value of the homeCityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomeCityCode() {
        return homeCityCode;
    }

    /**
     * Sets the value of the homeCityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomeCityCode(String value) {
        this.homeCityCode = value;
    }

    /**
     * Gets the value of the marketProvinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMarketProvinceCode() {
        return marketProvinceCode;
    }

    /**
     * Sets the value of the marketProvinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMarketProvinceCode(String value) {
        this.marketProvinceCode = value;
    }

    /**
     * Gets the value of the subMarketCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubMarketCode() {
        return subMarketCode;
    }

    /**
     * Sets the value of the subMarketCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubMarketCode(String value) {
        this.subMarketCode = value;
    }

    /**
     * Gets the value of the accountSegementCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountSegementCode() {
        return accountSegementCode;
    }

    /**
     * Sets the value of the accountSegementCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountSegementCode(String value) {
        this.accountSegementCode = value;
    }

    /**
     * Gets the value of the subscriberSegmentCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberSegmentCode() {
        return subscriberSegmentCode;
    }

    /**
     * Sets the value of the subscriberSegmentCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberSegmentCode(String value) {
        this.subscriberSegmentCode = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the contractTerm property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getContractTerm() {
        return contractTerm;
    }

    /**
     * Sets the value of the contractTerm property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTerm(BigInteger value) {
        this.contractTerm = value;
    }

    /**
     * Gets the value of the clientOwnedEquipmentIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isClientOwnedEquipmentIndicator() {
        return clientOwnedEquipmentIndicator;
    }

    /**
     * Sets the value of the clientOwnedEquipmentIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setClientOwnedEquipmentIndicator(Boolean value) {
        this.clientOwnedEquipmentIndicator = value;
    }

    /**
     * Gets the value of the npaNxx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpaNxx() {
        return npaNxx;
    }

    /**
     * Sets the value of the npaNxx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpaNxx(String value) {
        this.npaNxx = value;
    }

    /**
     * Gets the value of the activationPortInIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isActivationPortInIndicator() {
        return activationPortInIndicator;
    }

    /**
     * Sets the value of the activationPortInIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setActivationPortInIndicator(Boolean value) {
        this.activationPortInIndicator = value;
    }

    /**
     * Gets the value of the corporateEntity property.
     * 
     * @return
     *     possible object is
     *     {@link CorporateEntity }
     *     
     */
    public CorporateEntity getCorporateEntity() {
        return corporateEntity;
    }

    /**
     * Sets the value of the corporateEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateEntity }
     *     
     */
    public void setCorporateEntity(CorporateEntity value) {
        this.corporateEntity = value;
    }

    /**
     * Gets the value of the selectedCreditProgram property.
     * 
     * @return
     *     possible object is
     *     {@link CreditProgramDetail }
     *     
     */
    public CreditProgramDetail getSelectedCreditProgram() {
        return selectedCreditProgram;
    }

    /**
     * Sets the value of the selectedCreditProgram property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditProgramDetail }
     *     
     */
    public void setSelectedCreditProgram(CreditProgramDetail value) {
        this.selectedCreditProgram = value;
    }

    /**
     * Gets the value of the promotionIdentifierList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the promotionIdentifierList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPromotionIdentifierList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PromotionIdentifier }
     * 
     * 
     */
    public List<PromotionIdentifier> getPromotionIdentifierList() {
        if (promotionIdentifierList == null) {
            promotionIdentifierList = new ArrayList<PromotionIdentifier>();
        }
        return this.promotionIdentifierList;
    }

    /**
     * Sets the value of the promotionIdentifierList property.
     * 
     * @param promotionIdentifierList
     *     allowed object is
     *     {@link PromotionIdentifier }
     *     
     */
    public void setPromotionIdentifierList(List<PromotionIdentifier> promotionIdentifierList) {
        this.promotionIdentifierList = promotionIdentifierList;
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
            String theAccountTypeCode;
            theAccountTypeCode = this.getAccountTypeCode();
            strategy.appendField(locator, this, "accountTypeCode", buffer, theAccountTypeCode);
        }
        {
            String theAccountSubTypeCode;
            theAccountSubTypeCode = this.getAccountSubTypeCode();
            strategy.appendField(locator, this, "accountSubTypeCode", buffer, theAccountSubTypeCode);
        }
        {
            String theHomeProvinceCode;
            theHomeProvinceCode = this.getHomeProvinceCode();
            strategy.appendField(locator, this, "homeProvinceCode", buffer, theHomeProvinceCode);
        }
        {
            String theHomeCityCode;
            theHomeCityCode = this.getHomeCityCode();
            strategy.appendField(locator, this, "homeCityCode", buffer, theHomeCityCode);
        }
        {
            String theMarketProvinceCode;
            theMarketProvinceCode = this.getMarketProvinceCode();
            strategy.appendField(locator, this, "marketProvinceCode", buffer, theMarketProvinceCode);
        }
        {
            String theSubMarketCode;
            theSubMarketCode = this.getSubMarketCode();
            strategy.appendField(locator, this, "subMarketCode", buffer, theSubMarketCode);
        }
        {
            String theAccountSegementCode;
            theAccountSegementCode = this.getAccountSegementCode();
            strategy.appendField(locator, this, "accountSegementCode", buffer, theAccountSegementCode);
        }
        {
            String theSubscriberSegmentCode;
            theSubscriberSegmentCode = this.getSubscriberSegmentCode();
            strategy.appendField(locator, this, "subscriberSegmentCode", buffer, theSubscriberSegmentCode);
        }
        {
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        {
            BigInteger theContractTerm;
            theContractTerm = this.getContractTerm();
            strategy.appendField(locator, this, "contractTerm", buffer, theContractTerm);
        }
        {
            Boolean theClientOwnedEquipmentIndicator;
            theClientOwnedEquipmentIndicator = this.isClientOwnedEquipmentIndicator();
            strategy.appendField(locator, this, "clientOwnedEquipmentIndicator", buffer, theClientOwnedEquipmentIndicator);
        }
        {
            String theNpaNxx;
            theNpaNxx = this.getNpaNxx();
            strategy.appendField(locator, this, "npaNxx", buffer, theNpaNxx);
        }
        {
            Boolean theActivationPortInIndicator;
            theActivationPortInIndicator = this.isActivationPortInIndicator();
            strategy.appendField(locator, this, "activationPortInIndicator", buffer, theActivationPortInIndicator);
        }
        {
            CorporateEntity theCorporateEntity;
            theCorporateEntity = this.getCorporateEntity();
            strategy.appendField(locator, this, "corporateEntity", buffer, theCorporateEntity);
        }
        {
            CreditProgramDetail theSelectedCreditProgram;
            theSelectedCreditProgram = this.getSelectedCreditProgram();
            strategy.appendField(locator, this, "selectedCreditProgram", buffer, theSelectedCreditProgram);
        }
        {
            List<PromotionIdentifier> thePromotionIdentifierList;
            thePromotionIdentifierList = (((this.promotionIdentifierList!= null)&&(!this.promotionIdentifierList.isEmpty()))?this.getPromotionIdentifierList():null);
            strategy.appendField(locator, this, "promotionIdentifierList", buffer, thePromotionIdentifierList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessOfferFilter that = ((WirelessOfferFilter) object);
        {
            String lhsAccountTypeCode;
            lhsAccountTypeCode = this.getAccountTypeCode();
            String rhsAccountTypeCode;
            rhsAccountTypeCode = that.getAccountTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountTypeCode", lhsAccountTypeCode), LocatorUtils.property(thatLocator, "accountTypeCode", rhsAccountTypeCode), lhsAccountTypeCode, rhsAccountTypeCode)) {
                return false;
            }
        }
        {
            String lhsAccountSubTypeCode;
            lhsAccountSubTypeCode = this.getAccountSubTypeCode();
            String rhsAccountSubTypeCode;
            rhsAccountSubTypeCode = that.getAccountSubTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountSubTypeCode", lhsAccountSubTypeCode), LocatorUtils.property(thatLocator, "accountSubTypeCode", rhsAccountSubTypeCode), lhsAccountSubTypeCode, rhsAccountSubTypeCode)) {
                return false;
            }
        }
        {
            String lhsHomeProvinceCode;
            lhsHomeProvinceCode = this.getHomeProvinceCode();
            String rhsHomeProvinceCode;
            rhsHomeProvinceCode = that.getHomeProvinceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "homeProvinceCode", lhsHomeProvinceCode), LocatorUtils.property(thatLocator, "homeProvinceCode", rhsHomeProvinceCode), lhsHomeProvinceCode, rhsHomeProvinceCode)) {
                return false;
            }
        }
        {
            String lhsHomeCityCode;
            lhsHomeCityCode = this.getHomeCityCode();
            String rhsHomeCityCode;
            rhsHomeCityCode = that.getHomeCityCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "homeCityCode", lhsHomeCityCode), LocatorUtils.property(thatLocator, "homeCityCode", rhsHomeCityCode), lhsHomeCityCode, rhsHomeCityCode)) {
                return false;
            }
        }
        {
            String lhsMarketProvinceCode;
            lhsMarketProvinceCode = this.getMarketProvinceCode();
            String rhsMarketProvinceCode;
            rhsMarketProvinceCode = that.getMarketProvinceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "marketProvinceCode", lhsMarketProvinceCode), LocatorUtils.property(thatLocator, "marketProvinceCode", rhsMarketProvinceCode), lhsMarketProvinceCode, rhsMarketProvinceCode)) {
                return false;
            }
        }
        {
            String lhsSubMarketCode;
            lhsSubMarketCode = this.getSubMarketCode();
            String rhsSubMarketCode;
            rhsSubMarketCode = that.getSubMarketCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subMarketCode", lhsSubMarketCode), LocatorUtils.property(thatLocator, "subMarketCode", rhsSubMarketCode), lhsSubMarketCode, rhsSubMarketCode)) {
                return false;
            }
        }
        {
            String lhsAccountSegementCode;
            lhsAccountSegementCode = this.getAccountSegementCode();
            String rhsAccountSegementCode;
            rhsAccountSegementCode = that.getAccountSegementCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountSegementCode", lhsAccountSegementCode), LocatorUtils.property(thatLocator, "accountSegementCode", rhsAccountSegementCode), lhsAccountSegementCode, rhsAccountSegementCode)) {
                return false;
            }
        }
        {
            String lhsSubscriberSegmentCode;
            lhsSubscriberSegmentCode = this.getSubscriberSegmentCode();
            String rhsSubscriberSegmentCode;
            rhsSubscriberSegmentCode = that.getSubscriberSegmentCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberSegmentCode", lhsSubscriberSegmentCode), LocatorUtils.property(thatLocator, "subscriberSegmentCode", rhsSubscriberSegmentCode), lhsSubscriberSegmentCode, rhsSubscriberSegmentCode)) {
                return false;
            }
        }
        {
            String lhsProductCode;
            lhsProductCode = this.getProductCode();
            String rhsProductCode;
            rhsProductCode = that.getProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCode", lhsProductCode), LocatorUtils.property(thatLocator, "productCode", rhsProductCode), lhsProductCode, rhsProductCode)) {
                return false;
            }
        }
        {
            BigInteger lhsContractTerm;
            lhsContractTerm = this.getContractTerm();
            BigInteger rhsContractTerm;
            rhsContractTerm = that.getContractTerm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTerm", lhsContractTerm), LocatorUtils.property(thatLocator, "contractTerm", rhsContractTerm), lhsContractTerm, rhsContractTerm)) {
                return false;
            }
        }
        {
            Boolean lhsClientOwnedEquipmentIndicator;
            lhsClientOwnedEquipmentIndicator = this.isClientOwnedEquipmentIndicator();
            Boolean rhsClientOwnedEquipmentIndicator;
            rhsClientOwnedEquipmentIndicator = that.isClientOwnedEquipmentIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "clientOwnedEquipmentIndicator", lhsClientOwnedEquipmentIndicator), LocatorUtils.property(thatLocator, "clientOwnedEquipmentIndicator", rhsClientOwnedEquipmentIndicator), lhsClientOwnedEquipmentIndicator, rhsClientOwnedEquipmentIndicator)) {
                return false;
            }
        }
        {
            String lhsNpaNxx;
            lhsNpaNxx = this.getNpaNxx();
            String rhsNpaNxx;
            rhsNpaNxx = that.getNpaNxx();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "npaNxx", lhsNpaNxx), LocatorUtils.property(thatLocator, "npaNxx", rhsNpaNxx), lhsNpaNxx, rhsNpaNxx)) {
                return false;
            }
        }
        {
            Boolean lhsActivationPortInIndicator;
            lhsActivationPortInIndicator = this.isActivationPortInIndicator();
            Boolean rhsActivationPortInIndicator;
            rhsActivationPortInIndicator = that.isActivationPortInIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "activationPortInIndicator", lhsActivationPortInIndicator), LocatorUtils.property(thatLocator, "activationPortInIndicator", rhsActivationPortInIndicator), lhsActivationPortInIndicator, rhsActivationPortInIndicator)) {
                return false;
            }
        }
        {
            CorporateEntity lhsCorporateEntity;
            lhsCorporateEntity = this.getCorporateEntity();
            CorporateEntity rhsCorporateEntity;
            rhsCorporateEntity = that.getCorporateEntity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "corporateEntity", lhsCorporateEntity), LocatorUtils.property(thatLocator, "corporateEntity", rhsCorporateEntity), lhsCorporateEntity, rhsCorporateEntity)) {
                return false;
            }
        }
        {
            CreditProgramDetail lhsSelectedCreditProgram;
            lhsSelectedCreditProgram = this.getSelectedCreditProgram();
            CreditProgramDetail rhsSelectedCreditProgram;
            rhsSelectedCreditProgram = that.getSelectedCreditProgram();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedCreditProgram", lhsSelectedCreditProgram), LocatorUtils.property(thatLocator, "selectedCreditProgram", rhsSelectedCreditProgram), lhsSelectedCreditProgram, rhsSelectedCreditProgram)) {
                return false;
            }
        }
        {
            List<PromotionIdentifier> lhsPromotionIdentifierList;
            lhsPromotionIdentifierList = (((this.promotionIdentifierList!= null)&&(!this.promotionIdentifierList.isEmpty()))?this.getPromotionIdentifierList():null);
            List<PromotionIdentifier> rhsPromotionIdentifierList;
            rhsPromotionIdentifierList = (((that.promotionIdentifierList!= null)&&(!that.promotionIdentifierList.isEmpty()))?that.getPromotionIdentifierList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionIdentifierList", lhsPromotionIdentifierList), LocatorUtils.property(thatLocator, "promotionIdentifierList", rhsPromotionIdentifierList), lhsPromotionIdentifierList, rhsPromotionIdentifierList)) {
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
