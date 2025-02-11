package com.telus.csm.esdg.domain;

import java.io.Serializable;

public class EsdgOrderFulfillmentStatusHistoryDO implements Serializable {

	private static final long serialVersionUID = 4956028515626433806L;

	protected String fulfillmentTypeCd;
	protected long fulfillmentStatusTimeInMills;
	protected String fulfillmentTransactionJsonData;
	protected boolean isSuccessful;
	
	public String getFulfillmentTypeCd() {
		return fulfillmentTypeCd;
	}
	public void setFulfillmentTypeCd(String fulfillmentTypeCd) {
		this.fulfillmentTypeCd = fulfillmentTypeCd;
	}
	public long getFulfillmentStatusTimeInMills() {
		return fulfillmentStatusTimeInMills;
	}
	public void setFulfillmentStatusTimeInMills(long fulfillmentStatusTimeInMills) {
		this.fulfillmentStatusTimeInMills = fulfillmentStatusTimeInMills;
	}
	public String getFulfillmentTransactionJsonData() {
		return fulfillmentTransactionJsonData;
	}
	public void setFulfillmentTransactionJsonData(String fulfillmentTransactionJsonData) {
		this.fulfillmentTransactionJsonData = fulfillmentTransactionJsonData;
	}
	public boolean isSuccessful() {
		return isSuccessful;
	}
	public void setSuccessful(boolean isSuccessful) {
		this.isSuccessful = isSuccessful;
	}
}
