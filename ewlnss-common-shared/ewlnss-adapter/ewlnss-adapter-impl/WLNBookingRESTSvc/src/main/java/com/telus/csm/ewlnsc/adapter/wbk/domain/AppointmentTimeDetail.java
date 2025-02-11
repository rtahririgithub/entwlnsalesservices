package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AppointmentTimeDetail implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String appointmentDate;

    private String weekday;

    private List<AvailableAppointmentPeriods> availableAppointmentPeriods;

    private String timeZone;
    
    private String embargoinfo;
    
    

	public String getEmbargoinfo() {
		return embargoinfo;
	}

	public void setEmbargoinfo(String embargoinfo) {
		this.embargoinfo = embargoinfo;
	}

	public String getAppointmentDate() {
		if (this.appointmentDate != null)
			return appointmentDate;
		
		return "";
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getWeekday() {
		return weekday;
	}

	public void setWeekday(String weekday) {
		this.weekday = weekday;
	}

	public List<AvailableAppointmentPeriods> getAvailableAppointmentPeriods() {
		return availableAppointmentPeriods;
	}

	public void setAvailableAppointmentPeriods(List<AvailableAppointmentPeriods> availableAppointmentPeriods) {
		this.availableAppointmentPeriods = availableAppointmentPeriods;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}


}
