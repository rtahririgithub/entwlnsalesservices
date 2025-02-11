package com.telus.csm.ess.common.domain;

import java.io.Serializable;

public class ESDGCartItemVO implements Serializable {
	
	/**
	 * 
	 */
	
	private String jsonCartItem = null;
	
	private static final long serialVersionUID = 1L;
	
	public String getJsonCartItem() {
		return jsonCartItem;
	}

	public void setJsonCartItem(String jsonSalesItem) {
		this.jsonCartItem = jsonSalesItem;
	}

	

}
