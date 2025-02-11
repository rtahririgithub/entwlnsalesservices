package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class CustomerVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String customerId = null;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

}
