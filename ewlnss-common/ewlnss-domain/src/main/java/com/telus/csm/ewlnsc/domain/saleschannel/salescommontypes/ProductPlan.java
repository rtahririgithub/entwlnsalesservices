
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for ProductPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProductPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productTemplateId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="mainComponent" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier"/>
 *         &lt;element name="productPlanIdentifier" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier"/>
 *         &lt;element name="discountPlanInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="themePackInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="highSpeedPackInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProductPlan", propOrder = {
    "productTemplateId",
    "mainComponent",
    "productPlanIdentifier",
    "discountPlanInd",
    "themePackInd",
    "highSpeedPackInd"
})
public class ProductPlan
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productTemplateId;
    @XmlElement(required = true)
    protected ProductComponentIdentifier mainComponent;
    @XmlElement(required = true)
    protected ProductComponentIdentifier productPlanIdentifier;
    protected boolean discountPlanInd;
    protected boolean themePackInd;
    protected boolean highSpeedPackInd;

    /**
     * Gets the value of the productTemplateId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTemplateId() {
        return productTemplateId;
    }

    /**
     * Sets the value of the productTemplateId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTemplateId(String value) {
        this.productTemplateId = value;
    }

    /**
     * Gets the value of the mainComponent property.
     * 
     * @return
     *     possible object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public ProductComponentIdentifier getMainComponent() {
        return mainComponent;
    }

    /**
     * Sets the value of the mainComponent property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setMainComponent(ProductComponentIdentifier value) {
        this.mainComponent = value;
    }

    /**
     * Gets the value of the productPlanIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public ProductComponentIdentifier getProductPlanIdentifier() {
        return productPlanIdentifier;
    }

    /**
     * Sets the value of the productPlanIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setProductPlanIdentifier(ProductComponentIdentifier value) {
        this.productPlanIdentifier = value;
    }

    /**
     * Gets the value of the discountPlanInd property.
     * 
     */
    public boolean isDiscountPlanInd() {
        return discountPlanInd;
    }

    /**
     * Sets the value of the discountPlanInd property.
     * 
     */
    public void setDiscountPlanInd(boolean value) {
        this.discountPlanInd = value;
    }

    /**
     * Gets the value of the themePackInd property.
     * 
     */
    public boolean isThemePackInd() {
        return themePackInd;
    }

    /**
     * Sets the value of the themePackInd property.
     * 
     */
    public void setThemePackInd(boolean value) {
        this.themePackInd = value;
    }

    /**
     * Gets the value of the highSpeedPackInd property.
     * 
     */
    public boolean isHighSpeedPackInd() {
        return highSpeedPackInd;
    }

    /**
     * Sets the value of the highSpeedPackInd property.
     * 
     */
    public void setHighSpeedPackInd(boolean value) {
        this.highSpeedPackInd = value;
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
            String theProductTemplateId;
            theProductTemplateId = this.getProductTemplateId();
            strategy.appendField(locator, this, "productTemplateId", buffer, theProductTemplateId);
        }
        {
            ProductComponentIdentifier theMainComponent;
            theMainComponent = this.getMainComponent();
            strategy.appendField(locator, this, "mainComponent", buffer, theMainComponent);
        }
        {
            ProductComponentIdentifier theProductPlanIdentifier;
            theProductPlanIdentifier = this.getProductPlanIdentifier();
            strategy.appendField(locator, this, "productPlanIdentifier", buffer, theProductPlanIdentifier);
        }
        {
            boolean theDiscountPlanInd;
            theDiscountPlanInd = (true?this.isDiscountPlanInd():false);
            strategy.appendField(locator, this, "discountPlanInd", buffer, theDiscountPlanInd);
        }
        {
            boolean theThemePackInd;
            theThemePackInd = (true?this.isThemePackInd():false);
            strategy.appendField(locator, this, "themePackInd", buffer, theThemePackInd);
        }
        {
            boolean theHighSpeedPackInd;
            theHighSpeedPackInd = (true?this.isHighSpeedPackInd():false);
            strategy.appendField(locator, this, "highSpeedPackInd", buffer, theHighSpeedPackInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProductPlan)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProductPlan that = ((ProductPlan) object);
        {
            String lhsProductTemplateId;
            lhsProductTemplateId = this.getProductTemplateId();
            String rhsProductTemplateId;
            rhsProductTemplateId = that.getProductTemplateId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTemplateId", lhsProductTemplateId), LocatorUtils.property(thatLocator, "productTemplateId", rhsProductTemplateId), lhsProductTemplateId, rhsProductTemplateId)) {
                return false;
            }
        }
        {
            ProductComponentIdentifier lhsMainComponent;
            lhsMainComponent = this.getMainComponent();
            ProductComponentIdentifier rhsMainComponent;
            rhsMainComponent = that.getMainComponent();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mainComponent", lhsMainComponent), LocatorUtils.property(thatLocator, "mainComponent", rhsMainComponent), lhsMainComponent, rhsMainComponent)) {
                return false;
            }
        }
        {
            ProductComponentIdentifier lhsProductPlanIdentifier;
            lhsProductPlanIdentifier = this.getProductPlanIdentifier();
            ProductComponentIdentifier rhsProductPlanIdentifier;
            rhsProductPlanIdentifier = that.getProductPlanIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productPlanIdentifier", lhsProductPlanIdentifier), LocatorUtils.property(thatLocator, "productPlanIdentifier", rhsProductPlanIdentifier), lhsProductPlanIdentifier, rhsProductPlanIdentifier)) {
                return false;
            }
        }
        {
            boolean lhsDiscountPlanInd;
            lhsDiscountPlanInd = (true?this.isDiscountPlanInd():false);
            boolean rhsDiscountPlanInd;
            rhsDiscountPlanInd = (true?that.isDiscountPlanInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountPlanInd", lhsDiscountPlanInd), LocatorUtils.property(thatLocator, "discountPlanInd", rhsDiscountPlanInd), lhsDiscountPlanInd, rhsDiscountPlanInd)) {
                return false;
            }
        }
        {
            boolean lhsThemePackInd;
            lhsThemePackInd = (true?this.isThemePackInd():false);
            boolean rhsThemePackInd;
            rhsThemePackInd = (true?that.isThemePackInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "themePackInd", lhsThemePackInd), LocatorUtils.property(thatLocator, "themePackInd", rhsThemePackInd), lhsThemePackInd, rhsThemePackInd)) {
                return false;
            }
        }
        {
            boolean lhsHighSpeedPackInd;
            lhsHighSpeedPackInd = (true?this.isHighSpeedPackInd():false);
            boolean rhsHighSpeedPackInd;
            rhsHighSpeedPackInd = (true?that.isHighSpeedPackInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "highSpeedPackInd", lhsHighSpeedPackInd), LocatorUtils.property(thatLocator, "highSpeedPackInd", rhsHighSpeedPackInd), lhsHighSpeedPackInd, rhsHighSpeedPackInd)) {
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
