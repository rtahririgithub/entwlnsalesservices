package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class AppointmentTimeSlotVO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String startTime = null;
	private String endTime = null;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
