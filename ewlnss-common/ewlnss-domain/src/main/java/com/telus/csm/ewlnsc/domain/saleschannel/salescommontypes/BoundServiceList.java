
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
 * <p>Java class for BoundServiceList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BoundServiceList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="boundService" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}Service" maxOccurs="20"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BoundServiceList", propOrder = {
    "boundService"
})
public class BoundServiceList
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<Service> boundService;

    /**
     * Gets the value of the boundService property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the boundService property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBoundService().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Service }
     * 
     * 
     */
    public List<Service> getBoundService() {
        if (boundService == null) {
            boundService = new ArrayList<Service>();
        }
        return this.boundService;
    }

    /**
     * Sets the value of the boundService property.
     * 
     * @param boundService
     *     allowed object is
     *     {@link Service }
     *     
     */
    public void setBoundService(List<Service> boundService) {
        this.boundService = boundService;
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
            List<Service> theBoundService;
            theBoundService = (((this.boundService!= null)&&(!this.boundService.isEmpty()))?this.getBoundService():null);
            strategy.appendField(locator, this, "boundService", buffer, theBoundService);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof BoundServiceList)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final BoundServiceList that = ((BoundServiceList) object);
        {
            List<Service> lhsBoundService;
            lhsBoundService = (((this.boundService!= null)&&(!this.boundService.isEmpty()))?this.getBoundService():null);
            List<Service> rhsBoundService;
            rhsBoundService = (((that.boundService!= null)&&(!that.boundService.isEmpty()))?that.getBoundService():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "boundService", lhsBoundService), LocatorUtils.property(thatLocator, "boundService", rhsBoundService), lhsBoundService, rhsBoundService)) {
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
