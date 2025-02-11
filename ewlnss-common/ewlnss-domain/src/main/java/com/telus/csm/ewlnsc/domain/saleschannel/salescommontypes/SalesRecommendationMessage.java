
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Description;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v8.Name;
import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;


/**
 * Sales recommendation message
 * 
 * <p>Java class for SalesRecommendationMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesRecommendationMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="messageTypeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recommendationTitleNameList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Name" maxOccurs="10" minOccurs="0"/>
 *         &lt;element name="recommendationDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v8}Description" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesRecommendationMessage", propOrder = {
    "messageTypeCode",
    "recommendationTitleNameList",
    "recommendationDescriptionList"
})
public class SalesRecommendationMessage
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String messageTypeCode;
    protected List<Name> recommendationTitleNameList;
    protected List<Description> recommendationDescriptionList;

    /**
     * Gets the value of the messageTypeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageTypeCode() {
        return messageTypeCode;
    }

    /**
     * Sets the value of the messageTypeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageTypeCode(String value) {
        this.messageTypeCode = value;
    }

    /**
     * Gets the value of the recommendationTitleNameList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recommendationTitleNameList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecommendationTitleNameList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Name }
     * 
     * 
     */
    public List<Name> getRecommendationTitleNameList() {
        if (recommendationTitleNameList == null) {
            recommendationTitleNameList = new ArrayList<Name>();
        }
        return this.recommendationTitleNameList;
    }

    /**
     * Gets the value of the recommendationDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the recommendationDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRecommendationDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getRecommendationDescriptionList() {
        if (recommendationDescriptionList == null) {
            recommendationDescriptionList = new ArrayList<Description>();
        }
        return this.recommendationDescriptionList;
    }

    /**
     * Sets the value of the recommendationTitleNameList property.
     * 
     * @param recommendationTitleNameList
     *     allowed object is
     *     {@link Name }
     *     
     */
    public void setRecommendationTitleNameList(List<Name> recommendationTitleNameList) {
        this.recommendationTitleNameList = recommendationTitleNameList;
    }

    /**
     * Sets the value of the recommendationDescriptionList property.
     * 
     * @param recommendationDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setRecommendationDescriptionList(List<Description> recommendationDescriptionList) {
        this.recommendationDescriptionList = recommendationDescriptionList;
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
            String theMessageTypeCode;
            theMessageTypeCode = this.getMessageTypeCode();
            strategy.appendField(locator, this, "messageTypeCode", buffer, theMessageTypeCode);
        }
        {
            List<Name> theRecommendationTitleNameList;
            theRecommendationTitleNameList = (((this.recommendationTitleNameList!= null)&&(!this.recommendationTitleNameList.isEmpty()))?this.getRecommendationTitleNameList():null);
            strategy.appendField(locator, this, "recommendationTitleNameList", buffer, theRecommendationTitleNameList);
        }
        {
            List<Description> theRecommendationDescriptionList;
            theRecommendationDescriptionList = (((this.recommendationDescriptionList!= null)&&(!this.recommendationDescriptionList.isEmpty()))?this.getRecommendationDescriptionList():null);
            strategy.appendField(locator, this, "recommendationDescriptionList", buffer, theRecommendationDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesRecommendationMessage)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesRecommendationMessage that = ((SalesRecommendationMessage) object);
        {
            String lhsMessageTypeCode;
            lhsMessageTypeCode = this.getMessageTypeCode();
            String rhsMessageTypeCode;
            rhsMessageTypeCode = that.getMessageTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "messageTypeCode", lhsMessageTypeCode), LocatorUtils.property(thatLocator, "messageTypeCode", rhsMessageTypeCode), lhsMessageTypeCode, rhsMessageTypeCode)) {
                return false;
            }
        }
        {
            List<Name> lhsRecommendationTitleNameList;
            lhsRecommendationTitleNameList = (((this.recommendationTitleNameList!= null)&&(!this.recommendationTitleNameList.isEmpty()))?this.getRecommendationTitleNameList():null);
            List<Name> rhsRecommendationTitleNameList;
            rhsRecommendationTitleNameList = (((that.recommendationTitleNameList!= null)&&(!that.recommendationTitleNameList.isEmpty()))?that.getRecommendationTitleNameList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationTitleNameList", lhsRecommendationTitleNameList), LocatorUtils.property(thatLocator, "recommendationTitleNameList", rhsRecommendationTitleNameList), lhsRecommendationTitleNameList, rhsRecommendationTitleNameList)) {
                return false;
            }
        }
        {
            List<Description> lhsRecommendationDescriptionList;
            lhsRecommendationDescriptionList = (((this.recommendationDescriptionList!= null)&&(!this.recommendationDescriptionList.isEmpty()))?this.getRecommendationDescriptionList():null);
            List<Description> rhsRecommendationDescriptionList;
            rhsRecommendationDescriptionList = (((that.recommendationDescriptionList!= null)&&(!that.recommendationDescriptionList.isEmpty()))?that.getRecommendationDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "recommendationDescriptionList", lhsRecommendationDescriptionList), LocatorUtils.property(thatLocator, "recommendationDescriptionList", rhsRecommendationDescriptionList), lhsRecommendationDescriptionList, rhsRecommendationDescriptionList)) {
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
