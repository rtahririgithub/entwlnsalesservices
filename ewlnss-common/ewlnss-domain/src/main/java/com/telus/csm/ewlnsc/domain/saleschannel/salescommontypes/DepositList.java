
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
 * <p>Java class for DepositList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DepositList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="depositInformation" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}DepositInformation" maxOccurs="50"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DepositList", propOrder = {
    "depositInformation"
})
public class DepositList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<DepositInformation> depositInformation;

    /**
     * Gets the value of the depositInformation property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the depositInformation property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepositInformation().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepositInformation }
     * 
     * 
     */
    public List<DepositInformation> getDepositInformation() {
        if (depositInformation == null) {
            depositInformation = new ArrayList<DepositInformation>();
        }
        return this.depositInformation;
    }

    /**
     * Sets the value of the depositInformation property.
     * 
     * @param depositInformation
     *     allowed object is
     *     {@link DepositInformation }
     *     
     */
    public void setDepositInformation(List<DepositInformation> depositInformation) {
        this.depositInformation = depositInformation;
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
            List<DepositInformation> theDepositInformation;
            theDepositInformation = (((this.depositInformation!= null)&&(!this.depositInformation.isEmpty()))?this.getDepositInformation():null);
            strategy.appendField(locator, this, "depositInformation", buffer, theDepositInformation);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof DepositList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final DepositList that = ((DepositList) object);
        {
            List<DepositInformation> lhsDepositInformation;
            lhsDepositInformation = (((this.depositInformation!= null)&&(!this.depositInformation.isEmpty()))?this.getDepositInformation():null);
            List<DepositInformation> rhsDepositInformation;
            rhsDepositInformation = (((that.depositInformation!= null)&&(!that.depositInformation.isEmpty()))?that.getDepositInformation():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "depositInformation", lhsDepositInformation), LocatorUtils.property(thatLocator, "depositInformation", rhsDepositInformation), lhsDepositInformation, rhsDepositInformation)) {
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
