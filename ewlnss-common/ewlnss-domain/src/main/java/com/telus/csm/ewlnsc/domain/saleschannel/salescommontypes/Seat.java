
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * A seat is saved as an individual subscriber
 * 
 * <p>Java class for Seat complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Seat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="seatTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="seatSubTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="seatGroupId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="associatedPhoneNumberList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}PhoneNumberType" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="serviceEdition" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceEdition" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Seat", propOrder = {
    "seatTypeCode",
    "seatSubTypeCode",
    "seatGroupId",
    "associatedPhoneNumberList",
    "serviceEdition"
})
public class Seat
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String seatTypeCode;
    protected String seatSubTypeCode;
    protected long seatGroupId;
    protected List<PhoneNumberType> associatedPhoneNumberList;
    protected ServiceEdition serviceEdition;

    /**
     * Gets the value of the seatTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatTypeCode() {
        return seatTypeCode;
    }

    /**
     * Sets the value of the seatTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatTypeCode(String value) {
        this.seatTypeCode = value;
    }

    /**
     * Gets the value of the seatSubTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeatSubTypeCode() {
        return seatSubTypeCode;
    }

    /**
     * Sets the value of the seatSubTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeatSubTypeCode(String value) {
        this.seatSubTypeCode = value;
    }

    /**
     * Gets the value of the seatGroupId property.
     * 
     */
    public long getSeatGroupId() {
        return seatGroupId;
    }

    /**
     * Sets the value of the seatGroupId property.
     * 
     */
    public void setSeatGroupId(long value) {
        this.seatGroupId = value;
    }

    /**
     * Gets the value of the associatedPhoneNumberList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the associatedPhoneNumberList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAssociatedPhoneNumberList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PhoneNumberType }
     * 
     * 
     */
    public List<PhoneNumberType> getAssociatedPhoneNumberList() {
        if (associatedPhoneNumberList == null) {
            associatedPhoneNumberList = new ArrayList<PhoneNumberType>();
        }
        return this.associatedPhoneNumberList;
    }

    /**
     * Gets the value of the serviceEdition property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceEdition }
     *     
     */
    public ServiceEdition getServiceEdition() {
        return serviceEdition;
    }

    /**
     * Sets the value of the serviceEdition property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceEdition }
     *     
     */
    public void setServiceEdition(ServiceEdition value) {
        this.serviceEdition = value;
    }

    /**
     * Sets the value of the associatedPhoneNumberList property.
     * 
     * @param associatedPhoneNumberList
     *     allowed object is
     *     {@link PhoneNumberType }
     *     
     */
    public void setAssociatedPhoneNumberList(List<PhoneNumberType> associatedPhoneNumberList) {
        this.associatedPhoneNumberList = associatedPhoneNumberList;
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
            String theSeatTypeCode;
            theSeatTypeCode = this.getSeatTypeCode();
            strategy.appendField(locator, this, "seatTypeCode", buffer, theSeatTypeCode);
        }
        {
            String theSeatSubTypeCode;
            theSeatSubTypeCode = this.getSeatSubTypeCode();
            strategy.appendField(locator, this, "seatSubTypeCode", buffer, theSeatSubTypeCode);
        }
        {
            long theSeatGroupId;
            theSeatGroupId = (true?this.getSeatGroupId(): 0L);
            strategy.appendField(locator, this, "seatGroupId", buffer, theSeatGroupId);
        }
        {
            List<PhoneNumberType> theAssociatedPhoneNumberList;
            theAssociatedPhoneNumberList = (((this.associatedPhoneNumberList!= null)&&(!this.associatedPhoneNumberList.isEmpty()))?this.getAssociatedPhoneNumberList():null);
            strategy.appendField(locator, this, "associatedPhoneNumberList", buffer, theAssociatedPhoneNumberList);
        }
        {
            ServiceEdition theServiceEdition;
            theServiceEdition = this.getServiceEdition();
            strategy.appendField(locator, this, "serviceEdition", buffer, theServiceEdition);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Seat)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Seat that = ((Seat) object);
        {
            String lhsSeatTypeCode;
            lhsSeatTypeCode = this.getSeatTypeCode();
            String rhsSeatTypeCode;
            rhsSeatTypeCode = that.getSeatTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seatTypeCode", lhsSeatTypeCode), LocatorUtils.property(thatLocator, "seatTypeCode", rhsSeatTypeCode), lhsSeatTypeCode, rhsSeatTypeCode)) {
                return false;
            }
        }
        {
            String lhsSeatSubTypeCode;
            lhsSeatSubTypeCode = this.getSeatSubTypeCode();
            String rhsSeatSubTypeCode;
            rhsSeatSubTypeCode = that.getSeatSubTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seatSubTypeCode", lhsSeatSubTypeCode), LocatorUtils.property(thatLocator, "seatSubTypeCode", rhsSeatSubTypeCode), lhsSeatSubTypeCode, rhsSeatSubTypeCode)) {
                return false;
            }
        }
        {
            long lhsSeatGroupId;
            lhsSeatGroupId = (true?this.getSeatGroupId(): 0L);
            long rhsSeatGroupId;
            rhsSeatGroupId = (true?that.getSeatGroupId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "seatGroupId", lhsSeatGroupId), LocatorUtils.property(thatLocator, "seatGroupId", rhsSeatGroupId), lhsSeatGroupId, rhsSeatGroupId)) {
                return false;
            }
        }
        {
            List<PhoneNumberType> lhsAssociatedPhoneNumberList;
            lhsAssociatedPhoneNumberList = (((this.associatedPhoneNumberList!= null)&&(!this.associatedPhoneNumberList.isEmpty()))?this.getAssociatedPhoneNumberList():null);
            List<PhoneNumberType> rhsAssociatedPhoneNumberList;
            rhsAssociatedPhoneNumberList = (((that.associatedPhoneNumberList!= null)&&(!that.associatedPhoneNumberList.isEmpty()))?that.getAssociatedPhoneNumberList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedPhoneNumberList", lhsAssociatedPhoneNumberList), LocatorUtils.property(thatLocator, "associatedPhoneNumberList", rhsAssociatedPhoneNumberList), lhsAssociatedPhoneNumberList, rhsAssociatedPhoneNumberList)) {
                return false;
            }
        }
        {
            ServiceEdition lhsServiceEdition;
            lhsServiceEdition = this.getServiceEdition();
            ServiceEdition rhsServiceEdition;
            rhsServiceEdition = that.getServiceEdition();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceEdition", lhsServiceEdition), LocatorUtils.property(thatLocator, "serviceEdition", rhsServiceEdition), lhsServiceEdition, rhsServiceEdition)) {
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
