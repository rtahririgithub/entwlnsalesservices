package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;

public class CreateCustomerEventAdapterResponse extends AdapterResponseBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private long customerEventId;

	public long getCustomerEventId() {
		return customerEventId;
	}

	public void setCustomerEventId(long customerEventId) {
		this.customerEventId = customerEventId;
	}
	
	
}
