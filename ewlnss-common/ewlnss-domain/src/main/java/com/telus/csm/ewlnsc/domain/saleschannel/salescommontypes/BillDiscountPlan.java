
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for BillDiscountPlan complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BillDiscountPlan">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="discountCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discountDescription" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList" minOccurs="0"/>
 *         &lt;element name="discountTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="discountAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="discountMonthCount" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="discountGroupCdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BillDiscountPlan", propOrder = {
    "discountCode",
    "discountDescription",
    "discountTypeCode",
    "discountAmount",
    "discountMonthCount",
    "discountGroupCdList"
})
public class BillDiscountPlan
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String discountCode;
    protected MultilingualDescriptionList discountDescription;
    @XmlElement(required = true)
    protected String discountTypeCode;
    protected double discountAmount;
    protected long discountMonthCount;
    protected List<String> discountGroupCdList;

    /**
     * Gets the value of the discountCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountCode() {
        return discountCode;
    }

    /**
     * Sets the value of the discountCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountCode(String value) {
        this.discountCode = value;
    }

    /**
     * Gets the value of the discountDescription property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getDiscountDescription() {
        return discountDescription;
    }

    /**
     * Sets the value of the discountDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setDiscountDescription(MultilingualDescriptionList value) {
        this.discountDescription = value;
    }

    /**
     * Gets the value of the discountTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDiscountTypeCode() {
        return discountTypeCode;
    }

    /**
     * Sets the value of the discountTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountTypeCode(String value) {
        this.discountTypeCode = value;
    }

    /**
     * Gets the value of the discountAmount property.
     * 
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Sets the value of the discountAmount property.
     * 
     */
    public void setDiscountAmount(double value) {
        this.discountAmount = value;
    }

    /**
     * Gets the value of the discountMonthCount property.
     * 
     */
    public long getDiscountMonthCount() {
        return discountMonthCount;
    }

    /**
     * Sets the value of the discountMonthCount property.
     * 
     */
    public void setDiscountMonthCount(long value) {
        this.discountMonthCount = value;
    }

    /**
     * Gets the value of the discountGroupCdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the discountGroupCdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscountGroupCdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDiscountGroupCdList() {
        if (discountGroupCdList == null) {
            discountGroupCdList = new ArrayList<String>();
        }
        return this.discountGroupCdList;
    }

    /**
     * Sets the value of the discountGroupCdList property.
     * 
     * @param discountGroupCdList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDiscountGroupCdList(List<String> discountGroupCdList) {
        this.discountGroupCdList = discountGroupCdList;
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
            String theDiscountCode;
            theDiscountCode = this.getDiscountCode();
            strategy.appendField(locator, this, "discountCode", buffer, theDiscountCode);
        }
        {
            MultilingualDescriptionList theDiscountDescription;
            theDiscountDescription = this.getDiscountDescription();
            strategy.appendField(locator, this, "discountDescription", buffer, theDiscountDescription);
        }
        {
            String theDiscountTypeCode;
            theDiscountTypeCode = this.getDiscountTypeCode();
            strategy.appendField(locator, this, "discountTypeCode", buffer, theDiscountTypeCode);
        }
        {
            double theDiscountAmount;
            theDiscountAmount = (true?this.getDiscountAmount(): 0.0D);
            strategy.appendField(locator, this, "discountAmount", buffer, theDiscountAmount);
        }
        {
            long theDiscountMonthCount;
            theDiscountMonthCount = (true?this.getDiscountMonthCount(): 0L);
            strategy.appendField(locator, this, "discountMonthCount", buffer, theDiscountMonthCount);
        }
        {
            List<String> theDiscountGroupCdList;
            theDiscountGroupCdList = (((this.discountGroupCdList!= null)&&(!this.discountGroupCdList.isEmpty()))?this.getDiscountGroupCdList():null);
            strategy.appendField(locator, this, "discountGroupCdList", buffer, theDiscountGroupCdList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BillDiscountPlan)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BillDiscountPlan that = ((BillDiscountPlan) object);
        {
            String lhsDiscountCode;
            lhsDiscountCode = this.getDiscountCode();
            String rhsDiscountCode;
            rhsDiscountCode = that.getDiscountCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountCode", lhsDiscountCode), LocatorUtils.property(thatLocator, "discountCode", rhsDiscountCode), lhsDiscountCode, rhsDiscountCode)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsDiscountDescription;
            lhsDiscountDescription = this.getDiscountDescription();
            MultilingualDescriptionList rhsDiscountDescription;
            rhsDiscountDescription = that.getDiscountDescription();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountDescription", lhsDiscountDescription), LocatorUtils.property(thatLocator, "discountDescription", rhsDiscountDescription), lhsDiscountDescription, rhsDiscountDescription)) {
                return false;
            }
        }
        {
            String lhsDiscountTypeCode;
            lhsDiscountTypeCode = this.getDiscountTypeCode();
            String rhsDiscountTypeCode;
            rhsDiscountTypeCode = that.getDiscountTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountTypeCode", lhsDiscountTypeCode), LocatorUtils.property(thatLocator, "discountTypeCode", rhsDiscountTypeCode), lhsDiscountTypeCode, rhsDiscountTypeCode)) {
                return false;
            }
        }
        {
            double lhsDiscountAmount;
            lhsDiscountAmount = (true?this.getDiscountAmount(): 0.0D);
            double rhsDiscountAmount;
            rhsDiscountAmount = (true?that.getDiscountAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountAmount", lhsDiscountAmount), LocatorUtils.property(thatLocator, "discountAmount", rhsDiscountAmount), lhsDiscountAmount, rhsDiscountAmount)) {
                return false;
            }
        }
        {
            long lhsDiscountMonthCount;
            lhsDiscountMonthCount = (true?this.getDiscountMonthCount(): 0L);
            long rhsDiscountMonthCount;
            rhsDiscountMonthCount = (true?that.getDiscountMonthCount(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountMonthCount", lhsDiscountMonthCount), LocatorUtils.property(thatLocator, "discountMonthCount", rhsDiscountMonthCount), lhsDiscountMonthCount, rhsDiscountMonthCount)) {
                return false;
            }
        }
        {
            List<String> lhsDiscountGroupCdList;
            lhsDiscountGroupCdList = (((this.discountGroupCdList!= null)&&(!this.discountGroupCdList.isEmpty()))?this.getDiscountGroupCdList():null);
            List<String> rhsDiscountGroupCdList;
            rhsDiscountGroupCdList = (((that.discountGroupCdList!= null)&&(!that.discountGroupCdList.isEmpty()))?that.getDiscountGroupCdList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountGroupCdList", lhsDiscountGroupCdList), LocatorUtils.property(thatLocator, "discountGroupCdList", rhsDiscountGroupCdList), lhsDiscountGroupCdList, rhsDiscountGroupCdList)) {
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
