
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * <p>Java class for TransactionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransactionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransactionType", propOrder = {
    "transactionTypeCode",
    "transactionSubTypeCode"
})
public class TransactionType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String transactionTypeCode;
    protected String transactionSubTypeCode;

    /**
     * Gets the value of the transactionTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeCode() {
        return transactionTypeCode;
    }

    /**
     * Sets the value of the transactionTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeCode(String value) {
        this.transactionTypeCode = value;
    }

    /**
     * Gets the value of the transactionSubTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionSubTypeCode() {
        return transactionSubTypeCode;
    }

    /**
     * Sets the value of the transactionSubTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionSubTypeCode(String value) {
        this.transactionSubTypeCode = value;
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
            String theTransactionTypeCode;
            theTransactionTypeCode = this.getTransactionTypeCode();
            strategy.appendField(locator, this, "transactionTypeCode", buffer, theTransactionTypeCode);
        }
        {
            String theTransactionSubTypeCode;
            theTransactionSubTypeCode = this.getTransactionSubTypeCode();
            strategy.appendField(locator, this, "transactionSubTypeCode", buffer, theTransactionSubTypeCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TransactionType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TransactionType that = ((TransactionType) object);
        {
            String lhsTransactionTypeCode;
            lhsTransactionTypeCode = this.getTransactionTypeCode();
            String rhsTransactionTypeCode;
            rhsTransactionTypeCode = that.getTransactionTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionTypeCode", lhsTransactionTypeCode), LocatorUtils.property(thatLocator, "transactionTypeCode", rhsTransactionTypeCode), lhsTransactionTypeCode, rhsTransactionTypeCode)) {
                return false;
            }
        }
        {
            String lhsTransactionSubTypeCode;
            lhsTransactionSubTypeCode = this.getTransactionSubTypeCode();
            String rhsTransactionSubTypeCode;
            rhsTransactionSubTypeCode = that.getTransactionSubTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionSubTypeCode", lhsTransactionSubTypeCode), LocatorUtils.property(thatLocator, "transactionSubTypeCode", rhsTransactionSubTypeCode), lhsTransactionSubTypeCode, rhsTransactionSubTypeCode)) {
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
