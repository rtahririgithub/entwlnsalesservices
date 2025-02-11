
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
 * <p>Java class for OfferProductHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferProductHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productOrderType" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductOrderType" minOccurs="0"/>
 *         &lt;element name="productTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productComponentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="contractTermCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="winBackInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="recontractInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferProductHeader", propOrder = {
    "productOrderType",
    "productTypeCd",
    "productComponentList",
    "contractTermCd",
    "winBackInd",
    "recontractInd"
})
@XmlSeeAlso({
    WirelineProductDiscount.class
})
public class OfferProductHeader
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected ProductOrderType productOrderType;
    @XmlElement(required = true)
    protected String productTypeCd;
    protected List<ProductComponentIdentifier> productComponentList;
    protected String contractTermCd;
    protected Boolean winBackInd;
    protected Boolean recontractInd;

    /**
     * Gets the value of the productOrderType property.
     * 
     * @return
     *     possible object is
     *     {@link ProductOrderType }
     *     
     */
    public ProductOrderType getProductOrderType() {
        return productOrderType;
    }

    /**
     * Sets the value of the productOrderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link ProductOrderType }
     *     
     */
    public void setProductOrderType(ProductOrderType value) {
        this.productOrderType = value;
    }

    /**
     * Gets the value of the productTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCd() {
        return productTypeCd;
    }

    /**
     * Sets the value of the productTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCd(String value) {
        this.productTypeCd = value;
    }

    /**
     * Gets the value of the productComponentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productComponentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductComponentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductComponentIdentifier }
     * 
     * 
     */
    public List<ProductComponentIdentifier> getProductComponentList() {
        if (productComponentList == null) {
            productComponentList = new ArrayList<ProductComponentIdentifier>();
        }
        return this.productComponentList;
    }

    /**
     * Gets the value of the contractTermCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractTermCd() {
        return contractTermCd;
    }

    /**
     * Sets the value of the contractTermCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractTermCd(String value) {
        this.contractTermCd = value;
    }

    /**
     * Gets the value of the winBackInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isWinBackInd() {
        return winBackInd;
    }

    /**
     * Sets the value of the winBackInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setWinBackInd(Boolean value) {
        this.winBackInd = value;
    }

    /**
     * Gets the value of the recontractInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRecontractInd() {
        return recontractInd;
    }

    /**
     * Sets the value of the recontractInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRecontractInd(Boolean value) {
        this.recontractInd = value;
    }

    /**
     * Sets the value of the productComponentList property.
     * 
     * @param productComponentList
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setProductComponentList(List<ProductComponentIdentifier> productComponentList) {
        this.productComponentList = productComponentList;
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
            ProductOrderType theProductOrderType;
            theProductOrderType = this.getProductOrderType();
            strategy.appendField(locator, this, "productOrderType", buffer, theProductOrderType);
        }
        {
            String theProductTypeCd;
            theProductTypeCd = this.getProductTypeCd();
            strategy.appendField(locator, this, "productTypeCd", buffer, theProductTypeCd);
        }
        {
            List<ProductComponentIdentifier> theProductComponentList;
            theProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            strategy.appendField(locator, this, "productComponentList", buffer, theProductComponentList);
        }
        {
            String theContractTermCd;
            theContractTermCd = this.getContractTermCd();
            strategy.appendField(locator, this, "contractTermCd", buffer, theContractTermCd);
        }
        {
            Boolean theWinBackInd;
            theWinBackInd = this.isWinBackInd();
            strategy.appendField(locator, this, "winBackInd", buffer, theWinBackInd);
        }
        {
            Boolean theRecontractInd;
            theRecontractInd = this.isRecontractInd();
            strategy.appendField(locator, this, "recontractInd", buffer, theRecontractInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferProductHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final OfferProductHeader that = ((OfferProductHeader) object);
        {
            ProductOrderType lhsProductOrderType;
            lhsProductOrderType = this.getProductOrderType();
            ProductOrderType rhsProductOrderType;
            rhsProductOrderType = that.getProductOrderType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productOrderType", lhsProductOrderType), LocatorUtils.property(thatLocator, "productOrderType", rhsProductOrderType), lhsProductOrderType, rhsProductOrderType)) {
                return false;
            }
        }
        {
            String lhsProductTypeCd;
            lhsProductTypeCd = this.getProductTypeCd();
            String rhsProductTypeCd;
            rhsProductTypeCd = that.getProductTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeCd", lhsProductTypeCd), LocatorUtils.property(thatLocator, "productTypeCd", rhsProductTypeCd), lhsProductTypeCd, rhsProductTypeCd)) {
                return false;
            }
        }
        {
            List<ProductComponentIdentifier> lhsProductComponentList;
            lhsProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            List<ProductComponentIdentifier> rhsProductComponentList;
            rhsProductComponentList = (((that.productComponentList!= null)&&(!that.productComponentList.isEmpty()))?that.getProductComponentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentList", lhsProductComponentList), LocatorUtils.property(thatLocator, "productComponentList", rhsProductComponentList), lhsProductComponentList, rhsProductComponentList)) {
                return false;
            }
        }
        {
            String lhsContractTermCd;
            lhsContractTermCd = this.getContractTermCd();
            String rhsContractTermCd;
            rhsContractTermCd = that.getContractTermCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermCd", lhsContractTermCd), LocatorUtils.property(thatLocator, "contractTermCd", rhsContractTermCd), lhsContractTermCd, rhsContractTermCd)) {
                return false;
            }
        }
        {
            Boolean lhsWinBackInd;
            lhsWinBackInd = this.isWinBackInd();
            Boolean rhsWinBackInd;
            rhsWinBackInd = that.isWinBackInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "winBackInd", lhsWinBackInd), LocatorUtils.property(thatLocator, "winBackInd", rhsWinBackInd), lhsWinBackInd, rhsWinBackInd)) {
                return false;
            }
        }
        {
            Boolean lhsRecontractInd;
            lhsRecontractInd = this.isRecontractInd();
            Boolean rhsRecontractInd;
            rhsRecontractInd = that.isRecontractInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recontractInd", lhsRecontractInd), LocatorUtils.property(thatLocator, "recontractInd", rhsRecontractInd), lhsRecontractInd, rhsRecontractInd)) {
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
