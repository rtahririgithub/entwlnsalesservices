package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AvailableProductAppointmentVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<String> productServiceTypeCdList = null;
	private List<AvailableAppointmentVO> availableAppointments = null;

	public List<String> getProductServiceTypeCdList() {
		return productServiceTypeCdList;
	}

	public void setProductServiceTypeCdList(List<String> productServiceTypeCdList) {
		this.productServiceTypeCdList = productServiceTypeCdList;
	}

	public List<AvailableAppointmentVO> getAvailableAppointments() {
		if(availableAppointments==null){
			this.availableAppointments = new ArrayList<AvailableAppointmentVO>();
		}
		return this.availableAppointments;
	}

	public void setAvailableAppointments(List<AvailableAppointmentVO> availableAppointments) {
		this.availableAppointments = availableAppointments;
	}
	

}
