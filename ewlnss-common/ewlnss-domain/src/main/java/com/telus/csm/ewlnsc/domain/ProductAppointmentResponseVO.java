package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class ProductAppointmentResponseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<String> productServiceTypeList;
	
	private AppointmentTypeVO appointmentInfo;

	public List<String> getProductServiceTypeList() {
		return productServiceTypeList;
	}

	public void setProductServiceTypeList(List<String> productServiceTypeList) {
		this.productServiceTypeList = productServiceTypeList;
	}

	public AppointmentTypeVO getAppointmentInfo() {
		return appointmentInfo;
	}

	public void setAppointmentInfo(AppointmentTypeVO appointmentInfo) {
		this.appointmentInfo = appointmentInfo;
	}
	

}
