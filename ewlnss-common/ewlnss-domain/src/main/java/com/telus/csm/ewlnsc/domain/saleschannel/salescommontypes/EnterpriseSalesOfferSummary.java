
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferSummary;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for EnterpriseSalesOfferSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnterpriseSalesOfferSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferSummary">
 *       &lt;sequence>
 *         &lt;element name="recontractEligibleProductList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RecontractEligibleProduct" maxOccurs="3" minOccurs="0"/>
 *         &lt;element name="salesItemDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}EnterpriseSalesItemDescription" maxOccurs="300" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnterpriseSalesOfferSummary", propOrder = {
    "recontractEligibleProductList",
    "salesItemDescriptionList"
})
public class EnterpriseSalesOfferSummary
    extends OfferSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<RecontractEligibleProduct> recontractEligibleProductList;
    protected List<EnterpriseSalesItemDescription> salesItemDescriptionList;

    /**
     * Gets the value of the recontractEligibleProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recontractEligibleProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecontractEligibleProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RecontractEligibleProduct }
     * 
     * 
     */
    public List<RecontractEligibleProduct> getRecontractEligibleProductList() {
        if (recontractEligibleProductList == null) {
            recontractEligibleProductList = new ArrayList<RecontractEligibleProduct>();
        }
        return this.recontractEligibleProductList;
    }

    /**
     * Gets the value of the salesItemDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the salesItemDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSalesItemDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EnterpriseSalesItemDescription }
     * 
     * 
     */
    public List<EnterpriseSalesItemDescription> getSalesItemDescriptionList() {
        if (salesItemDescriptionList == null) {
            salesItemDescriptionList = new ArrayList<EnterpriseSalesItemDescription>();
        }
        return this.salesItemDescriptionList;
    }

    /**
     * Sets the value of the recontractEligibleProductList property.
     * 
     * @param recontractEligibleProductList
     *     allowed object is
     *     {@link RecontractEligibleProduct }
     *     
     */
    public void setRecontractEligibleProductList(List<RecontractEligibleProduct> recontractEligibleProductList) {
        this.recontractEligibleProductList = recontractEligibleProductList;
    }

    /**
     * Sets the value of the salesItemDescriptionList property.
     * 
     * @param salesItemDescriptionList
     *     allowed object is
     *     {@link EnterpriseSalesItemDescription }
     *     
     */
    public void setSalesItemDescriptionList(List<EnterpriseSalesItemDescription> salesItemDescriptionList) {
        this.salesItemDescriptionList = salesItemDescriptionList;
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
            List<RecontractEligibleProduct> theRecontractEligibleProductList;
            theRecontractEligibleProductList = (((this.recontractEligibleProductList!= null)&&(!this.recontractEligibleProductList.isEmpty()))?this.getRecontractEligibleProductList():null);
            strategy.appendField(locator, this, "recontractEligibleProductList", buffer, theRecontractEligibleProductList);
        }
        {
            List<EnterpriseSalesItemDescription> theSalesItemDescriptionList;
            theSalesItemDescriptionList = (((this.salesItemDescriptionList!= null)&&(!this.salesItemDescriptionList.isEmpty()))?this.getSalesItemDescriptionList():null);
            strategy.appendField(locator, this, "salesItemDescriptionList", buffer, theSalesItemDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EnterpriseSalesOfferSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final EnterpriseSalesOfferSummary that = ((EnterpriseSalesOfferSummary) object);
        {
            List<RecontractEligibleProduct> lhsRecontractEligibleProductList;
            lhsRecontractEligibleProductList = (((this.recontractEligibleProductList!= null)&&(!this.recontractEligibleProductList.isEmpty()))?this.getRecontractEligibleProductList():null);
            List<RecontractEligibleProduct> rhsRecontractEligibleProductList;
            rhsRecontractEligibleProductList = (((that.recontractEligibleProductList!= null)&&(!that.recontractEligibleProductList.isEmpty()))?that.getRecontractEligibleProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recontractEligibleProductList", lhsRecontractEligibleProductList), LocatorUtils.property(thatLocator, "recontractEligibleProductList", rhsRecontractEligibleProductList), lhsRecontractEligibleProductList, rhsRecontractEligibleProductList)) {
                return false;
            }
        }
        {
            List<EnterpriseSalesItemDescription> lhsSalesItemDescriptionList;
            lhsSalesItemDescriptionList = (((this.salesItemDescriptionList!= null)&&(!this.salesItemDescriptionList.isEmpty()))?this.getSalesItemDescriptionList():null);
            List<EnterpriseSalesItemDescription> rhsSalesItemDescriptionList;
            rhsSalesItemDescriptionList = (((that.salesItemDescriptionList!= null)&&(!that.salesItemDescriptionList.isEmpty()))?that.getSalesItemDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "salesItemDescriptionList", lhsSalesItemDescriptionList), LocatorUtils.property(thatLocator, "salesItemDescriptionList", rhsSalesItemDescriptionList), lhsSalesItemDescriptionList, rhsSalesItemDescriptionList)) {
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
