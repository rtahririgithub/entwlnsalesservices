
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.telus.csm.ewlnsc.domain.IPromotion;
import com.telus.csm.ewlnsc.domain.PromoCodeRefVO;
import com.telus.csm.ewlnsc.domain.PromotionGroupVO;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;


/**
 * <p>Java class for Discount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Discount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionTypeList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}TransactionType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractTermList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productCatalogueItemList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueItem" maxOccurs="unbounded"/>
 *         &lt;element name="includedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="stackableInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="winbackInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="recontractingInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="crossProductDependencyList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}CrossProductDependency" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="marketingDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="discountCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountProductCatalogueItemList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountProductCatalogueItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="marketingNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="discountedComponentTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="preSelectedDiscountInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Discount", propOrder = {
    "transactionTypeList",
    "contractTermList",
    "productCatalogueItemList",
    "includedInd",
    "stackableInd",
    "winbackInd",
    "recontractingInd",
    "crossProductDependencyList",
    "marketingDescriptionList",
    "discountCode",
    "discountProductCatalogueItemList",
    "marketingNameList",
    "discountedComponentTypeCd",
    "preSelectedDiscountInd"
})
public class Discount implements Serializable, Equals, ToString, IPromotion
{

    private final static long serialVersionUID = 2L;
    protected List<TransactionType> transactionTypeList;
    protected List<BigInteger> contractTermList;
    @XmlElement(required = true)
    protected List<ProductCatalogueItem> productCatalogueItemList;
    protected boolean includedInd;
    protected boolean stackableInd;
    protected boolean winbackInd;
    protected Boolean recontractingInd;
    protected List<CrossProductDependency> crossProductDependencyList;
    protected List<Description> marketingDescriptionList;
    protected String discountCode;
    protected List<DiscountProductCatalogueItem> discountProductCatalogueItemList;
    protected List<Description> marketingNameList;
    protected String discountedComponentTypeCd;
    protected Boolean preSelectedDiscountInd;
	private String promotionId;
	private Date promotionPerspectiveDate;
	private PromotionGroupVO promotionGroup;
	private PromoCodeRefVO promoCodeRef;
	private boolean installCredit;  // Gary 2019-07-30 WEEKDAY Installation Credit
    private boolean manualSweetener;
	private String productCriteriaValue;

    /**
     * Gets the value of the transactionTypeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the transactionTypeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTransactionTypeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TransactionType }
     * 
     * 
     */
    public List<TransactionType> getTransactionTypeList() {
        if (transactionTypeList == null) {
            transactionTypeList = new ArrayList<TransactionType>();
        }
        return this.transactionTypeList;
    }

    /**
     * Gets the value of the contractTermList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractTermList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractTermList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getContractTermList() {
        if (contractTermList == null) {
            contractTermList = new ArrayList<BigInteger>();
        }
        return this.contractTermList;
    }

    /**
     * Gets the value of the productCatalogueItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCatalogueItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCatalogueItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCatalogueItem }
     * 
     * 
     */
    public List<ProductCatalogueItem> getProductCatalogueItemList() {
        if (productCatalogueItemList == null) {
            productCatalogueItemList = new ArrayList<ProductCatalogueItem>();
        }
        return this.productCatalogueItemList;
    }

    /**
     * Gets the value of the includedInd property.
     * 
     */
    public boolean isIncludedInd() {
        return includedInd;
    }

    /**
     * Sets the value of the includedInd property.
     * 
     */
    public void setIncludedInd(boolean value) {
        this.includedInd = value;
    }

    /**
     * Gets the value of the stackableInd property.
     * 
     */
    public boolean isStackableInd() {
        return stackableInd;
    }

    /**
     * Sets the value of the stackableInd property.
     * 
     */
    public void setStackableInd(boolean value) {
        this.stackableInd = value;
    }

    /**
     * Gets the value of the winbackInd property.
     * 
     */
    public boolean isWinbackInd() {
        return winbackInd;
    }

    /**
     * Sets the value of the winbackInd property.
     * 
     */
    public void setWinbackInd(boolean value) {
        this.winbackInd = value;
    }

    /**
     * Gets the value of the recontractingInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecontractingInd() {
        return recontractingInd;
    }

    /**
     * Sets the value of the recontractingInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecontractingInd(Boolean value) {
        this.recontractingInd = value;
    }

    /**
     * Gets the value of the crossProductDependencyList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the crossProductDependencyList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCrossProductDependencyList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CrossProductDependency }
     * 
     * 
     */
    public List<CrossProductDependency> getCrossProductDependencyList() {
        if (crossProductDependencyList == null) {
            crossProductDependencyList = new ArrayList<CrossProductDependency>();
        }
        return this.crossProductDependencyList;
    }

    /**
     * Gets the value of the marketingDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketingDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketingDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getMarketingDescriptionList() {
        if (marketingDescriptionList == null) {
            marketingDescriptionList = new ArrayList<Description>();
        }
        return this.marketingDescriptionList;
    }

    /**
     * Gets the value of the discountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the value of the discountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountCode(String value) {
        this.discountCode = value;
    }

    /**
     * Gets the value of the discountProductCatalogueItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the discountProductCatalogueItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscountProductCatalogueItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DiscountProductCatalogueItem }
     * 
     * 
     */
    public List<DiscountProductCatalogueItem> getDiscountProductCatalogueItemList() {
        if (discountProductCatalogueItemList == null) {
            discountProductCatalogueItemList = new ArrayList<DiscountProductCatalogueItem>();
        }
        return this.discountProductCatalogueItemList;
    }

    /**
     * Gets the value of the marketingNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the marketingNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMarketingNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getMarketingNameList() {
        if (marketingNameList == null) {
            marketingNameList = new ArrayList<Description>();
        }
        return this.marketingNameList;
    }

    /**
     * Gets the value of the discountedComponentTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountedComponentTypeCd() {
        return discountedComponentTypeCd;
    }

    /**
     * Sets the value of the discountedComponentTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountedComponentTypeCd(String value) {
        this.discountedComponentTypeCd = value;
    }

    /**
     * Gets the value of the preSelectedDiscountInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPreSelectedDiscountInd() {
        return preSelectedDiscountInd;
    }

    /**
     * Sets the value of the preSelectedDiscountInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPreSelectedDiscountInd(Boolean value) {
        this.preSelectedDiscountInd = value;
    }

    /**
     * Sets the value of the transactionTypeList property.
     * 
     * @param transactionTypeList
     *     allowed object is
     *     {@link TransactionType }
     *     
     */
    public void setTransactionTypeList(List<TransactionType> transactionTypeList) {
        this.transactionTypeList = transactionTypeList;
    }

    /**
     * Sets the value of the contractTermList property.
     * 
     * @param contractTermList
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTermList(List<BigInteger> contractTermList) {
        this.contractTermList = contractTermList;
    }

    /**
     * Sets the value of the productCatalogueItemList property.
     * 
     * @param productCatalogueItemList
     *     allowed object is
     *     {@link ProductCatalogueItem }
     *     
     */
    public void setProductCatalogueItemList(List<ProductCatalogueItem> productCatalogueItemList) {
        this.productCatalogueItemList = productCatalogueItemList;
    }

    /**
     * Sets the value of the crossProductDependencyList property.
     * 
     * @param crossProductDependencyList
     *     allowed object is
     *     {@link CrossProductDependency }
     *     
     */
    public void setCrossProductDependencyList(List<CrossProductDependency> crossProductDependencyList) {
        this.crossProductDependencyList = crossProductDependencyList;
    }

    /**
     * Sets the value of the marketingDescriptionList property.
     * 
     * @param marketingDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setMarketingDescriptionList(List<Description> marketingDescriptionList) {
        this.marketingDescriptionList = marketingDescriptionList;
    }

    /**
     * Sets the value of the discountProductCatalogueItemList property.
     * 
     * @param discountProductCatalogueItemList
     *     allowed object is
     *     {@link DiscountProductCatalogueItem }
     *     
     */
    public void setDiscountProductCatalogueItemList(List<DiscountProductCatalogueItem> discountProductCatalogueItemList) {
        this.discountProductCatalogueItemList = discountProductCatalogueItemList;
    }

    /**
     * Sets the value of the marketingNameList property.
     * 
     * @param marketingNameList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setMarketingNameList(List<Description> marketingNameList) {
        this.marketingNameList = marketingNameList;
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
            List<TransactionType> theTransactionTypeList;
            theTransactionTypeList = (((this.transactionTypeList!= null)&&(!this.transactionTypeList.isEmpty()))?this.getTransactionTypeList():null);
            strategy.appendField(locator, this, "transactionTypeList", buffer, theTransactionTypeList);
        }
        {
            List<BigInteger> theContractTermList;
            theContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            strategy.appendField(locator, this, "contractTermList", buffer, theContractTermList);
        }
        {
            List<ProductCatalogueItem> theProductCatalogueItemList;
            theProductCatalogueItemList = (((this.productCatalogueItemList!= null)&&(!this.productCatalogueItemList.isEmpty()))?this.getProductCatalogueItemList():null);
            strategy.appendField(locator, this, "productCatalogueItemList", buffer, theProductCatalogueItemList);
        }
        {
            boolean theIncludedInd;
            theIncludedInd = (true?this.isIncludedInd():false);
            strategy.appendField(locator, this, "includedInd", buffer, theIncludedInd);
        }
        {
            boolean theStackableInd;
            theStackableInd = (true?this.isStackableInd():false);
            strategy.appendField(locator, this, "stackableInd", buffer, theStackableInd);
        }
        {
            boolean theWinbackInd;
            theWinbackInd = (true?this.isWinbackInd():false);
            strategy.appendField(locator, this, "winbackInd", buffer, theWinbackInd);
        }
        {
            Boolean theRecontractingInd;
            theRecontractingInd = this.isRecontractingInd();
            strategy.appendField(locator, this, "recontractingInd", buffer, theRecontractingInd);
        }
        {
            List<CrossProductDependency> theCrossProductDependencyList;
            theCrossProductDependencyList = (((this.crossProductDependencyList!= null)&&(!this.crossProductDependencyList.isEmpty()))?this.getCrossProductDependencyList():null);
            strategy.appendField(locator, this, "crossProductDependencyList", buffer, theCrossProductDependencyList);
        }
        {
            List<Description> theMarketingDescriptionList;
            theMarketingDescriptionList = (((this.marketingDescriptionList!= null)&&(!this.marketingDescriptionList.isEmpty()))?this.getMarketingDescriptionList():null);
            strategy.appendField(locator, this, "marketingDescriptionList", buffer, theMarketingDescriptionList);
        }
        {
            String theDiscountCode;
            theDiscountCode = this.getDiscountCode();
            strategy.appendField(locator, this, "discountCode", buffer, theDiscountCode);
        }
        {
            List<DiscountProductCatalogueItem> theDiscountProductCatalogueItemList;
            theDiscountProductCatalogueItemList = (((this.discountProductCatalogueItemList!= null)&&(!this.discountProductCatalogueItemList.isEmpty()))?this.getDiscountProductCatalogueItemList():null);
            strategy.appendField(locator, this, "discountProductCatalogueItemList", buffer, theDiscountProductCatalogueItemList);
        }
        {
            List<Description> theMarketingNameList;
            theMarketingNameList = (((this.marketingNameList!= null)&&(!this.marketingNameList.isEmpty()))?this.getMarketingNameList():null);
            strategy.appendField(locator, this, "marketingNameList", buffer, theMarketingNameList);
        }
        {
            String theDiscountedComponentTypeCd;
            theDiscountedComponentTypeCd = this.getDiscountedComponentTypeCd();
            strategy.appendField(locator, this, "discountedComponentTypeCd", buffer, theDiscountedComponentTypeCd);
        }
        {
            Boolean thePreSelectedDiscountInd;
            thePreSelectedDiscountInd = this.isPreSelectedDiscountInd();
            strategy.appendField(locator, this, "preSelectedDiscountInd", buffer, thePreSelectedDiscountInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Discount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Discount that = ((Discount) object);
        {
            List<TransactionType> lhsTransactionTypeList;
            lhsTransactionTypeList = (((this.transactionTypeList!= null)&&(!this.transactionTypeList.isEmpty()))?this.getTransactionTypeList():null);
            List<TransactionType> rhsTransactionTypeList;
            rhsTransactionTypeList = (((that.transactionTypeList!= null)&&(!that.transactionTypeList.isEmpty()))?that.getTransactionTypeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionTypeList", lhsTransactionTypeList), LocatorUtils.property(thatLocator, "transactionTypeList", rhsTransactionTypeList), lhsTransactionTypeList, rhsTransactionTypeList)) {
                return false;
            }
        }
        {
            List<BigInteger> lhsContractTermList;
            lhsContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            List<BigInteger> rhsContractTermList;
            rhsContractTermList = (((that.contractTermList!= null)&&(!that.contractTermList.isEmpty()))?that.getContractTermList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermList", lhsContractTermList), LocatorUtils.property(thatLocator, "contractTermList", rhsContractTermList), lhsContractTermList, rhsContractTermList)) {
                return false;
            }
        }
        {
            List<ProductCatalogueItem> lhsProductCatalogueItemList;
            lhsProductCatalogueItemList = (((this.productCatalogueItemList!= null)&&(!this.productCatalogueItemList.isEmpty()))?this.getProductCatalogueItemList():null);
            List<ProductCatalogueItem> rhsProductCatalogueItemList;
            rhsProductCatalogueItemList = (((that.productCatalogueItemList!= null)&&(!that.productCatalogueItemList.isEmpty()))?that.getProductCatalogueItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueItemList", lhsProductCatalogueItemList), LocatorUtils.property(thatLocator, "productCatalogueItemList", rhsProductCatalogueItemList), lhsProductCatalogueItemList, rhsProductCatalogueItemList)) {
                return false;
            }
        }
        {
            boolean lhsIncludedInd;
            lhsIncludedInd = (true?this.isIncludedInd():false);
            boolean rhsIncludedInd;
            rhsIncludedInd = (true?that.isIncludedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedInd", lhsIncludedInd), LocatorUtils.property(thatLocator, "includedInd", rhsIncludedInd), lhsIncludedInd, rhsIncludedInd)) {
                return false;
            }
        }
        {
            boolean lhsStackableInd;
            lhsStackableInd = (true?this.isStackableInd():false);
            boolean rhsStackableInd;
            rhsStackableInd = (true?that.isStackableInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stackableInd", lhsStackableInd), LocatorUtils.property(thatLocator, "stackableInd", rhsStackableInd), lhsStackableInd, rhsStackableInd)) {
                return false;
            }
        }
        {
            boolean lhsWinbackInd;
            lhsWinbackInd = (true?this.isWinbackInd():false);
            boolean rhsWinbackInd;
            rhsWinbackInd = (true?that.isWinbackInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "winbackInd", lhsWinbackInd), LocatorUtils.property(thatLocator, "winbackInd", rhsWinbackInd), lhsWinbackInd, rhsWinbackInd)) {
                return false;
            }
        }
        {
            Boolean lhsRecontractingInd;
            lhsRecontractingInd = this.isRecontractingInd();
            Boolean rhsRecontractingInd;
            rhsRecontractingInd = that.isRecontractingInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recontractingInd", lhsRecontractingInd), LocatorUtils.property(thatLocator, "recontractingInd", rhsRecontractingInd), lhsRecontractingInd, rhsRecontractingInd)) {
                return false;
            }
        }
        {
            List<CrossProductDependency> lhsCrossProductDependencyList;
            lhsCrossProductDependencyList = (((this.crossProductDependencyList!= null)&&(!this.crossProductDependencyList.isEmpty()))?this.getCrossProductDependencyList():null);
            List<CrossProductDependency> rhsCrossProductDependencyList;
            rhsCrossProductDependencyList = (((that.crossProductDependencyList!= null)&&(!that.crossProductDependencyList.isEmpty()))?that.getCrossProductDependencyList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "crossProductDependencyList", lhsCrossProductDependencyList), LocatorUtils.property(thatLocator, "crossProductDependencyList", rhsCrossProductDependencyList), lhsCrossProductDependencyList, rhsCrossProductDependencyList)) {
                return false;
            }
        }
        {
            List<Description> lhsMarketingDescriptionList;
            lhsMarketingDescriptionList = (((this.marketingDescriptionList!= null)&&(!this.marketingDescriptionList.isEmpty()))?this.getMarketingDescriptionList():null);
            List<Description> rhsMarketingDescriptionList;
            rhsMarketingDescriptionList = (((that.marketingDescriptionList!= null)&&(!that.marketingDescriptionList.isEmpty()))?that.getMarketingDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "marketingDescriptionList", lhsMarketingDescriptionList), LocatorUtils.property(thatLocator, "marketingDescriptionList", rhsMarketingDescriptionList), lhsMarketingDescriptionList, rhsMarketingDescriptionList)) {
                return false;
            }
        }
        {
            String lhsDiscountCode;
            lhsDiscountCode = this.getDiscountCode();
            String rhsDiscountCode;
            rhsDiscountCode = that.getDiscountCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountCode", lhsDiscountCode), LocatorUtils.property(thatLocator, "discountCode", rhsDiscountCode), lhsDiscountCode, rhsDiscountCode)) {
                return false;
            }
        }
        {
            List<DiscountProductCatalogueItem> lhsDiscountProductCatalogueItemList;
            lhsDiscountProductCatalogueItemList = (((this.discountProductCatalogueItemList!= null)&&(!this.discountProductCatalogueItemList.isEmpty()))?this.getDiscountProductCatalogueItemList():null);
            List<DiscountProductCatalogueItem> rhsDiscountProductCatalogueItemList;
            rhsDiscountProductCatalogueItemList = (((that.discountProductCatalogueItemList!= null)&&(!that.discountProductCatalogueItemList.isEmpty()))?that.getDiscountProductCatalogueItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountProductCatalogueItemList", lhsDiscountProductCatalogueItemList), LocatorUtils.property(thatLocator, "discountProductCatalogueItemList", rhsDiscountProductCatalogueItemList), lhsDiscountProductCatalogueItemList, rhsDiscountProductCatalogueItemList)) {
                return false;
            }
        }
        {
            List<Description> lhsMarketingNameList;
            lhsMarketingNameList = (((this.marketingNameList!= null)&&(!this.marketingNameList.isEmpty()))?this.getMarketingNameList():null);
            List<Description> rhsMarketingNameList;
            rhsMarketingNameList = (((that.marketingNameList!= null)&&(!that.marketingNameList.isEmpty()))?that.getMarketingNameList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "marketingNameList", lhsMarketingNameList), LocatorUtils.property(thatLocator, "marketingNameList", rhsMarketingNameList), lhsMarketingNameList, rhsMarketingNameList)) {
                return false;
            }
        }
        {
            String lhsDiscountedComponentTypeCd;
            lhsDiscountedComponentTypeCd = this.getDiscountedComponentTypeCd();
            String rhsDiscountedComponentTypeCd;
            rhsDiscountedComponentTypeCd = that.getDiscountedComponentTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountedComponentTypeCd", lhsDiscountedComponentTypeCd), LocatorUtils.property(thatLocator, "discountedComponentTypeCd", rhsDiscountedComponentTypeCd), lhsDiscountedComponentTypeCd, rhsDiscountedComponentTypeCd)) {
                return false;
            }
        }
        {
            Boolean lhsPreSelectedDiscountInd;
            lhsPreSelectedDiscountInd = this.isPreSelectedDiscountInd();
            Boolean rhsPreSelectedDiscountInd;
            rhsPreSelectedDiscountInd = that.isPreSelectedDiscountInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "preSelectedDiscountInd", lhsPreSelectedDiscountInd), LocatorUtils.property(thatLocator, "preSelectedDiscountInd", rhsPreSelectedDiscountInd), lhsPreSelectedDiscountInd, rhsPreSelectedDiscountInd)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

	public String getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(String promotionId) {
		this.promotionId = promotionId;
	}

	public Date getPromotionPerspectiveDate() {
		return promotionPerspectiveDate;
	}

	public void setPromotionPerspectiveDate(Date promotionPerspectiveDate) {
		this.promotionPerspectiveDate = promotionPerspectiveDate;
	}

	public PromotionGroupVO getPromotionGroup() {
		return promotionGroup;
	}

	public void setPromotionGroup(PromotionGroupVO promotionGroup) {
		this.promotionGroup = promotionGroup;
	}

	public PromoCodeRefVO getPromoCodeRef() {
		return promoCodeRef;
	}

	public void setPromoCodeRef(PromoCodeRefVO promoCodeRef) {
		this.promoCodeRef = promoCodeRef;
	}
	
	
	// Gary 2019-07-30 WEEKDAY Installation Credit
	public boolean hasInstallCredit(){
		return this.installCredit;
	}
	
	public void setInstallCredit(boolean installCredit){
		this.installCredit = installCredit;
	}

	public boolean isManualSweetener() {
		return manualSweetener;
	}

	public void setManualSweetener(boolean manualSweetener) {
		this.manualSweetener = manualSweetener;
	}

	public String getProductCriteriaValue() {
		return productCriteriaValue;
	}

	public void setProductCriteriaValue(String productCriteriaValue) {
		this.productCriteriaValue = productCriteriaValue;
	}
}
