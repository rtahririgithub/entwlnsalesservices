
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
 * <p>Java class for AccessoryDiscount complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryDiscount">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountSubTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="equalOrLessValueInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="itemQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="endDiscountTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="discountType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DiscountType" minOccurs="0"/>
 *         &lt;element name="discountAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="groupList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryGroup" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryDiscount", propOrder = {
    "discountSubTypeCd",
    "equalOrLessValueInd",
    "itemQty",
    "endDiscountTypeCd",
    "discountType",
    "discountAmt",
    "groupList"
})
public class AccessoryDiscount
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String discountSubTypeCd;
    protected Boolean equalOrLessValueInd;
    protected BigInteger itemQty;
    protected String endDiscountTypeCd;
    protected DiscountType discountType;
    protected Double discountAmt;
    @XmlElement(required = true)
    protected List<AccessoryGroup> groupList;

    /**
     * Gets the value of the discountSubTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountSubTypeCd() {
        return discountSubTypeCd;
    }

    /**
     * Sets the value of the discountSubTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountSubTypeCd(String value) {
        this.discountSubTypeCd = value;
    }

    /**
     * Gets the value of the equalOrLessValueInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isEqualOrLessValueInd() {
        return equalOrLessValueInd;
    }

    /**
     * Sets the value of the equalOrLessValueInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setEqualOrLessValueInd(Boolean value) {
        this.equalOrLessValueInd = value;
    }

    /**
     * Gets the value of the itemQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getItemQty() {
        return itemQty;
    }

    /**
     * Sets the value of the itemQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setItemQty(BigInteger value) {
        this.itemQty = value;
    }

    /**
     * Gets the value of the endDiscountTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEndDiscountTypeCd() {
        return endDiscountTypeCd;
    }

    /**
     * Sets the value of the endDiscountTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEndDiscountTypeCd(String value) {
        this.endDiscountTypeCd = value;
    }

    /**
     * Gets the value of the discountType property.
     * 
     * @return
     *     possible object is
     *     {@link DiscountType }
     *     
     */
    public DiscountType getDiscountType() {
        return discountType;
    }

    /**
     * Sets the value of the discountType property.
     * 
     * @param value
     *     allowed object is
     *     {@link DiscountType }
     *     
     */
    public void setDiscountType(DiscountType value) {
        this.discountType = value;
    }

    /**
     * Gets the value of the discountAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDiscountAmt() {
        return discountAmt;
    }

    /**
     * Sets the value of the discountAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDiscountAmt(Double value) {
        this.discountAmt = value;
    }

    /**
     * Gets the value of the groupList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the groupList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGroupList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryGroup }
     * 
     * 
     */
    public List<AccessoryGroup> getGroupList() {
        if (groupList == null) {
            groupList = new ArrayList<AccessoryGroup>();
        }
        return this.groupList;
    }

    /**
     * Sets the value of the groupList property.
     * 
     * @param groupList
     *     allowed object is
     *     {@link AccessoryGroup }
     *     
     */
    public void setGroupList(List<AccessoryGroup> groupList) {
        this.groupList = groupList;
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
            String theDiscountSubTypeCd;
            theDiscountSubTypeCd = this.getDiscountSubTypeCd();
            strategy.appendField(locator, this, "discountSubTypeCd", buffer, theDiscountSubTypeCd);
        }
        {
            Boolean theEqualOrLessValueInd;
            theEqualOrLessValueInd = this.isEqualOrLessValueInd();
            strategy.appendField(locator, this, "equalOrLessValueInd", buffer, theEqualOrLessValueInd);
        }
        {
            BigInteger theItemQty;
            theItemQty = this.getItemQty();
            strategy.appendField(locator, this, "itemQty", buffer, theItemQty);
        }
        {
            String theEndDiscountTypeCd;
            theEndDiscountTypeCd = this.getEndDiscountTypeCd();
            strategy.appendField(locator, this, "endDiscountTypeCd", buffer, theEndDiscountTypeCd);
        }
        {
            DiscountType theDiscountType;
            theDiscountType = this.getDiscountType();
            strategy.appendField(locator, this, "discountType", buffer, theDiscountType);
        }
        {
            Double theDiscountAmt;
            theDiscountAmt = this.getDiscountAmt();
            strategy.appendField(locator, this, "discountAmt", buffer, theDiscountAmt);
        }
        {
            List<AccessoryGroup> theGroupList;
            theGroupList = (((this.groupList!= null)&&(!this.groupList.isEmpty()))?this.getGroupList():null);
            strategy.appendField(locator, this, "groupList", buffer, theGroupList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryDiscount)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryDiscount that = ((AccessoryDiscount) object);
        {
            String lhsDiscountSubTypeCd;
            lhsDiscountSubTypeCd = this.getDiscountSubTypeCd();
            String rhsDiscountSubTypeCd;
            rhsDiscountSubTypeCd = that.getDiscountSubTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountSubTypeCd", lhsDiscountSubTypeCd), LocatorUtils.property(thatLocator, "discountSubTypeCd", rhsDiscountSubTypeCd), lhsDiscountSubTypeCd, rhsDiscountSubTypeCd)) {
                return false;
            }
        }
        {
            Boolean lhsEqualOrLessValueInd;
            lhsEqualOrLessValueInd = this.isEqualOrLessValueInd();
            Boolean rhsEqualOrLessValueInd;
            rhsEqualOrLessValueInd = that.isEqualOrLessValueInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equalOrLessValueInd", lhsEqualOrLessValueInd), LocatorUtils.property(thatLocator, "equalOrLessValueInd", rhsEqualOrLessValueInd), lhsEqualOrLessValueInd, rhsEqualOrLessValueInd)) {
                return false;
            }
        }
        {
            BigInteger lhsItemQty;
            lhsItemQty = this.getItemQty();
            BigInteger rhsItemQty;
            rhsItemQty = that.getItemQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "itemQty", lhsItemQty), LocatorUtils.property(thatLocator, "itemQty", rhsItemQty), lhsItemQty, rhsItemQty)) {
                return false;
            }
        }
        {
            String lhsEndDiscountTypeCd;
            lhsEndDiscountTypeCd = this.getEndDiscountTypeCd();
            String rhsEndDiscountTypeCd;
            rhsEndDiscountTypeCd = that.getEndDiscountTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "endDiscountTypeCd", lhsEndDiscountTypeCd), LocatorUtils.property(thatLocator, "endDiscountTypeCd", rhsEndDiscountTypeCd), lhsEndDiscountTypeCd, rhsEndDiscountTypeCd)) {
                return false;
            }
        }
        {
            DiscountType lhsDiscountType;
            lhsDiscountType = this.getDiscountType();
            DiscountType rhsDiscountType;
            rhsDiscountType = that.getDiscountType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountType", lhsDiscountType), LocatorUtils.property(thatLocator, "discountType", rhsDiscountType), lhsDiscountType, rhsDiscountType)) {
                return false;
            }
        }
        {
            Double lhsDiscountAmt;
            lhsDiscountAmt = this.getDiscountAmt();
            Double rhsDiscountAmt;
            rhsDiscountAmt = that.getDiscountAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountAmt", lhsDiscountAmt), LocatorUtils.property(thatLocator, "discountAmt", rhsDiscountAmt), lhsDiscountAmt, rhsDiscountAmt)) {
                return false;
            }
        }
        {
            List<AccessoryGroup> lhsGroupList;
            lhsGroupList = (((this.groupList!= null)&&(!this.groupList.isEmpty()))?this.getGroupList():null);
            List<AccessoryGroup> rhsGroupList;
            rhsGroupList = (((that.groupList!= null)&&(!that.groupList.isEmpty()))?that.getGroupList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "groupList", lhsGroupList), LocatorUtils.property(thatLocator, "groupList", rhsGroupList), lhsGroupList, rhsGroupList)) {
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
