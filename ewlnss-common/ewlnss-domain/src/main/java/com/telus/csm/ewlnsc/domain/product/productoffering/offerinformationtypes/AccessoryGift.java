
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for AccessoryGift complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryGift">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="systemId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}systemId"/>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="validDayCount" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="giftAmount" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="giftDiscountPaymentPlanList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}GiftDiscountPaymentPlan" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryGift", propOrder = {
    "typeCode",
    "systemId",
    "productCode",
    "validDayCount",
    "giftAmount",
    "giftDiscountPaymentPlanList"
})
public class AccessoryGift
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String typeCode;
    @XmlElement(required = true)
    protected String systemId;
    @XmlElement(required = true)
    protected String productCode;
    protected BigInteger validDayCount;
    protected Double giftAmount;
    protected List<GiftDiscountPaymentPlan> giftDiscountPaymentPlanList;

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the systemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemId() {
        return systemId;
    }

    /**
     * Sets the value of the systemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemId(String value) {
        this.systemId = value;
    }

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the validDayCount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getValidDayCount() {
        return validDayCount;
    }

    /**
     * Sets the value of the validDayCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setValidDayCount(BigInteger value) {
        this.validDayCount = value;
    }

    /**
     * Gets the value of the giftAmount property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGiftAmount() {
        return giftAmount;
    }

    /**
     * Sets the value of the giftAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGiftAmount(Double value) {
        this.giftAmount = value;
    }

    /**
     * Gets the value of the giftDiscountPaymentPlanList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the giftDiscountPaymentPlanList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGiftDiscountPaymentPlanList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link GiftDiscountPaymentPlan }
     * 
     * 
     */
    public List<GiftDiscountPaymentPlan> getGiftDiscountPaymentPlanList() {
        if (giftDiscountPaymentPlanList == null) {
            giftDiscountPaymentPlanList = new ArrayList<GiftDiscountPaymentPlan>();
        }
        return this.giftDiscountPaymentPlanList;
    }

    /**
     * Sets the value of the giftDiscountPaymentPlanList property.
     * 
     * @param giftDiscountPaymentPlanList
     *     allowed object is
     *     {@link GiftDiscountPaymentPlan }
     *     
     */
    public void setGiftDiscountPaymentPlanList(List<GiftDiscountPaymentPlan> giftDiscountPaymentPlanList) {
        this.giftDiscountPaymentPlanList = giftDiscountPaymentPlanList;
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
            String theTypeCode;
            theTypeCode = this.getTypeCode();
            strategy.appendField(locator, this, "typeCode", buffer, theTypeCode);
        }
        {
            String theSystemId;
            theSystemId = this.getSystemId();
            strategy.appendField(locator, this, "systemId", buffer, theSystemId);
        }
        {
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        {
            BigInteger theValidDayCount;
            theValidDayCount = this.getValidDayCount();
            strategy.appendField(locator, this, "validDayCount", buffer, theValidDayCount);
        }
        {
            Double theGiftAmount;
            theGiftAmount = this.getGiftAmount();
            strategy.appendField(locator, this, "giftAmount", buffer, theGiftAmount);
        }
        {
            List<GiftDiscountPaymentPlan> theGiftDiscountPaymentPlanList;
            theGiftDiscountPaymentPlanList = (((this.giftDiscountPaymentPlanList!= null)&&(!this.giftDiscountPaymentPlanList.isEmpty()))?this.getGiftDiscountPaymentPlanList():null);
            strategy.appendField(locator, this, "giftDiscountPaymentPlanList", buffer, theGiftDiscountPaymentPlanList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryGift)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryGift that = ((AccessoryGift) object);
        {
            String lhsTypeCode;
            lhsTypeCode = this.getTypeCode();
            String rhsTypeCode;
            rhsTypeCode = that.getTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "typeCode", lhsTypeCode), LocatorUtils.property(thatLocator, "typeCode", rhsTypeCode), lhsTypeCode, rhsTypeCode)) {
                return false;
            }
        }
        {
            String lhsSystemId;
            lhsSystemId = this.getSystemId();
            String rhsSystemId;
            rhsSystemId = that.getSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "systemId", lhsSystemId), LocatorUtils.property(thatLocator, "systemId", rhsSystemId), lhsSystemId, rhsSystemId)) {
                return false;
            }
        }
        {
            String lhsProductCode;
            lhsProductCode = this.getProductCode();
            String rhsProductCode;
            rhsProductCode = that.getProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCode", lhsProductCode), LocatorUtils.property(thatLocator, "productCode", rhsProductCode), lhsProductCode, rhsProductCode)) {
                return false;
            }
        }
        {
            BigInteger lhsValidDayCount;
            lhsValidDayCount = this.getValidDayCount();
            BigInteger rhsValidDayCount;
            rhsValidDayCount = that.getValidDayCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "validDayCount", lhsValidDayCount), LocatorUtils.property(thatLocator, "validDayCount", rhsValidDayCount), lhsValidDayCount, rhsValidDayCount)) {
                return false;
            }
        }
        {
            Double lhsGiftAmount;
            lhsGiftAmount = this.getGiftAmount();
            Double rhsGiftAmount;
            rhsGiftAmount = that.getGiftAmount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "giftAmount", lhsGiftAmount), LocatorUtils.property(thatLocator, "giftAmount", rhsGiftAmount), lhsGiftAmount, rhsGiftAmount)) {
                return false;
            }
        }
        {
            List<GiftDiscountPaymentPlan> lhsGiftDiscountPaymentPlanList;
            lhsGiftDiscountPaymentPlanList = (((this.giftDiscountPaymentPlanList!= null)&&(!this.giftDiscountPaymentPlanList.isEmpty()))?this.getGiftDiscountPaymentPlanList():null);
            List<GiftDiscountPaymentPlan> rhsGiftDiscountPaymentPlanList;
            rhsGiftDiscountPaymentPlanList = (((that.giftDiscountPaymentPlanList!= null)&&(!that.giftDiscountPaymentPlanList.isEmpty()))?that.getGiftDiscountPaymentPlanList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "giftDiscountPaymentPlanList", lhsGiftDiscountPaymentPlanList), LocatorUtils.property(thatLocator, "giftDiscountPaymentPlanList", rhsGiftDiscountPaymentPlanList), lhsGiftDiscountPaymentPlanList, rhsGiftDiscountPaymentPlanList)) {
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
