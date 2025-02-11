package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


public class AvailableAppointmentVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private Date appointmentDate;
	private List<AppointmentTimeSlotVO> availableTimeSlots;
	private List<AvailableInstallCreditTypeVO> availableInstallCreditList;
	private String timeZoneCd = null;

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public List<AppointmentTimeSlotVO> getAvailableTimeSlots() {
		return availableTimeSlots;
	}

	public void setAvailableTimeSlots(List<AppointmentTimeSlotVO> availableTimeSlots) {
		this.availableTimeSlots = availableTimeSlots;
	}

	public String getTimeZoneCd() {
		return timeZoneCd;
	}

	public void setTimeZoneCd(String timeZoneCd) {
		this.timeZoneCd = timeZoneCd;
	}

	public List<AvailableInstallCreditTypeVO> getAvailableInstallCreditList() {
		return availableInstallCreditList;
	}

	public void setAvailableInstallCreditList(List<AvailableInstallCreditTypeVO> availableInstallCreditList) {
		this.availableInstallCreditList = availableInstallCreditList;
	}
	

	
	
	

}
