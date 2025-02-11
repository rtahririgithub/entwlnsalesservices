
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
 * <p>Java class for ServiceAddressBase complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceAddressBase">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceAddressId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provinceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="cityCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceAddressBase", propOrder = {
    "serviceAddressId",
    "provinceCode",
    "cityCode"
})
public class ServiceAddressBase
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String serviceAddressId;
    @XmlElement(required = true)
    protected String provinceCode;
    @XmlElement(required = true)
    protected String cityCode;

    /**
     * Gets the value of the serviceAddressId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceAddressId() {
        return serviceAddressId;
    }

    /**
     * Sets the value of the serviceAddressId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceAddressId(String value) {
        this.serviceAddressId = value;
    }

    /**
     * Gets the value of the provinceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinceCode() {
        return provinceCode;
    }

    /**
     * Sets the value of the provinceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinceCode(String value) {
        this.provinceCode = value;
    }

    /**
     * Gets the value of the cityCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCityCode() {
        return cityCode;
    }

    /**
     * Sets the value of the cityCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCityCode(String value) {
        this.cityCode = value;
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
            String theServiceAddressId;
            theServiceAddressId = this.getServiceAddressId();
            strategy.appendField(locator, this, "serviceAddressId", buffer, theServiceAddressId);
        }
        {
            String theProvinceCode;
            theProvinceCode = this.getProvinceCode();
            strategy.appendField(locator, this, "provinceCode", buffer, theProvinceCode);
        }
        {
            String theCityCode;
            theCityCode = this.getCityCode();
            strategy.appendField(locator, this, "cityCode", buffer, theCityCode);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceAddressBase)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceAddressBase that = ((ServiceAddressBase) object);
        {
            String lhsServiceAddressId;
            lhsServiceAddressId = this.getServiceAddressId();
            String rhsServiceAddressId;
            rhsServiceAddressId = that.getServiceAddressId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddressId", lhsServiceAddressId), LocatorUtils.property(thatLocator, "serviceAddressId", rhsServiceAddressId), lhsServiceAddressId, rhsServiceAddressId)) {
                return false;
            }
        }
        {
            String lhsProvinceCode;
            lhsProvinceCode = this.getProvinceCode();
            String rhsProvinceCode;
            rhsProvinceCode = that.getProvinceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provinceCode", lhsProvinceCode), LocatorUtils.property(thatLocator, "provinceCode", rhsProvinceCode), lhsProvinceCode, rhsProvinceCode)) {
                return false;
            }
        }
        {
            String lhsCityCode;
            lhsCityCode = this.getCityCode();
            String rhsCityCode;
            rhsCityCode = that.getCityCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "cityCode", lhsCityCode), LocatorUtils.property(thatLocator, "cityCode", rhsCityCode), lhsCityCode, rhsCityCode)) {
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
