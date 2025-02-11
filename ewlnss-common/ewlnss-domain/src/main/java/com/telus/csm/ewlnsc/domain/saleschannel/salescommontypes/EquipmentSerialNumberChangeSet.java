
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for EquipmentSerialNumberChangeSet complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EquipmentSerialNumberChangeSet">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="simDetail" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="simProductCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="simSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="handsetDetail" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="handsetProductCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="handsetSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
@XmlType(name = "EquipmentSerialNumberChangeSet", propOrder = {
    "simDetail",
    "handsetDetail"
})
public class EquipmentSerialNumberChangeSet
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected EquipmentSerialNumberChangeSet.SimDetail simDetail;
    protected EquipmentSerialNumberChangeSet.HandsetDetail handsetDetail;

    /**
     * Gets the value of the simDetail property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentSerialNumberChangeSet.SimDetail }
     *     
     */
    public EquipmentSerialNumberChangeSet.SimDetail getSimDetail() {
        return simDetail;
    }

    /**
     * Sets the value of the simDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentSerialNumberChangeSet.SimDetail }
     *     
     */
    public void setSimDetail(EquipmentSerialNumberChangeSet.SimDetail value) {
        this.simDetail = value;
    }

    /**
     * Gets the value of the handsetDetail property.
     * 
     * @return
     *     possible object is
     *     {@link EquipmentSerialNumberChangeSet.HandsetDetail }
     *     
     */
    public EquipmentSerialNumberChangeSet.HandsetDetail getHandsetDetail() {
        return handsetDetail;
    }

    /**
     * Sets the value of the handsetDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipmentSerialNumberChangeSet.HandsetDetail }
     *     
     */
    public void setHandsetDetail(EquipmentSerialNumberChangeSet.HandsetDetail value) {
        this.handsetDetail = value;
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
            EquipmentSerialNumberChangeSet.SimDetail theSimDetail;
            theSimDetail = this.getSimDetail();
            strategy.appendField(locator, this, "simDetail", buffer, theSimDetail);
        }
        {
            EquipmentSerialNumberChangeSet.HandsetDetail theHandsetDetail;
            theHandsetDetail = this.getHandsetDetail();
            strategy.appendField(locator, this, "handsetDetail", buffer, theHandsetDetail);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EquipmentSerialNumberChangeSet)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EquipmentSerialNumberChangeSet that = ((EquipmentSerialNumberChangeSet) object);
        {
            EquipmentSerialNumberChangeSet.SimDetail lhsSimDetail;
            lhsSimDetail = this.getSimDetail();
            EquipmentSerialNumberChangeSet.SimDetail rhsSimDetail;
            rhsSimDetail = that.getSimDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "simDetail", lhsSimDetail), LocatorUtils.property(thatLocator, "simDetail", rhsSimDetail), lhsSimDetail, rhsSimDetail)) {
                return false;
            }
        }
        {
            EquipmentSerialNumberChangeSet.HandsetDetail lhsHandsetDetail;
            lhsHandsetDetail = this.getHandsetDetail();
            EquipmentSerialNumberChangeSet.HandsetDetail rhsHandsetDetail;
            rhsHandsetDetail = that.getHandsetDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetDetail", lhsHandsetDetail), LocatorUtils.property(thatLocator, "handsetDetail", rhsHandsetDetail), lhsHandsetDetail, rhsHandsetDetail)) {
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
     *         &lt;element name="handsetProductCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="handsetSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "handsetProductCd",
        "handsetSerialNumber"
    })
    public static class HandsetDetail
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String handsetProductCd;
        @XmlElement(required = true)
        protected String handsetSerialNumber;

        /**
         * Gets the value of the handsetProductCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHandsetProductCd() {
            return handsetProductCd;
        }

        /**
         * Sets the value of the handsetProductCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHandsetProductCd(String value) {
            this.handsetProductCd = value;
        }

        /**
         * Gets the value of the handsetSerialNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getHandsetSerialNumber() {
            return handsetSerialNumber;
        }

        /**
         * Sets the value of the handsetSerialNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setHandsetSerialNumber(String value) {
            this.handsetSerialNumber = value;
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
                String theHandsetProductCd;
                theHandsetProductCd = this.getHandsetProductCd();
                strategy.appendField(locator, this, "handsetProductCd", buffer, theHandsetProductCd);
            }
            {
                String theHandsetSerialNumber;
                theHandsetSerialNumber = this.getHandsetSerialNumber();
                strategy.appendField(locator, this, "handsetSerialNumber", buffer, theHandsetSerialNumber);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof EquipmentSerialNumberChangeSet.HandsetDetail)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final EquipmentSerialNumberChangeSet.HandsetDetail that = ((EquipmentSerialNumberChangeSet.HandsetDetail) object);
            {
                String lhsHandsetProductCd;
                lhsHandsetProductCd = this.getHandsetProductCd();
                String rhsHandsetProductCd;
                rhsHandsetProductCd = that.getHandsetProductCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetProductCd", lhsHandsetProductCd), LocatorUtils.property(thatLocator, "handsetProductCd", rhsHandsetProductCd), lhsHandsetProductCd, rhsHandsetProductCd)) {
                    return false;
                }
            }
            {
                String lhsHandsetSerialNumber;
                lhsHandsetSerialNumber = this.getHandsetSerialNumber();
                String rhsHandsetSerialNumber;
                rhsHandsetSerialNumber = that.getHandsetSerialNumber();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetSerialNumber", lhsHandsetSerialNumber), LocatorUtils.property(thatLocator, "handsetSerialNumber", rhsHandsetSerialNumber), lhsHandsetSerialNumber, rhsHandsetSerialNumber)) {
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
     *         &lt;element name="simProductCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="simSerialNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "simProductCd",
        "simSerialNumber"
    })
    public static class SimDetail
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String simProductCd;
        @XmlElement(required = true)
        protected String simSerialNumber;

        /**
         * Gets the value of the simProductCd property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSimProductCd() {
            return simProductCd;
        }

        /**
         * Sets the value of the simProductCd property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSimProductCd(String value) {
            this.simProductCd = value;
        }

        /**
         * Gets the value of the simSerialNumber property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSimSerialNumber() {
            return simSerialNumber;
        }

        /**
         * Sets the value of the simSerialNumber property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSimSerialNumber(String value) {
            this.simSerialNumber = value;
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
                String theSimProductCd;
                theSimProductCd = this.getSimProductCd();
                strategy.appendField(locator, this, "simProductCd", buffer, theSimProductCd);
            }
            {
                String theSimSerialNumber;
                theSimSerialNumber = this.getSimSerialNumber();
                strategy.appendField(locator, this, "simSerialNumber", buffer, theSimSerialNumber);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof EquipmentSerialNumberChangeSet.SimDetail)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final EquipmentSerialNumberChangeSet.SimDetail that = ((EquipmentSerialNumberChangeSet.SimDetail) object);
            {
                String lhsSimProductCd;
                lhsSimProductCd = this.getSimProductCd();
                String rhsSimProductCd;
                rhsSimProductCd = that.getSimProductCd();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "simProductCd", lhsSimProductCd), LocatorUtils.property(thatLocator, "simProductCd", rhsSimProductCd), lhsSimProductCd, rhsSimProductCd)) {
                    return false;
                }
            }
            {
                String lhsSimSerialNumber;
                lhsSimSerialNumber = this.getSimSerialNumber();
                String rhsSimSerialNumber;
                rhsSimSerialNumber = that.getSimSerialNumber();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "simSerialNumber", lhsSimSerialNumber), LocatorUtils.property(thatLocator, "simSerialNumber", rhsSimSerialNumber), lhsSimSerialNumber, rhsSimSerialNumber)) {
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
