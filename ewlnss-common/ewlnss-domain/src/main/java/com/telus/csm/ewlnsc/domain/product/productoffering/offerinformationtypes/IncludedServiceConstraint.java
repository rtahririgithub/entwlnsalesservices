
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
 * <p>Java class for IncludedServiceConstraint complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IncludedServiceConstraint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="salesCategory" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}SalesCategory" minOccurs="0"/>
 *         &lt;element name="serviceTypeCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="minItemQty" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="maxItemQty" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IncludedServiceConstraint", propOrder = {
    "salesCategory",
    "serviceTypeCodeList",
    "minItemQty",
    "maxItemQty"
})
public class IncludedServiceConstraint
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected SalesCategory salesCategory;
    protected List<String> serviceTypeCodeList;
    @XmlElement(required = true)
    protected BigInteger minItemQty;
    protected BigInteger maxItemQty;

    /**
     * Gets the value of the salesCategory property.
     * 
     * @return
     *     possible object is
     *     {@link SalesCategory }
     *     
     */
    public SalesCategory getSalesCategory() {
        return salesCategory;
    }

    /**
     * Sets the value of the salesCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesCategory }
     *     
     */
    public void setSalesCategory(SalesCategory value) {
        this.salesCategory = value;
    }

    /**
     * Gets the value of the serviceTypeCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceTypeCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceTypeCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServiceTypeCodeList() {
        if (serviceTypeCodeList == null) {
            serviceTypeCodeList = new ArrayList<String>();
        }
        return this.serviceTypeCodeList;
    }

    /**
     * Gets the value of the minItemQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMinItemQty() {
        return minItemQty;
    }

    /**
     * Sets the value of the minItemQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMinItemQty(BigInteger value) {
        this.minItemQty = value;
    }

    /**
     * Gets the value of the maxItemQty property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMaxItemQty() {
        return maxItemQty;
    }

    /**
     * Sets the value of the maxItemQty property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMaxItemQty(BigInteger value) {
        this.maxItemQty = value;
    }

    /**
     * Sets the value of the serviceTypeCodeList property.
     * 
     * @param serviceTypeCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCodeList(List<String> serviceTypeCodeList) {
        this.serviceTypeCodeList = serviceTypeCodeList;
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
            SalesCategory theSalesCategory;
            theSalesCategory = this.getSalesCategory();
            strategy.appendField(locator, this, "salesCategory", buffer, theSalesCategory);
        }
        {
            List<String> theServiceTypeCodeList;
            theServiceTypeCodeList = (((this.serviceTypeCodeList!= null)&&(!this.serviceTypeCodeList.isEmpty()))?this.getServiceTypeCodeList():null);
            strategy.appendField(locator, this, "serviceTypeCodeList", buffer, theServiceTypeCodeList);
        }
        {
            BigInteger theMinItemQty;
            theMinItemQty = this.getMinItemQty();
            strategy.appendField(locator, this, "minItemQty", buffer, theMinItemQty);
        }
        {
            BigInteger theMaxItemQty;
            theMaxItemQty = this.getMaxItemQty();
            strategy.appendField(locator, this, "maxItemQty", buffer, theMaxItemQty);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof IncludedServiceConstraint)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final IncludedServiceConstraint that = ((IncludedServiceConstraint) object);
        {
            SalesCategory lhsSalesCategory;
            lhsSalesCategory = this.getSalesCategory();
            SalesCategory rhsSalesCategory;
            rhsSalesCategory = that.getSalesCategory();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesCategory", lhsSalesCategory), LocatorUtils.property(thatLocator, "salesCategory", rhsSalesCategory), lhsSalesCategory, rhsSalesCategory)) {
                return false;
            }
        }
        {
            List<String> lhsServiceTypeCodeList;
            lhsServiceTypeCodeList = (((this.serviceTypeCodeList!= null)&&(!this.serviceTypeCodeList.isEmpty()))?this.getServiceTypeCodeList():null);
            List<String> rhsServiceTypeCodeList;
            rhsServiceTypeCodeList = (((that.serviceTypeCodeList!= null)&&(!that.serviceTypeCodeList.isEmpty()))?that.getServiceTypeCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTypeCodeList", lhsServiceTypeCodeList), LocatorUtils.property(thatLocator, "serviceTypeCodeList", rhsServiceTypeCodeList), lhsServiceTypeCodeList, rhsServiceTypeCodeList)) {
                return false;
            }
        }
        {
            BigInteger lhsMinItemQty;
            lhsMinItemQty = this.getMinItemQty();
            BigInteger rhsMinItemQty;
            rhsMinItemQty = that.getMinItemQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minItemQty", lhsMinItemQty), LocatorUtils.property(thatLocator, "minItemQty", rhsMinItemQty), lhsMinItemQty, rhsMinItemQty)) {
                return false;
            }
        }
        {
            BigInteger lhsMaxItemQty;
            lhsMaxItemQty = this.getMaxItemQty();
            BigInteger rhsMaxItemQty;
            rhsMaxItemQty = that.getMaxItemQty();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "maxItemQty", lhsMaxItemQty), LocatorUtils.property(thatLocator, "maxItemQty", rhsMaxItemQty), lhsMaxItemQty, rhsMaxItemQty)) {
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
