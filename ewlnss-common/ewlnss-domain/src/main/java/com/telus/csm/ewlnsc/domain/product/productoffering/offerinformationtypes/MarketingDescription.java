
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for MarketingDescription complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MarketingDescription">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="displayOrderNum" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="descriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MarketingDescription", propOrder = {
    "displayOrderNum",
    "descriptionList"
})
public class MarketingDescription
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected BigInteger displayOrderNum;
    @XmlElement(required = true)
    protected List<Description> descriptionList;

    /**
     * Gets the value of the displayOrderNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDisplayOrderNum() {
        return displayOrderNum;
    }

    /**
     * Sets the value of the displayOrderNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDisplayOrderNum(BigInteger value) {
        this.displayOrderNum = value;
    }

    /**
     * Gets the value of the descriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the descriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getDescriptionList() {
        if (descriptionList == null) {
            descriptionList = new ArrayList<Description>();
        }
        return this.descriptionList;
    }

    /**
     * Sets the value of the descriptionList property.
     * 
     * @param descriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescriptionList(List<Description> descriptionList) {
        this.descriptionList = descriptionList;
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
            BigInteger theDisplayOrderNum;
            theDisplayOrderNum = this.getDisplayOrderNum();
            strategy.appendField(locator, this, "displayOrderNum", buffer, theDisplayOrderNum);
        }
        {
            List<Description> theDescriptionList;
            theDescriptionList = (((this.descriptionList!= null)&&(!this.descriptionList.isEmpty()))?this.getDescriptionList():null);
            strategy.appendField(locator, this, "descriptionList", buffer, theDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MarketingDescription)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final MarketingDescription that = ((MarketingDescription) object);
        {
            BigInteger lhsDisplayOrderNum;
            lhsDisplayOrderNum = this.getDisplayOrderNum();
            BigInteger rhsDisplayOrderNum;
            rhsDisplayOrderNum = that.getDisplayOrderNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "displayOrderNum", lhsDisplayOrderNum), LocatorUtils.property(thatLocator, "displayOrderNum", rhsDisplayOrderNum), lhsDisplayOrderNum, rhsDisplayOrderNum)) {
                return false;
            }
        }
        {
            List<Description> lhsDescriptionList;
            lhsDescriptionList = (((this.descriptionList!= null)&&(!this.descriptionList.isEmpty()))?this.getDescriptionList():null);
            List<Description> rhsDescriptionList;
            rhsDescriptionList = (((that.descriptionList!= null)&&(!that.descriptionList.isEmpty()))?that.getDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "descriptionList", lhsDescriptionList), LocatorUtils.property(thatLocator, "descriptionList", rhsDescriptionList), lhsDescriptionList, rhsDescriptionList)) {
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
