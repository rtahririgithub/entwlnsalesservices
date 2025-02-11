
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
import com.telus.framework.xml.bind.DateTimeAdapter;
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
 * <p>Java class for ProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Handset">
 *       &lt;sequence>
 *         &lt;element name="productName" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualNameList"/>
 *         &lt;element name="productDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="inMarketProductName" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualNameList" minOccurs="0"/>
 *         &lt;element name="networkTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCategoryName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="isAppleIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="refurbishedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="productType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="universalProductCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productImageLinkUrl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productFeatureList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="productEffectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productExpiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="productClassificationCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="technologyTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productAliasList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductAliasType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="financingOptionList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}FinancingOptionsType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="compatibleSimList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CompatibleSimType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="sapMaterialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="directShipSkuIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="recommendedIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="pairableDeviceInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductSummary", propOrder = {
    "productName",
    "productDescription",
    "inMarketProductName",
    "networkTypeCode",
    "productCategoryName",
    "deviceTypeCode",
    "isAppleIndicator",
    "refurbishedIndicator",
    "productType",
    "universalProductCode",
    "productImageLinkUrl",
    "productFeatureList",
    "productEffectiveDate",
    "productExpiryDate",
    "productClassificationCode",
    "technologyTypeCode",
    "productAliasList",
    "financingOptionList",
    "compatibleSimList",
    "sapMaterialNum",
    "directShipSkuIndicator",
    "recommendedIndicator",
    "pairableDeviceInd"
})
public class ProductSummary
    extends Handset
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected MultilingualNameList productName;
    protected MultilingualDescriptionList productDescription;
    protected MultilingualNameList inMarketProductName;
    protected String networkTypeCode;
    protected String productCategoryName;
    protected String deviceTypeCode;
    protected Boolean isAppleIndicator;
    protected Boolean refurbishedIndicator;
    protected String productType;
    protected String universalProductCode;
    protected String productImageLinkUrl;
    protected List<String> productFeatureList;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date productEffectiveDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date productExpiryDate;
    protected String productClassificationCode;
    protected String technologyTypeCode;
    protected List<ProductAliasType> productAliasList;
    protected List<FinancingOptionsType> financingOptionList;
    protected List<CompatibleSimType> compatibleSimList;
    protected String sapMaterialNum;
    protected Boolean directShipSkuIndicator;
    protected Boolean recommendedIndicator;
    protected Boolean pairableDeviceInd;

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualNameList }
     *     
     */
    public MultilingualNameList getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualNameList }
     *     
     */
    public void setProductName(MultilingualNameList value) {
        this.productName = value;
    }

    /**
     * Gets the value of the productDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getProductDescription() {
        return productDescription;
    }

    /**
     * Sets the value of the productDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setProductDescription(MultilingualDescriptionList value) {
        this.productDescription = value;
    }

    /**
     * Gets the value of the inMarketProductName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualNameList }
     *     
     */
    public MultilingualNameList getInMarketProductName() {
        return inMarketProductName;
    }

    /**
     * Sets the value of the inMarketProductName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualNameList }
     *     
     */
    public void setInMarketProductName(MultilingualNameList value) {
        this.inMarketProductName = value;
    }

    /**
     * Gets the value of the networkTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNetworkTypeCode() {
        return networkTypeCode;
    }

    /**
     * Sets the value of the networkTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNetworkTypeCode(String value) {
        this.networkTypeCode = value;
    }

    /**
     * Gets the value of the productCategoryName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategoryName() {
        return productCategoryName;
    }

    /**
     * Sets the value of the productCategoryName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategoryName(String value) {
        this.productCategoryName = value;
    }

    /**
     * Gets the value of the deviceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceTypeCode() {
        return deviceTypeCode;
    }

    /**
     * Sets the value of the deviceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceTypeCode(String value) {
        this.deviceTypeCode = value;
    }

    /**
     * Gets the value of the isAppleIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isIsAppleIndicator() {
        return isAppleIndicator;
    }

    /**
     * Sets the value of the isAppleIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setIsAppleIndicator(Boolean value) {
        this.isAppleIndicator = value;
    }

    /**
     * Gets the value of the refurbishedIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRefurbishedIndicator() {
        return refurbishedIndicator;
    }

    /**
     * Sets the value of the refurbishedIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRefurbishedIndicator(Boolean value) {
        this.refurbishedIndicator = value;
    }

    /**
     * Gets the value of the productType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Sets the value of the productType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductType(String value) {
        this.productType = value;
    }

    /**
     * Gets the value of the universalProductCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUniversalProductCode() {
        return universalProductCode;
    }

    /**
     * Sets the value of the universalProductCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUniversalProductCode(String value) {
        this.universalProductCode = value;
    }

    /**
     * Gets the value of the productImageLinkUrl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductImageLinkUrl() {
        return productImageLinkUrl;
    }

    /**
     * Sets the value of the productImageLinkUrl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductImageLinkUrl(String value) {
        this.productImageLinkUrl = value;
    }

    /**
     * Gets the value of the productFeatureList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productFeatureList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductFeatureList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProductFeatureList() {
        if (productFeatureList == null) {
            productFeatureList = new ArrayList<String>();
        }
        return this.productFeatureList;
    }

    /**
     * Gets the value of the productEffectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getProductEffectiveDate() {
        return productEffectiveDate;
    }

    /**
     * Sets the value of the productEffectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductEffectiveDate(Date value) {
        this.productEffectiveDate = value;
    }

    /**
     * Gets the value of the productExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getProductExpiryDate() {
        return productExpiryDate;
    }

    /**
     * Sets the value of the productExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductExpiryDate(Date value) {
        this.productExpiryDate = value;
    }

    /**
     * Gets the value of the productClassificationCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductClassificationCode() {
        return productClassificationCode;
    }

    /**
     * Sets the value of the productClassificationCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductClassificationCode(String value) {
        this.productClassificationCode = value;
    }

    /**
     * Gets the value of the technologyTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTechnologyTypeCode() {
        return technologyTypeCode;
    }

    /**
     * Sets the value of the technologyTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTechnologyTypeCode(String value) {
        this.technologyTypeCode = value;
    }

    /**
     * Gets the value of the productAliasList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productAliasList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductAliasList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductAliasType }
     * 
     * 
     */
    public List<ProductAliasType> getProductAliasList() {
        if (productAliasList == null) {
            productAliasList = new ArrayList<ProductAliasType>();
        }
        return this.productAliasList;
    }

    /**
     * Gets the value of the financingOptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the financingOptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFinancingOptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link FinancingOptionsType }
     * 
     * 
     */
    public List<FinancingOptionsType> getFinancingOptionList() {
        if (financingOptionList == null) {
            financingOptionList = new ArrayList<FinancingOptionsType>();
        }
        return this.financingOptionList;
    }

    /**
     * Gets the value of the compatibleSimList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compatibleSimList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompatibleSimList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CompatibleSimType }
     * 
     * 
     */
    public List<CompatibleSimType> getCompatibleSimList() {
        if (compatibleSimList == null) {
            compatibleSimList = new ArrayList<CompatibleSimType>();
        }
        return this.compatibleSimList;
    }

    /**
     * Gets the value of the sapMaterialNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSapMaterialNum() {
        return sapMaterialNum;
    }

    /**
     * Sets the value of the sapMaterialNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSapMaterialNum(String value) {
        this.sapMaterialNum = value;
    }

    /**
     * Gets the value of the directShipSkuIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDirectShipSkuIndicator() {
        return directShipSkuIndicator;
    }

    /**
     * Sets the value of the directShipSkuIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDirectShipSkuIndicator(Boolean value) {
        this.directShipSkuIndicator = value;
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
     * Gets the value of the pairableDeviceInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPairableDeviceInd() {
        return pairableDeviceInd;
    }

    /**
     * Sets the value of the pairableDeviceInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPairableDeviceInd(Boolean value) {
        this.pairableDeviceInd = value;
    }

    /**
     * Sets the value of the productFeatureList property.
     * 
     * @param productFeatureList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductFeatureList(List<String> productFeatureList) {
        this.productFeatureList = productFeatureList;
    }

    /**
     * Sets the value of the productAliasList property.
     * 
     * @param productAliasList
     *     allowed object is
     *     {@link ProductAliasType }
     *     
     */
    public void setProductAliasList(List<ProductAliasType> productAliasList) {
        this.productAliasList = productAliasList;
    }

    /**
     * Sets the value of the financingOptionList property.
     * 
     * @param financingOptionList
     *     allowed object is
     *     {@link FinancingOptionsType }
     *     
     */
    public void setFinancingOptionList(List<FinancingOptionsType> financingOptionList) {
        this.financingOptionList = financingOptionList;
    }

    /**
     * Sets the value of the compatibleSimList property.
     * 
     * @param compatibleSimList
     *     allowed object is
     *     {@link CompatibleSimType }
     *     
     */
    public void setCompatibleSimList(List<CompatibleSimType> compatibleSimList) {
        this.compatibleSimList = compatibleSimList;
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
        super.appendFields(locator, buffer, strategy);
        {
            MultilingualNameList theProductName;
            theProductName = this.getProductName();
            strategy.appendField(locator, this, "productName", buffer, theProductName);
        }
        {
            MultilingualDescriptionList theProductDescription;
            theProductDescription = this.getProductDescription();
            strategy.appendField(locator, this, "productDescription", buffer, theProductDescription);
        }
        {
            MultilingualNameList theInMarketProductName;
            theInMarketProductName = this.getInMarketProductName();
            strategy.appendField(locator, this, "inMarketProductName", buffer, theInMarketProductName);
        }
        {
            String theNetworkTypeCode;
            theNetworkTypeCode = this.getNetworkTypeCode();
            strategy.appendField(locator, this, "networkTypeCode", buffer, theNetworkTypeCode);
        }
        {
            String theProductCategoryName;
            theProductCategoryName = this.getProductCategoryName();
            strategy.appendField(locator, this, "productCategoryName", buffer, theProductCategoryName);
        }
        {
            String theDeviceTypeCode;
            theDeviceTypeCode = this.getDeviceTypeCode();
            strategy.appendField(locator, this, "deviceTypeCode", buffer, theDeviceTypeCode);
        }
        {
            Boolean theIsAppleIndicator;
            theIsAppleIndicator = this.isIsAppleIndicator();
            strategy.appendField(locator, this, "isAppleIndicator", buffer, theIsAppleIndicator);
        }
        {
            Boolean theRefurbishedIndicator;
            theRefurbishedIndicator = this.isRefurbishedIndicator();
            strategy.appendField(locator, this, "refurbishedIndicator", buffer, theRefurbishedIndicator);
        }
        {
            String theProductType;
            theProductType = this.getProductType();
            strategy.appendField(locator, this, "productType", buffer, theProductType);
        }
        {
            String theUniversalProductCode;
            theUniversalProductCode = this.getUniversalProductCode();
            strategy.appendField(locator, this, "universalProductCode", buffer, theUniversalProductCode);
        }
        {
            String theProductImageLinkUrl;
            theProductImageLinkUrl = this.getProductImageLinkUrl();
            strategy.appendField(locator, this, "productImageLinkUrl", buffer, theProductImageLinkUrl);
        }
        {
            List<String> theProductFeatureList;
            theProductFeatureList = (((this.productFeatureList!= null)&&(!this.productFeatureList.isEmpty()))?this.getProductFeatureList():null);
            strategy.appendField(locator, this, "productFeatureList", buffer, theProductFeatureList);
        }
        {
            Date theProductEffectiveDate;
            theProductEffectiveDate = this.getProductEffectiveDate();
            strategy.appendField(locator, this, "productEffectiveDate", buffer, theProductEffectiveDate);
        }
        {
            Date theProductExpiryDate;
            theProductExpiryDate = this.getProductExpiryDate();
            strategy.appendField(locator, this, "productExpiryDate", buffer, theProductExpiryDate);
        }
        {
            String theProductClassificationCode;
            theProductClassificationCode = this.getProductClassificationCode();
            strategy.appendField(locator, this, "productClassificationCode", buffer, theProductClassificationCode);
        }
        {
            String theTechnologyTypeCode;
            theTechnologyTypeCode = this.getTechnologyTypeCode();
            strategy.appendField(locator, this, "technologyTypeCode", buffer, theTechnologyTypeCode);
        }
        {
            List<ProductAliasType> theProductAliasList;
            theProductAliasList = (((this.productAliasList!= null)&&(!this.productAliasList.isEmpty()))?this.getProductAliasList():null);
            strategy.appendField(locator, this, "productAliasList", buffer, theProductAliasList);
        }
        {
            List<FinancingOptionsType> theFinancingOptionList;
            theFinancingOptionList = (((this.financingOptionList!= null)&&(!this.financingOptionList.isEmpty()))?this.getFinancingOptionList():null);
            strategy.appendField(locator, this, "financingOptionList", buffer, theFinancingOptionList);
        }
        {
            List<CompatibleSimType> theCompatibleSimList;
            theCompatibleSimList = (((this.compatibleSimList!= null)&&(!this.compatibleSimList.isEmpty()))?this.getCompatibleSimList():null);
            strategy.appendField(locator, this, "compatibleSimList", buffer, theCompatibleSimList);
        }
        {
            String theSapMaterialNum;
            theSapMaterialNum = this.getSapMaterialNum();
            strategy.appendField(locator, this, "sapMaterialNum", buffer, theSapMaterialNum);
        }
        {
            Boolean theDirectShipSkuIndicator;
            theDirectShipSkuIndicator = this.isDirectShipSkuIndicator();
            strategy.appendField(locator, this, "directShipSkuIndicator", buffer, theDirectShipSkuIndicator);
        }
        {
            Boolean theRecommendedIndicator;
            theRecommendedIndicator = this.isRecommendedIndicator();
            strategy.appendField(locator, this, "recommendedIndicator", buffer, theRecommendedIndicator);
        }
        {
            Boolean thePairableDeviceInd;
            thePairableDeviceInd = this.isPairableDeviceInd();
            strategy.appendField(locator, this, "pairableDeviceInd", buffer, thePairableDeviceInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ProductSummary that = ((ProductSummary) object);
        {
            MultilingualNameList lhsProductName;
            lhsProductName = this.getProductName();
            MultilingualNameList rhsProductName;
            rhsProductName = that.getProductName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productName", lhsProductName), LocatorUtils.property(thatLocator, "productName", rhsProductName), lhsProductName, rhsProductName)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsProductDescription;
            lhsProductDescription = this.getProductDescription();
            MultilingualDescriptionList rhsProductDescription;
            rhsProductDescription = that.getProductDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productDescription", lhsProductDescription), LocatorUtils.property(thatLocator, "productDescription", rhsProductDescription), lhsProductDescription, rhsProductDescription)) {
                return false;
            }
        }
        {
            MultilingualNameList lhsInMarketProductName;
            lhsInMarketProductName = this.getInMarketProductName();
            MultilingualNameList rhsInMarketProductName;
            rhsInMarketProductName = that.getInMarketProductName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inMarketProductName", lhsInMarketProductName), LocatorUtils.property(thatLocator, "inMarketProductName", rhsInMarketProductName), lhsInMarketProductName, rhsInMarketProductName)) {
                return false;
            }
        }
        {
            String lhsNetworkTypeCode;
            lhsNetworkTypeCode = this.getNetworkTypeCode();
            String rhsNetworkTypeCode;
            rhsNetworkTypeCode = that.getNetworkTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "networkTypeCode", lhsNetworkTypeCode), LocatorUtils.property(thatLocator, "networkTypeCode", rhsNetworkTypeCode), lhsNetworkTypeCode, rhsNetworkTypeCode)) {
                return false;
            }
        }
        {
            String lhsProductCategoryName;
            lhsProductCategoryName = this.getProductCategoryName();
            String rhsProductCategoryName;
            rhsProductCategoryName = that.getProductCategoryName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCategoryName", lhsProductCategoryName), LocatorUtils.property(thatLocator, "productCategoryName", rhsProductCategoryName), lhsProductCategoryName, rhsProductCategoryName)) {
                return false;
            }
        }
        {
            String lhsDeviceTypeCode;
            lhsDeviceTypeCode = this.getDeviceTypeCode();
            String rhsDeviceTypeCode;
            rhsDeviceTypeCode = that.getDeviceTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deviceTypeCode", lhsDeviceTypeCode), LocatorUtils.property(thatLocator, "deviceTypeCode", rhsDeviceTypeCode), lhsDeviceTypeCode, rhsDeviceTypeCode)) {
                return false;
            }
        }
        {
            Boolean lhsIsAppleIndicator;
            lhsIsAppleIndicator = this.isIsAppleIndicator();
            Boolean rhsIsAppleIndicator;
            rhsIsAppleIndicator = that.isIsAppleIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "isAppleIndicator", lhsIsAppleIndicator), LocatorUtils.property(thatLocator, "isAppleIndicator", rhsIsAppleIndicator), lhsIsAppleIndicator, rhsIsAppleIndicator)) {
                return false;
            }
        }
        {
            Boolean lhsRefurbishedIndicator;
            lhsRefurbishedIndicator = this.isRefurbishedIndicator();
            Boolean rhsRefurbishedIndicator;
            rhsRefurbishedIndicator = that.isRefurbishedIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "refurbishedIndicator", lhsRefurbishedIndicator), LocatorUtils.property(thatLocator, "refurbishedIndicator", rhsRefurbishedIndicator), lhsRefurbishedIndicator, rhsRefurbishedIndicator)) {
                return false;
            }
        }
        {
            String lhsProductType;
            lhsProductType = this.getProductType();
            String rhsProductType;
            rhsProductType = that.getProductType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productType", lhsProductType), LocatorUtils.property(thatLocator, "productType", rhsProductType), lhsProductType, rhsProductType)) {
                return false;
            }
        }
        {
            String lhsUniversalProductCode;
            lhsUniversalProductCode = this.getUniversalProductCode();
            String rhsUniversalProductCode;
            rhsUniversalProductCode = that.getUniversalProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "universalProductCode", lhsUniversalProductCode), LocatorUtils.property(thatLocator, "universalProductCode", rhsUniversalProductCode), lhsUniversalProductCode, rhsUniversalProductCode)) {
                return false;
            }
        }
        {
            String lhsProductImageLinkUrl;
            lhsProductImageLinkUrl = this.getProductImageLinkUrl();
            String rhsProductImageLinkUrl;
            rhsProductImageLinkUrl = that.getProductImageLinkUrl();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productImageLinkUrl", lhsProductImageLinkUrl), LocatorUtils.property(thatLocator, "productImageLinkUrl", rhsProductImageLinkUrl), lhsProductImageLinkUrl, rhsProductImageLinkUrl)) {
                return false;
            }
        }
        {
            List<String> lhsProductFeatureList;
            lhsProductFeatureList = (((this.productFeatureList!= null)&&(!this.productFeatureList.isEmpty()))?this.getProductFeatureList():null);
            List<String> rhsProductFeatureList;
            rhsProductFeatureList = (((that.productFeatureList!= null)&&(!that.productFeatureList.isEmpty()))?that.getProductFeatureList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productFeatureList", lhsProductFeatureList), LocatorUtils.property(thatLocator, "productFeatureList", rhsProductFeatureList), lhsProductFeatureList, rhsProductFeatureList)) {
                return false;
            }
        }
        {
            Date lhsProductEffectiveDate;
            lhsProductEffectiveDate = this.getProductEffectiveDate();
            Date rhsProductEffectiveDate;
            rhsProductEffectiveDate = that.getProductEffectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productEffectiveDate", lhsProductEffectiveDate), LocatorUtils.property(thatLocator, "productEffectiveDate", rhsProductEffectiveDate), lhsProductEffectiveDate, rhsProductEffectiveDate)) {
                return false;
            }
        }
        {
            Date lhsProductExpiryDate;
            lhsProductExpiryDate = this.getProductExpiryDate();
            Date rhsProductExpiryDate;
            rhsProductExpiryDate = that.getProductExpiryDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productExpiryDate", lhsProductExpiryDate), LocatorUtils.property(thatLocator, "productExpiryDate", rhsProductExpiryDate), lhsProductExpiryDate, rhsProductExpiryDate)) {
                return false;
            }
        }
        {
            String lhsProductClassificationCode;
            lhsProductClassificationCode = this.getProductClassificationCode();
            String rhsProductClassificationCode;
            rhsProductClassificationCode = that.getProductClassificationCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productClassificationCode", lhsProductClassificationCode), LocatorUtils.property(thatLocator, "productClassificationCode", rhsProductClassificationCode), lhsProductClassificationCode, rhsProductClassificationCode)) {
                return false;
            }
        }
        {
            String lhsTechnologyTypeCode;
            lhsTechnologyTypeCode = this.getTechnologyTypeCode();
            String rhsTechnologyTypeCode;
            rhsTechnologyTypeCode = that.getTechnologyTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "technologyTypeCode", lhsTechnologyTypeCode), LocatorUtils.property(thatLocator, "technologyTypeCode", rhsTechnologyTypeCode), lhsTechnologyTypeCode, rhsTechnologyTypeCode)) {
                return false;
            }
        }
        {
            List<ProductAliasType> lhsProductAliasList;
            lhsProductAliasList = (((this.productAliasList!= null)&&(!this.productAliasList.isEmpty()))?this.getProductAliasList():null);
            List<ProductAliasType> rhsProductAliasList;
            rhsProductAliasList = (((that.productAliasList!= null)&&(!that.productAliasList.isEmpty()))?that.getProductAliasList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productAliasList", lhsProductAliasList), LocatorUtils.property(thatLocator, "productAliasList", rhsProductAliasList), lhsProductAliasList, rhsProductAliasList)) {
                return false;
            }
        }
        {
            List<FinancingOptionsType> lhsFinancingOptionList;
            lhsFinancingOptionList = (((this.financingOptionList!= null)&&(!this.financingOptionList.isEmpty()))?this.getFinancingOptionList():null);
            List<FinancingOptionsType> rhsFinancingOptionList;
            rhsFinancingOptionList = (((that.financingOptionList!= null)&&(!that.financingOptionList.isEmpty()))?that.getFinancingOptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "financingOptionList", lhsFinancingOptionList), LocatorUtils.property(thatLocator, "financingOptionList", rhsFinancingOptionList), lhsFinancingOptionList, rhsFinancingOptionList)) {
                return false;
            }
        }
        {
            List<CompatibleSimType> lhsCompatibleSimList;
            lhsCompatibleSimList = (((this.compatibleSimList!= null)&&(!this.compatibleSimList.isEmpty()))?this.getCompatibleSimList():null);
            List<CompatibleSimType> rhsCompatibleSimList;
            rhsCompatibleSimList = (((that.compatibleSimList!= null)&&(!that.compatibleSimList.isEmpty()))?that.getCompatibleSimList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "compatibleSimList", lhsCompatibleSimList), LocatorUtils.property(thatLocator, "compatibleSimList", rhsCompatibleSimList), lhsCompatibleSimList, rhsCompatibleSimList)) {
                return false;
            }
        }
        {
            String lhsSapMaterialNum;
            lhsSapMaterialNum = this.getSapMaterialNum();
            String rhsSapMaterialNum;
            rhsSapMaterialNum = that.getSapMaterialNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sapMaterialNum", lhsSapMaterialNum), LocatorUtils.property(thatLocator, "sapMaterialNum", rhsSapMaterialNum), lhsSapMaterialNum, rhsSapMaterialNum)) {
                return false;
            }
        }
        {
            Boolean lhsDirectShipSkuIndicator;
            lhsDirectShipSkuIndicator = this.isDirectShipSkuIndicator();
            Boolean rhsDirectShipSkuIndicator;
            rhsDirectShipSkuIndicator = that.isDirectShipSkuIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "directShipSkuIndicator", lhsDirectShipSkuIndicator), LocatorUtils.property(thatLocator, "directShipSkuIndicator", rhsDirectShipSkuIndicator), lhsDirectShipSkuIndicator, rhsDirectShipSkuIndicator)) {
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
            Boolean lhsPairableDeviceInd;
            lhsPairableDeviceInd = this.isPairableDeviceInd();
            Boolean rhsPairableDeviceInd;
            rhsPairableDeviceInd = that.isPairableDeviceInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pairableDeviceInd", lhsPairableDeviceInd), LocatorUtils.property(thatLocator, "pairableDeviceInd", rhsPairableDeviceInd), lhsPairableDeviceInd, rhsPairableDeviceInd)) {
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
