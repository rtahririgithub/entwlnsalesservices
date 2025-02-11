
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for WirelineProductHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineProductHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="selectedContractTermCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="product" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier" minOccurs="0"/>
 *         &lt;element name="equipmentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineEquipmentHeader" maxOccurs="20" minOccurs="0"/>
 *         &lt;element name="addOnList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineAddOnHeader" maxOccurs="20" minOccurs="0"/>
 *         &lt;element name="billDiscountList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier" maxOccurs="20" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineProductHeader", propOrder = {
    "selectedContractTermCd",
    "product",
    "equipmentList",
    "addOnList",
    "billDiscountList"
})
@XmlSeeAlso({
    HsicComponentHeader.class,
    HomePhoneComponentHeader.class,
    TelevisionComponentHeader.class
})
public class WirelineProductHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String selectedContractTermCd;
    protected ProductComponentIdentifier product;
    protected List<WirelineEquipmentHeader> equipmentList;
    protected List<WirelineAddOnHeader> addOnList;
    protected List<ProductComponentIdentifier> billDiscountList;

    /**
     * Gets the value of the selectedContractTermCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSelectedContractTermCd() {
        return selectedContractTermCd;
    }

    /**
     * Sets the value of the selectedContractTermCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSelectedContractTermCd(String value) {
        this.selectedContractTermCd = value;
    }

    /**
     * Gets the value of the product property.
     * 
     * @return
     *     possible object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public ProductComponentIdentifier getProduct() {
        return product;
    }

    /**
     * Sets the value of the product property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setProduct(ProductComponentIdentifier value) {
        this.product = value;
    }

    /**
     * Gets the value of the equipmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineEquipmentHeader }
     * 
     * 
     */
    public List<WirelineEquipmentHeader> getEquipmentList() {
        if (equipmentList == null) {
            equipmentList = new ArrayList<WirelineEquipmentHeader>();
        }
        return this.equipmentList;
    }

    /**
     * Gets the value of the addOnList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addOnList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddOnList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineAddOnHeader }
     * 
     * 
     */
    public List<WirelineAddOnHeader> getAddOnList() {
        if (addOnList == null) {
            addOnList = new ArrayList<WirelineAddOnHeader>();
        }
        return this.addOnList;
    }

    /**
     * Gets the value of the billDiscountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billDiscountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductComponentIdentifier }
     * 
     * 
     */
    public List<ProductComponentIdentifier> getBillDiscountList() {
        if (billDiscountList == null) {
            billDiscountList = new ArrayList<ProductComponentIdentifier>();
        }
        return this.billDiscountList;
    }

    /**
     * Sets the value of the equipmentList property.
     * 
     * @param equipmentList
     *     allowed object is
     *     {@link WirelineEquipmentHeader }
     *     
     */
    public void setEquipmentList(List<WirelineEquipmentHeader> equipmentList) {
        this.equipmentList = equipmentList;
    }

    /**
     * Sets the value of the addOnList property.
     * 
     * @param addOnList
     *     allowed object is
     *     {@link WirelineAddOnHeader }
     *     
     */
    public void setAddOnList(List<WirelineAddOnHeader> addOnList) {
        this.addOnList = addOnList;
    }

    /**
     * Sets the value of the billDiscountList property.
     * 
     * @param billDiscountList
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setBillDiscountList(List<ProductComponentIdentifier> billDiscountList) {
        this.billDiscountList = billDiscountList;
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
            String theSelectedContractTermCd;
            theSelectedContractTermCd = this.getSelectedContractTermCd();
            strategy.appendField(locator, this, "selectedContractTermCd", buffer, theSelectedContractTermCd);
        }
        {
            ProductComponentIdentifier theProduct;
            theProduct = this.getProduct();
            strategy.appendField(locator, this, "product", buffer, theProduct);
        }
        {
            List<WirelineEquipmentHeader> theEquipmentList;
            theEquipmentList = (((this.equipmentList!= null)&&(!this.equipmentList.isEmpty()))?this.getEquipmentList():null);
            strategy.appendField(locator, this, "equipmentList", buffer, theEquipmentList);
        }
        {
            List<WirelineAddOnHeader> theAddOnList;
            theAddOnList = (((this.addOnList!= null)&&(!this.addOnList.isEmpty()))?this.getAddOnList():null);
            strategy.appendField(locator, this, "addOnList", buffer, theAddOnList);
        }
        {
            List<ProductComponentIdentifier> theBillDiscountList;
            theBillDiscountList = (((this.billDiscountList!= null)&&(!this.billDiscountList.isEmpty()))?this.getBillDiscountList():null);
            strategy.appendField(locator, this, "billDiscountList", buffer, theBillDiscountList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineProductHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineProductHeader that = ((WirelineProductHeader) object);
        {
            String lhsSelectedContractTermCd;
            lhsSelectedContractTermCd = this.getSelectedContractTermCd();
            String rhsSelectedContractTermCd;
            rhsSelectedContractTermCd = that.getSelectedContractTermCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "selectedContractTermCd", lhsSelectedContractTermCd), LocatorUtils.property(thatLocator, "selectedContractTermCd", rhsSelectedContractTermCd), lhsSelectedContractTermCd, rhsSelectedContractTermCd)) {
                return false;
            }
        }
        {
            ProductComponentIdentifier lhsProduct;
            lhsProduct = this.getProduct();
            ProductComponentIdentifier rhsProduct;
            rhsProduct = that.getProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "product", lhsProduct), LocatorUtils.property(thatLocator, "product", rhsProduct), lhsProduct, rhsProduct)) {
                return false;
            }
        }
        {
            List<WirelineEquipmentHeader> lhsEquipmentList;
            lhsEquipmentList = (((this.equipmentList!= null)&&(!this.equipmentList.isEmpty()))?this.getEquipmentList():null);
            List<WirelineEquipmentHeader> rhsEquipmentList;
            rhsEquipmentList = (((that.equipmentList!= null)&&(!that.equipmentList.isEmpty()))?that.getEquipmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentList", lhsEquipmentList), LocatorUtils.property(thatLocator, "equipmentList", rhsEquipmentList), lhsEquipmentList, rhsEquipmentList)) {
                return false;
            }
        }
        {
            List<WirelineAddOnHeader> lhsAddOnList;
            lhsAddOnList = (((this.addOnList!= null)&&(!this.addOnList.isEmpty()))?this.getAddOnList():null);
            List<WirelineAddOnHeader> rhsAddOnList;
            rhsAddOnList = (((that.addOnList!= null)&&(!that.addOnList.isEmpty()))?that.getAddOnList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "addOnList", lhsAddOnList), LocatorUtils.property(thatLocator, "addOnList", rhsAddOnList), lhsAddOnList, rhsAddOnList)) {
                return false;
            }
        }
        {
            List<ProductComponentIdentifier> lhsBillDiscountList;
            lhsBillDiscountList = (((this.billDiscountList!= null)&&(!this.billDiscountList.isEmpty()))?this.getBillDiscountList():null);
            List<ProductComponentIdentifier> rhsBillDiscountList;
            rhsBillDiscountList = (((that.billDiscountList!= null)&&(!that.billDiscountList.isEmpty()))?that.getBillDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billDiscountList", lhsBillDiscountList), LocatorUtils.property(thatLocator, "billDiscountList", rhsBillDiscountList), lhsBillDiscountList, rhsBillDiscountList)) {
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
