package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterRequest;
import com.telus.csm.ewlnsc.adapter.domain.GetAvailableServiceInstanceListAdapterResponse;
import com.telus.csm.ewlnsc.delegate.AvailableServiceInstanceListDelegate;
import commonj.work.Work;

public class GetAvailableServiceInstanceListTask extends TaskBase implements Work {

	private GetAvailableServiceInstanceListAdapterRequest input;
	private GetAvailableServiceInstanceListAdapterResponse result;

	public GetAvailableServiceInstanceListTask(GetAvailableServiceInstanceListAdapterRequest input){
		this.input = input;
	}

	public GetAvailableServiceInstanceListAdapterResponse getResult(){
		return result;
	}

	@Override
	public void execute() {
		result = AvailableServiceInstanceListDelegate.execute(input);
	}

	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}
}
