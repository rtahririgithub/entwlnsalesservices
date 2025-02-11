
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 * <p>Java class for Address complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Address">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/TelusCommonAddressTypes_v5}Address">
 *       &lt;sequence>
 *         &lt;element name="poBox" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ruralNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetVector" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="COID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="clliCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Address", propOrder = {
    "poBox",
    "ruralNumber",
    "streetVector",
    "coid",
    "clliCd"
})
@XmlSeeAlso({
    ServiceAddress.class
})
public class Address
    extends com.telus.tmi.xmlschema.xsd.enterprise.basetypes.teluscommonaddresstypes_v5.Address
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String poBox;
    protected String ruralNumber;
    protected String streetVector;
    @XmlElement(name = "COID")
    protected String coid;
    protected String clliCd;

    /**
     * Gets the value of the poBox property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPoBox() {
        return poBox;
    }

    /**
     * Sets the value of the poBox property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPoBox(String value) {
        this.poBox = value;
    }

    /**
     * Gets the value of the ruralNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuralNumber() {
        return ruralNumber;
    }

    /**
     * Sets the value of the ruralNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuralNumber(String value) {
        this.ruralNumber = value;
    }

    /**
     * Gets the value of the streetVector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetVector() {
        return streetVector;
    }

    /**
     * Sets the value of the streetVector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetVector(String value) {
        this.streetVector = value;
    }

    /**
     * Gets the value of the coid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCOID() {
        return coid;
    }

    /**
     * Sets the value of the coid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCOID(String value) {
        this.coid = value;
    }

    /**
     * Gets the value of the clliCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getClliCd() {
        return clliCd;
    }

    /**
     * Sets the value of the clliCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setClliCd(String value) {
        this.clliCd = value;
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
            String thePoBox;
            thePoBox = this.getPoBox();
            strategy.appendField(locator, this, "poBox", buffer, thePoBox);
        }
        {
            String theRuralNumber;
            theRuralNumber = this.getRuralNumber();
            strategy.appendField(locator, this, "ruralNumber", buffer, theRuralNumber);
        }
        {
            String theStreetVector;
            theStreetVector = this.getStreetVector();
            strategy.appendField(locator, this, "streetVector", buffer, theStreetVector);
        }
        {
            String theCOID;
            theCOID = this.getCOID();
            strategy.appendField(locator, this, "coid", buffer, theCOID);
        }
        {
            String theClliCd;
            theClliCd = this.getClliCd();
            strategy.appendField(locator, this, "clliCd", buffer, theClliCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Address)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final Address that = ((Address) object);
        {
            String lhsPoBox;
            lhsPoBox = this.getPoBox();
            String rhsPoBox;
            rhsPoBox = that.getPoBox();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "poBox", lhsPoBox), LocatorUtils.property(thatLocator, "poBox", rhsPoBox), lhsPoBox, rhsPoBox)) {
                return false;
            }
        }
        {
            String lhsRuralNumber;
            lhsRuralNumber = this.getRuralNumber();
            String rhsRuralNumber;
            rhsRuralNumber = that.getRuralNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ruralNumber", lhsRuralNumber), LocatorUtils.property(thatLocator, "ruralNumber", rhsRuralNumber), lhsRuralNumber, rhsRuralNumber)) {
                return false;
            }
        }
        {
            String lhsStreetVector;
            lhsStreetVector = this.getStreetVector();
            String rhsStreetVector;
            rhsStreetVector = that.getStreetVector();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetVector", lhsStreetVector), LocatorUtils.property(thatLocator, "streetVector", rhsStreetVector), lhsStreetVector, rhsStreetVector)) {
                return false;
            }
        }
        {
            String lhsCOID;
            lhsCOID = this.getCOID();
            String rhsCOID;
            rhsCOID = that.getCOID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "coid", lhsCOID), LocatorUtils.property(thatLocator, "coid", rhsCOID), lhsCOID, rhsCOID)) {
                return false;
            }
        }
        {
            String lhsClliCd;
            lhsClliCd = this.getClliCd();
            String rhsClliCd;
            rhsClliCd = that.getClliCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "clliCd", lhsClliCd), LocatorUtils.property(thatLocator, "clliCd", rhsClliCd), lhsClliCd, rhsClliCd)) {
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
