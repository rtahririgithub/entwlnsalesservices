
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.CustomerGuarantor;
import com.telus.tmi.xmlschema.xsd.customer.basetypes.creditcommon_v1.PersonalInfo;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for CustomerCreditInformation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerCreditInformation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="creditAddress" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" minOccurs="0"/>
 *         &lt;element name="personalInfo" type="{http://xmlschema.tmi.telus.com/xsd/Customer/BaseTypes/CreditCommon_v1}PersonalInfo" minOccurs="0"/>
 *         &lt;element name="creditIdentification" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PersonalCreditInformation" minOccurs="0"/>
 *         &lt;element name="creditApplicationProvinceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customerGuarantor" type="{http://xmlschema.tmi.telus.com/xsd/Customer/BaseTypes/CreditCommon_v1}CustomerGuarantor" minOccurs="0"/>
 *         &lt;element name="commentTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerCreditInformation", propOrder = {
    "creditAddress",
    "personalInfo",
    "creditIdentification",
    "creditApplicationProvinceCd",
    "customerGuarantor",
    "commentTxt"
})
public class CustomerCreditInformation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Address creditAddress;
    protected PersonalInfo personalInfo;
    protected PersonalCreditInformation creditIdentification;
    protected String creditApplicationProvinceCd;
    protected CustomerGuarantor customerGuarantor;
    protected String commentTxt;

    /**
     * Gets the value of the creditAddress property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getCreditAddress() {
        return creditAddress;
    }

    /**
     * Sets the value of the creditAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setCreditAddress(Address value) {
        this.creditAddress = value;
    }

    /**
     * Gets the value of the personalInfo property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalInfo }
     *     
     */
    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    /**
     * Sets the value of the personalInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalInfo }
     *     
     */
    public void setPersonalInfo(PersonalInfo value) {
        this.personalInfo = value;
    }

    /**
     * Gets the value of the creditIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link PersonalCreditInformation }
     *     
     */
    public PersonalCreditInformation getCreditIdentification() {
        return creditIdentification;
    }

    /**
     * Sets the value of the creditIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link PersonalCreditInformation }
     *     
     */
    public void setCreditIdentification(PersonalCreditInformation value) {
        this.creditIdentification = value;
    }

    /**
     * Gets the value of the creditApplicationProvinceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCreditApplicationProvinceCd() {
        return creditApplicationProvinceCd;
    }

    /**
     * Sets the value of the creditApplicationProvinceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCreditApplicationProvinceCd(String value) {
        this.creditApplicationProvinceCd = value;
    }

    /**
     * Gets the value of the customerGuarantor property.
     * 
     * @return
     *     possible object is
     *     {@link CustomerGuarantor }
     *     
     */
    public CustomerGuarantor getCustomerGuarantor() {
        return customerGuarantor;
    }

    /**
     * Sets the value of the customerGuarantor property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomerGuarantor }
     *     
     */
    public void setCustomerGuarantor(CustomerGuarantor value) {
        this.customerGuarantor = value;
    }

    /**
     * Gets the value of the commentTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommentTxt() {
        return commentTxt;
    }

    /**
     * Sets the value of the commentTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentTxt(String value) {
        this.commentTxt = value;
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
            Address theCreditAddress;
            theCreditAddress = this.getCreditAddress();
            strategy.appendField(locator, this, "creditAddress", buffer, theCreditAddress);
        }
        {
            PersonalInfo thePersonalInfo;
            thePersonalInfo = this.getPersonalInfo();
            strategy.appendField(locator, this, "personalInfo", buffer, thePersonalInfo);
        }
        {
            PersonalCreditInformation theCreditIdentification;
            theCreditIdentification = this.getCreditIdentification();
            strategy.appendField(locator, this, "creditIdentification", buffer, theCreditIdentification);
        }
        {
            String theCreditApplicationProvinceCd;
            theCreditApplicationProvinceCd = this.getCreditApplicationProvinceCd();
            strategy.appendField(locator, this, "creditApplicationProvinceCd", buffer, theCreditApplicationProvinceCd);
        }
        {
            CustomerGuarantor theCustomerGuarantor;
            theCustomerGuarantor = this.getCustomerGuarantor();
            strategy.appendField(locator, this, "customerGuarantor", buffer, theCustomerGuarantor);
        }
        {
            String theCommentTxt;
            theCommentTxt = this.getCommentTxt();
            strategy.appendField(locator, this, "commentTxt", buffer, theCommentTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CustomerCreditInformation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CustomerCreditInformation that = ((CustomerCreditInformation) object);
        {
            Address lhsCreditAddress;
            lhsCreditAddress = this.getCreditAddress();
            Address rhsCreditAddress;
            rhsCreditAddress = that.getCreditAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditAddress", lhsCreditAddress), LocatorUtils.property(thatLocator, "creditAddress", rhsCreditAddress), lhsCreditAddress, rhsCreditAddress)) {
                return false;
            }
        }
        {
            PersonalInfo lhsPersonalInfo;
            lhsPersonalInfo = this.getPersonalInfo();
            PersonalInfo rhsPersonalInfo;
            rhsPersonalInfo = that.getPersonalInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "personalInfo", lhsPersonalInfo), LocatorUtils.property(thatLocator, "personalInfo", rhsPersonalInfo), lhsPersonalInfo, rhsPersonalInfo)) {
                return false;
            }
        }
        {
            PersonalCreditInformation lhsCreditIdentification;
            lhsCreditIdentification = this.getCreditIdentification();
            PersonalCreditInformation rhsCreditIdentification;
            rhsCreditIdentification = that.getCreditIdentification();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditIdentification", lhsCreditIdentification), LocatorUtils.property(thatLocator, "creditIdentification", rhsCreditIdentification), lhsCreditIdentification, rhsCreditIdentification)) {
                return false;
            }
        }
        {
            String lhsCreditApplicationProvinceCd;
            lhsCreditApplicationProvinceCd = this.getCreditApplicationProvinceCd();
            String rhsCreditApplicationProvinceCd;
            rhsCreditApplicationProvinceCd = that.getCreditApplicationProvinceCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "creditApplicationProvinceCd", lhsCreditApplicationProvinceCd), LocatorUtils.property(thatLocator, "creditApplicationProvinceCd", rhsCreditApplicationProvinceCd), lhsCreditApplicationProvinceCd, rhsCreditApplicationProvinceCd)) {
                return false;
            }
        }
        {
            CustomerGuarantor lhsCustomerGuarantor;
            lhsCustomerGuarantor = this.getCustomerGuarantor();
            CustomerGuarantor rhsCustomerGuarantor;
            rhsCustomerGuarantor = that.getCustomerGuarantor();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerGuarantor", lhsCustomerGuarantor), LocatorUtils.property(thatLocator, "customerGuarantor", rhsCustomerGuarantor), lhsCustomerGuarantor, rhsCustomerGuarantor)) {
                return false;
            }
        }
        {
            String lhsCommentTxt;
            lhsCommentTxt = this.getCommentTxt();
            String rhsCommentTxt;
            rhsCommentTxt = that.getCommentTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "commentTxt", lhsCommentTxt), LocatorUtils.property(thatLocator, "commentTxt", rhsCommentTxt), lhsCommentTxt, rhsCommentTxt)) {
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
