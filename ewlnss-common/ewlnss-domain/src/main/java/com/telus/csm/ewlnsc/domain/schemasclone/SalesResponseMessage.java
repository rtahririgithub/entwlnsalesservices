
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.ResponseMessage;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for SalesResponseMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesResponseMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageList" maxOccurs="100" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}ResponseMessage">
 *                 &lt;sequence>
 *                   &lt;element name="relatedMessageList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RelatedMessage" maxOccurs="10" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesResponseMessage", propOrder = {
    "messageList"
})
public class SalesResponseMessage
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<SalesResponseMessage.MessageList> messageList;

    /**
     * Gets the value of the messageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the messageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMessageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesResponseMessage.MessageList }
     * 
     * 
     */
    public List<SalesResponseMessage.MessageList> getMessageList() {
        if (messageList == null) {
            messageList = new ArrayList<SalesResponseMessage.MessageList>();
        }
        return this.messageList;
    }

    /**
     * Sets the value of the messageList property.
     * 
     * @param messageList
     *     allowed object is
     *     {@link SalesResponseMessage.MessageList }
     *     
     */
    public void setMessageList(List<SalesResponseMessage.MessageList> messageList) {
        this.messageList = messageList;
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
            List<SalesResponseMessage.MessageList> theMessageList;
            theMessageList = (((this.messageList!= null)&&(!this.messageList.isEmpty()))?this.getMessageList():null);
            strategy.appendField(locator, this, "messageList", buffer, theMessageList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesResponseMessage)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesResponseMessage that = ((SalesResponseMessage) object);
        {
            List<SalesResponseMessage.MessageList> lhsMessageList;
            lhsMessageList = (((this.messageList!= null)&&(!this.messageList.isEmpty()))?this.getMessageList():null);
            List<SalesResponseMessage.MessageList> rhsMessageList;
            rhsMessageList = (((that.messageList!= null)&&(!that.messageList.isEmpty()))?that.getMessageList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "messageList", lhsMessageList), LocatorUtils.property(thatLocator, "messageList", rhsMessageList), lhsMessageList, rhsMessageList)) {
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
     *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}ResponseMessage">
     *       &lt;sequence>
     *         &lt;element name="relatedMessageList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RelatedMessage" maxOccurs="10" minOccurs="0"/>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "relatedMessageList"
    })
    public static class MessageList
        extends ResponseMessage
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected List<RelatedMessage> relatedMessageList;

        /**
         * Gets the value of the relatedMessageList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the relatedMessageList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getRelatedMessageList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link RelatedMessage }
         * 
         * 
         */
        public List<RelatedMessage> getRelatedMessageList() {
            if (relatedMessageList == null) {
                relatedMessageList = new ArrayList<RelatedMessage>();
            }
            return this.relatedMessageList;
        }

        /**
         * Sets the value of the relatedMessageList property.
         * 
         * @param relatedMessageList
         *     allowed object is
         *     {@link RelatedMessage }
         *     
         */
        public void setRelatedMessageList(List<RelatedMessage> relatedMessageList) {
            this.relatedMessageList = relatedMessageList;
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
            super.appendFields(locator, buffer, strategy);
            {
                List<RelatedMessage> theRelatedMessageList;
                theRelatedMessageList = (((this.relatedMessageList!= null)&&(!this.relatedMessageList.isEmpty()))?this.getRelatedMessageList():null);
                strategy.appendField(locator, this, "relatedMessageList", buffer, theRelatedMessageList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof SalesResponseMessage.MessageList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            if (!super.equals(thisLocator, thatLocator, object, strategy)) {
                return false;
            }
            final SalesResponseMessage.MessageList that = ((SalesResponseMessage.MessageList) object);
            {
                List<RelatedMessage> lhsRelatedMessageList;
                lhsRelatedMessageList = (((this.relatedMessageList!= null)&&(!this.relatedMessageList.isEmpty()))?this.getRelatedMessageList():null);
                List<RelatedMessage> rhsRelatedMessageList;
                rhsRelatedMessageList = (((that.relatedMessageList!= null)&&(!that.relatedMessageList.isEmpty()))?that.getRelatedMessageList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "relatedMessageList", lhsRelatedMessageList), LocatorUtils.property(thatLocator, "relatedMessageList", rhsRelatedMessageList), lhsRelatedMessageList, rhsRelatedMessageList)) {
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
