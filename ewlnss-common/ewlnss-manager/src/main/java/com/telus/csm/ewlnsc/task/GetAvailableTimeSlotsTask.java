package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.IWLNBookingRestSvcAdapter;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterRequest;
import com.telus.csm.ewlnsc.adapter.wbk.domain.GetAvailableTimeSlotsAdapterResponse;

/**
 * @author Alejandro.Hernandez
 *
 */
public class GetAvailableTimeSlotsTask extends TaskBase {

	private GetAvailableTimeSlotsAdapterRequest input;
	private GetAvailableTimeSlotsAdapterResponse result;
	private IWLNBookingRestSvcAdapter adapter;
	
	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}
	public GetAvailableTimeSlotsAdapterResponse getResult(){
		rethrowException();
		return result;
	}

	public void setInput(GetAvailableTimeSlotsAdapterRequest input) {
		this.input = input;
	}

	public GetAvailableTimeSlotsAdapterRequest getInput(){
		return this.input;
	}

	public void setAdapter(IWLNBookingRestSvcAdapter adapter) {
		this.adapter = adapter;
	}

	public GetAvailableTimeSlotsTask(IWLNBookingRestSvcAdapter adapter,GetAvailableTimeSlotsAdapterRequest input) {
		this.adapter = adapter;
		this.input = input;
	}
	
	@Override
	protected void execute() {
		result = adapter.getAvailableTimeSlots(input);
	}

}