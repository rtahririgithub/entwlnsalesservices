/**
 * 
 */
package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * @author x145592
 *
 */
public class MultilingualTextVO implements Serializable {
	  
	private static final long serialVersionUID = 1L;

	private String localeTxt = null;
	private String valueTxt = null;

	public String getLocaleTxt() {
		return localeTxt;
	}

	public void setLocaleTxt(String localeTxt) {
		this.localeTxt = localeTxt;
	}

	public String getValueTxt() {
		return valueTxt;
	}

	public void setValueTxt(String valueTxt) {
		this.valueTxt = valueTxt;
	}

}
