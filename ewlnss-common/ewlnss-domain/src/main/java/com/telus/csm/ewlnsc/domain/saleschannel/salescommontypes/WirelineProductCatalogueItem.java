
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelineProductCatalogueItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineProductCatalogueItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueItemIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductCatalogueIdentifier"/>
 *         &lt;element name="productCatalogueNameTxt" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description"/>
 *         &lt;element name="productCatalogueDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description"/>
 *         &lt;element name="catalogueItemTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}QuantityAllowed" minOccurs="0"/>
 *         &lt;element name="defaultInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="mandatoryInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="productCharacteristicList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="productCatalogueItemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="productCharacteristicName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="productCharacteristicCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="defaultValueTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="componentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductCatalogueItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineProductCatalogueItem", propOrder = {
    "productCatalogueItemIdentifier",
    "productCatalogueNameTxt",
    "productCatalogueDescription",
    "catalogueItemTypeCode",
    "serviceTypeCode",
    "quantity",
    "defaultInd",
    "mandatoryInd",
    "productCharacteristicList",
    "componentList"
})
public class WirelineProductCatalogueItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductCatalogueIdentifier productCatalogueItemIdentifier;
    @XmlElement(required = true)
    protected Description productCatalogueNameTxt;
    @XmlElement(required = true)
    protected Description productCatalogueDescription;
    @XmlElement(required = true)
    protected String catalogueItemTypeCode;
    @XmlElement(required = true)
    protected String serviceTypeCode;
    protected QuantityAllowed quantity;
    protected Boolean defaultInd;
    protected Boolean mandatoryInd;
    protected List<WirelineProductCatalogueItem.ProductCharacteristicList> productCharacteristicList;
    protected List<WirelineProductCatalogueItem> componentList;

    /**
     * Gets the value of the productCatalogueItemIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getProductCatalogueItemIdentifier() {
        return productCatalogueItemIdentifier;
    }

    /**
     * Sets the value of the productCatalogueItemIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setProductCatalogueItemIdentifier(ProductCatalogueIdentifier value) {
        this.productCatalogueItemIdentifier = value;
    }

    /**
     * Gets the value of the productCatalogueNameTxt property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getProductCatalogueNameTxt() {
        return productCatalogueNameTxt;
    }

    /**
     * Sets the value of the productCatalogueNameTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setProductCatalogueNameTxt(Description value) {
        this.productCatalogueNameTxt = value;
    }

    /**
     * Gets the value of the productCatalogueDescription property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getProductCatalogueDescription() {
        return productCatalogueDescription;
    }

    /**
     * Sets the value of the productCatalogueDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setProductCatalogueDescription(Description value) {
        this.productCatalogueDescription = value;
    }

    /**
     * Gets the value of the catalogueItemTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatalogueItemTypeCode() {
        return catalogueItemTypeCode;
    }

    /**
     * Sets the value of the catalogueItemTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatalogueItemTypeCode(String value) {
        this.catalogueItemTypeCode = value;
    }

    /**
     * Gets the value of the serviceTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    /**
     * Sets the value of the serviceTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCode(String value) {
        this.serviceTypeCode = value;
    }

    /**
     * Gets the value of the quantity property.
     * 
     * @return
     *     possible object is
     *     {@link QuantityAllowed }
     *     
     */
    public QuantityAllowed getQuantity() {
        return quantity;
    }

    /**
     * Sets the value of the quantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link QuantityAllowed }
     *     
     */
    public void setQuantity(QuantityAllowed value) {
        this.quantity = value;
    }

    /**
     * Gets the value of the defaultInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isDefaultInd() {
        return defaultInd;
    }

    /**
     * Sets the value of the defaultInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setDefaultInd(Boolean value) {
        this.defaultInd = value;
    }

    /**
     * Gets the value of the mandatoryInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMandatoryInd() {
        return mandatoryInd;
    }

    /**
     * Sets the value of the mandatoryInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMandatoryInd(Boolean value) {
        this.mandatoryInd = value;
    }

    /**
     * Gets the value of the productCharacteristicList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productCharacteristicList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductCharacteristicList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineProductCatalogueItem.ProductCharacteristicList }
     * 
     * 
     */
    public List<WirelineProductCatalogueItem.ProductCharacteristicList> getProductCharacteristicList() {
        if (productCharacteristicList == null) {
            productCharacteristicList = new ArrayList<WirelineProductCatalogueItem.ProductCharacteristicList>();
        }
        return this.productCharacteristicList;
    }

    /**
     * Gets the value of the componentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the componentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComponentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineProductCatalogueItem }
     * 
     * 
     */
    public List<WirelineProductCatalogueItem> getComponentList() {
        if (componentList == null) {
            componentList = new ArrayList<WirelineProductCatalogueItem>();
        }
        return this.componentList;
    }

    /**
     * Sets the value of the productCharacteristicList property.
     * 
     * @param productCharacteristicList
     *     allowed object is
     *     {@link WirelineProductCatalogueItem.ProductCharacteristicList }
     *     
     */
    public void setProductCharacteristicList(List<WirelineProductCatalogueItem.ProductCharacteristicList> productCharacteristicList) {
        this.productCharacteristicList = productCharacteristicList;
    }

    /**
     * Sets the value of the componentList property.
     * 
     * @param componentList
     *     allowed object is
     *     {@link WirelineProductCatalogueItem }
     *     
     */
    public void setComponentList(List<WirelineProductCatalogueItem> componentList) {
        this.componentList = componentList;
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
            ProductCatalogueIdentifier theProductCatalogueItemIdentifier;
            theProductCatalogueItemIdentifier = this.getProductCatalogueItemIdentifier();
            strategy.appendField(locator, this, "productCatalogueItemIdentifier", buffer, theProductCatalogueItemIdentifier);
        }
        {
            Description theProductCatalogueNameTxt;
            theProductCatalogueNameTxt = this.getProductCatalogueNameTxt();
            strategy.appendField(locator, this, "productCatalogueNameTxt", buffer, theProductCatalogueNameTxt);
        }
        {
            Description theProductCatalogueDescription;
            theProductCatalogueDescription = this.getProductCatalogueDescription();
            strategy.appendField(locator, this, "productCatalogueDescription", buffer, theProductCatalogueDescription);
        }
        {
            String theCatalogueItemTypeCode;
            theCatalogueItemTypeCode = this.getCatalogueItemTypeCode();
            strategy.appendField(locator, this, "catalogueItemTypeCode", buffer, theCatalogueItemTypeCode);
        }
        {
            String theServiceTypeCode;
            theServiceTypeCode = this.getServiceTypeCode();
            strategy.appendField(locator, this, "serviceTypeCode", buffer, theServiceTypeCode);
        }
        {
            QuantityAllowed theQuantity;
            theQuantity = this.getQuantity();
            strategy.appendField(locator, this, "quantity", buffer, theQuantity);
        }
        {
            Boolean theDefaultInd;
            theDefaultInd = this.isDefaultInd();
            strategy.appendField(locator, this, "defaultInd", buffer, theDefaultInd);
        }
        {
            Boolean theMandatoryInd;
            theMandatoryInd = this.isMandatoryInd();
            strategy.appendField(locator, this, "mandatoryInd", buffer, theMandatoryInd);
        }
        {
            List<WirelineProductCatalogueItem.ProductCharacteristicList> theProductCharacteristicList;
            theProductCharacteristicList = (((this.productCharacteristicList!= null)&&(!this.productCharacteristicList.isEmpty()))?this.getProductCharacteristicList():null);
            strategy.appendField(locator, this, "productCharacteristicList", buffer, theProductCharacteristicList);
        }
        {
            List<WirelineProductCatalogueItem> theComponentList;
            theComponentList = (((this.componentList!= null)&&(!this.componentList.isEmpty()))?this.getComponentList():null);
            strategy.appendField(locator, this, "componentList", buffer, theComponentList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineProductCatalogueItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineProductCatalogueItem that = ((WirelineProductCatalogueItem) object);
        {
            ProductCatalogueIdentifier lhsProductCatalogueItemIdentifier;
            lhsProductCatalogueItemIdentifier = this.getProductCatalogueItemIdentifier();
            ProductCatalogueIdentifier rhsProductCatalogueItemIdentifier;
            rhsProductCatalogueItemIdentifier = that.getProductCatalogueItemIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueItemIdentifier", lhsProductCatalogueItemIdentifier), LocatorUtils.property(thatLocator, "productCatalogueItemIdentifier", rhsProductCatalogueItemIdentifier), lhsProductCatalogueItemIdentifier, rhsProductCatalogueItemIdentifier)) {
                return false;
            }
        }
        {
            Description lhsProductCatalogueNameTxt;
            lhsProductCatalogueNameTxt = this.getProductCatalogueNameTxt();
            Description rhsProductCatalogueNameTxt;
            rhsProductCatalogueNameTxt = that.getProductCatalogueNameTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueNameTxt", lhsProductCatalogueNameTxt), LocatorUtils.property(thatLocator, "productCatalogueNameTxt", rhsProductCatalogueNameTxt), lhsProductCatalogueNameTxt, rhsProductCatalogueNameTxt)) {
                return false;
            }
        }
        {
            Description lhsProductCatalogueDescription;
            lhsProductCatalogueDescription = this.getProductCatalogueDescription();
            Description rhsProductCatalogueDescription;
            rhsProductCatalogueDescription = that.getProductCatalogueDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueDescription", lhsProductCatalogueDescription), LocatorUtils.property(thatLocator, "productCatalogueDescription", rhsProductCatalogueDescription), lhsProductCatalogueDescription, rhsProductCatalogueDescription)) {
                return false;
            }
        }
        {
            String lhsCatalogueItemTypeCode;
            lhsCatalogueItemTypeCode = this.getCatalogueItemTypeCode();
            String rhsCatalogueItemTypeCode;
            rhsCatalogueItemTypeCode = that.getCatalogueItemTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "catalogueItemTypeCode", lhsCatalogueItemTypeCode), LocatorUtils.property(thatLocator, "catalogueItemTypeCode", rhsCatalogueItemTypeCode), lhsCatalogueItemTypeCode, rhsCatalogueItemTypeCode)) {
                return false;
            }
        }
        {
            String lhsServiceTypeCode;
            lhsServiceTypeCode = this.getServiceTypeCode();
            String rhsServiceTypeCode;
            rhsServiceTypeCode = that.getServiceTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTypeCode", lhsServiceTypeCode), LocatorUtils.property(thatLocator, "serviceTypeCode", rhsServiceTypeCode), lhsServiceTypeCode, rhsServiceTypeCode)) {
                return false;
            }
        }
        {
            QuantityAllowed lhsQuantity;
            lhsQuantity = this.getQuantity();
            QuantityAllowed rhsQuantity;
            rhsQuantity = that.getQuantity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "quantity", lhsQuantity), LocatorUtils.property(thatLocator, "quantity", rhsQuantity), lhsQuantity, rhsQuantity)) {
                return false;
            }
        }
        {
            Boolean lhsDefaultInd;
            lhsDefaultInd = this.isDefaultInd();
            Boolean rhsDefaultInd;
            rhsDefaultInd = that.isDefaultInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultInd", lhsDefaultInd), LocatorUtils.property(thatLocator, "defaultInd", rhsDefaultInd), lhsDefaultInd, rhsDefaultInd)) {
                return false;
            }
        }
        {
            Boolean lhsMandatoryInd;
            lhsMandatoryInd = this.isMandatoryInd();
            Boolean rhsMandatoryInd;
            rhsMandatoryInd = that.isMandatoryInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mandatoryInd", lhsMandatoryInd), LocatorUtils.property(thatLocator, "mandatoryInd", rhsMandatoryInd), lhsMandatoryInd, rhsMandatoryInd)) {
                return false;
            }
        }
        {
            List<WirelineProductCatalogueItem.ProductCharacteristicList> lhsProductCharacteristicList;
            lhsProductCharacteristicList = (((this.productCharacteristicList!= null)&&(!this.productCharacteristicList.isEmpty()))?this.getProductCharacteristicList():null);
            List<WirelineProductCatalogueItem.ProductCharacteristicList> rhsProductCharacteristicList;
            rhsProductCharacteristicList = (((that.productCharacteristicList!= null)&&(!that.productCharacteristicList.isEmpty()))?that.getProductCharacteristicList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCharacteristicList", lhsProductCharacteristicList), LocatorUtils.property(thatLocator, "productCharacteristicList", rhsProductCharacteristicList), lhsProductCharacteristicList, rhsProductCharacteristicList)) {
                return false;
            }
        }
        {
            List<WirelineProductCatalogueItem> lhsComponentList;
            lhsComponentList = (((this.componentList!= null)&&(!this.componentList.isEmpty()))?this.getComponentList():null);
            List<WirelineProductCatalogueItem> rhsComponentList;
            rhsComponentList = (((that.componentList!= null)&&(!that.componentList.isEmpty()))?that.getComponentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "componentList", lhsComponentList), LocatorUtils.property(thatLocator, "componentList", rhsComponentList), lhsComponentList, rhsComponentList)) {
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
     *         &lt;element name="productCatalogueItemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="productCharacteristicName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="productCharacteristicCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="defaultValueTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "productCatalogueItemId",
        "productCharacteristicName",
        "productCharacteristicCode",
        "defaultValueTxt"
    })
    public static class ProductCharacteristicList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String productCatalogueItemId;
        protected String productCharacteristicName;
        protected String productCharacteristicCode;
        protected String defaultValueTxt;

        /**
         * Gets the value of the productCatalogueItemId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductCatalogueItemId() {
            return productCatalogueItemId;
        }

        /**
         * Sets the value of the productCatalogueItemId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductCatalogueItemId(String value) {
            this.productCatalogueItemId = value;
        }

        /**
         * Gets the value of the productCharacteristicName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductCharacteristicName() {
            return productCharacteristicName;
        }

        /**
         * Sets the value of the productCharacteristicName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductCharacteristicName(String value) {
            this.productCharacteristicName = value;
        }

        /**
         * Gets the value of the productCharacteristicCode property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductCharacteristicCode() {
            return productCharacteristicCode;
        }

        /**
         * Sets the value of the productCharacteristicCode property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductCharacteristicCode(String value) {
            this.productCharacteristicCode = value;
        }

        /**
         * Gets the value of the defaultValueTxt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getDefaultValueTxt() {
            return defaultValueTxt;
        }

        /**
         * Sets the value of the defaultValueTxt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setDefaultValueTxt(String value) {
            this.defaultValueTxt = value;
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
                String theProductCatalogueItemId;
                theProductCatalogueItemId = this.getProductCatalogueItemId();
                strategy.appendField(locator, this, "productCatalogueItemId", buffer, theProductCatalogueItemId);
            }
            {
                String theProductCharacteristicName;
                theProductCharacteristicName = this.getProductCharacteristicName();
                strategy.appendField(locator, this, "productCharacteristicName", buffer, theProductCharacteristicName);
            }
            {
                String theProductCharacteristicCode;
                theProductCharacteristicCode = this.getProductCharacteristicCode();
                strategy.appendField(locator, this, "productCharacteristicCode", buffer, theProductCharacteristicCode);
            }
            {
                String theDefaultValueTxt;
                theDefaultValueTxt = this.getDefaultValueTxt();
                strategy.appendField(locator, this, "defaultValueTxt", buffer, theDefaultValueTxt);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof WirelineProductCatalogueItem.ProductCharacteristicList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final WirelineProductCatalogueItem.ProductCharacteristicList that = ((WirelineProductCatalogueItem.ProductCharacteristicList) object);
            {
                String lhsProductCatalogueItemId;
                lhsProductCatalogueItemId = this.getProductCatalogueItemId();
                String rhsProductCatalogueItemId;
                rhsProductCatalogueItemId = that.getProductCatalogueItemId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueItemId", lhsProductCatalogueItemId), LocatorUtils.property(thatLocator, "productCatalogueItemId", rhsProductCatalogueItemId), lhsProductCatalogueItemId, rhsProductCatalogueItemId)) {
                    return false;
                }
            }
            {
                String lhsProductCharacteristicName;
                lhsProductCharacteristicName = this.getProductCharacteristicName();
                String rhsProductCharacteristicName;
                rhsProductCharacteristicName = that.getProductCharacteristicName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productCharacteristicName", lhsProductCharacteristicName), LocatorUtils.property(thatLocator, "productCharacteristicName", rhsProductCharacteristicName), lhsProductCharacteristicName, rhsProductCharacteristicName)) {
                    return false;
                }
            }
            {
                String lhsProductCharacteristicCode;
                lhsProductCharacteristicCode = this.getProductCharacteristicCode();
                String rhsProductCharacteristicCode;
                rhsProductCharacteristicCode = that.getProductCharacteristicCode();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "productCharacteristicCode", lhsProductCharacteristicCode), LocatorUtils.property(thatLocator, "productCharacteristicCode", rhsProductCharacteristicCode), lhsProductCharacteristicCode, rhsProductCharacteristicCode)) {
                    return false;
                }
            }
            {
                String lhsDefaultValueTxt;
                lhsDefaultValueTxt = this.getDefaultValueTxt();
                String rhsDefaultValueTxt;
                rhsDefaultValueTxt = that.getDefaultValueTxt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "defaultValueTxt", lhsDefaultValueTxt), LocatorUtils.property(thatLocator, "defaultValueTxt", rhsDefaultValueTxt), lhsDefaultValueTxt, rhsDefaultValueTxt)) {
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
