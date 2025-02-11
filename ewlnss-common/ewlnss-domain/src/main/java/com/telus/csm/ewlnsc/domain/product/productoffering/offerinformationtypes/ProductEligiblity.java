
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
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


/**
 * Defines a product eligibility criteria that can be associated with an offer
 * 
 * <p>Java class for ProductEligiblity complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductEligiblity">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productEnforcementGroupCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productTemplateIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" minOccurs="0"/>
 *         &lt;element name="productComponentIdentifierList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="transactionType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}TransactionType" minOccurs="0"/>
 *         &lt;element name="contractTermList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductEligiblity", propOrder = {
    "productEnforcementGroupCd",
    "productTypeCd",
    "productTemplateIdentifier",
    "productComponentIdentifierList",
    "transactionType",
    "contractTermList"
})
public class ProductEligiblity
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String productEnforcementGroupCd;
    @XmlElement(required = true)
    protected String productTypeCd;
    protected ProductCatalogueIdentifier productTemplateIdentifier;
    protected List<ProductCatalogueIdentifier> productComponentIdentifierList;
    protected TransactionType transactionType;
    protected List<BigInteger> contractTermList;

    /**
     * Gets the value of the productEnforcementGroupCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductEnforcementGroupCd() {
        return productEnforcementGroupCd;
    }

    /**
     * Sets the value of the productEnforcementGroupCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductEnforcementGroupCd(String value) {
        this.productEnforcementGroupCd = value;
    }

    /**
     * Gets the value of the productTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCd() {
        return productTypeCd;
    }

    /**
     * Sets the value of the productTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCd(String value) {
        this.productTypeCd = value;
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
     * Gets the value of the productComponentIdentifierList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productComponentIdentifierList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductComponentIdentifierList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCatalogueIdentifier }
     * 
     * 
     */
    public List<ProductCatalogueIdentifier> getProductComponentIdentifierList() {
        if (productComponentIdentifierList == null) {
            productComponentIdentifierList = new ArrayList<ProductCatalogueIdentifier>();
        }
        return this.productComponentIdentifierList;
    }

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionType }
     *     
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionType }
     *     
     */
    public void setTransactionType(TransactionType value) {
        this.transactionType = value;
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
     * Sets the value of the productComponentIdentifierList property.
     * 
     * @param productComponentIdentifierList
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setProductComponentIdentifierList(List<ProductCatalogueIdentifier> productComponentIdentifierList) {
        this.productComponentIdentifierList = productComponentIdentifierList;
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
            String theProductEnforcementGroupCd;
            theProductEnforcementGroupCd = this.getProductEnforcementGroupCd();
            strategy.appendField(locator, this, "productEnforcementGroupCd", buffer, theProductEnforcementGroupCd);
        }
        {
            String theProductTypeCd;
            theProductTypeCd = this.getProductTypeCd();
            strategy.appendField(locator, this, "productTypeCd", buffer, theProductTypeCd);
        }
        {
            ProductCatalogueIdentifier theProductTemplateIdentifier;
            theProductTemplateIdentifier = this.getProductTemplateIdentifier();
            strategy.appendField(locator, this, "productTemplateIdentifier", buffer, theProductTemplateIdentifier);
        }
        {
            List<ProductCatalogueIdentifier> theProductComponentIdentifierList;
            theProductComponentIdentifierList = (((this.productComponentIdentifierList!= null)&&(!this.productComponentIdentifierList.isEmpty()))?this.getProductComponentIdentifierList():null);
            strategy.appendField(locator, this, "productComponentIdentifierList", buffer, theProductComponentIdentifierList);
        }
        {
            TransactionType theTransactionType;
            theTransactionType = this.getTransactionType();
            strategy.appendField(locator, this, "transactionType", buffer, theTransactionType);
        }
        {
            List<BigInteger> theContractTermList;
            theContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            strategy.appendField(locator, this, "contractTermList", buffer, theContractTermList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductEligiblity)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductEligiblity that = ((ProductEligiblity) object);
        {
            String lhsProductEnforcementGroupCd;
            lhsProductEnforcementGroupCd = this.getProductEnforcementGroupCd();
            String rhsProductEnforcementGroupCd;
            rhsProductEnforcementGroupCd = that.getProductEnforcementGroupCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productEnforcementGroupCd", lhsProductEnforcementGroupCd), LocatorUtils.property(thatLocator, "productEnforcementGroupCd", rhsProductEnforcementGroupCd), lhsProductEnforcementGroupCd, rhsProductEnforcementGroupCd)) {
                return false;
            }
        }
        {
            String lhsProductTypeCd;
            lhsProductTypeCd = this.getProductTypeCd();
            String rhsProductTypeCd;
            rhsProductTypeCd = that.getProductTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeCd", lhsProductTypeCd), LocatorUtils.property(thatLocator, "productTypeCd", rhsProductTypeCd), lhsProductTypeCd, rhsProductTypeCd)) {
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
            List<ProductCatalogueIdentifier> lhsProductComponentIdentifierList;
            lhsProductComponentIdentifierList = (((this.productComponentIdentifierList!= null)&&(!this.productComponentIdentifierList.isEmpty()))?this.getProductComponentIdentifierList():null);
            List<ProductCatalogueIdentifier> rhsProductComponentIdentifierList;
            rhsProductComponentIdentifierList = (((that.productComponentIdentifierList!= null)&&(!that.productComponentIdentifierList.isEmpty()))?that.getProductComponentIdentifierList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentIdentifierList", lhsProductComponentIdentifierList), LocatorUtils.property(thatLocator, "productComponentIdentifierList", rhsProductComponentIdentifierList), lhsProductComponentIdentifierList, rhsProductComponentIdentifierList)) {
                return false;
            }
        }
        {
            TransactionType lhsTransactionType;
            lhsTransactionType = this.getTransactionType();
            TransactionType rhsTransactionType;
            rhsTransactionType = that.getTransactionType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionType", lhsTransactionType), LocatorUtils.property(thatLocator, "transactionType", rhsTransactionType), lhsTransactionType, rhsTransactionType)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
