
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
 * <p>Java class for LikeStreetItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LikeStreetItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="streetTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="vectorTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="firstHouseNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lastHouseNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="treatmentCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LikeStreetItem", propOrder = {
    "streetTxt",
    "vectorTxt",
    "firstHouseNum",
    "lastHouseNum",
    "treatmentCd"
})
public class LikeStreetItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String streetTxt;
    @XmlElement(required = true)
    protected String vectorTxt;
    @XmlElement(required = true)
    protected String firstHouseNum;
    @XmlElement(required = true)
    protected String lastHouseNum;
    @XmlElement(required = true)
    protected String treatmentCd;

    /**
     * Gets the value of the streetTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetTxt() {
        return streetTxt;
    }

    /**
     * Sets the value of the streetTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetTxt(String value) {
        this.streetTxt = value;
    }

    /**
     * Gets the value of the vectorTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVectorTxt() {
        return vectorTxt;
    }

    /**
     * Sets the value of the vectorTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVectorTxt(String value) {
        this.vectorTxt = value;
    }

    /**
     * Gets the value of the firstHouseNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstHouseNum() {
        return firstHouseNum;
    }

    /**
     * Sets the value of the firstHouseNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstHouseNum(String value) {
        this.firstHouseNum = value;
    }

    /**
     * Gets the value of the lastHouseNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastHouseNum() {
        return lastHouseNum;
    }

    /**
     * Sets the value of the lastHouseNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastHouseNum(String value) {
        this.lastHouseNum = value;
    }

    /**
     * Gets the value of the treatmentCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTreatmentCd() {
        return treatmentCd;
    }

    /**
     * Sets the value of the treatmentCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTreatmentCd(String value) {
        this.treatmentCd = value;
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
            String theStreetTxt;
            theStreetTxt = this.getStreetTxt();
            strategy.appendField(locator, this, "streetTxt", buffer, theStreetTxt);
        }
        {
            String theVectorTxt;
            theVectorTxt = this.getVectorTxt();
            strategy.appendField(locator, this, "vectorTxt", buffer, theVectorTxt);
        }
        {
            String theFirstHouseNum;
            theFirstHouseNum = this.getFirstHouseNum();
            strategy.appendField(locator, this, "firstHouseNum", buffer, theFirstHouseNum);
        }
        {
            String theLastHouseNum;
            theLastHouseNum = this.getLastHouseNum();
            strategy.appendField(locator, this, "lastHouseNum", buffer, theLastHouseNum);
        }
        {
            String theTreatmentCd;
            theTreatmentCd = this.getTreatmentCd();
            strategy.appendField(locator, this, "treatmentCd", buffer, theTreatmentCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof LikeStreetItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final LikeStreetItem that = ((LikeStreetItem) object);
        {
            String lhsStreetTxt;
            lhsStreetTxt = this.getStreetTxt();
            String rhsStreetTxt;
            rhsStreetTxt = that.getStreetTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetTxt", lhsStreetTxt), LocatorUtils.property(thatLocator, "streetTxt", rhsStreetTxt), lhsStreetTxt, rhsStreetTxt)) {
                return false;
            }
        }
        {
            String lhsVectorTxt;
            lhsVectorTxt = this.getVectorTxt();
            String rhsVectorTxt;
            rhsVectorTxt = that.getVectorTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "vectorTxt", lhsVectorTxt), LocatorUtils.property(thatLocator, "vectorTxt", rhsVectorTxt), lhsVectorTxt, rhsVectorTxt)) {
                return false;
            }
        }
        {
            String lhsFirstHouseNum;
            lhsFirstHouseNum = this.getFirstHouseNum();
            String rhsFirstHouseNum;
            rhsFirstHouseNum = that.getFirstHouseNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "firstHouseNum", lhsFirstHouseNum), LocatorUtils.property(thatLocator, "firstHouseNum", rhsFirstHouseNum), lhsFirstHouseNum, rhsFirstHouseNum)) {
                return false;
            }
        }
        {
            String lhsLastHouseNum;
            lhsLastHouseNum = this.getLastHouseNum();
            String rhsLastHouseNum;
            rhsLastHouseNum = that.getLastHouseNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lastHouseNum", lhsLastHouseNum), LocatorUtils.property(thatLocator, "lastHouseNum", rhsLastHouseNum), lhsLastHouseNum, rhsLastHouseNum)) {
                return false;
            }
        }
        {
            String lhsTreatmentCd;
            lhsTreatmentCd = this.getTreatmentCd();
            String rhsTreatmentCd;
            rhsTreatmentCd = that.getTreatmentCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "treatmentCd", lhsTreatmentCd), LocatorUtils.property(thatLocator, "treatmentCd", rhsTreatmentCd), lhsTreatmentCd, rhsTreatmentCd)) {
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
