
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
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
 * <p>Java class for AccountEligibilityInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccountEligibilityInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="referToCreditAnalystIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="creditCheckResult" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="creditClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="creditDecisionMessage" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="securityDepositIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="creditLimitProgramAvailIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="eligibleForInternationalDialingIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="eligibleForInternationalRoamingIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="noDeviceProgramAvailableIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="clpCreditClassCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositCreditClassCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="noDeviceProgramCreditClassCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DepositList" minOccurs="0"/>
 *         &lt;element name="creditLimitProfile" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditLimitProfile" minOccurs="0"/>
 *         &lt;element name="termContractEligibilityIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="eligibleSubscriberCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxAdditionalNdpSubscriberCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxAdditionalDepositSubscriberCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="maxAdditionalClpSubscriberCount" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="creditAssessmentId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="prepaidPurchaseEligibility" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="payDevicePriceWithBalanceEligibilityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *                   &lt;element name="minPaymentFromBalanceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="maxBalanceAmountUsedForPurchaseAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                   &lt;element name="minRemainingBalanceRequiredAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccountEligibilityInformation", propOrder = {
    "referToCreditAnalystIndicator",
    "creditCheckResult",
    "securityDepositIndicator",
    "creditLimitProgramAvailIndicator",
    "eligibleForInternationalDialingIndicator",
    "eligibleForInternationalRoamingIndicator",
    "noDeviceProgramAvailableIndicator",
    "clpCreditClassCd",
    "depositCreditClassCd",
    "noDeviceProgramCreditClassCd",
    "depositList",
    "creditLimitProfile",
    "termContractEligibilityIndicator",
    "eligibleSubscriberCount",
    "maxAdditionalNdpSubscriberCount",
    "maxAdditionalDepositSubscriberCount",
    "maxAdditionalClpSubscriberCount",
    "creditAssessmentId",
    "prepaidPurchaseEligibility"
})
public class AccountEligibilityInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Boolean referToCreditAnalystIndicator;
    protected AccountEligibilityInformation.CreditCheckResult creditCheckResult;
    protected Boolean securityDepositIndicator;
    protected Boolean creditLimitProgramAvailIndicator;
    protected Boolean eligibleForInternationalDialingIndicator;
    protected Boolean eligibleForInternationalRoamingIndicator;
    protected Boolean noDeviceProgramAvailableIndicator;
    protected String clpCreditClassCd;
    protected String depositCreditClassCd;
    protected String noDeviceProgramCreditClassCd;
    protected DepositList depositList;
    protected CreditLimitProfile creditLimitProfile;
    protected Boolean termContractEligibilityIndicator;
    protected int eligibleSubscriberCount;
    protected Integer maxAdditionalNdpSubscriberCount;
    protected Integer maxAdditionalDepositSubscriberCount;
    protected Integer maxAdditionalClpSubscriberCount;
    protected Long creditAssessmentId;
    protected AccountEligibilityInformation.PrepaidPurchaseEligibility prepaidPurchaseEligibility;

    /**
     * Gets the value of the referToCreditAnalystIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReferToCreditAnalystIndicator() {
        return referToCreditAnalystIndicator;
    }

    /**
     * Sets the value of the referToCreditAnalystIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReferToCreditAnalystIndicator(Boolean value) {
        this.referToCreditAnalystIndicator = value;
    }

    /**
     * Gets the value of the creditCheckResult property.
     * 
     * @return
     *     possible object is
     *     {@link AccountEligibilityInformation.CreditCheckResult }
     *     
     */
    public AccountEligibilityInformation.CreditCheckResult getCreditCheckResult() {
        return creditCheckResult;
    }

    /**
     * Sets the value of the creditCheckResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountEligibilityInformation.CreditCheckResult }
     *     
     */
    public void setCreditCheckResult(AccountEligibilityInformation.CreditCheckResult value) {
        this.creditCheckResult = value;
    }

    /**
     * Gets the value of the securityDepositIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSecurityDepositIndicator() {
        return securityDepositIndicator;
    }

    /**
     * Sets the value of the securityDepositIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSecurityDepositIndicator(Boolean value) {
        this.securityDepositIndicator = value;
    }

    /**
     * Gets the value of the creditLimitProgramAvailIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isCreditLimitProgramAvailIndicator() {
        return creditLimitProgramAvailIndicator;
    }

    /**
     * Sets the value of the creditLimitProgramAvailIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCreditLimitProgramAvailIndicator(Boolean value) {
        this.creditLimitProgramAvailIndicator = value;
    }

    /**
     * Gets the value of the eligibleForInternationalDialingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEligibleForInternationalDialingIndicator() {
        return eligibleForInternationalDialingIndicator;
    }

    /**
     * Sets the value of the eligibleForInternationalDialingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEligibleForInternationalDialingIndicator(Boolean value) {
        this.eligibleForInternationalDialingIndicator = value;
    }

    /**
     * Gets the value of the eligibleForInternationalRoamingIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEligibleForInternationalRoamingIndicator() {
        return eligibleForInternationalRoamingIndicator;
    }

    /**
     * Sets the value of the eligibleForInternationalRoamingIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEligibleForInternationalRoamingIndicator(Boolean value) {
        this.eligibleForInternationalRoamingIndicator = value;
    }

    /**
     * Gets the value of the noDeviceProgramAvailableIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNoDeviceProgramAvailableIndicator() {
        return noDeviceProgramAvailableIndicator;
    }

    /**
     * Sets the value of the noDeviceProgramAvailableIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNoDeviceProgramAvailableIndicator(Boolean value) {
        this.noDeviceProgramAvailableIndicator = value;
    }

    /**
     * Gets the value of the clpCreditClassCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClpCreditClassCd() {
        return clpCreditClassCd;
    }

    /**
     * Sets the value of the clpCreditClassCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClpCreditClassCd(String value) {
        this.clpCreditClassCd = value;
    }

    /**
     * Gets the value of the depositCreditClassCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepositCreditClassCd() {
        return depositCreditClassCd;
    }

    /**
     * Sets the value of the depositCreditClassCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepositCreditClassCd(String value) {
        this.depositCreditClassCd = value;
    }

    /**
     * Gets the value of the noDeviceProgramCreditClassCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNoDeviceProgramCreditClassCd() {
        return noDeviceProgramCreditClassCd;
    }

    /**
     * Sets the value of the noDeviceProgramCreditClassCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNoDeviceProgramCreditClassCd(String value) {
        this.noDeviceProgramCreditClassCd = value;
    }

    /**
     * Gets the value of the depositList property.
     * 
     * @return
     *     possible object is
     *     {@link DepositList }
     *     
     */
    public DepositList getDepositList() {
        return depositList;
    }

    /**
     * Sets the value of the depositList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepositList }
     *     
     */
    public void setDepositList(DepositList value) {
        this.depositList = value;
    }

    /**
     * Gets the value of the creditLimitProfile property.
     * 
     * @return
     *     possible object is
     *     {@link CreditLimitProfile }
     *     
     */
    public CreditLimitProfile getCreditLimitProfile() {
        return creditLimitProfile;
    }

    /**
     * Sets the value of the creditLimitProfile property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditLimitProfile }
     *     
     */
    public void setCreditLimitProfile(CreditLimitProfile value) {
        this.creditLimitProfile = value;
    }

    /**
     * Gets the value of the termContractEligibilityIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTermContractEligibilityIndicator() {
        return termContractEligibilityIndicator;
    }

    /**
     * Sets the value of the termContractEligibilityIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTermContractEligibilityIndicator(Boolean value) {
        this.termContractEligibilityIndicator = value;
    }

    /**
     * Gets the value of the eligibleSubscriberCount property.
     * 
     */
    public int getEligibleSubscriberCount() {
        return eligibleSubscriberCount;
    }

    /**
     * Sets the value of the eligibleSubscriberCount property.
     * 
     */
    public void setEligibleSubscriberCount(int value) {
        this.eligibleSubscriberCount = value;
    }

    /**
     * Gets the value of the maxAdditionalNdpSubscriberCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxAdditionalNdpSubscriberCount() {
        return maxAdditionalNdpSubscriberCount;
    }

    /**
     * Sets the value of the maxAdditionalNdpSubscriberCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxAdditionalNdpSubscriberCount(Integer value) {
        this.maxAdditionalNdpSubscriberCount = value;
    }

    /**
     * Gets the value of the maxAdditionalDepositSubscriberCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxAdditionalDepositSubscriberCount() {
        return maxAdditionalDepositSubscriberCount;
    }

    /**
     * Sets the value of the maxAdditionalDepositSubscriberCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxAdditionalDepositSubscriberCount(Integer value) {
        this.maxAdditionalDepositSubscriberCount = value;
    }

    /**
     * Gets the value of the maxAdditionalClpSubscriberCount property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMaxAdditionalClpSubscriberCount() {
        return maxAdditionalClpSubscriberCount;
    }

    /**
     * Sets the value of the maxAdditionalClpSubscriberCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMaxAdditionalClpSubscriberCount(Integer value) {
        this.maxAdditionalClpSubscriberCount = value;
    }

    /**
     * Gets the value of the creditAssessmentId property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getCreditAssessmentId() {
        return creditAssessmentId;
    }

    /**
     * Sets the value of the creditAssessmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setCreditAssessmentId(Long value) {
        this.creditAssessmentId = value;
    }

    /**
     * Gets the value of the prepaidPurchaseEligibility property.
     * 
     * @return
     *     possible object is
     *     {@link AccountEligibilityInformation.PrepaidPurchaseEligibility }
     *     
     */
    public AccountEligibilityInformation.PrepaidPurchaseEligibility getPrepaidPurchaseEligibility() {
        return prepaidPurchaseEligibility;
    }

    /**
     * Sets the value of the prepaidPurchaseEligibility property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountEligibilityInformation.PrepaidPurchaseEligibility }
     *     
     */
    public void setPrepaidPurchaseEligibility(AccountEligibilityInformation.PrepaidPurchaseEligibility value) {
        this.prepaidPurchaseEligibility = value;
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
            Boolean theReferToCreditAnalystIndicator;
            theReferToCreditAnalystIndicator = this.isReferToCreditAnalystIndicator();
            strategy.appendField(locator, this, "referToCreditAnalystIndicator", buffer, theReferToCreditAnalystIndicator);
        }
        {
            AccountEligibilityInformation.CreditCheckResult theCreditCheckResult;
            theCreditCheckResult = this.getCreditCheckResult();
            strategy.appendField(locator, this, "creditCheckResult", buffer, theCreditCheckResult);
        }
        {
            Boolean theSecurityDepositIndicator;
            theSecurityDepositIndicator = this.isSecurityDepositIndicator();
            strategy.appendField(locator, this, "securityDepositIndicator", buffer, theSecurityDepositIndicator);
        }
        {
            Boolean theCreditLimitProgramAvailIndicator;
            theCreditLimitProgramAvailIndicator = this.isCreditLimitProgramAvailIndicator();
            strategy.appendField(locator, this, "creditLimitProgramAvailIndicator", buffer, theCreditLimitProgramAvailIndicator);
        }
        {
            Boolean theEligibleForInternationalDialingIndicator;
            theEligibleForInternationalDialingIndicator = this.isEligibleForInternationalDialingIndicator();
            strategy.appendField(locator, this, "eligibleForInternationalDialingIndicator", buffer, theEligibleForInternationalDialingIndicator);
        }
        {
            Boolean theEligibleForInternationalRoamingIndicator;
            theEligibleForInternationalRoamingIndicator = this.isEligibleForInternationalRoamingIndicator();
            strategy.appendField(locator, this, "eligibleForInternationalRoamingIndicator", buffer, theEligibleForInternationalRoamingIndicator);
        }
        {
            Boolean theNoDeviceProgramAvailableIndicator;
            theNoDeviceProgramAvailableIndicator = this.isNoDeviceProgramAvailableIndicator();
            strategy.appendField(locator, this, "noDeviceProgramAvailableIndicator", buffer, theNoDeviceProgramAvailableIndicator);
        }
        {
            String theClpCreditClassCd;
            theClpCreditClassCd = this.getClpCreditClassCd();
            strategy.appendField(locator, this, "clpCreditClassCd", buffer, theClpCreditClassCd);
        }
        {
            String theDepositCreditClassCd;
            theDepositCreditClassCd = this.getDepositCreditClassCd();
            strategy.appendField(locator, this, "depositCreditClassCd", buffer, theDepositCreditClassCd);
        }
        {
            String theNoDeviceProgramCreditClassCd;
            theNoDeviceProgramCreditClassCd = this.getNoDeviceProgramCreditClassCd();
            strategy.appendField(locator, this, "noDeviceProgramCreditClassCd", buffer, theNoDeviceProgramCreditClassCd);
        }
        {
            DepositList theDepositList;
            theDepositList = this.getDepositList();
            strategy.appendField(locator, this, "depositList", buffer, theDepositList);
        }
        {
            CreditLimitProfile theCreditLimitProfile;
            theCreditLimitProfile = this.getCreditLimitProfile();
            strategy.appendField(locator, this, "creditLimitProfile", buffer, theCreditLimitProfile);
        }
        {
            Boolean theTermContractEligibilityIndicator;
            theTermContractEligibilityIndicator = this.isTermContractEligibilityIndicator();
            strategy.appendField(locator, this, "termContractEligibilityIndicator", buffer, theTermContractEligibilityIndicator);
        }
        {
            int theEligibleSubscriberCount;
            theEligibleSubscriberCount = (true?this.getEligibleSubscriberCount(): 0);
            strategy.appendField(locator, this, "eligibleSubscriberCount", buffer, theEligibleSubscriberCount);
        }
        {
            Integer theMaxAdditionalNdpSubscriberCount;
            theMaxAdditionalNdpSubscriberCount = this.getMaxAdditionalNdpSubscriberCount();
            strategy.appendField(locator, this, "maxAdditionalNdpSubscriberCount", buffer, theMaxAdditionalNdpSubscriberCount);
        }
        {
            Integer theMaxAdditionalDepositSubscriberCount;
            theMaxAdditionalDepositSubscriberCount = this.getMaxAdditionalDepositSubscriberCount();
            strategy.appendField(locator, this, "maxAdditionalDepositSubscriberCount", buffer, theMaxAdditionalDepositSubscriberCount);
        }
        {
            Integer theMaxAdditionalClpSubscriberCount;
            theMaxAdditionalClpSubscriberCount = this.getMaxAdditionalClpSubscriberCount();
            strategy.appendField(locator, this, "maxAdditionalClpSubscriberCount", buffer, theMaxAdditionalClpSubscriberCount);
        }
        {
            Long theCreditAssessmentId;
            theCreditAssessmentId = this.getCreditAssessmentId();
            strategy.appendField(locator, this, "creditAssessmentId", buffer, theCreditAssessmentId);
        }
        {
            AccountEligibilityInformation.PrepaidPurchaseEligibility thePrepaidPurchaseEligibility;
            thePrepaidPurchaseEligibility = this.getPrepaidPurchaseEligibility();
            strategy.appendField(locator, this, "prepaidPurchaseEligibility", buffer, thePrepaidPurchaseEligibility);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccountEligibilityInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccountEligibilityInformation that = ((AccountEligibilityInformation) object);
        {
            Boolean lhsReferToCreditAnalystIndicator;
            lhsReferToCreditAnalystIndicator = this.isReferToCreditAnalystIndicator();
            Boolean rhsReferToCreditAnalystIndicator;
            rhsReferToCreditAnalystIndicator = that.isReferToCreditAnalystIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "referToCreditAnalystIndicator", lhsReferToCreditAnalystIndicator), LocatorUtils.property(thatLocator, "referToCreditAnalystIndicator", rhsReferToCreditAnalystIndicator), lhsReferToCreditAnalystIndicator, rhsReferToCreditAnalystIndicator)) {
                return false;
            }
        }
        {
            AccountEligibilityInformation.CreditCheckResult lhsCreditCheckResult;
            lhsCreditCheckResult = this.getCreditCheckResult();
            AccountEligibilityInformation.CreditCheckResult rhsCreditCheckResult;
            rhsCreditCheckResult = that.getCreditCheckResult();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCheckResult", lhsCreditCheckResult), LocatorUtils.property(thatLocator, "creditCheckResult", rhsCreditCheckResult), lhsCreditCheckResult, rhsCreditCheckResult)) {
                return false;
            }
        }
        {
            Boolean lhsSecurityDepositIndicator;
            lhsSecurityDepositIndicator = this.isSecurityDepositIndicator();
            Boolean rhsSecurityDepositIndicator;
            rhsSecurityDepositIndicator = that.isSecurityDepositIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "securityDepositIndicator", lhsSecurityDepositIndicator), LocatorUtils.property(thatLocator, "securityDepositIndicator", rhsSecurityDepositIndicator), lhsSecurityDepositIndicator, rhsSecurityDepositIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsCreditLimitProgramAvailIndicator;
            lhsCreditLimitProgramAvailIndicator = this.isCreditLimitProgramAvailIndicator();
            Boolean rhsCreditLimitProgramAvailIndicator;
            rhsCreditLimitProgramAvailIndicator = that.isCreditLimitProgramAvailIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitProgramAvailIndicator", lhsCreditLimitProgramAvailIndicator), LocatorUtils.property(thatLocator, "creditLimitProgramAvailIndicator", rhsCreditLimitProgramAvailIndicator), lhsCreditLimitProgramAvailIndicator, rhsCreditLimitProgramAvailIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsEligibleForInternationalDialingIndicator;
            lhsEligibleForInternationalDialingIndicator = this.isEligibleForInternationalDialingIndicator();
            Boolean rhsEligibleForInternationalDialingIndicator;
            rhsEligibleForInternationalDialingIndicator = that.isEligibleForInternationalDialingIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleForInternationalDialingIndicator", lhsEligibleForInternationalDialingIndicator), LocatorUtils.property(thatLocator, "eligibleForInternationalDialingIndicator", rhsEligibleForInternationalDialingIndicator), lhsEligibleForInternationalDialingIndicator, rhsEligibleForInternationalDialingIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsEligibleForInternationalRoamingIndicator;
            lhsEligibleForInternationalRoamingIndicator = this.isEligibleForInternationalRoamingIndicator();
            Boolean rhsEligibleForInternationalRoamingIndicator;
            rhsEligibleForInternationalRoamingIndicator = that.isEligibleForInternationalRoamingIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleForInternationalRoamingIndicator", lhsEligibleForInternationalRoamingIndicator), LocatorUtils.property(thatLocator, "eligibleForInternationalRoamingIndicator", rhsEligibleForInternationalRoamingIndicator), lhsEligibleForInternationalRoamingIndicator, rhsEligibleForInternationalRoamingIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsNoDeviceProgramAvailableIndicator;
            lhsNoDeviceProgramAvailableIndicator = this.isNoDeviceProgramAvailableIndicator();
            Boolean rhsNoDeviceProgramAvailableIndicator;
            rhsNoDeviceProgramAvailableIndicator = that.isNoDeviceProgramAvailableIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "noDeviceProgramAvailableIndicator", lhsNoDeviceProgramAvailableIndicator), LocatorUtils.property(thatLocator, "noDeviceProgramAvailableIndicator", rhsNoDeviceProgramAvailableIndicator), lhsNoDeviceProgramAvailableIndicator, rhsNoDeviceProgramAvailableIndicator)) {
                return false;
            }
        }
        {
            String lhsClpCreditClassCd;
            lhsClpCreditClassCd = this.getClpCreditClassCd();
            String rhsClpCreditClassCd;
            rhsClpCreditClassCd = that.getClpCreditClassCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "clpCreditClassCd", lhsClpCreditClassCd), LocatorUtils.property(thatLocator, "clpCreditClassCd", rhsClpCreditClassCd), lhsClpCreditClassCd, rhsClpCreditClassCd)) {
                return false;
            }
        }
        {
            String lhsDepositCreditClassCd;
            lhsDepositCreditClassCd = this.getDepositCreditClassCd();
            String rhsDepositCreditClassCd;
            rhsDepositCreditClassCd = that.getDepositCreditClassCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositCreditClassCd", lhsDepositCreditClassCd), LocatorUtils.property(thatLocator, "depositCreditClassCd", rhsDepositCreditClassCd), lhsDepositCreditClassCd, rhsDepositCreditClassCd)) {
                return false;
            }
        }
        {
            String lhsNoDeviceProgramCreditClassCd;
            lhsNoDeviceProgramCreditClassCd = this.getNoDeviceProgramCreditClassCd();
            String rhsNoDeviceProgramCreditClassCd;
            rhsNoDeviceProgramCreditClassCd = that.getNoDeviceProgramCreditClassCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "noDeviceProgramCreditClassCd", lhsNoDeviceProgramCreditClassCd), LocatorUtils.property(thatLocator, "noDeviceProgramCreditClassCd", rhsNoDeviceProgramCreditClassCd), lhsNoDeviceProgramCreditClassCd, rhsNoDeviceProgramCreditClassCd)) {
                return false;
            }
        }
        {
            DepositList lhsDepositList;
            lhsDepositList = this.getDepositList();
            DepositList rhsDepositList;
            rhsDepositList = that.getDepositList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositList", lhsDepositList), LocatorUtils.property(thatLocator, "depositList", rhsDepositList), lhsDepositList, rhsDepositList)) {
                return false;
            }
        }
        {
            CreditLimitProfile lhsCreditLimitProfile;
            lhsCreditLimitProfile = this.getCreditLimitProfile();
            CreditLimitProfile rhsCreditLimitProfile;
            rhsCreditLimitProfile = that.getCreditLimitProfile();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditLimitProfile", lhsCreditLimitProfile), LocatorUtils.property(thatLocator, "creditLimitProfile", rhsCreditLimitProfile), lhsCreditLimitProfile, rhsCreditLimitProfile)) {
                return false;
            }
        }
        {
            Boolean lhsTermContractEligibilityIndicator;
            lhsTermContractEligibilityIndicator = this.isTermContractEligibilityIndicator();
            Boolean rhsTermContractEligibilityIndicator;
            rhsTermContractEligibilityIndicator = that.isTermContractEligibilityIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "termContractEligibilityIndicator", lhsTermContractEligibilityIndicator), LocatorUtils.property(thatLocator, "termContractEligibilityIndicator", rhsTermContractEligibilityIndicator), lhsTermContractEligibilityIndicator, rhsTermContractEligibilityIndicator)) {
                return false;
            }
        }
        {
            int lhsEligibleSubscriberCount;
            lhsEligibleSubscriberCount = (true?this.getEligibleSubscriberCount(): 0);
            int rhsEligibleSubscriberCount;
            rhsEligibleSubscriberCount = (true?that.getEligibleSubscriberCount(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleSubscriberCount", lhsEligibleSubscriberCount), LocatorUtils.property(thatLocator, "eligibleSubscriberCount", rhsEligibleSubscriberCount), lhsEligibleSubscriberCount, rhsEligibleSubscriberCount)) {
                return false;
            }
        }
        {
            Integer lhsMaxAdditionalNdpSubscriberCount;
            lhsMaxAdditionalNdpSubscriberCount = this.getMaxAdditionalNdpSubscriberCount();
            Integer rhsMaxAdditionalNdpSubscriberCount;
            rhsMaxAdditionalNdpSubscriberCount = that.getMaxAdditionalNdpSubscriberCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxAdditionalNdpSubscriberCount", lhsMaxAdditionalNdpSubscriberCount), LocatorUtils.property(thatLocator, "maxAdditionalNdpSubscriberCount", rhsMaxAdditionalNdpSubscriberCount), lhsMaxAdditionalNdpSubscriberCount, rhsMaxAdditionalNdpSubscriberCount)) {
                return false;
            }
        }
        {
            Integer lhsMaxAdditionalDepositSubscriberCount;
            lhsMaxAdditionalDepositSubscriberCount = this.getMaxAdditionalDepositSubscriberCount();
            Integer rhsMaxAdditionalDepositSubscriberCount;
            rhsMaxAdditionalDepositSubscriberCount = that.getMaxAdditionalDepositSubscriberCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxAdditionalDepositSubscriberCount", lhsMaxAdditionalDepositSubscriberCount), LocatorUtils.property(thatLocator, "maxAdditionalDepositSubscriberCount", rhsMaxAdditionalDepositSubscriberCount), lhsMaxAdditionalDepositSubscriberCount, rhsMaxAdditionalDepositSubscriberCount)) {
                return false;
            }
        }
        {
            Integer lhsMaxAdditionalClpSubscriberCount;
            lhsMaxAdditionalClpSubscriberCount = this.getMaxAdditionalClpSubscriberCount();
            Integer rhsMaxAdditionalClpSubscriberCount;
            rhsMaxAdditionalClpSubscriberCount = that.getMaxAdditionalClpSubscriberCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxAdditionalClpSubscriberCount", lhsMaxAdditionalClpSubscriberCount), LocatorUtils.property(thatLocator, "maxAdditionalClpSubscriberCount", rhsMaxAdditionalClpSubscriberCount), lhsMaxAdditionalClpSubscriberCount, rhsMaxAdditionalClpSubscriberCount)) {
                return false;
            }
        }
        {
            Long lhsCreditAssessmentId;
            lhsCreditAssessmentId = this.getCreditAssessmentId();
            Long rhsCreditAssessmentId;
            rhsCreditAssessmentId = that.getCreditAssessmentId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditAssessmentId", lhsCreditAssessmentId), LocatorUtils.property(thatLocator, "creditAssessmentId", rhsCreditAssessmentId), lhsCreditAssessmentId, rhsCreditAssessmentId)) {
                return false;
            }
        }
        {
            AccountEligibilityInformation.PrepaidPurchaseEligibility lhsPrepaidPurchaseEligibility;
            lhsPrepaidPurchaseEligibility = this.getPrepaidPurchaseEligibility();
            AccountEligibilityInformation.PrepaidPurchaseEligibility rhsPrepaidPurchaseEligibility;
            rhsPrepaidPurchaseEligibility = that.getPrepaidPurchaseEligibility();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prepaidPurchaseEligibility", lhsPrepaidPurchaseEligibility), LocatorUtils.property(thatLocator, "prepaidPurchaseEligibility", rhsPrepaidPurchaseEligibility), lhsPrepaidPurchaseEligibility, rhsPrepaidPurchaseEligibility)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="creditClass" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="creditDecisionMessage" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "creditClass",
        "creditDecisionMessage"
    })
    public static class CreditCheckResult
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String creditClass;
        @XmlElement(required = true)
        protected MultilingualDescriptionList creditDecisionMessage;

        /**
         * Gets the value of the creditClass property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreditClass() {
            return creditClass;
        }

        /**
         * Sets the value of the creditClass property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreditClass(String value) {
            this.creditClass = value;
        }

        /**
         * Gets the value of the creditDecisionMessage property.
         * 
         * @return
         *     possible object is
         *     {@link MultilingualDescriptionList }
         *     
         */
        public MultilingualDescriptionList getCreditDecisionMessage() {
            return creditDecisionMessage;
        }

        /**
         * Sets the value of the creditDecisionMessage property.
         * 
         * @param value
         *     allowed object is
         *     {@link MultilingualDescriptionList }
         *     
         */
        public void setCreditDecisionMessage(MultilingualDescriptionList value) {
            this.creditDecisionMessage = value;
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
                String theCreditClass;
                theCreditClass = this.getCreditClass();
                strategy.appendField(locator, this, "creditClass", buffer, theCreditClass);
            }
            {
                MultilingualDescriptionList theCreditDecisionMessage;
                theCreditDecisionMessage = this.getCreditDecisionMessage();
                strategy.appendField(locator, this, "creditDecisionMessage", buffer, theCreditDecisionMessage);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AccountEligibilityInformation.CreditCheckResult)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AccountEligibilityInformation.CreditCheckResult that = ((AccountEligibilityInformation.CreditCheckResult) object);
            {
                String lhsCreditClass;
                lhsCreditClass = this.getCreditClass();
                String rhsCreditClass;
                rhsCreditClass = that.getCreditClass();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "creditClass", lhsCreditClass), LocatorUtils.property(thatLocator, "creditClass", rhsCreditClass), lhsCreditClass, rhsCreditClass)) {
                    return false;
                }
            }
            {
                MultilingualDescriptionList lhsCreditDecisionMessage;
                lhsCreditDecisionMessage = this.getCreditDecisionMessage();
                MultilingualDescriptionList rhsCreditDecisionMessage;
                rhsCreditDecisionMessage = that.getCreditDecisionMessage();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "creditDecisionMessage", lhsCreditDecisionMessage), LocatorUtils.property(thatLocator, "creditDecisionMessage", rhsCreditDecisionMessage), lhsCreditDecisionMessage, rhsCreditDecisionMessage)) {
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


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="payDevicePriceWithBalanceEligibilityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
     *         &lt;element name="minPaymentFromBalanceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="maxBalanceAmountUsedForPurchaseAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *         &lt;element name="minRemainingBalanceRequiredAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "payDevicePriceWithBalanceEligibilityInd",
        "minPaymentFromBalanceAmt",
        "maxBalanceAmountUsedForPurchaseAmt",
        "minRemainingBalanceRequiredAmt"
    })
    public static class PrepaidPurchaseEligibility
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected Boolean payDevicePriceWithBalanceEligibilityInd;
        protected Double minPaymentFromBalanceAmt;
        protected Double maxBalanceAmountUsedForPurchaseAmt;
        protected Double minRemainingBalanceRequiredAmt;

        /**
         * Gets the value of the payDevicePriceWithBalanceEligibilityInd property.
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isPayDevicePriceWithBalanceEligibilityInd() {
            return payDevicePriceWithBalanceEligibilityInd;
        }

        /**
         * Sets the value of the payDevicePriceWithBalanceEligibilityInd property.
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setPayDevicePriceWithBalanceEligibilityInd(Boolean value) {
            this.payDevicePriceWithBalanceEligibilityInd = value;
        }

        /**
         * Gets the value of the minPaymentFromBalanceAmt property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getMinPaymentFromBalanceAmt() {
            return minPaymentFromBalanceAmt;
        }

        /**
         * Sets the value of the minPaymentFromBalanceAmt property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setMinPaymentFromBalanceAmt(Double value) {
            this.minPaymentFromBalanceAmt = value;
        }

        /**
         * Gets the value of the maxBalanceAmountUsedForPurchaseAmt property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getMaxBalanceAmountUsedForPurchaseAmt() {
            return maxBalanceAmountUsedForPurchaseAmt;
        }

        /**
         * Sets the value of the maxBalanceAmountUsedForPurchaseAmt property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setMaxBalanceAmountUsedForPurchaseAmt(Double value) {
            this.maxBalanceAmountUsedForPurchaseAmt = value;
        }

        /**
         * Gets the value of the minRemainingBalanceRequiredAmt property.
         * 
         * @return
         *     possible object is
         *     {@link Double }
         *     
         */
        public Double getMinRemainingBalanceRequiredAmt() {
            return minRemainingBalanceRequiredAmt;
        }

        /**
         * Sets the value of the minRemainingBalanceRequiredAmt property.
         * 
         * @param value
         *     allowed object is
         *     {@link Double }
         *     
         */
        public void setMinRemainingBalanceRequiredAmt(Double value) {
            this.minRemainingBalanceRequiredAmt = value;
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
                Boolean thePayDevicePriceWithBalanceEligibilityInd;
                thePayDevicePriceWithBalanceEligibilityInd = this.isPayDevicePriceWithBalanceEligibilityInd();
                strategy.appendField(locator, this, "payDevicePriceWithBalanceEligibilityInd", buffer, thePayDevicePriceWithBalanceEligibilityInd);
            }
            {
                Double theMinPaymentFromBalanceAmt;
                theMinPaymentFromBalanceAmt = this.getMinPaymentFromBalanceAmt();
                strategy.appendField(locator, this, "minPaymentFromBalanceAmt", buffer, theMinPaymentFromBalanceAmt);
            }
            {
                Double theMaxBalanceAmountUsedForPurchaseAmt;
                theMaxBalanceAmountUsedForPurchaseAmt = this.getMaxBalanceAmountUsedForPurchaseAmt();
                strategy.appendField(locator, this, "maxBalanceAmountUsedForPurchaseAmt", buffer, theMaxBalanceAmountUsedForPurchaseAmt);
            }
            {
                Double theMinRemainingBalanceRequiredAmt;
                theMinRemainingBalanceRequiredAmt = this.getMinRemainingBalanceRequiredAmt();
                strategy.appendField(locator, this, "minRemainingBalanceRequiredAmt", buffer, theMinRemainingBalanceRequiredAmt);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof AccountEligibilityInformation.PrepaidPurchaseEligibility)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final AccountEligibilityInformation.PrepaidPurchaseEligibility that = ((AccountEligibilityInformation.PrepaidPurchaseEligibility) object);
            {
                Boolean lhsPayDevicePriceWithBalanceEligibilityInd;
                lhsPayDevicePriceWithBalanceEligibilityInd = this.isPayDevicePriceWithBalanceEligibilityInd();
                Boolean rhsPayDevicePriceWithBalanceEligibilityInd;
                rhsPayDevicePriceWithBalanceEligibilityInd = that.isPayDevicePriceWithBalanceEligibilityInd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "payDevicePriceWithBalanceEligibilityInd", lhsPayDevicePriceWithBalanceEligibilityInd), LocatorUtils.property(thatLocator, "payDevicePriceWithBalanceEligibilityInd", rhsPayDevicePriceWithBalanceEligibilityInd), lhsPayDevicePriceWithBalanceEligibilityInd, rhsPayDevicePriceWithBalanceEligibilityInd)) {
                    return false;
                }
            }
            {
                Double lhsMinPaymentFromBalanceAmt;
                lhsMinPaymentFromBalanceAmt = this.getMinPaymentFromBalanceAmt();
                Double rhsMinPaymentFromBalanceAmt;
                rhsMinPaymentFromBalanceAmt = that.getMinPaymentFromBalanceAmt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "minPaymentFromBalanceAmt", lhsMinPaymentFromBalanceAmt), LocatorUtils.property(thatLocator, "minPaymentFromBalanceAmt", rhsMinPaymentFromBalanceAmt), lhsMinPaymentFromBalanceAmt, rhsMinPaymentFromBalanceAmt)) {
                    return false;
                }
            }
            {
                Double lhsMaxBalanceAmountUsedForPurchaseAmt;
                lhsMaxBalanceAmountUsedForPurchaseAmt = this.getMaxBalanceAmountUsedForPurchaseAmt();
                Double rhsMaxBalanceAmountUsedForPurchaseAmt;
                rhsMaxBalanceAmountUsedForPurchaseAmt = that.getMaxBalanceAmountUsedForPurchaseAmt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "maxBalanceAmountUsedForPurchaseAmt", lhsMaxBalanceAmountUsedForPurchaseAmt), LocatorUtils.property(thatLocator, "maxBalanceAmountUsedForPurchaseAmt", rhsMaxBalanceAmountUsedForPurchaseAmt), lhsMaxBalanceAmountUsedForPurchaseAmt, rhsMaxBalanceAmountUsedForPurchaseAmt)) {
                    return false;
                }
            }
            {
                Double lhsMinRemainingBalanceRequiredAmt;
                lhsMinRemainingBalanceRequiredAmt = this.getMinRemainingBalanceRequiredAmt();
                Double rhsMinRemainingBalanceRequiredAmt;
                rhsMinRemainingBalanceRequiredAmt = that.getMinRemainingBalanceRequiredAmt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "minRemainingBalanceRequiredAmt", lhsMinRemainingBalanceRequiredAmt), LocatorUtils.property(thatLocator, "minRemainingBalanceRequiredAmt", rhsMinRemainingBalanceRequiredAmt), lhsMinRemainingBalanceRequiredAmt, rhsMinRemainingBalanceRequiredAmt)) {
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

}
