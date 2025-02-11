
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * Financing options like Voip Easy Pay charge detail to be returned here.
 * 
 * <p>Java class for FinancingOptionsType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FinancingOptionsType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="term" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="deviceMsrpAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="downPaymentAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="downPaymentTaxAmt" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxAmount" minOccurs="0"/>
 *         &lt;element name="installmentAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="totalPaymentAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="totalFinancedAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="totalFinancedTaxAmt" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxAmount" minOccurs="0"/>
 *         &lt;element name="financingOptionTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="serviceSocCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="provinceCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="30"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FinancingOptionsType", propOrder = {
    "term",
    "deviceMsrpAmt",
    "downPaymentAmt",
    "downPaymentTaxAmt",
    "installmentAmt",
    "totalPaymentAmt",
    "totalFinancedAmt",
    "totalFinancedTaxAmt",
    "financingOptionTypeCode",
    "serviceSocCode",
    "provinceCodeList"
})
@XmlSeeAlso({
    SelectedFinancingOptionType.class
})
public class FinancingOptionsType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Integer term;
    protected Double deviceMsrpAmt;
    protected double downPaymentAmt;
    protected TaxAmount downPaymentTaxAmt;
    protected Double installmentAmt;
    protected double totalPaymentAmt;
    protected double totalFinancedAmt;
    protected TaxAmount totalFinancedTaxAmt;
    @XmlElement(required = true)
    protected String financingOptionTypeCode;
    @XmlElement(required = true)
    protected String serviceSocCode;
    @XmlElement(required = true)
    protected List<String> provinceCodeList;

    /**
     * Gets the value of the term property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTerm() {
        return term;
    }

    /**
     * Sets the value of the term property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTerm(Integer value) {
        this.term = value;
    }

    /**
     * Gets the value of the deviceMsrpAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDeviceMsrpAmt() {
        return deviceMsrpAmt;
    }

    /**
     * Sets the value of the deviceMsrpAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDeviceMsrpAmt(Double value) {
        this.deviceMsrpAmt = value;
    }

    /**
     * Gets the value of the downPaymentAmt property.
     * 
     */
    public double getDownPaymentAmt() {
        return downPaymentAmt;
    }

    /**
     * Sets the value of the downPaymentAmt property.
     * 
     */
    public void setDownPaymentAmt(double value) {
        this.downPaymentAmt = value;
    }

    /**
     * Gets the value of the downPaymentTaxAmt property.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getDownPaymentTaxAmt() {
        return downPaymentTaxAmt;
    }

    /**
     * Sets the value of the downPaymentTaxAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setDownPaymentTaxAmt(TaxAmount value) {
        this.downPaymentTaxAmt = value;
    }

    /**
     * Gets the value of the installmentAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getInstallmentAmt() {
        return installmentAmt;
    }

    /**
     * Sets the value of the installmentAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setInstallmentAmt(Double value) {
        this.installmentAmt = value;
    }

    /**
     * Gets the value of the totalPaymentAmt property.
     * 
     */
    public double getTotalPaymentAmt() {
        return totalPaymentAmt;
    }

    /**
     * Sets the value of the totalPaymentAmt property.
     * 
     */
    public void setTotalPaymentAmt(double value) {
        this.totalPaymentAmt = value;
    }

    /**
     * Gets the value of the totalFinancedAmt property.
     * 
     */
    public double getTotalFinancedAmt() {
        return totalFinancedAmt;
    }

    /**
     * Sets the value of the totalFinancedAmt property.
     * 
     */
    public void setTotalFinancedAmt(double value) {
        this.totalFinancedAmt = value;
    }

    /**
     * Gets the value of the totalFinancedTaxAmt property.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getTotalFinancedTaxAmt() {
        return totalFinancedTaxAmt;
    }

    /**
     * Sets the value of the totalFinancedTaxAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setTotalFinancedTaxAmt(TaxAmount value) {
        this.totalFinancedTaxAmt = value;
    }

    /**
     * Gets the value of the financingOptionTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinancingOptionTypeCode() {
        return financingOptionTypeCode;
    }

    /**
     * Sets the value of the financingOptionTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinancingOptionTypeCode(String value) {
        this.financingOptionTypeCode = value;
    }

    /**
     * Gets the value of the serviceSocCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceSocCode() {
        return serviceSocCode;
    }

    /**
     * Sets the value of the serviceSocCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceSocCode(String value) {
        this.serviceSocCode = value;
    }

    /**
     * Gets the value of the provinceCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the provinceCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProvinceCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getProvinceCodeList() {
        if (provinceCodeList == null) {
            provinceCodeList = new ArrayList<String>();
        }
        return this.provinceCodeList;
    }

    /**
     * Sets the value of the provinceCodeList property.
     * 
     * @param provinceCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCodeList(List<String> provinceCodeList) {
        this.provinceCodeList = provinceCodeList;
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
            Integer theTerm;
            theTerm = this.getTerm();
            strategy.appendField(locator, this, "term", buffer, theTerm);
        }
        {
            Double theDeviceMsrpAmt;
            theDeviceMsrpAmt = this.getDeviceMsrpAmt();
            strategy.appendField(locator, this, "deviceMsrpAmt", buffer, theDeviceMsrpAmt);
        }
        {
            double theDownPaymentAmt;
            theDownPaymentAmt = (true?this.getDownPaymentAmt(): 0.0D);
            strategy.appendField(locator, this, "downPaymentAmt", buffer, theDownPaymentAmt);
        }
        {
            TaxAmount theDownPaymentTaxAmt;
            theDownPaymentTaxAmt = this.getDownPaymentTaxAmt();
            strategy.appendField(locator, this, "downPaymentTaxAmt", buffer, theDownPaymentTaxAmt);
        }
        {
            Double theInstallmentAmt;
            theInstallmentAmt = this.getInstallmentAmt();
            strategy.appendField(locator, this, "installmentAmt", buffer, theInstallmentAmt);
        }
        {
            double theTotalPaymentAmt;
            theTotalPaymentAmt = (true?this.getTotalPaymentAmt(): 0.0D);
            strategy.appendField(locator, this, "totalPaymentAmt", buffer, theTotalPaymentAmt);
        }
        {
            double theTotalFinancedAmt;
            theTotalFinancedAmt = (true?this.getTotalFinancedAmt(): 0.0D);
            strategy.appendField(locator, this, "totalFinancedAmt", buffer, theTotalFinancedAmt);
        }
        {
            TaxAmount theTotalFinancedTaxAmt;
            theTotalFinancedTaxAmt = this.getTotalFinancedTaxAmt();
            strategy.appendField(locator, this, "totalFinancedTaxAmt", buffer, theTotalFinancedTaxAmt);
        }
        {
            String theFinancingOptionTypeCode;
            theFinancingOptionTypeCode = this.getFinancingOptionTypeCode();
            strategy.appendField(locator, this, "financingOptionTypeCode", buffer, theFinancingOptionTypeCode);
        }
        {
            String theServiceSocCode;
            theServiceSocCode = this.getServiceSocCode();
            strategy.appendField(locator, this, "serviceSocCode", buffer, theServiceSocCode);
        }
        {
            List<String> theProvinceCodeList;
            theProvinceCodeList = (((this.provinceCodeList!= null)&&(!this.provinceCodeList.isEmpty()))?this.getProvinceCodeList():null);
            strategy.appendField(locator, this, "provinceCodeList", buffer, theProvinceCodeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FinancingOptionsType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FinancingOptionsType that = ((FinancingOptionsType) object);
        {
            Integer lhsTerm;
            lhsTerm = this.getTerm();
            Integer rhsTerm;
            rhsTerm = that.getTerm();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "term", lhsTerm), LocatorUtils.property(thatLocator, "term", rhsTerm), lhsTerm, rhsTerm)) {
                return false;
            }
        }
        {
            Double lhsDeviceMsrpAmt;
            lhsDeviceMsrpAmt = this.getDeviceMsrpAmt();
            Double rhsDeviceMsrpAmt;
            rhsDeviceMsrpAmt = that.getDeviceMsrpAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deviceMsrpAmt", lhsDeviceMsrpAmt), LocatorUtils.property(thatLocator, "deviceMsrpAmt", rhsDeviceMsrpAmt), lhsDeviceMsrpAmt, rhsDeviceMsrpAmt)) {
                return false;
            }
        }
        {
            double lhsDownPaymentAmt;
            lhsDownPaymentAmt = (true?this.getDownPaymentAmt(): 0.0D);
            double rhsDownPaymentAmt;
            rhsDownPaymentAmt = (true?that.getDownPaymentAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "downPaymentAmt", lhsDownPaymentAmt), LocatorUtils.property(thatLocator, "downPaymentAmt", rhsDownPaymentAmt), lhsDownPaymentAmt, rhsDownPaymentAmt)) {
                return false;
            }
        }
        {
            TaxAmount lhsDownPaymentTaxAmt;
            lhsDownPaymentTaxAmt = this.getDownPaymentTaxAmt();
            TaxAmount rhsDownPaymentTaxAmt;
            rhsDownPaymentTaxAmt = that.getDownPaymentTaxAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "downPaymentTaxAmt", lhsDownPaymentTaxAmt), LocatorUtils.property(thatLocator, "downPaymentTaxAmt", rhsDownPaymentTaxAmt), lhsDownPaymentTaxAmt, rhsDownPaymentTaxAmt)) {
                return false;
            }
        }
        {
            Double lhsInstallmentAmt;
            lhsInstallmentAmt = this.getInstallmentAmt();
            Double rhsInstallmentAmt;
            rhsInstallmentAmt = that.getInstallmentAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "installmentAmt", lhsInstallmentAmt), LocatorUtils.property(thatLocator, "installmentAmt", rhsInstallmentAmt), lhsInstallmentAmt, rhsInstallmentAmt)) {
                return false;
            }
        }
        {
            double lhsTotalPaymentAmt;
            lhsTotalPaymentAmt = (true?this.getTotalPaymentAmt(): 0.0D);
            double rhsTotalPaymentAmt;
            rhsTotalPaymentAmt = (true?that.getTotalPaymentAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalPaymentAmt", lhsTotalPaymentAmt), LocatorUtils.property(thatLocator, "totalPaymentAmt", rhsTotalPaymentAmt), lhsTotalPaymentAmt, rhsTotalPaymentAmt)) {
                return false;
            }
        }
        {
            double lhsTotalFinancedAmt;
            lhsTotalFinancedAmt = (true?this.getTotalFinancedAmt(): 0.0D);
            double rhsTotalFinancedAmt;
            rhsTotalFinancedAmt = (true?that.getTotalFinancedAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalFinancedAmt", lhsTotalFinancedAmt), LocatorUtils.property(thatLocator, "totalFinancedAmt", rhsTotalFinancedAmt), lhsTotalFinancedAmt, rhsTotalFinancedAmt)) {
                return false;
            }
        }
        {
            TaxAmount lhsTotalFinancedTaxAmt;
            lhsTotalFinancedTaxAmt = this.getTotalFinancedTaxAmt();
            TaxAmount rhsTotalFinancedTaxAmt;
            rhsTotalFinancedTaxAmt = that.getTotalFinancedTaxAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalFinancedTaxAmt", lhsTotalFinancedTaxAmt), LocatorUtils.property(thatLocator, "totalFinancedTaxAmt", rhsTotalFinancedTaxAmt), lhsTotalFinancedTaxAmt, rhsTotalFinancedTaxAmt)) {
                return false;
            }
        }
        {
            String lhsFinancingOptionTypeCode;
            lhsFinancingOptionTypeCode = this.getFinancingOptionTypeCode();
            String rhsFinancingOptionTypeCode;
            rhsFinancingOptionTypeCode = that.getFinancingOptionTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "financingOptionTypeCode", lhsFinancingOptionTypeCode), LocatorUtils.property(thatLocator, "financingOptionTypeCode", rhsFinancingOptionTypeCode), lhsFinancingOptionTypeCode, rhsFinancingOptionTypeCode)) {
                return false;
            }
        }
        {
            String lhsServiceSocCode;
            lhsServiceSocCode = this.getServiceSocCode();
            String rhsServiceSocCode;
            rhsServiceSocCode = that.getServiceSocCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceSocCode", lhsServiceSocCode), LocatorUtils.property(thatLocator, "serviceSocCode", rhsServiceSocCode), lhsServiceSocCode, rhsServiceSocCode)) {
                return false;
            }
        }
        {
            List<String> lhsProvinceCodeList;
            lhsProvinceCodeList = (((this.provinceCodeList!= null)&&(!this.provinceCodeList.isEmpty()))?this.getProvinceCodeList():null);
            List<String> rhsProvinceCodeList;
            rhsProvinceCodeList = (((that.provinceCodeList!= null)&&(!that.provinceCodeList.isEmpty()))?that.getProvinceCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceCodeList", lhsProvinceCodeList), LocatorUtils.property(thatLocator, "provinceCodeList", rhsProvinceCodeList), lhsProvinceCodeList, rhsProvinceCodeList)) {
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
