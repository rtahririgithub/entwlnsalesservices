
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
 * <p>Java class for ServiceTransactionAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceTransactionAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="transactionActionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceTransactionAction", propOrder = {
    "serviceCode",
    "transactionActionCode"
})
public class ServiceTransactionAction
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceCode;
    @XmlElement(required = true)
    protected String transactionActionCode;

    /**
     * Gets the value of the serviceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceCode() {
        return serviceCode;
    }

    /**
     * Sets the value of the serviceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCode(String value) {
        this.serviceCode = value;
    }

    /**
     * Gets the value of the transactionActionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransactionActionCode() {
        return transactionActionCode;
    }

    /**
     * Sets the value of the transactionActionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransactionActionCode(String value) {
        this.transactionActionCode = value;
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
            String theServiceCode;
            theServiceCode = this.getServiceCode();
            strategy.appendField(locator, this, "serviceCode", buffer, theServiceCode);
        }
        {
            String theTransactionActionCode;
            theTransactionActionCode = this.getTransactionActionCode();
            strategy.appendField(locator, this, "transactionActionCode", buffer, theTransactionActionCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceTransactionAction)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceTransactionAction that = ((ServiceTransactionAction) object);
        {
            String lhsServiceCode;
            lhsServiceCode = this.getServiceCode();
            String rhsServiceCode;
            rhsServiceCode = that.getServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCode", lhsServiceCode), LocatorUtils.property(thatLocator, "serviceCode", rhsServiceCode), lhsServiceCode, rhsServiceCode)) {
                return false;
            }
        }
        {
            String lhsTransactionActionCode;
            lhsTransactionActionCode = this.getTransactionActionCode();
            String rhsTransactionActionCode;
            rhsTransactionActionCode = that.getTransactionActionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionActionCode", lhsTransactionActionCode), LocatorUtils.property(thatLocator, "transactionActionCode", rhsTransactionActionCode), lhsTransactionActionCode, rhsTransactionActionCode)) {
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
