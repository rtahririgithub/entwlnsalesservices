
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
 * <p>Java class for AccessoryOrderPricingType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryOrderPricingType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="overallPricingDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PriceAmountAndTax" minOccurs="0"/>
 *         &lt;element name="productPricingDetailList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductPricingType" maxOccurs="100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryOrderPricingType", propOrder = {
    "overallPricingDetail",
    "productPricingDetailList"
})
public class AccessoryOrderPricingType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected PriceAmountAndTax overallPricingDetail;
    protected List<ProductPricingType> productPricingDetailList;

    /**
     * Gets the value of the overallPricingDetail property.
     * 
     * @return
     *     possible object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public PriceAmountAndTax getOverallPricingDetail() {
        return overallPricingDetail;
    }

    /**
     * Sets the value of the overallPricingDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link PriceAmountAndTax }
     *     
     */
    public void setOverallPricingDetail(PriceAmountAndTax value) {
        this.overallPricingDetail = value;
    }

    /**
     * Gets the value of the productPricingDetailList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productPricingDetailList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductPricingDetailList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductPricingType }
     * 
     * 
     */
    public List<ProductPricingType> getProductPricingDetailList() {
        if (productPricingDetailList == null) {
            productPricingDetailList = new ArrayList<ProductPricingType>();
        }
        return this.productPricingDetailList;
    }

    /**
     * Sets the value of the productPricingDetailList property.
     * 
     * @param productPricingDetailList
     *     allowed object is
     *     {@link ProductPricingType }
     *     
     */
    public void setProductPricingDetailList(List<ProductPricingType> productPricingDetailList) {
        this.productPricingDetailList = productPricingDetailList;
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
            PriceAmountAndTax theOverallPricingDetail;
            theOverallPricingDetail = this.getOverallPricingDetail();
            strategy.appendField(locator, this, "overallPricingDetail", buffer, theOverallPricingDetail);
        }
        {
            List<ProductPricingType> theProductPricingDetailList;
            theProductPricingDetailList = (((this.productPricingDetailList!= null)&&(!this.productPricingDetailList.isEmpty()))?this.getProductPricingDetailList():null);
            strategy.appendField(locator, this, "productPricingDetailList", buffer, theProductPricingDetailList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryOrderPricingType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryOrderPricingType that = ((AccessoryOrderPricingType) object);
        {
            PriceAmountAndTax lhsOverallPricingDetail;
            lhsOverallPricingDetail = this.getOverallPricingDetail();
            PriceAmountAndTax rhsOverallPricingDetail;
            rhsOverallPricingDetail = that.getOverallPricingDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "overallPricingDetail", lhsOverallPricingDetail), LocatorUtils.property(thatLocator, "overallPricingDetail", rhsOverallPricingDetail), lhsOverallPricingDetail, rhsOverallPricingDetail)) {
                return false;
            }
        }
        {
            List<ProductPricingType> lhsProductPricingDetailList;
            lhsProductPricingDetailList = (((this.productPricingDetailList!= null)&&(!this.productPricingDetailList.isEmpty()))?this.getProductPricingDetailList():null);
            List<ProductPricingType> rhsProductPricingDetailList;
            rhsProductPricingDetailList = (((that.productPricingDetailList!= null)&&(!that.productPricingDetailList.isEmpty()))?that.getProductPricingDetailList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productPricingDetailList", lhsProductPricingDetailList), LocatorUtils.property(thatLocator, "productPricingDetailList", rhsProductPricingDetailList), lhsProductPricingDetailList, rhsProductPricingDetailList)) {
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
