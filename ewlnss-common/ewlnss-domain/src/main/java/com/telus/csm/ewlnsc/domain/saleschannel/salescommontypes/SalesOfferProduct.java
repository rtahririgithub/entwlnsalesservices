
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
 * <p>Java class for SalesOfferProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOfferProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wirelineOfferProductList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferProduct" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOfferProduct", propOrder = {
    "wirelineOfferProductList"
})
public class SalesOfferProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<WirelineOfferProduct> wirelineOfferProductList;

    /**
     * Gets the value of the wirelineOfferProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the wirelineOfferProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getWirelineOfferProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineOfferProduct }
     * 
     * 
     */
    public List<WirelineOfferProduct> getWirelineOfferProductList() {
        if (wirelineOfferProductList == null) {
            wirelineOfferProductList = new ArrayList<WirelineOfferProduct>();
        }
        return this.wirelineOfferProductList;
    }

    /**
     * Sets the value of the wirelineOfferProductList property.
     * 
     * @param wirelineOfferProductList
     *     allowed object is
     *     {@link WirelineOfferProduct }
     *     
     */
    public void setWirelineOfferProductList(List<WirelineOfferProduct> wirelineOfferProductList) {
        this.wirelineOfferProductList = wirelineOfferProductList;
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
            List<WirelineOfferProduct> theWirelineOfferProductList;
            theWirelineOfferProductList = (((this.wirelineOfferProductList!= null)&&(!this.wirelineOfferProductList.isEmpty()))?this.getWirelineOfferProductList():null);
            strategy.appendField(locator, this, "wirelineOfferProductList", buffer, theWirelineOfferProductList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesOfferProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesOfferProduct that = ((SalesOfferProduct) object);
        {
            List<WirelineOfferProduct> lhsWirelineOfferProductList;
            lhsWirelineOfferProductList = (((this.wirelineOfferProductList!= null)&&(!this.wirelineOfferProductList.isEmpty()))?this.getWirelineOfferProductList():null);
            List<WirelineOfferProduct> rhsWirelineOfferProductList;
            rhsWirelineOfferProductList = (((that.wirelineOfferProductList!= null)&&(!that.wirelineOfferProductList.isEmpty()))?that.getWirelineOfferProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineOfferProductList", lhsWirelineOfferProductList), LocatorUtils.property(thatLocator, "wirelineOfferProductList", rhsWirelineOfferProductList), lhsWirelineOfferProductList, rhsWirelineOfferProductList)) {
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
