
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.math.BigInteger;
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
 * <p>Java class for CrossProductDependency complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CrossProductDependency">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="productCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="contractTermList" type="{http://www.w3.org/2001/XMLSchema}integer" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CrossProductDependency", propOrder = {
    "productCode",
    "contractTermList"
})
public class CrossProductDependency
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected String productCode;
    @XmlElement(required = true)
    protected List<BigInteger> contractTermList;

    /**
     * Gets the value of the productCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductCode() {
        return productCode;
    }

    /**
     * Sets the value of the productCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductCode(String value) {
        this.productCode = value;
    }

    /**
     * Gets the value of the contractTermList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the contractTermList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getContractTermList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BigInteger }
     * 
     * 
     */
    public List<BigInteger> getContractTermList() {
        if (contractTermList == null) {
            contractTermList = new ArrayList<BigInteger>();
        }
        return this.contractTermList;
    }

    /**
     * Sets the value of the contractTermList property.
     * 
     * @param contractTermList
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setContractTermList(List<BigInteger> contractTermList) {
        this.contractTermList = contractTermList;
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
            String theProductCode;
            theProductCode = this.getProductCode();
            strategy.appendField(locator, this, "productCode", buffer, theProductCode);
        }
        {
            List<BigInteger> theContractTermList;
            theContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            strategy.appendField(locator, this, "contractTermList", buffer, theContractTermList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CrossProductDependency)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CrossProductDependency that = ((CrossProductDependency) object);
        {
            String lhsProductCode;
            lhsProductCode = this.getProductCode();
            String rhsProductCode;
            rhsProductCode = that.getProductCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "productCode", lhsProductCode), LocatorUtils.property(thatLocator, "productCode", rhsProductCode), lhsProductCode, rhsProductCode)) {
                return false;
            }
        }
        {
            List<BigInteger> lhsContractTermList;
            lhsContractTermList = (((this.contractTermList!= null)&&(!this.contractTermList.isEmpty()))?this.getContractTermList():null);
            List<BigInteger> rhsContractTermList;
            rhsContractTermList = (((that.contractTermList!= null)&&(!that.contractTermList.isEmpty()))?that.getContractTermList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "contractTermList", lhsContractTermList), LocatorUtils.property(thatLocator, "contractTermList", rhsContractTermList), lhsContractTermList, rhsContractTermList)) {
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
