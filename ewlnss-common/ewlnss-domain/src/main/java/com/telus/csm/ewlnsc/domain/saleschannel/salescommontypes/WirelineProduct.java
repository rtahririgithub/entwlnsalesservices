
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
 * <p>Java class for WirelineProduct complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="WirelineProduct">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productTypeCd" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="productComponentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}WirelineProductComponent" maxOccurs="200" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "WirelineProduct", propOrder = {
    "productTypeCd",
    "productComponentList"
})
public class WirelineProduct
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productTypeCd;
    protected List<WirelineProductComponent> productComponentList;

    /**
     * Gets the value of the productTypeCd property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductTypeCd() {
        return productTypeCd;
    }

    /**
     * Sets the value of the productTypeCd property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductTypeCd(String value) {
        this.productTypeCd = value;
    }

    /**
     * Gets the value of the productComponentList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the productComponentList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductComponentList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link WirelineProductComponent }
     * 
     * 
     */
    public List<WirelineProductComponent> getProductComponentList() {
        if (productComponentList == null) {
            productComponentList = new ArrayList<WirelineProductComponent>();
        }
        return this.productComponentList;
    }

    /**
     * Sets the value of the productComponentList property.
     * 
     * @param productComponentList
     *     allowed object is
     *     {@link WirelineProductComponent }
     *     
     */
    public void setProductComponentList(List<WirelineProductComponent> productComponentList) {
        this.productComponentList = productComponentList;
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
            String theProductTypeCd;
            theProductTypeCd = this.getProductTypeCd();
            strategy.appendField(locator, this, "productTypeCd", buffer, theProductTypeCd);
        }
        {
            List<WirelineProductComponent> theProductComponentList;
            theProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            strategy.appendField(locator, this, "productComponentList", buffer, theProductComponentList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof WirelineProduct)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final WirelineProduct that = ((WirelineProduct) object);
        {
            String lhsProductTypeCd;
            lhsProductTypeCd = this.getProductTypeCd();
            String rhsProductTypeCd;
            rhsProductTypeCd = that.getProductTypeCd();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productTypeCd", lhsProductTypeCd), LocatorUtils.property(thatLocator, "productTypeCd", rhsProductTypeCd), lhsProductTypeCd, rhsProductTypeCd)) {
                return false;
            }
        }
        {
            List<WirelineProductComponent> lhsProductComponentList;
            lhsProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            List<WirelineProductComponent> rhsProductComponentList;
            rhsProductComponentList = (((that.productComponentList!= null)&&(!that.productComponentList.isEmpty()))?that.getProductComponentList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productComponentList", lhsProductComponentList), LocatorUtils.property(thatLocator, "productComponentList", rhsProductComponentList), lhsProductComponentList, rhsProductComponentList)) {
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
