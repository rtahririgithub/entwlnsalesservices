
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.telus.csm.ewlnsc.domain.schemasclone.OfferProductHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineOfferHeaderWithOfferFilter;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineSalesSummary;


public class WirelineSalesSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected WirelineOfferHeaderWithOfferFilter offerHeader;
    protected List<OfferProductHeader> productList;

    /**
     * Gets the value of the offerHeader property.
     * 
     * @return
     *     possible object is
     *     {@link WirelineOfferHeaderWithOfferFilter }
     *     
     */
    public WirelineOfferHeaderWithOfferFilter getOfferHeader() {
        return offerHeader;
    }

    /**
     * Sets the value of the offerHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link WirelineOfferHeaderWithOfferFilter }
     *     
     */
    public void setOfferHeader(WirelineOfferHeaderWithOfferFilter value) {
        this.offerHeader = value;
    }

    /**
     * Gets the value of the productList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferProductHeader }
     * 
     * 
     */
    public List<OfferProductHeader> getProductList() {
        if (productList == null) {
            productList = new ArrayList<OfferProductHeader>();
        }
        return this.productList;
    }

    /**
     * Sets the value of the productList property.
     * 
     * @param productList
     *     allowed object is
     *     {@link OfferProductHeader }
     *     
     */
    public void setProductList(List<OfferProductHeader> productList) {
        this.productList = productList;
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
            WirelineOfferHeaderWithOfferFilter theOfferHeader;
            theOfferHeader = this.getOfferHeader();
            strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
        }
        {
            List<OfferProductHeader> theProductList;
            theProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            strategy.appendField(locator, this, "productList", buffer, theProductList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineSalesSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineSalesSummary that = ((WirelineSalesSummary) object);
        {
            WirelineOfferHeaderWithOfferFilter lhsOfferHeader;
            lhsOfferHeader = this.getOfferHeader();
            WirelineOfferHeaderWithOfferFilter rhsOfferHeader;
            rhsOfferHeader = that.getOfferHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
                return false;
            }
        }
        {
            List<OfferProductHeader> lhsProductList;
            lhsProductList = (((this.productList!= null)&&(!this.productList.isEmpty()))?this.getProductList():null);
            List<OfferProductHeader> rhsProductList;
            rhsProductList = (((that.productList!= null)&&(!that.productList.isEmpty()))?that.getProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productList", lhsProductList), LocatorUtils.property(thatLocator, "productList", rhsProductList), lhsProductList, rhsProductList)) {
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
