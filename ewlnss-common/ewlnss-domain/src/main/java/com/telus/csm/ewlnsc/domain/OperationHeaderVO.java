package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class OperationHeaderVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String salesTransactionId;
	private String sourceSystemId = null;
	private String userId = null;

	public String getSalesTransactionId() {
		return salesTransactionId;
	}

	public void setSalesTransactionId(String salesTransactionId) {
		this.salesTransactionId = salesTransactionId;
	}

	public String getSourceSystemId() {
		return sourceSystemId;
	}

	public void setSourceSystemId(String sourceSystemId) {
		this.sourceSystemId = sourceSystemId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
}
