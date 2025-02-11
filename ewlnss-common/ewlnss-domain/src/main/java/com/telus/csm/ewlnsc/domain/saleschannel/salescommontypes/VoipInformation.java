
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * VoIP Phone Number containing system generated WLS phone number (not visible to the client), VoIP local phone number, or VoIP toll-free phone number
 * 
 * <p>Java class for VoipInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VoipInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalKeyIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productAliasList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductAliasType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="byodIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="paymentAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="paymentChargeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="selectedFinancingOption" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SelectedFinancingOptionType" minOccurs="0"/>
 *         &lt;element name="devicePriceAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VoipInformation", propOrder = {
    "productTypeCode",
    "productCode",
    "externalKeyIdentifier",
    "productAliasList",
    "byodIndicator",
    "paymentAmount",
    "paymentChargeCode",
    "selectedFinancingOption",
    "devicePriceAmount"
})
public class VoipInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String productTypeCode;
    protected String productCode;
    protected String externalKeyIdentifier;
    protected List<ProductAliasType> productAliasList;
    protected Boolean byodIndicator;
    protected Double paymentAmount;
    protected String paymentChargeCode;
    protected SelectedFinancingOptionType selectedFinancingOption;
    protected Double devicePriceAmount;

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
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the externalKeyIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalKeyIdentifier() {
        return externalKeyIdentifier;
    }

    /**
     * Sets the value of the externalKeyIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalKeyIdentifier(String value) {
        this.externalKeyIdentifier = value;
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
     * Gets the value of the byodIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isByodIndicator() {
        return byodIndicator;
    }

    /**
     * Sets the value of the byodIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setByodIndicator(Boolean value) {
        this.byodIndicator = value;
    }

    /**
     * Gets the value of the paymentAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getPaymentAmount() {
        return paymentAmount;
    }

    /**
     * Sets the value of the paymentAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setPaymentAmount(Double value) {
        this.paymentAmount = value;
    }

    /**
     * Gets the value of the paymentChargeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentChargeCode() {
        return paymentChargeCode;
    }

    /**
     * Sets the value of the paymentChargeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentChargeCode(String value) {
        this.paymentChargeCode = value;
    }

    /**
     * Gets the value of the selectedFinancingOption property.
     * 
     * @return
     *     possible object is
     *     {@link SelectedFinancingOptionType }
     *     
     */
    public SelectedFinancingOptionType getSelectedFinancingOption() {
        return selectedFinancingOption;
    }

    /**
     * Sets the value of the selectedFinancingOption property.
     * 
     * @param value
     *     allowed object is
     *     {@link SelectedFinancingOptionType }
     *     
     */
    public void setSelectedFinancingOption(SelectedFinancingOptionType value) {
        this.selectedFinancingOption = value;
    }

    /**
     * Gets the value of the devicePriceAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDevicePriceAmount() {
        return devicePriceAmount;
    }

    /**
     * Sets the value of the devicePriceAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDevicePriceAmount(Double value) {
        this.devicePriceAmount = value;
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
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        {
            String theExternalKeyIdentifier;
            theExternalKeyIdentifier = this.getExternalKeyIdentifier();
            strategy.appendField(locator, this, "externalKeyIdentifier", buffer, theExternalKeyIdentifier);
        }
        {
            List<ProductAliasType> theProductAliasList;
            theProductAliasList = (((this.productAliasList!= null)&&(!this.productAliasList.isEmpty()))?this.getProductAliasList():null);
            strategy.appendField(locator, this, "productAliasList", buffer, theProductAliasList);
        }
        {
            Boolean theByodIndicator;
            theByodIndicator = this.isByodIndicator();
            strategy.appendField(locator, this, "byodIndicator", buffer, theByodIndicator);
        }
        {
            Double thePaymentAmount;
            thePaymentAmount = this.getPaymentAmount();
            strategy.appendField(locator, this, "paymentAmount", buffer, thePaymentAmount);
        }
        {
            String thePaymentChargeCode;
            thePaymentChargeCode = this.getPaymentChargeCode();
            strategy.appendField(locator, this, "paymentChargeCode", buffer, thePaymentChargeCode);
        }
        {
            SelectedFinancingOptionType theSelectedFinancingOption;
            theSelectedFinancingOption = this.getSelectedFinancingOption();
            strategy.appendField(locator, this, "selectedFinancingOption", buffer, theSelectedFinancingOption);
        }
        {
            Double theDevicePriceAmount;
            theDevicePriceAmount = this.getDevicePriceAmount();
            strategy.appendField(locator, this, "devicePriceAmount", buffer, theDevicePriceAmount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof VoipInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final VoipInformation that = ((VoipInformation) object);
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
            String lhsProductCode;
            lhsProductCode = this.getProductCode();
            String rhsProductCode;
            rhsProductCode = that.getProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCode", lhsProductCode), LocatorUtils.property(thatLocator, "productCode", rhsProductCode), lhsProductCode, rhsProductCode)) {
                return false;
            }
        }
        {
            String lhsExternalKeyIdentifier;
            lhsExternalKeyIdentifier = this.getExternalKeyIdentifier();
            String rhsExternalKeyIdentifier;
            rhsExternalKeyIdentifier = that.getExternalKeyIdentifier();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "externalKeyIdentifier", lhsExternalKeyIdentifier), LocatorUtils.property(thatLocator, "externalKeyIdentifier", rhsExternalKeyIdentifier), lhsExternalKeyIdentifier, rhsExternalKeyIdentifier)) {
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
            Boolean lhsByodIndicator;
            lhsByodIndicator = this.isByodIndicator();
            Boolean rhsByodIndicator;
            rhsByodIndicator = that.isByodIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "byodIndicator", lhsByodIndicator), LocatorUtils.property(thatLocator, "byodIndicator", rhsByodIndicator), lhsByodIndicator, rhsByodIndicator)) {
                return false;
            }
        }
        {
            Double lhsPaymentAmount;
            lhsPaymentAmount = this.getPaymentAmount();
            Double rhsPaymentAmount;
            rhsPaymentAmount = that.getPaymentAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentAmount", lhsPaymentAmount), LocatorUtils.property(thatLocator, "paymentAmount", rhsPaymentAmount), lhsPaymentAmount, rhsPaymentAmount)) {
                return false;
            }
        }
        {
            String lhsPaymentChargeCode;
            lhsPaymentChargeCode = this.getPaymentChargeCode();
            String rhsPaymentChargeCode;
            rhsPaymentChargeCode = that.getPaymentChargeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentChargeCode", lhsPaymentChargeCode), LocatorUtils.property(thatLocator, "paymentChargeCode", rhsPaymentChargeCode), lhsPaymentChargeCode, rhsPaymentChargeCode)) {
                return false;
            }
        }
        {
            SelectedFinancingOptionType lhsSelectedFinancingOption;
            lhsSelectedFinancingOption = this.getSelectedFinancingOption();
            SelectedFinancingOptionType rhsSelectedFinancingOption;
            rhsSelectedFinancingOption = that.getSelectedFinancingOption();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedFinancingOption", lhsSelectedFinancingOption), LocatorUtils.property(thatLocator, "selectedFinancingOption", rhsSelectedFinancingOption), lhsSelectedFinancingOption, rhsSelectedFinancingOption)) {
                return false;
            }
        }
        {
            Double lhsDevicePriceAmount;
            lhsDevicePriceAmount = this.getDevicePriceAmount();
            Double rhsDevicePriceAmount;
            rhsDevicePriceAmount = that.getDevicePriceAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "devicePriceAmount", lhsDevicePriceAmount), LocatorUtils.property(thatLocator, "devicePriceAmount", rhsDevicePriceAmount), lhsDevicePriceAmount, rhsDevicePriceAmount)) {
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
