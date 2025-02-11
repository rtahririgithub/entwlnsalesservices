
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
 * <p>Java class for CreditAssessmentResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditAssessmentResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="creditValueCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTypeQualificationList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductTypeQualification" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="assessmentMessageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fraudIndicatorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="depositAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="rtcaSystemErrorCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fraudMessageCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="100" minOccurs="0"/>
 *         &lt;element name="promotionalGiftInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditAssessmentResult", propOrder = {
    "customerId",
    "creditValueCode",
    "productTypeQualificationList",
    "assessmentMessageCode",
    "fraudIndicatorCode",
    "depositAmount",
    "rtcaSystemErrorCode",
    "fraudMessageCodeList",
    "promotionalGiftInd"
})
public class CreditAssessmentResult
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String customerId;
    protected String creditValueCode;
    protected List<ProductTypeQualification> productTypeQualificationList;
    protected String assessmentMessageCode;
    protected String fraudIndicatorCode;
    protected Double depositAmount;
    protected String rtcaSystemErrorCode;
    protected List<String> fraudMessageCodeList;
    protected Boolean promotionalGiftInd;

    /**
     * Gets the value of the customerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * Sets the value of the customerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerId(String value) {
        this.customerId = value;
    }

    /**
     * Gets the value of the creditValueCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditValueCode() {
        return creditValueCode;
    }

    /**
     * Sets the value of the creditValueCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditValueCode(String value) {
        this.creditValueCode = value;
    }

    /**
     * Gets the value of the productTypeQualificationList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productTypeQualificationList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductTypeQualificationList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductTypeQualification }
     * 
     * 
     */
    public List<ProductTypeQualification> getProductTypeQualificationList() {
        if (productTypeQualificationList == null) {
            productTypeQualificationList = new ArrayList<ProductTypeQualification>();
        }
        return this.productTypeQualificationList;
    }

    /**
     * Gets the value of the assessmentMessageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssessmentMessageCode() {
        return assessmentMessageCode;
    }

    /**
     * Sets the value of the assessmentMessageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssessmentMessageCode(String value) {
        this.assessmentMessageCode = value;
    }

    /**
     * Gets the value of the fraudIndicatorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFraudIndicatorCode() {
        return fraudIndicatorCode;
    }

    /**
     * Sets the value of the fraudIndicatorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFraudIndicatorCode(String value) {
        this.fraudIndicatorCode = value;
    }

    /**
     * Gets the value of the depositAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDepositAmount() {
        return depositAmount;
    }

    /**
     * Sets the value of the depositAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDepositAmount(Double value) {
        this.depositAmount = value;
    }

    /**
     * Gets the value of the rtcaSystemErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRtcaSystemErrorCode() {
        return rtcaSystemErrorCode;
    }

    /**
     * Sets the value of the rtcaSystemErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRtcaSystemErrorCode(String value) {
        this.rtcaSystemErrorCode = value;
    }

    /**
     * Gets the value of the fraudMessageCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the fraudMessageCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFraudMessageCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getFraudMessageCodeList() {
        if (fraudMessageCodeList == null) {
            fraudMessageCodeList = new ArrayList<String>();
        }
        return this.fraudMessageCodeList;
    }

    /**
     * Gets the value of the promotionalGiftInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPromotionalGiftInd() {
        return promotionalGiftInd;
    }

    /**
     * Sets the value of the promotionalGiftInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPromotionalGiftInd(Boolean value) {
        this.promotionalGiftInd = value;
    }

    /**
     * Sets the value of the productTypeQualificationList property.
     * 
     * @param productTypeQualificationList
     *     allowed object is
     *     {@link ProductTypeQualification }
     *     
     */
    public void setProductTypeQualificationList(List<ProductTypeQualification> productTypeQualificationList) {
        this.productTypeQualificationList = productTypeQualificationList;
    }

    /**
     * Sets the value of the fraudMessageCodeList property.
     * 
     * @param fraudMessageCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFraudMessageCodeList(List<String> fraudMessageCodeList) {
        this.fraudMessageCodeList = fraudMessageCodeList;
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
            String theCustomerId;
            theCustomerId = this.getCustomerId();
            strategy.appendField(locator, this, "customerId", buffer, theCustomerId);
        }
        {
            String theCreditValueCode;
            theCreditValueCode = this.getCreditValueCode();
            strategy.appendField(locator, this, "creditValueCode", buffer, theCreditValueCode);
        }
        {
            List<ProductTypeQualification> theProductTypeQualificationList;
            theProductTypeQualificationList = (((this.productTypeQualificationList!= null)&&(!this.productTypeQualificationList.isEmpty()))?this.getProductTypeQualificationList():null);
            strategy.appendField(locator, this, "productTypeQualificationList", buffer, theProductTypeQualificationList);
        }
        {
            String theAssessmentMessageCode;
            theAssessmentMessageCode = this.getAssessmentMessageCode();
            strategy.appendField(locator, this, "assessmentMessageCode", buffer, theAssessmentMessageCode);
        }
        {
            String theFraudIndicatorCode;
            theFraudIndicatorCode = this.getFraudIndicatorCode();
            strategy.appendField(locator, this, "fraudIndicatorCode", buffer, theFraudIndicatorCode);
        }
        {
            Double theDepositAmount;
            theDepositAmount = this.getDepositAmount();
            strategy.appendField(locator, this, "depositAmount", buffer, theDepositAmount);
        }
        {
            String theRtcaSystemErrorCode;
            theRtcaSystemErrorCode = this.getRtcaSystemErrorCode();
            strategy.appendField(locator, this, "rtcaSystemErrorCode", buffer, theRtcaSystemErrorCode);
        }
        {
            List<String> theFraudMessageCodeList;
            theFraudMessageCodeList = (((this.fraudMessageCodeList!= null)&&(!this.fraudMessageCodeList.isEmpty()))?this.getFraudMessageCodeList():null);
            strategy.appendField(locator, this, "fraudMessageCodeList", buffer, theFraudMessageCodeList);
        }
        {
            Boolean thePromotionalGiftInd;
            thePromotionalGiftInd = this.isPromotionalGiftInd();
            strategy.appendField(locator, this, "promotionalGiftInd", buffer, thePromotionalGiftInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreditAssessmentResult)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CreditAssessmentResult that = ((CreditAssessmentResult) object);
        {
            String lhsCustomerId;
            lhsCustomerId = this.getCustomerId();
            String rhsCustomerId;
            rhsCustomerId = that.getCustomerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerId", lhsCustomerId), LocatorUtils.property(thatLocator, "customerId", rhsCustomerId), lhsCustomerId, rhsCustomerId)) {
                return false;
            }
        }
        {
            String lhsCreditValueCode;
            lhsCreditValueCode = this.getCreditValueCode();
            String rhsCreditValueCode;
            rhsCreditValueCode = that.getCreditValueCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditValueCode", lhsCreditValueCode), LocatorUtils.property(thatLocator, "creditValueCode", rhsCreditValueCode), lhsCreditValueCode, rhsCreditValueCode)) {
                return false;
            }
        }
        {
            List<ProductTypeQualification> lhsProductTypeQualificationList;
            lhsProductTypeQualificationList = (((this.productTypeQualificationList!= null)&&(!this.productTypeQualificationList.isEmpty()))?this.getProductTypeQualificationList():null);
            List<ProductTypeQualification> rhsProductTypeQualificationList;
            rhsProductTypeQualificationList = (((that.productTypeQualificationList!= null)&&(!that.productTypeQualificationList.isEmpty()))?that.getProductTypeQualificationList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeQualificationList", lhsProductTypeQualificationList), LocatorUtils.property(thatLocator, "productTypeQualificationList", rhsProductTypeQualificationList), lhsProductTypeQualificationList, rhsProductTypeQualificationList)) {
                return false;
            }
        }
        {
            String lhsAssessmentMessageCode;
            lhsAssessmentMessageCode = this.getAssessmentMessageCode();
            String rhsAssessmentMessageCode;
            rhsAssessmentMessageCode = that.getAssessmentMessageCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assessmentMessageCode", lhsAssessmentMessageCode), LocatorUtils.property(thatLocator, "assessmentMessageCode", rhsAssessmentMessageCode), lhsAssessmentMessageCode, rhsAssessmentMessageCode)) {
                return false;
            }
        }
        {
            String lhsFraudIndicatorCode;
            lhsFraudIndicatorCode = this.getFraudIndicatorCode();
            String rhsFraudIndicatorCode;
            rhsFraudIndicatorCode = that.getFraudIndicatorCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fraudIndicatorCode", lhsFraudIndicatorCode), LocatorUtils.property(thatLocator, "fraudIndicatorCode", rhsFraudIndicatorCode), lhsFraudIndicatorCode, rhsFraudIndicatorCode)) {
                return false;
            }
        }
        {
            Double lhsDepositAmount;
            lhsDepositAmount = this.getDepositAmount();
            Double rhsDepositAmount;
            rhsDepositAmount = that.getDepositAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositAmount", lhsDepositAmount), LocatorUtils.property(thatLocator, "depositAmount", rhsDepositAmount), lhsDepositAmount, rhsDepositAmount)) {
                return false;
            }
        }
        {
            String lhsRtcaSystemErrorCode;
            lhsRtcaSystemErrorCode = this.getRtcaSystemErrorCode();
            String rhsRtcaSystemErrorCode;
            rhsRtcaSystemErrorCode = that.getRtcaSystemErrorCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rtcaSystemErrorCode", lhsRtcaSystemErrorCode), LocatorUtils.property(thatLocator, "rtcaSystemErrorCode", rhsRtcaSystemErrorCode), lhsRtcaSystemErrorCode, rhsRtcaSystemErrorCode)) {
                return false;
            }
        }
        {
            List<String> lhsFraudMessageCodeList;
            lhsFraudMessageCodeList = (((this.fraudMessageCodeList!= null)&&(!this.fraudMessageCodeList.isEmpty()))?this.getFraudMessageCodeList():null);
            List<String> rhsFraudMessageCodeList;
            rhsFraudMessageCodeList = (((that.fraudMessageCodeList!= null)&&(!that.fraudMessageCodeList.isEmpty()))?that.getFraudMessageCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fraudMessageCodeList", lhsFraudMessageCodeList), LocatorUtils.property(thatLocator, "fraudMessageCodeList", rhsFraudMessageCodeList), lhsFraudMessageCodeList, rhsFraudMessageCodeList)) {
                return false;
            }
        }
        {
            Boolean lhsPromotionalGiftInd;
            lhsPromotionalGiftInd = this.isPromotionalGiftInd();
            Boolean rhsPromotionalGiftInd;
            rhsPromotionalGiftInd = that.isPromotionalGiftInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionalGiftInd", lhsPromotionalGiftInd), LocatorUtils.property(thatLocator, "promotionalGiftInd", rhsPromotionalGiftInd), lhsPromotionalGiftInd, rhsPromotionalGiftInd)) {
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
