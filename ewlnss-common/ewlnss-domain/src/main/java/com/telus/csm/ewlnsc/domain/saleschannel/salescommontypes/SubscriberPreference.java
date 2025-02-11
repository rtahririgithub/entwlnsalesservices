
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import java.io.Serializable;
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
 * <p>Java class for SubscriberPreference complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubscriberPreference">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="unblockDataPrivilegeInd" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubscriberPreference", propOrder = {
    "unblockDataPrivilegeInd"
})
public class SubscriberPreference
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected boolean unblockDataPrivilegeInd;

    /**
     * Gets the value of the unblockDataPrivilegeInd property.
     * 
     */
    public boolean isUnblockDataPrivilegeInd() {
        return unblockDataPrivilegeInd;
    }

    /**
     * Sets the value of the unblockDataPrivilegeInd property.
     * 
     */
    public void setUnblockDataPrivilegeInd(boolean value) {
        this.unblockDataPrivilegeInd = value;
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
            boolean theUnblockDataPrivilegeInd;
            theUnblockDataPrivilegeInd = (true?this.isUnblockDataPrivilegeInd():false);
            strategy.appendField(locator, this, "unblockDataPrivilegeInd", buffer, theUnblockDataPrivilegeInd);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SubscriberPreference)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SubscriberPreference that = ((SubscriberPreference) object);
        {
            boolean lhsUnblockDataPrivilegeInd;
            lhsUnblockDataPrivilegeInd = (true?this.isUnblockDataPrivilegeInd():false);
            boolean rhsUnblockDataPrivilegeInd;
            rhsUnblockDataPrivilegeInd = (true?that.isUnblockDataPrivilegeInd():false);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "unblockDataPrivilegeInd", lhsUnblockDataPrivilegeInd), LocatorUtils.property(thatLocator, "unblockDataPrivilegeInd", rhsUnblockDataPrivilegeInd), lhsUnblockDataPrivilegeInd, rhsUnblockDataPrivilegeInd)) {
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
