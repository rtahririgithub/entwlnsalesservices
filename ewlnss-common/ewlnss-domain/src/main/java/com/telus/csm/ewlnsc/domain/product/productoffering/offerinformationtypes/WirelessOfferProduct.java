
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
 * <p>Java class for WirelessOfferProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferProduct">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessOfferProductSummary">
 *       &lt;sequence>
 *         &lt;element name="wirelessEquipment" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessEquipment" minOccurs="0"/>
 *         &lt;element name="wirelessPricePlanSet" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessPricePlanSet" minOccurs="0"/>
 *         &lt;element name="wirelessServiceSetList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}WirelessServiceSet" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="minimumServiceCommitmentList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}MinimumMonthlyServiceCommitment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="deviceBalanceWaiver" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}DeviceBalanceWaiver" minOccurs="0"/>
 *         &lt;element name="billCreditList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}BillCredit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="serviceDiscountList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ServiceDiscount" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessOfferProduct", propOrder = {
    "wirelessEquipment",
    "wirelessPricePlanSet",
    "wirelessServiceSetList",
    "minimumServiceCommitmentList",
    "deviceBalanceWaiver",
    "billCreditList",
    "serviceDiscountList"
})
public class WirelessOfferProduct
    extends WirelessOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelessEquipment wirelessEquipment;
    protected WirelessPricePlanSet wirelessPricePlanSet;
    protected List<WirelessServiceSet> wirelessServiceSetList;
    protected List<MinimumMonthlyServiceCommitment> minimumServiceCommitmentList;
    protected DeviceBalanceWaiver deviceBalanceWaiver;
    protected List<BillCredit> billCreditList;
    protected List<ServiceDiscount> serviceDiscountList;

    /**
     * Gets the value of the wirelessEquipment property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessEquipment }
     *     
     */
    public WirelessEquipment getWirelessEquipment() {
        return wirelessEquipment;
    }

    /**
     * Sets the value of the wirelessEquipment property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessEquipment }
     *     
     */
    public void setWirelessEquipment(WirelessEquipment value) {
        this.wirelessEquipment = value;
    }

    /**
     * Gets the value of the wirelessPricePlanSet property.
     * 
     * @return
     *     possible object is
     *     {@link WirelessPricePlanSet }
     *     
     */
    public WirelessPricePlanSet getWirelessPricePlanSet() {
        return wirelessPricePlanSet;
    }

    /**
     * Sets the value of the wirelessPricePlanSet property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelessPricePlanSet }
     *     
     */
    public void setWirelessPricePlanSet(WirelessPricePlanSet value) {
        this.wirelessPricePlanSet = value;
    }

    /**
     * Gets the value of the wirelessServiceSetList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelessServiceSetList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelessServiceSetList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelessServiceSet }
     * 
     * 
     */
    public List<WirelessServiceSet> getWirelessServiceSetList() {
        if (wirelessServiceSetList == null) {
            wirelessServiceSetList = new ArrayList<WirelessServiceSet>();
        }
        return this.wirelessServiceSetList;
    }

    /**
     * Gets the value of the minimumServiceCommitmentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the minimumServiceCommitmentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMinimumServiceCommitmentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MinimumMonthlyServiceCommitment }
     * 
     * 
     */
    public List<MinimumMonthlyServiceCommitment> getMinimumServiceCommitmentList() {
        if (minimumServiceCommitmentList == null) {
            minimumServiceCommitmentList = new ArrayList<MinimumMonthlyServiceCommitment>();
        }
        return this.minimumServiceCommitmentList;
    }

    /**
     * Gets the value of the deviceBalanceWaiver property.
     * 
     * @return
     *     possible object is
     *     {@link DeviceBalanceWaiver }
     *     
     */
    public DeviceBalanceWaiver getDeviceBalanceWaiver() {
        return deviceBalanceWaiver;
    }

    /**
     * Sets the value of the deviceBalanceWaiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link DeviceBalanceWaiver }
     *     
     */
    public void setDeviceBalanceWaiver(DeviceBalanceWaiver value) {
        this.deviceBalanceWaiver = value;
    }

    /**
     * Gets the value of the billCreditList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the billCreditList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBillCreditList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BillCredit }
     * 
     * 
     */
    public List<BillCredit> getBillCreditList() {
        if (billCreditList == null) {
            billCreditList = new ArrayList<BillCredit>();
        }
        return this.billCreditList;
    }

    /**
     * Gets the value of the serviceDiscountList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceDiscountList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceDiscountList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceDiscount }
     * 
     * 
     */
    public List<ServiceDiscount> getServiceDiscountList() {
        if (serviceDiscountList == null) {
            serviceDiscountList = new ArrayList<ServiceDiscount>();
        }
        return this.serviceDiscountList;
    }

    /**
     * Sets the value of the wirelessServiceSetList property.
     * 
     * @param wirelessServiceSetList
     *     allowed object is
     *     {@link WirelessServiceSet }
     *     
     */
    public void setWirelessServiceSetList(List<WirelessServiceSet> wirelessServiceSetList) {
        this.wirelessServiceSetList = wirelessServiceSetList;
    }

    /**
     * Sets the value of the minimumServiceCommitmentList property.
     * 
     * @param minimumServiceCommitmentList
     *     allowed object is
     *     {@link MinimumMonthlyServiceCommitment }
     *     
     */
    public void setMinimumServiceCommitmentList(List<MinimumMonthlyServiceCommitment> minimumServiceCommitmentList) {
        this.minimumServiceCommitmentList = minimumServiceCommitmentList;
    }

    /**
     * Sets the value of the billCreditList property.
     * 
     * @param billCreditList
     *     allowed object is
     *     {@link BillCredit }
     *     
     */
    public void setBillCreditList(List<BillCredit> billCreditList) {
        this.billCreditList = billCreditList;
    }

    /**
     * Sets the value of the serviceDiscountList property.
     * 
     * @param serviceDiscountList
     *     allowed object is
     *     {@link ServiceDiscount }
     *     
     */
    public void setServiceDiscountList(List<ServiceDiscount> serviceDiscountList) {
        this.serviceDiscountList = serviceDiscountList;
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
            WirelessEquipment theWirelessEquipment;
            theWirelessEquipment = this.getWirelessEquipment();
            strategy.appendField(locator, this, "wirelessEquipment", buffer, theWirelessEquipment);
        }
        {
            WirelessPricePlanSet theWirelessPricePlanSet;
            theWirelessPricePlanSet = this.getWirelessPricePlanSet();
            strategy.appendField(locator, this, "wirelessPricePlanSet", buffer, theWirelessPricePlanSet);
        }
        {
            List<WirelessServiceSet> theWirelessServiceSetList;
            theWirelessServiceSetList = (((this.wirelessServiceSetList!= null)&&(!this.wirelessServiceSetList.isEmpty()))?this.getWirelessServiceSetList():null);
            strategy.appendField(locator, this, "wirelessServiceSetList", buffer, theWirelessServiceSetList);
        }
        {
            List<MinimumMonthlyServiceCommitment> theMinimumServiceCommitmentList;
            theMinimumServiceCommitmentList = (((this.minimumServiceCommitmentList!= null)&&(!this.minimumServiceCommitmentList.isEmpty()))?this.getMinimumServiceCommitmentList():null);
            strategy.appendField(locator, this, "minimumServiceCommitmentList", buffer, theMinimumServiceCommitmentList);
        }
        {
            DeviceBalanceWaiver theDeviceBalanceWaiver;
            theDeviceBalanceWaiver = this.getDeviceBalanceWaiver();
            strategy.appendField(locator, this, "deviceBalanceWaiver", buffer, theDeviceBalanceWaiver);
        }
        {
            List<BillCredit> theBillCreditList;
            theBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
            strategy.appendField(locator, this, "billCreditList", buffer, theBillCreditList);
        }
        {
            List<ServiceDiscount> theServiceDiscountList;
            theServiceDiscountList = (((this.serviceDiscountList!= null)&&(!this.serviceDiscountList.isEmpty()))?this.getServiceDiscountList():null);
            strategy.appendField(locator, this, "serviceDiscountList", buffer, theServiceDiscountList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelessOfferProduct that = ((WirelessOfferProduct) object);
        {
            WirelessEquipment lhsWirelessEquipment;
            lhsWirelessEquipment = this.getWirelessEquipment();
            WirelessEquipment rhsWirelessEquipment;
            rhsWirelessEquipment = that.getWirelessEquipment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessEquipment", lhsWirelessEquipment), LocatorUtils.property(thatLocator, "wirelessEquipment", rhsWirelessEquipment), lhsWirelessEquipment, rhsWirelessEquipment)) {
                return false;
            }
        }
        {
            WirelessPricePlanSet lhsWirelessPricePlanSet;
            lhsWirelessPricePlanSet = this.getWirelessPricePlanSet();
            WirelessPricePlanSet rhsWirelessPricePlanSet;
            rhsWirelessPricePlanSet = that.getWirelessPricePlanSet();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessPricePlanSet", lhsWirelessPricePlanSet), LocatorUtils.property(thatLocator, "wirelessPricePlanSet", rhsWirelessPricePlanSet), lhsWirelessPricePlanSet, rhsWirelessPricePlanSet)) {
                return false;
            }
        }
        {
            List<WirelessServiceSet> lhsWirelessServiceSetList;
            lhsWirelessServiceSetList = (((this.wirelessServiceSetList!= null)&&(!this.wirelessServiceSetList.isEmpty()))?this.getWirelessServiceSetList():null);
            List<WirelessServiceSet> rhsWirelessServiceSetList;
            rhsWirelessServiceSetList = (((that.wirelessServiceSetList!= null)&&(!that.wirelessServiceSetList.isEmpty()))?that.getWirelessServiceSetList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessServiceSetList", lhsWirelessServiceSetList), LocatorUtils.property(thatLocator, "wirelessServiceSetList", rhsWirelessServiceSetList), lhsWirelessServiceSetList, rhsWirelessServiceSetList)) {
                return false;
            }
        }
        {
            List<MinimumMonthlyServiceCommitment> lhsMinimumServiceCommitmentList;
            lhsMinimumServiceCommitmentList = (((this.minimumServiceCommitmentList!= null)&&(!this.minimumServiceCommitmentList.isEmpty()))?this.getMinimumServiceCommitmentList():null);
            List<MinimumMonthlyServiceCommitment> rhsMinimumServiceCommitmentList;
            rhsMinimumServiceCommitmentList = (((that.minimumServiceCommitmentList!= null)&&(!that.minimumServiceCommitmentList.isEmpty()))?that.getMinimumServiceCommitmentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumServiceCommitmentList", lhsMinimumServiceCommitmentList), LocatorUtils.property(thatLocator, "minimumServiceCommitmentList", rhsMinimumServiceCommitmentList), lhsMinimumServiceCommitmentList, rhsMinimumServiceCommitmentList)) {
                return false;
            }
        }
        {
            DeviceBalanceWaiver lhsDeviceBalanceWaiver;
            lhsDeviceBalanceWaiver = this.getDeviceBalanceWaiver();
            DeviceBalanceWaiver rhsDeviceBalanceWaiver;
            rhsDeviceBalanceWaiver = that.getDeviceBalanceWaiver();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "deviceBalanceWaiver", lhsDeviceBalanceWaiver), LocatorUtils.property(thatLocator, "deviceBalanceWaiver", rhsDeviceBalanceWaiver), lhsDeviceBalanceWaiver, rhsDeviceBalanceWaiver)) {
                return false;
            }
        }
        {
            List<BillCredit> lhsBillCreditList;
            lhsBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
            List<BillCredit> rhsBillCreditList;
            rhsBillCreditList = (((that.billCreditList!= null)&&(!that.billCreditList.isEmpty()))?that.getBillCreditList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billCreditList", lhsBillCreditList), LocatorUtils.property(thatLocator, "billCreditList", rhsBillCreditList), lhsBillCreditList, rhsBillCreditList)) {
                return false;
            }
        }
        {
            List<ServiceDiscount> lhsServiceDiscountList;
            lhsServiceDiscountList = (((this.serviceDiscountList!= null)&&(!this.serviceDiscountList.isEmpty()))?this.getServiceDiscountList():null);
            List<ServiceDiscount> rhsServiceDiscountList;
            rhsServiceDiscountList = (((that.serviceDiscountList!= null)&&(!that.serviceDiscountList.isEmpty()))?that.getServiceDiscountList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceDiscountList", lhsServiceDiscountList), LocatorUtils.property(thatLocator, "serviceDiscountList", rhsServiceDiscountList), lhsServiceDiscountList, rhsServiceDiscountList)) {
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
