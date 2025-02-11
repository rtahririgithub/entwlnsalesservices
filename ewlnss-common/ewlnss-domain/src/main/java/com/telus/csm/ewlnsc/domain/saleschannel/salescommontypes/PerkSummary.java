
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for PerkSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PerkSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="perkProgramId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perkProgramCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perkProgramDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="perkOfferId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perkOfferDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="perkOfferSystemId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}SystemID" minOccurs="0"/>
 *         &lt;element name="perkOfferPerspectiveDate" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/WirelessSubscriberOfferInformationTypes_v2}PerspectiveDate" minOccurs="0"/>
 *         &lt;element name="eligibleProductList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="eligibleOfferIdList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PerkSummary", propOrder = {
    "perkProgramId",
    "perkProgramCode",
    "perkProgramDescriptionList",
    "perkOfferId",
    "perkOfferDescriptionList",
    "perkOfferSystemId",
    "perkOfferPerspectiveDate",
    "eligibleProductList",
    "eligibleOfferIdList"
})
@XmlSeeAlso({
    WirelessPerk.class,
    WirelinePerk.class
})
public class PerkSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String perkProgramId;
    protected String perkProgramCode;
    protected List<Description> perkProgramDescriptionList;
    protected String perkOfferId;
    protected List<Description> perkOfferDescriptionList;
    protected String perkOfferSystemId;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    protected Date perkOfferPerspectiveDate;
    protected List<String> eligibleProductList;
    protected List<String> eligibleOfferIdList;

    /**
     * Gets the value of the perkProgramId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerkProgramId() {
        return perkProgramId;
    }

    /**
     * Sets the value of the perkProgramId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerkProgramId(String value) {
        this.perkProgramId = value;
    }

    /**
     * Gets the value of the perkProgramCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerkProgramCode() {
        return perkProgramCode;
    }

    /**
     * Sets the value of the perkProgramCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerkProgramCode(String value) {
        this.perkProgramCode = value;
    }

    /**
     * Gets the value of the perkProgramDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perkProgramDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPerkProgramDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getPerkProgramDescriptionList() {
        if (perkProgramDescriptionList == null) {
            perkProgramDescriptionList = new ArrayList<Description>();
        }
        return this.perkProgramDescriptionList;
    }

    /**
     * Gets the value of the perkOfferId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerkOfferId() {
        return perkOfferId;
    }

    /**
     * Sets the value of the perkOfferId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerkOfferId(String value) {
        this.perkOfferId = value;
    }

    /**
     * Gets the value of the perkOfferDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the perkOfferDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPerkOfferDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getPerkOfferDescriptionList() {
        if (perkOfferDescriptionList == null) {
            perkOfferDescriptionList = new ArrayList<Description>();
        }
        return this.perkOfferDescriptionList;
    }

    /**
     * Gets the value of the perkOfferSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPerkOfferSystemId() {
        return perkOfferSystemId;
    }

    /**
     * Sets the value of the perkOfferSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerkOfferSystemId(String value) {
        this.perkOfferSystemId = value;
    }

    /**
     * Gets the value of the perkOfferPerspectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPerkOfferPerspectiveDate() {
        return perkOfferPerspectiveDate;
    }

    /**
     * Sets the value of the perkOfferPerspectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerkOfferPerspectiveDate(Date value) {
        this.perkOfferPerspectiveDate = value;
    }

    /**
     * Gets the value of the eligibleProductList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibleProductList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibleProductList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEligibleProductList() {
        if (eligibleProductList == null) {
            eligibleProductList = new ArrayList<String>();
        }
        return this.eligibleProductList;
    }

    /**
     * Gets the value of the eligibleOfferIdList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the eligibleOfferIdList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEligibleOfferIdList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getEligibleOfferIdList() {
        if (eligibleOfferIdList == null) {
            eligibleOfferIdList = new ArrayList<String>();
        }
        return this.eligibleOfferIdList;
    }

    /**
     * Sets the value of the perkProgramDescriptionList property.
     * 
     * @param perkProgramDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setPerkProgramDescriptionList(List<Description> perkProgramDescriptionList) {
        this.perkProgramDescriptionList = perkProgramDescriptionList;
    }

    /**
     * Sets the value of the perkOfferDescriptionList property.
     * 
     * @param perkOfferDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setPerkOfferDescriptionList(List<Description> perkOfferDescriptionList) {
        this.perkOfferDescriptionList = perkOfferDescriptionList;
    }

    /**
     * Sets the value of the eligibleProductList property.
     * 
     * @param eligibleProductList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibleProductList(List<String> eligibleProductList) {
        this.eligibleProductList = eligibleProductList;
    }

    /**
     * Sets the value of the eligibleOfferIdList property.
     * 
     * @param eligibleOfferIdList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibleOfferIdList(List<String> eligibleOfferIdList) {
        this.eligibleOfferIdList = eligibleOfferIdList;
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
            String thePerkProgramId;
            thePerkProgramId = this.getPerkProgramId();
            strategy.appendField(locator, this, "perkProgramId", buffer, thePerkProgramId);
        }
        {
            String thePerkProgramCode;
            thePerkProgramCode = this.getPerkProgramCode();
            strategy.appendField(locator, this, "perkProgramCode", buffer, thePerkProgramCode);
        }
        {
            List<Description> thePerkProgramDescriptionList;
            thePerkProgramDescriptionList = (((this.perkProgramDescriptionList!= null)&&(!this.perkProgramDescriptionList.isEmpty()))?this.getPerkProgramDescriptionList():null);
            strategy.appendField(locator, this, "perkProgramDescriptionList", buffer, thePerkProgramDescriptionList);
        }
        {
            String thePerkOfferId;
            thePerkOfferId = this.getPerkOfferId();
            strategy.appendField(locator, this, "perkOfferId", buffer, thePerkOfferId);
        }
        {
            List<Description> thePerkOfferDescriptionList;
            thePerkOfferDescriptionList = (((this.perkOfferDescriptionList!= null)&&(!this.perkOfferDescriptionList.isEmpty()))?this.getPerkOfferDescriptionList():null);
            strategy.appendField(locator, this, "perkOfferDescriptionList", buffer, thePerkOfferDescriptionList);
        }
        {
            String thePerkOfferSystemId;
            thePerkOfferSystemId = this.getPerkOfferSystemId();
            strategy.appendField(locator, this, "perkOfferSystemId", buffer, thePerkOfferSystemId);
        }
        {
            Date thePerkOfferPerspectiveDate;
            thePerkOfferPerspectiveDate = this.getPerkOfferPerspectiveDate();
            strategy.appendField(locator, this, "perkOfferPerspectiveDate", buffer, thePerkOfferPerspectiveDate);
        }
        {
            List<String> theEligibleProductList;
            theEligibleProductList = (((this.eligibleProductList!= null)&&(!this.eligibleProductList.isEmpty()))?this.getEligibleProductList():null);
            strategy.appendField(locator, this, "eligibleProductList", buffer, theEligibleProductList);
        }
        {
            List<String> theEligibleOfferIdList;
            theEligibleOfferIdList = (((this.eligibleOfferIdList!= null)&&(!this.eligibleOfferIdList.isEmpty()))?this.getEligibleOfferIdList():null);
            strategy.appendField(locator, this, "eligibleOfferIdList", buffer, theEligibleOfferIdList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof PerkSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final PerkSummary that = ((PerkSummary) object);
        {
            String lhsPerkProgramId;
            lhsPerkProgramId = this.getPerkProgramId();
            String rhsPerkProgramId;
            rhsPerkProgramId = that.getPerkProgramId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkProgramId", lhsPerkProgramId), LocatorUtils.property(thatLocator, "perkProgramId", rhsPerkProgramId), lhsPerkProgramId, rhsPerkProgramId)) {
                return false;
            }
        }
        {
            String lhsPerkProgramCode;
            lhsPerkProgramCode = this.getPerkProgramCode();
            String rhsPerkProgramCode;
            rhsPerkProgramCode = that.getPerkProgramCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkProgramCode", lhsPerkProgramCode), LocatorUtils.property(thatLocator, "perkProgramCode", rhsPerkProgramCode), lhsPerkProgramCode, rhsPerkProgramCode)) {
                return false;
            }
        }
        {
            List<Description> lhsPerkProgramDescriptionList;
            lhsPerkProgramDescriptionList = (((this.perkProgramDescriptionList!= null)&&(!this.perkProgramDescriptionList.isEmpty()))?this.getPerkProgramDescriptionList():null);
            List<Description> rhsPerkProgramDescriptionList;
            rhsPerkProgramDescriptionList = (((that.perkProgramDescriptionList!= null)&&(!that.perkProgramDescriptionList.isEmpty()))?that.getPerkProgramDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkProgramDescriptionList", lhsPerkProgramDescriptionList), LocatorUtils.property(thatLocator, "perkProgramDescriptionList", rhsPerkProgramDescriptionList), lhsPerkProgramDescriptionList, rhsPerkProgramDescriptionList)) {
                return false;
            }
        }
        {
            String lhsPerkOfferId;
            lhsPerkOfferId = this.getPerkOfferId();
            String rhsPerkOfferId;
            rhsPerkOfferId = that.getPerkOfferId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkOfferId", lhsPerkOfferId), LocatorUtils.property(thatLocator, "perkOfferId", rhsPerkOfferId), lhsPerkOfferId, rhsPerkOfferId)) {
                return false;
            }
        }
        {
            List<Description> lhsPerkOfferDescriptionList;
            lhsPerkOfferDescriptionList = (((this.perkOfferDescriptionList!= null)&&(!this.perkOfferDescriptionList.isEmpty()))?this.getPerkOfferDescriptionList():null);
            List<Description> rhsPerkOfferDescriptionList;
            rhsPerkOfferDescriptionList = (((that.perkOfferDescriptionList!= null)&&(!that.perkOfferDescriptionList.isEmpty()))?that.getPerkOfferDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkOfferDescriptionList", lhsPerkOfferDescriptionList), LocatorUtils.property(thatLocator, "perkOfferDescriptionList", rhsPerkOfferDescriptionList), lhsPerkOfferDescriptionList, rhsPerkOfferDescriptionList)) {
                return false;
            }
        }
        {
            String lhsPerkOfferSystemId;
            lhsPerkOfferSystemId = this.getPerkOfferSystemId();
            String rhsPerkOfferSystemId;
            rhsPerkOfferSystemId = that.getPerkOfferSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkOfferSystemId", lhsPerkOfferSystemId), LocatorUtils.property(thatLocator, "perkOfferSystemId", rhsPerkOfferSystemId), lhsPerkOfferSystemId, rhsPerkOfferSystemId)) {
                return false;
            }
        }
        {
            Date lhsPerkOfferPerspectiveDate;
            lhsPerkOfferPerspectiveDate = this.getPerkOfferPerspectiveDate();
            Date rhsPerkOfferPerspectiveDate;
            rhsPerkOfferPerspectiveDate = that.getPerkOfferPerspectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perkOfferPerspectiveDate", lhsPerkOfferPerspectiveDate), LocatorUtils.property(thatLocator, "perkOfferPerspectiveDate", rhsPerkOfferPerspectiveDate), lhsPerkOfferPerspectiveDate, rhsPerkOfferPerspectiveDate)) {
                return false;
            }
        }
        {
            List<String> lhsEligibleProductList;
            lhsEligibleProductList = (((this.eligibleProductList!= null)&&(!this.eligibleProductList.isEmpty()))?this.getEligibleProductList():null);
            List<String> rhsEligibleProductList;
            rhsEligibleProductList = (((that.eligibleProductList!= null)&&(!that.eligibleProductList.isEmpty()))?that.getEligibleProductList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleProductList", lhsEligibleProductList), LocatorUtils.property(thatLocator, "eligibleProductList", rhsEligibleProductList), lhsEligibleProductList, rhsEligibleProductList)) {
                return false;
            }
        }
        {
            List<String> lhsEligibleOfferIdList;
            lhsEligibleOfferIdList = (((this.eligibleOfferIdList!= null)&&(!this.eligibleOfferIdList.isEmpty()))?this.getEligibleOfferIdList():null);
            List<String> rhsEligibleOfferIdList;
            rhsEligibleOfferIdList = (((that.eligibleOfferIdList!= null)&&(!that.eligibleOfferIdList.isEmpty()))?that.getEligibleOfferIdList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibleOfferIdList", lhsEligibleOfferIdList), LocatorUtils.property(thatLocator, "eligibleOfferIdList", rhsEligibleOfferIdList), lhsEligibleOfferIdList, rhsEligibleOfferIdList)) {
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
