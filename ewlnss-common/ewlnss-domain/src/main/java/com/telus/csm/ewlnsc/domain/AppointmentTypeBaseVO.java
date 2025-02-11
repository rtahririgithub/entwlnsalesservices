package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;


public class AppointmentTypeBaseVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Date appointmentDate;
	
	private AppointmentTimeSlotVO appointmentTimeSlot;

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public AppointmentTimeSlotVO getAppointmentTimeSlot() {
		return appointmentTimeSlot;
	}

	public void setAppointmentTimeSlot(AppointmentTimeSlotVO appointmentTimeSlot) {
		this.appointmentTimeSlot = appointmentTimeSlot;
	}

}
