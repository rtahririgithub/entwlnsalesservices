
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for AccessoryItemReceiptDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryItemReceiptDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accessoryProductList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccessoryProduct" maxOccurs="100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryItemReceiptDetail", propOrder = {
    "accessoryProductList"
})
public class AccessoryItemReceiptDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<AccessoryProduct> accessoryProductList;

    /**
     * Gets the value of the accessoryProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accessoryProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccessoryProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryProduct }
     * 
     * 
     */
    public List<AccessoryProduct> getAccessoryProductList() {
        if (accessoryProductList == null) {
            accessoryProductList = new ArrayList<AccessoryProduct>();
        }
        return this.accessoryProductList;
    }

    /**
     * Sets the value of the accessoryProductList property.
     * 
     * @param accessoryProductList
     *     allowed object is
     *     {@link AccessoryProduct }
     *     
     */
    public void setAccessoryProductList(List<AccessoryProduct> accessoryProductList) {
        this.accessoryProductList = accessoryProductList;
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
            List<AccessoryProduct> theAccessoryProductList;
            theAccessoryProductList = (((this.accessoryProductList!= null)&&(!this.accessoryProductList.isEmpty()))?this.getAccessoryProductList():null);
            strategy.appendField(locator, this, "accessoryProductList", buffer, theAccessoryProductList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryItemReceiptDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryItemReceiptDetail that = ((AccessoryItemReceiptDetail) object);
        {
            List<AccessoryProduct> lhsAccessoryProductList;
            lhsAccessoryProductList = (((this.accessoryProductList!= null)&&(!this.accessoryProductList.isEmpty()))?this.getAccessoryProductList():null);
            List<AccessoryProduct> rhsAccessoryProductList;
            rhsAccessoryProductList = (((that.accessoryProductList!= null)&&(!that.accessoryProductList.isEmpty()))?that.getAccessoryProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accessoryProductList", lhsAccessoryProductList), LocatorUtils.property(thatLocator, "accessoryProductList", rhsAccessoryProductList), lhsAccessoryProductList, rhsAccessoryProductList)) {
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
