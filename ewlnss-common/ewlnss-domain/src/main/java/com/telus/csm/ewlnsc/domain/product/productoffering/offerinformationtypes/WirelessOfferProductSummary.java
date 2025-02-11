
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
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
 * Summarized version of the 'WirelessProduct' type.
 * 
 * <p>Java class for WirelessOfferProductSummary complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelessOfferProductSummary">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="transactionType" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}TransactionType"/>
 *         &lt;element name="equipmentContextInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="contractTermId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="clientOwnEquipmentInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="keepExistingPlanId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="financeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="optionTypeId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="contractCustomization" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}ContractCustomization" minOccurs="0"/>
 *         &lt;element name="productCatalogSystemId" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}systemId"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelessOfferProductSummary", propOrder = {
    "transactionType",
    "equipmentContextInd",
    "contractTermId",
    "clientOwnEquipmentInd",
    "keepExistingPlanId",
    "financeCode",
    "optionTypeId",
    "contractCustomization",
    "productCatalogSystemId"
})
@XmlSeeAlso({
    WirelessOfferProduct.class
})
public class WirelessOfferProductSummary
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected TransactionType transactionType;
    protected boolean equipmentContextInd;
    @XmlElement(required = true)
    protected BigInteger contractTermId;
    protected boolean clientOwnEquipmentInd;
    protected Integer keepExistingPlanId;
    protected String financeCode;
    protected Integer optionTypeId;
    protected ContractCustomization contractCustomization;
    @XmlElement(required = true)
    protected String productCatalogSystemId;

    /**
     * Gets the value of the transactionType property.
     * 
     * @return
     *     possible object is
     *     {@link TransactionType }
     *     
     */
    public TransactionType getTransactionType() {
        return transactionType;
    }

    /**
     * Sets the value of the transactionType property.
     * 
     * @param value
     *     allowed object is
     *     {@link TransactionType }
     *     
     */
    public void setTransactionType(TransactionType value) {
        this.transactionType = value;
    }

    /**
     * Gets the value of the equipmentContextInd property.
     * 
     */
    public boolean isEquipmentContextInd() {
        return equipmentContextInd;
    }

    /**
     * Sets the value of the equipmentContextInd property.
     * 
     */
    public void setEquipmentContextInd(boolean value) {
        this.equipmentContextInd = value;
    }

    /**
     * Gets the value of the contractTermId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getContractTermId() {
        return contractTermId;
    }

    /**
     * Sets the value of the contractTermId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTermId(BigInteger value) {
        this.contractTermId = value;
    }

    /**
     * Gets the value of the clientOwnEquipmentInd property.
     * 
     */
    public boolean isClientOwnEquipmentInd() {
        return clientOwnEquipmentInd;
    }

    /**
     * Sets the value of the clientOwnEquipmentInd property.
     * 
     */
    public void setClientOwnEquipmentInd(boolean value) {
        this.clientOwnEquipmentInd = value;
    }

    /**
     * Gets the value of the keepExistingPlanId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getKeepExistingPlanId() {
        return keepExistingPlanId;
    }

    /**
     * Sets the value of the keepExistingPlanId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setKeepExistingPlanId(Integer value) {
        this.keepExistingPlanId = value;
    }

    /**
     * Gets the value of the financeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFinanceCode() {
        return financeCode;
    }

    /**
     * Sets the value of the financeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFinanceCode(String value) {
        this.financeCode = value;
    }

    /**
     * Gets the value of the optionTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getOptionTypeId() {
        return optionTypeId;
    }

    /**
     * Sets the value of the optionTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setOptionTypeId(Integer value) {
        this.optionTypeId = value;
    }

    /**
     * Gets the value of the contractCustomization property.
     * 
     * @return
     *     possible object is
     *     {@link ContractCustomization }
     *     
     */
    public ContractCustomization getContractCustomization() {
        return contractCustomization;
    }

    /**
     * Sets the value of the contractCustomization property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContractCustomization }
     *     
     */
    public void setContractCustomization(ContractCustomization value) {
        this.contractCustomization = value;
    }

    /**
     * Gets the value of the productCatalogSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCatalogSystemId() {
        return productCatalogSystemId;
    }

    /**
     * Sets the value of the productCatalogSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCatalogSystemId(String value) {
        this.productCatalogSystemId = value;
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
            TransactionType theTransactionType;
            theTransactionType = this.getTransactionType();
            strategy.appendField(locator, this, "transactionType", buffer, theTransactionType);
        }
        {
            boolean theEquipmentContextInd;
            theEquipmentContextInd = (true?this.isEquipmentContextInd():false);
            strategy.appendField(locator, this, "equipmentContextInd", buffer, theEquipmentContextInd);
        }
        {
            BigInteger theContractTermId;
            theContractTermId = this.getContractTermId();
            strategy.appendField(locator, this, "contractTermId", buffer, theContractTermId);
        }
        {
            boolean theClientOwnEquipmentInd;
            theClientOwnEquipmentInd = (true?this.isClientOwnEquipmentInd():false);
            strategy.appendField(locator, this, "clientOwnEquipmentInd", buffer, theClientOwnEquipmentInd);
        }
        {
            Integer theKeepExistingPlanId;
            theKeepExistingPlanId = this.getKeepExistingPlanId();
            strategy.appendField(locator, this, "keepExistingPlanId", buffer, theKeepExistingPlanId);
        }
        {
            String theFinanceCode;
            theFinanceCode = this.getFinanceCode();
            strategy.appendField(locator, this, "financeCode", buffer, theFinanceCode);
        }
        {
            Integer theOptionTypeId;
            theOptionTypeId = this.getOptionTypeId();
            strategy.appendField(locator, this, "optionTypeId", buffer, theOptionTypeId);
        }
        {
            ContractCustomization theContractCustomization;
            theContractCustomization = this.getContractCustomization();
            strategy.appendField(locator, this, "contractCustomization", buffer, theContractCustomization);
        }
        {
            String theProductCatalogSystemId;
            theProductCatalogSystemId = this.getProductCatalogSystemId();
            strategy.appendField(locator, this, "productCatalogSystemId", buffer, theProductCatalogSystemId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelessOfferProductSummary)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelessOfferProductSummary that = ((WirelessOfferProductSummary) object);
        {
            TransactionType lhsTransactionType;
            lhsTransactionType = this.getTransactionType();
            TransactionType rhsTransactionType;
            rhsTransactionType = that.getTransactionType();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transactionType", lhsTransactionType), LocatorUtils.property(thatLocator, "transactionType", rhsTransactionType), lhsTransactionType, rhsTransactionType)) {
                return false;
            }
        }
        {
            boolean lhsEquipmentContextInd;
            lhsEquipmentContextInd = (true?this.isEquipmentContextInd():false);
            boolean rhsEquipmentContextInd;
            rhsEquipmentContextInd = (true?that.isEquipmentContextInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "equipmentContextInd", lhsEquipmentContextInd), LocatorUtils.property(thatLocator, "equipmentContextInd", rhsEquipmentContextInd), lhsEquipmentContextInd, rhsEquipmentContextInd)) {
                return false;
            }
        }
        {
            BigInteger lhsContractTermId;
            lhsContractTermId = this.getContractTermId();
            BigInteger rhsContractTermId;
            rhsContractTermId = that.getContractTermId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermId", lhsContractTermId), LocatorUtils.property(thatLocator, "contractTermId", rhsContractTermId), lhsContractTermId, rhsContractTermId)) {
                return false;
            }
        }
        {
            boolean lhsClientOwnEquipmentInd;
            lhsClientOwnEquipmentInd = (true?this.isClientOwnEquipmentInd():false);
            boolean rhsClientOwnEquipmentInd;
            rhsClientOwnEquipmentInd = (true?that.isClientOwnEquipmentInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "clientOwnEquipmentInd", lhsClientOwnEquipmentInd), LocatorUtils.property(thatLocator, "clientOwnEquipmentInd", rhsClientOwnEquipmentInd), lhsClientOwnEquipmentInd, rhsClientOwnEquipmentInd)) {
                return false;
            }
        }
        {
            Integer lhsKeepExistingPlanId;
            lhsKeepExistingPlanId = this.getKeepExistingPlanId();
            Integer rhsKeepExistingPlanId;
            rhsKeepExistingPlanId = that.getKeepExistingPlanId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "keepExistingPlanId", lhsKeepExistingPlanId), LocatorUtils.property(thatLocator, "keepExistingPlanId", rhsKeepExistingPlanId), lhsKeepExistingPlanId, rhsKeepExistingPlanId)) {
                return false;
            }
        }
        {
            String lhsFinanceCode;
            lhsFinanceCode = this.getFinanceCode();
            String rhsFinanceCode;
            rhsFinanceCode = that.getFinanceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "financeCode", lhsFinanceCode), LocatorUtils.property(thatLocator, "financeCode", rhsFinanceCode), lhsFinanceCode, rhsFinanceCode)) {
                return false;
            }
        }
        {
            Integer lhsOptionTypeId;
            lhsOptionTypeId = this.getOptionTypeId();
            Integer rhsOptionTypeId;
            rhsOptionTypeId = that.getOptionTypeId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "optionTypeId", lhsOptionTypeId), LocatorUtils.property(thatLocator, "optionTypeId", rhsOptionTypeId), lhsOptionTypeId, rhsOptionTypeId)) {
                return false;
            }
        }
        {
            ContractCustomization lhsContractCustomization;
            lhsContractCustomization = this.getContractCustomization();
            ContractCustomization rhsContractCustomization;
            rhsContractCustomization = that.getContractCustomization();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractCustomization", lhsContractCustomization), LocatorUtils.property(thatLocator, "contractCustomization", rhsContractCustomization), lhsContractCustomization, rhsContractCustomization)) {
                return false;
            }
        }
        {
            String lhsProductCatalogSystemId;
            lhsProductCatalogSystemId = this.getProductCatalogSystemId();
            String rhsProductCatalogSystemId;
            rhsProductCatalogSystemId = that.getProductCatalogSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCatalogSystemId", lhsProductCatalogSystemId), LocatorUtils.property(thatLocator, "productCatalogSystemId", rhsProductCatalogSystemId), lhsProductCatalogSystemId, rhsProductCatalogSystemId)) {
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
