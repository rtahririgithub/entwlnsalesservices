
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.product.productoffering.bundlecommitmentcommontype_v1.IntegratedOfferDetail;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Bundle object
 * 
 * <p>Java class for BundleDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BundleDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bundleOfferDetail" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/BundleCommitmentCommonType_v1}IntegratedOfferDetail" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BundleDetail", propOrder = {
    "bundleOfferDetail"
})
public class BundleDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected IntegratedOfferDetail bundleOfferDetail;

    /**
     * Gets the value of the bundleOfferDetail property.
     * 
     * @return
     *     possible object is
     *     {@link IntegratedOfferDetail }
     *     
     */
    public IntegratedOfferDetail getBundleOfferDetail() {
        return bundleOfferDetail;
    }

    /**
     * Sets the value of the bundleOfferDetail property.
     * 
     * @param value
     *     allowed object is
     *     {@link IntegratedOfferDetail }
     *     
     */
    public void setBundleOfferDetail(IntegratedOfferDetail value) {
        this.bundleOfferDetail = value;
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
            IntegratedOfferDetail theBundleOfferDetail;
            theBundleOfferDetail = this.getBundleOfferDetail();
            strategy.appendField(locator, this, "bundleOfferDetail", buffer, theBundleOfferDetail);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BundleDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BundleDetail that = ((BundleDetail) object);
        {
            IntegratedOfferDetail lhsBundleOfferDetail;
            lhsBundleOfferDetail = this.getBundleOfferDetail();
            IntegratedOfferDetail rhsBundleOfferDetail;
            rhsBundleOfferDetail = that.getBundleOfferDetail();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "bundleOfferDetail", lhsBundleOfferDetail), LocatorUtils.property(thatLocator, "bundleOfferDetail", rhsBundleOfferDetail), lhsBundleOfferDetail, rhsBundleOfferDetail)) {
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
