package com.telus.csm.ewlnsc.adapter.fwa.transformer;

import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterResponse;
import com.telus.tmi.xmlschema.srv.rmo.processmgmt.fieldworkappointmentservicerequestresponse_v3.SearchAvailableAppointmentList;
import com.telus.tmi.xmlschema.srv.rmo.processmgmt.fieldworkappointmentservicerequestresponse_v3.SearchAvailableAppointmentListResponse;

public class Transformer {
	
	private Transformer(){
		
	}

	public static SearchAvailableAppointmentList transform(final FieldWorkAppointmentAdapterRequest param) {
		final SearchAvailableAppointmentList result = new SearchAvailableAppointmentList();
		
		result.setInputHeader(param.getInputHeader());
		result.setWorkOrder(param.getWorkOrder());
		result.setStartDate(param.getStartDate());
		result.setEndDate(param.getEndDate());
		result.setGradeAppointmentInd(param.getGradeAppointmentInd());
		result.setFullSearchInd(param.getFullSearchInd());
	    
	    return result;
	}

	public static FieldWorkAppointmentAdapterResponse transform(final SearchAvailableAppointmentListResponse param) {
		final FieldWorkAppointmentAdapterResponse result = new FieldWorkAppointmentAdapterResponse();
		
		result.setWorkOrder(param.getWorkOrder());
		result.setAvailableAppointmentList(param.getAvailableAppointmentList());
		result.setSuccessfulProcessInd(true);
		
		return result;
	}

}
