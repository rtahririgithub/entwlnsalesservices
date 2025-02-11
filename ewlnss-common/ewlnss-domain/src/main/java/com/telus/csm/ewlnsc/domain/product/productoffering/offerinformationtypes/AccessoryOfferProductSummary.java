
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
 * Summarized version of the 'AccessoryProduct' type.
 * 
 * <p>Java class for AccessoryOfferProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOfferProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessoryOfferCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="eligibilityTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="stackableInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="recursiveInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="productCatalogSystemId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}systemId"/>
 *         &lt;element name="contractTermList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="incompatibleDiscountIdList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductCatalogueIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="contractEnforcedProductCdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOfferProductSummary", propOrder = {
    "accessoryOfferCode",
    "eligibilityTypeCd",
    "stackableInd",
    "recursiveInd",
    "productCatalogSystemId",
    "contractTermList",
    "incompatibleDiscountIdList",
    "contractEnforcedProductCdList"
})
@XmlSeeAlso({
    AccessoryOfferProduct.class
})
public class AccessoryOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String accessoryOfferCode;
    @XmlElement(required = true)
    protected String eligibilityTypeCd;
    protected boolean stackableInd;
    protected boolean recursiveInd;
    @XmlElement(required = true)
    protected String productCatalogSystemId;
    protected List<BigInteger> contractTermList;
    protected List<ProductCatalogueIdentifier> incompatibleDiscountIdList;
    protected List<String> contractEnforcedProductCdList;

    /**
     * Gets the value of the accessoryOfferCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccessoryOfferCode() {
        return accessoryOfferCode;
    }

    /**
     * Sets the value of the accessoryOfferCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccessoryOfferCode(String value) {
        this.accessoryOfferCode = value;
    }

    /**
     * Gets the value of the eligibilityTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibilityTypeCd() {
        return eligibilityTypeCd;
    }

    /**
     * Sets the value of the eligibilityTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibilityTypeCd(String value) {
        this.eligibilityTypeCd = value;
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
     * Gets the value of the recursiveInd property.
     * 
     */
    public boolean isRecursiveInd() {
        return recursiveInd;
    }

    /**
     * Sets the value of the recursiveInd property.
     * 
     */
    public void setRecursiveInd(boolean value) {
        this.recursiveInd = value;
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
     * Gets the value of the incompatibleDiscountIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the incompatibleDiscountIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getIncompatibleDiscountIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductCatalogueIdentifier }
     * 
     * 
     */
    public List<ProductCatalogueIdentifier> getIncompatibleDiscountIdList() {
        if (incompatibleDiscountIdList == null) {
            incompatibleDiscountIdList = new ArrayList<ProductCatalogueIdentifier>();
        }
        return this.incompatibleDiscountIdList;
    }

    /**
     * Gets the value of the contractEnforcedProductCdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractEnforcedProductCdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractEnforcedProductCdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getContractEnforcedProductCdList() {
        if (contractEnforcedProductCdList == null) {
            contractEnforcedProductCdList = new ArrayList<String>();
        }
        return this.contractEnforcedProductCdList;
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
     * Sets the value of the incompatibleDiscountIdList property.
     * 
     * @param incompatibleDiscountIdList
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setIncompatibleDiscountIdList(List<ProductCatalogueIdentifier> incompatibleDiscountIdList) {
        this.incompatibleDiscountIdList = incompatibleDiscountIdList;
    }

    /**
     * Sets the value of the contractEnforcedProductCdList property.
     * 
     * @param contractEnforcedProductCdList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractEnforcedProductCdList(List<String> contractEnforcedProductCdList) {
        this.contractEnforcedProductCdList = contractEnforcedProductCdList;
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
            String theAccessoryOfferCode;
            theAccessoryOfferCode = this.getAccessoryOfferCode();
            strategy.appendField(locator, this, "accessoryOfferCode", buffer, theAccessoryOfferCode);
        }
        {
            String theEligibilityTypeCd;
            theEligibilityTypeCd = this.getEligibilityTypeCd();
            strategy.appendField(locator, this, "eligibilityTypeCd", buffer, theEligibilityTypeCd);
        }
        {
            boolean theStackableInd;
            theStackableInd = (true?this.isStackableInd():false);
            strategy.appendField(locator, this, "stackableInd", buffer, theStackableInd);
        }
        {
            boolean theRecursiveInd;
            theRecursiveInd = (true?this.isRecursiveInd():false);
            strategy.appendField(locator, this, "recursiveInd", buffer, theRecursiveInd);
        }
        {
            String theProductCatalogSystemId;
            theProductCatalogSystemId = this.getProductCatalogSystemId();
            strategy.appendField(locator, this, "productCatalogSystemId", buffer, theProductCatalogSystemId);
        }
        {
            List<BigInteger> theContractTermList;
            theContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            strategy.appendField(locator, this, "contractTermList", buffer, theContractTermList);
        }
        {
            List<ProductCatalogueIdentifier> theIncompatibleDiscountIdList;
            theIncompatibleDiscountIdList = (((this.incompatibleDiscountIdList!= null)&&(!this.incompatibleDiscountIdList.isEmpty()))?this.getIncompatibleDiscountIdList():null);
            strategy.appendField(locator, this, "incompatibleDiscountIdList", buffer, theIncompatibleDiscountIdList);
        }
        {
            List<String> theContractEnforcedProductCdList;
            theContractEnforcedProductCdList = (((this.contractEnforcedProductCdList!= null)&&(!this.contractEnforcedProductCdList.isEmpty()))?this.getContractEnforcedProductCdList():null);
            strategy.appendField(locator, this, "contractEnforcedProductCdList", buffer, theContractEnforcedProductCdList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOfferProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOfferProductSummary that = ((AccessoryOfferProductSummary) object);
        {
            String lhsAccessoryOfferCode;
            lhsAccessoryOfferCode = this.getAccessoryOfferCode();
            String rhsAccessoryOfferCode;
            rhsAccessoryOfferCode = that.getAccessoryOfferCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accessoryOfferCode", lhsAccessoryOfferCode), LocatorUtils.property(thatLocator, "accessoryOfferCode", rhsAccessoryOfferCode), lhsAccessoryOfferCode, rhsAccessoryOfferCode)) {
                return false;
            }
        }
        {
            String lhsEligibilityTypeCd;
            lhsEligibilityTypeCd = this.getEligibilityTypeCd();
            String rhsEligibilityTypeCd;
            rhsEligibilityTypeCd = that.getEligibilityTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibilityTypeCd", lhsEligibilityTypeCd), LocatorUtils.property(thatLocator, "eligibilityTypeCd", rhsEligibilityTypeCd), lhsEligibilityTypeCd, rhsEligibilityTypeCd)) {
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
            boolean lhsRecursiveInd;
            lhsRecursiveInd = (true?this.isRecursiveInd():false);
            boolean rhsRecursiveInd;
            rhsRecursiveInd = (true?that.isRecursiveInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recursiveInd", lhsRecursiveInd), LocatorUtils.property(thatLocator, "recursiveInd", rhsRecursiveInd), lhsRecursiveInd, rhsRecursiveInd)) {
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
            List<BigInteger> lhsContractTermList;
            lhsContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            List<BigInteger> rhsContractTermList;
            rhsContractTermList = (((that.contractTermList!= null)&&(!that.contractTermList.isEmpty()))?that.getContractTermList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermList", lhsContractTermList), LocatorUtils.property(thatLocator, "contractTermList", rhsContractTermList), lhsContractTermList, rhsContractTermList)) {
                return false;
            }
        }
        {
            List<ProductCatalogueIdentifier> lhsIncompatibleDiscountIdList;
            lhsIncompatibleDiscountIdList = (((this.incompatibleDiscountIdList!= null)&&(!this.incompatibleDiscountIdList.isEmpty()))?this.getIncompatibleDiscountIdList():null);
            List<ProductCatalogueIdentifier> rhsIncompatibleDiscountIdList;
            rhsIncompatibleDiscountIdList = (((that.incompatibleDiscountIdList!= null)&&(!that.incompatibleDiscountIdList.isEmpty()))?that.getIncompatibleDiscountIdList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "incompatibleDiscountIdList", lhsIncompatibleDiscountIdList), LocatorUtils.property(thatLocator, "incompatibleDiscountIdList", rhsIncompatibleDiscountIdList), lhsIncompatibleDiscountIdList, rhsIncompatibleDiscountIdList)) {
                return false;
            }
        }
        {
            List<String> lhsContractEnforcedProductCdList;
            lhsContractEnforcedProductCdList = (((this.contractEnforcedProductCdList!= null)&&(!this.contractEnforcedProductCdList.isEmpty()))?this.getContractEnforcedProductCdList():null);
            List<String> rhsContractEnforcedProductCdList;
            rhsContractEnforcedProductCdList = (((that.contractEnforcedProductCdList!= null)&&(!that.contractEnforcedProductCdList.isEmpty()))?that.getContractEnforcedProductCdList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractEnforcedProductCdList", lhsContractEnforcedProductCdList), LocatorUtils.property(thatLocator, "contractEnforcedProductCdList", rhsContractEnforcedProductCdList), lhsContractEnforcedProductCdList, rhsContractEnforcedProductCdList)) {
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
