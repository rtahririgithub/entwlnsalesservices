
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Message;
import com.telus.tmi.xmlschema.xsd.marketingsales.saleschannel.salescommontypes_v5.SweetenerOfferSummary;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Extends Program; contains minimal offer information that may be used in search operations. This information is included in offer types such as 'OfferSummary' and 'Offer'.
 * 
 * <p>Java class for OfferHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="OfferHeader">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}Program">
 *       &lt;sequence>
 *         &lt;element name="offerId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="offerReferenceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="offerDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="offerTierId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="offerCategoryList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferCategory" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="basePriceAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="wirelessOfferCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="paymentSupportTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rankingNum" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="productEligiblityList" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ProductEligiblity" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="assignedOfferInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="offerMarketingMessageList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Message" maxOccurs="2" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "OfferHeader", propOrder = {
    "offerId",
    "offerReferenceId",
    "offerDescriptionList",
    "offerTierId",
    "offerCategoryList",
    "basePriceAmt",
    "wirelessOfferCode",
    "paymentSupportTypeCode",
    "rankingNum",
    "productEligiblityList",
    "assignedOfferInd",
    "offerMarketingMessageList"
})
@XmlSeeAlso({
    SweetenerOfferSummary.class,
    OfferSummary.class,
    Offer.class
})
public class OfferHeader
    extends Program
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected long offerId;
    protected String offerReferenceId;
    protected List<Description> offerDescriptionList;
    protected Integer offerTierId;
    protected List<OfferCategory> offerCategoryList;
    protected Double basePriceAmt;
    protected String wirelessOfferCode;
    @XmlElement(required = true)
    protected String paymentSupportTypeCode;
    protected BigInteger rankingNum;
    protected List<ProductEligiblity> productEligiblityList;
    protected Boolean assignedOfferInd;
    protected List<Message> offerMarketingMessageList;

    /**
     * Gets the value of the offerId property.
     * 
     */
    public long getOfferId() {
        return offerId;
    }

    /**
     * Sets the value of the offerId property.
     * 
     */
    public void setOfferId(long value) {
        this.offerId = value;
    }

    /**
     * Gets the value of the offerReferenceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOfferReferenceId() {
        return offerReferenceId;
    }

    /**
     * Sets the value of the offerReferenceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOfferReferenceId(String value) {
        this.offerReferenceId = value;
    }

    /**
     * Gets the value of the offerDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getOfferDescriptionList() {
        if (offerDescriptionList == null) {
            offerDescriptionList = new ArrayList<Description>();
        }
        return this.offerDescriptionList;
    }

    /**
     * Gets the value of the offerTierId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOfferTierId() {
        return offerTierId;
    }

    /**
     * Sets the value of the offerTierId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOfferTierId(Integer value) {
        this.offerTierId = value;
    }

    /**
     * Gets the value of the offerCategoryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerCategoryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferCategoryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OfferCategory }
     * 
     * 
     */
    public List<OfferCategory> getOfferCategoryList() {
        if (offerCategoryList == null) {
            offerCategoryList = new ArrayList<OfferCategory>();
        }
        return this.offerCategoryList;
    }

    /**
     * Gets the value of the basePriceAmt property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getBasePriceAmt() {
        return basePriceAmt;
    }

    /**
     * Sets the value of the basePriceAmt property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setBasePriceAmt(Double value) {
        this.basePriceAmt = value;
    }

    /**
     * Gets the value of the wirelessOfferCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWirelessOfferCode() {
        return wirelessOfferCode;
    }

    /**
     * Sets the value of the wirelessOfferCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWirelessOfferCode(String value) {
        this.wirelessOfferCode = value;
    }

    /**
     * Gets the value of the paymentSupportTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPaymentSupportTypeCode() {
        return paymentSupportTypeCode;
    }

    /**
     * Sets the value of the paymentSupportTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPaymentSupportTypeCode(String value) {
        this.paymentSupportTypeCode = value;
    }

    /**
     * Gets the value of the rankingNum property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getRankingNum() {
        return rankingNum;
    }

    /**
     * Sets the value of the rankingNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setRankingNum(BigInteger value) {
        this.rankingNum = value;
    }

    /**
     * Gets the value of the productEligiblityList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productEligiblityList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductEligiblityList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductEligiblity }
     * 
     * 
     */
    public List<ProductEligiblity> getProductEligiblityList() {
        if (productEligiblityList == null) {
            productEligiblityList = new ArrayList<ProductEligiblity>();
        }
        return this.productEligiblityList;
    }

    /**
     * Gets the value of the assignedOfferInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssignedOfferInd() {
        return assignedOfferInd;
    }

    /**
     * Sets the value of the assignedOfferInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssignedOfferInd(Boolean value) {
        this.assignedOfferInd = value;
    }

    /**
     * Gets the value of the offerMarketingMessageList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the offerMarketingMessageList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOfferMarketingMessageList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Message }
     * 
     * 
     */
    public List<Message> getOfferMarketingMessageList() {
        if (offerMarketingMessageList == null) {
            offerMarketingMessageList = new ArrayList<Message>();
        }
        return this.offerMarketingMessageList;
    }

    /**
     * Sets the value of the offerDescriptionList property.
     * 
     * @param offerDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setOfferDescriptionList(List<Description> offerDescriptionList) {
        this.offerDescriptionList = offerDescriptionList;
    }

    /**
     * Sets the value of the offerCategoryList property.
     * 
     * @param offerCategoryList
     *     allowed object is
     *     {@link OfferCategory }
     *     
     */
    public void setOfferCategoryList(List<OfferCategory> offerCategoryList) {
        this.offerCategoryList = offerCategoryList;
    }

    /**
     * Sets the value of the productEligiblityList property.
     * 
     * @param productEligiblityList
     *     allowed object is
     *     {@link ProductEligiblity }
     *     
     */
    public void setProductEligiblityList(List<ProductEligiblity> productEligiblityList) {
        this.productEligiblityList = productEligiblityList;
    }

    /**
     * Sets the value of the offerMarketingMessageList property.
     * 
     * @param offerMarketingMessageList
     *     allowed object is
     *     {@link Message }
     *     
     */
    public void setOfferMarketingMessageList(List<Message> offerMarketingMessageList) {
        this.offerMarketingMessageList = offerMarketingMessageList;
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
        super.appendFields(locator, buffer, strategy);
        {
            long theOfferId;
            theOfferId = (true?this.getOfferId(): 0L);
            strategy.appendField(locator, this, "offerId", buffer, theOfferId);
        }
        {
            String theOfferReferenceId;
            theOfferReferenceId = this.getOfferReferenceId();
            strategy.appendField(locator, this, "offerReferenceId", buffer, theOfferReferenceId);
        }
        {
            List<Description> theOfferDescriptionList;
            theOfferDescriptionList = (((this.offerDescriptionList!= null)&&(!this.offerDescriptionList.isEmpty()))?this.getOfferDescriptionList():null);
            strategy.appendField(locator, this, "offerDescriptionList", buffer, theOfferDescriptionList);
        }
        {
            Integer theOfferTierId;
            theOfferTierId = this.getOfferTierId();
            strategy.appendField(locator, this, "offerTierId", buffer, theOfferTierId);
        }
        {
            List<OfferCategory> theOfferCategoryList;
            theOfferCategoryList = (((this.offerCategoryList!= null)&&(!this.offerCategoryList.isEmpty()))?this.getOfferCategoryList():null);
            strategy.appendField(locator, this, "offerCategoryList", buffer, theOfferCategoryList);
        }
        {
            Double theBasePriceAmt;
            theBasePriceAmt = this.getBasePriceAmt();
            strategy.appendField(locator, this, "basePriceAmt", buffer, theBasePriceAmt);
        }
        {
            String theWirelessOfferCode;
            theWirelessOfferCode = this.getWirelessOfferCode();
            strategy.appendField(locator, this, "wirelessOfferCode", buffer, theWirelessOfferCode);
        }
        {
            String thePaymentSupportTypeCode;
            thePaymentSupportTypeCode = this.getPaymentSupportTypeCode();
            strategy.appendField(locator, this, "paymentSupportTypeCode", buffer, thePaymentSupportTypeCode);
        }
        {
            BigInteger theRankingNum;
            theRankingNum = this.getRankingNum();
            strategy.appendField(locator, this, "rankingNum", buffer, theRankingNum);
        }
        {
            List<ProductEligiblity> theProductEligiblityList;
            theProductEligiblityList = (((this.productEligiblityList!= null)&&(!this.productEligiblityList.isEmpty()))?this.getProductEligiblityList():null);
            strategy.appendField(locator, this, "productEligiblityList", buffer, theProductEligiblityList);
        }
        {
            Boolean theAssignedOfferInd;
            theAssignedOfferInd = this.isAssignedOfferInd();
            strategy.appendField(locator, this, "assignedOfferInd", buffer, theAssignedOfferInd);
        }
        {
            List<Message> theOfferMarketingMessageList;
            theOfferMarketingMessageList = (((this.offerMarketingMessageList!= null)&&(!this.offerMarketingMessageList.isEmpty()))?this.getOfferMarketingMessageList():null);
            strategy.appendField(locator, this, "offerMarketingMessageList", buffer, theOfferMarketingMessageList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof OfferHeader)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final OfferHeader that = ((OfferHeader) object);
        {
            long lhsOfferId;
            lhsOfferId = (true?this.getOfferId(): 0L);
            long rhsOfferId;
            rhsOfferId = (true?that.getOfferId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerId", lhsOfferId), LocatorUtils.property(thatLocator, "offerId", rhsOfferId), lhsOfferId, rhsOfferId)) {
                return false;
            }
        }
        {
            String lhsOfferReferenceId;
            lhsOfferReferenceId = this.getOfferReferenceId();
            String rhsOfferReferenceId;
            rhsOfferReferenceId = that.getOfferReferenceId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerReferenceId", lhsOfferReferenceId), LocatorUtils.property(thatLocator, "offerReferenceId", rhsOfferReferenceId), lhsOfferReferenceId, rhsOfferReferenceId)) {
                return false;
            }
        }
        {
            List<Description> lhsOfferDescriptionList;
            lhsOfferDescriptionList = (((this.offerDescriptionList!= null)&&(!this.offerDescriptionList.isEmpty()))?this.getOfferDescriptionList():null);
            List<Description> rhsOfferDescriptionList;
            rhsOfferDescriptionList = (((that.offerDescriptionList!= null)&&(!that.offerDescriptionList.isEmpty()))?that.getOfferDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerDescriptionList", lhsOfferDescriptionList), LocatorUtils.property(thatLocator, "offerDescriptionList", rhsOfferDescriptionList), lhsOfferDescriptionList, rhsOfferDescriptionList)) {
                return false;
            }
        }
        {
            Integer lhsOfferTierId;
            lhsOfferTierId = this.getOfferTierId();
            Integer rhsOfferTierId;
            rhsOfferTierId = that.getOfferTierId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerTierId", lhsOfferTierId), LocatorUtils.property(thatLocator, "offerTierId", rhsOfferTierId), lhsOfferTierId, rhsOfferTierId)) {
                return false;
            }
        }
        {
            List<OfferCategory> lhsOfferCategoryList;
            lhsOfferCategoryList = (((this.offerCategoryList!= null)&&(!this.offerCategoryList.isEmpty()))?this.getOfferCategoryList():null);
            List<OfferCategory> rhsOfferCategoryList;
            rhsOfferCategoryList = (((that.offerCategoryList!= null)&&(!that.offerCategoryList.isEmpty()))?that.getOfferCategoryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerCategoryList", lhsOfferCategoryList), LocatorUtils.property(thatLocator, "offerCategoryList", rhsOfferCategoryList), lhsOfferCategoryList, rhsOfferCategoryList)) {
                return false;
            }
        }
        {
            Double lhsBasePriceAmt;
            lhsBasePriceAmt = this.getBasePriceAmt();
            Double rhsBasePriceAmt;
            rhsBasePriceAmt = that.getBasePriceAmt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "basePriceAmt", lhsBasePriceAmt), LocatorUtils.property(thatLocator, "basePriceAmt", rhsBasePriceAmt), lhsBasePriceAmt, rhsBasePriceAmt)) {
                return false;
            }
        }
        {
            String lhsWirelessOfferCode;
            lhsWirelessOfferCode = this.getWirelessOfferCode();
            String rhsWirelessOfferCode;
            rhsWirelessOfferCode = that.getWirelessOfferCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelessOfferCode", lhsWirelessOfferCode), LocatorUtils.property(thatLocator, "wirelessOfferCode", rhsWirelessOfferCode), lhsWirelessOfferCode, rhsWirelessOfferCode)) {
                return false;
            }
        }
        {
            String lhsPaymentSupportTypeCode;
            lhsPaymentSupportTypeCode = this.getPaymentSupportTypeCode();
            String rhsPaymentSupportTypeCode;
            rhsPaymentSupportTypeCode = that.getPaymentSupportTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "paymentSupportTypeCode", lhsPaymentSupportTypeCode), LocatorUtils.property(thatLocator, "paymentSupportTypeCode", rhsPaymentSupportTypeCode), lhsPaymentSupportTypeCode, rhsPaymentSupportTypeCode)) {
                return false;
            }
        }
        {
            BigInteger lhsRankingNum;
            lhsRankingNum = this.getRankingNum();
            BigInteger rhsRankingNum;
            rhsRankingNum = that.getRankingNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rankingNum", lhsRankingNum), LocatorUtils.property(thatLocator, "rankingNum", rhsRankingNum), lhsRankingNum, rhsRankingNum)) {
                return false;
            }
        }
        {
            List<ProductEligiblity> lhsProductEligiblityList;
            lhsProductEligiblityList = (((this.productEligiblityList!= null)&&(!this.productEligiblityList.isEmpty()))?this.getProductEligiblityList():null);
            List<ProductEligiblity> rhsProductEligiblityList;
            rhsProductEligiblityList = (((that.productEligiblityList!= null)&&(!that.productEligiblityList.isEmpty()))?that.getProductEligiblityList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productEligiblityList", lhsProductEligiblityList), LocatorUtils.property(thatLocator, "productEligiblityList", rhsProductEligiblityList), lhsProductEligiblityList, rhsProductEligiblityList)) {
                return false;
            }
        }
        {
            Boolean lhsAssignedOfferInd;
            lhsAssignedOfferInd = this.isAssignedOfferInd();
            Boolean rhsAssignedOfferInd;
            rhsAssignedOfferInd = that.isAssignedOfferInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "assignedOfferInd", lhsAssignedOfferInd), LocatorUtils.property(thatLocator, "assignedOfferInd", rhsAssignedOfferInd), lhsAssignedOfferInd, rhsAssignedOfferInd)) {
                return false;
            }
        }
        {
            List<Message> lhsOfferMarketingMessageList;
            lhsOfferMarketingMessageList = (((this.offerMarketingMessageList!= null)&&(!this.offerMarketingMessageList.isEmpty()))?this.getOfferMarketingMessageList():null);
            List<Message> rhsOfferMarketingMessageList;
            rhsOfferMarketingMessageList = (((that.offerMarketingMessageList!= null)&&(!that.offerMarketingMessageList.isEmpty()))?that.getOfferMarketingMessageList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerMarketingMessageList", lhsOfferMarketingMessageList), LocatorUtils.property(thatLocator, "offerMarketingMessageList", rhsOfferMarketingMessageList), lhsOfferMarketingMessageList, rhsOfferMarketingMessageList)) {
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
