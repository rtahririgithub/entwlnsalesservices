package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AppointmentTimeList implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<AppointmentTimeDetail> appointmentTimeList;

	public List<AppointmentTimeDetail> getAppointmentTimeList() {
		return appointmentTimeList;
	}

	public void setAppointmentTimeList(List<AppointmentTimeDetail> appointmentTimeDetailList) {
		this.appointmentTimeList = appointmentTimeDetailList;
	}




	}