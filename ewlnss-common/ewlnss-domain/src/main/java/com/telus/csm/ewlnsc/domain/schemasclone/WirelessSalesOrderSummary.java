
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.telus.csm.ewlnsc.domain.schemasclone.MobilityOfferHeader;
import com.telus.csm.ewlnsc.domain.schemasclone.WirelessSalesOrderSummary;


public class WirelessSalesOrderSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String billingAccountNumber;
    protected String phoneNumber;
    protected MobilityOfferHeader offerHeader;
    protected String handsetProductCd;

    /**
     * Gets the value of the billingAccountNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBillingAccountNumber() {
        return billingAccountNumber;
    }

    /**
     * Sets the value of the billingAccountNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBillingAccountNumber(String value) {
        this.billingAccountNumber = value;
    }

    /**
     * Gets the value of the phoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the value of the phoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneNumber(String value) {
        this.phoneNumber = value;
    }

    /**
     * Gets the value of the offerHeader property.
     * 
     * @return
     *     possible object is
     *     {@link MobilityOfferHeader }
     *     
     */
    public MobilityOfferHeader getOfferHeader() {
        return offerHeader;
    }

    /**
     * Sets the value of the offerHeader property.
     * 
     * @param value
     *     allowed object is
     *     {@link MobilityOfferHeader }
     *     
     */
    public void setOfferHeader(MobilityOfferHeader value) {
        this.offerHeader = value;
    }

    /**
     * Gets the value of the handsetProductCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHandsetProductCd() {
        return handsetProductCd;
    }

    /**
     * Sets the value of the handsetProductCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHandsetProductCd(String value) {
        this.handsetProductCd = value;
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
            String theBillingAccountNumber;
            theBillingAccountNumber = this.getBillingAccountNumber();
            strategy.appendField(locator, this, "billingAccountNumber", buffer, theBillingAccountNumber);
        }
        {
            String thePhoneNumber;
            thePhoneNumber = this.getPhoneNumber();
            strategy.appendField(locator, this, "phoneNumber", buffer, thePhoneNumber);
        }
        {
            MobilityOfferHeader theOfferHeader;
            theOfferHeader = this.getOfferHeader();
            strategy.appendField(locator, this, "offerHeader", buffer, theOfferHeader);
        }
        {
            String theHandsetProductCd;
            theHandsetProductCd = this.getHandsetProductCd();
            strategy.appendField(locator, this, "handsetProductCd", buffer, theHandsetProductCd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessSalesOrderSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessSalesOrderSummary that = ((WirelessSalesOrderSummary) object);
        {
            String lhsBillingAccountNumber;
            lhsBillingAccountNumber = this.getBillingAccountNumber();
            String rhsBillingAccountNumber;
            rhsBillingAccountNumber = that.getBillingAccountNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "billingAccountNumber", lhsBillingAccountNumber), LocatorUtils.property(thatLocator, "billingAccountNumber", rhsBillingAccountNumber), lhsBillingAccountNumber, rhsBillingAccountNumber)) {
                return false;
            }
        }
        {
            String lhsPhoneNumber;
            lhsPhoneNumber = this.getPhoneNumber();
            String rhsPhoneNumber;
            rhsPhoneNumber = that.getPhoneNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "phoneNumber", lhsPhoneNumber), LocatorUtils.property(thatLocator, "phoneNumber", rhsPhoneNumber), lhsPhoneNumber, rhsPhoneNumber)) {
                return false;
            }
        }
        {
            MobilityOfferHeader lhsOfferHeader;
            lhsOfferHeader = this.getOfferHeader();
            MobilityOfferHeader rhsOfferHeader;
            rhsOfferHeader = that.getOfferHeader();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "offerHeader", lhsOfferHeader), LocatorUtils.property(thatLocator, "offerHeader", rhsOfferHeader), lhsOfferHeader, rhsOfferHeader)) {
                return false;
            }
        }
        {
            String lhsHandsetProductCd;
            lhsHandsetProductCd = this.getHandsetProductCd();
            String rhsHandsetProductCd;
            rhsHandsetProductCd = that.getHandsetProductCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "handsetProductCd", lhsHandsetProductCd), LocatorUtils.property(thatLocator, "handsetProductCd", rhsHandsetProductCd), lhsHandsetProductCd, rhsHandsetProductCd)) {
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
