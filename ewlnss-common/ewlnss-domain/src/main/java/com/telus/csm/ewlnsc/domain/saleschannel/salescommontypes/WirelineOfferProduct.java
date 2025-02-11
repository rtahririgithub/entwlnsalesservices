
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.AddOn;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Discount;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.Feature;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.WirelineOfferProductSummary;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelineOfferProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineOfferProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelineOfferProductSummary">
 *       &lt;sequence>
 *         &lt;element name="wirelineEquipmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesWirelineEquipment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="addOn" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AddOn" minOccurs="0"/>
 *         &lt;element name="discountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Discount" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="feature" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Feature" minOccurs="0"/>
 *         &lt;element name="sellableInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="recontractEligibilityInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineOfferProduct", propOrder = {
    "wirelineEquipmentList",
    "addOn",
    "discountList",
    "feature",
    "sellableInd",
    "recontractEligibilityInd"
})
public class WirelineOfferProduct
    extends WirelineOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<SalesWirelineEquipment> wirelineEquipmentList;
    protected AddOn addOn;
    protected List<Discount> discountList;
    protected Feature feature;
    protected Boolean sellableInd;
    protected Boolean recontractEligibilityInd;

    /**
     * Gets the value of the wirelineEquipmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelineEquipmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelineEquipmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SalesWirelineEquipment }
     * 
     * 
     */
    public List<SalesWirelineEquipment> getWirelineEquipmentList() {
        if (wirelineEquipmentList == null) {
            wirelineEquipmentList = new ArrayList<SalesWirelineEquipment>();
        }
        return this.wirelineEquipmentList;
    }

    /**
     * Gets the value of the addOn property.
     * 
     * @return
     *     possible object is
     *     {@link AddOn }
     *     
     */
    public AddOn getAddOn() {
        return addOn;
    }

    /**
     * Sets the value of the addOn property.
     * 
     * @param value
     *     allowed object is
     *     {@link AddOn }
     *     
     */
    public void setAddOn(AddOn value) {
        this.addOn = value;
    }

    /**
     * Gets the value of the discountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the discountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Discount }
     * 
     * 
     */
    public List<Discount> getDiscountList() {
        if (discountList == null) {
            discountList = new ArrayList<Discount>();
        }
        return this.discountList;
    }

    /**
     * Gets the value of the feature property.
     * 
     * @return
     *     possible object is
     *     {@link Feature }
     *     
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * Sets the value of the feature property.
     * 
     * @param value
     *     allowed object is
     *     {@link Feature }
     *     
     */
    public void setFeature(Feature value) {
        this.feature = value;
    }

    /**
     * Gets the value of the sellableInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSellableInd() {
        return sellableInd;
    }

    /**
     * Sets the value of the sellableInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSellableInd(Boolean value) {
        this.sellableInd = value;
    }

    /**
     * Gets the value of the recontractEligibilityInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecontractEligibilityInd() {
        return recontractEligibilityInd;
    }

    /**
     * Sets the value of the recontractEligibilityInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecontractEligibilityInd(Boolean value) {
        this.recontractEligibilityInd = value;
    }

    /**
     * Sets the value of the wirelineEquipmentList property.
     * 
     * @param wirelineEquipmentList
     *     allowed object is
     *     {@link SalesWirelineEquipment }
     *     
     */
    public void setWirelineEquipmentList(List<SalesWirelineEquipment> wirelineEquipmentList) {
        this.wirelineEquipmentList = wirelineEquipmentList;
    }

    /**
     * Sets the value of the discountList property.
     * 
     * @param discountList
     *     allowed object is
     *     {@link Discount }
     *     
     */
    public void setDiscountList(List<Discount> discountList) {
        this.discountList = discountList;
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
            List<SalesWirelineEquipment> theWirelineEquipmentList;
            theWirelineEquipmentList = (((this.wirelineEquipmentList!= null)&&(!this.wirelineEquipmentList.isEmpty()))?this.getWirelineEquipmentList():null);
            strategy.appendField(locator, this, "wirelineEquipmentList", buffer, theWirelineEquipmentList);
        }
        {
            AddOn theAddOn;
            theAddOn = this.getAddOn();
            strategy.appendField(locator, this, "addOn", buffer, theAddOn);
        }
        {
            List<Discount> theDiscountList;
            theDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            strategy.appendField(locator, this, "discountList", buffer, theDiscountList);
        }
        {
            Feature theFeature;
            theFeature = this.getFeature();
            strategy.appendField(locator, this, "feature", buffer, theFeature);
        }
        {
            Boolean theSellableInd;
            theSellableInd = this.isSellableInd();
            strategy.appendField(locator, this, "sellableInd", buffer, theSellableInd);
        }
        {
            Boolean theRecontractEligibilityInd;
            theRecontractEligibilityInd = this.isRecontractEligibilityInd();
            strategy.appendField(locator, this, "recontractEligibilityInd", buffer, theRecontractEligibilityInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineOfferProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelineOfferProduct that = ((WirelineOfferProduct) object);
        {
            List<SalesWirelineEquipment> lhsWirelineEquipmentList;
            lhsWirelineEquipmentList = (((this.wirelineEquipmentList!= null)&&(!this.wirelineEquipmentList.isEmpty()))?this.getWirelineEquipmentList():null);
            List<SalesWirelineEquipment> rhsWirelineEquipmentList;
            rhsWirelineEquipmentList = (((that.wirelineEquipmentList!= null)&&(!that.wirelineEquipmentList.isEmpty()))?that.getWirelineEquipmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineEquipmentList", lhsWirelineEquipmentList), LocatorUtils.property(thatLocator, "wirelineEquipmentList", rhsWirelineEquipmentList), lhsWirelineEquipmentList, rhsWirelineEquipmentList)) {
                return false;
            }
        }
        {
            AddOn lhsAddOn;
            lhsAddOn = this.getAddOn();
            AddOn rhsAddOn;
            rhsAddOn = that.getAddOn();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "addOn", lhsAddOn), LocatorUtils.property(thatLocator, "addOn", rhsAddOn), lhsAddOn, rhsAddOn)) {
                return false;
            }
        }
        {
            List<Discount> lhsDiscountList;
            lhsDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            List<Discount> rhsDiscountList;
            rhsDiscountList = (((that.discountList!= null)&&(!that.discountList.isEmpty()))?that.getDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountList", lhsDiscountList), LocatorUtils.property(thatLocator, "discountList", rhsDiscountList), lhsDiscountList, rhsDiscountList)) {
                return false;
            }
        }
        {
            Feature lhsFeature;
            lhsFeature = this.getFeature();
            Feature rhsFeature;
            rhsFeature = that.getFeature();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "feature", lhsFeature), LocatorUtils.property(thatLocator, "feature", rhsFeature), lhsFeature, rhsFeature)) {
                return false;
            }
        }
        {
            Boolean lhsSellableInd;
            lhsSellableInd = this.isSellableInd();
            Boolean rhsSellableInd;
            rhsSellableInd = that.isSellableInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sellableInd", lhsSellableInd), LocatorUtils.property(thatLocator, "sellableInd", rhsSellableInd), lhsSellableInd, rhsSellableInd)) {
                return false;
            }
        }
        {
            Boolean lhsRecontractEligibilityInd;
            lhsRecontractEligibilityInd = this.isRecontractEligibilityInd();
            Boolean rhsRecontractEligibilityInd;
            rhsRecontractEligibilityInd = that.isRecontractEligibilityInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recontractEligibilityInd", lhsRecontractEligibilityInd), LocatorUtils.property(thatLocator, "recontractEligibilityInd", rhsRecontractEligibilityInd), lhsRecontractEligibilityInd, rhsRecontractEligibilityInd)) {
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
