
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

import com.telus.csm.ewlnsc.domain.schemasclone.SalesHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetenerOfferFilter;
import com.telus.csm.ewlnsc.domain.schemasclone.SweetnerOfferFilterCriteria;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelineSalesSummary;


public class SweetnerOfferFilterCriteria
    extends SalesHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<WirelineSalesSummary> associatedWirelineSalesSummaryList;
    protected SweetenerOfferFilter offerFilter;

    /**
     * Gets the value of the associatedWirelineSalesSummaryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedWirelineSalesSummaryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedWirelineSalesSummaryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineSalesSummary }
     * 
     * 
     */
    public List<WirelineSalesSummary> getAssociatedWirelineSalesSummaryList() {
        if (associatedWirelineSalesSummaryList == null) {
            associatedWirelineSalesSummaryList = new ArrayList<WirelineSalesSummary>();
        }
        return this.associatedWirelineSalesSummaryList;
    }

    /**
     * Gets the value of the offerFilter property.
     * 
     * @return
     *     possible object is
     *     {@link SweetenerOfferFilter }
     *     
     */
    public SweetenerOfferFilter getOfferFilter() {
        return offerFilter;
    }

    /**
     * Sets the value of the offerFilter property.
     * 
     * @param value
     *     allowed object is
     *     {@link SweetenerOfferFilter }
     *     
     */
    public void setOfferFilter(SweetenerOfferFilter value) {
        this.offerFilter = value;
    }

    /**
     * Sets the value of the associatedWirelineSalesSummaryList property.
     * 
     * @param associatedWirelineSalesSummaryList
     *     allowed object is
     *     {@link WirelineSalesSummary }
     *     
     */
    public void setAssociatedWirelineSalesSummaryList(List<WirelineSalesSummary> associatedWirelineSalesSummaryList) {
        this.associatedWirelineSalesSummaryList = associatedWirelineSalesSummaryList;
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
            List<WirelineSalesSummary> theAssociatedWirelineSalesSummaryList;
            theAssociatedWirelineSalesSummaryList = (((this.associatedWirelineSalesSummaryList!= null)&&(!this.associatedWirelineSalesSummaryList.isEmpty()))?this.getAssociatedWirelineSalesSummaryList():null);
            strategy.appendField(locator, this, "associatedWirelineSalesSummaryList", buffer, theAssociatedWirelineSalesSummaryList);
        }
        {
            SweetenerOfferFilter theOfferFilter;
            theOfferFilter = this.getOfferFilter();
            strategy.appendField(locator, this, "offerFilter", buffer, theOfferFilter);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SweetnerOfferFilterCriteria)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final SweetnerOfferFilterCriteria that = ((SweetnerOfferFilterCriteria) object);
        {
            List<WirelineSalesSummary> lhsAssociatedWirelineSalesSummaryList;
            lhsAssociatedWirelineSalesSummaryList = (((this.associatedWirelineSalesSummaryList!= null)&&(!this.associatedWirelineSalesSummaryList.isEmpty()))?this.getAssociatedWirelineSalesSummaryList():null);
            List<WirelineSalesSummary> rhsAssociatedWirelineSalesSummaryList;
            rhsAssociatedWirelineSalesSummaryList = (((that.associatedWirelineSalesSummaryList!= null)&&(!that.associatedWirelineSalesSummaryList.isEmpty()))?that.getAssociatedWirelineSalesSummaryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedWirelineSalesSummaryList", lhsAssociatedWirelineSalesSummaryList), LocatorUtils.property(thatLocator, "associatedWirelineSalesSummaryList", rhsAssociatedWirelineSalesSummaryList), lhsAssociatedWirelineSalesSummaryList, rhsAssociatedWirelineSalesSummaryList)) {
                return false;
            }
        }
        {
            SweetenerOfferFilter lhsOfferFilter;
            lhsOfferFilter = this.getOfferFilter();
            SweetenerOfferFilter rhsOfferFilter;
            rhsOfferFilter = that.getOfferFilter();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerFilter", lhsOfferFilter), LocatorUtils.property(thatLocator, "offerFilter", rhsOfferFilter), lhsOfferFilter, rhsOfferFilter)) {
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
