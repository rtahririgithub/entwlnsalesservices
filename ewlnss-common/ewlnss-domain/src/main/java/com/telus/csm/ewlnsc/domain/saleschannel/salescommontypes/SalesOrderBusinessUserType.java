
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
 * <p>Java class for SalesOrderBusinessUserType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesOrderBusinessUserType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seat" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Seat" minOccurs="0"/>
 *         &lt;element name="addressList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesOrderBusinessUserType", propOrder = {
    "seat",
    "addressList"
})
public class SalesOrderBusinessUserType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected Seat seat;
    protected List<Address> addressList;

    /**
     * Gets the value of the seat property.
     * 
     * @return
     *     possible object is
     *     {@link Seat }
     *     
     */
    public Seat getSeat() {
        return seat;
    }

    /**
     * Sets the value of the seat property.
     * 
     * @param value
     *     allowed object is
     *     {@link Seat }
     *     
     */
    public void setSeat(Seat value) {
        this.seat = value;
    }

    /**
     * Gets the value of the addressList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the addressList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAddressList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Address }
     * 
     * 
     */
    public List<Address> getAddressList() {
        if (addressList == null) {
            addressList = new ArrayList<Address>();
        }
        return this.addressList;
    }

    /**
     * Sets the value of the addressList property.
     * 
     * @param addressList
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
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
            Seat theSeat;
            theSeat = this.getSeat();
            strategy.appendField(locator, this, "seat", buffer, theSeat);
        }
        {
            List<Address> theAddressList;
            theAddressList = (((this.addressList!= null)&&(!this.addressList.isEmpty()))?this.getAddressList():null);
            strategy.appendField(locator, this, "addressList", buffer, theAddressList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesOrderBusinessUserType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesOrderBusinessUserType that = ((SalesOrderBusinessUserType) object);
        {
            Seat lhsSeat;
            lhsSeat = this.getSeat();
            Seat rhsSeat;
            rhsSeat = that.getSeat();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seat", lhsSeat), LocatorUtils.property(thatLocator, "seat", rhsSeat), lhsSeat, rhsSeat)) {
                return false;
            }
        }
        {
            List<Address> lhsAddressList;
            lhsAddressList = (((this.addressList!= null)&&(!this.addressList.isEmpty()))?this.getAddressList():null);
            List<Address> rhsAddressList;
            rhsAddressList = (((that.addressList!= null)&&(!that.addressList.isEmpty()))?that.getAddressList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "addressList", lhsAddressList), LocatorUtils.property(thatLocator, "addressList", rhsAddressList), lhsAddressList, rhsAddressList)) {
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
