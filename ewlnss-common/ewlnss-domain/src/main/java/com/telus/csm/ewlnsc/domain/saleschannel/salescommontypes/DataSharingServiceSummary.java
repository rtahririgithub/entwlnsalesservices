
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
 * <p>Java class for DataSharingServiceSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSharingServiceSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSharingServiceCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataSharingContributionAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="pendingTransactionInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSharingServiceSummary", propOrder = {
    "dataSharingServiceCode",
    "dataSharingContributionAmt",
    "pendingTransactionInd"
})
public class DataSharingServiceSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String dataSharingServiceCode;
    protected double dataSharingContributionAmt;
    protected Boolean pendingTransactionInd;

    /**
     * Gets the value of the dataSharingServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSharingServiceCode() {
        return dataSharingServiceCode;
    }

    /**
     * Sets the value of the dataSharingServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSharingServiceCode(String value) {
        this.dataSharingServiceCode = value;
    }

    /**
     * Gets the value of the dataSharingContributionAmt property.
     * 
     */
    public double getDataSharingContributionAmt() {
        return dataSharingContributionAmt;
    }

    /**
     * Sets the value of the dataSharingContributionAmt property.
     * 
     */
    public void setDataSharingContributionAmt(double value) {
        this.dataSharingContributionAmt = value;
    }

    /**
     * Gets the value of the pendingTransactionInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPendingTransactionInd() {
        return pendingTransactionInd;
    }

    /**
     * Sets the value of the pendingTransactionInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPendingTransactionInd(Boolean value) {
        this.pendingTransactionInd = value;
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
            String theDataSharingServiceCode;
            theDataSharingServiceCode = this.getDataSharingServiceCode();
            strategy.appendField(locator, this, "dataSharingServiceCode", buffer, theDataSharingServiceCode);
        }
        {
            double theDataSharingContributionAmt;
            theDataSharingContributionAmt = (true?this.getDataSharingContributionAmt(): 0.0D);
            strategy.appendField(locator, this, "dataSharingContributionAmt", buffer, theDataSharingContributionAmt);
        }
        {
            Boolean thePendingTransactionInd;
            thePendingTransactionInd = this.isPendingTransactionInd();
            strategy.appendField(locator, this, "pendingTransactionInd", buffer, thePendingTransactionInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DataSharingServiceSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DataSharingServiceSummary that = ((DataSharingServiceSummary) object);
        {
            String lhsDataSharingServiceCode;
            lhsDataSharingServiceCode = this.getDataSharingServiceCode();
            String rhsDataSharingServiceCode;
            rhsDataSharingServiceCode = that.getDataSharingServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingServiceCode", lhsDataSharingServiceCode), LocatorUtils.property(thatLocator, "dataSharingServiceCode", rhsDataSharingServiceCode), lhsDataSharingServiceCode, rhsDataSharingServiceCode)) {
                return false;
            }
        }
        {
            double lhsDataSharingContributionAmt;
            lhsDataSharingContributionAmt = (true?this.getDataSharingContributionAmt(): 0.0D);
            double rhsDataSharingContributionAmt;
            rhsDataSharingContributionAmt = (true?that.getDataSharingContributionAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingContributionAmt", lhsDataSharingContributionAmt), LocatorUtils.property(thatLocator, "dataSharingContributionAmt", rhsDataSharingContributionAmt), lhsDataSharingContributionAmt, rhsDataSharingContributionAmt)) {
                return false;
            }
        }
        {
            Boolean lhsPendingTransactionInd;
            lhsPendingTransactionInd = this.isPendingTransactionInd();
            Boolean rhsPendingTransactionInd;
            rhsPendingTransactionInd = that.isPendingTransactionInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "pendingTransactionInd", lhsPendingTransactionInd), LocatorUtils.property(thatLocator, "pendingTransactionInd", rhsPendingTransactionInd), lhsPendingTransactionInd, rhsPendingTransactionInd)) {
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
