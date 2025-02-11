package com.telus.csm.ewlnsc.adapter.domain;

import com.telus.csm.ewlnss.adapter.domain.AdapterRequestBase;
import com.telus.tmi.xmlschema.srv.cmo.informationmgmt.consumerdiaryservicerequestresponse_v1.CustomerEvent;

public class CreateCustomerEventAdapterRequest extends AdapterRequestBase{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomerEvent customerEvent;
	private String externalTypeId;
	
	public CustomerEvent getCustomerEvent() {
		return customerEvent;
	}
	
	public void setCustomerEvent(CustomerEvent customerEvent) {
		this.customerEvent = customerEvent;
	}
	
	public String getExternalTypeId() {
		return externalTypeId;
	}
	
	public void setExternalTypeId(String externalTypeId) {
		this.externalTypeId = externalTypeId;
	}
	
	
}
