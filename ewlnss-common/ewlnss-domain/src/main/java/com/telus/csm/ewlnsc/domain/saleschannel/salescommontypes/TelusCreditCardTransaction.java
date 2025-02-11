
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for TelusCreditCardTransaction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TelusCreditCardTransaction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditCard" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CreditCard"/>
 *         &lt;element name="newCardApplication" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="newCardApplicationResultCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="creditCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="applicationSalesRepId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                   &lt;element name="applicationOutletId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="cardPaymentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelusCreditCardTransactionItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="totalChargedAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelusCreditCardTransaction", propOrder = {
    "creditCard",
    "newCardApplication",
    "cardPaymentList",
    "totalChargedAmt"
})
public class TelusCreditCardTransaction
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected CreditCard creditCard;
    protected TelusCreditCardTransaction.NewCardApplication newCardApplication;
    protected List<TelusCreditCardTransactionItem> cardPaymentList;
    protected Double totalChargedAmt;

    /**
     * Gets the value of the creditCard property.
     * 
     * @return
     *     possible object is
     *     {@link CreditCard }
     *     
     */
    public CreditCard getCreditCard() {
        return creditCard;
    }

    /**
     * Sets the value of the creditCard property.
     * 
     * @param value
     *     allowed object is
     *     {@link CreditCard }
     *     
     */
    public void setCreditCard(CreditCard value) {
        this.creditCard = value;
    }

    /**
     * Gets the value of the newCardApplication property.
     * 
     * @return
     *     possible object is
     *     {@link TelusCreditCardTransaction.NewCardApplication }
     *     
     */
    public TelusCreditCardTransaction.NewCardApplication getNewCardApplication() {
        return newCardApplication;
    }

    /**
     * Sets the value of the newCardApplication property.
     * 
     * @param value
     *     allowed object is
     *     {@link TelusCreditCardTransaction.NewCardApplication }
     *     
     */
    public void setNewCardApplication(TelusCreditCardTransaction.NewCardApplication value) {
        this.newCardApplication = value;
    }

    /**
     * Gets the value of the cardPaymentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cardPaymentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCardPaymentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TelusCreditCardTransactionItem }
     * 
     * 
     */
    public List<TelusCreditCardTransactionItem> getCardPaymentList() {
        if (cardPaymentList == null) {
            cardPaymentList = new ArrayList<TelusCreditCardTransactionItem>();
        }
        return this.cardPaymentList;
    }

    /**
     * Gets the value of the totalChargedAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTotalChargedAmt() {
        return totalChargedAmt;
    }

    /**
     * Sets the value of the totalChargedAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTotalChargedAmt(Double value) {
        this.totalChargedAmt = value;
    }

    /**
     * Sets the value of the cardPaymentList property.
     * 
     * @param cardPaymentList
     *     allowed object is
     *     {@link TelusCreditCardTransactionItem }
     *     
     */
    public void setCardPaymentList(List<TelusCreditCardTransactionItem> cardPaymentList) {
        this.cardPaymentList = cardPaymentList;
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
            CreditCard theCreditCard;
            theCreditCard = this.getCreditCard();
            strategy.appendField(locator, this, "creditCard", buffer, theCreditCard);
        }
        {
            TelusCreditCardTransaction.NewCardApplication theNewCardApplication;
            theNewCardApplication = this.getNewCardApplication();
            strategy.appendField(locator, this, "newCardApplication", buffer, theNewCardApplication);
        }
        {
            List<TelusCreditCardTransactionItem> theCardPaymentList;
            theCardPaymentList = (((this.cardPaymentList!= null)&&(!this.cardPaymentList.isEmpty()))?this.getCardPaymentList():null);
            strategy.appendField(locator, this, "cardPaymentList", buffer, theCardPaymentList);
        }
        {
            Double theTotalChargedAmt;
            theTotalChargedAmt = this.getTotalChargedAmt();
            strategy.appendField(locator, this, "totalChargedAmt", buffer, theTotalChargedAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TelusCreditCardTransaction)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TelusCreditCardTransaction that = ((TelusCreditCardTransaction) object);
        {
            CreditCard lhsCreditCard;
            lhsCreditCard = this.getCreditCard();
            CreditCard rhsCreditCard;
            rhsCreditCard = that.getCreditCard();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCard", lhsCreditCard), LocatorUtils.property(thatLocator, "creditCard", rhsCreditCard), lhsCreditCard, rhsCreditCard)) {
                return false;
            }
        }
        {
            TelusCreditCardTransaction.NewCardApplication lhsNewCardApplication;
            lhsNewCardApplication = this.getNewCardApplication();
            TelusCreditCardTransaction.NewCardApplication rhsNewCardApplication;
            rhsNewCardApplication = that.getNewCardApplication();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "newCardApplication", lhsNewCardApplication), LocatorUtils.property(thatLocator, "newCardApplication", rhsNewCardApplication), lhsNewCardApplication, rhsNewCardApplication)) {
                return false;
            }
        }
        {
            List<TelusCreditCardTransactionItem> lhsCardPaymentList;
            lhsCardPaymentList = (((this.cardPaymentList!= null)&&(!this.cardPaymentList.isEmpty()))?this.getCardPaymentList():null);
            List<TelusCreditCardTransactionItem> rhsCardPaymentList;
            rhsCardPaymentList = (((that.cardPaymentList!= null)&&(!that.cardPaymentList.isEmpty()))?that.getCardPaymentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cardPaymentList", lhsCardPaymentList), LocatorUtils.property(thatLocator, "cardPaymentList", rhsCardPaymentList), lhsCardPaymentList, rhsCardPaymentList)) {
                return false;
            }
        }
        {
            Double lhsTotalChargedAmt;
            lhsTotalChargedAmt = this.getTotalChargedAmt();
            Double rhsTotalChargedAmt;
            rhsTotalChargedAmt = that.getTotalChargedAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "totalChargedAmt", lhsTotalChargedAmt), LocatorUtils.property(thatLocator, "totalChargedAmt", rhsTotalChargedAmt), lhsTotalChargedAmt, rhsTotalChargedAmt)) {
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
     *         &lt;element name="newCardApplicationResultCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="creditCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="languageCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="applicationSalesRepId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *         &lt;element name="applicationOutletId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
        "newCardApplicationResultCd",
        "creditCardId",
        "firstName",
        "lastName",
        "languageCd",
        "applicationSalesRepId",
        "applicationOutletId"
    })
    public static class NewCardApplication
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected String newCardApplicationResultCd;
        protected String creditCardId;
        protected String firstName;
        protected String lastName;
        protected String languageCd;
        protected String applicationSalesRepId;
        protected String applicationOutletId;

        /**
         * Gets the value of the newCardApplicationResultCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNewCardApplicationResultCd() {
            return newCardApplicationResultCd;
        }

        /**
         * Sets the value of the newCardApplicationResultCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNewCardApplicationResultCd(String value) {
            this.newCardApplicationResultCd = value;
        }

        /**
         * Gets the value of the creditCardId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getCreditCardId() {
            return creditCardId;
        }

        /**
         * Sets the value of the creditCardId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setCreditCardId(String value) {
            this.creditCardId = value;
        }

        /**
         * Gets the value of the firstName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getFirstName() {
            return firstName;
        }

        /**
         * Sets the value of the firstName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setFirstName(String value) {
            this.firstName = value;
        }

        /**
         * Gets the value of the lastName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLastName() {
            return lastName;
        }

        /**
         * Sets the value of the lastName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLastName(String value) {
            this.lastName = value;
        }

        /**
         * Gets the value of the languageCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getLanguageCd() {
            return languageCd;
        }

        /**
         * Sets the value of the languageCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setLanguageCd(String value) {
            this.languageCd = value;
        }

        /**
         * Gets the value of the applicationSalesRepId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplicationSalesRepId() {
            return applicationSalesRepId;
        }

        /**
         * Sets the value of the applicationSalesRepId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplicationSalesRepId(String value) {
            this.applicationSalesRepId = value;
        }

        /**
         * Gets the value of the applicationOutletId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getApplicationOutletId() {
            return applicationOutletId;
        }

        /**
         * Sets the value of the applicationOutletId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setApplicationOutletId(String value) {
            this.applicationOutletId = value;
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
                String theNewCardApplicationResultCd;
                theNewCardApplicationResultCd = this.getNewCardApplicationResultCd();
                strategy.appendField(locator, this, "newCardApplicationResultCd", buffer, theNewCardApplicationResultCd);
            }
            {
                String theCreditCardId;
                theCreditCardId = this.getCreditCardId();
                strategy.appendField(locator, this, "creditCardId", buffer, theCreditCardId);
            }
            {
                String theFirstName;
                theFirstName = this.getFirstName();
                strategy.appendField(locator, this, "firstName", buffer, theFirstName);
            }
            {
                String theLastName;
                theLastName = this.getLastName();
                strategy.appendField(locator, this, "lastName", buffer, theLastName);
            }
            {
                String theLanguageCd;
                theLanguageCd = this.getLanguageCd();
                strategy.appendField(locator, this, "languageCd", buffer, theLanguageCd);
            }
            {
                String theApplicationSalesRepId;
                theApplicationSalesRepId = this.getApplicationSalesRepId();
                strategy.appendField(locator, this, "applicationSalesRepId", buffer, theApplicationSalesRepId);
            }
            {
                String theApplicationOutletId;
                theApplicationOutletId = this.getApplicationOutletId();
                strategy.appendField(locator, this, "applicationOutletId", buffer, theApplicationOutletId);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof TelusCreditCardTransaction.NewCardApplication)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final TelusCreditCardTransaction.NewCardApplication that = ((TelusCreditCardTransaction.NewCardApplication) object);
            {
                String lhsNewCardApplicationResultCd;
                lhsNewCardApplicationResultCd = this.getNewCardApplicationResultCd();
                String rhsNewCardApplicationResultCd;
                rhsNewCardApplicationResultCd = that.getNewCardApplicationResultCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "newCardApplicationResultCd", lhsNewCardApplicationResultCd), LocatorUtils.property(thatLocator, "newCardApplicationResultCd", rhsNewCardApplicationResultCd), lhsNewCardApplicationResultCd, rhsNewCardApplicationResultCd)) {
                    return false;
                }
            }
            {
                String lhsCreditCardId;
                lhsCreditCardId = this.getCreditCardId();
                String rhsCreditCardId;
                rhsCreditCardId = that.getCreditCardId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "creditCardId", lhsCreditCardId), LocatorUtils.property(thatLocator, "creditCardId", rhsCreditCardId), lhsCreditCardId, rhsCreditCardId)) {
                    return false;
                }
            }
            {
                String lhsFirstName;
                lhsFirstName = this.getFirstName();
                String rhsFirstName;
                rhsFirstName = that.getFirstName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "firstName", lhsFirstName), LocatorUtils.property(thatLocator, "firstName", rhsFirstName), lhsFirstName, rhsFirstName)) {
                    return false;
                }
            }
            {
                String lhsLastName;
                lhsLastName = this.getLastName();
                String rhsLastName;
                rhsLastName = that.getLastName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "lastName", lhsLastName), LocatorUtils.property(thatLocator, "lastName", rhsLastName), lhsLastName, rhsLastName)) {
                    return false;
                }
            }
            {
                String lhsLanguageCd;
                lhsLanguageCd = this.getLanguageCd();
                String rhsLanguageCd;
                rhsLanguageCd = that.getLanguageCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "languageCd", lhsLanguageCd), LocatorUtils.property(thatLocator, "languageCd", rhsLanguageCd), lhsLanguageCd, rhsLanguageCd)) {
                    return false;
                }
            }
            {
                String lhsApplicationSalesRepId;
                lhsApplicationSalesRepId = this.getApplicationSalesRepId();
                String rhsApplicationSalesRepId;
                rhsApplicationSalesRepId = that.getApplicationSalesRepId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "applicationSalesRepId", lhsApplicationSalesRepId), LocatorUtils.property(thatLocator, "applicationSalesRepId", rhsApplicationSalesRepId), lhsApplicationSalesRepId, rhsApplicationSalesRepId)) {
                    return false;
                }
            }
            {
                String lhsApplicationOutletId;
                lhsApplicationOutletId = this.getApplicationOutletId();
                String rhsApplicationOutletId;
                rhsApplicationOutletId = that.getApplicationOutletId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "applicationOutletId", lhsApplicationOutletId), LocatorUtils.property(thatLocator, "applicationOutletId", rhsApplicationOutletId), lhsApplicationOutletId, rhsApplicationOutletId)) {
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
