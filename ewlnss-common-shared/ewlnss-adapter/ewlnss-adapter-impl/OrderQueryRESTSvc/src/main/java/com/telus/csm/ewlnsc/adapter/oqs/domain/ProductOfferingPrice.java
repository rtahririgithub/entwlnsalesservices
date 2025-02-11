package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 
 * @author Jose.Mena
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductOfferingPrice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String amount;
	private String priceType;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
}
