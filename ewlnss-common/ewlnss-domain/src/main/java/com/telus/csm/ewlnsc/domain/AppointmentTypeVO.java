package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.Date;


public class AppointmentTypeVO implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private String appointmentId = null;
	  
	  private AppointmentTimeSlotVO appointmentTimeSlot=null;

	  private Date appointmentDate = null;

	  /**
	   * Get appointmentId
	   * @return appointmentId
	  **/

	  public String getAppointmentId() {
	    return appointmentId;
	  }

	  public void setAppointmentId(String appointmentId) {
	    this.appointmentId = appointmentId;
	  }
	  /**
	   * Date of the appointment.
	   * @return appointmentDate
	  **/

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
