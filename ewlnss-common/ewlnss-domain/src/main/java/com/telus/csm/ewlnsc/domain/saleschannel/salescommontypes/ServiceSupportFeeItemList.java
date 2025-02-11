
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.customer.customer.customerservicesupportfeecommon_v1.ServiceSupportFee;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for ServiceSupportFeeItemList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceSupportFeeItemList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceSupportFeeItem" type="{http://xmlschema.tmi.telus.com/xsd/Customer/Customer/CustomerServiceSupportFeeCommon_v1}ServiceSupportFee" maxOccurs="50"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceSupportFeeItemList", propOrder = {
    "serviceSupportFeeItem"
})
public class ServiceSupportFeeItemList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<ServiceSupportFee> serviceSupportFeeItem;

    /**
     * Gets the value of the serviceSupportFeeItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the serviceSupportFeeItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getServiceSupportFeeItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ServiceSupportFee }
     * 
     * 
     */
    public List<ServiceSupportFee> getServiceSupportFeeItem() {
        if (serviceSupportFeeItem == null) {
            serviceSupportFeeItem = new ArrayList<ServiceSupportFee>();
        }
        return this.serviceSupportFeeItem;
    }

    /**
     * Sets the value of the serviceSupportFeeItem property.
     * 
     * @param serviceSupportFeeItem
     *     allowed object is
     *     {@link ServiceSupportFee }
     *     
     */
    public void setServiceSupportFeeItem(List<ServiceSupportFee> serviceSupportFeeItem) {
        this.serviceSupportFeeItem = serviceSupportFeeItem;
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
            List<ServiceSupportFee> theServiceSupportFeeItem;
            theServiceSupportFeeItem = (((this.serviceSupportFeeItem!= null)&&(!this.serviceSupportFeeItem.isEmpty()))?this.getServiceSupportFeeItem():null);
            strategy.appendField(locator, this, "serviceSupportFeeItem", buffer, theServiceSupportFeeItem);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceSupportFeeItemList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceSupportFeeItemList that = ((ServiceSupportFeeItemList) object);
        {
            List<ServiceSupportFee> lhsServiceSupportFeeItem;
            lhsServiceSupportFeeItem = (((this.serviceSupportFeeItem!= null)&&(!this.serviceSupportFeeItem.isEmpty()))?this.getServiceSupportFeeItem():null);
            List<ServiceSupportFee> rhsServiceSupportFeeItem;
            rhsServiceSupportFeeItem = (((that.serviceSupportFeeItem!= null)&&(!that.serviceSupportFeeItem.isEmpty()))?that.getServiceSupportFeeItem():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceSupportFeeItem", lhsServiceSupportFeeItem), LocatorUtils.property(thatLocator, "serviceSupportFeeItem", rhsServiceSupportFeeItem), lhsServiceSupportFeeItem, rhsServiceSupportFeeItem)) {
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
