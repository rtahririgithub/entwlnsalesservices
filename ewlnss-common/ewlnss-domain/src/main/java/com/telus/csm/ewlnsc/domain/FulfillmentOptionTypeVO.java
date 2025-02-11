package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class FulfillmentOptionTypeVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String fulfillmentOptionTypeTxt = null;

	public String getFulfillmentOptionTypeTxt() {
		return fulfillmentOptionTypeTxt;
	}

	public void setFulfillmentOptionTypeTxt(String fulfillmentOptionTypeTxt) {
		this.fulfillmentOptionTypeTxt = fulfillmentOptionTypeTxt;
	}
}
