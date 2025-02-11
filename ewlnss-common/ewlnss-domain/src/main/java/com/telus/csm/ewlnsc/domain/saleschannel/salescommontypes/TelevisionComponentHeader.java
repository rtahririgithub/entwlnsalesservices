
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * <p>Java class for TelevisionComponentHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TelevisionComponentHeader">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductHeader">
 *       &lt;sequence>
 *         &lt;element name="televisionQuestionairreList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}TelevisionQuestionairre" maxOccurs="20" minOccurs="0"/>
 *         &lt;element name="appointmentDetail" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AppointmentDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelevisionComponentHeader", propOrder = {
    "televisionQuestionairreList",
    "appointmentDetail"
})
@XmlSeeAlso({
    TelevisionComponent.class
})
public class TelevisionComponentHeader
    extends WirelineProductHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<TelevisionQuestionairre> televisionQuestionairreList;
    protected AppointmentDetail appointmentDetail;

    /**
     * Gets the value of the televisionQuestionairreList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the televisionQuestionairreList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTelevisionQuestionairreList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TelevisionQuestionairre }
     * 
     * 
     */
    public List<TelevisionQuestionairre> getTelevisionQuestionairreList() {
        if (televisionQuestionairreList == null) {
            televisionQuestionairreList = new ArrayList<TelevisionQuestionairre>();
        }
        return this.televisionQuestionairreList;
    }

    /**
     * Gets the value of the appointmentDetail property.
     * 
     * @return
     *     possible object is
     *     {@link AppointmentDetail }
     *     
     */
    public AppointmentDetail getAppointmentDetail() {
        return appointmentDetail;
    }

    /**
     * Sets the value of the appointmentDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link AppointmentDetail }
     *     
     */
    public void setAppointmentDetail(AppointmentDetail value) {
        this.appointmentDetail = value;
    }

    /**
     * Sets the value of the televisionQuestionairreList property.
     * 
     * @param televisionQuestionairreList
     *     allowed object is
     *     {@link TelevisionQuestionairre }
     *     
     */
    public void setTelevisionQuestionairreList(List<TelevisionQuestionairre> televisionQuestionairreList) {
        this.televisionQuestionairreList = televisionQuestionairreList;
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
            List<TelevisionQuestionairre> theTelevisionQuestionairreList;
            theTelevisionQuestionairreList = (((this.televisionQuestionairreList!= null)&&(!this.televisionQuestionairreList.isEmpty()))?this.getTelevisionQuestionairreList():null);
            strategy.appendField(locator, this, "televisionQuestionairreList", buffer, theTelevisionQuestionairreList);
        }
        {
            AppointmentDetail theAppointmentDetail;
            theAppointmentDetail = this.getAppointmentDetail();
            strategy.appendField(locator, this, "appointmentDetail", buffer, theAppointmentDetail);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TelevisionComponentHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final TelevisionComponentHeader that = ((TelevisionComponentHeader) object);
        {
            List<TelevisionQuestionairre> lhsTelevisionQuestionairreList;
            lhsTelevisionQuestionairreList = (((this.televisionQuestionairreList!= null)&&(!this.televisionQuestionairreList.isEmpty()))?this.getTelevisionQuestionairreList():null);
            List<TelevisionQuestionairre> rhsTelevisionQuestionairreList;
            rhsTelevisionQuestionairreList = (((that.televisionQuestionairreList!= null)&&(!that.televisionQuestionairreList.isEmpty()))?that.getTelevisionQuestionairreList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "televisionQuestionairreList", lhsTelevisionQuestionairreList), LocatorUtils.property(thatLocator, "televisionQuestionairreList", rhsTelevisionQuestionairreList), lhsTelevisionQuestionairreList, rhsTelevisionQuestionairreList)) {
                return false;
            }
        }
        {
            AppointmentDetail lhsAppointmentDetail;
            lhsAppointmentDetail = this.getAppointmentDetail();
            AppointmentDetail rhsAppointmentDetail;
            rhsAppointmentDetail = that.getAppointmentDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "appointmentDetail", lhsAppointmentDetail), LocatorUtils.property(thatLocator, "appointmentDetail", rhsAppointmentDetail), lhsAppointmentDetail, rhsAppointmentDetail)) {
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
