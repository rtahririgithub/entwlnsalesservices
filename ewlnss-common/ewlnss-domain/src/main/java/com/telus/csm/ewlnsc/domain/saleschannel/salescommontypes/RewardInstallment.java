
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
 * <p>Java class for RewardInstallment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RewardInstallment">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="installmentsLeftNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="installmentsAppliedNum" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="installmentFrequencyTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="installmentDuration" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ContractDuration" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RewardInstallment", propOrder = {
    "installmentsLeftNum",
    "installmentsAppliedNum",
    "installmentFrequencyTxt",
    "installmentDuration"
})
public class RewardInstallment
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected int installmentsLeftNum;
    protected int installmentsAppliedNum;
    @XmlElement(required = true)
    protected String installmentFrequencyTxt;
    protected ContractDuration installmentDuration;

    /**
     * Gets the value of the installmentsLeftNum property.
     * 
     */
    public int getInstallmentsLeftNum() {
        return installmentsLeftNum;
    }

    /**
     * Sets the value of the installmentsLeftNum property.
     * 
     */
    public void setInstallmentsLeftNum(int value) {
        this.installmentsLeftNum = value;
    }

    /**
     * Gets the value of the installmentsAppliedNum property.
     * 
     */
    public int getInstallmentsAppliedNum() {
        return installmentsAppliedNum;
    }

    /**
     * Sets the value of the installmentsAppliedNum property.
     * 
     */
    public void setInstallmentsAppliedNum(int value) {
        this.installmentsAppliedNum = value;
    }

    /**
     * Gets the value of the installmentFrequencyTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstallmentFrequencyTxt() {
        return installmentFrequencyTxt;
    }

    /**
     * Sets the value of the installmentFrequencyTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstallmentFrequencyTxt(String value) {
        this.installmentFrequencyTxt = value;
    }

    /**
     * Gets the value of the installmentDuration property.
     * 
     * @return
     *     possible object is
     *     {@link ContractDuration }
     *     
     */
    public ContractDuration getInstallmentDuration() {
        return installmentDuration;
    }

    /**
     * Sets the value of the installmentDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractDuration }
     *     
     */
    public void setInstallmentDuration(ContractDuration value) {
        this.installmentDuration = value;
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
            int theInstallmentsLeftNum;
            theInstallmentsLeftNum = (true?this.getInstallmentsLeftNum(): 0);
            strategy.appendField(locator, this, "installmentsLeftNum", buffer, theInstallmentsLeftNum);
        }
        {
            int theInstallmentsAppliedNum;
            theInstallmentsAppliedNum = (true?this.getInstallmentsAppliedNum(): 0);
            strategy.appendField(locator, this, "installmentsAppliedNum", buffer, theInstallmentsAppliedNum);
        }
        {
            String theInstallmentFrequencyTxt;
            theInstallmentFrequencyTxt = this.getInstallmentFrequencyTxt();
            strategy.appendField(locator, this, "installmentFrequencyTxt", buffer, theInstallmentFrequencyTxt);
        }
        {
            ContractDuration theInstallmentDuration;
            theInstallmentDuration = this.getInstallmentDuration();
            strategy.appendField(locator, this, "installmentDuration", buffer, theInstallmentDuration);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof RewardInstallment)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final RewardInstallment that = ((RewardInstallment) object);
        {
            int lhsInstallmentsLeftNum;
            lhsInstallmentsLeftNum = (true?this.getInstallmentsLeftNum(): 0);
            int rhsInstallmentsLeftNum;
            rhsInstallmentsLeftNum = (true?that.getInstallmentsLeftNum(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "installmentsLeftNum", lhsInstallmentsLeftNum), LocatorUtils.property(thatLocator, "installmentsLeftNum", rhsInstallmentsLeftNum), lhsInstallmentsLeftNum, rhsInstallmentsLeftNum)) {
                return false;
            }
        }
        {
            int lhsInstallmentsAppliedNum;
            lhsInstallmentsAppliedNum = (true?this.getInstallmentsAppliedNum(): 0);
            int rhsInstallmentsAppliedNum;
            rhsInstallmentsAppliedNum = (true?that.getInstallmentsAppliedNum(): 0);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "installmentsAppliedNum", lhsInstallmentsAppliedNum), LocatorUtils.property(thatLocator, "installmentsAppliedNum", rhsInstallmentsAppliedNum), lhsInstallmentsAppliedNum, rhsInstallmentsAppliedNum)) {
                return false;
            }
        }
        {
            String lhsInstallmentFrequencyTxt;
            lhsInstallmentFrequencyTxt = this.getInstallmentFrequencyTxt();
            String rhsInstallmentFrequencyTxt;
            rhsInstallmentFrequencyTxt = that.getInstallmentFrequencyTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "installmentFrequencyTxt", lhsInstallmentFrequencyTxt), LocatorUtils.property(thatLocator, "installmentFrequencyTxt", rhsInstallmentFrequencyTxt), lhsInstallmentFrequencyTxt, rhsInstallmentFrequencyTxt)) {
                return false;
            }
        }
        {
            ContractDuration lhsInstallmentDuration;
            lhsInstallmentDuration = this.getInstallmentDuration();
            ContractDuration rhsInstallmentDuration;
            rhsInstallmentDuration = that.getInstallmentDuration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "installmentDuration", lhsInstallmentDuration), LocatorUtils.property(thatLocator, "installmentDuration", rhsInstallmentDuration), lhsInstallmentDuration, rhsInstallmentDuration)) {
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
