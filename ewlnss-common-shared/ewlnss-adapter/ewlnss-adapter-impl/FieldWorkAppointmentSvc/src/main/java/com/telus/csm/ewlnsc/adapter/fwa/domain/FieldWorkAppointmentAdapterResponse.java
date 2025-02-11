package com.telus.csm.ewlnsc.adapter.fwa.domain;

import java.util.List;

import com.telus.csm.ewlnss.adapter.domain.AdapterResponseBase;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.AvailableAppointment;
import com.telus.tmi.xmlschema.xsd.resource.resource.workforcemanagementordertypes_v3.WorkOrder;

public class FieldWorkAppointmentAdapterResponse extends AdapterResponseBase {

	private static final long serialVersionUID = 1L;

    private WorkOrder workOrder;
    private List<AvailableAppointment> availableAppointmentList;
    
	public WorkOrder getWorkOrder() {
		return workOrder;
	}
	
	public void setWorkOrder(WorkOrder workOrder) {
		this.workOrder = workOrder;
	}
	
	public List<AvailableAppointment> getAvailableAppointmentList() {
		return availableAppointmentList;
	}
	
	public void setAvailableAppointmentList(List<AvailableAppointment> availableAppointmentList) {
		this.availableAppointmentList = availableAppointmentList;
	}
	
    
}
