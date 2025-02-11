
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

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
 * <p>Java class for SalesProductPlanIdentifier complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SalesProductPlanIdentifier">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productComponentList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}ProductComponentIdentifier" maxOccurs="10" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SalesProductPlanIdentifier", propOrder = {
    "productComponentList"
})
public class SalesProductPlanIdentifier
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected List<ProductComponentIdentifier> productComponentList;

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
     * {@link ProductComponentIdentifier }
     * 
     * 
     */
    public List<ProductComponentIdentifier> getProductComponentList() {
        if (productComponentList == null) {
            productComponentList = new ArrayList<ProductComponentIdentifier>();
        }
        return this.productComponentList;
    }

    /**
     * Sets the value of the productComponentList property.
     * 
     * @param productComponentList
     *     allowed object is
     *     {@link ProductComponentIdentifier }
     *     
     */
    public void setProductComponentList(List<ProductComponentIdentifier> productComponentList) {
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
            List<ProductComponentIdentifier> theProductComponentList;
            theProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            strategy.appendField(locator, this, "productComponentList", buffer, theProductComponentList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof SalesProductPlanIdentifier)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final SalesProductPlanIdentifier that = ((SalesProductPlanIdentifier) object);
        {
            List<ProductComponentIdentifier> lhsProductComponentList;
            lhsProductComponentList = (((this.productComponentList!= null)&&(!this.productComponentList.isEmpty()))?this.getProductComponentList():null);
            List<ProductComponentIdentifier> rhsProductComponentList;
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
