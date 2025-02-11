
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
 * - CNP_DB_CHANNEL_TRANS_ID: this value must be derived from processCreditCardTransactionResponse.creditCardChannelTransactionId (for DB payment)
 * - CNP_DB_TRANS_ITEM_ID: this vale must be derived from  processCreditCardTransactionResponse.creditCardTransactionItemList for device balance transactionItemId
 * - CNP_DB_TRANS_AMT: this vale must be derived from  processCreditCardTransactionResponse.creditCardTransactionItemList for device balance transactionItemAmt
 * - CNP_HANDSET_CHANNEL_TRANS_ID: this value must be derived from processCreditCardTransactionResponse.creditCardChannelTransactionId (for Device Price (handset) payment)
 * - CNP_HANDSET_TRANS_ITEM_ID: this vale must be derived from  processCreditCardTransactionResponse.creditCardTransactionItemList for final handset price transactionItemId
 * - CNP_HANDSET_TRANS_AMT: this vale must be derived from  processCreditCardTransactionResponse.creditCardTransactionItemList for final handset price transactionItemAmt
 * 
 * 
 * <p>Java class for TelusCreditCardTransactionItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TelusCreditCardTransactionItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="channelTransactionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionItemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelusCreditCardTransactionItem", propOrder = {
    "transactionTypeCd",
    "channelTransactionId",
    "transactionItemId",
    "transactionAmt"
})
public class TelusCreditCardTransactionItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String transactionTypeCd;
    @XmlElement(required = true)
    protected String channelTransactionId;
    @XmlElement(required = true)
    protected String transactionItemId;
    protected double transactionAmt;

    /**
     * Gets the value of the transactionTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionTypeCd() {
        return transactionTypeCd;
    }

    /**
     * Sets the value of the transactionTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionTypeCd(String value) {
        this.transactionTypeCd = value;
    }

    /**
     * Gets the value of the channelTransactionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getChannelTransactionId() {
        return channelTransactionId;
    }

    /**
     * Sets the value of the channelTransactionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setChannelTransactionId(String value) {
        this.channelTransactionId = value;
    }

    /**
     * Gets the value of the transactionItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionItemId() {
        return transactionItemId;
    }

    /**
     * Sets the value of the transactionItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionItemId(String value) {
        this.transactionItemId = value;
    }

    /**
     * Gets the value of the transactionAmt property.
     * 
     */
    public double getTransactionAmt() {
        return transactionAmt;
    }

    /**
     * Sets the value of the transactionAmt property.
     * 
     */
    public void setTransactionAmt(double value) {
        this.transactionAmt = value;
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
            String theTransactionTypeCd;
            theTransactionTypeCd = this.getTransactionTypeCd();
            strategy.appendField(locator, this, "transactionTypeCd", buffer, theTransactionTypeCd);
        }
        {
            String theChannelTransactionId;
            theChannelTransactionId = this.getChannelTransactionId();
            strategy.appendField(locator, this, "channelTransactionId", buffer, theChannelTransactionId);
        }
        {
            String theTransactionItemId;
            theTransactionItemId = this.getTransactionItemId();
            strategy.appendField(locator, this, "transactionItemId", buffer, theTransactionItemId);
        }
        {
            double theTransactionAmt;
            theTransactionAmt = (true?this.getTransactionAmt(): 0.0D);
            strategy.appendField(locator, this, "transactionAmt", buffer, theTransactionAmt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TelusCreditCardTransactionItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TelusCreditCardTransactionItem that = ((TelusCreditCardTransactionItem) object);
        {
            String lhsTransactionTypeCd;
            lhsTransactionTypeCd = this.getTransactionTypeCd();
            String rhsTransactionTypeCd;
            rhsTransactionTypeCd = that.getTransactionTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionTypeCd", lhsTransactionTypeCd), LocatorUtils.property(thatLocator, "transactionTypeCd", rhsTransactionTypeCd), lhsTransactionTypeCd, rhsTransactionTypeCd)) {
                return false;
            }
        }
        {
            String lhsChannelTransactionId;
            lhsChannelTransactionId = this.getChannelTransactionId();
            String rhsChannelTransactionId;
            rhsChannelTransactionId = that.getChannelTransactionId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "channelTransactionId", lhsChannelTransactionId), LocatorUtils.property(thatLocator, "channelTransactionId", rhsChannelTransactionId), lhsChannelTransactionId, rhsChannelTransactionId)) {
                return false;
            }
        }
        {
            String lhsTransactionItemId;
            lhsTransactionItemId = this.getTransactionItemId();
            String rhsTransactionItemId;
            rhsTransactionItemId = that.getTransactionItemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionItemId", lhsTransactionItemId), LocatorUtils.property(thatLocator, "transactionItemId", rhsTransactionItemId), lhsTransactionItemId, rhsTransactionItemId)) {
                return false;
            }
        }
        {
            double lhsTransactionAmt;
            lhsTransactionAmt = (true?this.getTransactionAmt(): 0.0D);
            double rhsTransactionAmt;
            rhsTransactionAmt = (true?that.getTransactionAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionAmt", lhsTransactionAmt), LocatorUtils.property(thatLocator, "transactionAmt", rhsTransactionAmt), lhsTransactionAmt, rhsTransactionAmt)) {
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
