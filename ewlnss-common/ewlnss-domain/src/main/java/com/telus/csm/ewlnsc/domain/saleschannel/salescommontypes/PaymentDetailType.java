
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
 * <p>Java class for PaymentDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PaymentDetailType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="totalPaymentAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="taxAmount" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TaxAmount" minOccurs="0"/>
 *         &lt;element name="paymentChargeList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PaymentInformation" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PaymentDetailType", propOrder = {
    "totalPaymentAmount",
    "taxAmount",
    "paymentChargeList"
})
public class PaymentDetailType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected double totalPaymentAmount;
    protected TaxAmount taxAmount;
    protected List<PaymentInformation> paymentChargeList;

    /**
     * Gets the value of the totalPaymentAmount property.
     * 
     */
    public double getTotalPaymentAmount() {
        return totalPaymentAmount;
    }

    /**
     * Sets the value of the totalPaymentAmount property.
     * 
     */
    public void setTotalPaymentAmount(double value) {
        this.totalPaymentAmount = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link TaxAmount }
     *     
     */
    public TaxAmount getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link TaxAmount }
     *     
     */
    public void setTaxAmount(TaxAmount value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the paymentChargeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentChargeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentChargeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentInformation }
     * 
     * 
     */
    public List<PaymentInformation> getPaymentChargeList() {
        if (paymentChargeList == null) {
            paymentChargeList = new ArrayList<PaymentInformation>();
        }
        return this.paymentChargeList;
    }

    /**
     * Sets the value of the paymentChargeList property.
     * 
     * @param paymentChargeList
     *     allowed object is
     *     {@link PaymentInformation }
     *     
     */
    public void setPaymentChargeList(List<PaymentInformation> paymentChargeList) {
        this.paymentChargeList = paymentChargeList;
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
            double theTotalPaymentAmount;
            theTotalPaymentAmount = (true?this.getTotalPaymentAmount(): 0.0D);
            strategy.appendField(locator, this, "totalPaymentAmount", buffer, theTotalPaymentAmount);
        }
        {
            TaxAmount theTaxAmount;
            theTaxAmount = this.getTaxAmount();
            strategy.appendField(locator, this, "taxAmount", buffer, theTaxAmount);
        }
        {
            List<PaymentInformation> thePaymentChargeList;
            thePaymentChargeList = (((this.paymentChargeList!= null)&&(!this.paymentChargeList.isEmpty()))?this.getPaymentChargeList():null);
            strategy.appendField(locator, this, "paymentChargeList", buffer, thePaymentChargeList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PaymentDetailType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PaymentDetailType that = ((PaymentDetailType) object);
        {
            double lhsTotalPaymentAmount;
            lhsTotalPaymentAmount = (true?this.getTotalPaymentAmount(): 0.0D);
            double rhsTotalPaymentAmount;
            rhsTotalPaymentAmount = (true?that.getTotalPaymentAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalPaymentAmount", lhsTotalPaymentAmount), LocatorUtils.property(thatLocator, "totalPaymentAmount", rhsTotalPaymentAmount), lhsTotalPaymentAmount, rhsTotalPaymentAmount)) {
                return false;
            }
        }
        {
            TaxAmount lhsTaxAmount;
            lhsTaxAmount = this.getTaxAmount();
            TaxAmount rhsTaxAmount;
            rhsTaxAmount = that.getTaxAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "taxAmount", lhsTaxAmount), LocatorUtils.property(thatLocator, "taxAmount", rhsTaxAmount), lhsTaxAmount, rhsTaxAmount)) {
                return false;
            }
        }
        {
            List<PaymentInformation> lhsPaymentChargeList;
            lhsPaymentChargeList = (((this.paymentChargeList!= null)&&(!this.paymentChargeList.isEmpty()))?this.getPaymentChargeList():null);
            List<PaymentInformation> rhsPaymentChargeList;
            rhsPaymentChargeList = (((that.paymentChargeList!= null)&&(!that.paymentChargeList.isEmpty()))?that.getPaymentChargeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentChargeList", lhsPaymentChargeList), LocatorUtils.property(thatLocator, "paymentChargeList", rhsPaymentChargeList), lhsPaymentChargeList, rhsPaymentChargeList)) {
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
