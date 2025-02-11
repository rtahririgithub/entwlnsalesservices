
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
 * <p>Java class for ServiceInstallDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceInstallDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="serviceTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recommendedInstallTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceInstallDetail", propOrder = {
    "serviceTypeCd",
    "recommendedInstallTypeCd"
})
public class ServiceInstallDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String serviceTypeCd;
    @XmlElement(required = true)
    protected String recommendedInstallTypeCd;

    /**
     * Gets the value of the serviceTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceTypeCd() {
        return serviceTypeCd;
    }

    /**
     * Sets the value of the serviceTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceTypeCd(String value) {
        this.serviceTypeCd = value;
    }

    /**
     * Gets the value of the recommendedInstallTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecommendedInstallTypeCd() {
        return recommendedInstallTypeCd;
    }

    /**
     * Sets the value of the recommendedInstallTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecommendedInstallTypeCd(String value) {
        this.recommendedInstallTypeCd = value;
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
            String theServiceTypeCd;
            theServiceTypeCd = this.getServiceTypeCd();
            strategy.appendField(locator, this, "serviceTypeCd", buffer, theServiceTypeCd);
        }
        {
            String theRecommendedInstallTypeCd;
            theRecommendedInstallTypeCd = this.getRecommendedInstallTypeCd();
            strategy.appendField(locator, this, "recommendedInstallTypeCd", buffer, theRecommendedInstallTypeCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceInstallDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ServiceInstallDetail that = ((ServiceInstallDetail) object);
        {
            String lhsServiceTypeCd;
            lhsServiceTypeCd = this.getServiceTypeCd();
            String rhsServiceTypeCd;
            rhsServiceTypeCd = that.getServiceTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceTypeCd", lhsServiceTypeCd), LocatorUtils.property(thatLocator, "serviceTypeCd", rhsServiceTypeCd), lhsServiceTypeCd, rhsServiceTypeCd)) {
                return false;
            }
        }
        {
            String lhsRecommendedInstallTypeCd;
            lhsRecommendedInstallTypeCd = this.getRecommendedInstallTypeCd();
            String rhsRecommendedInstallTypeCd;
            rhsRecommendedInstallTypeCd = that.getRecommendedInstallTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendedInstallTypeCd", lhsRecommendedInstallTypeCd), LocatorUtils.property(thatLocator, "recommendedInstallTypeCd", rhsRecommendedInstallTypeCd), lhsRecommendedInstallTypeCd, rhsRecommendedInstallTypeCd)) {
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
