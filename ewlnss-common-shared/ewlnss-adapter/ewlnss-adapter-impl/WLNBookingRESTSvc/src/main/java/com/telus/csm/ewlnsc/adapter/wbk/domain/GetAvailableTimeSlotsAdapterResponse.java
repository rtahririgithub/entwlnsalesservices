package com.telus.csm.ewlnsc.adapter.wbk.domain;


import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.telus.csm.ewlnss.adapter.domain.WlnOPRestSvcResponseBase;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class GetAvailableTimeSlotsAdapterResponse extends WlnOPRestSvcResponseBase
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@JsonProperty("appointmentTimeList")
    private AppointmentTimeList appointmentTimeList;

    private String technicianVisitRequired;

	public AppointmentTimeList getAppointmentTimeList() {
		return appointmentTimeList;
	}

	public void setAppointmentTimeList(AppointmentTimeList appointmentTimeList) {
		this.appointmentTimeList = appointmentTimeList;
	}

	public String getTechnicianVisitRequired ()
    {
        return technicianVisitRequired;
    }

    public void setTechnicianVisitRequired (String technicianVisitRequired)
    {
        this.technicianVisitRequired = technicianVisitRequired;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [appointmentTimeList = "+appointmentTimeList+", technicianVisitRequired = "+technicianVisitRequired+"]";
    }
}
