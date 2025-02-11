package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IFieldWorkAppointmentSvcAdapter;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterRequest;
import com.telus.csm.ewlnsc.adapter.fwa.domain.FieldWorkAppointmentAdapterResponse;

import commonj.work.Work;


public class FieldWorkAppointmentTask extends TaskBase implements Work {
	
	private FieldWorkAppointmentAdapterRequest input;
	private FieldWorkAppointmentAdapterResponse result;
	private IFieldWorkAppointmentSvcAdapter adapter;

	public FieldWorkAppointmentTask(IFieldWorkAppointmentSvcAdapter adapter, FieldWorkAppointmentAdapterRequest input) {
		this.input=input;
		this.adapter = adapter;
	}
	
	public FieldWorkAppointmentAdapterRequest getInput() {
		return input;
	}

	public FieldWorkAppointmentAdapterResponse getResult() {
		rethrowException();
		return result;
	}

	@Override
	protected void execute() {
		result = adapter.searchAvailableAppointmentList(input);
	}

}
