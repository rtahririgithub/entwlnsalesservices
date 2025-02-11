
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for ContractCustomization complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ContractCustomization">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="forcedContractStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="forcedCommitmentMonthCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ContractCustomization", propOrder = {
    "forcedContractStartDate",
    "forcedCommitmentMonthCount"
})
public class ContractCustomization
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date forcedContractStartDate;
    protected BigInteger forcedCommitmentMonthCount;

    /**
     * Gets the value of the forcedContractStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getForcedContractStartDate() {
        return forcedContractStartDate;
    }

    /**
     * Sets the value of the forcedContractStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForcedContractStartDate(Date value) {
        this.forcedContractStartDate = value;
    }

    /**
     * Gets the value of the forcedCommitmentMonthCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getForcedCommitmentMonthCount() {
        return forcedCommitmentMonthCount;
    }

    /**
     * Sets the value of the forcedCommitmentMonthCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setForcedCommitmentMonthCount(BigInteger value) {
        this.forcedCommitmentMonthCount = value;
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
            Date theForcedContractStartDate;
            theForcedContractStartDate = this.getForcedContractStartDate();
            strategy.appendField(locator, this, "forcedContractStartDate", buffer, theForcedContractStartDate);
        }
        {
            BigInteger theForcedCommitmentMonthCount;
            theForcedCommitmentMonthCount = this.getForcedCommitmentMonthCount();
            strategy.appendField(locator, this, "forcedCommitmentMonthCount", buffer, theForcedCommitmentMonthCount);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ContractCustomization)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ContractCustomization that = ((ContractCustomization) object);
        {
            Date lhsForcedContractStartDate;
            lhsForcedContractStartDate = this.getForcedContractStartDate();
            Date rhsForcedContractStartDate;
            rhsForcedContractStartDate = that.getForcedContractStartDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "forcedContractStartDate", lhsForcedContractStartDate), LocatorUtils.property(thatLocator, "forcedContractStartDate", rhsForcedContractStartDate), lhsForcedContractStartDate, rhsForcedContractStartDate)) {
                return false;
            }
        }
        {
            BigInteger lhsForcedCommitmentMonthCount;
            lhsForcedCommitmentMonthCount = this.getForcedCommitmentMonthCount();
            BigInteger rhsForcedCommitmentMonthCount;
            rhsForcedCommitmentMonthCount = that.getForcedCommitmentMonthCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "forcedCommitmentMonthCount", lhsForcedCommitmentMonthCount), LocatorUtils.property(thatLocator, "forcedCommitmentMonthCount", rhsForcedCommitmentMonthCount), lhsForcedCommitmentMonthCount, rhsForcedCommitmentMonthCount)) {
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
