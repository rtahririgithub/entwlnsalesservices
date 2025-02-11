
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
 * <p>Java class for AccessoryOfferProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOfferProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryOfferProductSummary">
 *       &lt;sequence>
 *         &lt;element name="printToReceipt" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryPrintToReceipt" minOccurs="0"/>
 *         &lt;element name="giftList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryGift" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="discountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}AccessoryDiscount" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOfferProduct", propOrder = {
    "printToReceipt",
    "giftList",
    "discountList"
})
public class AccessoryOfferProduct
    extends AccessoryOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected AccessoryPrintToReceipt printToReceipt;
    protected List<AccessoryGift> giftList;
    protected List<AccessoryDiscount> discountList;

    /**
     * Gets the value of the printToReceipt property.
     * 
     * @return
     *     possible object is
     *     {@link AccessoryPrintToReceipt }
     *     
     */
    public AccessoryPrintToReceipt getPrintToReceipt() {
        return printToReceipt;
    }

    /**
     * Sets the value of the printToReceipt property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccessoryPrintToReceipt }
     *     
     */
    public void setPrintToReceipt(AccessoryPrintToReceipt value) {
        this.printToReceipt = value;
    }

    /**
     * Gets the value of the giftList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the giftList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGiftList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccessoryGift }
     * 
     * 
     */
    public List<AccessoryGift> getGiftList() {
        if (giftList == null) {
            giftList = new ArrayList<AccessoryGift>();
        }
        return this.giftList;
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
     * {@link AccessoryDiscount }
     * 
     * 
     */
    public List<AccessoryDiscount> getDiscountList() {
        if (discountList == null) {
            discountList = new ArrayList<AccessoryDiscount>();
        }
        return this.discountList;
    }

    /**
     * Sets the value of the giftList property.
     * 
     * @param giftList
     *     allowed object is
     *     {@link AccessoryGift }
     *     
     */
    public void setGiftList(List<AccessoryGift> giftList) {
        this.giftList = giftList;
    }

    /**
     * Sets the value of the discountList property.
     * 
     * @param discountList
     *     allowed object is
     *     {@link AccessoryDiscount }
     *     
     */
    public void setDiscountList(List<AccessoryDiscount> discountList) {
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
            AccessoryPrintToReceipt thePrintToReceipt;
            thePrintToReceipt = this.getPrintToReceipt();
            strategy.appendField(locator, this, "printToReceipt", buffer, thePrintToReceipt);
        }
        {
            List<AccessoryGift> theGiftList;
            theGiftList = (((this.giftList!= null)&&(!this.giftList.isEmpty()))?this.getGiftList():null);
            strategy.appendField(locator, this, "giftList", buffer, theGiftList);
        }
        {
            List<AccessoryDiscount> theDiscountList;
            theDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            strategy.appendField(locator, this, "discountList", buffer, theDiscountList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOfferProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final AccessoryOfferProduct that = ((AccessoryOfferProduct) object);
        {
            AccessoryPrintToReceipt lhsPrintToReceipt;
            lhsPrintToReceipt = this.getPrintToReceipt();
            AccessoryPrintToReceipt rhsPrintToReceipt;
            rhsPrintToReceipt = that.getPrintToReceipt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printToReceipt", lhsPrintToReceipt), LocatorUtils.property(thatLocator, "printToReceipt", rhsPrintToReceipt), lhsPrintToReceipt, rhsPrintToReceipt)) {
                return false;
            }
        }
        {
            List<AccessoryGift> lhsGiftList;
            lhsGiftList = (((this.giftList!= null)&&(!this.giftList.isEmpty()))?this.getGiftList():null);
            List<AccessoryGift> rhsGiftList;
            rhsGiftList = (((that.giftList!= null)&&(!that.giftList.isEmpty()))?that.getGiftList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "giftList", lhsGiftList), LocatorUtils.property(thatLocator, "giftList", rhsGiftList), lhsGiftList, rhsGiftList)) {
                return false;
            }
        }
        {
            List<AccessoryDiscount> lhsDiscountList;
            lhsDiscountList = (((this.discountList!= null)&&(!this.discountList.isEmpty()))?this.getDiscountList():null);
            List<AccessoryDiscount> rhsDiscountList;
            rhsDiscountList = (((that.discountList!= null)&&(!that.discountList.isEmpty()))?that.getDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "discountList", lhsDiscountList), LocatorUtils.property(thatLocator, "discountList", rhsDiscountList), lhsDiscountList, rhsDiscountList)) {
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
