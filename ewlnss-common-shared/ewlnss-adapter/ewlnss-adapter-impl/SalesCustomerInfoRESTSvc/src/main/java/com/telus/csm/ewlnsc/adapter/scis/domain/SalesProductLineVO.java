
package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;


public enum SalesProductLineVO  implements Serializable{

    HOME_PHONE("Home Phone"),
    TELUS_TV("TELUS TV"),
    HIGH_SPEED_INTERNET("High Speed Internet");
    private final String value;

    SalesProductLineVO(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SalesProductLineVO fromValue(String v) {
        for (SalesProductLineVO c: SalesProductLineVO.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
