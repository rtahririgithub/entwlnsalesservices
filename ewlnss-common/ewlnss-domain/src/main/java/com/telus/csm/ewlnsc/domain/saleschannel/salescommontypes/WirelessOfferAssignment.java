
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.OfferPlanCustomization;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.ProgramCustomization;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessOfferAssignment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferAssignment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="customerOfferId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerCustomization" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}ProgramCustomization" minOccurs="0"/>
 *         &lt;element name="offerItemPlanCustomizationList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}OfferPlanCustomization" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessOfferAssignment", propOrder = {
    "customerOfferId",
    "offerCustomization",
    "offerItemPlanCustomizationList"
})
public class WirelessOfferAssignment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String customerOfferId;
    protected ProgramCustomization offerCustomization;
    protected List<OfferPlanCustomization> offerItemPlanCustomizationList;

    /**
     * Gets the value of the customerOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomerOfferId() {
        return customerOfferId;
    }

    /**
     * Sets the value of the customerOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomerOfferId(String value) {
        this.customerOfferId = value;
    }

    /**
     * Gets the value of the offerCustomization property.
     * 
     * @return
     *     possible object is
     *     {@link ProgramCustomization }
     *     
     */
    public ProgramCustomization getOfferCustomization() {
        return offerCustomization;
    }

    /**
     * Sets the value of the offerCustomization property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgramCustomization }
     *     
     */
    public void setOfferCustomization(ProgramCustomization value) {
        this.offerCustomization = value;
    }

    /**
     * Gets the value of the offerItemPlanCustomizationList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerItemPlanCustomizationList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferItemPlanCustomizationList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferPlanCustomization }
     * 
     * 
     */
    public List<OfferPlanCustomization> getOfferItemPlanCustomizationList() {
        if (offerItemPlanCustomizationList == null) {
            offerItemPlanCustomizationList = new ArrayList<OfferPlanCustomization>();
        }
        return this.offerItemPlanCustomizationList;
    }

    /**
     * Sets the value of the offerItemPlanCustomizationList property.
     * 
     * @param offerItemPlanCustomizationList
     *     allowed object is
     *     {@link OfferPlanCustomization }
     *     
     */
    public void setOfferItemPlanCustomizationList(List<OfferPlanCustomization> offerItemPlanCustomizationList) {
        this.offerItemPlanCustomizationList = offerItemPlanCustomizationList;
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
            String theCustomerOfferId;
            theCustomerOfferId = this.getCustomerOfferId();
            strategy.appendField(locator, this, "customerOfferId", buffer, theCustomerOfferId);
        }
        {
            ProgramCustomization theOfferCustomization;
            theOfferCustomization = this.getOfferCustomization();
            strategy.appendField(locator, this, "offerCustomization", buffer, theOfferCustomization);
        }
        {
            List<OfferPlanCustomization> theOfferItemPlanCustomizationList;
            theOfferItemPlanCustomizationList = (((this.offerItemPlanCustomizationList!= null)&&(!this.offerItemPlanCustomizationList.isEmpty()))?this.getOfferItemPlanCustomizationList():null);
            strategy.appendField(locator, this, "offerItemPlanCustomizationList", buffer, theOfferItemPlanCustomizationList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferAssignment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessOfferAssignment that = ((WirelessOfferAssignment) object);
        {
            String lhsCustomerOfferId;
            lhsCustomerOfferId = this.getCustomerOfferId();
            String rhsCustomerOfferId;
            rhsCustomerOfferId = that.getCustomerOfferId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "customerOfferId", lhsCustomerOfferId), LocatorUtils.property(thatLocator, "customerOfferId", rhsCustomerOfferId), lhsCustomerOfferId, rhsCustomerOfferId)) {
                return false;
            }
        }
        {
            ProgramCustomization lhsOfferCustomization;
            lhsOfferCustomization = this.getOfferCustomization();
            ProgramCustomization rhsOfferCustomization;
            rhsOfferCustomization = that.getOfferCustomization();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCustomization", lhsOfferCustomization), LocatorUtils.property(thatLocator, "offerCustomization", rhsOfferCustomization), lhsOfferCustomization, rhsOfferCustomization)) {
                return false;
            }
        }
        {
            List<OfferPlanCustomization> lhsOfferItemPlanCustomizationList;
            lhsOfferItemPlanCustomizationList = (((this.offerItemPlanCustomizationList!= null)&&(!this.offerItemPlanCustomizationList.isEmpty()))?this.getOfferItemPlanCustomizationList():null);
            List<OfferPlanCustomization> rhsOfferItemPlanCustomizationList;
            rhsOfferItemPlanCustomizationList = (((that.offerItemPlanCustomizationList!= null)&&(!that.offerItemPlanCustomizationList.isEmpty()))?that.getOfferItemPlanCustomizationList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerItemPlanCustomizationList", lhsOfferItemPlanCustomizationList), LocatorUtils.property(thatLocator, "offerItemPlanCustomizationList", rhsOfferItemPlanCustomizationList), lhsOfferItemPlanCustomizationList, rhsOfferItemPlanCustomizationList)) {
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
