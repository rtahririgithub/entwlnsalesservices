
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
 * To define corporate account summary
 * 
 * <p>Java class for CorporateAccountSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorporateAccountSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="subsidiaryEntityId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingAccountNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="billingAccountName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="billingAccountStatusCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorporateAccountSummary", propOrder = {
    "subsidiaryEntityId",
    "billingAccountNum",
    "billingAccountName",
    "billingAccountStatusCd"
})
public class CorporateAccountSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String subsidiaryEntityId;
    @XmlElement(required = true)
    protected String billingAccountNum;
    protected String billingAccountName;
    protected String billingAccountStatusCd;

    /**
     * Gets the value of the subsidiaryEntityId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubsidiaryEntityId() {
        return subsidiaryEntityId;
    }

    /**
     * Sets the value of the subsidiaryEntityId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubsidiaryEntityId(String value) {
        this.subsidiaryEntityId = value;
    }

    /**
     * Gets the value of the billingAccountNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNum() {
        return billingAccountNum;
    }

    /**
     * Sets the value of the billingAccountNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNum(String value) {
        this.billingAccountNum = value;
    }

    /**
     * Gets the value of the billingAccountName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountName() {
        return billingAccountName;
    }

    /**
     * Sets the value of the billingAccountName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountName(String value) {
        this.billingAccountName = value;
    }

    /**
     * Gets the value of the billingAccountStatusCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountStatusCd() {
        return billingAccountStatusCd;
    }

    /**
     * Sets the value of the billingAccountStatusCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountStatusCd(String value) {
        this.billingAccountStatusCd = value;
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
            String theSubsidiaryEntityId;
            theSubsidiaryEntityId = this.getSubsidiaryEntityId();
            strategy.appendField(locator, this, "subsidiaryEntityId", buffer, theSubsidiaryEntityId);
        }
        {
            String theBillingAccountNum;
            theBillingAccountNum = this.getBillingAccountNum();
            strategy.appendField(locator, this, "billingAccountNum", buffer, theBillingAccountNum);
        }
        {
            String theBillingAccountName;
            theBillingAccountName = this.getBillingAccountName();
            strategy.appendField(locator, this, "billingAccountName", buffer, theBillingAccountName);
        }
        {
            String theBillingAccountStatusCd;
            theBillingAccountStatusCd = this.getBillingAccountStatusCd();
            strategy.appendField(locator, this, "billingAccountStatusCd", buffer, theBillingAccountStatusCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CorporateAccountSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CorporateAccountSummary that = ((CorporateAccountSummary) object);
        {
            String lhsSubsidiaryEntityId;
            lhsSubsidiaryEntityId = this.getSubsidiaryEntityId();
            String rhsSubsidiaryEntityId;
            rhsSubsidiaryEntityId = that.getSubsidiaryEntityId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subsidiaryEntityId", lhsSubsidiaryEntityId), LocatorUtils.property(thatLocator, "subsidiaryEntityId", rhsSubsidiaryEntityId), lhsSubsidiaryEntityId, rhsSubsidiaryEntityId)) {
                return false;
            }
        }
        {
            String lhsBillingAccountNum;
            lhsBillingAccountNum = this.getBillingAccountNum();
            String rhsBillingAccountNum;
            rhsBillingAccountNum = that.getBillingAccountNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNum", lhsBillingAccountNum), LocatorUtils.property(thatLocator, "billingAccountNum", rhsBillingAccountNum), lhsBillingAccountNum, rhsBillingAccountNum)) {
                return false;
            }
        }
        {
            String lhsBillingAccountName;
            lhsBillingAccountName = this.getBillingAccountName();
            String rhsBillingAccountName;
            rhsBillingAccountName = that.getBillingAccountName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountName", lhsBillingAccountName), LocatorUtils.property(thatLocator, "billingAccountName", rhsBillingAccountName), lhsBillingAccountName, rhsBillingAccountName)) {
                return false;
            }
        }
        {
            String lhsBillingAccountStatusCd;
            lhsBillingAccountStatusCd = this.getBillingAccountStatusCd();
            String rhsBillingAccountStatusCd;
            rhsBillingAccountStatusCd = that.getBillingAccountStatusCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountStatusCd", lhsBillingAccountStatusCd), LocatorUtils.property(thatLocator, "billingAccountStatusCd", rhsBillingAccountStatusCd), lhsBillingAccountStatusCd, rhsBillingAccountStatusCd)) {
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
