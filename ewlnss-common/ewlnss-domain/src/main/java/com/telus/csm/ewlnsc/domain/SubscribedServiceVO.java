package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

/**
 * SubscribedServiceVO
 */

public class SubscribedServiceVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String serviceId = null;

	private String serviceReferenceId = null;

	/**
	 * @return the serviceId
	 */
	public String getServiceId() {
		return serviceId;
	}

	/**
	 * @param serviceId
	 *            the serviceId to set
	 */
	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	/**
	 * @return the serviceReferenceId
	 */
	public String getServiceReferenceId() {
		return serviceReferenceId;
	}

	/**
	 * @param serviceReferenceId
	 *            the serviceReferenceId to set
	 */
	public void setServiceReferenceId(String serviceReferenceId) {
		this.serviceReferenceId = serviceReferenceId;
	}

}
