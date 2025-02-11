
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.MultilingualDescriptionList;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * <p>Java class for TelevisionQuestionairre complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TelevisionQuestionairre">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nameCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="questionTxt" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}MultilingualDescriptionList"/>
 *         &lt;element name="answerTxt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TelevisionQuestionairre", propOrder = {
    "nameCd",
    "questionTxt",
    "answerTxt"
})
public class TelevisionQuestionairre
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String nameCd;
    @XmlElement(required = true)
    protected MultilingualDescriptionList questionTxt;
    @XmlElement(required = true)
    protected String answerTxt;

    /**
     * Gets the value of the nameCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameCd() {
        return nameCd;
    }

    /**
     * Sets the value of the nameCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameCd(String value) {
        this.nameCd = value;
    }

    /**
     * Gets the value of the questionTxt property.
     * 
     * @return
     *     possible object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public MultilingualDescriptionList getQuestionTxt() {
        return questionTxt;
    }

    /**
     * Sets the value of the questionTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link MultilingualDescriptionList }
     *     
     */
    public void setQuestionTxt(MultilingualDescriptionList value) {
        this.questionTxt = value;
    }

    /**
     * Gets the value of the answerTxt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswerTxt() {
        return answerTxt;
    }

    /**
     * Sets the value of the answerTxt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswerTxt(String value) {
        this.answerTxt = value;
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
            String theNameCd;
            theNameCd = this.getNameCd();
            strategy.appendField(locator, this, "nameCd", buffer, theNameCd);
        }
        {
            MultilingualDescriptionList theQuestionTxt;
            theQuestionTxt = this.getQuestionTxt();
            strategy.appendField(locator, this, "questionTxt", buffer, theQuestionTxt);
        }
        {
            String theAnswerTxt;
            theAnswerTxt = this.getAnswerTxt();
            strategy.appendField(locator, this, "answerTxt", buffer, theAnswerTxt);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof TelevisionQuestionairre)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final TelevisionQuestionairre that = ((TelevisionQuestionairre) object);
        {
            String lhsNameCd;
            lhsNameCd = this.getNameCd();
            String rhsNameCd;
            rhsNameCd = that.getNameCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "nameCd", lhsNameCd), LocatorUtils.property(thatLocator, "nameCd", rhsNameCd), lhsNameCd, rhsNameCd)) {
                return false;
            }
        }
        {
            MultilingualDescriptionList lhsQuestionTxt;
            lhsQuestionTxt = this.getQuestionTxt();
            MultilingualDescriptionList rhsQuestionTxt;
            rhsQuestionTxt = that.getQuestionTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "questionTxt", lhsQuestionTxt), LocatorUtils.property(thatLocator, "questionTxt", rhsQuestionTxt), lhsQuestionTxt, rhsQuestionTxt)) {
                return false;
            }
        }
        {
            String lhsAnswerTxt;
            lhsAnswerTxt = this.getAnswerTxt();
            String rhsAnswerTxt;
            rhsAnswerTxt = that.getAnswerTxt();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "answerTxt", lhsAnswerTxt), LocatorUtils.property(thatLocator, "answerTxt", rhsAnswerTxt), lhsAnswerTxt, rhsAnswerTxt)) {
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
