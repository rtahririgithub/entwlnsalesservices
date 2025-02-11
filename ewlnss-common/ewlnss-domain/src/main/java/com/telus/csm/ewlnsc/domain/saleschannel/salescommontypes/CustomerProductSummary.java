
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
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


/**
 * Deprecated. To be removed in V6.x. CustomerProductSummary provides a product summary of all Wireline Billing Associated associated with the Customer 
 * 
 * <p>Java class for CustomerProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CustomerProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="accountProductSummaryList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}AccountProductSummary" maxOccurs="100"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CustomerProductSummary", propOrder = {
    "accountProductSummaryList"
})
public class CustomerProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<AccountProductSummary> accountProductSummaryList;

    /**
     * Gets the value of the accountProductSummaryList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accountProductSummaryList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccountProductSummaryList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccountProductSummary }
     * 
     * 
     */
    public List<AccountProductSummary> getAccountProductSummaryList() {
        if (accountProductSummaryList == null) {
            accountProductSummaryList = new ArrayList<AccountProductSummary>();
        }
        return this.accountProductSummaryList;
    }

    /**
     * Sets the value of the accountProductSummaryList property.
     * 
     * @param accountProductSummaryList
     *     allowed object is
     *     {@link AccountProductSummary }
     *     
     */
    public void setAccountProductSummaryList(List<AccountProductSummary> accountProductSummaryList) {
        this.accountProductSummaryList = accountProductSummaryList;
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
            List<AccountProductSummary> theAccountProductSummaryList;
            theAccountProductSummaryList = (((this.accountProductSummaryList!= null)&&(!this.accountProductSummaryList.isEmpty()))?this.getAccountProductSummaryList():null);
            strategy.appendField(locator, this, "accountProductSummaryList", buffer, theAccountProductSummaryList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CustomerProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CustomerProductSummary that = ((CustomerProductSummary) object);
        {
            List<AccountProductSummary> lhsAccountProductSummaryList;
            lhsAccountProductSummaryList = (((this.accountProductSummaryList!= null)&&(!this.accountProductSummaryList.isEmpty()))?this.getAccountProductSummaryList():null);
            List<AccountProductSummary> rhsAccountProductSummaryList;
            rhsAccountProductSummaryList = (((that.accountProductSummaryList!= null)&&(!that.accountProductSummaryList.isEmpty()))?that.getAccountProductSummaryList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "accountProductSummaryList", lhsAccountProductSummaryList), LocatorUtils.property(thatLocator, "accountProductSummaryList", rhsAccountProductSummaryList), lhsAccountProductSummaryList, rhsAccountProductSummaryList)) {
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
