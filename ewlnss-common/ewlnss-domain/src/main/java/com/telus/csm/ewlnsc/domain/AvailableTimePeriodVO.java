package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;

public class AvailableTimePeriodVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String startTime;
	String endTime;
	
	public AvailableTimePeriodVO(String startTime, String endTime) {
		super();
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
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
