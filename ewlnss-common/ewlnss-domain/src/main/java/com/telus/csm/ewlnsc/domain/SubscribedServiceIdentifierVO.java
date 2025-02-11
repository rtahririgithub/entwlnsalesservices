package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class SubscribedServiceIdentifierVO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String serviceId;
	private String serviceReferenceId;
	

	public String getServiceId() {
		return serviceId;
	}
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceReferenceId() {
		return serviceReferenceId;
	}
	public void setServiceReferenceId(String serviceReferenceId) {
		this.serviceReferenceId = serviceReferenceId;
	}

}
