
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for ProgramPromotion complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ProgramPromotion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="promotionId" type="{http://www.w3.org/2001/XMLSchema}integer"/>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="challengeQuestionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="mandatoryResponseInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="responseTypeId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="responseFormatTypeId" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="responseMaxLengthCnt" type="{http://www.w3.org/2001/XMLSchema}integer" minOccurs="0"/>
 *         &lt;element name="challengeQuestionInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProgramPromotion", propOrder = {
    "promotionCode",
    "promotionId",
    "programId",
    "challengeQuestionList",
    "mandatoryResponseInd",
    "responseTypeId",
    "responseFormatTypeId",
    "responseMaxLengthCnt",
    "challengeQuestionInd"
})
public class ProgramPromotion
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String promotionCode;
    @XmlElement(required = true)
    protected BigInteger promotionId;
    protected long programId;
    protected List<Description> challengeQuestionList;
    protected boolean mandatoryResponseInd;
    protected BigInteger responseTypeId;
    protected BigInteger responseFormatTypeId;
    protected BigInteger responseMaxLengthCnt;
    protected boolean challengeQuestionInd;

    /**
     * Gets the value of the promotionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Sets the value of the promotionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
    }

    /**
     * Gets the value of the promotionId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getPromotionId() {
        return promotionId;
    }

    /**
     * Sets the value of the promotionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setPromotionId(BigInteger value) {
        this.promotionId = value;
    }

    /**
     * Gets the value of the programId property.
     * 
     */
    public long getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     */
    public void setProgramId(long value) {
        this.programId = value;
    }

    /**
     * Gets the value of the challengeQuestionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the challengeQuestionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChallengeQuestionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getChallengeQuestionList() {
        if (challengeQuestionList == null) {
            challengeQuestionList = new ArrayList<Description>();
        }
        return this.challengeQuestionList;
    }

    /**
     * Gets the value of the mandatoryResponseInd property.
     * 
     */
    public boolean isMandatoryResponseInd() {
        return mandatoryResponseInd;
    }

    /**
     * Sets the value of the mandatoryResponseInd property.
     * 
     */
    public void setMandatoryResponseInd(boolean value) {
        this.mandatoryResponseInd = value;
    }

    /**
     * Gets the value of the responseTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResponseTypeId() {
        return responseTypeId;
    }

    /**
     * Sets the value of the responseTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResponseTypeId(BigInteger value) {
        this.responseTypeId = value;
    }

    /**
     * Gets the value of the responseFormatTypeId property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResponseFormatTypeId() {
        return responseFormatTypeId;
    }

    /**
     * Sets the value of the responseFormatTypeId property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResponseFormatTypeId(BigInteger value) {
        this.responseFormatTypeId = value;
    }

    /**
     * Gets the value of the responseMaxLengthCnt property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResponseMaxLengthCnt() {
        return responseMaxLengthCnt;
    }

    /**
     * Sets the value of the responseMaxLengthCnt property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResponseMaxLengthCnt(BigInteger value) {
        this.responseMaxLengthCnt = value;
    }

    /**
     * Gets the value of the challengeQuestionInd property.
     * 
     */
    public boolean isChallengeQuestionInd() {
        return challengeQuestionInd;
    }

    /**
     * Sets the value of the challengeQuestionInd property.
     * 
     */
    public void setChallengeQuestionInd(boolean value) {
        this.challengeQuestionInd = value;
    }

    /**
     * Sets the value of the challengeQuestionList property.
     * 
     * @param challengeQuestionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setChallengeQuestionList(List<Description> challengeQuestionList) {
        this.challengeQuestionList = challengeQuestionList;
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
            String thePromotionCode;
            thePromotionCode = this.getPromotionCode();
            strategy.appendField(locator, this, "promotionCode", buffer, thePromotionCode);
        }
        {
            BigInteger thePromotionId;
            thePromotionId = this.getPromotionId();
            strategy.appendField(locator, this, "promotionId", buffer, thePromotionId);
        }
        {
            long theProgramId;
            theProgramId = (true?this.getProgramId(): 0L);
            strategy.appendField(locator, this, "programId", buffer, theProgramId);
        }
        {
            List<Description> theChallengeQuestionList;
            theChallengeQuestionList = (((this.challengeQuestionList!= null)&&(!this.challengeQuestionList.isEmpty()))?this.getChallengeQuestionList():null);
            strategy.appendField(locator, this, "challengeQuestionList", buffer, theChallengeQuestionList);
        }
        {
            boolean theMandatoryResponseInd;
            theMandatoryResponseInd = (true?this.isMandatoryResponseInd():false);
            strategy.appendField(locator, this, "mandatoryResponseInd", buffer, theMandatoryResponseInd);
        }
        {
            BigInteger theResponseTypeId;
            theResponseTypeId = this.getResponseTypeId();
            strategy.appendField(locator, this, "responseTypeId", buffer, theResponseTypeId);
        }
        {
            BigInteger theResponseFormatTypeId;
            theResponseFormatTypeId = this.getResponseFormatTypeId();
            strategy.appendField(locator, this, "responseFormatTypeId", buffer, theResponseFormatTypeId);
        }
        {
            BigInteger theResponseMaxLengthCnt;
            theResponseMaxLengthCnt = this.getResponseMaxLengthCnt();
            strategy.appendField(locator, this, "responseMaxLengthCnt", buffer, theResponseMaxLengthCnt);
        }
        {
            boolean theChallengeQuestionInd;
            theChallengeQuestionInd = (true?this.isChallengeQuestionInd():false);
            strategy.appendField(locator, this, "challengeQuestionInd", buffer, theChallengeQuestionInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof ProgramPromotion)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final ProgramPromotion that = ((ProgramPromotion) object);
        {
            String lhsPromotionCode;
            lhsPromotionCode = this.getPromotionCode();
            String rhsPromotionCode;
            rhsPromotionCode = that.getPromotionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionCode", lhsPromotionCode), LocatorUtils.property(thatLocator, "promotionCode", rhsPromotionCode), lhsPromotionCode, rhsPromotionCode)) {
                return false;
            }
        }
        {
            BigInteger lhsPromotionId;
            lhsPromotionId = this.getPromotionId();
            BigInteger rhsPromotionId;
            rhsPromotionId = that.getPromotionId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionId", lhsPromotionId), LocatorUtils.property(thatLocator, "promotionId", rhsPromotionId), lhsPromotionId, rhsPromotionId)) {
                return false;
            }
        }
        {
            long lhsProgramId;
            lhsProgramId = (true?this.getProgramId(): 0L);
            long rhsProgramId;
            rhsProgramId = (true?that.getProgramId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programId", lhsProgramId), LocatorUtils.property(thatLocator, "programId", rhsProgramId), lhsProgramId, rhsProgramId)) {
                return false;
            }
        }
        {
            List<Description> lhsChallengeQuestionList;
            lhsChallengeQuestionList = (((this.challengeQuestionList!= null)&&(!this.challengeQuestionList.isEmpty()))?this.getChallengeQuestionList():null);
            List<Description> rhsChallengeQuestionList;
            rhsChallengeQuestionList = (((that.challengeQuestionList!= null)&&(!that.challengeQuestionList.isEmpty()))?that.getChallengeQuestionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "challengeQuestionList", lhsChallengeQuestionList), LocatorUtils.property(thatLocator, "challengeQuestionList", rhsChallengeQuestionList), lhsChallengeQuestionList, rhsChallengeQuestionList)) {
                return false;
            }
        }
        {
            boolean lhsMandatoryResponseInd;
            lhsMandatoryResponseInd = (true?this.isMandatoryResponseInd():false);
            boolean rhsMandatoryResponseInd;
            rhsMandatoryResponseInd = (true?that.isMandatoryResponseInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "mandatoryResponseInd", lhsMandatoryResponseInd), LocatorUtils.property(thatLocator, "mandatoryResponseInd", rhsMandatoryResponseInd), lhsMandatoryResponseInd, rhsMandatoryResponseInd)) {
                return false;
            }
        }
        {
            BigInteger lhsResponseTypeId;
            lhsResponseTypeId = this.getResponseTypeId();
            BigInteger rhsResponseTypeId;
            rhsResponseTypeId = that.getResponseTypeId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "responseTypeId", lhsResponseTypeId), LocatorUtils.property(thatLocator, "responseTypeId", rhsResponseTypeId), lhsResponseTypeId, rhsResponseTypeId)) {
                return false;
            }
        }
        {
            BigInteger lhsResponseFormatTypeId;
            lhsResponseFormatTypeId = this.getResponseFormatTypeId();
            BigInteger rhsResponseFormatTypeId;
            rhsResponseFormatTypeId = that.getResponseFormatTypeId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "responseFormatTypeId", lhsResponseFormatTypeId), LocatorUtils.property(thatLocator, "responseFormatTypeId", rhsResponseFormatTypeId), lhsResponseFormatTypeId, rhsResponseFormatTypeId)) {
                return false;
            }
        }
        {
            BigInteger lhsResponseMaxLengthCnt;
            lhsResponseMaxLengthCnt = this.getResponseMaxLengthCnt();
            BigInteger rhsResponseMaxLengthCnt;
            rhsResponseMaxLengthCnt = that.getResponseMaxLengthCnt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "responseMaxLengthCnt", lhsResponseMaxLengthCnt), LocatorUtils.property(thatLocator, "responseMaxLengthCnt", rhsResponseMaxLengthCnt), lhsResponseMaxLengthCnt, rhsResponseMaxLengthCnt)) {
                return false;
            }
        }
        {
            boolean lhsChallengeQuestionInd;
            lhsChallengeQuestionInd = (true?this.isChallengeQuestionInd():false);
            boolean rhsChallengeQuestionInd;
            rhsChallengeQuestionInd = (true?that.isChallengeQuestionInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "challengeQuestionInd", lhsChallengeQuestionInd), LocatorUtils.property(thatLocator, "challengeQuestionInd", rhsChallengeQuestionInd), lhsChallengeQuestionInd, rhsChallengeQuestionInd)) {
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
