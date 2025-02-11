
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
 * To define CBUCID or RCID
 * 
 * <p>Java class for CorporateHierarchy complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CorporateHierarchy">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="corporateEntity" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateEntitySummary"/>
 *         &lt;element name="subsidiaryEntityList" type="{http://xmlschema.tmi.telus.com/xsd/MarketingSales/SalesChannel/SalesCommonTypes_v5}CorporateEntitySummary" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CorporateHierarchy", propOrder = {
    "corporateEntity",
    "subsidiaryEntityList"
})
public class CorporateHierarchy
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    @XmlElement(required = true)
    protected CorporateEntitySummary corporateEntity;
    protected List<CorporateEntitySummary> subsidiaryEntityList;

    /**
     * Gets the value of the corporateEntity property.
     * 
     * @return
     *     possible object is
     *     {@link CorporateEntitySummary }
     *     
     */
    public CorporateEntitySummary getCorporateEntity() {
        return corporateEntity;
    }

    /**
     * Sets the value of the corporateEntity property.
     * 
     * @param value
     *     allowed object is
     *     {@link CorporateEntitySummary }
     *     
     */
    public void setCorporateEntity(CorporateEntitySummary value) {
        this.corporateEntity = value;
    }

    /**
     * Gets the value of the subsidiaryEntityList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the subsidiaryEntityList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSubsidiaryEntityList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CorporateEntitySummary }
     * 
     * 
     */
    public List<CorporateEntitySummary> getSubsidiaryEntityList() {
        if (subsidiaryEntityList == null) {
            subsidiaryEntityList = new ArrayList<CorporateEntitySummary>();
        }
        return this.subsidiaryEntityList;
    }

    /**
     * Sets the value of the subsidiaryEntityList property.
     * 
     * @param subsidiaryEntityList
     *     allowed object is
     *     {@link CorporateEntitySummary }
     *     
     */
    public void setSubsidiaryEntityList(List<CorporateEntitySummary> subsidiaryEntityList) {
        this.subsidiaryEntityList = subsidiaryEntityList;
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
            CorporateEntitySummary theCorporateEntity;
            theCorporateEntity = this.getCorporateEntity();
            strategy.appendField(locator, this, "corporateEntity", buffer, theCorporateEntity);
        }
        {
            List<CorporateEntitySummary> theSubsidiaryEntityList;
            theSubsidiaryEntityList = (((this.subsidiaryEntityList!= null)&&(!this.subsidiaryEntityList.isEmpty()))?this.getSubsidiaryEntityList():null);
            strategy.appendField(locator, this, "subsidiaryEntityList", buffer, theSubsidiaryEntityList);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof CorporateHierarchy)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final CorporateHierarchy that = ((CorporateHierarchy) object);
        {
            CorporateEntitySummary lhsCorporateEntity;
            lhsCorporateEntity = this.getCorporateEntity();
            CorporateEntitySummary rhsCorporateEntity;
            rhsCorporateEntity = that.getCorporateEntity();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "corporateEntity", lhsCorporateEntity), LocatorUtils.property(thatLocator, "corporateEntity", rhsCorporateEntity), lhsCorporateEntity, rhsCorporateEntity)) {
                return false;
            }
        }
        {
            List<CorporateEntitySummary> lhsSubsidiaryEntityList;
            lhsSubsidiaryEntityList = (((this.subsidiaryEntityList!= null)&&(!this.subsidiaryEntityList.isEmpty()))?this.getSubsidiaryEntityList():null);
            List<CorporateEntitySummary> rhsSubsidiaryEntityList;
            rhsSubsidiaryEntityList = (((that.subsidiaryEntityList!= null)&&(!that.subsidiaryEntityList.isEmpty()))?that.getSubsidiaryEntityList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "subsidiaryEntityList", lhsSubsidiaryEntityList), LocatorUtils.property(thatLocator, "subsidiaryEntityList", rhsSubsidiaryEntityList), lhsSubsidiaryEntityList, rhsSubsidiaryEntityList)) {
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
