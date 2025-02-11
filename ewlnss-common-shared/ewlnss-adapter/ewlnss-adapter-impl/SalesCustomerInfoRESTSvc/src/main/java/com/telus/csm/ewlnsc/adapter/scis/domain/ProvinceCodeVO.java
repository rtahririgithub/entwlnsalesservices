
package com.telus.csm.ewlnsc.adapter.scis.domain;

import java.io.Serializable;


public enum ProvinceCodeVO  implements Serializable{

    AB,
    BC,
    MB,
    NB,
    NF,
    NT,
    NS,
    ON,
    PE,
    PQ,
    SK,
    YT,
    NU;

    public String value() {
        return name();
    }

    public static ProvinceCodeVO fromValue(String v) {
        return valueOf(v);
    }

}
