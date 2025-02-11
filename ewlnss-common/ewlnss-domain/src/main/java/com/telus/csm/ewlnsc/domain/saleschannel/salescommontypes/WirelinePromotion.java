
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for WirelinePromotion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelinePromotion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="productDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="offerPromotionList" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="promotionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="promotionNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
 *                   &lt;element name="promotionDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
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
@XmlType(name = "WirelinePromotion", propOrder = {
    "productId",
    "productTypeCode",
    "productNameList",
    "productDescriptionList",
    "offerPromotionList"
})
public class WirelinePromotion
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String productId;
    protected String productTypeCode;
    protected List<Description> productNameList;
    protected List<Description> productDescriptionList;
    protected List<WirelinePromotion.OfferPromotionList> offerPromotionList;

    /**
     * Gets the value of the productId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductId() {
        return productId;
    }

    /**
     * Sets the value of the productId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductId(String value) {
        this.productId = value;
    }

    /**
     * Gets the value of the productTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCode() {
        return productTypeCode;
    }

    /**
     * Sets the value of the productTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCode(String value) {
        this.productTypeCode = value;
    }

    /**
     * Gets the value of the productNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getProductNameList() {
        if (productNameList == null) {
            productNameList = new ArrayList<Description>();
        }
        return this.productNameList;
    }

    /**
     * Gets the value of the productDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getProductDescriptionList() {
        if (productDescriptionList == null) {
            productDescriptionList = new ArrayList<Description>();
        }
        return this.productDescriptionList;
    }

    /**
     * Gets the value of the offerPromotionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerPromotionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferPromotionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelinePromotion.OfferPromotionList }
     * 
     * 
     */
    public List<WirelinePromotion.OfferPromotionList> getOfferPromotionList() {
        if (offerPromotionList == null) {
            offerPromotionList = new ArrayList<WirelinePromotion.OfferPromotionList>();
        }
        return this.offerPromotionList;
    }

    /**
     * Sets the value of the productNameList property.
     * 
     * @param productNameList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setProductNameList(List<Description> productNameList) {
        this.productNameList = productNameList;
    }

    /**
     * Sets the value of the productDescriptionList property.
     * 
     * @param productDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setProductDescriptionList(List<Description> productDescriptionList) {
        this.productDescriptionList = productDescriptionList;
    }

    /**
     * Sets the value of the offerPromotionList property.
     * 
     * @param offerPromotionList
     *     allowed object is
     *     {@link WirelinePromotion.OfferPromotionList }
     *     
     */
    public void setOfferPromotionList(List<WirelinePromotion.OfferPromotionList> offerPromotionList) {
        this.offerPromotionList = offerPromotionList;
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
            String theProductId;
            theProductId = this.getProductId();
            strategy.appendField(locator, this, "productId", buffer, theProductId);
        }
        {
            String theProductTypeCode;
            theProductTypeCode = this.getProductTypeCode();
            strategy.appendField(locator, this, "productTypeCode", buffer, theProductTypeCode);
        }
        {
            List<Description> theProductNameList;
            theProductNameList = (((this.productNameList!= null)&&(!this.productNameList.isEmpty()))?this.getProductNameList():null);
            strategy.appendField(locator, this, "productNameList", buffer, theProductNameList);
        }
        {
            List<Description> theProductDescriptionList;
            theProductDescriptionList = (((this.productDescriptionList!= null)&&(!this.productDescriptionList.isEmpty()))?this.getProductDescriptionList():null);
            strategy.appendField(locator, this, "productDescriptionList", buffer, theProductDescriptionList);
        }
        {
            List<WirelinePromotion.OfferPromotionList> theOfferPromotionList;
            theOfferPromotionList = (((this.offerPromotionList!= null)&&(!this.offerPromotionList.isEmpty()))?this.getOfferPromotionList():null);
            strategy.appendField(locator, this, "offerPromotionList", buffer, theOfferPromotionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelinePromotion)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelinePromotion that = ((WirelinePromotion) object);
        {
            String lhsProductId;
            lhsProductId = this.getProductId();
            String rhsProductId;
            rhsProductId = that.getProductId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productId", lhsProductId), LocatorUtils.property(thatLocator, "productId", rhsProductId), lhsProductId, rhsProductId)) {
                return false;
            }
        }
        {
            String lhsProductTypeCode;
            lhsProductTypeCode = this.getProductTypeCode();
            String rhsProductTypeCode;
            rhsProductTypeCode = that.getProductTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeCode", lhsProductTypeCode), LocatorUtils.property(thatLocator, "productTypeCode", rhsProductTypeCode), lhsProductTypeCode, rhsProductTypeCode)) {
                return false;
            }
        }
        {
            List<Description> lhsProductNameList;
            lhsProductNameList = (((this.productNameList!= null)&&(!this.productNameList.isEmpty()))?this.getProductNameList():null);
            List<Description> rhsProductNameList;
            rhsProductNameList = (((that.productNameList!= null)&&(!that.productNameList.isEmpty()))?that.getProductNameList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productNameList", lhsProductNameList), LocatorUtils.property(thatLocator, "productNameList", rhsProductNameList), lhsProductNameList, rhsProductNameList)) {
                return false;
            }
        }
        {
            List<Description> lhsProductDescriptionList;
            lhsProductDescriptionList = (((this.productDescriptionList!= null)&&(!this.productDescriptionList.isEmpty()))?this.getProductDescriptionList():null);
            List<Description> rhsProductDescriptionList;
            rhsProductDescriptionList = (((that.productDescriptionList!= null)&&(!that.productDescriptionList.isEmpty()))?that.getProductDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productDescriptionList", lhsProductDescriptionList), LocatorUtils.property(thatLocator, "productDescriptionList", rhsProductDescriptionList), lhsProductDescriptionList, rhsProductDescriptionList)) {
                return false;
            }
        }
        {
            List<WirelinePromotion.OfferPromotionList> lhsOfferPromotionList;
            lhsOfferPromotionList = (((this.offerPromotionList!= null)&&(!this.offerPromotionList.isEmpty()))?this.getOfferPromotionList():null);
            List<WirelinePromotion.OfferPromotionList> rhsOfferPromotionList;
            rhsOfferPromotionList = (((that.offerPromotionList!= null)&&(!that.offerPromotionList.isEmpty()))?that.getOfferPromotionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerPromotionList", lhsOfferPromotionList), LocatorUtils.property(thatLocator, "offerPromotionList", rhsOfferPromotionList), lhsOfferPromotionList, rhsOfferPromotionList)) {
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
     *         &lt;element name="promotionId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="promotionNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
     *         &lt;element name="promotionDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="2" minOccurs="0"/>
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
        "promotionId",
        "promotionNameList",
        "promotionDescriptionList"
    })
    public static class OfferPromotionList
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        @XmlElement(required = true)
        protected String promotionId;
        protected List<Description> promotionNameList;
        protected List<Description> promotionDescriptionList;

        /**
         * Gets the value of the promotionId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPromotionId() {
            return promotionId;
        }

        /**
         * Sets the value of the promotionId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPromotionId(String value) {
            this.promotionId = value;
        }

        /**
         * Gets the value of the promotionNameList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the promotionNameList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPromotionNameList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Description }
         * 
         * 
         */
        public List<Description> getPromotionNameList() {
            if (promotionNameList == null) {
                promotionNameList = new ArrayList<Description>();
            }
            return this.promotionNameList;
        }

        /**
         * Gets the value of the promotionDescriptionList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the promotionDescriptionList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getPromotionDescriptionList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link Description }
         * 
         * 
         */
        public List<Description> getPromotionDescriptionList() {
            if (promotionDescriptionList == null) {
                promotionDescriptionList = new ArrayList<Description>();
            }
            return this.promotionDescriptionList;
        }

        /**
         * Sets the value of the promotionNameList property.
         * 
         * @param promotionNameList
         *     allowed object is
         *     {@link Description }
         *     
         */
        public void setPromotionNameList(List<Description> promotionNameList) {
            this.promotionNameList = promotionNameList;
        }

        /**
         * Sets the value of the promotionDescriptionList property.
         * 
         * @param promotionDescriptionList
         *     allowed object is
         *     {@link Description }
         *     
         */
        public void setPromotionDescriptionList(List<Description> promotionDescriptionList) {
            this.promotionDescriptionList = promotionDescriptionList;
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
                String thePromotionId;
                thePromotionId = this.getPromotionId();
                strategy.appendField(locator, this, "promotionId", buffer, thePromotionId);
            }
            {
                List<Description> thePromotionNameList;
                thePromotionNameList = (((this.promotionNameList!= null)&&(!this.promotionNameList.isEmpty()))?this.getPromotionNameList():null);
                strategy.appendField(locator, this, "promotionNameList", buffer, thePromotionNameList);
            }
            {
                List<Description> thePromotionDescriptionList;
                thePromotionDescriptionList = (((this.promotionDescriptionList!= null)&&(!this.promotionDescriptionList.isEmpty()))?this.getPromotionDescriptionList():null);
                strategy.appendField(locator, this, "promotionDescriptionList", buffer, thePromotionDescriptionList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof WirelinePromotion.OfferPromotionList)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final WirelinePromotion.OfferPromotionList that = ((WirelinePromotion.OfferPromotionList) object);
            {
                String lhsPromotionId;
                lhsPromotionId = this.getPromotionId();
                String rhsPromotionId;
                rhsPromotionId = that.getPromotionId();
                if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionId", lhsPromotionId), LocatorUtils.property(thatLocator, "promotionId", rhsPromotionId), lhsPromotionId, rhsPromotionId)) {
                    return false;
                }
            }
            {
                List<Description> lhsPromotionNameList;
                lhsPromotionNameList = (((this.promotionNameList!= null)&&(!this.promotionNameList.isEmpty()))?this.getPromotionNameList():null);
                List<Description> rhsPromotionNameList;
                rhsPromotionNameList = (((that.promotionNameList!= null)&&(!that.promotionNameList.isEmpty()))?that.getPromotionNameList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionNameList", lhsPromotionNameList), LocatorUtils.property(thatLocator, "promotionNameList", rhsPromotionNameList), lhsPromotionNameList, rhsPromotionNameList)) {
                    return false;
                }
            }
            {
                List<Description> lhsPromotionDescriptionList;
                lhsPromotionDescriptionList = (((this.promotionDescriptionList!= null)&&(!this.promotionDescriptionList.isEmpty()))?this.getPromotionDescriptionList():null);
                List<Description> rhsPromotionDescriptionList;
                rhsPromotionDescriptionList = (((that.promotionDescriptionList!= null)&&(!that.promotionDescriptionList.isEmpty()))?that.getPromotionDescriptionList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionDescriptionList", lhsPromotionDescriptionList), LocatorUtils.property(thatLocator, "promotionDescriptionList", rhsPromotionDescriptionList), lhsPromotionDescriptionList, rhsPromotionDescriptionList)) {
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
