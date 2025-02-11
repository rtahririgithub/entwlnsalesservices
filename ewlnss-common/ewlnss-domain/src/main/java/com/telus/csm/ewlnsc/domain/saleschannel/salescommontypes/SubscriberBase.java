
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for SubscriberBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="phoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="subscriberStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberBase", propOrder = {
    "phoneNumber",
    "subscriberId",
    "subscriberStatusCd",
    "customerId"
})
@XmlSeeAlso({
    SubscriberBenefitSummaryType.class,
    SubscriberDataSharingSummary.class,
    SubscriberPricePlanType.class
})
public class SubscriberBase
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String phoneNumber;
    protected String subscriberId;
    protected String subscriberStatusCd;
    protected String customerId;

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the subscriberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberId() {
        return subscriberId;
    }

    /**
     * Sets the value of the subscriberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberId(String value) {
        this.subscriberId = value;
    }

    /**
     * Gets the value of the subscriberStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubscriberStatusCd() {
        return subscriberStatusCd;
    }

    /**
     * Sets the value of the subscriberStatusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubscriberStatusCd(String value) {
        this.subscriberStatusCd = value;
    }

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
            String thePhoneNumber;
            thePhoneNumber = this.getPhoneNumber();
            strategy.appendField(locator, this, "phoneNumber", buffer, thePhoneNumber);
        }
        {
            String theSubscriberId;
            theSubscriberId = this.getSubscriberId();
            strategy.appendField(locator, this, "subscriberId", buffer, theSubscriberId);
        }
        {
            String theSubscriberStatusCd;
            theSubscriberStatusCd = this.getSubscriberStatusCd();
            strategy.appendField(locator, this, "subscriberStatusCd", buffer, theSubscriberStatusCd);
        }
        {
            String theCustomerId;
            theCustomerId = this.getCustomerId();
            strategy.appendField(locator, this, "customerId", buffer, theCustomerId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SubscriberBase)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SubscriberBase that = ((SubscriberBase) object);
        {
            String lhsPhoneNumber;
            lhsPhoneNumber = this.getPhoneNumber();
            String rhsPhoneNumber;
            rhsPhoneNumber = that.getPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumber", lhsPhoneNumber), LocatorUtils.property(thatLocator, "phoneNumber", rhsPhoneNumber), lhsPhoneNumber, rhsPhoneNumber)) {
                return false;
            }
        }
        {
            String lhsSubscriberId;
            lhsSubscriberId = this.getSubscriberId();
            String rhsSubscriberId;
            rhsSubscriberId = that.getSubscriberId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberId", lhsSubscriberId), LocatorUtils.property(thatLocator, "subscriberId", rhsSubscriberId), lhsSubscriberId, rhsSubscriberId)) {
                return false;
            }
        }
        {
            String lhsSubscriberStatusCd;
            lhsSubscriberStatusCd = this.getSubscriberStatusCd();
            String rhsSubscriberStatusCd;
            rhsSubscriberStatusCd = that.getSubscriberStatusCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subscriberStatusCd", lhsSubscriberStatusCd), LocatorUtils.property(thatLocator, "subscriberStatusCd", rhsSubscriberStatusCd), lhsSubscriberStatusCd, rhsSubscriberStatusCd)) {
                return false;
            }
        }
        {
            String lhsCustomerId;
            lhsCustomerId = this.getCustomerId();
            String rhsCustomerId;
            rhsCustomerId = that.getCustomerId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerId", lhsCustomerId), LocatorUtils.property(thatLocator, "customerId", rhsCustomerId), lhsCustomerId, rhsCustomerId)) {
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
