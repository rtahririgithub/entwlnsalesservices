
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * Summarized version of the 'WirelineProduct' type.
 * 
 * <p>Java class for WirelineOfferProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineOfferProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productCategoryCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transactionTypeList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}TransactionType" maxOccurs="unbounded"/>
 *         &lt;element name="contractTermList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded"/>
 *         &lt;element name="productTemplateProductTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTemplateIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" minOccurs="0"/>
 *         &lt;element name="productComponentList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductComponent" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="productCatalogSystemId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}systemId"/>
 *         &lt;element name="mainComponentIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineOfferProductSummary", propOrder = {
    "productTypeCode",
    "productCategoryCode",
    "transactionTypeList",
    "contractTermList",
    "productTemplateProductTypeCode",
    "productTemplateIdentifier",
    "productComponentList",
    "productCatalogSystemId",
    "mainComponentIdentifier"
})
@XmlSeeAlso({
    WirelineOfferProduct.class
})
public class WirelineOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productTypeCode;
    protected String productCategoryCode;
    @XmlElement(required = true)
    protected List<TransactionType> transactionTypeList;
    @XmlElement(required = true)
    protected List<BigInteger> contractTermList;
    protected String productTemplateProductTypeCode;
    protected ProductCatalogueIdentifier productTemplateIdentifier;
    protected List<ProductComponent> productComponentList;
    @XmlElement(required = true)
    protected String productCatalogSystemId;
    protected ProductCatalogueIdentifier mainComponentIdentifier;

    /**
     * Gets the value of the productTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCode() {
        return productTypeCode;
    }

    /**
     * Sets the value of the productTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCode(String value) {
        this.productTypeCode = value;
    }

    /**
     * Gets the value of the productCategoryCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    /**
     * Sets the value of the productCategoryCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCategoryCode(String value) {
        this.productCategoryCode = value;
    }

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
     * Gets the value of the productTemplateProductTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTemplateProductTypeCode() {
        return productTemplateProductTypeCode;
    }

    /**
     * Sets the value of the productTemplateProductTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTemplateProductTypeCode(String value) {
        this.productTemplateProductTypeCode = value;
    }

    /**
     * Gets the value of the productTemplateIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getProductTemplateIdentifier() {
        return productTemplateIdentifier;
    }

    /**
     * Sets the value of the productTemplateIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setProductTemplateIdentifier(ProductCatalogueIdentifier value) {
        this.productTemplateIdentifier = value;
    }

    /**
     * Gets the value of the productComponentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productComponentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductComponentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductComponent }
     * 
     * 
     */
    public List<ProductComponent> getProductComponentList() {
        if (productComponentList == null) {
            productComponentList = new ArrayList<ProductComponent>();
        }
        return this.productComponentList;
    }

    /**
     * Gets the value of the productCatalogSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatalogSystemId() {
        return productCatalogSystemId;
    }

    /**
     * Sets the value of the productCatalogSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatalogSystemId(String value) {
        this.productCatalogSystemId = value;
    }

    /**
     * Gets the value of the mainComponentIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getMainComponentIdentifier() {
        return mainComponentIdentifier;
    }

    /**
     * Sets the value of the mainComponentIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setMainComponentIdentifier(ProductCatalogueIdentifier value) {
        this.mainComponentIdentifier = value;
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
     * Sets the value of the productComponentList property.
     * 
     * @param productComponentList
     *     allowed object is
     *     {@link ProductComponent }
     *     
     */
    public void setProductComponentList(List<ProductComponent> productComponentList) {
        this.productComponentList = productComponentList;
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
            String theProductTypeCode;
            theProductTypeCode = this.getProductTypeCode();
            strategy.appendField(locator, this, "productTypeCode", buffer, theProductTypeCode);
        }
        {
            String theProductCategoryCode;
            theProductCategoryCode = this.getProductCategoryCode();
            strategy.appendField(locator, this, "productCategoryCode", buffer, theProductCategoryCode);
        }
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
            String theProductTemplateProductTypeCode;
            theProductTemplateProductTypeCode = this.getProductTemplateProductTypeCode();
            strategy.appendField(locator, this, "productTemplateProductTypeCode", buffer, theProductTemplateProductTypeCode);
        }
        {
            ProductCatalogueIdentifier theProductTemplateIdentifier;
            theProductTemplateIdentifier = this.getProductTemplateIdentifier();
            strategy.appendField(locator, this, "productTemplateIdentifier", buffer, theProductTemplateIdentifier);
        }
        {
            List<ProductComponent> theProductComponentList;
            theProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            strategy.appendField(locator, this, "productComponentList", buffer, theProductComponentList);
        }
        {
            String theProductCatalogSystemId;
            theProductCatalogSystemId = this.getProductCatalogSystemId();
            strategy.appendField(locator, this, "productCatalogSystemId", buffer, theProductCatalogSystemId);
        }
        {
            ProductCatalogueIdentifier theMainComponentIdentifier;
            theMainComponentIdentifier = this.getMainComponentIdentifier();
            strategy.appendField(locator, this, "mainComponentIdentifier", buffer, theMainComponentIdentifier);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineOfferProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineOfferProductSummary that = ((WirelineOfferProductSummary) object);
        {
            String lhsProductTypeCode;
            lhsProductTypeCode = this.getProductTypeCode();
            String rhsProductTypeCode;
            rhsProductTypeCode = that.getProductTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeCode", lhsProductTypeCode), LocatorUtils.property(thatLocator, "productTypeCode", rhsProductTypeCode), lhsProductTypeCode, rhsProductTypeCode)) {
                return false;
            }
        }
        {
            String lhsProductCategoryCode;
            lhsProductCategoryCode = this.getProductCategoryCode();
            String rhsProductCategoryCode;
            rhsProductCategoryCode = that.getProductCategoryCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCategoryCode", lhsProductCategoryCode), LocatorUtils.property(thatLocator, "productCategoryCode", rhsProductCategoryCode), lhsProductCategoryCode, rhsProductCategoryCode)) {
                return false;
            }
        }
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
            String lhsProductTemplateProductTypeCode;
            lhsProductTemplateProductTypeCode = this.getProductTemplateProductTypeCode();
            String rhsProductTemplateProductTypeCode;
            rhsProductTemplateProductTypeCode = that.getProductTemplateProductTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTemplateProductTypeCode", lhsProductTemplateProductTypeCode), LocatorUtils.property(thatLocator, "productTemplateProductTypeCode", rhsProductTemplateProductTypeCode), lhsProductTemplateProductTypeCode, rhsProductTemplateProductTypeCode)) {
                return false;
            }
        }
        {
            ProductCatalogueIdentifier lhsProductTemplateIdentifier;
            lhsProductTemplateIdentifier = this.getProductTemplateIdentifier();
            ProductCatalogueIdentifier rhsProductTemplateIdentifier;
            rhsProductTemplateIdentifier = that.getProductTemplateIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTemplateIdentifier", lhsProductTemplateIdentifier), LocatorUtils.property(thatLocator, "productTemplateIdentifier", rhsProductTemplateIdentifier), lhsProductTemplateIdentifier, rhsProductTemplateIdentifier)) {
                return false;
            }
        }
        {
            List<ProductComponent> lhsProductComponentList;
            lhsProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            List<ProductComponent> rhsProductComponentList;
            rhsProductComponentList = (((that.productComponentList!= null)&&(!that.productComponentList.isEmpty()))?that.getProductComponentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentList", lhsProductComponentList), LocatorUtils.property(thatLocator, "productComponentList", rhsProductComponentList), lhsProductComponentList, rhsProductComponentList)) {
                return false;
            }
        }
        {
            String lhsProductCatalogSystemId;
            lhsProductCatalogSystemId = this.getProductCatalogSystemId();
            String rhsProductCatalogSystemId;
            rhsProductCatalogSystemId = that.getProductCatalogSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogSystemId", lhsProductCatalogSystemId), LocatorUtils.property(thatLocator, "productCatalogSystemId", rhsProductCatalogSystemId), lhsProductCatalogSystemId, rhsProductCatalogSystemId)) {
                return false;
            }
        }
        {
            ProductCatalogueIdentifier lhsMainComponentIdentifier;
            lhsMainComponentIdentifier = this.getMainComponentIdentifier();
            ProductCatalogueIdentifier rhsMainComponentIdentifier;
            rhsMainComponentIdentifier = that.getMainComponentIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mainComponentIdentifier", lhsMainComponentIdentifier), LocatorUtils.property(thatLocator, "mainComponentIdentifier", rhsMainComponentIdentifier), lhsMainComponentIdentifier, rhsMainComponentIdentifier)) {
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

