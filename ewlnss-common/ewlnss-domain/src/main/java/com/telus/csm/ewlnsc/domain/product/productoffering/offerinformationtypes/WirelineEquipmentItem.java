
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

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

import com.telus.csm.ewlnsc.domain.CharacteristicVO;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;


/**
 * <p>Java class for WirelineEquipmentItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineEquipmentItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="materialItemCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="equipmentPurchasePriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="equipmentRentalPriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="equipmentLateReturnPriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="equipmentDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2"/>
 *         &lt;element name="deliveryMethodList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded"/>
 *         &lt;element name="includedInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="purchaseDiscountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}PurchaseDiscount" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="paymentOptionList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}PaymentOption" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineEquipmentItem", propOrder = {
    "materialItemCode",
    "equipmentPurchasePriceAmt",
    "equipmentRentalPriceAmt",
    "equipmentLateReturnPriceAmt",
    "equipmentDescriptionList",
    "deliveryMethodList",
    "includedInd",
    "purchaseDiscountList",
    "paymentOptionList"
})
public class WirelineEquipmentItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String materialItemCode;
    protected Double equipmentPurchasePriceAmt;
    protected Double equipmentRentalPriceAmt;
    protected Double equipmentLateReturnPriceAmt;
    @XmlElement(required = true)
    protected List<Description> equipmentDescriptionList;
    @XmlElement(required = true)
    protected List<String> deliveryMethodList;
    protected boolean includedInd;
    protected List<PurchaseDiscount> purchaseDiscountList;
    protected List<PaymentOption> paymentOptionList;

    //FIFA-SHS : equipment characteristic 
    private List<CharacteristicVO> characteristics; 
    
    /**
     * Gets the value of the materialItemCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMaterialItemCode() {
        return materialItemCode;
    }

    /**
     * Sets the value of the materialItemCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMaterialItemCode(String value) {
        this.materialItemCode = value;
    }

    /**
     * Gets the value of the equipmentPurchasePriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEquipmentPurchasePriceAmt() {
        return equipmentPurchasePriceAmt;
    }

    /**
     * Sets the value of the equipmentPurchasePriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEquipmentPurchasePriceAmt(Double value) {
        this.equipmentPurchasePriceAmt = value;
    }

    /**
     * Gets the value of the equipmentRentalPriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEquipmentRentalPriceAmt() {
        return equipmentRentalPriceAmt;
    }

    /**
     * Sets the value of the equipmentRentalPriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEquipmentRentalPriceAmt(Double value) {
        this.equipmentRentalPriceAmt = value;
    }

    /**
     * Gets the value of the equipmentLateReturnPriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getEquipmentLateReturnPriceAmt() {
        return equipmentLateReturnPriceAmt;
    }

    /**
     * Sets the value of the equipmentLateReturnPriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setEquipmentLateReturnPriceAmt(Double value) {
        this.equipmentLateReturnPriceAmt = value;
    }

    /**
     * Gets the value of the equipmentDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipmentDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipmentDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getEquipmentDescriptionList() {
        if (equipmentDescriptionList == null) {
            equipmentDescriptionList = new ArrayList<Description>();
        }
        return this.equipmentDescriptionList;
    }

    /**
     * Gets the value of the deliveryMethodList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryMethodList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryMethodList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getDeliveryMethodList() {
        if (deliveryMethodList == null) {
            deliveryMethodList = new ArrayList<String>();
        }
        return this.deliveryMethodList;
    }

    /**
     * Gets the value of the includedInd property.
     * 
     */
    public boolean isIncludedInd() {
        return includedInd;
    }

    /**
     * Sets the value of the includedInd property.
     * 
     */
    public void setIncludedInd(boolean value) {
        this.includedInd = value;
    }

    /**
     * Gets the value of the purchaseDiscountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the purchaseDiscountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPurchaseDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PurchaseDiscount }
     * 
     * 
     */
    public List<PurchaseDiscount> getPurchaseDiscountList() {
        if (purchaseDiscountList == null) {
            purchaseDiscountList = new ArrayList<PurchaseDiscount>();
        }
        return this.purchaseDiscountList;
    }

    /**
     * Gets the value of the paymentOptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the paymentOptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaymentOptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PaymentOption }
     * 
     * 
     */
    public List<PaymentOption> getPaymentOptionList() {
        if (paymentOptionList == null) {
            paymentOptionList = new ArrayList<PaymentOption>();
        }
        return this.paymentOptionList;
    }

    /**
     * Sets the value of the equipmentDescriptionList property.
     * 
     * @param equipmentDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setEquipmentDescriptionList(List<Description> equipmentDescriptionList) {
        this.equipmentDescriptionList = equipmentDescriptionList;
    }

    /**
     * Sets the value of the deliveryMethodList property.
     * 
     * @param deliveryMethodList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeliveryMethodList(List<String> deliveryMethodList) {
        this.deliveryMethodList = deliveryMethodList;
    }

    /**
     * Sets the value of the purchaseDiscountList property.
     * 
     * @param purchaseDiscountList
     *     allowed object is
     *     {@link PurchaseDiscount }
     *     
     */
    public void setPurchaseDiscountList(List<PurchaseDiscount> purchaseDiscountList) {
        this.purchaseDiscountList = purchaseDiscountList;
    }

    /**
     * Sets the value of the paymentOptionList property.
     * 
     * @param paymentOptionList
     *     allowed object is
     *     {@link PaymentOption }
     *     
     */
    public void setPaymentOptionList(List<PaymentOption> paymentOptionList) {
        this.paymentOptionList = paymentOptionList;
    }

	public List<CharacteristicVO> getCharacteristics() {
		return characteristics;
	}

	public void setCharacteristics(List<CharacteristicVO> characteristics) {
		this.characteristics = characteristics;
	}
	
	public void addCharacteristic( CharacteristicVO characteristic) {
		if (characteristics==null) {
			characteristics  = new ArrayList<CharacteristicVO>();
		}
		characteristics.add(characteristic);
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
            String theMaterialItemCode;
            theMaterialItemCode = this.getMaterialItemCode();
            strategy.appendField(locator, this, "materialItemCode", buffer, theMaterialItemCode);
        }
        {
            Double theEquipmentPurchasePriceAmt;
            theEquipmentPurchasePriceAmt = this.getEquipmentPurchasePriceAmt();
            strategy.appendField(locator, this, "equipmentPurchasePriceAmt", buffer, theEquipmentPurchasePriceAmt);
        }
        {
            Double theEquipmentRentalPriceAmt;
            theEquipmentRentalPriceAmt = this.getEquipmentRentalPriceAmt();
            strategy.appendField(locator, this, "equipmentRentalPriceAmt", buffer, theEquipmentRentalPriceAmt);
        }
        {
            Double theEquipmentLateReturnPriceAmt;
            theEquipmentLateReturnPriceAmt = this.getEquipmentLateReturnPriceAmt();
            strategy.appendField(locator, this, "equipmentLateReturnPriceAmt", buffer, theEquipmentLateReturnPriceAmt);
        }
        {
            List<Description> theEquipmentDescriptionList;
            theEquipmentDescriptionList = (((this.equipmentDescriptionList!= null)&&(!this.equipmentDescriptionList.isEmpty()))?this.getEquipmentDescriptionList():null);
            strategy.appendField(locator, this, "equipmentDescriptionList", buffer, theEquipmentDescriptionList);
        }
        {
            List<String> theDeliveryMethodList;
            theDeliveryMethodList = (((this.deliveryMethodList!= null)&&(!this.deliveryMethodList.isEmpty()))?this.getDeliveryMethodList():null);
            strategy.appendField(locator, this, "deliveryMethodList", buffer, theDeliveryMethodList);
        }
        {
            boolean theIncludedInd;
            theIncludedInd = (true?this.isIncludedInd():false);
            strategy.appendField(locator, this, "includedInd", buffer, theIncludedInd);
        }
        {
            List<PurchaseDiscount> thePurchaseDiscountList;
            thePurchaseDiscountList = (((this.purchaseDiscountList!= null)&&(!this.purchaseDiscountList.isEmpty()))?this.getPurchaseDiscountList():null);
            strategy.appendField(locator, this, "purchaseDiscountList", buffer, thePurchaseDiscountList);
        }
        {
            List<PaymentOption> thePaymentOptionList;
            thePaymentOptionList = (((this.paymentOptionList!= null)&&(!this.paymentOptionList.isEmpty()))?this.getPaymentOptionList():null);
            strategy.appendField(locator, this, "paymentOptionList", buffer, thePaymentOptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineEquipmentItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineEquipmentItem that = ((WirelineEquipmentItem) object);
        {
            String lhsMaterialItemCode;
            lhsMaterialItemCode = this.getMaterialItemCode();
            String rhsMaterialItemCode;
            rhsMaterialItemCode = that.getMaterialItemCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "materialItemCode", lhsMaterialItemCode), LocatorUtils.property(thatLocator, "materialItemCode", rhsMaterialItemCode), lhsMaterialItemCode, rhsMaterialItemCode)) {
                return false;
            }
        }
        {
            Double lhsEquipmentPurchasePriceAmt;
            lhsEquipmentPurchasePriceAmt = this.getEquipmentPurchasePriceAmt();
            Double rhsEquipmentPurchasePriceAmt;
            rhsEquipmentPurchasePriceAmt = that.getEquipmentPurchasePriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentPurchasePriceAmt", lhsEquipmentPurchasePriceAmt), LocatorUtils.property(thatLocator, "equipmentPurchasePriceAmt", rhsEquipmentPurchasePriceAmt), lhsEquipmentPurchasePriceAmt, rhsEquipmentPurchasePriceAmt)) {
                return false;
            }
        }
        {
            Double lhsEquipmentRentalPriceAmt;
            lhsEquipmentRentalPriceAmt = this.getEquipmentRentalPriceAmt();
            Double rhsEquipmentRentalPriceAmt;
            rhsEquipmentRentalPriceAmt = that.getEquipmentRentalPriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentRentalPriceAmt", lhsEquipmentRentalPriceAmt), LocatorUtils.property(thatLocator, "equipmentRentalPriceAmt", rhsEquipmentRentalPriceAmt), lhsEquipmentRentalPriceAmt, rhsEquipmentRentalPriceAmt)) {
                return false;
            }
        }
        {
            Double lhsEquipmentLateReturnPriceAmt;
            lhsEquipmentLateReturnPriceAmt = this.getEquipmentLateReturnPriceAmt();
            Double rhsEquipmentLateReturnPriceAmt;
            rhsEquipmentLateReturnPriceAmt = that.getEquipmentLateReturnPriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentLateReturnPriceAmt", lhsEquipmentLateReturnPriceAmt), LocatorUtils.property(thatLocator, "equipmentLateReturnPriceAmt", rhsEquipmentLateReturnPriceAmt), lhsEquipmentLateReturnPriceAmt, rhsEquipmentLateReturnPriceAmt)) {
                return false;
            }
        }
        {
            List<Description> lhsEquipmentDescriptionList;
            lhsEquipmentDescriptionList = (((this.equipmentDescriptionList!= null)&&(!this.equipmentDescriptionList.isEmpty()))?this.getEquipmentDescriptionList():null);
            List<Description> rhsEquipmentDescriptionList;
            rhsEquipmentDescriptionList = (((that.equipmentDescriptionList!= null)&&(!that.equipmentDescriptionList.isEmpty()))?that.getEquipmentDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentDescriptionList", lhsEquipmentDescriptionList), LocatorUtils.property(thatLocator, "equipmentDescriptionList", rhsEquipmentDescriptionList), lhsEquipmentDescriptionList, rhsEquipmentDescriptionList)) {
                return false;
            }
        }
        {
            List<String> lhsDeliveryMethodList;
            lhsDeliveryMethodList = (((this.deliveryMethodList!= null)&&(!this.deliveryMethodList.isEmpty()))?this.getDeliveryMethodList():null);
            List<String> rhsDeliveryMethodList;
            rhsDeliveryMethodList = (((that.deliveryMethodList!= null)&&(!that.deliveryMethodList.isEmpty()))?that.getDeliveryMethodList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deliveryMethodList", lhsDeliveryMethodList), LocatorUtils.property(thatLocator, "deliveryMethodList", rhsDeliveryMethodList), lhsDeliveryMethodList, rhsDeliveryMethodList)) {
                return false;
            }
        }
        {
            boolean lhsIncludedInd;
            lhsIncludedInd = (true?this.isIncludedInd():false);
            boolean rhsIncludedInd;
            rhsIncludedInd = (true?that.isIncludedInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "includedInd", lhsIncludedInd), LocatorUtils.property(thatLocator, "includedInd", rhsIncludedInd), lhsIncludedInd, rhsIncludedInd)) {
                return false;
            }
        }
        {
            List<PurchaseDiscount> lhsPurchaseDiscountList;
            lhsPurchaseDiscountList = (((this.purchaseDiscountList!= null)&&(!this.purchaseDiscountList.isEmpty()))?this.getPurchaseDiscountList():null);
            List<PurchaseDiscount> rhsPurchaseDiscountList;
            rhsPurchaseDiscountList = (((that.purchaseDiscountList!= null)&&(!that.purchaseDiscountList.isEmpty()))?that.getPurchaseDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "purchaseDiscountList", lhsPurchaseDiscountList), LocatorUtils.property(thatLocator, "purchaseDiscountList", rhsPurchaseDiscountList), lhsPurchaseDiscountList, rhsPurchaseDiscountList)) {
                return false;
            }
        }
        {
            List<PaymentOption> lhsPaymentOptionList;
            lhsPaymentOptionList = (((this.paymentOptionList!= null)&&(!this.paymentOptionList.isEmpty()))?this.getPaymentOptionList():null);
            List<PaymentOption> rhsPaymentOptionList;
            rhsPaymentOptionList = (((that.paymentOptionList!= null)&&(!that.paymentOptionList.isEmpty()))?that.getPaymentOptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentOptionList", lhsPaymentOptionList), LocatorUtils.property(thatLocator, "paymentOptionList", rhsPaymentOptionList), lhsPaymentOptionList, rhsPaymentOptionList)) {
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
