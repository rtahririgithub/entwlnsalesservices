
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
 * Indicates whether this is a recommended feature based on real time assessment result from PEIA and PUSGS database via the Infor Interaction Advisor (IA) owned by ACE
 * 
 * <p>Java class for FeatureRecommendation complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FeatureRecommendation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actionName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rankNum" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FeatureRecommendation", propOrder = {
    "actionName",
    "rankNum"
})
public class FeatureRecommendation
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String actionName;
    protected long rankNum;

    /**
     * Gets the value of the actionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the value of the actionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionName(String value) {
        this.actionName = value;
    }

    /**
     * Gets the value of the rankNum property.
     * 
     */
    public long getRankNum() {
        return rankNum;
    }

    /**
     * Sets the value of the rankNum property.
     * 
     */
    public void setRankNum(long value) {
        this.rankNum = value;
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
            String theActionName;
            theActionName = this.getActionName();
            strategy.appendField(locator, this, "actionName", buffer, theActionName);
        }
        {
            long theRankNum;
            theRankNum = (true?this.getRankNum(): 0L);
            strategy.appendField(locator, this, "rankNum", buffer, theRankNum);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof FeatureRecommendation)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final FeatureRecommendation that = ((FeatureRecommendation) object);
        {
            String lhsActionName;
            lhsActionName = this.getActionName();
            String rhsActionName;
            rhsActionName = that.getActionName();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "actionName", lhsActionName), LocatorUtils.property(thatLocator, "actionName", rhsActionName), lhsActionName, rhsActionName)) {
                return false;
            }
        }
        {
            long lhsRankNum;
            lhsRankNum = (true?this.getRankNum(): 0L);
            long rhsRankNum;
            rhsRankNum = (true?that.getRankNum(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "rankNum", lhsRankNum), LocatorUtils.property(thatLocator, "rankNum", rhsRankNum), lhsRankNum, rhsRankNum)) {
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
