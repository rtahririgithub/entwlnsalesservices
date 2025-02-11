package com.telus.csm.ewlnsc.task;

import com.telus.csm.ewlnsc.delegate.ServiceAddressDelegate;
import com.telus.csm.ewlnsc.domain.ServiceAddressRequestVO;
import com.telus.csm.ewlnsc.domain.ServiceAddressResponseVO;

public class ForeborneTask extends TaskBase {

	private ServiceAddressRequestVO input;
	private ServiceAddressResponseVO result;

	public ForeborneTask(final ServiceAddressRequestVO serviceAddressRequestVO) {
		this.input = serviceAddressRequestVO;
	}
	
	public ServiceAddressResponseVO getResult() {
		rethrowException();
		return result;
	}
	
	@Override
	protected void execute() {
		result = ServiceAddressDelegate.getServiceAddress(input);
	}

}
