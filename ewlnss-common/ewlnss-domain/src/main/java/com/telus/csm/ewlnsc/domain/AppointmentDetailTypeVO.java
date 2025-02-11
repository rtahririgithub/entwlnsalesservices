package com.telus.csm.ewlnsc.domain;

import java.io.Serializable;
import java.util.List;

public class AppointmentDetailTypeVO implements Serializable{
	  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	  private AppointmentTypeVO bookedAppointmentDate = null;

	  private List<AppointmentTypeVO> preferredAppointmentDates = null;

	  /**
	   * Get bookedAppointmentDate
	   * @return bookedAppointmentDate
	  **/
	  public AppointmentTypeVO getBookedAppointmentDate() {
	    return bookedAppointmentDate;
	  }

	  public void setBookedAppointmentDate(AppointmentTypeVO bookedAppointmentDate) {
	    this.bookedAppointmentDate = bookedAppointmentDate;
	  }

	  /**
	   * Get preferredAppointmentDates
	   * @return preferredAppointmentDates
	  **/
	  public List<AppointmentTypeVO> getPreferredAppointmentDates() {
	    return preferredAppointmentDates;
	  }

	  public void setPreferredAppointmentDates(List<AppointmentTypeVO> preferredAppointmentDates) {
	    this.preferredAppointmentDates = preferredAppointmentDates;
	  }

	}

