
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
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
 * <p>Java class for AccessoryPrintToReceipt complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccessoryPrintToReceipt">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="printDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccessoryPrintToReceipt", propOrder = {
    "printDescriptionList"
})
public class AccessoryPrintToReceipt
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected List<Description> printDescriptionList;

    /**
     * Gets the value of the printDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the printDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPrintDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getPrintDescriptionList() {
        if (printDescriptionList == null) {
            printDescriptionList = new ArrayList<Description>();
        }
        return this.printDescriptionList;
    }

    /**
     * Sets the value of the printDescriptionList property.
     * 
     * @param printDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setPrintDescriptionList(List<Description> printDescriptionList) {
        this.printDescriptionList = printDescriptionList;
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
            List<Description> thePrintDescriptionList;
            thePrintDescriptionList = (((this.printDescriptionList!= null)&&(!this.printDescriptionList.isEmpty()))?this.getPrintDescriptionList():null);
            strategy.appendField(locator, this, "printDescriptionList", buffer, thePrintDescriptionList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof AccessoryPrintToReceipt)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final AccessoryPrintToReceipt that = ((AccessoryPrintToReceipt) object);
        {
            List<Description> lhsPrintDescriptionList;
            lhsPrintDescriptionList = (((this.printDescriptionList!= null)&&(!this.printDescriptionList.isEmpty()))?this.getPrintDescriptionList():null);
            List<Description> rhsPrintDescriptionList;
            rhsPrintDescriptionList = (((that.printDescriptionList!= null)&&(!that.printDescriptionList.isEmpty()))?that.getPrintDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "printDescriptionList", lhsPrintDescriptionList), LocatorUtils.property(thatLocator, "printDescriptionList", rhsPrintDescriptionList), lhsPrintDescriptionList, rhsPrintDescriptionList)) {
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
