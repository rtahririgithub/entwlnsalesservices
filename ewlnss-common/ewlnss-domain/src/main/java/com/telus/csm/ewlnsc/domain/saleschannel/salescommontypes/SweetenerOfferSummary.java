
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.telus.csm.ewlnsc.domain.IPromotion;
import com.telus.csm.ewlnsc.domain.PromoCodeRefVO;
import com.telus.csm.ewlnsc.domain.PromotionGroupVO;
import com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes.OfferHeader;


/**
 * Extends OfferHeader; represents the summarized version of a single offer.
 *             
 * 
 * <p>Java class for SweetenerOfferSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SweetenerOfferSummary">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}OfferHeader">
 *       &lt;sequence>
 *         &lt;element name="offerProductSummary">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="wirelineOfferProductSummaryList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferProductSummary" maxOccurs="10" minOccurs="0"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="stackableInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SweetenerOfferSummary", propOrder = {
    "offerProductSummary",
    "stackableInd"
})
public class SweetenerOfferSummary 
    extends OfferHeader
    implements Serializable, Equals, ToString, IPromotion
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected SweetenerOfferSummary.OfferProductSummary offerProductSummary;
    protected Boolean stackableInd;
	private String promotionId;
	private Date promotionPerspectiveDate;
	private PromotionGroupVO promotionGroup;
	private PromoCodeRefVO promoCodeRef;
	private boolean installCredit;  // Gary 2019-07-30 WEEKDAY Installation Credit
   
	/**
     * Gets the value of the offerProductSummary property.
     * 
     * @return
     *     possible object is
     *     {@link SweetenerOfferSummary.OfferProductSummary }
     *     
     */
    public SweetenerOfferSummary.OfferProductSummary getOfferProductSummary() {
        return offerProductSummary;
    }

    /**
     * Sets the value of the offerProductSummary property.
     * 
     * @param value
     *     allowed object is
     *     {@link SweetenerOfferSummary.OfferProductSummary }
     *     
     */
    public void setOfferProductSummary(SweetenerOfferSummary.OfferProductSummary value) {
        this.offerProductSummary = value;
    }

    /**
     * Gets the value of the stackableInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isStackableInd() {
        return stackableInd;
    }

    /**
     * Sets the value of the stackableInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setStackableInd(Boolean value) {
        this.stackableInd = value;
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
            SweetenerOfferSummary.OfferProductSummary theOfferProductSummary;
            theOfferProductSummary = this.getOfferProductSummary();
            strategy.appendField(locator, this, "offerProductSummary", buffer, theOfferProductSummary);
        }
        {
            Boolean theStackableInd;
            theStackableInd = this.isStackableInd();
            strategy.appendField(locator, this, "stackableInd", buffer, theStackableInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SweetenerOfferSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final SweetenerOfferSummary that = ((SweetenerOfferSummary) object);
        {
            SweetenerOfferSummary.OfferProductSummary lhsOfferProductSummary;
            lhsOfferProductSummary = this.getOfferProductSummary();
            SweetenerOfferSummary.OfferProductSummary rhsOfferProductSummary;
            rhsOfferProductSummary = that.getOfferProductSummary();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerProductSummary", lhsOfferProductSummary), LocatorUtils.property(thatLocator, "offerProductSummary", rhsOfferProductSummary), lhsOfferProductSummary, rhsOfferProductSummary)) {
                return false;
            }
        }
        {
            Boolean lhsStackableInd;
            lhsStackableInd = this.isStackableInd();
            Boolean rhsStackableInd;
            rhsStackableInd = that.isStackableInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "stackableInd", lhsStackableInd), LocatorUtils.property(thatLocator, "stackableInd", rhsStackableInd), lhsStackableInd, rhsStackableInd)) {
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
     *         &lt;element name="wirelineOfferProductSummaryList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineOfferProductSummary" maxOccurs="10" minOccurs="0"/>
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
        "wirelineOfferProductSummaryList"
    })
    public static class OfferProductSummary
        implements Serializable, Equals, ToString
    {

        private final static long serialVersionUID = 2L;
        protected List<WirelineOfferProductSummary> wirelineOfferProductSummaryList;

        /**
         * Gets the value of the wirelineOfferProductSummaryList property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the wirelineOfferProductSummaryList property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getWirelineOfferProductSummaryList().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link WirelineOfferProductSummary }
         * 
         * 
         */
        public List<WirelineOfferProductSummary> getWirelineOfferProductSummaryList() {
            if (wirelineOfferProductSummaryList == null) {
                wirelineOfferProductSummaryList = new ArrayList<WirelineOfferProductSummary>();
            }
            return this.wirelineOfferProductSummaryList;
        }

        /**
         * Sets the value of the wirelineOfferProductSummaryList property.
         * 
         * @param wirelineOfferProductSummaryList
         *     allowed object is
         *     {@link WirelineOfferProductSummary }
         *     
         */
        public void setWirelineOfferProductSummaryList(List<WirelineOfferProductSummary> wirelineOfferProductSummaryList) {
            this.wirelineOfferProductSummaryList = wirelineOfferProductSummaryList;
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
                List<WirelineOfferProductSummary> theWirelineOfferProductSummaryList;
                theWirelineOfferProductSummaryList = (((this.wirelineOfferProductSummaryList!= null)&&(!this.wirelineOfferProductSummaryList.isEmpty()))?this.getWirelineOfferProductSummaryList():null);
                strategy.appendField(locator, this, "wirelineOfferProductSummaryList", buffer, theWirelineOfferProductSummaryList);
            }
            return buffer;
        }

        public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
            if (!(object instanceof SweetenerOfferSummary.OfferProductSummary)) {
                return false;
            }
            if (this == object) {
                return true;
            }
            final SweetenerOfferSummary.OfferProductSummary that = ((SweetenerOfferSummary.OfferProductSummary) object);
            {
                List<WirelineOfferProductSummary> lhsWirelineOfferProductSummaryList;
                lhsWirelineOfferProductSummaryList = (((this.wirelineOfferProductSummaryList!= null)&&(!this.wirelineOfferProductSummaryList.isEmpty()))?this.getWirelineOfferProductSummaryList():null);
                List<WirelineOfferProductSummary> rhsWirelineOfferProductSummaryList;
                rhsWirelineOfferProductSummaryList = (((that.wirelineOfferProductSummaryList!= null)&&(!that.wirelineOfferProductSummaryList.isEmpty()))?that.getWirelineOfferProductSummaryList():null);
                if (!strategy.equals(LocatorUtils.property(thisLocator, "wirelineOfferProductSummaryList", lhsWirelineOfferProductSummaryList), LocatorUtils.property(thatLocator, "wirelineOfferProductSummaryList", rhsWirelineOfferProductSummaryList), lhsWirelineOfferProductSummaryList, rhsWirelineOfferProductSummaryList)) {
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


	@Override
	public String getPromotionId() {
		return this.promotionId;
	}

	@Override
	public PromotionGroupVO getPromotionGroup() {
		return this.promotionGroup;
	}
	
	public String setPromotionId(String promotionId) {
		return this.promotionId=promotionId;
	}

	public Date setPromotionPerspectiveDate(Date date) {
		return this.promotionPerspectiveDate = date;
	}

	public PromotionGroupVO setPromotionGroup(PromotionGroupVO promoGroup) {
		return this.promotionGroup = promoGroup;
	}

	@Override
	public PromoCodeRefVO getPromoCodeRef() {
		return promoCodeRef;
	}

	public void setPromoCodeRef(PromoCodeRefVO promoCodeRefVO) {
		this.promoCodeRef = promoCodeRefVO;
	}

	@Override
	public Date getPromotionPerspectiveDate() {
		return promotionPerspectiveDate;
	}
	
	// Gary 2019-07-30 WEEKDAY Installation Credit
	public boolean hasInstallCredit(){
		return this.installCredit;
	}
	
	public void setInstallCredit(boolean installCredit){
		this.installCredit = installCredit;
	}
}
