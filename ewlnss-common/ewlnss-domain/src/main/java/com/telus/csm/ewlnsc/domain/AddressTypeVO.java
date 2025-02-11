
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public enum AddressTypeVO implements Serializable{
  
	C,
    D,
    F;

    public String value() {
        return name();
    }

    public static AddressTypeVO fromValue(String v) {
        return valueOf(v);
    }

}
