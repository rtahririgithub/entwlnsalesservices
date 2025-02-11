
package com.telus.csm.ewlnsc.domain.schemasclone;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 * <p>Java class for ServiceAddress complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ServiceAddress">
 *   &lt;complexContent>
 *     &lt;extension base="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Address">
 *       &lt;sequence>
 *         &lt;element name="municipalityClliCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="prewireDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="enterPhoneNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="jurisdictionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseRateAreaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="baseRateAreaMileageTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="transmissionZoneCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nnxCodeSpecialTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rateCenterCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="commentLineList" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="switchNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="switchTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="switchId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="terminalNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="npaCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lowestNxxCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serviceCount" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="serviceAddressIndividualLineServiceCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="portabilityCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="localRoutingNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ratingNpaNxxCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rateCentreRemarksTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="blockAddressInd" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="legalLandDescriptionTxt" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="streetNumberSuffixCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropFacilityCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropLengthNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dropExceptionCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentTransportTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futureTransportTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futurePlantReadyDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="futureTransportRemarkTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gponBuildTypeCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="provisioningSystemCd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="locationPdsId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="gisId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceAddress", propOrder = {
    "municipalityClliCd",
    "prewireDate",
    "enterPhoneNum",
    "jurisdictionCd",
    "baseRateAreaCd",
    "baseRateAreaMileageTxt",
    "transmissionZoneCd",
    "nnxCodeSpecialTxt",
    "rateCenterCd",
    "commentLineList",
    "switchNumber",
    "switchTypeCd",
    "switchId",
    "terminalCd",
    "terminalNumber",
    "npaCd",
    "lowestNxxCd",
    "serviceCount",
    "serviceAddressIndividualLineServiceCode",
    "portabilityCd",
    "localRoutingNumber",
    "ratingNpaNxxCd",
    "rateCentreRemarksTxt",
    "blockAddressInd",
    "legalLandDescriptionTxt",
    "streetNumberSuffixCd",
    "dropFacilityCd",
    "dropTypeCd",
    "dropLengthNum",
    "dropExceptionCd",
    "currentTransportTypeCd",
    "futureTransportTypeCd",
    "futurePlantReadyDate",
    "futureTransportRemarkTypeCd",
    "gponBuildTypeCd",
    "provisioningSystemCd",
    "locationPdsId",
    "gisId"
})
public class ServiceAddress
    extends Address
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected String municipalityClliCd;
    protected String prewireDate;
    protected String enterPhoneNum;
    protected String jurisdictionCd;
    protected String baseRateAreaCd;
    protected String baseRateAreaMileageTxt;
    protected String transmissionZoneCd;
    protected String nnxCodeSpecialTxt;
    protected String rateCenterCd;
    protected List<String> commentLineList;
    protected String switchNumber;
    protected String switchTypeCd;
    protected String switchId;
    protected String terminalCd;
    protected String terminalNumber;
    protected String npaCd;
    protected String lowestNxxCd;
    protected Long serviceCount;
    protected String serviceAddressIndividualLineServiceCode;
    protected String portabilityCd;
    protected String localRoutingNumber;
    protected String ratingNpaNxxCd;
    protected String rateCentreRemarksTxt;
    protected Boolean blockAddressInd;
    protected String legalLandDescriptionTxt;
    protected String streetNumberSuffixCd;
    protected String dropFacilityCd;
    protected String dropTypeCd;
    protected String dropLengthNum;
    protected String dropExceptionCd;
    protected String currentTransportTypeCd;
    protected String futureTransportTypeCd;
    protected String futurePlantReadyDate;
    protected String futureTransportRemarkTypeCd;
    protected String gponBuildTypeCd;
    protected String provisioningSystemCd;
    protected String locationPdsId;
    protected String gisId;

    /**
     * Gets the value of the municipalityClliCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMunicipalityClliCd() {
        return municipalityClliCd;
    }

    /**
     * Sets the value of the municipalityClliCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMunicipalityClliCd(String value) {
        this.municipalityClliCd = value;
    }

    /**
     * Gets the value of the prewireDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrewireDate() {
        return prewireDate;
    }

    /**
     * Sets the value of the prewireDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrewireDate(String value) {
        this.prewireDate = value;
    }

    /**
     * Gets the value of the enterPhoneNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEnterPhoneNum() {
        return enterPhoneNum;
    }

    /**
     * Sets the value of the enterPhoneNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEnterPhoneNum(String value) {
        this.enterPhoneNum = value;
    }

    /**
     * Gets the value of the jurisdictionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJurisdictionCd() {
        return jurisdictionCd;
    }

    /**
     * Sets the value of the jurisdictionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJurisdictionCd(String value) {
        this.jurisdictionCd = value;
    }

    /**
     * Gets the value of the baseRateAreaCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseRateAreaCd() {
        return baseRateAreaCd;
    }

    /**
     * Sets the value of the baseRateAreaCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseRateAreaCd(String value) {
        this.baseRateAreaCd = value;
    }

    /**
     * Gets the value of the baseRateAreaMileageTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBaseRateAreaMileageTxt() {
        return baseRateAreaMileageTxt;
    }

    /**
     * Sets the value of the baseRateAreaMileageTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBaseRateAreaMileageTxt(String value) {
        this.baseRateAreaMileageTxt = value;
    }

    /**
     * Gets the value of the transmissionZoneCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransmissionZoneCd() {
        return transmissionZoneCd;
    }

    /**
     * Sets the value of the transmissionZoneCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransmissionZoneCd(String value) {
        this.transmissionZoneCd = value;
    }

    /**
     * Gets the value of the nnxCodeSpecialTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNnxCodeSpecialTxt() {
        return nnxCodeSpecialTxt;
    }

    /**
     * Sets the value of the nnxCodeSpecialTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNnxCodeSpecialTxt(String value) {
        this.nnxCodeSpecialTxt = value;
    }

    /**
     * Gets the value of the rateCenterCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCenterCd() {
        return rateCenterCd;
    }

    /**
     * Sets the value of the rateCenterCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCenterCd(String value) {
        this.rateCenterCd = value;
    }

    /**
     * Gets the value of the commentLineList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the commentLineList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCommentLineList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getCommentLineList() {
        if (commentLineList == null) {
            commentLineList = new ArrayList<String>();
        }
        return this.commentLineList;
    }

    /**
     * Gets the value of the switchNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwitchNumber() {
        return switchNumber;
    }

    /**
     * Sets the value of the switchNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwitchNumber(String value) {
        this.switchNumber = value;
    }

    /**
     * Gets the value of the switchTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwitchTypeCd() {
        return switchTypeCd;
    }

    /**
     * Sets the value of the switchTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwitchTypeCd(String value) {
        this.switchTypeCd = value;
    }

    /**
     * Gets the value of the switchId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwitchId() {
        return switchId;
    }

    /**
     * Sets the value of the switchId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwitchId(String value) {
        this.switchId = value;
    }

    /**
     * Gets the value of the terminalCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalCd() {
        return terminalCd;
    }

    /**
     * Sets the value of the terminalCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalCd(String value) {
        this.terminalCd = value;
    }

    /**
     * Gets the value of the terminalNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTerminalNumber() {
        return terminalNumber;
    }

    /**
     * Sets the value of the terminalNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTerminalNumber(String value) {
        this.terminalNumber = value;
    }

    /**
     * Gets the value of the npaCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNpaCd() {
        return npaCd;
    }

    /**
     * Sets the value of the npaCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNpaCd(String value) {
        this.npaCd = value;
    }

    /**
     * Gets the value of the lowestNxxCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLowestNxxCd() {
        return lowestNxxCd;
    }

    /**
     * Sets the value of the lowestNxxCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLowestNxxCd(String value) {
        this.lowestNxxCd = value;
    }

    /**
     * Gets the value of the serviceCount property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getServiceCount() {
        return serviceCount;
    }

    /**
     * Sets the value of the serviceCount property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setServiceCount(Long value) {
        this.serviceCount = value;
    }

    /**
     * Gets the value of the serviceAddressIndividualLineServiceCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getServiceAddressIndividualLineServiceCode() {
        return serviceAddressIndividualLineServiceCode;
    }

    /**
     * Sets the value of the serviceAddressIndividualLineServiceCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setServiceAddressIndividualLineServiceCode(String value) {
        this.serviceAddressIndividualLineServiceCode = value;
    }

    /**
     * Gets the value of the portabilityCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPortabilityCd() {
        return portabilityCd;
    }

    /**
     * Sets the value of the portabilityCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPortabilityCd(String value) {
        this.portabilityCd = value;
    }

    /**
     * Gets the value of the localRoutingNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalRoutingNumber() {
        return localRoutingNumber;
    }

    /**
     * Sets the value of the localRoutingNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalRoutingNumber(String value) {
        this.localRoutingNumber = value;
    }

    /**
     * Gets the value of the ratingNpaNxxCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRatingNpaNxxCd() {
        return ratingNpaNxxCd;
    }

    /**
     * Sets the value of the ratingNpaNxxCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRatingNpaNxxCd(String value) {
        this.ratingNpaNxxCd = value;
    }

    /**
     * Gets the value of the rateCentreRemarksTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateCentreRemarksTxt() {
        return rateCentreRemarksTxt;
    }

    /**
     * Sets the value of the rateCentreRemarksTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateCentreRemarksTxt(String value) {
        this.rateCentreRemarksTxt = value;
    }

    /**
     * Gets the value of the blockAddressInd property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isBlockAddressInd() {
        return blockAddressInd;
    }

    /**
     * Sets the value of the blockAddressInd property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setBlockAddressInd(Boolean value) {
        this.blockAddressInd = value;
    }

    /**
     * Gets the value of the legalLandDescriptionTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLegalLandDescriptionTxt() {
        return legalLandDescriptionTxt;
    }

    /**
     * Sets the value of the legalLandDescriptionTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLegalLandDescriptionTxt(String value) {
        this.legalLandDescriptionTxt = value;
    }

    /**
     * Gets the value of the streetNumberSuffixCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStreetNumberSuffixCd() {
        return streetNumberSuffixCd;
    }

    /**
     * Sets the value of the streetNumberSuffixCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStreetNumberSuffixCd(String value) {
        this.streetNumberSuffixCd = value;
    }

    /**
     * Gets the value of the dropFacilityCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropFacilityCd() {
        return dropFacilityCd;
    }

    /**
     * Sets the value of the dropFacilityCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropFacilityCd(String value) {
        this.dropFacilityCd = value;
    }

    /**
     * Gets the value of the dropTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropTypeCd() {
        return dropTypeCd;
    }

    /**
     * Sets the value of the dropTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropTypeCd(String value) {
        this.dropTypeCd = value;
    }

    /**
     * Gets the value of the dropLengthNum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropLengthNum() {
        return dropLengthNum;
    }

    /**
     * Sets the value of the dropLengthNum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropLengthNum(String value) {
        this.dropLengthNum = value;
    }

    /**
     * Gets the value of the dropExceptionCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDropExceptionCd() {
        return dropExceptionCd;
    }

    /**
     * Sets the value of the dropExceptionCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDropExceptionCd(String value) {
        this.dropExceptionCd = value;
    }

    /**
     * Gets the value of the currentTransportTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCurrentTransportTypeCd() {
        return currentTransportTypeCd;
    }

    /**
     * Sets the value of the currentTransportTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCurrentTransportTypeCd(String value) {
        this.currentTransportTypeCd = value;
    }

    /**
     * Gets the value of the futureTransportTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFutureTransportTypeCd() {
        return futureTransportTypeCd;
    }

    /**
     * Sets the value of the futureTransportTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFutureTransportTypeCd(String value) {
        this.futureTransportTypeCd = value;
    }

    /**
     * Gets the value of the futurePlantReadyDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFuturePlantReadyDate() {
        return futurePlantReadyDate;
    }

    /**
     * Sets the value of the futurePlantReadyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFuturePlantReadyDate(String value) {
        this.futurePlantReadyDate = value;
    }

    /**
     * Gets the value of the futureTransportRemarkTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFutureTransportRemarkTypeCd() {
        return futureTransportRemarkTypeCd;
    }

    /**
     * Sets the value of the futureTransportRemarkTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFutureTransportRemarkTypeCd(String value) {
        this.futureTransportRemarkTypeCd = value;
    }

    /**
     * Gets the value of the gponBuildTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGponBuildTypeCd() {
        return gponBuildTypeCd;
    }

    /**
     * Sets the value of the gponBuildTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGponBuildTypeCd(String value) {
        this.gponBuildTypeCd = value;
    }

    /**
     * Gets the value of the provisioningSystemCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvisioningSystemCd() {
        return provisioningSystemCd;
    }

    /**
     * Sets the value of the provisioningSystemCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvisioningSystemCd(String value) {
        this.provisioningSystemCd = value;
    }

    /**
     * Gets the value of the locationPdsId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocationPdsId() {
        return locationPdsId;
    }

    /**
     * Sets the value of the locationPdsId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocationPdsId(String value) {
        this.locationPdsId = value;
    }

    /**
     * Gets the value of the gisId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGisId() {
        return gisId;
    }

    /**
     * Sets the value of the gisId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGisId(String value) {
        this.gisId = value;
    }

    /**
     * Sets the value of the commentLineList property.
     * 
     * @param commentLineList
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommentLineList(List<String> commentLineList) {
        this.commentLineList = commentLineList;
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
            String theMunicipalityClliCd;
            theMunicipalityClliCd = this.getMunicipalityClliCd();
            strategy.appendField(locator, this, "municipalityClliCd", buffer, theMunicipalityClliCd);
        }
        {
            String thePrewireDate;
            thePrewireDate = this.getPrewireDate();
            strategy.appendField(locator, this, "prewireDate", buffer, thePrewireDate);
        }
        {
            String theEnterPhoneNum;
            theEnterPhoneNum = this.getEnterPhoneNum();
            strategy.appendField(locator, this, "enterPhoneNum", buffer, theEnterPhoneNum);
        }
        {
            String theJurisdictionCd;
            theJurisdictionCd = this.getJurisdictionCd();
            strategy.appendField(locator, this, "jurisdictionCd", buffer, theJurisdictionCd);
        }
        {
            String theBaseRateAreaCd;
            theBaseRateAreaCd = this.getBaseRateAreaCd();
            strategy.appendField(locator, this, "baseRateAreaCd", buffer, theBaseRateAreaCd);
        }
        {
            String theBaseRateAreaMileageTxt;
            theBaseRateAreaMileageTxt = this.getBaseRateAreaMileageTxt();
            strategy.appendField(locator, this, "baseRateAreaMileageTxt", buffer, theBaseRateAreaMileageTxt);
        }
        {
            String theTransmissionZoneCd;
            theTransmissionZoneCd = this.getTransmissionZoneCd();
            strategy.appendField(locator, this, "transmissionZoneCd", buffer, theTransmissionZoneCd);
        }
        {
            String theNnxCodeSpecialTxt;
            theNnxCodeSpecialTxt = this.getNnxCodeSpecialTxt();
            strategy.appendField(locator, this, "nnxCodeSpecialTxt", buffer, theNnxCodeSpecialTxt);
        }
        {
            String theRateCenterCd;
            theRateCenterCd = this.getRateCenterCd();
            strategy.appendField(locator, this, "rateCenterCd", buffer, theRateCenterCd);
        }
        {
            List<String> theCommentLineList;
            theCommentLineList = (((this.commentLineList!= null)&&(!this.commentLineList.isEmpty()))?this.getCommentLineList():null);
            strategy.appendField(locator, this, "commentLineList", buffer, theCommentLineList);
        }
        {
            String theSwitchNumber;
            theSwitchNumber = this.getSwitchNumber();
            strategy.appendField(locator, this, "switchNumber", buffer, theSwitchNumber);
        }
        {
            String theSwitchTypeCd;
            theSwitchTypeCd = this.getSwitchTypeCd();
            strategy.appendField(locator, this, "switchTypeCd", buffer, theSwitchTypeCd);
        }
        {
            String theSwitchId;
            theSwitchId = this.getSwitchId();
            strategy.appendField(locator, this, "switchId", buffer, theSwitchId);
        }
        {
            String theTerminalCd;
            theTerminalCd = this.getTerminalCd();
            strategy.appendField(locator, this, "terminalCd", buffer, theTerminalCd);
        }
        {
            String theTerminalNumber;
            theTerminalNumber = this.getTerminalNumber();
            strategy.appendField(locator, this, "terminalNumber", buffer, theTerminalNumber);
        }
        {
            String theNpaCd;
            theNpaCd = this.getNpaCd();
            strategy.appendField(locator, this, "npaCd", buffer, theNpaCd);
        }
        {
            String theLowestNxxCd;
            theLowestNxxCd = this.getLowestNxxCd();
            strategy.appendField(locator, this, "lowestNxxCd", buffer, theLowestNxxCd);
        }
        {
            Long theServiceCount;
            theServiceCount = this.getServiceCount();
            strategy.appendField(locator, this, "serviceCount", buffer, theServiceCount);
        }
        {
            String theServiceAddressIndividualLineServiceCode;
            theServiceAddressIndividualLineServiceCode = this.getServiceAddressIndividualLineServiceCode();
            strategy.appendField(locator, this, "serviceAddressIndividualLineServiceCode", buffer, theServiceAddressIndividualLineServiceCode);
        }
        {
            String thePortabilityCd;
            thePortabilityCd = this.getPortabilityCd();
            strategy.appendField(locator, this, "portabilityCd", buffer, thePortabilityCd);
        }
        {
            String theLocalRoutingNumber;
            theLocalRoutingNumber = this.getLocalRoutingNumber();
            strategy.appendField(locator, this, "localRoutingNumber", buffer, theLocalRoutingNumber);
        }
        {
            String theRatingNpaNxxCd;
            theRatingNpaNxxCd = this.getRatingNpaNxxCd();
            strategy.appendField(locator, this, "ratingNpaNxxCd", buffer, theRatingNpaNxxCd);
        }
        {
            String theRateCentreRemarksTxt;
            theRateCentreRemarksTxt = this.getRateCentreRemarksTxt();
            strategy.appendField(locator, this, "rateCentreRemarksTxt", buffer, theRateCentreRemarksTxt);
        }
        {
            Boolean theBlockAddressInd;
            theBlockAddressInd = this.isBlockAddressInd();
            strategy.appendField(locator, this, "blockAddressInd", buffer, theBlockAddressInd);
        }
        {
            String theLegalLandDescriptionTxt;
            theLegalLandDescriptionTxt = this.getLegalLandDescriptionTxt();
            strategy.appendField(locator, this, "legalLandDescriptionTxt", buffer, theLegalLandDescriptionTxt);
        }
        {
            String theStreetNumberSuffixCd;
            theStreetNumberSuffixCd = this.getStreetNumberSuffixCd();
            strategy.appendField(locator, this, "streetNumberSuffixCd", buffer, theStreetNumberSuffixCd);
        }
        {
            String theDropFacilityCd;
            theDropFacilityCd = this.getDropFacilityCd();
            strategy.appendField(locator, this, "dropFacilityCd", buffer, theDropFacilityCd);
        }
        {
            String theDropTypeCd;
            theDropTypeCd = this.getDropTypeCd();
            strategy.appendField(locator, this, "dropTypeCd", buffer, theDropTypeCd);
        }
        {
            String theDropLengthNum;
            theDropLengthNum = this.getDropLengthNum();
            strategy.appendField(locator, this, "dropLengthNum", buffer, theDropLengthNum);
        }
        {
            String theDropExceptionCd;
            theDropExceptionCd = this.getDropExceptionCd();
            strategy.appendField(locator, this, "dropExceptionCd", buffer, theDropExceptionCd);
        }
        {
            String theCurrentTransportTypeCd;
            theCurrentTransportTypeCd = this.getCurrentTransportTypeCd();
            strategy.appendField(locator, this, "currentTransportTypeCd", buffer, theCurrentTransportTypeCd);
        }
        {
            String theFutureTransportTypeCd;
            theFutureTransportTypeCd = this.getFutureTransportTypeCd();
            strategy.appendField(locator, this, "futureTransportTypeCd", buffer, theFutureTransportTypeCd);
        }
        {
            String theFuturePlantReadyDate;
            theFuturePlantReadyDate = this.getFuturePlantReadyDate();
            strategy.appendField(locator, this, "futurePlantReadyDate", buffer, theFuturePlantReadyDate);
        }
        {
            String theFutureTransportRemarkTypeCd;
            theFutureTransportRemarkTypeCd = this.getFutureTransportRemarkTypeCd();
            strategy.appendField(locator, this, "futureTransportRemarkTypeCd", buffer, theFutureTransportRemarkTypeCd);
        }
        {
            String theGponBuildTypeCd;
            theGponBuildTypeCd = this.getGponBuildTypeCd();
            strategy.appendField(locator, this, "gponBuildTypeCd", buffer, theGponBuildTypeCd);
        }
        {
            String theProvisioningSystemCd;
            theProvisioningSystemCd = this.getProvisioningSystemCd();
            strategy.appendField(locator, this, "provisioningSystemCd", buffer, theProvisioningSystemCd);
        }
        {
            String theLocationPdsId;
            theLocationPdsId = this.getLocationPdsId();
            strategy.appendField(locator, this, "locationPdsId", buffer, theLocationPdsId);
        }
        {
            String theGisId;
            theGisId = this.getGisId();
            strategy.appendField(locator, this, "gisId", buffer, theGisId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ServiceAddress)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        if (!super.equals(thisLocator, thatLocator, object, strategy)) {
            return false;
        }
        final ServiceAddress that = ((ServiceAddress) object);
        {
            String lhsMunicipalityClliCd;
            lhsMunicipalityClliCd = this.getMunicipalityClliCd();
            String rhsMunicipalityClliCd;
            rhsMunicipalityClliCd = that.getMunicipalityClliCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "municipalityClliCd", lhsMunicipalityClliCd), LocatorUtils.property(thatLocator, "municipalityClliCd", rhsMunicipalityClliCd), lhsMunicipalityClliCd, rhsMunicipalityClliCd)) {
                return false;
            }
        }
        {
            String lhsPrewireDate;
            lhsPrewireDate = this.getPrewireDate();
            String rhsPrewireDate;
            rhsPrewireDate = that.getPrewireDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "prewireDate", lhsPrewireDate), LocatorUtils.property(thatLocator, "prewireDate", rhsPrewireDate), lhsPrewireDate, rhsPrewireDate)) {
                return false;
            }
        }
        {
            String lhsEnterPhoneNum;
            lhsEnterPhoneNum = this.getEnterPhoneNum();
            String rhsEnterPhoneNum;
            rhsEnterPhoneNum = that.getEnterPhoneNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "enterPhoneNum", lhsEnterPhoneNum), LocatorUtils.property(thatLocator, "enterPhoneNum", rhsEnterPhoneNum), lhsEnterPhoneNum, rhsEnterPhoneNum)) {
                return false;
            }
        }
        {
            String lhsJurisdictionCd;
            lhsJurisdictionCd = this.getJurisdictionCd();
            String rhsJurisdictionCd;
            rhsJurisdictionCd = that.getJurisdictionCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "jurisdictionCd", lhsJurisdictionCd), LocatorUtils.property(thatLocator, "jurisdictionCd", rhsJurisdictionCd), lhsJurisdictionCd, rhsJurisdictionCd)) {
                return false;
            }
        }
        {
            String lhsBaseRateAreaCd;
            lhsBaseRateAreaCd = this.getBaseRateAreaCd();
            String rhsBaseRateAreaCd;
            rhsBaseRateAreaCd = that.getBaseRateAreaCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseRateAreaCd", lhsBaseRateAreaCd), LocatorUtils.property(thatLocator, "baseRateAreaCd", rhsBaseRateAreaCd), lhsBaseRateAreaCd, rhsBaseRateAreaCd)) {
                return false;
            }
        }
        {
            String lhsBaseRateAreaMileageTxt;
            lhsBaseRateAreaMileageTxt = this.getBaseRateAreaMileageTxt();
            String rhsBaseRateAreaMileageTxt;
            rhsBaseRateAreaMileageTxt = that.getBaseRateAreaMileageTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "baseRateAreaMileageTxt", lhsBaseRateAreaMileageTxt), LocatorUtils.property(thatLocator, "baseRateAreaMileageTxt", rhsBaseRateAreaMileageTxt), lhsBaseRateAreaMileageTxt, rhsBaseRateAreaMileageTxt)) {
                return false;
            }
        }
        {
            String lhsTransmissionZoneCd;
            lhsTransmissionZoneCd = this.getTransmissionZoneCd();
            String rhsTransmissionZoneCd;
            rhsTransmissionZoneCd = that.getTransmissionZoneCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "transmissionZoneCd", lhsTransmissionZoneCd), LocatorUtils.property(thatLocator, "transmissionZoneCd", rhsTransmissionZoneCd), lhsTransmissionZoneCd, rhsTransmissionZoneCd)) {
                return false;
            }
        }
        {
            String lhsNnxCodeSpecialTxt;
            lhsNnxCodeSpecialTxt = this.getNnxCodeSpecialTxt();
            String rhsNnxCodeSpecialTxt;
            rhsNnxCodeSpecialTxt = that.getNnxCodeSpecialTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nnxCodeSpecialTxt", lhsNnxCodeSpecialTxt), LocatorUtils.property(thatLocator, "nnxCodeSpecialTxt", rhsNnxCodeSpecialTxt), lhsNnxCodeSpecialTxt, rhsNnxCodeSpecialTxt)) {
                return false;
            }
        }
        {
            String lhsRateCenterCd;
            lhsRateCenterCd = this.getRateCenterCd();
            String rhsRateCenterCd;
            rhsRateCenterCd = that.getRateCenterCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rateCenterCd", lhsRateCenterCd), LocatorUtils.property(thatLocator, "rateCenterCd", rhsRateCenterCd), lhsRateCenterCd, rhsRateCenterCd)) {
                return false;
            }
        }
        {
            List<String> lhsCommentLineList;
            lhsCommentLineList = (((this.commentLineList!= null)&&(!this.commentLineList.isEmpty()))?this.getCommentLineList():null);
            List<String> rhsCommentLineList;
            rhsCommentLineList = (((that.commentLineList!= null)&&(!that.commentLineList.isEmpty()))?that.getCommentLineList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "commentLineList", lhsCommentLineList), LocatorUtils.property(thatLocator, "commentLineList", rhsCommentLineList), lhsCommentLineList, rhsCommentLineList)) {
                return false;
            }
        }
        {
            String lhsSwitchNumber;
            lhsSwitchNumber = this.getSwitchNumber();
            String rhsSwitchNumber;
            rhsSwitchNumber = that.getSwitchNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "switchNumber", lhsSwitchNumber), LocatorUtils.property(thatLocator, "switchNumber", rhsSwitchNumber), lhsSwitchNumber, rhsSwitchNumber)) {
                return false;
            }
        }
        {
            String lhsSwitchTypeCd;
            lhsSwitchTypeCd = this.getSwitchTypeCd();
            String rhsSwitchTypeCd;
            rhsSwitchTypeCd = that.getSwitchTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "switchTypeCd", lhsSwitchTypeCd), LocatorUtils.property(thatLocator, "switchTypeCd", rhsSwitchTypeCd), lhsSwitchTypeCd, rhsSwitchTypeCd)) {
                return false;
            }
        }
        {
            String lhsSwitchId;
            lhsSwitchId = this.getSwitchId();
            String rhsSwitchId;
            rhsSwitchId = that.getSwitchId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "switchId", lhsSwitchId), LocatorUtils.property(thatLocator, "switchId", rhsSwitchId), lhsSwitchId, rhsSwitchId)) {
                return false;
            }
        }
        {
            String lhsTerminalCd;
            lhsTerminalCd = this.getTerminalCd();
            String rhsTerminalCd;
            rhsTerminalCd = that.getTerminalCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "terminalCd", lhsTerminalCd), LocatorUtils.property(thatLocator, "terminalCd", rhsTerminalCd), lhsTerminalCd, rhsTerminalCd)) {
                return false;
            }
        }
        {
            String lhsTerminalNumber;
            lhsTerminalNumber = this.getTerminalNumber();
            String rhsTerminalNumber;
            rhsTerminalNumber = that.getTerminalNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "terminalNumber", lhsTerminalNumber), LocatorUtils.property(thatLocator, "terminalNumber", rhsTerminalNumber), lhsTerminalNumber, rhsTerminalNumber)) {
                return false;
            }
        }
        {
            String lhsNpaCd;
            lhsNpaCd = this.getNpaCd();
            String rhsNpaCd;
            rhsNpaCd = that.getNpaCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "npaCd", lhsNpaCd), LocatorUtils.property(thatLocator, "npaCd", rhsNpaCd), lhsNpaCd, rhsNpaCd)) {
                return false;
            }
        }
        {
            String lhsLowestNxxCd;
            lhsLowestNxxCd = this.getLowestNxxCd();
            String rhsLowestNxxCd;
            rhsLowestNxxCd = that.getLowestNxxCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "lowestNxxCd", lhsLowestNxxCd), LocatorUtils.property(thatLocator, "lowestNxxCd", rhsLowestNxxCd), lhsLowestNxxCd, rhsLowestNxxCd)) {
                return false;
            }
        }
        {
            Long lhsServiceCount;
            lhsServiceCount = this.getServiceCount();
            Long rhsServiceCount;
            rhsServiceCount = that.getServiceCount();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceCount", lhsServiceCount), LocatorUtils.property(thatLocator, "serviceCount", rhsServiceCount), lhsServiceCount, rhsServiceCount)) {
                return false;
            }
        }
        {
            String lhsServiceAddressIndividualLineServiceCode;
            lhsServiceAddressIndividualLineServiceCode = this.getServiceAddressIndividualLineServiceCode();
            String rhsServiceAddressIndividualLineServiceCode;
            rhsServiceAddressIndividualLineServiceCode = that.getServiceAddressIndividualLineServiceCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "serviceAddressIndividualLineServiceCode", lhsServiceAddressIndividualLineServiceCode), LocatorUtils.property(thatLocator, "serviceAddressIndividualLineServiceCode", rhsServiceAddressIndividualLineServiceCode), lhsServiceAddressIndividualLineServiceCode, rhsServiceAddressIndividualLineServiceCode)) {
                return false;
            }
        }
        {
            String lhsPortabilityCd;
            lhsPortabilityCd = this.getPortabilityCd();
            String rhsPortabilityCd;
            rhsPortabilityCd = that.getPortabilityCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "portabilityCd", lhsPortabilityCd), LocatorUtils.property(thatLocator, "portabilityCd", rhsPortabilityCd), lhsPortabilityCd, rhsPortabilityCd)) {
                return false;
            }
        }
        {
            String lhsLocalRoutingNumber;
            lhsLocalRoutingNumber = this.getLocalRoutingNumber();
            String rhsLocalRoutingNumber;
            rhsLocalRoutingNumber = that.getLocalRoutingNumber();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "localRoutingNumber", lhsLocalRoutingNumber), LocatorUtils.property(thatLocator, "localRoutingNumber", rhsLocalRoutingNumber), lhsLocalRoutingNumber, rhsLocalRoutingNumber)) {
                return false;
            }
        }
        {
            String lhsRatingNpaNxxCd;
            lhsRatingNpaNxxCd = this.getRatingNpaNxxCd();
            String rhsRatingNpaNxxCd;
            rhsRatingNpaNxxCd = that.getRatingNpaNxxCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "ratingNpaNxxCd", lhsRatingNpaNxxCd), LocatorUtils.property(thatLocator, "ratingNpaNxxCd", rhsRatingNpaNxxCd), lhsRatingNpaNxxCd, rhsRatingNpaNxxCd)) {
                return false;
            }
        }
        {
            String lhsRateCentreRemarksTxt;
            lhsRateCentreRemarksTxt = this.getRateCentreRemarksTxt();
            String rhsRateCentreRemarksTxt;
            rhsRateCentreRemarksTxt = that.getRateCentreRemarksTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rateCentreRemarksTxt", lhsRateCentreRemarksTxt), LocatorUtils.property(thatLocator, "rateCentreRemarksTxt", rhsRateCentreRemarksTxt), lhsRateCentreRemarksTxt, rhsRateCentreRemarksTxt)) {
                return false;
            }
        }
        {
            Boolean lhsBlockAddressInd;
            lhsBlockAddressInd = this.isBlockAddressInd();
            Boolean rhsBlockAddressInd;
            rhsBlockAddressInd = that.isBlockAddressInd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "blockAddressInd", lhsBlockAddressInd), LocatorUtils.property(thatLocator, "blockAddressInd", rhsBlockAddressInd), lhsBlockAddressInd, rhsBlockAddressInd)) {
                return false;
            }
        }
        {
            String lhsLegalLandDescriptionTxt;
            lhsLegalLandDescriptionTxt = this.getLegalLandDescriptionTxt();
            String rhsLegalLandDescriptionTxt;
            rhsLegalLandDescriptionTxt = that.getLegalLandDescriptionTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "legalLandDescriptionTxt", lhsLegalLandDescriptionTxt), LocatorUtils.property(thatLocator, "legalLandDescriptionTxt", rhsLegalLandDescriptionTxt), lhsLegalLandDescriptionTxt, rhsLegalLandDescriptionTxt)) {
                return false;
            }
        }
        {
            String lhsStreetNumberSuffixCd;
            lhsStreetNumberSuffixCd = this.getStreetNumberSuffixCd();
            String rhsStreetNumberSuffixCd;
            rhsStreetNumberSuffixCd = that.getStreetNumberSuffixCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "streetNumberSuffixCd", lhsStreetNumberSuffixCd), LocatorUtils.property(thatLocator, "streetNumberSuffixCd", rhsStreetNumberSuffixCd), lhsStreetNumberSuffixCd, rhsStreetNumberSuffixCd)) {
                return false;
            }
        }
        {
            String lhsDropFacilityCd;
            lhsDropFacilityCd = this.getDropFacilityCd();
            String rhsDropFacilityCd;
            rhsDropFacilityCd = that.getDropFacilityCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dropFacilityCd", lhsDropFacilityCd), LocatorUtils.property(thatLocator, "dropFacilityCd", rhsDropFacilityCd), lhsDropFacilityCd, rhsDropFacilityCd)) {
                return false;
            }
        }
        {
            String lhsDropTypeCd;
            lhsDropTypeCd = this.getDropTypeCd();
            String rhsDropTypeCd;
            rhsDropTypeCd = that.getDropTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dropTypeCd", lhsDropTypeCd), LocatorUtils.property(thatLocator, "dropTypeCd", rhsDropTypeCd), lhsDropTypeCd, rhsDropTypeCd)) {
                return false;
            }
        }
        {
            String lhsDropLengthNum;
            lhsDropLengthNum = this.getDropLengthNum();
            String rhsDropLengthNum;
            rhsDropLengthNum = that.getDropLengthNum();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dropLengthNum", lhsDropLengthNum), LocatorUtils.property(thatLocator, "dropLengthNum", rhsDropLengthNum), lhsDropLengthNum, rhsDropLengthNum)) {
                return false;
            }
        }
        {
            String lhsDropExceptionCd;
            lhsDropExceptionCd = this.getDropExceptionCd();
            String rhsDropExceptionCd;
            rhsDropExceptionCd = that.getDropExceptionCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "dropExceptionCd", lhsDropExceptionCd), LocatorUtils.property(thatLocator, "dropExceptionCd", rhsDropExceptionCd), lhsDropExceptionCd, rhsDropExceptionCd)) {
                return false;
            }
        }
        {
            String lhsCurrentTransportTypeCd;
            lhsCurrentTransportTypeCd = this.getCurrentTransportTypeCd();
            String rhsCurrentTransportTypeCd;
            rhsCurrentTransportTypeCd = that.getCurrentTransportTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "currentTransportTypeCd", lhsCurrentTransportTypeCd), LocatorUtils.property(thatLocator, "currentTransportTypeCd", rhsCurrentTransportTypeCd), lhsCurrentTransportTypeCd, rhsCurrentTransportTypeCd)) {
                return false;
            }
        }
        {
            String lhsFutureTransportTypeCd;
            lhsFutureTransportTypeCd = this.getFutureTransportTypeCd();
            String rhsFutureTransportTypeCd;
            rhsFutureTransportTypeCd = that.getFutureTransportTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "futureTransportTypeCd", lhsFutureTransportTypeCd), LocatorUtils.property(thatLocator, "futureTransportTypeCd", rhsFutureTransportTypeCd), lhsFutureTransportTypeCd, rhsFutureTransportTypeCd)) {
                return false;
            }
        }
        {
            String lhsFuturePlantReadyDate;
            lhsFuturePlantReadyDate = this.getFuturePlantReadyDate();
            String rhsFuturePlantReadyDate;
            rhsFuturePlantReadyDate = that.getFuturePlantReadyDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "futurePlantReadyDate", lhsFuturePlantReadyDate), LocatorUtils.property(thatLocator, "futurePlantReadyDate", rhsFuturePlantReadyDate), lhsFuturePlantReadyDate, rhsFuturePlantReadyDate)) {
                return false;
            }
        }
        {
            String lhsFutureTransportRemarkTypeCd;
            lhsFutureTransportRemarkTypeCd = this.getFutureTransportRemarkTypeCd();
            String rhsFutureTransportRemarkTypeCd;
            rhsFutureTransportRemarkTypeCd = that.getFutureTransportRemarkTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "futureTransportRemarkTypeCd", lhsFutureTransportRemarkTypeCd), LocatorUtils.property(thatLocator, "futureTransportRemarkTypeCd", rhsFutureTransportRemarkTypeCd), lhsFutureTransportRemarkTypeCd, rhsFutureTransportRemarkTypeCd)) {
                return false;
            }
        }
        {
            String lhsGponBuildTypeCd;
            lhsGponBuildTypeCd = this.getGponBuildTypeCd();
            String rhsGponBuildTypeCd;
            rhsGponBuildTypeCd = that.getGponBuildTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gponBuildTypeCd", lhsGponBuildTypeCd), LocatorUtils.property(thatLocator, "gponBuildTypeCd", rhsGponBuildTypeCd), lhsGponBuildTypeCd, rhsGponBuildTypeCd)) {
                return false;
            }
        }
        {
            String lhsProvisioningSystemCd;
            lhsProvisioningSystemCd = this.getProvisioningSystemCd();
            String rhsProvisioningSystemCd;
            rhsProvisioningSystemCd = that.getProvisioningSystemCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "provisioningSystemCd", lhsProvisioningSystemCd), LocatorUtils.property(thatLocator, "provisioningSystemCd", rhsProvisioningSystemCd), lhsProvisioningSystemCd, rhsProvisioningSystemCd)) {
                return false;
            }
        }
        {
            String lhsLocationPdsId;
            lhsLocationPdsId = this.getLocationPdsId();
            String rhsLocationPdsId;
            rhsLocationPdsId = that.getLocationPdsId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "locationPdsId", lhsLocationPdsId), LocatorUtils.property(thatLocator, "locationPdsId", rhsLocationPdsId), lhsLocationPdsId, rhsLocationPdsId)) {
                return false;
            }
        }
        {
            String lhsGisId;
            lhsGisId = this.getGisId();
            String rhsGisId;
            rhsGisId = that.getGisId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "gisId", lhsGisId), LocatorUtils.property(thatLocator, "gisId", rhsGisId), lhsGisId, rhsGisId)) {
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
