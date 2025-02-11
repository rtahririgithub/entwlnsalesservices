
package com.telus.csm.ewlnsc.domain.saleschannel.salescommontypes;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SalesProductLine.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="SalesProductLine">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="Home Phone"/>
 *     &lt;enumeration value="TELUS TV"/>
 *     &lt;enumeration value="High Speed Internet"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "SalesProductLine")
@XmlEnum
public enum SalesProductLine {

    @XmlEnumValue("Home Phone")
    HOME_PHONE("Home Phone"),
    @XmlEnumValue("TELUS TV")
    TELUS_TV("TELUS TV"),
    @XmlEnumValue("High Speed Internet")
    HIGH_SPEED_INTERNET("High Speed Internet");
    private final String value;

    SalesProductLine(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SalesProductLine fromValue(String v) {
        for (SalesProductLine c: SalesProductLine.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
