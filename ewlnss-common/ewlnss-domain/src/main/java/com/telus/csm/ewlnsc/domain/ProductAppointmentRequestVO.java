package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class ProductAppointmentRequestVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> productServiceTypeList;
	
	private AppointmentTypeBaseVO requestedAppointment;

	public List<String> getProductServiceTypeList() {
		return productServiceTypeList;
	}

	public void setProductServiceTypeList(List<String> productServiceTypeList) {
		this.productServiceTypeList = productServiceTypeList;
	}

	public AppointmentTypeBaseVO getRequestedAppointment() {
		return requestedAppointment;
	}

	public void setRequestedAppointment(AppointmentTypeBaseVO requestedAppointment) {
		this.requestedAppointment = requestedAppointment;
	}

}
