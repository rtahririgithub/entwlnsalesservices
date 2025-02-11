
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.AccountList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.AreaList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.BrandList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.ContractTermList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.DiscountDefinitionType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.DiscountDeliveryType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.DiscountType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.EligibilityAdditionalInfo;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.HardwareList;
import com.telus.tmi.xmlschema.xsd.product.productoffering.productofferingcommon_v5.ProductType;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PromotionalOfferItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PromotionalOfferItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sapMaterialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="functionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="accountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}AccountList"/>
 *         &lt;element name="availableAreaList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}AreaList"/>
 *         &lt;element name="marketAreaList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}AreaList"/>
 *         &lt;element name="brandList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}BrandList"/>
 *         &lt;element name="nationalOfferIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}DiscountType"/>
 *         &lt;element name="discountDefinitionType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}DiscountDefinitionType"/>
 *         &lt;element name="discountDeliveryType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}DiscountDeliveryType"/>
 *         &lt;element name="sameBuyAndGetHardwareIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}sameBuyAndGetHardwareIndicatorType"/>
 *         &lt;element name="multipleUnitIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}multipleUnitIndicatorType"/>
 *         &lt;element name="sameBanIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}sameBanIndicatorType"/>
 *         &lt;element name="mustBuyQty" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}mustBuyQtyType" minOccurs="0"/>
 *         &lt;element name="mustGetQty" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}mustGetQtyType" minOccurs="0"/>
 *         &lt;element name="containSimIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}containSimIndicatorType"/>
 *         &lt;element name="containEsnIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}containEsnIndicatorType"/>
 *         &lt;element name="containImeiIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}containImeiIndicatorType"/>
 *         &lt;element name="hasOneGetOneIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}hasOneGetOneIndicatorType"/>
 *         &lt;element name="protectionPeriod" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}protectionPeriodType" minOccurs="0"/>
 *         &lt;element name="billingMethod" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}billingMethodCodeType"/>
 *         &lt;element name="eligibleTermList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}ContractTermList" minOccurs="0"/>
 *         &lt;element name="eligibleBuyTermList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}ContractTermList" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}ProductType"/>
 *         &lt;element name="eligibilityAdditionalInfo" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}EligibilityAdditionalInfo" minOccurs="0"/>
 *         &lt;element name="hardwareBuyList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}HardwareList" minOccurs="0"/>
 *         &lt;element name="hardwareGetList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}HardwareList" minOccurs="0"/>
 *         &lt;element name="billCreditForAllSKUIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="buyOneGetOneIndicator" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/ProductOfferingCommon_v5}buyOneGetOneIndicatorType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PromotionalOfferItem", propOrder = {
    "sapMaterialNumber",
    "functionCode",
    "accountList",
    "availableAreaList",
    "marketAreaList",
    "brandList",
    "nationalOfferIndicator",
    "discountType",
    "discountDefinitionType",
    "discountDeliveryType",
    "sameBuyAndGetHardwareIndicator",
    "multipleUnitIndicator",
    "sameBanIndicator",
    "mustBuyQty",
    "mustGetQty",
    "containSimIndicator",
    "containEsnIndicator",
    "containImeiIndicator",
    "hasOneGetOneIndicator",
    "protectionPeriod",
    "billingMethod",
    "eligibleTermList",
    "eligibleBuyTermList",
    "productType",
    "eligibilityAdditionalInfo",
    "hardwareBuyList",
    "hardwareGetList",
    "billCreditForAllSKUIndicator",
    "buyOneGetOneIndicator"
})
public class PromotionalOfferItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String sapMaterialNumber;
    @XmlElement(required = true)
    protected String functionCode;
    @XmlElement(required = true)
    protected AccountList accountList;
    @XmlElement(required = true)
    protected AreaList availableAreaList;
    @XmlElement(required = true)
    protected AreaList marketAreaList;
    @XmlElement(required = true)
    protected BrandList brandList;
    protected boolean nationalOfferIndicator;
    @XmlElement(required = true)
    protected DiscountType discountType;
    @XmlElement(required = true)
    protected DiscountDefinitionType discountDefinitionType;
    @XmlElement(required = true)
    protected DiscountDeliveryType discountDeliveryType;
    protected boolean sameBuyAndGetHardwareIndicator;
    protected boolean multipleUnitIndicator;
    protected boolean sameBanIndicator;
    protected BigInteger mustBuyQty;
    protected BigInteger mustGetQty;
    protected boolean containSimIndicator;
    protected boolean containEsnIndicator;
    protected boolean containImeiIndicator;
    protected boolean hasOneGetOneIndicator;
    protected BigInteger protectionPeriod;
    @XmlElement(required = true)
    protected String billingMethod;
    protected ContractTermList eligibleTermList;
    protected ContractTermList eligibleBuyTermList;
    @XmlElement(required = true)
    protected ProductType productType;
    protected EligibilityAdditionalInfo eligibilityAdditionalInfo;
    protected HardwareList hardwareBuyList;
    protected HardwareList hardwareGetList;
    protected boolean billCreditForAllSKUIndicator;
    protected boolean buyOneGetOneIndicator;

    /**
     * Gets the value of the sapMaterialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapMaterialNumber() {
        return sapMaterialNumber;
    }

    /**
     * Sets the value of the sapMaterialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapMaterialNumber(String value) {
        this.sapMaterialNumber = value;
    }

    /**
     * Gets the value of the functionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFunctionCode() {
        return functionCode;
    }

    /**
     * Sets the value of the functionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFunctionCode(String value) {
        this.functionCode = value;
    }

    /**
     * Gets the value of the accountList property.
     * 
     * @return
     *     possible object is
     *     {@link AccountList }
     *     
     */
    public AccountList getAccountList() {
        return accountList;
    }

    /**
     * Sets the value of the accountList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccountList }
     *     
     */
    public void setAccountList(AccountList value) {
        this.accountList = value;
    }

    /**
     * Gets the value of the availableAreaList property.
     * 
     * @return
     *     possible object is
     *     {@link AreaList }
     *     
     */
    public AreaList getAvailableAreaList() {
        return availableAreaList;
    }

    /**
     * Sets the value of the availableAreaList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaList }
     *     
     */
    public void setAvailableAreaList(AreaList value) {
        this.availableAreaList = value;
    }

    /**
     * Gets the value of the marketAreaList property.
     * 
     * @return
     *     possible object is
     *     {@link AreaList }
     *     
     */
    public AreaList getMarketAreaList() {
        return marketAreaList;
    }

    /**
     * Sets the value of the marketAreaList property.
     * 
     * @param value
     *     allowed object is
     *     {@link AreaList }
     *     
     */
    public void setMarketAreaList(AreaList value) {
        this.marketAreaList = value;
    }

    /**
     * Gets the value of the brandList property.
     * 
     * @return
     *     possible object is
     *     {@link BrandList }
     *     
     */
    public BrandList getBrandList() {
        return brandList;
    }

    /**
     * Sets the value of the brandList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BrandList }
     *     
     */
    public void setBrandList(BrandList value) {
        this.brandList = value;
    }

    /**
     * Gets the value of the nationalOfferIndicator property.
     * 
     */
    public boolean isNationalOfferIndicator() {
        return nationalOfferIndicator;
    }

    /**
     * Sets the value of the nationalOfferIndicator property.
     * 
     */
    public void setNationalOfferIndicator(boolean value) {
        this.nationalOfferIndicator = value;
    }

    /**
     * Gets the value of the discountType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Sets the value of the discountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setDiscountType(DiscountType value) {
        this.discountType = value;
    }

    /**
     * Gets the value of the discountDefinitionType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountDefinitionType }
     *     
     */
    public DiscountDefinitionType getDiscountDefinitionType() {
        return discountDefinitionType;
    }

    /**
     * Sets the value of the discountDefinitionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountDefinitionType }
     *     
     */
    public void setDiscountDefinitionType(DiscountDefinitionType value) {
        this.discountDefinitionType = value;
    }

    /**
     * Gets the value of the discountDeliveryType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountDeliveryType }
     *     
     */
    public DiscountDeliveryType getDiscountDeliveryType() {
        return discountDeliveryType;
    }

    /**
     * Sets the value of the discountDeliveryType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountDeliveryType }
     *     
     */
    public void setDiscountDeliveryType(DiscountDeliveryType value) {
        this.discountDeliveryType = value;
    }

    /**
     * Gets the value of the sameBuyAndGetHardwareIndicator property.
     * 
     */
    public boolean isSameBuyAndGetHardwareIndicator() {
        return sameBuyAndGetHardwareIndicator;
    }

    /**
     * Sets the value of the sameBuyAndGetHardwareIndicator property.
     * 
     */
    public void setSameBuyAndGetHardwareIndicator(boolean value) {
        this.sameBuyAndGetHardwareIndicator = value;
    }

    /**
     * Gets the value of the multipleUnitIndicator property.
     * 
     */
    public boolean isMultipleUnitIndicator() {
        return multipleUnitIndicator;
    }

    /**
     * Sets the value of the multipleUnitIndicator property.
     * 
     */
    public void setMultipleUnitIndicator(boolean value) {
        this.multipleUnitIndicator = value;
    }

    /**
     * Gets the value of the sameBanIndicator property.
     * 
     */
    public boolean isSameBanIndicator() {
        return sameBanIndicator;
    }

    /**
     * Sets the value of the sameBanIndicator property.
     * 
     */
    public void setSameBanIndicator(boolean value) {
        this.sameBanIndicator = value;
    }

    /**
     * Gets the value of the mustBuyQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMustBuyQty() {
        return mustBuyQty;
    }

    /**
     * Sets the value of the mustBuyQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMustBuyQty(BigInteger value) {
        this.mustBuyQty = value;
    }

    /**
     * Gets the value of the mustGetQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMustGetQty() {
        return mustGetQty;
    }

    /**
     * Sets the value of the mustGetQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMustGetQty(BigInteger value) {
        this.mustGetQty = value;
    }

    /**
     * Gets the value of the containSimIndicator property.
     * 
     */
    public boolean isContainSimIndicator() {
        return containSimIndicator;
    }

    /**
     * Sets the value of the containSimIndicator property.
     * 
     */
    public void setContainSimIndicator(boolean value) {
        this.containSimIndicator = value;
    }

    /**
     * Gets the value of the containEsnIndicator property.
     * 
     */
    public boolean isContainEsnIndicator() {
        return containEsnIndicator;
    }

    /**
     * Sets the value of the containEsnIndicator property.
     * 
     */
    public void setContainEsnIndicator(boolean value) {
        this.containEsnIndicator = value;
    }

    /**
     * Gets the value of the containImeiIndicator property.
     * 
     */
    public boolean isContainImeiIndicator() {
        return containImeiIndicator;
    }

    /**
     * Sets the value of the containImeiIndicator property.
     * 
     */
    public void setContainImeiIndicator(boolean value) {
        this.containImeiIndicator = value;
    }

    /**
     * Gets the value of the hasOneGetOneIndicator property.
     * 
     */
    public boolean isHasOneGetOneIndicator() {
        return hasOneGetOneIndicator;
    }

    /**
     * Sets the value of the hasOneGetOneIndicator property.
     * 
     */
    public void setHasOneGetOneIndicator(boolean value) {
        this.hasOneGetOneIndicator = value;
    }

    /**
     * Gets the value of the protectionPeriod property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getProtectionPeriod() {
        return protectionPeriod;
    }

    /**
     * Sets the value of the protectionPeriod property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setProtectionPeriod(BigInteger value) {
        this.protectionPeriod = value;
    }

    /**
     * Gets the value of the billingMethod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingMethod() {
        return billingMethod;
    }

    /**
     * Sets the value of the billingMethod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingMethod(String value) {
        this.billingMethod = value;
    }

    /**
     * Gets the value of the eligibleTermList property.
     * 
     * @return
     *     possible object is
     *     {@link ContractTermList }
     *     
     */
    public ContractTermList getEligibleTermList() {
        return eligibleTermList;
    }

    /**
     * Sets the value of the eligibleTermList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractTermList }
     *     
     */
    public void setEligibleTermList(ContractTermList value) {
        this.eligibleTermList = value;
    }

    /**
     * Gets the value of the eligibleBuyTermList property.
     * 
     * @return
     *     possible object is
     *     {@link ContractTermList }
     *     
     */
    public ContractTermList getEligibleBuyTermList() {
        return eligibleBuyTermList;
    }

    /**
     * Sets the value of the eligibleBuyTermList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractTermList }
     *     
     */
    public void setEligibleBuyTermList(ContractTermList value) {
        this.eligibleBuyTermList = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link ProductType }
     *     
     */
    public ProductType getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductType }
     *     
     */
    public void setProductType(ProductType value) {
        this.productType = value;
    }

    /**
     * Gets the value of the eligibilityAdditionalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link EligibilityAdditionalInfo }
     *     
     */
    public EligibilityAdditionalInfo getEligibilityAdditionalInfo() {
        return eligibilityAdditionalInfo;
    }

    /**
     * Sets the value of the eligibilityAdditionalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EligibilityAdditionalInfo }
     *     
     */
    public void setEligibilityAdditionalInfo(EligibilityAdditionalInfo value) {
        this.eligibilityAdditionalInfo = value;
    }

    /**
     * Gets the value of the hardwareBuyList property.
     * 
     * @return
     *     possible object is
     *     {@link HardwareList }
     *     
     */
    public HardwareList getHardwareBuyList() {
        return hardwareBuyList;
    }

    /**
     * Sets the value of the hardwareBuyList property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardwareList }
     *     
     */
    public void setHardwareBuyList(HardwareList value) {
        this.hardwareBuyList = value;
    }

    /**
     * Gets the value of the hardwareGetList property.
     * 
     * @return
     *     possible object is
     *     {@link HardwareList }
     *     
     */
    public HardwareList getHardwareGetList() {
        return hardwareGetList;
    }

    /**
     * Sets the value of the hardwareGetList property.
     * 
     * @param value
     *     allowed object is
     *     {@link HardwareList }
     *     
     */
    public void setHardwareGetList(HardwareList value) {
        this.hardwareGetList = value;
    }

    /**
     * Gets the value of the billCreditForAllSKUIndicator property.
     * 
     */
    public boolean isBillCreditForAllSKUIndicator() {
        return billCreditForAllSKUIndicator;
    }

    /**
     * Sets the value of the billCreditForAllSKUIndicator property.
     * 
     */
    public void setBillCreditForAllSKUIndicator(boolean value) {
        this.billCreditForAllSKUIndicator = value;
    }

    /**
     * Gets the value of the buyOneGetOneIndicator property.
     * 
     */
    public boolean isBuyOneGetOneIndicator() {
        return buyOneGetOneIndicator;
    }

    /**
     * Sets the value of the buyOneGetOneIndicator property.
     * 
     */
    public void setBuyOneGetOneIndicator(boolean value) {
        this.buyOneGetOneIndicator = value;
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
            String theSapMaterialNumber;
            theSapMaterialNumber = this.getSapMaterialNumber();
            strategy.appendField(locator, this, "sapMaterialNumber", buffer, theSapMaterialNumber);
        }
        {
            String theFunctionCode;
            theFunctionCode = this.getFunctionCode();
            strategy.appendField(locator, this, "functionCode", buffer, theFunctionCode);
        }
        {
            AccountList theAccountList;
            theAccountList = this.getAccountList();
            strategy.appendField(locator, this, "accountList", buffer, theAccountList);
        }
        {
            AreaList theAvailableAreaList;
            theAvailableAreaList = this.getAvailableAreaList();
            strategy.appendField(locator, this, "availableAreaList", buffer, theAvailableAreaList);
        }
        {
            AreaList theMarketAreaList;
            theMarketAreaList = this.getMarketAreaList();
            strategy.appendField(locator, this, "marketAreaList", buffer, theMarketAreaList);
        }
        {
            BrandList theBrandList;
            theBrandList = this.getBrandList();
            strategy.appendField(locator, this, "brandList", buffer, theBrandList);
        }
        {
            boolean theNationalOfferIndicator;
            theNationalOfferIndicator = (true?this.isNationalOfferIndicator():false);
            strategy.appendField(locator, this, "nationalOfferIndicator", buffer, theNationalOfferIndicator);
        }
        {
            DiscountType theDiscountType;
            theDiscountType = this.getDiscountType();
            strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
        }
        {
            DiscountDefinitionType theDiscountDefinitionType;
            theDiscountDefinitionType = this.getDiscountDefinitionType();
            strategy.appendField(locator, this, "discountDefinitionType", buffer, theDiscountDefinitionType);
        }
        {
            DiscountDeliveryType theDiscountDeliveryType;
            theDiscountDeliveryType = this.getDiscountDeliveryType();
            strategy.appendField(locator, this, "discountDeliveryType", buffer, theDiscountDeliveryType);
        }
        {
            boolean theSameBuyAndGetHardwareIndicator;
            theSameBuyAndGetHardwareIndicator = (true?this.isSameBuyAndGetHardwareIndicator():false);
            strategy.appendField(locator, this, "sameBuyAndGetHardwareIndicator", buffer, theSameBuyAndGetHardwareIndicator);
        }
        {
            boolean theMultipleUnitIndicator;
            theMultipleUnitIndicator = (true?this.isMultipleUnitIndicator():false);
            strategy.appendField(locator, this, "multipleUnitIndicator", buffer, theMultipleUnitIndicator);
        }
        {
            boolean theSameBanIndicator;
            theSameBanIndicator = (true?this.isSameBanIndicator():false);
            strategy.appendField(locator, this, "sameBanIndicator", buffer, theSameBanIndicator);
        }
        {
            BigInteger theMustBuyQty;
            theMustBuyQty = this.getMustBuyQty();
            strategy.appendField(locator, this, "mustBuyQty", buffer, theMustBuyQty);
        }
        {
            BigInteger theMustGetQty;
            theMustGetQty = this.getMustGetQty();
            strategy.appendField(locator, this, "mustGetQty", buffer, theMustGetQty);
        }
        {
            boolean theContainSimIndicator;
            theContainSimIndicator = (true?this.isContainSimIndicator():false);
            strategy.appendField(locator, this, "containSimIndicator", buffer, theContainSimIndicator);
        }
        {
            boolean theContainEsnIndicator;
            theContainEsnIndicator = (true?this.isContainEsnIndicator():false);
            strategy.appendField(locator, this, "containEsnIndicator", buffer, theContainEsnIndicator);
        }
        {
            boolean theContainImeiIndicator;
            theContainImeiIndicator = (true?this.isContainImeiIndicator():false);
            strategy.appendField(locator, this, "containImeiIndicator", buffer, theContainImeiIndicator);
        }
        {
            boolean theHasOneGetOneIndicator;
            theHasOneGetOneIndicator = (true?this.isHasOneGetOneIndicator():false);
            strategy.appendField(locator, this, "hasOneGetOneIndicator", buffer, theHasOneGetOneIndicator);
        }
        {
            BigInteger theProtectionPeriod;
            theProtectionPeriod = this.getProtectionPeriod();
            strategy.appendField(locator, this, "protectionPeriod", buffer, theProtectionPeriod);
        }
        {
            String theBillingMethod;
            theBillingMethod = this.getBillingMethod();
            strategy.appendField(locator, this, "billingMethod", buffer, theBillingMethod);
        }
        {
            ContractTermList theEligibleTermList;
            theEligibleTermList = this.getEligibleTermList();
            strategy.appendField(locator, this, "eligibleTermList", buffer, theEligibleTermList);
        }
        {
            ContractTermList theEligibleBuyTermList;
            theEligibleBuyTermList = this.getEligibleBuyTermList();
            strategy.appendField(locator, this, "eligibleBuyTermList", buffer, theEligibleBuyTermList);
        }
        {
            ProductType theProductType;
            theProductType = this.getProductType();
            strategy.appendField(locator, this, "productType", buffer, theProductType);
        }
        {
            EligibilityAdditionalInfo theEligibilityAdditionalInfo;
            theEligibilityAdditionalInfo = this.getEligibilityAdditionalInfo();
            strategy.appendField(locator, this, "eligibilityAdditionalInfo", buffer, theEligibilityAdditionalInfo);
        }
        {
            HardwareList theHardwareBuyList;
            theHardwareBuyList = this.getHardwareBuyList();
            strategy.appendField(locator, this, "hardwareBuyList", buffer, theHardwareBuyList);
        }
        {
            HardwareList theHardwareGetList;
            theHardwareGetList = this.getHardwareGetList();
            strategy.appendField(locator, this, "hardwareGetList", buffer, theHardwareGetList);
        }
        {
            boolean theBillCreditForAllSKUIndicator;
            theBillCreditForAllSKUIndicator = (true?this.isBillCreditForAllSKUIndicator():false);
            strategy.appendField(locator, this, "billCreditForAllSKUIndicator", buffer, theBillCreditForAllSKUIndicator);
        }
        {
            boolean theBuyOneGetOneIndicator;
            theBuyOneGetOneIndicator = (true?this.isBuyOneGetOneIndicator():false);
            strategy.appendField(locator, this, "buyOneGetOneIndicator", buffer, theBuyOneGetOneIndicator);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PromotionalOfferItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PromotionalOfferItem that = ((PromotionalOfferItem) object);
        {
            String lhsSapMaterialNumber;
            lhsSapMaterialNumber = this.getSapMaterialNumber();
            String rhsSapMaterialNumber;
            rhsSapMaterialNumber = that.getSapMaterialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sapMaterialNumber", lhsSapMaterialNumber), LocatorUtils.property(thatLocator, "sapMaterialNumber", rhsSapMaterialNumber), lhsSapMaterialNumber, rhsSapMaterialNumber)) {
                return false;
            }
        }
        {
            String lhsFunctionCode;
            lhsFunctionCode = this.getFunctionCode();
            String rhsFunctionCode;
            rhsFunctionCode = that.getFunctionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "functionCode", lhsFunctionCode), LocatorUtils.property(thatLocator, "functionCode", rhsFunctionCode), lhsFunctionCode, rhsFunctionCode)) {
                return false;
            }
        }
        {
            AccountList lhsAccountList;
            lhsAccountList = this.getAccountList();
            AccountList rhsAccountList;
            rhsAccountList = that.getAccountList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountList", lhsAccountList), LocatorUtils.property(thatLocator, "accountList", rhsAccountList), lhsAccountList, rhsAccountList)) {
                return false;
            }
        }
        {
            AreaList lhsAvailableAreaList;
            lhsAvailableAreaList = this.getAvailableAreaList();
            AreaList rhsAvailableAreaList;
            rhsAvailableAreaList = that.getAvailableAreaList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "availableAreaList", lhsAvailableAreaList), LocatorUtils.property(thatLocator, "availableAreaList", rhsAvailableAreaList), lhsAvailableAreaList, rhsAvailableAreaList)) {
                return false;
            }
        }
        {
            AreaList lhsMarketAreaList;
            lhsMarketAreaList = this.getMarketAreaList();
            AreaList rhsMarketAreaList;
            rhsMarketAreaList = that.getMarketAreaList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "marketAreaList", lhsMarketAreaList), LocatorUtils.property(thatLocator, "marketAreaList", rhsMarketAreaList), lhsMarketAreaList, rhsMarketAreaList)) {
                return false;
            }
        }
        {
            BrandList lhsBrandList;
            lhsBrandList = this.getBrandList();
            BrandList rhsBrandList;
            rhsBrandList = that.getBrandList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "brandList", lhsBrandList), LocatorUtils.property(thatLocator, "brandList", rhsBrandList), lhsBrandList, rhsBrandList)) {
                return false;
            }
        }
        {
            boolean lhsNationalOfferIndicator;
            lhsNationalOfferIndicator = (true?this.isNationalOfferIndicator():false);
            boolean rhsNationalOfferIndicator;
            rhsNationalOfferIndicator = (true?that.isNationalOfferIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nationalOfferIndicator", lhsNationalOfferIndicator), LocatorUtils.property(thatLocator, "nationalOfferIndicator", rhsNationalOfferIndicator), lhsNationalOfferIndicator, rhsNationalOfferIndicator)) {
                return false;
            }
        }
        {
            DiscountType lhsDiscountType;
            lhsDiscountType = this.getDiscountType();
            DiscountType rhsDiscountType;
            rhsDiscountType = that.getDiscountType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountType", lhsDiscountType), LocatorUtils.property(thatLocator, "discountType", rhsDiscountType), lhsDiscountType, rhsDiscountType)) {
                return false;
            }
        }
        {
            DiscountDefinitionType lhsDiscountDefinitionType;
            lhsDiscountDefinitionType = this.getDiscountDefinitionType();
            DiscountDefinitionType rhsDiscountDefinitionType;
            rhsDiscountDefinitionType = that.getDiscountDefinitionType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountDefinitionType", lhsDiscountDefinitionType), LocatorUtils.property(thatLocator, "discountDefinitionType", rhsDiscountDefinitionType), lhsDiscountDefinitionType, rhsDiscountDefinitionType)) {
                return false;
            }
        }
        {
            DiscountDeliveryType lhsDiscountDeliveryType;
            lhsDiscountDeliveryType = this.getDiscountDeliveryType();
            DiscountDeliveryType rhsDiscountDeliveryType;
            rhsDiscountDeliveryType = that.getDiscountDeliveryType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountDeliveryType", lhsDiscountDeliveryType), LocatorUtils.property(thatLocator, "discountDeliveryType", rhsDiscountDeliveryType), lhsDiscountDeliveryType, rhsDiscountDeliveryType)) {
                return false;
            }
        }
        {
            boolean lhsSameBuyAndGetHardwareIndicator;
            lhsSameBuyAndGetHardwareIndicator = (true?this.isSameBuyAndGetHardwareIndicator():false);
            boolean rhsSameBuyAndGetHardwareIndicator;
            rhsSameBuyAndGetHardwareIndicator = (true?that.isSameBuyAndGetHardwareIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sameBuyAndGetHardwareIndicator", lhsSameBuyAndGetHardwareIndicator), LocatorUtils.property(thatLocator, "sameBuyAndGetHardwareIndicator", rhsSameBuyAndGetHardwareIndicator), lhsSameBuyAndGetHardwareIndicator, rhsSameBuyAndGetHardwareIndicator)) {
                return false;
            }
        }
        {
            boolean lhsMultipleUnitIndicator;
            lhsMultipleUnitIndicator = (true?this.isMultipleUnitIndicator():false);
            boolean rhsMultipleUnitIndicator;
            rhsMultipleUnitIndicator = (true?that.isMultipleUnitIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "multipleUnitIndicator", lhsMultipleUnitIndicator), LocatorUtils.property(thatLocator, "multipleUnitIndicator", rhsMultipleUnitIndicator), lhsMultipleUnitIndicator, rhsMultipleUnitIndicator)) {
                return false;
            }
        }
        {
            boolean lhsSameBanIndicator;
            lhsSameBanIndicator = (true?this.isSameBanIndicator():false);
            boolean rhsSameBanIndicator;
            rhsSameBanIndicator = (true?that.isSameBanIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sameBanIndicator", lhsSameBanIndicator), LocatorUtils.property(thatLocator, "sameBanIndicator", rhsSameBanIndicator), lhsSameBanIndicator, rhsSameBanIndicator)) {
                return false;
            }
        }
        {
            BigInteger lhsMustBuyQty;
            lhsMustBuyQty = this.getMustBuyQty();
            BigInteger rhsMustBuyQty;
            rhsMustBuyQty = that.getMustBuyQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mustBuyQty", lhsMustBuyQty), LocatorUtils.property(thatLocator, "mustBuyQty", rhsMustBuyQty), lhsMustBuyQty, rhsMustBuyQty)) {
                return false;
            }
        }
        {
            BigInteger lhsMustGetQty;
            lhsMustGetQty = this.getMustGetQty();
            BigInteger rhsMustGetQty;
            rhsMustGetQty = that.getMustGetQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mustGetQty", lhsMustGetQty), LocatorUtils.property(thatLocator, "mustGetQty", rhsMustGetQty), lhsMustGetQty, rhsMustGetQty)) {
                return false;
            }
        }
        {
            boolean lhsContainSimIndicator;
            lhsContainSimIndicator = (true?this.isContainSimIndicator():false);
            boolean rhsContainSimIndicator;
            rhsContainSimIndicator = (true?that.isContainSimIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "containSimIndicator", lhsContainSimIndicator), LocatorUtils.property(thatLocator, "containSimIndicator", rhsContainSimIndicator), lhsContainSimIndicator, rhsContainSimIndicator)) {
                return false;
            }
        }
        {
            boolean lhsContainEsnIndicator;
            lhsContainEsnIndicator = (true?this.isContainEsnIndicator():false);
            boolean rhsContainEsnIndicator;
            rhsContainEsnIndicator = (true?that.isContainEsnIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "containEsnIndicator", lhsContainEsnIndicator), LocatorUtils.property(thatLocator, "containEsnIndicator", rhsContainEsnIndicator), lhsContainEsnIndicator, rhsContainEsnIndicator)) {
                return false;
            }
        }
        {
            boolean lhsContainImeiIndicator;
            lhsContainImeiIndicator = (true?this.isContainImeiIndicator():false);
            boolean rhsContainImeiIndicator;
            rhsContainImeiIndicator = (true?that.isContainImeiIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "containImeiIndicator", lhsContainImeiIndicator), LocatorUtils.property(thatLocator, "containImeiIndicator", rhsContainImeiIndicator), lhsContainImeiIndicator, rhsContainImeiIndicator)) {
                return false;
            }
        }
        {
            boolean lhsHasOneGetOneIndicator;
            lhsHasOneGetOneIndicator = (true?this.isHasOneGetOneIndicator():false);
            boolean rhsHasOneGetOneIndicator;
            rhsHasOneGetOneIndicator = (true?that.isHasOneGetOneIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hasOneGetOneIndicator", lhsHasOneGetOneIndicator), LocatorUtils.property(thatLocator, "hasOneGetOneIndicator", rhsHasOneGetOneIndicator), lhsHasOneGetOneIndicator, rhsHasOneGetOneIndicator)) {
                return false;
            }
        }
        {
            BigInteger lhsProtectionPeriod;
            lhsProtectionPeriod = this.getProtectionPeriod();
            BigInteger rhsProtectionPeriod;
            rhsProtectionPeriod = that.getProtectionPeriod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "protectionPeriod", lhsProtectionPeriod), LocatorUtils.property(thatLocator, "protectionPeriod", rhsProtectionPeriod), lhsProtectionPeriod, rhsProtectionPeriod)) {
                return false;
            }
        }
        {
            String lhsBillingMethod;
            lhsBillingMethod = this.getBillingMethod();
            String rhsBillingMethod;
            rhsBillingMethod = that.getBillingMethod();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingMethod", lhsBillingMethod), LocatorUtils.property(thatLocator, "billingMethod", rhsBillingMethod), lhsBillingMethod, rhsBillingMethod)) {
                return false;
            }
        }
        {
            ContractTermList lhsEligibleTermList;
            lhsEligibleTermList = this.getEligibleTermList();
            ContractTermList rhsEligibleTermList;
            rhsEligibleTermList = that.getEligibleTermList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleTermList", lhsEligibleTermList), LocatorUtils.property(thatLocator, "eligibleTermList", rhsEligibleTermList), lhsEligibleTermList, rhsEligibleTermList)) {
                return false;
            }
        }
        {
            ContractTermList lhsEligibleBuyTermList;
            lhsEligibleBuyTermList = this.getEligibleBuyTermList();
            ContractTermList rhsEligibleBuyTermList;
            rhsEligibleBuyTermList = that.getEligibleBuyTermList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleBuyTermList", lhsEligibleBuyTermList), LocatorUtils.property(thatLocator, "eligibleBuyTermList", rhsEligibleBuyTermList), lhsEligibleBuyTermList, rhsEligibleBuyTermList)) {
                return false;
            }
        }
        {
            ProductType lhsProductType;
            lhsProductType = this.getProductType();
            ProductType rhsProductType;
            rhsProductType = that.getProductType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productType", lhsProductType), LocatorUtils.property(thatLocator, "productType", rhsProductType), lhsProductType, rhsProductType)) {
                return false;
            }
        }
        {
            EligibilityAdditionalInfo lhsEligibilityAdditionalInfo;
            lhsEligibilityAdditionalInfo = this.getEligibilityAdditionalInfo();
            EligibilityAdditionalInfo rhsEligibilityAdditionalInfo;
            rhsEligibilityAdditionalInfo = that.getEligibilityAdditionalInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibilityAdditionalInfo", lhsEligibilityAdditionalInfo), LocatorUtils.property(thatLocator, "eligibilityAdditionalInfo", rhsEligibilityAdditionalInfo), lhsEligibilityAdditionalInfo, rhsEligibilityAdditionalInfo)) {
                return false;
            }
        }
        {
            HardwareList lhsHardwareBuyList;
            lhsHardwareBuyList = this.getHardwareBuyList();
            HardwareList rhsHardwareBuyList;
            rhsHardwareBuyList = that.getHardwareBuyList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwareBuyList", lhsHardwareBuyList), LocatorUtils.property(thatLocator, "hardwareBuyList", rhsHardwareBuyList), lhsHardwareBuyList, rhsHardwareBuyList)) {
                return false;
            }
        }
        {
            HardwareList lhsHardwareGetList;
            lhsHardwareGetList = this.getHardwareGetList();
            HardwareList rhsHardwareGetList;
            rhsHardwareGetList = that.getHardwareGetList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "hardwareGetList", lhsHardwareGetList), LocatorUtils.property(thatLocator, "hardwareGetList", rhsHardwareGetList), lhsHardwareGetList, rhsHardwareGetList)) {
                return false;
            }
        }
        {
            boolean lhsBillCreditForAllSKUIndicator;
            lhsBillCreditForAllSKUIndicator = (true?this.isBillCreditForAllSKUIndicator():false);
            boolean rhsBillCreditForAllSKUIndicator;
            rhsBillCreditForAllSKUIndicator = (true?that.isBillCreditForAllSKUIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billCreditForAllSKUIndicator", lhsBillCreditForAllSKUIndicator), LocatorUtils.property(thatLocator, "billCreditForAllSKUIndicator", rhsBillCreditForAllSKUIndicator), lhsBillCreditForAllSKUIndicator, rhsBillCreditForAllSKUIndicator)) {
                return false;
            }
        }
        {
            boolean lhsBuyOneGetOneIndicator;
            lhsBuyOneGetOneIndicator = (true?this.isBuyOneGetOneIndicator():false);
            boolean rhsBuyOneGetOneIndicator;
            rhsBuyOneGetOneIndicator = (true?that.isBuyOneGetOneIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "buyOneGetOneIndicator", lhsBuyOneGetOneIndicator), LocatorUtils.property(thatLocator, "buyOneGetOneIndicator", rhsBuyOneGetOneIndicator), lhsBuyOneGetOneIndicator, rhsBuyOneGetOneIndicator)) {
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
