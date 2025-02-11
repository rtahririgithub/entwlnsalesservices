
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * To define equipment warranty summary
 * 
 * <p>Java class for EquipmentWarrantySummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipmentWarrantySummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="handsetSerialNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="handsetProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warrantyContractId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="warrantyStartDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="warrantyExpiryDate" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="warrantyVendorCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warrantyProductCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warrantyServiceCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="warrantyPaymentMethodCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EquipmentWarrantySummary", propOrder = {
    "handsetSerialNum",
    "handsetProductCd",
    "warrantyContractId",
    "warrantyStartDate",
    "warrantyExpiryDate",
    "warrantyVendorCd",
    "warrantyProductCd",
    "warrantyServiceCd",
    "warrantyPaymentMethodCd"
})
public class EquipmentWarrantySummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String handsetSerialNum;
    protected String handsetProductCd;
    @XmlElement(required = true)
    protected String warrantyContractId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date warrantyStartDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlSchemaType(name = "date")
    protected Date warrantyExpiryDate;
    protected String warrantyVendorCd;
    protected String warrantyProductCd;
    protected String warrantyServiceCd;
    protected String warrantyPaymentMethodCd;

    /**
     * Gets the value of the handsetSerialNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetSerialNum() {
        return handsetSerialNum;
    }

    /**
     * Sets the value of the handsetSerialNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetSerialNum(String value) {
        this.handsetSerialNum = value;
    }

    /**
     * Gets the value of the handsetProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetProductCd() {
        return handsetProductCd;
    }

    /**
     * Sets the value of the handsetProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetProductCd(String value) {
        this.handsetProductCd = value;
    }

    /**
     * Gets the value of the warrantyContractId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantyContractId() {
        return warrantyContractId;
    }

    /**
     * Sets the value of the warrantyContractId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyContractId(String value) {
        this.warrantyContractId = value;
    }

    /**
     * Gets the value of the warrantyStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getWarrantyStartDate() {
        return warrantyStartDate;
    }

    /**
     * Sets the value of the warrantyStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyStartDate(Date value) {
        this.warrantyStartDate = value;
    }

    /**
     * Gets the value of the warrantyExpiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getWarrantyExpiryDate() {
        return warrantyExpiryDate;
    }

    /**
     * Sets the value of the warrantyExpiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyExpiryDate(Date value) {
        this.warrantyExpiryDate = value;
    }

    /**
     * Gets the value of the warrantyVendorCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantyVendorCd() {
        return warrantyVendorCd;
    }

    /**
     * Sets the value of the warrantyVendorCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyVendorCd(String value) {
        this.warrantyVendorCd = value;
    }

    /**
     * Gets the value of the warrantyProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantyProductCd() {
        return warrantyProductCd;
    }

    /**
     * Sets the value of the warrantyProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyProductCd(String value) {
        this.warrantyProductCd = value;
    }

    /**
     * Gets the value of the warrantyServiceCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantyServiceCd() {
        return warrantyServiceCd;
    }

    /**
     * Sets the value of the warrantyServiceCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyServiceCd(String value) {
        this.warrantyServiceCd = value;
    }

    /**
     * Gets the value of the warrantyPaymentMethodCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWarrantyPaymentMethodCd() {
        return warrantyPaymentMethodCd;
    }

    /**
     * Sets the value of the warrantyPaymentMethodCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWarrantyPaymentMethodCd(String value) {
        this.warrantyPaymentMethodCd = value;
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
            String theHandsetSerialNum;
            theHandsetSerialNum = this.getHandsetSerialNum();
            strategy.appendField(locator, this, "handsetSerialNum", buffer, theHandsetSerialNum);
        }
        {
            String theHandsetProductCd;
            theHandsetProductCd = this.getHandsetProductCd();
            strategy.appendField(locator, this, "handsetProductCd", buffer, theHandsetProductCd);
        }
        {
            String theWarrantyContractId;
            theWarrantyContractId = this.getWarrantyContractId();
            strategy.appendField(locator, this, "warrantyContractId", buffer, theWarrantyContractId);
        }
        {
            Date theWarrantyStartDate;
            theWarrantyStartDate = this.getWarrantyStartDate();
            strategy.appendField(locator, this, "warrantyStartDate", buffer, theWarrantyStartDate);
        }
        {
            Date theWarrantyExpiryDate;
            theWarrantyExpiryDate = this.getWarrantyExpiryDate();
            strategy.appendField(locator, this, "warrantyExpiryDate", buffer, theWarrantyExpiryDate);
        }
        {
            String theWarrantyVendorCd;
            theWarrantyVendorCd = this.getWarrantyVendorCd();
            strategy.appendField(locator, this, "warrantyVendorCd", buffer, theWarrantyVendorCd);
        }
        {
            String theWarrantyProductCd;
            theWarrantyProductCd = this.getWarrantyProductCd();
            strategy.appendField(locator, this, "warrantyProductCd", buffer, theWarrantyProductCd);
        }
        {
            String theWarrantyServiceCd;
            theWarrantyServiceCd = this.getWarrantyServiceCd();
            strategy.appendField(locator, this, "warrantyServiceCd", buffer, theWarrantyServiceCd);
        }
        {
            String theWarrantyPaymentMethodCd;
            theWarrantyPaymentMethodCd = this.getWarrantyPaymentMethodCd();
            strategy.appendField(locator, this, "warrantyPaymentMethodCd", buffer, theWarrantyPaymentMethodCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EquipmentWarrantySummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EquipmentWarrantySummary that = ((EquipmentWarrantySummary) object);
        {
            String lhsHandsetSerialNum;
            lhsHandsetSerialNum = this.getHandsetSerialNum();
            String rhsHandsetSerialNum;
            rhsHandsetSerialNum = that.getHandsetSerialNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetSerialNum", lhsHandsetSerialNum), LocatorUtils.property(thatLocator, "handsetSerialNum", rhsHandsetSerialNum), lhsHandsetSerialNum, rhsHandsetSerialNum)) {
                return false;
            }
        }
        {
            String lhsHandsetProductCd;
            lhsHandsetProductCd = this.getHandsetProductCd();
            String rhsHandsetProductCd;
            rhsHandsetProductCd = that.getHandsetProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetProductCd", lhsHandsetProductCd), LocatorUtils.property(thatLocator, "handsetProductCd", rhsHandsetProductCd), lhsHandsetProductCd, rhsHandsetProductCd)) {
                return false;
            }
        }
        {
            String lhsWarrantyContractId;
            lhsWarrantyContractId = this.getWarrantyContractId();
            String rhsWarrantyContractId;
            rhsWarrantyContractId = that.getWarrantyContractId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyContractId", lhsWarrantyContractId), LocatorUtils.property(thatLocator, "warrantyContractId", rhsWarrantyContractId), lhsWarrantyContractId, rhsWarrantyContractId)) {
                return false;
            }
        }
        {
            Date lhsWarrantyStartDate;
            lhsWarrantyStartDate = this.getWarrantyStartDate();
            Date rhsWarrantyStartDate;
            rhsWarrantyStartDate = that.getWarrantyStartDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyStartDate", lhsWarrantyStartDate), LocatorUtils.property(thatLocator, "warrantyStartDate", rhsWarrantyStartDate), lhsWarrantyStartDate, rhsWarrantyStartDate)) {
                return false;
            }
        }
        {
            Date lhsWarrantyExpiryDate;
            lhsWarrantyExpiryDate = this.getWarrantyExpiryDate();
            Date rhsWarrantyExpiryDate;
            rhsWarrantyExpiryDate = that.getWarrantyExpiryDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyExpiryDate", lhsWarrantyExpiryDate), LocatorUtils.property(thatLocator, "warrantyExpiryDate", rhsWarrantyExpiryDate), lhsWarrantyExpiryDate, rhsWarrantyExpiryDate)) {
                return false;
            }
        }
        {
            String lhsWarrantyVendorCd;
            lhsWarrantyVendorCd = this.getWarrantyVendorCd();
            String rhsWarrantyVendorCd;
            rhsWarrantyVendorCd = that.getWarrantyVendorCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyVendorCd", lhsWarrantyVendorCd), LocatorUtils.property(thatLocator, "warrantyVendorCd", rhsWarrantyVendorCd), lhsWarrantyVendorCd, rhsWarrantyVendorCd)) {
                return false;
            }
        }
        {
            String lhsWarrantyProductCd;
            lhsWarrantyProductCd = this.getWarrantyProductCd();
            String rhsWarrantyProductCd;
            rhsWarrantyProductCd = that.getWarrantyProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyProductCd", lhsWarrantyProductCd), LocatorUtils.property(thatLocator, "warrantyProductCd", rhsWarrantyProductCd), lhsWarrantyProductCd, rhsWarrantyProductCd)) {
                return false;
            }
        }
        {
            String lhsWarrantyServiceCd;
            lhsWarrantyServiceCd = this.getWarrantyServiceCd();
            String rhsWarrantyServiceCd;
            rhsWarrantyServiceCd = that.getWarrantyServiceCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyServiceCd", lhsWarrantyServiceCd), LocatorUtils.property(thatLocator, "warrantyServiceCd", rhsWarrantyServiceCd), lhsWarrantyServiceCd, rhsWarrantyServiceCd)) {
                return false;
            }
        }
        {
            String lhsWarrantyPaymentMethodCd;
            lhsWarrantyPaymentMethodCd = this.getWarrantyPaymentMethodCd();
            String rhsWarrantyPaymentMethodCd;
            rhsWarrantyPaymentMethodCd = that.getWarrantyPaymentMethodCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "warrantyPaymentMethodCd", lhsWarrantyPaymentMethodCd), LocatorUtils.property(thatLocator, "warrantyPaymentMethodCd", rhsWarrantyPaymentMethodCd), lhsWarrantyPaymentMethodCd, rhsWarrantyPaymentMethodCd)) {
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
