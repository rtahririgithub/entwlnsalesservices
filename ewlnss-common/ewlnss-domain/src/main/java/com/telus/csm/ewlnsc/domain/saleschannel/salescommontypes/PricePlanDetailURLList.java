
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
 * <p>Java class for PricePlanDetailURLList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PricePlanDetailURLList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="pricePlanDetailURLItem" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PricePlanDetailURLItem" maxOccurs="10"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PricePlanDetailURLList", propOrder = {
    "pricePlanDetailURLItem"
})
public class PricePlanDetailURLList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<PricePlanDetailURLItem> pricePlanDetailURLItem;

    /**
     * Gets the value of the pricePlanDetailURLItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanDetailURLItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanDetailURLItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PricePlanDetailURLItem }
     * 
     * 
     */
    public List<PricePlanDetailURLItem> getPricePlanDetailURLItem() {
        if (pricePlanDetailURLItem == null) {
            pricePlanDetailURLItem = new ArrayList<PricePlanDetailURLItem>();
        }
        return this.pricePlanDetailURLItem;
    }

    /**
     * Sets the value of the pricePlanDetailURLItem property.
     * 
     * @param pricePlanDetailURLItem
     *     allowed object is
     *     {@link PricePlanDetailURLItem }
     *     
     */
    public void setPricePlanDetailURLItem(List<PricePlanDetailURLItem> pricePlanDetailURLItem) {
        this.pricePlanDetailURLItem = pricePlanDetailURLItem;
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
            List<PricePlanDetailURLItem> thePricePlanDetailURLItem;
            thePricePlanDetailURLItem = (((this.pricePlanDetailURLItem!= null)&&(!this.pricePlanDetailURLItem.isEmpty()))?this.getPricePlanDetailURLItem():null);
            strategy.appendField(locator, this, "pricePlanDetailURLItem", buffer, thePricePlanDetailURLItem);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PricePlanDetailURLList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PricePlanDetailURLList that = ((PricePlanDetailURLList) object);
        {
            List<PricePlanDetailURLItem> lhsPricePlanDetailURLItem;
            lhsPricePlanDetailURLItem = (((this.pricePlanDetailURLItem!= null)&&(!this.pricePlanDetailURLItem.isEmpty()))?this.getPricePlanDetailURLItem():null);
            List<PricePlanDetailURLItem> rhsPricePlanDetailURLItem;
            rhsPricePlanDetailURLItem = (((that.pricePlanDetailURLItem!= null)&&(!that.pricePlanDetailURLItem.isEmpty()))?that.getPricePlanDetailURLItem():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanDetailURLItem", lhsPricePlanDetailURLItem), LocatorUtils.property(thatLocator, "pricePlanDetailURLItem", rhsPricePlanDetailURLItem), lhsPricePlanDetailURLItem, rhsPricePlanDetailURLItem)) {
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
