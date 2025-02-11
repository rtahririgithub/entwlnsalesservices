package com.telus.csm.ewlnsc.domain;

/**
 * @author Jose.Mena
 *
 */
public enum ValueTypeVO {

	NUMERIC("numeric"),
	   
    STRING("string"),
    
    BOOLEAN("boolean");
    private final String value;

    ValueTypeVO(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static ValueTypeVO fromValue(String v) {
        for (ValueTypeVO c: ValueTypeVO.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }
}
