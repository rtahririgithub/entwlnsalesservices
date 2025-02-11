
package com.telus.csm.ewlnsc.domain.product.productoffering.offerinformationtypes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.jvnet.jaxb2_commons.lang.Equals;
import org.jvnet.jaxb2_commons.lang.EqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBEqualsStrategy;
import org.jvnet.jaxb2_commons.lang.JAXBToStringStrategy;
import org.jvnet.jaxb2_commons.lang.ToString;
import org.jvnet.jaxb2_commons.lang.ToStringStrategy;
import org.jvnet.jaxb2_commons.locator.ObjectLocator;
import org.jvnet.jaxb2_commons.locator.util.LocatorUtils;

import com.telus.framework.xml.bind.DateTimeAdapter;
import com.telus.tmi.xmlschema.xsd.enterprise.basetypes.enterprisecommontypes_v9.Description;


/**
 * <p>Java class for Program complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Program">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="programId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="programCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="programDescriptionList" type="{http://xmlschema.tmi.telus.com/xsd/Enterprise/BaseTypes/EnterpriseCommonTypes_v9}Description" maxOccurs="2" minOccurs="0"/>
 *         &lt;element name="perspectiveDate" type="{http://xmlschema.tmi.telus.com/xsd/Product/ProductOffering/OfferInformationTypes_v3}perspectiveDate"/>
 *         &lt;element name="effectiveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="expiryDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="brandId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="promotionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="typeCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="sourceSystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Program", propOrder = {
    "programId",
    "programCode",
    "programDescriptionList",
    "perspectiveDate",
    "effectiveDate",
    "expiryDate",
    "brandId",
    "promotionCode",
    "typeCode",
    "sourceSystemId"
})
@XmlSeeAlso({
    OfferHeader.class
})
public class Program
    implements Serializable, Equals, ToString
{

    private final static long serialVersionUID = 2L;
    protected long programId;
    @XmlElement(required = true)
    protected String programCode;
    protected List<Description> programDescriptionList;
    @XmlElement(required = true, type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    protected Date perspectiveDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date effectiveDate;
    @XmlElement(type = String.class)
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @XmlSchemaType(name = "dateTime")
    protected Date expiryDate;
    protected long brandId;
    protected String promotionCode;
    @XmlElement(required = true)
    protected String typeCode;
    protected String sourceSystemId;

    /**
     * Gets the value of the programId property.
     * 
     */
    public long getProgramId() {
        return programId;
    }

    /**
     * Sets the value of the programId property.
     * 
     */
    public void setProgramId(long value) {
        this.programId = value;
    }

    /**
     * Gets the value of the programCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProgramCode() {
        return programCode;
    }

    /**
     * Sets the value of the programCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProgramCode(String value) {
        this.programCode = value;
    }

    /**
     * Gets the value of the programDescriptionList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the programDescriptionList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProgramDescriptionList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Description }
     * 
     * 
     */
    public List<Description> getProgramDescriptionList() {
        if (programDescriptionList == null) {
            programDescriptionList = new ArrayList<Description>();
        }
        return this.programDescriptionList;
    }

    /**
     * Gets the value of the perspectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getPerspectiveDate() {
        return perspectiveDate;
    }

    /**
     * Sets the value of the perspectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPerspectiveDate(Date value) {
        this.perspectiveDate = value;
    }

    /**
     * Gets the value of the effectiveDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    /**
     * Sets the value of the effectiveDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEffectiveDate(Date value) {
        this.effectiveDate = value;
    }

    /**
     * Gets the value of the expiryDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public Date getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets the value of the expiryDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExpiryDate(Date value) {
        this.expiryDate = value;
    }

    /**
     * Gets the value of the brandId property.
     * 
     */
    public long getBrandId() {
        return brandId;
    }

    /**
     * Sets the value of the brandId property.
     * 
     */
    public void setBrandId(long value) {
        this.brandId = value;
    }

    /**
     * Gets the value of the promotionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPromotionCode() {
        return promotionCode;
    }

    /**
     * Sets the value of the promotionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPromotionCode(String value) {
        this.promotionCode = value;
    }

    /**
     * Gets the value of the typeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeCode() {
        return typeCode;
    }

    /**
     * Sets the value of the typeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeCode(String value) {
        this.typeCode = value;
    }

    /**
     * Gets the value of the sourceSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceSystemId() {
        return sourceSystemId;
    }

    /**
     * Sets the value of the sourceSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceSystemId(String value) {
        this.sourceSystemId = value;
    }

    /**
     * Sets the value of the programDescriptionList property.
     * 
     * @param programDescriptionList
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setProgramDescriptionList(List<Description> programDescriptionList) {
        this.programDescriptionList = programDescriptionList;
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
            long theProgramId;
            theProgramId = (true?this.getProgramId(): 0L);
            strategy.appendField(locator, this, "programId", buffer, theProgramId);
        }
        {
            String theProgramCode;
            theProgramCode = this.getProgramCode();
            strategy.appendField(locator, this, "programCode", buffer, theProgramCode);
        }
        {
            List<Description> theProgramDescriptionList;
            theProgramDescriptionList = (((this.programDescriptionList!= null)&&(!this.programDescriptionList.isEmpty()))?this.getProgramDescriptionList():null);
            strategy.appendField(locator, this, "programDescriptionList", buffer, theProgramDescriptionList);
        }
        {
            Date thePerspectiveDate;
            thePerspectiveDate = this.getPerspectiveDate();
            strategy.appendField(locator, this, "perspectiveDate", buffer, thePerspectiveDate);
        }
        {
            Date theEffectiveDate;
            theEffectiveDate = this.getEffectiveDate();
            strategy.appendField(locator, this, "effectiveDate", buffer, theEffectiveDate);
        }
        {
            Date theExpiryDate;
            theExpiryDate = this.getExpiryDate();
            strategy.appendField(locator, this, "expiryDate", buffer, theExpiryDate);
        }
        {
            long theBrandId;
            theBrandId = (true?this.getBrandId(): 0L);
            strategy.appendField(locator, this, "brandId", buffer, theBrandId);
        }
        {
            String thePromotionCode;
            thePromotionCode = this.getPromotionCode();
            strategy.appendField(locator, this, "promotionCode", buffer, thePromotionCode);
        }
        {
            String theTypeCode;
            theTypeCode = this.getTypeCode();
            strategy.appendField(locator, this, "typeCode", buffer, theTypeCode);
        }
        {
            String theSourceSystemId;
            theSourceSystemId = this.getSourceSystemId();
            strategy.appendField(locator, this, "sourceSystemId", buffer, theSourceSystemId);
        }
        return buffer;
    }

    public boolean equals(ObjectLocator thisLocator, ObjectLocator thatLocator, Object object, EqualsStrategy strategy) {
        if (!(object instanceof Program)) {
            return false;
        }
        if (this == object) {
            return true;
        }
        final Program that = ((Program) object);
        {
            long lhsProgramId;
            lhsProgramId = (true?this.getProgramId(): 0L);
            long rhsProgramId;
            rhsProgramId = (true?that.getProgramId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programId", lhsProgramId), LocatorUtils.property(thatLocator, "programId", rhsProgramId), lhsProgramId, rhsProgramId)) {
                return false;
            }
        }
        {
            String lhsProgramCode;
            lhsProgramCode = this.getProgramCode();
            String rhsProgramCode;
            rhsProgramCode = that.getProgramCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programCode", lhsProgramCode), LocatorUtils.property(thatLocator, "programCode", rhsProgramCode), lhsProgramCode, rhsProgramCode)) {
                return false;
            }
        }
        {
            List<Description> lhsProgramDescriptionList;
            lhsProgramDescriptionList = (((this.programDescriptionList!= null)&&(!this.programDescriptionList.isEmpty()))?this.getProgramDescriptionList():null);
            List<Description> rhsProgramDescriptionList;
            rhsProgramDescriptionList = (((that.programDescriptionList!= null)&&(!that.programDescriptionList.isEmpty()))?that.getProgramDescriptionList():null);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "programDescriptionList", lhsProgramDescriptionList), LocatorUtils.property(thatLocator, "programDescriptionList", rhsProgramDescriptionList), lhsProgramDescriptionList, rhsProgramDescriptionList)) {
                return false;
            }
        }
        {
            Date lhsPerspectiveDate;
            lhsPerspectiveDate = this.getPerspectiveDate();
            Date rhsPerspectiveDate;
            rhsPerspectiveDate = that.getPerspectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "perspectiveDate", lhsPerspectiveDate), LocatorUtils.property(thatLocator, "perspectiveDate", rhsPerspectiveDate), lhsPerspectiveDate, rhsPerspectiveDate)) {
                return false;
            }
        }
        {
            Date lhsEffectiveDate;
            lhsEffectiveDate = this.getEffectiveDate();
            Date rhsEffectiveDate;
            rhsEffectiveDate = that.getEffectiveDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "effectiveDate", lhsEffectiveDate), LocatorUtils.property(thatLocator, "effectiveDate", rhsEffectiveDate), lhsEffectiveDate, rhsEffectiveDate)) {
                return false;
            }
        }
        {
            Date lhsExpiryDate;
            lhsExpiryDate = this.getExpiryDate();
            Date rhsExpiryDate;
            rhsExpiryDate = that.getExpiryDate();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "expiryDate", lhsExpiryDate), LocatorUtils.property(thatLocator, "expiryDate", rhsExpiryDate), lhsExpiryDate, rhsExpiryDate)) {
                return false;
            }
        }
        {
            long lhsBrandId;
            lhsBrandId = (true?this.getBrandId(): 0L);
            long rhsBrandId;
            rhsBrandId = (true?that.getBrandId(): 0L);
            if (!strategy.equals(LocatorUtils.property(thisLocator, "brandId", lhsBrandId), LocatorUtils.property(thatLocator, "brandId", rhsBrandId), lhsBrandId, rhsBrandId)) {
                return false;
            }
        }
        {
            String lhsPromotionCode;
            lhsPromotionCode = this.getPromotionCode();
            String rhsPromotionCode;
            rhsPromotionCode = that.getPromotionCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "promotionCode", lhsPromotionCode), LocatorUtils.property(thatLocator, "promotionCode", rhsPromotionCode), lhsPromotionCode, rhsPromotionCode)) {
                return false;
            }
        }
        {
            String lhsTypeCode;
            lhsTypeCode = this.getTypeCode();
            String rhsTypeCode;
            rhsTypeCode = that.getTypeCode();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "typeCode", lhsTypeCode), LocatorUtils.property(thatLocator, "typeCode", rhsTypeCode), lhsTypeCode, rhsTypeCode)) {
                return false;
            }
        }
        {
            String lhsSourceSystemId;
            lhsSourceSystemId = this.getSourceSystemId();
            String rhsSourceSystemId;
            rhsSourceSystemId = that.getSourceSystemId();
            if (!strategy.equals(LocatorUtils.property(thisLocator, "sourceSystemId", lhsSourceSystemId), LocatorUtils.property(thatLocator, "sourceSystemId", rhsSourceSystemId), lhsSourceSystemId, rhsSourceSystemId)) {
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
