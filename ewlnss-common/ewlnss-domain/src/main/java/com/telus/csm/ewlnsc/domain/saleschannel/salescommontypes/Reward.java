
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualCodeDescTextList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Reward amount redeemed.
 * 
 * <p>Java class for Reward complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Reward">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="typeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="incentiveAmount" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="rewardInstallment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}RewardInstallment" minOccurs="0"/>
 *         &lt;element name="contractDuration" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ContractDuration"/>
 *         &lt;element name="minimumCommitment" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}MinimumCommitment" minOccurs="0"/>
 *         &lt;element name="minimumServiceCommitmentPaidIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="redeemedOfferID" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="redeemedOfferContextCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dataCommitmentIndicator" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="originalHandsetValueAmt" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="usimID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualCodeDescTextList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Reward", propOrder = {
    "typeCode",
    "incentiveAmount",
    "rewardInstallment",
    "contractDuration",
    "minimumCommitment",
    "minimumServiceCommitmentPaidIndicator",
    "redeemedOfferID",
    "redeemedOfferContextCode",
    "dataCommitmentIndicator",
    "originalHandsetValueAmt",
    "serialNumber",
    "usimID",
    "productName"
})
public class Reward
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String typeCode;
    protected double incentiveAmount;
    protected RewardInstallment rewardInstallment;
    @XmlElement(required = true)
    protected ContractDuration contractDuration;
    protected MinimumCommitment minimumCommitment;
    protected Boolean minimumServiceCommitmentPaidIndicator;
    protected Long redeemedOfferID;
    protected String redeemedOfferContextCode;
    protected boolean dataCommitmentIndicator;
    protected double originalHandsetValueAmt;
    protected String serialNumber;
    protected String usimID;
    protected MultilingualCodeDescTextList productName;

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the incentiveAmount property.
     * 
     */
    public double getIncentiveAmount() {
        return incentiveAmount;
    }

    /**
     * Sets the value of the incentiveAmount property.
     * 
     */
    public void setIncentiveAmount(double value) {
        this.incentiveAmount = value;
    }

    /**
     * Gets the value of the rewardInstallment property.
     * 
     * @return
     *     possible object is
     *     {@link RewardInstallment }
     *     
     */
    public RewardInstallment getRewardInstallment() {
        return rewardInstallment;
    }

    /**
     * Sets the value of the rewardInstallment property.
     * 
     * @param value
     *     allowed object is
     *     {@link RewardInstallment }
     *     
     */
    public void setRewardInstallment(RewardInstallment value) {
        this.rewardInstallment = value;
    }

    /**
     * Gets the value of the contractDuration property.
     * 
     * @return
     *     possible object is
     *     {@link ContractDuration }
     *     
     */
    public ContractDuration getContractDuration() {
        return contractDuration;
    }

    /**
     * Sets the value of the contractDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractDuration }
     *     
     */
    public void setContractDuration(ContractDuration value) {
        this.contractDuration = value;
    }

    /**
     * Gets the value of the minimumCommitment property.
     * 
     * @return
     *     possible object is
     *     {@link MinimumCommitment }
     *     
     */
    public MinimumCommitment getMinimumCommitment() {
        return minimumCommitment;
    }

    /**
     * Sets the value of the minimumCommitment property.
     * 
     * @param value
     *     allowed object is
     *     {@link MinimumCommitment }
     *     
     */
    public void setMinimumCommitment(MinimumCommitment value) {
        this.minimumCommitment = value;
    }

    /**
     * Gets the value of the minimumServiceCommitmentPaidIndicator property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isMinimumServiceCommitmentPaidIndicator() {
        return minimumServiceCommitmentPaidIndicator;
    }

    /**
     * Sets the value of the minimumServiceCommitmentPaidIndicator property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setMinimumServiceCommitmentPaidIndicator(Boolean value) {
        this.minimumServiceCommitmentPaidIndicator = value;
    }

    /**
     * Gets the value of the redeemedOfferID property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getRedeemedOfferID() {
        return redeemedOfferID;
    }

    /**
     * Sets the value of the redeemedOfferID property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setRedeemedOfferID(Long value) {
        this.redeemedOfferID = value;
    }

    /**
     * Gets the value of the redeemedOfferContextCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRedeemedOfferContextCode() {
        return redeemedOfferContextCode;
    }

    /**
     * Sets the value of the redeemedOfferContextCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRedeemedOfferContextCode(String value) {
        this.redeemedOfferContextCode = value;
    }

    /**
     * Gets the value of the dataCommitmentIndicator property.
     * 
     */
    public boolean isDataCommitmentIndicator() {
        return dataCommitmentIndicator;
    }

    /**
     * Sets the value of the dataCommitmentIndicator property.
     * 
     */
    public void setDataCommitmentIndicator(boolean value) {
        this.dataCommitmentIndicator = value;
    }

    /**
     * Gets the value of the originalHandsetValueAmt property.
     * 
     */
    public double getOriginalHandsetValueAmt() {
        return originalHandsetValueAmt;
    }

    /**
     * Sets the value of the originalHandsetValueAmt property.
     * 
     */
    public void setOriginalHandsetValueAmt(double value) {
        this.originalHandsetValueAmt = value;
    }

    /**
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
    }

    /**
     * Gets the value of the usimID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsimID() {
        return usimID;
    }

    /**
     * Sets the value of the usimID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsimID(String value) {
        this.usimID = value;
    }

    /**
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public MultilingualCodeDescTextList getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualCodeDescTextList }
     *     
     */
    public void setProductName(MultilingualCodeDescTextList value) {
        this.productName = value;
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
            String theTypeCode;
            theTypeCode = this.getTypeCode();
            strategy.appendField(locator, this, "typeCode", buffer, theTypeCode);
        }
        {
            double theIncentiveAmount;
            theIncentiveAmount = (true?this.getIncentiveAmount(): 0.0D);
            strategy.appendField(locator, this, "incentiveAmount", buffer, theIncentiveAmount);
        }
        {
            RewardInstallment theRewardInstallment;
            theRewardInstallment = this.getRewardInstallment();
            strategy.appendField(locator, this, "rewardInstallment", buffer, theRewardInstallment);
        }
        {
            ContractDuration theContractDuration;
            theContractDuration = this.getContractDuration();
            strategy.appendField(locator, this, "contractDuration", buffer, theContractDuration);
        }
        {
            MinimumCommitment theMinimumCommitment;
            theMinimumCommitment = this.getMinimumCommitment();
            strategy.appendField(locator, this, "minimumCommitment", buffer, theMinimumCommitment);
        }
        {
            Boolean theMinimumServiceCommitmentPaidIndicator;
            theMinimumServiceCommitmentPaidIndicator = this.isMinimumServiceCommitmentPaidIndicator();
            strategy.appendField(locator, this, "minimumServiceCommitmentPaidIndicator", buffer, theMinimumServiceCommitmentPaidIndicator);
        }
        {
            Long theRedeemedOfferID;
            theRedeemedOfferID = this.getRedeemedOfferID();
            strategy.appendField(locator, this, "redeemedOfferID", buffer, theRedeemedOfferID);
        }
        {
            String theRedeemedOfferContextCode;
            theRedeemedOfferContextCode = this.getRedeemedOfferContextCode();
            strategy.appendField(locator, this, "redeemedOfferContextCode", buffer, theRedeemedOfferContextCode);
        }
        {
            boolean theDataCommitmentIndicator;
            theDataCommitmentIndicator = (true?this.isDataCommitmentIndicator():false);
            strategy.appendField(locator, this, "dataCommitmentIndicator", buffer, theDataCommitmentIndicator);
        }
        {
            double theOriginalHandsetValueAmt;
            theOriginalHandsetValueAmt = (true?this.getOriginalHandsetValueAmt(): 0.0D);
            strategy.appendField(locator, this, "originalHandsetValueAmt", buffer, theOriginalHandsetValueAmt);
        }
        {
            String theSerialNumber;
            theSerialNumber = this.getSerialNumber();
            strategy.appendField(locator, this, "serialNumber", buffer, theSerialNumber);
        }
        {
            String theUsimID;
            theUsimID = this.getUsimID();
            strategy.appendField(locator, this, "usimID", buffer, theUsimID);
        }
        {
            MultilingualCodeDescTextList theProductName;
            theProductName = this.getProductName();
            strategy.appendField(locator, this, "productName", buffer, theProductName);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Reward)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Reward that = ((Reward) object);
        {
            String lhsTypeCode;
            lhsTypeCode = this.getTypeCode();
            String rhsTypeCode;
            rhsTypeCode = that.getTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "typeCode", lhsTypeCode), LocatorUtils.property(thatLocator, "typeCode", rhsTypeCode), lhsTypeCode, rhsTypeCode)) {
                return false;
            }
        }
        {
            double lhsIncentiveAmount;
            lhsIncentiveAmount = (true?this.getIncentiveAmount(): 0.0D);
            double rhsIncentiveAmount;
            rhsIncentiveAmount = (true?that.getIncentiveAmount(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "incentiveAmount", lhsIncentiveAmount), LocatorUtils.property(thatLocator, "incentiveAmount", rhsIncentiveAmount), lhsIncentiveAmount, rhsIncentiveAmount)) {
                return false;
            }
        }
        {
            RewardInstallment lhsRewardInstallment;
            lhsRewardInstallment = this.getRewardInstallment();
            RewardInstallment rhsRewardInstallment;
            rhsRewardInstallment = that.getRewardInstallment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rewardInstallment", lhsRewardInstallment), LocatorUtils.property(thatLocator, "rewardInstallment", rhsRewardInstallment), lhsRewardInstallment, rhsRewardInstallment)) {
                return false;
            }
        }
        {
            ContractDuration lhsContractDuration;
            lhsContractDuration = this.getContractDuration();
            ContractDuration rhsContractDuration;
            rhsContractDuration = that.getContractDuration();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractDuration", lhsContractDuration), LocatorUtils.property(thatLocator, "contractDuration", rhsContractDuration), lhsContractDuration, rhsContractDuration)) {
                return false;
            }
        }
        {
            MinimumCommitment lhsMinimumCommitment;
            lhsMinimumCommitment = this.getMinimumCommitment();
            MinimumCommitment rhsMinimumCommitment;
            rhsMinimumCommitment = that.getMinimumCommitment();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumCommitment", lhsMinimumCommitment), LocatorUtils.property(thatLocator, "minimumCommitment", rhsMinimumCommitment), lhsMinimumCommitment, rhsMinimumCommitment)) {
                return false;
            }
        }
        {
            Boolean lhsMinimumServiceCommitmentPaidIndicator;
            lhsMinimumServiceCommitmentPaidIndicator = this.isMinimumServiceCommitmentPaidIndicator();
            Boolean rhsMinimumServiceCommitmentPaidIndicator;
            rhsMinimumServiceCommitmentPaidIndicator = that.isMinimumServiceCommitmentPaidIndicator();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "minimumServiceCommitmentPaidIndicator", lhsMinimumServiceCommitmentPaidIndicator), LocatorUtils.property(thatLocator, "minimumServiceCommitmentPaidIndicator", rhsMinimumServiceCommitmentPaidIndicator), lhsMinimumServiceCommitmentPaidIndicator, rhsMinimumServiceCommitmentPaidIndicator)) {
                return false;
            }
        }
        {
            Long lhsRedeemedOfferID;
            lhsRedeemedOfferID = this.getRedeemedOfferID();
            Long rhsRedeemedOfferID;
            rhsRedeemedOfferID = that.getRedeemedOfferID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "redeemedOfferID", lhsRedeemedOfferID), LocatorUtils.property(thatLocator, "redeemedOfferID", rhsRedeemedOfferID), lhsRedeemedOfferID, rhsRedeemedOfferID)) {
                return false;
            }
        }
        {
            String lhsRedeemedOfferContextCode;
            lhsRedeemedOfferContextCode = this.getRedeemedOfferContextCode();
            String rhsRedeemedOfferContextCode;
            rhsRedeemedOfferContextCode = that.getRedeemedOfferContextCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "redeemedOfferContextCode", lhsRedeemedOfferContextCode), LocatorUtils.property(thatLocator, "redeemedOfferContextCode", rhsRedeemedOfferContextCode), lhsRedeemedOfferContextCode, rhsRedeemedOfferContextCode)) {
                return false;
            }
        }
        {
            boolean lhsDataCommitmentIndicator;
            lhsDataCommitmentIndicator = (true?this.isDataCommitmentIndicator():false);
            boolean rhsDataCommitmentIndicator;
            rhsDataCommitmentIndicator = (true?that.isDataCommitmentIndicator():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dataCommitmentIndicator", lhsDataCommitmentIndicator), LocatorUtils.property(thatLocator, "dataCommitmentIndicator", rhsDataCommitmentIndicator), lhsDataCommitmentIndicator, rhsDataCommitmentIndicator)) {
                return false;
            }
        }
        {
            double lhsOriginalHandsetValueAmt;
            lhsOriginalHandsetValueAmt = (true?this.getOriginalHandsetValueAmt(): 0.0D);
            double rhsOriginalHandsetValueAmt;
            rhsOriginalHandsetValueAmt = (true?that.getOriginalHandsetValueAmt(): 0.0D);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "originalHandsetValueAmt", lhsOriginalHandsetValueAmt), LocatorUtils.property(thatLocator, "originalHandsetValueAmt", rhsOriginalHandsetValueAmt), lhsOriginalHandsetValueAmt, rhsOriginalHandsetValueAmt)) {
                return false;
            }
        }
        {
            String lhsSerialNumber;
            lhsSerialNumber = this.getSerialNumber();
            String rhsSerialNumber;
            rhsSerialNumber = that.getSerialNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serialNumber", lhsSerialNumber), LocatorUtils.property(thatLocator, "serialNumber", rhsSerialNumber), lhsSerialNumber, rhsSerialNumber)) {
                return false;
            }
        }
        {
            String lhsUsimID;
            lhsUsimID = this.getUsimID();
            String rhsUsimID;
            rhsUsimID = that.getUsimID();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "usimID", lhsUsimID), LocatorUtils.property(thatLocator, "usimID", rhsUsimID), lhsUsimID, rhsUsimID)) {
                return false;
            }
        }
        {
            MultilingualCodeDescTextList lhsProductName;
            lhsProductName = this.getProductName();
            MultilingualCodeDescTextList rhsProductName;
            rhsProductName = that.getProductName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productName", lhsProductName), LocatorUtils.property(thatLocator, "productName", rhsProductName), lhsProductName, rhsProductName)) {
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
