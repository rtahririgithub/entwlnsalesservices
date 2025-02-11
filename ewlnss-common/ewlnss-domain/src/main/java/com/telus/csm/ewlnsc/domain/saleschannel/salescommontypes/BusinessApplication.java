
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualNameList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for BusinessApplication complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BusinessApplication">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="businessAppName" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualNameList"/>
 *         &lt;element name="associatedServiceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="quantityNum" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="businessAppRegistrationEntryList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="entryValueTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BusinessApplication", propOrder = {
    "businessAppName",
    "associatedServiceCode",
    "quantityNum",
    "businessAppRegistrationEntryList"
})
public class BusinessApplication
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected MultilingualNameList businessAppName;
    @XmlElement(required = true)
    protected String associatedServiceCode;
    protected Long quantityNum;
    protected List<BusinessApplication.BusinessAppRegistrationEntryList> businessAppRegistrationEntryList;

    /**
     * Gets the value of the businessAppName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualNameList }
     *     
     */
    public MultilingualNameList getBusinessAppName() {
        return businessAppName;
    }

    /**
     * Sets the value of the businessAppName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualNameList }
     *     
     */
    public void setBusinessAppName(MultilingualNameList value) {
        this.businessAppName = value;
    }

    /**
     * Gets the value of the associatedServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssociatedServiceCode() {
        return associatedServiceCode;
    }

    /**
     * Sets the value of the associatedServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssociatedServiceCode(String value) {
        this.associatedServiceCode = value;
    }

    /**
     * Gets the value of the quantityNum property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getQuantityNum() {
        return quantityNum;
    }

    /**
     * Sets the value of the quantityNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setQuantityNum(Long value) {
        this.quantityNum = value;
    }

    /**
     * Gets the value of the businessAppRegistrationEntryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the businessAppRegistrationEntryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBusinessAppRegistrationEntryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BusinessApplication.BusinessAppRegistrationEntryList }
     * 
     * 
     */
    public List<BusinessApplication.BusinessAppRegistrationEntryList> getBusinessAppRegistrationEntryList() {
        if (businessAppRegistrationEntryList == null) {
            businessAppRegistrationEntryList = new ArrayList<BusinessApplication.BusinessAppRegistrationEntryList>();
        }
        return this.businessAppRegistrationEntryList;
    }

    /**
     * Sets the value of the businessAppRegistrationEntryList property.
     * 
     * @param businessAppRegistrationEntryList
     *     allowed object is
     *     {@link BusinessApplication.BusinessAppRegistrationEntryList }
     *     
     */
    public void setBusinessAppRegistrationEntryList(List<BusinessApplication.BusinessAppRegistrationEntryList> businessAppRegistrationEntryList) {
        this.businessAppRegistrationEntryList = businessAppRegistrationEntryList;
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
            MultilingualNameList theBusinessAppName;
            theBusinessAppName = this.getBusinessAppName();
            strategy.appendField(locator, this, "businessAppName", buffer, theBusinessAppName);
        }
        {
            String theAssociatedServiceCode;
            theAssociatedServiceCode = this.getAssociatedServiceCode();
            strategy.appendField(locator, this, "associatedServiceCode", buffer, theAssociatedServiceCode);
        }
        {
            Long theQuantityNum;
            theQuantityNum = this.getQuantityNum();
            strategy.appendField(locator, this, "quantityNum", buffer, theQuantityNum);
        }
        {
            List<BusinessApplication.BusinessAppRegistrationEntryList> theBusinessAppRegistrationEntryList;
            theBusinessAppRegistrationEntryList = (((this.businessAppRegistrationEntryList!= null)&&(!this.businessAppRegistrationEntryList.isEmpty()))?this.getBusinessAppRegistrationEntryList():null);
            strategy.appendField(locator, this, "businessAppRegistrationEntryList", buffer, theBusinessAppRegistrationEntryList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BusinessApplication)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BusinessApplication that = ((BusinessApplication) object);
        {
            MultilingualNameList lhsBusinessAppName;
            lhsBusinessAppName = this.getBusinessAppName();
            MultilingualNameList rhsBusinessAppName;
            rhsBusinessAppName = that.getBusinessAppName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessAppName", lhsBusinessAppName), LocatorUtils.property(thatLocator, "businessAppName", rhsBusinessAppName), lhsBusinessAppName, rhsBusinessAppName)) {
                return false;
            }
        }
        {
            String lhsAssociatedServiceCode;
            lhsAssociatedServiceCode = this.getAssociatedServiceCode();
            String rhsAssociatedServiceCode;
            rhsAssociatedServiceCode = that.getAssociatedServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "associatedServiceCode", lhsAssociatedServiceCode), LocatorUtils.property(thatLocator, "associatedServiceCode", rhsAssociatedServiceCode), lhsAssociatedServiceCode, rhsAssociatedServiceCode)) {
                return false;
            }
        }
        {
            Long lhsQuantityNum;
            lhsQuantityNum = this.getQuantityNum();
            Long rhsQuantityNum;
            rhsQuantityNum = that.getQuantityNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "quantityNum", lhsQuantityNum), LocatorUtils.property(thatLocator, "quantityNum", rhsQuantityNum), lhsQuantityNum, rhsQuantityNum)) {
                return false;
            }
        }
        {
            List<BusinessApplication.BusinessAppRegistrationEntryList> lhsBusinessAppRegistrationEntryList;
            lhsBusinessAppRegistrationEntryList = (((this.businessAppRegistrationEntryList!= null)&&(!this.businessAppRegistrationEntryList.isEmpty()))?this.getBusinessAppRegistrationEntryList():null);
            List<BusinessApplication.BusinessAppRegistrationEntryList> rhsBusinessAppRegistrationEntryList;
            rhsBusinessAppRegistrationEntryList = (((that.businessAppRegistrationEntryList!= null)&&(!that.businessAppRegistrationEntryList.isEmpty()))?that.getBusinessAppRegistrationEntryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "businessAppRegistrationEntryList", lhsBusinessAppRegistrationEntryList), LocatorUtils.property(thatLocator, "businessAppRegistrationEntryList", rhsBusinessAppRegistrationEntryList), lhsBusinessAppRegistrationEntryList, rhsBusinessAppRegistrationEntryList)) {
                return false;
            }
        }
        return true;
    }

    public boolean equals(Object object) {
        final EqualsStrategy strategy = JAXBEqualsStrategy.INSTANCE;
        return equals(null, null, object, strategy);
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entryName" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="entryValueTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entryName",
        "entryValueTxt"
    })
    public static class BusinessAppRegistrationEntryList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String entryName;
        @XmlElement(required = true)
        protected String entryValueTxt;

        /**
         * Gets the value of the entryName property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEntryName() {
            return entryName;
        }

        /**
         * Sets the value of the entryName property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEntryName(String value) {
            this.entryName = value;
        }

        /**
         * Gets the value of the entryValueTxt property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getEntryValueTxt() {
            return entryValueTxt;
        }

        /**
         * Sets the value of the entryValueTxt property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setEntryValueTxt(String value) {
            this.entryValueTxt = value;
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
                String theEntryName;
                theEntryName = this.getEntryName();
                strategy.appendField(locator, this, "entryName", buffer, theEntryName);
            }
            {
                String theEntryValueTxt;
                theEntryValueTxt = this.getEntryValueTxt();
                strategy.appendField(locator, this, "entryValueTxt", buffer, theEntryValueTxt);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof BusinessApplication.BusinessAppRegistrationEntryList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final BusinessApplication.BusinessAppRegistrationEntryList that = ((BusinessApplication.BusinessAppRegistrationEntryList) object);
            {
                String lhsEntryName;
                lhsEntryName = this.getEntryName();
                String rhsEntryName;
                rhsEntryName = that.getEntryName();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "entryName", lhsEntryName), LocatorUtils.property(thatLocator, "entryName", rhsEntryName), lhsEntryName, rhsEntryName)) {
                    return false;
                }
            }
            {
                String lhsEntryValueTxt;
                lhsEntryValueTxt = this.getEntryValueTxt();
                String rhsEntryValueTxt;
                rhsEntryValueTxt = that.getEntryValueTxt();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "entryValueTxt", lhsEntryValueTxt), LocatorUtils.property(thatLocator, "entryValueTxt", rhsEntryValueTxt), lhsEntryValueTxt, rhsEntryValueTxt)) {
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

}
