/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class MoneyVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private Float valueAmt = null;

	public Float getValueAmt() {
		return valueAmt;
	}

	public void setValueAmt(Float valueAmt) {
		this.valueAmt = valueAmt;
	}


}
