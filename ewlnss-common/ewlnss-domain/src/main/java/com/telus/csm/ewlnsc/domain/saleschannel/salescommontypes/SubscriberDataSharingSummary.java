
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
 * <p>Java class for SubscriberDataSharingSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberDataSharingSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SubscriberBase">
 *       &lt;sequence>
 *         &lt;element name="dataSharingServiceList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DataSharingServiceSummary" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberDataSharingSummary", propOrder = {
    "dataSharingServiceList"
})
public class SubscriberDataSharingSummary
    extends SubscriberBase
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<DataSharingServiceSummary> dataSharingServiceList;

    /**
     * Gets the value of the dataSharingServiceList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dataSharingServiceList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDataSharingServiceList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DataSharingServiceSummary }
     * 
     * 
     */
    public List<DataSharingServiceSummary> getDataSharingServiceList() {
        if (dataSharingServiceList == null) {
            dataSharingServiceList = new ArrayList<DataSharingServiceSummary>();
        }
        return this.dataSharingServiceList;
    }

    /**
     * Sets the value of the dataSharingServiceList property.
     * 
     * @param dataSharingServiceList
     *     allowed object is
     *     {@link DataSharingServiceSummary }
     *     
     */
    public void setDataSharingServiceList(List<DataSharingServiceSummary> dataSharingServiceList) {
        this.dataSharingServiceList = dataSharingServiceList;
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
            List<DataSharingServiceSummary> theDataSharingServiceList;
            theDataSharingServiceList = (((this.dataSharingServiceList!= null)&&(!this.dataSharingServiceList.isEmpty()))?this.getDataSharingServiceList():null);
            strategy.appendField(locator, this, "dataSharingServiceList", buffer, theDataSharingServiceList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SubscriberDataSharingSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final SubscriberDataSharingSummary that = ((SubscriberDataSharingSummary) object);
        {
            List<DataSharingServiceSummary> lhsDataSharingServiceList;
            lhsDataSharingServiceList = (((this.dataSharingServiceList!= null)&&(!this.dataSharingServiceList.isEmpty()))?this.getDataSharingServiceList():null);
            List<DataSharingServiceSummary> rhsDataSharingServiceList;
            rhsDataSharingServiceList = (((that.dataSharingServiceList!= null)&&(!that.dataSharingServiceList.isEmpty()))?that.getDataSharingServiceList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingServiceList", lhsDataSharingServiceList), LocatorUtils.property(thatLocator, "dataSharingServiceList", rhsDataSharingServiceList), lhsDataSharingServiceList, rhsDataSharingServiceList)) {
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
