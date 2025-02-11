package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.delegate.ServiceAddressDelegate;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;

import commonj.work.Work;

public class GetServiceAddressDetailsTask extends TaskBase implements Work {

	private ServiceAddressRequestVO input;
	private ServiceAddressResponseVO result;

	public GetServiceAddressDetailsTask(ServiceAddressRequestVO input){
		this.input = input;
	}

	public ServiceAddressResponseVO getResult(){
		return result;
	}

	@Override
	public void execute() {
		result = ServiceAddressDelegate.getServiceAddressDetails(input);
	}

	public Exception getRuntimeException(){
		if (this.runtimeException != null)
			return this.runtimeException;
		else
			return null;
	}
}
