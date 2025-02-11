
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferHeader;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.ProgramPromotion;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for EnterpriseSalesOfferDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="EnterpriseSalesOfferDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="offerHeader" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferHeader"/>
 *         &lt;element name="offerProduct" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}SalesOfferProduct"/>
 *         &lt;element name="promotionCodeOfferInfo" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProgramPromotion" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EnterpriseSalesOfferDetail", propOrder = {
    "offerHeader",
    "offerProduct",
    "promotionCodeOfferInfo"
})
public class EnterpriseSalesOfferDetail
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected OfferHeader offerHeader;
    @XmlElement(required = true)
    protected SalesOfferProduct offerProduct;
    protected ProgramPromotion promotionCodeOfferInfo;

    /**
     * Gets the value of the offerHeader property.
     * 
     * @return
     *     possible object is
     *     {@link OfferHeader }
     *     
     */
    public OfferHeader getOfferHeader() {
        return offerHeader;
    }

    /**
     * Sets the value of the offerHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link OfferHeader }
     *     
     */
    public void setOfferHeader(OfferHeader value) {
        this.offerHeader = value;
    }

    /**
     * Gets the value of the offerProduct property.
     * 
     * @return
     *     possible object is
     *     {@link SalesOfferProduct }
     *     
     */
    public SalesOfferProduct getOfferProduct() {
        return offerProduct;
    }

    /**
     * Sets the value of the offerProduct property.
     * 
     * @param value
     *     allowed object is
     *     {@link SalesOfferProduct }
     *     
     */
    public void setOfferProduct(SalesOfferProduct value) {
        this.offerProduct = value;
    }

    /**
     * Gets the value of the promotionCodeOfferInfo property.
     * 
     * @return
     *     possible object is
     *     {@link ProgramPromotion }
     *     
     */
    public ProgramPromotion getPromotionCodeOfferInfo() {
        return promotionCodeOfferInfo;
    }

    /**
     * Sets the value of the promotionCodeOfferInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProgramPromotion }
     *     
     */
    public void setPromotionCodeOfferInfo(ProgramPromotion value) {
        this.promotionCodeOfferInfo = value;
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
            OfferHeader theOfferHeader;
            theOfferHeader = this.getOfferHeader();
            strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
        }
        {
            SalesOfferProduct theOfferProduct;
            theOfferProduct = this.getOfferProduct();
            strategy.appendField(locator, this, "offerProduct", buffer, theOfferProduct);
        }
        {
            ProgramPromotion thePromotionCodeOfferInfo;
            thePromotionCodeOfferInfo = this.getPromotionCodeOfferInfo();
            strategy.appendField(locator, this, "promotionCodeOfferInfo", buffer, thePromotionCodeOfferInfo);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof EnterpriseSalesOfferDetail)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final EnterpriseSalesOfferDetail that = ((EnterpriseSalesOfferDetail) object);
        {
            OfferHeader lhsOfferHeader;
            lhsOfferHeader = this.getOfferHeader();
            OfferHeader rhsOfferHeader;
            rhsOfferHeader = that.getOfferHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
                return false;
            }
        }
        {
            SalesOfferProduct lhsOfferProduct;
            lhsOfferProduct = this.getOfferProduct();
            SalesOfferProduct rhsOfferProduct;
            rhsOfferProduct = that.getOfferProduct();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProduct", lhsOfferProduct), LocatorUtils.property(thatLocator, "offerProduct", rhsOfferProduct), lhsOfferProduct, rhsOfferProduct)) {
                return false;
            }
        }
        {
            ProgramPromotion lhsPromotionCodeOfferInfo;
            lhsPromotionCodeOfferInfo = this.getPromotionCodeOfferInfo();
            ProgramPromotion rhsPromotionCodeOfferInfo;
            rhsPromotionCodeOfferInfo = that.getPromotionCodeOfferInfo();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionCodeOfferInfo", lhsPromotionCodeOfferInfo), LocatorUtils.property(thatLocator, "promotionCodeOfferInfo", rhsPromotionCodeOfferInfo), lhsPromotionCodeOfferInfo, rhsPromotionCodeOfferInfo)) {
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
