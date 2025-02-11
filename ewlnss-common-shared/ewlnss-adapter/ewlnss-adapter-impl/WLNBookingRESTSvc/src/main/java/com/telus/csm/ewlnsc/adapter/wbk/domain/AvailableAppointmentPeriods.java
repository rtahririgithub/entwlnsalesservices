package com.telus.csm.ewlnsc.adapter.wbk.domain;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonSerialize(include=JsonSerialize.Inclusion.NON_NULL)
public class AvailableAppointmentPeriods implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String endAppointmentTime;

    private String startAppointmentTime;

    public String getEndAppointmentTime ()
    {
        return endAppointmentTime;
    }

    public void setEndAppointmentTime (String endAppointmentTime)
    {
        this.endAppointmentTime = endAppointmentTime;
    }

    public String getStartAppointmentTime ()
    {
        return startAppointmentTime;
    }

    public void setStartAppointmentTime (String startAppointmentTime)
    {
        this.startAppointmentTime = startAppointmentTime;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [endAppointmentTime = "+endAppointmentTime+", startAppointmentTime = "+startAppointmentTime+"]";
    }
}
