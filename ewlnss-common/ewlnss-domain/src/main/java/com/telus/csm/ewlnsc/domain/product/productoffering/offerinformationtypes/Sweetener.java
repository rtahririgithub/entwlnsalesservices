
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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
 * Defines a single a Sweeetner offer.
 * 
 * <p>Java class for Sweetener complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Sweetener">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Offer">
 *       &lt;sequence>
 *         &lt;element name="eligibleOfferIdentifierList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferIdentifier" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="stackableInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Sweetener", propOrder = {
    "eligibleOfferIdentifierList",
    "stackableInd"
})
public class Sweetener
    extends Offer
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<OfferIdentifier> eligibleOfferIdentifierList;
    protected Boolean stackableInd;

    /**
     * Gets the value of the eligibleOfferIdentifierList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibleOfferIdentifierList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibleOfferIdentifierList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferIdentifier }
     * 
     * 
     */
    public List<OfferIdentifier> getEligibleOfferIdentifierList() {
        if (eligibleOfferIdentifierList == null) {
            eligibleOfferIdentifierList = new ArrayList<OfferIdentifier>();
        }
        return this.eligibleOfferIdentifierList;
    }

    /**
     * Gets the value of the stackableInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStackableInd() {
        return stackableInd;
    }

    /**
     * Sets the value of the stackableInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStackableInd(Boolean value) {
        this.stackableInd = value;
    }

    /**
     * Sets the value of the eligibleOfferIdentifierList property.
     * 
     * @param eligibleOfferIdentifierList
     *     allowed object is
     *     {@link OfferIdentifier }
     *     
     */
    public void setEligibleOfferIdentifierList(List<OfferIdentifier> eligibleOfferIdentifierList) {
        this.eligibleOfferIdentifierList = eligibleOfferIdentifierList;
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
        super.appendFields(locator, buffer, strategy);
        {
            List<OfferIdentifier> theEligibleOfferIdentifierList;
            theEligibleOfferIdentifierList = (((this.eligibleOfferIdentifierList!= null)&&(!this.eligibleOfferIdentifierList.isEmpty()))?this.getEligibleOfferIdentifierList():null);
            strategy.appendField(locator, this, "eligibleOfferIdentifierList", buffer, theEligibleOfferIdentifierList);
        }
        {
            Boolean theStackableInd;
            theStackableInd = this.isStackableInd();
            strategy.appendField(locator, this, "stackableInd", buffer, theStackableInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Sweetener)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Sweetener that = ((Sweetener) object);
        {
            List<OfferIdentifier> lhsEligibleOfferIdentifierList;
            lhsEligibleOfferIdentifierList = (((this.eligibleOfferIdentifierList!= null)&&(!this.eligibleOfferIdentifierList.isEmpty()))?this.getEligibleOfferIdentifierList():null);
            List<OfferIdentifier> rhsEligibleOfferIdentifierList;
            rhsEligibleOfferIdentifierList = (((that.eligibleOfferIdentifierList!= null)&&(!that.eligibleOfferIdentifierList.isEmpty()))?that.getEligibleOfferIdentifierList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleOfferIdentifierList", lhsEligibleOfferIdentifierList), LocatorUtils.property(thatLocator, "eligibleOfferIdentifierList", rhsEligibleOfferIdentifierList), lhsEligibleOfferIdentifierList, rhsEligibleOfferIdentifierList)) {
                return false;
            }
        }
        {
            Boolean lhsStackableInd;
            lhsStackableInd = this.isStackableInd();
            Boolean rhsStackableInd;
            rhsStackableInd = that.isStackableInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stackableInd", lhsStackableInd), LocatorUtils.property(thatLocator, "stackableInd", rhsStackableInd), lhsStackableInd, rhsStackableInd)) {
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
