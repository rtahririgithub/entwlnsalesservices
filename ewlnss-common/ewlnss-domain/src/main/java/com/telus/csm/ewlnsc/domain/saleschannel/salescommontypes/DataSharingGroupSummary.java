
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
 * <p>Java class for DataSharingGroupSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataSharingGroupSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="dataSharingGroupCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contributingInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataSharingGroupSummary", propOrder = {
    "dataSharingGroupCode",
    "contributingInd"
})
public class DataSharingGroupSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String dataSharingGroupCode;
    protected boolean contributingInd;

    /**
     * Gets the value of the dataSharingGroupCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataSharingGroupCode() {
        return dataSharingGroupCode;
    }

    /**
     * Sets the value of the dataSharingGroupCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataSharingGroupCode(String value) {
        this.dataSharingGroupCode = value;
    }

    /**
     * Gets the value of the contributingInd property.
     * 
     */
    public boolean isContributingInd() {
        return contributingInd;
    }

    /**
     * Sets the value of the contributingInd property.
     * 
     */
    public void setContributingInd(boolean value) {
        this.contributingInd = value;
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
            String theDataSharingGroupCode;
            theDataSharingGroupCode = this.getDataSharingGroupCode();
            strategy.appendField(locator, this, "dataSharingGroupCode", buffer, theDataSharingGroupCode);
        }
        {
            boolean theContributingInd;
            theContributingInd = (true?this.isContributingInd():false);
            strategy.appendField(locator, this, "contributingInd", buffer, theContributingInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DataSharingGroupSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DataSharingGroupSummary that = ((DataSharingGroupSummary) object);
        {
            String lhsDataSharingGroupCode;
            lhsDataSharingGroupCode = this.getDataSharingGroupCode();
            String rhsDataSharingGroupCode;
            rhsDataSharingGroupCode = that.getDataSharingGroupCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataSharingGroupCode", lhsDataSharingGroupCode), LocatorUtils.property(thatLocator, "dataSharingGroupCode", rhsDataSharingGroupCode), lhsDataSharingGroupCode, rhsDataSharingGroupCode)) {
                return false;
            }
        }
        {
            boolean lhsContributingInd;
            lhsContributingInd = (true?this.isContributingInd():false);
            boolean rhsContributingInd;
            rhsContributingInd = (true?that.isContributingInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contributingInd", lhsContributingInd), LocatorUtils.property(thatLocator, "contributingInd", rhsContributingInd), lhsContributingInd, rhsContributingInd)) {
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
