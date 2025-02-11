
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
 * <p>Java class for LikeHouseItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="LikeHouseItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="houseNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="houseSuffixNum" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "LikeHouseItem", propOrder = {
    "houseNum",
    "houseSuffixNum"
})
public class LikeHouseItem
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String houseNum;
    @XmlElement(required = true)
    protected String houseSuffixNum;

    /**
     * Gets the value of the houseNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseNum() {
        return houseNum;
    }

    /**
     * Sets the value of the houseNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseNum(String value) {
        this.houseNum = value;
    }

    /**
     * Gets the value of the houseSuffixNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHouseSuffixNum() {
        return houseSuffixNum;
    }

    /**
     * Sets the value of the houseSuffixNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHouseSuffixNum(String value) {
        this.houseSuffixNum = value;
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
            String theHouseNum;
            theHouseNum = this.getHouseNum();
            strategy.appendField(locator, this, "houseNum", buffer, theHouseNum);
        }
        {
            String theHouseSuffixNum;
            theHouseSuffixNum = this.getHouseSuffixNum();
            strategy.appendField(locator, this, "houseSuffixNum", buffer, theHouseSuffixNum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof LikeHouseItem)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final LikeHouseItem that = ((LikeHouseItem) object);
        {
            String lhsHouseNum;
            lhsHouseNum = this.getHouseNum();
            String rhsHouseNum;
            rhsHouseNum = that.getHouseNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "houseNum", lhsHouseNum), LocatorUtils.property(thatLocator, "houseNum", rhsHouseNum), lhsHouseNum, rhsHouseNum)) {
                return false;
            }
        }
        {
            String lhsHouseSuffixNum;
            lhsHouseSuffixNum = this.getHouseSuffixNum();
            String rhsHouseSuffixNum;
            rhsHouseSuffixNum = that.getHouseSuffixNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "houseSuffixNum", lhsHouseSuffixNum), LocatorUtils.property(thatLocator, "houseSuffixNum", rhsHouseSuffixNum), lhsHouseSuffixNum, rhsHouseSuffixNum)) {
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
