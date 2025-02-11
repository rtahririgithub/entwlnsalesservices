
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Npa Nxx Type defined to be used for WLS, VOIP-LOCAL, VOIP-TOLL-FREE numbers.
 * 
 * <p>Java class for NpaNxxType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="NpaNxxType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="npa" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nxx" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="descriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NpaNxxType", propOrder = {
    "npa",
    "nxx",
    "descriptionList"
})
public class NpaNxxType
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String npa;
    protected String nxx;
    protected MultilingualCodeDescTextList descriptionList;

    /**
     * Gets the value of the npa property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpa() {
        return npa;
    }

    /**
     * Sets the value of the npa property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpa(String value) {
        this.npa = value;
    }

    /**
     * Gets the value of the nxx property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNxx() {
        return nxx;
    }

    /**
     * Sets the value of the nxx property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNxx(String value) {
        this.nxx = value;
    }

    /**
     * Gets the value of the descriptionList property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getDescriptionList() {
        return descriptionList;
    }

    /**
     * Sets the value of the descriptionList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setDescriptionList(MultilingualCodeDescTextList value) {
        this.descriptionList = value;
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
            String theNpa;
            theNpa = this.getNpa();
            strategy.appendField(locator, this, "npa", buffer, theNpa);
        }
        {
            String theNxx;
            theNxx = this.getNxx();
            strategy.appendField(locator, this, "nxx", buffer, theNxx);
        }
        {
            MultilingualCodeDescTextList theDescriptionList;
            theDescriptionList = this.getDescriptionList();
            strategy.appendField(locator, this, "descriptionList", buffer, theDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof NpaNxxType)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final NpaNxxType that = ((NpaNxxType) object);
        {
            String lhsNpa;
            lhsNpa = this.getNpa();
            String rhsNpa;
            rhsNpa = that.getNpa();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "npa", lhsNpa), LocatorUtils.property(thatLocator, "npa", rhsNpa), lhsNpa, rhsNpa)) {
                return false;
            }
        }
        {
            String lhsNxx;
            lhsNxx = this.getNxx();
            String rhsNxx;
            rhsNxx = that.getNxx();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nxx", lhsNxx), LocatorUtils.property(thatLocator, "nxx", rhsNxx), lhsNxx, rhsNxx)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsDescriptionList;
            lhsDescriptionList = this.getDescriptionList();
            MultilingualCodeDescTextList rhsDescriptionList;
            rhsDescriptionList = that.getDescriptionList();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "descriptionList", lhsDescriptionList), LocatorUtils.property(thatLocator, "descriptionList", rhsDescriptionList), lhsDescriptionList, rhsDescriptionList)) {
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
