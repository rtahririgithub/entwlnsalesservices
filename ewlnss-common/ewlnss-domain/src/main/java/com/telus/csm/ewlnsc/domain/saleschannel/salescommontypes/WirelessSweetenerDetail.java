
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.wirelesssubscriberofferinformationtypes_v2.BillCredit;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelessSweetenerDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessSweetenerDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelessSweetenerSummary">
 *       &lt;sequence>
 *         &lt;element name="pricePlanCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="25" minOccurs="0"/>
 *         &lt;element name="serviceCodeList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="25" minOccurs="0"/>
 *         &lt;element name="billCreditList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}BillCredit" maxOccurs="25" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessSweetenerDetail", propOrder = {
    "pricePlanCodeList",
    "serviceCodeList",
    "billCreditList"
})
public class WirelessSweetenerDetail
    extends WirelessSweetenerSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<String> pricePlanCodeList;
    protected List<String> serviceCodeList;
    protected List<BillCredit> billCreditList;

    /**
     * Gets the value of the pricePlanCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the pricePlanCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPricePlanCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getPricePlanCodeList() {
        if (pricePlanCodeList == null) {
            pricePlanCodeList = new ArrayList<String>();
        }
        return this.pricePlanCodeList;
    }

    /**
     * Gets the value of the serviceCodeList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceCodeList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceCodeList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getServiceCodeList() {
        if (serviceCodeList == null) {
            serviceCodeList = new ArrayList<String>();
        }
        return this.serviceCodeList;
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
     * Sets the value of the pricePlanCodeList property.
     * 
     * @param pricePlanCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPricePlanCodeList(List<String> pricePlanCodeList) {
        this.pricePlanCodeList = pricePlanCodeList;
    }

    /**
     * Sets the value of the serviceCodeList property.
     * 
     * @param serviceCodeList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceCodeList(List<String> serviceCodeList) {
        this.serviceCodeList = serviceCodeList;
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
            List<String> thePricePlanCodeList;
            thePricePlanCodeList = (((this.pricePlanCodeList!= null)&&(!this.pricePlanCodeList.isEmpty()))?this.getPricePlanCodeList():null);
            strategy.appendField(locator, this, "pricePlanCodeList", buffer, thePricePlanCodeList);
        }
        {
            List<String> theServiceCodeList;
            theServiceCodeList = (((this.serviceCodeList!= null)&&(!this.serviceCodeList.isEmpty()))?this.getServiceCodeList():null);
            strategy.appendField(locator, this, "serviceCodeList", buffer, theServiceCodeList);
        }
        {
            List<BillCredit> theBillCreditList;
            theBillCreditList = (((this.billCreditList!= null)&&(!this.billCreditList.isEmpty()))?this.getBillCreditList():null);
            strategy.appendField(locator, this, "billCreditList", buffer, theBillCreditList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessSweetenerDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final WirelessSweetenerDetail that = ((WirelessSweetenerDetail) object);
        {
            List<String> lhsPricePlanCodeList;
            lhsPricePlanCodeList = (((this.pricePlanCodeList!= null)&&(!this.pricePlanCodeList.isEmpty()))?this.getPricePlanCodeList():null);
            List<String> rhsPricePlanCodeList;
            rhsPricePlanCodeList = (((that.pricePlanCodeList!= null)&&(!that.pricePlanCodeList.isEmpty()))?that.getPricePlanCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pricePlanCodeList", lhsPricePlanCodeList), LocatorUtils.property(thatLocator, "pricePlanCodeList", rhsPricePlanCodeList), lhsPricePlanCodeList, rhsPricePlanCodeList)) {
                return false;
            }
        }
        {
            List<String> lhsServiceCodeList;
            lhsServiceCodeList = (((this.serviceCodeList!= null)&&(!this.serviceCodeList.isEmpty()))?this.getServiceCodeList():null);
            List<String> rhsServiceCodeList;
            rhsServiceCodeList = (((that.serviceCodeList!= null)&&(!that.serviceCodeList.isEmpty()))?that.getServiceCodeList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCodeList", lhsServiceCodeList), LocatorUtils.property(thatLocator, "serviceCodeList", rhsServiceCodeList), lhsServiceCodeList, rhsServiceCodeList)) {
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
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }

}
