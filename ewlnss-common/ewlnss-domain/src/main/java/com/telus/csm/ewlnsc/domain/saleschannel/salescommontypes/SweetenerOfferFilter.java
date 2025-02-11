
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for SweetenerOfferFilter complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SweetenerOfferFilter">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="selectedAccessoryOfferList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccessoryOfferHeader" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SweetenerOfferFilter", propOrder = {
    "selectedAccessoryOfferList"
})
public class SweetenerOfferFilter
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<AccessoryOfferHeader> selectedAccessoryOfferList;

    /**
     * Gets the value of the selectedAccessoryOfferList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the selectedAccessoryOfferList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSelectedAccessoryOfferList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryOfferHeader }
     * 
     * 
     */
    public List<AccessoryOfferHeader> getSelectedAccessoryOfferList() {
        if (selectedAccessoryOfferList == null) {
            selectedAccessoryOfferList = new ArrayList<AccessoryOfferHeader>();
        }
        return this.selectedAccessoryOfferList;
    }

    /**
     * Sets the value of the selectedAccessoryOfferList property.
     * 
     * @param selectedAccessoryOfferList
     *     allowed object is
     *     {@link AccessoryOfferHeader }
     *     
     */
    public void setSelectedAccessoryOfferList(List<AccessoryOfferHeader> selectedAccessoryOfferList) {
        this.selectedAccessoryOfferList = selectedAccessoryOfferList;
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
            List<AccessoryOfferHeader> theSelectedAccessoryOfferList;
            theSelectedAccessoryOfferList = (((this.selectedAccessoryOfferList!= null)&&(!this.selectedAccessoryOfferList.isEmpty()))?this.getSelectedAccessoryOfferList():null);
            strategy.appendField(locator, this, "selectedAccessoryOfferList", buffer, theSelectedAccessoryOfferList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SweetenerOfferFilter)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SweetenerOfferFilter that = ((SweetenerOfferFilter) object);
        {
            List<AccessoryOfferHeader> lhsSelectedAccessoryOfferList;
            lhsSelectedAccessoryOfferList = (((this.selectedAccessoryOfferList!= null)&&(!this.selectedAccessoryOfferList.isEmpty()))?this.getSelectedAccessoryOfferList():null);
            List<AccessoryOfferHeader> rhsSelectedAccessoryOfferList;
            rhsSelectedAccessoryOfferList = (((that.selectedAccessoryOfferList!= null)&&(!that.selectedAccessoryOfferList.isEmpty()))?that.getSelectedAccessoryOfferList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedAccessoryOfferList", lhsSelectedAccessoryOfferList), LocatorUtils.property(thatLocator, "selectedAccessoryOfferList", rhsSelectedAccessoryOfferList), lhsSelectedAccessoryOfferList, rhsSelectedAccessoryOfferList)) {
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
