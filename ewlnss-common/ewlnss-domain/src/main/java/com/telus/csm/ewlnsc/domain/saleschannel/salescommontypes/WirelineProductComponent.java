
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
 * <p>Java class for WirelineProductComponent complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineProductComponent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCatalogueIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductCatalogueIdentifier"/>
 *         &lt;element name="productComponentNameTxt" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList"/>
 *         &lt;element name="productComponentDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList"/>
 *         &lt;element name="productComponentFamilyCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productTierCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineProductComponent", propOrder = {
    "productCatalogueIdentifier",
    "productComponentNameTxt",
    "productComponentDescription",
    "productComponentFamilyCd",
    "productTierCode"
})
public class WirelineProductComponent
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ProductCatalogueIdentifier productCatalogueIdentifier;
    @XmlElement(required = true)
    protected MultilingualDescriptionList productComponentNameTxt;
    @XmlElement(required = true)
    protected MultilingualDescriptionList productComponentDescription;
    @XmlElement(required = true)
    protected String productComponentFamilyCd;
    @XmlElement(required = true)
    protected String productTierCode;

    /**
     * Gets the value of the productCatalogueIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public ProductCatalogueIdentifier getProductCatalogueIdentifier() {
        return productCatalogueIdentifier;
    }

    /**
     * Sets the value of the productCatalogueIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductCatalogueIdentifier }
     *     
     */
    public void setProductCatalogueIdentifier(ProductCatalogueIdentifier value) {
        this.productCatalogueIdentifier = value;
    }

    /**
     * Gets the value of the productComponentNameTxt property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getProductComponentNameTxt() {
        return productComponentNameTxt;
    }

    /**
     * Sets the value of the productComponentNameTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setProductComponentNameTxt(MultilingualDescriptionList value) {
        this.productComponentNameTxt = value;
    }

    /**
     * Gets the value of the productComponentDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getProductComponentDescription() {
        return productComponentDescription;
    }

    /**
     * Sets the value of the productComponentDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setProductComponentDescription(MultilingualDescriptionList value) {
        this.productComponentDescription = value;
    }

    /**
     * Gets the value of the productComponentFamilyCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductComponentFamilyCd() {
        return productComponentFamilyCd;
    }

    /**
     * Sets the value of the productComponentFamilyCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductComponentFamilyCd(String value) {
        this.productComponentFamilyCd = value;
    }

    /**
     * Gets the value of the productTierCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTierCode() {
        return productTierCode;
    }

    /**
     * Sets the value of the productTierCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTierCode(String value) {
        this.productTierCode = value;
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
            ProductCatalogueIdentifier theProductCatalogueIdentifier;
            theProductCatalogueIdentifier = this.getProductCatalogueIdentifier();
            strategy.appendField(locator, this, "productCatalogueIdentifier", buffer, theProductCatalogueIdentifier);
        }
        {
            MultilingualDescriptionList theProductComponentNameTxt;
            theProductComponentNameTxt = this.getProductComponentNameTxt();
            strategy.appendField(locator, this, "productComponentNameTxt", buffer, theProductComponentNameTxt);
        }
        {
            MultilingualDescriptionList theProductComponentDescription;
            theProductComponentDescription = this.getProductComponentDescription();
            strategy.appendField(locator, this, "productComponentDescription", buffer, theProductComponentDescription);
        }
        {
            String theProductComponentFamilyCd;
            theProductComponentFamilyCd = this.getProductComponentFamilyCd();
            strategy.appendField(locator, this, "productComponentFamilyCd", buffer, theProductComponentFamilyCd);
        }
        {
            String theProductTierCode;
            theProductTierCode = this.getProductTierCode();
            strategy.appendField(locator, this, "productTierCode", buffer, theProductTierCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineProductComponent)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineProductComponent that = ((WirelineProductComponent) object);
        {
            ProductCatalogueIdentifier lhsProductCatalogueIdentifier;
            lhsProductCatalogueIdentifier = this.getProductCatalogueIdentifier();
            ProductCatalogueIdentifier rhsProductCatalogueIdentifier;
            rhsProductCatalogueIdentifier = that.getProductCatalogueIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogueIdentifier", lhsProductCatalogueIdentifier), LocatorUtils.property(thatLocator, "productCatalogueIdentifier", rhsProductCatalogueIdentifier), lhsProductCatalogueIdentifier, rhsProductCatalogueIdentifier)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsProductComponentNameTxt;
            lhsProductComponentNameTxt = this.getProductComponentNameTxt();
            MultilingualDescriptionList rhsProductComponentNameTxt;
            rhsProductComponentNameTxt = that.getProductComponentNameTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentNameTxt", lhsProductComponentNameTxt), LocatorUtils.property(thatLocator, "productComponentNameTxt", rhsProductComponentNameTxt), lhsProductComponentNameTxt, rhsProductComponentNameTxt)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsProductComponentDescription;
            lhsProductComponentDescription = this.getProductComponentDescription();
            MultilingualDescriptionList rhsProductComponentDescription;
            rhsProductComponentDescription = that.getProductComponentDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentDescription", lhsProductComponentDescription), LocatorUtils.property(thatLocator, "productComponentDescription", rhsProductComponentDescription), lhsProductComponentDescription, rhsProductComponentDescription)) {
                return false;
            }
        }
        {
            String lhsProductComponentFamilyCd;
            lhsProductComponentFamilyCd = this.getProductComponentFamilyCd();
            String rhsProductComponentFamilyCd;
            rhsProductComponentFamilyCd = that.getProductComponentFamilyCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentFamilyCd", lhsProductComponentFamilyCd), LocatorUtils.property(thatLocator, "productComponentFamilyCd", rhsProductComponentFamilyCd), lhsProductComponentFamilyCd, rhsProductComponentFamilyCd)) {
                return false;
            }
        }
        {
            String lhsProductTierCode;
            lhsProductTierCode = this.getProductTierCode();
            String rhsProductTierCode;
            rhsProductTierCode = that.getProductTierCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTierCode", lhsProductTierCode), LocatorUtils.property(thatLocator, "productTierCode", rhsProductTierCode), lhsProductTierCode, rhsProductTierCode)) {
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
