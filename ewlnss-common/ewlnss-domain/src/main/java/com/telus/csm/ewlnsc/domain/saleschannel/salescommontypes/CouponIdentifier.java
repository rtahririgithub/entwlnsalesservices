
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.telus.framework.xml.bind.DateTimeAdapter;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for CouponIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CouponIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="couponCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="perspectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CouponIdentifier", propOrder = {
    "couponCd",
    "perspectiveDate"
})
public class CouponIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String couponCd;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date perspectiveDate;

    /**
     * Gets the value of the couponCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCouponCd() {
        return couponCd;
    }

    /**
     * Sets the value of the couponCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCouponCd(String value) {
        this.couponCd = value;
    }

    /**
     * Gets the value of the perspectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPerspectiveDate() {
        return perspectiveDate;
    }

    /**
     * Sets the value of the perspectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerspectiveDate(Date value) {
        this.perspectiveDate = value;
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
            String theCouponCd;
            theCouponCd = this.getCouponCd();
            strategy.appendField(locator, this, "couponCd", buffer, theCouponCd);
        }
        {
            Date thePerspectiveDate;
            thePerspectiveDate = this.getPerspectiveDate();
            strategy.appendField(locator, this, "perspectiveDate", buffer, thePerspectiveDate);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CouponIdentifier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CouponIdentifier that = ((CouponIdentifier) object);
        {
            String lhsCouponCd;
            lhsCouponCd = this.getCouponCd();
            String rhsCouponCd;
            rhsCouponCd = that.getCouponCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "couponCd", lhsCouponCd), LocatorUtils.property(thatLocator, "couponCd", rhsCouponCd), lhsCouponCd, rhsCouponCd)) {
                return false;
            }
        }
        {
            Date lhsPerspectiveDate;
            lhsPerspectiveDate = this.getPerspectiveDate();
            Date rhsPerspectiveDate;
            rhsPerspectiveDate = that.getPerspectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perspectiveDate", lhsPerspectiveDate), LocatorUtils.property(thatLocator, "perspectiveDate", rhsPerspectiveDate), lhsPerspectiveDate, rhsPerspectiveDate)) {
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
