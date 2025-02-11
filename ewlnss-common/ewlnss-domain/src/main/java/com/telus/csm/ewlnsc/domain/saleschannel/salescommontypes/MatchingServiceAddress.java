
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
 * <p>Java class for MatchingServiceAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MatchingServiceAddress">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ServiceAddress"/>
 *         &lt;element name="startIndexNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="likeStreetItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}LikeStreetItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="likeMunicipalityItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}LikeMunicipalityItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="likeHouseItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}LikeHouseItem" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="likeApartmentItemList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}LikeApartmentItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MatchingServiceAddress", propOrder = {
    "address",
    "startIndexNum",
    "likeStreetItemList",
    "likeMunicipalityItemList",
    "likeHouseItemList",
    "likeApartmentItemList"
})
public class MatchingServiceAddress
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected ServiceAddress address;
    protected String startIndexNum;
    protected List<LikeStreetItem> likeStreetItemList;
    protected List<LikeMunicipalityItem> likeMunicipalityItemList;
    protected List<LikeHouseItem> likeHouseItemList;
    protected List<LikeApartmentItem> likeApartmentItemList;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link ServiceAddress }
     *     
     */
    public ServiceAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link ServiceAddress }
     *     
     */
    public void setAddress(ServiceAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the startIndexNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStartIndexNum() {
        return startIndexNum;
    }

    /**
     * Sets the value of the startIndexNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStartIndexNum(String value) {
        this.startIndexNum = value;
    }

    /**
     * Gets the value of the likeStreetItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the likeStreetItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLikeStreetItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LikeStreetItem }
     * 
     * 
     */
    public List<LikeStreetItem> getLikeStreetItemList() {
        if (likeStreetItemList == null) {
            likeStreetItemList = new ArrayList<LikeStreetItem>();
        }
        return this.likeStreetItemList;
    }

    /**
     * Gets the value of the likeMunicipalityItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the likeMunicipalityItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLikeMunicipalityItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LikeMunicipalityItem }
     * 
     * 
     */
    public List<LikeMunicipalityItem> getLikeMunicipalityItemList() {
        if (likeMunicipalityItemList == null) {
            likeMunicipalityItemList = new ArrayList<LikeMunicipalityItem>();
        }
        return this.likeMunicipalityItemList;
    }

    /**
     * Gets the value of the likeHouseItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the likeHouseItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLikeHouseItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LikeHouseItem }
     * 
     * 
     */
    public List<LikeHouseItem> getLikeHouseItemList() {
        if (likeHouseItemList == null) {
            likeHouseItemList = new ArrayList<LikeHouseItem>();
        }
        return this.likeHouseItemList;
    }

    /**
     * Gets the value of the likeApartmentItemList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the likeApartmentItemList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLikeApartmentItemList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LikeApartmentItem }
     * 
     * 
     */
    public List<LikeApartmentItem> getLikeApartmentItemList() {
        if (likeApartmentItemList == null) {
            likeApartmentItemList = new ArrayList<LikeApartmentItem>();
        }
        return this.likeApartmentItemList;
    }

    /**
     * Sets the value of the likeStreetItemList property.
     * 
     * @param likeStreetItemList
     *     allowed object is
     *     {@link LikeStreetItem }
     *     
     */
    public void setLikeStreetItemList(List<LikeStreetItem> likeStreetItemList) {
        this.likeStreetItemList = likeStreetItemList;
    }

    /**
     * Sets the value of the likeMunicipalityItemList property.
     * 
     * @param likeMunicipalityItemList
     *     allowed object is
     *     {@link LikeMunicipalityItem }
     *     
     */
    public void setLikeMunicipalityItemList(List<LikeMunicipalityItem> likeMunicipalityItemList) {
        this.likeMunicipalityItemList = likeMunicipalityItemList;
    }

    /**
     * Sets the value of the likeHouseItemList property.
     * 
     * @param likeHouseItemList
     *     allowed object is
     *     {@link LikeHouseItem }
     *     
     */
    public void setLikeHouseItemList(List<LikeHouseItem> likeHouseItemList) {
        this.likeHouseItemList = likeHouseItemList;
    }

    /**
     * Sets the value of the likeApartmentItemList property.
     * 
     * @param likeApartmentItemList
     *     allowed object is
     *     {@link LikeApartmentItem }
     *     
     */
    public void setLikeApartmentItemList(List<LikeApartmentItem> likeApartmentItemList) {
        this.likeApartmentItemList = likeApartmentItemList;
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
            ServiceAddress theAddress;
            theAddress = this.getAddress();
            strategy.appendField(locator, this, "address", buffer, theAddress);
        }
        {
            String theStartIndexNum;
            theStartIndexNum = this.getStartIndexNum();
            strategy.appendField(locator, this, "startIndexNum", buffer, theStartIndexNum);
        }
        {
            List<LikeStreetItem> theLikeStreetItemList;
            theLikeStreetItemList = (((this.likeStreetItemList!= null)&&(!this.likeStreetItemList.isEmpty()))?this.getLikeStreetItemList():null);
            strategy.appendField(locator, this, "likeStreetItemList", buffer, theLikeStreetItemList);
        }
        {
            List<LikeMunicipalityItem> theLikeMunicipalityItemList;
            theLikeMunicipalityItemList = (((this.likeMunicipalityItemList!= null)&&(!this.likeMunicipalityItemList.isEmpty()))?this.getLikeMunicipalityItemList():null);
            strategy.appendField(locator, this, "likeMunicipalityItemList", buffer, theLikeMunicipalityItemList);
        }
        {
            List<LikeHouseItem> theLikeHouseItemList;
            theLikeHouseItemList = (((this.likeHouseItemList!= null)&&(!this.likeHouseItemList.isEmpty()))?this.getLikeHouseItemList():null);
            strategy.appendField(locator, this, "likeHouseItemList", buffer, theLikeHouseItemList);
        }
        {
            List<LikeApartmentItem> theLikeApartmentItemList;
            theLikeApartmentItemList = (((this.likeApartmentItemList!= null)&&(!this.likeApartmentItemList.isEmpty()))?this.getLikeApartmentItemList():null);
            strategy.appendField(locator, this, "likeApartmentItemList", buffer, theLikeApartmentItemList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof MatchingServiceAddress)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final MatchingServiceAddress that = ((MatchingServiceAddress) object);
        {
            ServiceAddress lhsAddress;
            lhsAddress = this.getAddress();
            ServiceAddress rhsAddress;
            rhsAddress = that.getAddress();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "address", lhsAddress), LocatorUtils.property(thatLocator, "address", rhsAddress), lhsAddress, rhsAddress)) {
                return false;
            }
        }
        {
            String lhsStartIndexNum;
            lhsStartIndexNum = this.getStartIndexNum();
            String rhsStartIndexNum;
            rhsStartIndexNum = that.getStartIndexNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "startIndexNum", lhsStartIndexNum), LocatorUtils.property(thatLocator, "startIndexNum", rhsStartIndexNum), lhsStartIndexNum, rhsStartIndexNum)) {
                return false;
            }
        }
        {
            List<LikeStreetItem> lhsLikeStreetItemList;
            lhsLikeStreetItemList = (((this.likeStreetItemList!= null)&&(!this.likeStreetItemList.isEmpty()))?this.getLikeStreetItemList():null);
            List<LikeStreetItem> rhsLikeStreetItemList;
            rhsLikeStreetItemList = (((that.likeStreetItemList!= null)&&(!that.likeStreetItemList.isEmpty()))?that.getLikeStreetItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "likeStreetItemList", lhsLikeStreetItemList), LocatorUtils.property(thatLocator, "likeStreetItemList", rhsLikeStreetItemList), lhsLikeStreetItemList, rhsLikeStreetItemList)) {
                return false;
            }
        }
        {
            List<LikeMunicipalityItem> lhsLikeMunicipalityItemList;
            lhsLikeMunicipalityItemList = (((this.likeMunicipalityItemList!= null)&&(!this.likeMunicipalityItemList.isEmpty()))?this.getLikeMunicipalityItemList():null);
            List<LikeMunicipalityItem> rhsLikeMunicipalityItemList;
            rhsLikeMunicipalityItemList = (((that.likeMunicipalityItemList!= null)&&(!that.likeMunicipalityItemList.isEmpty()))?that.getLikeMunicipalityItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "likeMunicipalityItemList", lhsLikeMunicipalityItemList), LocatorUtils.property(thatLocator, "likeMunicipalityItemList", rhsLikeMunicipalityItemList), lhsLikeMunicipalityItemList, rhsLikeMunicipalityItemList)) {
                return false;
            }
        }
        {
            List<LikeHouseItem> lhsLikeHouseItemList;
            lhsLikeHouseItemList = (((this.likeHouseItemList!= null)&&(!this.likeHouseItemList.isEmpty()))?this.getLikeHouseItemList():null);
            List<LikeHouseItem> rhsLikeHouseItemList;
            rhsLikeHouseItemList = (((that.likeHouseItemList!= null)&&(!that.likeHouseItemList.isEmpty()))?that.getLikeHouseItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "likeHouseItemList", lhsLikeHouseItemList), LocatorUtils.property(thatLocator, "likeHouseItemList", rhsLikeHouseItemList), lhsLikeHouseItemList, rhsLikeHouseItemList)) {
                return false;
            }
        }
        {
            List<LikeApartmentItem> lhsLikeApartmentItemList;
            lhsLikeApartmentItemList = (((this.likeApartmentItemList!= null)&&(!this.likeApartmentItemList.isEmpty()))?this.getLikeApartmentItemList():null);
            List<LikeApartmentItem> rhsLikeApartmentItemList;
            rhsLikeApartmentItemList = (((that.likeApartmentItemList!= null)&&(!that.likeApartmentItemList.isEmpty()))?that.getLikeApartmentItemList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "likeApartmentItemList", lhsLikeApartmentItemList), LocatorUtils.property(thatLocator, "likeApartmentItemList", rhsLikeApartmentItemList), lhsLikeApartmentItemList, rhsLikeApartmentItemList)) {
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
