
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
 * <p>Java class for CreditEligibilityResult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CreditEligibilityResult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="fraudInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="inTreatementInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="eligibilityWarningMessageCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="equipmentQualificationList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductEquipment" maxOccurs="100" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreditEligibilityResult", propOrder = {
    "fraudInd",
    "inTreatementInd",
    "eligibilityWarningMessageCd",
    "equipmentQualificationList"
})
public class CreditEligibilityResult
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected boolean fraudInd;
    protected boolean inTreatementInd;
    @XmlElement(required = true)
    protected String eligibilityWarningMessageCd;
    protected List<ProductEquipment> equipmentQualificationList;

    /**
     * Gets the value of the fraudInd property.
     * 
     */
    public boolean isFraudInd() {
        return fraudInd;
    }

    /**
     * Sets the value of the fraudInd property.
     * 
     */
    public void setFraudInd(boolean value) {
        this.fraudInd = value;
    }

    /**
     * Gets the value of the inTreatementInd property.
     * 
     */
    public boolean isInTreatementInd() {
        return inTreatementInd;
    }

    /**
     * Sets the value of the inTreatementInd property.
     * 
     */
    public void setInTreatementInd(boolean value) {
        this.inTreatementInd = value;
    }

    /**
     * Gets the value of the eligibilityWarningMessageCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEligibilityWarningMessageCd() {
        return eligibilityWarningMessageCd;
    }

    /**
     * Sets the value of the eligibilityWarningMessageCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEligibilityWarningMessageCd(String value) {
        this.eligibilityWarningMessageCd = value;
    }

    /**
     * Gets the value of the equipmentQualificationList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the equipmentQualificationList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEquipmentQualificationList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ProductEquipment }
     * 
     * 
     */
    public List<ProductEquipment> getEquipmentQualificationList() {
        if (equipmentQualificationList == null) {
            equipmentQualificationList = new ArrayList<ProductEquipment>();
        }
        return this.equipmentQualificationList;
    }

    /**
     * Sets the value of the equipmentQualificationList property.
     * 
     * @param equipmentQualificationList
     *     allowed object is
     *     {@link ProductEquipment }
     *     
     */
    public void setEquipmentQualificationList(List<ProductEquipment> equipmentQualificationList) {
        this.equipmentQualificationList = equipmentQualificationList;
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
            boolean theFraudInd;
            theFraudInd = (true?this.isFraudInd():false);
            strategy.appendField(locator, this, "fraudInd", buffer, theFraudInd);
        }
        {
            boolean theInTreatementInd;
            theInTreatementInd = (true?this.isInTreatementInd():false);
            strategy.appendField(locator, this, "inTreatementInd", buffer, theInTreatementInd);
        }
        {
            String theEligibilityWarningMessageCd;
            theEligibilityWarningMessageCd = this.getEligibilityWarningMessageCd();
            strategy.appendField(locator, this, "eligibilityWarningMessageCd", buffer, theEligibilityWarningMessageCd);
        }
        {
            List<ProductEquipment> theEquipmentQualificationList;
            theEquipmentQualificationList = (((this.equipmentQualificationList!= null)&&(!this.equipmentQualificationList.isEmpty()))?this.getEquipmentQualificationList():null);
            strategy.appendField(locator, this, "equipmentQualificationList", buffer, theEquipmentQualificationList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CreditEligibilityResult)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CreditEligibilityResult that = ((CreditEligibilityResult) object);
        {
            boolean lhsFraudInd;
            lhsFraudInd = (true?this.isFraudInd():false);
            boolean rhsFraudInd;
            rhsFraudInd = (true?that.isFraudInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "fraudInd", lhsFraudInd), LocatorUtils.property(thatLocator, "fraudInd", rhsFraudInd), lhsFraudInd, rhsFraudInd)) {
                return false;
            }
        }
        {
            boolean lhsInTreatementInd;
            lhsInTreatementInd = (true?this.isInTreatementInd():false);
            boolean rhsInTreatementInd;
            rhsInTreatementInd = (true?that.isInTreatementInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "inTreatementInd", lhsInTreatementInd), LocatorUtils.property(thatLocator, "inTreatementInd", rhsInTreatementInd), lhsInTreatementInd, rhsInTreatementInd)) {
                return false;
            }
        }
        {
            String lhsEligibilityWarningMessageCd;
            lhsEligibilityWarningMessageCd = this.getEligibilityWarningMessageCd();
            String rhsEligibilityWarningMessageCd;
            rhsEligibilityWarningMessageCd = that.getEligibilityWarningMessageCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "eligibilityWarningMessageCd", lhsEligibilityWarningMessageCd), LocatorUtils.property(thatLocator, "eligibilityWarningMessageCd", rhsEligibilityWarningMessageCd), lhsEligibilityWarningMessageCd, rhsEligibilityWarningMessageCd)) {
                return false;
            }
        }
        {
            List<ProductEquipment> lhsEquipmentQualificationList;
            lhsEquipmentQualificationList = (((this.equipmentQualificationList!= null)&&(!this.equipmentQualificationList.isEmpty()))?this.getEquipmentQualificationList():null);
            List<ProductEquipment> rhsEquipmentQualificationList;
            rhsEquipmentQualificationList = (((that.equipmentQualificationList!= null)&&(!that.equipmentQualificationList.isEmpty()))?that.getEquipmentQualificationList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentQualificationList", lhsEquipmentQualificationList), LocatorUtils.property(thatLocator, "equipmentQualificationList", rhsEquipmentQualificationList), lhsEquipmentQualificationList, rhsEquipmentQualificationList)) {
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
