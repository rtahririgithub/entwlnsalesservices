
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for Message complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Message">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList"/>
 *         &lt;element name="messageKey" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Message", propOrder = {
    "messageTypeCode",
    "message",
    "messageKey"
})
public class Message
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String messageTypeCode;
    @XmlElement(required = true)
    protected MultilingualCodeDescTextList message;
    @XmlElement(required = true)
    protected String messageKey;

    /**
     * Gets the value of the messageTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTypeCode() {
        return messageTypeCode;
    }

    /**
     * Sets the value of the messageTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTypeCode(String value) {
        this.messageTypeCode = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setMessage(MultilingualCodeDescTextList value) {
        this.message = value;
    }

    /**
     * Gets the value of the messageKey property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageKey() {
        return messageKey;
    }

    /**
     * Sets the value of the messageKey property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageKey(String value) {
        this.messageKey = value;
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
            String theMessageTypeCode;
            theMessageTypeCode = this.getMessageTypeCode();
            strategy.appendField(locator, this, "messageTypeCode", buffer, theMessageTypeCode);
        }
        {
            MultilingualCodeDescTextList theMessage;
            theMessage = this.getMessage();
            strategy.appendField(locator, this, "message", buffer, theMessage);
        }
        {
            String theMessageKey;
            theMessageKey = this.getMessageKey();
            strategy.appendField(locator, this, "messageKey", buffer, theMessageKey);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Message)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Message that = ((Message) object);
        {
            String lhsMessageTypeCode;
            lhsMessageTypeCode = this.getMessageTypeCode();
            String rhsMessageTypeCode;
            rhsMessageTypeCode = that.getMessageTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "messageTypeCode", lhsMessageTypeCode), LocatorUtils.property(thatLocator, "messageTypeCode", rhsMessageTypeCode), lhsMessageTypeCode, rhsMessageTypeCode)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsMessage;
            lhsMessage = this.getMessage();
            MultilingualCodeDescTextList rhsMessage;
            rhsMessage = that.getMessage();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "message", lhsMessage), LocatorUtils.property(thatLocator, "message", rhsMessage), lhsMessage, rhsMessage)) {
                return false;
            }
        }
        {
            String lhsMessageKey;
            lhsMessageKey = this.getMessageKey();
            String rhsMessageKey;
            rhsMessageKey = that.getMessageKey();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "messageKey", lhsMessageKey), LocatorUtils.property(thatLocator, "messageKey", rhsMessageKey), lhsMessageKey, rhsMessageKey)) {
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
