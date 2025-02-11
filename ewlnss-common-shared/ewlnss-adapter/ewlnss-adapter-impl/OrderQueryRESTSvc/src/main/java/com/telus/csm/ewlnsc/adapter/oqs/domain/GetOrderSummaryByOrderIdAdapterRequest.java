package com.telus.csm.ewlnsc.adapter.oqs.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class GetOrderSummaryByOrderIdAdapterRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String orderId;
	private String txnId;
	private boolean priceOffering;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public boolean isPriceOffering() {
		return priceOffering;
	}

	public void setPriceOffering(boolean priceOffering) {
		this.priceOffering = priceOffering;
	}

	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	
	public String getKey() {
		return "getOrderSummaryByOrderId_salesTxnId_" + txnId + "_orderId_" + orderId + "_priceOffering_" + priceOffering;
	}
}